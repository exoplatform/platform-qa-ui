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
package org.exoplatform.platform.qa.ui.selenium.locator.chat;

import org.openqa.selenium.By;

public class ChatLocator {
  public static final String ELEMENT_CHAT_UISTATUSPROFILEPORTLET =
                                                                 ".//*[@id='UIStatusProfilePortlet']//*[@class='${icon}' and @data-original-title='${status}']";

  public static final String ELEMENT_CHAT_TOOLTIP                =
                                                  "//*[contains(@class,'tooltip-inner') and contains(text(),'${tooltip}')]";

  public static final String ELEMENT_CHAT_STATUS                 = "//a[@class='chat-status']/*[contains(text(),'${status}')]";

  public static final By     ELEMENT_CHAT_ICON                   = By.xpath(".//*[@id='chat-status']/a");
}
