package junit.org.rapidpm.vaadin.jumpstart.gui;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.jumpstart.gui.basics.MainWindowImpl;
import org.rapidpm.vaadin.jumpstart.gui.menubar.RapidMenuBar;
import org.rapidpm.vaadin.jumpstart.gui.screens.login.LoginScreenCustom;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;

/**
 * Created by svenruppert on 07.04.17.
 */
public class BaseUITest extends BaseTestbenchTest {


    @Test
    public void doNothing() throws Exception {
        Assert.assertTrue(true);
    }


    @Inject public PropertyService propertyService;

    public <T extends BaseTestbenchTest> T loginAsAdmin() {
        final VerticalLayoutElement loginElement = $(VerticalLayoutElement.class).id(LoginScreenCustom.LOGIN_SCREEN);

        $(TextFieldElement.class).id(LoginScreenCustom.USERNAME_FIELD).setValue("admin");
        $(TextFieldElement.class).id(LoginScreenCustom.PASSWORD_FIELD).setValue("admin");
        ComboBoxElement comboBoxElement = $(ComboBoxElement.class).id(LoginScreenCustom.LANGUAGE_COMBO);
        comboBoxElement.selectByText(propertyService.resolve("login.language.en"));
        $(ButtonElement.class).id(LoginScreenCustom.LOGIN_BUTTON).click();
        return (T) this;
    }

    protected WebElement getElement(final String usernameField) {
        return mainLayout().findElement(By.id(usernameField));
    }

    protected WebElement mainLayout() {
        return findElement(By.id(MainWindowImpl.WORKING_AREA_CONTAINER));
    }

    protected WebElement mainMenue() {
        return findElement(By.id(RapidMenuBar.MENUBAR));
    }

    public WebElement menubar() {
        return getElement(RapidMenuBar.MENUBAR);
    }

    public String resolve(String key) {
        return propertyService.resolve(key);
    }
}