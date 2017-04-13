package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPool;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by svenruppert on 11.04.17.
 */
public interface BasicJDBCOperation<T> extends BasicOperation {

    //BiFunction -> Optional
    Optional<T> executeJDBCMethod(final Statement statement, final String sql)
        throws SQLException;

    default Optional<T> executeJDBC(JDBCConnectionPool connectionPool) {
        final HikariDataSource dataSource = connectionPool.getDataSource();
        try {
            final Connection connection = dataSource.getConnection();
            final Optional<T> returnValue;
            try (final Statement statement = connection.createStatement()) {
                returnValue = executeJDBCMethod(statement, createSQL());
                statement.close();
            }
            dataSource.evictConnection(connection);
            return returnValue;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
