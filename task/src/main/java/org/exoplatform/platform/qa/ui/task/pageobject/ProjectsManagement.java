package org.exoplatform.platform.qa.ui.task.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * This class will define actions about management tasks
 */

public class ProjectsManagement {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ProjectsManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Open Context Menu of Projects by clicking on "+" icon
   */
  public void goToContMenuProject() {
    info("Click on + icon to open Context Menu of Projects");
  }

  /**
   * Select an option in Context Menu of Projects
   *
   * @param op is an option in Context Menu as:Add Project, Show hidden project.
   */
  public void selectOpContMenuProject(optionContMenuProject op) {
    goToContMenuProject();
    switch (op) {
      case Add_Project:
        info("Select Add Project option");
        break;
      case Show_Hidden_Project:
        info("Select Show hide Project");
        break;
      default:
        info("No option in the list. Please select correct option.");
        break;
    }
  }

  /**
   * Open Context menu of a given project
   *
   * @param project is a project's name in the list
   */
  public void goToContMenuGivenProject(String project) {
    info("Right click on a project in the list");
  }

  /**
   * Select an option in Context Menu of a Given Project in Project list
   *
   * @param project is project's name of a given project in the list
   * @param op is an option in Context Menu as Edit,Share,Clone,...
   */
  public void selectOpContMenuGivenProject(String project, optionContMenuGivenProject op) {
    goToContMenuGivenProject(project);
    switch (op) {
      case Edit:
        info("Select Edit option");
        break;
      case Share:
        info("Select Share option");
        break;
      case Clone:
        info("Select Clone option");
        break;
      case Hide:
        info("Select Hide option");
        break;
      case Delete:
        info("Select Delete option");
        break;
      case Add_Project:
        info("Select Add Project option");
        break;
      default:
        info("No option in the list. Please select correct option");
        break;
    }
  }

  /**
   * Change color of a given project
   *
   * @param project is project's name
   * @param color is a color in color list as pink, red,sky_blue,... these colors
   *          will be read from ColorDatabase.java file
   */
  public void selectColor(String project, String color) {
    goToContMenuGivenProject(project);
    info("Select " + color + " in color list");
    evt.click(ELEMENT_COLOR_TABLE_ITEM.replace("$project", project).replace("$color", color));

  }

  /**
   * Add a new project
   *
   * @param title is project's name
   * @param des is project's description
   * @param enableCalendar = true if want to create a task calendar for the
   *          project in Calendar application = false if don't want.
   */
  public void addProject(String title, String des, boolean enableCalendar) {
    ELEMENT_PROJECT_ICON_ADD_PROJECT.click();
    ELEMENT_ADD_PROJECT.click();
    info("Create a new project");
    if (title != null || title != "") {
      info("Input title");
      $(ELEMENT_ADD_PROJECT_TITLE).setValue(title);
    }
    if (des != null || des != "") {
      info("Input description");
      // $(ELEMENT_ADD_PROJECT_DES).setValue(des);
    }
    if (enableCalendar) {
      info("Enable Calendar intergration");
      evt.check(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    } else {
      info("Disable Calendar intergration");
      evt.uncheck(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    }
    ELEMENT_SAVE_PROJECT.click();
    $(byText(title)).should(Condition.visible);
  }

  /**
   * Save all changes for adding project
   */
  public void saveAddProject() {
    info("Click on Save button");

  }

  /**
   * Cancel all changes for adding project
   */
  public void cancelAddProject() {
    info("Click on Cancel button");

  }

  /**
   * Open a project
   *
   * @param projectPath is project's names as Project1/sub_Project1
   */
  public void selectProject(String projectPath) {
    info("Open a project");
    String[] projects = projectPath.split("/");
    for (String project : projects) {
      info("Click on " + project + " on left panel");
      evt.click(ELEMENT_LEFT_PANEL_PROJECT_NAME.replace("$project", project));
    }

  }

  /**
   * Edit a project
   *
   * @param projectPath is project's names as Project1/sub_Project1
   * @param title is a new project's title to edit
   * @param des is a new description of the project to edit
   * @param opt 0 and opt[0]==true if want to input new title/description with
   *          keeping all old title/description 0 or inf0 if want to input new
   *          title/description after cleared all old title/description sup1 and
   *          opt[1]eg true if want to enable calendar integration sup0 and inf1 if
   *          want to disable or uncheck calendar integration
   */
  public void editProject(String projectPath, String title, String newTitle, String des, boolean... opt) {
    $(byClassName("sub-item")).click();
    //$(byText(projectPath)).click();
    //$(byText(projectPath)).parent().parent().find(ELEMENT_ICON_PROJECT).click();
    $(byClassName("list-projects")).parent().parent().parent().parent().find(byClassName("uiIconRightMenu")).click();
   // $(byText(projectPath)).parent().parent().find(ELEMENT_EDIT_PROJECT_OPTION).click();
   $ (ELEMENT_EDIT_PROJECT_OPTION).click();

    if (title != null && title != "") {
      info("Input title");
      ELEMENT_POPUB_EDIT_PROJECT.find(byText(title)).click();
      // Input a new title with clearing an old title
      ELEMENT_EDIT_PROJECT.setValue(newTitle).pressEnter();
    }
    if (des != null && des != "") {
      info("Input description");
      $(byClassName("prjDescription")).click();
    }

    if (opt.length >= 1 && opt[0] == true) {
      info("Enable Calendar intergration");
      ELEMETN_EDIT_PROJECT_ENABLE_CALENDAR_CHECKBOX.click();
    } else {
      info("Disable Calendar intergration");
      evt.uncheck(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    }
    saveEditProject();
  }

  public void deleteProject(String title) {
    $(byText(title)).click();
    $(byText(title)).parent().parent().find(ELEMENT_ICON_PROJECT).click();
    $(byText(title)).parent().parent().find(ELEMENT_DELETE_PROJECT_OPTION).click();
    ELEMENT_CONFIRM_DELETE.click();
    $(byText(title)).should(Condition.not(Condition.visible));
  }

  /**
   * Save all changes when editing a project
   */
  public void saveEditProject() {
    info("Click on Save button");
    ELEMENT_BUTTON_SAVE.click();
  }

  /**
   * Cancel all changes when editing a project
   */
  public void cancelEditProject() {
    info("Click on Cancel button");
  }

  /**
   * Change Project Parent
   *
   * @param project String
   */
  public void changeParentProject(String project) {
    info("Click on Parent project field");
    evt.click(ELEMENT_ADD_PROJECT_PARENT_PROJECT);
    evt.click(ELEMENT_ADD_PROJECT_PARENT_PROJECT_DROP_MENU.replace("$project", project));

  }

  /**
   * Share a Project
   */
  public void shareProject() {

  }

  /**
   * Define options in Context Menu of Projects category
   */
  public enum optionContMenuProject {
    Add_Project, Show_Hidden_Project;
  }

  /**
   * Define options in Context Menu of a given project in Project list
   */
  public enum optionContMenuGivenProject {
    Edit, Share, Clone, Hide, Delete, Add_Project;
  }
}