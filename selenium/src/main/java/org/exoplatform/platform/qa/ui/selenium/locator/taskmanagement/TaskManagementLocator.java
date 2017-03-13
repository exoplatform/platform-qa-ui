package org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement;

import org.openqa.selenium.By;

/**
 * This class will be define locators for Task Management feature The feature
 * has following parts: 1. Left panel 2. Central panel 3. Right panel
 */

public class TaskManagementLocator {

  /***********************************************************
   * LEFT PANEL
   ******************************************************/
  // General

  // Task area

  // Project area-->General
  public static final String ELEMENT_LEFT_PANEL_PROJECT_NAME              =
                                                             ".//*[contains(@class,'project-name')][contains(.,'$project')]";

  // Project area-->Context Menu

  // Labels area

  /**********************************************************
   * CENTRAL PANEL
   ******************************************************/

  // *************************************Task
  // Management*****************************************//

  // Task area-->General

  // Add Task form

  // Task area-->Incoming

  // Task area-->All tasks

  // Task area--->Overdue

  // Task area--->Today

  // Task area-->Tomorrow

  // Task area-->Upcoming

  // *************************************Project
  // Management*****************************************//

  // Project-->General

  // Add project form
  public static final By     ELEMENT_ADD_PROJECT_PARENT_PROJECT           =
                                                                By.xpath("(.//*[contains(@class,'addProject')]//*[contains(@class,'hoverStatusBlock')])[1]");

  public static final String ELEMENT_ADD_PROJECT_PARENT_PROJECT_DROP_MENU =
                                                                          ".//*[contains(@class,'uiDropdownMenu')]//*[contains(text(),'$project')]";

  public static final By     ELEMENT_ADD_PROJECT_TITLE                    =
                                                       By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'name')]");

  public static final By     ELEMENT_ADD_PROJECT_DES                      =
                                                     By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'description')]");

  public static final By     ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX =
                                                                          By.xpath(".//*[contains(@class,'addProject')]//*[contains(@class,'checkbox')]");

  // Edit project form

  // Share project form

  // Color
  public static final String ELEMENT_COLOR_TABLE_ITEM                     =
                                                      ".//*[contains(@class,'project-name')][contains(.,'$project')]/..//*[contains(@class,'$color')]";

  // Clone project

  // Show and Hide project

  // Delete project

  // *************************************Labels
  // Management*****************************************//

  // Labels-->General

  // Add labels form

  // Edit labels form

  // Delete label

  // Show and Hide labels

  // Change color

  // *************************************List
  // View*****************************************//

  // List View-->General

  // List View-->Group by Status

  // List View-->Group by Project

  // List View-->Group by Assignee

  // List View --> Group by Label

  // List View - Group by Due Date

  // List View - Sort By Rank

  // List View - Sort by Priority

  // List View - Sort by Created Date

  /***********************************************************
   * RIGHT PANEL
   ******************************************************/

}
