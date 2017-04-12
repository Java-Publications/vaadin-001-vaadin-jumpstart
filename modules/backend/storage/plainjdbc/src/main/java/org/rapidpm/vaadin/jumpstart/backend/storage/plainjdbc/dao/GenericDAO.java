package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPool;

/**
 * Created by svenruppert on 11.04.17.
 */
public class GenericDAO implements DAO {
  private JDBCConnectionPool connectionPool;

  public void workOnPool(final JDBCConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public JDBCConnectionPool connectionPool() {
    return connectionPool;
  }
}
