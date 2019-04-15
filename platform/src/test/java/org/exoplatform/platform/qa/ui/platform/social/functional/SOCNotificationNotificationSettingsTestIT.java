package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_MY_NOTIFICATION_SETTING_FORM;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

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
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;

@Tag("functional")
@Tag("social")

public class SOCNotificationNotificationSettingsTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  HomePagePlatform       homePagePlatform;

  UserAddManagement      userAddManagement;

  ActivityStream         activityStream;

  IntranetNotification   intranetNotification;

  MyNotificationsSetting myNotificationsSetting;

  NotificationActivity   notificationActivity;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    userAddManagement = new UserAddManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    intranetNotification = new IntranetNotification(this);
    spaceHomePage = new SpaceHomePage(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    notificationActivity = new NotificationActivity(this);

  }

  /**
   * <li>Case ID:117388.</li>
   * <li>Test Case Name: Access notification settings.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Access notification settings Step
   * Description: - Log in as a user. - Move mouse over the full name of user and
   * select [My Notifications] on the menu. Input Data: Expected Outcome: -
   * Notification Settings page is appeared. - The entry is placed in the menu
   * after My Dashboard. -It opens the Notifications Settings page in the user
   * navigation.
   */
  @Test
  public void test01_AccessNotificationSettings() {

    info("Test 1: Access notification settings");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, "123456", email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    $(ELEMENT_MY_NOTIFICATION_SETTING_FORM).waitUntil(Condition.visible, Configuration.timeout);
  }
}
