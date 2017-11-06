package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
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
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
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
   */

  /*
   * Step number: 2 Step Name: Step 2: Remove attachment Step Description: - Click
   * [Delete] icon - Click Save Input Data: Expected Outcome: - Attachment is
   * deleted
   */
  @Test
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
    eventManagement.moreDetailsEvent();

    info("Add attachment");
    eventManagement.inputDataEventInDetailForm(titleEvent, content, getDate(0, "MM/dd/yyyy"), getDate(0, "MM/dd/yyyy"), false);
    $(ELEMENT_ADD_EDIT_EVENT_NAME).click();
    $(ELEMENT_EVENT_ADD_ATTACHMENT).click();
    $(byClassName("uploadContainer")).find(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
    $(byClassName("progressBarFrame")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(ELEMENT_ATTACHMENT_SAVE_BUTTON).click();
    eventManagement.saveAddEventDetails();
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
    eventManagement.saveAddEventDetails();

    info("Delete data");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.DAY,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
  }

  @Test
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
    eventManagement.moreDetailsEvent();

    info("Add attachment");
    eventManagement.inputDataEventInDetailForm(titleEvent, content, getDate(0, "MM/dd/yyyy"), getDate(0, "MM/dd/yyyy"), false);
    $(ELEMENT_ADD_EDIT_EVENT_NAME).click();
    $(ELEMENT_EVENT_ADD_ATTACHMENT).click();
    $(byClassName("uploadContainer")).find(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
    $(byClassName("progressBarFrame")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(ELEMENT_ATTACHMENT_SAVE_BUTTON).click();
    eventManagement.saveAddEventDetails();
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
    eventManagement.saveAddEventDetails();

    info("Delete data");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.DAY,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     null);
  }

  /**
   * Case ID:115622. Test Case Name: Check busy time on schedule tab.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Add new
   * event Step Description: - Login as John for ex - Go to Calendar - Click
   * [Event] or right click - -> [New Event] - Input start and end time: from 9h00
   * to 10h00 for example - Click Save Input Data: Expected Outcome: - New event
   * is added successfully Step number: 2 Step Name: Step 2: Add participant to
   * event Step Description: - Login as other user (Jack for ex) - Go to Calendar
   * - Click [Event] or right click, select [Event] - Click [More details], open
   * Schedule tab - Click Add participant and select John to add Input Data:
   * Expected Outcome: - John is displayed in list with busy time is same as event
   * created at step 1
   */
  @Test
  public void test03_CheckBusyTimeOnScheduleTab() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 3: Check busy time on schedule tab");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent, content, getDate(0, "MM/dd/yyyy"), getDate(0, "MM/dd/yyyy"), true);
    $(ELEMENT_QUICK_INPUT_EVENT_NAME).click();
    eventManagement.moreDetailsEvent();
    $(ELEMENT_EVENT_SCHEDULE_TAB).click();
    eventManagement.addParticipants(DATA_USER1, 1);
    eventManagement.checkBusyTimeOfUser(DATA_USER1, getDate(0, "HH") + ":00", getDate(0, "HH") + ":30");

    info("clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));
  }

  /**
   * Case ID:115623. Test Case Name: Edit schedule of event. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Step 1: Open add/edit event pop up
   * Step Description: - Select a calendar, Click Setting icon of this calendar
   * and choose [Add Event] or Click Event button on action bar - Input start and
   * end time - Click [More Details] Input Data: Expected Outcome: Add/Edit Event
   * pop -up has 4 tabs - Details - Reminders - Participants - Schedule Step
   * number: 2 Step Name: Step 2: Add aprticipant Step Description: - Click
   * [Schedule] tab - Change time of event on schedule tab Input Data: Expected
   * Outcome: - Schedule is updated
   */
  @Test
  public void test04_EditScheduleOfEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Test 4: Edit schedule of event");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent, content, getDate(0, "MM/dd/yyyy"), getDate(0, "MM/dd/yyyy"), false);
    info("Check default time ");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    $(ELEMENT_QUICK_INPUT_EVENT_NAME).click();
    eventManagement.moreDetailsEvent();
    click(ELEMENT_EVENT_SCHEDULE_TAB);
    eventManagement.changeTimeEventFromScheduleTab(PlatformBase.selectArrowOption.NEXT,
                                                   getDate(1, "HH") + ":00",
                                                   getDate(1, "HH") + ":30");
    $(ELEMENT_EVENT_DETAILS_TAB).click();
    eventManagement.checkSuggestionEventTimeInDetailForm(getDate(1, "MM/dd/yyyy HH") + ":00",
                                                         getDate(1, "MM/dd/yyyy HH") + ":30",
                                                         30);
    eventManagement.saveAddEventDetails();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));

  }

  /**
   * Case ID:115624. Test Case Name: Check start/end time of event on schedule.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Open
   * add/edit event pop up Step Description: - Select a calendar, Click Setting
   * icon of this calendar and choose [Add Event] or Click Event button on action
   * bar - Input start and end time - Click [More Details] Input Data: Expected
   * Outcome: Add/Edit Event pop -up has 4 tabs - Details - Reminders -
   * Participants - Schedule Step number: 2 Step Name: Step 2: Add aprticipant
   * Step Description: - Click [Schedule] tab - Check time on schedule tab Input
   * Data: Expected Outcome: - Time on schedule tab is correct (input at step 1)
   */
  @Test
  public void test05_CheckStartEndTimeOfEventOnSchedule() {
    String titleEvent = "" + getRandomNumber();
    String contentEvent = "" + getRandomNumber();
    info("Test 5: Check start/end time of event on schedule");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy HH") + ":00",
                                              getDate(0, "MM/dd/yyyy HH") + ":30",
                                              false);
    $(ELEMENT_QUICK_INPUT_EVENT_NAME).click();
    eventManagement.moreDetailsEvent();

    $(ELEMENT_EVENT_SCHEDULE_TAB).click();
    eventManagement.checkScheduleTimeOfUser(getDate(0, "MM/dd/yyyy HH") + ":00", getDate(0, "MM/dd/yyyy HH") + ":30");
    eventManagement.cancelAddEditDetailEvent();
  }

  /**
   * Case ID:115625. Test Case Name: Check privacy. Pre-Condition: Post-Condition:
   * Step Number: 1 Step Name: Step 1: Open add/edit event pop up Step
   * Description: - Select a calendar, Click Setting icon of this calendar and
   * choose [Add Event] or Click Event button on action bar - Click [More Details]
   * Input Data: Expected Outcome: Add/Edit Event pop -up has 4 tabs - Details -
   * Reminders - Participants - Schedule Step number: 2 Step Name: Step 2: Add
   * aprticipant Step Description: - Click [Participants] tab - Select Privacy
   * option - Click Save Input Data: Expected Outcome: - Event is saved with new
   * privacy option
   */
  @Test
  public void test06_CheckPrivacy() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 6: Check privacy");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent, content, getDate(0, "MM/dd/yyyy"), getDate(0, "MM/dd/yyyy"), false);
    $(ELEMENT_QUICK_INPUT_EVENT_NAME).click();
    eventManagement.moreDetailsEvent();

    click(ELEMENT_EVENT_PARTICIPANTS_TAB);
    eventManagement.selectPrivacyParticipant(true);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));
  }

}
