package junit.org.rapidpm.vaadin.jumpstart.gui.uilogic.security;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.security.LoginService;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.security.User;

import junit.org.rapidpm.vaadin.jumpstart.gui.uilogic.BaseDDITest;

/**
 * Created by svenruppert on 06.04.17.
 */
public class LoginServiceImplTest extends BaseDDITest {

    @Test
    public void validateUser001()
        throws Exception {
        final Optional<User> user = DI.activateDI(LoginService.class)
            .loadUser(null, null);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void validateUser002()
        throws Exception {
        final Optional<User> user = DI.activateDI(LoginService.class)
            .loadUser(null, "XX");
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void validateUser003()
        throws Exception {
        final Optional<User> user = DI.activateDI(LoginService.class)
            .loadUser("XX", null);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void validateUser004()
        throws Exception {
        final Optional<User> user = DI.activateDI(LoginService.class)
            .loadUser("admin", "");
        Assert.assertEquals(User.NO_USER, user.get());
    }

    @Test
    public void validateUser005()
        throws Exception {
        final Optional<User> user = DI.activateDI(LoginService.class)
            .loadUser("", "admin");
        Assert.assertEquals(User.NO_USER, user.get());
    }

    @Test
    public void validateUser006()
        throws Exception {
        final Optional<User> user = DI.activateDI(LoginService.class)
            .loadUser("admin", "admin");
        Assert.assertTrue(user.isPresent());
    }

}