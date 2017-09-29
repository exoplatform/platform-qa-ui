package org.exoplatform.platform.qa.ui.calendar.smoke;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

@Tag("calendar")
@Tag("smoke")
public class CALManagePersonalCalendarTestIT extends Base {

  HomePagePlatform   homePagePlatform;

  CalendarManagement calendarManagement;

  CalendarHomePage   calendarHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    calendarHomePage = new CalendarHomePage(this);
  }

  /**
   * <li>Case ID:116384.</li>
   * <li>Test Case Name: Add new calendar with valid information.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test03_AddNewCalendarWithValidInformation() {
    info("Test 3: Add new calendar with valid information");
    /*
     * Step Number: 1 Step Name: Step 1: Add calendar Step Description: Click
     * Calendar action icon and select Add calendar Input Data: Expected Outcome:
     * Add new calendar form is shown
     */

    /*
     * Step number: 2 Step Name: Step 2: Add calendar with valid information Step
     * Description: - Input valid values for all fields in Calendar details tab -
     * Click Save Input Data: Expected Outcome: Calendar is saved successfully and
     * listed in personal calendar box
     */
    info("Create a new calendar");
    String calendar = "calendar" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar)).waitUntil(Condition.appears, 10000);
    calendarManagement.deleteCalendar(calendar, true);
  }

  /**
   * <li>Case ID:116450.</li>
   * <li>Test Case Name: Edit a personal calendar with valid value.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */

}
