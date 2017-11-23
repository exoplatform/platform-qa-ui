/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.testbase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;


public class DateTestBase {
  private final TestBase testBase;

  public DateTestBase(TestBase testBase) {
    this.testBase = testBase;
  }

  /**
   * Get minute in format "HH" from current date
   *
   * @return hours
   */
  public int getHours() {
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int minute = cal.get(Calendar.HOUR);
    return (minute);
  }

  /**
   * Get date by text format ex. Saturday, Febuary 16, 2015
   *
   * @param format String
   * @return date
   */
  public String getDateByTextFormat(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar cal = Calendar.getInstance();
    String date = dateFormat.format(cal.getTime());
    Logger.info(date);
    return date;
  }

  /**
   * Get first day of week
   *
   * @param format String
   * @return firstDayOfWeek
   */
  public String getFirstDayOfWeek(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK, 2);
    String firstDayOfWeek = dateFormat.format(calendar.getTime());
    Logger.info("firstDayOfWeek:" + firstDayOfWeek);
    return firstDayOfWeek;
  }

  /**
   * Get last day of week
   *
   * @param format String
   * @return firstDayOfWeek
   */
  public String getLastDayOfWeek(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar currentDate = Calendar.getInstance();
    int firstDayOfWeek = currentDate.getFirstDayOfWeek();

    Calendar startDate = Calendar.getInstance();
    startDate.setTime(currentDate.getTime());
    int days = (startDate.get(Calendar.DAY_OF_WEEK) + 7 - firstDayOfWeek) % 7;
    startDate.add(Calendar.DATE, -days);

    Calendar endDate = Calendar.getInstance();
    endDate.setTime(startDate.getTime());
    endDate.add(Calendar.DATE, 5);
    String lastDayOfWeek = dateFormat.format(endDate.getTime());
    Logger.info("lastDayOfWeek:" + lastDayOfWeek);
    return lastDayOfWeek;
  }

  /**
   * function get current Date of system follow a format
   *
   * @param format String
   * @return current Date of system
   */
  public String getCurrentDate(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Date date = new Date();
    return (dateFormat.format(date));
  }

  /**
   * Get current date with time zone
   *
   * @param format String
   * @param local String
   * @return current Date with correct time zone
   */
  public String getCurrentDate(String format, String local) {
    DateFormat df = new SimpleDateFormat(format);
    Date date = new Date();
    df.setTimeZone(TimeZone.getTimeZone(local));
    System.out.println("Date and time in" + local + ": " + df.format(date));
    return df.format(date);
  }

  /**
   * Add 1 minute to current date time
   *
   * @param min int
   * @param format String
   * @return string minute
   */
  public String addMinuteToCurrentDateTime(int min, String... format) {
    DateFormat dateFormat = format.length > 0 ? new SimpleDateFormat(format[0]) : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MINUTE, min);
    return (dateFormat.format(cal.getTime()));
  }

  /**
   * Get date in format "dd"
   *
   * @param gap distance from current date
   * @param format String
   * @return date in format "dd"
   */
  public String getDate(int gap, String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, gap);
    Logger.info(dateFormat.format(cal.getTime()));
    return (dateFormat.format(cal.getTime()));
  }

  /**
   * Get date from firstDayOf Week
   *
   * @param gap int
   * @param format String
   * @return date in format
   */
  public String getDateFromFirstDayOfWeek(int gap, String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK, 2);
    calendar.add(Calendar.DAY_OF_MONTH, gap);
    String getDateFromFirstDayOfWeek = dateFormat.format(calendar.getTime());
    Logger.info("getDateFromFirstDayOfWeek:" + getDateFromFirstDayOfWeek);
    return getDateFromFirstDayOfWeek;
  }

  /**
   * Get day of week
   *
   * @param gap distance from current date
   * @return day of week (monday, tuesday,..., sunday)
   */
  public int getDayOfWeek(int gap) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, gap);
    return cal.get(Calendar.DAY_OF_WEEK);
  }

  /**
   * Get day of the next month
   *@param dayNum int
   * @param weekNum int
   * @param format String
   * @return date
   */
  public String getDayOfNextMonth(String format, int dayNum, int weekNum) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar calendar = Calendar.getInstance();

    Calendar nextMonth = Calendar.getInstance();
    nextMonth.setTime(calendar.getTime());
    nextMonth.add(Calendar.DAY_OF_WEEK_IN_MONTH, weekNum);
    nextMonth.add(Calendar.DAY_OF_MONTH, dayNum);
    String dayOfNextMonth = dateFormat.format(nextMonth.getTime());
    System.out.println("dayOfNextMonth:" + dayOfNextMonth);
    return dayOfNextMonth;

  }

  /**
   * Get the day of next year
   *
   * @param format String
   * @param year int
   * @return dayOfYear
   */
  public String getDayOfNextYear(String format, int year) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar calendar = Calendar.getInstance();

    Calendar nextYear = Calendar.getInstance();
    nextYear.setTime(calendar.getTime());
    nextYear.add(Calendar.YEAR, year);
    nextYear.add(Calendar.MONTH, 12);
    String dayOfYear = dateFormat.format(nextYear.getTime());
    System.out.println("dayOfYear:" + dayOfYear);
    return dayOfYear;
  }

  /**
   * Get the day of next week
   *
   * @param format String
   * @return
   */
  public String getDayOfNextWeek(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar calendar = Calendar.getInstance();
    String dayOfNextMonth1 = dateFormat.format(calendar.getTime());
    System.out.println("dayOfNextMonth1:" + dayOfNextMonth1);

    Calendar nextWeek = Calendar.getInstance();
    nextWeek.setTime(calendar.getTime());
    nextWeek.add(Calendar.DATE, 7);
    String dayOfNextWeek = dateFormat.format(nextWeek.getTime());
    return dayOfNextWeek;

  }

  /**
   * Get the number of current week
   *
   * @return weekNum
   */
  public int getWeekNumber() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

  public int getDayNumber() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return calendar.get(Calendar.DATE);
  }

  /**
   * Get current month/day/year
   *
   * @param format as MMM for month, dd for day, or yyyy for year
   * @return dateFormat.format(now.getTime())
   */
  public String getCurrentMonthDayYear(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar now = Calendar.getInstance();
    return dateFormat.format(now.getTime());
  }

  /**
   * Get minute in format "mm" from current date
   *
   * @return minute
   */
  public int getMinute() {
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int minute = cal.get(Calendar.MINUTE);
    return (minute);
  }
}
