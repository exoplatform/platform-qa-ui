package org.exoplatform.platform.qa.ui.task.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 08/06/17.
 */
@Tag("smoke")
@Tag("task")
public class ProjectManagementTestIT extends Base {
    HomePagePlatform homePagePlatform;

    ProjectsManagement projectsManagement;

    TasksManagement tasksManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        projectsManagement = new ProjectsManagement(this);
        tasksManagement = new TasksManagement(this);
    }

    @Test
    public void test01_Add_Project() {
        String title = "title" + getRandomNumber();
        ;
        homePagePlatform.goToTaskPage();
        info("add project");
        projectsManagement.addProject(title, "", false);
        info("verify project added");
        $(byText(title)).should(Condition.exist);

    }


    @Test
    public void test04_Add_TaskInProject() {
        String title = "title" + getRandomNumber();
        String task = "task" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        projectsManagement.addProject(title, "", false);
        $(byText(title)).scrollTo().click();
        executeJavaScript("window.scrollBy(0,-1550)");
        tasksManagement.addTask(task);
        $(byText(task)).should(Condition.exist);


    }

}
