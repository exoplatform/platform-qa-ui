package org.exoplatform.platform.qa.ui.calendar.smoke;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_CONTEXT_MENU_VIEW;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("calendar")
@Tag("smoke")

public class CALEventDeleteTestIT extends Base {

  HomePagePlatform homePagePlatform;

  CalendarManagement calendarManagement;

  EventManagement eventManagement;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    eventManagement = new EventManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signIn(PLFData.username, PLFData.password);

}

  /**
   * <li>Case ID:116397.</li>
   * <li>Test Case Name: Delete event of group calendar.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */

  @Test
  public void test01_DeleteEventOfGroupCalendar() {

    info("Delete event of group calendar");
    /*
     * Step Number: 1 Step Name: Step 1: Add event Step Description: - Add new group
     * calendar - Assign calendar for an user with edit right - Add new event on
     * this group calendar Input Data: Expected Outcome: - Event is added
     * successfully - Group calendar and its event(s) are displayed for all member
     * of its group(s)
     */

    info("Create a new calendar");
    String userGroup = "/platform/users";
    String calendar = "calendar" + getRandomNumber();
    String DATA_USER2 = "mary";
    String DATA_USER3 = "james";
    String DATA_PASS = "123456";
    String[] user = {DATA_USER2};
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.selectGroupInGroupTabCalendarForm(userGroup, true);
    calendarManagement.selectUserEditPermissionInGroup(user, 1);
    calendarManagement.saveAddCalendar();

    info("Add an Event");
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputBasicQuickEvent(titleEvent, content, calendar);
    eventManagement.saveQuickAddEvent();

    /*
     * Step number: 2 Step Name: Step 2: Edit event by member of group without edit
     * right Step Description: - Login by a member of group calendar who does not
     * have edit right on that calendar - Right click on group calendar's event
     * Input Data: Expected Outcome: Only View action is displayed
     */

    // manageLogInOut.signOut();
    manageLogInOut.signIn(PLFData.DATA_USER3, PLFData.DATA_PASS);
    refresh();
    sleep(2000);
    homePagePlatform.goToCalendarPage();
    // rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
    $(byText(titleEvent)).contextClick();
    $(ELEMENT_CONTEXT_MENU_VIEW).waitUntil(Condition.appears, 10000);
    $(byClassName("eventAction")).find(byText("Delete")).shouldNot(Condition.exist);

    /*
     * Step number: 3 Step Name: Step 3: Edit event by member of group with edit
     * right Step Description: - Login by member who is assign edit right on above
     * group calendar - Right click on event of shared calendar and select Delete
     * Input Data: Expected Outcome: Event is removed from group calendar
     */
    // manageLogInOut.signOut();
    manageLogInOut.signIn(PLFData.DATA_USER2, PLFData.DATA_PASS);
    refresh();
    sleep(2000);
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteTaskEvent(titleEvent);

  }

}
