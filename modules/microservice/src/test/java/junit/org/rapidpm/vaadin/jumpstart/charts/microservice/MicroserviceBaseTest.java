package junit.org.rapidpm.vaadin.jumpstart.charts.microservice;

import org.junit.After;
import org.junit.Before;
import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;
import org.rapidpm.microservice.test.RestUtils;

/**
 * Created by svenruppert on 04.04.17.
 */
public class MicroserviceBaseTest {


  @Before
  public void setUp() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages(this.getClass());
    Main.deploy();
  }

  @After
  public void tearDown() throws Exception {
    Main.stop();
    DI.clearReflectionModel();
  }

  public String generateBasicReqURL(Class restClass) {
    final String restAppPath = MainUndertow.CONTEXT_PATH_REST;
    return new RestUtils().generateBasicReqURL(restClass, restAppPath);
  }


}
