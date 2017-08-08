package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase.filterOption;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiPermission {

  private final TestBase       testBase;

  public Dialog                dialog;

  public Button                button;

  public PlatformBase          plf;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param dr
   */
  public WikiPermission(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.button = new Button(testBase);
    this.dialog = new Dialog(testBase);
    this.plf = new PlatformBase(testBase);
  }

  /**
   * Delete a group, users in permission popup
   *
   * @param group
   */
  public void deletePermission(String groupUsers) {
    By bDelete = By.xpath(ELEMENT_DELETE_PERMISSION.replace("$user", groupUsers));
    if (evt.waitForAndGetElement(bDelete, 20000, 0) != null) {
      info("--Delete permission--");
      evt.click(bDelete);
      evt.waitForElementNotPresent(bDelete);
    }

  }

  /**
   * Select permission for a username/group/membership
   *
   * @param userGroup
   * @param type
   */
  public void selectPermission(String userGroup, permissionType type) {
    switch (type) {
    case View_Pages:
      info("Select View pages permission");
      evt.check(ELEMENT_PERMISSION_VIEW_CHECKBOX.replace("$userGroup", userGroup), 2);
      break;
    case Edit_Pages:
      info("Select Edit pages permission");
      $(byText(userGroup)).parent().parent().findAll(byClassName("uiCheckbox")).get(1).click();
      break;
    case Admin_Pages:
      info("Select View pages permission");
      evt.check(ELEMENT_PERMISSION_ADMPAGE_CHECKBOX.replace("$userGroup", userGroup), 2);
      break;
    case Admin_Wiki:
      info("Select View pages permission");
      evt.check(ELEMENT_PERMISSION_ADMWIKI_CHECKBOX.replace("$userGroup", userGroup), 2);
      break;
    }
  }

  /**
   * Unselect permission for a username/group/membership
   *
   * @param userGroup
   * @param type
   */
  public void unSelectPermission(String userGroup, permissionType type) {
    switch (type) {
    case View_Pages:
      info("un Select View pages permission");
      evt.uncheck(ELEMENT_PERMISSION_VIEW_CHECKBOX.replace("$userGroup", userGroup), 2);
      break;
    case Edit_Pages:
      info("un Select View pages permission");
      evt.uncheck(ELEMENT_PERMISSION_EDIT_CHECKBOX.replace("$userGroup", userGroup), 2);
      break;
    case Admin_Pages:
      info("Select View pages permission");
      evt.uncheck(ELEMENT_PERMISSION_ADMPAGE_CHECKBOX.replace("$userGroup", userGroup), 2);
      break;
    case Admin_Wiki:
      info("Select View pages permission");
      evt.uncheck(ELEMENT_PERMISSION_ADMWIKI_CHECKBOX.replace("$userGroup", userGroup), 2);
      break;
    }
  }

  /**
   * Add a group/user/membership to permission table by type
   *
   * @param groupUsers
   */
  public void addPermisisonByType(String groupUsers) {
    if (!groupUsers.isEmpty()) {
      info("Type a:" + groupUsers + " to the textbox");
     $(ELEMENT_PERMISSION_TYPE_INPUT).setValue(groupUsers);
      info("Click on Add button");
      $(ELEMENT_PERMISSION_ADD_BUTTON).click();
      info("The group/user/membership is added successfully");
    }
  }

  /**
   * Open User list
   */
  public void goToSelectUser() {
    info("Click on select user button");
    evt.click(ELEMENT_PERMISSION_SELECT_USER);

  }

  /**
   * Open Membership list
   */
  public void goToSelectMemberShip() {
    info("Click on select membership button");
    evt.click(ELEMENT_PERMISSION_SELECT_MEMBERSHIP);

  }

  /**
   * Open Group list
   */
  public void goToGroup() {
    info("Click on select membership button");
    evt.click(ELEMENT_PERMISSION_SELECT_GROUP);

  }

  /**
   * Add permission for a user/group/membership by selecting
   *
   * @param groupUsers
   * @param membership
   * @param type
   * @param op
   */
  public void addPermissionBySelect(String groupUsers, String membership, userGroupTypes type) {
    switch (type) {
    case Users_UserName:
      goToSelectUser();
      plf.selectUser(groupUsers, filterOption.userName);
      break;
    case Users_FirstName:
      goToSelectUser();
      plf.selectUser(groupUsers, filterOption.firstName);
      break;
    case Users_LastName:
      goToSelectUser();
      plf.selectUser(groupUsers, filterOption.lastName);
      break;
    case Users_Email:
      goToSelectUser();
      plf.selectUser(groupUsers, filterOption.email);
      break;
    case Membership:
      goToSelectMemberShip();
      plf.selectMembership(groupUsers, membership);
      break;
    case Groups:
      goToGroup();
      plf.selectGroup(groupUsers);
      break;
    }
    info("Click on Add button");
    evt.click(ELEMENT_PERMISSION_ADD_BUTTON);

    info("The group/user/membership is added successfully");
  }

  /**
   * Click on Save button in More/Permission
   */
  public void savePermisison(Boolean... booleans) {
    boolean savePresent = (booleans.length > 0 ? booleans[0] : true);
    info("Click on Save button");
    $(ELEMENT_PERMISSION_BUTTON_SAVE).click();
    if (!savePresent)
      evt.waitForElementNotPresent(ELEMENT_PERMISSION_BUTTON_SAVE);

  }

  /**
   * Save permission in wiki setting
   */
  public void savePermWikiSetting() {
    info("Click on Save button");
    evt.click(ELEMENT_PERMISSION_BUTTON_SAVE, 0, true);
    evt.click(ELEMENT_PERMISSION_BUTTON_OK, 0, true);
  }

  /**
   * Define permission Types as Users, Membership or Groups
   */
  public enum userGroupTypes {
    Users_UserName, Users_Email, Users_FirstName, Users_LastName, Membership, Groups;
  }

  /**
   * Define mode to input permission as type or select
   */
  public enum modeInput {
    Type, Select;
  }

  /**
   * Define permission types as View page or Edit page
   */
  public enum permissionType {
    View_Pages, Edit_Pages, Admin_Pages, Admin_Wiki;
  }
}
