package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class EditorPortlet {
  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public EditorPortlet(TestBase testBase) {
    this.testBase = testBase;
    alert = new ManageAlert(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Open Access Permission tab
   */
  public void goToAccessPermissionTab() {
    info("Click on Access Permission tab");
    evt.click(ELEMENT_PORTLET_ACCESS_PERMISSION_TAB);
  }

  /**
   * Delete a access permission group
   *
   * @param group is group path as: /platform/users,...
   */
  public void deleteGroupPermission(String group) {
    if (evt.waitForAndGetElement(ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN.replace("$group", group), 3000, 0) != null) {
      info("Click on delete button");
      evt.click(ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN.replace("$group", group));
      alert.waitForConfirmation(ELEMENT_CONFIRM_DELETE_MESSAGE);
      alert.acceptAlert();
      evt.waitForElementNotPresent(ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN.replace("$group", group));
      info("Group is deleted successfully");
    }
  }

  /**
   * Add permission for a portlet
   *
   * @param groupPath is group's path as: Platform/Administration,..
   * @param membership is membership's name
   * @param addedGroup is group's name that is added after added permission in
   *          list
   */
  public void addPremission(String groupPath, String membership, String addedGroup) {
    info("Click on Add Premission button");
    evt.click(ELEMENT_PORTLET_ACCESS_PERMISSION_ADD_BTN);
    evt.waitForAndGetElement(ELEMENT_PORTLET_SELECT_PERMISSION_POPUP);
    String[] groups = groupPath.split("/");
    for (String group : groups) {
      evt.click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME.replace("$name", group));
      Utils.pause(2000);
    }
    if (!membership.isEmpty()) {
      info("Select membership");
      evt.click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME.replace("$name", membership));
      Utils.pause(2000);
    }
    evt.waitForAndGetElement(ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME.replace("$group", addedGroup));
    info("Access group is added successfully");
  }

  /**
   * Save or close all changes
   *
   * @param isSave
   */
  public void saveCloseChange(Boolean isSave) {
    if (isSave) {
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON);
    } else {
      info("Click on Close button");
      evt.click(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
    }
    evt.waitForElementNotPresent(ELEMENT_EDIT_PORTLET_FORM);
    info("Save or Close successfully");
  }

}
