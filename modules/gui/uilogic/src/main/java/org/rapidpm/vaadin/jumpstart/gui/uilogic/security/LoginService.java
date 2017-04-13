package org.rapidpm.vaadin.jumpstart.gui.uilogic.security;

/**
 * Created by svenruppert on 06.04.17.
 */

import java.util.Optional;

public interface LoginService {

    boolean isLoginAllowed(String username, String password);

    Optional<User> loadUser(String username, String password);

}
