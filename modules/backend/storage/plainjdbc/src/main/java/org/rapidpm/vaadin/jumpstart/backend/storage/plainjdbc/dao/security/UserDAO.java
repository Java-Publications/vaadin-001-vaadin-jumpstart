package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import org.rapidpm.vaadin.jumpstart.api.model.security.User;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.CrudDAO;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud.QueryOneTypedValue;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud.QueryOneValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by svenruppert on 11.04.17.
 */
public interface UserDAO extends CrudDAO<User> {

  String createMailByCustomerID(int customerID);

  default Optional<String> readMailAddress(int customerID) {
    return ((QueryOneTypedValue.QueryOneString) () -> createMailByCustomerID(customerID)).executeJDBC(connectionPool());
  }

  Function<ResultSet, Optional<User>> MAP_RESULT_SET_TO_TYPE = (resultSet) -> {
    try {
      return Optional.of(new User(
          resultSet.getInt("CUSTOMER_ID"),
          resultSet.getString("FIRSTNAME"),
          resultSet.getString("LASTNAME"),
          resultSet.getString("EMAIL")
      ));
    } catch (SQLException e) {
      // wrap Exception into a Result container
      return Optional.empty();
    }
  };

  interface QueryOneUser extends QueryOneValue<User> {
    @Override
    default Function<ResultSet, Optional<User>> createMappingFunction() {
      return MAP_RESULT_SET_TO_TYPE;
    }
  }

  //String createReadSql(int customerID);

  @Override
  default Optional<User> read(int id) {
    return ((QueryOneUser) () -> createReadSql(id)).executeJDBC(connectionPool());
  }
}
