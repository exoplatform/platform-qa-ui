package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("dw")
public class NotificationsManagementDWTestIt extends BaseDW {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

  TribeSpaceManagement tribeSpaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_OpenClose_Notifications() {

    info("Open Notifications");
    navigationToolbar.goToIntranetNotificationDigitalWorkplace();

    info("Check that Notification Settings Button is displayed");
    ELEMENT_NOTIFICATION_TITLE_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    ELEMENT_NOTIFICATION_SETTINGS_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Close Notifications");
    navigationToolbar.closeNotificationsDW();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test02_CheckThatNotificationSettingsButtonIsClickable() {

    info("Open Notifications");
    navigationToolbar.goToIntranetNotificationDigitalWorkplace();

    info("Check that Notification Settings Button is displayed");
    ELEMENT_NOTIFICATION_TITLE_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    ELEMENT_NOTIFICATION_SETTINGS_DW.waitUntil(Condition.visible, openBrowserTimeoutMs).click();

    info("Check That Settings Page is displayed");
    $(byXpath("//*[@id='UserSettingLanguage']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  @Test
  public void test03_MarkANotificationReceivedByOtherUserAsRead() {

    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationDW(spaceNamea);
    navigationToolbar.closeNotificationsDW();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Comment Activity");
    tribeActivityStream.addActivityComment(activityId, comment);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    info("Mark All Notifications as read");
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.markAllNotificationAsReadDW();
    navigationToolbar.checkThatAllNotificationAreMarkedAsReadDW();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWhoCommentsActivityIsDisplayedDW(activity1, username1, comment);
    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test04_MarkAllNotificationsAsRead() {

    info("Open Notifications");
    navigationToolbar.goToIntranetNotificationDigitalWorkplace();

    info("Check that Notification Settings Button is displayed");
    ELEMENT_NOTIFICATION_TITLE_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    ELEMENT_NOTIFICATION_SETTINGS_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Mark All Notifications as read");
    ELEMENT_MARK_AS_READ.waitUntil(Condition.visible, openBrowserTimeoutMs).click();

    info("Close Notifications");
    navigationToolbar.closeNotificationsDW();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test05_SeeAllNotifications() {

    info("Open Notifications");
    navigationToolbar.goToIntranetNotificationDigitalWorkplace();

    info("Check that Notification Settings Button is displayed");
    ELEMENT_NOTIFICATION_TITLE_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    ELEMENT_NOTIFICATION_SETTINGS_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("See All Notifications");
    ELEMENT_SEE_ALL_NOTIFICATIONS.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    info("Check that All Notifications are displayed");
    ELEMENT_NOTIFICATION_ACTIONS_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    ELEMENT_ALL_NOTIFICATIONS_DISPLAYED_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

  }

  @Test
  public void test06_Remove_Notification() {

    info("Open Notifications");
    navigationToolbar.goToIntranetNotificationDigitalWorkplace();

    info("Check that Notification Settings Button is displayed");
    ELEMENT_NOTIFICATION_TITLE_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    ELEMENT_NOTIFICATION_SETTINGS_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Remove First Notification");
    navigationToolbar.removeFirstNotificationDW();

    info("Close Notifications");
    navigationToolbar.closeNotificationsDW();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

}
