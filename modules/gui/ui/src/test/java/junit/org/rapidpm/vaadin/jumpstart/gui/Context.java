package junit.org.rapidpm.vaadin.jumpstart.gui;

import java.util.function.Supplier;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;

/**
 * Created by svenruppert on 24.04.17.
 */
public interface Context {

// non running configs
//    Supplier<Boolean> chooseSelenium = () -> false;
//    Supplier<String> browserTypeSupplier = () -> BrowserType.FIREFOX;

// org.openqa.selenium.SessionNotCreatedException: Unable to create new remote session.
// desired capabilities = Capabilities [{browserName=phantomjs, version=, platform=LINUX}], required capabilities = Capabilities [{}]
//    Supplier<Boolean> chooseSelenium = () -> true;
//    Supplier<String> browserTypeSupplier = () -> BrowserType.PHANTOMJS;



    // running configs
//    Supplier<Boolean> chooseSelenium = () -> true;
//    Supplier<String> browserTypeSupplier = () -> BrowserType.FIREFOX;

//    Supplier<Boolean> chooseSelenium = () -> false;
//    Supplier<String> browserTypeSupplier = () -> BrowserType.PHANTOMJS;

//    Supplier<Boolean> chooseSelenium = () -> false;
//    Supplier<String> browserTypeSupplier = () -> BrowserType.CHROME;

    Supplier<Boolean> chooseSelenium = () -> true;
    Supplier<String> browserTypeSupplier = () -> BrowserType.CHROME;



    Supplier<Boolean> chooseLocale = () -> true;
    Supplier<Platform> platformSupplier = () -> Platform.LINUX;

//    Supplier<String> ipSupplier = () -> "localhost";
    Supplier<String> ipSupplier = () -> "192.168.2.47";
    //    Supplier<String> ipSupplier = () -> "10.64.12.132";
//    Supplier<String> ipSupplier = () -> "10.64.12.132"; external HUB IP to play with

}
