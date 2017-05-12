package org.exoplatform.platform.qa.ui.calendar.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

@Tag("calendar")
@Tag("smoke")
public class CalendarCalendarTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  CalendarManagement calendarManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
  }

  /**
   * Case ID:115671. Test Case Name: Add Personal Calendar. Case ID:115672. Test
   * Case Name: Edit Personal Calendar. Case ID:115696. Test Case Name: Delete
   * Personal Calendar. Pre-Condition: Post-Condition:
   */
  @Test
  public void test02_03_04_AddEditDeletePersonalCalendar() {
    String calendarName = "calendarName" + getRandomNumber();
    String newCalendar = "newCalendar" + getRandomNumber();
    String calendarColor = "light_purple";

    info("Test 2: Add Personal Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add a personal
     * calendar Input Data: - Click + iconon the top left pane and selectAdd
     * Calendar - Fill required fields in Calendar Details tab - Save Expected
     * Outcome: - The calendar is added into Personal Calendar
     */
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);
    info("Test 3: Edit Personal Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Edit Personal
     * Calendar Input Data: - Add a personal calendar or choose an existing one.
     * - Mouse over calendar then click on wheelicon of an existing calendar and
     * select Edit - Edit some value - Save Expected Outcome: - The calendar is
     * updated with the changed value
     */
    calendarManagement.editCalendar(calendarName, newCalendar, newCalendar, calendarColor, null);
    calendarManagement.saveAddCalendar();
    $(byText(newCalendar)).waitUntil(Condition.appears, 10000);

    info("Test 4 Delete Personal Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Delete Personal
     * Calendar Input Data: - Create a personal calendar or choose an existing
     * one - Mouse over calendar then click on wheelicon of an existing calendar
     * and select Remove - Click OK at confirmation Expected Outcome: - The
     * calendar is removed from the personal calendar group
     */
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(newCalendar, true);
  }

  /**
   * Case ID:115673. Test Case Name: Add Group Calendar. Case ID:115674. Test
   * Case Name: Edit Group Calendar. Case ID:115697. Test Case Name: Delete
   * Group Calendar. Pre-Condition: Post-Condition:
   */
  @Test
  public void test05_06_07_AddEditDeleteGroupCalendar() {
    String groupCalendar = "/platform/users";
    String calendarName = "calendarName" + getRandomNumber();
    String newCalendar = "newCalendar" + getRandomNumber();
    String calendarColor = "light_purple";

    info("Test 5: Add Group Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add Group Calendar
     * Input Data: + Open Add Calendar form+ Input Display Name+ In Show in
     * Groups tab, choose group(s) and select User name/ membership+ Click Save
     * Expected Outcome: - The group calendar is added normally and displayed at
     * the group calendar
     */
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.selectGroupInGroupTabCalendarForm(groupCalendar, true);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);
    info("Test 6: Edit Group Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Edit Group Calendar
     * Input Data: - Add a Group calendar - Edit a group calendar by mouse over
     * then click wheel icon of existing group calendar and select Edit Expected
     * Outcome: - The group calendars are updated normally.
     */
    calendarManagement.editCalendar(calendarName, newCalendar, newCalendar, calendarColor, null);
    calendarManagement.saveAddCalendar();
    $(byText(newCalendar)).waitUntil(Condition.appears, 10000);
    info("Test 7 Delete Group Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Delete Group
     * Calendar Input Data: - Add a group calendar - Delete a public calendar by
     * mouse over then click wheel icon of existing group calendar and select
     * Remove - Click OK at confirmation message Expected Outcome: - The group
     * calendar is removed from the group calendar
     */

    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(newCalendar, true);
  }

}
