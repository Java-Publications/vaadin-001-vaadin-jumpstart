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
import org.rapidpm.frp.model.Pair;

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


    static <T1,T2> Pair<T1, T2> nextPair(T1 a, T2 b) {
        return new Pair<>(a, b);
    }

    Supplier<Stream<Pair<Supplier<Boolean>, Supplier<String>>>> streamOfPair = () -> Stream
        .of(nextPair(() -> true, () -> BrowserType.FIREFOX),
            nextPair(() -> true, () -> BrowserType.CHROME),
//            nextPair(() -> true, () -> BrowserType.PHANTOMJS),
//            nextPair(() -> false, () -> BrowserType.FIREFOX),
            nextPair(() -> false, () -> BrowserType.CHROME),
            nextPair(() -> false, () -> BrowserType.PHANTOMJS));
    //

    //working config
//    Supplier<Boolean> chooseSelenium = () -> true;
//    Supplier<String> browserTypeSupplier = () -> BrowserType.FIREFOX;

    Supplier<Boolean> chooseLocale = () -> true;
    Supplier<Platform> platformSupplier = () -> Platform.LINUX;

    //    Supplier<String> ipSupplier = () -> "localhost";
    //    Supplier<String> ipSupplier = () -> "192.168.2.47";
    //    Supplier<String> ipSupplier = () -> "10.64.12.132"; external HUB IP to play with

    Supplier<String> ipSupplier = () -> {
        /*
        127.xxx.xxx.xxx
        169.254.xxx.xxx
        224.xxx.xxx.xxx - 239.xxx.xxx.xxx
        255.255.255.255
         */
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

}

