package org.exoplatform.platform.qa.ui.calendar.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        ELEMENT_CALENDAR_DAY_BUTTON.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        break;
      case WEEK:
        ELEMENT_CALENDAR_WEEK_BUTTON.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs + Configuration.collectionsTimeout).click();
        break;
      case LIST:
        ELEMENT_CALENDAR_LIST_BUTTON.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        break;
      case MONTH:
        sleep(Configuration.timeout);
        $(byXpath(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Month"))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        $(byXpath(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Month"))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
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
      if ($(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).is(Condition.not(Condition.visible))) {
        $(byXpath("(//*[@data-original-title='Next Month']//i)[2]")).waitUntil(Condition.visible, Configuration.timeout).click();
        $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date",
                date))).contextClick();
        if(!$(ELEMENT_CONTEXT_MENU_DELETE).isDisplayed())
        {
         $(byXpath("//*[@id='UIMonthViewGrid']//*[contains(@starttimefull,'Feb 07 2020')]/following::*[@class='eventOnDayContent']//*[@class='eventSummary']//*[contains(text(),'${title}')]".replace("${title}",name))).contextClick();

        }
      }
      else
      {
        sleep(2000);
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
        $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date",
                date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).contextClick();
        sleep(1000);
        if($(ELEMENT_CONTEXT_MENU_DELETE).isDisplayed()) {
          info("Delete Icon exists");
        }
        else{
          testBase.getExoWebDriver().getWebDriver().navigate().refresh();
          $(byXpath("//*[@class='eventSummary']//*[contains(text(),'${titName}')]".replace("${titName}",name))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).contextClick();
        }

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
          ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
        }
        $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
        $(byText(name)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs + Configuration.timeout).contextClick();
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
    $(ELEMENT_CONTEXT_MENU_EDIT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
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
        sleep(2000);
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
            $(byText(name)).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
            break;
          case ALLDAY:
            $(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
            break;
          default:
            $(byXpath(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
            break;
        }

        break;
      case WEEK:
        switch (optionDay) {
          case DETAILTIME:
            $(byText(name)).waitUntil(Condition.appears, Configuration.collectionsTimeout);
            break;
          case ALLDAY:

            $(byXpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout);
            break;
          default:
            $(byXpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name))).waitUntil(Condition.visible,Configuration.timeout);
            break;
        }
        break;
      case LIST:
        if ($(ELEMENT_TOTAL_PAGE).is(Condition.exist)) {
          $(byXpath(ELEMENT_ANY_PAGE.replace("$page", "1"))).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
          while ((evt.waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name), 5000, 0) == null)
                  && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                  .getText()
                  .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
            evt.click(ELEMENT_NEXT_PAGE);
          $(byXpath(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name))).waitUntil(Condition.visible,Configuration.collectionsTimeout);
          $(byXpath(ELEMENT_ANY_PAGE.replace("$page", "1"))).waitUntil(Condition.visible,Configuration.timeout).click();
        } else {
          sleep(Configuration.collectionsTimeout);
          $(byText(name)).waitUntil(Condition.appears, Configuration.collectionsTimeout);
        }
        break;
      case MONTH:
        $(byXpath(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
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
            $(byText(name)).waitUntil(Condition.appears, Configuration.collectionsTimeout);
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
          if(!$(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).isDisplayed()) {
            $(byXpath("(//i[@class='uiIconMiniArrowRight uiIconLightGray'])[2]")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
            $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).waitUntil(Condition.visible,Configuration.timeout);
            $(byXpath("(//i[@class='uiIconMiniArrowLeft uiIconLightGray'])[3]")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
          }
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
          if ($(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).isDisplayed()) {
          $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).waitUntil(Condition.visible,Configuration.timeout).hover();
          $(byXpath("//*[@class='popover-content']//*[text()='$name']".replace("$name", name))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);}
          else
          {
            $(byXpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
          }
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
        sleep(2000);
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
    $(ELEMENT_CONTEXT_MENU_DELETE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
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
    $(byText(name)).waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
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

}