package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
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
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;

@Tag("functional")
@Tag("social")
public class SOCNotificationIntranetSpaceInvitationTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  UserAddManagement      userAddManagement;

  ActivityStream         activityStream;

  IntranetNotification   intranetNotification;

  MyNotificationsSetting myNotificationsSetting;

  NotificationActivity   notificationActivity;

  ConnectionsManagement  connectionsManagement;

  UserProfilePage        userProfilePage;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    userAddManagement = new UserAddManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    intranetNotification = new IntranetNotification(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    notificationActivity = new NotificationActivity(this);
    connectionsManagement = new ConnectionsManagement(this);
    userProfilePage = new UserProfilePage(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
  }

  @Test
  @Tag("SOC-6212")
  public void test01_CheckSpaceInvitationNotification() {

    ArrayList<String> arrayUser = new ArrayList<String>();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "Aa123456";

    info("CheckRefuseInvitation");
    homePagePlatform.goToPeople();
    homePagePlatform.searchUsersPeople(DATA_USER3);
    spaceSettingManagement.connectSearchedUser();
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    spaceSettingManagement.declineNotificationConnectRequest(DATA_NAME_USER1);
    homePagePlatform.goToPeople();
    homePagePlatform.searchUsersPeople(DATA_USER1);
    spaceSettingManagement.checkUserNotConnected();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToPeople();
    homePagePlatform.searchUsersPeople(DATA_USER3);
    spaceSettingManagement.checkUserNotConnected();

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);
    info("Check Space Invitation notification");
    info("User B login");
    manageLogInOut.signIn(username2, password);
    info("User B create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    info("User B invites UserA to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, "");
    info("User A login");
    manageLogInOut.signIn(username1, "Aa123456");
    String status = "You're invited to join";
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatusSpace(status, spaceName);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.checkStatusSpace(status, spaceName);
    intranetNotification.checkAvatarInStatus(spaceName, true);
    intranetNotification.checkBtnConnectJoinRequest(spaceName);
    intranetNotification.goToDetailInvitationSpace(spaceName, true);
    $(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET).waitUntil(not(Condition.visible), Configuration.timeout);
    info("AcceptASpaceInvitationFromNotification");
    info("User A accepted invitation");
    intranetNotification.acceptRqConnection(spaceName);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(spaceName);
    $(byXpath("//div[@id='UISpaceActivityStreamPortlet']")).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    String statusJoin = "You joined";
    intranetNotification.checkStatusSpace(statusJoin, spaceName);
    intranetNotification.checkAvatarInStatus(spaceName, true);
    manageLogInOut.signOut();
    info("Refuse a Space Invitation from notification");
    info("User B login");
    manageLogInOut.signIn(username2, password);
    info("User B create a new space");
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
    info("User B invites UserA to the space");
    homePagePlatform.goToSpecificSpace(spaceNameb);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, "");
    info("User A login");
    manageLogInOut.signIn(username1, password);
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatusSpace(status, spaceNameb);
    homePagePlatform.refreshUntil($(byText(spaceNameb)), Condition.visible, 2000);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.checkStatusSpace(status, spaceNameb);
    info("User A refuse invitation");
    intranetNotification.refuseRqConnection(spaceNameb);
    intranetNotification.checkNotStatusSpace(status, spaceNameb);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

}
