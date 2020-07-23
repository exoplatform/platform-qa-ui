package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
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

import java.util.ArrayList;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TABLE_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("dw")
@Tag("social")
@Tag("sniff")
public class ChatManagementDWTestIt extends BaseDW {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

  RoomManagement roomManagement;

  ChatManagement chatManagement;

  TribeSpaceManagement tribeSpaceManagement;

  ProjectsManagement projectsManagement;

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
    roomManagement = new RoomManagement(this);
    chatManagement = new ChatManagement(this);
    projectsManagement = new ProjectsManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_OpenClose_ChatDrawer() {

    String room = "room" + getRandomNumber();
    info("Open Chat Drawer");
    ELEMENT_TRIBE_MINICHAT_TOPBAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs).click();

    switchTo().window(1);
    roomManagement.addRoomTribe(room);
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    switchTo().window(1).close();

    switchTo().window(0);
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

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    info("Open Chat Drawer");

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(spaceNamea);

    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
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
    switchTo().window(1).close();

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
    chatManagement.checkPopUpAssignTaskDW();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
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
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}", room))).waitUntil(Condition.visible, openBrowserTimeoutMs);
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}", room))).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    projectsManagement.deleteProjectTribe(room);
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test06_CheckTaskWithAssigneeFirstName() {

    String room = "room" + getRandomNumber();
    String task = "task" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    chatManagement.CreateTask(task, username1);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    $(byClassName("uiIconStatus")).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    homePagePlatform.goToTasksPageDW();
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}", room))).waitUntil(Condition.visible, openBrowserTimeoutMs);
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}", room))).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).waitUntil(Condition.exist, openBrowserTimeoutMs).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(username1));
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test07_checkMenuCollaborativeActions() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    chatManagement.checkMenuCollaborativeAction();
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test08_checkaddEventPopUp() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_ADD_EVENT.click();
    chatManagement.checkAddEventPopUp();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test09_addEventInChatRoom() {

    String room = "room" + getRandomNumber();
    String event = "event" + getRandomNumber();
    String location = "location" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    chatManagement.addEventInChat(event, location);
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    ELEMENT_CHAT_ICON_STATUS.click();
    ELEMENT_CHAT_NOTIFICATION.find(byText(event)).should(Condition.appears);
    ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatCreateEvent")).should(Condition.appears);
    homePagePlatform.goToCalendarPage();
    $(byText(event)).waitUntil(Condition.appear, Configuration.timeout);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test10_checkAskQuestionPopUp() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    ELEMENT_CHAT_ASK_QUESTION.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    chatManagement.checkAskQuestionPopUp();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test11_checkRaiseHandPopUp() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_RAISE_HAND.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    chatManagement.checkRaiseHandPopUp();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test12_addQuestionInChat() {

    String room = "room" + getRandomNumber();
    String Question = "Question" + getRandomString();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    chatManagement.addQuestionInChat(Question);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(room);
    $(byClassName("uiIconChatQuestion")).should(Condition.appears);
    $(byText(Question + "?")).should(Condition.appears);
    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void Test13_addRoomWithSeveralUsers() {

    String room = "room" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String username2 = "usernameb" + getRandomString();
    String username3 = "usernamec" + getRandomString();

    String email1 = username1 + "@test.com";
    String email2 = username2 + "@test.com";
    String email3 = username3 + "@test.com";

    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    addUsers.addUserTribe(username2, password, email2, username2, username2, "");
    addUsers.addUserTribe(username3, password, email3, username3, username3, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    inviteUsers.add(username2);
    inviteUsers.add(username3);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1, username2, username3);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username3, password);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test14_sendMessageInARoom() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";
    String message = "message" + getRandomNumber();

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    info("send message");
    chatManagement.sendMessageInRoomOrSpace(room, message);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    info("verify message");
    ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test15_modifyTheTittleOfARoom() {

    String room = "room" + getRandomNumber();
    String newroom = "room" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    info("edit title");
    roomManagement.editTitleofAroomTribe(room, newroom);
    ELEMENT_CONTACT_LIST.$(byText(newroom)).should(Condition.exist);
    roomManagement.deleteRomm(newroom);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test16_CheckOnSiteNotificationWhenSendEmoticons() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    chatManagement.sendSmile("smile");
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(room);
    $(byXpath("//*[@class='chat-emoticon emoticon-smile']//*[contains(text(),':)')]")).waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test17_CheckOnSiteNotificationWhenStartMeeting() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    roomManagement.startStopmeetingDW(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(room);

    $(byClassName("uiIconChatMeeting")).should(Condition.visible);
    assertEquals("Meeting started", $(byXpath("//*[@class='message-content']//*[contains(text(),'Meeting started')]")).getText());

    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test18_CheckOnSiteNotificationWhenStopMeeting() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    roomManagement.startStopmeetingDW(room);
    roomManagement.startStopmeetingDW(room);

    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(room);

    $(byClassName("uiIconChatMeeting")).should(Condition.visible);
    assertEquals("The notes have been saved", $(byXpath("//*[@class='message-content msMeetingNotes']//b")).getText());

    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();
    manageLogInOut.signOutTribe();

    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test19_checkUploadFilePopUp() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_UPLOAD_FILE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    chatManagement.checkUploadFilePopUp();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test20_uploadFileInChat() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    chatManagement.uploadFile("eXo-Platform.png");
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(room);
    $(byXpath("//*[@class='message-description']//*[@class='uiIconChatUpload']")).should(Condition.appears);
    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test21_checkShareLinkPopUp() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);

    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_SHARE_LINK.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    chatManagement.checkShareLinPopUp();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test22_addShareLinkInChat() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    chatManagement.shareLinkInChat("https://www.google.fr/");
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Go To A Chat Contact");
    navigationToolbar.goToAChatContactDW(room);
    $(byXpath("//*[@class='message-description']//*[@class='uiIconChatLink']")).should(Condition.appears);
    $(byXpath("//*[@class='message-content']//a[contains(text(),'https://www.google.fr/')]")).should(Condition.appears);
    info("Go Back To The Chat Contact List");
    navigationToolbar.goBackToChatContactListDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test23_addInvalidLinkInChat() {

    String room = "room" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_CHAT_SHARE_LINK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='E.g: http://www.exoplatform.com']")).sendKeys("link");
    ELEMENT_CHAT_SHARE_LINK_BUTTON.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    $(byText("The link has an incorrect format. Please enter a valid URL.")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    roomManagement.deleteRomm(room);
    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test24_searchForASpecifiedRoomThroughTheDiscussionsFilterByAnOtherUser() {

    String room = "room" + getRandomNumber();
    String room2 = "room" + getRandomNumber();
    String room3 = "room" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.addRoomTribe(room, username1);
    roomManagement.addRoomTribe(room2, username1);
    roomManagement.addRoomTribe(room3, username1);
    switchTo().window(1).close();
    switchToParentWindow();

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.openChatDrawerDW();
    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    navigationToolbar.searchARoomViaDiscussionsFilter(room2);
    navigationToolbar.closeSearchARoomViaDiscussionsFilter();

    info("Check that Open Chat Page Button is displayed");
    ELEMENT_OPEN_CHAT_BUTTON_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Check that Discussions Filter Button is displayed");
    ELEMENT_CHAT_DISCUSSIONS_FILTER_DW.waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("Close Chat Drawer");
    navigationToolbar.closeChatDrawerDW();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.openChatDrawerDW();
    navigationToolbar.goToChatPageDW();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    roomManagement.deleteRomm(room2);
    roomManagement.deleteRomm(room3);

    switchTo().window(1).close();
    switchToParentWindow();
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

}
