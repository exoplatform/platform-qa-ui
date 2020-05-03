package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeLabelsManagement;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeProjectsManagement;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("task")
public class TaskManagementTestIT extends BaseTribe {
  HomePagePlatform   homePagePlatform;
  SpaceManagement    spaceManagement;
  SpaceHomePage      spaceHomePage;
  TasksManagement    tasksManagement;
  ManageLogInOut     manageLogInOut;
  TribeLabelsManagement tribeLabelsManagement;
  TribeProjectsManagement tribeProjectsManagement;
  TribeActivityStream tribeActivityStream;
  TribeSpaceManagement tribeSpaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    tasksManagement = new TasksManagement(this);
    tribeLabelsManagement = new TribeLabelsManagement(this);
    tribeProjectsManagement = new TribeProjectsManagement(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_Add_Edit_DeleteTask() {

    String taskName = "task" + getRandomNumber();
    String newTaskName = "newTaskName" + getRandomNumber();

    info("Add Task");
    homePagePlatform.goToTasksPageTribe();
    tasksManagement.addTask(taskName);
    info("verify task added");
    $(byText(taskName)).should(Condition.exist);

    info("Edit Task");
    tasksManagement.editTask(taskName, newTaskName, "Haute");
    info("verify task edited");
    $(byText(newTaskName)).should(Condition.exist);

    info("Delete Task");
    tasksManagement.deleteTask(newTaskName);
    info("verify task deleted");
    $(byText(newTaskName)).should(Condition.not(Condition.visible));

  }

  @Test
  public void test02_Add_Edit_Delete_Label() {

    String label = "label" + getRandomNumber();
    String newLabel = "newLabel" + getRandomNumber();

    info("Add Label");
    homePagePlatform.goToTasksPageTribe();
    tribeLabelsManagement.addLabel(label);
    info("verify label added");
    $(byText(label)).should(Condition.exist);

    info("Edit Label");
    tribeLabelsManagement.editLabel(label, newLabel);
    info("verify label edited");
    $(byText(newLabel)).should(Condition.exist);

    info("Delete Label");
    tribeLabelsManagement.deleteLabel(newLabel);
    info("verify label deleted");
    $(byText(newLabel)).should(Condition.not(Condition.visible));

  }

  @Test
  public void test03_Add_Edit_Delete_Project() {

    String project = "project" + getRandomNumber();
    String newProject = "newProject" + getRandomNumber();

    info("Add Project");
    homePagePlatform.goToTasksPageTribe();
    tribeProjectsManagement.addProject(project, "", false);
    info("verify project added");
    $(byText(project)).should(Condition.exist);

    info("Edit Project");
    tribeProjectsManagement.editProject(project, project, newProject, "", true);
    info("verify project edited");
    $(byText(newProject)).should(Condition.exist);

    info("Delete Project");
    tribeProjectsManagement.deleteProject(newProject);
    info("verify project deleted");
    $(byText(newProject)).should(Condition.not(Condition.visible));
  }

  @Test
  public void test04_Add_TaskInProject() {

    String title = "title" + getRandomNumber();
    String task = "task" + getRandomNumber();

    homePagePlatform.goToTasksPageTribe();

    tribeProjectsManagement.addProject(title, "", false);
    executeJavaScript("window.scrollBy(0,-20000)");
    $(byText(title)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.hasText(title), Configuration.timeout);

    executeJavaScript("window.scrollBy(0,-20000)");
    tasksManagement.addTask(task);
    $(byText(task)).should(Condition.exist);

    info("delete task");
    tasksManagement.deleteTask(task);
    tribeProjectsManagement.deleteProject(title);

  }
  @Test
  public void test05_Edit_TaskInProject() {

    String title = "title" + getRandomNumber();
    String task = "task" + getRandomNumber();
    String newTask = "newTask" + getRandomNumber();

    homePagePlatform.goToTasksPageTribe();

    tribeProjectsManagement.addProject(title, "", false);
    executeJavaScript("window.scrollBy(0,-20000)");
    $(byText(title)).click();
    ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.hasText(title),Configuration.timeout);

    executeJavaScript("window.scrollBy(0,-20000)");
    tasksManagement.addTask(task);

    info("edit task");
    tasksManagement.editTask(task, newTask, "Haute");
    $(byText(task)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);
    $(byText(newTask)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("delete task");
    tasksManagement.deleteTask(newTask);
    $(byText(newTask)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);
    tribeProjectsManagement.deleteProject(title);

  }

  @Test
  public void test06_addTaskInLabel() {

    String task = "task" + getRandomNumber();
    String label = "label" + getRandomNumber();

    info("add task in label");
    homePagePlatform.goToTasksPageTribe();

    tribeLabelsManagement.addLabel(label);
    $(byText(label)).click();
    ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.hasText(label),Configuration.timeout);

    tasksManagement.addTask(task);
    ELEMENT_TASKS_LIST.find(byText(task)).parent().find(byText(label)).should(Condition.exist);

    homePagePlatform.goToTasksPageTribe();

    tasksManagement.deleteTask(task);
    tribeLabelsManagement.deleteLabel(label);

  }

  @Test
  public void test07_MarkTaskAsCompleted() {

    String task = "task" + getRandomNumber();

    homePagePlatform.goToTasksPageTribe();

    info("add task");
    tasksManagement.addTask(task);

    info("mark task as completed");
    ELEMENT_TASKS_LIST.find(byText(task)).parent().find(ELEMENT_ICON_MARK_AS_COMPLETED).click();
    $(byText(task)).shouldNot(Condition.exist);

  }

  @Test
  @Tag("TA-611")
  public void test08_commentTask_WithImage() {

    String taskName = "task" + getRandomNumber();

    homePagePlatform.goToTasksPageTribe();

    tasksManagement.addTask(taskName);

    ELEMENT_INPUT_COMMENT_TASK.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_ICON_ADD_IMAGE_IN_COMMENT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();


    $(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BUTTON_OK_UPLOAD.waitUntil(Condition.not(Condition.attribute("disabled")), Configuration.openBrowserTimeoutMs).click();
    COMMENT_BUTTON.waitUntil(Condition.enabled, Configuration.openBrowserTimeoutMs).pressEnter();

    tasksManagement.deleteTask(taskName);
  }

}
