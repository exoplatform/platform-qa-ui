package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static java.time.zone.ZoneRulesProvider.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
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
   */
  public void goToEditNavigation() {

    ELEMENT_BUTTON_EDIT_NAVIGATION.waitUntil(Condition.appears, Configuration.timeout);

    ELEMENT_BUTTON_EDIT_NAVIGATION.click();

    $(ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Edit layout of a portal
   *
   * @param site String
   */
  public void goToEditLayout(String site) {
    info("Click on Edit layout button");
    evt.click(ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON.replace("${site}", site));

  }

  /**
   * change config of a portal
   *
   * @param site String
   */
  public void changeConfig(String site) {
    goToEditLayout(site);
    info("Click on site's config button");
    evt.click(ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN);

  }

  /**
   * Go to Edit site configuration
   *
   * @param site String
   */
  public void goToEditSiteConfig(String site) {
    info("Click on Edit Site Configuration button");
    $(byXpath(ELEMENT_MANAGESITES_EDIT_CONFIG_ICON.replace("${site}", site))).waitUntil(visible,Configuration.timeout).click();
  }
public void goToDefaultSkin() {
  $(byId("skin")).selectOptionByValue("Default");
  $((ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN)).click();
  $(ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  refresh();
}
  public void goToEntrepriseSkin() {
    $(byId("skin")).selectOptionByValue("Enterprise");
    $((ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN)).click();
    $(ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    refresh();
  }
  /**
   * Add a simple portal
   *
   * @param portalName String
   * @param label String
   * @param des String
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

  }

  /**
   * Select a membership of a group
   *
   * @param memberShip String
   */
  public void selectMemberShip(String memberShip) {
    info("Select a membership:" + memberShip);
    evt.click(ELEMENT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}", memberShip));

  }

  /**
   * Save all data when create a new portal
   */
  public void saveNewPortal() {
    info("click on Save button");
    evt.click(ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN);

  }

  /**
   * Edit a simple portal
   *
   * @param portalName String
   * @param label String
   * @param des String
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
   * @param portalName String
   * @param label String
   * @param description String
   * @param portalLocale String
   * @param portalSkin String
   * @param portalSession String
   * @param publicMode boolean
   * @param permissions String
   * @param editGroupId String
   * @param editMembership String
   * @param template String
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
    $(byXpath(ELEMENT_ADD_NEW_PORTAL_LINK)).click();
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
   * @param portalName String
   * @param label String
   * @param description String
   * @param portalLocale String
   * @param portalSkin String
   * @param portalSession String
   * @param publicMode boolean
   * @param permissions String
   * @param editGroupId String
   * @param editMembership String
   * @param template String
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
      $(ELEMENT_INPUT_NAME).setValue(portalName);
    }
    if (label != null) {
      $(ELEMENT_PORTAL_LABEL).setValue(label);
    }
    if (description != null) {
      $(ELEMENT_PORTAL_DESCRIPTION).setValue(description);
    }
    if (portalLocale != null) {
      $(byXpath(ELEMENT_SELECT_LOCALE)).selectOption(portalLocale);

    }
    if (portalSkin != null) {
      $(ELEMENT_SELECT_SKIN).selectOption(portalSkin);
    }
    if (portalSession != null) {
      $(byXpath(ELEMENT_PROPERTIES_TAB)).click();
      $(byXpath(ELEMENT_SELECT_SESSION_ALIVE)).selectOption(portalSession);
    }
    $(byXpath(ELEMENT_PERMISSION_SETTING_TAB)).click();
    if (publicMode) {
      if($(ELEMENT_CHECKBOX_PUBLIC_MODE).is(Condition.not(Condition.checked)))
      $(ELEMENT_CHECKBOX_PUBLIC_MODE).parent().click();
      $(ELEMENT_ADD_PERMISSION_BUTTON).waitUntil(Condition.not(visible),Configuration.timeout);
    } else {
      for (String key : permissions.keySet()) {
        setViewPermissions(key, permissions.get(key));
      }
    }
    if (editGroupId != null && editMembership != null) {
      $(byXpath(ELEMENT_EDIT_PERMISSION_SETTING)).click();
      setEditPermissions(editGroupId, editMembership);
    }
    if (template.length > 0) {
      $(byXpath(ELEMENT_PORTAL_TEMPLATE_TAB)).click();
      WebElement temp = evt.getElementFromTextByJquery(template[0]);
      temp.click();
    }
    $(byXpath(ELEMENT_SAVE_BUTTON)).click();

    $(ELEMENT_POPUP_ADD_PORTAL).waitUntil(Condition.not(visible),Configuration.timeout);


    if (evt.waitForAndGetElement(ELEMENT_POPUP_ADD_PORTAL, 10000, 0) == null)

    $(ELEMENT_EDIT_PERMISSION_SETTING).waitUntil(Condition.not(visible),Configuration.timeout);
  }

  /**
   * Set View Permissions
   *
   * @param groupId String
   * @param membership String
   */

  public void setViewPermissions(String groupId, String membership) {
    String membershipToSelect = ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM.replace("${membership}", membership);
    String selectedMembership = ELEMENT_SELECTED_ACCESS_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

    info("--Setting view permission to " + groupId + ", " + membership + "--");
    String[] groups = groupId.split("/");

    evt.click(ELEMENT_ADD_PERMISSION_BUTTON, 0, true);
    for (String group : groups) {
      String groupToSelect = ELEMENT_SELECT_ACCESS_GROUP_ITEM.replace("${group}", group);
      evt.click(groupToSelect);
    }

    evt.click(membershipToSelect);

    evt.waitForAndGetElement(selectedMembership);
  }

  /**
   * Set Edit Permissions
   *
   * @param groupId String
   * @param membership String
   */
  public void setEditPermissions(String groupId, String membership) {
    String membershipToSelect = ELEMENT_SELECT_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);


    info("--Setting edit permission to " + groupId + ", " + membership + "--");
    String[] groups = groupId.split("/");
        $(ELEMENT_SELECT_PERMISSION_BUTTON).click();
    $(byText("Permission Selector")).waitUntil(visible,Configuration.timeout);
    for (String group : groups) {
      String groupToSelect = ELEMENT_SELECT_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", group);
      $(byXpath(groupToSelect)).click();
    }
    $(byXpath(membershipToSelect)).click();
    $("Permission Selector").waitUntil(Condition.not(visible),Configuration.timeout);


  }

  /**
   * Delete Portal
   *
   * @param portalName String
   */
  public void deletePortal(String portalName) {
    String portalDeleteIcon = ELEMENT_PORTAL_DELETE_ICON.replace("${portalName}", portalName);
    info("--Delete portal (" + portalName + ")--");
    $(byXpath(portalDeleteIcon)).click();
    alert.acceptAlert();
    $(byXpath(ELEMENT_PORTAL_DELETE_ICON.replace("${portalName}", portalName))).waitUntil(not(visible),Configuration.timeout);
  }

  /**
   * Verify permission on site
   *@param portal string
   * @param isEnable boolean
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
