package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.pageobject.TribeUserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
public class MeedsPeopleManagementDWTestIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  ConnectionsManagement connectionsManagement;

  TribeSpaceManagement tribeSpaceManagement;

  HomePagePlatform homePagePlatform;

  TribeUserProfilePage tribeUserProfilePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    tribeUserProfilePage = new TribeUserProfilePage(this);
    manageLogInOut = new ManageLogInOut(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_CheckThatPeopleListCardsAreDisplayedInPeoplePage() {

    info("Go to People Page");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Check that the Fullname is displayed");
    ELEMENT_ADD_CONTACT_FULLNAME_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that the circular avatar is displayed");
    ELEMENT_CONTACT_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that the Add Contact Button is displayed");
    ELEMENT_ADD_CONTACT_TITLE_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_ADD_CONTACT_TITLE_DW.getText(), "Connect");

    info("Check that the cover is displayed");
    ELEMENT_ADD_CONTACT_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_ADD_CONTACT_COVER_WIDTH_DW.getAttribute("style"), "width: 1584px;");

    info("Check that the job is displayed");
    ELEMENT_ADD_CONTACT_JOB_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  @Test
  public void test02_CheckThePullDownFilterInPeoplePageAfterConnectingToOtherUser() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    info("Go to People Page");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Check that Filter is displayed");
    ELEMENT_PEOPLE_TRIBE_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_PEOPLE_TRIBE_SEARCH_TEXT.getAttribute("placeholder"), "Filter by name, position or skill");

    ELEMENT_PEOPLE_SHOWING_RESULTS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    String resultsNumber = ELEMENT_PEOPLE_SHOWING_RESULTS_DW.getText().split("Showing ")[1].split("results")[0];
    info(resultsNumber + "Results are displayed");

    info("Check that Pulldown Filter is displayed");
    ELEMENT_PEOPLE_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Access people list, invite an user");
    connectionsManagement.tribeConnectToAUser(username1);

    info("Invited user accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(DATA_NAME_USER1);
    info("Verify after accept");
    ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    homePagePlatform.goToPeoplePageTribeViaUrl();
    ELEMENT_PEOPLE_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("My Connections");

    info("Check My Connections Pull Down Filter");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", username1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Go to My Connections and Click Remove Connection button");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.removeConnectionDW(username1);
    info("Verify after remove invitation");
    connectionsManagement.verifyConnectionDW(username1, false, "My Connections");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.deleteUserDW(username1);
  }

  @Test
  public void test03_CheckTheSuggestionsWidgetInPeoplePage() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    addUsers.addUserTribe(username2, password, email2, username2, username2, "");
    addUsers.addUserTribe(username3, password, email3, username3, username3, "");
    sleep(2000);
    addUsers.addUserTribe(username4, password, email4, username4, username4, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    inviteUsers.add(username2);
    inviteUsers.add(username3);
    inviteUsers.add(username4);

    info("Go to People Page");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("The suggestion widget is existing and displayed only 2 users with Add button and Delete buttons");
    ELEMENT_FIRST_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_SECOND_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_ADD_FIRST_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_ADD_SECOND_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_DELETE_FIRST_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_DELETE_SECOND_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    String firstUserSuggestion = ELEMENT_FIRST_SUGGESTION_DW.getText();
    String secondtUserSuggestion = ELEMENT_SECOND_SUGGESTION_DW.getText();

    info("Add the first user suggestion");
    ELEMENT_ADD_FIRST_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The first user suggestion is not displayed");
    sleep(2000);
    Assert.assertNotEquals(ELEMENT_FIRST_SUGGESTION_DW.getText(), firstUserSuggestion);
    Assert.assertEquals(ELEMENT_FIRST_SUGGESTION_DW.getText(), secondtUserSuggestion);

    info("Delete the user suggestion");
    ELEMENT_DELETE_FIRST_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The user suggestion is not displayed");
    sleep(3000);
    Assert.assertNotEquals(ELEMENT_FIRST_SUGGESTION_DW.getText(), secondtUserSuggestion);

    info("Go to Sent Requests");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    ELEMENT_SENT_REQUESTS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that the added user suggestion is displayed");
    $(byXpath(ELEMENT_SENT_REQUESTS_USERS_DW.replace("${user}", firstUserSuggestion))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check that the deleted user suggestion is not displayed");
    $(byXpath(ELEMENT_SENT_REQUESTS_USERS_DW.replace("${user}", secondtUserSuggestion))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    info("Delete the Sent Request");
    $(byXpath(ELEMENT_DELETE_SENT_REQUESTS_USERS_DW.replace("${user}", firstUserSuggestion))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Close Sent Requests Button");
    ELEMENT_CLOSE_SENT_REQUESTS_BTN.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.deleteUserDW(username1);
    addUsers.deleteUserDW(username2);
    addUsers.deleteUserDW(username3);
    addUsers.deleteUserDW(username4);

  }

  @Test
  public void test04_CheckThatTheLeaderboardWidgetIsExisting() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spacenameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    String spaceNamec = "spacenamec" + getRandomNumber();
    String spaceDesc = "descriptionc" + getRandomNumber();
    String spaceNamed = "spacenamed" + getRandomNumber();
    String spaceDesd = "descriptiond" + getRandomNumber();

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    addUsers.addUserTribe(username2, password, email2, username2, username2, "");
    addUsers.addUserTribe(username3, password, email3, username3, username3, "");
    addUsers.addUserTribe(username4, password, email4, username4, username4, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    inviteUsers.add(username2);
    inviteUsers.add(username3);
    inviteUsers.add(username4);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceDesb, "Open", "No", null);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamec, spaceDesc, "Open", "No", null);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamed, spaceDesd, "Open", "No", null);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    info("Go to People Page");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Check that the Leaderboard Widget is displayed");
    Assert.assertEquals(ELEMENT_LEADER_BOARD_TITLE_DW.getText(), "Leaderboard");

    info("Display the 9 first places on Leaderboard");
    String firstUserLeaderboard = ELEMENT_FIRST_USER_LEADER_BOARD_POSITION_DW.getText();
    String secondUserLeaderboard = ELEMENT_SECOND_USER_LEADER_BOARD_POSITION_DW.getText();
    String thirdUserLeaderboard = ELEMENT_THIRD_USER_LEADER_BOARD_POSITION_DW.getText();


    info("First User On Leaderboard is" + firstUserLeaderboard);
    info("Second User On Leaderboard is" + secondUserLeaderboard);
    info("Third User On Leaderboard is" + thirdUserLeaderboard);


    info("Display the 9 first users points on Leaderboard");
    String firstUsePointsLeaderboard = ELEMENT_FIRST_USER_LEADER_BOARD_POINTS_DW.getText();
    String secondUserPointsLeaderboard = ELEMENT_SECOND_USER_LEADER_BOARD_POINTS_DW.getText();
    String thirdUserPointsLeaderboard = ELEMENT_THIRD_USER_LEADER_BOARD_POINTS_DW.getText();

    info("First User Points On Leaderboard is" + firstUsePointsLeaderboard);
    info("Second User Points On Leaderboard is" + secondUserPointsLeaderboard);
    info("Third User Points On Leaderboard is" + thirdUserPointsLeaderboard);

    String currentUserPositionLeaderboard = ELEMENT_CURRENT_USER_LEADER_BOARD_POSITION_DW.getText();
    String currentUserPointsLeaderboard = $(By.xpath(ELEMENT_CURRENT_USER_LEADER_BOARD_POINTS_DW.replace("{id}", currentUserPositionLeaderboard))).getText();

    info("Current user position on Leaderboard" + currentUserPositionLeaderboard);
    info("Current user points on Leaderboard" + currentUserPointsLeaderboard);

  }

  @Test
  public void test05_CheckReceivedInvitationsBehavior() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    addUsers.addUserTribe(username2, password, email2, username2, username2, "");
    addUsers.addUserTribe(username3, password, email3, username3, username3, "");
    addUsers.addUserTribe(username4, password, email4, username4, username4, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    inviteUsers.add(username2);
    inviteUsers.add(username3);
    inviteUsers.add(username4);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("Click on Connect button to invite users");
    connectionsManagement.tribeConnectToAUser(username3);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("Click on Connect button to invite users");
    connectionsManagement.tribeConnectToAUser(username3);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("Click on Connect button to invite users");
    connectionsManagement.tribeConnectToAUser(username4);

    homePagePlatform.goToPeoplePageTribeViaUrl();

    ELEMENT_SENT_REQUESTS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that " + username4 + " is displayed");
    $(byXpath(ELEMENT_SENT_REQUESTS_USERS_DW.replace("${user}", username4))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Delete the Sent Request");
    $(byXpath(ELEMENT_DELETE_SENT_REQUESTS_USERS_DW.replace("${user}", username4))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Close Sent Requests Button");
    ELEMENT_CLOSE_SENT_REQUESTS_BTN.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    homePagePlatform.goToPeoplePageTribeViaUrl();

    ELEMENT_SENT_INVITATIONS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that " + username2 + " is displayed");
    $(byXpath(ELEMENT_SENT_INVITATIONS_USERS_DW.replace("${user}", username2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check that " + username1 + " is displayed");
    $(byXpath(ELEMENT_SENT_INVITATIONS_USERS_DW.replace("${user}", username1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Delete the Invitations");
    $(byXpath(ELEMENT_DELETE_SENT_INVITATIONS_USERS_DW.replace("${user}", username1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath(ELEMENT_DELETE_SENT_INVITATIONS_USERS_DW.replace("${user}", username2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Close Invitations Button");
    ELEMENT_CLOSE_SENT_INVITATIONS_BTN.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.deleteUserDW(username1);
    addUsers.deleteUserDW(username2);
    addUsers.deleteUserDW(username3);
    addUsers.deleteUserDW(username4);

  }

}
