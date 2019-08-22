package org.exoplatform.platform.qa.ui.calendar.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_AVATAR_CHANGELANGUAGE;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.ELEMENT_CHANGE_LANGUAGE_POPUP_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class CalendarHomePage {

  private final TestBase       testBase;

  public PlatformPermission    per;

  public ManageAlert           alert;

  public HomePagePlatform homePagePlatform;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase TestBase
   */
  public CalendarHomePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.homePagePlatform=new HomePagePlatform(testBase);
  }

  /**
   * Go to a view from calendar panel
   *
   * @param view name of view: get value from selectViewOption
   */
  public void goToView(selectViewOption view) {
    info("Go to view " + view);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    switch (view) {
      case DAY:
        ELEMENT_CALENDAR_DAY_BUTTON.click();
        break;
      case WEEK:
        ELEMENT_CALENDAR_WEEK_BUTTON.click();
        break;
      case LIST:
        ELEMENT_CALENDAR_LIST_BUTTON.click();
        break;
      case MONTH:
        $(byXpath(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Month"))).waitUntil(Condition.visible,Configuration.timeout);
        $(byXpath(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Month"))).click();
        $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Month"))).waitUntil(Condition.visible,Configuration.timeout);
        break;
      case WORKWEEK:
        $(byXpath(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Work Week"))).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Work Week"))).waitUntil(Condition.visible,Configuration.timeout);
        break;
      default:
        evt.waitForAndGetElement(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Week"), testBase.getDefaultTimeout(), 1);
        evt.click(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Week"));
        evt.waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"), testBase.getDefaultTimeout(), 1, 2);
        break;
    }

  }

  /**
   * goToRightMenuTaskEventFromDayView
   *
   * @param name name of event or task
   * @param optionDay select ONEDAY or ALLDAY
   */
  public void goToRightMenuTaskEventFromDayView(String name, selectDayOption optionDay) {
    info("Got to edit task from day view");
    goToView(selectViewOption.DAY);
    $(byXpath(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Day"))).click();
    $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Day"))).waitUntil(Condition.visible,Configuration.timeout);
    switch (optionDay) {
      case DETAILTIME:
        homePagePlatform.refreshUntil($(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name))),Condition.visible,1000);
        executeJavaScript("window.scrollBy(0,450)", "");
        $(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name))).contextClick();
        break;
      case ALLDAY:
        homePagePlatform.refreshUntil($(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name))),Condition.visible,1000);
        $(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name))).contextClick();
        break;
      default:
        evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name)));
        evt.rightClickOnElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
        break;
    }
  }

  /**
   * goToRightMenuTaskEventFromWeekView
   *
   * @param name name of event or task
   * @param optionDay select ONEDAY or ALLDAY
   * @param date date of event: format (MMM dd yyyy)
   */
  public void goToRightMenuTaskEventFromWeekView(String name, selectDayOption optionDay, String date) {
    info("Got to edit task from week view");
    executeJavaScript("window.scrollBy(0,-2000)", "");
    goToView(selectViewOption.WEEK);
    if (date != null && date != "") {
      switch (optionDay) {
        case DETAILTIME:
          homePagePlatform.refreshUntil(ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.find(byText(name)),Condition.visible,Configuration.timeout);
          executeJavaScript("window.scrollBy(0,200)", "");
          ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.find(byText(name))
                  .waitUntil(Condition.appears, Configuration.collectionsTimeout)
                  .contextClick();
          break;
        case ALLDAY:
          homePagePlatform.refreshUntil(ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.find(byText(name)),Condition.visible,Configuration.timeout);
          ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.find(byText(name))
                  .waitUntil(Condition.appears, Configuration.timeout)
                  .contextClick();
          break;
        default:
          evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name)
                  .replace("$date", date)));
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
          break;

      }
    } else {
      switch (optionDay) {
        case DETAILTIME:
          evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
          break;
        case ALLDAY:
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name));
          break;
        default:
          evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
          break;
      }
    }
  }

    /**
     * goToRightMenuTaskEventFromMonthView
     *
     * @param name name of event or task
     * @param date date of event: format (MMM dd yyyy)
     */
  public void goToRightMenuTaskEventFromMonthView(String name, String date) {
    info("Got to edit task from month view");
    goToView(selectViewOption.MONTH);
    if (date != null && date != "") {
      if ($(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).is(Condition.not(Condition.visible))
              && $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date))).is(Condition.visible)
              ) {
        info("Click more button");
        $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date))).click();
        $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date",
                date))).scrollTo().contextClick();}
      if ($(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).is(Condition.not(Condition.visible)))
        $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date",
                date))).contextClick();
      else {
        homePagePlatform.refreshUntil($(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))),Condition.visible,1000);
        $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).contextClick();

      }

    }
    else {
      homePagePlatform.refreshUntil($(byXpath(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name))),Condition.visible,1000);
      $(byXpath(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name))).contextClick();
    }
  }

  /**
   * goToRightMenuTaskEventFromListView
   *
   * @param name name of event or task
   * @param date date of event: format (MMM dd yyyy)
   */
  public void goToRightMenuTaskEventFromListView(String name, String date) {
    info("Got to edit task from list view");
    goToView(selectViewOption.LIST);
    if (date != null && date != "") {
      if ($(ELEMENT_TOTAL_PAGE).is(Condition.exist)) {
        info("paginator page in calendar list view");
        evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        while ((evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name), 5000, 0) == null)
                && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                .getText()
                .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
          evt.click(ELEMENT_NEXT_PAGE);
        evt.waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
        evt.rightClickOnElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
      } else {
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        if( $(byId("UIListUsers")).find(byText(name)).is(Condition.not(Condition.visible))){
          ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON.click();
        }
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byText(name)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(name)).waitUntil(Condition.visible,Configuration.timeout).contextClick();
      }
    } else {
      if ($(ELEMENT_TOTAL_PAGE).is(Condition.visible)) {
        evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        while ((evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name), 5000, 0) == null)
                && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                .getText()
                .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
          evt.click(ELEMENT_NEXT_PAGE);
        evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
        evt.rightClickOnElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
      } else {
        refresh();
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout).contextClick();
      }
    }
  }

  /**
   * goToRightMenuTaskEventFromWorkWeekView
   *
   * @param name name of event or task
   * @param optionDay select ONEDAY or ALLDAY
   * @param date date of event: format (MMM dd yyyy)
   */
  public void goToRightMenuTaskEventFromWorkWeekView(String name, selectDayOption optionDay, String date) {
    info("Got to edit task from work week view");
    goToView(selectViewOption.WORKWEEK);
    if (date != null && date != "") {
      switch (optionDay) {
        case DETAILTIME:
          evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name)
                  .replace("$date", date)));
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                  date));
          break;
        case ALLDAY:
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date",
                  date));
          break;
        default:
          evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name)
                  .replace("$date", date)));
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                  date));
          break;
      }
    } else {
      switch (optionDay) {
        case DETAILTIME:
          evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
          break;
        case ALLDAY:
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
          break;
        default:
          evt.scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
          evt.rightClickOnElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
          break;

      }
    }
  }

  /**
   * Open "Edit task" form popup
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   */
  public void goToEditTaskFromPopover(String name, selectViewOption view, selectDayOption optionDay) {
    info("Verify task and event is not displayed on calendar panel");
    evt.doubleClickOnElement(getEventTaskElement(name, view, optionDay));
    evt.waitForAndGetElement(ELEMENT_ADD_EDIT_TASK_POPUP);
  }

  /**
   * Open "Edit task" form by right click
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   * @param date date of event: format (MMM dd yyyy)
   */
  public void goToEditEventTaskFormByRightClick(String name, selectViewOption view, selectDayOption optionDay, String date) {
    info("Open Edit Task Event form");
    switch (view) {
      case DAY:
        goToRightMenuTaskEventFromDayView(name, optionDay);
        break;
      case WEEK:
        goToRightMenuTaskEventFromWeekView(name, optionDay, date);
        break;
      case LIST:
        goToRightMenuTaskEventFromListView(name, date);
        break;
      case MONTH:
        goToRightMenuTaskEventFromMonthView(name, date);
        break;
      case WORKWEEK:
        goToRightMenuTaskEventFromWorkWeekView(name, optionDay, date);
        break;
      default:
        goToRightMenuTaskEventFromDayView(name, optionDay);
        break;
    }
    $(ELEMENT_CONTEXT_MENU_EDIT).click();
    ELEMENT_EVENT_DRAWER.waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Verify event or task is not displayed on calendar panel
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   */
  public void verifyIsNotPresentEventTask(String name, selectViewOption view, selectDayOption optionDay) {
    info("Verify task and event is not displayed on calendar panel");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    goToView(view);
    switch (view) {
      case DAY:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            $(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
        }

        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            $(byXpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name))).shouldNot(Condition.visible);
            break;
          case ALLDAY:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      case LIST:
        if ($(ELEMENT_TOTAL_PAGE).is(Condition.visible)) {
          $(byXpath(ELEMENT_ANY_PAGE.replace("$page", "1"))).click();
          while (!(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                  .getText()
                  .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText()))) {
            evt.click(ELEMENT_NEXT_PAGE);
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
          }
          evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        } else {
          evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
        }


        break;
      case MONTH:
        $(byXpath(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name))).shouldNotBe(Condition.visible);
        break;
      case WORKWEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            $(byXpath(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      default:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
    }
  }


  /**
   * Verify event or task is displayed on calendar panel
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   */
  public void verifyIsPresentEventTask(String name, selectViewOption view, selectDayOption optionDay) {
    info("Verify task and event is not displayed on calendar panel");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    goToView(view);
    switch (view) {
      case DAY:
        switch (optionDay) {
          case DETAILTIME:
            $(byText(name)).waitUntil(Condition.appears, Configuration.timeout);
            break;
          case ALLDAY:
            $(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout);
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
        }

        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            $(byText(name)).waitUntil(Condition.appears, Configuration.timeout);
            break;
          case ALLDAY:
            $(byXpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout);
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      case LIST:
        if ($(ELEMENT_TOTAL_PAGE).is(Condition.exist)) {
          evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
          while ((evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name), 5000, 0) == null)
                  && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                  .getText()
                  .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
            evt.click(ELEMENT_NEXT_PAGE);
          evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
          evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        } else {
          $(byText(name)).waitUntil(Condition.appears, Configuration.timeout);
        }
        break;
      case MONTH:
        $(byXpath(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout);
        break;
      case WORKWEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            $(byXpath(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout);
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      default:
        switch (optionDay) {
          case DETAILTIME:
            $(byText(name)).waitUntil(Condition.appears, Configuration.timeout);
            break;
          case ALLDAY:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
    }
  }

  /**
   * Open "Edit task" form popup
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   * @return element
   */
  public WebElement getEventTaskElement(String name, selectViewOption view, selectDayOption optionDay) {
    info("Verify task and event is not displayed on calendar panel");
    WebElement element = null;
    goToView(view);
    switch (view) {
      case DAY:
        switch (optionDay) {
          case DETAILTIME:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      case LIST:
        if (evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE, 5000, 0) != null) {
          element = evt.waitForAndGetElement(ELEMENT_ANY_PAGE.replace("$page", "1"));
          while ((evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name), 5000, 0) == null)
                  && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                  .getText()
                  .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
            evt.click(ELEMENT_NEXT_PAGE);
          element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
          element = evt.waitForAndGetElement(ELEMENT_ANY_PAGE.replace("$page", "1"));
        } else {
          element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
        }
        break;
      case MONTH:
        element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name));
        break;
      case WORKWEEK:
        switch (optionDay) {
          case DETAILTIME:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      default:
        switch (optionDay) {
          case DETAILTIME:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            element = evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
    }
    return element;
  }

  /**
   * verify event exitst or not
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   * @param date date of event: format (MMM dd yyyy)
   */
  public void verifyIsPresentEventTaskWithDateTime(String name, String date, selectViewOption view, selectDayOption optionDay) {
    info("Verify task and event is not displayed on calendar panel");
    goToView(view);
    switch (view) {
      case DAY:
        switch (optionDay) {
          case DETAILTIME:
            $(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout);
            break;
          case ALLDAY:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
            break;
          case ALLDAY:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date", date));
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
            break;
        }
        break;
      case LIST:
        if ($(ELEMENT_TOTAL_PAGE).is(Condition.visible)) {

          evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
          while ((evt.waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name)
                          .replace("$date", date),
                  5000,
                  0) == null)
                  && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                  .getText()
                  .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
            evt.click(ELEMENT_NEXT_PAGE);
          evt.waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
          evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        } else {
          $(byXpath(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date))).waitUntil(Condition.visible,Configuration.timeout);
        }
        break;
      case MONTH:
        if ($(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).is(Condition.not(Condition.visible))

                && $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date))).is(Condition.visible)) {
          $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date))).click();
          $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date", date))).waitUntil(Condition.visible,Configuration.timeout);
        } else
          $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).waitUntil(Condition.visible,Configuration.timeout);

        break;
      case WORKWEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                    date));
            break;
          case ALLDAY:
            $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date",
                    date))).waitUntil(Condition.visible,Configuration.timeout);
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                    date));
            break;
        }
        break;
      default:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));

            break;
          case ALLDAY:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            evt.waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
    }
  }


  /**
   * Open Add/Edit task/event by left clicking
   *
   * @param date date of event: format (MMM dd yyyy HH:mm:ss)
   * @param view view: DAY, WEEK, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   */
  public void openAddEditEventTaskByLeftClick(String date, selectViewOption view, selectDayOption optionDay) {
    info("Open Quick Add/EDit task/event by left click");
    goToView(view);
    switch (view) {
      case DAY:
        evt.click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
            break;
          case ALLDAY:
            evt.click(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace("$date", date));
            break;
          default:
            evt.click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
            break;
        }
        break;
      case MONTH:
        evt.click(ELEMENT_EVENT_TASK_MONTH_DATE.replace("$date", date));
        break;
      case WORKWEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
            break;
          case ALLDAY:
            evt.click(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace("$date", date));
            break;
          default:
            evt.click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
            break;
        }
        break;
      default:
        info("You don't select a datetime.Please select a datetime.");
        break;
    }

  }

  /**
   * Open Add/Edit task/event by right clicking
   *
   * @param date date of event: format (MMM dd yyyy HH:mm:ss)
   * @param view view: DAY, WEEK, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   * @param option contextMenuAddEditEvenTaskOption
   */
  public void openAddEditEventTaskByRightClick(String date,
                                               selectViewOption view,
                                               selectDayOption optionDay,
                                               contextMenuAddEditEvenTaskOption option) {
    info("Open Quick Add/EDit task/event by right click");
    goToView(view);
    switch (view) {
      case DAY:
        evt.rightClickOnElement(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$time", date));

        selectOptionByRightclickOnDateTime(option);
        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date", date));

            selectOptionByRightclickOnDateTime(option);
            break;
          case ALLDAY:
            evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace("$date", date));

            selectOptionByRightclickOnDateTime(option);
            break;
          default:
            evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date", date));

            selectOptionByRightclickOnDateTime(option);
            break;
        }
        break;
      case MONTH:
        evt.rightClickOnElement(ELEMENT_EVENT_TASK_MONTH_DATE.replace("$date", date));

        selectOptionByRightclickOnDateTime(option);
        break;
      case WORKWEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date", date));

            selectOptionByRightclickOnDateTime(option);
            break;
          case ALLDAY:
            evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace("$date", date));

            selectOptionByRightclickOnDateTime(option);
            break;
          default:
            evt.rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date", date));

            selectOptionByRightclickOnDateTime(option);
            break;
        }
        break;
      default:
        info("You don't select a optionDay.Please select other optionDay.");
        break;
    }

  }

  /**
   * Select an option in context menu
   * 
   * @param option  contextMenuAddEditEvenTaskOption
   */
  public void selectOptionByRightclickOnDateTime(contextMenuAddEditEvenTaskOption option) {
    switch (option) {
      case ADD_EVENT:
        info("Select Add new event option");
        evt.click(ELEMENT_CONTEXT_MENU_ADD_EVENT);
        break;
      case ADD_TASK:
        info("Select Add new task option");
        evt.click(ELEMENT_CONTEXT_MENU_ADD_TASK);
        break;
      default:
        info("No option to select");
        break;
    }
  }

  /**
   * verify event is not exitst
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   * @param date date of event: format (MMM dd yyyy)
   */
  public void verifyIsNotPresentEventTaskWithDateTime(String name,
                                                      String date,
                                                      selectViewOption view,
                                                      selectDayOption optionDay) {
    info("Verify task and event is not displayed on calendar panel");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    goToView(view);
    switch (view) {
      case DAY:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                    date));
            break;
          case ALLDAY:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date",
                    date));
            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                    date));
            break;
        }
        break;
      case LIST:
        if (evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE, 5000, 0) != null) {
          evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
          while (!(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                  .getText()
                  .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText()))) {
            evt.click(ELEMENT_NEXT_PAGE);
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date",
                    date));
          }
          evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        } else {
          evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date",
                  date));
        }
        break;
      case MONTH:
        if ($(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).is(Condition.not(Condition.visible))

                && $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date))).is(Condition.visible)
                ) {
          $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date))).click();
          $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date",
                  date))).shouldNotBe(Condition.visible);
        } else
          $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
        break;
      case WORKWEEK:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                    date));
            break;
          case ALLDAY:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date",
                    date));

            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date",
                    date));
            break;
        }
        break;
      default:
        switch (optionDay) {
          case DETAILTIME:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
          case ALLDAY:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
            break;
          default:
            evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
            break;
        }
        break;
    }
  }

  /**
   * goToRightMenuTaskEventFromAnyView
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   * @param date date of event: format (MMM dd yyyy)
   */
  public void goToRightMenuTaskEventFromAnyView(String name, selectViewOption view, selectDayOption optionDay, String date) {
    info("click right menu task/event");
    executeJavaScript("window.scrollBy(0,-2000)", "");
    refresh();
    switch (view) {
      case DAY:
        goToRightMenuTaskEventFromDayView(name, optionDay);
        break;
      case WEEK:
        goToRightMenuTaskEventFromWeekView(name, optionDay, date);
        break;
      case LIST:
        goToRightMenuTaskEventFromListView(name, date);
        break;
      case MONTH:
        goToRightMenuTaskEventFromMonthView(name, date);
        break;
      case WORKWEEK:
        goToRightMenuTaskEventFromWorkWeekView(name, optionDay, date);
        break;
      default:
        goToRightMenuTaskEventFromDayView(name, optionDay);
        break;
    }

  }

  /**
   * Delete an event or task
   *
   * @param name name of event or task
   * @param view view: DAY, WEEK, LIST, MONTH, WORKWEEK;
   * @param optionDay select ONEDAY or ALLDAY
   * @param date date of event: format (MMM dd yyyy)
   * @param opParams opParams[0]: false - Don't verify confirm message to delete
   *          task/event true - Verify confirm message to delete task/event
   *          opParms[1]: true - Confirm message of event false - Confirm message
   *          of task
   */
  public void deleteEventTask(String name, selectViewOption view, selectDayOption optionDay, String date, Object... opParams) {
    boolean isVerify = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    boolean isEvent = (Boolean) (opParams.length > 1 ? opParams[1] : false);
    info("Delete event/tak: " + name);
    Button button = new Button(this.testBase);
    goToRightMenuTaskEventFromAnyView(name, view, optionDay, date);
    $(ELEMENT_CONTEXT_MENU_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
    if (isVerify) {
      if (isEvent) {
        $(byText(ELEMENT_CONFIRM_DELETE_EVENT_MSG)).waitUntil(Condition.appears, Configuration.timeout);
        alert.verifyAlertMessage(ELEMENT_CONFIRM_DELETE_EVENT_MSG);
        $(byText(ELEMENT_CONFIRM_DELETE_EVENT_MSG)).waitUntil(Condition.disappears, Configuration.timeout);
      } else
        alert.verifyAlertMessage(ELEMENT_CONFIRM_DELETE_TASK_MSG);
      testBase.getExoWebDriver().getWebDriver().navigate().refresh();

      verifyIsNotPresentEventTask(name, view, optionDay);
    } else
      button.yes();
    $(byText(name)).waitUntil(Condition.disappears, Configuration.timeout);
  }

  /**
   * select category from list
   *
   * @param option category type: call, all, client, holiday,anniversary
   */
  public void selectCategory(selectCategoryOption option) {
    info("Select category from list");
    if (option != null) {
      switch (option) {
        case ALL:
          evt.select(ELEMENT_CATEGORY_OPTION, "All", 2);
          break;
        case ANNIVERSARY:
          evt.select(ELEMENT_CATEGORY_OPTION, "Anniversary", 2);
          break;
        case CALL:
          evt.select(ELEMENT_CATEGORY_OPTION, "Calls", 2);
          break;
        case CLIENT:
          evt.select(ELEMENT_CATEGORY_OPTION, "Clients", 2);
          break;
        case HOLIDAY:
          evt.select(ELEMENT_CATEGORY_OPTION, "Holiday", 2);
          break;
        case MEETING:
          evt.select(ELEMENT_CATEGORY_OPTION, "Meeting", 2);
          break;
        default:
          break;

      }
    }
  }

  /**
   * Do quick calendar search
   *
   * @param keyword keyword which is to input into search box
   */
  public void quickSearchCalendar(String keyword) {
    info("----Type in quick search box----");
    $(ELEMENT_QUICK_SEARCH_INPUT).setValue(keyword).pressEnter();
    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Advance search in Calendar
   *
   * @param keyword keyword which is to input into search box
   */
  public void advanceSearchCalendar(String keyword) {
    info("----Open Advance Search window----");
    $(byXpath(ELEMENT_BUTTON_OPEN_ADVANCE_SEARCH_FORM)).waitUntil(Condition.visible,Configuration.timeout);
    $(byXpath(ELEMENT_BUTTON_OPEN_ADVANCE_SEARCH_FORM)).click();
    info("----Input keyword----");
    $(byXpath(ELEMENT_INPUT_TEXT_ADVANCE_SEARCH)).waitUntil(Condition.visible,Configuration.timeout);
    $(byXpath(ELEMENT_INPUT_TEXT_ADVANCE_SEARCH)).setValue(keyword);
    $(byXpath(ELEMENT_BUTTON_SEARCH_ADVANCE_SEARCH)).click();
    info("----Confirm search result displayed----");

    $(byXpath(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT)).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * check on checkboxes of events/tasks that has different name in Month view
   * 
   * @param names String
   */
  public void checkBoxEventTaskInMonthView(String[] names) {
    // goToView(selectViewOption.MONTH);
    for (int i = 0; i < names.length; i++) {
      info("Select the events/tasks:" + names[i]);
      evt.check(ELEMENT_EVENT_TASK_CHECKBOX.replace("$name", names[i]), 2);
    }

  }

  /**
   * check on check boxes of recurring event that have same name in Monthview
   *
   * @param name the name of recurring event
   * @param number the event's number of recurring event as first event =1, second
   *          event =2,.. Example: number="2". This means that second recurring
   *          event will be checked
   * @param opt the summary of recurring event instances of the same series that
   *          will be checked. Example:opt=3. This means that 3 recurring events
   *          will be checked
   */
  public void checkBoxEventTaskInMonthView(String name, String number, int... opt) {
    // goToView(selectViewOption.MONTH);
    if (opt.length > 0) {
      int event_Start = Integer.parseInt(number); // from the event that will be
      // checked
      int number_event = opt[0] + event_Start; // sum event's number will be
      // checked
      for (int i = event_Start; i < number_event; i++) {
        info("Click on check box of event's number:" + i);
        evt.check(ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX.replace("$name", name).replace("$number",
                Integer.toString(i)),
                2);
      }
    } else {
      info("Click on check box of event's number:" + number);
      evt.check(ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX.replace("$name", name).replace("$number", number), 2);
    }

  }

  /**
   * Check box on Event/task by date of Month and Week view
   * 
   * @param name String
   * @param date String
   */
  public void checkBoxEventTaskInMonthView(String name, String date) {
    info("Select the event/task");
    evt.check(ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX_DATE.replace("$date", date).replace("$name", name), 2);

  }

  /**
   * Click on Next arrow of header panel to jump to next days/weeks/months
   * 
   * @param number int
   */
  public void nextDate(int number) {
    if (number != 0) {
      info("Jump to next:" + number + " days/weeks/months");
      for (int i = 0; i < number; i++) {
        info("Click on Next arrow");
        evt.click(ELEMENT_NEXT_BUTTON_ANY_VIEW);

      }
    }
  }

  /**
   * Click on Next arrow to jump to next month in Calendar mini
   * 
   * @param number int
   */
  public void nextMonth(int number) {
    if (number != 0) {
      info("Jump to next:" + number + " months");
      for (int i = 0; i < number; i++) {
        info("Click on Next arrow");
        evt.click(ELEMENT_CALENDAR_MINI_NEXT_MONTH);

      }
    }
  }

  /**
   * Click on Previous arrow of header panel to back previous days/weeks/months
   * 
   * @param number int
   */
  public void previousDate(int number) {
    if (number != 0) {
      info("Jump to previous:" + number + " days/weeks/months");
      for (int i = 0; i < number; i++) {
        info("Click on Previous arrow");
        evt.click(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);

      }
    }
  }

  /**
   * Click on previous arrow to jump to previous month in Calendar mini
   * 
   * @param number int
   */
  public void previousMonth(int number) {
    if (number != 0) {
      info("Jump to previous:" + number + " months");
      for (int i = 0; i < number; i++) {
        info("Click on Previous arrow");
        evt.click(ELEMENT_CALENDAR_MINI_PREVIOUS_MONTH);

      }
    }
  }

  /**
   * Show/Hide left panel
   */
  public void showHideLefPanel() {
    info("Click on the show/hide button");
    if (evt.waitForAndGetElement(ELEMENT_CALENDAR_MINI, 2000, 0) != null) {
      evt.click(ELEMENT_SHOW_HIDE_LEFT_PANEL);
      evt.waitForElementNotPresent(ELEMENT_CALENDAR_MINI);
      info("The left panel is hidded");
    } else {
      evt.click(ELEMENT_SHOW_HIDE_LEFT_PANEL);
      evt.waitForAndGetElement(ELEMENT_CALENDAR_MINI);
      info("The left panel is shown");
    }
  }

  /**
   * Category list
   */
  public enum selectCategoryOption {
    ALL, MEETING, CALL, CLIENT, ANNIVERSARY, HOLIDAY
  }

  /**
   * View list in calendar
   */
  public enum selectViewOption {
    DAY, WEEK, LIST, MONTH, WORKWEEK
  }

  /**
   * Select an option when creating an Event/Task: ONE DAY or ALL DAY
   */
  public enum selectDayOption {
    DETAILTIME, ALLDAY;
  }

  /**
   * right click on a datetime to open Quick ADD/EDIT an event/task
   */
  public enum contextMenuAddEditEvenTaskOption {
    ADD_EVENT, ADD_TASK;
  }
}