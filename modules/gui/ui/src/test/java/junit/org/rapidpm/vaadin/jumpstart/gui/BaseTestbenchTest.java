package junit.org.rapidpm.vaadin.jumpstart.gui;

import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.success;
import static org.rapidpm.microservice.MainUndertow.DEFAULT_SERVLET_PORT;
import static org.rapidpm.microservice.MainUndertow.MYAPP;
import static org.rapidpm.microservice.MainUndertow.SERVLET_PORT_PROPERTY;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.rapidpm.ddi.DI;
import org.rapidpm.frp.matcher.Case;
import org.rapidpm.frp.model.Result;
import org.rapidpm.microservice.Main;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.testbench.TestBenchDriverProxy;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Created by svenruppert on 07.04.17.
 */
public class BaseTestbenchTest extends TestBenchTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestbenchTest.class);

    @Rule public TestName testName = new TestName();

    @BeforeClass
    public static void setUpClass() {
        new MicroserviceTestUtils().setUpMicroserviceProperties();
    }

    @Before
    public void setUpBase()
        throws Exception {
        DI.clearReflectionModel();
        DI.activatePackages("org.rapidpm");
        DI.activatePackages(this.getClass());
        DI.activateDI(this);
        Main.deploy();
        setUpTestbench();
        saveScreenshot("001_before");
    }

    public void setUpTestbench() throws Exception {

        new BrowserDriverSupplier()
            .get()
            .ifPresent(w -> w.ifPresent(this::setDriver));
        getDriver().get(baseURL() + "?restartApplication");
        if (getDriver() instanceof PhantomJSDriver) {
            final Capabilities remoteWebDriverCapabilities = ((RemoteWebDriver) getDriver()).getCapabilities();
            if (remoteWebDriverCapabilities != null)
                if (remoteWebDriverCapabilities.getBrowserName().equals(BrowserType.PHANTOMJS)) {
                    getCommandExecutor().resizeViewPortTo(1280, 768);
                }
        }
        getCommandExecutor().enableWaitForVaadin();
//        String pageSource = getDriver().getPageSource();
//        String errorMsg = "Application is not available at " + baseURL() + ". Server not started?";
//        Assert.assertFalse(errorMsg, pageSource.contains("HTTP Status 404") || pageSource.contains("can't establish a connection to the server"));
    }

    @After
    public void tearDown()
        throws Exception {
        saveScreenshot("002_after");
        tearDownTestbench();
        Main.stop();
        DI.clearReflectionModel();
    }

    public void tearDownTestbench() throws Exception {
        WebDriver driver = getDriver();
        if (driver != null) driver.quit();
    }
    //TestBenchCommandExecutor

    public Function<TestBenchDriverProxy, Result<byte[]>> takeScreenshot = (proxy) -> {
        final WebDriver actualDriver = proxy.getActualDriver();
        return Case.match(
            Case.matchCase(() -> Result.success(((TakesScreenshot) actualDriver).getScreenshotAs(OutputType.BYTES))),
            Case.matchCase(() -> actualDriver == null, () -> Result.failure("actualDriver is null")),
            Case.matchCase(() -> !(actualDriver instanceof TakesScreenshot), () -> Result.failure("actualDriver is not instanceof TakesScreenshot")));
    };

    protected void saveScreenshot(String name)
        throws IOException {
        String fileName = String.format("%s_%s.png", getClass().getSimpleName() + "_" + testName.getMethodName(), name);

        final WebDriver webDriver = getDriver();
        match(
            matchCase(() -> success(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES))),
            matchCase(() -> webDriver instanceof RemoteWebDriver, () -> success(((RemoteWebDriver) webDriver).getScreenshotAs(OutputType.BYTES))),
            matchCase(() -> webDriver instanceof TestBenchDriverProxy, ()-> takeScreenshot.apply((TestBenchDriverProxy)webDriver)))
            .bind(bytes -> {
                File file = new File("target", fileName);
                try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.warn(e.getMessage());
                }
            }, LOGGER::warn);
    }

    public String baseURL() {
        final String key = SERVLET_PORT_PROPERTY;
        final String actualUsedServletPort = System.getProperty(key) == null ? DEFAULT_SERVLET_PORT + "" : System.getProperty(key);
        return "http://"+Context.ipSupplier.get()+":" + actualUsedServletPort + "/" + MYAPP;
    }

}
