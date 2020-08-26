package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("sniff")
public class SOCSpaceMemberManagementTestIT extends Base {

  NavigationToolbar      navigationToolbar;

  ManageLogInOut         manageLogInOut;

  AddUsers               addUsers;

  SpaceSettingManagement spaceSettingManagement;

  SpaceHomePage          spaceHomePage;

  SpaceManagement        spaceManagement;

  HomePagePlatform       homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    addUsers = new AddUsers(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:121895.</li>
   * <li>Test Case Name: Invite/Accept user to Space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Invite/Accept user
   * Step Description: - Access to a space from my space list - Click on Space
   * Settings icon, select Member tab - Click on User icon, select some users from
   * list and click on Invite icon - Login by invited users: + Go to My space ->
   * Invitations Received tab and click on Accept + Another user click on Ignore
   * button Input Data: Expected Outcome: - Space settings is displayed, focus on
   * Member tab - Name of invited user is displayed on invited list. - After
   * invited user accept invitation, invited space will move from invitation space
   * list to my space list of user - Accepted user is displayed on Member List -
   * After user ignored, the invitation is removed from Received Invitations list
   * and this user does not displayed on Member list of space
   */

  @Test
  public void test02_InviteAcceptUserToSpace() {
    info("Test 02: Invite/Accept user to Space");
    String space = "space" + getRandomNumber();
    String space0 = "space0" + getRandomNumber();
    String space1 = "space1" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String username5 = "usernamee" + getRandomString();
    String email5 = username5 + "@test.com";
    String password = "Aa123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space0, space0);
    spaceSettingManagement.inviteUser(username1, false, "");
    spaceSettingManagement.inviteUser(username2, false, "");
    spaceSettingManagement.inviteUser(username3, false, "");
    spaceSettingManagement.inviteUser(username4, false, "");

    manageLogInOut.signIn(username1, password);
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info(" After invited user accept invitation, invited  space will move from invitation space list to my space list of user");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    spaceSettingManagement.inviteUser(username3, false, "");
    spaceSettingManagement.inviteUser(username4, false, "");
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpace(space1, space1, "validation", "No", "");

    info("Request/Accept user to Space");
    manageLogInOut.signIn(username5, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.sendARequestToASpace(space1);
    spaceManagement.goToRequestPendingTab();
    $(byXpath(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING.replace("${space}", space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space0);

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space0);

    info("Change member's role in Space");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.changeRole(username2 + " " + username2);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.acceptRequest(username5);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space0);

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();

    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.ignoreAInvitation(space);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space0);

    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToSpaceSettingTab();
    info("Accepted user is displayed on Member List");
    ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
    $(byXpath(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}", username2 + " " + username2))).waitUntil(Condition.visible,
                                                                                                           Configuration.timeout);
    $(byXpath(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}",
                                                       username3 + " " + username3))).waitUntil(Condition.not(Condition.visible),
                                                                                                Configuration.timeout);
    info("Remove user from space");
    spaceSettingManagement.removeUser(username4 + " " + username4);
    info("Leave Space");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.leaveSpace(space);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToSpaceSettingTab();
    info(" Space is disappeared from list and moved to All spaces list, user is not member of space");
    ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
    $(byXpath(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}", username2 + " " + username2))).shouldNot(Condition.exist);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space0)).click();
    spaceSettingManagement.goToMemberTab();
    info("Check Members List Is Sorted By Alphabetical Order");
    ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(0).shouldHave(Condition.text(username1 + " " + username1));
    ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(1).shouldHave(Condition.text(username2 + " " + username2));
    ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(2).shouldHave(Condition.text(username3 + " " + username3));
    ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(3).shouldHave(Condition.text(username4 + " " + username4));
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space0, false);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);

  }

}
