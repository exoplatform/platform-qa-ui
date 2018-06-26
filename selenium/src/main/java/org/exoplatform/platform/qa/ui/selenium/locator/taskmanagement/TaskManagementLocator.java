package org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

/**
 * This class will be define locators for Task Management feature The feature
 * has following parts: 1. Left panel 2. Central panel 3. Right panel
 */

public class TaskManagementLocator {

  // LEFT PANEL
  // General

  // Task area
  public static final SelenideElement ELEMENT_BUTTON_ADD_TASK                       =
                                                              $(byXpath("//*[@id=\"taskManagement\"]/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/a"));

  public static final SelenideElement ELEMENT_INPUT_TASK_TITLE                      = $(byName("taskTitle"));

  public static final SelenideElement ELEMENT_TASK_FORM                             = $(byClassName("rightPanelContent "));

  public static final SelenideElement ELEMENT_TASK_FORM_INPUT_TITLE                 =
                                                                    $(byXpath("//*[@id=\"taskManagement\"]/div[3]/div[2]/div[1]/div/div[3]/div/span[2]/div/form/div/div[1]/div/input"));

  public static final SelenideElement ELEMENT_TASK_FORM_PRIORITY                    = $(byClassName("priority"));

  public static final SelenideElement ELEMENT_TASKS_LIST                            = $(byClassName("centerPanel"));

  public static final SelenideElement ELEMENT_TASK_SELECT_PRIORITY                  = $(byClassName("selectboxMini"));

  public static final SelenideElement ELEMENT_TASK_FORM_ICOND_DROP_DOWN_MENU        =
                                                                             $(byXpath("//*[@id=\"taskManagement\"]/div[3]/div[2]/div[1]/div/div[1]/div[1]/a[1]"));

  public static final SelenideElement ELEMENT_TASK_BUTTON_DELETE                    = $(byClassName("action-delete-task"));

  public static final SelenideElement ELEMENT_TASK_BUTTON_DELETE_OK                 =
                                                                    $(byClassName("confirmDeleteTask")).find(byXpath("//*[@id=\"taskManagement\"]/div[4]/div/div[2]/div[2]/button[1]"));

  public static final By              ELEMENT_ICON_MARK_AS_COMPLETED                = byClassName("actionIcon");

  // Project area-->General
  public static final String          ELEMENT_LEFT_PANEL_PROJECT_NAME               =
                                                                      ".//*[contains(@class,'project-name')][contains(.,'$project')]";

  // Project area-->Context Menu
  public static final SelenideElement ELEMENT_PROJECT_ICON_ADD_PROJECT              =
                                                                       $(byAttribute("data-id",
                                                                                     "0")).parent()
                                                                                          .find(byClassName("addProject"));

  public static final SelenideElement ELEMENT_ADD_PROJECT                           =
                                                          ELEMENT_PROJECT_ICON_ADD_PROJECT.parent()
                                                                                          .parent()
                                                                                          .find(byClassName("uiIconAddProject"));

  // Labels area
  public static final SelenideElement ELEMENT_LABEL_ICON_ADD_LABEL                  =
                                                                   $(byAttribute("data-labelid",
                                                                                 "0")).parent().find(byClassName("addLabel"));

  public static final SelenideElement ELEMENT_ADD_LABEL                             = $(byClassName("uiIconAddLabel"));

  public static final SelenideElement ELEMENT_INPUT_LABEL                           = $(byClassName("addLabelInput"));

  public static final SelenideElement ELEMENT_LABEL_BUTTON_CONFIRM_DELETE           = $(byClassName("confirmDelete"));

  // CENTRAL PANEL

  // Task
  // Management

  // Task area-->General

  // Add Task form

  // Task area-->Incoming

  // Task area-->All tasks

  // Task area--->Overdue

  // Task area--->Today

  // Task area-->Tomorrow

  // Task area-->Upcoming

  // Project
  // Management

  // Project-->General

  // Add project form
  public static final By              ELEMENT_ADD_PROJECT_PARENT_PROJECT            =
                                                                         By.xpath("(.//*[contains(@class,'addProject')]//*[contains(@class,'hoverStatusBlock')])[1]");

  public static final String          ELEMENT_ADD_PROJECT_PARENT_PROJECT_DROP_MENU  =
                                                                                   ".//*[contains(@class,'uiDropdownMenu')]//*[contains(text(),'$project')]";

  public static final By              ELEMENT_ADD_PROJECT_TITLE                     =
                                                                By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'name')]");

  public static final By              ELEMENT_ADD_PROJECT_DES                       =
                                                              By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'description')]");

  public static final By              ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX  =
                                                                                   By.xpath(".//*[contains(@class,'addProject')]//*[contains(@class,'checkbox')]");

  public static final SelenideElement ELEMENT_SAVE_PROJECT                          =
                                                           $(byXpath("//*[@id=\"taskManagement\"]/div[4]/div/div[2]/form/div[7]/button[1]"));

  // Edit project form
  public static final SelenideElement ELEMENT_POPUB_EDIT_PROJECT                    = $(byClassName("PopupContent"));

  public static final By              ELEMENT_ICON_PROJECT                          = byClassName("uiIconRightMenu");

  public static final By              ELEMENT_EDIT_PROJECT_OPTION                   = byClassName("uiIconEdit");

  public static final SelenideElement ELEMENT_MENU_PROJECT                          = $(byClassName("uiDropdownWithIcon"));

  public static final SelenideElement ELEMENT_BUTTON_SAVE                           =
                                                          $(byXpath("//*[@id=\"taskManagement\"]/div[4]/div/div[2]/div[7]/button[1]"));

  public static final SelenideElement ELEMETN_EDIT_PROJECT_ENABLE_CALENDAR_CHECKBOX = $(byClassName("uiCheckbox"));

  public static final SelenideElement ELEMENT_EDIT_PROJECT                          =
                                                           $(byXpath("//*[@id=\"taskManagement\"]/div[4]/div/div[2]/div[2]/span/div/form/div/div[1]/div/input"));
  // Share project form

  // Color
  public static final String          ELEMENT_COLOR_TABLE_ITEM                      =
                                                               ".//*[contains(@class,'project-name')][contains(.,'$project')]/..//*[contains(@class,'$color')]";

  // Clone project

  // Show and Hide project

  // Delete project
  public static final By              ELEMENT_DELETE_PROJECT_OPTION                 = byClassName("uiIconTrash");

  public static final SelenideElement ELEMENT_CONFIRM_DELETE                        =
                                                             $(byClassName("confirmDeleteProject")).find(byXpath("//*[@id=\"taskManagement\"]/div[4]/div/div[2]/div[2]/button[1]"));
  // *************************************Labels
  // Management*****************************************//

  // Labels-->General
  public static final SelenideElement ELEMENT_SAVE_EDIT_LABEL                       =
                                                              $(byXpath("//*[@id=\"taskManagement\"]/div[4]/div/div[2]/div/div[2]/button[1]"));

  public static final By              ELEMENT_ICON_OPEN_MENU_LABEL                  = byClassName("uiIconRightMenu");

  public static final By              ELEMENT_OPEN_MENU_EDIT_LABEL                  = byClassName("uiIconEdit");

  public static final By              ELEMENT_OPEN_MENU_DELETE_LABEL                = byClassName("uiIconTrash");

  public static final SelenideElement ELEMENT_INPUT_COMMENT_TASK                    =
                                                                 $(byClassName("rightPanelContent")).find(byClassName("cke_wysiwyg_frame"));

  public static final SelenideElement ELEMENT_EDIT_ASSIGNEE                         = $(byClassName("editAssignee"));

  public static final SelenideElement COMMENT_INPUT_AREA                            = $(byXpath("/html/body"));

  public static final SelenideElement COMMENT_BUTTON                                = $(byId("taskCommentButton"));

  public static final SelenideElement COWORKER_INPUT_FIELD                          =
                                                           $(byXpath("//*[@id=\"taskManagement\"]/div[3]/div[2]/div[1]/div/div[4]/div[2]/div/div/div/div[2]/div[2]/div[1]/input"));

  public static final SelenideElement COWORKER_FILED                                =
                                                     $(byXpath("//*[@id=\"taskManagement\"]/div[3]/div[2]/div[1]/div/div[4]/div[2]/div/div/div/div[2]/div[2]/div[3]"));

public static final SelenideElement ELEMENT_LABEL_REPLY_TASK=$(byClassName("replyCommentLink"));
public static final String ELEMENT_VIEW_ALL_REPLIES_LINK_TASK="SubCommentShowAll_{id}";



  public static final SelenideElement ELEMENT_LEFT_PANEL_IN_TASK_PAGE               = $(byClassName("left-menu"));

  public static final SelenideElement ELEMENT_TITLE_OF_PROJECT=$(byClassName("projectName"));

  public static final SelenideElement ELEMENT_WORK_PLANED_FIELD                     =
                                                                $(byClassName("addTask")).find(byClassName("date-work-plan"))
                                                                                         .find(byClassName("editableField"));

  public static final SelenideElement ELEMENT_CHECKBOX_ALL_DAY                      = $(byName("allday"));

  public static final SelenideElement ELEMENT_INPUT_FROM_TIME                       = $(byName("fromTime"));

  public static final SelenideElement ELEMENT_INPUT_TO_TIME                         = $(byName("toTime"));

  public static final SelenideElement ELEMENT_MINI_CALENDAR_FROM                    = $(byId("UITaskFromCalendarControl"));

  public static final SelenideElement ELEMENT_MINI_CALENDAR_TO                      = $(byId("UITaskToCalendarControl"));

  public static final SelenideElement ELEMENT_ICON_ADD_IMAGE_IN_COMMENT=$(byClassName("cke_button__selectimage_icon"));

  public static final SelenideElement ELEMENT_BUTTON_OK_UPLOAD=$(byClassName("cke_dialog_ui_hbox_first")).find(byClassName("btn"));

  // Add labels form

  // Edit labels form

  // Delete label

  // Show and Hide labels

  // Change color

  // List
  // View

  // List View-->General

  // List View-->Group by Status

  // List View-->Group by Project

  // List View-->Group by Assignee

  // List View --> Group by Label

  // List View - Group by Due Date

  // List View - Sort By Rank

  // List View - Sort by Priority

  // List View - Sort by Created Date

  // RIGHT PANEL

}
