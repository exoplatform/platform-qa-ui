package org.exoplatform.platform.qa.ui.calendar.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_USER_CLOSE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class TaskManagement {
  private final TestBase       testBase;

  public PlatformPermission    pPer;

  private ElementEventTestBase evt;

  // CalendarHomePage cHome;
  public TaskManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.pPer = new PlatformPermission(testBase);
    // cHome = new CalendarHomePage(driver);

  }

  /**
   * Open reminders tab
   */
  public void goToRemindersTab() {
    info("Click on Reminders tab");
    evt.click(ELEMENT_EVENT_REMINDER_TAB);

  }

  /**
   * @param date date to create task format: MM/dd/yyyy (Ex: 12/09/2014)
   * @param time time to create task format: hh:mm (Ex: 12:30)
   */
  public void goToAddTaskByRightClickFromMainPanel(String date, String time) {
    info("Go to add task by right clicking from main panel");
    SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat format2 = new SimpleDateFormat("MMM dd yyyy");
    String tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
    Date tempDate1 = null;
    String tempTime = testBase.getCurrentDate("HH") + ":00";

    info("Get date");
    if (date != null && date != "") {
      try {
        tempDate1 = format1.parse(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      tempDate2 = format2.format(tempDate1);
      info("Selected date is " + tempDate2);
    } else {
      tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
      info("Selected date is current date" + tempDate2);
    }

    info("Get time");
    if (time != null && time != "") {
      tempTime = time;
      info("Selected date is " + tempTime);
    } else {
      tempTime = testBase.getCurrentDate("HH") + ":00";
      info("Selected date is current date" + tempTime);
    }

    String cell = ELEMENT_CELL_TO_WORKING_PANEL.replace("$date", tempDate2).replace("$time", tempTime);
    evt.rightClickOnElement(cell);
    evt.click(ELEMENT_CONTEXT_MENU_ADD_TASK);
    evt.waitForAndGetElement(ELEMENT_QUICK_ADD_TASK_POPUP);
  }

  /**
   * Open "Add new task" form from action bar
   */
  public void goToAddTaskFromActionBar() {
    info("Go to Add Task page from action bar");

    // waitForAndGetElement(ELEMENT_BUTTON_TASK, DEFAULT_TIMEOUT, 1);
    evt.click(ELEMENT_BUTTON_TASK);
    // clickByJavascript(ELEMENT_BUTTON_TASK, 2);
    evt.waitForAndGetElement(ELEMENT_QUICK_ADD_TASK_POPUP);
  }

  /**
   * Input into basic fields of Quick task form
   * 
   * @param name name of a task
   * @param note note of a task
   * @param opt optional parameter opt[0]: calendar opt[1]: category
   */
  public void inputBasicQuickTask(String name, String note, String... opt) {
    info("Input into basic fields of Quick task form");
    if (name != null) {
      evt.type(ELEMENT_QUICK_INPUT_TASK_NAME, name, true);
    }
    if (note != null) {
      evt.type(ELEMENT_QUICK_INPUT_TASK_NOTE, note, true);
    }
    if (opt.length > 0 && opt[0] != null) {
      evt.select(ELEMENT_QUICK_INPUT_TASK_CALENDAR, opt[0]);
    }
    if (opt.length > 1 && opt[1] != null) {
      evt.select(ELEMENT_QUICK_INPUT_TASK_CATEGORY, opt[1]);
    }
  }

  /**
   * Input into basic fields of task detail form
   * 
   * @param name name of a task
   * @param note note of a task
   * @param opt optional parameter opt[0]: calendar opt[1]: category
   */
  public void inputBasicDetailTask(String name, String note, String... opt) {
    info("Input into basic fields of detail task form");
    if (name != null) {
      evt.type(ELEMENT_ADD_EDIT_TASK_NAME, name, true);
    }
    if (note != null) {
      evt.type(ELEMENT_ADD_EDIT_TASK_NOTE, note, true);
    }
    if (opt.length > 0 && opt[0] != null) {
      evt.select(ELEMENT_ADD_EDIT_TASK_CALENDAR, opt[0]);
    }
    if (opt.length > 1 && opt[1] != null) {
      evt.select(ELEMENT_ADD_EDIT_TASK_CATEGORY, opt[1]);
    }
    if (opt.length > 2 && opt[2] != null) {
      evt.type(ELEMENT_ADD_EDIT_TASK_LOCATION, opt[2], true);
    }
  }

  /**
   * Input into "From, To" and check/uncheck allday checkbox fields of a task in
   * quick form
   * 
   * @param from From date, time of a task i.e.: 11/06/2013 14:00
   * @param to To date, time of a task, i.e.: 11/06/2013 14:00
   * @param allDay Option "all day" of a task
   */
  public void inputFromToQuickTask(String from, String to, boolean allDay) {
    info("Input into From, To and check/uncheck allday checkbox fields of a task");
    if (allDay) {
      info("Check all day, then select date");
      // waitForAndGetElement(ELEMENT_QUICK_CHECKBOX_TASK_ALLDAY,
      // DEFAULT_TIMEOUT, 1);
      evt.check(ELEMENT_QUICK_CHECKBOX_TASK_ALLDAY, 2);
      if ((from != null) & (from != ""))
        evt.type(ELEMENT_QUICK_INPUT_TASK_FROM_DATE, from, true);
      if ((to != null) & (to != ""))
        evt.type(ELEMENT_QUICK_INPUT_TASK_TO_DATE, to, true);

    } else {
      info("Uncheck all day, then select date time");
      // waitForAndGetElement(ELEMENT_QUICK_CHECKBOX_TASK_ALLDAY,
      // DEFAULT_TIMEOUT, 1);
      evt.uncheck(ELEMENT_QUICK_CHECKBOX_TASK_ALLDAY, 2);
      if ((from != null) & (from != "")) {
        String[] dateTimeFrom = from.split(" ");
        if (dateTimeFrom.length > 0)
          evt.type(ELEMENT_QUICK_INPUT_TASK_FROM_DATE, dateTimeFrom[0], true);
        if (dateTimeFrom.length > 1) {
          evt.click(ELEMENT_QUICK_INPUT_TASK_FROM_TIME_INPUT, 2);
          evt.click(ELEMENT_QUICK_TASK_SELECT_FROM_TIME.replace("${time}", dateTimeFrom[1]));
        }
      }
      if ((to != null) & (to != "")) {
        String[] dateTimeTo = to.split(" ");
        if (dateTimeTo.length > 0)
          evt.type(ELEMENT_QUICK_INPUT_TASK_TO_DATE, dateTimeTo[0], true);
        if (dateTimeTo.length > 1) {
          evt.click(ELEMENT_QUICK_INPUT_TASK_TO_TIME_INPUT, 2);
          evt.click(ELEMENT_QUICK_TASK_SELECT_TO_TIME.replace("${time}", dateTimeTo[1]));
        }
      }
    }
  }

  /**
   * Input into "From, To" and check/uncheck allday checkbox fields of a task in
   * detail form
   * 
   * @param from From date, time of a task i.e.: 11/06/2013 14:00
   * @param to To date, time of a task, i.e.: 11/06/2013 14:00
   * @param allDay Option "all day" of a task
   */
  public void inputFromToDetailTask(String from, String to, boolean allDay) {
    info("Input into From, To and check/uncheck allday checkbox fields of a task");
    if (allDay) {
      info("Check all day, then select date");
      evt.check(ELEMENT_QUICK_CHECKBOX_TASK_ALLDAY, 2);
      if ((from != null) & (from != ""))
        evt.type(ELEMENT_QUICK_INPUT_TASK_FROM_DATE, from, true);
      if ((to != null) & (to != ""))
        evt.type(ELEMENT_QUICK_INPUT_TASK_TO_DATE, to, true);

    } else {
      info("Uncheck all day, then select date time");
      evt.uncheck(ELEMENT_ADD_EDIT_TASK_ALLDAY, 2);
      if ((from != null) & (from != "")) {
        String[] dateTimeFrom = from.split(" ");
        if (dateTimeFrom.length > 0)
          evt.type(ELEMENT_ADD_EDIT_TASK_FROM_DATE, dateTimeFrom[0], true);
        if (dateTimeFrom.length > 1) {
          evt.click(ELEMENT_ADD_EDIT_TASK_FROM_TIME_INPUT, 2);
          evt.click(ELEMENT_ADD_EDIT_TASK_SELECT_FROM_TIME.replace("${time}", dateTimeFrom[1]));
        }
      }
      if ((to != null) & (to != "")) {
        String[] dateTimeTo = to.split(" ");
        if (dateTimeTo.length > 0)
          evt.type(ELEMENT_ADD_EDIT_TASK_TO_DATE, dateTimeTo[0], true);
        if (dateTimeTo.length > 1) {
          evt.click(ELEMENT_ADD_EDIT_TASK_TO_TIME_INPUT, 2);
          evt.click(ELEMENT_ADD_EDIT_TASK_SELECT_TO_TIME.replace("${time}", dateTimeTo[1]));
        }
      }
    }
  }

  /**
   * Input into basic fields of Quick task form
   * 
   * @param name String
   * @param from From date, time of a task i.e.: 11/06/2013 14:00
   * @param to To date, time of a task, i.e.: 11/06/2013 14:00
   * @param allDay boolean
   * @param opt String
   */
  public void inputDataTaskInQuickForm(String name, String note, String from, String to, boolean allDay, String... opt) {
    inputFromToQuickTask(from, to, allDay);
    inputBasicQuickTask(name, note, opt);
  }

  /**
   * Input into basic fields of detail task form
   * 
   * @param name String
   * @param note String
   * @param from From date, time of a task i.e.: 11/06/2013 14:00
   * @param to To date, time of a task, i.e.: 11/06/2013 14:00
   * @param allDay boolean
   * @param opt String
   */
  public void inputDataTaskInDetailForm(String name, String note, String from, String to, boolean allDay, String... opt) {
    inputBasicDetailTask(name, note, opt);
    inputFromToDetailTask(from, to, allDay);
  }

  /**
   * Attach file in "Add new task" form
   * 
   * @param path String
   * @param opt boolean
   */
  public void attachFileToTask(String path, Boolean... opt) {
    String fullPath = "";
    if ("win".equals(server)) {
      fullPath = "TestData\\" + path;
    } else {

      fullPath = "TestData/" + path;
    }
    info("fullPath:" + fullPath);
    evt.click(ELEMENT_TASK_ADD_ATTACHMENT);
    evt.click(ELEMENT_SELECT_FILE_BUTTON);
    testBase.uploadFileUsingRobot(fullPath);
    info("opt.length:" + opt.length);
    if (opt.length == 0) {
      evt.waitForAndGetElement(ELEMENT_ATTACHMENT_FORM_FILE_NAME.replace("$fileName", path));
      evt.click(ELEMENT_ATTACHMENT_SAVE_BUTTON, 0, true);
      evt.waitForAndGetElement(ELEMENT_ATTACH_FILE_NAME.replace("$fileName", path));
    }
  }

  /**
   * Check default suggestion task time in detail add form
   * 
   * @param fromDateTime (Format: MM/dd/yyyy HH:mm)
   * @param toDateTime (Format: MM/dd/yyyy HH:mm)
   * @param duration int
   */
  public void checkSuggestionTaskTimeInQuickForm(String fromDateTime, String toDateTime, int duration) {
    info("Check date is current date");
    DateFormat formatterTime = new SimpleDateFormat("HH:mm");
    DateFormat formatterTimeTemp = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    DateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
    String dateFrom = testBase.getValue(ELEMENT_QUICK_INPUT_TASK_FROM_DATE);
    String dateTo = testBase.getValue(ELEMENT_QUICK_INPUT_TASK_TO_DATE);
    String fromTime = evt.waitForAndGetElement(ELEMENT_QUICK_INPUT_TASK_FROM_TIME, testBase.getDefaultTimeout(), 1, 2)
                         .getAttribute("value");
    String toTime = evt.waitForAndGetElement(ELEMENT_QUICK_INPUT_TASK_TO_TIME, testBase.getDefaultTimeout(), 1, 2)
                       .getAttribute("value");

    info("Check default suggestion EVENT time");
    if (fromDateTime == null || fromDateTime == "") {
      info("Check time suggestion default");
      assert dateFrom.equals(testBase.getCurrentDate("MM/dd/yyyy"));
    } else {
      info("Check suggesion when select from time");
      try {
        Date fr = formatterDate.parse(fromDateTime);
        Date frTime = formatterTimeTemp.parse(fromDateTime);
        assert dateFrom.equals(formatterDate.format(fr));
        assert fromTime.equals(formatterTime.format(frTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (toDateTime == null || toDateTime == "") {
      info("Check time suggestion default");
      assert dateTo.equals(testBase.getCurrentDate("MM/dd/yyyy"));
    } else {
      info("Check suggesion when select to time");
      try {
        Date to = formatterDate.parse(toDateTime);
        Date tTime = formatterTimeTemp.parse(toDateTime);
        assert dateTo.equals(formatterDate.format(to));
        assert toTime.equals(formatterTime.format(tTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    info("Check duration");
    try {
      Date fr = formatterTime.parse(fromTime);
      Date to = formatterTime.parse(toTime);
      long diff = (to.getTime() - fr.getTime()) / 60000;
      info("Duration is " + diff + " minus");
      assert duration == (int) diff;
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check default suggestion task time in detail add form
   * 
   * @param fromDateTime (Format: MM/dd/yyyy HH:mm)
   * @param toDateTime (Format: MM/dd/yyyy HH:mm)
   * @param duration int
   */
  public void checkSuggestionTaskTimeInDetailForm(String fromDateTime, String toDateTime, int duration) {
    info("Check date is current date");
    DateFormat formatterTimeTemp = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
    SimpleDateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
    String dateFrom = testBase.getValue(ELEMENT_ADD_EDIT_TASK_FROM_DATE);
    String dateTo = testBase.getValue(ELEMENT_ADD_EDIT_TASK_TO_DATE);
    String fromTime = evt.waitForAndGetElement(ELEMENT_ADD_EDIT_INPUT_TASK_FROM_TIME, testBase.getDefaultTimeout(), 1, 2)
                         .getAttribute("value");
    String toTime = evt.waitForAndGetElement(ELEMENT_ADD_EDIT_INPUT_TASK_TO_TIME, testBase.getDefaultTimeout(), 1, 2)
                       .getAttribute("value");

    info("Check default suggestion EVENT time");
    if (fromDateTime == null || fromDateTime == "") {
      info("Check time suggestion default");
      assert dateFrom.equals(testBase.getCurrentDate("MM/dd/yyyy"));
    } else {
      info("Check suggesion when select from time");
      try {
        Date fr = formatterDate.parse(fromDateTime);
        Date frTime = formatterTimeTemp.parse(fromDateTime);
        assert dateFrom.equals(formatterDate.format(fr));
        assert fromTime.equals(formatterTime.format(frTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (toDateTime == null || toDateTime == "") {
      info("Check time suggestion default");
      assert dateTo.equals(testBase.getCurrentDate("MM/dd/yyyy"));
    } else {
      info("Check suggesion when select to time");
      try {
        Date to = formatterDate.parse(toDateTime);
        Date tTime = formatterTimeTemp.parse(toDateTime);
        assert dateTo.equals(formatterDate.format(to));
        assert toTime.equals(formatterTime.format(tTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    info("Check duration");
    try {
      Date fr = formatterTime.parse(fromTime);
      Date to = formatterTime.parse(toTime);
      long diff = (to.getTime() - fr.getTime()) / 60000;
      info("Duration is " + diff + " minus");
      assert duration == (int) diff;
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * select status of task from list
   * 
   * @param option category type: NPROGRESS,CANCELLED,COMPLETED,NEEDACTION;
   */
  public void selectStatus(statusTask option) {
    info("Select status of task from list");
    if (option != null) {
      switch (option) {
      case INPROGRESS:
        evt.select(ELEMENT_ADD_EDIT_TASK_STATUS, "In Progress");
        break;
      case CANCELLED:
        evt.select(ELEMENT_ADD_EDIT_TASK_STATUS, "Canceled");
        break;
      case COMPLETED:
        evt.select(ELEMENT_ADD_EDIT_TASK_STATUS, "Completed");
        break;
      case NEEDACTION:
        evt.select(ELEMENT_ADD_EDIT_TASK_STATUS, "Need Action");
        break;
      default:
        evt.select(ELEMENT_ADD_EDIT_TASK_STATUS, "Need Action");
        break;
      }
    }
  }

  /**
   * Save add Task
   */
  public void saveQuickAddTask() {
    info("save quick add task");
    evt.waitForAndGetElement(ELEMENT_BUTTON_TASK_SAVE, testBase.getDefaultTimeout(), 1);
    // click(ELEMENT_BUTTON_TASK_SAVE);
    evt.clickByJavascript(ELEMENT_BUTTON_TASK_SAVE, 2);
    evt.waitForElementNotPresent(ELEMENT_BUTTON_TASK_SAVE);

  }

  /**
   * Click on cancel button
   */
  public void cancelQuickAddEditTask() {
    evt.click(ELEMENT_BUTTON_TASK_QUICK_CANCEL);
    evt.waitForElementNotPresent(ELEMENT_BUTTON_TASK_QUICK_CANCEL);
  }

  /**
   * Click on cancel button of detail add/edit task
   */
  public void cancelAddEditDetailTask() {
    evt.click(ELEMENT_BUTTON_TASK_CANCEL_DETAILS);
    evt.waitForElementNotPresent(ELEMENT_BUTTON_TASK_CANCEL_DETAILS);
  }

  /**
   * Save a task with more details
   */
  public void saveAddTaskDetails() {
    info("Save a task with more detail");
    evt.waitForAndGetElement(ELEMENT_BUTTON_TASK_SAVE_DETAILS, testBase.getDefaultTimeout(), 1);
    evt.clickByJavascript(ELEMENT_BUTTON_TASK_SAVE_DETAILS, 2);
    // click(ELEMENT_BUTTON_TASK_SAVE_DETAILS);
    evt.waitForElementNotPresent(ELEMENT_BUTTON_TASK_SAVE_DETAILS);

  }

  /**
   * Click on more details
   */
  public void moreDetailsTask() {
    evt.click(ELEMENT_BUTTON_TASK_MORE_DETAILS);
    evt.waitForElementNotPresent(ELEMENT_BUTTON_TASK_MORE_DETAILS);
  }

  /**
   * Remove attachment
   * 
   * @param file name of file
   */
  public void removeAttachment(String file) {
    evt.click(ELEMENT_TASK_ATTACHMENT.replace("${file}", "Remove"));
    evt.waitForElementNotPresent(ELEMENT_TASK_ATTACHMENT.replace("${file}", file));
  }

  /**
   * Attach file without robot framework
   * 
   * @param pathFile as:"TestData/filename"
   */
  public void attachFilebDetailsTask(String pathFile) {
    evt.click(ELEMENT_TASK_ADD_ATTACHMENT);
    WebElement upload = evt.waitForAndGetElement(ELEMENT_ADD_EVENT_UPLOAD_FILE, testBase.getDefaultTimeout(), 1, 2);
    String path = Utils.getAbsoluteFilePathFromFile(pathFile);
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].style.display='block';", upload);
    upload.sendKeys(path);
    evt.waitForAndGetElement(ELEMENT_ADD_EVENT_TASK_UPLOAD_FINISH);
    String[] links = pathFile.split("/");
    evt.waitForAndGetElement(ELEMENT_ATTACH_FILE_LABEL.replace("${file}", links[links.length - 1]), 60000);
    evt.click(ELEMENT_ATTACH_FILE_SAVE_BUTTON);
    evt.waitForAndGetElement(ELEMENT_ADD_EVENT_FILE_ATTACHED.replace("${file}", links[links.length - 1]));

  }

  /**
   * Check user selector of task
   * 
   * @param user String
   * @param isPresent boolean
   */
  public void checkUserSelectorOfTask(String user, boolean isPresent) {
    goToAddTaskFromActionBar();
    moreDetailsTask();
    info("check delegation");
    evt.click(ELEMENT_ADD_EDIT_TASK_DELIGATION_ADDUSER_ICON);
    pPer.checkUserSelector(user, isPresent);
    evt.click(ELEMENT_USER_CLOSE_BUTTON);
    info("check reminder");
    goToRemindersTab();
    evt.click(ELEMENT_REMINDER_ADDMORE_ICON);
    pPer.checkUserSelector(user, isPresent);
    evt.click(ELEMENT_USER_CLOSE_BUTTON);
  }

  /**
   * function: check content of mail then delete mail in email server
   * 
   * @param titleTask String
   * @param opParams object
   */
  public void checkEmailNotificationReminderTask(String titleTask, Object... opParams) {
    info("Check and delete mail");
    Boolean checkOrNo = (Boolean) (opParams.length > 0 ? opParams[0] : true);
    String parentWindow = testBase.getExoWebDriver().getWebDriver().getWindowHandle();
    info("parentWindow:" + parentWindow);
    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
    }
    if (opParams.length > 0) {
      if (checkOrNo == true)
        evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_REMINDER_TASK.replace("$task", titleTask), 30000, 1);
      else
        evt.waitForElementNotPresent(ELEMENT_GMAIL_CONTENT_REMINDER_TASK.replace("$task", titleTask), 30000, 1);
    }
  }

  /**
   * View list in calendar
   */
  public enum statusTask {
    INPROGRESS, CANCELLED, COMPLETED, NEEDACTION;
  }
}
