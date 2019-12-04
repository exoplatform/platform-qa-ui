package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_OK_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;

@Tag("sniff")
@Tag("calendar")
public class CalendarSettingTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  CalendarManagement calendarManagement;

  ManageLogInOut     manageLogInOut;

  EventManagement    eventManagement;

  CalendarHomePage   calendarHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    eventManagement = new EventManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");

  }

  /**
   * Case ID:115601. Test Case Name: Cancel settings form. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Step 1: Show calendar setting form
   * Step Description: Click on Calendar Settings icon Input Data: Expected
   * Outcome: Calendar Settings pop up appears. Step number: 2 Step Name: Step 2:
   * Choose default view type Step Description: - Change something in settings
   * form - Click Cancel Input Data: Expected Outcome: - The changes are not saved
   */
  @Test
  public void test01_CancelSettingsForm() {
    info("Test 1: Cancel settings form");

    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"))).waitUntil(Condition.visible, Configuration.timeout);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);

    calendarManagement.changeSettingCalendar("Month", null, null, null, null, null, null);
    calendarManagement.cancelSetting();
    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"))).waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Case ID:115602. Test Case Name: Setup a default View type to show calendar.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Show
   * calendar setting form Step Description: Click on Calendar Settings icon Input
   * Data: Expected Outcome: Calendar Settings pop up appears. Step number: 2 Step
   * Name: Step 2: Choose default view type Step Description: - Select one value
   * in View type - Click Save Input Data: Expected Outcome: - New setting for
   * calendar is saved - Whenever this user goes to calendar application, new
   * selected view type is shown as default
   */
  @Test
  public void test02_SetupADefaultViewTypeToShowCalendar() {
    info("Test 2: Setup a default View type to show calendar");

    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"))).waitUntil(Condition.visible, Configuration.timeout);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Month", null, null, null, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Month"))).waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Case ID:115603. Test Case Name: Setup date format to show calendar.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Show
   * calendar setting form Step Description: Click on Settings from Tool bar Input
   * Data: Expected Outcome: Calendar Settings pop up appears. Step number: 2 Step
   * Name: Step 2: Set date format Step Description: - Choose a format from list
   * for Date format - Click Save Input Data: Expected Outcome: New setting is
   * saved Step number: 3 Step Name: -Step 3: Check after re -setup date format
   * Step Description: - Click Add event/task - Check calendar in List View or
   * search result Input Data: Expected Outcome: All fields relate to date are
   * displayed as new selected format
   */
  @Test
  public void test03_SetupDateFormatToShowCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String formatDate = "MM-dd-yyyy";
    String defaultFormatDate = "mm/dd/yyyy";
    String dateFrom;
    String dateTo;

    info("Test 3: Setup date format to show calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week", null, formatDate.toLowerCase(), null, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.goToCalendarPage();
    info("verify dateformat of event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    dateFrom = waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE).getAttribute("format");
    dateTo = waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_TO_DATE).getAttribute("format");
    assert dateFrom.equals(formatDate);
    assert dateTo.equals(formatDate);
    eventManagement.inputDataEventInQuickForm(titleEvent, content, getDate(0, formatDate), getDate(0, formatDate), true);
    eventManagement.saveQuickAddEvent();
    info("verify dateformat of list view");
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(0, formatDate),
                                                          CalendarHomePage.selectViewOption.LIST,
                                                          CalendarHomePage.selectDayOption.ALLDAY);
    info("Delete data");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, formatDate));
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week", null, defaultFormatDate, null, null, null, null);
    calendarManagement.saveSetting();
  }

  /**
   * Case ID:115604. Test Case Name: Setup time format to show calendar.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Show
   * calendar setting form Step Description: Click on Settings from Tool bar Input
   * Data: Expected Outcome: Calendar Settings pop up appears.* Step number: 2
   * Step Name: Step 2: Set time format Step Description: - Choose a format from
   * list for Time format - Click Save Input Data: Expected Outcome: New setting
   * is saved Step number: 3 Step Name: Step 3: Check after re -setup time format
   * Step Description: - Click Add event/task - Check calendar in List View or
   * search result Input Data: Expected Outcome: All fields relate to time are
   * displayed as new selected format
   */
  @Test
  public void test04_SetupTimeFormatToShowCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String formatTime = "AM/PM";
    String defaultFormatTime = "24 Hours";
    String defaultFormatDate = "MM-dd-yyyy";
    String timeFrom;
    String timeTo;
    String timeEvent;
    info("Test 4: Setup time format to show calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);

    calendarManagement.changeSettingCalendar("Week", null, defaultFormatDate.toLowerCase(), formatTime, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.goToCalendarPage();

    info("verify dateformat of event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    timeFrom = waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_FROM_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
    timeTo = waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_TO_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
    assert (timeFrom.contains("AM") || timeFrom.contains("PM"));
    assert (timeTo.contains("AM") || timeTo.contains("PM"));
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate + " HH"),
                                              getDate(0, defaultFormatDate + "HH"),
                                              false);
    eventManagement.saveQuickAddEvent();

    info("verify dateformat of list view - event");
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    if ($(ELEMENT_TOTAL_PAGE).is(Condition.visible)) {
      click(ELEMENT_ANY_PAGE.replace("$page", "1"));
      while ((waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent), 5000, 0) == null)
          && !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
        click(ELEMENT_NEXT_PAGE);
      waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent));
      timeEvent =
                waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", titleEvent)
                                                                                   .replace("$date",
                                                                                            getDate(0, defaultFormatDate)))
                                                                                                                           .getText();
      click(ELEMENT_ANY_PAGE.replace("$page", "1"));
    } else {
      $(byXpath(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent))).waitUntil(Condition.visible, Configuration.timeout);
      timeEvent =
                $(byXpath(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", titleEvent)
                                                                        .replace("$date", getDate(0, defaultFormatDate))))
                                                                                                                          .getText();
    }
    assert (timeEvent.contains("AM") || timeFrom.contains("PM"));
    info("Delete data");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, defaultFormatDate));
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week", null, defaultFormatDate.toLowerCase(), defaultFormatTime, null, null, null);
    calendarManagement.saveSetting();
  }

  /**
   * Case ID:115605. Test Case Name: Setup Time zone to show calendar.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Show
   * calendar setting form Step Description: Click on Settings from Tool bar Input
   * Data: Expected Outcome: Calendar Settings pop up appears. Step number: 2 Step
   * Name: Step 2: Change calendar settings Step Description: - Choose value from
   * list for Time Zone - Click Save Input Data: Expected Outcome: New setting is
   * saved Step number: 3 Step Name: Step 3: Check after change calendar setting
   * Step Description: - Click Add event/task Input Data: Expected Outcome: Time
   * Zone are displayed as selected
   */
  @Test
  public void test05_SetupTimeZoneToShowCalendar() {
    String defaultFormatTime = "24 Hours";
    String defaultFormatDate = "MM-dd-yyyy";
    String defaultTimeZone = "(GMT +01:00) Africa/Tunis";
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Test 5: Setup Time zone to show calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week",
                                             defaultTimeZone,
                                             defaultFormatDate.toLowerCase(),
                                             defaultFormatTime,
                                             null,
                                             null,
                                             null);
    calendarManagement.saveSetting();
    info("verify dateformat of event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate + " HH"),
                                              getDate(0, defaultFormatDate + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    info("Delete data");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
  }

  /**
   * Case ID:115606. Test Case Name: Setup start day for week. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Step 1: Show calendar setting form
   * Step Description: Click on Settings from Tool bar Input Data: Expected
   * Outcome: Calendar Settings pop up appears.* Step number: 2 Step Name: Step 2:
   * Change calendar settings Step Description: - Select a day from list form Week
   * start on - Click Save Input Data: Expected Outcome: New setting is saved Step
   * number: 3 Step Name: Step 3: Check after change calendar setting Step
   * Description: Select Week View Input Data: Expected Outcome: The start date of
   * week on both mini calendar and main calendar is new selected date
   */
  @Test
  public void test06_SetupStartDayForWeek() {
    String defaultFormatTime = "24 Hours";
    String defaultFormatDate = "MM/dd/yyyy";
    String defaultTimeZone = "(GMT +01:00) Africa/Tunis";
    String day = "Tuesday";
    String defaultDay = "Monday";
    String dayBar = "Tue";

    info("Test 6: Setup start day for week");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);

    calendarManagement.changeSettingCalendar("Week",
                                             defaultTimeZone,
                                             defaultFormatDate.toLowerCase(),
                                             defaultFormatTime,
                                             day,
                                             null,
                                             null);
    calendarManagement.saveSetting();

    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    $(byXpath(ELEMENT_WEEK_VIEW_BAR_TIME.replace("$index", "2"))).getText().contains(dayBar);

    info("Reset data");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week",
                                             defaultTimeZone,
                                             defaultFormatDate.toLowerCase(),
                                             defaultFormatTime,
                                             defaultDay,
                                             null,
                                             null);
    calendarManagement.saveSetting();
  }

  /**
   * Case ID:115607. Test Case Name: Setup working time for calendar.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Open
   * Settings form Step Description: Click on Settings from Tool bar Input Data:
   * Expected Outcome: Calendar Settings pop up appears.* Step number: 2 Step
   * Name: Step 2: Show form to setup working time Step Description: - Check Show
   * working times option Input Data: Expected Outcome: Start and End time fields
   * are shown to choose* Step number: 3 Step Name: Step 3: Setup working time
   * Step Description: - Select time for Start and End - Click Save Input Data:
   * Expected Outcome: Change is saved Step number: 4 Step Name: Step 4: Check
   * after changing calendar setting Step Description: Check working pane Input
   * Data: Expected Outcome: Working pane is shown as normal from selected Start
   * to End time, other time in day will be in Gray
   */
  @Test
  public void test07_SetupWorkingTimeForCalendar() {
    String defaultFormatTime = "24 Hours";
    String defaultFormatDate = "MM/dd/yyyy";
    String defaultTimeZone = "(GMT +01:00) Africa/Tunis";
    String defaultDay = "Monday";
    String beginTime = "01:00";
    String endTime = "23:59";
    String color = "rgba(240, 240, 240, 1)";

    info("Test 7: Setup working time for calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);

    calendarManagement.changeSettingCalendar("Week",
                                             defaultTimeZone,
                                             defaultFormatDate.toLowerCase(),
                                             defaultFormatTime,
                                             defaultDay,
                                             true,
                                             null);
    select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_BEGIN_TIME, beginTime, 2);
    select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_END_TIME, endTime, 2);
    calendarManagement.saveSetting();

    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    String cell = ELEMENT_CELL_TO_WORKING_PANEL.replace("$date", getDate(0, "MMM dd yyyy")).replace("$time", "00:00");
    refresh();
    String backgroundColor = $(byXpath(cell)).getCssValue("background-color");
    info(backgroundColor);
    assertEquals(color, backgroundColor);

    info("Reset data");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week",
                                             defaultTimeZone,
                                             defaultFormatDate.toLowerCase(),
                                             defaultFormatTime,
                                             defaultDay,
                                             false,
                                             null);
    calendarManagement.saveSetting();
  }

  /**
   * Case ID:115608. Test Case Name: Set invitation option. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Step 1: Show calendar setting form
   * Step Description: Click on Settings from Tool bar Input Data: Expected
   * Outcome: Calendar Settings pop up appears. Step number: 2 Step Name: Step 2:
   * Finish setting Step Description: - Click Always/Never/Ask radio - Click Save
   * Input Data: Expected Outcome: Setting calendar was set Step number: 3 Step
   * Name: Step 3: Check invitation option while adding event Step Description: -
   * Click [Event] - Go to Participant tab - Check Send invitation option Input
   * Data: Expected Outcome: - Send invitation option is correct as step 2
   */
  @Test
  public void test08_SetInvitationOption() {
    String defaultFormatTime = "24 Hours";
    String defaultFormatDate = "MM-dd-yyyy";
    String defaultTimeZone = "(GMT +01:00) Africa/Tunis";
    String defaultDay = "Monday";

    info("Test 8: Set invitation option");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week",
                                             defaultTimeZone,
                                             defaultFormatDate.toLowerCase(),
                                             defaultFormatTime,
                                             defaultDay,
                                             false,
                                             PlatformBase.selectInvitationOption.ALWAYS);
    calendarManagement.saveSetting();
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    $(ELEMENT_EVENT_PARTICIPANTS_TAB).click();
    eventManagement.cancelAddEditDetailEvent();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar("Week",
                                             defaultTimeZone,
                                             defaultFormatDate.toLowerCase(),
                                             defaultFormatTime,
                                             defaultDay,
                                             false,
                                             PlatformBase.selectInvitationOption.NEVER);
    calendarManagement.saveSetting();
  }

  /**
   * Case ID:115656. Test Case Name: Displayed calendar. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Step 1: Show calendar setting form
   * Step Description: - Click Calendar Action icon - Click Settings option on
   * menuOr - Click Wheel icon beside Search box on the top right Input Data:
   * Expected Outcome: Calendar Settings pop up appears.There are 3 tabs in this
   * pop up: Settings, Displayed calendar and Feed Step number: 2 Step Name: Step
   * 2: Check Displayed Calendar tab Step Description: In Calendar settings form:
   * - Select Display Calendars tab Input Data: Expected Outcome: - List all
   * existing calendar using the exact same list as on the Calendar view including
   * Personal calendar, Group calendar, Shared calendar - Calendars that showing
   * are checked Step number: 3 Step Name: - Step Description: Step 3: Setup to
   * show/hide specific calendar Input Data: - Unchecked the calendar(s) you don't
   * want to show - Click Save Expected Outcome: - Change is saved - Unchecked
   * calendar(s) is not shown in left pane and its event/task are also not shown
   * in working pane or list
   */
  @Test
  public void test09_DisplayedCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String group = "Development";
    String defaultFormatDate = "MM-dd-yyyy";
    String fullName = PLFData.DATA_NAME_USER1;
    info("create data test");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate + " HH"),
                                              getDate(0, defaultFormatDate + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 9: Displayed calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.goToDisplayCalendarTab();
    $(byXpath(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", fullName))).should(Condition.visible);
    $(byXpath(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX.replace("$name", group))).should(Condition.visible);
    calendarManagement.showHideCalendar(fullName, false);
    calendarManagement.showHideCalendar(group, false);
    calendarManagement.saveSetting();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", fullName))).shouldNot(Condition.visible);
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", group))).shouldNot(Condition.visible);
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    info("Reset data");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.goToDisplayCalendarTab();
    calendarManagement.showHideCalendar(fullName, true);
    calendarManagement.showHideCalendar(group, true);
    calendarManagement.saveSetting();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", fullName))).should(Condition.visible);
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", group))).should(Condition.visible);
  }

  /**
   * Case ID:115657. Test Case Name: Add new feed. Pre-Condition: Calendar to add
   * feed must have task/ event Post-Condition: Step Number: 1 Step Name: Step 1:
   * Show calendar setting form Step Description: - Click Calendar Action icon -
   * Click Settings option on menuOr - Click Wheel icon beside Search box on the
   * top right Input Data: Expected Outcome: Calendar Settings pop up
   * appears.There are 3 tabs in this pop up: Settings, Displayed calendar and
   * Feed Step number: 2 Step Name: Step 2: Select Feed tab Step Description: In
   * Calendar settings form: - Select Feed tab - Click Add Input Data: Expected
   * Outcome: - Feed tab is shown - Form to add new feed is shown Step number: 3
   * Step Name: Step 3: Add new feed Step Description: - Put value in Name field -
   * Select calendar by + Click Add more box+ Then choose a calendar in drop down
   * menu+ Click Add calendar (+ icon) - Click Save button Input Data: Expected
   * Outcome: New feed is created successfully Step number: 4 Step Name: Step
   * Description: Step 4: Edit Feed Input Data: - Click Edit icon corresponding to
   * Feed above - Change some values - Save Expected Outcome: Feed is edited
   * successfully with new values Step number: 2 Step Name: Step Description: Step
   * 2: Delete Feed Input Data: - Click Delete icon corresponding to Feed above -
   * Click OK to confirmation message Expected Outcome: Feed is deleted
   * successfully
   */
  @Test
  public void test10_AddNewFeed() {
    String name = "name" + getRandomNumber();

    String calendar = PLFData.DATA_NAME_USER1;

    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM-dd-yyyy";

    info("create data test");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate + " HH"),
                                              getDate(0, defaultFormatDate + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 10 Add new feed");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);

    calendarManagement.goToFeedTab();
    $(ELEMENT_FEED_TAB_SAVE_BUTTON).click();
    $(ELEMENT_FEED_EDIT_FEED_FORM).waitUntil(Condition.visible, Configuration.timeout);

    calendarManagement.addEditFeed(name, "", calendar);
    $(ELEMENT_FEED_EDIT_FEED_SAVE_FORM).click();
    $(byText(ELEMENT_FEED_CONFIRM_ADD_FEED.replace("$name", name))).waitUntil(Condition.appears, Configuration.timeout);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", name))).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_OK_BUTTON).click();

    info("Test 12 Delete a feed");

    calendarManagement.deleteFeed(name, true);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", name))).waitUntil(Condition.not(Condition.visible),
                                                                                   Configuration.timeout);
    calendarManagement.saveSetting();

    info("Reset data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
  }

  @Test
  public void test1_EditNewFeed() {
    String name = "name" + getRandomNumber();
    String newName = "newName" + getRandomNumber();

    String calendar = PLFData.DATA_NAME_USER1;

    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM-dd-yyyy";

    info("create data test");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate + " HH"),
                                              getDate(0, defaultFormatDate + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 10 Add new feed");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);

    calendarManagement.goToFeedTab();
    $(ELEMENT_FEED_TAB_SAVE_BUTTON).click();
    $(ELEMENT_FEED_EDIT_FEED_FORM).waitUntil(Condition.visible, Configuration.timeout);

    calendarManagement.addEditFeed(name, "", calendar);
    $(ELEMENT_FEED_EDIT_FEED_SAVE_FORM).click();
    $(byText(ELEMENT_FEED_CONFIRM_ADD_FEED.replace("$name", name))).waitUntil(Condition.appears, Configuration.timeout);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", name))).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_OK_BUTTON).click();

    info("Test 11 Edit a feed");

    $(byXpath(ELEMENT_FEED_LIST_ITEM_EDIT_BUTTON.replace("$name", name))).click();
    calendarManagement.addEditFeed(newName, "", "");
    $(ELEMENT_FEED_EDIT_FEED_SAVE_FORM).click();
    $(byText(ELEMENT_FEED_CONFIRM_ADD_FEED.replace("$name", newName))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", newName))).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_OK_BUTTON).click();
    info("Test 12 Delete a feed");

    calendarManagement.deleteFeed(newName, true);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", newName))).waitUntil(Condition.not(Condition.visible),
                                                                                      Configuration.timeout);
    calendarManagement.saveSetting();

    info("Reset data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
  }

  @Test
  public void test11_DeleteNewFeed() {
    String name = "name" + getRandomNumber();

    String calendar = PLFData.DATA_NAME_USER1;

    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM-dd-yyyy";

    info("create data test");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate + " HH"),
                                              getDate(0, defaultFormatDate + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 10 Add new feed");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);

    calendarManagement.goToFeedTab();
    $(ELEMENT_FEED_TAB_SAVE_BUTTON).click();
    $(ELEMENT_FEED_EDIT_FEED_FORM).waitUntil(Condition.visible, Configuration.timeout);

    calendarManagement.addEditFeed(name, "", calendar);
    $(ELEMENT_FEED_EDIT_FEED_SAVE_FORM).click();
    $(byText(ELEMENT_FEED_CONFIRM_ADD_FEED.replace("$name", name))).waitUntil(Condition.appears, Configuration.timeout);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", name))).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_OK_BUTTON).click();

    info("Test 12 Delete a feed");

    calendarManagement.deleteFeed(name, true);
    $(byXpath(ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", name))).waitUntil(Condition.not(Condition.visible),
                                                                                   Configuration.timeout);
    calendarManagement.saveSetting();

    info("Reset data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
  }
}
