package org.exoplatform.platform.qa.ui.selenium.platform.taskmanagement;

import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * This class will define actions about management tasks
 */

public class ManagementProjects {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ManagementProjects(TestBase testBase) {
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
   * @param color is a color in color list as pink, red,sky_blue,... these
   *          colors will be read from ColorDatabase.java file
   */
  public void selectColor(String project, String color) {
    goToContMenuGivenProject(project);
    info("Select " + color + " in color list");
    evt.click(ELEMENT_COLOR_TABLE_ITEM.replace("$project", project).replace("$color", color));
    Utils.pause(2000);
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
    info("Create a new project");
    if (title != null || title != "") {
      info("Input title");
      evt.type(ELEMENT_ADD_PROJECT_TITLE, title, true);
    }
    if (des != null || des != "") {
      info("Input description");
      evt.type(ELEMENT_ADD_PROJECT_DES, des, true);
    }
    if (enableCalendar) {
      info("Enable Calendar intergration");
      evt.check(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    } else {
      info("Disable Calendar intergration");
      evt.uncheck(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    }

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
    Utils.pause(2000);

  }

  /**
   * Edit a project
   * 
   * @param projectPath is project's names as Project1/sub_Project1
   * @param title is a new project's title to edit
   * @param des is a new description of the project to edit
   * @param opt >0 and opt[0]==true if want to input new title/description with
   *          keeping all old title/description =0 or <0 if want to input new
   *          title/description after cleared all old title/description >1 and
   *          opt[1]==true if want to enable calendar integration >0 and <1 if
   *          want to disable or uncheck calendar integration
   */
  public void editProject(String projectPath, String title, String des, boolean... opt) {
    selectProject(projectPath);
    if (title != null || title != "") {
      info("Input title");
      if (opt.length > 0 && opt[0] == true)
        evt.type(ELEMENT_ADD_PROJECT_TITLE, title, false);// Input a new title
                                                          // with keeping old
                                                          // title
      else
        evt.type(ELEMENT_ADD_PROJECT_TITLE, title, true);// Input a new title
                                                         // with clearing all
                                                         // old title
    }
    if (des != null || des != "") {
      info("Input description");
      if (opt.length > 0 && opt[0] == true)
        evt.type(ELEMENT_ADD_PROJECT_DES, des, false);// Input a new description
                                                      // with keeping old
                                                      // description
      else
        evt.type(ELEMENT_ADD_PROJECT_DES, des, true);// Input a new description
                                                     // with clearing all old
                                                     // description
    }

    if (opt.length > 1 && opt[1] == true) {
      info("Enable Calendar intergration");
      evt.check(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    } else {
      info("Disable Calendar intergration");
      evt.uncheck(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    }
  }

  /**
   * Save all changes when editing a project
   */
  public void saveEditProject() {
    info("Click on Save button");
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
   * @param project
   */
  public void changeParentProject(String project) {
    info("Click on Parent project field");
    evt.click(ELEMENT_ADD_PROJECT_PARENT_PROJECT);
    evt.click(ELEMENT_ADD_PROJECT_PARENT_PROJECT_DROP_MENU.replace("$project", project));
    Utils.pause(2000);
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
