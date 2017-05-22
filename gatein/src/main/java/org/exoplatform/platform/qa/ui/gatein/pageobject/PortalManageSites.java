package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class PortalManageSites {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public PortalManageSites(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * Open Navigation Management popup
   *
   * @param site as acme or intranet
   */
   public void goToEditNavigation() {

    $(byText("Edit Navigation")).waitUntil(Condition.appears, Configuration.timeout);

    $(byText("Edit Navigation")).click();

    $(ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Edit layout of a portal
   *
   * @param site
   */
  public void goToEditLayout(String site) {
    info("Click on Edit layout button");
    evt.click(ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON.replace("${site}", site));
    Utils.pause(3000);
  }

  /**
   * change config of a portal
   *
   * @param site
   */
  public void changeConfig(String site) {
    goToEditLayout(site);
    info("Click on site's config button");
    evt.click(ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN);
    Utils.pause(3000);
  }

  /**
   * Go to Edit site configuration
   *
   * @param site
   */
  public void goToEditSiteConfig(String site) {
    info("Click on Edit Site Configuration button");
    evt.click(ELEMENT_MANAGESITES_EDIT_CONFIG_ICON.replace("${site}", site));
    Utils.pause(2000);
  }

  /**
   * Add a simple portal
   *
   * @param portalName
   * @param label
   * @param des
   * @param groupsPath is as: Platform/Content Management
   * @param memberShips is as: author
   */
  public void addSimplePortal(String portalName, String label, String des, String groupsPath, String memberShips) {
    info("Click on Add New Portal button");
    evt.click(ELEMENT_MANAGESITES_ADD_NEW_BTN);
    if (!portalName.isEmpty()) {
      info("Input new name for portal name");
      evt.type(ELEMENT_ADD_NEW_PORTAL_POPUP_NAME, portalName, true);
    }
    if (!label.isEmpty()) {
      info("Input label");
      evt.type(ELEMENT_ADD_NEW_PORTAL_POPUP_LABEL, label, true);
    }
    if (!des.isEmpty()) {
      info("Input description");
      evt.type(ELEMENT_ADD_NEW_PORTAL_POPUP_DESC, des, true);
    }
    info("Select permission tab");
    evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_PERMISSION_TAB);
    info("Select public permission checkbox");
    evt.check(ELEMENT_ADD_NEW_PORTAL_POPUP_PUBLIC_PERMISSION, 2);
    if (!groupsPath.isEmpty()) {
      info("Select Edit permission settings tab");
      evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_EDIT_PERMISSITION_SETTINGS);
      info("Click on Select permission button");
      evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_SELECT_PERMISSION_BTN);
      info("Select a group");
      selectGroup(groupsPath);
      info("Select a meberships");
      selectMemberShip(memberShips);
    }
    saveNewPortal();
  }

  /**
   * Select a group in permission selector popup
   *
   * @param groupsPath is path of groups as:Platform/Content Manangement
   */
  public void selectGroup(String groupsPath) {
    info("Select a group with the path:" + groupsPath);
    String[] groups = groupsPath.split("/");
    for (String groupSelect : groups) {
      info("Select group:" + groupSelect);
      evt.click(ELEMENT_PERMISSION_SELECTOR_POPUP_GROUP.replace("${group}", groupSelect));
    }
    Utils.pause(2000);
  }

  /**
   * Select a membership of a group
   *
   * @param memberShip
   */
  public void selectMemberShip(String memberShip) {
    info("Select a membership:" + memberShip);
    evt.click(ELEMENT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}", memberShip));
    Utils.pause(2000);
  }

  /**
   * Save all data when create a new portal
   */
  public void saveNewPortal() {
    info("click on Save button");
    evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN);
    Utils.pause(3000);
  }

  /**
   * Edit a simple portal
   *
   * @param portalName
   * @param label
   * @param des
   * @param groupsPath is as: Platform/Content Management
   * @param memberShips is as: author
   */
  public void editSimplePortal(String portalName, String label, String des, String groupsPath, String memberShips) {
    if (!portalName.isEmpty()) {
      info("Input new name for portal name");
      evt.type(ELEMENT_ADD_NEW_PORTAL_POPUP_NAME, portalName, true);
    }
    if (!label.isEmpty()) {
      info("Input label");
      evt.type(ELEMENT_ADD_NEW_PORTAL_POPUP_LABEL, label, true);
    }
    if (!des.isEmpty()) {
      info("Input description");
      evt.type(ELEMENT_ADD_NEW_PORTAL_POPUP_DESC, des, true);
    }
    info("Select permission tab");
    evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_PERMISSION_TAB);
    info("Select public permission checkbox");
    evt.check(ELEMENT_ADD_NEW_PORTAL_POPUP_PUBLIC_PERMISSION, 2);
    if (!groupsPath.isEmpty()) {
      info("Select Edit permission settings tab");
      evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_EDIT_PERMISSITION_SETTINGS);
      info("Click on Select permission button");
      evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_SELECT_PERMISSION_BTN);
      info("Select a group");
      selectGroup(groupsPath);
      info("Select a meberships");
      selectMemberShip(memberShips);
    }
    saveNewPortal();
  }

  /**
   * Add New Portal
   *
   * @param portalName
   * @param label
   * @param description
   * @param portalLocale
   * @param portalSkin
   * @param portalSession
   * @param publicMode
   * @param permissions
   * @param editGroupId
   * @param editMembership
   * @param template
   */

  public void addNewPortal(String portalName,
                           String label,
                           String description,
                           String portalLocale,
                           String portalSkin,
                           String portalSession,
                           boolean publicMode,
                           Map<String, String> permissions,
                           String editGroupId,
                           String editMembership,
                           String... template) {
    info("--Create new portal--");
    evt.click(ELEMENT_ADD_NEW_PORTAL_LINK);
    configPortal(portalName,
                 label,
                 description,
                 portalLocale,
                 portalSkin,
                 portalSession,
                 publicMode,
                 permissions,
                 editGroupId,
                 editMembership,
                 template);
  }

  /**
   * Configure Portal
   *
   * @param portalName
   * @param label
   * @param description
   * @param portalLocale
   * @param portalSkin
   * @param portalSession
   * @param publicMode
   * @param permissions
   * @param editGroupId
   * @param editMembership
   * @param template
   */

  public void configPortal(String portalName,
                           String label,
                           String description,
                           String portalLocale,
                           String portalSkin,
                           String portalSession,
                           boolean publicMode,
                           Map<String, String> permissions,
                           String editGroupId,
                           String editMembership,
                           String... template) {
    if (portalName != null) {
      evt.type(ELEMENT_INPUT_NAME, portalName, true);
    }
    if (label != null) {
      evt.type(ELEMENT_PORTAL_LABEL, label, true);
    }
    if (description != null) {
      evt.type(ELEMENT_PORTAL_DESCRIPTION, description, true);
    }
    if (portalLocale != null) {
      evt.select(ELEMENT_SELECT_LOCALE, portalLocale);
    }
    if (portalSkin != null) {
      evt.select(ELEMENT_SELECT_SKIN, portalSkin);
    }
    if (portalSession != null) {
      evt.click(ELEMENT_PROPERTIES_TAB);
      evt.select(ELEMENT_SELECT_SESSION_ALIVE, portalSession);
    }
    evt.click(ELEMENT_PERMISSION_SETTING_TAB);
    if (publicMode) {
      evt.check(ELEMENT_CHECKBOX_PUBLIC_MODE, 2);
      evt.waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
    } else {
      for (String key : permissions.keySet()) {
        setViewPermissions(key, permissions.get(key));
      }
    }
    if (editGroupId != null && editMembership != null) {
      evt.click(ELEMENT_EDIT_PERMISSION_SETTING);
      setEditPermissions(editGroupId, editMembership);
    }
    if (template.length > 0) {
      evt.click(ELEMENT_PORTAL_TEMPLATE_TAB);
      WebElement temp = evt.getElementFromTextByJquery(template[0]);
      temp.click();
    }
    evt.click(ELEMENT_SAVE_BUTTON);
    Utils.pause(2000);
    evt.waitForElementNotPresent(ELEMENT_POPUP_ADD_PORTAL, 180000, 0);
    if (evt.waitForAndGetElement(ELEMENT_POPUP_ADD_PORTAL, 10000, 0) == null)
      evt.waitForElementNotPresent(ELEMENT_EDIT_PERMISSION_SETTING, 120000);
  }

  /**
   * Set View Permissions
   *
   * @param groupId
   * @param membership
   */

  public void setViewPermissions(String groupId, String membership) {
    String membershipToSelect = ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM.replace("${membership}", membership);
    String selectedMembership = ELEMENT_SELECTED_ACCESS_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

    info("--Setting view permission to " + groupId + ", " + membership + "--");
    String[] groups = groupId.split("/");
    Utils.pause(500);
    evt.click(ELEMENT_ADD_PERMISSION_BUTTON, 0, true);
    for (String group : groups) {
      String groupToSelect = ELEMENT_SELECT_ACCESS_GROUP_ITEM.replace("${group}", group);
      evt.click(groupToSelect);
    }
    Utils.pause(500);
    evt.click(membershipToSelect);
    Utils.pause(500);
    evt.waitForAndGetElement(selectedMembership);
  }

  /**
   * Set Edit Permissions
   *
   * @param groupId
   * @param membership
   */
  public void setEditPermissions(String groupId, String membership) {
    String membershipToSelect = ELEMENT_SELECT_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

    info("--Setting edit permission to " + groupId + ", " + membership + "--");
    String[] groups = groupId.split("/");
    evt.click(ELEMENT_SELECT_PERMISSION_BUTTON);
    Utils.pause(500);
    evt.waitForTextPresent("Permission Selector");
    for (String group : groups) {
      String groupToSelect = ELEMENT_SELECT_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", group);
      evt.click(groupToSelect);
    }
    evt.click(membershipToSelect);
    evt.waitForTextNotPresent("Permission Selector");
  }

  /**
   * Delete Portal
   *
   * @param portalName
   */
  public void deletePortal(String portalName) {
    String portalDeleteIcon = ELEMENT_PORTAL_DELETE_ICON.replace("${portalName}", portalName);
    info("--Delete portal (" + portalName + ")--");
    evt.click(portalDeleteIcon);
    alert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_PORTAL_DELETE_ICON.replace("${portalName}", portalName), 2000);
  }

  /**
   * Verify permission on site
   *
   * @param isEnable
   */
  public void verifyPermOnSite(String portal, boolean isEnable) {
    info("check permission to access site: " + portal);
    testBase.getExoWebDriver().getWebDriver().get(testBase.getExoWebDriver().getBaseUrl() + "/" + portal);
    if (isEnable)
      evt.waitForAndGetElement(ELEMENT_NEW_PORTAL_LOGOUT, 3000, 0);
    else
      evt.waitForElementNotPresent(ELEMENT_NEW_PORTAL_LOGOUT, 3000, 0);
  }
}
