package junit.org.rapidpm.vaadin.jumpstart.gui;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.jumpstart.gui.basics.MainWindowImpl;
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
    final VerticalLayoutElement loginElement = $(VerticalLayoutElement.class).id(LoginScreenCustom.LOGIN_SCREEN);
    loginElement.findElement(By.id(LoginScreenCustom.USERNAME_FIELD)).sendKeys("admin");
    loginElement.findElement(By.id(LoginScreenCustom.PASSWORD_FIELD)).sendKeys("admin");
    ComboBoxElement comboBoxElement = $(ComboBoxElement.class).id(LoginScreenCustom.LANGUAGE_COMBO);
    comboBoxElement.openPopup();
    comboBoxElement.sendKeys(Keys.ARROW_DOWN);
    comboBoxElement.sendKeys(Keys.ENTER);
    loginElement.findElement(By.id(LoginScreenCustom.LOGIN_BUTTON)).click();
    return (T) this;
  }

  protected WebElement getElement(final String usernameField) {
    return mainLayout()
        .findElement(By.id(usernameField));
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