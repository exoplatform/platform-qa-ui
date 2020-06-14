package org.exoplatform.platform.qa.ui.exoTribe;

import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeChangeSettings;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("sniff")
@Tag("social")
public class SOCSettingsEditTestIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  UserProfilePage userProfilePage;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  TribeSpaceManagement tribeSpaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  UserAndGroupManagement userAndGroupManagement;

  TribeChangeSettings tribeChangeSettings;

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
    userProfilePage = new UserProfilePage(this);
    spaceHomePage = new SpaceHomePage(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    tribeChangeSettings = new TribeChangeSettings(this);
    manageLogInOut.signInTribe(tribe_username, tribe_password);
  }

  @Test
  public void test01_CancelThenAcceptEditLanguage() {

    String language = "English";
    String firstLanguage = "French";

    homePagePlatform.goToSettingsPageTribeViaUrl();

    info("Cancel Edit Language");
    tribeChangeSettings.tribeEditLanguage(language);
    tribeChangeSettings.tribeCancelEditLanguage();

    info("Confirm Edit Language");
    tribeChangeSettings.tribeEditLanguage(language);
    tribeChangeSettings.tribeAcceptEditLanguage();

    info("Reset Data : Set the first Language");
    tribeChangeSettings.tribeEditLanguage(firstLanguage);
    tribeChangeSettings.tribeAcceptEditLanguage();
  }

  @Test
  public void test02_CancelThenConfirmEditTimeZone() {

    String timeZone = "+02:00";
    String firstTimeZone = "+01:00";

    homePagePlatform.goToSettingsPageTribeViaUrl();

    info("Cancel Edit Time Zone");
    tribeChangeSettings.tribeEditTimeZone(timeZone);
    tribeChangeSettings.tribeCancelEditTimeZone();

    info("Confirm Edit Time Zone");
    tribeChangeSettings.tribeEditTimeZone(timeZone);
    tribeChangeSettings.tribeAcceptEditTimeZone();

    info("Reset Data : Set the first Time Zone");
    tribeChangeSettings.tribeEditTimeZone(firstTimeZone);
    tribeChangeSettings.tribeAcceptEditTimeZone();

  }

  @Test
  public void test03_CancelThenConfirmEditPassword() {

    String newPassword = "password" + getRandomNumber();

    homePagePlatform.goToSettingsPageTribeViaUrl();

    info("Cancel Edit Password");
    sleep(2000);
    tribeChangeSettings.tribeEditPassword(tribe_password, newPassword);
    tribeChangeSettings.tribeCancelEditPassword();

    info("Confirm Edit Password");
    tribeChangeSettings.tribeEditPassword(tribe_password, newPassword);
    tribeChangeSettings.tribeAcceptEditPassword();

    info("Reset Data : Set the first Time Zone");
    homePagePlatform.goToSettingsPageTribe();
    tribeChangeSettings.tribeEditPassword(newPassword, tribe_password);
    tribeChangeSettings.tribeAcceptEditPassword();

  }

  @Test
  public void test04_EnableDisableNotificationViaMail() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    info("Disable Notification Via Mail");
    sleep(3000);
    tribeChangeSettings.tribeEnableDisableNotificationViaMail();
    getExoWebDriver().getWebDriver().navigate().refresh();

    info("Notification Via Mail is disabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL_STATUS.getAttribute("aria-checked"), "false");

    info("Enable Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableNotificationViaMail();
    getExoWebDriver().getWebDriver().navigate().refresh();

    info("Notification Via Mail is enabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL_STATUS.getAttribute("aria-checked"), "true");

  }

  @Test
  public void test05_EnableDisableNotificationOnMobile() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    info("Disable Notification On Mobile");
    sleep(2000);
    tribeChangeSettings.tribeEnableDisableNotificationOnMobile();
    getExoWebDriver().getWebDriver().navigate().refresh();

    info("Notification On Mobile is disabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE_STATUS.getAttribute("aria-checked"), "false");

    info("Enable Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableNotificationOnMobile();
    getExoWebDriver().getWebDriver().navigate().refresh();

    info("Notification On Mobile is enabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE_STATUS.getAttribute("aria-checked"), "true");

  }

  @Test
  public void test06_EnableDisableNotificationOnSite() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    info("Disable Notification On Site");
    sleep(2000);
    tribeChangeSettings.tribeEnableDisableNotificationOnSite();
    getExoWebDriver().getWebDriver().navigate().refresh();

    info("Notification On Site is disabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_SITE_STATUS.getAttribute("aria-checked"), "false");

    info("Enable Notification On Site");
    tribeChangeSettings.tribeEnableDisableNotificationOnSite();
    getExoWebDriver().getWebDriver().navigate().refresh();

    info("Notification On Site is enabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_SITE_STATUS.getAttribute("aria-checked"), "true");

  }

  @Test
  public void test07_EnableDisableGeneralNotificationsOnSite() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();

    info("Enable General Notification On Site");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    info("Cancel Enable General Notification On Site");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    info("Enable General Notification On Site");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    info("Apply Enable General Notification On Site");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();
    info("General Notification On Site is enabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications sur le site"));

    info("Disable Notification On Site");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("Notification On Site is disabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications sur le site"));

  }

  @Test
  public void test08_EnableDisableGeneralNotificationsOnMobile() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();

    info("Enable General Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();
    info("Cancel Enable General Notification On Mobile");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    info("Enable General Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();
    info("Apply Enable General Notification On Mobile");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();
    info("General Notification On Mobile is enabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Me notifier sur mobile"));

    info("Disable Notification On Mobile");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("Notification On Site is disabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Me notifier sur mobile"));

  }

  @Test
  public void test09_EnableDisableGeneralNotificationsViaMail() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();

    info("Enable General Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    info("Cancel Enable General Notification Via Mail");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    info("Enable General Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    info("Apply Enable General Notification Via Mail");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();
    info("General Notification Via Mail is enabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications par email"));

    info("Disable Notification Via Mail");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("Notification Via Mail is disabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications par email"));

  }

  @Test
  public void test10_SelectSendMeASummaryEmailAtGeneralNotifications() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Jamais"));

    info("Select Weekly at Send Me A Summary Email");
    tribeChangeSettings.selectSendMeASummaryEmail(TribeChangeSettings.mailSendingType.WEEKLY);
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Hebdomadaire"));
    info("Cancel Select Weekly at Send Me A Summary Email");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Jamais"));
    info("Select Weekly at Send Me A Summary Email");
    tribeChangeSettings.selectSendMeASummaryEmail(TribeChangeSettings.mailSendingType.WEEKLY);
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Hebdomadaire"));
    info("Apply Select Weekly at Send Me A Summary Email");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Hebdomadaire"));
    info("Select Daily at Send Me A Summary Email");
    tribeChangeSettings.selectSendMeASummaryEmail(TribeChangeSettings.mailSendingType.DAILY);
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Quotidien"));
    info("Apply Select Daily at Send Me A Summary Email");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Quotidien"));
    info("Select Never at Send Me A Summary Email");
    tribeChangeSettings.selectSendMeASummaryEmail(TribeChangeSettings.mailSendingType.NEVER);
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Jamais"));
    info("Apply Select Never at Send Me A Summary Email");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    Assert.assertTrue(ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE.getText().contains("Jamais"));
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

  }

  @Test
  public void test11_EnableDisableAllGeneralNotifications() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();

    info("Enable General Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    info("Enable General Notification On Site");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    info("Enable General Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();

    info("Cancel Enable All General Notifications");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotificationsTribe();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    info("Enable General Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    info("Enable General Notification On Site");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    info("Enable General Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();

    info("Apply Enable All General Notifications");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();
    info("All General Notifications are enabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications par email"));
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Me notifier sur mobile"));
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications sur le site"));

    info("Disable All General Notifications");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();

    info("Disable All General Notifications");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("All General Notifications are disabled");
    tribeChangeSettings.goToManageNotificationsTribe();
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications par email"));
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Me notifier sur mobile"));
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notifications sur le site"));

  }

}
