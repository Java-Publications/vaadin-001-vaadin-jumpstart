package junit.org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.vaadin.jumpstart.api.model.security.User;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPool;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security.UserDAO;

import java.util.Optional;

/**
 * Created by svenruppert on 11.04.17.
 */
public class UserDAOTest003 extends UserDAOBaseTest {

  @Test
  public void test001() throws Exception {
    final Optional<JDBCConnectionPool> connectionPoolOptional = pools().getPool(poolname());
    final JDBCConnectionPool connectionPool = connectionPoolOptional.get();

    final UserDAO userDAO = new UserDAO()
        .workOnPool(connectionPool);

    final Optional<User> user = userDAO.readUser(0);

    Assert.assertNotNull(user);
    Assert.assertTrue(user.isPresent());
    Assert.assertEquals("Sven", user.get().getFirstname());
  }
}
