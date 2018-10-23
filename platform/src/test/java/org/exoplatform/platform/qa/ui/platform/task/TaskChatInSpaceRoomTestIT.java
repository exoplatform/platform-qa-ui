package org.exoplatform.platform.qa.ui.platform.task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.LabelsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_NAME_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_LIST_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TABLE_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("task")
@Tag("sniff")
public class TaskChatInSpaceRoomTestIT extends Base {
    HomePagePlatform homePagePlatform;
    SpaceManagement spaceManagement;
    SpaceHomePage spaceHomePage;
    TasksManagement tasksManagement;
    ManageLogInOut manageLogInOut;
    LabelsManagement labelsManagement;
    ProjectsManagement projectsManagement;
    RoomManagement roomManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceManagement = new SpaceManagement(this);
        tasksManagement = new TasksManagement(this);
        labelsManagement = new LabelsManagement(this);
        projectsManagement = new ProjectsManagement(this);
        roomManagement = new RoomManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
    }

    @Test
    public void test01_TaskInSpaceRoomWithNoAssignee() {

        String space = "space" + getRandomNumber();
        String task = "task" + getRandomNumber();

        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 6000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CONTACT_LIST.find(byText(space)).click();
        ELEMENT_COLLABORATION_ACTIONS.click();
        ELEMENT_CHAT_CREATE_TASK.click();
        ELEMENT_CHAT_TASK_NAME.setValue(task);
        ELEMENT_CHAT_POST_TASK_BUTTON.click();
        ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(task)).shouldBe(Condition.visible);
        switchToParentWindow();
        homePagePlatform.goToTaskPage();
        ELEMENT_LIST_PROJECT.find(byText(space)).click();
        ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).should(Condition.exist).click();
        ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(DATA_NAME_USER1));
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }
}
