package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import org.rapidpm.vaadin.jumpstart.api.model.security.Login;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.GenericDAO;

/**
 * Created by svenruppert on 12.04.17.
 */
public class LoginHsqlDBDAO extends GenericDAO implements LoginDAO {

    @Override
    public String createReadSql(int id) {
        return "SELECT ID, USERNAME, PASSWORD, VALID, CUSTOMER_ID " + "FROM LOGIN L " + "WHERE L.ID=" + id;
    }

    @Override
    public String createInsert(Login value) {
        return "INSERT INTO LOGIN (ID, USERNAME, PASSWORD, VALID, CUSTOMER_ID) " +

            "VALUES " + value.getId() + ", " + "'" + value.getUsername() + "'" + ", " + "'" + value.getPassword() + "'" + ", " + value.isValid() + ", " + value.getUserID();
    }

    @Override
    public String deleteString(Login value) {
        throw new RuntimeException("not yet implemented");
    }
}
