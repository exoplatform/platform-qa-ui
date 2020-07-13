package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

@Tag("sniff")
@Tag("calendar")
public class CalendarPublishActivityTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  CalendarManagement calendarManagement;

  SpaceManagement    spaceManagement;

  EventManagement    eventManagement;

  ManageLogInOut     manageLogInOut;

  CalendarHomePage   calendarHomePage;

  String             spaceName;

  String             spaceDes;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    spaceManagement = new SpaceManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    eventManagement = new EventManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    spaceName = "spaceName" + getRandomNumber();
    spaceDes = "spaceDes" + getRandomNumber();
  }

  /**
   * Create data test
   */
  public void createDataTest() {

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
  }

  /**
   * Delete datatest
   */
  public void deleteDataTest() {
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceName, false);
  }

  /**
   * Case ID:115635. Test Case Name: Activities should be updated after deleting
   * of an edited recurring event. Pre-Condition: - Event should be in group
   * calendars for spaces - An edited recurring event is displayed, edited by drag
   * & drop or by only this event option - An activity of edited recurring event
   * is displayed in the activity stream Post-Condition: Step Number: 1 Step Name:
   * <p>
   * Step 1: Open calendar application<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Connect to Intranet<br>
   * - Open "Calendar" application
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - The calendar application is displayed
   * </p>
   * <p>
   * - An edited recurring event is displayed&nbsp;
   * </p>
   * <p>
   * <br>
   * </p>
   * Step number: 2 Step Name:
   * <p>
   * Step 2: Delete the edited recurring event<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Right click on the edited recurring event
   * </p>
   * <p>
   * - Click Delete
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - The edited recurring event is deleted
   * </p>
   * Step number: 3 Step Name:
   * <p>
   * Step 3: Check activity<br data -mce -bogus="1">
   * </p>
   * Step Description: - Go to the Homepage Input Data: Expected Outcome:
   * <p>
   * - In the activity stream:
   * </p>
   * <p>
   * Activity of the edited recurring event is
   * </p>
   * <p>
   * A comment is added to the main activity (of series):&nbsp;<em>Event cancelled
   * for $CANCEL_DATE</em>
   * </p>
   * <p>
   * <em>where&nbsp;<span>$CANCEL_DATE : the date of the event removed</span></em>
   * </p>
   */

  @Test
  @Tag("eventis")
  public void test08_ActivitiesShouldBeUpdatedAfterDeletingOfAnEditedRecurringEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Test 8: Activities should be updated after deleting of an edited recurring event");
    createDataTest();
    info("Add a recurring event");
    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.MONTH,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ONLY_EVENT,
                                         getDate(2, "MMM dd yyyy"),
                                         true);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_RECURRING_CANCEL.replace("$name", titleEvent)
                                                                  .replace("$date", getDate(2, "EEEE, MMMM d, yyyy"))))
                                                                                                                       .should(Condition.visible);
    info("Clear data");
    homePagePlatform.goToCalendarPage();
    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.MONTH,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(0, "MMM dd yyyy"),
                                         true);
    deleteDataTest();

  }

  /**
   * Case ID:115637. Test Case Name: Activity of recurring event should be deleted
   * after deleting all events. Pre-Condition: - Event should be in group
   * calendars for spaces - A recurring event is displayed in the Calendar - An
   * activity recurring event is displayed in the activity stream Post-Condition:
   * Step Number: 1 Step Name:
   * <p>
   * Step 1: Open calendar application<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Connect to Intranet<br>
   * - Open "Calendar" application
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - The calendar application is displayed
   * </p>
   * <p>
   * - A recurring event is displayed&nbsp;
   * </p>
   * <p>
   * <br>
   * </p>
   * Step number: 2 Step Name:
   * <p>
   * Step 2: Choose an event<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Right click on an event from the series
   * </p>
   * <p>
   * - Click Delete
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - The Delete Recurring Event will be disappeared with 3 options: Only this
   * instance, Following this instance, All events in the series.<br>
   * </p>
   * <p>
   * <br class="uiRadio">
   * </p>
   * Step number: 3 Step Name:
   * <p>
   * Step 3: Delete event<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Choose "All events in the series" option
   * </p>
   * <p>
   * - Click on Delete
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - All events of the series are deleted
   * </p>
   * Step number: 4 Step Name:
   * <p>
   * Step 4: Check activity after delete event<br data -mce -bogus="1">
   * </p>
   * Step Description: - Go to the Homepage Input Data: Expected Outcome:
   * <p>
   * - In the activity stream, the activity of recurring event is deleted
   * </p>
   */
  @Test
  @Tag("eventis")
  public void test10_ActivityOfRecurringEventShouldBeDeletedAfterDeletingAllEvents() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 10: Activity of recurring event should be deleted after deleting all events");
    info("Add a event");
    createDataTest();
    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    check(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX, 2);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEvent))).should(Condition.visible);
    homePagePlatform.goToCalendarPage();
    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.MONTH,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(1, "MMM dd yyyy"),
                                         true);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEvent))).shouldNot(Condition.visible);
    deleteDataTest();
  }

  /**
   * Case ID:115638. Test Case Name: A comment to event activity should be added
   * after adding a repeat event. Pre-Condition: * Event should be in group
   * calendars for spaces* An event is displayed in Calendar* An activity event is
   * displayed in the activity stream Post-Condition: Step Number: 1 Step Name:
   * <p>
   * Step 1: Show application calendar<br data -mce -bogus="1">
   * </p>
   * Step Description: - Connect to Intranet - Open "Calendar" application Input
   * Data: Expected Outcome: - An event is displayed Step number: 2 Step Name:
   * <p>
   * Step 2: Chang event to recurring event<br data -mce -bogus="1">
   * </p>
   * Step Description:
   * <p>
   * - Edit the event<br>
   * - Check "Repeat" option<br>
   * - Click Save
   * </p>
   * Input Data: Expected Outcome:
   * <p>
   * - A recurring event is added
   * </p>
   * Step number: 3 Step Name:
   * <p>
   * Step 3: Check acitity after add recurring event<br data -mce -bogus="1">
   * </p>
   * Step Description: - Go to the Homepage Input Data: Expected Outcome:
   * <p>
   * - In the activity stream, a comment is added to the main activity event:
   * "Event will be repeated $REPETITION"<br>
   * where $REPETITION : strong format of the recurring settings (e.g : every week
   * on Wednesday)
   * </p>
   */

  @Test
  @Tag("eventis")
  public void test11_ACommentToEventActivityShouldBeAddedAfterAddingARepeatEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String comment = ELEMENT_ACTIVITY_EVENT_COMMENT_REPEAT_DAY.replace("$number", "5");

    info("Test 11: A comment to event activity should be added after adding a repeat event");
    createDataTest();
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.MONTH,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.MONTH,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
                                            "1",
                                            null,
                                            EventManagement.repeatEndType.After,
                                            "5");
    click(ELEMENT_SAVE_EVENT_OCCURRING);
    eventManagement.saveAddEventDetails();
    sleep(3000);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment",
                                                                                       comment))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    info("Clear data");
    homePagePlatform.goToCalendarPage();
    eventManagement.deleteRecurringEvent(titleEvent,
                                         CalendarHomePage.selectViewOption.MONTH,
                                         CalendarHomePage.selectDayOption.DETAILTIME,
                                         EventManagement.recurringType.ALL_EVENT,
                                         getDate(1, "MMM dd yyyy"),
                                         true);
    deleteDataTest();
  }

  /**
   * Case ID:115661. Test Case Name: Update activity for event of Space Calendar-
   * event is updated as all day eventa space. Pre-Condition: a space is created
   * Post-Condition: Step Number: 1 Step Name: - Create an event Step Description:
   * - Connect to Intranet - Go to Calendar - Create an event for space calendar
   * Input Data: Expected Outcome: - Event is created and activity is published in
   * HomePage Step number: 2 Step Name: - Update Event Step Description: - Edit
   * event and check "all day" Input Data: Expected Outcome: - The event in the
   * activity stream is updated - A comment is added to the activity with the
   * following message Event is now an all day event.
   */

  @Test
  @Tag("eventis")
  public void test12_UpdateActivityForEventOfSpaceCalendarEventIsUpdatedAsAllDayEventaSpace() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String comment = ELEMENT_ACTIVITY_EVENT_COMMENT_CHECK_ALL_DAY;

    info("Test 12: Update activity for event of Space Calendar- event is updated as all day eventa space");

    createDataTest();
    homePagePlatform.goToCalendarPage();
    info("create event for space calendar");
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.MONTH,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.MONTH,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    check(ELEMENT_ADD_EDIT_EVENT_ALLDAY, 2);
    eventManagement.saveAddEventDetails();
    sleep(3000);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment",
                                                                                       comment))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    info("Clear data");
    homePagePlatform.goToCalendarPage();
    sleep(2000);
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.ALLDAY,
                                     getDate(0, "MMM dd yyyy"));
    deleteDataTest();
  }

  /**
   * Case ID:115662. Test Case Name: Update activity for event of Space Calendar -
   * event title. Pre-Condition: a space is created Post-Condition: Step Number: 1
   * Step Name: - Create an event Step Description: - Connect to Intranet - Go to
   * Calendar - Create an event for space calendar Input Data: Expected Outcome: -
   * Event is created and activity is published Step number: 2 Step Name: - Update
   * Event Step Description: - Update Title of the event Input Data: Expected
   * Outcome: - The title of event in the activity stream is updated - A comment
   * is added with the following message: "Title has been updated to: $value."
   */
  @Test
  @Tag("eventis")
  public void test13_UpdateActivityForEventOfSpaceCalendarEventTitle() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String newTitleEvent = "newTitleEvent" + getRandomNumber();
    String content = "content";
    String comment = ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_TITLE.replace("$title", newTitleEvent);

    info("Test 13 Update activity for event of Space Calendar - event title");

    createDataTest();
    homePagePlatform.goToCalendarPage();
    info("create event for space calendar");
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.MONTH,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.MONTH,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.inputBasicDetailEvent(newTitleEvent, null);
    eventManagement.saveAddEventDetails();
    sleep(3000);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", newTitleEvent).replace("$comment",
                                                                                          comment))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(newTitleEvent,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
    deleteDataTest();
  }

  /**
   * Case ID:115663. Test Case Name: Update activity for event of Space Calendar -
   * event description. Pre-Condition: a space is created Post-Condition: Step
   * Number: 1 Step Name: - Create an event Step Description: - Connect to
   * Intranet - Go to Calendar - Create an event for space calendar Input Data:
   * Expected Outcome: - Event is created and activity is published in activity
   * stream Step number: 2 Step Name: - Update an event Step Description: - Update
   * an event description and check activity stream Input Data: Expected Outcome:
   * - The description of event in the activity stream is updated - A comment is
   * added to the activity with the following message: Description has been
   * updated to: $value.
   */

  @Test
  @Tag("eventis")
  public void test14_UpdateActivityForEventOfSpaceCalendarEventDescription() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String comment = ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_DES.replace("$description", newContent);

    info("Test 14 Update activity for event of Space Calendar - event description");
    createDataTest();
    homePagePlatform.goToCalendarPage();
    info("create event for space calendar");
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.MONTH,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.MONTH,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.inputBasicDetailEvent(null, newContent);
    eventManagement.saveAddEventDetails();
    sleep(3000);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment",
                                                                                       comment))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
    deleteDataTest();
  }

  /**
   * Case ID:115664. Test Case Name: Update activity for event of Space Calendar -
   * event location. Pre-Condition: a space is created Post-Condition: Step
   * Number: 1 Step Name: - Create an event Step Description: - Connect to
   * Intranet - Go to calendar - Create an event for Space Calendar Input Data:
   * Expected Outcome: - Event is created and activity is published in activity
   * stream Step number: 2 Step Name: - Update Event Step Description: - Update an
   * event location Input Data: Expected Outcome: - The location of event in the
   * activity stream is updated - A comment is added to the activity with the
   * following message: Location has been updated to: $value.
   */
  @Test
  @Tag("eventis")
  public void test15_UpdateActivityForEventOfSpaceCalendarEventLocation() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String location = "location" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String comment = ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_LOC.replace("$location", location);

    info("Test 15 Update activity for event of Space Calendar - event location");

    createDataTest();
    homePagePlatform.goToCalendarPage();
    info("create event for space calendar");
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.MONTH,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.MONTH,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.inputBasicDetailEvent(null, null, null, null, location);
    eventManagement.saveAddEventDetails();
    sleep(3000);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment",
                                                                                       comment))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
    deleteDataTest();
  }

  /**
   * Case ID:115660. Test Case Name: Publish activity for event of Group Calendar
   * of a Space. Case ID:115630. Test Case Name: Activity isn't publish for a
   * event of Personal, Shared and other group calendars than Space. Case
   * ID:115651. Test Case Name: Open event from activity stream. Case ID:115665.
   * Test Case Name: Delete an event of space calendar. Pre-Condition: Please note
   * : Only group calendars for spaces are publishing activities. Personal, Shared
   * and other group calendars are not concerned. Post-Condition: Step Number: 1
   * Step Name: - Create a Group calendar Step Description: - Login and Click
   * [Join a Space] - Create a new space Input Data: Expected Outcome: - Space and
   * Group calendar of space is created succesfully // See function
   * setUpBeforeTest() Step number: 2 Step Name: - Create new event Step
   * Description: - Go to Calendar - Click Add Event button - Input the info of
   * event and click save Input Data: Expected Outcome: - Event is created
   * successfully Step number: 3 Step Name: - Check Activity stream Step
   * Description: - Goto Homepage and check activity stream Input Data: Expected
   * Outcome: - The activity of creating Event is added and displayed in the
   * activity stream Step number: 2 Step Name: Step Description: - Click on Title
   * of the event Input Data: Expected Outcome: Space calendar opens with event
   * details opened. Step number: 2 Step Name: - Delete event Step Description: -
   * Right click on event and select delete - Click ok to confirm - Check activity
   * stream Input Data: Expected Outcome: - The event's activity is removed from
   * the activity stream
   */
  @Test
  public void test01_VerifyActivityEventOfPersonalCalendars() {
    String titleEventPersonal = "titleEventPersonal" + getRandomNumber();
    String personalCalendar = "personalCalendar" + getRandomNumber();
    String calendarColor = "purple";
    info("Create datatest");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(personalCalendar, personalCalendar, calendarColor);
    calendarManagement.saveAddCalendar();

    homePagePlatform.goToCalendarPage();
    info("create event for personal calendar");
    calendarManagement.executeActionCalendar(personalCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEventPersonal,
                                              titleEventPersonal,
                                              getDate(1, "MM/dd/yyyy HH") + ":00",
                                              getDate(1, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToCalendarPage();
    homePagePlatform.goToHomePage();

    info("Test 3: Activity isn't publish for a event of Personal, Shared and other group calendars than Space");
    info("Event/task of personal/group/share calendar are NOT displayed on homepage activity");
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventPersonal))).shouldNot(Condition.visible);

    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(personalCalendar, false);

  }

  @Test
  public void test02_VerifyActivityEventOfSharedAndCalendars() {

    String titleEventShare = "titleEventShare" + getRandomNumber();

    String shareCalendar = "shareCalendar" + getRandomNumber();
    String calendarColor = "purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { true };

    info("Create datatest");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(shareCalendar, shareCalendar, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", shareCalendar))).should(Condition.visible);
    calendarManagement.shareCalendar(shareCalendar, groupShare, edit, 1);

    homePagePlatform.goToCalendarPage();
    info("create event for share calendar");
    calendarManagement.executeActionCalendar(shareCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEventShare,
                                              titleEventShare,
                                              getDate(1, "MM/dd/yyyy HH") + ":00",
                                              getDate(1, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();

    homePagePlatform.goToHomePage();

    info("Test 3: Activity isn't publish for a event of Personal, Shared and other group calendars than Space");
    info("Event/task of personal/group/share calendar are NOT displayed on homepage activity");
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventShare))).shouldNot(Condition.visible);

    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(shareCalendar, false);

  }

  @Test
  @Tag("eventis")
  public void test03_VerifyActivityEventOfGroupCalendars() {
    String titleEventGroup = "titleEventGroup" + getRandomNumber();
    String groupCalendar = "Content Management";
    homePagePlatform.goToCalendarPage();
    info("create event for group calendar");
    calendarManagement.executeActionCalendar(groupCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEventGroup,
                                              titleEventGroup,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToHomePage();
    info("Test 3: Activity isn't publish for a event of Personal, Shared and other group calendars than Space");
    info("Event/task of personal/group/share calendar are NOT displayed on homepage activity");
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventGroup))).shouldNot(Condition.visible);

    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEventGroup,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"),
                                     false,
                                     false);
  }

  @Test
  @Tag("eventis")
  public void test01_02_03_04VerifyActivityEventOfPersonalSharedAndOtherGroupCalendarsSpace() {
    String titleEventSpace = "titleEventSpace" + getRandomNumber();
    String titleEventPersonal = "titleEventPersonal" + getRandomNumber();
    String titleEventGroup = "titleEventGroup" + getRandomNumber();
    String titleEventShare = "titleEventShare" + getRandomNumber();

    String groupCalendar = "Content Management";
    String personalCalendar = "personalCalendar" + getRandomNumber();
    String shareCalendar = "shareCalendar" + getRandomNumber();
    String calendarColor = "purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { true };

    info("Create datatest");
    createDataTest();
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(shareCalendar, shareCalendar, calendarColor);
    calendarManagement.saveAddCalendar();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(personalCalendar, shareCalendar, calendarColor);
    calendarManagement.saveAddCalendar();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(groupCalendar, shareCalendar, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", shareCalendar))).should(Condition.visible);
    calendarManagement.shareCalendar(shareCalendar, groupShare, edit, 1);

    homePagePlatform.goToCalendarPage();
    info("create event for space calendar");
    calendarManagement.executeActionCalendar(spaceName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEventSpace,
                                              titleEventSpace,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    sleep(2000);
    homePagePlatform.goToCalendarPage();
    info("create event for personal calendar");
    calendarManagement.executeActionCalendar(personalCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEventPersonal,
                                              titleEventPersonal,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToCalendarPage();
    info("create event for group calendar");
    calendarManagement.executeActionCalendar(groupCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEventGroup,
                                              titleEventGroup,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    homePagePlatform.goToCalendarPage();
    info("create event for share calendar");
    calendarManagement.executeActionCalendar(shareCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    eventManagement.inputDataEventInQuickForm(titleEventShare,
                                              titleEventShare,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();

    homePagePlatform.goToHomePage();
    info("Test 1: Publish activity for event of Group Calendar of a Space");
    info("Event of space calendar are displayed on homepage activity");
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace))).should(Condition.visible);

    info("Test 2 Publish activity for Task of Group Calendar of Space");

    info("Test 3: Activity isn't publish for a event of Personal, Shared and other group calendars than Space");
    info("Event/task of personal/group/share calendar are NOT displayed on homepage activity");
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventPersonal))).shouldNot(Condition.visible);
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventGroup))).shouldNot(Condition.visible);
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventShare))).shouldNot(Condition.visible);

    info("Test 4: Open event from activity stream");

    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace))).click();
    $(byXpath(ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleEventSpace))).should(Condition.visible);
    $(ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM).click();

    info("Test 6 Delete an event of space calendar");

    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace))),Condition.visible,1000);
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace))).click();
    $(byXpath(ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleEventSpace))).should(Condition.visible);
    $(ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM).click();
    $(byXpath(ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleEventSpace))).shouldNot(Condition.visible);
    calendarHomePage.deleteEventTask(titleEventSpace,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"),
                                     false,
                                     false);

    homePagePlatform.goToHomePage();
    info("Event activit of space calendar is removed from homepage activity");
    $(byXpath(ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace))).shouldNot(Condition.visible);
    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(shareCalendar, false);

    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEventGroup,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"),
                                     true,
                                     true);
    calendarHomePage.deleteEventTask(titleEventPersonal,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"),
                                     true,
                                     true);
    deleteDataTest();

  }
}
