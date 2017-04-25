package junit.org.rapidpm.vaadin.jumpstart.gui;

import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.MainUndertow;

/**
 * Created by svenruppert on 23.04.17.
 */
public class MicroserviceTestUtils {


    // TODO moce to MicroService client module
    public void setUpMicroserviceProperties() {
        final PortUtils portUtils = new PortUtils();

        System.setProperty(MainUndertow.REST_HOST_PROPERTY, Context.ipSupplierLocalIP.get());
        System.setProperty(MainUndertow.SERVLET_HOST_PROPERTY, Context.ipSupplierLocalIP.get());

        System.setProperty(MainUndertow.REST_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
        System.setProperty(MainUndertow.SERVLET_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
    }

}
