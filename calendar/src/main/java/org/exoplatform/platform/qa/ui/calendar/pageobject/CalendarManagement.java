package org.exoplatform.platform.qa.ui.calendar.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_USER_CLOSE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class CalendarManagement {

  private final TestBase       testBase;

  public PlatformPermission    pPer;

  public EventManagement       evMg;

  public ManageAlert           alert;

  public Button                button;

  public CalendarHomePage      cHome;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase
   */
  public CalendarManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.pPer = new PlatformPermission(testBase);
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.evMg = new EventManagement(testBase);
    this.cHome = new CalendarHomePage(testBase);
  };

  /**
   * Execute action of calendar: Edit, Delete, Share, export....
   *
   * @param action action that needs to be done, e.g.: "ShareCalendar"
   */
  public void goToMenuFromMainCalendar(menuOfMainCalendar action) {
    info("Select action from menu");

    $(ELEMENT_CALENDAR_MENU_ACTIONS_ICON).click();
    $(ELEMENT_CALENDAR_MENU).waitUntil(Condition.visible, Configuration.timeout);
    switch (action) {
      case ADDCAL:
        info("Go to add calendar");

        $(ELEMENT_CALENDAR_MENU_ACTIONS_ADD).waitUntil(Condition.appears, Configuration.timeout);
        $(ELEMENT_CALENDAR_MENU_ACTIONS_ADD).click();
        $(ELEMENT_CALENDAR_ADD_FORM).waitUntil(Condition.appears, Configuration.timeout);
        break;
      case REMOTECAL:
        info("Go to remote calendar");
        evt.click(ELEMENT_CALENDAR_MENU_ACTIONS_REMOTE, 0, true);
        evt.waitForAndGetElement(ELEMENT_REMOTE_CALENDAR_FORM);
        break;
      case ADDCATEGORY:
        info("Go to add category calendar");
        $(ELEMENT_CALENDAR_MENU_ACTIONS_ADD_EVENT_CATEGORY).click();
        $(ELEMENT_ADD_EVENT_CATEGORY_FORM).waitUntil(Condition.appears, Configuration.timeout);
        break;
      case CALSETTING:
        info("Go to calendar setting");

        evt.waitForAndGetElement(ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING, testBase.getDefaultTimeout(), 1);
        evt.click(ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING, 0, true);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_SETTING_FORM, testBase.getDefaultTimeout(), 1);
        break;
      case IMPORT:
        info("Import calendar");
        evt.click(ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT, 0, true);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
        break;
      default:
        info("Go to add calendar");
        evt.click(ELEMENT_CALENDAR_MENU_ACTIONS_ADD, 0, true);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
        break;
    }
  }

  /**
   * Open Calendar setting form from Setting button on Actions Bar
   */
  public void openSettingCalendar() {
    info("Click on Settings icon");
    evt.click(ELEMENT_ACTION_BAR_SETTING_ICON);
    evt.waitForAndGetElement(ELEMENT_CALENDAR_SETTING_FORM);
    info("Settings Calendar form is shown");
  }

  /**
   * Delete a calendar
   *
   * @param name name of calendar
   * @param verify optional parameter. If not be set, by default, it is considered
   *          that calendar is deleted = true: verify that calendar is deleted, =
   *          false: not verify that calendar is deleted,
   */
  public void deleteCalendar(String name, boolean... verify) {
    if ($(byId("UICalendars")).find(byText(name)).is(Condition.exist)) {
      boolean isVerify = (verify.length > 0 ? verify[0] : false);
      info("Remove calendar");
      refresh();
      executeActionCalendar(name, menuOfCalendarOption.REMOVE);
      if (isVerify)
        alert.verifyAlertMessage(ELEMENT_CONFIRM_REMOVE_CALENDAR_MSG);
      else
        ELEMENT_YES_BUTTON.click();
      $(byText(name)).waitUntil(Condition.disappears, Configuration.timeout);
    }

  }

  /**
   * Edit a calendar
   *
   * @param oldName old name of calendar
   * @param name new name of calendar
   * @param description new description of calendar
   * @param color new color of calendar
   * @param group group: example (/developers, /platform/administrators,
   *          /platform/users, /platform/web-contributors,
   *          /organization/management/executive-board, /organization/employees
   */
  public void editCalendar(String oldName, String name, String description, String color, String group) {
    executeActionCalendar(oldName, menuOfCalendarOption.EDIT);
    inputDataInDetailTabCalendarForm(name, description, color);
    if (group != null && group != "")
      selectGroupInGroupTabCalendarForm(group, true);
  }

  /**
   * Input into tab Detail of Add calendar form
   *
   * @param name name of calendar
   * @param description description of calendar
   * @param color color of calendar
   */
  public void inputDataInDetailTabCalendarForm(String name, String description, String color) {
    info("Input into tab Detail of Add calendar form");
    ELEMENT_CALENDAR_DETAIL_TAB.click();
    if (name != null && name != "")
      $(ELEMENT_CALENDAR_DISPLAY_NAME_INPUT).setValue(name);
    if (description != null && description != "")
      $(ELEMENT_CALENDAR_DESC_INPUT).setValue(description);
    if (color != null && color != "") {
      $(ELEMENT_CALENDAR_COLOR).click();
      $(byId("UICalendarPopupWindow")).find(byClassName(color)).waitUntil(Condition.appears, Configuration.timeout);
      $(byId("UICalendarPopupWindow")).find(byClassName(color)).click();

    }
  }

  /**
   * selectGroupInGroupTabCalendarForm
   *
   * @param group group: example (/developers, /platform/administrators,
   *          /platform/users, /platform/web-contributors,
   *          /organization/management/executive-board, /organization/employees
   * @param isType true: select a group by typing false: select a group by select
   *          group icon
   */
  public void selectGroupInGroupTabCalendarForm(String group, boolean isType) {
    info("Input into tab Show in Group of Add calendar form");
    $(ELEMENT_CALENDAR_GROUP_TAB).click();
    if (group != null && group != "") {
      if (isType) {
        $(ELEMENT_CALENDAR_GROUP_INPUT).setValue(group);
      } else {
        evt.click(ELEMENT_CALENDAR_SELECT_GROUP_ICON);
        evt.click(group);
      }
    }
    $(ELEMENT_CALENDAR_ADD_GROUP_BUTTON).click();
  }

  /**
   * Remove a group in group table of Calendar form
   * 
   * @param groupName String
   */
  public void removeGroupInGroupTabCalendarForm(String groupName) {
    info("Input into tab Show in Group of Add calendar form");
    evt.click(ELEMENT_CALENDAR_GROUP_TAB);
    evt.click(ELEMENT_CALENDAR_GROUP_REMOVE_BTN.replace("$group", groupName));
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_REMOVE_BTN.replace("$group", groupName));
  }

  /**
   * Select a user/role who has edit permission in a group
   * 
   * @param user String
   * @param mode way to input users, groups. =0: type directly =1: select user =2:
   *          select role
   */
  public void selectUserEditPermissionInGroup(String[] user, int... mode) {
    for (int i = 0; i < user.length; i++) {
      int modeUser = mode.length > i ? mode[i] : 0;
      switch (modeUser) {
        case 0:
          info("user" + user[i]);
          evt.type(ELEMENT_CALENDAR_GROUP_INPUT_USER, user[i], true);
          break;
        case 1:
          $(ELEMENT_CALENDAR_GROUP_SELECT_USER_BTN).click();
          // evt.click(ELEMENT_CALENDAR_GROUP_USER_IN_SELECT_FORM.replace("$user",
          // user[i]));
          $(byText(user[i])).click();
          break;
        case 2:
          String[] groupMem = user[i].split(":");
          String[] membership = groupMem[1].split(".");
          evt.click(ELEMENT_CALENDAR_GROUP_SELECT_ROLE_BTN);
          pPer.selectGroupMembership(groupMem[0], membership[1]);
          break;
      }
    }
  }

  /**
   * Select group permission
   * 
   * @param group String
   * @param membership String
   */
  public void selectGroupPermission(String group, String membership) {
    info("select group:" + group);
    evt.click(ELEMENT_CALENDAR_SELECT_GROUP_ICON, 0, true);
    evt.waitForAndGetElement(ELEMENT_CALENDAR_GROUP_SELECT_FORM);
    evt.click(ELEMENT_CALENDAR_GROUP_SELECT_LIST.replace("$group", group));
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_SELECT_FORM);
    evt.click(ELEMENT_CALENDAR_ADD_GROUP_BUTTON, 0, true);

    evt.click(ELEMENT_CALENDAR_GROUP_SELECT_ROLE_BTN);
    evt.waitForAndGetElement(ELEMENT_CALENDAR_GROUP_SELECT_FORM);
    evt.click(ELEMENT_CALENDAR_GROUP_SELECT_LIST.replace("$group", membership));
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_SELECT_FORM);

  }

  /**
   * Check user selector in group calendar
   * 
   * @param cal String
   * @param user String
   * @param isPresent boolean
   */
  public void checkUserSelectorOfGroupCalendar(String cal, String user, boolean isPresent) {
    executeActionCalendar(cal, menuOfCalendarOption.EDIT);
    evt.click(ELEMENT_CALENDAR_GROUP_TAB);
    evt.click(ELEMENT_CALENDAR_GROUP_SELECT_USER_BTN);
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_CALENDAR_GROUP_USER_IN_SELECT_FORM.replace("$user", user));
    else
      evt.waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_USER_IN_SELECT_FORM.replace("$user", user));
  }

  /**
   * Save add calendar
   */
  public void saveAddCalendar() {
    info("Save add calendar");
    $(ELEMENT_CALENDAR_ADD_SAVE_BUTTON).click();

  }

  /**
   * Cancel add calendar
   */
  public void cancelAddCalendar() {
    info("Click on Cancel button");
    evt.click(ELEMENT_CALENDAR_ADD_CANCEL_BUTTON);

  }

  /**
   * Go to Calendar Actions -Add Event Category
   * 
   * @param categoryName category name of Calendar
   */
  public void addEventCategory(String categoryName) {
    info("----Add new event category----");
    goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
    $(ELEMENT_ADD_EVENT_CATEGORY_INPUT).setValue(categoryName);
    evt.click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_ADD);
    info("----Verify if event category is added in Category List or not----");
    $(byText(categoryName)).waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE.click();
  }

  /**
   * Delete Event Category
   *
   * @param categoryName category name of calendar
   */
  public void deleteEventCategory(String categoryName) {
    info("Delete category");
    goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
    $(byText(categoryName)).waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_LIST_CATEGORY.find(byText(categoryName)).parent().parent().find(ELEMENT_DELETE_CATEGORY).click();
    alert.acceptAlert();
    button.yes();
    $(byText(categoryName)).waitUntil(Condition.disappears, Configuration.timeout);
    ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE.click();
  }

  /**
   * Edit Event Category
   *
   * @param oldCategory old category name
   * @param newCategory new category name
   */

  public void editEventCategory(String oldCategory, String newCategory) {
    $(byXpath(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}", oldCategory))).waitUntil(Condition.visible,Configuration.timeout);
    $(byXpath(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}", oldCategory))).click();
    $(ELEMENT_ADD_EVENT_CATEGORY_INPUT).setValue(newCategory);
    $(ELEMENT_ADD_EVENT_CATEGORY_INPUT).click();
    $(ELEMENT_EDIT_EVENT_CATEGORY_BUTTON_UPDATE).click();
    $(byXpath(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}", newCategory))).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE).click();
  }

  /**
   * Open menu an a calendar
   *
   * @param calendar name of calendar
   */
  public void openMenuOfCalendar(String calendar) {
    info("Open menu of a calendar");
    ELEMENT_LIST_CALENDAR.find(byText(calendar)).waitUntil(Condition.appears, Configuration.timeout)
            .hover()
            .parent()
            .parent()
            .find(ELEMENT_CALENDAR_ICON_SETTINGS_OF_CALENDAR)
            .click();

  }

  /**
   * Execute action of calendar: Edit, Delete, Share, export....
   *
   * @param calendar name of calendar
   * @param action action that needs to be done, e.g.: "ShareCalendar"
   * @param color color that is selected for calendar
   */
  public void executeActionCalendar(String calendar, menuOfCalendarOption action, String... color) {
    info("Select action from menu");
    openMenuOfCalendar(calendar);
    switch (action) {
    case ADDTASK:
      $(ELEMENT_CALENDAR_ADD_TASK_MENU).click();
      $(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case ADDEVENT:
      $(ELEMENT_CALENDAR_ADD_EVENT_MENU).click();
      $(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case EDIT:
      evt.clickByJavascript(ELEMENT_CALENDAR_EDIT_MENU, 2);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_ADD_FORM);
      break;
    case REMOVE:
      // evt.clickByJavascript(ELEMENT_CALENDAR_REMOVE_MENU, 2);
      $(ELEMENT_CALENDAR_REMOVE_MENU).click();
      $(ELEMENT_CALENDAR_REMOVE_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case SHARE:
      $(ELEMENT_CALENDAR_SHARE_MENU).click();
      $(ELEMENT_CALENDAR_SHARE_FORM).waitUntil(Condition.visible, Configuration.timeout);
      break;
    case IMPORT:
      evt.clickByJavascript(ELEMENT_CALENDAR_IMPORT_MENU, 2);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
      break;
    case EXPORT:
      evt.clickByJavascript(ELEMENT_CALENDAR_EXPORT_MENU, 2);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
      break;
    case REFRESH:
      evt.clickByJavascript(ELEMENT_CALENDAR_REFRESH_MENU, 2);
      break;
    case COLOR:
      info("Select a color");
      evt.click(ELEMENT_CALENDAR_MENU_ACTIONS_COLOR.replace("${color}", color[0]));
      break;
    default:
      evt.click(ELEMENT_CALENDAR_ADD_TASK_MENU, 2);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
      break;


    }
  }

  /**
   * Share a calendar
   *
   * @param calendar name of calendar
   * @param userGroup array of users or groups that are shared with
   * @param canEdit array of "canEdit" permissions for users, groups respectively
   *          inputed by userGroup variable
   * @param mode way to input users, groups. =0: type directly =1: select user =2:
   *          select group =3: select membership
   */
  public void shareCalendar(String calendar, String[] userGroup, boolean[] canEdit, int... mode) {
    info("Share calendar");
    executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
    if (userGroup.length > 0 && !userGroup[0].isEmpty()) {
      for (int i = 0; i < userGroup.length; i++) {
        int modeUser = mode.length > i ? mode[i] : 0;
        switch (modeUser) {
        case 0:
          info("userGroup[i]" + userGroup[i]);
          $(ELEMENT_CALENDAR_SHARE_INPUT).setValue(userGroup[i]);
          break;
        case 1:
          $(ELEMENT_CALENDAR_SELECT_USER_ICON).click();
          pPer.selectUserPermission(userGroup[i], 1);
          break;
        case 2:
          String[] group = userGroup[i].split(":");
          $(ELEMENT_CALENDAR_SELECT_GROUP_ICON).click();
          pPer.selectGroupPermission(group[0]);
          break;
        case 3:
          String[] groupMem = userGroup[i].split(":");
          String[] membership = groupMem[1].split(".");
          $(ELEMENT_CALENDAR_SELECT_MEMBERSHIP_ICON).click();
          pPer.selectGroupMembership(groupMem[0], membership[1]);
          break;

        }
      }
      evt.click(ELEMENT_CALENDAR_SHARE_ADD_BUTTON);
    }

    for (int j = 0; j < canEdit.length; j++) {
      if (canEdit[j] == true) {
        evt.check(byXpath(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", userGroup[j])));
      } else {
        evt.uncheck(byXpath(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", userGroup[j])));
      }
    }
    $(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON).click();
    $(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * Check user selector of share calendar
   *
   * @param cal String
   * @param user String
   * @param isPresent boolean
   */
  public void checkUserSelectorOfShareCalendar(String cal, String user, boolean isPresent) {
    executeActionCalendar(cal, menuOfCalendarOption.SHARE);
    evt.click(ELEMENT_CALENDAR_SELECT_USER_ICON);

    pPer.checkUserSelector(user, isPresent);
    evt.click(ELEMENT_USER_CLOSE_BUTTON);
  }

  /**
   * Share a calendar
   *
   * @param calendar name of calendar
   * @param userGroup array of users or groups that are shared with
   */
  public void removeUserGrooupFromShareCalendar(String calendar, String[] userGroup) {
    info("Share calendar");
    executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
    for (int i = 0; i < userGroup.length; i++) {
      $(byXpath(ELEMENT_DELETE_SHARE_USER.replace("{$user}", userGroup[i]))).click();
    }
    $(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON).click();
    $(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * Upload calendar
   *
   */
  public void uploadCalendar() {
    info("--Upload Calendar--");
    $(byClassName("uploadContainer")).find(byClassName("file")).uploadFromClasspath("testCalendar.ics");
    $(byClassName("progressBarFrame")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(ELEMENT_CALENDAR_IMPORT_DELETE_ICON).waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Import calendar
   *
   * @param name name of calendar
   * @param description description of calendar
   * @param color color of calendar
   */
  public void importCalendar(String name, String description, String color) {
    /*
     * click(ELEMENT_CALENDAR_MENU_ACTIONS_ICON);
     * click(ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT);
     */
    goToMenuFromMainCalendar(menuOfMainCalendar.IMPORT);
    // waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
    uploadCalendar();
    if (name != null)
      $(ELEMENT_CALENDAR_IMPORT_NAME_INPUT).setValue(name);
    if (description != null)
      $(ELEMENT_CALENDAR_IMPORT_DESC_INPUT).setValue(description);

    if (color != null) {
      $(ELEMENT_CALENDAR_IMPORT_COLOR).click();
      $(byXpath(ELEMENT_CALENDAR_COLOR_SELECT.replace("${color}", color))).click();
    }
    $(ELEMENT_CALENDAR_IMPORT_SAVE_BUTTON).click();
    $(ELEMENT_CALENDAR_IMPORT_POPUP_FORM).shouldNot(Condition.visible);
    refresh();
    evt.waitForAndGetElement(By.linkText(name));
  }

  /**
   * Import event/task to an calendar
   *
   * @param calendar name of calendar will be imported
   * @param path path of a file which is for upload
   */
  public void importTaskEvent(String calendar, String path) {
    executeActionCalendar(calendar, menuOfCalendarOption.IMPORT);
    uploadCalendar();
    evt.click(ELEMENT_CALENDAR_IMPORT_SAVE_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
    this.testBase.getExoWebDriver().getWebDriver().navigate().refresh();
  }

  /**
   * Export calendar
   *
   * @param calendar name of calendar
   * @param name filename of exported calendar
   */
  public void exportCalendar(String calendar, String name) {
    info("Export calendar");
    executeActionCalendar(calendar, menuOfCalendarOption.EXPORT);
    $(ELEMENT_CALENDAR_EXPORT_POPUP_FORM).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_CALENDAR_EXPORT_FILE_NAME).setValue(name);
    $(ELEMENT_CALENDAR_EXPORT_SAVE_BUTTON).click();
    $(ELEMENT_CALENDAR_EXPORT_POPUP_FORM).shouldNot(Condition.visible);
  }

  /**
   * Export Task/Event
   *
   * @param taskEvent name of task or event
   * @param name filenam of exported task/event
   */
  public void exportTaskEvent(String taskEvent, String name) {
    info("Export calendar");
    evt.rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}", taskEvent));
    selectOptionByRightclickOnEvent(contextMenuEditEvenOption.EXPORT);
    evt.waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
    evt.type(ELEMENT_CALENDAR_EXPORT_FILE_NAME, name, true);
    evt.click(ELEMENT_CALENDAR_EXPORT_SAVE_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
  }

  /**
   * change values in setting tab of calendar setting form
   *
   * @param viewtype default view fof calendar: Week, Day, Month, List, Work Week
   * @param timezone time zone of calendar, e.g.: (GMT -11:00) Pacific/Samoa *
   * @param dateformat date format of calendar:dd/mm/yyyy, dd-mm-yyyy, mm/dd/yyyy,
   *          mm-dd-yyyy
   * @param timeformat time format of calendar: AM/PM, 24 Hours
   * @param day start day of week: monday, tuesday, wednesday, thursday, saturday,
   *          sunday
   * @param isShow true: show working time false: don't show working time
   * @param option option to send mail inviatation: NEVER, ASK, ALWAYS
   */
  public void changeSettingCalendar(String viewtype,
                                    String timezone,
                                    String dateformat,
                                    String timeformat,
                                    String day,
                                    Boolean isShow,
                                    PlatformBase.selectInvitationOption option) {
    if (viewtype != null && viewtype != "") {
      info("-- Select filter option of view type --");
      $(ELEMENT_CALENDAR_SETTING_VIEW_TYPE).selectOption(viewtype);
    }
    if (timezone != null && timezone != "") {
      info("-- Select filter option of Timezone --");
      $(ELEMENT_CALENDAR_SETTING_TIME_ZONE).selectOption(timezone);
    }
    if (dateformat != null && dateformat != "") {
      info("-- Select filter option of date format --");
      $(ELEMENT_CALENDAR_SETTING_DATE_FORMAT).selectOption(dateformat);
    }
    if (timeformat != null && timeformat != "") {
      info("-- Select filter option of time format --");
      $(ELEMENT_CALENDAR_SETTING_TIME_FORMAT).selectOption(timeformat);
    }
    if (day != null && day != "") {
      info("-- Select filter option of week start on --");
      $(ELEMENT_CALENDAR_SETTING_WEEK_START_ON).selectOption(day);
    }
    if (isShow != null) {
      info("-- Select show working time or not --");
      if (isShow)
        evt.check(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX, 2);
      else
        evt.uncheck(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX, 2);
    }

    if (option != null) {
      info("-- Select send invitaion option --");
      switch (option) {
      case NEVER:
        // check(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX,2);
        $(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX).parent().click();
        break;
      case ALWAYS:
        // check(ELEMENT_CALENDAR_SETTING_ALWAYS_SEND_INVITE_CHECKBOX,2);
        $(ELEMENT_CALENDAR_SETTING_ALWAYS_SEND_INVITE_CHECKBOX).parent().click();
        break;
      case ASK:
        // check(ELEMENT_CALENDAR_SETTING_ASK_SEND_INVITE_CHECKBOX,2);
        $(ELEMENT_CALENDAR_SETTING_ASK_SEND_INVITE_CHECKBOX).click();
        break;
      default:
        // check(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX,2);
        $(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX).click();
        break;


      }
    }
  }

  /**
   * change start and end time for working time in calendar setting form
   * 
   * @param startTime String
   * @param endTime String
   */
  public void changeWorkingTime(String startTime, String endTime) {
    if (!startTime.isEmpty())
      evt.select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_BEGIN_TIME, startTime);
    if (!endTime.isEmpty())
      evt.select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_END_TIME, endTime);
  }

  /**
   * save setting form
   */
  public void saveSetting() {
    evt.click(ELEMENT_SETTING_FORM_SAVE_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_SETTING_FORM);
  }

  /**
   * cancel setting form
   */
  public void cancelSetting() {
    evt.click(ELEMENT_SETTING_FORM_CANCEL_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_SETTING_FORM);

  }

  /**
   * show/hide personal calendar in tab displayed calendar of calendar setting
   * form
   *
   * @param calendarName name of calendar
   * @param isShow true: check show calendar false: hide calendar
   */
  public void showHideCalendar(String calendarName, Boolean isShow) {
    info("Show/Hide personal calenar");
    goToDisplayCalendarTab();
    if (isShow != null) {
      if (isShow) {
        if ($(byXpath(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName))).parent().is(Condition.visible))
          $(byXpath(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName))).parent().click();
          $(byXpath(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName))).parent().waitUntil(Condition.visible,Configuration.timeout);
      } else {
        if ($(byXpath(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName))).is(Condition.visible))
          $(byXpath(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName))).click();
          $(byXpath(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName))).parent().waitUntil(Condition.visible,Configuration.timeout);
      }
    } else {
      if (evt.waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName), 5000, 0) != null)
        evt.click(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName));
      evt.waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName));
    }
  }

  /**
   * Show/Hide event/task from Calendar list
   * 
   * @param calendar String
   */
  public void showHideEventTask(String calendar) {
    info("Show/Hide event/task");
    evt.click(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendar));

  }

  /**
   * Add/Edit new feed
   *
   * @param name name of feed
   * @param url url of feed
   * @param calendars calendar list (split by "/": ex: John Smith/Development)
   */
  public void addEditFeed(String name, String url, String calendars) {
    info("Add new feed");
    if (name != null && name != "") {
      $(ELEMENT_FEED_NAME_INPUT).setValue(name);
    }
    if (url != null && url != "") {
      evt.type(ELEMENT_FEED_URL_INPUT, name, true);
    }
    if (calendars != null && calendars != "") {
      String[] calendar = calendars.split("/");
      for (int i = 0; i < calendar.length; i++) {
        $(ELEMENT_FEED_CALENDAR_OPTION).selectOption(calendar[i]);
        $(ELEMENT_FEED_EDIT_FEED_ADD_CALENDAR).click();
      }
    }
  }

  /**
   * Generate URL
   */
  public void generateUrl() {
    info("Generate URL");
    evt.click(ELEMENT_FEED_EDIT_FEED_GENERATE_URL);
  }

  /**
   * Reset URL
   */
  public void resetUrl() {
    info("Reset URL");
    evt.click(ELEMENT_FEED_EDIT_FEED_RESET_URL);
  }

  /**
   * delete calendar from feed
   *
   * @param calendar name of calendar
   */
  public void deleteCalendarFromFeed(String calendar) {
    info("delete calendar from feed");
    if (calendar != null && calendar != "") {
      evt.click(ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR.replace("$name", calendar));
      evt.waitForElementNotPresent(ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR.replace("$name", calendar));
    }
  }

  /**
   * Delete Feed
   *
   * @param feed name of feed
   * @param isVerify true: verify confirm message false: not verify confirm
   *          message
   */
  public void deleteFeed(String feed, Boolean isVerify) {
    info("Delete Feed");
    ManageAlert alert = new ManageAlert(testBase);
    Button button = new Button(testBase);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_DELETE_BUTTON.replace("$name", feed))).click();
    if (isVerify != null) {
      if (isVerify)
        alert.verifyAlertMessage(ELEMENT_FEED_CONFIRM_DELETE);
      else
        evt.click(button.ELEMENT_YES_BUTTON);
    } else
      evt.click(button.ELEMENT_YES_BUTTON);
  }

  /**
   * Select an option in context menu
   * 
   * @param option String
   */
  public void selectOptionByRightclickOnEvent(contextMenuEditEvenOption option) {
    switch (option) {
      case VIEW:
        info("Select View option");
        evt.click(ELEMENT_CONTEXT_MENU_VIEW);
        break;
      case EDIT:
        info("Select Edit option");
        evt.click(ELEMENT_CONTEXT_MENU_EDIT);
        break;
      case DELETE:
        info("Select Delete option");
        $(ELEMENT_CONTEXT_MENU_DELETE).click();
        // click(ELEMENT_CONFIRM_POPUP_OK);
        break;
      case DELETE_RECURRING:
        info("Select Delete option");
        evt.click(ELEMENT_CONTEXT_MENU_DELETE);
        break;
      case EXPORT:
        info("Select Export option");
        evt.click(ELEMENT_CONTEXT_MENU_EXPORT);
        break;
      default:
        info("No option to select");
        break;
    }
  }

  /**
   * Open edit event form by right click on the event
   * 
   * @param name String
   */
  public void openEditPopupEventByRightClick(String name) {
    info("Right click on an Event/Task");
    evt.rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}", name));
    selectOptionByRightclickOnEvent(contextMenuEditEvenOption.EDIT);
    evt.waitForAndGetElement(ELEMENT_ADD_EDIT_EVENT_POPUP, 3000, 1);
    info("Edit/Task form is shown");
  }

  /**
   * Open add/edit event popup by double clicking
   * 
   * @param name String
   * @param opt is an instance of a repeated event as 1,2,3,4....
   * @param view view
   */
  public void openEditEventTaskPopup(String name, CalendarHomePage.selectViewOption view, String... opt) {
    info("Edit an event");
    Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    cHome.goToView(view);
    if (opt.length > 0 && opt[0] != null) {
      switch (view) {
        case WEEK:
          WebElement el_week = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$date", opt[0])
                  .replace("$name", name));

          scrollElementIntoView(el_week);
          action.moveToElement(el_week).doubleClick().perform();
          break;
        case MONTH:
          WebElement el_month = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$date", opt[0])
                  .replace("$name", name));

          scrollElementIntoView(el_month);
          action.moveToElement(el_month).doubleClick().perform();
          break;
        case WORKWEEK:
          WebElement el_workweek =
                  evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$date", opt[0])
                          .replace("$name", name));

          scrollElementIntoView(el_workweek);
          action.moveToElement(el_workweek).doubleClick().perform();
          break;
        default:
          info("Please input only Month, Week and WorkWeek view");
          break;
      }
      info("Double click on the event");

    } else {
      info("Double click on the event");
      // scrollElementIntoView(this.testBase.getExoWebDriver().getWebDriver()
      // .findElement(By.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}",
      // name))));
      // action.moveToElement(evt.waitForAndGetElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",
      // name))).doubleClick().perform();
      if (($(byText(name)).is(Condition.not(Condition.exist)))) {
        ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON.click();
        ELEMENT_POUPUP_LIST_EVENT.find(byText(name)).doubleClick();
      } else {
        ELEMENT_POUPUP_LIST_EVENT.find(byText(name)).doubleClick();
      }
    }
    $(ELEMENT_ADD_EDIT_EVENT_POPUP).waitUntil(Condition.appears, Configuration.timeout);
    info("The edit form is shown");
  }

  /**
   * Open quick Add Event/Task popup by index in Month View
   * 
   * @param col String
   * @param row String
   */
  public void openQuickAddByClickOnIndexInMonthView(String col, String row) {
    info("Go to Month view");
    cHome.goToView(CalendarHomePage.selectViewOption.MONTH);
    evt.click(ELEMENT_EVENT_TASK_DAY_BY_INDEX_MONTH_VIEW.replace("$col", col).replace("$row", row));
    evt.waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM);
    info("Quick Add Event task is shown");
  }

  /**
   * Remove an event or a task in any views by right Click
   * 
   * @param name String
   */
  public void deleteTaskEvent(String name) {
    info("Right click on an Event/Task");
    // scrollElementIntoView(this.driver.findElement(By.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name))));
    // evt.rightClickOnElement(By.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}",
    // name)));
    $(byText(name)).contextClick();
    selectOptionByRightclickOnEvent(contextMenuEditEvenOption.DELETE);
    $(ELEMENT_YES_BUTTON).click();
    // evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_TITLE.replace("${name}",
    // name));
    $(byText(name)).shouldNot(Condition.exist);
  }

  /**
   * Delete task/event by selecting an task/event's checkbox in List View
   * 
   * @param name String
   */
  public void deleteTaskEventInListView(String name) {
    if (!name.isEmpty()) {
      cHome.goToView(CalendarHomePage.selectViewOption.LIST);
      info("Select the event/task");
      evt.check(ELEMENT_EVENT_TASK_CHECKBOX_LIST_VIEW.replace("$name", name), 2);
      deleteTaskEvent();
      evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_TITLE.replace("${name}", name));
    }

  }

  /**
   * Delete task/events by click on Delete button on header bar of Month or List
   * View
   */
  public void deleteTaskEvent(EventManagement.recurringType... type) {
    info("Click on Delete button");
    evt.click(ELEMENT_EVENT_TASK_DELETE_BUTTON);
    confirmDeleteEventTask();

  }

  /**
   * Confirm deleting Events/Tasks
   */
  public void confirmDeleteEventTask(EventManagement.recurringType... type) {
    if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK, 2000, 0) != null)
      evt.click(ELEMENT_CONFIRM_POPUP_OK);
    if (evt.waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT, 2000, 0) != null) {
      if (type.length > 0)
        evMg.deleteRecurringConfirm(type[0]);
      else
        evMg.deleteRecurringConfirm(EventManagement.recurringType.ALL_EVENT);
    }
    if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_DELETE, 2000, 0) != null) {
      evt.click(ELEMENT_CONFIRM_POPUP_DELETE);
      evt.waitForElementNotPresent(ELEMENT_CONFIRM_POPUP_DELETE);
    }

  }

  /**
   * Remove an event or a task on List tab
   * 
   */
  public void deleteAllTaskEvent() {
    if (evt.waitForAndGetElement(ELMENT_CALENDAR_TAB_LIST_EMPTY, 3000, 0) == null) {
      info("Select all task/events in the list tab");
      evt.check(ELEMENT_CALENDAR_LIST_TAB_SELECT_ALL_CHECKBOX, 2);
      evt.click(ELEMENT_EVENT_TASK_DELETE_BUTTON);
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK, 2000, 0) != null)
        evt.click(ELEMENT_CONFIRM_POPUP_OK);
      if (evt.waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT, 2000, 0) != null) {
        evMg.deleteRecurringConfirm(EventManagement.recurringType.ALL_EVENT);
      }
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_DELETE, 2000, 0) != null)
        evt.click(ELEMENT_CONFIRM_POPUP_DELETE);
    }

  }

  /**
   * Scroll to element to view
   * 
   * @param element WebElement
   */
  public void scrollElementIntoView(WebElement element) {
    info("Scroll to the element to view");
    ((JavascriptExecutor) this.testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
            element);
  }

  /**
   * function: check content of mail then delete mail in email server
   * 
   * @param titleEventTask String
   * @param fileAttch String
   * @param link String
   * @param user String
   * @param opParams boolean
   */
  public void checkEmailNotificationCalendar(String titleEventTask,
                                             String fileAttch,
                                             String link,
                                             String user,
                                             Boolean... opParams) {
    info("Check and delete mail");
    String parentWindow = testBase.getExoWebDriver().getWebDriver().getWindowHandle();
    info("parentWindow:" + parentWindow);
    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
    }
    if (opParams.length > 0) {
      if (opParams[0] == true)
        evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask", titleEventTask), 30000, 1);
      else
        evt.waitForElementNotPresent(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask", titleEventTask), 30000, 1);
    }

    if (opParams.length > 1) {
      info("Click on the email");
      evt.click(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask", titleEventTask));

      for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
        testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
        info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
      }
      if (!fileAttch.isEmpty())
        evt.waitForAndGetElement(ELEMENT_GMAIL_EMAIL_ICS_FILE.replace("$file", fileAttch));

      if (!link.isEmpty()) {
        info("Click on the link");
        evt.click(ELEMENT_GMAIL_EMAIL_DETAIL_LINK.replace("$link", link));
        for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
          testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
          info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
        }
        if (!user.isEmpty()) {
          if (evt.waitForAndGetElement(ELEMENT_REFUSE_INVITATION_MESSAGE.replace("$user", user), 2000, 0) != null)
            assert true;
          else if (evt.waitForAndGetElement(ELEMENT_MAYBE_INVITATION_MESSAGE.replace("$user", user), 2000, 0) != null)
            assert true;
          else if (evt.waitForAndGetElement(ELEMENT_ACCEPT_INVITATION_MESSAGE.replace("$user", user), 2000, 0) != null)
            assert true;
          else
            assert false : "Wrong message or Cannot access to the link";
        }
      }
    }
  }

  /**
   * Open display calendar tab in Calendar setting form
   */
  public void goToDisplayCalendarTab() {
    info("Click on the Display calendar tab");
    evt.click(ELEMENT_CALENDAR_SETTING_DISPLAY_TAB);

  }

  /**
   * Open Feed tab in Calendar setting form
   */
  public void goToFeedTab() {
    info("Click on the Display calendar tab");
    $(ELEMENT_CALENDAR_SETTING_FEED_TAB).click();

  }

  /**
   * Enabled or disabled public access for an calendar
   * 
   * @param calendar String
   */
  public void enabledPublicAccess(String calendar) {
    info("Click on Edit link");
    executeActionCalendar(calendar, menuOfCalendarOption.EDIT);
    info("Click on the link");
    if (evt.waitForAndGetElement(ELEMENT_CALENDAR_EDIT_ENABLE_PUBLIC_LINK, 3000, 0) != null) {
      evt.click(ELEMENT_CALENDAR_EDIT_ENABLE_PUBLIC_LINK);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_EDIT_DISNABLE_PUBLIC_LINK);
    } else {
      evt.click(ELEMENT_CALENDAR_EDIT_DISNABLE_PUBLIC_LINK);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_EDIT_ENABLE_PUBLIC_LINK);
    }

  }

  /**
   * Get public access link of public calendar
   *
   * @return returnText
   */
  public String getPublicAccessLink(String calendar) {
    info("Click on Edit link");
    if (evt.waitForAndGetElement(ELEMENT_CALENDAR_EDIT_DISNABLE_PUBLIC_LINK, 3000, 0) != null) {
      evt.click(ELEMENT_CALENDAR_EDIT_PUBLIC_LINK_BTN);
    }
    String returnText = evt.waitForAndGetElement(ELEMENT_CALENDAR_EDIT_FEED_LINK).getText();
    evt.click(ELEMENT_CALENDAR_EDIT_FEED_CLOSED_BTN);
    return returnText;

  }

  /**
   * Add Remote Calendar
   *
   * @param url url of the remote calendar
   * @param isChangeType true: if want to change type of remote calendar false: if
   *          want to keep default type of remote calendar
   * @param name the display name of remote calendar
   * @param des the description of remote calendar
   * @param type the mode radio of remote calendar
   */

  public void addRemoteCalender(String url, boolean isChangeType, String name, String des, int... type) {
    goToMenuFromMainCalendar(menuOfMainCalendar.REMOTECAL);
    if (isChangeType) {
      info("Select a type");
      if (type.length > 0)
        evt.check(ELEMENT_REMOTE_CALENDAR_ICALENDAR_RADIO, 2);
      else
        evt.check(ELEMENT_REMOTE_CALENDAR_CALDAV_RADIO, 2);
    }
    info("input url link to the field");
    if (!url.isEmpty())
      evt.type(ELEMENT_REMOTE_CALENDAR_URL, url, true);

    info("Click on Next button");
    evt.click(ELMENT_REMOTE_CALENDAR_NEXT_BTN);

    if (!name.isEmpty()) {
      info("Type a name");
      evt.type(ELEMENT_REMOTE_CALENDAR_NAME, name, true);
    }

    if (!des.isEmpty()) {
      info("Type a description");
      evt.type(ELEMENT_REMOTE_CALENDAR_DES, des, true);
    }

  }

  public void changeColorRemoteCalendar(String color) {
    if (!color.isEmpty()) {
      info("Click on color field");
      evt.click(ELEMENT_REMOTE_CALENDAR_COLOR_FIELD);
      evt.click(ELEMENT_REMOTE_CALENDAR_COLOR_SELECT.replace("${color}", color));
    }
  }

  /**
   * UnCheck user authentication checkbox
   */
  public void unCheckUserAuthentication() {
    info("UnCheck user Authentication checkbox");
    evt.uncheck(ELEMENT_REMOTE_CALENDAR_AUTHENTICATION_CHECKBOX);
    evt.waitForAndGetElement(ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_DISABLED);
  }

  /**
   * Check user authentication checkbox
   */
  public void checkUserAuthentication() {
    info("Check user Authentication checkbox");
    evt.check(ELEMENT_REMOTE_CALENDAR_AUTHENTICATION_CHECKBOX);
    evt.waitForElementNotPresent(ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_DISABLED);
  }

  /**
   * Input username and password of user authentication in remote calendar
   * 
   * @param username String
   * @param password String
   */
  public void addUserAuthentication(String username, String password) {
    if (!username.isEmpty()) {
      info("Input username");
      evt.type(ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_ENABLED, username, true);
    }
    if (!password.isEmpty()) {
      info("Input password");
      evt.type(ELEMENT_REMOTE_CALENDAR_PASSWORD_FIELD_ENABLED, password, true);
    }

  }

  /**
   * Save all changes of Remote calendar
   */
  public void saveRemoteCalendar() {
    info("Click on Save button");
    evt.click(ELEMENT_REMOTE_CALENDAR_SAVE_BTN);

  }

  /**
   * Cancel all changes of remote calendar
   */
  public void cancelRemoteCalendar() {
    info("Click on Cancel button");
    evt.click(ELEMENT_REMOTE_CALENDAR_CANCEL_BTN);

  }

  /**
   * Click on Back button of remote calendar
   */
  public void backRemoteCalendar() {
    info("Click on Back button");
    evt.click(ELEMENT_REMOTE_CALENDAR_BACK_BTN);

  }

  /**
   * quick search an event/task
   * 
   * @param name String
   */
  public void searchQuickEventTask(String name) {
    info("Search an event/task");
    if (!name.isEmpty()) {
      info("Input key search");
      evt.type(ELEMENT_EVENT_TASK_QUICK_SEARCH, name, true);
      evt.click(ELEMENT_EVENT_TASK_SEARCH_BTN);

      evt.waitForAndGetElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}", name));
    }
  }

  /**
   * Close search function
   */
  public void closeSearch() {
    info("click on Close search button");
    evt.click(ELEMENT_EVENT_TASK_CLOSE_SEARCH_BTN);

  }

  /**
   * Right click on Event/Tasks
   * 
   * @param name String
   * @param date String
   */
  public void rightClickEventTaskInMonth(String name, String date) {
    info("Right click on Event/Task");
    evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$date", testBase.getLastDayOfWeek("MMM dd yyyy"))
            .replace("$name", name));
    evt.waitForAndGetElement(ELEMENT_CONTEXT_MENU);
  }

  /**
   * Delete event/tasks in Month view by right click
   * 
   * @param name String
   * @param date String
   */
  public void deleteEventTaskInMonthView(String name, String date) {
    info("Delete event/tasks in Month view by right click");
    rightClickEventTaskInMonth(name, date);
    selectOptionByRightclickOnEvent(contextMenuEditEvenOption.DELETE);
    confirmDeleteEventTask();

  }

  /***
   * View detail of an event/task in List View
   * 
   * @param name String
   */
  public void viewDetailsEventTaskInList(String name) {
    info("Open list View");
    cHome.goToView(CalendarHomePage.selectViewOption.LIST);
    info("Click on the event/task");
    evt.click(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
    evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_LIST_VIEW.replace("$name", name));
  }

  /**
   * View large image of Task/Event in List View
   *
   * @param number the number of images as: =1 is first image;=2 is second
   *          image;...
   */
  public void viewLargeImageInList(String number, boolean verify) {
    info("Click on image");
    Dimension size_img = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL.replace("$number", number)).getSize();
    evt.mouseOver(ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_CONTAINER.replace("$number", number), true);
    evt.click(ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_VIEW.replace("$number", number));

    if (verify == true) {
      Dimension size_large_img = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LARGE_IMAGE).getSize();
      if (size_large_img.width > size_img.width && size_large_img.height > size_img.height) {
        if (size_large_img.width == 170 || size_large_img.height == 170)
          assert true;
        else
          assert false : "Large image is incorrect size";
      } else
        assert false : "Large image is not shown";
    }

    info("Large image is shown");
  }

  /**
   * Close large image of task/event in list view
   * 
   * @param verify String
   */
  public void closeViewLargeImageInList(boolean verify) {
    info("Click on Close button");
    evt.click(ELMEENT_EVENT_TASK_LARGE_IMAGE_CLOSE);
    if (verify == true) {
      Dimension size_large_img = testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_EVENT_TASK_LARGE_IMAGE).getSize();
      info("size_large_img.width:" + size_large_img.width);
      info("size_large_img.height:" + size_large_img.height);
      if (size_large_img.width == 0 && size_large_img.height == 0)
        assert true;
      else
        assert false : "Large image can not closed";
    }

  }

  /**
   * Click on large image of task/event in List view to download the image
   */
  public void downloadImageInList() {
    if (evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LARGE_IMAGE).getSize().width > 0) {
      info("Click on large image");
      evt.click(ELEMENT_EVENT_TASK_LARGE_IMAGE_DOWNLOAD);
    }

  }

  /**
   * Open Quick Add Event/Tasks by drag and drop row's time in Day view
   * 
   * @param sourceTimeHour String
   * @param targetTimeHour String
   */
  public void openQuickAddEventTaskInDayView(String sourceTimeHour, String targetTimeHour) {
    info("Click on Day view");
    cHome.goToView(CalendarHomePage.selectViewOption.DAY);
    if (sourceTimeHour != null || sourceTimeHour != "") {
      info("Drag and drop time row");
      evt.dragAndDropToObject(ELEMENT_EVENT_TASK_DATE_TIME_VALUE.replace("$time", sourceTimeHour + ":00"),
              ELEMENT_EVENT_TASK_DATE_TIME_VALUE.replace("$time", targetTimeHour + ":00"));
      evt.waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM, 3000, 0);
      info("The quick add form is shown");
    }

  }

  /**
   * Open Quick Add Event/Tasks by drag and drop the time in Week view
   *
   * @param sourceDateTime has the format:Tue Jun 23 2015 15:30
   * @param targetDateTime has the format:Tue Jun 23 2015 15:30
   */
  public void openQuickAddEventTaskInWeekView(String sourceDateTime, String targetDateTime) {
    info("Click on Day view");
    cHome.goToView(CalendarHomePage.selectViewOption.WEEK);
    if (sourceDateTime != null || targetDateTime != "") {
      info("Drag and drop time row");
      evt.dragAndDropToObject(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date", sourceDateTime + ":00"),
              ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date", targetDateTime + ":00"));
      evt.waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM, 3000, 0);
      info("The quick add form is shown");
    }

  }

  /**
   * HEAD Check accessibility of share calendar
   * 
   * @param isAccess true if user can view calendar
   * @param isEdit true if user can edit calendar
   * @param cal
   */
  public void checkAccessibilityOfShareCalendar(boolean isAccess, boolean isEdit, String cal) {
    if (isAccess) {
      evt.waitForAndGetElement(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", cal));
      openMenuOfCalendar(cal);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_REMOVE_SHARE_CALENDAR);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_REFRESH_MENU);
      if (isEdit) {
        evt.waitForAndGetElement(ELEMENT_CALENDAR_EDIT_MENU, testBase.getDefaultTimeout(), 1, 2);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_MENU);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_MENU);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_ADD_EVENT_MENU, testBase.getDefaultTimeout(), 1, 2);
      }
    } else {
      evt.waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", cal));
    }
  }

  /**
   * Check accessibility of calendar
   *
   * @param isAccess true if user can view calendar
   * @param isEdit true if user can edit calendar
   * @param cal String
   */
  public void checkAccessibilityOfCalendar(boolean isAccess, boolean isEdit, String cal) {
    if (isAccess) {
      evt.waitForAndGetElement(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", cal));
      openMenuOfCalendar(cal);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_REMOVE_MENU);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_REFRESH_MENU);
      if (isEdit) {
        evt.waitForAndGetElement(ELEMENT_CALENDAR_EDIT_MENU, testBase.getDefaultTimeout(), 1, 2);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_MENU);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_MENU);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_ADD_EVENT_MENU, testBase.getDefaultTimeout(), 1, 2);
      }
    } else {
      evt.waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", cal));
    }
  }

  /**
   * Share calendar to group
   * 
   * @param calendar String
   * @param group String
   * @param membership String
   * @param canEdit String
   */
  public void shareCalendarToGroup(String calendar, String group, String membership, boolean canEdit) {
    info("Share calendar to group user");
    executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
    evt.click(ELEMENT_CALENDAR_SELECT_MEMBERSHIP_ICON, 0, true);
    pPer.selectGroupMembership(group, membership);
    evt.click(ELEMENT_CALENDAR_SHARE_ADD_BUTTON, 0, true);
    if (canEdit) {
      evt.check(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", group), 2);
    } else {
      evt.uncheck(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", group), 2);
    }
    evt.click(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
  }

  /**
   * Check display of calendar
   * 
   * @param cal String
   * @param isPresent String
   */
  public void checkDisplayOfCalendar(String cal, boolean isPresent) {
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", cal));
    else
      evt.waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", cal));
  }

  /**
   * View list in calendar (Click icon +)
   */
  public enum menuOfMainCalendar {
    IMPORT, ADDCAL, REMOTECAL, ADDCATEGORY, CALSETTING
  }

  /**
   * View list in calendar (click on an calendar - Click icon *)
   */
  public enum menuOfCalendarOption {
    ADDTASK, ADDEVENT, EDIT, REMOVE, SHARE, IMPORT, EXPORT, REFRESH, COLOR
  }

  /**
   * right click on an even to show context menu
   */
  public enum contextMenuEditEvenOption {
    VIEW, EDIT, DELETE, DELETE_RECURRING, EXPORT;
  }

}