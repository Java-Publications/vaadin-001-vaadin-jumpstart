package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import org.rapidpm.vaadin.jumpstart.api.model.security.User;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.GenericDAO;

/**
 * Created by svenruppert on 11.04.17.
 */
public class UserHsqlDBDAO extends GenericDAO implements UserDAO {

  public String deleteString(User value) {
    throw new RuntimeException("not yet implemented");
  }

  public String createInsert(final User user) {
    final String sql = "INSERT INTO CUSTOMER " +
        "( CUSTOMER_ID, FIRSTNAME, LASTNAME, EMAIL ) " +
        " VALUES " +
        "( " +
        user.getCustomerID() + ", " +
        "'" + user.getFirstname() + "', " +
        "'" + user.getLastname() + "' , " +
        "'" + user.getEmail() + "' " +
        ")";
    return sql;
  }

  public String createMailByCustomerID(int customerID) {
    return "SELECT EMAIL FROM CUSTOMER WHERE CUSTOMER_ID = " + customerID + "";
  }



  public String createReadSql(int customerID) {
    return "SELECT CUSTOMER_ID, FIRSTNAME, LASTNAME, EMAIL " +
        " FROM CUSTOMER " +
        " WHERE CUSTOMER_ID = " + customerID + "";
  }


}
