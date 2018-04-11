package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;

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
    $(byText("/" + groupName)).waitUntil(Condition.appears, Configuration.timeout);
    /*
     * the order of the groups in the add navigation page is random, so we defined
     * this condition to fix the location of the linkText "add navigation" that we
     * should clic on it
     */

    $(byText("/" + groupName)).parent().find(byText("Add Navigation")).click();

    $(ELEMENT_CANCEL_BUTON).click();
    $(byText(groupName)).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * function delete navigation for group
   *
   * @param groupName name of Group
   */
  public void deleteNavigationForGroup(String groupName) {

    /*
     * info("Delete navigation of group " + groupName);
     * $(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName)).click();
     * alert.acceptAlert(); $(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}",
     * groupName)).waitUntil(Condition.disappears,10000);
     */
    // clic on delete navigation
    $(byText(groupName)).parent().find(byXpath("//*[@id=\"UIGroupNavigationGrid\"]/table[1]/tbody/tr[1]/td[4]/a")).click();
    alert.acceptAlert();
    $(byText(groupName)).shouldNot(Condition.exist);
  }

  /**
   * function: Edit Priority For Group
   *
   * @param groupAdmin Description of group
   * @param priority Priority you want to set for group
   */
  public void editPriorityForGroup(String groupAdmin, String priority) {
    info("Select group navigation [Administration] and click [Edit Properties]");
    $(byXpath(ELEMENT_EDIT_PROPERTIES_ICON.replace("${groupName}", groupAdmin))).click();
    info("Change priority for this group");
    $(byXpath(ELEMENT_GROUP_NAVIGATION_PRIORITY)).selectOption(priority);
    $(byXpath(ELEMENT_SAVE_BTN)).click();
  }

  /**
   * function: Go to Edit navigation
   *
   * @param currentNavigation String
   */
  public void editNavigation(String currentNavigation) {
    By navigation = byXpath(ELEMENT_EDIT_NAVIGATION.replace("${groupName}", currentNavigation));
    $(navigation).click();
    $(byXpath(ELEMENT_TITLE_NAVIGATION_MANAGEMENT)).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Verify Add Navigation permission
   *
   * @param title page's title
   * @param isEnable boolean
   * @param groupName String
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
   * @param group String
   * @param isEnable boolean
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
