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
}
