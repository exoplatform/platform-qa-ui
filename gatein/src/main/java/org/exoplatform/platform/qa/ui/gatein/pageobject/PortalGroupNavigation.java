package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.actions;
import static org.exoplatform.platform.qa.ui.selenium.DownloadFileControl.driver;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.exoplatform.platform.qa.ui.core.config.driver.Driver;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PortalGroupNavigation {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public PortalManagePages     portMg;

  private ElementEventTestBase evt;

  public PortalGroupNavigation(TestBase testBase) {
    this.testBase = testBase;
    this.alert = new ManageAlert(testBase);
    this.portMg = new PortalManagePages(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * function: add new navigation for group
   *
   * @param groupName name of group you want to add navigation
   */
  public void addNewNavigationForGroup(String groupName) {
    info("Add navigation for group " + groupName);
    $(byText("Add Navigation")).click();
/*    int x =$(byText("/"+groupName)).getLocation().getX();
    int y =$(byText("/"+groupName)).getLocation().getY();
    int x1=x+520;
    int y1=y+20;

  actions().moveByOffset(x1,y1).click();*/
$(byText(groupName)).click();
$(ELEMENT_CANCEL_BUTON).click();
$(byText(groupName)).shouldNot(Condition.exist);
$(byText(groupName)).waitUntil(Condition.appears,10000);
  }

  /**
   * function delete navigation for group
   *
   * @param groupName name of Group
   */
  public void deleteNavigationForGroup(String groupName) {

    info("Delete navigation of group " + groupName);
    evt.click(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName));
    alert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName));
  }

  /**
   * function: Edit Priority For Group
   *
   * @param groupAdmin Description of group
   * @param priority Priority you want to set for group
   */
  public void editPriorityForGroup(String groupAdmin, String priority) {
    info("Select group navigation [Administration] and click [Edit Properties]");
    evt.click(ELEMENT_EDIT_PROPERTIES_ICON.replace("${groupName}", groupAdmin));
    info("Change priority for this group");
    evt.select(ELEMENT_GROUP_NAVIGATION_PRIORITY, priority);
    evt.click(ELEMENT_SAVE_BTN);
  }

  /**
   * function: Go to Edit navigation
   *
   * @param currentNavigation
   */
  public void editNavigation(String currentNavigation) {
    String navigation = ELEMENT_EDIT_NAVIGATION.replace("${groupName}", currentNavigation);
    evt.click(navigation);
    evt.waitForAndGetElement(ELEMENT_TITLE_NAVIGATION_MANAGEMENT);
  }

  /**
   * Verify Add Navigation permission
   *
   * @param title page's title
   * @param isEnable
   * @param groupName
   */
  public void verifyAddNavigationPerm(String title, boolean isEnable, String groupName) {
    info("verify Add Navigation permission");
    portMg.openPage(testBase.getExoWebDriver().getBaseUrl() + "/intranet/home/" + title);
    if (isEnable && groupName.length() > 0) {
      evt.waitForAndGetElement(ELEMENT_ADD_NAVIGATION_BUTTON);
      evt.click(ELEMENT_ADD_NAVIGATION_BUTTON);
      evt.click(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
      evt.waitForElementNotPresent(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
      evt.click(ELEMENT_CANCEL_BUTON);
      evt.waitForAndGetElement(ELEMENT_GROUP_NAME.replace("${groupName}", groupName));
    }
  }

  /**
   * Verify Manage Group Site
   *
   * @param group
   * @param isEnable
   */
  public void verifyManageGroupSitePerm(String group, boolean isEnable) {
    info("Verify Manage Group Site");
    if (isEnable) {
      evt.waitForAndGetElement(ELEMENT_EDIT_NAVIGATION.replace("${groupName}", group));
      evt.waitForAndGetElement(ELEMENT_EDIT_PROPERTIES_ICON.replace("${groupName}", group));
      evt.waitForAndGetElement(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", group));
    }
  }
}
