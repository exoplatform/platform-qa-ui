package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_INTRANET_NOTIFICATION_BELL;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting;

@Tag("functional")
@Tag("social")

public class SOCNotificationIntranetSpaceJoinRequestTestIT extends Base {
  NavigationToolbar        navigationToolbar;

  NotificationsAdminSeting notificationsAdminSeting;

  ManageLogInOut           manageLogInOut;

  AddUsers                 addUsers;

  HomePagePlatform         homePagePlatform;

  SpaceManagement          spaceManagement;

  IntranetNotification     intranetNotification;

  MyNotificationsSetting   myNotificationsSetting;

  UserAddManagement        userAddManagement;

  ActivityStream           activityStream;

  ArrayList<String>        arrayUser;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    notificationsAdminSeting = new NotificationsAdminSeting(this);
    userAddManagement = new UserAddManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    activityStream = new ActivityStream(this);
    intranetNotification = new IntranetNotification(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");

  }

  /**
   * <li>Case ID:125150.</li>
   * <li>Test Case Name: Check Space Join Request notification.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1 - Space Join Request notification is activated in User's B
   * settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : check
   * notification list Step Description: - Login with User B - Click the
   * notification icon - Check the notification list Input Data: Expected Outcome:
   * - The Space Join Request notification is displayed in the list Step number: 2
   * Step Name: Step : Check content of notification Step Description: - Check the
   * notification message Input Data: Expected Outcome: The notification message
   * is : - $AVATAR - $USER has requested access to $SPACE space. - [Accept] |
   * [Refuse] - $DATEWhere : - $AVATAR is the thumbnail of User A - $USER is User
   * A - $DATE is the date of the notification Step number: 3 Step Name: Step 3 :
   * click notification area Step Description: - Click the notification area Input
   * Data: Expected Outcome: - The user is redirect to the Space 1
   */
  @Test
  @Tag("sabis")
  public void test01_CheckSpaceJoinRequestNotification() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@gmail.com";
    String password = "Aa123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);

    info("Check Space Join Request notification");
    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "spaceDes" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpace(spaceName, spaceDes, "validation", "No", "");

    info("User B login");
    manageLogInOut.signIn(username2, password);
    info("User B send a join request to UserA's space");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    spaceManagement.sendARequestToASpace(spaceName, true);

    info("User A login");
    manageLogInOut.signIn(username1, password);
    info("Check intranet notification format");
    String status = "has requested access to ";
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatusSpace(status, spaceName);

    intranetNotification.checkBtnConnectJoinRequest(spaceName);
    info("The user is redirected to the Space");
    intranetNotification.goToDetailRequestJoinSpace(username2, true);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET),Condition.visible,1000);
    info("Check intranet notification format");
    String statusJoin = "joined ";
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.acceptRqConnection(username2 + " " + username2);
    intranetNotification.checkStatusSpace(statusJoin, spaceName);
    info("The user is redirected to the Space");
    intranetNotification.goToDetailJoinSpace(username2, true);
    $(byXpath("//div[@id='UISpaceActivityStreamPortlet']")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs + Configuration.openBrowserTimeoutMs);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatusSpace(statusJoin, spaceName);
    intranetNotification.checkNotStatusSpace(status, spaceName);

    info("Refuse a Space Join Request from notification");
    info("User B login");
    manageLogInOut.signIn(username4, password);

    info("User B send a join request to UserA's space");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    spaceManagement.sendARequestToASpace(spaceName, true);

    info("User A login");
    manageLogInOut.signIn(username3, password);

    info("User A is accepted and member of the space");
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.refuseRqConnection(username4);
    intranetNotification.checkNotStatusSpace(status, spaceName);
    info("Open All page by link");
    open(Configuration.baseUrl+ "/portal/intranet/allNotifications/");
    intranetNotification.checkNotStatusSpace(status, spaceName);

    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username1);
  }


}
