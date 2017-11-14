package org.exoplatform.platform.qa.ui.platform.calendar;


import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class CalendarViewTestIT extends Base {
	HomePagePlatform homePagePlatform;
	CalendarHomePage calendarHomePage;
	ManageLogInOut manageLogInOut;
	EventManagement eventManagement;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		calendarHomePage=new CalendarHomePage(this);
		homePagePlatform=new HomePagePlatform(this);
		eventManagement=new EventManagement(this);
		manageLogInOut=new ManageLogInOut(this);
		manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");
	}

	/**
	 * Case ID:115595.
	 * Test Case Name: Check Today view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description:
			- Go to calendar
			- Add new task/event
		 *Input Data:

		 *Expected Outcome:
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Select view
		 *Step Description:
			- Select view (day/week/month/list/work week) which has not the current day
		 *Input Data:

		 *Expected Outcome:
			- Calendar is displayed with the selected view(day/week/month/list/work week)*/

		/*Step number: 3
		 *Step Name: Step 3: Check displaying of task and event.
		 *Step Description:
			- Click on Today on the main bar
		 *Input Data:

		 *Expected Outcome:
			- Go to the current view which has the current day (today view). Mini calendar is updated to the week has current day*/

	@Test
	public  void test01_CheckTodayView() {
		info("Test 1: Check Today view");
		String titleEventCur="titleEventCur"+getRandomNumber();
		String titleEventNext="titleEventNext"+getRandomNumber();
		String titleEventPre="titleEventPre"+getRandomNumber();
		String content="content"+getRandomNumber();
		String defaultFormatDate="MM/dd/yyyy";
		homePagePlatform.goToCalendarPage();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventCur, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventNext, content, getDate(1,defaultFormatDate), getDate(1,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventPre, content, getDate(-1,defaultFormatDate), getDate(-1,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		homePagePlatform.goToCalendarPage();
		calendarHomePage.goToView(CalendarHomePage.selectViewOption.DAY);
		$(ELEMENT_TODAY_ACTION_BAR).click();
		calendarHomePage.verifyIsPresentEventTask(titleEventCur, CalendarHomePage.selectViewOption.DAY, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventNext, CalendarHomePage.selectViewOption.DAY, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventPre, CalendarHomePage.selectViewOption.DAY, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.deleteEventTask( titleEventCur, CalendarHomePage.selectViewOption.WEEK, CalendarHomePage.selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		calendarHomePage.deleteEventTask( titleEventNext, CalendarHomePage.selectViewOption.MONTH, CalendarHomePage.selectDayOption.ALLDAY,getDate(1,"MMM dd yyyy"));
		calendarHomePage.deleteEventTask( titleEventPre, CalendarHomePage.selectViewOption.MONTH, CalendarHomePage.selectDayOption.ALLDAY,getDate(-1,"MMM dd yyyy"));
	}
	/**
	 * Case ID:115596.
	 * Test Case Name: Check displaying added task/event in work week view.
	 * Pre-Condition:
	 * Post-Condition:
	 Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description:
			- Go to calendar
			- Add new task/event
		 *Input Data:

		 *Expected Outcome:
			Task/ event are created

		*Step number: 2
		 *Step Name: Step 2: Check displaying of task and event.
		 *Step Description:
			- Click on Work Week on the main bar
		 *Input Data:

		 *Expected Outcome:
			Event/ task is displayed in Work Week view

		*Step number: 3
		 *Step Name: Step 3: Check next week/previous week
		 *Step Description:
			- Click next week/previous week icon
		 *Input Data:

		 *Expected Outcome:
			- Next week/previous week is displayed correctly
			- Mini calendar is updated also*/
	@Test
	public  void test02_CheckDisplayingAddedTaskeventInWorkWeekView() {
		info("Test 2: Check displaying added task/event in work week view");
		String titleEventCur="titleEventCur"+getRandomNumber();
		String titleEventNextWeek="titleEventNextWeek"+getRandomNumber();
		String titleEventPreWeek="titleEventPreWeek"+getRandomNumber();
		String content="content"+getRandomNumber();
		String defaultFormatDate="MM/dd/yyyy";

		homePagePlatform.goToCalendarPage();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventCur, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventNextWeek, content, getDate(7,defaultFormatDate), getDate(7,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventPreWeek, content, getDate(-7,defaultFormatDate), getDate(-7,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		$(ELEMENT_TODAY_ACTION_BAR).click();
		calendarHomePage.goToView(CalendarHomePage.selectViewOption.WORKWEEK);
		$(ELEMENT_TODAY_ACTION_BAR).click();
		calendarHomePage.verifyIsPresentEventTask(titleEventCur, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventNextWeek, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventPreWeek, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		$(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
		calendarHomePage.verifyIsPresentEventTask(titleEventNextWeek, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventPreWeek, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventCur, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		$(ELEMENT_TODAY_ACTION_BAR).click();
		$(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
		calendarHomePage.verifyIsNotPresentEventTask(titleEventCur, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventNextWeek, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsPresentEventTask(titleEventPreWeek, CalendarHomePage.selectViewOption.WORKWEEK, CalendarHomePage.selectDayOption.ALLDAY);
		$(ELEMENT_TODAY_ACTION_BAR).click();
		$(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
		calendarHomePage.deleteEventTask( titleEventNextWeek, CalendarHomePage.selectViewOption.WEEK, CalendarHomePage.selectDayOption.ALLDAY,getDate(7,"MMM dd yyyy"));
		$(ELEMENT_TODAY_ACTION_BAR).click();
		calendarHomePage.deleteEventTask( titleEventCur, CalendarHomePage.selectViewOption.WEEK, CalendarHomePage.selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		$(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
		calendarHomePage.deleteEventTask( titleEventPreWeek, CalendarHomePage.selectViewOption.WEEK, CalendarHomePage.selectDayOption.ALLDAY,getDate(-7,"MMM dd yyyy"));

	}
	/**
	 * Case ID:115597.
	 * Test Case Name: Check displaying added task/event in list view.
	 * Pre-Condition:
	 * Post-Condition:
	Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description:
			- Go to calendar
			- Add new task/event
		 *Input Data:

		 *Expected Outcome:
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Check displaying of task and event.
		 *Step Description:
			- Click on List on the main bar
		 *Input Data:

		 *Expected Outcome:
			Event/ task is displayed in List view*/

		/*Step number: 3
		 *Step Name: Step 3: Check next week/previous day
		 *Step Description:
			- Click on next week/previous day icon
		 *Input Data:

		 *Expected Outcome:
			- Next week/previous day is displayed correctly
			- Mini calendar is updated also*/
	@Test
	public  void test03_CheckDisplayingAddedTaskeventInListView() {
		info("Test 3: Check displaying added task/event in list view");
		info("Test 1: Check Today view");
		String titleEventCur="titleEventCur"+getRandomNumber();
		String titleEventNext="titleEventNext"+getRandomNumber();
		String titleEventPre="titleEventPre"+getRandomNumber();
		String content="content"+getRandomNumber();
		String defaultFormatDate="MM/dd/yyyy";
		homePagePlatform.goToCalendarPage();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventCur, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventNext, content, getDate(1,defaultFormatDate), getDate(1,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		eventManagement.goToAddEventFromActionBar();
		eventManagement.inputDataEventInQuickForm(titleEventPre, content, getDate(-1,defaultFormatDate), getDate(-1,defaultFormatDate),true);
		eventManagement.saveQuickAddEvent();
		homePagePlatform.goToCalendarPage();
		calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
		$(ELEMENT_TODAY_ACTION_BAR).click();
		calendarHomePage.verifyIsPresentEventTask(titleEventCur, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventNext, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventPre, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		$(ELEMENT_NEXT_BUTTON_ANY_VIEW).click();
		calendarHomePage.verifyIsNotPresentEventTask(titleEventCur, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsPresentEventTask(titleEventNext, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventPre, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		$(ELEMENT_TODAY_ACTION_BAR).click();
		$(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW).click();
		calendarHomePage.verifyIsNotPresentEventTask(titleEventCur, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsNotPresentEventTask(titleEventNext, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		calendarHomePage.verifyIsPresentEventTask(titleEventPre, CalendarHomePage.selectViewOption.LIST, CalendarHomePage.selectDayOption.ALLDAY);
		$(ELEMENT_TODAY_ACTION_BAR).click();
		calendarHomePage.deleteEventTask( titleEventCur, CalendarHomePage.selectViewOption.WEEK, CalendarHomePage.selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		calendarHomePage.deleteEventTask( titleEventNext, CalendarHomePage.selectViewOption.MONTH, CalendarHomePage.selectDayOption.ALLDAY,getDate(1,"MMM dd yyyy"));
		calendarHomePage.deleteEventTask( titleEventPre, CalendarHomePage.selectViewOption.MONTH, CalendarHomePage.selectDayOption.ALLDAY,getDate(-1,"MMM dd yyyy"));


	}
}