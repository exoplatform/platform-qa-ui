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

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public final class QuickSearchResultLocator {

  public static final By              ELEMENT_QUICKSEARCHRESULT_SORTBY              = By.xpath("//*[@class='btn dropdown-toggle']");

  public static final By              ELEMENT_QUICKSEARCHRESULT_SORTBY_DATE         = By.xpath("//*[@sort='date']");

  public static final SelenideElement ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH      = $(byClassName("seeAllmsg"));

  // filter box
  public static final By              ELEMENT_SEARCHRESULT_ALLTYPECHECK             = By.xpath("//*[@id=\"facetsFilter\"]/div/div/div[2]/h6/span");

  public static final By              ELEMENT_SEARCHRESULT_FILESTYPECHECK           = By.xpath("//*[@id=\"lstContentTypes\"]/li[1]/span");

  public static final By              ELEMENT_SEARCHRESULT_DOCTYPECHECK             = By.xpath("//*[@value='document' and @name='contentType']");

  public static final By              ELEMENT_SEARCHRESULT_DISCTYPECHECK            = By.xpath("//*[@id=\"lstContentTypes\"]/li[5]/span");

  public static final By              ELEMENT_SEARCHRESULT_WIKITYPECHECK            = By.xpath("//*[@id=\"lstContentTypes\"]/li[3]/span");

  public static final By              ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX           = By.xpath(".//*[@id='ToolBarSearch']//*[@name='adminkeyword']");

  public static final SelenideElement ELEMENT_RESULT_SEARCH                         = $(byId("result"));

  public static final SelenideElement ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH = $(byClassName("uiQuickSearchResult"));

  public static final SelenideElement ELEMENT_BUTTON_DISABLE_ENABLE_WIKI_SEARCH     = $(byId("wiki"));
}
