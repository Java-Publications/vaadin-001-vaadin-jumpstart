package junit.org.rapidpm.vaadin.jumpstart.gui.screens.login;

import com.vaadin.testbench.elements.NotificationElement;
import junit.org.rapidpm.vaadin.jumpstart.gui.BaseMicroserviceTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.jumpstart.gui.menubar.RapidMenuBar;
import org.rapidpm.vaadin.jumpstart.gui.screens.login.LoginScreenCustom;

import static org.junit.Assert.assertEquals;

/**
 * Created by svenruppert on 07.04.17.
 */
public class LoginScreenCustomTest extends BaseMicroserviceTest {


  @Test @Ignore
  public void test001() throws Exception {
    saveScreenshot("before");
    getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.LOGIN_BUTTON).click();

    final WebElement menubar = getElement(RapidMenuBar.MENUBAR);
    Assert.assertNotNull(menubar);
    Assert.assertTrue(menubar.isDisplayed());
    saveScreenshot("after");
  }

  @Test @Ignore
  public void test002() throws Exception {
    saveScreenshot("before");
    getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("XX");
    getElement(LoginScreenCustom.LOGIN_BUTTON).click();

    NotificationElement notification = $(NotificationElement.class).first();
    assertEquals(propertyService.resolve("login.failed"), notification.getCaption());
    assertEquals(propertyService.resolve("login.failed.description"), notification.getDescription());
    assertEquals("warning", notification.getType());
    notification.close();
    saveScreenshot("after");
  }





}
