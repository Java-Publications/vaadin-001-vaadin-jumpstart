package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.BasicJDBCOperation;

/**
 * Created by svenruppert on 11.04.17.
 */
public interface Execute extends BasicJDBCOperation<Boolean> {

    default Optional<Boolean> executeJDBCMethod(final Statement statement,
        final String sql)
        throws SQLException {
        return Optional.of(statement.execute(sql));
    }

}
