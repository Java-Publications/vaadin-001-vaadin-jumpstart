package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;

import org.rapidpm.vaadin.jumpstart.api.model.security.User;

/**
 * Created by svenruppert on 11.04.17.
 */
public class Login {

  private int id;
  private String username;
  private String password;
  private boolean valid;

  private User user;


  public Login(int id, String username, String password, boolean valid, User user) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.valid = valid;
    this.user = user;
  }

  public Login id(final int id) {
    this.id = id;
    return this;
  }

  public Login username(final String username) {
    this.username = username;
    return this;
  }

  public Login password(final String password) {
    this.password = password;
    return this;
  }

  public Login valid(final boolean valid) {
    this.valid = valid;
    return this;
  }

  public Login user(final User user) {
    this.user = user;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public boolean isValid() {
    return valid;
  }

  public User getUser() {
    return user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (! (o instanceof Login)) return false;

    Login login = (Login) o;

    if (id != login.id) return false;
    if (valid != login.valid) return false;
    if (username != null ? ! username.equals(login.username) : login.username != null) return false;
    if (password != null ? ! password.equals(login.password) : login.password != null) return false;
    return user != null ? user.equals(login.user) : login.user == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (valid ? 1 : 0);
    result = 31 * result + (user != null ? user.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Login{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", valid=" + valid +
        ", user=" + user +
        '}';
  }
}
