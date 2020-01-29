/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - API.
 *
 * eXo PLF:: QA - UI - API is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - API software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - API; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.core.config.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by mgreau on 13/02/2017.
 */
public interface Driver {

  WebDriver getWebDriver();

  String getBrowser();

  String getBaseUrl();

  default boolean isChromeDriver() {
    return getWebDriver() instanceof ChromeDriver;
  }

  default boolean isIEDriver() {
    return getWebDriver() instanceof InternetExplorerDriver;
  }

  // @TODO Decide how to manage the following behavior:

  WebDriver getDriverAutoOpenWindow();

  WebDriver getDriverSetLanguage(String locale);

  void setPreferenceRunTime();

}
