package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ICON_LIKE_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_CONTENT_NAME_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import java.awt.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;

public class SOCNotificationIntranetViewAllTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  HomePagePlatform       homePagePlatform;

  ActivityStream         activityStream;

  ManageLogInOut         manageLogInOut;

  SiteExplorerHome       siteExplorerHome;

  CreateNewDocument      createNewDocument;

  SpaceManagement        spaceManagement;

  ConnectionsManagement  connectionsManagement;

  UserProfilePage        userProfilePage;

  AddUsers               addUsers;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  MyNotificationsSetting myNotificationsSetting;

  IntranetNotification   intranetNotification;

  NotificationActivity   notificationActivity;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    activityStream = new ActivityStream(this);
    manageLogInOut = new ManageLogInOut(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    connectionsManagement = new ConnectionsManagement(this);
    userProfilePage = new UserProfilePage(this);
    addUsers = new AddUsers(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    intranetNotification = new IntranetNotification(this);
    notificationActivity = new NotificationActivity(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  /**
   * <li>Case ID:123026.</li>
   * <li>Test Case Name: Check the work of links on View All page.</li>
   * <li>Case ID:123005.</li>
   * <li>Test Case Name: Check UI of the View All page.</li>
   * 
   * @throws AWTException
   */
  @Test
  public void test01_02_CheckTheWorkOfLinksOnViewAllPage_ChecUIOfTheViewAllPage() throws AWTException {
    // set Data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";

    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity2" + getRandomNumber();
    String activity3 = "activity3" + getRandomNumber();
    String activity4 = "activity4" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();

    /*
     * Precondition: The user has following notifications - New User - Space
     * Invitation - Space Join Request - Like - Comment - Connection request - Post
     * on My stream - Post on My Space - Connect ion request (read) - Post in My
     * stream (read)
     */
    info("Create user1 and enable new user and like notifications for user1");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("sign in user1 and enable new user and like notifications");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

    info("Create notifications for user 1");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);

    info("Sign in as user 1 and connect to user 2");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Add activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.checkActivity(activity1);

    info("Add a new space and ivite user 2");
    homePagePlatform.goToAllSpace();
    spaceManagement.goToCreateSpace();
    spaceManagement.addNewSpaceSimple(space1, space1);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User 2 comments to user1's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity1, comment);
    executeJavaScript("window.scrollBy(0,-1500)", "");
    info("user 2 likes user1's activity");
    refresh();
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);

    info("user2 mentions user1 in activity stream");
    homePagePlatform.goToHomePage();
    activityStream.mentionUserActivity(username1, activity4);
    activityStream.checkActivity(activity4);

    info("user 2 posts activity in user1's activity stream");
    homePagePlatform.goToConnections();
    $(byText(username1 + " " + username1)).click();
    navigationToolbar.goToMyActivities();
    activityStream.addActivity(activity2, "");
    activityStream.checkActivity(activity2);

    info("User 2 posts activity in activity stream of user1's space");
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space1);

    info("user posted an activity in space");
    activityStream.addActivity(activity3, "");
    activityStream.checkActivity(activity3);

    info("Sign in as user 3 and create a space, invite user1 to join space and connect to user1");
    manageLogInOut.signIn(username3, password);
    info("User 3 create a space");
    homePagePlatform.goToAllSpace();
    spaceManagement.goToCreateSpace();
    spaceManagement.addNewSpaceSimple(space2, space2);
    info("User3 invites user1 to join space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");
    info("user3 connects to user 1");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);

    info("Sign in as user 4 and request to join user1's space");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.requestToJoinSpace(space1);

    /*
     * Step Number: 1 Step Name: Step 1: Open View All page Step Description: -
     * Login - Click the notifications icon in the top navigation - Click View All
     * button Input Data: Expected Outcome: - The user is redirected to the View All
     * page
     */
    manageLogInOut.signIn(username1, password);
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.not(Condition.exist))) {
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
      } else {
        break;
      }
    }
    /*
     * Step Number: 2 Step Name: Step 2: Check Connection Request notification Step
     * Description: - Click on the Connection Request notification Input Data:
     * Expected Outcome: - The user is redirect to the profile of User A
     */
    $(byText(username3 + " " + username3)).parent()
                                          .shouldHave(Condition.text(username3 + " " + username3 + " wants to connect with you"));
    for (int i = 0; i <= 100; i++) {
      if ($(byText(username3 + " " + username3)).parent().has(Condition.text(username3 + " " + username3
          + " wants to connect with you"))) {
        $(byText(username3 + " " + username3)).parent().click();

      } else {
        break;
      }
    }

    info("Verify that user was redidected to user A's profile");
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(username3 + " " + username3)).should(Condition.exist);

    /*
     * Step Number: 4 Step Name: Step 4: Check Space Invitation notification Step
     * Description: - Click the Space Invitation notification Input Data: Expected
     * Outcome: - The user is redirected to the home of space 1
     */
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.not(Condition.exist))) {
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
      } else {
        break;
      }
    }
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.exist)) {
        $(byText(space2)).parent().click();
      } else {
        break;
      }
    }
    info("Verify user is not redirected to home page of space");
    $(byText(space2)).parent()
                     .shouldHave(Condition.text("You are invited to join the space " + space2 + " by the administrator."));
    refresh();
    /*
     * Step Number: 5 Step Name: Step 5: Check Space Join Request notification Step
     * Description: - Click on the Space Join Request notification Input Data:
     * Expected Outcome: - The user is redirected to the home of space 1
     */
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.not(Condition.exist))) {
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
      } else {
        break;
      }
    }
    $(byId("UIIntranetNotificationsPortlet")).find(byText(space1)).parent().shouldHave(Condition.text(username4 + " " + username4
        + " has requested access to " + space1));
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.exist)) {
        $(byId("UIIntranetNotificationsPortlet")).find(byText(space1)).parent().click();
      } else {
        break;
      }
    }
    info("Verify user is redirected to home page of space");
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(space1)).should(Condition.exist);
    refresh();
    /*
     * Step Number: 6 Step Name: Step 6: Check Comment notification Step
     * Description: - Click on the Comment notification Input Data: Expected
     * Outcome: - The activity is displayed in the activity viewer with all comment
     * expanded.
     */

    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.not(Condition.exist))) {
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
      } else {
        break;
      }
    }
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.exist)) {
        $(byId("UIIntranetNotificationsPortlet")).find(byText(activity1)).parent().click();
      } else {
        break;
      }
    }
    $(byText(activity1)).parent().parent().parent().find(byText(comment)).should(Condition.exist);
    refresh();
    /*
     * Step Number: 7 Step Name: Step 7: Check Like notification Step Description: -
     * Click on the Like notification Input Data: Expected Outcome: - The activity
     * is displayed in the activity viewer with all comment expanded..
     */
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.not(Condition.exist))) {
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
      } else {
        break;
      }
    }
    for (int i = 0; i <= 100; i++) {
      if ($(byId("UIIntranetNotificationsPortlet")).is(Condition.exist)) {
        $(byId("UIIntranetNotificationsPortlet")).find(byText(activity1)).parent().click();
      } else {
        break;
      }
    }
    $(byText(activity1)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).parent().shouldHave(Condition.text("1"));
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);

  }
}
