package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("tribe")
@Tag("social")
@Tag("sniff")
public class SideAndTopBarItemsManagementTestIt extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

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
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_CheckTopBarItemsOrder() {

    info("Check that Top Bar Items are displayed");

    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_BRANDING_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SEARCH_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_MINICHAT_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_APPLICATIONS_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_NOTIFICATIONS_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check Top Bar Items Order");
    Assert.assertTrue(ELEMENT_TRIBE_SIDEBAR_MENU_CHECK_ORDER.getAttribute("id").contains("app"));
    Assert.assertTrue(ELEMENT_TRIBE_BRANDING_CHECK_ORDER.getAttribute("id").contains("brandingTopBar"));
    Assert.assertTrue(ELEMENT_TRIBE_MINICHAT_CHECK_ORDER.getAttribute("id").contains("miniChatDrawer"));
    Assert.assertTrue(ELEMENT_TRIBE_APPLICATIONS_CHECK_ORDER.getAttribute("id").contains("appLauncher"));
    Assert.assertTrue(ELEMENT_TRIBE_NOTIFICATIONS_CHECK_ORDER.getAttribute("id").contains("NotificationPopoverPortlet"));
    Assert.assertTrue(ELEMENT_TRIBE_SEARCH_CHECK_ORDER.getAttribute("id").contains("SearchApplication"));

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test02_CheckSideBarMenuItemsOrder() {

    info("Check Side Bar Items Order");
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToSideBarMenuTribe();
    sleep(2000);

    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_CHECK_ORDER.getText(), "Aperçu");
    Assert.assertEquals(ELEMENT_TRIBE_STREAM_CHECK_ORDER.getText(), "Flux");
    Assert.assertEquals(ELEMENT_TRIBE_SPACES_CHECK_ORDER.getText(), "Espaces");
    Assert.assertEquals(ELEMENT_TRIBE_PEOPLE_CHECK_ORDER.getText(), "Personnes");
    Assert.assertEquals(ELEMENT_TRIBE_LAST_VISITED_SPACES_CHECK_ORDER.getText(), "Espaces récents");
    Assert.assertEquals(ELEMENT_TRIBE_SETTINGS_CHECK_ORDER.getText(), "Paramètres");
    Assert.assertEquals(ELEMENT_TRIBE_LOGOUT_CHECK_ORDER.getText(), "Déconnexion");
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(3000);

    info("Check the display of all pages");
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToMySpacesTribeViaUrl();
    homePagePlatform.goToPeoplePageTribeViaUrl();
    homePagePlatform.goToSettingsPageTribeViaUrl();

  }

  @Test
  public void test03_CheckThatSideBarMenuItemsAreClickable() {

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToSideBarMenuTribe();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToPeoplePageTribe();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToStreamPageTribe();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToSnapshotPageTribe();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToMySpacesTribe();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToSettingsPageTribe();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test04_CheckThatSideBarMenuProfileSectionIsDisplayed() {

    info("Click on Hamburger Navigation Menu Button");
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that SideBar Menu Profile Section is displayed");
    ELEMENT_TRIBE_SIDEBAR_MENU_PROFILE_SECTION.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Profile Avatar is displayed in SideBar Menu");
    ELEMENT_TRIBE_SIDEBAR_MENU_PROFILE_AVATAR.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Profile First name and Last name are displayed in SideBar Menu");
    Assert.assertEquals(ELEMENT_TRIBE_SIDEBAR_MENU_PROFILE_FIRST_LAST_NAME.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).getText(),tribe_user);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test05_CheckThatEditAdministrationButtonIsNotExistingForASimpleUser() {

    info("Check that Top Bar Items are displayed");

    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_BRANDING_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SEARCH_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_MINICHAT_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_APPLICATIONS_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_NOTIFICATIONS_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check That Edit Administration Button Is Not Existing For A Simple User");

    ELEMENT_TRIBE_EDIT_ADMINISTRATION_TOPBAR.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

}
