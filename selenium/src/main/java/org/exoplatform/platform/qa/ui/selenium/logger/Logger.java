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
package org.exoplatform.platform.qa.ui.selenium.logger;

import org.slf4j.LoggerFactory;

/**
 *
 */
public class Logger {

  public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

  private Logger() {

  }

  public static String log(String message) {
    Throwable t = new Throwable();
    String logMessage = message;
    StackTraceElement[] elements = t.getStackTrace();
    String Filename = elements[2].getFileName();
    String sClassName = Filename.substring(0, Filename.length() - 5);
    String sMethodName = elements[2].getMethodName();
    logMessage = String.format("[%-10s][%s] %s", sClassName, sMethodName, message);
    return logMessage;
  }

  public static void debug(String message) {
    logger.debug(log(message));
  }

  public static void info(String message) {
    logger.info(log(message));
  }

  public static void error(String message) {
    logger.error(log(message));
  }

  public static void error(String message, Exception ex) {
    logger.error(log(message), ex);
  }
}
