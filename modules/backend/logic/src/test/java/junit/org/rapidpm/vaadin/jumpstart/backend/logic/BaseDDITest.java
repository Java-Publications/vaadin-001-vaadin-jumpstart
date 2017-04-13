package junit.org.rapidpm.vaadin.jumpstart.backend.logic;

import org.junit.After;
import org.junit.Before;
import org.rapidpm.ddi.DI;

/**
 * Created by svenruppert on 06.04.17.
 */
public class BaseDDITest {

    @Before
    public void setUp()
        throws Exception {
        DI.clearReflectionModel();
        DI.activatePackages("org.rapidpm");
        DI.activatePackages(this.getClass().getPackage().getName());
    }

    @After
    public void tearDown()
        throws Exception {
        DI.clearReflectionModel();
    }
}
