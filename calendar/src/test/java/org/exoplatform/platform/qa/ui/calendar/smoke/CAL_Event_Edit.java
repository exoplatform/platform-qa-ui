package org.exoplatform.platform.qa.ui.calendar.smoke;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;

import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class CAL_Event_Edit extends Base{


    HomePagePlatform homePagePlatform;
    CalendarManagement calendarManagement;
    EventManagement eventManagement;
    ManageLogInOut manageLogInOut;
    CalendarHomePage calendarHomePage;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform=new HomePagePlatform(this);
        calendarManagement=new CalendarManagement(this);
        eventManagement=new EventManagement(this);
        manageLogInOut=new ManageLogInOut(this);
        calendarHomePage=new CalendarHomePage(this);
    }

/**
*<li> Case ID:116425.</li>
*<li> Test Case Name: Edit event of group calendar.</li>
*<li> Pre-Condition: </li>
*<li> Post-Condition: </li>
*/
@Test
@Smoke
@Tag("smoke")
public  void test03_EditEventOfGroupCalendar() {
    info("Test 3: Edit event of group calendar");
    /*Step Number: 1
    *Step Name: Step 1: Add event
    *Step Description:
        - Add new group calendar
        - Assign calendar for an user with edit right
        - Add new event on this group calendar
    *Input Data:

    *Expected Outcome:
        - Event is added successfully
        - Group calendar and its event(s) are displayed for all member of its group(s)*/
    String DATA_USER2="mary1";
    String DATA_USER1= "john";
    String DATA_PASS="123456";
    info("Create a new calendar");
    String userGroup="/platform/users";
    String [] user={DATA_USER1};
    String calendar = "calendar"+getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar,null);
    calendarManagement.selectGroupInGroupTabCalendarForm(userGroup,true);
    calendarManagement.selectUserEditPermissionInGroup(user,1);
    calendarManagement.saveAddCalendar();

info("Add an Event");
    String titleEvent = "titleEvent"+getRandomNumber();
    String content= "content"+getRandomNumber();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputBasicQuickEvent(titleEvent, content,calendar);
    eventManagement.saveQuickAddEvent();

    /*Step number: 2
    *Step Name: Step 2: Edit event by member of group without edit right
    *Step Description:
        - Login by a member of group calendar who does not have edit right on that calendar
        - Right click on group calendar's event
    *Input Data:

    *Expected Outcome:
        Only "View" action is displayed.*/
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToCalendarPage();
    //rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
    $(byText(titleEvent)).contextClick();
    $(ELEMENT_CONTEXT_MENU_VIEW).waitUntil(Condition.appears,10000);
    $(byClassName("eventAction")).find(byText("Delete")).shouldNot(Condition.exist);
    $(byClassName("eventAction")).find(byText("Edit")).shouldNot(Condition.exist);

    /*Step number: 3
    *Step Name: Step 3: Edit event by member of group with edit right
    *Step Description:
        - Login by member who is assign edit right on above group calendar
        - Right click on event of shared calendar and select Edit or drag and drop event in working pane
    *Input Data:

    *Expected Outcome:
        - Edit event form appears with current information of event in form (if select Edit) or event is changed date/time as drag and drop
        - Edit event successfully by shared user with edit right*/
    manageLogInOut.signIn(DATA_USER1, DATA_PASS);
    info("Edit the Event");
    homePagePlatform.goToCalendarPage();
    String titleEvent1 = "titleEvent1"+getRandomNumber();
    calendarManagement.openEditEventTaskPopup(titleEvent, CalendarHomePage.selectViewOption.LIST);
   eventManagement.inputBasicDetailEvent(titleEvent1,null);
    eventManagement.saveAddEventDetails();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    $(byText(titleEvent1)).should(Condition.exist);

    calendarManagement.deleteCalendar(calendar);
 }
}