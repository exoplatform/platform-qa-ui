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
   * <li>Case ID:121951.</li>
   * <li>Test Case Name: Notification Administration.</li> Step Number: 1 Step
   * Name: Step 1: Access notification Administration Step Description: - Login as
   * admin - From top navigation, click Administration/Portal/Email Notification
   * Input Data: Expected Outcome: - Email Notifications Administration page
   * appears as attachment with 1. Notification types with 3 columns: +
   * [Notifications] list all activities which support notification email +
   * [Title] Title of activities which is shown in [Notification setting] table +
   * [Enable] let users enable/disable notification option
   */
  @Test
  public void test01_NotificationAdministration() {
    info("Test 1: Notification Administration");

    navigationToolbar.goToAdminNotifications();
    waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE, 2000, 1);
    waitForAndGetElement(ELEMENT_NOTIFICATION_GRID_TITLE, 2000, 1);
    waitForAndGetElement(ELEMENT_TITLE_NOTIFICATION_GRID, 2000, 1);
    waitForAndGetElement(ELEMENT_ENABLE_NOTIFICATION_GRID, 2000, 1);
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
  public void test05_EnableAnIntranetNotificationType() {
    info("Test 4: Enable an Intranet Notification type");
    String spaceName = "spaceName" + getRandomNumber();

    navigationToolbar.goToAdminNotifications();
    waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE, 2000, 1);

    notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.Space_Invitation_intranet);
    navigationToolbar.goToMyNotifications();
    waitForAndGetElement(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_ICON, 2000, 1);

    String username = "username" + getRandomString();
    String email = username + "@gmail.com";
    String password = "123456";
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
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
  }

  /**
   * <li>Case ID:122976.</li>
   * <li>Test Case Name: Disable an Intranet Notification type.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition: - User A and User B are connected - User A has enabled
   * Intranet Notification for the Comment</li>
   * <li>Done with OSs and browsers :</li> Step Number: 1 Step Name: Step 1:
   * Access notification Administration Step Description: - Login as admin - From
   * top navigation, click Administration/Portal/Notifications Input Data:
   * Expected Outcome: - Notifications Administration page is displayed
   */
  /*
   * Step number: 2 Step Name: Step 2: Disable one option Step Description: -
   * Select the notification[Comment] and click the Edit icon - Untick the
   * checkbox "Intranet Notifications - Save - In the user navigation, go to
   * [Notifications] Input Data: Expected Outcome: The option is not shown in
   * Notification Setting anymore. ("Someone comments on one of my activity" is
   * not listed) Step number: 3 Step Name: Step 3: Test the disable option Step
   * Description: - With User A post an activity - With User B, comment on the
   * activity of User A - With User A, check notifications list from the top
   * navigation Input Data: Expected Outcome: - No intranet notifications is
   * displayed
   */
  @Test
  public void test03_DisableAnIntranetNotificationType() {
    info("Test 03: Disable an Intranet Notification type");

    navigationToolbar.goToAdminNotifications();
    waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE, 2000, 1);

    notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.AS_Comment_intranet);
    navigationToolbar.goToMyNotifications();
    waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_ICON_INTRANET_NOTIFICATION, 2000, 1);

    String text = "text" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    String username = "username" + getRandomString();
    String email = username + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);

    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    manageLogInOut.signIn(username, password);

    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");

    homePagePlatform.goToHomePage();
    activityStream.addActivity(text, "");

    manageLogInOut.signIn(username, password);
    activityStream.commentActivity(text, comment);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToNotificationList();
    info("Verify that the notification isnot listed in the list");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(text)).shouldNot(Condition.exist);
    info("Reset changed data");
    navigationToolbar.goToAdminNotifications();
    notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.AS_Comment_intranet);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);

  }
}
