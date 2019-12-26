package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.openqa.selenium.Keys;

@Tag("calendar")
@Tag("sniff")
public class CalendarEventTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  ManageLogInOut     manageLogInOut;

  CalendarHomePage   calendarHomePage;

  EventManagement    eventManagement;

  CalendarManagement calendarManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    calendarHomePage = new CalendarHomePage(this);
    eventManagement = new EventManagement(this);
    calendarManagement = new CalendarManagement(this);
    manageLogInOut.signInCas(username, "gtn");
  }

  /**
   * Case ID:115621. Test Case Name: Add attachment to event. Case ID:115620. Test
   * Case Name: Remove attachment of event. Pre-Condition: Event with attachment
   * is exist Post-Condition: Step Number: 1 Step Name: Step 1: Open Add event
   * form Step Description: - Select a calendar, Click Setting icon of this
   * calendar and choose [Add Event] or Click Event button on action bar - Input
   * start and end time - Click [More Details Input Data: Expected Outcome:
   * Add/Edit Event pop -up has 4 tabs - Details - Reminders - Participants -
   * Schedule Step number: 2 Step Name: Step 2: Add attachment Step Description: -
   * Click [Add Attachment] - Browse to file and click save Input Data: Expected
   * Outcome: - Attachment is added to event Step Number: 1 Step Name: Step 1:
   * Open edit event form Step Description: - Go to calendar - Select event which
   * has attachment and edit Input Data: Expected Outcome: - Edit form appears
   * Step number: 2 Step Name: Step 2: Remove attachment Step Description: - Click
   * [Delete] icon - Click Save Input Data: Expected Outcome: - Attachment is
   * deleted
   */
  @Test
  @Tag("eventis")
  public void test01_AddAttachmentOfEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 1: Add attachment to event");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    eventManagement.goToAddEventFromActionBar();
    info("Add attachment");
    $(ELEMENT_ADD_EDIT_EVENT_NAME).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(titleEvent);
    $(byId("cal-attach-file")).uploadFromClasspath("eXo-Platform.png");
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.DAY,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 2: Remove attachment of event");

    info("Remove the attachment");
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.DAY,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       null);
    eventManagement.removeAttachment("eXo-Platform.png");
    eventManagement.saveQuickAddEvent();

    info("Delete data");
    calendarHomePage.deleteEventTask(titleEvent,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));
  }

  @Test
  @Tag("eventis")
  public void test02_RemoveAttachmentOfEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 1: Add attachment to event");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    eventManagement.goToAddEventFromActionBar();
    info("Add attachment");
    eventManagement.inputDataEventInDetailForm(titleEvent, content, getDate(0, "MM-dd-yyyy"), getDate(0, "MM-dd-yyyy"), false);
    $(ELEMENT_ADD_EDIT_EVENT_NAME).click();
    $(byId("cal-attach-file")).uploadFromClasspath("eXo-Platform.png");
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.DAY,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 2: Remove attachment of event");

    info("Remove the attachment");
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.DAY,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       null);
    eventManagement.removeAttachment("eXo-Platform.png");
    eventManagement.saveQuickAddEvent();

    info("Delete data");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.DAY,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
  }

  /**
   * Case ID:115626. Test Case Name: Check date suggestion. Pre-Condition: Time
   * suggestion is set on configuration.properties#auto suggest the end of event
   * time about 1 hourexo.calendar.default.event.suggest=2 Post-Condition: Step
   * Number: 1 Step Name: Step1: Check date suggestion when add event by action
   * bar Step Description: - Go to Calendar - Click [Event] on action bar - Select
   * a from time Input Data: Expected Outcome: - Open quick add event form with To
   * time = From time + 1 hour - When set a new From time, To time = From time + 1
   * hour - From date = To date = Current date Step number: 2 Step Name: Step2:
   * Check date suggestion when add event on a calendar Step Description: - Add
   * new calendar - On calendar, click setting icon then choose [Add Event] -
   * Select From time Input Data: Expected Outcome: - Open quick add event form
   * with To time = From time + 1 hour - When set a new From time, To time = From
   * time + 1 hour - From date = To date = current date Step number: 3 Step Name:
   * Setp3: Check date suggestion when add event by left click on main panel Step
   * Description: - Left click on a cell on main panel on Week view/Daily view -
   * Select From time Input Data: Expected Outcome: - Open quick add event form
   * with To time = From time + 30 minus (by a block time) - When set new From
   * time, To time = From time + 1 hour - From date = To date = Date of click
   */
  @Test
  @Tag("eventis")
  public void test07_CheckDateSuggestion() {
    String fullName = "fullName" + getRandomNumber();
    String from = getDate(0, "MM-dd-yyyy HH") + ":00";
    info("Test 7: Check date suggestion");

    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    info("Check default time ");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.cancelQuickAddEditEvent();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(fullName, fullName, "");
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(fullName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.cancelQuickAddEditEvent();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    eventManagement.goToAddEventByLeftClickFromMainPanel("", "");
    info("Check default time ");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 30);
    eventManagement.inputFromToQuickEvent(from, "", false);
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 30);
    eventManagement.cancelQuickAddEditEvent();
    eventManagement.goToAddEventByRightClickFromMainPanel("", "");
    info("Check default time ");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.cancelQuickAddEditEvent();
    calendarManagement.deleteCalendar(fullName, true);
  }

  /**
   * Case ID:115628. Test Case Name: Add a participant. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Step 1: Open add/edit event pop up
   * Step Description: - Select a calendar, Click Setting icon of this calendar
   * and choose [Add Event] or Click Event button on action bar - Click [More
   * Details] Input Data: Expected Outcome: Add/Edit Event pop -up has 4 tabs -
   * Details - Reminders - Participants - Schedule Step number: 2 Step Name: Step
   * 2: Add aprticipant Step Description: - Click [Participants] tab - Select
   * Privacy, Available - Click [Add Participant] icon (+) - Pick some users -
   * Fill Invitation message - Click Save - Select 1 option for Send Invitations -
   * Save Input Data: Expected Outcome: - Selected users are listed on the table
   * with 3 column + Name: participant fullname + Information: participant email +
   * Status: participant status yes/no/not sure + Cction with remove icon - Event
   * is created with added participants
   */

  @Test
  @Tag("eventis")
  public void test09_AddAParticipant() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String users = DATA_USER2 + "/" + DATA_USER3;

    info("Test 9: Add a participant");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              false);
    eventManagement.selectUserParticipants(users);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    info("Clear data");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));
  }

  /**
   * Case ID:115679. Test Case Name: Drag-drop event. Pre-Condition: The event is
   * created Post-Condition: Step Number: 1 Step Name: Step 1: Drag and drop an
   * event Step Description: - Choose View type from main bar (except List View) -
   * Drag & drop added the eventin working pane Input Data: Expected Outcome: -
   * Event is moved to new place in working pane - time of event is updated
   * according to new place in main pane
   */
  @Test
  @Tag("eventis")
  public void test16_DragDropEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String dateTime = getDate(1, "MM-dd-yyyy");

    info("Create data test");
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              false);
    String fromTime =
                    waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
    String toTime = waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 16 Drag-drop event");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.MONTH);
    $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent)
                                                       .replace("$date", getDate(0, "MMM dd yyyy"))))
                                                                                                     .dragAndDropTo($(byXpath(ELEMENT_ANY_TARGET_DATE.replace("${targetDate}",
                                                                                                                                                              getDate(1,
                                                                                                                                                                      "MMM dd yyyy")))));
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.WEEK,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(1, "MMM dd yyyy"));
    eventManagement.checkSuggestionEventTimeInDetailForm(fromTime, toTime, 60);
    eventManagement.cancelAddEditDetailEvent();
    info("Delete data");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.MONTH,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
  }

  /**
   * Case ID:115690. Test Case Name: Add an event in group calendar. Case
   * ID:115680. Test Case Name: Edit an event in group calendar. Case ID:115681.
   * Test Case Name: Delete an event in group calendar. Pre-Condition:
   * Post-Condition:
   */
  @Test
  @Tag("eventis")
  public void test17_AddAnEventInGroupCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    String groupCalendar = "Content Management";

    info("Test 17 Add an event in group calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(groupCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 19 Delete an event in group calendar");
    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
  }

  @Test
  @Tag("eventis")
  public void test18_EditAnEventInGroupCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    String groupCalendar = "Content Management";

    info("Test 17 Add an event in group calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(groupCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 18 Edit an event in group calendar");
    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.DAY,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.checkSuggestionEventTimeInDetailForm(null, null, 60);
    eventManagement.inputDataEventInDetailForm(titleEvent2,
                                               contentEvent2,
                                               getDate(0, "MM-dd-yyyy" + " HH"),
                                               getDate(0, "MM-dd-yyyy" + " HH"),
                                               false);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent2,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 19 Delete an event in group calendar");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent2,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));

  }

  @Test
  @Tag("eventis")
  public void test19_DeleteAnEventInGroupCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    String groupCalendar = "Content Management";

    info("Test 17 Add an event in group calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(groupCalendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 19 Delete an event in group calendar");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
    manageLogInOut.signIn(DATA_USER3, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
  }

  /**
   * Case ID:115689. Test Case Name: Add an event for shared calendar. Case
   * ID:115683. Test Case Name: Edit an event in shared calendar. Case ID:115682.
   * Test Case Name: Delete an event in shared calendar. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: - Step Description: Step 1: Create
   * & share a calendar Input Data: - Create personal calendar - Share added
   * calendar with edit right Expected Outcome: Calendar is created and shared
   * Step number: 2 Step Name: Step Description: Step 2: Add an event to a shared
   * calendar Input Data: - Login by shared user - Click wheel icon of shared
   * calendar and select Add event Expected Outcome: - The pop up "Quick Add
   * Event" is displayed - The Default Start date "From" is set to Today (System
   * date) -The default duration for event is 1 hour Step number: 3 Step Name:
   * Step Description: Step 3: Save Input Data: - Fill values - Save Expected
   * Outcome: Event is added successfully in shared calendar Step number: 3 Step
   * Name: Step 3: Edit an event to a shared calendar Step Description: - Right
   * click on event then choose Edit - Update some values - Change From time Input
   * Data: Expected Outcome: - To time is automatically set = From Time + 1 hour
   * Step number: 4 Step Name: Step 4: Save Step Description: - Click Save to
   * finish Input Data: Expected Outcome: - Event in shared calendar is edited -
   * Sharing user can see updated event Step Number: 1 Step Name: - Step
   * Description: Step 1: Create & share a calendar Input Data: - Create personal
   * calendar - Share added calendar with edit right Expected Outcome: Calendar is
   * created and shared Step number: 2 Step Name: Step Description: Step 2: Add an
   * event to a shared calendar Input Data: - Log in as shared user - Click wheel
   * icon of shared calendar then choose Add event - Input Event summary and click
   * Save Expected Outcome: - The shared user can see the shared calendar and add
   * event into it. Step number: 3 Step Name: - Step Description: Step 3: Delete
   * an event to a shared calendar Input Data: - Right click on event then choose
   * Delete - Click OK at confirmation - Save Expected Outcome: - Event is deleted
   * - The sharing user cannot see this event
   */
  @Test
  @Tag("eventis")
  public void test22DeleteAnEventInSharedCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { true };

    info("Test 20 Add an event for shared calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    calendarManagement.shareCalendar(calendarName, groupShare, edit, 1);

    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy HH"),
                                              getDate(0, "MM-dd-yyyy HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 22 Delete an event in shared calendar");

    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"),
                                     false,
                                     false);

    info("Delete data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);

  }

  @Test
  @Tag("eventis")
  public void test20_AddAnEventInSharedCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { true };

    info("Test 20 Add an event for shared calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    calendarManagement.shareCalendar(calendarName, groupShare, edit, 1);

    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy HH"),
                                              getDate(0, "MM-dd-yyyy HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Delete data");
    manageLogInOut.signIn(username, password);
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);

  }

  @Test
  @Tag("eventis")
  public void test21_EditAnEventInSharedCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { true };

    info("Test 20 Add an event for shared calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    calendarManagement.shareCalendar(calendarName, groupShare, edit, 1);

    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy HH"),
                                              getDate(0, "MM-dd-yyyy HH"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 21 Edit an event in shared calendar");

    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.WEEK,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.checkSuggestionEventTimeInDetailForm(null, null, 60);
    eventManagement.inputDataEventInDetailForm(titleEvent2,
                                               contentEvent2,
                                               getDate(0, "MM-dd-yyyy HH"),
                                               getDate(0, "MM-dd-yyyy HH"),
                                               false);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Delete data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);

  }

  @Tag("CAL-1321")
  @Tag("eventis")
  @Test
  public void test_22_Calendar_event_time_granularity() {
    String titleEvent = "titleEvent" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
            "",
            getDate(0, "MM-dd-yyyy HH")  + ":13",
            getDate(0, "MM-dd-yyyy HH") + ":58",
            false);
    eventManagement.saveQuickAddEvent();
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.find(byText(titleEvent))
                                        .parent()
                                        .parent()
                                        .find(byText(getDate(0, "HH") + ":13" + " - " + getDate(0, "HH") + ":58"))
                                        .shouldBe(Condition.visible);
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"),
                                     false,
                                     false);
  }
}
