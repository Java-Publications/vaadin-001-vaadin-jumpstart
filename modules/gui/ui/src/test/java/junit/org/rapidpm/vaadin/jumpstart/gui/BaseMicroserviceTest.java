package junit.org.rapidpm.vaadin.jumpstart.gui;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.HorizontalLayoutElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;
import com.vaadin.ui.HorizontalLayout;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.jumpstart.gui.menubar.RapidMenuBar;
import org.rapidpm.vaadin.jumpstart.gui.screens.login.LoginScreenCustom;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import javax.inject.Inject;

/**
 * Created by svenruppert on 07.04.17.
 */
public class BaseMicroserviceTest extends BaseTestbenchTest {

  @Inject public PropertyService propertyService;

  public <T extends BaseTestbenchTest> T loginAsAdmin() {
    getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.LOGIN_BUTTON).click();
    return (T) this;
  }

  protected WebElement getElement(final String usernameField) {
    final WebElement webElement = mainLayout();
    org.openqa.selenium.By id = By.id(usernameField);
    return webElement.findElement(id);
  }

  protected WebElement mainLayout() {
//    return $(VerticalLayoutElement.class).first();
    return $(HorizontalLayoutElement.class).first();
  }

  public <T extends BaseTestbenchTest> T walk2MenuBar() {
    getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.LOGIN_BUTTON).click();
    return (T) this;
  }

  public WebElement menubar() {
    final WebElement menubar = getElement(RapidMenuBar.MENUBAR);
    return menubar;
  }
}