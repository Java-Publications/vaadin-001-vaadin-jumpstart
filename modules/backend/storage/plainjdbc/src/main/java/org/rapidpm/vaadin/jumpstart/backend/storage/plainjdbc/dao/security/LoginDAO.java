package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.CrudDAO;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud.QueryOneValue;

/**
 * Created by svenruppert on 11.04.17.
 */
public interface LoginDAO extends CrudDAO<Login> {

    Function<ResultSet, Optional<Login>> MAP_RESULT_SET_TO_TYPE = (resultSet) -> {
        try {
            return Optional.of(new Login(resultSet.getInt("ID"),
                resultSet.getString("USERNAME"),
                resultSet.getString("PASSWORD"),
                resultSet.getBoolean("VALID"),
                resultSet.getInt("CUSTOMER_ID")));
        } catch (SQLException e) {
            // wrap Exception into a Result container
            return Optional.empty();
        }
    };

    interface QueryOneLogin extends QueryOneValue<Login> {
        @Override
        default Function<ResultSet, Optional<Login>> createMappingFunction() {
            return MAP_RESULT_SET_TO_TYPE;
        }
    }

    @Override
    default Optional<Login> read(int id) {
        return ((QueryOneLogin) () -> createReadSql(id))
            .executeJDBC(connectionPool());
    }
}