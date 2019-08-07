package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

/**
 * Created by ilyes on 08/11/17.
 */
@Tag("smoke")
@Tag("calendar")
public class CalendarBasicTestsInSpaceTestIT extends Base {
  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  SpaceHomePage    spaceHomePage;

  ManageLogInOut   manageLogInOut;

  EventManagement  eventManagement;

  CalendarHomePage calendarHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    eventManagement = new EventManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    manageLogInOut = new ManageLogInOut(this);

  }

  @Test
  public void test01_AddEventInSpace() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceManagement.goToAgendaTab();
    info("Test 13 Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT),Condition.visible,1000);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title",$(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All day",$(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location",$(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event",$(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants",$(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear",$(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save",$(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel",$(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);


    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test02_EditEventInSpace() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceManagement.goToAgendaTab();
    info("Test 13 Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    $(byClassName("createEvent")).waitUntil(Condition.visible, Configuration.timeout).click();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy"),
                                              getDate(0, "MM-dd-yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.WEEK,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.inputDataEventInDetailForm(titleEvent2,
                                               contentEvent2,
                                               getDate(0, "MM-dd-yyyy"),
                                               getDate(0, "MM-dd-yyyy"),
                                               false);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test03_DeleteEventInSpace() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceManagement.goToAgendaTab();
    info("Test 13 Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    $(byClassName("createEvent")).waitUntil(Condition.visible, Configuration.timeout).click();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy"),
                                              getDate(0, "MM-dd-yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    info("Test 15 Delete an Event in personal calendar");

    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM-dd-yyyy"));
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

}
