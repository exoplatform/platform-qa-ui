package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.exoplatform.platform.qa.ui.core.PLFData;
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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ilyes on 08/11/17.
 */
@Tag("sniff")
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
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.username, PLFData.password);
  }

  @Test
  @Tag("eventis")
  public void test01_AddEventInSpace() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String pattern = "MM-dd-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());
    String space = "space" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceManagement.goToAgendaTab();
    info("Test 13 Add an event in personal calendar");
    ELEMENT_CALENDAR_TODAY_FIRST_TIME.waitUntil(Condition.visible, Configuration.timeout).contextClick();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT),Condition.visible,1000);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    eventManagement.goToAddEventFromActionBar();
    eventManagement.checkEventPopUp(date, PLFData.DATA_NAME_ROOT, PLFData.username);
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
  @Tag("eventis")
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
    ELEMENT_CALENDAR_TODAY_FIRST_TIME.waitUntil(Condition.visible, Configuration.timeout).contextClick();
    $(byClassName("createEvent")).waitUntil(Condition.visible, Configuration.timeout).click();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              getDate(0, "MM-dd-yyyy" + " HH"),
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
                                               getDate(0, "MM-dd-yyyy" + " HH"),
                                               getDate(0, "MM-dd-yyyy" + " HH"),
                                               false);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  @Tag("eventis")
  public void test03_DeleteEventInSpace() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceManagement.goToAgendaTab();
    info("Test 13 Add an event in personal calendar");
    ELEMENT_CALENDAR_TODAY_FIRST_TIME.waitUntil(Condition.visible, Configuration.timeout).contextClick();
    $(byClassName("createEvent")).waitUntil(Condition.visible, Configuration.timeout).click();
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM-dd-yyyy" + " HH"),
                                              getDate(0, "MM-dd-yyyy" + " HH"),
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
