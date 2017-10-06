package org.exoplatform.platform.qa.ui.calendar.smoke;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("calendar")
@Tag("smoke")
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
    calendarManagement = new CalendarManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    eventManagement = new EventManagement(this);
    manageLogInOut = new ManageLogInOut(this);

  }

  /**
   * Case ID:115684. Test Case Name: Add an event in personal calendar
   */
  @Test
  public void test13_AddnEventInPersonalCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String calendar = "calendar" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    info("Test 13 Add an event in personal calendar");
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
    calendarManagement.executeActionCalendar(calendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
  }


}
