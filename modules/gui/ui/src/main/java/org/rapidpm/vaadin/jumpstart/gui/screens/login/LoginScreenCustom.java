package org.rapidpm.vaadin.jumpstart.gui.screens.login;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.rapidpm.ddi.DI;
import org.rapidpm.frp.core.model.Result;
import org.rapidpm.frp.core.model.matcher.Case;
import org.rapidpm.vaadin.jumpstart.gui.basics.MainWindow;
import org.rapidpm.vaadin.jumpstart.gui.design.login.LoginScreen;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.security.LoginService;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.security.User;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static java.util.Arrays.asList;

/**
 * Created by svenruppert on 06.04.17.
 */
public class LoginScreenCustom extends LoginScreen {

  //how to make this more comfortable for the developer ?
  public static final String LOGIN_SCREEN = "loginScreen";
  public static final String USERNAME_FIELD = "tfUsername";
  public static final String PASSWORD_FIELD = "pfPassword";
  public static final String LANGUAGE_COMBO = "cbLanguage";
  public static final String LOGIN_BUTTON = "btnLogin";
  public static final String USERNAME = "username";
  public static final String LANGUAGE_SESSION_ATTRIBUTE = "language";

  @Inject LoginService loginService;
  @Inject PropertyService propertyService;
//  @Inject MainWindow mainWindow;

  public LoginScreenCustom() {
    this.setId(LOGIN_SCREEN);
    tfUsername.setId(USERNAME_FIELD);
    pfPassword.setId(PASSWORD_FIELD);
    btnLogin.setId(LOGIN_BUTTON);

    setSizeFull();
    setSizeUndefined();
    tfUsername.focus();

    //languageBox.setImmediate(true);
//    languageBox.setNullSelectionAllowed(false);
    cbLanguage.setTextInputAllowed(false);
  }

//  private final Function<Tuple<String, String>, Result<Optional<User>>> validate = (Tuple<String, String> input) -> {
//    return Case.match(
//        Case.matchCase(() -> Result.success(loginService.loadUser(input.getT1(), input.getT2()))), // Default Case
//        Case.matchCase(input.getT1()::isEmpty, () -> Result.failure(resolve("login.failed.description.empty.username"))),
//        Case.matchCase(input.getT2()::isEmpty, () -> Result.failure(resolve("login.failed.description.empty.password"))),
//        Case.matchCase(() -> loginService.isLoginAllowed(
//            input.getT1(), input.getT2()), () -> Result.failure(resolve("login.failed.description")))
//    );
//  };


  @PostConstruct
  public void postconstruct() {

    cbLanguage.setId(LANGUAGE_COMBO);
    cbLanguage.setItems(
        asList(
            resolve("login.language.en"),
            resolve("login.language.de"))
    );

    cbLanguage.setValue(resolve("login.language.en"));

    tfUsername.setCaption(resolve("login.username"));
    tfUsername.setValue("");
    pfPassword.setCaption(resolve("login.password"));
    pfPassword.setValue("");

    btnLogin.setCaption(resolve("login.name"));
    btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    btnLogin.setDescription(resolve("login.info"));
    btnLogin.addClickListener(clickEvent -> {
      final String username = tfUsername.getValue();
      final String password = pfPassword.getValue();

      VaadinSession.getCurrent().setAttribute(LANGUAGE_SESSION_ATTRIBUTE, cbLanguage.getValue());

      Case
          .match(
              Case.matchCase(() -> Result.success(loginService.loadUser(username, password))), // Default Case
              Case.matchCase(username::isEmpty, () -> Result.failure(resolve("login.failed.description.empty.username"))),
              Case.matchCase(password::isEmpty, () -> Result.failure(resolve("login.failed.description.empty.password"))),
              Case.matchCase(() ->  ! loginService.isLoginAllowed(username, password), () -> Result.failure(resolve("login.failed.description")))
          )
          .bind(validateUser -> validateUser
                  .ifPresent(user -> {
                    getSession().setAttribute(User.class, user);
                    final MainWindow mainWindow = DI.activateDI(MainWindow.class);
                    UI.getCurrent().setContent(mainWindow);
                  }),
              failedMessage -> Notification.show(
                  resolve("login.failed"),
                  failedMessage,
                  Notification.Type.WARNING_MESSAGE));
    });
  }

  private String resolve(String key) {
    return propertyService.resolve(key);
  }

}
