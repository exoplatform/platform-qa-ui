package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.ELEMENT_EVENT_TASK_LIST_VIEW;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

public class CalendarSearchTestIT extends Base {
  HomePagePlatform homePagePlatform;

  EventManagement  eventManagement;

  CalendarHomePage calendarHomePage;

  ManageLogInOut   manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    eventManagement = new EventManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * Case ID:115694. Test Case Name: Quick search. Pre-Condition: Some
   * events/tasks contaning the word "dinner" already exists in Calendar
   * Post-Condition: Step Number: 1 Step Name: - Step 1: Quick search Step
   * Description: - Input the word "dinner" in search text box - Hit Enter Input
   * Data: Expected Outcome: Return search results matching with search keyword
   * with no error
   */
  @Test
  public void test01_QuickSearch() {
    String titleEvent = "titleEvent" + " dinner " + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";
    String key = "dinner";
    String key1 = "dinnertest";

    info("create data test");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 1: Quick search");

    calendarHomePage.quickSearchCalendar(key);
    $(byXpath(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).click();
    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

    calendarHomePage.quickSearchCalendar(key1);
    $(byXpath(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent))).shouldNot(Condition.visible);
    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).click();
    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    info("Delete data");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, defaultFormatDate));
  }

  /**
   * Case ID:115695. Test Case Name: Advanced search. Pre-Condition: Some
   * events/tasks containing the word "dinner" already exist. Post-Condition:
   */
  @Test
  public void test02_AdvancedSearch() {
    String titleEvent = "titleEvent" + " dinner " + getRandomNumber();
    String content = "content" + getRandomNumber();
    String defaultFormatDate = "MM/dd/yyyy";
    String key = "dinner";
    String key1 = "dinnertest";

    info("create data test");
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              content,
                                              getDate(0, defaultFormatDate),
                                              getDate(0, defaultFormatDate),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 1: Quick search");

    calendarHomePage.quickSearchCalendar(key);
    calendarHomePage.advanceSearchCalendar(key);
    $(byXpath(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).click();

    calendarHomePage.quickSearchCalendar(key1);
    calendarHomePage.advanceSearchCalendar(key1);
    $(byXpath(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent))).shouldNot(Condition.visible);
    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).click();

    info("Delete data");
    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, defaultFormatDate));

  }

}
