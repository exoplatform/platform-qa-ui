package org.exoplatform.platform.qa.ui.task.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * This class will define all actions on Home page of the feature
 */
public class TaskManagementHome {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public TaskManagementHome(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to Tasks area by clicking on Task header on left panel
   */
  public void goToTasks() {
    info("--Go to Tasks area--");
  }

  /**
   * Go to Project area by clicking on Project Header on left panel
   */
  public void goToProjects() {
    info("--Go to Project area--");
  }

  /**
   * Go to Labels are by clicking on Label Header on left panel
   */
  public void goToLabels() {
    info("--Go to Labels area--");
  }

  /**
   * Go to List View by clicking on List button on Action bar
   */
  public void goToListView() {
    info("--Go to List View--");
  }

  /**
   * Go to Board by clicking on Board button on Actions bar
   */
  public void goToBoard() {
    info("--Go to Board--");
  }

  /**
   * Select an option in Sort list
   * 
   * @param op is an option in the list as: Title,Create Date,...
   */
  public void selectOptionSort(optionSortList op) {
    switch (op) {
    case Title:
      info("Select Title option");
      break;
    case Status:
      info("Select Status option");
      break;
    case Created_Date:
      info("Select Create Date option");
      break;
    case Due_Date:
      info("Select Due Date option");
      break;
    case Priority:
      info("Select Priority option");
      break;
    case Rank:
      info("Select Rank option");
      break;
    default:
      info("No option in the list. Please select correct option");
      break;
    }
  }

  /**
   * Select an option in Group list of Incoming and All Tasks
   * 
   * @param op is an option in the list as: None,Assignee,...
   */
  public void selectOptionGroup(optionGroup op) {
    switch (op) {
    case None:
      info("Select None option");
      break;
    case Assignee:
      info("Select Assignee option");
      break;
    case Label:
      info("Select Label option");
      break;
    case Project:
      info("Select Project option");
      break;
    case Due_Date:
      info("Select Due Date option");
      break;
    default:
      info("No option in the list. Please select correct option.");
      break;
    }
  }

  /**
   * Define options in Sort list
   */
  public enum optionSortList {
    Title, Status, Created_Date, Due_Date, Priority, Rank;
  }

  /**
   * Define options in Group list of Incoming
   */
  public enum optionGroup {
    None, Assignee, Label, Project, Due_Date;
  }

}
