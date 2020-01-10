package org.exoplatform.platform.qa.ui.selenium.platform.administration;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ManageLayout {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public ManageLayout(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * Public mode of a Site
   */
  public void publicMode() {
    info("Check on Public mode checkbox");
    evt.check(ELEMENT_PERMISSION_PUBLIC_CHECKBOX, 2);
    evt.waitForElementNotPresent(ELEMENT_PERMISSION_GRID);
    info("The public mode is checked");
  }

  /**
   * Save all changes of edit portlet popup
   */
  public void saveChangesPortletPopup() {
    info("Click on Save button");
    evt.click(ELEMETN_PORTLET_POPUP_SAVE_BTN);
    evt.waitForElementNotPresent(ELEMETN_PORTLET_POPUP_SAVE_BTN);
    info("All changes are saved");
  }

  /**
   * Open permission tab of edit portlet popup
   */
  public void goToPortletPermissionTab() {
    info("Click on Permission tab");
    evt.click(ELEMENT_PORTLET_POPUP_PERMISSION_TAB);
    evt.waitForAndGetElement(ELEMENT_PERMISSION_GRID);
    info("The permission tab's content is shown");
  }

  /**
   * Open container tab
   */
  public void goToContainerTab() {
    info("Click on Container tab");
    evt.click(ELEMENT_PAGE_EDIT_LAYOUT_CONTAINER_TAB);

  }

  /**
   * Open permission tab of a edit container popup
   */
  public void goToPermissionContainer() {
    info("Click on Permission tab");
    evt.click(ELEMENT_CONTAINER_POPUP_PERMISSION_TAB);
    evt.waitForAndGetElement(ELEMENT_PERMISSION_GRID);
  }

  /**
   * Close the page editing form
   */
  public void abortPageUpdate() {
    $(ELEMENT_EDIT_PORTLET_ABORT).isDisplayed();
    $(ELEMENT_EDIT_PORTLET_ABORT).waitUntil(Condition.visible, Configuration.timeout).click();
  }

  /**
   * Add permission for a portlet
   * 
   * @param groupPath
   * @param membership
   * @param addedGroup
   * @param permissionSelector
   * @param popupName  HEAD is tab name: Access/Move Apps/Move Containers
   * @param popupTitle FQA-2759:PLF43 - Write High Fnc/PLF/Restricted Page
   *          Editor/Site Permissions
   */
  public void addPremission(String groupPath,
                            String membership,
                            String addedGroup,
                            String permissionSelector,
                            String popupName,
                            String popupTitle) {
    info("Click on Add Premission button");
    evt.click(ELEMENT_PERMISSION_ADD_USER_BTN.replace("${tabName}", permissionSelector));
    evt.waitForAndGetElement(ELEMENT_PORTLET_SELECT_PERMISSION_POPUP_TITLE.replace("${tabName}", popupName)
                                                                          .replace("${popupTitle}", popupTitle));
    String[] groups = groupPath.split("/");
    for (String group : groups) {
      evt.click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME.replace("${tabName}", popupName).replace("${name}",
                                                                                                                 group));

    }
    if (!membership.isEmpty()) {
      info("Select membership");
      evt.click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME.replace("${tabName}", popupName).replace("${name}",
                                                                                                                 membership));

    }
    evt.waitForAndGetElement(ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME.replace("${tabName}", permissionSelector)
                                                                         .replace("${group}", addedGroup));
    info("Access group is added successfully");
  }

}
