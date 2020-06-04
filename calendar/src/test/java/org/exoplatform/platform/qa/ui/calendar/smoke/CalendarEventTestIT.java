package org.exoplatform.platform.qa.ui.calendar.smoke;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_ADD_EDIT_EVENT_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("calendar")
@Tag("smoke")
public class CalendarEventTestIT extends Base {
  HomePagePlatform homePagePlatform;

  ManageLogInOut manageLogInOut;

  CalendarHomePage calendarHomePage;

  EventManagement eventManagement;

  CalendarManagement calendarManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    eventManagement = new EventManagement(this);
    manageLogInOut = new ManageLogInOut(this);

  }

  /**
   * ID:115677. Test Case Name: Edit an Event in personal calendar. Case
   */
  @Test
  public void test01_AddEditAnEventInPersonalCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String calendar = "calendar" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    info("Add an event in personal calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add an event Input
     * Data: - Select a personal calendar - Click Setting icon of this personal
     * calendar and choose [Add Event] Expected Outcome: - The pop up
     * "Quick Add Event" is displayed - The Default Start date "From" is set to
     * Today (System date) -The default duration for event is 1 hour
     */

    /*
     * Step number: 2 Step Name: Step Description: Step 2: save Input Data: - Input
     * other values - Save Expected Outcome: Event is created in personal calendar
     */
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    sleep(2000);
    calendarManagement.executeActionCalendar(calendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
            contentEvent,
            getDate(0, "MM/dd/yyyy" + " HH"),
            getDate(0, "MM/dd/yyyy" + " HH"),
            false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME);

    info("Edit an Event in personal calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Edit an event Input
     * Data: - Add an event - Edit an event by right click on a existing event and
     * select Edit - Update some values - Change From time Expected Outcome: - To
     * time is automatically set = From Time + 1hour
     */

    /*
     * Step number: 2 Step Name: Step Description: Step 2: Save Input Data: - Save
     * Expected Outcome: Event is saved successfully in personal calendar
     */
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
            CalendarHomePage.selectViewOption.WEEK,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MMM dd yyyy"));
    eventManagement.checkSuggestionEventTimeInDetailForm(null, null, 60);
    eventManagement.inputDataEventInDetailForm(titleEvent2,
            contentEvent2,
            getDate(0, "MM/dd/yyyy" + " HH"),
            getDate(0, "MM/dd/yyyy" + " HH"),
            false);
    $(ELEMENT_ADD_EDIT_EVENT_NAME).click();
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME);

    info("Delete an Event in personal calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Delete an event Input
     * Data: - Add an event - Delete an event right click on an existing event and
     * select Delete - Click OK at confirmation message Expected Outcome: - The
     * event is removed normally.
     */
    calendarHomePage.deleteEventTask(titleEvent2,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));
    calendarManagement.deleteCalendar(calendar, false);
  }

  /*
   * Case ID:115684. Test Case Name: Add an event in personal calendar
   * Step Number: 1 Step Name: - Step Description: Step 1: Add an event Input
   * Data: - Select a personal calendar - Click Setting icon of this personal
   * calendar and choose [Add Event] Expected Outcome: - The pop up
   * "Quick Add Event" is displayed - The Default Start date "From" is set to
   * Today (System date) -The default duration for event is 1 hour
   * Step number: 2 Step Name: Step Description: Step 2: save Input Data: - Input
   * other values - Save Expected Outcome: Event is created in personal calendar
   * Step Number: 1 Step Name: - Step Description: Step 1: Delete an event Input
   * Data: - Add an event - Delete an event right click on an existing event and
   * select Delete - Click OK at confirmation message Expected Outcome: - The
   * event is removed normally.
   */
  @Test
  public void test02_AddnEventInPersonalCalendarWithSpecialCharacter() {
    String titleEvent = "l'event" + getRandomNumber();
    String calendar = "calendar" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    info("Add an event in personal calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    sleep(2000);
    calendarManagement.executeActionCalendar(calendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
            contentEvent,
            getDate(0, "MM/dd/yyyy" + " HH"),
            getDate(0, "MM/dd/yyyy" + " HH"),
            false);
    eventManagement.saveQuickAddEvent();
    $(byText(titleEvent)).should(Condition.exist);
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME);

    info("Delete an Event in personal calendar");

    calendarHomePage.deleteEventTask(titleEvent,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));
    calendarManagement.deleteCalendar(calendar, false);

  }

}
