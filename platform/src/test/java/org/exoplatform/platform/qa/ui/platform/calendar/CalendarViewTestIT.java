package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("sniff")
@Tag("calendar")
public class CalendarViewTestIT extends Base {
  HomePagePlatform homePagePlatform;

  CalendarHomePage calendarHomePage;

  ManageLogInOut   manageLogInOut;

  EventManagement  eventManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    calendarHomePage = new CalendarHomePage(this);
    homePagePlatform = new HomePagePlatform(this);
    eventManagement = new EventManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * Case ID:115595. Test Case Name: Check Today view. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Step 1: Add task/ event Step
   * Description: - Go to calendar - Add new task/event Input Data: Expected
   * Outcome: Task/ event are created Step number: 2 Step Name: Step 2: Select
   * view Step Description: - Select view (day/week/month/list/work week) which
   * has not the current day Input Data: Expected Outcome: - Calendar is displayed
   * with the selected view(day/week/month/list/work week) Step number: 3 Step
   * Name: Step 3: Check displaying of task and event. Step Description: - Click
   * on Today on the main bar Input Data: Expected Outcome: - Go to the current
   * view which has the current day (today view). Mini calendar is updated to the
   * week has current day
   */

  @Test
  public void test01_CheckTodayView() {
    info("Test 1: Check Today view");
    String titleEventCur = "titleEventCur" + getRandomNumber();
    String titleEventNext = "titleEventNext" + getRandomNumber();
    String titleEventPre = "titleEventPre" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventCur,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventNext,
                                              content,
                                              getDate(1, defaultFormatDate),
                                              getDate(1, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventPre,
                                              content,
                                              getDate(-1, defaultFormatDate),
                                              getDate(-1, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.DAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.verifyIsPresentEventTask(titleEventCur,
                                              CalendarHomePage.selectViewOption.DAY,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNext,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPre,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.deleteEventTask(titleEventCur,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventNext,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(1, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventPre,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(-1, "MMM dd yyyy"));
  }

  /**
   * Case ID:115596. Test Case Name: Check displaying added task/event in work
   * week view. Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1:
   * Add task/ event Step Description: - Go to calendar - Add new task/event Input
   * Data: Expected Outcome: Task/ event are created Step number: 2 Step Name:
   * Step 2: Check displaying of task and event. Step Description: - Click on Work
   * Week on the main bar Input Data: Expected Outcome: Event/ task is displayed
   * in Work Week view Step number: 3 Step Name: Step 3: Check next week/previous
   * week Step Description: - Click next week/previous week icon Input Data:
   * Expected Outcome: - Next week/previous week is displayed correctly - Mini
   * calendar is updated also
   */
  @Test
  public void test02_CheckDisplayingAddedTaskeventInWorkWeekView() {
    info("Test 2: Check displaying added task/event in work week view");
    String titleEventCur = "titleEventCur" + getRandomNumber();
    String titleEventNextWeek = "titleEventNextWeek" + getRandomNumber();
    String titleEventPreWeek = "titleEventPreWeek" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";

    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventCur,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventNextWeek,
                                              content,
                                              getDate(7, defaultFormatDate),
                                              getDate(7, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventPreWeek,
                                              content,
                                              getDate(-7, defaultFormatDate),
                                              getDate(-7, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WORKWEEK);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.verifyIsPresentEventTask(titleEventCur,
                                              CalendarHomePage.selectViewOption.WORKWEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNextWeek,
                                                 CalendarHomePage.selectViewOption.WORKWEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPreWeek,
                                                 CalendarHomePage.selectViewOption.WORKWEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
    calendarHomePage.verifyIsPresentEventTask(titleEventNextWeek,
                                              CalendarHomePage.selectViewOption.WORKWEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPreWeek,
                                                 CalendarHomePage.selectViewOption.WORKWEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.WORKWEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    $(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.WORKWEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNextWeek,
                                                 CalendarHomePage.selectViewOption.WORKWEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventPreWeek,
                                              CalendarHomePage.selectViewOption.WORKWEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    $(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
    calendarHomePage.deleteEventTask(titleEventNextWeek,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(7, "MMM dd yyyy"));
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.deleteEventTask(titleEventCur,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    $(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
    calendarHomePage.deleteEventTask(titleEventPreWeek,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(-7, "MMM dd yyyy"));

  }

  /**
   * Case ID:115597. Test Case Name: Check displaying added task/event in list
   * view. Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Add
   * task/ event Step Description: - Go to calendar - Add new task/event Input
   * Data: Expected Outcome: Task/ event are created Step number: 2 Step Name:
   * Step 2: Check displaying of task and event. Step Description: - Click on List
   * on the main bar Input Data: Expected Outcome: Event/ task is displayed in
   * List view Step number: 3 Step Name: Step 3: Check next week/previous day Step
   * Description: - Click on next week/previous day icon Input Data: Expected
   * Outcome: - Next week/previous day is displayed correctly - Mini calendar is
   * updated also
   */
  @Test
  public void test03_CheckDisplayingAddedTaskeventInListView() {
    info("Test 3: Check displaying added task/event in list view");
    info("Test 1: Check Today view");
    String titleEventCur = "titleEventCur" + getRandomNumber();
    String titleEventNext = "titleEventNext" + getRandomNumber();
    String titleEventPre = "titleEventPre" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventCur,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventNext,
                                              content,
                                              getDate(1, defaultFormatDate),
                                              getDate(1, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventPre,
                                              content,
                                              getDate(-1, defaultFormatDate),
                                              getDate(-1, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.verifyIsPresentEventTask(titleEventCur,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNext,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPre,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventNext,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPre,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    $(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNext,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventPre,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.deleteEventTask(titleEventCur,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventNext,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(1, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventPre,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(-1, "MMM dd yyyy"));
  }

  /**
   * Case ID:115598. Test Case Name: Check displaying added task/event in month
   * view. Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Add
   * task/ event Step Description: - Go to calendar - Add new task/event Input
   * Data: Expected Outcome: Task/ event are created Step number: 2 Step Name:
   * Step 2: Check displaying of task and event. Step Description: - Click on
   * Month on the main bar Input Data: Expected Outcome: Event/ task is displayed
   * in Month view Step number: 3 Step Name: Step 3: Check next month/previous
   * month Step Description: - Click on next month/previous month icon Input Data:
   * Expected Outcome: - Next month/previous month is displayed correctly - Mini
   * calendar is updated also
   */
  @Test
  public void test04_CheckDisplayingAddedTaskeventInMonthView() {
    String titleEventCur = "titleEventCur" + getRandomNumber();
    String titleEventNextMonth = "titleEventNextMonth" + getRandomNumber();
    String titleEventPreMonth = "titleEventPreMonth" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";
    info("Test 4: Check displaying added task/event in month view");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventCur,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventNextMonth,
                                              content,
                                              getDate(31, defaultFormatDate),
                                              getDate(31, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventPreMonth,
                                              content,
                                              getDate(-31, defaultFormatDate),
                                              getDate(-31, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToCalendarPage();
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.MONTH);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEventCur,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNextMonth,
                                                 CalendarHomePage.selectViewOption.MONTH,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPreMonth,
                                                 CalendarHomePage.selectViewOption.MONTH,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEventCur,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventNextMonth,
                                              CalendarHomePage.selectViewOption.MONTH,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPreMonth,
                                                 CalendarHomePage.selectViewOption.MONTH,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    $(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEventCur,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNextMonth,
                                                 CalendarHomePage.selectViewOption.MONTH,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventPreMonth,
                                              CalendarHomePage.selectViewOption.MONTH,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.deleteEventTask(titleEventCur,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.MONTH);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    $(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
    calendarHomePage.deleteEventTask(titleEventNextMonth,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     null);
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.MONTH);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    $(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
    calendarHomePage.deleteEventTask(titleEventPreMonth,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     null);
  }

  /**
   * Case ID:115599. Test Case Name: Check displaying added task/event in week
   * view. Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Add
   * task/ event Step Description: - Go to calendar - Add new task/event Input
   * Data: Expected Outcome: Task/ event are created Step number: 2 Step Name:
   * Step 2: Check displaying of task and event. Step Description: - Click on Week
   * on the main bar Input Data: Expected Outcome: Event/ task is displayed in
   * Week view Step number: 3 Step Name: Step 3: Check next week/previous week
   * Step Description: - Click next week/previous week icon Input Data: Expected
   * Outcome: - Next week/previous week is displayed correctly - Mini calendar is
   * updated also
   */
  @Test
  public void test05_CheckDisplayingAddedTaskeventInWeekView() {
    info("Test 5: Check displaying added task/event in week view");
    String titleEventCur = "titleEventCur" + getRandomNumber();
    String titleEventNextWeek = "titleEventNextWeek" + getRandomNumber();
    String titleEventPreWeek = "titleEventPreWeek" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventCur,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventNextWeek,
                                              content,
                                              getDate(7, defaultFormatDate),
                                              getDate(7, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventPreWeek,
                                              content,
                                              getDate(-7, defaultFormatDate),
                                              getDate(-7, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    click(ELEMENT_TODAY_ACTION_BAR);
    calendarHomePage.verifyIsPresentEventTask(titleEventCur,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNextWeek,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPreWeek,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    click(ELEMENT_NEXT_BUTTON_ANY_VIEW);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventNextWeek,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPreWeek,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    click(ELEMENT_TODAY_ACTION_BAR);
    click(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNextWeek,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventPreWeek,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    $(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
    calendarHomePage.deleteEventTask(titleEventNextWeek,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(7, "MMM dd yyyy"));
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.deleteEventTask(titleEventCur,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    $(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
    calendarHomePage.deleteEventTask(titleEventPreWeek,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(-7, "MMM dd yyyy"));
  }

  /**
   * Case ID:115600. Test Case Name: Check displaying added task/event in day
   * view. Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Add
   * task/ event Step Description: - Go to calendar - Add new task/event Input
   * Data: Expected Outcome: Task/ event are created Step number: 2 Step Name:
   * Step 2: Check displaying of task and event. Step Description: - Click on Day
   * on the main bar Input Data: Expected Outcome: Event/ task is displayed in Day
   * view Step number: 3 Step Name: Step 3: Check next day/previous day Step
   * Description: - Click next day/ previous day icon Input Data: Expected
   * Outcome: - Next day/previous day is displayed correctly - Mini calendar is
   * updated also
   */
  @Test
  public void test06_CheckDisplayingAddedTaskeventInDayView() {
    info("Test 6: Check displaying added task/event in day view");
    String titleEventCur = "titleEventCur" + getRandomNumber();
    String titleEventNext = "titleEventNext" + getRandomNumber();
    String titleEventPre = "titleEventPre" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventCur,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventNext,
                                              content,
                                              getDate(1, defaultFormatDate),
                                              getDate(1, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventPre,
                                              content,
                                              getDate(-1, defaultFormatDate),
                                              getDate(-1, defaultFormatDate),
                                              true);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.DAY);
    click(ELEMENT_TODAY_ACTION_BAR);
    calendarHomePage.verifyIsPresentEventTask(titleEventCur,
                                              CalendarHomePage.selectViewOption.DAY,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNext,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPre,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    click(ELEMENT_NEXT_BUTTON_ANY_VIEW);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventNext,
                                              CalendarHomePage.selectViewOption.DAY,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventPre,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    click(ELEMENT_TODAY_ACTION_BAR);
    click(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCur,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventNext,
                                                 CalendarHomePage.selectViewOption.DAY,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventPre,
                                              CalendarHomePage.selectViewOption.DAY,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.deleteEventTask(titleEventPre,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(-1, "MMM dd yyyy"));
    click(ELEMENT_TODAY_ACTION_BAR);
    calendarHomePage.deleteEventTask(titleEventCur,
                                     CalendarHomePage.selectViewOption.DAY,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    click(ELEMENT_NEXT_BUTTON_ANY_VIEW);
    calendarHomePage.deleteEventTask(titleEventNext,
                                     CalendarHomePage.selectViewOption.DAY,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(1, "MMM dd yyyy"));
  }

  /**
   * Case ID:115647. Test Case Name: Check category filter in Week view.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Create some
   * event/task Step Description: - goto Calendar and click [Week] to show week
   * view - Create some event/tasks belong to some different category like
   * anniversary, holiday, meeting, call - in Categories drop -down box, choose in
   * turn these categories Input Data: Expected Outcome: - Events/Tasks belong to
   * the category are shown in main pane - When selecting [All], all the Events
   * are shown
   */
  @Test
  public void test07_CheckCategoryFilterInWeekView() {
    info("Test 7: Check category filter in Week view");
    String titleEventMeeting = "titleEventMeeting" + getRandomNumber();
    String titleEventCall = "titleEventCall" + getRandomNumber();
    String titleEventClient = "titleEventClient" + getRandomNumber();
    String titleEventHoliday = "titleEventHoliday" + getRandomNumber();
    String titleEventAnni = "titleEventAnni" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";

    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventMeeting,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true,
                                              null,
                                              "Meeting");
    eventManagement.saveQuickAddEvent();

    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventCall,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true,
                                              null,
                                              "Calls");
    eventManagement.saveQuickAddEvent();

    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventClient,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true,
                                              null,
                                              "Clients");
    eventManagement.saveQuickAddEvent();

    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventHoliday,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true,
                                              null,
                                              "Holiday");
    eventManagement.saveQuickAddEvent();

    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEventAnni,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              true,
                                              null,
                                              "Anniversary");
    eventManagement.saveQuickAddEvent();
    click(ELEMENT_TODAY_ACTION_BAR);
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.MEETING);

    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventMeeting,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);

    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.ANNIVERSARY);

    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventAnni,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);

    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.CALL);

    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventCall,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);

    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.HOLIDAY);

    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventHoliday,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);

    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.CLIENT);

    calendarHomePage.verifyIsPresentEventTask(titleEventClient,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.ALLDAY);

    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.ALL);

    calendarHomePage.verifyIsPresentEventTask(titleEventClient,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventHoliday,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventCall,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventAnni,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventMeeting,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);

    calendarHomePage.deleteEventTask(titleEventMeeting,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventCall,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventClient,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventHoliday,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventAnni,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));

  }

  /**
   * Case ID:115648. Test Case Name: Check category filter in List view.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Create some
   * events/tasks Step Description: - goto Calendar and click [List] to show List
   * view - Create some event/tasks belong to some different category like
   * anniversary, holiday, meeting, call - in Categories drop -down box, choose in
   * turn these categories Input Data: Expected Outcome: - Events/Tasks belong to
   * the category are shown in main pane - When selecting [All], all the Events
   * are shown
   */
  @Test
  public void test08_CheckCategoryFilterInListView() {
    info("Test 8: Check category filter in List view");
    String titleEventMeeting = "titleEventMeeting" + getRandomNumber();
    String titleEventCall = "titleEventCall" + getRandomNumber();
    String titleEventClient = "titleEventClient" + getRandomNumber();
    String titleEventHoliday = "titleEventHoliday" + getRandomNumber();
    String titleEventAnni = "titleEventAnni" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";

    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    ELEMENT_EVENT_TITLE_DRAWER.waitUntil(Condition.visible, Configuration.timeout).setValue(titleEventMeeting);
    ELEMENT_EVENT_DESCRIPTION.setValue(content);
    ELEMENT_EVENT_CATEGORY.selectOption("Meeting");
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    ELEMENT_EVENT_TITLE_DRAWER.waitUntil(Condition.visible,Configuration.timeout).setValue(titleEventCall);
    ELEMENT_EVENT_DESCRIPTION.setValue(content);
    ELEMENT_EVENT_CATEGORY.selectOption("Calls");
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    ELEMENT_EVENT_TITLE_DRAWER.waitUntil(Condition.visible,Configuration.timeout).setValue(titleEventClient);
    ELEMENT_EVENT_DESCRIPTION.setValue(content);
    ELEMENT_EVENT_CATEGORY.selectOption("Clients");
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    ELEMENT_EVENT_TITLE_DRAWER.waitUntil(Condition.visible,Configuration.timeout).setValue(titleEventHoliday);
    ELEMENT_EVENT_DESCRIPTION.setValue(content);
    ELEMENT_EVENT_CATEGORY.selectOption("Holiday");
    eventManagement.saveQuickAddEvent();
    eventManagement.goToAddEventFromActionBar();
    ELEMENT_EVENT_TITLE_DRAWER.waitUntil(Condition.visible,Configuration.timeout).setValue(titleEventAnni);
    ELEMENT_EVENT_DESCRIPTION.setValue(content);
    ELEMENT_EVENT_CATEGORY.selectOption("Anniversary");
    eventManagement.saveQuickAddEvent();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    click(ELEMENT_TODAY_ACTION_BAR);
    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.ANNIVERSARY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventAnni,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.MEETING);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventMeeting,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.CALL);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventCall,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.HOLIDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventClient,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventHoliday,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.CLIENT);
    calendarHomePage.verifyIsPresentEventTask(titleEventClient,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventHoliday,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventCall,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventAnni,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsNotPresentEventTask(titleEventMeeting,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.selectCategory(CalendarHomePage.selectCategoryOption.ALL);
    calendarHomePage.verifyIsPresentEventTask(titleEventClient,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventHoliday,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventCall,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventAnni,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    calendarHomePage.verifyIsPresentEventTask(titleEventMeeting,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.ALLDAY);
    $(ELEMENT_TODAY_ACTION_BAR).click();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    calendarHomePage.deleteEventTask(titleEventMeeting,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventCall,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventClient,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventHoliday,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    calendarHomePage.deleteEventTask(titleEventAnni,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));

  }
}
