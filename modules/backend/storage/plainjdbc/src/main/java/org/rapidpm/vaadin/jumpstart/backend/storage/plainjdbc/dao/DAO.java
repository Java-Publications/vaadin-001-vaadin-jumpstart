package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPool;

/**
 * Created by svenruppert on 11.04.17.
 */

@FunctionalInterface
public interface DAO {
  //Supplier -> JDBCConnectionPool
  JDBCConnectionPool connectionPool();
}
