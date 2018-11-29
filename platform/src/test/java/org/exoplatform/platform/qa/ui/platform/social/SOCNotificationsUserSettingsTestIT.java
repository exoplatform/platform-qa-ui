package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ALERT_NOTIFICATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import java.awt.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
@Tag("sniff")
@Tag("social")
public class SOCNotificationsUserSettingsTestIT extends Base {

  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  IntranetNotification   intranetNotification;

  ManageLogInOut         manageLogInOut;

  MyNotificationsSetting myNotificationsSetting;

  HomePagePlatform       homePagePlatform;

  ConnectionsManagement  connectionsManagement;

  ActivityStream         activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    intranetNotification = new IntranetNotification(this);
    manageLogInOut = new ManageLogInOut(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }

    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:121950.</li>
   * <li>Test Case Name: Notification Settings.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Access
   * notification settings Step Description: - Login - Click full name of user and
   * select [Notifications] on the menu Input Data: Expected Outcome: Notification
   * setting page is shown with 2 main parts1. Toggle buttons for Email and
   * Intranet Notifications, by default set to YES 2. A table contains all
   * notification settings for the user.The columns are : - Notify me when:
   * contains title of the notifications and categories - How to get notifications
   * : the notification information and an edit button* Step number: 2 Step Name:
   * Step 2 : check edit Step Description: - Click edit button of a notification
   * type Input Data: Expected Outcome: The edition options are : - a checkbox to
   * indicate that the user wants to receive the email notification instantly
   * named "Send me an email right away" - a combo boxwith one of the following
   * values :+ Never : to not include this notification in any digest + Daily : to
   * include notifications of this type in the daily digestemail + Weekly : to
   * include the notifications of this type in the weekly digest email - a
   * checkbox to indicate if the user wants to receive Intranet Notifications
   * named "See on My Intranet"A button [Save] is displayed to save the selection
   */

  @Test
  public void test01_NotificationSettings() {
    info("Test 1: Notification Settings");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    $(ELEMENT_SWITCH_ONOFF_MAIL_ON).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_SWITCH_ONOFF_WEB_ON).waitUntil(Condition.visible,Configuration.timeout);
    waitForAndGetElement(ELEMENT_COLUMN_NOTIFYME);
    waitForAndGetElement(ELEMENT_COLUMN_HOWTO);
    click(ELEMENT_EDIT_NEWUSER_ICON);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_LIST_DAILY);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_LIST_WEEKLY);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_LIST_NEVER);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_SAVE_BTN);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122973.</li>
   * <li>Test Case Name: Switch OFF Intranet Notifications.</li>
   * <li>Pre-Condition: - User A has activated Intranet Notifications on all
   * type.</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Access
   * notification settings Step Description: - Login with user A - Click full name
   * of user and select [Notifications] on the menu Input Data: Expected Outcome:
   * - Notification Settings page is appeared* Step number: 2 Step Name: Step 2:
   * Switch OFF Intranet Notifications Step Description: - Switch on NO the toggle
   * "Get Intranet Notifications" Input Data: Expected Outcome: - The intranet
   * notifications settings are disabled. - The user settings related to Intranet
   * are disabled in the table myNotifPage.turnOnOffNotiIntranet(true);
   * myNotifPage.enableNotification(myNotiType.NewUser_intranet); Step number: 3
   * Step Name: Step 3 : Check Intranet Notification disabled Step Description: -
   * Generate an intranet notification activated previously by the user Input
   * Data: Expected Outcome: - No intranet notifications are sent or displayed in
   * the Intranet
   */
  @Test
  public void test03_SwitchOFFIntranetNotifications() {
    info("Test 03: Switch OFF Intranet Notifications");
    String username1 = "usernamea" + getRandomString();
    String password1 = "123456";
    String email1 = username1 + "@test.com";
    info("goto My notification page");
    navigationToolbar.goToMyNotifications();
    info("turn off intranet notifications");
    myNotificationsSetting.turnOnOffNotiIntranet(false);
    $(ELEMENT_WEB_VIEWMODE_FALSE).waitUntil(Condition.visible,Configuration.timeout);
    info("add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password1, email1, username1, username1);
    info("Check intranet notification");
    waitForElementNotPresent(ELEMENT_TOOLBAR_NOTIFICATION_LIST);
    waitForElementNotPresent(ELEMENT_NOTIFICATION_LIST_USER.replace("${user}", username1));
    info("restore data");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.confirmResetNotificationSetting();
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122974.</li>
   * <li>Test Case Name: Update an Intranet notification option.</li>
   * <li>Pre-Condition: PLF server has mail configured</li>
   * <li>Post-Condition:</li>
   * 
   * @throws AWTException Step Number: 1 Step Name: Step 1: Email is sent when
   *           option is ticked Step Description: - Login with user A - Click
   *           username on the right of top navigation - Click Notifications - In
   *           column [How to get notifications], select 1 row (eg: Someone
   *           mentions you) - Click the edit button - Tick the check box "See on
   *           My Intranet" - Click Save Input Data: Expected Outcome: The
   *           settings is saved Step number: 2 Step Name: Step 2 : Check settings
   *           Step Description: - Login with User B and mention User A - Login
   *           with User A and check notification list in the top navigation Input
   *           Data: Expected Outcome: An intranet notification is displayed in
   *           the list Step number: 3 Step Name: Step 2: Email is not sent when
   *           option is not ticked Step Description: - Login with User A - Go to
   *           Notification Setting screen again - Un -tick option in step 1 -
   *           With User B, mention again User A new user Input Data: Expected
   *           Outcome: No intranet notification is displayed in the notifications
   *           list.
   */
  @Test
  public void test02_UpdateAnIntranetNotificationOption() throws AWTException {
    info("Test 02: Update an Intranet notification option");
    String mention = "mention" + getRandomNumber();
    String mention2 = "mention2" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("goto My notification page");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("login by another user");
    manageLogInOut.signIn(username2, password);
    info("add mention");
    activityStream.mentionUserActivity(username1, mention);
    manageLogInOut.signIn(username1, password);
    info("Check intranet notification");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(mention)).should(Condition.exist);
    click(ELEMENT_NOTIFICATION_REMOVE_ICON);
    info("goto My notification page");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("login by another user");
    manageLogInOut.signIn(username3, password);
    info("add mention");
    activityStream.mentionUserActivity(username1, mention2);
    manageLogInOut.signIn(username1, password);
    info("Check intranet notification");
    ELEMENT_ALERT_NOTIFICATION.shouldNot(Condition.visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(mention2)).shouldNot(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }
}
