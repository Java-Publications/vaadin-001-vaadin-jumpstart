package org.rapidpm.vaadin.jumpstart.gui.screens.login;

import static java.util.Arrays.asList;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.rapidpm.ddi.DI;
import org.rapidpm.frp.matcher.Case;
import org.rapidpm.frp.model.Result;
import org.rapidpm.vaadin.jumpstart.gui.basics.MainWindow;
import org.rapidpm.vaadin.jumpstart.gui.design.login.LoginScreen;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.security.LoginService;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.security.User;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

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
        cbLanguage.setItems(asList(resolve("login.language.en"), resolve("login.language.de")));

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

            Result<Optional<User>> match = Case
                .match(
                    matchCase(() -> success(loginService.loadUser(username, password))),
                    matchCase(username::isEmpty, () -> failure(resolve("login.failed.description.empty.username"))),
                    matchCase(password::isEmpty, () -> failure(resolve("login.failed.description.empty.password"))),
                    matchCase(() -> !loginService.isLoginAllowed(username, password), () -> failure(resolve("login.failed.description"))));

            match.bind(validateUser -> validateUser.ifPresent(user -> {
                getSession().setAttribute(User.class, user);
                final MainWindow mainWindow = DI.activateDI(MainWindow.class);
                UI.getCurrent().setContent(mainWindow);
            }), failedMessage -> Notification.show(resolve("login.failed"), failedMessage, Notification.Type.WARNING_MESSAGE));
        });
    }

    private String resolve(String key) {
        return propertyService.resolve(key);
    }

}
