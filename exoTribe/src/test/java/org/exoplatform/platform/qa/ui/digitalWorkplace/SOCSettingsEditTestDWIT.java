package org.exoplatform.platform.qa.ui.digitalWorkplace;

import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
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
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
@Tag("sniff")
@Tag("social")
public class SOCSettingsEditTestDWIT extends BaseDW {
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
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }


  @Test
  public void test01_CancelThenAcceptEditLanguage() {

    String language = "French";
    String firstLanguage = "English";

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
    tribeChangeSettings.tribeEditPassword(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    tribeChangeSettings.tribeCancelEditPassword();

    info("Confirm Edit Password");
    tribeChangeSettings.tribeEditPassword(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    tribeChangeSettings.tribeAcceptEditPassword();

    info("Reset Data : Set the first Time Zone");
    homePagePlatform.goToSettingsPageTribe();
    tribeChangeSettings.tribeEditPassword(newPassword, PLFData.DATA_PASS2);
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
    tribeChangeSettings.goToManageNotifications();
    tribeChangeSettings.goToTribeEditGeneralNotifications();

    info("Enable General Notification On Site");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    info("Cancel Enable General Notification On Site");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotifications();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    info("Enable General Notification On Site");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    info("Apply Enable General Notification On Site");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();
    info("General Notification On Site is enabled");
    tribeChangeSettings.goToManageNotifications();
    sleep(2000);
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on-site"));

    info("Disable Notification On Site");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("Notification On Site is disabled");
    tribeChangeSettings.goToManageNotifications();
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on-site"));

  }

  @Test
  public void test08_EnableDisableGeneralNotificationsOnMobile() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotifications();
    tribeChangeSettings.goToTribeEditGeneralNotifications();

    info("Enable General Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();
    info("Cancel Enable General Notification On Mobile");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotifications();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    info("Enable General Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();
    info("Apply Enable General Notification On Mobile");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();
    info("General Notification On Mobile is enabled");
    tribeChangeSettings.goToManageNotifications();
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on mobile"));

    info("Disable Notification On Mobile");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("Notification On Site is disabled");
    tribeChangeSettings.goToManageNotifications();
    sleep(2000);
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on mobile"));

  }

  @Test
  public void test09_EnableDisableGeneralNotificationsViaMail() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotifications();
    tribeChangeSettings.goToTribeEditGeneralNotifications();

    info("Enable General Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    info("Cancel Enable General Notification Via Mail");
    tribeChangeSettings.tribeCancelEditGeneralNotifications();

    refresh();
    tribeChangeSettings.goToManageNotifications();
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    info("Enable General Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    info("Apply Enable General Notification Via Mail");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();
    info("General Notification Via Mail is enabled");
    tribeChangeSettings.goToManageNotifications();
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me by email"));

    info("Disable Notification Via Mail");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("Notification Via Mail is disabled");
    tribeChangeSettings.goToManageNotifications();
    sleep(2000);
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me by email"));

  }

  @Test
  public void test11_EnableDisableAllGeneralNotifications() {

    homePagePlatform.goToSettingsPageTribeViaUrl();

    sleep(2000);
    tribeChangeSettings.goToManageNotifications();
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
    tribeChangeSettings.goToManageNotifications();
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
    tribeChangeSettings.goToManageNotifications();
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me by email"));
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on mobile"));
    Assert.assertTrue($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on-site"));

    info("Disable All General Notifications");
    tribeChangeSettings.goToTribeEditGeneralNotifications();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsViaMail();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnSite();
    tribeChangeSettings.tribeEnableDisableGeneralNotificationsOnMobile();

    info("Disable All General Notifications");
    tribeChangeSettings.tribeApplyEditGeneralNotifications();
    refresh();

    info("All General Notifications are disabled");
    tribeChangeSettings.goToManageNotifications();
    sleep(2000);
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me by email"));
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on mobile"));
    Assert.assertFalse($(By.xpath("(//*[@class='v-list-item__content pa-0'])[1]")).getText().contains("Notify me on-site"));

  }

}
