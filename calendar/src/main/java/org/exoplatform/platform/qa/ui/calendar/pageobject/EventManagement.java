package org.exoplatform.platform.qa.ui.calendar.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.bouncycastle.crypto.tls.ConnectionEnd.server;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_USER_CLOSE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TITLE_WIKI_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class EventManagement {
  private final TestBase testBase;

  public PlatformPermission pPer;

  public CalendarHomePage cHome;

  public ManageAlert alert;

  private ElementEventTestBase evt;

  public EventManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    pPer = new PlatformPermission(testBase);
    cHome = new CalendarHomePage(testBase);
    alert = new ManageAlert(testBase);
  }

  /**
   * @param date date to create event format: MM/dd/yyyy (Ex: 12/09/2014)
   * @param time time to create event format: hh:mm (Ex: 12:30)
   */
  public void goToAddEventByLeftClickFromMainPanel(String date, String time) {
    info("Go to add task by right clicking from main panel");
    SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat format2 = new SimpleDateFormat("MMM dd yyyy");
    String tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
    Date tempDate1 = null;
    String tempTime = testBase.getCurrentDate("HH") + ":00";

    info("Get date");
    if (date != null && date != "") {
      try {
        tempDate1 = format1.parse(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      tempDate2 = format2.format(tempDate1);
      info("Selected date is " + tempDate2);
    } else {
      tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
      info("Selected date is current date" + tempDate2);
    }

    info("Get time");
    if (time != null && time != "") {
      tempTime = time;
      info("Selected date is " + tempTime);
    } else {
      tempTime = testBase.getCurrentDate("HH") + ":00";
      info("Selected date is current date" + tempTime);
    }
    String cell = ELEMENT_CELL_TO_WORKING_PANEL.replace("$date", tempDate2).replace("$time", tempTime);
    info(cell);
    $(byXpath(cell)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_QUICK_ADD_EVENT_POPUP).waitUntil(Condition.visible, Configuration.collectionsTimeout);
  }

  /**
   * @param date date to create event format: MM/dd/yyyy (Ex: 12/09/2014)
   * @param time time to create event format: hh:mm (Ex: 12:30)
   */
  public void goToAddEventByRightClickFromMainPanel(String date, String time) {
    info("Go to add task by right clicking from main panel");
    SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat format2 = new SimpleDateFormat("MMM dd yyyy");
    String tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
    Date tempDate1 = null;
    String tempTime = testBase.getCurrentDate("HH") + ":00";

    info("Get date");
    if (date != null && date != "") {
      try {
        tempDate1 = format1.parse(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      tempDate2 = format2.format(tempDate1);
      info("Selected date is " + tempDate2);
    } else {
      tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
      info("Selected date is current date" + tempDate2);
    }

    info("Get time");
    if (time != null && time != "") {
      tempTime = time;
      info("Selected date is " + tempTime);
    } else {
      tempTime = testBase.getCurrentDate("HH") + ":00";
      info("Selected date is current date" + tempTime);
    }
    String cell = ELEMENT_CELL_TO_WORKING_PANEL.replace("$date", tempDate2).replace("$time", tempTime);
    $(byXpath(cell)).contextClick();
    $(ELEMENT_CONTEXT_MENU_ADD_EVENT).click();
    $(ELEMENT_QUICK_ADD_EVENT_POPUP).waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Open add event/task by right click in Month view
   *
   * @param date date to create event format: MM/dd/yyyy (Ex: 12/09/2014)
   */
  public void goToAddEventByRightClickForMothnView(String date) {
    info("Go to add task by right clicking from main panel");
    SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat format2 = new SimpleDateFormat("MMM dd yyyy");
    String tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
    Date tempDate1 = null;

    info("Get date");
    if (date != null && date != "") {
      try {
        tempDate1 = format1.parse(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      tempDate2 = format2.format(tempDate1);
      info("Selected date is " + tempDate2);
    } else {
      tempDate2 = testBase.getCurrentDate("MMM dd yyyy");
      info("Selected date is current date" + tempDate2);
    }
    String cell = ELEMENT_CELL_TO_MONTH_WORKING_PANEL.replace("$date", tempDate2);
    evt.rightClickOnElement(cell);
    evt.click(ELEMENT_CONTEXT_MENU_ADD_EVENT);
    evt.waitForAndGetElement(ELEMENT_QUICK_ADD_EVENT_POPUP);
  }

  /**
   * Open "Add new event" form from action bar
   */
  public void goToAddEventFromActionBar() {
    info("Go to Add Event page from action bar");
    executeJavaScript("window.scrollBy(0,-2000)", "");
    $(ELEMENT_BUTTON_EVENT).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_EVENT_DRAWER.parent().waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Input into basic fields of Quick EVENT form
   *
   * @param name name of a EVENT
   * @param note note of a EVENT
   * @param opt  optional parameter opt[0]: calendar opt[1]: category
   */
  public void inputBasicQuickEvent(String name, String note, String... opt) {
    info("Input into basic fields of Quick EVENT form");
    if (name != null) {
      $(ELEMENT_EVENT_TITLE_DRAWER).setValue(name);
    }
    if (note != null) {
      $(ELEMENT_ADD_EDIT_EVENT_NOTE).setValue(note);
    }
    if (opt.length > 0 && opt[0] != null) {
      $(byXpath("//*[@class='control-label']/following::*[@class='dropdown']")).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
      $(byXpath("//*[@class='dropdown-submenu']//*[@class='trimText' and contains(text(),'${calendarName}')]".replace("${calendarName}",opt[0]))).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    }
    if (opt.length > 1 && opt[1] != null) {
      $(byXpath("//*[@id='ExoCalendarEventForm']/div[1]/div[2]/form/div[1]/div[2]/span/select")).selectOption(opt[1]);
    }

  }

  /**
   * Input into basic fields of EVENT detail form
   *
   * @param name name of a EVENT
   * @param note note of a EVENT
   * @param opt  optional parameter opt[0]: calendar opt[1]: category opt[2]:
   *             location
   */
  public void inputBasicDetailEvent(String name, String note, String... opt) {
    info("Input into basic fields of Quick EVENT form");
    if (name != null) {
      $(ELEMENT_ADD_EDIT_EVENT_NAME).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(name);
    }

    if (note != null) {
      $(ELEMENT_ADD_EDIT_EVENT_NOTE).waitUntil(Condition.visible,Configuration.collectionsTimeout).setValue(note);
    }

    if (opt.length > 1 && opt[1] != null) {
      $(ELEMENT_ADD_EDIT_EVENT_CATEGORY).selectOption(opt[1]);
    }
    if (opt.length > 2 && opt[2] != null) {
      $(ELEMENT_ADD_EDIT_EVENT_LOCATION).setValue(opt[2]);
    }
  }

  /**
   * Input into "From, To" and check/uncheck allday checkbox fields of a EVENT in
   * quick form
   *
   * @param from   From date, time of a EVENT i.e.: 11/06/2013 14:00
   * @param to     To date, time of a EVENT, i.e.: 11/06/2013 14:00
   * @param allDay Option "all day" of a EVENT
   */
  public void inputFromToQuickEvent(String from, String to, boolean allDay) {
    info("Input into From, To and check/uncheck allday checkbox fields of a EVENT");
    if (allDay) {
      info("Check all day, then select date");
      evt.check(ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY, 2);
      if ((from != null) & (from != "")) {
        String[] dateTimeFrom = from.split(" ");
        $(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE).click();
        if (dateTimeFrom[0].charAt(0) == '0') {
         String day = dateTimeFrom[0].substring(1, 2);
         String year = dateTimeFrom[0].substring(6, 10);
          if(dateTimeFrom[0].charAt(3) =='0')
         {
           String month = dateTimeFrom[0].substring(4, 5);
           String date = year + ',' + day + ',' + month;
           do
           {
             DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
             LocalDateTime now = LocalDateTime.now();
             if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
             {

             }
           if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
           {
            $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
           }

           if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
           {
             $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

           }
           }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
           $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
         }
          String month = dateTimeFrom[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }
         else
        {
          String day = dateTimeFrom[0].substring(0, 2);
          String year = dateTimeFrom[0].substring(6, 10);
          if(dateTimeFrom[0].charAt(3) =='0')
          {
            String month = dateTimeFrom[0].substring(4, 5);
            String date = year + ',' + day + ',' + month;
            do
            {
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
              LocalDateTime now = LocalDateTime.now();
              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
              {

              }
              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
              {
                $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }

              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
              {
                $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

              }
            }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
            $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
          }
          String month = dateTimeFrom[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }

      }
      if ((to != null) & (to != "")) {
        String[] dateTimeTo = to.split(" ");
        $(ELEMENT_QUICK_INPUT_EVENT_TO_DATE).click();
        if (dateTimeTo[0].charAt(0) == '0') {
          String day = dateTimeTo[0].substring(1, 2);
          String year = dateTimeTo[0].substring(6, 10);
          if(dateTimeTo[0].charAt(3) =='0')
          {
            String month = dateTimeTo[0].substring(4, 5);
            String date = year + ',' + day + ',' + month;
            do
            {
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
              LocalDateTime now = LocalDateTime.now();
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
              {

              }
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
              {
                $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }

              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
              {
                $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

              }
            }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
            $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
          }
          String month = dateTimeTo[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }
        else
        {
          String day = dateTimeTo[0].substring(0, 2);
          String year = dateTimeTo[0].substring(6, 10);
          if(dateTimeTo[0].charAt(3) =='0')
          {
            String month = dateTimeTo[0].substring(4, 5);
            String date = year + ',' + day + ',' + month;
            do
            {
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
              LocalDateTime now = LocalDateTime.now();
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
              {

              }
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
              {
                $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }

              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
              {
                $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

              }
            }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
            $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
          }
          String month = dateTimeTo[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }

      }

    } else {
      info("Uncheck all day, then select date time");
      evt.uncheck(ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY, 2);
      if ((from != null) & (from != "")) {
        String[] dateTimeFrom = from.split(" ");
        $(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE).click();
        if (dateTimeFrom[0].charAt(0) == '0') {
          String day = dateTimeFrom[0].substring(1, 2);
          String year = dateTimeFrom[0].substring(6, 10);
          if(dateTimeFrom[0].charAt(3) =='0')
          {
            String month = dateTimeFrom[0].substring(4, 5);
            String date = year + ',' + day + ',' + month;
            do
            {
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
              LocalDateTime now = LocalDateTime.now();
              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
              {

              }
              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
              {
                $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }

              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
              {
                $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

              }
            }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
            $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
          }
          String month = dateTimeFrom[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }
        else
        {
          String day = dateTimeFrom[0].substring(0, 2);
          String year = dateTimeFrom[0].substring(6, 10);
          if(dateTimeFrom[0].charAt(3) =='0')
          {
            String month = dateTimeFrom[0].substring(4, 5);
            String date = year + ',' + day + ',' + month;
            do
            {
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
              LocalDateTime now = LocalDateTime.now();
              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
              {

              }
              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
              {
                $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }

              if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
              {
                $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

              }
            }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
            $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
          }
          String month = dateTimeFrom[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeFrom[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }

        $(byXpath("(//input[@class='cbb_input'])[1]")).waitUntil(Condition.visible,Configuration.timeout).setValue(dateTimeFrom[1]);
        if (dateTimeFrom.length > 1) {
          $(ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT).waitUntil(Condition.visible,Configuration.timeout).click();
        }
      }
      if ((to != null) & (to != "")) {
        String[] dateTimeTo = to.split(" ");
        $(ELEMENT_QUICK_INPUT_EVENT_TO_DATE).click();
        if (dateTimeTo[0].charAt(0) == '0') {
          String day = dateTimeTo[0].substring(1, 2);
          String year = dateTimeTo[0].substring(6, 10);
          if(dateTimeTo[0].charAt(3) =='0')
          {
            String month = dateTimeTo[0].substring(4, 5);
            String date = year + ',' + day + ',' + month;
            do
            {
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
              LocalDateTime now = LocalDateTime.now();
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
              {

              }
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
              {
                $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }

              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
              {
                $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }
            }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
            $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
          }
          String month = dateTimeTo[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }
        else
        {
          String day = dateTimeTo[0].substring(0, 2);
          String year = dateTimeTo[0].substring(6, 10);
          if(dateTimeTo[0].charAt(3) =='0')
          {
            String month = dateTimeTo[0].substring(4, 5);
            String date = year + ',' + day + ',' + month;
            do
            {
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
              LocalDateTime now = LocalDateTime.now();
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
              {

              }
              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
              {
                $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
              }

              if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
              {
                $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

              }
            }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
            $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
          }
          String month = dateTimeTo[0].substring(3, 5);
          String date = year + ',' + day + ',' + month;
          do
          {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) == 0)
            {

            }
            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) < 0)
            {
              $(PREVIOUS_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            }

            if(dateTimeTo[0].substring(0,2).compareTo(dateTimeFormatter.format(now).substring(0,2)) > 0)
            {
              $(NEXT_MONTH_DATE_PICKER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

            }
          }while(!$(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).exists());
          $(By.xpath("//*[@onclick='eXo.UIDateTimePicker.setDate(${date})']".replace("${date}", date))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }

        $(byXpath("(//input[@class='cbb_input'])[2]")).waitUntil(Condition.visible,Configuration.timeout).setValue(dateTimeTo[1]);
        if (dateTimeTo.length > 1) {
          $(ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT).waitUntil(Condition.visible,Configuration.timeout).click();
        }
      }
    }
  }

  /**
   * Input into "From, To" and check/uncheck allday checkbox fields of a EVENT in
   * detail form
   *
   * @param from   From date, time of a EVENT i.e.: 11/06/2013 14:00
   * @param to     To date, time of a EVENT, i.e.: 11/06/2013 14:00
   * @param allDay Option "all day" of a EVENT
   */
  public void inputFromToDetailEvent(String from, String to, boolean allDay, boolean... opt) {
    info("Input into From, To and check/uncheck allday checkbox fields of a EVENT");
    if (allDay) {
      info("Check all day, then select date");
      evt.check(ELEMENT_ADD_EDIT_EVENT_ALLDAY, 2);
      if ((from != null) & (from != ""))
        evt.type(ELEMENT_ADD_EDIT_EVENT_FROM_DATE, from, true);
      if ((to != null) & (to != ""))
        evt.type(ELEMENT_ADD_EDIT_EVENT_TO_DATE, to, true);

    } else {
      info("Uncheck all day, then select date time");
      evt.uncheck(ELEMENT_ADD_EDIT_EVENT_ALLDAY, 2);
      if ((from != null) & (from != "")) {
        String[] dateTimeFrom = from.split(" ");
        if (dateTimeFrom.length > 0)
          evt.type(ELEMENT_ADD_EDIT_EVENT_FROM_DATE, dateTimeFrom[0], true);
        if (dateTimeFrom.length > 1) {
          if (opt.length > 0) {
            evt.type(ELEMENT_ADD_EDIT_EVENT_FROM_TIME_INPUT, dateTimeFrom[1], true);
          } /*else {
            evt.click(ELEMENT_ADD_EDIT_EVENT_FROM_TIME_INPUT, 2);
            evt.click(ELEMENT_ADD_EDIT_EVENT_SELECT_FROM_TIME.replace("${time}", dateTimeFrom[1]));
          }*/
        }
      }
      if ((to != null) & (to != "")) {
        String[] dateTimeTo = to.split(" ");
        if (dateTimeTo.length > 0)
          evt.type(ELEMENT_ADD_EDIT_EVENT_TO_DATE, dateTimeTo[0], true);
        if (dateTimeTo.length > 1) {
          if (opt.length > 0) {
            evt.type(ELEMENT_ADD_EDIT_EVENT_TO_TIME_INPUT, dateTimeTo[1], true);
          }/* else {
            evt.click(ELEMENT_ADD_EDIT_EVENT_TO_TIME_INPUT, 2);
            evt.click(ELEMENT_ADD_EDIT_EVENT_SELECT_TO_TIME.replace("${time}", dateTimeTo[1]));
          }*/
        }
      }
    }
  }

  /**
   * Input into basic fields of Quick EVENT form
   *
   * @param name   String
   * @param from   From date, time of a EVENT i.e.: 11/06/2013 14:00
   * @param to     To date, time of a EVENT, i.e.: 11/06/2013 14:00
   * @param allDay boolean
   * @param opt    String
   */
  public void inputDataEventInQuickForm(String name, String note, String from, String to, boolean allDay, String... opt) {
    inputFromToQuickEvent(from, to, allDay);
    inputBasicQuickEvent(name, note, opt);

  }

  /**
   * Input into basic fields of detail EVENT form
   *
   * @param name   String
   * @param note   String
   * @param from   From date, time of a EVENT i.e.: 11/06/2013 14:00
   * @param to     To date, time of a EVENT, i.e.: 11/06/2013 14:00
   * @param allDay boolean
   * @param opt    boolean
   */
  public void inputDataEventInDetailForm(String name, String note, String from, String to, boolean allDay, String... opt) {
    inputBasicDetailEvent(name, note, opt);
    inputFromToDetailEvent(from, to, allDay);
  }

  /**
   * Attach file in "Add new EVENT" form
   *
   * @param path path of attachment of a EVENT
   */
  public void attachFileToEvent(String path, Boolean... opt) {
    String fullPath = "";
    if ("win".equals(server)) {
      fullPath = "TestData\\" + path;
    } else {
      fullPath = "TestData/" + path;
    }
    evt.click(ELEMENT_EVENT_ADD_ATTACHMENT);
    evt.click(ELEMENT_SELECT_FILE_BUTTON);
    testBase.uploadFileUsingRobot(fullPath);
    info("opt.length:" + opt.length);

    if (opt.length == 0) {
      evt.waitForAndGetElement(ELEMENT_ATTACHMENT_FORM_FILE_NAME.replace("$fileName", path));
      evt.click(ELEMENT_ATTACHMENT_SAVE_BUTTON, 0, true);

      evt.waitForAndGetElement(ELEMENT_ATTACH_FILE_NAME.replace("$fileName", path));
    }
  }

  /**
   * Check default suggestion EVENT time in detail add form
   *
   * @param fromDateTime (Format: MM/dd/yyyy HH:mm)
   * @param toDateTime   (Format: MM/dd/yyyy HH:mm)
   * @param duration     int
   */
  public void checkSuggestionEventTimeInQuickForm(String fromDateTime, String toDateTime, int duration) {
    info("Check date is current date");
    DateFormat formatterTimeTemp = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    DateFormat formatterTime = new SimpleDateFormat("HH:mm");
    DateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
    String dateFrom = $(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE).getValue();
    String dateTo = $(ELEMENT_QUICK_INPUT_EVENT_TO_DATE).getValue();
    String fromTime = evt.waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_FROM_TIME, testBase.getDefaultTimeout(), 1, 2)
            .getAttribute("value");
    String toTime = evt.waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_TO_TIME, testBase.getDefaultTimeout(), 1, 2)
            .getAttribute("value");

    info("Check default suggestion EVENT time");
    if (fromDateTime == null || fromDateTime == "") {
      info("Check time suggestion default");
      if (testBase.getCurrentDate("MM/dd/yyyy").charAt(0) == '0') {
        assert ("0" + dateFrom).equals(testBase.getCurrentDate("MM/dd/yyyy"));
      }
      else{
        assert dateFrom.equals(testBase.getCurrentDate("MM-dd-yyyy"));
       }
    } else {
      info("Check suggesion when select from time");
      try {
        Date fr = formatterDate.parse(fromDateTime);
        Date frTime = formatterTimeTemp.parse(fromDateTime);
        assert dateFrom.equals(formatterDate.format(fr));
        assert fromTime.equals(formatterTime.format(frTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (toDateTime == null || toDateTime == "") {
      info("Check time suggestion default");
      if (testBase.getCurrentDate("MM/dd/yyyy").charAt(0) == '0') {
        assert ("0" + dateFrom).equals(testBase.getCurrentDate("MM/dd/yyyy"));
      }
      else{
        assert dateFrom.equals(testBase.getCurrentDate("MM-dd-yyyy"));
      }
    } else {
      info("Check suggesion when select to time");
      try {
        Date to = formatterDate.parse(toDateTime);
        Date tTime = formatterTimeTemp.parse(toDateTime);
        assert dateTo.equals(formatterDate.format(to));
        assert toTime.equals(formatterTime.format(tTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    info("Check duration");
    try {
      Date fr = formatterTime.parse(fromTime);
      Date to = formatterTime.parse(toTime);
      long diff = (to.getTime() - fr.getTime()) / 60000;
      info("Duration is " + diff + " minus");
      assert duration == (int) diff;
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check default suggestion EVENT time in detail add form
   *
   * @param fromDateTime (Format: MM/dd/yyyy HH:mm)
   * @param toDateTime   (Format: MM/dd/yyyy HH:mm)
   * @param duration     int
   */
  public void checkSuggestionEventTimeInDetailForm(String fromDateTime, String toDateTime, int duration) {
    info("Check date is current date");
    DateFormat formatterTimeTemp = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    DateFormat formatterTime = new SimpleDateFormat("HH:mm");
    DateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
    String dateFrom = testBase.getValue(ELEMENT_ADD_EDIT_EVENT_FROM_DATE);
    String dateTo = testBase.getValue(ELEMENT_ADD_EDIT_EVENT_TO_DATE);
    String fromTime = evt.waitForAndGetElement(ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME, testBase.getDefaultTimeout(), 1, 2)
            .getAttribute("value");
    String toTime = evt.waitForAndGetElement(ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME, testBase.getDefaultTimeout(), 1, 2)
            .getAttribute("value");

    info("Check default suggestion EVENT time");
    if (fromDateTime == null || fromDateTime == "") {
      info("Check time suggestion default");
      if (testBase.getCurrentDate("MM/dd/yyyy").charAt(0) == '0') {
        assert ("0" + dateFrom).equals(testBase.getCurrentDate("MM/dd/yyyy"));
      }
      else{
        assert dateFrom.equals(testBase.getCurrentDate("MM-dd-yyyy"));
      }
    } else {
      info("Check suggesion when select from time");
      try {
        Date fr = formatterDate.parse(fromDateTime);
        Date frTime = formatterTimeTemp.parse(fromDateTime);
        assert dateFrom.equals(formatterDate.format(fr));
        assert fromTime.equals(formatterTime.format(frTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (toDateTime == null || toDateTime == "") {
      info("Check time suggestion default");
      if (testBase.getCurrentDate("MM/dd/yyyy").charAt(0) == '0') {
        assert ("0" + dateFrom).equals(testBase.getCurrentDate("MM/dd/yyyy"));
      }
      else{
        assert dateFrom.equals(testBase.getCurrentDate("MM-dd-yyyy"));
      }
    } else {
      info("Check suggesion when select to time");
      try {
        Date to = formatterDate.parse(toDateTime);
        Date tTime = formatterTimeTemp.parse(toDateTime);
        assert dateTo.equals(formatterDate.format(to));
        assert toTime.equals(formatterTime.format(tTime));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    info("Check duration");
    try {
      Date fr = formatterTime.parse(fromTime);
      Date to = formatterTime.parse(toTime);
      long diff = (to.getTime() - fr.getTime()) / 60000;
      info("Duration is " + diff + " minus");
      assert duration == (int) diff;
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * add participants into schedule
   *
   * @param user String
   * @param type int
   */
  public void addParticipants(String user, int type) {
    $(ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_SCHEDULE_TAB).click();
    pPer.selectUserPermission(user, type);
    if (type != 2 && type != 3 && type != 4)
      $(byXpath(ELEMENT_USER_CHECKBOX_USERNAME.replace("${user}", user))).parent().waitUntil(Condition.visible,Configuration.timeout);
    else
      $(byXpath(ELEMENT_USER_CHECKBOX_FULLNAME.replace("${user}", user))).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * convertFromTimeToIndex
   *
   * @param time (ex: HH:mm)
   */
  @SuppressWarnings("deprecation")
  public int convertFromTimeToIndex(String time) {
    int index = 0;
    SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
    try {
      Date tempHour = hour.parse(time);
      if (tempHour.getMinutes() == 0)
        index = (Integer.valueOf(tempHour.getHours()) * 4) + 2;
      else
        index = (Integer.valueOf(tempHour.getHours()) * 4) + 3;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    info("Index is " + String.valueOf(index));
    return index;
  }

  /**
   * Check busy time of user
   *
   * @param user String
   * @param from (ex: HH:mm)
   * @param to   (ex: HH:mm)
   */
  public void checkBusyTimeOfUser(String user, String from, String to) {
    info("Check busy time of an user");
    int fromIndex = convertFromTimeToIndex(from);
    // System.out.println("fromIndex: " + fromIndex);
    int toIndex = convertFromTimeToIndex(to);
    // System.out.println("toIndex: " + toIndex);
    info("From index is " + String.valueOf(fromIndex));
    info("To index is " + String.valueOf(toIndex));
    for (int i = fromIndex; i <= toIndex - 1; i++) {
      info("index:" + i);
      $(byXpath(ELEMENT_SCHEDULE_BUSY_TIME.replace("${user}", user).replace("${index}", String.valueOf(i))))

              .shouldHave(Condition.attribute("class", "busyDotTime"));

    }
  }

  /**
   * Check schedule time of user
   *
   * @param from (ex: MM/dd/yyyy HH:mm)
   * @param to   (ex: MM/dd/yyyy HH:mm)
   */
  public void checkScheduleTimeOfUser(String from, String to) {
    info("Check schedule time of an user");
    DateFormat formatterDateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    DateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
    try {
      Date dateFrom = formatterDateTime.parse(from);
      Date dateTo = formatterDateTime.parse(to);
      Date date = formatterDate.parse(from);

      String dateS = formatterDate.format(date);
      @SuppressWarnings("deprecation")
      String timeFrom = String.valueOf(dateFrom.getHours()) + ":" + String.valueOf(dateFrom.getMinutes());
      @SuppressWarnings("deprecation")
      String timeTo = String.valueOf(dateTo.getHours()) + ":" + String.valueOf(dateTo.getMinutes());

      int fromIndex = convertFromTimeToIndex(timeFrom);
      int toIndex = convertFromTimeToIndex(timeTo);
      for (int i = fromIndex; i <= toIndex; i++) {
        assert $(byXpath(ELEMENT_SCHEDULE_TIME.replace("${index}", String.valueOf(i))))
                .getAttribute("class")
                .contains("userSelection") : "Wrong schedule time";
      }
      $(byXpath(ELEMENT_SCHEDULE_SELECTED_DATE.replace("$date", dateS))).waitUntil(Condition.visible, Configuration.timeout);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * change time event from schedule tab
   *
   * @param option   String
   * @param fromTime String
   * @param toTime   String
   */
  public void changeTimeEventFromScheduleTab(PlatformBase.selectArrowOption option, String fromTime, String toTime) {
    info("Change time event in schedule tab");
    int fromIndex = convertFromTimeToIndex(fromTime);
    int toIndex = convertFromTimeToIndex(toTime);
    info("From is " + fromIndex);
    info("To is " + toIndex);
    switch (option) {
      case NEXT:
        $(ELEMENT_SCHEDULE_NEXT_DAY).click();
        break;
      case PREVIOUS:
        evt.click(ELEMENT_SCHEDULE_PREVIOUS_DAY);
        break;
      case NOW:
        break;
      default:
        break;
    }
    $(byXpath(ELEMENT_SCHEDULE_DRAG.replace("${index}", String.valueOf(fromIndex)))).dragAndDropTo($(byXpath(ELEMENT_SCHEDULE_DRAG.replace("${index}", String.valueOf(toIndex)))));

  }

  /**
   * Select privacy
   *
   * @param isPublic true: check public checkbox false: check private checkbox
   */
  public void selectPrivacyParticipant(boolean isPublic) {
    if (isPublic) {
      info("Select public privacy");
      evt.check(ELEMENT_PRIVACY_PUBLIC_CHECKBOX, 2);
    } else {
      info("Select private privacy");
      evt.check(ELEMENT_PRIVACY_PRIVATE_CHECKBOX, 2);
    }
  }

  /**
   * selectAvailable on participant tab
   *
   * @param option enum
   */
  public void selectAvailable(selectAvailableOption option) {
    switch (option) {
      case AVAILABLE:
        info("Select AVAILABLE");
        evt.check(ELEMENT_AVAILABLE_CHECKBOX, 2);
        break;
      case BUSY:
        info("Select BUSY");
        evt.check(ELEMENT_BUSY_CHECKBOX, 2);
        break;
      case OUTSIDE:
        info("Select OUTSIDE");
        evt.check(ELEMENT_OUTSIDE_CHECKBOX, 2);
        break;
      default:
        info("Select AVAILABLE");
        evt.check(ELEMENT_AVAILABLE_CHECKBOX, 2);
        break;
    }
  }

  /**
   * selectSendInvitation
   *
   * @param option enum
   */
  public void selectSendInvitation(PlatformBase.selectInvitationOption option) {
    switch (option) {
      case ALWAYS:
        info("Select ALWAYS");
        evt.check(ELEMENT_SEND_INVITATION_ALWAYS_CHECKBOX, 2);
        break;
      case NEVER:
        info("Select NEVER");
        evt.check(ELEMENT_SEND_INVITATION_NEVER_CHECKBOX, 2);
        break;
      case ASK:
        info("Select ASK");
        evt.check(ELEMENT_SEND_INVITATION_ASK_CHECKBOX, 2);
        break;
      default:
        info("Select NEVER");
        evt.check(ELEMENT_SEND_INVITATION_NEVER_CHECKBOX, 2);
        break;
    }

  }

  /**
   * select user participant
   *
   * @param users   String
   */
  public void selectUserParticipants(String users) {
    info("Select User Participant");
      String[] temp = users.split("/");
      for (int i = 0; i < temp.length; i++) {
          ELEMENT_EVENT_ADD_PARTICIPANT.waitUntil(Condition.visible, Configuration.timeout).setValue(temp[i]).waitUntil(Condition.visible, Configuration.timeout).click();
          sleep(3000);
          ELEMENT_EVENT_ADD_PARTICIPANT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(Keys.ENTER);
      }
  }

  /**
   * Save add EVENT
   */
  public void saveQuickAddEvent() {
    info("Save quick add event");
    ELEMENT_BUTTON_EVENT_SAVE.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_EVENT_SAVE.waitUntil(Condition.disappears, Configuration.timeout);
  }
  /**
   * Select Previous and Next day
   */
  public void selectPreviousAndNextDayInTheEventToAdd(String previousDay, String nextDay){
    info("Select Previous and Next day");
    ELEMENT_EVENT_SELECT_FROM.waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    if (previousDay!= null) {
      final SelenideElement ELEMENT_EVENT_PREVIOUS_DAY= $(By.xpath("(//td/a[contains(text(),'${CurrentDay}')]/../preceding-sibling::td/a[@href='#SelectDate'])[last()]".replace("${CurrentDay}",ELEMENT_EVENT_CURRENT_DAY.getText())));
      ELEMENT_EVENT_PREVIOUS_DAY.waitUntil(Condition.visible,Configuration.timeout).click();
      ELEMENT_EVENT_SELECT_TO.waitUntil(Condition.visible,Configuration.timeout).click();
      ELEMENT_EVENT_PREVIOUS_DAY.waitUntil(Condition.visible,Configuration.timeout).click();
    }
    if (nextDay!= null) {
      ELEMENT_EVENT_NEXT_DAY.waitUntil(Condition.visible,Configuration.timeout).click();
      ELEMENT_EVENT_SELECT_TO.waitUntil(Condition.visible,Configuration.timeout).click();
      ELEMENT_EVENT_NEXT_DAY.waitUntil(Condition.visible,Configuration.timeout).click();
    }
  }
  /**
   * Save a EVENT with more details
   */
  public void saveAddEventDetails() {
    info("Save add event details");
    if ($(ELEMENT_ADD_EDIT_EVENT_NAME).is(Condition.visible))
      $(ELEMENT_ADD_EDIT_EVENT_NAME).click();
    ELEMENT_EVENT_SAVE_BUTTON.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
  }

  /**
   *
   */

  /**
   * Click on more details
   */
  public void moreDetailsEvent() {
    info("Go to More Details");

    $(ELEMENT_BUTTON_EVENT_MORE_DETAILS).waitUntil(Condition.visible, Configuration.timeout);
    // click(ELEMENT_BUTTON_EVENT_MORE_DETAILS);
    $(ELEMENT_BUTTON_EVENT_MORE_DETAILS).click();
    $(ELEMENT_BUTTON_EVENT_MORE_DETAILS).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * Click on cancel button
   */
  public void cancelQuickAddEditEvent() {
    ELEMENT_EVENT_CANCEL_BUTTON.click();
    ELEMENT_EVENT_CANCEL_BUTTON.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  public void cancelAddEditDetailEvent() {
    evt.click(ELEMENT_BUTTON_EVENT_CANCEL_DETAILS);
    evt.waitForElementNotPresent(ELEMENT_BUTTON_EVENT_CANCEL_DETAILS);
  }

  /**
   * Remove attachment
   *
   * @param file name of file
   */
  public void removeAttachment(String file) {
    $(byXpath("//div[@title='${file}']/following::div[@class='removeFile']/a".replace("${file}",file))).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
  }

  /**
   * input recurring info event
   *
   * @param repeatType     repeat type: Daily, Weekly, Monthly, Yearly;
   * @param repeatInterval String
   * @param repeatOn       String
   * @param endRepeat      String
   * @param option         occurrennumber if endRepeat.equals(repeatEndType.After) day
   *                       format if endRepeat.equals(repeatEndType.ByThisDate) -- format:
   *                       mm/dd/yyyy
   */
  public void inputRecurringInfoEvent(repeatType repeatType,
                                      String repeatInterval,
                                      repeatOn[] repeatOn,
                                      repeatEndType endRepeat,
                                      String... option) {
    info("Add recurring information");
    String occurence = option.length > 0 ? option[0] : "";
    switch (repeatType) {
      case Daily:
        info("Select Daily option");
        $(byXpath("//*[@id='repeatType' and @class='selectbox']")).waitUntil(Condition.visible,Configuration.collectionsTimeout).selectOption("Daily");
        break;
      case Weekly:
        info("Select Weekly option");
        $(byXpath("//*[@id='repeatType' and @class='selectbox']")).waitUntil(Condition.visible,Configuration.collectionsTimeout).selectOption("Weekly");
        break;
      case Monthly:
        info("Select Monthly option");
        $(byXpath("//*[@id='repeatType' and @class='selectbox']")).waitUntil(Condition.visible,Configuration.collectionsTimeout).selectOption("Monthly");
        break;
      case Yearly:
        info("Select Yearly option");
        $(byXpath("//*[@id='repeatType' and @class='selectbox']")).waitUntil(Condition.visible,Configuration.collectionsTimeout).selectOption("Yearly");
        break;
      default:
        info("No option in the list.Please select correct option.");
        break;
    }
    if (endRepeat != null) {
      switch (endRepeat) {
        case After:
          info("Check After option");
          evt.check(byId("endAfter"), 2);
          if (occurence != "")
            break;
        case ByThisDate:
          info("Check By this date option");
          evt.check(ELEMENT_BY_THIS_DATE_END_RECURRING_EVENT, 2);
          if (occurence != "")
            evt.type(ELEMENT_DATE_TIME_PICKER, option[0], true);
          break;
        case Never:
          info("Check never option");
          evt.check(ELEMENT_NEVER_END_RECURRING_EVENT, 2);
          break;
      }
    }
  }

  /**
   * Add more Repeat By option
   *
   * @param isMonthByMonth boolean
   */
  public void selectRepeatByOption(boolean isMonthByMonth) {
    if (isMonthByMonth)
      evt.check(ELEMENT_REPEAT_BY_DAY_OF_MONTH, 2);
    else
      evt.check(ELEMENT_REPEAT_BY_DAY_OF_WEEK, 2);

  }

  /**
   * Delete an event
   *
   * @param name          name of event or task
   * @param view          view of calendar: day, week, list...
   * @param optionDay     ONEDAY or ALLDAY
   * @param optDeleteType ONLY_EVENT, FOLLOW_EVENT or ALL_EVENT
   * @param date          date of event: format (MMM dd yyyy)
   * @param opParams      isVerify, if not be set, the event/task will be automatically
   *                      set as verify delete confirm message = true: verify delete confirm
   *                      message = false: not verify delete confirm message
   */
  public void deleteRecurringEvent(String name,
                                   CalendarHomePage.selectViewOption view,
                                   CalendarHomePage.selectDayOption optionDay,
                                   recurringType optDeleteType,
                                   String date,
                                   Object... opParams) {
    boolean isVerify = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    info("Delete event/tak: " + name);
    cHome.goToRightMenuTaskEventFromAnyView(name, view, optionDay, date);
    $(ELEMENT_CONTEXT_MENU_DELETE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_DELETE_RECURRING_EVENT_FORM).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    if (isVerify) {
      $(ELEMENT_CONFIRM_DELETE_RECURRING_EVENT).waitUntil(Condition.visible, Configuration.timeout);
      $(ELEMENT_DELETE_ONE_EVENT).parent().waitUntil(Condition.visible, Configuration.timeout);
      info(evt.waitForAndGetElement(ELEMENT_CONFIRM_DELETE_RECURRING_EVENT).getText());
      assert evt.waitForAndGetElement(ELEMENT_CONFIRM_DELETE_RECURRING_EVENT)
              .getText()
              .contains(ELEMENT_CONFIRM_DELETE_MESSAGE);
      assert evt.waitForAndGetElement(ELEMENT_DELETE_ONE_EVENT, testBase.getDefaultTimeout(), 1, 2).isSelected();
    }
    switch (optDeleteType) {
      case ONLY_EVENT:
        info("Delete only event recurring");
        if (testBase.getBrowser().contains("iexplorer"))
          evt.clickByJavascript(ELEMENT_DELETE_ONE_EVENT, 2);
        else
          evt.check(ELEMENT_DELETE_ONE_EVENT, 2);
        break;
      case FOLLOW_EVENT:
        info("Delete following event recurring");
        if (testBase.getBrowser().contains("iexplorer"))
          evt.clickByJavascript(ELEMENT_DELETE_FOLLOWING_EVENT, 2);
        else
          evt.check(ELEMENT_DELETE_FOLLOWING_EVENT, 2);
        break;
      case ALL_EVENT:
        info("Delete all event recurring");
        if (testBase.getBrowser().contains("iexplorer"))
          evt.clickByJavascript(ELEMENT_DELETE_ALL_EVENT, 2);
        else
          evt.check(ELEMENT_DELETE_ALL_EVENT, 2);
        break;
      default:
        info("Delete only event recurring");
        if (testBase.getBrowser().contains("iexplorer"))
          evt.clickByJavascript(ELEMENT_DELETE_ONE_EVENT, 2);
        else
          evt.check(ELEMENT_DELETE_ONE_EVENT, 2);
        break;
    }
    evt.waitForAndGetElement(ELEMENT_CONFIRM_DELETE_BUTTON, testBase.getDefaultTimeout(), 1);
    $(ELEMENT_CONFIRM_DELETE_BUTTON).click();
    $(ELEMENT_DELETE_RECURRING_EVENT_FORM).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * edit recurring event
   *
   * @param optEditType type of edit recurring
   * @param opParams    isVerify, if not be set, the event/task will be automatically
   *                    set as verify edit confirm message = true: verify edit confirm
   *                    message = false: not verify edit confirm message
   */
  public void editRecurringEvent(recurringType optEditType, Object... opParams) {
    boolean isVerify = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    info("Edit recurring event");
    evt.waitForAndGetElement(ELEMENT_CONFIRM_EDIT_RECURRING_FORM);
    if (isVerify) {
      evt.waitForAndGetElement(ELEMENT_CONFIRM_EDIT_RECURRING_EVENT);
      evt.waitForAndGetElement(ELEMENT_EDIT_RECURRING_EVENT_SAVE, testBase.getDefaultTimeout(), 1, 2);
      info(evt.waitForAndGetElement(ELEMENT_CONFIRM_EDIT_RECURRING_EVENT).getText());
      assert evt.waitForAndGetElement(ELEMENT_CONFIRM_EDIT_RECURRING_EVENT)
              .getText()
              .contains(ELEMENT_CONFIRM_EDIT_MESSAGE);
    }
    switch (optEditType) {
      case ONLY_EVENT:
        info("Edit only event recurring");
        evt.check(ELEMENT_EDIT_ONE_EVENT, 2);
        break;
      case FOLLOW_EVENT:
        info("Edit following event recurring");
        evt.check(ELEMENT_EDIT_FOLLOWING_EVENT, 2);
        break;
      case ALL_EVENT:
        info("Edit all event recurring");
        evt.check(ELEMENT_EDIT_ALL_EVENT, 2);
        break;
    }
    $(ELEMENT_CONFIRM_EDIT_BUTTON).click();
    $(ELEMENT_CONFIRM_EDIT_RECURRING_FORM).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Delete recurring Confirm selection
   *
   * @param optEditType String
   */
  public void deleteRecurringConfirm(recurringType optEditType) {
    evt.waitForAndGetElement(ELEMENT_CONFIRM_EDIT_RECURRING_FORM);
    switch (optEditType) {
      case ONLY_EVENT:
        info("Edit only event recurring");
        evt.check(ELEMENT_DELETE_ONE_EVENT, 2);
        break;
      case FOLLOW_EVENT:
        info("Edit following event recurring");
        evt.check(ELEMENT_DELETE_FOLLOWING_EVENT, 2);
        break;
      case ALL_EVENT:
        info("Edit all event recurring");
        evt.click(ELEMENT_DELETE_ALL_EVENT, 2);
        break;
    }

    evt.click(ELEMENT_CONFIRM_DELETE_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CONFIRM_DELETE_BUTTON);
  }

  /**
   * Open Recurring form
   */
  public void openRecurringForm() {
    if (evt.waitForAndGetElement(ELEMENT_RECURRING_REPEAT_BTN, 3000, 0) == null) {
      info("Click on Repeat checkbox");
      evt.check(ELEMENT_IS_REPEAT_CHECKBOX, 2);
    } else {
      info("Click on Edit button");
      evt.click(ELEMENT_RECURRING_REPEAT_BTN);
    }
    evt.waitForAndGetElement(ELEMENT_RECURRING_FORM, 2000, 1);
  }

  /**
   * Save recurring form
   */
  public void saveRecurringForm() {
    info("Click on Save button");
    evt.click(ELEMENT_RECURRING_SAVE_BTN);
    evt.waitForElementNotPresent(ELEMENT_RECURRING_SAVE_BTN);
  }

  /**
   * Cancel recurring form
   */
  public void cancelRecurringForm() {
    info("Click on Cancel Button");
    evt.click(ELEMENT_RECURRING_CANCEL_BTN);
    evt.waitForElementNotPresent(ELEMENT_RECURRING_CANCEL_BTN);
  }

  /**
   * Cancel confirming editing recurring form
   */
  public void cancelEditRecurringForm() {
    info("Click on Cancel button");
    evt.click(ELEMENT_CONFIRM_EDIT_RECURRING_EVENT_CANCEL_BTN);
    evt.waitForElementNotPresent(ELEMENT_CONFIRM_EDIT_RECURRING_EVENT_CANCEL_BTN);
  }

  /**
   * Save Edit Recurring Event Confirm popup
   */
  public void saveEditRecurringEventConfirmPopup() {
    info("Click on Save button");
    evt.click(ELEMENT_EDIT_RECURRING_EVENT_FORM_SAVE_BTN);
    evt.waitForElementNotPresent(ELEMENT_EDIT_RECURRING_EVENT_FORM_SAVE_BTN);
  }

  /**
   * Cancel confirming deleting recurring form
   */
  public void cancelDeletingRecurringForm() {
    info("Click on Cancel Button");
    evt.click(ELEMENT_CONFIRM_CANCEL_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CONFIRM_CANCEL_BUTTON);
  }

  /**
   * Select an option as 5,10,15,20,...60 for reminder Email box
   *
   * @param option String
   */
  public void selectReminderEmailBox(String option) {
    info("Select an option");
    evt.select(ELEMENT_REMINDER_DROP_BOX, option);

  }

  /**
   * Open schedule tab
   */
  public void goToScheduleTab() {
    info("Click on Schedule tab");
    evt.click(ELEMENT_EVENT_SCHEDULE_TAB);

  }

  /**
   * Open Detail tab
   */
  public void goToDetailsTab() {
    info("Click on Detail tab");
    evt.click(ELEMENT_EVENT_DETAILS_TAB);

  }

  /**
   * Open participants tab
   */
  public void goToParticipantsTab() {
    info("Click on Participants tab");
    evt.click(ELEMENT_EVENT_PARTICIPANTS_TAB);

  }

  /**
   * Open reminders tab
   */
  public void goToRemindersTab() {
    info("Click on Reminders tab");
    evt.click(ELEMENT_EVENT_REMINDER_TAB);

  }

  /**
   * Open invitation participant popup
   */
  public void goToInvitationParticipantForm() {
    info("Click on invitation Participants button");
    evt.click(ELEMENT_INVITATION_PARTICITPANT_USER);

  }

  /**
   * Save all changes of invitation participant form
   */
  public void saveInvitationParticipantForm() {
    info("Click on save button");
    evt.click(ELEMETN_INVITATION_SAVE_BUTTON);

  }

  /**
   * Remove an user in participants tab of Add/Edit Event/Task form
   *
   * @param fullName
   */
  public void removeUser(String fullName) {
    info("Click on Delete button");
    evt.click(ELEMENT_INVITATION_PARTICIPANTS_REMOVE_BTN.replace("$fullName", fullName));
    evt.click(ELEMENT_YES_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_INVITATION_PARTICIPANTS_REMOVE_BTN.replace("$fullName", fullName));
  }

  /**
   * Check user selector of event
   *
   * @param user
   * @param isPresent
   */
  public void checkUserSelectorOfEvent(String user, boolean isPresent) {
    goToAddEventFromActionBar();
    moreDetailsEvent();
    info("check reminder");
    goToRemindersTab();
    evt.click(ELEMENT_REMINDER_ADDMORE_ICON);
    pPer.checkUserSelector(user, isPresent);
    evt.click(ELEMENT_USER_CLOSE_BUTTON);
    info("check participant");
    goToParticipantsTab();
    evt.click(ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_PARTICIPANT_TAB);

    evt.click(ELEMENT_PICK_USER_PARTICIPANTS_TAB);
    pPer.checkUserSelector(user, isPresent);
    evt.click(ELEMENT_USER_CLOSE_BUTTON);
    info("check schedule");
    goToScheduleTab();
    evt.click(ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_SCHEDULE_TAB);
    pPer.checkUserSelector(user, isPresent);
    evt.click(ELEMENT_USER_CLOSE_BUTTON);
  }

  /**
   * Check display of event
   *
   * @param event     String
   * @param isPresent boolean
   */
  public void checkDisplayOfEvent(String event, boolean isPresent) {
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}", event));
    else
      evt.waitForElementNotPresent(ELEMENT_EVENT_TASK_TITLE.replace("${name}", event));
  }

  /**
   * function: check content of mail then delete mail in email server
   *
   * @param titleEvent String
   * @param opParams   object
   */
  public void checkEmailNotificationReminderEvent(String titleEvent, Object... opParams) {
    info("Check and delete mail");
    Boolean checkOrNo = (Boolean) (opParams.length > 0 ? opParams[0] : true);
    String parentWindow = testBase.getExoWebDriver().getWebDriver().getWindowHandle();
    info("parentWindow:" + parentWindow);
    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
    }
    if (opParams.length > 0) {
      if (checkOrNo == true)
        evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_REMINDER_EVENT.replace("$task", titleEvent), 30000, 1);
      else
        evt.waitForElementNotPresent(ELEMENT_GMAIL_CONTENT_REMINDER_EVENT.replace("$task", titleEvent), 30000, 1);
    }
  }

  /**
   * Define a type of priority none normal high low
   */
  public enum priorityType {
    None, Normal, High, Low;
  }

  /**
   * Available option
   */
  public enum selectAvailableOption {
    AVAILABLE, BUSY, OUTSIDE
  }

  /**
   * Define a type of repeat Daily Weekly Monthly Yearly
   */
  public enum repeatType {
    Daily, Weekly, Monthly, Yearly;
  }

  /**
   * Define a type of repeat on MO TU WE TH FR SA SU
   */
  public enum repeatOn {
    MO, TU, WE, TH, FR, SA, SU;
  }

  /**
   * Define a type of repeat Never After ByThisDate
   */
  public enum repeatEndType {
    Never, After, ByThisDate;
  }

  /**
   * Define a time of reminder by email minutes, hours, days, weeks
   */
  public enum timeEmailReminder {
    minutes, hours, days, weeks;
  }

  /**
   * Define a type of delete recurring Only this event Following events All events
   */
  public enum recurringType {
    ONLY_EVENT, FOLLOW_EVENT, ALL_EVENT;
  }

  /**
   * Check Event PopUp Fields
   */
  public void checkEventPopUp(String date, String userName, String user) {
    switchTo().activeElement();
    $(byText("Add Event")).should(Condition.exist);
    assertEquals("Event title", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[1]/input")).getAttribute("placeholder"));
    $(byXpath("//span/select[@class='selectbox category']")).should(Condition.appears);
    assertEquals("Events", ELEMENT_EVENT_CATEGORY.getText());
    assertEquals("All day", $(byXpath("(//input[@id=\"allday\"]/following::span)[1]")).getText());
    assertEquals("Location", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[1]")).getText());
    assertEquals("Enter a location for this event", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input")).getAttribute("placeholder"));
    assertEquals("Participants", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[5]/div[1]")).getText());
    info("Date From is" + date);
    if (date.charAt(0) == '0') {
      assertEquals(date, "0" + $(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE).getValue());
    }
    else{
      assertEquals(date, $(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE).getValue());
    }
    info("Date To is" + date);
    if (date.charAt(0) == '0') {
      assertEquals(date, "0" + $(ELEMENT_QUICK_INPUT_EVENT_TO_DATE).getValue());
    }
    else{
      assertEquals(date, $(ELEMENT_QUICK_INPUT_EVENT_TO_DATE).getValue());
    }
    $(ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX).exists();
    $(ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX).exists();
    assertEquals(userName, $(byXpath("//*[@data-value='${user}']".replace("${user}",user))).getText().split("\n")[0]);
    $(byXpath("//i[@class=\"uiIcon attachFileIcon\"]")).exists();
    $(byXpath("//textarea[@placeholder='Add a description']")).exists();
    assertEquals("Clear", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]")).getText());
    assertEquals("Save", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]")).getText());
    assertEquals("Cancel", $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]")).getText());
  }

  public void checkReminderLabel() {

  }

}
