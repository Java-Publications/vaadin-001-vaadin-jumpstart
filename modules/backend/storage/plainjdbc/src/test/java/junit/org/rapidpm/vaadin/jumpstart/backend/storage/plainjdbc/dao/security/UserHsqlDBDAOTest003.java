package junit.org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.vaadin.jumpstart.api.model.security.User;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPool;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security.UserDAO;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security.UserHsqlDBDAO;

import java.util.Optional;

/**
 * Created by svenruppert on 11.04.17.
 */
public class UserHsqlDBDAOTest003 extends UserHsqlDBDAOBaseTest {

  @Test
  public void test001() throws Exception {
    final Optional<JDBCConnectionPool> connectionPoolOptional = pools().getPool(poolname());
    final JDBCConnectionPool connectionPool = connectionPoolOptional.get();

    final UserHsqlDBDAO userDAO = new UserHsqlDBDAO();
    userDAO.workOnPool(connectionPool);

    final Optional<User> user = userDAO.read(0);

    Assert.assertNotNull(user);
    Assert.assertTrue(user.isPresent());
    Assert.assertEquals("Sven", user.get().getFirstname());
  }
}
