package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ALERT_NOTIFICATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("sniff")
@Tag("social")
public class SOCNotificationsUserSettingsTestIT extends Base {

  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  IntranetNotification intranetNotification;

  ManageLogInOut manageLogInOut;

  MyNotificationsSetting myNotificationsSetting;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

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

  @Test
  public void test01_UpdateAnIntranetNotificationOption() throws AWTException {
    info("Test 02: Update an Intranet notification option");
    String mention = "mention" + getRandomNumber();
    String mention2 = "mention2" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username11 = "usernamea" + getRandomString();
    String email11 = username11 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username11, password, email11, username11, username11);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username11, password);
    info("goto My notification page");
    navigationToolbar.goToMyNotifications();
    info("Notifications Settings");
    waitForAndGetElement(ELEMENT_SWITCH_ONOFF_MAIL_ON, 5000, 1, 2);
    waitForAndGetElement(ELEMENT_SWITCH_ONOFF_WEB_ON, 5000, 1, 2);
    waitForAndGetElement(ELEMENT_COLUMN_NOTIFYME);
    waitForAndGetElement(ELEMENT_COLUMN_HOWTO);
    click(ELEMENT_EDIT_NEWUSER_ICON);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_LIST_DAILY);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_LIST_WEEKLY);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_LIST_NEVER);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX);
    waitForAndGetElement(ELEMENT_EDIT_NEWUSER_SAVE_BTN);
    navigationToolbar.goToMyNotifications();
    info("Switch OFF Intranet Notifications");
    info("turn off intranet notifications");
    myNotificationsSetting.turnOnOffNotiIntranet(false);
    waitForAndGetElement(ELEMENT_WEB_VIEWMODE_FALSE, 3000, 1, 2);
    info("Check intranet notification");
    waitForElementNotPresent(ELEMENT_TOOLBAR_NOTIFICATION_LIST);
    waitForElementNotPresent(ELEMENT_NOTIFICATION_LIST_USER.replace("${user}", username11));
    info("Update An Intranet Notification Option");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("login by another user");
    manageLogInOut.signIn(username2, password);
    info("add mention");
    activityStream.mentionUserActivity(username1, mention);
    manageLogInOut.signIn(username1, password);
    info("Check intranet notification");
    sleep(2000);
    $(ELEMENT_ALERT_NOTIFICATION).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(mention)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_NOTIFICATION_REMOVE_ICON).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    info("goto My notification page");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("login by another user");
    manageLogInOut.signIn(username3, password);
    info("add mention");
    activityStream.mentionUserActivity(username1, mention2);
    manageLogInOut.signIn(username1, password);
    info("Check intranet notification");
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(mention2)).shouldNot(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username11);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }
}
