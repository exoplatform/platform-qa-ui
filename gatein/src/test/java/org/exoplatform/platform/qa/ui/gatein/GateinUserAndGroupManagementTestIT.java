package org.exoplatform.platform.qa.ui.gatein;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONTENT_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_NAME_OF_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_INPUT_SEARCH_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

/**
 * @date March 10 2015
 * @author tult
 */
@Tag("gatein")
@Tag("smoke")
public class GateinUserAndGroupManagementTestIT extends Base {

  NavigationToolbar      navigationToolbar;

  UserAndGroupManagement userandgroupmanagement;

  UserAddManagement      useraddmanagement;

  HomePagePlatform       homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    navigationToolbar = new NavigationToolbar(this);
    userandgroupmanagement = new UserAndGroupManagement(this);
    useraddmanagement = new UserAddManagement(this);
    homePagePlatform = new HomePagePlatform(this);
  }

  /**
   * <li>Case ID:123072.</li>
   * <li>Test Case Name: Add new group.</li>
   * <li>Case ID:123094.</li>
   * <li>Test Case Name: Edit Group.</li>
   * <li>Case ID:123073.</li>
   * <li>Test Case Name: Add user into group.</li>
   * <li>Case ID:123095.</li>
   * <li>Test Case Name: Delete group.</li>
   */
  @Test
  public void test01_02_03_04_AddEditDeleteGroupAddUsersToGroup() {
    String groupName = "groupName" + getRandomNumber();
    String groupLabel = "groupLabel" + getRandomNumber();
    String groupDesc = "groupDesc" + getRandomNumber();

    String newLabel = "newLabel" + getRandomNumber();
    String newDesc = "newDesc" + getRandomNumber();

    String membership1 = "author";
    String membership2 = "editor";
    /*
     * Step Number: 1 Step Name: Step 1: Add new Group Step Description: - Go to
     * Group/Organization/ User and group management - Click Add new group icon -
     * Input some filed required and click Save Input Data: Expected Outcome: - Add
     * group successfully
     */
    info("Test Case 01: Add new group");
    navigationToolbar.goToUsersAndGroupsManagement();
    userandgroupmanagement.goToGroupTab();
    userandgroupmanagement.addGroup(groupName, groupLabel, groupDesc, true);

    /*
     * Step Number: 2 Step Name: Step 2: Edit Group Step Description: - Go to
     * Administration/Users/Groups and Roles - Select a group and click [Edit
     * selected group] - Change some fields and click Save Input Data: Expected
     * Outcome: - The group is updated with the change value
     */
    info("Test Case 02: Edit group");
    userandgroupmanagement.editGroup(groupLabel, null, newLabel, newDesc, true);

    /*
     * Step Number: 3 Step Name: Step 3: Add user to Group Step Description: -
     * Choose Group management form - Select a group from list - Input or search
     * user from list - Click Add button - Choose membership type and Save Input
     * Data: Expected Outcome: - Add user into group successfully
     */
    info("Test Case 03: Add some users into group");
    userandgroupmanagement.addUsersToGroup("mary", membership1, true, true);
    userandgroupmanagement.addUsersToGroup("demo", membership2, false, true);

    /*
     * Step Number: 4 Step Name: Step 4: Delete Group Step Description: - Go to
     * Group/Organization/ User and group management - Select a group and click
     * [Delete selected group] icon - Click Save Input Data: Expected Outcome: - The
     * group is removed from the list
     */
    info("Test Case 04: Delete group");
    userandgroupmanagement.deleteGroup(groupLabel, true);
  }

  /**
   * <li>Case ID:123098.</li>
   * <li>Test Case Name: Edit User information.</li>
   * <li>Case ID:123071.</li>
   * <li>Test Case Name: Search user.</li>
   * <li>Case ID:123070.</li>
   * <li>Test Case Name: Delete user.</li>
   */
  @Test
  public void test08EditUser() {
    String username = "username" + getRandomNumber();
    String password = "password" + getRandomNumber();
    String firstName = getRandomString();
    String lastName = getRandomString();
    String email = firstName + getRandomNumber() + "@test.com";

    String newFirstName = getRandomString();
    String newLastName = getRandomString();
    String newDisplayName = "newDisplayName" + getRandomNumber();
    String newEmail = newFirstName + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create new user");
    useraddmanagement.addUser(username, password, email, firstName, lastName);

    /*
     * Step Number: 1 Step Name: Step 1: Edit User information Step Description: -
     * Go to Group/Organization/ User and group management - Select a user and click
     * [Edit user info] icon - Change some fields and click Save Input Data:
     * Expected Outcome: - Users list is shown properly - User is updated with the
     * change value
     */
    info("Test Case 08: Edit user information");
    navigationToolbar.goToUsersAndGroupsManagement();
    userandgroupmanagement.goToEditUserInfo(username);
    userandgroupmanagement.editUserInfo_AccountTab(newFirstName, newLastName, newDisplayName, newEmail);
    $(byText(newFirstName)).should(Condition.exist);

    /*
     * Step Number: 2 Step Name: Step 2: Search User Step Description: - Search user
     * by: + User name + Last Name + First Name + Email Input Data: Expected
     * Outcome: - The results are displayed matching with the search keyword
     */
    /*
     * info("Test Case 09: Search user follow username, last name, first name, email"
     * ); userandgroupmanagement.searchUser(DATA_USER1, searchUserName);
     * userandgroupmanagement.searchUser(searchUser1, searchLastName);
     * userandgroupmanagement.searchUser(searchUser2, searchFirstName);
     * userandgroupmanagement.searchUser(newEmail, searchEmail); /*Step Number: 3
     * Step Name: Step 3: Delete User Step Description: - Go to Group/Organization/
     * User and group management - Select a user in list and click [Delete user]
     * icon Input Data: Expected Outcome: - - The user will be removed from the list
     */
    info("Test Case 10: Delete user");
    userandgroupmanagement.deleteUser(username);
  }

  /**
   * <li>Case ID:123098.</li>
   * <li>Test Case Name: Edit User information.</li>
   * <li>Case ID:123071.</li>
   * <li>Test Case Name: Search user.</li>
   * <li>Case ID:123070.</li>
   * <li>Test Case Name: Delete user.</li>
   */
  @Test
  public void test09_SearchUser() {
    String username = "username" + getRandomNumber();
    String password = "password" + getRandomNumber();
    String firstName = getRandomString();
    String lastName = getRandomString();
    String email = firstName + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create new user");
    useraddmanagement.addUser(username, password, email, firstName, lastName);

    /*
     * Step Number: 1 Step Name: Step 1: Edit User information Step Description: -
     * Go to Group/Organization/ User and group management - Select a user and click
     * [Edit user info] icon - Change some fields and click Save Input Data:
     * Expected Outcome: - Users list is shown properly - User is updated with the
     * change value
     */
    info("Test Case 08: Edit user information");
    navigationToolbar.goToUsersAndGroupsManagement();
    /*
     * if(waitForAndGetElement(ELEMENT_USER_MANAGEMENT_ACTIVE_TAB, 3000, 0) == null)
     * click(ELEMENT_USER_MANAGEMENT_TAB);
     */

    /*
     * Step Number: 2 Step Name: Step 2: Search User Step Description: - Search user
     * by: + User name + Last Name + First Name + Email Input Data: Expected
     * Outcome: - The results are displayed matching with the search keyword
     */
    /*
     * info("Test Case 09: Search user follow username, last name, first name, email"
     * ); userandgroupmanagement.searchUser(DATA_USER1, searchUserName);
     * userandgroupmanagement.searchUser(searchUser1, searchLastName);
     * userandgroupmanagement.searchUser(searchUser2, searchFirstName);
     * userandgroupmanagement.searchUser(newEmail, searchEmail); /*Step Number: 3
     * Step Name: Step 3: Delete User Step Description: - Go to Group/Organization/
     * User and group management - Select a user in list and click [Delete user]
     * icon Input Data: Expected Outcome: - - The user will be removed from the list
     */
    info("Test Case 10: Delete user");
    userandgroupmanagement.deleteUser(username);
  }

  /**
   * <li>Case ID:123098.</li>
   * <li>Test Case Name: Edit User information.</li>
   * <li>Case ID:123071.</li>
   * <li>Test Case Name: Search user.</li>
   * <li>Case ID:123070.</li>
   * <li>Test Case Name: Delete user.</li>
   */
  @Test
  public void test10_DeleteUser() {
    String username = "username" + getRandomNumber();
    String password = "password" + getRandomNumber();
    String firstName = getRandomString();
    String lastName = getRandomString();
    String email = firstName + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create new user");
    useraddmanagement.addUser(username, password, email, firstName, lastName);

    /*
     * Step Number: 1 Step Name: Step 1: Edit User information Step Description: -
     * Go to Group/Organization/ User and group management - Select a user and click
     * [Edit user info] icon - Change some fields and click Save Input Data:
     * Expected Outcome: - Users list is shown properly - User is updated with the
     * change value
     */
    info("Test Case 08: Edit user information");
    navigationToolbar.goToUsersAndGroupsManagement();
    /*
     * Step Number: 2 Step Name: Step 2: Search User Step Description: - Search user
     * by: + User name + Last Name + First Name + Email Input Data: Expected
     * Outcome: - The results are displayed matching with the search keyword
     */
    /*
     * info("Test Case 09: Search user follow username, last name, first name, email"
     * ); userandgroupmanagement.searchUser(DATA_USER1, searchUserName);
     * userandgroupmanagement.searchUser(searchUser1, searchLastName);
     * userandgroupmanagement.searchUser(searchUser2, searchFirstName);
     * userandgroupmanagement.searchUser(newEmail, searchEmail); /*Step Number: 3
     * Step Name: Step 3: Delete User Step Description: - Go to Group/Organization/
     * User and group management - Select a user in list and click [Delete user]
     * icon Input Data: Expected Outcome: - - The user will be removed from the list
     */
    info("Test Case 10: Delete user");
    userandgroupmanagement.deleteUser(username);
  }

  @Test
  @Tag("search")
  public void test11_SearchUserFromHomeSearchBar() {
    String username = "username" + getRandomNumber();
    String password = "password" + getRandomNumber();
    String firstName = getRandomString();
    String lastName = getRandomString();
    String email = firstName + getRandomNumber() + "@test.com";

    String username1 = "username1" + getRandomNumber();
    String password1 = "password1" + getRandomNumber();
    String firstName1 = getRandomString();
    String lastName1 = getRandomString();
    String email1 = firstName + getRandomNumber() + "@test.com";

    String username2 = "username" + getRandomNumber();
    String password2 = "password" + getRandomNumber();
    String firstName2 = getRandomString();
    String lastName2 = getRandomString();
    String email2 = firstName + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    useraddmanagement.addUser(username, password, email, firstName, lastName);
    useraddmanagement.addUser(username1, password1, email1, firstName1, lastName1);
    useraddmanagement.addUser(username2, password2, email2, firstName2, lastName2);
    homePagePlatform.goToHomePage();
    ELEMENT_ICON_SEARCH.click();
    ELEMENT_SEARCH_INPUT.setValue(firstName + " " + lastName);
    ELEMENT_SEARCH_RESULT.shouldHave(Condition.exactText(firstName + " " + lastName));
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(username);
    userandgroupmanagement.deleteUser(username1);
    userandgroupmanagement.deleteUser(username2);

  }

  @Test
  @Tag("search")
  public void test12_SearchUserFromPeopleSearchByName() {
    String username = "username" + getRandomNumber();
    String password = "password" + getRandomNumber();
    String firstName = getRandomString();
    String lastName = getRandomString();
    String email = firstName + getRandomNumber() + "@test.com";

    String username1 = "username1" + getRandomNumber();
    String password1 = "password1" + getRandomNumber();
    String firstName1 = getRandomString();
    String lastName1 = getRandomString();
    String email1 = firstName + getRandomNumber() + "@test.com";

    String username2 = "username" + getRandomNumber();
    String password2 = "password" + getRandomNumber();
    String firstName2 = getRandomString();
    String lastName2 = getRandomString();
    String email2 = firstName + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    useraddmanagement.addUser(username, password, email, firstName, lastName);
    useraddmanagement.addUser(username1, password1, email1, firstName1, lastName1);
    useraddmanagement.addUser(username2, password2, email2, firstName2, lastName2);
    homePagePlatform.goToConnections();
    ELEMENT_NAME_OF_PEOPLE.setValue(firstName + " " + lastName).waitUntil(Condition.visible, Configuration.timeout).pressEnter();
    ELEMENT_CONTENT_PEOPLE.waitUntil(Condition.visible, Configuration.timeout).find(byText(firstName + " " + lastName)).should(Condition.exist);
    ELEMENT_CONTENT_PEOPLE.waitUntil(Condition.visible, Configuration.timeout).find(byText(firstName1 + " " + lastName1)).shouldNot(Condition.exist);
    refresh();
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(username);
    userandgroupmanagement.deleteUser(username1);
    userandgroupmanagement.deleteUser(username2);

  }
  @Test
  @Tag("search")
  public void test09_SearchUserWithDashInFirstName() {
    String username = "username" + getRandomNumber();
    String password = "password" + getRandomNumber();
    String firstName = "user-name"+getRandomString();
    String lastName = getRandomString();
    String email = firstName + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create new user");
    useraddmanagement.addUser(username, password, email, firstName, lastName);
    info("Test Case 08: Edit user information");
    navigationToolbar.goToUsersAndGroupsManagement();
    $(ELEMENT_INPUT_SEARCH_USER_NAME).setValue(firstName);
    ELEMENT_SELECT_BOX_USERS.selectOptionByValue("firstName");
    ELEMENT_BTN_SEARCH_USER.click();
    ELEMENT_TABLE_LIST_USERS.find(byText(username)).should(Condition.exist);
    ELEMENT_TABLE_LIST_USERS.findAll(ELEMENT_LINE_IN_TABLE_LIST_USERS).shouldHaveSize(1);
    info("Test Case 10: Delete user");
    userandgroupmanagement.deleteUser(username);
  }
}
