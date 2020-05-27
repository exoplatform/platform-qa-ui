package org.exoplatform.platform.qa.ui.exoTribe;

import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeChangeSettings;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
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

    homePagePlatform.goToSettingsPageTribe();

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

    homePagePlatform.goToSettingsPageTribe();

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

    homePagePlatform.goToSettingsPageTribe();

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

    homePagePlatform.goToSettingsPageTribe();

    info("Disable Notification Via Mail");
    sleep(2000);
    tribeChangeSettings.tribeEnableDisableNotificationViaMail();
    refresh();

    info("Notification Via Mail is disabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL_STATUS.getAttribute("aria-checked"), "false");

    info("Enable Notification Via Mail");
    tribeChangeSettings.tribeEnableDisableNotificationViaMail();
    refresh();

    info("Notification Via Mail is enabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL_STATUS.getAttribute("aria-checked"), "true");

  }

  @Test
  public void test05_EnableDisableNotificationOnMobile() {

    homePagePlatform.goToSettingsPageTribe();

    info("Disable Notification On Mobile");
    sleep(2000);
    tribeChangeSettings.tribeEnableDisableNotificationOnMobile();
    refresh();

    info("Notification On Mobile is disabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE_STATUS.getAttribute("aria-checked"), "false");

    info("Enable Notification On Mobile");
    tribeChangeSettings.tribeEnableDisableNotificationOnMobile();
    refresh();

    info("Notification On Mobile is enabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE_STATUS.getAttribute("aria-checked"), "true");

  }

  @Test
  public void test06_EnableDisableNotificationOnSite() {

    homePagePlatform.goToSettingsPageTribe();

    info("Disable Notification On Site");
    sleep(2000);
    tribeChangeSettings.tribeEnableDisableNotificationOnSite();
    refresh();

    info("Notification On Site is disabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_SITE_STATUS.getAttribute("aria-checked"), "false");

    info("Enable Notification On Site");
    tribeChangeSettings.tribeEnableDisableNotificationOnSite();
    refresh();

    info("Notification On Site is enabled");
    Assert.assertEquals(ELEMENT_TRIBE_NOTIFICATION_ON_SITE_STATUS.getAttribute("aria-checked"), "true");

  }

}
