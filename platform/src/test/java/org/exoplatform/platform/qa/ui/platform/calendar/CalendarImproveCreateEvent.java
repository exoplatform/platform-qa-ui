package org.exoplatform.platform.qa.ui.platform.calendar;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("calendar")
@Tag("sniff")
public class CalendarImproveCreateEvent extends Base {
  HomePagePlatform homePagePlatform;
  CalendarManagement calendarManagement;
  ManageLogInOut manageLogInOut;
  CalendarHomePage calendarHomePage;
  EventManagement eventManagement;
  NavigationToolbar navigationToolbar;
  AddUsers addUsers;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarHomePage = new CalendarHomePage(this);
    calendarManagement = new CalendarManagement(this);
    eventManagement = new EventManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.username, PLFData.DATA_PASS);
  }

  @Test
  public void test01_CheckEventPopUpWhenCLickOnAddEvent() {
    //21495 //21498 //21500 //21503 //21505 //21511 //21522
    String eventTitle = "event" + getRandomNumber();
    String pattern = "MM-dd-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    info("Date From is" + date);
    assertEquals(date, $(byXpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label' and text()='From']/following::input[@class='date'])[1]")).getValue());
    info("Date To is" + date);
    assertEquals(date, $(byXpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label' and text()='From']/following::input[@class='date'])[2]")).getValue());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(eventTitle);
    ELEMENT_EVENT_ADD_PARTICIPANT.setValue(PLFData.DATA_NAME_USER1).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
    ELEMENT_EVENT_ADD_PARTICIPANT.sendKeys(Keys.ENTER);
    sleep(Configuration.timeout);
    $(byXpath("//div[@class='item' and text()='${participantName}']".replace("${participantName}", PLFData.DATA_NAME_USER1))).exists();
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(eventTitle,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(eventTitle,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));
  }


  @Test
  public void test02_CheckEventPopUpWhenCLickOnTimeSlot() {
    //21496
    String titleEvent = "titleEvent" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.refreshUntil($(byAttribute("startfull", getDate(0, "EEE MMM dd yyyy HH" + ":00:00"))), Condition.visible, 1000);
    refresh();
    sleep(2000);
    $(byAttribute("startfull", getDate(0, "EEE MMM dd yyyy HH" + ":00:00"))).doubleClick();
    sleep(2000);
    ELEMENT_EVENT_DRAWER.parent().waitUntil(Condition.visible, Configuration.timeout);
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    info("Clear data");
    homePagePlatform.goToCalendarPage();
    calendarHomePage.deleteEventTask(titleEvent,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));

  }

  @Test
  public void test03_checkCloseButtonOfEventDrawer() {
    //21499
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    ELEMENT_CLOSE_BUTTON_DRAWER.click();
    ELEMENT_BUTTON_EVENT_SAVE.waitUntil(Condition.disappears, Configuration.timeout);

  }

  @Test
  public void test04_checkCancelButtonOfEventDrawer() {
    //21501
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    ELEMENT_EVENT_CANCEL_BUTTON.click();
    ELEMENT_BUTTON_EVENT_SAVE.waitUntil(Condition.disappears, Configuration.timeout);

  }

  @Test
  public void test05_CheckClearButtonInEventDrawer() {
    //21501
    String titleEvent = "titleEvent" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    ELEMENT_EVENT_DRAWER_TITLE.setValue(titleEvent);
    ELEMENT_EVENT_CATEGORY.selectOption("Meeting");
    ELEMENT_EVENT_LOCATION.setValue("Location");
    ELEMENT_EVENT_DESCRIPTION.setValue("Description");
    ELEMENT_EVENT_CLEAR_BUTTON.click();
    assertEquals("", ELEMENT_EVENT_DRAWER_TITLE.getText());
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("", ELEMENT_EVENT_LOCATION.getText());
    assertEquals("", ELEMENT_EVENT_DESCRIPTION.getText());
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test06_checkSaveButtonWhenTitleIsEmpty() {
    //21502
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    assertEquals("", ELEMENT_EVENT_DRAWER_TITLE.getText());
    ELEMENT_EVENT_SAVE_BUTTON.waitUntil(Condition.disabled, Configuration.timeout);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test07_checkSaveButtonWhenTitleIsTyped() {
    //21504
    String titleEvent = "titleEvent" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    ELEMENT_EVENT_DRAWER_TITLE.setValue(titleEvent);
    ELEMENT_EVENT_SAVE_BUTTON.waitUntil(Condition.enabled, Configuration.timeout);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test08_AddParticipantToEvent() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String username = "usernamea" + getRandomString();
    String Firstname = "Firstname" + getRandomString();
    String Lastname = "Lastname" + getRandomString();
    String email1 = username + "@test.com";
    String password = "123456";
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email1, Firstname, Lastname);
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    ELEMENT_EVENT_DRAWER_TITLE.setValue(titleEvent);
    ELEMENT_PARTICIPANT_DRAWER.sendKeys(Firstname);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/div/div/input")).pressEnter();
    ELEMENT_EVENT_SAVE_BUTTON.click();

  }

  @Test
  public void test09_CheckRepeatPreferencePopup() {
    //21514
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_REPEAT_PREFERRENCE_IMPROVE_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    $(byText("Recurring Event")).waitUntil(Condition.exist, Configuration.timeout);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test10_CheckReminderPopup() {
    //21518
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_REMINDER_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    $(byText("Reminder")).waitUntil(Condition.exist, Configuration.timeout);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test11_CheckReminderLabel() {
    //21519 //21520 //21521
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
    $(byXpath("//input[@id='mailReminderTimeEntry']")).setValue("15");
    sleep(2000);
    $(ELEMENT_SAVE_REMINDER_BUTTON).waitUntil(Condition.visible, Configuration.timeout).click();
    Assert.assertEquals($(byXpath("//div[@class='reminder pull-left']/div/a")).getText(), "15mn before");
    $(byXpath("//div[@class='reminder pull-left']/div/a")).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_REMINDER_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    Assert.assertEquals($(byXpath("//input[@id='mailReminderTimeEntry']")).getValue(), "15");
    sleep(2000);
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
    Assert.assertNotEquals($(byXpath("//div[@class='reminder pull-left']/div/a")).getText(), "15mn before");
    sleep(2000);
    ELEMENT_REMINDER_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    Assert.assertEquals($(byXpath("//input[@id='mailReminderTimeEntry']")).getValue(), "10");
    Assert.assertEquals($(byXpath("//select[@id='mailReminderTime']")).getValue(), "minutes");
    $(ELEMENT_CANCEL_REMINDER_BUTTON).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test12_CheckRepeatLabel() {
    //21515 //21516
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
    $(ELEMENT_SAVE_EVENT_OCCURRING).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_REPEAT_LABEL.waitUntil(Condition.text("Repeat"), Configuration.timeout);
    info("Check the behavior when click on repeat label");
    ELEMENT_REPEAT_LABEL.click();
    ELEMENT_REPEAT_PREFERRENCE_IMPROVE_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test13_CheckFindTimePopup() {
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    ELEMENT_FIND_TIME_BUTTON.click();
    ELEMENT_FIND_TIME_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHECK_TIME_ICON.waitUntil(Condition.checked, Configuration.timeout);
    ELEMENT_FIND_TIME_SAVE_BUTTON.click();
    ELEMENT_FIND_TIME_POPUP.waitUntil(Condition.disappear, Configuration.timeout);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

  @Test
  public void test14_CheckMaximumOfAttachedFileToAnEvent() {
    //21507 //21506 //21508 //21509 //21523 //21524 //21525 //21526 //21527
    String event = "event" + getRandomNumber();
    String uploadedFile = "eXo-Platform.png";
    String pattern = "MM-dd-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    info("Date From is" + date);
    assertEquals(date, $(byXpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label' and text()='From']/following::input[@class='date'])[1]")).getValue());
    info("Date To is" + date);
    assertEquals(date, $(byXpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label' and text()='From']/following::input[@class='date'])[2]")).getValue());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    for (int i = 0; i <= 11; i++) {
      $(By.className("attachFile")).uploadFromClasspath(uploadedFile);
    }
    sleep(Configuration.timeout);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).dragAndDropTo($(byXpath("//div[@title=\"eXo-Platform.png_________\"]")));
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "_"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "__"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "___"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "____"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "_____"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "______"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "_______"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "________"))).exists();
    $(byXpath("//div[@title='${uploadedFile}']".replace("${uploadedFile}", uploadedFile + "_________"))).exists();
    info("File Size exists");
    $(byXpath("//div[@class=\"fileSize pull-left\"]")).exists();
    info("Delete button exists");
    $(byXpath("//div[@class=\"removeFile\"]//i[@class=\"uiIconClose\"]")).exists();
    info("Upload the Eleventh file");
    $(By.className("attachFile")).uploadFromClasspath(uploadedFile);
    info("Inability to upload up to 10 files at once.");
    $(byXpath("//div[contains(text(),'You can only upload up to 10 files at once.')]")).exists();
    sleep(2000);
    ELEMENT_EVENT_TITLE_DRAWER.setValue(event);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(event,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.deleteEventTask(event,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));

  }

  @Test
  public void test15_CheckThatFileSizeMoreThan10MBCanNotBeAttachedToAnEvent() {
    //21510
    String event = "event" + getRandomNumber();
    String bigSizePhoto = "eXoMoreThan10MB.jpg";
    String pattern = "MM-dd-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
    calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
    calendarManagement.saveSetting();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    info("Date From is" + date);
    assertEquals(date, $(byXpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label' and text()='From']/following::input[@class='date'])[1]")).getValue());
    info("Date To is" + date);
    assertEquals(date, $(byXpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label' and text()='From']/following::input[@class='date'])[2]")).getValue());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    sleep(Configuration.timeout);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).dragAndDropTo($(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")));
    $(By.className("attachFile")).uploadFromClasspath(bigSizePhoto);
    info("Inability to upload a file with size more than 10 MB");
    $(byXpath("//div[contains(text(),'File size exceeds the 10 MB limit.')]")).exists();
    sleep(2000);
    ELEMENT_EVENT_TITLE_DRAWER.setValue(event);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(event,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.deleteEventTask(event,
            CalendarHomePage.selectViewOption.LIST,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));

  }

  @Test
  public void test16_CheckBehaviorWhenDisableRepeatButtonFromYesToNo() {
    //21517
    homePagePlatform.goToCalendarPage();
    eventManagement.goToAddEventFromActionBar();
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).getAttribute("placeholder"));
    sleep(2000);
    $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select")).exists();
    assertEquals("All", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals("Root Root", $(byXpath("//div[@class=\"trimText\"]")).getText());
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_REPEAT_PREFERRENCE_IMPROVE_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    eventManagement.inputRecurringInfoEvent(EventManagement.repeatType.Daily,
            "1",
            null,
            EventManagement.repeatEndType.After,
            "5");
    sleep(2000);
    $(ELEMENT_SAVE_EVENT_OCCURRING).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_REPEAT_LABEL.waitUntil(Condition.text("Repeat"), Configuration.timeout);
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_REPEAT_PREFERRENCE_IMPROVE_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    //Assert.assertEquals("");
    $(byText("Recurring Event")).waitUntil(Condition.exist, Configuration.timeout);
    ELEMENT_EVENT_CANCEL_BUTTON.click();

  }

}
