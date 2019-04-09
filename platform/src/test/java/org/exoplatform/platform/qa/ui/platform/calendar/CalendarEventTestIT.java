package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

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
$(byXpath("//*[@id=\"UIEventForm\"]/div[4]/button[2]")).click();
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
  public void test07_CheckDateSuggestion() {
    String fullName = "fullName" + getRandomNumber();
    String from = getDate(0, "MM/dd/yyyy HH") + ":00";
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
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.cancelQuickAddEditEvent();
    eventManagement.goToAddEventByRightClickFromMainPanel("", "");
    info("Check default time ");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.cancelQuickAddEditEvent();
    calendarManagement.deleteCalendar(fullName, true);
  }

  /**
   * Case ID:115627. Test Case Name: Send invitation. Pre-Condition: Mail is
   * configuredEmail of participant is valid to receive mail Post-Condition:
   * PENDING: Should verify link by checking manual Step Number: 1 Step Name: Step
   * 1: Open add/edit event pop up Step Description: - Select a calendar, Click
   * Setting icon of this calendar and choose [Add Event] or Click Event button on
   * action bar - Click [More Details] Input Data: Expected Outcome: Add/Edit
   * Event pop -up has 4 tabs - Details - Reminders - Participants - Schedule Step
   * number: 2 Step Name: Step 2: Add aprticipant Step Description: - Click
   * [Participants] tab - Select Privacy, Available - Add aparticipant - Select 1
   * option to Send Invitations, eg Always - Save Input Data: Expected Outcome:
   * Automatically send the invitation mail to the participants.Their statuses
   * will be updated in the Status column after they have answered the invitations
   * via emails.If the participants agree to participate (by clicking Yes in their
   * received invitation emails), their statuses will be yes.If the participants
   * do not agree to participate (by clicking No), their statuses will be no.If
   * the participants have not decided to take part in the event (by clicking Not
   * sure), their statuses will be pending.
   */

  @Test
  public void test08_SendInvitation() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Test 8: Send invitation");

    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    $(ELEMENT_QUICK_INPUT_EVENT_NAME).click();
    eventManagement.moreDetailsEvent();
    $(ELEMENT_EVENT_PARTICIPANTS_TAB).click();
    eventManagement.selectPrivacyParticipant(false);
    eventManagement.selectAvailable(EventManagement.selectAvailableOption.AVAILABLE);
    ELEMENT_ICON_ADD_PARTICIPANT.click();
    eventManagement.selectUserParticipants(DATA_USER2, content, 0);
    $(ELEMETN_INVITATION_SAVE_BUTTON).pressEnter();
    eventManagement.selectSendInvitation(PlatformBase.selectInvitationOption.ALWAYS);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Clear data");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
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
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    $(ELEMENT_QUICK_INPUT_EVENT_NAME).click();
    eventManagement.moreDetailsEvent();
    click(ELEMENT_EVENT_PARTICIPANTS_TAB);
    eventManagement.selectPrivacyParticipant(false);
    eventManagement.selectAvailable(EventManagement.selectAvailableOption.AVAILABLE);
    $(ELEMENT_INVITATION_PARTICITPANT_USER).click();
    eventManagement.selectUserParticipants(users, content, 0);
    $(ELEMETN_INVITATION_SAVE_BUTTON).pressEnter();
    eventManagement.selectSendInvitation(PlatformBase.selectInvitationOption.NEVER);
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
   * Case ID:115629. Test Case Name: [More Details] add/edit event popup.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Step 1: Check quick
   * add event form Step Description: - Select a calendar, Click Setting icon of
   * this calendar and choose [Add Event] or Click Event button on action bar
   * Input Data: Expected Outcome: - The pop up "Quick Add Event" is displayed
   * with fields + Title + Description + From + To + All day checkbox + Calendar +
   * Event Category Step number: 2 Step Name: Step 2: View more detail Step
   * Description: - Click [More Details] Input Data: Expected Outcome: Add/Edit
   * Event pop -up has 4 tabs - Details - Reminders - Participants - Schedule Step
   * number: 3 Step Name: Step 3: View details tab Step Description: - View
   * Details tab Input Data: Expected Outcome: Detail tab with fields - Title: -
   * Description: - Location: - From: - To: - All Day checkbox - Priority: -
   * Repeat: - Calendar: - Event Category: - Files: Attachment Step number: 4 Step
   * Name: Step 4: View Reminder tab Step Description: View Reminder tab Input
   * Data: Expected Outcome: Reminder tab includes: - Remind by Email - Display a
   * notification pop -up Step number: 5 Step Name: Step 5: View Participants tab
   * Step Description: View Participants tab Input Data: Expected Outcome:
   * Participants tab includes: - Privacy - Available - Add participant - Send
   * Invitations Step number: 6 Step Name: Step 5: View Schedule tab Step
   * Description: Step 5: View Schedule tab Input Data: Expected Outcome: Schedule
   * tab includes a schedule table listing available/busy time of participants
   */
  @Test
  public void test10_MoreDetailsAddeditEventPopup() {
    info("Test 10 [More Details] add/edit event popup");
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.moreDetailsEvent();
    $(ELEMENT_ADD_EDIT_EVENT_NAME).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_NOTE).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_LOCATION).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_CALENDAR).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_CATEGORY).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_PRIORITY).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_ALLDAY).parent().should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_FROM_DATE).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_TO_DATE).should(Condition.visible);
    $(ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME).parent().should(Condition.visible);
    $(ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME).parent().should(Condition.visible);
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_EVENT_ADD_ATTACHMENT).should(Condition.visible);
    $(ELEMENT_EVENT_REMINDER_TAB).click();
    $(ELEMENT_REMINDER_BY_POPUP).parent().should(Condition.visible);
    $(ELEMENT_REMINDER_BY_MAIL).parent().should(Condition.visible);
    click(ELEMENT_EVENT_PARTICIPANTS_TAB);
    $(ELEMENT_PRIVACY_PUBLIC_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_PRIVACY_PRIVATE_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_AVAILABLE_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_BUSY_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_OUTSIDE_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_SEND_INVITATION_NEVER_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_SEND_INVITATION_ALWAYS_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_SEND_INVITATION_ASK_CHECKBOX).parent().should(Condition.visible);
    $(ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_PARTICIPANT_TAB).should(Condition.visible);
    click(ELEMENT_EVENT_SCHEDULE_TAB);
    $(ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_SCHEDULE_TAB).should(Condition.visible);
    eventManagement.cancelAddEditDetailEvent();
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
  public void test16_DragDropEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String dateTime = getDate(1, "MM/dd/yyyy");

    info("Create data test");
    info("Add a event");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
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
                                     getDate(1, "MMM dd yyyy"));
  }

  /**
   * Case ID:115690. Test Case Name: Add an event in group calendar. Case
   * ID:115680. Test Case Name: Edit an event in group calendar. Case ID:115681.
   * Test Case Name: Delete an event in group calendar. Pre-Condition:
   * Post-Condition:
   */
  @Test
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
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 19 Delete an event in group calendar");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.WEEK,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MMM dd yyyy"));
  }

  @Test
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
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 18 Edit an event in group calendar");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.DAY,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.checkSuggestionEventTimeInDetailForm(null, null, 60);
    eventManagement.inputDataEventInDetailForm(titleEvent2,
                                               contentEvent2,
                                               getDate(0, "MM/dd/yyyy"),
                                               getDate(0, "MM/dd/yyyy"),
                                               false);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
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
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
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
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.WEEK,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
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

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
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

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.WEEK,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Delete data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);

  }

  @Test
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

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
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
                                               getDate(0, "MM/dd/yyyy"),
                                               getDate(0, "MM/dd/yyyy"),
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
  @Test
  public void test_22_Calendar_event_time_granularity() {
    String titleEvent = "titleEvent" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    $(ELEMENT_QUICK_INPUT_EVENT_NAME).setValue(titleEvent);
    $(ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT).setValue(getDate(0, "HH") + ":13");
    $(ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT).setValue(getDate(0, "HH") + ":58");
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

  @Tag("CAL-1461")
  @Test
  public void test23_checkClosewithEchapWhenNoText(){
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.closeWithEchap("");

  }
  @Tag("CAL-1461")
  @Test
  public void test24_checkClosewithEchapWhenText(){
    String titleEvent = "titleEvent" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.closeWithEchap(titleEvent);

  }
  @Tag("CAL-1461")
  @Test
  public void test25_checkClosewithClickonOutsideNoText(){
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.closeWithClickonOutsidethedrawer("");


  }
  @Tag("CAL-1461")
  @Test
  public void test26_checkClosewithClickonOutsidewhenText(){
    String titleEvent = "titleEvent" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.closeWithClickonOutsidethedrawer(titleEvent);


  }
}