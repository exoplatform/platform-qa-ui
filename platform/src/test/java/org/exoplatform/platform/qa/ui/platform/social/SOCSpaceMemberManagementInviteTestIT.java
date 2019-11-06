package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("social")
@Tag("sniff")
public class SOCSpaceMemberManagementInviteTestIT extends Base {

  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  SpaceHomePage          spaceHomePage;

  UserAndGroupManagement userAndGroupManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    userAndGroupManagement = new UserAndGroupManagement(this);

  }

  @Test
  public void test01_InvitedUserAcceptInvitation() {
    info("Test 2: Invited user accept invitation");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    /*
     * Step Number: 1 Step Name: Step 1: Create a new Space Step Description: - Sign
     * in system - Select space page to view - Click on [Add new Space] - Enter all
     * valid data - Click [Create] Input Data: Expected Outcome: - Create new Space
     * successfully.
     */
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);

    /*
     * Step number: 2 Step Name: Step 2: Send invitation Step Description: - Creator
     * selects the Space which he/she has created at step 1.1. - Click on [Edit
     * space] icon or access the Space and select space settings portlet - Select
     * [Members] tab - Enter valid user name or select a user from list - Click
     * [invite] Input Data: Expected Outcome: Send invitation successfully:
     */
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    ELEMENT_INPUT_INVITE_USER.sendKeys(username2);
    $(ELEMENT_SPACE_BTN_INVITE).click();
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(Condition.appears, Configuration.timeout).click();
    $(byText("Accept")).click();
    homePagePlatform.goToAllSpace();
    $(byText(space)).should(Condition.exist);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceSettingManagement.goToMemberTab();
    $(byId("spaceMemberListBox")).scrollTo().find(byText(username2 + " " + username2)).should(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);

  }

  /**
   * <li>Case ID:122496.</li>
   * <li>Test Case Name: Send request to Space at Visible mode and Validation for
   * registration.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test12_SendRequestToSpaceAtVisibleModeAndValidationForRegistration() {
    info("Test 12 Send request to Space at Visible mode and Validation for registration");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";

    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: create a new space at
     * the visible and validation mode Input Data: - Sign in system - Select space
     * page - Click on [Add new space] button - Enter valid name - Select visibility
     * and check in visible and Validation - Click on [create] button. Expected
     * Outcome: Create a new space successfully
     */
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);

    // spaceSettingManagement.setPermissionForSpace(arrayRight);

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Send request Input
     * Data: - Other user sign in - Go space page - Selectpublic spaces list -
     * Select the space which has created and click on [Request to Join] button
     * Expected Outcome: Send request successfully: The space is move out Public
     * space and added into pending space
     */
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.sendARequestToASpace(space);
    spaceManagement.goToRequestPendingTab();
    $(byText(space)).waitUntil(Condition.appears, Configuration.timeout);
    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Check request sending
     * successfully Input Data: - User created the space sign in - Go to space page
     * - Select the space and click [edit space] or access the space and access the
     * space setting portlet - Select [members] tab Expected Outcome: Name of user
     * at step 3, is in pending list of the space. Click on [validate] if manager
     * accept the request else click on [decline] button
     */
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTabInSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    $(byText(username2 + " " + username2)).scrollTo().should(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);

  }

}
