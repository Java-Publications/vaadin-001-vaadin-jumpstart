package junit.org.rapidpm.vaadin.jumpstart.gui.screens.login;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.jumpstart.gui.screens.login.LoginScreenCustom;

import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;
import junit.org.rapidpm.vaadin.jumpstart.gui.BaseUITest;

/**
 * Created by svenruppert on 07.04.17.
 */
public class LoginScreenCustomTest extends BaseUITest {

    // not working on MainLayout so far
    protected WebElement mainLayout() {
        return $(VerticalLayoutElement.class)
            .id(LoginScreenCustom.LOGIN_SCREEN);
    }

    @Test
    public void test001()
        throws Exception {
        getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
        getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("admin");

        ComboBoxElement comboBoxElement = $(ComboBoxElement.class)
            .id(LoginScreenCustom.LANGUAGE_COMBO);
        comboBoxElement.openPopup();
        comboBoxElement.sendKeys(Keys.ARROW_DOWN);
        comboBoxElement.sendKeys(Keys.ENTER);
        saveScreenshot("klicked_001"); // only to try

        Assert.assertEquals(comboBoxElement.getValue(),
            resolve("login.language.de"));

        getElement(LoginScreenCustom.LOGIN_BUTTON).click();

        final WebElement menubar = mainMenue();
        Assert.assertNotNull(menubar);
        Assert.assertTrue(menubar.isDisplayed());
    }

    @Test
    public void test002()
        throws Exception {
        //    saveScreenshot("before");
        getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
        getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("XX");
        getElement(LoginScreenCustom.LOGIN_BUTTON).click();
        saveScreenshot("klicked_001");

        NotificationElement notification = $(NotificationElement.class).first();
        assertEquals(propertyService.resolve("login.failed"),
            notification.getCaption());
        assertEquals(propertyService.resolve("login.failed.description"),
            notification.getDescription());
        assertEquals("warning", notification.getType());
    }
}
