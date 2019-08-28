package org.exoplatform.platform.qa.ui.platform.task;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_LEFT_PANEL_IN_TASK_PAGE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.LabelsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;

/**
 * Created by ilyes on 09/11/17.
 */
@Tag("sniff")
@Tag("task")
public class TaskManagementInSpaceTestIT extends Base {
  HomePagePlatform   homePagePlatform;
  SpaceManagement    spaceManagement;
  SpaceHomePage      spaceHomePage;
  TasksManagement    tasksManagement;
  ManageLogInOut     manageLogInOut;
  LabelsManagement   labelsManagement;
  ProjectsManagement projectsManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    tasksManagement = new TasksManagement(this);
    labelsManagement = new LabelsManagement(this);
    projectsManagement = new ProjectsManagement(this);
    manageLogInOut = new ManageLogInOut(this);
  }

  @Test
  public void test01_AddTaskInSpace() {

    String space = "space" + getRandomNumber();
    String taskName = "task" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    ELEMENT_LEFT_PANEL_IN_TASK_PAGE.find(byText(space)).click();
    tasksManagement.addTask(taskName);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test02_EditTaskInSpace() {

    String space = "space" + getRandomNumber();
    String taskName = "task" + getRandomNumber();
    String newTaskName = "newTaskName" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    ELEMENT_LEFT_PANEL_IN_TASK_PAGE.find(byText(space)).click();
    tasksManagement.addTask(taskName);
    tasksManagement.editTask(taskName, newTaskName, "High");
    $(byText(newTaskName)).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test03_DeleteTaskInSpace() {

    String space = "space" + getRandomNumber();
    String taskName = "task" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    ELEMENT_LEFT_PANEL_IN_TASK_PAGE.find(byText(space)).click();
    tasksManagement.addTask(taskName);
    tasksManagement.deleteTask(taskName);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test04_AddLabelInSpace() {

    String space = "space" + getRandomNumber();
    String label = "label" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    labelsManagement.addLabel(label);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test05_EditLabelInSpace() {

    String space = "space" + getRandomNumber();
    String label = "label" + getRandomNumber();
    String newLabel = "newLabel" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    labelsManagement.addLabel(label);
    labelsManagement.editLabel(label, newLabel);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test06_DeleteLabelInSpace() {

    String space = "space" + getRandomNumber();
    String label = "label" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    labelsManagement.addLabel(label);
    labelsManagement.deleteLabel(label);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test07_AddProjectInSpace() {

    String space = "space" + getRandomNumber();
    String project = "project" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    projectsManagement.addProject(project, "", false);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test08_EditProjectInSpace() {

    String space = "space" + getRandomNumber();
    String project = "project" + getRandomNumber();
    String newProject = "newProject" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    projectsManagement.addProject(project, "", false);
    projectsManagement.editProject(project, project, newProject, "", true);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test09_DeleteProjectInSpace() {

    String space = "space" + getRandomNumber();
    String project = "project" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceManagement.goToTaskTab();
    projectsManagement.addProject(project, "", false);
    projectsManagement.deleteProject(project);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }
}
