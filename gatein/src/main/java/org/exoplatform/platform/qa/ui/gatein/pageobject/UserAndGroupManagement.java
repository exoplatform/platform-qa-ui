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
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME;

import java.util.ArrayList;

import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
public class UserAndGroupManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public HomePagePlatform homePagePlatform;

  public Dialog                dialog;
  

  private ElementEventTestBase evt;

  private PlatformBase         plfBase;

  public UserAndGroupManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.dialog = new Dialog(testBase);
    this.plfBase = new PlatformBase(testBase);
    this.homePagePlatform=new HomePagePlatform(testBase);

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
    info("Select Platform/administration group");
    selectGroup("Platform/Administration");
    info("Add user to administration group by type");
    homePagePlatform.refreshUntil($(ELEMENT_INPUT_USERNAME),Condition.visible,1000);
    if (membership.length > 0)
      $(byXpath(ELEMENT_SELECT_MEMBERSHIP)).selectOptionByValue(membership[0]);
    $(ELEMENT_INPUT_USERNAME).setValue(user);
    homePagePlatform.refreshUntil($(ELEMENT_SAVE_BUTTON_2),Condition.visible,1000);
    $(ELEMENT_SAVE_BUTTON_2).click();
    homePagePlatform.refreshUntil($(ELEMENT_SAVE_BUTTON_2),Condition.visible,1000);
    String addedUser = ELEMENT_ADDED_GROUP_USER_IN_TABLE.replace("${username}", user);
    if ($(ELEMENT_MSG_TOTAL_PAGES).is(Condition.visible)) {
      plfBase.usePaginator(addedUser, ELEMENT_USER_NOT_FOUND.replace("${user}", user));
    } else {
     $(byXpath(addedUser)).waitUntil(Condition.visible,Configuration.timeout);
    }
    info("User is added to administration group");
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
    } else {
      $(ELEMENT_INPUT_USERNAME).waitUntil(Condition.visible,Configuration.timeout).scrollTo();
      homePagePlatform.refreshUntil($(ELEMENT_INPUT_USERNAME),Condition.visible,1000);
      $(ELEMENT_INPUT_USERNAME).sendKeys(userNames);
    }
    $(byId("membership")).selectOption(memberShip);
    $(ELEMENT_SAVE_BUTTON_2).click();
    $(byXpath(ELEMENT_ADDED_GROUP_USER_IN_TABLE.replace("${username}", userNames))).waitUntil(Condition.visible,Configuration.timeout);
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

  // ************************************************DISABLE
  // USERS***********************************//
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
}
