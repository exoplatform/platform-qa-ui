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
    /* the order of the groups in the add navigation page is random, so we defined this condition to fix
    * the location of the linkText "add navigation" that we should clic on it*/

  for(int i=1; i<15;i++){
  //xpath of the tr that have text groupName
      if ($(byXpath("//*[@id=\"UIAddGroupNavigationGrid\"]/table/tbody/tr["+i+"]/td[1]")).has(Condition.text(groupName)))
  /*(i-1) is the order of the linkText add navigation of the groupName
  (i-1) because the function get(X) sould have arguments 0=< X=< n
   */
          {$(byClassName("uiAddGroupNavigation")).findAll(byLinkText("Add Navigation")).get(i-1).click();

  }
}

$(ELEMENT_CANCEL_BUTON).click();
$(byText(groupName)).waitUntil(Condition.appears,10000);
  }

  /**
   * function delete navigation for group
   *
   * @param groupName name of Group
   */
  public void deleteNavigationForGroup(String groupName) {

    /*info("Delete navigation of group " + groupName);
    $(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName)).click();
    alert.acceptAlert();
    $(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName)).waitUntil(Condition.disappears,10000);

*/
    for(int i=0;i<10;i++){
      for(int j=0;j<4;j++){
        //search the location of groupName by xpath
        if ($(byXpath("//*[@id=\"UIGroupNavigationGrid\"]/table["+(i+1)+"]/tbody/tr["+(j+1)+"]/td[1]")).has(Condition.text(groupName))){
          // clic by xpath on delete navigation corresponding to the groupName
          $(byXpath("//*[@id=\"UIGroupNavigationGrid\"]/table["+(i+1)+"]/tbody/tr["+(j+1)+"]/td[4]")).scrollTo().click();
          alert.acceptAlert();

        }
      }

    }
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
    $(ELEMENT_EDIT_PROPERTIES_ICON.replace("${groupName}", groupAdmin));
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
