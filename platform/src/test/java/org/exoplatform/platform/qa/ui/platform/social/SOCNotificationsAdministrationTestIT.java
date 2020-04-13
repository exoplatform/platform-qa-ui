package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ALERT_NOTIFICATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting;

/**
 * @author eXo
 */
@Tag("social")
@Tag("sniff")
public class SOCNotificationsAdministrationTestIT extends Base {
  NavigationToolbar        navigationToolbar;

  ManageLogInOut           manageLogInOut;

  NotificationsAdminSeting notificationsAdminSeting;

  HomePagePlatform         homePagePlatform;

  SpaceSettingManagement   spaceSettingManagement;

  SpaceHomePage            spaceHomePage;

  SpaceManagement          spaceManagement;

  AddUsers                 addUsers;

  ConnectionsManagement    connectionsManagement;

  ActivityStream           activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    notificationsAdminSeting = new NotificationsAdminSeting(this);
    addUsers = new AddUsers(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122975.</li>
   * <li>Test Case Name: Enable an Intranet Notification type.</li>
   * <li>Pre-Condition: - User B is manager of Space 1 - User A is not member of
   * Space 1 - The Space Invitation notification is disabled for the User A (and
   * was set to "See on My Intranet" initially).</li> Step Number: 1 Step Name:
   * Step 1: Access notification Administration Step Description: - Login as admin
   * - From top navigation, click Administration/Portal/Notifications Input Data:
   * Expected Outcome: - Notifications Administration page is displayed Step
   * number: 2 Step Name: Step 3: Enable option again Step Description: - In the
   * Notifications row Space Invitation, click the Edit Icon - Tick the option
   * Intranet Notification Notifications - Save - Go to Notification setting page
   * again Input Data: Expected Outcome: The option is shown in the user settings
   * page. "I receive a Space invitation" with intranet nofication option is shown
   * Step number: 3 Step Name: Step Description: - Login with User B - Invite User
   * A to join Space 1 - Login with User A and check the notification list from
   * the top navigation Input Data: Expected Outcome: - An intranet notification
   * is displayed in the list
   */
  @Test
  public void test05_EnableThenDisableAnIntranetNotificationType() {

    String spaceName = "spaceName" + getRandomNumber();
    String text = "text" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String username = "username" + getRandomString();
    String email = username + "@gmail.com";
    String password = "123456";

    info("Notification Administration");
    navigationToolbar.goToAdminNotifications();
    $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    $(ELEMENT_NOTIFICATION_GRID_TITLE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);;
    $(ELEMENT_TITLE_NOTIFICATION_GRID).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);;
    $(ELEMENT_ENABLE_NOTIFICATION_GRID).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);;
    navigationToolbar.goToAdminNotifications();
    $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);;

    info("Enable an Intranet Notification type");
    notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.Space_Invitation_intranet);
    navigationToolbar.goToMyNotifications();
    $(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_ICON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);;
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    manageLogInOut.signIn(username, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, "");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(DATA_USER1, false, "");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    navigationToolbar.goToNotificationList();
    info("Verify that the notification is listed in the list");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(spaceName))
            .parent()
            .shouldHave(Condition.text("You're invited to join {space} space.".replace("{space}",
                    spaceName)));
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);
    navigationToolbar.goToAdminNotifications();
    $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);;
    notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.AS_Comment_intranet);
    navigationToolbar.goToMyNotifications();
    waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_ICON_INTRANET_NOTIFICATION, 2000, 1);
    info("Disable an Intranet Notification type");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(text, "");
    manageLogInOut.signIn(username, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(text, comment);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToNotificationList();
    info("Verify that the notification is not listed in the list");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(text)).shouldNot(Condition.exist);
    info("Reset changed data");
    navigationToolbar.goToAdminNotifications();
    notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.AS_Comment_intranet);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
  }

}
