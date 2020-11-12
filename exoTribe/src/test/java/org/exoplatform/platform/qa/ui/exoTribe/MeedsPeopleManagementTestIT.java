package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
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

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeMeedsPeople")
public class MeedsPeopleManagementTestIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform homePagePlatform;

  TribeUserProfilePage tribeUserProfilePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    tribeUserProfilePage = new TribeUserProfilePage(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);

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
    connectionsManagement.tribeConnectToAUser(tribe_username3);

    info("Invited user accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(tribe_user1);
    info("Verify after accept");
    ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);

    homePagePlatform.goToPeoplePageTribeViaUrl();
    ELEMENT_PEOPLE_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("My Connections");

    info("Check My Connections Pull Down Filter");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user3))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Go to My Connections and Click Remove Connection button");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.removeConnectionDW(tribe_user3);
    info("Verify after remove invitation");
    connectionsManagement.verifyConnectionDW(tribe_user3, false, "My Connections");

  }

  @Test
  public void test03_CheckTheSuggestionsWidgetInPeoplePage() {

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
    sleep(2000);
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
    ELEMENT_CLOSE_SENT_REQUESTS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  @Test
  public void test04_CheckThatTheLeaderboardWidgetIsExisting() {

    manageLogInOut.signInTribe(tribe_username, tribe_password);

    info("Go to People Page");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Check that the Leaderboard Widget is displayed");
    Assert.assertEquals(ELEMENT_LEADER_BOARD_TITLE_DW.getText(), "Classement");

    info("Display the 9 first places on Leaderboard");
    String firstUserLeaderboard = ELEMENT_FIRST_USER_LEADER_BOARD_POSITION_DW.getText();
    String secondUserLeaderboard = ELEMENT_SECOND_USER_LEADER_BOARD_POSITION_DW.getText();

    info("First User On Leaderboard is" + firstUserLeaderboard);
    info("Second User On Leaderboard is" + secondUserLeaderboard);

    info("Display the 9 first users points on Leaderboard");
    String firstUsePointsLeaderboard = ELEMENT_FIRST_USER_LEADER_BOARD_POINTS_DW.getText();
    String secondUserPointsLeaderboard = ELEMENT_SECOND_USER_LEADER_BOARD_POINTS_DW.getText();

    info("First User Points On Leaderboard is" + firstUsePointsLeaderboard);
    info("Second User Points On Leaderboard is" + secondUserPointsLeaderboard);

    String currentUserPositionLeaderboard = ELEMENT_CURRENT_USER_LEADER_BOARD_POSITION_DW.getText();
    String currentUserPointsLeaderboard = $(By.xpath(ELEMENT_CURRENT_USER_LEADER_BOARD_POINTS_DW.replace("{id}", currentUserPositionLeaderboard))).getText();

    info("Current user position on Leaderboard" + currentUserPositionLeaderboard);
    info("Current user points on Leaderboard" + currentUserPointsLeaderboard);

  }

  @Test
  public void test05_CheckReceivedInvitationsBehavior() {

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("Click on Connect button to invite users");
    connectionsManagement.tribeConnectToAUser(tribe_username3);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username2, tribe_password2);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("Click on Connect button to invite users");
    connectionsManagement.tribeConnectToAUser(tribe_username3);
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("Click on Connect button to invite users");
    connectionsManagement.tribeConnectToAUser(tribe_username4);

    homePagePlatform.goToPeoplePageTribeViaUrl();

    ELEMENT_SENT_REQUESTS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that " + tribe_user4 + " is displayed");
    $(byXpath(ELEMENT_SENT_REQUESTS_USERS_DW.replace("${user}", tribe_user4))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Delete the Sent Request");
    $(byXpath(ELEMENT_DELETE_SENT_REQUESTS_USERS_DW.replace("${user}", tribe_user4))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Close Sent Requests Button");
    ELEMENT_CLOSE_SENT_REQUESTS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    homePagePlatform.goToPeoplePageTribeViaUrl();

    ELEMENT_SENT_INVITATIONS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that " + tribe_dw_user2 + " is displayed");
    $(byXpath(ELEMENT_SENT_INVITATIONS_USERS_DW.replace("${user}", tribe_dw_user2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check that " + tribe_dw_user1 + " is displayed");
    $(byXpath(ELEMENT_SENT_INVITATIONS_USERS_DW.replace("${user}", tribe_dw_user1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Delete the Invitations");
    $(byXpath(ELEMENT_DELETE_SENT_INVITATIONS_USERS_DW.replace("${user}", tribe_dw_user1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath(ELEMENT_DELETE_SENT_INVITATIONS_USERS_DW.replace("${user}", tribe_dw_user2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Close Invitations Button");
    ELEMENT_CLOSE_SENT_INVITATIONS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();


  }

}
