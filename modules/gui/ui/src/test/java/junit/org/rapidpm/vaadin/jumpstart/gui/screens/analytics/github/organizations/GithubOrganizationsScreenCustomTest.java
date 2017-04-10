package junit.org.rapidpm.vaadin.jumpstart.gui.screens.analytics.github.organizations;

import junit.org.rapidpm.vaadin.jumpstart.gui.BaseMicroserviceTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.jumpstart.gui.screens.analytics.github.organizations.GithubOrganizationsScreenCustom;

/**
 * Created by svenruppert on 10.04.17.
 */
public class GithubOrganizationsScreenCustomTest extends BaseMicroserviceTest {

  private void navigateToCharts() {
    loginAsAdmin();

    final WebElement mainMenue = mainMenue();
    mainMenue.sendKeys(Keys.ARROW_DOWN);
    mainMenue.sendKeys(Keys.ARROW_RIGHT);
    mainMenue.sendKeys(Keys.ARROW_DOWN);
    mainMenue.sendKeys(Keys.ARROW_RIGHT);
    mainMenue.sendKeys(Keys.ENTER);
  }

  // write Menue DSL


  @Test
  public void test001() throws Exception {
    navigateToCharts();
    getElement(GithubOrganizationsScreenCustom.BUTTON_LOAD_ID).click();
    Assert.assertTrue(true);

  }


}
