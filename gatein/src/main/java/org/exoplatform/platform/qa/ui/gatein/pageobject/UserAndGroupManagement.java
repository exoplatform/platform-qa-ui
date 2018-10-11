/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_NEXT_PAGE;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import junit.framework.Assert;

public class UserAndGroupManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public Dialog                dialog;

  private ElementEventTestBase evt;

  private PlatformBase         plfBase;

  public UserAndGroupManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.dialog = new Dialog(testBase);
    this.plfBase = new PlatformBase(testBase);

  }

  /**
   * Select users tab function: Choose Users Tab
   */
  public void goToUsersTab() {
    info("-- Choose Users Management tab--");
    evt.click(ELEMENT_USER_MANAGEMENT_TAB, 0, true);
    evt.waitForAndGetElement(GateinLocator.ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);

  }

  /**
   * Select group management tab function: Choose Group Tab
   */
  public void goToGroupTab() {
    info("-- Choose Group Management tab--");
    ELEMENT_GROUP_TAB.click();
    ELEMENT_TITLE_GROUP_INFO.waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Select membership management tab function: Choose MemberShip Tab
   */
  public void goToMembershipTab() {
    info("-- Choose Membership Management tab--");

    evt.click(ELEMENT_TAB_MEMBERSHIP_MANAGEMENT);
    evt.waitForAndGetElement(ELEMENT_MEMBERSHIP_MANAGEMENT_GRID);

  }

  /**
   * Select a group by array
   *
   * @param arrayGroupPath String
   */
  public void selectGroup(String[] arrayGroupPath) {
    info("Select a group in the list");
    for (String group : arrayGroupPath) {
      info("Select a group:" + group);
      evt.click(ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", group));
    }

  }

  /**
   * Select a group in permission selector popup by string
   *
   * @param groupsPath is path of groups as:Platform/Content Manangement
   */
  public void selectGroup(String groupsPath) {
    info("Select a group with the path:" + groupsPath);
    String[] groups = groupsPath.split("/");
    for (String groupSelect : groups) {
      info("Select group:" + groupSelect);
      $(byXpath(ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", groupSelect))).click();
    }

  }

  /**
   * function: Add new group
   *
   * @param groupName name of Group
   * @param groupLabel label of Group
   * @param groupDesc description of Group
   * @param verify (True if you want to verify new group created successfully)
   */
  public void addGroup(String groupName, String groupLabel, String groupDesc, boolean verify) {
    info("--Add a new group--");
    $(byClassName("uiIconPlus")).click();

    inputDataGroup(groupName, groupLabel, groupDesc);
    $(byText("Save")).click();
    $(byText(groupLabel)).should(Condition.exist);
  }

  /**
   * Add a user to administration group
   *
   * @param user String
   * @param membership String
   */
  public void addUserAdmin(String user, String... membership) {
    info("Go to Group tab");
    goToGroupTab();
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    info("Select Platform/administration group");
    selectGroup("Platform/Administration");
    info("Add user to administration group by type");
    $(ELEMENT_INPUT_USERNAME).setValue(user);
    if (membership.length > 0)
      evt.select(ELEMENT_SELECT_MEMBERSHIP, membership[0]);
    evt.scrollToElement(evt.waitForAndGetElement(ELEMENT_SAVE_BUTTON_2), this.testBase.getExoWebDriver().getWebDriver());
    evt.click(ELEMENT_SAVE_BUTTON_2);
    String addedUser = ELEMENT_ADDED_GROUP_USER_IN_TABLE.replace("${username}", user);
    if (testBase.isTextPresent(ELEMENT_MSG_TOTAL_PAGES)) {
      plfBase.usePaginator(addedUser, ELEMENT_USER_NOT_FOUND.replace("${user}", user));
    } else {
      $(byXpath(addedUser)).waitUntil(Condition.visible,Configuration.timeout);
    }
    info("User is added to administration group");
  }

  /**
   * Add a user to content management group
   *
   * @param user String
   * @param membership String
   */
  public void addUserContentManagement(String user, String... membership) {
    info("Go to Group tab");
    goToGroupTab();
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    info("Select Platform/Content Management group");
    selectGroup("Platform/Content Management");
    info("Add user to Content Management group by type");
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    evt.type(ELEMENT_INPUT_USERNAME, user, true);
    if (membership.length > 0)
      evt.select(ELEMENT_SELECT_MEMBERSHIP, membership[0]);
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    evt.click(ELEMENT_SAVE_BUTTON_2);
    String addedUser = ELEMENT_ADDED_GROUP_USER_IN_TABLE.replace("${username}", user);
    if (testBase.isTextPresent(ELEMENT_MSG_TOTAL_PAGES)) {
      plfBase.usePaginator(addedUser, ELEMENT_USER_NOT_FOUND.replace("${user}", user));
    } else {
      evt.waitForAndGetElement(addedUser);
    }
    info("User is added to Content Managment group");
  }

  /**
   * function: Input data to create a new group
   *
   * @param groupName name of Group
   * @param groupLabel label of Group
   * @param groupDesc description of Group
   */
  public void inputDataGroup(String groupName, String groupLabel, String groupDesc) {

    $(ELEMENT_INPUT_GROUP_NAME).waitUntil(Condition.appears, 10000);
    $(ELEMENT_INPUT_GROUP_NAME).setValue(groupName);
    $(ELEMENT_INPUT_LABEL).setValue(groupLabel);
    $(ELEMENT_TEXTAREA_DESCRIPTION).setValue(groupDesc);
  }

  public void inputDataGroupForEdit(String groupLabel, String groupDesc) {

    $(ELEMENT_INPUT_GROUP_NAME).waitUntil(Condition.appears, 10000);

    $(ELEMENT_INPUT_LABEL).setValue(groupLabel);
    $(ELEMENT_TEXTAREA_DESCRIPTION).setValue(groupDesc);
  }

  /**
   * function: Edit group
   *
   * @param oldName old name of Group
   * @param groupName name of Group
   * @param groupLabel label of Group
   * @param groupDesc description of Group
   * @param verify (True if you want to verify new group edited successfully)
   */
  public void editGroup(String oldName, String groupName, String groupLabel, String groupDesc, boolean verify) {
    info("-- Edit group: " + groupName + "--");
    $(byClassName("uiIconEdit")).click();
    inputDataGroupForEdit(groupLabel, groupDesc);
    $(byText("Save")).click();
    if (verify) {
      $(byText(groupLabel)).should(Condition.exist);
    }
  }

  /**
   * function: Add user to Group
   *
   * @param userNames name of user you want to add to group
   * @param memberShip membership of user in group
   * @param select (True: if you want to search user by selecting user / False: If
   *          you want to search user by typing user name)
   * @param verify (True: if you want to verify new user added successfully)
   */
  public void addUsersToGroup(String userNames, String memberShip, boolean select, boolean verify) {
    info("--Adding users to group--");
    String[] users = userNames.split(",");
    if (select) {
      if (testBase.isElementPresent(ELEMENT_GROUP_SEARCH_USER_ICON)) {
        $(ELEMENT_GROUP_SEARCH_USER_ICON).click();
      } else if (testBase.isElementPresent(By.xpath(ELEMENT_GROUP_SEARCH_USER_ICON_2))) {
        $(By.xpath(ELEMENT_GROUP_SEARCH_USER_ICON_2)).click();
      }

      for (String user : users) {

        $(ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT).setValue(user);
        $(byClassName("btnSearchUser")).click();
        $(byClassName("uiCheckbox")).click();
      }
      $(byText("Add")).click();

      Assert.assertEquals(testBase.getValue(ELEMENT_INPUT_USERNAME), userNames);
    } else {
      $(ELEMENT_INPUT_USERNAME).setValue(userNames);
    }
    $(byId("membership")).selectOption(memberShip);
    $(ELEMENT_SAVE_BUTTON_2).click();
    if (verify) {
      for (String user : users) {
        if (testBase.isTextPresent(ELEMENT_MSG_TOTAL_PAGES)) {
          plfBase.usePaginator($(byText(user)), ELEMENT_USER_NOT_FOUND.replace("${user}", user));
        } else {
          $(byText(user)).should(Condition.exist);
        }
      }
    }
  }

  /**
   * Verify user-membership
   *
   * @param user String
   * @param member String
   * @param isDisplay boolean
   */
  public void verifyUserMemInTable(String user, String member, boolean isDisplay) {
    info("verify user -membership");
    if (isDisplay) {
      evt.waitForAndGetElement(ELEMENT_ADDED_GROUP_USER_IN_TABLE1.replace("$mem", member).replace("$user", user));
    }
  }

  /**
   * function: Search user In Group Management (To add to group)
   *
   * @param user (Can be: User name, Last name, First name or Email of the user
   *          you want to search)
   * @param searchOption (Can be: User name, Last name, First me or Email option
   *          corresponding with information you input in "Search")
   * @param verify (True: if you want to verify new user Searched successfully)
   */
  public void searchUserInGroupManagement(String user, String searchOption, boolean verify) {
    info("--Search user " + user + "--");
    $(ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT).click();
    $(ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT).setValue(user);
    $(ELEMENT_GROUP_SEARCH_USER_OPTION).selectOption(searchOption);
    $(ELEMENT_GROUP_SEARCH_USER_SEARCH_ICON).click();
    if (verify) {
      // evt.waitForAndGetElement(ELEMENT_SEARCH_GROUP_USER_IN_TABLE.replace("${username}",
      // user));
      $(byText(user)).should(Condition.exist);
    }
  }

  /**
   * function: Delete group
   *
   * @param groupName name of Group
   * @param verify (True: if you want to verify group deleted successfully)
   * @param wait time to evt.wait to verify group deleted successfully
   */
  public void deleteGroup(String groupName, boolean verify, int... wait) {
    info("-- Delete group: " + groupName + "--");
    int waitTime = wait.length > 0 ? wait[0] : testBase.getDefaultTimeout();
    $(byClassName("uiIconTrash")).click();
    alert.acceptAlert();
    if (verify) {
      $(byText(groupName)).shouldNot(Condition.exist);
    }
  }

  /**
   * function: Add new membership
   *
   * @param membershipName name of membership
   * @param membershipDesc description of membership
   * @param verify (True if you want to verify new membership created
   *          successfully)
   */
  public void addMembership(String membershipName, String membershipDesc, boolean verify) {
    By member = By.xpath(ELEMENT_MEMBERSHIHP.replace("${membershipName}", membershipName));
    info("--Creating new membership--");
    evt.click(ELEMENT_TAB_MEMBERSHIP_MANAGEMENT);
    evt.waitForAndGetElement(ELEMENT_INPUT_NAME);
    evt.type(ELEMENT_INPUT_NAME, membershipName, true);
    evt.type(ELEMENT_TEXTAREA_DESCRIPTION, membershipDesc, true);
    evt.click(ELEMENT_SAVE_BUTTON);
    if (evt.waitForAndGetElement(member, 10000, 0) == null) {
      evt.click(ELEMENT_NEXT_PAGE);
    }
    if (verify)
      evt.waitForAndGetElement(member);
  }

  /**
   * function: Edit membership
   *
   * @param membershipName name of membership you want to edit
   * @param newDesc new description of membership
   */
  public void editMembership(String membershipName, String newDesc) {
    info("-- Edit membership: " + membershipName + "--");
    boolean verifyMembership;
    verifyMembership = testBase.isTextPresent(membershipName);
    if (verifyMembership) {
      evt.waitForTextPresent(membershipName);
    } else {
      evt.click(ELEMENT_NEXT_PAGE);
    }
    String editIcon = ELEMENT_MEMBERSHIP_EDIT_ICON.replace("${membership}", membershipName);
    String membershipInput = ELEMENT_MEMBERSHIP_INPUT.replace("${membershipName}", membershipName);
    evt.click(editIcon);

    evt.waitForAndGetElement(membershipInput);
    evt.type(ELEMENT_TEXTAREA_DESCRIPTION, newDesc, true);
    evt.click(ELEMENT_SAVE_BUTTON);
    if (verifyMembership) {
      evt.waitForTextPresent(membershipName);
    } else {
      evt.click(ELEMENT_NEXT_PAGE);
    }
    evt.waitForTextPresent(newDesc);
  }

  /**
   * Verify membership
   *
   * @param mem boolean
   * @param des boolean
   * @param isDisplay boolean
   */
  public void verifyMembership(String mem, String des, boolean isDisplay) {
    info("verify display of membership");
    evt.click(ELEMENT_TAB_MEMBERSHIP_MANAGEMENT);
    if (isDisplay) {
      evt.waitForAndGetElement(ELEMENT_MEMBERSHIP_DESCRIPTION.replace("$mem", mem).replace("$des", des));
    }
  }

  /**
   * Verify membership in edit form
   *@param user String
   * @param mem String
   * @param isDisplay boolean
   */
  public void verifyMembershipInEditForm(String user, String mem, boolean isDisplay) {
    info("verify display of membership");
    evt.click(ELEMENT_EDIT_USER_MEM_IN_TABLE_ICON.replace("$user", user), 0, true);
    if (isDisplay) {
      evt.waitForAndGetElement(ELEMENT_EDIT_USER_MEM_FORM.replace("$mem", mem));
    }
  }

  /**
   * function: Delete Membership
   *
   * @param membershipName name of membership
   * @param verify (True: if you want to verify membership deleted successfully)
   */
  public void deleteMembership(String membershipName, boolean verify) {
    boolean verifyMembership;
    verifyMembership = testBase.isTextPresent(membershipName);
    if (verifyMembership) {
      evt.waitForTextPresent(membershipName);
    } else {
      evt.click(ELEMENT_NEXT_PAGE);
    }
    String deleteIcon = ELEMENT_MEMBERSHIP_DELETE_ICON.replace("${membership}", membershipName);
    info("--Deleting membership--");
    evt.click(deleteIcon);
    alert.waitForConfirmation(ELEMENT_MSG_CONFIRM_DELETE_MEMBERSHIP);
    if (verify) {
      if (verifyMembership)
        evt.waitForTextNotPresent(membershipName);
      if (evt.waitForAndGetElement(ELEMENT_NEXT_PAGE, 10000, 0) != null) {
        evt.click(ELEMENT_NEXT_PAGE);
        evt.waitForTextNotPresent(membershipName);
      }
    } else {
      evt.waitForTextPresent(ELEMENT_MSG_CANNOT_DELETE);
      evt.click(ELEMENT_OK_BUTTON, 0, true);
    }
  }

  /**
   * function: Go to edit user's information
   *
   * @param username name of user need to edit information
   */
  public void goToEditUserInfo(String username) {
    info("--Search user " + username + "--");
    if (ELEMENT_BTN_SEARCH_USER.is(Condition.not(Condition.exist))) {
      ELEMENT_BTN_USER_TAB.click();
    }
    if (testBase.isTextPresent("Search")) {
      $(ELEMENT_INPUT_SEARCH_USER_NAME).setValue(username);
    }
    ELEMENT_SELECT_BOX_USERS.selectOptionByValue("userName");
    ELEMENT_BTN_SEARCH_USER.click();
    info("--Editing user " + username + "--");
    $(byText(username)).parent().parent().find(ELEMENT_ICON_EDIT_USER).click();
  }

  /**
   * function: Edit user's information in Account Tab
   *
   * @param first first name of user
   * @param last last name of user
   * @param displayName display name of user
   * @param email email of user
   */
  public void editUserInfo_AccountTab(String first, String last, String displayName, String email) {
    info("--editUserInfo_AccountTab--");

    $(ELEMENT_FIRSTNAME).setValue(first);
    $(ELEMENT_LASTNAME).setValue(last);
    $(ELEMENT_DISPLAY_NAME).setValue(displayName);
    $(ELEMENT_EMAIL).setValue(email);

    ELEMENT_BTN_SAVE_EDIT_USER.click();
    ELEMENT_BTN_SAVE_EDIT_USER.waitUntil(Condition.disappears, Configuration.timeout);
    $(byText("The user profile has been updated.")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText("OK")).click();
  }

  /**
   * function: Edit user's information in User Profile Tab
   *
   * @param givenName given name of user
   * @param familyName family name of user
   * @param nickName nick name of user
   * @param birthday birthday of user
   * @param Gender Gender of user
   * @param Employer Employer of user
   * @param Department Department of user
   * @param jobTitle jobTitle of user
   * @param language language of user
   */
  public void editUserInfo_UserProfileTab(String givenName,
                                          String familyName,
                                          String nickName,
                                          String birthday,
                                          String Gender,
                                          String Employer,
                                          String Department,
                                          String jobTitle,
                                          String language) {
    evt.waitForAndGetElement(ELEMENT_USER_PROFILE_TAB, 2000, 1);
    evt.click(ELEMENT_USER_PROFILE_TAB);
    info("--editUserInfo_UserProfileTab--");
    if (givenName != null && givenName != "") {
      evt.type(ELEMENT_GIVEN_NAME, givenName, true);
    }
    if (familyName != null && familyName != "") {
      evt.type(ELEMENT_FAMILY_NAME, familyName, true);
    }
    if (nickName != null && nickName != "") {
      evt.type(ELEMENT_NICK_NAME, nickName, true);
    }
    if (birthday != null && birthday != "") {
      evt.type(ELEMENT_BIRTHDAY, birthday, true);
    }
    if (Gender != null && Gender != "") {
      $(ELEMENT_GENDER).selectOptionByValue(Gender);
    }
    if (Employer != null && Employer != "") {
      evt.type(ELEMENT_EMPLOYER, Employer, true);
    }
    if (Department != null && Department != "") {
      evt.type(ELEMENT_DEPARTMENT, Department, true);
    }
    if (jobTitle != null && jobTitle != "") {
      evt.type(ELEMENT_JOB_TITLE, jobTitle, true);
    }
    if (language != null && language != "") {
      evt.select(ELEMENT_LANGUAGE, language);
    }
    $(byXpath(ELEMENT_SAVE_BUTTON)).click();
    $(byXpath(ELEMENT_SAVE_BUTTON)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    $(byText(ELEMENT_MSG_UPDATE_USER_PROFILE)).waitUntil(Condition.visible,Configuration.timeout);
    $(byXpath(ELEMENT_CLOSE_MESSAGE)).click();
    $(byXpath(ELEMENT_CLOSE_MESSAGE)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * function: Search user
   *
   * @param user (Can be: User name, Last name, First name or Email of the user
   *          you want to search)
   * @param searchOption (Can be: User name, Last name, First name or Email option
   *          corresponding with information you input in "Search")
   */
  public void searchUser(String user, String searchOption) {
    info("--Search user " + user + "--");
    if (testBase.isTextPresent("Search")) {
      evt.type(GateinLocator.ELEMENT_INPUT_SEARCH_USER_NAME, user, true);
      evt.select(GateinLocator.ELEMENT_SELECT_SEARCH_OPTION, searchOption);
    }

    evt.click(GateinLocator.ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
    if (testBase.isTextNotPresent(user))
      evt.click(ELEMENT_OK_BUTTON);
  }

  /**
   * Search User is not found
   *
   * @param user  String
   * @param searchOption String
   */
  public void searchUserNotFound(String user, String searchOption) {
    info("--Search user " + user + "--");
    if (testBase.isTextPresent("Search")) {
      evt.type(GateinLocator.ELEMENT_INPUT_SEARCH_USER_NAME, user, true);
      evt.select(GateinLocator.ELEMENT_SELECT_SEARCH_OPTION, searchOption);
    }

    evt.click(GateinLocator.ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
    evt.waitForTextNotPresent(user);
  }

  /**
   * Find a user by clicking next arrow
   *
   * @param user String
   */
  public void findUsersBbyNextArrow(String user) {
    if (evt.waitForAndGetElement(ELEMENT_PAGINATION_CONTROL, 2000, 0) != null) {
      int totalPage = Integer.parseInt(evt.waitForAndGetElement(ELEMENT_PAGINATION_TOTAL_PAGE).getText());
      // if not found user
      if (evt.waitForAndGetElement(ELEMENT_USER_DELETE_ICON.replace("${username}", user), 2000, 0) == null) {
        // click on next arrow
        for (int i = 0; i < totalPage; i++) {
          info("Click on Next arrow");
          evt.click(ELEMENT_PAGINATION_ENABLED_NEXT_ARROW);
          // if found the user, break the loop
          if (evt.waitForAndGetElement(ELEMENT_USER_DELETE_ICON.replace("${username}", user), 2000, 0) != null)
            break;
          // if next arrow is disabled, break the loop
          if (evt.waitForAndGetElement(ELMEMENT_PAGINATION_CONTROL_DISBALED_NEXT_ARROW, 2000, 0) != null)
            break;
        }
      }
    }
  }

  // ************************************************DISABLE
  // USERS***********************************//

  /**
   * Select an option in Disable User drop list
   *
   * @param option is a value as :ENABLED,DISABLED OR ALL
   */
  public void selectDisableStatus(String option) {
    info("---Select status----");
    if (!option.isEmpty()) {
      evt.select(GateinLocator.ELEMENT_DISABLE_USER_DROP_BOX, option);
    }

  }

  /**
   * Enable or disable a User
   *
   * @param userName is username of the user
   * @param isEnabled = true if want to check the user that is enabled = false if
   *          want to check the user that is disabled
   */
  public void enableDisableUser(String userName, boolean isEnabled) {
    info("---Enable a user---");
    evt.click(ELEMENT_DISABLE_USER_HANDLE_BTN.replace("$userName", userName));

    if (isEnabled) {
      info("Verify that user is enabled");
      selectDisableStatus("Enabled");
      evt.waitForAndGetElement(ELEMENT_DISBALE_USER_ENABLED.replace("$userName", userName), 2000, 1);
    } else {
      info("Verify that user is disabled");
      selectDisableStatus("Disabled");
      evt.waitForElementNotPresent(ELEMENT_DISBALE_USER_ENABLED.replace("$userName", userName), 2000, 1);
    }
  }

  /**
   * Verify that the user is shown in the user list
   *
   * @param userName is user-name of the user
   */
  public void verifyUserPresent(String userName) {
    info("---Verify that the user is shown in the table");
    evt.waitForAndGetElement(ELEMENT_USER_NAME_IN_USER_LIST.replace("$userName", userName));
    info("The user is shown in the table");
  }

  /**
   * Verify that the user is not shown in the user list
   *
   * @param userName is user-name of the user
   */
  public void verifyUserNotPresent(String userName) {
    info("---Verify that the user is not shown in the table");
    evt.waitForElementNotPresent(ELEMENT_USER_NAME_IN_USER_LIST.replace("$userName", userName));
    info("The user is not shown in the table");
  }
  // *********************************************************************************************************//

  /**
   * function: Delete user
   *
   * @param username name of user
   */
  public void deleteUser(String username) {
    info("--Deleting user " + username + "--");
    info("--Search user " + username + "--");
    ELEMENT_SELECT_BOX_USERS.selectOptionByValue("userName");
    if (testBase.isTextPresent("Search")) {
      $(ELEMENT_INPUT_SEARCH_USER_NAME).setValue(username);
    }

    ELEMENT_BTN_SEARCH_USER.click();

    $(byClassName("uiIconDeleteUser")).click();
    alert.acceptAlert();
    $(ELEMENT_INPUT_SEARCH_USER_NAME).setValue(username);
    ELEMENT_BTN_SEARCH_USER.click();
    $(byText("No result found.")).waitUntil(Condition.appears, Configuration.timeout);
    dialog.closeMessageDialog();

  }

  /**
   * Remove a user from a group
   *
   * @param username String
   */
  public void removeUser(String username) {
    info("Click on Delete button");
    evt.click(ELEMENT_USER_REMOVE_MEMBER_ICON.replace("${userName}", username));
    alert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_USER_REMOVE_MEMBER_ICON.replace("${userName}", username));
    info("User is removed from the group successfully");
  }

  /**
   * Delete many users at the same time
   *
   * @param arrayUsers ArrayList
   */
  public void deleteAllUsers(ArrayList<String> arrayUsers) {
    for (int i = 0; i < arrayUsers.size(); i++) {
      info("Delete user:" + arrayUsers.get(i));
      deleteUser(arrayUsers.get(i));
      info("Delete user:" + arrayUsers.get(i) + " successfully");
    }
  }

  /**
   * Check status drop box
   */
  public void checkStatusDropBox() {
    info("check status drop box");
    evt.waitForAndGetElement(ELEMENT_DISABLE_USER_STATUS_SELECTED.replace("$status", "Enabled"), 2000, 1);
    evt.waitForAndGetElement(GateinLocator.ELEMENT_DISABLE_USER_DROP_BOX);
    evt.waitForAndGetElement(ELEMENT_DISABLE_USER_STATUS_ALL);
    evt.waitForAndGetElement(ELEMENT_DISABLE_USER_STATUS_DISABLED);
  }

  /**
   * Check display of all enable and disable user
   *
   * @param users String
   */
  public void checkDisplayAllUsers(String... users) {
    info("check display of all users");
    selectDisableStatus("All");
    evt.waitForAndGetElement(GateinLocator.ELEMENT_INPUT_SEARCH_USER_NAME).clear();
    evt.click(GateinLocator.ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
    for (String user : users) {
      findUsersBbyNextArrow(user);
      verifyUserPresent(user);
    }
  }

  /**
   * Check display of enable user
   *
   * @param users String
   */
  public void checkDisplayEnableUser(String... users) {
    info("check display of enable users");
    evt.waitForAndGetElement(ELEMENT_DISABLE_USER_COLUMN);
    for (String user : users) {
      evt.waitForAndGetElement(ELEMENT_DISBALE_USER_ENABLED.replace("$userName", user), 2000, 1);
      evt.waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_NO.replace("$userName", user), 2000, 1, 2);
      evt.waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_YES.replace("$userName", user), 2000, 1, 2);
    }
  }

  /**
   * Check display of disable user
   *
   * @param users String
   */
  public void checkDisplayDisableUser(String... users) {
    info("check display of disable users");
    evt.waitForAndGetElement(ELEMENT_DISABLE_USER_COLUMN);
    for (String user : users) {
      evt.waitForAndGetElement(ELEMENT_DISBALE_USER_DISABLED.replace("$userName", user), 2000, 1);
      evt.waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_NO.replace("$userName", user), 2000, 1, 2);
      evt.waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_YES.replace("$userName", user), 2000, 1, 2);
    }
  }

  /**
   * function: Delete user
   */
  public void deleteUser() {
    info("--Deleting user ");
    if (evt.waitForAndGetElement(ELEMENT_USER_DELETE_ICON1, 2000, 0) != null) {

      evt.click(ELEMENT_USER_DELETE_ICON1);
      alert.waitForConfirmation(ELEMENT_MSG_CONFIRM_DELETE1);

      evt.waitForElementNotPresent(ELEMENT_USER_DELETE_ICON1);
    }
  }

  /**
   * Open Users tab
   */
  public void gotoUserTab() {
    info("Open Users tab");
    evt.click(ELEMENT_USER_TAB);

  }

  /**
   * Check A link is established between the eXo Platform user account and the
   * social network account. By: QuyenNT Date: Dec 1, 2015
   * @param element String
   * @param placeHolder String
   * @param accountValue  String
   */
  public void checkLinkedSocialAccount(String element, String placeHolder, String accountValue) {
    evt.waitForAndGetElement(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
    evt.click(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
    evt.waitForAndGetElement(element.replace(placeHolder, accountValue));
  }

  /**
   * Unlink social network account By: QuyenNT Date: Dec 3, 2015
   * @param unlinkElement object
   */
  public void unLinkedSocialAccount(Object unlinkElement) {
    evt.waitForAndGetElement(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
    evt.click(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
    evt.waitForAndGetElement(unlinkElement);
    evt.click(unlinkElement);
  }
}
