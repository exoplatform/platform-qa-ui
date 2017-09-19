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

  public static final By              ELEMENT_QUICKSEARCHRESULT_NUMBEROFRESULT                 =
                                                                               By.xpath("//*[contains(text(), 'Results 1 to 5')]");

  public static final By              ELEMENT_QUICKSEARCHRESULT_SORTBY                         =
                                                                       By.xpath("//*[@class='btn dropdown-toggle']");

  public static final By              ELEMENT_QUICKSEARCHRESULT_SORTBY_DATE                    = By.xpath("//*[@sort='date']");

  public static final String          ELEMENT_QUICKSEARCHRESULT_ICONDOC                        =
                                                                        "//*[@class='uiIcon64x64TemplateFolderDefault uiIcon64x64Templateacme_product']/../..//*[text()='${title}']";

  public static final SelenideElement ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH                 = $(byClassName("seeAllmsg"));

  // filter box
  public static final By              ELEMENT_SEARCHRESULT_ALLTYPECHECK                        =
                                                                        By.xpath("//*[@id=\"facetsFilter\"]/div/div/div[2]/h6/span");

  public static final By              ELEMENT_SEARCHRESULT_FILESTYPECHECK                      =
                                                                          By.xpath("//*[@id=\"lstContentTypes\"]/li[1]/span");

  public static final By              ELEMENT_SEARCHRESULT_DOCTYPECHECK                        =
                                                                        By.xpath("//*[@value='document' and @name='contentType']");

  public static final By              ELEMENT_SEARCHRESULT_DISCTYPECHECK                       =
                                                                         By.xpath("//*[@id=\"lstContentTypes\"]/li[5]/span");

  public static final By              ELEMENT_SEARCHRESULT_TASKTYPECHECK                       =
                                                                         By.xpath("//*[@value='task' and @name='contentType']");

  public static final By              ELEMENT_SEARCHRESULT_EVENTSTYPECHECK                     =
                                                                           By.xpath("//*[@value='event' and @name='contentType']");

  public static final By              ELEMENT_SEARCHRESULT_WIKITYPECHECK                       =
                                                                         By.xpath("//*[@id=\"lstContentTypes\"]/li[3]/span");

  public static final By              ELEMENT_SEARCHRESULT_SPACETYPECHECK                      =
                                                                          By.xpath("//*[@value='space' and @name='contentType']");

  public static final By              ELEMENT_SEARCHRESULT_PEOPLETYPECHECK                     =
                                                                           By.xpath("//*[@value='people' and @name='contentType']");

  public static final By              ELEMENT_SEARCHRESULT_ANSWERTYPECHECK                     =
                                                                           By.xpath("//*[@value='answer' and @name='contentType']");

  public static final By              ELEMENT_SEARCHRESULT_PAGETYPECHECK                       =
                                                                         By.xpath("//*[@value='page' and @name='contentType']");

  public static final By              ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX                      =
                                                                          By.xpath(".//*[@id='ToolBarSearch']//*[@name='adminkeyword']");

  public static final String          ELEMENT_QUICKSEARCH_LIST_CONTENT                         =
                                                                       ".//*[@class='uiQuickSearchResult']//a[contains(.,'${name}')]";

  /// Searched results page-->task,event
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_TASK_NAME_NOTE              =
                                                                                  ".//*[@id='result']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_TASK_DUEDATE                =
                                                                                ".//*[@id='result']//*[contains(text(),'${name}')]/../../..//*[contains(text(),'Due for')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_TASK_ICON                   =
                                                                             ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_EVENT_NAME_NOTE             =
                                                                                   ".//*[@id='result']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_EVENT_DUEDATE               =
                                                                                 ".//h6//*[contains(text(),'${name}')]/../..//*[contains(.,'${date}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_EVENT_ICON                  =
                                                                              ".//h6//*[contains(text(),'${name}')]/../../../../..//*[@class='avatar pull-left']";

  // Searched results page-->file
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_FILE_TITLE                  =
                                                                              ".//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_FILE_LOCATION               =
                                                                                 "//*[contains(text(), '${name}')]/../..//*[contains(text(),'${location}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_FILE_ICON                   =
                                                                             ".//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left ']";

  // Searched results page -->document
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DOC_ICON                    =
                                                                            ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DOC_TITLE                   =
                                                                             ".//h6//*[contains(text(),'${name}')]";

  // Searched results page -->discussion
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_ICON            =
                                                                                    ".//*[contains(text(),'${name}')]/../../..//*[@class='uiIconApp64x64Forum']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_POST_TITLE      =
                                                                                          "//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_EXCERPT         =
                                                                                       ".//*[contains(text(),'${name}')]/../../..//*[@class='excerpt']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_FORUM_NAME      =
                                                                                          ".//*[contains(text(),'${topic}')]/../../..//*[@class='detail'][contains(text(),'${forum}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_POST_DATE       =
                                                                                         ".//*[contains(text(),'${topic}')]/../../..//*[@class='detail'][contains(text(),'${date}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_RATING          =
                                                                                      ".//*[contains(text(),'${topic}')]/../../..//*[@class='avgRatingImages clearfix']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_NUMBER_REPLY    =
                                                                                            ".//*[contains(text(),'${topic}')]/../../..//*[@class='detail'][contains(text(),'${number}')]";

  // Searched results page --> wiki
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_WIKI_ICON                   =
                                                                             ".//h6//*[contains(text(),'${name}')]/../../../../..//*[@class='avatar pull-left']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_WIKI_TITLE                  =
                                                                              ".//h6//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_WIKI_LAST_MODIFICATION_DATE =
                                                                                               ".//h6//*[contains(text(),'${name}')]/../../../../..//*[contains(text(),'${date}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_WIKI_LOCATION               =
                                                                                 ".//h6//*[contains(text(),'${name}')]/../../../../..//*[contains(text(),'${location}')]";

  // Searched results page --> Spaces
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_SPACE_AVATAR                =
                                                                                ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left userThumbnail']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_SPACE_TITLE                 =
                                                                               ".//h6//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_SPACE_DESCRIPTION           =
                                                                                     ".//p[contains(text(),'${des}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_SPACE_MEMBER_COUNT          =
                                                                                      ".//*[contains(text(),'${name}')]/../..//*[contains(text(),'${number}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_SPACE_STATUS                =
                                                                                ".//*[contains(text(),'${name}')]/../..//*[contains(text(),'${status}')]";

  // Searched results page --> People
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_USER_AVATAR                 =
                                                                               ".//h6[contains(.,'${fullname}')]/../..//*[@class='avatar pull-left userThumbnail']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_USER_FULL_NAME              =
                                                                                  ".//h6[contains(.,'${fullname}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_USER_TITLE                  =
                                                                              ".//h6[contains(.,'${fullname}')]/../..//*[@class='excerpt']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_USER_EMAIL                  =
                                                                              ".//h6[contains(.,'${fullname}')]/../..//*[@class='detail'][contains(.,'${email}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_USER_GENDER                 =
                                                                               ".//h6[contains(.,'${fullname}')]/../..//*[@class='detail'][contains(text(),'${gender}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_USER_PHONE                  =
                                                                              ".//h6[contains(.,'${fullname}')]/../..//*[@class='detail'][contains(text(),'${phone}')]";

  // Page searched results-->pages
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_PAGE_ICON                   =
                                                                             ".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../../..//img";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_PAGE_TITLE                  =
                                                                              ".//*[@id='result']//h6//*[contains(text(),'${page}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_PAGE_EXCERPT                =
                                                                                ".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../..//*[@class='excerpt']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_BELONGS_TO        =
                                                                                        ".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../..//*[contains(text(),'${site}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_URL               =
                                                                                 ".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../..//*[contains(.,'${url}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_HREF              =
                                                                                  ".//*[@id='result']//h6//*[contains(text(),'${page}')]/..";

  // Searched results page -->Answers
  public static final String          ELEMENT_SEARCHRESULT_CONTENT_ANSWER_TITLE                =
                                                                                ".//h6//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_ANSWER_ICON                 =
                                                                               ".//h6//*[contains(text(),'${name}')]/../../../..//*[@class='avatar pull-left']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_ANSWER_EXCERPT              =
                                                                                  ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='excerpt']";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_ANSWER_NUMBER_REPLY         =
                                                                                       ".//h6//*[contains(text(),'${name}')]/../../..//*[contains(text(),'${number}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_ANSWER_NUMBER_COMMENTS      =
                                                                                          ".//h6//*[contains(text(),'${name}')]/../../..//*[contains(text(),'${comment}')]";

  public static final String          ELEMENT_SEARCHRESULT_CONTENT_ANSWER_RATING               =
                                                                                 ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avgRatingImages clearfix']";

  public static final SelenideElement ELEMENT_RESULT_SEARCH                                    = $(byId("result"));

  public static final SelenideElement ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH            =
                                                                                    $(byClassName("uiQuickSearchResult"));

  public static final SelenideElement ELEMENT_BUTTON_DISABLE_ENABLE_WIKI_SEARCH                = $(byId("wiki"));
}
