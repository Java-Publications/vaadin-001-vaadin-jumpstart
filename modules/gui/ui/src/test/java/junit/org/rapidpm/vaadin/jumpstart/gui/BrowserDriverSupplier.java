package junit.org.rapidpm.vaadin.jumpstart.gui;

import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.success;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;
import java.util.function.Supplier;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.rapidpm.frp.functions.QuadFunction;
import org.rapidpm.frp.model.Result;

import com.vaadin.testbench.TestBench;

/**
 * Created by svenruppert on 23.04.17.
 */
public interface BrowserDriverSupplier /*extends Supplier<Result<Optional<WebDriver>>> */ {

    //    public static final String VAADIN_TESTBENCH_DRIVER_PROPERTY = "vaadin.testbench.driver";

    Function<String, Result<WebDriver>> webdriver = browserType -> match(
        matchCase(() -> success(new PhantomJSDriver())),
        //            Case.matchCase(() -> browserType == null, () -> Result.failure("browserTape should not be null")),
        matchCase(browserType::isEmpty, () -> Result.failure("browserTape should not be emtpy")),
        matchCase(() -> browserType.equals(BrowserType.PHANTOMJS), () -> success(new PhantomJSDriver())),
        matchCase(() -> browserType.equals(BrowserType.FIREFOX), () -> success(new FirefoxDriver())),
        matchCase(() -> browserType.equals(BrowserType.CHROME), () -> success(new ChromeDriver())),
        matchCase(() -> browserType.equals(BrowserType.IE), () -> success(new InternetExplorerDriver())));

    Function<String, Result<DesiredCapabilities>> desiredCapabilities = (browsertype) -> match(
        matchCase(() -> success(DesiredCapabilities.phantomjs())),
        //            Case.matchCase(() -> browsertype == null, () -> Result.failure("browsertype should not be null")),
        matchCase(browsertype::isEmpty, () -> Result.failure("browsertype should not be empty")),
        matchCase(() -> browsertype.equals(BrowserType.PHANTOMJS), () -> success(DesiredCapabilities.phantomjs())),
        matchCase(() -> browsertype.equals(BrowserType.FIREFOX), () -> success(DesiredCapabilities.firefox())),
        matchCase(() -> browsertype.equals(BrowserType.CHROME), () -> success(DesiredCapabilities.chrome())),
        matchCase(() -> browsertype.equals(BrowserType.IE), () -> success(DesiredCapabilities.internetExplorer())));

    QuadFunction<Supplier<String>, Supplier<Platform>, Supplier<Boolean>, Supplier<String>, Result<WebDriver>> webDriver
        = (browserType, platform, runningLocal, seleniumHubIP) ->
        match(
            matchCase(() -> {
                initSystemProperties(); //
                return webdriver.apply(browserType.get());
            }),
//            matchCase(() -> runningLocal.get() , () -> Result.failure("remote MicroService not supported until now")),
            matchCase( ()-> !runningLocal.get(), () -> {
                final Result<DesiredCapabilities> capabilities = desiredCapabilities.apply(browserType.get());
                capabilities.bind(
                    success -> success.setPlatform(platform.get()),
                    error -> System.out.println("error to log = " + error));

                return match(matchCase(() -> {
                                 final DesiredCapabilities desiredCapabilities = capabilities.get();
                                 try {
                                     final URL url = new URL("http://" + seleniumHubIP.get() + ":4444/wd/hub");
                                     final RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, desiredCapabilities);
                                     final WebDriver webDriver = TestBench.createDriver(remoteWebDriver);
                                     return success(webDriver);
                                 } catch (MalformedURLException e) {
                                     return Result.failure("url not correct " + e.getMessage());
                                 }
                             }),
                             matchCase(() -> !capabilities.isPresent(), () -> Result.failure("capabilities are absent")));
            }));

    // for local driver - maven plugin for download ???
    public static final String DATA_DRIVER_BASE_FOLDER = "/_data/driver/";

    public static void initSystemProperties() {
        final String pointToStartFrom = new File("").getAbsolutePath();
        final String OS = "osx";
        String basePath = pointToStartFrom + DATA_DRIVER_BASE_FOLDER + OS;
        System.setProperty("webdriver.chrome.driver", basePath + "/chrome/chromedriver");
        System.setProperty("webdriver.gecko.driver", basePath + "/gecko/geckodriver");
        System.setProperty("phantomjs.binary.path", basePath + "/phantomjs/phantomjs");
    }

}
