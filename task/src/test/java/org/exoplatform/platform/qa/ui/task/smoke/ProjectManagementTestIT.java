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
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TITLE_OF_PROJECT;
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
        info("delete project");
        projectsManagement.deleteProject(title);
        info("verify project deleted");
        $(byText(title)).waitUntil(Condition.disappears, Configuration.timeout);

    }
    @Test
    public void test02Edit_Project() {
        String title = "title" + getRandomNumber();
        String newTitle = "newTitle" + getRandomNumber();
        ;
        homePagePlatform.goToTaskPage();
        info("add project");
        projectsManagement.addProject(title, "", false);
        info("verify project added");
        $(byText(title)).should(Condition.exist);
        info("edit project");
        projectsManagement.editProject(title, title, newTitle, "", true);
        info("verify project edited");
        $(byText(newTitle)).should(Condition.exist);
        info("delete project");
        projectsManagement.deleteProject(newTitle);
        info("verify project deleted");
        $(byText(newTitle)).waitUntil(Condition.disappears, Configuration.timeout);

    }
    @Test
    public void test03_DeleteProject() {
        String title = "title" + getRandomNumber();
        ;
        homePagePlatform.goToTaskPage();
        info("add project");
        projectsManagement.addProject(title, "", false);
        info("verify project added");
        $(byText(title)).should(Condition.exist);
        info("delete project");
        projectsManagement.deleteProject(title);
        info("verify project deleted");
        $(byText(title)).waitUntil(Condition.disappears, Configuration.timeout);

    }

    @Test
    public void test04_Add_TaskInProject() {
        String title = "title" + getRandomNumber();
        String task = "task" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        projectsManagement.addProject(title, "", false);
        $(byText(title)).click();
        ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.matchesText(title),Configuration.timeout);
        tasksManagement.addTask(task);
        $(byText(task)).should(Condition.exist);
        info("delete task");
        tasksManagement.deleteTask(task);
        projectsManagement.deleteProject(title);

    }
    @Test
    public void test05_Edit_TaskInProject() {
        String title = "title" + getRandomNumber();
        String task = "task" + getRandomNumber();
        String newTask = "newTask" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        projectsManagement.addProject(title, "", false);
        $(byText(title)).click();
        ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.matchesText(title),Configuration.timeout);
        tasksManagement.addTask(task);
        info("edit task");
        tasksManagement.editTask(task, newTask, "High");
        $(byText(newTask)).should(Condition.exist);
        info("delete task");
        tasksManagement.deleteTask(newTask);
        projectsManagement.deleteProject(title);

    }

    @Test
    public void test06_DeleteTaskInProject() {
        String title = "title" + getRandomNumber();
        String task = "task" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        projectsManagement.addProject(title, "", false);
        $(byText(title)).click();
        ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.matchesText(title),Configuration.timeout);
        tasksManagement.addTask(task);
        info("delete task");
        tasksManagement.deleteTask(task);
        $(byText(task)).shouldNot(Condition.exist);
        projectsManagement.deleteProject(title);

    }
}
