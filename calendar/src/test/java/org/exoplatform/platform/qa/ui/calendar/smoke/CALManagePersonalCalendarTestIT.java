package org.exoplatform.platform.qa.ui.calendar.smoke;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("calendar")
@Tag("smoke")
public class CALManagePersonalCalendarTestIT extends Base {

  HomePagePlatform homePagePlatform;

  CalendarManagement calendarManagement;

  CalendarHomePage calendarHomePage;

    ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signIn(PLFData.username, PLFData.password);
}

  /**
   * <li>Case ID:116450.</li>
   * <li>Test Case Name: Edit a personal calendar with valid value.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_EditAPersonalCalendarWithValidValue() {
    info("Edit a personal calendar with valid value");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Show edit personal
     * calendar form Input Data: - Create new personal calendar[ Details ] - Right
     * click on the calendar and select Edit Expected Outcome: - Edit personal
     * calendar form is shown Calendar details tab only, Groups tab is hidden -
     * Current informations of that calendar are displayed in form
     */

    info("Create a new calendar");
    String calendar = "calendar" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    sleep(2000);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar)).waitUntil(Condition.appears, 10000);
    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Complete editing
     * personal calendar Input Data: - Make changes (name, description color) -
     * Click Save Expected Outcome: - Changes are saved - The calendar will be
     * updated with new value
     */
    String calendar1 = "calendar1" + getRandomNumber();
    calendarManagement.editCalendar(calendar, calendar1, calendar1, "light_blue", null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar1)).waitUntil(Condition.appears, 10000);
    $(byClassName("light_blue")).waitUntil(Condition.appears, 10000);
    calendarManagement.deleteCalendar(calendar1, true);

  }

}
