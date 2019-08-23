package org.exoplatform.platform.qa.ui.platform.task;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_TASK_DETAIL;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by esmaoui on 02/03/2018.
 */

@Tag("sniff")
@Tag("task")
public class TaskInCalendarTestIT extends Base {

    HomePagePlatform homePagePlatform;
    SpaceManagement spaceManagement;
    SpaceHomePage spaceHomePage;
    TasksManagement tasksManagement;
    ProjectsManagement projectsManagement;
    CalendarManagement calendarManagement;
    CalendarHomePage calendarHomePage;
    ManageLogInOut manageLogInOut;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceManagement = new SpaceManagement(this);
        tasksManagement = new TasksManagement(this);
        projectsManagement = new ProjectsManagement(this);
        calendarManagement = new CalendarManagement(this);
        calendarHomePage = new CalendarHomePage(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas(PLFData.USER_ROOT, PLFData.password);
    }
    @Test
    @Tag("TA-605")
    public void test01_ShowTaskDetailInCalendar() throws InterruptedException {

        String space = "space" + getRandomNumber();
        String taskName = "task" + getRandomNumber();
        String ContentTask = "contentTask" + getRandomNumber();

        info("Create a space");
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space, space, 6000);
        Thread.sleep(1000);
        spaceManagement.goToTaskTab();
        ELEMENT_LEFT_PANEL_IN_TASK_PAGE.find(byText(space)).click();
        tasksManagement.addTask(taskName);
        ELEMENT_WORK_PLANED_FIELD.click();
        if (ELEMENT_CHECKBOX_ALL_DAY.is(Condition.selected)) {
            ElementsCollection elements = $$(byClassName("today"));
            elements.get(0).click();
            elements.get(1).click();
        }
        ELEMENT_WORK_PLANED_FIELD.click();
        ELEMENT_SHOW_IN_CALENDAR.parent().find(byClassName("uiIconPLFCalendar")).click();
        projectsManagement.editProject(space, space, space, "", true);
        spaceManagement.goToAgendaTab();
        calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
        ELEMENT_TASK_DETAIL.parent().parent().parent().find(byText(taskName))
                .shouldBe(Condition.exist);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space,false);
        }
    }