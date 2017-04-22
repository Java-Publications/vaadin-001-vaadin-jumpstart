package junit.org.rapidpm.vaadin.jumpstart.gui;

import org.junit.BeforeClass;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.MainUndertow;

import com.vaadin.testbench.TestBenchTestCase;

/**
 * Created by svenruppert on 22.04.17.
 */
public class BaseMicroserviceTest extends TestBenchTestCase {
    @BeforeClass
    public static void setUpClass() {
        final PortUtils portUtils = new PortUtils();

        System.setProperty(MainUndertow.REST_HOST_PROPERTY, "127.0.0.1");
        System.setProperty(MainUndertow.SERVLET_HOST_PROPERTY, "127.0.0.1");

        System.setProperty(MainUndertow.REST_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
        System.setProperty(MainUndertow.SERVLET_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
    }
}
