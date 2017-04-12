package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.GenericDAO;

/**
 * Created by svenruppert on 12.04.17.
 */
public class LoginHsqlDBDAO extends GenericDAO implements LoginDAO {
  @Override
  public String createReadSql(int id) {
    return null;
  }

  @Override
  public String createInsert(Login value) {
    return null;
  }

  @Override
  public String deleteString(Login value) {
    return null;
  }
}
