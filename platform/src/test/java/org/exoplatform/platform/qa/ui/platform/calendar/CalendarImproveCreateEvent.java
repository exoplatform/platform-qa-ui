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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static net.sourceforge.htmlunit.cyberneko.HTMLEntities.get;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
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
        calendarHomePage= new CalendarHomePage(this);
        calendarManagement=new CalendarManagement(this);
        eventManagement=new EventManagement(this);
        navigationToolbar=new NavigationToolbar(this);
        addUsers= new AddUsers (this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.username, PLFData.DATA_PASS);
    }

    @Test
    public void test01_CheckEventPopUpWhenCLickOnAddEvent(){

        String Event= "event"+ getRandomNumber();
        homePagePlatform.goToCalendarPage();
        calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
        calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
        calendarManagement.saveSetting();
        homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT),Condition.visible,1000);
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        info("Add event");
        ELEMENT_EVENT_TITLE_DRAWER.setValue(Event);
        eventManagement.saveQuickAddEvent();
        calendarHomePage.verifyIsPresentEventTask(Event,
                CalendarHomePage.selectViewOption.DAY,
                CalendarHomePage.selectDayOption.DETAILTIME);

    }


    @Test
    public void test03_CheckEventPopUpWhenCLickOnTimeSlot(){
        String titleEvent="titleEvent"+getRandomNumber();
        homePagePlatform.goToCalendarPage();
        calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.CALSETTING);
        calendarManagement.changeSettingCalendar(null, "(GMT +01:00) Africa/Tunis", null, null, null, null, null);
        calendarManagement.saveSetting();
        homePagePlatform.refreshUntil($(byAttribute("startfull",getDate(0,"EEE MMM dd yyyy HH"+":00:00"))),Condition.visible,1000);
        refresh();
        $(byAttribute("startfull",getDate(0,"EEE MMM dd yyyy HH"+":00:00"))).click();
        ELEMENT_EVENT_DRAWER.parent().waitUntil(Condition.visible, Configuration.timeout);
        eventManagement.checkEventPopUp();
        info("Add event");
        ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
        eventManagement.saveQuickAddEvent();
        calendarHomePage.verifyIsPresentEventTask(titleEvent,
                CalendarHomePage.selectViewOption.DAY,
                CalendarHomePage.selectDayOption.DETAILTIME);



    }

    @Test
    public void test04_checkCloseButtonOfEventDrawer() {
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_CLOSE_BUTTON_DRAWER.click();
        ELEMENT_BUTTON_EVENT_SAVE.waitUntil(Condition.disappears, Configuration.timeout);
    }

    @Test
    public void test05_checkCancelButtonOfEventDrawer(){
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_EVENT_CANCEL_BUTTON.click();
        ELEMENT_BUTTON_EVENT_SAVE.waitUntil(Condition.disappears, Configuration.timeout);
    }

    @Test
    public void test06_CheckClearButtonInEventDrawer(){
        String titleEvent="titleEvent"+getRandomNumber();
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_EVENT_DRAWER_TITLE.setValue(titleEvent);
        ELEMENT_EVENT_CATEGORY.selectOption("Meeting");
        ELEMENT_EVENT_LOCATION.setValue("fgfg");
        ELEMENT_EVENT_DESCRIPTION.setValue("fgv");
        ELEMENT_EVENT_CLEAR_BUTTON.click();
        assertEquals("",ELEMENT_EVENT_DRAWER_TITLE.getText());
        assertEquals("All",ELEMENT_EVENT_CATEGORY.getText());
        assertEquals("",ELEMENT_EVENT_LOCATION.getText());
        assertEquals("",ELEMENT_EVENT_DESCRIPTION.getText());
        ELEMENT_EVENT_CANCEL_BUTTON.click();


    }

    @Test
    public void test07_checkSaveButtonWhenTitleIsEmpty(){
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        assertEquals("", ELEMENT_EVENT_DRAWER_TITLE.getText());
        ELEMENT_EVENT_SAVE_BUTTON.waitUntil(Condition.disabled,Configuration.timeout);
        ELEMENT_EVENT_CANCEL_BUTTON.click();

    }

    @Test
    public void test08_checkSaveButtonWhenTitleIsTyped(){
        String titleEvent="titleEvent"+getRandomNumber();

        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_EVENT_DRAWER_TITLE.setValue(titleEvent);
        ELEMENT_EVENT_SAVE_BUTTON.waitUntil(Condition.enabled,Configuration.timeout);
        ELEMENT_EVENT_CANCEL_BUTTON.click();

    }

    @Test
    public void test09_AddParticipantToEvent(){
        String titleEvent="titleEvent"+getRandomNumber();
        String username = "usernamea" + getRandomString();
        String Firstname = "Firstname" + getRandomString();
        String Lastname = "Lastname" + getRandomString();
        String email1 = username + "@test.com";
        String password="123456";
        navigationToolbar.goToAddUser();
        addUsers.addUser(username, password, email1, Firstname, Lastname);
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_EVENT_DRAWER_TITLE.setValue(titleEvent);
        ELEMENT_PARTICIPANT_DRAWER.sendKeys(Firstname);
        $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/div/div/input")).pressEnter();
        ELEMENT_EVENT_SAVE_BUTTON.click();
    }

    @Test
    public void test10_CheckRepeatPreferencePopup(){
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_REPEAT_SWITCH_LABEL.click();
        ELEMENT_REPEAT_PREFERRENCE_IMPROVE_POPUP.waitUntil(Condition.appear,Configuration.timeout);
        $(byText("Recurring Event")).waitUntil(Condition.exist,Configuration.timeout);
        ELEMENT_EVENT_CANCEL_BUTTON.click();

    }

    @Test
    public void test11_CheckReminderPopup (){
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_REMINDER_SWITCH_LABEL.click();
        ELEMENT_REMINDER_POPUP.waitUntil(Condition.appear,Configuration.timeout);
        $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).click();
        ELEMENT_REMINDER_POPUP.waitUntil(Condition.disappear,Configuration.timeout);
        ELEMENT_EVENT_CANCEL_BUTTON.click();

    }

    @Test
    public void test12_CheckReminderLabel(){

        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_REMINDER_SWITCH_LABEL.click();
        ELEMENT_SAVE_REMINDER_BUTTON.click();
        ELEMENT_REMINDER_LABEL.waitUntil(Condition.text("5mn before"),Configuration.timeout);
        assertEquals("underline solid rgb(87, 141, 201)", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[2]/div[2]/a")).getCssValue("text-decoration"));
        ELEMENT_EVENT_CANCEL_BUTTON.click();

    }

    @Test
    public void test13_CheckRepeatLabel(){
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_REPEAT_SWITCH_LABEL.click();
        ELEMENT_REPEAT_SAVE_BUTTON.click();
        ELEMENT_REPEAT_LABEL.waitUntil(Condition.text("Repeat"),Configuration.timeout);
        assertEquals("underline solid rgb(87, 141, 201)", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[3]/div[2]/a")).getCssValue("text-decoration"));
        info("Check the behavior when click on repeat label");
        ELEMENT_REPEAT_LABEL.click();
        ELEMENT_REPEAT_PREFERRENCE_IMPROVE_POPUP.waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_EVENT_CANCEL_BUTTON.click();
    }


    @Test
    public void test12_CheckFindTimePopup(){
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_FIND_TIME_BUTTON.click();
        ELEMENT_FIND_TIME_POPUP.waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_CHECK_TIME_ICON.waitUntil(Condition.checked,Configuration.timeout);
        ELEMENT_FIND_TIME_SAVE_BUTTON.click();
        ELEMENT_FIND_TIME_POPUP.waitUntil(Condition.disappear,Configuration.timeout);
        ELEMENT_EVENT_CANCEL_BUTTON.click();

    }


}
