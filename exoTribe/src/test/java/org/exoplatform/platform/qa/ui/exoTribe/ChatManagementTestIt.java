package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password3;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_ASSIGNEE_TASK;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TABLE_PROJECT;
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

  RoomManagement roomManagement;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

  TribeSpaceManagement tribeSpaceManagement;

  ChatManagement chatManagement;

  SpaceSettingManagement spaceSettingManagement;

  ProjectsManagement projectsManagement;

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
    roomManagement = new RoomManagement(this);
    chatManagement = new ChatManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    projectsManagement = new ProjectsManagement(this);
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

  @Test
  public void test04_CheckPopupAssignTasK() {

    String room = "room" + getRandomNumber();
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

    roomManagement.addRoomTribe(room);
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_CREATE_TASK.click();
    info("check the popup assign task is displayed with task title, Assignee, Due date");
    chatManagement.checkPopUpAssignTaskTribe();
    roomManagement.deleteRomm(room);
    switchTo().window(0);
    navigationToolbar.closeChatDrawerDW();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test05_CheckTaskWithNoAssignee() {

    String room = "room" + getRandomNumber();
    String task = "task" + getRandomNumber();

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room);
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    chatManagement.CreateTask(task);
    switchToParentWindow();
    homePagePlatform.goToTasksPageDW();
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}",room))).waitUntil(Condition.visible, openBrowserTimeoutMs);
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}",room))).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    projectsManagement.deleteProjectTribe(room);
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(0);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test06_CheckTaskWithAssigneeFirstName() {

    String room = "room" + getRandomNumber();
    String task = "task" + getRandomNumber();

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, tribe_user3);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username, tribe_password);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    chatManagement.CreateTask(task, tribe_user3);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    $(byClassName("uiIconStatus")).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    homePagePlatform.goToTasksPageDW();
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}",room))).waitUntil(Condition.visible, openBrowserTimeoutMs);
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}",room))).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).waitUntil(Condition.exist, openBrowserTimeoutMs).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(tribe_user3));
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username, tribe_password);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

}
