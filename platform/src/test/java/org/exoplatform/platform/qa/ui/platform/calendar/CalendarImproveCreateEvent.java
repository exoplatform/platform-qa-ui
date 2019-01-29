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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static net.sourceforge.htmlunit.cyberneko.HTMLEntities.get;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
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
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        calendarHomePage= new CalendarHomePage(this);
        calendarManagement=new CalendarManagement(this);
        eventManagement=new EventManagement(this);
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
        $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).setValue(Event);
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
        $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input")).setValue(titleEvent);
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
        $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[1]/a")).click();
        ELEMENT_BUTTON_EVENT_SAVE.waitUntil(Condition.disappears, Configuration.timeout);
    }

    @Test
    public void test05_checkCancelButtonOfEventDrawer(){
        homePagePlatform.goToCalendarPage();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).click();
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
    public void test07_CheckEditEvent(){

        String titleEvent="titleEvent"+getRandomNumber();
        String newTitelEvent="newtitleEvent"+getRandomNumber();
        homePagePlatform.goToCalendarPage();
        homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT),Condition.visible,1000);
        refresh();
        eventManagement.goToAddEventFromActionBar();
        eventManagement.checkEventPopUp();
        ELEMENT_EVENT_DRAWER_TITLE.setValue(titleEvent);
        eventManagement.saveQuickAddEvent();
        calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                CalendarHomePage.selectViewOption.DAY,
                CalendarHomePage.selectDayOption.DETAILTIME,
                null);







    }
}
