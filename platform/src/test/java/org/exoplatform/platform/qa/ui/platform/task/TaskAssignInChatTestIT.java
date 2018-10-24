package org.exoplatform.platform.qa.ui.platform.task;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TABLE_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TASK_DUE_DATE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
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

    ManageLogInOut manageLogInOut;

    ChatManagement chatManagement;

    TasksManagement tasksManagement;
    RoomManagement roomManagement;
    ProjectsManagement projectsManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        chatManagement = new ChatManagement(this);
        tasksManagement = new TasksManagement(this);
        roomManagement = new RoomManagement(this);
        projectsManagement = new ProjectsManagement(this);

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
        ELEMENT_CHAT_INPUT_SEARCH_USER.setValue("@" + PLFData.DATA_USER2);
        $(byText(PLFData.DATA_NAME_USER2)).click();
        chatManagement.assignTaskInChat(taskName, "", PLFData.DATA_USER2);
        ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(taskName)).click();
        switchTo().window(2);
        assertEquals("Tasks", title());
        $(byText(taskName)).shouldBe(Condition.visible);
        tasksManagement.deleteTask(taskName);


    }

    @BugInPLF("CHAT-989")
    public void test08_CheckDueDateInTask() {
        String room = "room" + getRandomNumber();
        String taskName = "taskname" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        chatManagement.sendMessageTaskDueDate(room, taskName, "Monday");
        ELEMENT_CHAT_LIST_MSG.find(byText(taskName)).parent().parent().findAll(byClassName("custom-message-item")).get(1)
                .shouldNotHave(Condition.text("Due date: Not set"));
        switchToParentWindow();
        homePagePlatform.goToTaskPage();
        $(byText(room)).should(Condition.exist);
        $(byText(room)).click();
        ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
        ELEMENT_TASK_DUE_DATE.shouldNotHave(Condition.text("DueDate"));
        projectsManagement.deleteProject(room);
        switchTo().window(1);
        roomManagement.deleteRomm(room);
    }
}
