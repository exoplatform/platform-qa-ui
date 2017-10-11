package org.exoplatform.platform.qa.ui.calendar.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

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
   * Case ID:115671. Test Case Name: Add Personal Calendar.
   */
  @Test
  public void test02_AddPersonalCalendar() {
    String calendarName = "calendarName AddPersonalCalendar" ;
    String calendarColor = "light_purple";

    info("Test 2: Add Personal Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add a personal calendar
     * Input Data: - Click + iconon the top left pane and selectAdd Calendar - Fill
     * required fields in Calendar Details tab - Save Expected Outcome: - The
     * calendar is added into Personal Calendar
     */
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);

  }

   /**
   * Case ID:115673. Test Case Name: Add Group Calendar. Case ID:115674. Test Case
   * Name: Edit Group Calendar. Case ID:115697. Test Case Name: Delete Group
   * Calendar. Pre-Condition: Post-Condition:
   */
  @Test
  public void test05_AddGroupCalendar() {
    String groupCalendar = "/platform/users";
    String calendarName = "calendarName AddGroupCalendar";
    String newCalendar = "newCalendar AddGroupCalendar" ;
    String calendarColor = "light_purple";

    info("Test 5: Add Group Calendar");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add Group Calendar
     * Input Data: + Open Add Calendar form+ Input Display Name+ In Show in Groups
     * tab, choose group(s) and select User name/ membership+ Click Save Expected
     * Outcome: - The group calendar is added normally and displayed at the group
     * calendar
     */
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.selectGroupInGroupTabCalendarForm(groupCalendar, true);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);

  }


}
