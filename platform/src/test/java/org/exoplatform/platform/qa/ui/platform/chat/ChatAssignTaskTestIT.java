package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TABLE_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("chat")
@Tag("sniff")
public class ChatAssignTaskTestIT extends Base {

  HomePagePlatform homePagePlatform;
  ManageLogInOut manageLogInOut;
  UserAndGroupManagement userandgroupmanagement;
  UserAddManagement userAddManagement;
  RoomManagement roomManagement;
  ProjectsManagement projectsManagement;
  NavigationToolbar navigationToolbar;
  ConnectionsManagement connectionsManagement;
  ChatManagement chatManagement;
  UserAndGroupManagement userAndGroupManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    userAddManagement = new UserAddManagement(this);
    userandgroupmanagement = new UserAndGroupManagement(this);
    roomManagement = new RoomManagement(this);
    projectsManagement = new ProjectsManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    connectionsManagement = new ConnectionsManagement(this);
    chatManagement = new ChatManagement(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }


  @Test
  public void test01_CheckTaskWithAssigneeFirstName() {

    String room = "room" + getRandomNumber();
    String task = "task" + getRandomNumber();
    String room1 = "room" + getRandomNumber();
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String email = "email" + getRandomNumber() + "@test.com";
    String FirstName = "FirstName" + getRandomString();
    String LastName = "LastName" + getRandomString();

    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room1);
    ELEMENT_CONTACT_LIST.find(byText(room1)).click();
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_CREATE_TASK.click();
    info("check the popup assign task is displayed with task title, Assignee, Due date");
    chatManagement.checkPopUpAssignTask();
    roomManagement.deleteRomm(room1);

    info("Check Task With No Assignee");
    switchTo().window(0);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    chatManagement.CreateTask(task);
    switchToParentWindow();
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).should(Condition.exist);
    projectsManagement.deleteProject(room);
    switchTo().window(1);
    roomManagement.deleteRomm(room);

    info("Check Task With Assignee First Name");
    switchTo().window(0);
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(usernamea, password, email, FirstName, LastName);
    homePagePlatform.goToChat();
    switchTo().window(1);
    sleep(1000);
    roomManagement.addRoom(room, usernamea);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    chatManagement.CreateTask(task, FirstName);
    switchToParentWindow();
    manageLogInOut.signIn(usernamea, password);
    sleep(2000);
    $(byClassName("uiIconStatus")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_NOTIFICATION.find(byText(task)).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).should(Condition.exist).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(FirstName));
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);

  }

  @BugInPLF("CHAT-982")
  @Test
  public void test03_CheckTaskWithAssigneeUsername() {

    String room = "room" + getRandomNumber();
    String task = "task" + getRandomNumber();
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String email = "email" + getRandomNumber() + "@test.com";
    String FirstName = "FirstName" + getRandomString();
    String LastName = "LastName" + getRandomString();
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(usernamea, password, email, FirstName, LastName);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room, usernamea);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible, Configuration.timeout);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    ELEMENT_COLLABORATION_ACTIONS.click();
    chatManagement.CreateTask(task, usernamea);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(usernamea, password);
    $(byClassName("uiIconStatus")).click();
    ELEMENT_CHAT_NOTIFICATION.find(byText(task)).should(Condition.appears);
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).should(Condition.exist).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(FirstName));
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);

  }

}