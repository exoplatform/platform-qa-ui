package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * Created by ilyes on 17/11/17.
 */
@Tag("calendar")
@Tag("sniff")
public class CalendarCheckboxStatusTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  CalendarManagement calendarManagement;

  ManageLogInOut     manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.username, PLFData.DATA_PASS);
  }

  @Test
  public void test01_checkCalendarStatusAfterRefreshingPage() {
    String calendar1 = "calendar" + getRandomNumber();
    String calendar2 = "groupCalendar" + getRandomNumber();
    String groupCalendar = "/platform/users";
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar1, calendar1, "");
    calendarManagement.saveAddCalendar();
    $(byText(calendar1)).waitUntil(Condition.visible, Configuration.timeout);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar2, calendar2, "");
    calendarManagement.selectGroupInGroupTabCalendarForm(groupCalendar, true);
    calendarManagement.saveAddCalendar();
    $(byText(calendar2)).waitUntil(Condition.visible, Configuration.timeout);
    info("check that calendar is selected");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_CHECK_CALENDAR).should(Condition.exist);
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_CHECK_CALENDAR).should(Condition.exist);
    info("uncheck the 2 calendar");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_CHECK_CALENDAR).click();
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_CHECK_CALENDAR).click();
    info("check that calendar is not selected");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    info("refresh");
    refresh();
    info("check that the 2 calendar still not selected");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    calendarManagement.deleteCalendar(calendar1, true);
    calendarManagement.deleteCalendar(calendar2, true);

  }

  @Test
  public void test02_checkCalendarStatusAfterGoingToOtherPage() {
    String calendar1 = "calendar" + getRandomNumber();
    String calendar2 = "groupCalendar" + getRandomNumber();
    String groupCalendar = "/platform/users";
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar1, calendar1, "");
    calendarManagement.saveAddCalendar();
    $(byText(calendar1)).waitUntil(Condition.visible, Configuration.timeout);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar2, calendar2, "");
    calendarManagement.selectGroupInGroupTabCalendarForm(groupCalendar, true);
    calendarManagement.saveAddCalendar();
    $(byText(calendar2)).waitUntil(Condition.visible, Configuration.timeout);
    info("check that calendar is selected");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_CHECK_CALENDAR).should(Condition.exist);
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_CHECK_CALENDAR).should(Condition.exist);
    info("uncheck the 2 calendar");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_CHECK_CALENDAR).click();
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_CHECK_CALENDAR).click();
    info("check that calendar is not selected");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    info("go to home page");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToCalendarPage();
    info("check that the 2 calendar still not selected");
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar1)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    $(ELEMENT_CALENDAR_PANEL).find(byText(calendar2)).parent().find(ELEMENT_UNCHECK_CALENDAR).should(Condition.exist);
    calendarManagement.deleteCalendar(calendar1, true);
    calendarManagement.deleteCalendar(calendar2, true);

  }
}
