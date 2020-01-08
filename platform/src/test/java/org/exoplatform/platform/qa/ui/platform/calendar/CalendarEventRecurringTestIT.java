package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.captureScreen;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("calendar")
@Tag("sniff")
public class CalendarEventRecurringTestIT extends Base {
  HomePagePlatform homePagePlatform;

  EventManagement  eventManagement;

  CalendarHomePage calendarHomePage;

  ManageLogInOut   manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    eventManagement = new EventManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * Case ID:115634. Test Case Name: Edit a recurring event with "Following
   * events" option. Case ID:115632. Test Case Name: Delete Following events in
   * recurring event. Pre-Condition: A recurring events is created Post-Condition:
   * Step number: 2 Step Name: Step 2: Show Edit an event form Step Description: -
   * Edit an even from the series Input Data: Expected Outcome: - The pop up "Edit
   * Recurring event" is displayed - The icon "Repeat" is checked Step number: 3
   * Step Name: Step 3: Edit repeat option Step Description: - Edit the repeat
   * option - Click "Save" Input Data: Expected Outcome: - A confirmation pop up
   * is displayed to ask user: "Would you like to change only this event, all
   * events in the series, or this and all following events in the series?" - 3
   * options: + Only this event + Following events + All events - The option "Only
   * this Event" is selcted by Default Step number: 4 Step Name: Step 4: Choose
   * option to change Step Description: - Choose "Following events" - Click "Save"
   * Input Data: Expected Outcome: - Changes are appliyed for current & following
   * events Step number: 2 Step Name:
   * <p>
   * Step 2: Select a recurring event<br data -mce -bogus="1">
   * </p>
   * Step Description: - Right click on event from the series - Choose the option
   * "Delete" Input Data: Expected Outcome: - A pop upis displayed with:* A title:
   * "Delete Recurring Event"* A message: Would you like to delete only this
   * event, all events in the series, or this and all following events in the
   * series?* The option 'Only this event" is selected by default* A button
   * "Delete" Step number: 3 Step Name:
   * <p>
   * Step 3: Delete only this event<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Choose the default option "Following events"<br>
   * - Click "Delete"
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - Selected and following event are deleted
   * </p>
   */
  @Test
  @Tag("eventis")
  public void test02_EditFollowingEventsInRecurringEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    info("Test 01 Edit a recurring event with Following events option");
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.WEEK,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(1, "MMM dd yyyy"));
    eventManagement.inputDataEventInDetailForm(titleEvent2, contentEvent2, null, null, false);
    eventManagement.saveAddEventDetails();
    eventManagement.editRecurringEvent(EventManagement.recurringType.FOLLOW_EVENT, true);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(1, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(2, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(3, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(4, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 02 Delete Following events in recurring event");
    eventManagement.deleteRecurringEvent(titleEvent2,
                                         CalendarHomePage.selectViewOption.WEEK,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.FOLLOW_EVENT,
                                         getDate(2, "MMM dd yyyy"),
                                         true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(2, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(3, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(4, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);

  }

  @Test
  @Tag("eventis")
  public void test02_DeleteFollowingEventsInRecurringEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(1, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 02 Delete Following events in recurring event");
    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.WEEK,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.FOLLOW_EVENT,
                                         getDate(0, "MMM dd yyyy"),
                                         true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(2, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(3, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(4, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);

  }

  /**
   * Case ID:115639. Test Case Name: Delete only a current recurring event.
   * Pre-Condition: A recurring events is created Post-Condition: Step number: 2
   * Step Name: Step 2: Show Edit an event form Step Description: - Edit an even
   * from the series Input Data: Expected Outcome: - The pop up "Edit Recurring
   * event" is displayed - The icon "Repeat" is checked Step number: 3 Step Name:
   * Step 3: Edit repeat option Step Description: - Edit any information of event
   * - Click "Save" Input Data: Expected Outcome: - A confirmation pop up is
   * displayed to ask user: "Would you like to change only this event, all events
   * in the series, or this and all following events in the series?" - 3 new
   * options: Only this event, Following events, All events. - The option "Only
   * this event" is selected by default. Step number: 4 Step Name: Step 4: Choose
   * option to change Step Description: - Choose the default option "Only this
   * event" - Click "Save" Input Data: Expected Outcome: - Changes are restricted
   * to the edited recurring event Step Number: 1 Step Name:
   * <p>
   * Step 1: Show calendar application<br data -mce -bogus="1">
   * </p>
   * Step Description: - Connect to Intranet - Open "Calendar" application Input
   * Data: Expected Outcome: - A recurring events is displayed Step number: 2 Step
   * Name:
   * <p>
   * Step 2: Select a recurring event<br data -mce -bogus="1">
   * </p>
   * Step Description: - Right click on event from the series - Choose the option
   * "Delete" Input Data: Expected Outcome: - A pop upis displayed with:* A title:
   * "Delete Recurring Event"* A message: Would you like to delete only this
   * event, all events in the series, or this and all following events in the
   * series?* The option 'Only this event" is selected by default* A button
   * "Delete" Step number: 3 Step Name:
   * <p>
   * Step 3: Delete only this event<br data -mce -bogus="1">
   * </p>
   * Step Description: - Choose the default option "Only this event" - Click
   * "Delete" Input Data: Expected Outcome: - Only current event is deleted from
   * the series
   */
  @Test
  @Tag("eventis")
  public void test03_04_EditDeleteOnlyACurrentRecurringEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    info("Test 07 Edit a recurring event with Only this event option");

    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.WEEK,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.inputDataEventInDetailForm(titleEvent2, contentEvent2, null, null, false);
    $(ELEMENT_EVENT_SAVE_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    eventManagement.editRecurringEvent(EventManagement.recurringType.ONLY_EVENT, true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(1, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 04 Delete only a current recurring event");

    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.WEEK,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ONLY_EVENT,
                                         getDate(1, "MMM dd yyyy"),
                                         true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    info("Clear data");
    calendarHomePage.deleteEventTask(titleEvent2,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"),
                                     true,
                                     true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                         getDate(0, "MMM dd yyyy"),
                                         CalendarHomePage.selectViewOption.MONTH,
                                         CalendarHomePage.selectDayOption.DETAILTIME);
    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.WEEK,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(2, "MMM dd yyyy"),
                                         true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
            getDate(2, "MMM dd yyyy"),
            CalendarHomePage.selectViewOption.MONTH,
            CalendarHomePage.selectDayOption.DETAILTIME);

    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
            getDate(3, "MMM dd yyyy"),
            CalendarHomePage.selectViewOption.MONTH,
            CalendarHomePage.selectDayOption.DETAILTIME);

    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
            getDate(4, "MMM dd yyyy"),
            CalendarHomePage.selectViewOption.MONTH,
            CalendarHomePage.selectDayOption.DETAILTIME);
  }

  /**
   * Case ID:115644. Test Case Name: Add a Recurring Events. Case ID:115633. Test
   * Case Name: Edit a recurring event with "All events" option. Case ID:115641.
   * Test Case Name: Delete all events. Pre-Condition: Post-Condition: Step
   * Number: 1 Step Name: Step 1: Show calendar application Step Description: -
   * Connect to Intranet - Choose Calendar application Input Data: Expected
   * Outcome: - The Calendar is displayed Step number: 2 Step Name: Step 2: Show
   * Add event form Step Description: Click Event on action bar Input Data:
   * Expected Outcome: - The pop up "Quick Add Event" is displayed Step number: 3
   * Step Name: Step 3: Add a recurring event Step Description: - Input some
   * requiredfields - Click [More Details] - Tick Repeat option - Select Repeat,
   * eg daily, Repeat every 1 day andEnd repeat after 5 occurrence - Save - Save
   * Input Data: Expected Outcome: -The default duration for Event (From -To) is 1
   * hour - Series of events is displayed as setting repeat. Step Number: 1 Step
   * Name: Step 1: Show recurring event Step Description: - Connect to Intranet -
   * Choose Calendar application Input Data: Expected Outcome: - The Calendar is
   * displayed - A recurring event is displayed Step number: 2 Step Name: Step 2:
   * Show Edit an event form Step Description: - Edit an even from the series
   * Input Data: Expected Outcome: - The pop up "Edit Recurring event" is
   * displayed - The icon "Repeat" is checked Step number: 3 Step Name: Step 3:
   * Edit repeat option Step Description: - Edit the repeat option - Click "Save"
   * Input Data: Expected Outcome: - A confirmation pop up is displayed to ask
   * user: "Would you like to change only this event, all events in the series, or
   * this and all following events in the series?" - 3 options: + Only this event
   * + Following events + All events - The option "Only this Event" is selcted by
   * Default Step number: 4 Step Name: Step 4: Choose option to change Step
   * Description: - Choose "All events" - Click "Save" Input Data: Expected
   * Outcome: - Changes are appliyed for all events Step number: 3 Step Name:
   * <p>
   * Step 3: Delete event<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Choose the default option "All events"<br>
   * - Click "Delete"
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - All events are deletes
   * </p>
   * <p>
   * - Series no longer exists
   * </p>
   */
  @Test
  @Tag("eventis")
  public void test05_AddRecurringEvents() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Test 05 Add a Recurring Events");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    $(ELEMENT_SAVE_EVENT_OCCURRING).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(1, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 07 Delete all events");

    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.DAY,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(0, "MMM dd yyyy"),
                                         true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(2, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(3, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(4, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
  }

  @Test
  @Tag("eventis")
  public void test06_EditAllRecurringEvents() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    info("Test 05 Add a Recurring Events");

    info("Add a event");
    homePagePlatform.goToCalendarPage();

    eventManagement.goToAddEventFromActionBar();

    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(1, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 06 Edit a recurring event with All events option");

    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.DAY,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.inputDataEventInDetailForm(titleEvent2, contentEvent2, null, null, false);
    $(ELEMENT_EVENT_SAVE_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    eventManagement.editRecurringEvent(EventManagement.recurringType.ALL_EVENT, true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(2, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(3, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(4, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);

    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(1, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 07 Delete all events");

    eventManagement.deleteRecurringEvent(titleEvent2,
                                         CalendarHomePage.selectViewOption.DAY,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(0, "MMM dd yyyy"),
                                         true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(2, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(3, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent2,
                                                             getDate(4, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
  }

  @Test
  @Tag("eventis")
  public void test07_DeleteAllRecurringEvents() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Test 05 Add a Recurring Events");

    info("Add a event");
    homePagePlatform.goToCalendarPage();

    eventManagement.goToAddEventFromActionBar();

    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(1, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(2, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(3, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent,
                                                          getDate(4, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 07 Delete all events");

    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.DAY,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(0, "MMM dd yyyy"),
                                         true);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(0, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(1, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(2, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(3, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.verifyIsNotPresentEventTaskWithDateTime(titleEvent,
                                                             getDate(4, "MMM dd yyyy"),
                                                             CalendarHomePage.selectViewOption.MONTH,
                                                             CalendarHomePage.selectDayOption.DETAILTIME);
  }

  /**
   * Case ID:115643. Test Case Name: An extra icon is displayed for a repeated
   * event. Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Open
   * the calendar application Step Description: - Connect to Intranet - Choose
   * Calendar application Input Data: Expected Outcome - The Calendar is displayed
   * Step number: 2 Step Name: Step 2: Add a recurring event Step Description: -
   * Create a Recurring event Input Data: Expected Outcome: - Series of events is
   * displayed with an extra icon, see attachment [extraIcon.png] Step number: 3
   * Step Name: Step 3: Check show the popover Step Description: - Move the mouse
   * over the event Input Data: Expected Outcome: - The popover of the event shows
   * + Title of event+ Description+ Location+ Time+ Icon is the same icon with the
   * label "Recurring event", pls see attachment [Recurring_icon_On_Popover.png]
   */

  @Test
  @Tag("eventis")
  public void test09_AnExtraIconIsDisplayedForARepeatedEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 08 An extra icon is displayed for a repeated event");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.MONTH);
    $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent).replace("$date",
                                                                                             getDate(0, "MMM dd yyyy")))).waitUntil(Condition.visible,Configuration.timeout);
    $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent).replace("$date",
            getDate(0, "MMM dd yyyy")))).hover();
    $(ELEMENT_TITLE_RECURRING_EVENT).isDisplayed();
    $(ELEMENT_DATE_TIME_RECURRING_EVENT).isDisplayed();
    $(ELEMENT_RECURRING_TEXT_RECURRING_EVENT).isDisplayed();

    info("Clear data");
    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.DAY,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(0, "MMM dd yyyy"),
                                         false);
  }

  @Test
  @Tag("CAL-1330")
  @Tag("eventis")
  public void test10_addRecurringEventStartAfterMonth() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(30, "MM-dd-yyyy HH") + ":00",
                                              getDate(30, "MM-dd-yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Yearly,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "3");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.MONTH);
    $(ELEMENT_NEXT_BUTTON_ANY_VIEW).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byText(titleEvent)).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byText(titleEvent)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent2,
                                              content,
                                              getDate(0, "MM-dd-yyyy HH") + ":00",
                                              getDate(0, "MM-dd-yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTaskWithDateTime(titleEvent2,
                                                          getDate(0, "MMM dd yyyy"),
                                                          CalendarHomePage.selectViewOption.MONTH,
                                                          CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.deleteEventTask(titleEvent2,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));

    eventManagement.deleteRecurringEvent(titleEvent,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     EventManagement.recurringType.ALL_EVENT,
                                     getDate(30, "MMM dd yyyy"),
                                    true);

  }
}
