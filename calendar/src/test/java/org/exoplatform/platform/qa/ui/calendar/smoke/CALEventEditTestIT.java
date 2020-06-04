package org.exoplatform.platform.qa.ui.calendar.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
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
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_CONTEXT_MENU_VIEW;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("calendar")
@Tag("smoke")
public class CALEventEditTestIT extends Base {

  HomePagePlatform homePagePlatform;

  CalendarManagement calendarManagement;

  EventManagement eventManagement;

  ManageLogInOut manageLogInOut;

  CalendarHomePage calendarHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    eventManagement = new EventManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    calendarHomePage = new CalendarHomePage(this);
  }

  /**
   * <li>Case ID:116425.</li>
   * <li>Test Case Name: Edit event of group calendar.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_EditEventOfGroupCalendar() {
    info("Edit event of group calendar");
    /*
     * Step Number: 1 Step Name: Step 1: Add event Step Description: - Add new group
     * calendar - Assign calendar for an user with edit right - Add new event on
     * this group calendar Input Data: Expected Outcome: - Event is added
     * successfully - Group calendar and its event(s) are displayed for all member
     * of its group(s)
     */
    String DATA_USER2 = "mary";
    String DATA_USER1 = "john";
    String DATA_PASS = "123456";
    info("Create a new calendar");
    String userGroup = "/platform/users";
    String[] user = {DATA_USER1};
    String calendar = "calendar" + getRandomNumber();
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
     * Input Data: Expected Outcome: Only "View" action is displayed.
     */
    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToCalendarPage();
    // rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
    $(byText(titleEvent)).contextClick();
    $(ELEMENT_CONTEXT_MENU_VIEW).waitUntil(Condition.appears, 10000);
    $(byClassName("eventAction")).find(byText("Delete")).shouldNot(Condition.exist);
    $(byClassName("eventAction")).find(byText("Edit")).shouldNot(Condition.exist);

    /*
     * Step number: 3 Step Name: Step 3: Edit event by member of group with edit
     * right Step Description: - Login by member who is assign edit right on above
     * group calendar - Right click on event of shared calendar and select Edit or
     * drag and drop event in working pane Input Data: Expected Outcome: - Edit
     * event form appears with current information of event in form (if select Edit)
     * or event is changed date/time as drag and drop - Edit event successfully by
     * shared user with edit right
     */
    manageLogInOut.signIn(DATA_USER1, PLFData.DATA_PASS2);
    info("Edit the Event");
    homePagePlatform.goToCalendarPage();
    String titleEvent1 = "titleEvent1" + getRandomNumber();
    calendarManagement.openEditEventTaskPopup(titleEvent, CalendarHomePage.selectViewOption.LIST);
    eventManagement.inputBasicDetailEvent(titleEvent1, null);
    eventManagement.saveAddEventDetails();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    $(byText(titleEvent1)).should(Condition.exist);

    calendarManagement.deleteCalendar(calendar);

  }

  /**
   * <li>Case ID:116441.</li>
   * <li>Test Case Name: Edit event with valid data.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test02_EditEventWithValidData() {
    info("Edit event with valid data");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add event Input Data: -
     * Add category [ Details ] - calendar [ Details ] - event [ Details ] Expected
     * Outcome: All events/tasks of selecting calendar are displayed in list
     */
    info("Create a new category");
    String categoryName = "categoryName" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarManagement.addEventCategory(categoryName);

    info("Create a new calendar");
    String calendar = "calendar" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar)).waitUntil(Condition.appears, 10000);
    info("Add an Event");
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent, content, null, null, false, calendar);
    eventManagement.saveQuickAddEvent();

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Show form to edit event
     * Input Data: - Select view type - Right click on event that you have edit
     * right from list/working pane and select Edit Expected Outcome: - Edit event
     * form appears with current information of event in form - all events/tasks in
     * list are kept in alphabetical order if they was sorted before
     */

    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Edit event Input Data:
     * Do some valid changes and clickSave Expected Outcome: Event is changed and
     * updated in list/working pane
     */
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    calendarManagement.openEditEventTaskPopup(titleEvent, CalendarHomePage.selectViewOption.LIST);
    eventManagement.inputDataEventInDetailForm(titleEvent2, titleEvent2, null, null, false, null, categoryName);
    eventManagement.saveAddEventDetails();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    if (($(byText(titleEvent2)).is(Condition.not(Condition.exist)))) {
      ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON.click();
      $(byText(titleEvent2)).waitUntil(Condition.appears, Configuration.timeout);
    } else {
      $(byText(titleEvent2)).waitUntil(Condition.appears, Configuration.timeout);
    }
    calendarManagement.deleteCalendar(calendar);
    calendarManagement.deleteEventCategory(categoryName);

  }

}
