package org.exoplatform.platform.qa.ui.platform.task;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_INPUT_SEARCH_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CONTAINER_LIST_MESSAGES;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
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

  TasksManagement  tasksManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    chatManagement = new ChatManagement(this);
    tasksManagement = new TasksManagement(this);
  }

  @Test
  @Tag("TA-609")
  public void test01_checkAssignTaskInChatContainLinkToTaskApp() {
    String taskName = "task" + getRandomNumber();
    manageLogInOut.signIn(PLFData.DATA_USER2, PLFData.password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToChat();
    switchTo().window(1);
    $(byXpath("//input[@placeholder='Filter discussions']")).setValue(PLFData.DATA_USER2);
    $(byText(PLFData.DATA_NAME_USER2)).click();
    chatManagement.assignTaskInChat(taskName, PLFData.DATA_USER2);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(taskName)).click();
    switchTo().window(2);
    assertEquals("Tasks", title());
    $(byText(taskName)).shouldBe(Condition.visible);
    tasksManagement.deleteTask(taskName);

  }
}
