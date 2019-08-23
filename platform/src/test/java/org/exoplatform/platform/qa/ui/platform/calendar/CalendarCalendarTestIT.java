package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.TaskManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("calendar")
@Tag("sniff")
public class CalendarCalendarTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  ManageLogInOut     manageLogInOut;

  CalendarHomePage   calendarHomePage;

  TaskManagement     taskManagement;

  CalendarManagement calendarManagement;

  EventManagement    eventManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    calendarHomePage = new CalendarHomePage(this);
    taskManagement = new TaskManagement(this);
    calendarManagement = new CalendarManagement(this);
    eventManagement = new EventManagement(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");

  }

  /**
   * Case ID:115594. Test Case Name: Check collapse/expand on left panel.
   * Pre-Condition: Calendar is displayed with default is expanded left panel
   * Post-Condition: Step Number: 1 Step Name: Step 1: Collapse panel Step
   * Description: - Go to Calendar - Click arrow icon on the left panel Input
   * Data: Expected Outcome: - Left panel is collapsed, mini calendar and calendar
   * list are not shown Step number: 2 Step Name: Step 2: Expand panel Step
   * Description: - When panel is collapsed, click arrow icon Input Data: Expected
   * Outcome: - Left panel is expanded, mini calendar and calendar list are shown
   */

  @Test
  public void test01_CheckCollapseexpandOnLeftPanel() {
    info("Test 1: Check collapse/expand on left panel");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    $(ELEMENT_SHOW_HIDE_LEFT_PANEL).click();
    $(ELEMENT_CALENDAR_PANEL).shouldNot(Condition.visible);
    $(ELEMENT_TOOLBAR_MINI_CALENDAR).shouldNot(Condition.visible);
    $(ELEMENT_SHOW_HIDE_LEFT_PANEL).click();
    $(ELEMENT_CALENDAR_PANEL).should(Condition.visible);
    $(ELEMENT_TOOLBAR_MINI_CALENDAR).should(Condition.visible);
  }

  /**
   * Case ID:115698. Test Case Name: Share a calendar. Step Number: 1 Step Name: -
   * Share a calendar Step Description: - Login and goto calendar - Click on wheel
   * icon of existing Personal calendar and select Share - Select user or group to
   * share the calendar - Click Save - Log in by user who is shared and goto
   * calendar to check - Click on the wheel icon to show action menu Input Data:
   * Expected Outcome: - can see and do following actions only:RefreshRemove
   */
  @Test
  public void test08_ShareACalendar() {
    String calendarName = "calendarName" + getRandomNumber();
    String newCalendar = "newCalendar" + getRandomNumber();
    String calendarColor = "light_purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { false };
    boolean[] newEdit = { true };
    info("Test 08 Share a calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).waitUntil(Condition.visible, Configuration.timeout);
    calendarManagement.shareCalendar(calendarName, groupShare, edit, 1);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_SHARED_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    calendarManagement.openMenuOfCalendar(calendarName);
    $(ELEMENT_CALENDAR_ADD_TASK_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_ADD_EVENT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_EDIT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_REMOVE_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_SHARE_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_IMPORT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_EXPORT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_REFRESH_MENU).should(Condition.visible);

    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);
  }

  /**
   * Case ID:115676. Test Case Name: Edit a shared calendar. Pre-Condition: - A
   * calendar exists in Personal calendar Post-Condition: Step Number: 1 Step
   * Name: - Share a calendar Step Description: - Login and goto calendar - Click
   * on wheel icon of existing Personal calendar and select Share - Select user or
   * group to share the calendar - Click Save - Log in by user who is shared and
   * goto calendar to check - Click on the wheel icon to show action menu Input
   * Data: Expected Outcome: - can see and do following actions only:RefreshRemove
   * Step Number: 1 Step Name: - Edit a shared calendar Step Description: - Share
   * a calendar to a user/ group - Click wheel icon of existing shared calendar
   * and select Share - Select user/ group and grant[Edit Permission] right - Save
   * Input Data: Expected Outcome: - Permission of shared user/ group is edited
   * successfully
   */

  /*
   * Step number: 2 Step Name: - Check shared calendar Step Description: - Login
   * with the user who is shared - Click on the wheel icon in shared calendar to
   * show menu actions Input Data: Expected Outcome: - In the action menu, there
   * are following items:Add eventAdd taskImportExportRefreshRemove
   */
  @Test
  public void test09_EditShareACalendar() {
    String calendarName = "calendarName" + getRandomNumber();
    String newCalendar = "newCalendar" + getRandomNumber();
    String calendarColor = "light_purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { false };
    boolean[] newEdit = { true };
    info("Test 08 Share a calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).waitUntil(Condition.visible, Configuration.timeout);
    calendarManagement.shareCalendar(calendarName, groupShare, edit, 1);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_SHARED_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    calendarManagement.openMenuOfCalendar(calendarName);
    $(ELEMENT_CALENDAR_ADD_TASK_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_ADD_EVENT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_EDIT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_REMOVE_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_SHARE_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_IMPORT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_EXPORT_MENU).shouldNot(Condition.visible);
    $(ELEMENT_CALENDAR_REFRESH_MENU).should(Condition.visible);

    info("Test 09: Edit a shared calendar");

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.EDIT);
    calendarManagement.inputDataInDetailTabCalendarForm(newCalendar, newCalendar, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", newCalendar))).should(Condition.visible);
    calendarManagement.shareCalendar(newCalendar, groupShare, newEdit, 1);
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", newCalendar))).should(Condition.visible);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    calendarManagement.openMenuOfCalendar(newCalendar);
    $(ELEMENT_CALENDAR_REMOVE_SHARE_CALENDAR).should(Condition.visible);
    $(ELEMENT_CALENDAR_IMPORT_MENU).should(Condition.visible);
    $(ELEMENT_CALENDAR_EXPORT_MENU).should(Condition.visible);
    $(ELEMENT_CALENDAR_REFRESH_MENU).should(Condition.visible);
    $(ELEMENT_CALENDAR_ADD_EVENT_MENU).should(Condition.visible);
    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(newCalendar, true);
  }

  /**
   * Case ID:115675. Test Case Name: Remove user/group from shared list.
   * Pre-Condition: - A calendar is sharing to some users Post-Condition: Step
   * Number: 1 Step Name: Step 1: Remove User from Shared list Step Description: -
   * Click on wheel icon of existing Shared calendar and select Share -
   * Selectuser/ group you want to remove and click Delete icon - Click Save -
   * Login with user who is removed from shared list and goto Calendar Input Data:
   * Expected Outcome: - He/ she cannot view shared calendar in shared list
   */
  @Test
  public void test10_RemoveUsergroupFromSharedList() {
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "light_purple";
    String[] groupShare = { DATA_USER2 };
    boolean[] edit = { true };
    info("Test 10: Remove user/group from shared list");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    calendarManagement.shareCalendar(calendarName, groupShare, edit, 1);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.removeUserGrooupFromShareCalendar(calendarName, groupShare);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).shouldNot(Condition.visible);
    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);
  }

  /**
   * Case ID:115693. Test Case Name: Export calendar. Case ID:115692. Test Case
   * Name: Import calendar. Pre-Condition: Post-Condition: Step Number: 1 Step
   * Name: - Step Description: Step 1: Export Calendar Input Data: - Mouse over
   * then click on wheel icon of an existing calendar and select Export - Click
   * Save Expected Outcome: Export successfully with .ics file
   */
  @Test
  public void test12_ExportCalendar() {
    String titleEvent = "titleEvent" + "115693";
    String attachment = getRandomNumber() + ".ics";
    String calendarName = "calendarName" + "115693";
    String calendarColor = "purple";
    info("Create data test");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byXpath(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName))).should(Condition.visible);
    calendarManagement.executeActionCalendar(calendarName, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent, titleEvent, getDate(0, "MM/dd/yyyy"), getDate(0, "MM/dd/yyyy"), false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 12 Export calendar");

    calendarManagement.exportCalendar(calendarName, attachment);
    calendarManagement.deleteCalendar(calendarName, true);

    info("Check the task is not present");
    calendarHomePage.verifyIsNotPresentEventTask(titleEvent,
                                                 CalendarHomePage.selectViewOption.LIST,
                                                 CalendarHomePage.selectDayOption.DETAILTIME);

  }

  /**
   * Case ID:115692. Test Case Name: Import calendar. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: - Step Description: Step 1: Import
   * Calendar Input Data: - Click on Calendar actions and select Import - Select
   * format - Upload file - Choose calendar - Click Save Expected Outcome: Import
   * successfully with: - Valid name - All events/task in the imported file
   */
  @Test
  @BugInPLF("CAL-1295")
  public void test13_ImportCalendar() {
    String titleEvent = "testEvent";
    String calendarName = "calendarName" + "115693";

    info("Test 13: Import calendar");

    calendarManagement.importCalendar(calendarName, null, null);
    info("Check the task is present ");
    refresh();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    info("Delete file and task");
    calendarManagement.deleteCalendar(calendarName);
  }
}
