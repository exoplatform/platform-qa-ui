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
import static java.awt.SystemColor.info;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_INPUT_UPLOAD_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeMeedsPeople")
public class MeedsPeopleManagementTestDWIT extends BaseTribe {
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
    ELEMENT_FIRST_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_SECOND_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_ADD_FIRST_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_ADD_SECOND_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_DELETE_FIRST_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_DELETE_SECOND_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    String firstUserSuggestion = ELEMENT_FIRST_USER_SUGGESTION_DW.getText();
    String secondtUserSuggestion = ELEMENT_SECOND_USER_SUGGESTION_DW.getText();

    info("Add the first user suggestion");
    ELEMENT_ADD_FIRST_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The first user suggestion is not displayed");
    sleep(2000);
    Assert.assertNotEquals(ELEMENT_FIRST_USER_SUGGESTION_DW.getText(), firstUserSuggestion);
    Assert.assertEquals(ELEMENT_FIRST_USER_SUGGESTION_DW.getText(), secondtUserSuggestion);

    info("Delete the user suggestion");
    ELEMENT_DELETE_FIRST_USER_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The user suggestion is not displayed");
    sleep(2000);
    Assert.assertNotEquals(ELEMENT_FIRST_USER_SUGGESTION_DW.getText(), secondtUserSuggestion);

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

    info("Go to People Page");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Check that the Leaderboard Widget is displayed");
    Assert.assertEquals(ELEMENT_LEADER_BOARD_TITLE_DW.getText(), "Leaderboard");

    info("Display the 9 first places on Leaderboard");
    String firstUserLeaderboard = ELEMENT_FIRST_USER_LEADER_BOARD_POSITION_DW.getText();
    String secondUserLeaderboard = ELEMENT_SECOND_USER_LEADER_BOARD_POSITION_DW.getText();
    String thirdUserLeaderboard = ELEMENT_THIRD_USER_LEADER_BOARD_POSITION_DW.getText();
    String fourthUserLeaderboard = ELEMENT_FOURTH_USER_LEADER_BOARD_POSITION_DW.getText();
    String fifthUserLeaderboard = ELEMENT_FIFTH_USER_LEADER_BOARD_POSITION_DW.getText();
    String sixthUserLeaderboard = ELEMENT_SIXTH_USER_LEADER_BOARD_POSITION_DW.getText();
    String seventhUserLeaderboard = ELEMENT_SEVENTH_USER_LEADER_BOARD_POSITION_DW.getText();
    String eighthUserLeaderboard = ELEMENT_EIGHTH_USER_LEADER_BOARD_POSITION_DW.getText();
    String ninthUserLeaderboard = ELEMENT_NINTH_USER_LEADER_BOARD_POSITION_DW.getText();

    info("First User On Leaderboard is" + firstUserLeaderboard);
    info("Second User On Leaderboard is" + secondUserLeaderboard);
    info("Third User On Leaderboard is" + thirdUserLeaderboard);
    info("Fourth User On Leaderboard is" + fourthUserLeaderboard);
    info("Fifth User On Leaderboard is" + fifthUserLeaderboard);
    info("Sixth User On Leaderboard is" + sixthUserLeaderboard);
    info("Seventh User On Leaderboard is" + seventhUserLeaderboard);
    info("Eighth User On Leaderboard is" + eighthUserLeaderboard);
    info("Ninth User On Leaderboard is" + ninthUserLeaderboard);


    info("Display the 9 first users points on Leaderboard");
    String firstUsePointsLeaderboard = ELEMENT_FIRST_USER_LEADER_BOARD_POINTS_DW.getText();
    String secondUserPointsLeaderboard = ELEMENT_SECOND_USER_LEADER_BOARD_POINTS_DW.getText();
    String thirdUserPointsLeaderboard = ELEMENT_THIRD_USER_LEADER_BOARD_POINTS_DW.getText();
    String fourthUserPointsLeaderboard = ELEMENT_FOURTH_USER_LEADER_BOARD_POINTS_DW.getText();
    String fifthUserPointsLeaderboard = ELEMENT_FIFTH_USER_LEADER_BOARD_POINTS_DW.getText();
    String sixthUserPointsLeaderboard = ELEMENT_SIXTH_USER_LEADER_BOARD_POINTS_DW.getText();
    String seventhUserPointsLeaderboard = ELEMENT_SEVENTH_USER_LEADER_BOARD_POINTS_DW.getText();
    String eighthUserPointsLeaderboard = ELEMENT_EIGHTH_USER_LEADER_BOARD_POINTS_DW.getText();
    String ninthUserPointsLeaderboard = ELEMENT_NINTH_USER_LEADER_BOARD_POINTS_DW.getText();

    info("First User Points On Leaderboard is" + firstUsePointsLeaderboard);
    info("Second User Points On Leaderboard is" + secondUserPointsLeaderboard);
    info("Third User Points On Leaderboard is" + thirdUserPointsLeaderboard);
    info("Fourth User Points On Leaderboard is" + fourthUserPointsLeaderboard);
    info("Fifth User Points On Leaderboard is" + fifthUserPointsLeaderboard);
    info("Sixth User Points On Leaderboard is" + sixthUserPointsLeaderboard);
    info("Seventh User Points On Leaderboard is" + seventhUserPointsLeaderboard);
    info("Eighth User Points On Leaderboard is" + eighthUserPointsLeaderboard);
    info("Ninth User Points On Leaderboard is" + ninthUserPointsLeaderboard);

    String currentUserPositionLeaderboard = ELEMENT_CURRENT_USER_LEADER_BOARD_POSITION_DW.getText();
    String currentUserPointsLeaderboard = $(By.xpath(ELEMENT_CURRENT_USER_LEADER_BOARD_POINTS_DW.replace("{id}", currentUserPositionLeaderboard))).getText();

    info("Current user position on Leaderboard" + currentUserPositionLeaderboard);
    info("Current user points on Leaderboard" + currentUserPointsLeaderboard);

  }

  @Test
  public void test05_CheckTheUserAvatarAndCoverInProfilePage() {

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Check That User Cover is displayed in Profile Page");
    ELEMENT_PROFILE_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check That User Avatar is displayed in Profile Page");
    ELEMENT_PROFILE_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check That User Fullname is displayed in Profile Page");
    Assert.assertEquals(ELEMENT_PROFILE_FULLNAME_DW.getText(), tribe_user3);

    info("Check That User Job is displayed in Profile Page");
    Assert.assertEquals(ELEMENT_PROFILE_JOB_DW.getText(), "IT Engineer");

  }

  @Test
  public void test06_CheckContactInformationsInProfilePage() {

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Check That Profile Contact Fullname is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_TITLE_DW.getText(), "Contact information");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FULLNAME_DW.getText(), tribe_user3);

    ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    String contactEmail = ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.getText();
    info("Check That Profile Contact Email is displayed" + contactEmail);

    info("Check That Profile Contact Job is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), "IT Engineer");

    manageLogInOut.signInTribe(tribe_username4, tribe_password4);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Check That User Job is not displayed in Profile Page");
    Assert.assertEquals(ELEMENT_PROFILE_JOB_DW.getText(), " ");

    info("Check That Profile Contact Job is not displayed");
    Assert.assertNotEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), "IT Engineer");

  }

  @Test
  public void test07_UpdateUserBasicInformations() {

    String newFirstName = "Bilel";
    String newLastName = "Dridi";
    String job= "IT Engineer";
    String newJob= "Solutions Consultant";
    String newMail= "Bilel.Dridi@exo.com";

    manageLogInOut.signInTribe(tribe_username, tribe_password);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Update Contact Basic Informations");
    tribeUserProfilePage.updateBasicInformationTribe(newFirstName,newLastName,newMail,newJob);

    info("Check That Profile Contact New Fullname is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FULLNAME_DW.getText(), newFirstName + " " + newLastName);

    ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    String contactEmail = ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.getText();
    info("Check That Profile Contact New Email is displayed" + newMail);

    info("Check That Profile Contact New Job is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), newJob);

    info("Reset Contact Basic Informations");
    tribeUserProfilePage.updateBasicInformationTribe(tribe_user.split(" ")[0],tribe_user.split(" ")[1],tribe_mail,job);

    info("Check That Profile Contact Fullname is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FULLNAME_DW.getText(), tribe_user);

    ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    info("Check That Profile Contact Email is displayed" + tribe_mail);

    info("Check That Profile Contact Job is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), "IT Engineer");

  }

  @Test
  public void test08_UpdateAndAddNewUserOtherInformations() {

    String company = "eXo";
    String phone = "50870088";
    String secondPhone = "71703865";
    String skype = "khalil.riahi12";
    String github= "kriahi12";
    String profileUrl= "https://community-preprod.exoplatform.com/portal/dw/profile/" + tribe_username;
    String secondUrl = "https://www.linkedin.com/";

    manageLogInOut.signInTribe(tribe_username, tribe_password);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Update Contact Other Informations");
    tribeUserProfilePage.updateContactOtherInformationsTribe(company, TribeUserProfilePage.PhoneType.WORK, phone, TribeUserProfilePage.InstantMessagingType.SKYPE, skype, profileUrl);

    info("Check That Profile Contact Company is displayed");
    sleep(2000);
    ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), company);

    info("Check That Profile Contact Phone is displayed" + phone);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_PHONE_DW.getText(), "Professionnel: " + phone);

    info("Check That Profile Contact Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_INSTANT_MESSAGING_DW.getText(), "Skype: " + skype);

    info("Check That Profile Contact Url is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_URL_DW.getText(), profileUrl);

    tribeUserProfilePage.goToUpdateContactInformationsTribe();

    info("Add Second Phone Number");
    tribeUserProfilePage.addNewPhoneNumber();
    tribeUserProfilePage.enterNewPhoneNumber(TribeUserProfilePage.PhoneType.HOME, secondPhone);

    info("Add Second Instant Messaging");
    tribeUserProfilePage.addNewMessagingInstant();
    tribeUserProfilePage.enterNewMessagingInstant(TribeUserProfilePage.InstantMessagingType.GITHUB, github);

    info("Add Second Url");
    tribeUserProfilePage.addNewUrl();
    tribeUserProfilePage.enterNewUrl(secondUrl);

    info("Save Updated Informations");
    tribeUserProfilePage.saveUpdatedInformations();

    info("Check That Profile Contact Company is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), company);

    info("Check That Profile Contact First Phone is displayed" + phone);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION__FIRST_PHONE_DW.getText(), "Professionnel: " + phone);

    info("Check That Profile Contact Second Phone is displayed" + secondPhone);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_PHONE_DW.getText(), "Accueil: " + secondPhone);

    info("Check That Profile Contact First Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FIRST_INSTANT_MESSAGING_DW.getText(), "Skype: " + skype);

    info("Check That Profile Contact Second Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_INSTANT_MESSAGING_DW.getText(), "Github: " + github);

    info("Check That Profile Contact First Url is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION__FIRST_URL_DW.getText(), profileUrl);

    info("Check That Profile Contact Second Url is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_URL_DW.getText(), secondUrl);

    info("Reset Contact Informations");

    tribeUserProfilePage.goToUpdateContactInformationsTribe();

    tribeUserProfilePage.enterNewPhoneNumber(TribeUserProfilePage.PhoneType.OTHER, " ");

    tribeUserProfilePage.enterNewMessagingInstant(TribeUserProfilePage.InstantMessagingType.OTHER, " ");

    tribeUserProfilePage.enterNewUrl(" ");

    tribeUserProfilePage.saveUpdatedInformations();

    tribeUserProfilePage.updateContactOtherInformationsTribe("", TribeUserProfilePage.PhoneType.OTHER, " ", TribeUserProfilePage.InstantMessagingType.GITHUB, github, " ");

    info("Check That Profile Contact Company is not displayed");
    sleep(2000);
    Assert.assertNotEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), company);

    info("Check That Profile Contact Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), "Github: " + github);

  }

}
