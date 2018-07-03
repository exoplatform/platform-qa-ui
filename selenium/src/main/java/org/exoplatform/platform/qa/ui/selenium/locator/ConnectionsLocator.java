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
package org.exoplatform.platform.qa.ui.selenium.locator;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class ConnectionsLocator {
  public static final By              ELEMENT_CONNECTION_EVERYONE_TITLE       =
                                                                        By.xpath(".//*[@id='UIAllPeople']//*[contains(text(),'Everyone')]");

  public static final SelenideElement ELEMENT_CONNECTION_CONNECT_BTN          = $(byText("Connect"));

  public static final SelenideElement ELEMENT_CONNECTION_CANCEL_BTN           = $(byText("Cancel Request"));

  public static final SelenideElement ELEMENT_CONNECTION_REVOVE_BTN           = $(byText("Remove Connection"));

  public static final String          ELEMENT_CONNECTION_IGNORE_BTN           =
                                                                    " //a[contains(@href,'${user}')]/../..//*[text()='Ignore']";

  public static final SelenideElement ELEMENT_CONNECTION_CONFIRM_BTN          = $(byText("Confirm"));

  public static final String          ELEMENT_CONNECTION_USER_AVARTAR         = "//a[contains(@href,'${user}')]/img";

  public static final String          ELEMENT_CONNECTION_USER_NAME            =
                                                                   "//a[contains(@href,'${user}') and @data-key='title']";

  public static final By              ELEMENT_ALL_CONNECTIONS_TAB             =
                                                                  By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'all-people')]");

  public static final By              ELEMENT_MY_CONNECTIONS_TAB              =
                                                                 By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'network')]");

  public static final By              ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB =
                                                                              By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'receivedInvitations')]");

  public static final By              ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB =
                                                                              By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'pendingRequests')]");

  public static final By              ELEMENT_ALL_RESULTS                     = By.id("searchAll");

  // public static final By ELEMENT_NAME_OF_PEOPLE = By.id("name");
  public static final By              ELEMENT_POSITIONS_OF_PEOPLE             = By.id("position");

  public static final By              ELEMENT_SKILL_OF_PEOPLE                 = By.id("skills");

  public static final By              ELEMENT_SEARCH_BUTTON                   = By.id("SearchButton");

  public static final String          ELEMENT_USER_LINK                       =
                                                        "//*[@class='spaceTitle']//*[contains(@href,'${userName}')]";

  public static final String          ELEMENT_USER_AVATAR                     = ".//*[@alt='${fullname}']";

  public static final SelenideElement ELEMENT_NAME_OF_PEOPLE                  =
                                                             $(byXpath("//*[@id=\"uiTableProfileUserSearchInput\"]/div[1]/div/div/div[1]/input"));

  public static final SelenideElement ELEMENT_CONTENT_PEOPLE                  = $(byXpath("//*[@id=\"UIAllPeople\"]/div[2]/div"));

  public static final SelenideElement ELEMENT_POPOVER_USER=$(byId("tiptip_content"));
}
