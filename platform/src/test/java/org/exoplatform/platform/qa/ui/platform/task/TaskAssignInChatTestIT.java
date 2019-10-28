package org.exoplatform.platform.qa.ui.platform.task;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_RESULT_SEARCH;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_MINI_CHAT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;

/**
 * Created by exo on 06/03/18.
 */
@Tag("task")
@Tag("sniff")
public class TaskAssignInChatTestIT extends Base {
  HomePagePlatform homePagePlatform;

  ManageLogInOut   manageLogInOut;

  ChatManagement   chatManagement;

  ConnectionsManagement connectionsManagement;

  TasksManagement  tasksManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    chatManagement = new ChatManagement(this);
    tasksManagement = new TasksManagement(this);
  }

  @Test
  @Tag("TA-609")
  public void test01_checkAssignTaskInChatContainLinkToTaskApp() {
    String taskName = "task" + getRandomNumber();
    String MiniChatName;
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(PLFData.DATA_USER2);
    manageLogInOut.signIn(PLFData.DATA_USER2, "gtn");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToChat();
    switchTo().window(0);
    homePagePlatform.goToPeople();
    homePagePlatform.searchUsersPeople(PLFData.DATA_USER2);
    refresh();
    ELEMENT_USER_RESULT_SEARCH.find(byText(PLFData.DATA_NAME_USER2)).click();
    ELEMENT_USER_PROFILE.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_BUTTON_USER_PROFILE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    MiniChatName = $(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
    Assert.assertEquals(PLFData.DATA_NAME_USER2, MiniChatName);
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
    switchTo().window(1);
    $(byXpath("//input[@placeholder='Filter discussions']")).setValue(PLFData.DATA_USER2);
    $(byText(PLFData.DATA_NAME_USER2)).waitUntil(Condition.visible,2000).click();
    chatManagement.assignTaskInChat(taskName, PLFData.DATA_USER2);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(taskName)).click();
    switchTo().window(2);
    Assert.assertEquals(taskName,$(byXpath("(//div[@class='column-item column-title taskName'])[1]")).getText());
    $(byText(taskName)).shouldBe(Condition.visible);
    tasksManagement.deleteTask(taskName);

  }
}
