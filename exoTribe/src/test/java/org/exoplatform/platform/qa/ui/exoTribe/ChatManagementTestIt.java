package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
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

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("tribe")
@Tag("social")
@Tag("sniff")
public class ChatManagementTestIt extends BaseTribe {
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
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_OpenClose_ChatDrawer() {

    info("Open Chat Drawer");
    navigationToolbar.openChatDrawerDW();

    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test02_GoToAChatContact() {

    String contact = "QA team";

    info("Open Chat Drawer");
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(contact);

    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test03_GoToChatPage() {

    info("Open Chat Drawer");
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Go To The Chat Page");
    navigationToolbar.goToChatPageDW();

    switchTo().window(1);
    info("Check that the Chat Page is displayed");
    ELEMENT_CHAT_PAGE_DISPLAYED_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    switchTo().window(0);

    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

}
