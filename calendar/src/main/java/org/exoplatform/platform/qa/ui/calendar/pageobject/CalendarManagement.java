package org.exoplatform.platform.qa.ui.calendar.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_USER_CLOSE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
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

    $(ELEMENT_CALENDAR_MENU_ACTIONS_ICON).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_CALENDAR_MENU).waitUntil(Condition.visible, Configuration.collectionsTimeout);
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
        $(ELEMENT_ADD_EVENT_CATEGORY_FORM).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
        break;
      case CALSETTING:
        info("Go to calendar setting");

        evt.waitForAndGetElement(ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING, testBase.getDefaultTimeout(), 1);
        evt.click(ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING, 0, true);
        evt.waitForAndGetElement(ELEMENT_CALENDAR_SETTING_FORM, testBase.getDefaultTimeout(), 1);
        sleep(Configuration.collectionsTimeout);
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
   * Save add calendar
   */
  public void saveAddCalendar() {
    info("Save add calendar");
    $(ELEMENT_CALENDAR_ADD_SAVE_BUTTON).click();

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
    $(byXpath(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}", newCategory))).exists();
    $(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE).click();
  }

  /**
   * Open menu an a calendar
   *
   * @param calendar name of calendar
   */
  public void openMenuOfCalendar(String calendar) {
    info("Open menu of a calendar");
    ELEMENT_LIST_CALENDAR.find(byText(calendar)).waitUntil(Condition.appears, Configuration.collectionsTimeout)
            .hover()
            .parent()
            .parent()
            .waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs)
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
      $(ELEMENT_CALENDAR_ADD_TASK_MENU).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      $(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
      break;
    case ADDEVENT:
      $(ELEMENT_CALENDAR_ADD_EVENT_MENU).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      $(ELEMENT_EVENT_DRAWER).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
      break;
    case EDIT:
      $(ELEMENT_CALENDAR_EDIT_MENU).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_CALENDAR_ADD_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case REMOVE:
      $(ELEMENT_CALENDAR_REMOVE_MENU).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_CALENDAR_REMOVE_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case SHARE:
      $(ELEMENT_CALENDAR_SHARE_MENU).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_CALENDAR_SHARE_FORM).waitUntil(Condition.visible, Configuration.timeout);
      break;
    case IMPORT:
      $(ELEMENT_CALENDAR_IMPORT_MENU).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_CALENDAR_IMPORT_POPUP_FORM).waitUntil(Condition.visible, Configuration.timeout);
      break;
    case EXPORT:
      $(ELEMENT_CALENDAR_EXPORT_MENU).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_CALENDAR_EXPORT_POPUP_FORM).waitUntil(Condition.visible, Configuration.timeout);
      break;
    case REFRESH:
      $(ELEMENT_CALENDAR_REFRESH_MENU).waitUntil(Condition.visible,Configuration.timeout).click();
      break;
    case COLOR:
      info("Select a color");
      $(ELEMENT_CALENDAR_MENU_ACTIONS_COLOR).waitUntil(Condition.visible,Configuration.timeout).click();
      break;
    default:
      $(ELEMENT_CALENDAR_ADD_TASK_MENU).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM).waitUntil(Condition.visible, Configuration.timeout);
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
    goToMenuFromMainCalendar(menuOfMainCalendar.IMPORT);
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
        $(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX).parent().click();
        break;
      case ALWAYS:
        $(ELEMENT_CALENDAR_SETTING_ALWAYS_SEND_INVITE_CHECKBOX).parent().click();
        break;
      case ASK:
        $(ELEMENT_CALENDAR_SETTING_ASK_SEND_INVITE_CHECKBOX).click();
        break;
      default:
        $(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX).click();
        break;


      }
    }
  }

  /**
   * save setting form
   */
  public void saveSetting() {
    sleep(2000);
    evt.click(ELEMENT_SETTING_FORM_SAVE_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CALENDAR_SETTING_FORM);
  }

  /**
   * cancel setting form
   */
  public void cancelSetting() {
    $(ELEMENT_SETTING_FORM_CANCEL_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CALENDAR_SETTING_FORM).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

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
        ELEMENT_POUPUP_LIST_EVENT.find(byText(name)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).doubleClick();
    }
    ELEMENT_EVENT_DRAWER.waitUntil(Condition.appears, Configuration.timeout);
    info("The edit form is shown");
  }

  /**
   * Remove an event or a task in any views by right Click
   * 
   * @param name String
   */
  public void deleteTaskEvent(String name) {
    info("Right click on an Event/Task");
    $(byText(name)).contextClick();
    selectOptionByRightclickOnEvent(contextMenuEditEvenOption.DELETE);
    $(ELEMENT_YES_BUTTON).click();
    $(byText(name)).shouldNot(Condition.exist);
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