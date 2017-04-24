package junit.org.rapidpm.vaadin.jumpstart.gui;

import static java.util.Optional.of;
import static junit.org.rapidpm.vaadin.jumpstart.gui.Context.browserTypeSupplier;
import static junit.org.rapidpm.vaadin.jumpstart.gui.Context.chooseLocale;
import static junit.org.rapidpm.vaadin.jumpstart.gui.Context.chooseSelenium;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.success;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
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
import org.rapidpm.frp.model.Result;

import com.vaadin.testbench.TestBench;

/**
 * Created by svenruppert on 23.04.17.
 */
public class BrowserDriverSupplier implements Supplier<Result<Optional<WebDriver>>> {

    public static final String VAADIN_TESTBENCH_DRIVER_PROPERTY = "vaadin.testbench.driver";

    public static final String DATA_DRIVER_BASE_FOLDER = "/_data/driver/";

    private static final Function<String, Result<Optional<WebDriver>>> webdriver = browserType -> match(
        matchCase(() -> success(of(new PhantomJSDriver()))),
        //            Case.matchCase(() -> browserType == null, () -> Result.failure("browserTape should not be null")),
        matchCase(browserType::isEmpty, () -> Result.failure("browserTape should not be emtpy")),
        matchCase(() -> browserType.equals(BrowserType.PHANTOMJS), () -> success(of(new PhantomJSDriver()))),
        matchCase(() -> browserType.equals(BrowserType.FIREFOX), () -> success(of(new FirefoxDriver()))),
        matchCase(() -> browserType.equals(BrowserType.CHROME), () -> success(of(new ChromeDriver()))),
        matchCase(() -> browserType.equals(BrowserType.IE), () -> success(of(new InternetExplorerDriver()))));


    private static final Function<String, Result<Optional<DesiredCapabilities>>> desiredCapabilities = (browsertype) -> match(
        matchCase(() -> success(of(DesiredCapabilities.phantomjs()))),
//            Case.matchCase(() -> browsertype == null, () -> Result.failure("browsertype should not be null")),
        matchCase(browsertype::isEmpty, () -> Result.failure("browsertype should not be empty")),
        matchCase(() -> browsertype.equals(BrowserType.PHANTOMJS), () -> success(of(DesiredCapabilities.phantomjs()))),
        matchCase(() -> browsertype.equals(BrowserType.FIREFOX), () -> success(of(DesiredCapabilities.firefox()))),
        matchCase(() -> browsertype.equals(BrowserType.CHROME), () -> success(of(DesiredCapabilities.chrome()))),
        matchCase(() -> browsertype.equals(BrowserType.IE), () -> success(of(DesiredCapabilities.internetExplorer()))));

    // transform to curried function
    public Result<Optional<WebDriver>> get() {
        return match(
            matchCase(() -> {
                initSystemProperties(); //
                return webdriver.apply(browserTypeSupplier.get());
            }),
            matchCase(() -> !chooseLocale.get() && !chooseSelenium.get(), () -> Result.failure("remote but not selenium is not supported")),
            matchCase(chooseSelenium::get, () -> {
                final Result<Optional<DesiredCapabilities>> capabilities = desiredCapabilities.apply(browserTypeSupplier.get());
                capabilities.bind(
                    success -> success.ifPresent((c) -> c.setPlatform(Context.platformSupplier.get())),
                    error -> System.out.println("error to log = " + error));

                return match(matchCase(() -> {
                        final DesiredCapabilities desiredCapabilities = capabilities.get().get();
                        try {
                            final URL url = new URL("http://" + Context.ipSupplier.get() + ":4444/wd/hub");
                            final RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, desiredCapabilities);
                            final WebDriver webDriver = TestBench.createDriver(remoteWebDriver);
                            return success(of(webDriver));
                        } catch (MalformedURLException e) {
                            return Result.failure("url not correct " + e.getMessage());
                        }
                    }),
                    matchCase(() -> !capabilities.isPresent(), () -> Result.failure("capabilities are absent")),
                    matchCase(() -> capabilities.isPresent() && !capabilities.get().isPresent(), () -> Result.failure("capabilities are absent")));
            }));
    }

    private void initSystemProperties() {
        final String pointToStartFrom = new File("").getAbsolutePath();
        final String OS = "osx";
        String basePath = pointToStartFrom + DATA_DRIVER_BASE_FOLDER + OS;
        System.setProperty("webdriver.chrome.driver", basePath + "/chrome/chromedriver");
        System.setProperty("webdriver.gecko.driver", basePath + "/gecko/geckodriver");
        System.setProperty("phantomjs.binary.path", basePath + "/phantomjs/phantomjs");
    }

}
