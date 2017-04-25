package junit.org.rapidpm.vaadin.jumpstart.gui;

import static org.rapidpm.frp.StringFunctions.notEmpty;
import static org.rapidpm.frp.StringFunctions.notStartsWith;
import static org.rapidpm.frp.Transformations.not;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.rapidpm.frp.Transformations;
import org.rapidpm.frp.functions.CheckedSupplier;
import org.rapidpm.frp.model.Quad;

/**
 * Created by svenruppert on 24.04.17.
 */
public interface Context {

    Supplier<String> ipSupplierLocalIP = () -> {
        final CheckedSupplier<Enumeration<NetworkInterface>> checkedSupplier = NetworkInterface::getNetworkInterfaces;

        return Transformations.<NetworkInterface>enumToStream()
            .apply(checkedSupplier.getOrElse(Collections::emptyEnumeration))
            .map(NetworkInterface::getInetAddresses)
            .flatMap(iaEnum -> Transformations.<InetAddress>enumToStream().apply(iaEnum))
            .filter(inetAddress -> inetAddress instanceof Inet4Address)
            .filter(not(InetAddress::isMulticastAddress))
            .map(InetAddress::getHostAddress)
            .filter(notEmpty())
            .filter(adr -> notStartsWith().apply(adr, "127"))
            .filter(adr -> notStartsWith().apply(adr, "169.254"))
            .filter(adr -> notStartsWith().apply(adr, "255.255.255.255"))
            .filter(adr -> notStartsWith().apply(adr, "255.255.255.255"))
            .filter(adr -> notStartsWith().apply(adr, "0.0.0.0"))
            //            .filter(adr -> range(224, 240).noneMatch(nr -> adr.startsWith(valueOf(nr))))
            .findFirst().orElse("localhost");
    };

    // IP Vaadin Selenium Hub  http://tb3-hub.intra.itmill.com/

    Supplier<Platform> platformLinux = () -> Platform.LINUX;
    Supplier<Platform> platformWindows = () -> Platform.WINDOWS;

    Supplier<String> browserFireFox = () -> BrowserType.FIREFOX;
    Supplier<String> browserChrome = () -> BrowserType.CHROME;
    Supplier<String> browserPhantomJS = () -> BrowserType.PHANTOMJS;

    Supplier<String> localHost = ipSupplierLocalIP;
    Supplier<String> seleniumHubLocal = ipSupplierLocalIP;
    Supplier<String> seleniumHubVaadin = () -> "tb3-hub.intra.itmill.com";

    Supplier<Boolean> runningLocalBrowser = () -> Boolean.TRUE; // on local installed Browser
    Supplier<Boolean> runningSeleniumHub = () -> Boolean.FALSE; // inside SeleniumHub

    static <T1, T2, T3, T4> Quad<T1, T2, T3, T4> nextQuad(T1 a, T2 b, T3 c, T4 d) {
        return new Quad<>(a, b, c, d);
    }

    //browserType, platform, runningLocal, seleniumHubIP
    Supplier<
        Stream<
            Quad<
                Supplier<String>,
                Supplier<Platform>,
                Supplier<Boolean>,
                Supplier<String>>>> streamOfConfig = () ->
        Stream.of(
            nextQuad(browserChrome, platformLinux, runningLocalBrowser, localHost),
            nextQuad(browserFireFox, platformLinux, runningLocalBrowser, localHost),
            nextQuad(browserPhantomJS, platformLinux, runningLocalBrowser, localHost),

            nextQuad(browserChrome, platformLinux, runningSeleniumHub, seleniumHubLocal),
            nextQuad(browserFireFox, platformLinux, runningSeleniumHub, seleniumHubLocal),
            nextQuad(browserPhantomJS, platformLinux, runningSeleniumHub, seleniumHubLocal),

            nextQuad(browserChrome, platformLinux, runningSeleniumHub, seleniumHubVaadin),
            nextQuad(browserFireFox, platformLinux, runningSeleniumHub, seleniumHubVaadin),
            nextQuad(browserPhantomJS, platformLinux, runningSeleniumHub, seleniumHubVaadin),


            nextQuad(browserChrome, platformWindows, runningSeleniumHub, seleniumHubVaadin), // exception
            nextQuad(browserFireFox, platformWindows, runningSeleniumHub, seleniumHubVaadin), // exception
            nextQuad(browserPhantomJS, platformWindows, runningSeleniumHub, seleniumHubVaadin)

//            nextQuad(browserChrome, platformWindows, runningSeleniumHub, seleniumHubVaadin),// exception test002
//            nextQuad(browserFireFox, platformWindows, runningSeleniumHub, seleniumHubVaadin), // exception
//            nextQuad(browserPhantomJS, platformWindows, runningSeleniumHub, seleniumHubVaadin)// exception
        );
}

