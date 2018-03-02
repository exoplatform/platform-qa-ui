package org.exoplatform.platform.qa.ui.platform.task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by esmaoui on 27/02/2018.
 */
@Tag("sniff")
@Tag("task")
public class FilterTaskTestIT extends Base {
    HomePagePlatform     homePagePlatform;
    ManageLogInOut       manageLogInOut;
    TasksManagement      tasksManagement;
    ProjectsManagement   projectsManagement;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        tasksManagement = new TasksManagement(this);
        projectsManagement = new ProjectsManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas(PLFData.USER_ROOT,PLFData.password);

    }
   /*
    bug TA-571
   */

    @Test
    public void test01_FilterCompletedTask(){

        String project = "project" + getRandomNumber();
        String taskName = "task" + getRandomNumber();
        String taskName1 = "task1" + getRandomNumber();
        String taskName2 = "task2" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        projectsManagement.addProject(project, "", false);
        tasksManagement.addTask(taskName);
        tasksManagement.addTask(taskName1);
        ELEMENT_MARK_AS_COMPLETED.click();
        tasksManagement.addTask(taskName2);
        ELEMENT_MARK_AS_COMPLETED.click();
        ELEMENT_FILTER.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_FILTER.click();
        ELEMENT_SHOW_COMPLETED_TASK.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_SHOW_COMPLETED_TASK.click();
        ELEMENT_CLOSE_FILTER.click();
    }

}
