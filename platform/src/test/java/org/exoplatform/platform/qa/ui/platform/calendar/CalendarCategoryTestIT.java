package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("calendar")
@Tag("sniff")
public class CalendarCategoryTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  ManageLogInOut     manageLogInOut;

  CalendarManagement calendarManagement;

  EventManagement    eventManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    calendarManagement = new CalendarManagement(this);
    eventManagement = new EventManagement(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");

  }

  /**
   * Case ID:115653. Test Case Name: Add Event Category. Case ID:115654. Test Case
   * Name: Edit Event Category. Case ID:115655. Test Case Name: Delete Event
   * Category. Pre-Condition: Post-Condition: Step Number: 1 Step Name: - Step
   * Description: Step 1: Show Event categories form Input Data: Click on Calendar
   * Action icon from left pane and select Add event category in menu Expected
   * Outcome: Event Categories form is displayed with a list of all existing event
   * categories and a sub form to add new category* Step number: 2 Step Name: -
   * Step Description: Step 2: Add new category Input Data: - Input valid data
   * into text field. - Click Add Expected Outcome: - Add category successfully -
   * Added category is displayed in list above - This category is visible while
   * creating an event/task and used for the creator only* Step number: 3 Step
   * Name: - Step Description: Step 3: Edit category with valid data Input Data: -
   * Make change on Event category name - Click Update Expected Outcome: - Change
   * is saved, all values disappears from edit form - Existing categorylist also
   * is updated Step number: 2 Step Name: - Step 2: Delete category Step
   * Description: - Click on Calendar icon from left panel and select Add event
   * category in menu - Click Delete icon corresponding to thecategory you want to
   * deletein Event categories form - Click OKto confirm Input Data: Expected
   * Outcome: - Confirm message is shown. - This category is deleted from the
   * category management list - Event(s)/task(s) that was added into this category
   * are not deleted and they have "all" as category
   */

  @Test
  public void test01_AddEventCategory() {
    String oldNameCategory = "oldNameCategory" + getRandomNumber();

    info("Test 1: Add Event Category");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();

    calendarManagement.addEventCategory(oldNameCategory);
    eventManagement.goToAddEventFromActionBar();
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).find(byText(oldNameCategory));
    eventManagement.cancelQuickAddEditEvent();

    info("Test 3: Delete Event Category");

    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteEventCategory(oldNameCategory);
  }

  @Test
  public void test02_EditEventCategory() {
    String oldNameCategory = "oldNameCategory" + getRandomNumber();
    String newNameCategory = "newNameCategory" + getRandomNumber();

    info("Test 1: Add Event Category");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();

    calendarManagement.addEventCategory(oldNameCategory);
    eventManagement.goToAddEventFromActionBar();
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).find(byText(oldNameCategory));
    eventManagement.cancelQuickAddEditEvent();

    info("Test 2: Edit Event Category");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCATEGORY);
    calendarManagement.editEventCategory(oldNameCategory, newNameCategory);
    eventManagement.goToAddEventFromActionBar();
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).find(byText(newNameCategory));
    eventManagement.cancelQuickAddEditEvent();

    info("Test 3: Delete Event Category");

    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteEventCategory(newNameCategory);
  }

  @Test
  public void test03_DeleteEventCategory() {
    String oldNameCategory = "oldNameCategory" + getRandomNumber();
    String newNameCategory = "newNameCategory" + getRandomNumber();

    info("Test 1: Add Event Category");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();

    calendarManagement.addEventCategory(oldNameCategory);
    eventManagement.goToAddEventFromActionBar();
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).find(byText(newNameCategory));
    eventManagement.cancelQuickAddEditEvent();

    info("Test 3: Delete Event Category");

    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteEventCategory(oldNameCategory);
  }
}
