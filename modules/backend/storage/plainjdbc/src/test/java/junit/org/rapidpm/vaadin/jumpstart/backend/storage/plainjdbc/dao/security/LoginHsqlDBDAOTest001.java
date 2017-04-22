package junit.org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPool;
import org.rapidpm.vaadin.jumpstart.api.model.security.Login;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security.LoginHsqlDBDAO;

/**
 * Created by svenruppert on 13.04.17.
 */
public class LoginHsqlDBDAOTest001 extends LoginHsqlDBDAOBaseTest {
    @Test
    public void test001()
        throws Exception {
        final Optional<JDBCConnectionPool> connectionPoolOptional = pools().getPool(poolname());

        final JDBCConnectionPool connectionPool = connectionPoolOptional.get();

        LoginHsqlDBDAO dao = new LoginHsqlDBDAO();
        dao.workOnPool(connectionPool);

        final Login login = new Login(1_000, "username", "password", true, 0);
        dao.write(login);

        Optional<Login> read = dao.read(1_000);

        Assert.assertNotNull(read);
        Assert.assertTrue(read.isPresent());
        Assert.assertEquals(login.getUserID(), read.get().getUserID());
        Assert.assertEquals(login.getUsername(), read.get().getUsername());
    }

}
