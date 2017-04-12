package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud.Execute;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud.QueryOneValue;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud.Update;

import java.util.Optional;

/**
 * Created by svenruppert on 11.04.17.
 */
public interface CrudDAO<T> extends DAO {

  String createReadSql(int id);

  //Function int -> T
  default Optional<T> read(int id) {
    return ((QueryOneValue<T>) () -> createReadSql(id)).executeJDBC(connectionPool());
  }

  String createInsert(T value);

  //Consumer
  default void write(T value) {
    ((Update) () -> createInsert(value)).executeJDBC(connectionPool());
  }

  String deleteString(T value);

  //Function T -> boolean
  default Optional<Boolean> delete(T value) {
    return ((Execute) () -> deleteString(value)).executeJDBC(connectionPool());
  }

}
