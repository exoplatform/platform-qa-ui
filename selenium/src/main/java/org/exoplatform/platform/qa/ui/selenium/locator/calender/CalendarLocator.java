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
package org.exoplatform.platform.qa.ui.selenium.locator.calender;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class CalendarLocator {

  /**************************************************
   * CALENDAR HOME PAGE
   **********************************************************************/
  // GRID
  public static final String          ELEMENT_EVENT_TASK_TITLE                                         =
                                                               "//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_EVENT_TASK_TITLE1                                        =
                                                                "//*[@deschtml='${name}']";

  public static final String          ELEMENT_EVENT_TASK_COLOR                                         =
                                                               ".//*[contains(text(),'$name')]/..//*[contains(@class,'$color')]";

  public static final String          ELEMENT_EVENT_TASK_TITLE_WEEK_COUNT                              =
                                                                          "(.//*[contains(@class,'eventContainer')]//*[contains(text(),'${name}')])[$number]";

  public static final By              ELEMENT_ADD_EDIT_EVENT_POPUP                                     =
                                                                   By.xpath(".//*[@id='UICalendarPopupWindow']");

  public static final By              ELEMENT_CALENDAR_WORKING_PANEL                                   =
                                                                     By.id("UICalendarWorkingContainer");

  public static final String          ELEMENT_CELL_TO_WORKING_PANEL                                    =
                                                                    "//td[contains(@startfull,'$date $time:00')]";

  public static final String          ELEMENT_ANY_TARGET_DATE                                          =
                                                              "//*[contains(@startfull, '${targetDate}') or contains(@starttimefull, '${targetDate}')]";

  // Action bar-->left area (Add task/event)
  public static final By              ELEMENT_BUTTON_TASK                                              =
                                                          By.id("UIActionBarQuickAddTask");

  public static final By              ELEMENT_BUTTON_EVENT                                             =
                                                           By.id("UIActionBarQuickAddEvent");

  // Action bar--> right area-->Today link
  public static final By              ELEMENT_TODAY_ACTION_BAR                                         =
                                                               By.xpath("//*[@class='todayActionBar']");

  // Action bar--> right area-->Views types as Day,List,Week,Month, WorkWeek
  public static final String          ELEMENT_CALENDAR_VIEW_BUTTON                                     = "//*[text()='$view']";

  public static final String          ELEMENT_CALENDAR_ACTIVE_VIEW                                     =
                                                                   "//*[@class='btn active']//*[text()='$view']";

  // Action bar--> right area-->Settings
  public static final By              ELEMENT_ACTION_BAR_SETTING_ICON                                  =
                                                                      By.xpath(".//*[@id='UIActionBar']//*[contains(@class,'uiIconSetting')]");

  // Action bar--> right area-->Quick Search
  public static final By              ELEMENT_EVENT_TASK_QUICK_SEARCH                                  =
                                                                      By.xpath(".//*[@id='value']");

  public static final By              ELEMENT_EVENT_TASK_CLOSE_SEARCH_BTN                              =
                                                                          By.xpath(".//*[@id='UIListView']//button[contains(text(),'Close Search')]");

  public static final By              ELEMENT_EVENT_TASK_SEARCH_BTN                                    =
                                                                    By.xpath(".//*[@id='UISearchForm']//*[contains(@class,'uiIconSearch')]");

  // Calendar left panel-->General
  public static final By              ELEMENT_SHOW_HIDE_LEFT_PANEL                                     =
                                                                   By.xpath("//div[@id='ShowHideAll']/i");

  // Calendar left panel-->Mini calendar
  public static final By              ELEMENT_CALENDAR_MINI                                            = By.id("UIMiniCalendar");

  public static final By              ELEMENT_CALENDAR_MINI_NEXT_MONTH                                 =
                                                                       By.xpath(".//*[@id='UIMiniCalendar']//*[contains(@class,'uiIconMiniArrowRight')]");

  public static final By              ELEMENT_CALENDAR_MINI_PREVIOUS_MONTH                             =
                                                                           By.xpath(".//*[@id='UIMiniCalendar']//*[contains(@class,'uiIconMiniArrowLeft')]");

  public static final String          ELEMENT_CALENDAR_MINI_HEADER_BAR                                 =
                                                                       ".//*[@id='UIMiniCalendar']//*[contains(text(),'$month')]";

  public static final String          ELEMENT_CALENDAR_MINI_DAY                                        =
                                                                ".//*[@id='UIMiniCalendar']//td[contains(text(),'$day')]";

  public static final String          ELEMENT_CALENDAR_MINI_ORDER_DAY                                  =
                                                                      "(.//*[@id='UIMiniCalendar']//td)[$number][contains(text(),'$shortDay')]";

  // Calendar left panel-->Calendar list
  public static final By              ELEMENT_CALENDAR_PANEL                                           =
                                                             By.xpath("//div[@class='uiBox uiCalendars']");

  public static final By              ELEMENT_CALENDAR_MENU_ACTIONS_ICON                               =
                                                                         By.xpath("//*[@class='uiIconCalSimplePlus uiIconLightGray']");

  public static final String          ELEMENT_SHARED_CALENDAR_LIST_ITEM                                =
                                                                        "//*[@id='UICalendars']//*[text()='Shared Calendars']/..//*[text()='$calendar']";

  public static final String          ELEMENT_GROUP_CALENDAR_LIST_ITEM                                 =
                                                                       "//*[@id='UICalendars']//*[text()='Group Calendars']/..//*[text()='$calendar']";

  public static final String          ELEMENT_PERSONAL_CALENDAR_LIST_ITEM                              =
                                                                          "//*[@id='UICalendars']//*[text()='Personal Calendars']/..//*[text()='$calendar']";

  public static final By              ELEMENT_PERSONAL_CALENDAR_LIST                                   =
                                                                     By.xpath("//*[@id='UICalendars']//*[text()='Personal Calendars']");

  public static final By              ELEMENT_GROUP_CALENDAR_LIST                                      =
                                                                  By.xpath("//*[@id='UICalendars']//*[text()='Group Calendars']");

  public static final String          ELEMENT_CALENDAR_LIST_ITEM                                       =
                                                                 "//*[@id='UICalendars']//*[contains(text(),'$calendar')]";

  public static final String          ELEMENT_CALENDAR_LIST_ITEM_COLOR                                 =
                                                                       "//*[@id='UICalendars']//*[text()='$calendar']/..//*[contains(@class,'$color')]";

  public static final String          ELEMENT_CALENDAR_LIST_UNCHECKED                                  =
                                                                      ".//*[contains(@data-original-title,'$calendar')]/..//*[contains(@class,'iconUnCheckBox')]";

  public static final String          ELEMENT_CALENDAR_LIST_CHECKED                                    =
                                                                    ".//*[contains(@data-original-title,'$calendar')]/..//*[contains(@class,'iconCheckBox')]";

  public static final String          ELEMENT_CALENDAR_SETTING_ICON                                    =
                                                                    "//*[text()='$calendar']/../..//*[contains(@class,'uiIconCalSettingMini')]";

  // Popover popup
  public static final String          ELEMENT_EVENT_TASK_POPOVER_POPUP_INFOR                           =
                                                                             "//*[contains(@class,'popover-content')]//*[contains(text(),'$info')]";

  // Preview popup
  public static final By              ELEMENT_EVENT_TASK_PREVIEW_POPUP                                 =
                                                                       By.xpath(".//*[@id='UIPreviewPopup']");

  public static final By              ELEMENT_EVENT_TASK_PREVIEW_POPOP_CLOSE_BTN                       =
                                                                                 By.xpath(".//*[@id='UIPreviewPopup']//button");

  // VIEW GENERAL-->Header bar
  public static final By              ELEMENT_NEXT_BUTTON_ANY_VIEW                                     =
                                                                   By.xpath("//*[@class='title']//*[contains(@class,'uiIconMiniArrowRight')]");

  public static final By              ELEMENT_PREVIOUS_BUTTON_ANY_VIEW                                 =
                                                                       By.xpath("//*[@class='title']//*[contains(@class,'uiIconMiniArrowLeft')]");

  public static final By              ELEMENT_CATEGORY_OPTION                                          =
                                                              By.xpath("//*[@name='eventCategories']");

  public static final String          ELEMENT_CATEGORY_OPTION_SELECTED                                 =
                                                                       "//*[@name='eventCategories']//*[@selected='selected' and text()='$name']";

  public static final By              ELEMENT_EVENT_TASK_DELETE_BUTTON                                 =
                                                                       By.xpath(".//*[contains(@data-original-title,'Delete')]//*[contains(@class,'uiIconDelete')]");

  public static final String          ELEMENT_TASK_EVENT_RECURRING_ICON                                =
                                                                        "(.//*[contains(text(),'$name')]/..//*[contains(@class,'uiIconCalRecurring')])[$number]";

  public static final String          ELEMENT_TASK_EVENT_RECURRING_ICON_ONLY_EVENT                     =
                                                                                   ".//*[contains(@class,'eventContainerBorder ')]//*[contains(text(),'$name')]/..//*[contains(@class,'uiIconCalEditRecurring')]";

  public static final By              ELEMENT_TASK_EVENT_RECURRING_POPOVER_CONTENT                     =
                                                                                   By.xpath(".//*[@class='popover-content']//*[contains(text(),'Recurring event')]");

  public static final By              ELEMENT_TASK_EVENT_RECURRING_POPOVER_CONTENT_EDIT                =
                                                                                        By.xpath(".//*[@class='popover-content']//*[contains(text(),'Edited Recurring event')]");

  // LIST VIEW-->Header bar
  public static final By              ELEMENT_CALENDAR_LIST_TAB_SELECT_ALL_CHECKBOX                    =
                                                                                    By.xpath(".//*[@id='UIListUsers']//*[contains(@data-original-title,'Select All')]//input");

  public static final By              ELEMENT_CALENDAR_LIST_VIEW_SHOW_ALL_ARROW                        =
                                                                                By.xpath(".//*[contains(@class,'uiIconMiniArrowDown')]");

  public static final By              ELEMENT_CALENDAR_LIST_VIEW_SHOW_ALL_ITEM_EVENT_ONLY              =
                                                                                          By.xpath("//*[contains(@onclick,'objectId=Event')]");

  public static final By              ELEMENT_CALENDAR_LIST_VIEW_SHOW_ALL_ITEM_TASK_ONLY               =
                                                                                         By.xpath("//*[contains(@onclick,'objectId=Task')]");

  // LIST VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_LIST_VIEW                                     =
                                                                   "//*[@id='UIListView']//*[@class='uiListViewRow']//*[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW                   =
                                                                                     "//*[@id='UIListView']//*[contains(text(),'$name')]/../..//td[5][contains(text(),'$date')]";

  public static final String          ELEMENT_EVENT_TASK_END_DETAIL_DATE_LIST_VIEW                     =
                                                                                   "//*[@id='UIListView']//*[contains(text(),'$name')]/../..//td[6][contains(text(),'$date')]";

  public static final String          ELEMENT_EVENT_TASK_ATTACHMENT_LIST_VIEW                          =
                                                                              ".//*[@id='UIPreview']//*[contains(@data-original-title,'${file}')]";

  public static final String          ELEMENT_EVENT_TASK_PARTICIPANTS_LIST_VIEW                        =
                                                                                ".//*[@id='RowContainerDay']//*[contains(text(),'${username}')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_LIST_VIEW                              =
                                                                          ".//*[@id='UIPreview']//*[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_CHECKBOX_LIST_VIEW                            =
                                                                            ".//*[contains(text(),'$name')]/../..//input";

  public static final By              ELEMENT_CALENDAR_ROW_TAB_LIST                                    =
                                                                    By.xpath(".//*[@id='UIListUsers']//td[1]");

  public static final By              ELMENT_CALENDAR_TAB_LIST_EMPTY                                   =
                                                                     By.xpath(".//*[@id='UIListUsers']//*[contains(@class,'empty')]");

  public static final By              ELEMENT_EVENT_TASK_DETAIL_IMAGE_VIEW                             =
                                                                           By.xpath(".//*[@id='UIPreview']//*[contains(@class,'columnLeft')]//*[@class='view']");

  public static final String          ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL                        =
                                                                                "(.//*[@id='UIPreview']//*[contains(@class,'columnLeft')]//*[@class='thumbnail'])[$number]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_CONTAINER              =
                                                                                          ".//*[@class='thumbnailContainer'][$number]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_VIEW                   =
                                                                                     ".//*[@class='thumbnailContainer'][1]//*[@class='view']";

  public static final By              ELEMENT_EVENT_TASK_DETAIL_ATTACHMENT_ICON                        =
                                                                                By.xpath(".//*[@id='UIPreview']//*[contains(@class,'columnLeft')]//*[contains(@class,'uiIcon16x16nt_file')]");

  public static final By              ELEMENT_EVENT_TASK_LARGE_IMAGE                                   =
                                                                     By.xpath(".//*[@id='imagePreviewContainer']");

  public static final By              ELEMENT_EVENT_TASK_LARGE_IMAGE_DOWNLOAD                          =
                                                                              By.xpath(".//*[@id='imagePreviewContainer']//*[@id='downloadImage']");

  public static final By              ELMEENT_EVENT_TASK_LARGE_IMAGE_CLOSE                             =
                                                                           By.xpath(".//*[@id='closeButton']//*[contains(@class,'uiIconClose')]");

  // DAY VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY                              =
                                                                          "//*[@id='UIDayView']//*[@class='eventAllDay']//*[contains(@class,'eventContainer')]//div[contains(.,'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY                              =
                                                                          "//*[@id='UIDayViewGrid']//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DAY_ONE_DAY                                   =
                                                                     ".//*[contains(@class,'tdLine')][contains(@startfull,'$date')]";

  public static final String          ELEMENT_EVENT_TASK_DAY_TIME                                      =
                                                                  ".//*[@id='UIDayViewGrid']//*[contains(text(),'$name')]/..//*[contains(text(),'$time')]";

  public static final String          ELEMENT_EVENT_TASK_DATE_TIME_VALUE                               =
                                                                         ".//*[@id='UIDayViewGrid']//td[contains(@startfull,'$time')]";

  public static final String          ELEMENT_DAY_VIEW_HEADER_BAR                                      =
                                                                  ".//*[@id='UIDayView']//*[contains(@class,'titleHeader')]//*[contains(text(),'$month')]/..[contains(.,'/ $day / $year')]";

  // WEEK VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY                             =
                                                                           "//*[@id='UIWeekView']//*[@class='eventAllDay']//*[contains(@class,'eventContainer') and contains(@style,'display: block')]//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY                             =
                                                                           "//*[@id='UIWeekViewGrid']//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY                 =
                                                                                       "//*[@id='UIWeekViewGrid']//*[contains(@startfull,'$date')]//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ALL_DAY                 =
                                                                                       "//*[@id='UIWeekViewGridAllDay']//*[contains(text(),'$name')]/..[contains(@starttimefull,'$date')]";

  public static final String          ELEMENT_WEEK_VIEW_BAR_TIME                                       =
                                                                 "//*[@class='eventWeekBar']//td['$index']/a";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK                              =
                                                                          "//*[@id='UIWeekViewGrid']//*[contains(@startfull,'$date')]//div[contains(text(),'$name')]/..//*[contains(.,'$time')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY                      =
                                                                                  "//*[@id='UIWeekViewGrid']//*[contains(@startfull,'$date')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY                      =
                                                                                  "//*[@id='UIWeekViewGridAllDay']//*[contains(@starttimefull,'$date')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK_ONE_DAY                      =
                                                                                  "//*[@id='UIWeekViewGrid']//*[contains(text(),'$name')]/..//*[contains(.,'$time')]";

  public static final String          ELEMENT_EVENT_TASK_NUMBER_RECURRING_WEEK_VIEW                    =
                                                                                    "(.//*[@id='UIWeekViewGrid']//*[contains(text(),'$name')])[$number]";

  public static final String          ELEMENT_EVENT_TASK_WEEK_TIME                                     =
                                                                   ".//*[@id='UIWeekViewGrid']//*[contains(text(),'$name')]/..//*[contains(.,'$time')]";

  public static final String          ELEMENT_WEEK_VIEW_ORDER_DAY                                      =
                                                                  "(.//*[@id='UIWeekView']//td[contains(@class,'uiCellBlock')])[$number][contains(@starttimeFull,'$day')]";

  // MONTH VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_MONTH_VIEW                                    =
                                                                    "//*[@id='UIMonthView']//span[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_MONTH_DATE                                    =
                                                                    "//*[@id='UIMonthViewGrid']//*[contains(@starttimefull,'$date')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW                        =
                                                                                "//*[@id='UIMonthView']//*[@class='eventMonthContent']//*[@class='rowContainerDay']/*[contains(@starttimefull,'$date')]//span[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE                   =
                                                                                     "//*[@id='UIMonthView']//*[@class='eventMonthContent']//*[@class='moreEventContainer']//*[contains(@starttimefull,'$date')]//span[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON              =
                                                                                          "//*[@id='UIMonthView']//*[contains(@starttimefull,'$date')]/..//*[@class='moreEvent' and not(contains(@style, 'display'))]/*[@class='moreEventLabel']";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_LABEL             =
                                                                                           ".//*[contains(@starttimefull,'$date')]/../../..//*[contains(@class,'moreEventLabel')]";

  public static final String          ELEMENT_EVENT_TASK_CHECKBOX                                      =
                                                                  ".//*[contains(text(),'$name')]/../..//input[@type='checkbox']";

  public static final String          ELEMENT_EVENT_TASK_CHECKBOX_CHECKED                              =
                                                                          ".//*[contains(text(),'$name')]/../..//input[@checked='checked']";

  public static final String          ELEMENT_CELL_TO_MONTH_WORKING_PANEL                              =
                                                                          "//td[contains(@starttimefull,'$date')]";

  public static final String          ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX          =
                                                                                              "(.//*[@id='UIMonthView']//*[contains(text(),'$name')])[$number]/../..//input[@type='checkbox']";

  public static final String          ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX_DATE     =
                                                                                                   "//*[@id='UIMonthView']//*[@class='eventMonthContent']//*[@class='rowContainerDay']/*[contains(@starttimefull,'$date')]//span[contains(text(),'$name')]/../..//input[@type='checkbox']";

  public static final String          ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW                   =
                                                                                     "(.//*[@id='UIMonthView']//*[contains(text(),'$name')])[$number]";

  public static final String          ELEMENT_EVENT_TASK_DAY_BY_INDEX_MONTH_VIEW                       =
                                                                                 ".//*[@cindex='$col' and @rindex='$row']";

  public static final String          ELEMENT_MONTH_VIEW_HEADER_BAR                                    =
                                                                    ".//*[@id='UIMonthView']//div[contains(text(),'$monthYear')]";

  public static final String          ELEMENT_MONTH_VIEW_ORDER_DAY                                     =
                                                                   "(.//*[@id='UIMonthView']//*[contains(@class,'uiCellBlock')])[$number][contains(text(),'$day')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_LABEL1            =
                                                                                            "//*[@class='moreEventLabel' and contains(.,'${text}')]";

  // WORK WEEK VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY                        =
                                                                                "//*[@id='UIWeekView']//*[@class='eventAllDay']//*[contains(@class,'eventContainer')]//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY                        =
                                                                                "//*[@id='UIWeekView']//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY            =
                                                                                            "//*[@id='UIWeekViewGrid']//*[contains(@startfull,'$date')]//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY            =
                                                                                            "//*[@id='UIWeekViewGridAllDay']//*[contains(@starttimefull,'$date')]//div[contains(text(),'$name')]";

  public static final String          ELEMENT_WORK_WEEK_VIEW_ORDER_DAY                                 =
                                                                       "(.//*[@id='UIWeekView']//td[contains(@class,'uiCellBlock')])[$number][contains(@starttimeFull,'$day')]";

  /***************************************
   * CATEGORY MANAGEMENT
   ********************************************************************************************/

  // Add event category form
  public static final By              ELEMENT_ADD_EVENT_CATEGORY_FORM                                  =
                                                                      By.id("UICalendarPopupAction");

  public static final By              ELEMENT_ADD_EVENT_CATEGORY_INPUT                                 =
                                                                       By.id("eventCategoryName");

  public static final By              ELEMENT_ADD_EVENT_CATEGORY_BUTTON_ADD                            =
                                                                            By.id("btnEventCategoryFormContainer");

  public static final SelenideElement ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE                          =
                                                                              $(byId("UICalendarPopupWindow")).find(byText("Close"));

  public static final String          ELEMENT_LIST_EVENT_CATEGORY                                      =
                                                                  "//*[@id='UIEventCategoryList']//span[contains(text(),'${categoryName}')]";

  public static final String          ELEMENT_LIST_EDIT_EVENT_BUTTON                                   =
                                                                     ".//*[@id='UIEventCategoryList']//span[contains(text(),'${categoryName}')]/parent::td/parent::tr//a[@data-original-title='Edit']/i[@class='uiIconEdit uiIconLightGray']";

  public static final By              ELEMENT_EDIT_EVENT_CATEGORY_BUTTON_UPDATE                        =
                                                                                By.id("btnEventCategoryFormContainer");

  public static final By              ELEMENT_TOOLBAR_MINI_CALENDAR                                    =
                                                                    By.xpath("//*[@class='weekDays']");

  public static final By              ELEMENT_DELETE_ALL_CATEGORY                                      =
                                                                  By.xpath(".//*[contains(text(),'Cannot delete the default event category.')]");

  public static final By              ELEMENT_DELETE_ALL_CATEGORY_OK_BTN                               =
                                                                         By.xpath(".//*[contains(@class,'UIPopupWindow')]//*[contains(text(),'OK')]");

  /***************************************
   * CALENDAR MANAGEMENT
   ********************************************************************************************/
  // CONTEXT MENU-->CALENDAR ACTIONS
  public static final By              ELEMENT_CALENDAR_MENU                                            = By.id("tmpMenuElement");

  public static final By              ELEMENT_CALENDAR_MENU_ACTIONS_ADD                                =
                                                                        By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'AddCalendar')]");

  public static final By              ELEMENT_CALENDAR_MENU_ACTIONS_REMOTE                             =
                                                                           By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'RemoteCalendar')]");

  public static final By              ELEMENT_CALENDAR_MENU_ACTIONS_ADD_EVENT_CATEGORY                 =
                                                                                       By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'AddEventCategory')]");

  public static final By              ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT                             =
                                                                           By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'ImportCalendar')]");

  public static final By              ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING                   =
                                                                                     By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'CalendarSetting')]");

  public static final String          ELEMENT_CALENDAR_MENU_ACTIONS_COLOR                              =
                                                                          ".//*[@id='tmpMenuElement']//a[contains(@class,'${color}')]";

  // CONTEXT MENU-->CALENDAR DETAIL ACTIONS
  public static final By              ELEMENT_CALENDAR_RIGHT_MENU                                      = By.id("tmpMenuElement");

  public static final By              ELEMENT_CALENDAR_ADD_TASK_MENU                                   =
                                                                     By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalCreateTask uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_ADD_EVENT_MENU                                  =
                                                                      By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalCreateEvent uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_ADD_EVENT_MENU_NO_DISPLAY                       =
                                                                                 By.xpath(".//*[@id='tmpMenuElement']//*[contains(@style,'display: none;')]//*[contains(@class,'uiIconCalCreateEvent')]");

  public static final By              ELEMENT_CALENDAR_EDIT_MENU                                       =
                                                                 By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_SHARE_MENU                                      =
                                                                  By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalShare uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_REMOVE_MENU                                     =
                                                                   By.xpath("//*[@id='tmpMenuElement']//*[contains(@href,'RemoveCalendar')]");

  public static final By              ELEMENT_CALENDAR_REMOVE_SHARE_CALENDAR                           =
                                                                             By.xpath("//*[@id='tmpMenuElement']//*[contains(@href,'RemoveSharedCalendar')]");

  public static final By              ELEMENT_CALENDAR_IMPORT_MENU                                     =
                                                                   By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalImportCalendar uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_EXPORT_MENU                                     =
                                                                   By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalExportCalendar uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_REFRESH_MENU                                    =
                                                                    By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconRefresh uiIconLightGray']");

  // Calendar import form
  public static final By              ELEMENT_CALENDAR_IMPORT_POPUP_FORM                               = By.id("UIImportForm");

  public static final By              ELEMENT_CALENDAR_IMPORT_DESC_INPUT                               =
                                                                         By.xpath("//form[@id='UIImportForm']//*[@id='description']");

  public static final By              ELEMENT_CALENDAR_IMPORT_SAVE_BUTTON                              =
                                                                          By.xpath("//form[@id='UIImportForm']//*[text()='Save']");

  public static final By              ELEMENT_CALENDAR_IMPORT_SELECT_FILE                              = By.name("file");

  public static final By              ELEMENT_CALENDAR_IMPORT_DELETE_ICON                              =
                                                                          By.xpath("//a[@data-original-title='Delete']/i[@class='uiIconDelete uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_IMPORT_NAME_INPUT                               = By.id("displayName");

  public static final By              ELEMENT_CALENDAR_IMPORT_COLOR                                    =
                                                                    By.xpath("//*[contains(@class,'displayValue')]");

  // Calendar export form
  public static final By              ELEMENT_CALENDAR_EXPORT_POPUP_FORM                               =
                                                                         By.id("UICalendarPopupWindow");

  public static final By              ELEMENT_CALENDAR_EXPORT_FILE_NAME                                = By.id("name");

  public static final By              ELEMENT_CALENDAR_EXPORT_SAVE_BUTTON                              =
                                                                          By.xpath("//*[@id='UIExportForm']//*[text()='Save']");

  // Calendar share form
  public static final By              ELEMENT_CALENDAR_SHARE_FORM                                      = By.id("UISharedForm");

  public static final By              ELEMENT_CALENDAR_SHARE_INPUT                                     =
                                                                   By.id("PermissionOwnerInput");

  public static final By              ELEMENT_CALENDAR_SELECT_USER_ICON                                =
                                                                        By.xpath("//*[@class='uiIconUser uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_SELECT_MEMBERSHIP_ICON                          =
                                                                              By.xpath("//*[@class='uiIconMembership uiIconLightGray']");

  public static final By              ELEMENT_CALENDAR_SELECT_GROUP_ICON                               =
                                                                         By.xpath("//*[@class='uiIconGroup uiIconLightGray']");

  public static final String          ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION                           =
                                                                             "//div[contains(@title,'${user}')]/../..//input[@class='checkbox']";

  public static final By              ELEMENT_CALENDAR_SHARE_ADD_BUTTON                                =
                                                                        By.xpath("//form[@id='UISharedForm']//*[text()='Add']");

  public static final By              ELEMENT_CALENDAR_SHARE_SAVE_BUTTON                               =
                                                                         By.xpath("//form[@id='UISharedForm']//*[text()='Save']");

  public static final String          ELEMENT_DELETE_SHARE_USER                                        =
                                                                "//*[@id='UISharedForm']//*[contains(text(),'{$user}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By              ELEMENT_CALENDAR_WARINING_POPUP                                  =
                                                                      By.xpath(".//*[contains(@class,'warningIcon')]");

  public static final By              ELEMENT_CALENDAR_OK_BTN_WARNING_POPUP                            =
                                                                            By.xpath(".//*[contains(@class,'warningIcon')]/../../..//*[contains(@class,'btn')]");

  // Calendar information popup for removing
  public static final By              ELEMENT_CALENDAR_REMOVE_FORM                                     =
                                                                   By.xpath("//*[@class='confirmationIcon']");

  public static final SelenideElement ELEMENT_YES_BUTTON                                               =
                                                         $(byXpath("//*[@id=\"UIConfirmation\"]/div[2]/div/a[1]"));

  public static final String          ELEMENT_CONFIRM_REMOVE_CALENDAR_MSG                              =
                                                                          "Are you sure you want to delete this calendar and all its events?";

  // Calendar setting form
  public static final By              ELEMENT_CALENDAR_SETTING_FORM                                    =
                                                                    By.id("UICalendarSettingForm");

  public static final By              ELEMENT_CALENDAR_SETTING_DISPLAY_TAB                             =
                                                                           By.xpath("//*[@data-target='#defaultCalendarTab-tab']");

  public static final By              ELEMENT_CALENDAR_SETTING_FEED_TAB                                =
                                                                        By.xpath("//*[@data-target='#feedTab-tab']");

  public static final By              ELEMENT_CALENDAR_SETTING_VIEW_TYPE                               = By.name("viewType");

  public static final By              ELEMENT_CALENDAR_SETTING_TIME_ZONE                               = By.name("timeZone");

  public static final By              ELEMENT_CALENDAR_SETTING_DATE_FORMAT                             = By.name("dateFormat");

  public static final By              ELEMENT_CALENDAR_SETTING_TIME_FORMAT                             = By.name("timeFormat");

  public static final By              ELEMENT_CALENDAR_SETTING_WEEK_START_ON                           = By.name("weekStartOn");

  public static final By              ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX              =
                                                                                          By.xpath(".//input[@id='showWorkingTime']");

  public static final By              ELEMENT_CALENDAR_SETTING_SHOW_WORKING_BEGIN_TIME                 = By.name("beginTime");

  public static final By              ELEMENT_CALENDAR_SETTING_SHOW_WORKING_END_TIME                   = By.name("endTime");

  public static final By              ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX              =
                                                                                          By.xpath("//*[@value='never']");

  public static final By              ELEMENT_CALENDAR_SETTING_ALWAYS_SEND_INVITE_CHECKBOX             =
                                                                                           By.xpath("//*[@value='always']");

  public static final By              ELEMENT_CALENDAR_SETTING_ASK_SEND_INVITE_CHECKBOX                =
                                                                                        By.xpath("//*[@value='ask']");

  public static final String          ELEMENT_CALENDAR_SETTING_TIMEZONE_VALUE                          =
                                                                              "//*[@id='setting']//select[@name='timeZone']/option[@value='$time']";

  public static final String          ELEMENT_CALENDAR_SETTING_VIEWTYPE_VALUE                          =
                                                                              "//*[@id='setting']//select[@name='viewType']/option[@value='$view']";

  public static final String          ELEMENT_CALENDAR_SETTING_DATEFORMAT_VALUE                        =
                                                                                "//*[@id='setting']//select[@name='dateFormat']/option[@value='$date']";

  public static final String          ELEMENT_CALENDAR_SETTING_TIMEFORMATE_VALUE                       =
                                                                                 "//*[@id='setting']//select[@name='timeFormat']/option[@value='$time']";

  public static final String          ELEMENT_CALENDAR_SETTING_STARTON_VALUE                           =
                                                                             "//*[@id='setting']//select[@name='weekStartOn']/option[@value='$time']";

  public static final By              ELEMENT_SETTING_FORM_SAVE_BUTTON                                 =
                                                                       By.xpath(".//*[@id='UICalendarSettingForm']//button[1]");

  public static final By              ELEMENT_SETTING_FORM_CANCEL_BUTTON                               =
                                                                         By.xpath(".//*[@id='UICalendarSettingForm']//button[2]");

  // Calendar setting - Displayed calendar form
  public static final String          ELEMENT_DISPLAY_FORM_PERSONAL_CALENDAR_ITEM                      =
                                                                                  "//*[@id='UIPopupCalendarSettingContainer']//*[text()='Personal Calendars']/../..//*[@class='calendarName' and text()='$name']";

  public static final String          ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED                            =
                                                                            ".//*[@id='UICalendarPopupWindow']//*[contains(text(),'$calendar')]/..//*[contains(@class,'iconCheckBox')]";

  public static final String          ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED                          =
                                                                              ".//*[@id='UICalendarPopupWindow']//*[contains(text(),'$calendar')]/..//*[contains(@class,'iconUnCheckBox')]";

  public static final String          ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM                         =
                                                                               "//*[@id='UIPopupCalendarSettingContainer']//*[text()='Group Calendars']/../..//*[@class='calendarName' and contains(text(),'$name')]";

  public static final String          ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX                =
                                                                                        "//*[@id='UIPopupCalendarSettingContainer']//*[text()='Group Calendars']/../..//*[@class='calendarName' and contains(text(),'$name')]/../..//*[@class='iconCheckBox checkbox' or @class='checkbox iconCheckBox']";

  public static final String          ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_UNCHECKBOX              =
                                                                                          "//*[@id='UIPopupCalendarSettingContainer']//*[text()='Group Calendars']/../..//*[@class='calendarName' and contains(text(),'$name')]/../..//*[@class='iconUnCheckBox checkbox' or @class='checkbox iconUnCheckBox']";

  // Calendar setting - Feed tab
  public static final By              ELEMENT_FEED_TAB_FORM                                            = By.id("feedTab");

  public static final By              ELEMENT_FEED_TAB_SAVE_BUTTON                                     =
                                                                   By.xpath("//*[@id='feedTab']//*[text()='Add']");

  public static final By              ELEMENT_FEED_EDIT_FEED_FORM                                      =
                                                                  By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']");

  public static final By              ELEMENT_FEED_EDIT_FEED_SAVE_FORM                                 =
                                                                       By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[contains(@onclick,'Save')]");

  public static final By              ELEMENT_FEED_EDIT_FEED_CANCEL_FORM                               =
                                                                         By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[contains(@onclick,'Cancel')]");

  public static final By              ELEMENT_FEED_NAME_INPUT                                          = By.id("name");

  public static final By              ELEMENT_FEED_URL_INPUT                                           = By.id("url");

  public static final By              ELEMENT_FEED_CALENDAR_OPTION                                     = By.name("addMore");

  public static final By              ELEMENT_FEED_EDIT_FEED_RESET_URL                                 =
                                                                       By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[@data-original-title='Reset URL']");

  public static final By              ELEMENT_FEED_EDIT_FEED_GENERATE_URL                              =
                                                                          By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[@data-original-title='Generate URL']");

  public static final By              ELEMENT_FEED_EDIT_FEED_ADD_CALENDAR                              =
                                                                          By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[@data-original-title='Add Calendar']");

  public static final String          ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR                           =
                                                                             "//*[@id='UIEditFeed' and @class='UIEditFeed']//*[text()='$name']/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_FEED_LIST_ITEM_RSS                                       =
                                                                 "//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Rss']";

  public static final String          ELEMENT_FEED_LIST_ITEM_RSS_BUTTON                                =
                                                                        "//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Rss']";

  public static final String          ELEMENT_FEED_LIST_ITEM_EDIT_BUTTON                               =
                                                                         "//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Edit']";

  public static final String          ELEMENT_FEED_LIST_ITEM_DELETE_BUTTON                             =
                                                                           "//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Delete']";

  public static final String          ELEMENT_FEED_CONFIRM_DELETE                                      =
                                                                  "Are you sure you want to delete this feed from the list?";

  public static final String          ELEMENT_FEED_CONFIRM_ADD_FEED                                    =
                                                                    "The feed $name has been generated successfully.";

  // Remote calendar form
  public static final By              ELEMENT_REMOTE_CALENDAR_FORM                                     =
                                                                   By.id("UICalendarPopupWindow");

  public static final By              ELEMENT_REMOTE_CALENDAR_URL                                      = By.id("url");

  public static final By              ELEMENT_REMOTE_CALENDAR_ICALENDAR_RADIO                          =
                                                                              By.xpath(".//*[@id='UISubscribeForm']//input[contains(@value,'ICalendar(.ics)')]");

  public static final By              ELEMENT_REMOTE_CALENDAR_CALDAV_RADIO                             =
                                                                           By.xpath(".//*[@id='UISubscribeForm']//input[contains(@value,'CalDAV')]");

  public static final By              ELMENT_REMOTE_CALENDAR_NEXT_BTN                                  =
                                                                      By.xpath(".//*[@id='UISubscribeForm']//button[1]");

  public static final By              ELMENT_REMOTE_CALENDAR_SUBCRIBE_CANCEL_BTN                       =
                                                                                 By.xpath(".//*[@id='UISubscribeForm']//button[2]");

  public static final By              ELEMENT_REMOTE_CALENDAR_NAME                                     =
                                                                   By.xpath(".//.//*[@id='UIRemoteCalendar']//*[@id='name']");

  public static final By              ELEMENT_REMOTE_CALENDAR_DES                                      =
                                                                  By.xpath(".//*[@id='UIRemoteCalendar']//*[@id='description']");

  public static final By              ELEMENT_REMOTE_CALENDAR_SAVE_BTN                                 =
                                                                       By.xpath(".//*[@id='UIRemoteCalendar']//button[2]");

  public static final By              ELEMENT_REMOTE_CALENDAR_CANCEL_BTN                               =
                                                                         By.xpath(".//*[@id='UIRemoteCalendar']//button[3]");

  public static final By              ELEMENT_REMOTE_CALENDAR_AUTHENTICATION_CHECKBOX                  =
                                                                                      By.xpath(".//*[@name='useAuthentication']");

  public static final By              ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_DISABLED                  =
                                                                                      By.xpath(".//*[@id='username'][contains(@disabled,\"\")]");

  public static final By              ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_ENABLED                   = By.id("username");

  public static final By              ELEMENT_REMOTE_CALENDAR_PASSWORD_FIELD_ENABLED                   = By.id("password");

  public static final By              ELEMENT_REMOTE_CALENDAR_WARNING_AUTHENTICATION                   =
                                                                                     By.xpath(".//*[contains(text(),'The remote URL is invalid or the authentication failed.')]");

  public static final By              ELEMENT_REMOTE_CALENDAR_WARNING_INVALID_URL                      =
                                                                                  By.xpath(".//*[contains(text(),'The \"URL\" field does not contain a valid URL.')]");

  public static final By              ELEMENT_REMOTE_CALENDAR_WARNING_EMPTY_DISPLAY_NAME               =
                                                                                         By.xpath(".//*[contains(text(),'The field \"Display Name\" is required.')]");

  public static final By              ELEMENT_REMOTE_CALENDAR_WARNING_EMPTY_USERNAME                   =
                                                                                     By.xpath(".//*[contains(text(),'Username required to authenticate.')]");

  public static final By              ELEMENT_REMOTE_CALENDAR_BACK_BTN                                 =
                                                                       By.xpath(".//*[@id='UIRemoteCalendar']//button[1]");

  public static final By              ELEMENT_REMOTE_CALENDAR_COLOR_FIELD                              =
                                                                          By.xpath("//*[contains(@class,'displayValue')]");

  public static final String          ELEMENT_REMOTE_CALENDAR_COLOR_SELECT                             =
                                                                           ".//*[@id='UIRemoteCalendar']//*[contains(@class,'${color}')]";

  // Add calendar form
  public static final By              ELEMENT_CALENDAR_ADD_FORM                                        =
                                                                By.id("UICalendarPopupWindow");

  public static final By              ELEMENT_CALENDAR_DISPLAY_NAME_INPUT                              = By.id("displayName");

  public static final By              ELEMENT_CALENDAR_DESC_INPUT                                      =
                                                                  By.xpath("//*[@id='UICalendarForm']//*[@id='description']");

  public static final By              ELEMENT_CALENDAR_TIMEZONE                                        = By.id("timeZone");

  public static final By              ELEMENT_CALENDAR_COLOR                                           =
                                                             By.xpath("//*[contains(@class,'displayValue')]");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB                                       =
                                                                 By.xpath(".//*[@id='uiPopupAddCalendarContainer']//*[contains(@data-target,'#public-tab')]");

  public static final SelenideElement ELEMENT_CALENDAR_DETAIL_TAB                                      =
                                                                  $(byXpath("//*[@id=\"uiPopupAddCalendarContainer\"]/div/ul/li[1]"));

  public static final By              ELEMENT_CALENDAR_GROUP_INPUT                                     = By.id("AddGroupInput");

  public static final By              ELEMENT_CALENDAR_GROUP_INPUT_USER                                =
                                                                        By.xpath(".//*[contains(@class,'inputLarge')]//input");

  public static final By              ELEMENT_CALENDAR_GROUP_SELECT_USER_BTN                           =
                                                                             By.xpath(".//*[@id='public']//*[contains(@class,'uiIconUser')]");

  public static final String          ELEMENT_CALENDAR_GROUP_USER_IN_SELECT_FORM                       =
                                                                                 ".//*[@id='UIGroupSelector']//*[contains(text(),'$user')]";

  public static final By              ELEMENT_CALENDAR_GROUP_SELECT_FORM                               =
                                                                         By.xpath(".//*[@id='UIGroupSelector']//*[@class='uiContentBox']");

  public static final String          ELEMENT_CALENDAR_GROUP_SELECT_LIST                               =
                                                                         "//*[@id='UIGroupSelector']//a[contains(.,'$group')]";

  public static final By              ELEMENT_CALENDAR_GROUP_SELECT_ROLE_BTN                           =
                                                                             By.xpath(".//*[@id='public']//*[contains(@class,'uiIconMembership')]");

  public static final String          ELEMENT_CALENDAR_GROUP_REMOVE_BTN                                =
                                                                        ".//*[contains(text(),'$group')]/..//*[contains(@class,'uiIconDelete')]";

  public static final String          ELEMENT_CALENDAR_GROUP_TOOLTIP_ONLY_PERMISSION                   =
                                                                                     ".//*[contains(text(),'$group')]/..//*[contains(@title,'Only 1 permission, cannot delete')]";

  public static final String          ELEMENT_CALENDAR_GROUP_REMOVE_DISABLE_BTN                        =
                                                                                ".//*[contains(text(),'$group')]/..//*[contains(@class,'disableIcon')]";

  public static final String          ELEMENT_CALENDAR_GROUP_USER_PERMISSION                           =
                                                                             ".//*[@id='UICalendarPopupWindow']//*[contains(@value,'$user')]";

  public static final String          ELEMENT_CALENDAR_GROUP_ITEM                                      =
                                                                  "//*[@id='UICalendarChildPopupWindow']//*[@data-original-title='$group']";

  public static final By              ELEMENT_CALENDAR_ADD_GROUP_BUTTON                                =
                                                                        By.xpath("//*[@class='addGroup']//*[text()='Add']");

  public static final SelenideElement ELEMENT_CALENDAR_ADD_SAVE_BUTTON                                 =
                                                                       $(byXpath("//*[@id=\"UICalendarForm\"]/div[4]/button[1]"));

  public static final By              ELEMENT_CALENDAR_ADD_CANCEL_BUTTON                               =
                                                                         By.xpath("//*[@id='UICalendarForm']//*[text()='Cancel']");

  public static final By              ELEMENT_CALENDAR_ADD_RESET_BUTTON                                =
                                                                        By.xpath("//*[@id='UICalendarForm']//*[text()='Reset']");

  public static final String          ELEMENT_CALENDAR_COLOR_SELECT                                    =
                                                                    "//*[@id='UICalendarForm']//a[contains(@class,'${color}')]";

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TOOLTIP_GROUP_BTN                     =
                                                                                   By.xpath(".//*[@rel='tooltip' and @data-original-title='Group']");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TOOLTIP_ACTIONS_SELECT_USER_BTN       =
                                                                                                 By.xpath(".//*[@rel='tooltip' and @title='Select User']");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TOOLTIP_ACTIONS_SELECT_ROLE_BTN       =
                                                                                                 By.xpath(".//*[@rel='tooltip' and @title='Select Role']");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TOOLTIP_ACTIONS_DELETE_PERMISSION_BTN =
                                                                                                       By.xpath(".//*[@rel='tooltip' and @title='Delete Permission']");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TABLE_GROUPS_COLUMN                   =
                                                                                     By.xpath(".//*[@id='public']//th[text()='Groups']");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TABLE_USERS_COLUMN                    =
                                                                                    By.xpath(".//*[@id='public']//th[text()='User able to edit calendar']");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TABLE_ACTIONS_COLUMN                  =
                                                                                      By.xpath(".//*[@id='public']//th[text()='Actions']");

  public static final By              ELEMENT_CALENDAR_GROUP_TAB_TABLE_EMPTY                           =
                                                                             By.xpath(".//*[@id='public']//td[text()='No Group Selected']");

  // Edit calendar form
  public static final By              ELEMENT_CALENDAR_EDIT_ENABLE_PUBLIC_LINK                         =
                                                                               By.xpath(".//*[@id='calendarDetail']//*[contains(text(),'Enable Public Access')]");

  public static final By              ELEMENT_CALENDAR_EDIT_DISNABLE_PUBLIC_LINK                       =
                                                                                 By.xpath(".//*[@id='calendarDetail']//*[contains(text(),'Disable Public Access')]");

  public static final By              ELEMENT_CALENDAR_EDIT_PUBLIC_LINK_BTN                            =
                                                                            By.xpath(".//*[@id='calendarDetail']//*[contains(@title,'Open')]//*[contains(@class,'uiIconCalICal')]");

  public static final By              ELEMENT_CALENDAR_EDIT_FEED_LINK                                  =
                                                                      By.xpath(".//*[@id='UIFeed']//*[contains(@class,'feedLink')]");

  public static final By              ELEMENT_CALENDAR_EDIT_FEED_CLOSED_BTN                            =
                                                                            By.xpath(".//*[@id='UIFeed']//button");

  /***************************************
   * EVENT/TASK MANAGEMENT GENERAL
   ********************************************************************************************/

  // CONTEXT MENU-->Right Click on Task/Event in Grid
  public static final By              ELEMENT_CONTEXT_MENU                                             =
                                                           By.xpath(".//*[@id='tmpMenuElement']");

  public static final By              ELEMENT_CONTEXT_MENU_VIEW                                        =
                                                                By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'iIconPreview')]");

  public static final By              ELEMENT_CONTEXT_MENU_EDIT                                        =
                                                                By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_CONTEXT_MENU_DELETE                                      =
                                                                  By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconDelete')]");

  public static final By              ELEMENT_CONTEXT_MENU_EXPORT                                      =
                                                                  By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconCalExportCalendar')]");

  // CONTEXT MENU-->Right click on empty grid
  public static final By              ELEMENT_CONTEXT_MENU_ADD_EVENT                                   =
                                                                     By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconCalCreateEvent')]");

  public static final By              ELEMENT_CONTEXT_MENU_ADD_TASK                                    =
                                                                    By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconCalCreateTask')]");

  // Preview Task/Event popup
  public static final String          ELEMENT_CALENDAR_PREVIEW_TASK_EVENT                              =
                                                                          ".//*[@id='UIPreviewPopup']//strong[contains(text(),'${name}')]";

  public static final By              ELEMENT_PREVIEW_TASK_EVENT_FORM                                  = By.id("UIEventPreview");

  public static final String          ELEMENT_PREVIEW_TASK_EVENT_NAME                                  =
                                                                      "//*[@id='UIEventPreview']//*[text()='$name']";

  public static final By              ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM                            =
                                                                            By.xpath("//*[@id='UIEventPreview']//*[text()='Close']");

  // Confirm popup Task/Event
  public static final By              ELEMENT_CONFIRM_POPUP_OK                                         =
                                                               By.xpath(".//*[@id='UIConfirmation']//*[contains(text(),'Yes')]");

  public static final By              ELEMENT_CONFIRM_POPUP_DELETE                                     =
                                                                   By.xpath(".//*[contains(@class,'uiConfirmForm')]//button[1]");

  public static final By              ELEMENT_CONFIRM_POPUP_NO                                         =
                                                               By.xpath(".//*[@id='UIConfirmation']//*[contains(text(),'No')]");

  // ADD QUICK TASK FORM
  public static final By              ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM                             = By.id("UIQuickAddTask");

  // ADD/EDIT DETAIL TASK/EVENT FORM
  public static final SelenideElement ELEMENT_ADD_EDIT_TASK_POPUP                                      =
                                                                  $(byId("UICalendarPopupWindow"));

  // Resize task or event
  public static final String          ELEMENT_RESIZE_CONTAINER                                         =
                                                               "//*[contains(text(),'$name')]/../..//*[@class='resizeEventContainer']";

  public static final String          ELEMENT_EVENT_TASK_RESIZE_CONTAINER                              =
                                                                          "//*[contains(text(),'$name')]/..//*[contains(@class,'resizeEventContainer')]/span";

  // Delete task/event warning
  public static final String          ELEMENT_CONFIRM_DELETE_TASK_MSG                                  =
                                                                      "Are you sure you want to delete this task?";

  public static final String          ELEMENT_CONFIRM_DELETE_EVENT_MSG                                 =
                                                                       "Are you sure you want to delete this event?";

  // quick and advance search of Task/Event
  public static final By              ELEMENT_QUICK_SEARCH_INPUT                                       = By.id("value");

  public static final String          ELEMENT_QUICK_SEARCH_FORM                                        =
                                                                "//div[@class='uiSearchForm uiSearchInput pull-right']";

  public static final String          ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT                         =
                                                                               "//*[@id='UIListView']//button[contains(text(),'Close Search')]";

  public static final String          ELEMENT_BUTTON_OPEN_ADVANCE_SEARCH_FORM                          =
                                                                              "//*[@id='UIListView']//button[contains(text(),'Advanced Search')]";

  public static final String          ELEMENT_INPUT_TEXT_ADVANCE_SEARCH                                =
                                                                        "//*[@id='UIAdvancedSearchForm']//*[@id='text']";

  public static final String          ELEMENT_BUTTON_SEARCH_ADVANCE_SEARCH                             =
                                                                           "//*[@id='UIAdvancedSearchForm']//button[contains(text(),'Search')]";

  /***************************************
   * EVENT MANAGEMANT
   ********************************************************************************************/
  // Upload form
  public static final By              ELEMENT_ADD_EVENT_UPLOAD_FILE                                    =
                                                                    By.xpath("//*[@name='file']");

  public static final String          ELEMENT_ATTACH_FILE_LABEL                                        =
                                                                "//div[@class='fileNameLabel' and contains(text(),'${file}')]";

  public static final By              ELEMENT_ATTACH_FILE_SAVE_BUTTON                                  =
                                                                      By.xpath("//form[@id='UIAttachFileForm']//button[text()='Save']");

  public static final String          ELEMENT_ADD_EVENT_FILE_ATTACHED                                  =
                                                                      "//a[@data-original-title='${file}']";

  public static final By              ELEMENT_ADD_EVENT_TASK_UPLOAD_FINISH                             =
                                                                           By.xpath(".//*[contains(@class,'pull-left percent')][text()='100%']");

  // ADD QUICK EVENT FORM
  public static final By              ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM                            =
                                                                            By.id("UIQuickAddEventPopupWindow");

  public static final By              ELEMENT_QUICK_ADD_EVENT_POPUP                                    =
                                                                    By.id("UIQuickAddEventPopupWindow");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_NAME                                   =
                                                                     By.xpath("//*[@id='UIQuickAddEvent']//*[@id='eventName']");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_NOTE                                   =
                                                                     By.xpath("//*[@id='UIQuickAddEvent']//*[@id='description']");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_CALENDAR                               =
                                                                         By.xpath("//*[@id='UIQuickAddEvent']//*[@name='calendar']");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_CATEGORY                               =
                                                                         By.xpath("//*[@id='UIQuickAddEvent']//*[@name='category']");

  public static final By              ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY                              =
                                                                          By.xpath("//*[@id='UIQuickAddEvent']//*[@name='allDay']");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_FROM_DATE                              =
                                                                          By.xpath("//*[@id='UIQuickAddEvent']//*[@name='from']");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_TO_DATE                                =
                                                                        By.xpath("//*[@id='UIQuickAddEvent']//*[@name='to']");

  public static final String          ELEMENT_QUICK_INPUT_EVENT_FROM_DATE_VALUE                        =
                                                                                "//*[@id='UIQuickAddEvent']//*[@name='from'][contains(@value,'$value')]";

  public static final String          ELEMENT_QUICK_INPUT_EVENT_TO_DATE_VALUE                          =
                                                                              "//*[@id='UIQuickAddEvent']//*[@name='to'][contains(@value,'$value')]";

  public static final By              ELEMENT_QUICK_INPUT_EVENT_FROM_TIME                              =
                                                                          By.xpath("//*[@id='UIQuickAddEvent']//input[@id='fromTime']");

  public static final String          ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_VALUE                        =
                                                                                "//*[@id='UIQuickAddEvent']//input[@id='fromTime'][contains(@value,'$value')]";

  public static final By              ELEMENT_QUICK_INPUT_EVENT_TO_TIME                                =
                                                                        By.xpath("//*[@id='UIQuickAddEvent']//input[@id='toTime']");

  public static final String          ELEMENT_QUICK_INPUT_EVENT_TO_TIME_VALUE                          =
                                                                              "//*[@id='UIQuickAddEvent']//input[@id='toTime'][contains(@value,'$value')]";

  public static final By              ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT                        =
                                                                                By.xpath("//*[@id='UIQuickAddEvent']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT                          =
                                                                              By.xpath("//*[@id='UIQuickAddEvent']//*[@id='toTime']/..//*[@class='UIComboboxInput']");

  public static final String          ELEMENT_QUICK_EVENT_SELECT_TO_TIME                               =
                                                                         "//*[@id='UIQuickAddEvent']//*[@id='toTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final String          ELEMENT_QUICK_EVENT_SELECT_FROM_TIME                             =
                                                                           "//*[@id='UIQuickAddEvent']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final SelenideElement ELEMENT_BUTTON_EVENT_SAVE                                        =
                               $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[3]/div/button[1]"));

  public static final String          ELEMENT_ITEM_QUICK_EVENT_CATEGORY_OPTION                         =
                                                                               "//*[@id='UIQuickAddEventPopupWindow']//*[@name='category']/*[text()='$category']";

  public static final String          ELEMENT_EVENT_TITLE                                              =
                                                          ".//*[@id='UIWeekViewGrid']//*[contains(@class,'eventContainer') and text()='${name}']";

  public static final String          ELEMENT_EVENT_INPUT_EVENT_TIME_COMBOBOX                          =
                                                                              ".//*[@id='eventDetail']//input[@class='UIComboboxInput' and @value='${time}']";

  public static final By              ELEMENT_EVENT_TASK_LIST_HOUR_FROM_DATE_VALUE                     =
                                                                                   By.xpath("(.//*[@id='UIQuickAddEvent']//*[contains(@options,\"['00:00','00:30','01:00','01:30','02:00',"
                                                                                       + "'02:30','03:00','03:30','04:00','04:30','05:00','05:30','06:00','06:30','07:00',"
                                                                                       + "'07:30','08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00',"
                                                                                       + "'12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00',"
                                                                                       + "'17:30','18:00','18:30','19:00','19:30','20:00','20:30','21:00','21:30','22:00',"
                                                                                       + "'22:30','23:00','23:30','23:59']\")])[1]");

  public static final By              ELEMENT_EVENT_TASK_LIST_HOUR_TO_DATE_VALUE                       =
                                                                                 By.xpath("(.//*[@id='UIQuickAddEvent']//*[contains(@options,\"['00:00','00:30','01:00','01:30','02:00',"
                                                                                     + "'02:30','03:00','03:30','04:00','04:30','05:00','05:30','06:00','06:30','07:00',"
                                                                                     + "'07:30','08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00',"
                                                                                     + "'12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00',"
                                                                                     + "'17:30','18:00','18:30','19:00','19:30','20:00','20:30','21:00','21:30','22:00',"
                                                                                     + "'22:30','23:00','23:30','23:59']\")])[2]");

  // Add EVENT Form (more details )
  public static final By              ELEMENT_ADD_EDIT_EVENT_POPUP_FORM                                =
                                                                        By.xpath("//*[@id='UIEventForm']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_NAME                                      =
                                                                  By.xpath("//*[@id='UIEventForm']//*[@name='eventName']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_NOTE                                      =
                                                                  By.xpath("//*[@id='UIEventForm']//*[@id='description']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_LOCATION                                  =
                                                                      By.xpath("//*[@id='UIEventForm']//*[@id='place']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_CALENDAR                                  =
                                                                      By.xpath("//*[@id='UIEventForm']//*[@name='calendar']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_CATEGORY                                  =
                                                                      By.xpath("//*[@id='UIEventForm']//*[@name='category']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_PRIORITY                                  =
                                                                      By.xpath("//*[@id='UIEventForm']//*[@name='priority']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_ALLDAY                                    =
                                                                    By.xpath("//*[@id='UIEventForm']//*[@name='allDay']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_FROM_DATE                                 =
                                                                       By.xpath("//*[@id='UIEventForm']//*[@name='from']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_TO_DATE                                   =
                                                                     By.xpath("//*[@id='UIEventForm']//*[@name='to']");

  public static final String          ELEMENT_ADD_EDIT_EVENT_FROM_DATE_VALUE                           =
                                                                             "//*[@id='UIEventForm']//*[@name='from'][contains(@value,'$value')]";

  public static final String          ELEMENT_ADD_EDIT_EVENT_TO_DATE_VALUE                             =
                                                                           "//*[@id='UIEventForm']//*[@name='to'][contains(@value,'$value')]";

  public static final By              ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME                           =
                                                                             By.xpath("//*[@id='UIEventForm']//*[@name='fromTime']");

  public static final By              ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME                             =
                                                                           By.xpath("//*[@id='UIEventForm']//*[@name='toTime']");

  public static final String          ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME_VALUE                     =
                                                                                   "//*[@id='UIEventForm']//input[@id='fromTime'][contains(@value,'$time')]";

  public static final String          ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME_VALUE                       =
                                                                                 "//*[@id='UIEventForm']//input[@id='fromTime'][contains(@value,'$time')]";

  public static final By              ELEMENT_ADD_EDIT_EVENT_FROM_TIME_INPUT                           =
                                                                             By.xpath("//*[@id='UIEventForm']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_TO_TIME_INPUT                             =
                                                                           By.xpath("//*[@id='UIEventForm']//*[@id='toTime']/..//*[@class='UIComboboxInput']");

  public static final String          ELEMENT_ADD_EDIT_EVENT_SELECT_FROM_TIME                          =
                                                                              "//*[@id='UIEventForm']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final String          ELEMENT_ADD_EDIT_EVENT_SELECT_TO_TIME                            =
                                                                            "//*[@id='UIEventForm']//*[@id='toTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final By              ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX                           = By.id("isRepeat");

  public static final By              ELEMENT_BUTTON_EVENT_MORE_DETAILS                                =
                                                                        By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='More Details']");

  public static final By              ELEMENT_BUTTON_EVENT_QUICK_CANCEL                                =
                                                                        By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='Cancel']");

  public static final By              ELEMENT_BUTTON_EVENT_CANCEL_DETAILS                              =
                                                                          By.xpath("//*[ @id='UIEventForm']//*[text()='Cancel']");

  public static final SelenideElement ELEMENT_BUTTON_EVENT_SAVE_DETAILS                                =
                                                                        $(byXpath("//*[@id=\"UIEventForm\"]/div[4]/button[1]"));

  public static final String          ELEMENT_ATTACH_FILE_NAME                                         =
                                                               "//*[@data-original-title='$fileName']";

  public static final By              ELEMENT_EVENT_FILE_INPUT                                         =
                                                               By.xpath("//*[@id='upload']//*[@name='file']");

  public static final By              ELEMENT_SELECT_FILE_BUTTON                                       =
                                                                 By.xpath("//*[@class='uploadButton']/*[@class='btn']");

  public static final By              ELEMENT_EVENT_REMINDER_TAB                                       =
                                                                 By.xpath("//*[text()='Reminders']");

  public static final By              ELEMENT_EVENT_PARTICIPANTS_TAB                                   =
                                                                     By.xpath("//*[text()='Participants']");

  public static final By              ELEMENT_EVENT_SCHEDULE_TAB                                       =
                                                                 By.xpath("//*[text()='Schedule']");

  public static final By              ELEMENT_EVENT_DETAILS_TAB                                        =
                                                                By.xpath("//*[text()='Details']");

  // Warning message
  public static final String          ELEMENT_CREATE_EVENT_TASK_SPECIAL_CHARATERS_MESSAGE              =
                                                                                          ".//*[contains(@class,'warningIcon')][contains(text(),\"Event summary does not contain ${characters}.\")]";

  public static final By              ELEMENT_CREATE_EVENT_TASK_TIME                                   =
                                                                     By.xpath(".//*[contains(text(),'To date must be later than From date.')]");

  // Attach file form
  public static final By              ELEMENT_ATTACH_SAVE_BUTTON                                       =
                                                                 By.xpath("//form[@id='UIAttachFileForm']//*[text()='Save']");

  public static final By              ELEMENT_ATTACH_LABEL_FIELD                                       =
                                                                 By.xpath(".//*[@id='eventDetail']//div[@class='control-label' and text()='Files:']");

  public static final By              ELEMENT_EVENT_ADD_ATTACHMENT                                     =
                                                                   By.xpath("//button[contains(@onclick,'AddAttachment')]");

  public static final String          ELEMENT_EVENT_ATTACHMENT                                         =
                                                               "//*[@id='UIEventForm']/..//a[@data-original-title='${file}']";

  public static final By              ELEMENT_ATTACHMENT_SAVE_BUTTON                                   =
                                                                     By.xpath("//*[@id='UIAttachFileForm']//*[text()='Save']");

  public static final By              ELEMENT_ATTACHMENT_CANCEL_BUTTON                                 =
                                                                       By.xpath("//*[@id='UIAttachFileForm']//*[text()='Cancel']");

  public static final String          ELEMENT_ATTACHMENT_FORM_FILE_NAME                                =
                                                                        "//*[@class='fileNameLabel' and text()='$fileName']";

  public static final String          ELEMENT_ATTACHMENT_DELETE_BTN                                    =
                                                                    "//*[@data-original-title='$fileName']/following-sibling::*[2]";

  public static final By              ELEMENT_ATTACH_FORM                                              =
                                                          By.xpath(".//*[contains(@class,'UIAttachFileForm')]");

  public static final By              ELEMENT_ATTACHMENT_FORM_SELECT_FILE                              =
                                                                          By.xpath(".//*[@id='upload']//label[text()='Select File']");

  public static final By              ELEMENT_ATTACHMENT_FORM_NO_FILE                                  =
                                                                      By.xpath(".//*[@id='upload']//label[@class='noFile']");

  // Schedule tab
  public static final By              ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_SCHEDULE_TAB                  =
                                                                                      By.xpath("//*[@id='UIEventForm']//*[@class='uiIconCalInviteUser uiIconLightGray']");

  public static final String          ELEMENT_USER_CHECKBOX_FULLNAME                                   =
                                                                     "//*[contains(text(),'${user}')]/../..//*[@type='checkbox']";

  public static final String          ELEMENT_USER_CHECKBOX_USERNAME                                   = "//*[@name='${user}']";

  public static final String          ELEMENT_SCHEDULE_BUSY_TIME                                       =
                                                                 "//*[@id='${user}']/../../../../..//td[${index}]";

  public static final String          ELEMENT_SCHEDULE_TIME                                            =
                                                            "//*[@id='RowContainerDay']/../../../..//tr[2]//td[${index}]";

  public static final By              ELEMENT_SCHEDULE_NEXT_DAY                                        =
                                                                By.xpath("//*[@title='Next Day' or @data-original-title='Next Day']");

  public static final By              ELEMENT_SCHEDULE_PREVIOUS_DAY                                    =
                                                                    By.xpath("//*[@title='Previous Day' or @data-original-title='Previous Day']");

  public static final String          ELEMENT_SCHEDULE_DRAG                                            =
                                                            "//td[${index}]//span[@data-original-title=\"Drag here to change your event's start and end times\" or @title=\"Drag here to change your event's start and end times\"]";

  public static final String          ELEMENT_SCHEDULE_SELECTED_DATE                                   =
                                                                     "//*[@id='RowContainerDay' and @datevalue='$date']";

  public static final String          ELEMENT_SCHEDULE_TOOLTIP_PARTICIPANTS                            =
                                                                            ".//*[@id='RowContainerDay']//tr[2]/td[${index}]/span[contains(@rel,'tooltip')][contains(@data-original-title,\"Drag here to change your event's start and end times\")]";

  // Participant tab
  public static final By              ELEMENT_PRIVACY_PUBLIC_CHECKBOX                                  =
                                                                      By.xpath("//*[@value='public']");

  public static final By              ELEMENT_PRIVACY_PRIVATE_CHECKBOX                                 =
                                                                       By.xpath("//*[@value='private']");

  public static final By              ELEMENT_AVAILABLE_CHECKBOX                                       =
                                                                 By.xpath("//*[@value='available']");

  public static final By              ELEMENT_BUSY_CHECKBOX                                            =
                                                            By.xpath("//*[@value='busy']");

  public static final By              ELEMENT_OUTSIDE_CHECKBOX                                         =
                                                               By.xpath("//*[@value='outside']");

  public static final By              ELEMENT_SEND_INVITATION_NEVER_CHECKBOX                           =
                                                                             By.xpath("//*[@value='never']");

  public static final By              ELEMENT_SEND_INVITATION_ALWAYS_CHECKBOX                          =
                                                                              By.xpath("//*[@value='always']");

  public static final By              ELEMENT_SEND_INVITATION_ASK_CHECKBOX                             =
                                                                           By.xpath("//*[@value='ask']");

  public static final By              ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_PARTICIPANT_TAB               =
                                                                                         By.xpath("//*[@class='uiFormGrid']//*[@class='uiIconPlus uiIconLightGray']");

  public static final By              ELEMENT_PICK_USER_PARTICIPANTS_TAB                               =
                                                                         By.xpath(".//*[@id='uiInvitationUser']/*[contains(@class,'uiIconUser')]");

  public static final By              ELEMENT_INVITATION_PARTICITPANT_USER                             =
                                                                           By.xpath("//*[@id='eventShare-tab']//*[@data-original-title='Add Participant' or @title='Add Participant']");

  public static final By              ELEMENT_INVITATION_PARTICIPANT_TEXTBOX                           = By.id("participant");

  public static final By              ELEMENT_INVITATION_PARTICITPANT_MSG                              = By.id("invitation-msg");

  public static final By              ELEMENT_INVITATION_SELECT_USER_BUTTON                            =
                                                                            By.id("uiInvitationUser");

  public static final By              ELEMETN_INVITATION_SAVE_BUTTON                                   =
                                                                     By.xpath("//*[@id='UIInvitationContainer']//*[text()='Save']");

  public static final By              ELEMETN_INVITATION_CANCEL_BUTTON                                 =
                                                                       By.xpath("//*[@id='UIInvitationContainer']//*[text()='Cancel']");

  public static final String          ELEMENT_PARTICIPANT_SEND_INVITATION_OPTION_CHECKED               =
                                                                                         ".//*[@id='eventShare']//input[@value='$option' and @checked='checked']";

  public static final By              ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE                          =
                                                                              By.xpath(".//*[@id='UIConfirmation']//*[contains(text(),'Would you like to send updates to all guests?')]");

  public static final By              ELMEMENT_CONFIRM_SEND_INVITATION_YES_BTN                         =
                                                                               By.xpath(".//*[@id='UIConfirmation']//*[contains(@class,'btn')][contains(text(),'Yes')]");

  public static final By              ELMEMENT_CONFIRM_SEND_INVITATION_NO_BTN                          =
                                                                              By.xpath(".//*[@id='UIConfirmation']//*[contains(@class,'btn')][contains(text(),'No')]");

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_USER                             =
                                                                           ".//*[@id='UIParticipantList']//tr//*[contains(text(),'$fullName')]";

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_REFUSED                          =
                                                                              ".//*[@id='UIParticipantList']//*[contains(text(),'$fullName')]/../..//*[contains(text(),'No')]";

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_MAYBE                            =
                                                                            ".//*[@id='UIParticipantList']//*[contains(text(),'$fullName')]/../..//*[contains(text(),'Maybe')]";

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_YES                              =
                                                                          ".//*[@id='UIParticipantList']//*[contains(text(),'$fullName')]/../..//*[contains(text(),'Yes')]";

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_REMOVE_BTN                       =
                                                                                 ".//*[@id='UIParticipantList']//*[contains(text(),'$fullName')]/../..//*[contains(@class,'uiIconDelete')]";

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_INVALID_USER_MESSAGE             =
                                                                                           ".//*[contains(@class,'warningIcon')][contains(text(),\"'$user' is not a valid participant.\")]";

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_INVALID_EMAIL_MESSAGE            =
                                                                                            ".//*[contains(@class,'warningIcon')][contains(text(),\"'$email' is not a valid email.\")]";

  // Reminder tab
  public static final By              ELEMENT_REMINDER_TAB                                             =
                                                           By.xpath(".//*[contains(@data-target,'#eventReminder-tab')]");

  public static final By              ELEMENT_REMINDER_BY_POPUP                                        = By.id("popupReminder");

  public static final By              ELEMENT_REMINDER_BY_MAIL                                         = By.id("mailReminder");

  public static final By              ELEMENT_REMINDER_DROP_BOX                                        =
                                                                By.xpath(".//*[contains(@name,'mailReminderTime')]");

  public static final By              ELEMENT_REMINDER_ADDMORE_ICON                                    =
                                                                    By.xpath("//*[@class='reminderByEmail']//*[contains(@class,'uiIconPlus')]");

  /* Recurring event form */
  public static final By              ELEMENT_RECURRING_FORM                                           =
                                                             By.id("UIRepeatEventForm");

  public static final By              ELEMENT_RECURRING_TYPE_SELECT_BOX                                =
                                                                        By.xpath("//*[@name='repeatType']");

  public static final By              ELEMENT_INTERVAL_SELECT_BOX                                      =
                                                                  By.xpath("//*[@name='interval']");

  public static final By              ELEMENT_END_AFTER_NUMBER                                         = By.id("endAfterNumber");

  public static final By              ELEMENT_NEVER_END_RECURRING_EVENT                                = By.id("endNever");

  public static final By              ELEMENT_AFTER_END_RECURRING_EVENT                                = By.id("endAfter");

  public static final By              ELEMENT_BY_THIS_DATE_END_RECURRING_EVENT                         = By.id("endByDate");

  public static final By              ELEMENT_DATE_TIME_PICKER                                         =
                                                               By.xpath("//*[contains(@id, 'DateTimePicker')]");

  public static final By              ELEMENT_IS_REPEAT_CHECKBOX                                       = By.id("isRepeat");

  public static final By              ELEMENT_IS_REPEAT_CHECKBOX_CHECKED                               =
                                                                         By.xpath(".//*[@class='repeatSummary'][contains(text(),' Daily, 5 times')]");

  public static final By              ELEMENT_SAVE_EVENT_OCCURRING                                     =
                                                                   By.xpath("//*[@id='UIRepeatEventForm']//*[contains(text(),'Save')]");

  public static final By              ELEMENT_RECURRING_SAVE_BTN                                       =
                                                                 By.xpath(".//*[@id='UIRepeatEventForm']//button[1]");

  public static final By              ELEMENT_RECURRING_CANCEL_BTN                                     =
                                                                   By.xpath(".//*[@id='UIRepeatEventForm']//button[2]");

  public static final By              ELEMENT_EDIT_RECURRING_EVENT_FORM_SAVE_BTN                       =
                                                                                 By.xpath(".//*[@id='UIConfirmFormUpdate']//button[1]");

  public static final By              ELEMENT_RECURRING_REPEAT_BTN                                     =
                                                                   By.xpath(".//*[@id='eventDetail']//*[contains(@class,'uiIconEdit')]");

  public static final String          ELEMENT_REPEAT_ON                                                =
                                                        ".//*[@id='$day'][@checked='']";

  public static final String          ELEMENT_SELECT_BOX_VALUE_SELECTED                                =
                                                                        ".//*[@id='UIRepeatEventForm']//option[@value='$name' and @selected='selected']";

  public static final By              ELEMENT_NEVER_END_RECURRING_CHECKED                              =
                                                                          By.xpath(".//*[@id='endNever'][@checked='']");

  public static final By              ELEMENT_REPEAT_BY_DAY_OF_WEEK                                    =
                                                                    By.xpath(".//*[@id='UIRepeatEventForm']//input[@value='monthlyByDay']");

  public static final By              ELEMENT_REPEAT_BY_DAY_OF_MONTH                                   =
                                                                     By.xpath(".//*[@id='UIRepeatEventForm']//input[@value='monthlyByMonthDay']");

  /* Delete recurring event form */
  public static final By              ELEMENT_DELETE_RECURRING_EVENT_FORM                              =
                                                                          By.xpath("//*[@class='uiConfirmForm']");

  public static final By              ELEMENT_EDIT_DELETE_ONE_EVENT                                    =
                                                                    By.xpath("//*[@value='save_one']");

  public static final By              ELEMENT_EDIT_DELETE_FOLLOWING_EVENT                              =
                                                                          By.xpath("//*[@value='save_follow']");

  public static final By              ELEMENT_EDIT_DELETE_ALL_EVENT                                    =
                                                                    By.xpath("//*[@value='save_all']");

  public static final By              ELEMENT_CONFIRM_DELETE_BUTTON                                    =
                                                                    By.xpath("//*[@class='uiConfirmForm']//button[1]");

  public static final By              ELEMENT_CONFIRM_CANCEL_BUTTON                                    =
                                                                    By.xpath("//*[@class='uiConfirmForm']//button[2]");

  public static final String          ELEMENT_CONFIRM_DELETE_MESSAGE                                   =
                                                                     "Would you like to delete only this event, all events in the series, or this and all following events in the series?";

  public static final By              ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT                      =
                                                                                  By.xpath("//*[@class='media-body']");

  public static final By              ELEMENT_CONFIRM_EDIT_RECURRING_EVENT_CANCEL_BTN                  =
                                                                                      By.xpath("//*[@id='UIConfirmFormUpdate']//button[2]");

  /* Delete recurring event */
  public static final By              ELEMENT_CONFIRM_EDIT_BUTTON                                      =
                                                                  By.xpath("//*[@id='UIConfirmFormUpdate']//button[1]");

  public static final By              ELEMENT_CONFIRM_EDIT_RECURRING_FORM                              =
                                                                          By.xpath(".//*[@class='confirmRadio']");

  public static final String          ELEMENT_CONFIRM_EDIT_MESSAGE                                     =
                                                                   "Would you like to change only this event, all events in the series, or this and all following events in the series?";

  /* Content recurring */
  public static final By              ELEMENT_TITLE_RECURRING_EVENT                                    =
                                                                    By.xpath("//*[@class='popover-content']/*[@class='title clearfix']/*[@class='text']");

  public static final By              ELEMENT_DATE_TIME_RECURRING_EVENT                                =
                                                                        By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalClockMini']/../../*[@class='text']");

  public static final By              ELEMENT_RECURRING_TEXT_RECURRING_EVENT                           =
                                                                             By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalRecurring']/../../*[@class='text']");

  public static final By              ELEMENT_EDITED_RECURRING_TEXT_RECURRING_EVENT                    =
                                                                                    By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalEditRecurring']/../../*[@class='text']");

  public static final By              ELEMENT_DESCRIPTION_EVENT                                        =
                                                                By.xpath("//*[@class='popover-content']/*[@class='description']");

  // Popover contain
  public static final By              ELEMENT_EVENT_POPOVER_TITLE_ELEMENT                              =
                                                                          By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'title')]");

  public static final By              ELEMENT_EVENT_POPOVER_TIME_ELEMENT                               =
                                                                         By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'time')]");

  public static final By              ELEMENT_EVENT_POPOVER_DESCRIPTION_ELEMENT                        =
                                                                                By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'description')]");

  public static final By              ELEMENT_EVENT_POPOVER_LOCATION_ELEMENT                           =
                                                                             By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'location')]");

  public static final String          ELEMENT_EVENT_POPOVER_TITLE_INFO                                 =
                                                                       "//*[contains(@class,'popover-content')]//*[contains(@class,'title')]//*[contains(text(),'$info')]";

  public static final String          ELEMENT_EVENT_POPOVER_TIME_INFO                                  =
                                                                      "//*[contains(@class,'popover-content')]//*[contains(@class,'time')]//*[contains(text(),'$info')]";

  public static final String          ELEMENT_EVENT_POPOVER_DESCRIPTION_INFO                           =
                                                                             "//*[contains(@class,'popover-content')]//*[contains(@class,'description') and contains(text(),'$info')]";

  /***************************************
   * TASK MANAGEMANT
   ********************************************************************************************/
  // Add Quick Task Form
  public static final By              ELEMENT_QUICK_ADD_TASK_POPUP                                     =
                                                                   By.id("UIQuickAddTaskPopupWindow");

  public static final By              ELEMENT_QUICK_INPUT_TASK_NAME                                    =
                                                                    By.xpath("//*[@id='UIQuickAddTask']//*[@id='eventName']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_NOTE                                    =
                                                                    By.xpath("//*[@id='UIQuickAddTask']//*[@id='description']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_CALENDAR                                =
                                                                        By.xpath("//*[@id='UIQuickAddTask']//*[@name='calendar']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_CATEGORY                                =
                                                                        By.xpath("//*[@id='UIQuickAddTask']//*[@name='category']");

  public static final By              ELEMENT_QUICK_CHECKBOX_TASK_ALLDAY                               =
                                                                         By.xpath("//*[@id='UIQuickAddTask']//*[@name='allDay']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_FROM_DATE                               =
                                                                         By.xpath("//*[@id='UIQuickAddTask']//*[@name='from']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_TO_DATE                                 =
                                                                       By.xpath("//*[@id='UIQuickAddTask']//*[@name='to']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_FROM_TIME                               =
                                                                         By.xpath("//*[@id='UIQuickAddTask']//*[@name='fromTime']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_TO_TIME                                 =
                                                                       By.xpath("//*[@id='UIQuickAddTask']//*[@name='toTime']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_FROM_TIME_INPUT                         =
                                                                               By.xpath("//*[@id='UIQuickAddTask']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");

  public static final By              ELEMENT_QUICK_INPUT_TASK_TO_TIME_INPUT                           =
                                                                             By.xpath("//*[@id='UIQuickAddTask']//*[@id='toTime']/..//*[@class='UIComboboxInput']");

  public static final String          ELEMENT_QUICK_TASK_SELECT_TO_TIME                                =
                                                                        "//*[@id='UIQuickAddTask']//*[@id='toTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final String          ELEMENT_QUICK_TASK_SELECT_FROM_TIME                              =
                                                                          "//*[@id='UIQuickAddTask']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final By              ELEMENT_ADD_EDIT_TASK_LOCATION                                   =
                                                                     By.xpath("//*[@id='UITaskForm']//*[@id='place']");

  public static final By              ELEMENT_BUTTON_TASK_SAVE                                         =
                                                               By.xpath("//*[@id='UIQuickAddTaskPopupWindow']//*[text()='Save']");

  public static final By              ELEMENT_BUTTON_TASK_QUICK_CANCEL                                 =
                                                                       By.xpath("//*[@id='UIQuickAddTaskPopupWindow']//*[text()='Cancel']");

  public static final String          ELEMENT_QUICK_INPUT_TASK_FROM_DATE_VALUE                         =
                                                                               "//*[@id='UIQuickAddTask']//*[@name='from'][contains(@value,'$value')]";

  public static final String          ELEMENT_QUICK_INPUT_TASK_FROM_TIME_VALUE                         =
                                                                               "//*[@id='UIQuickAddTask']//input[@id='fromTime'][contains(@value,'$value')]";

  public static final String          ELEMENT_QUICK_INPUT_TASK_TO_DATE_VALUE                           =
                                                                             "//*[@id='UIQuickAddEvent']//*[@name='to'][contains(@value,'$value')]";

  public static final By              ELEMENT_TASK_LIST_HOUR_FROM_DATE_VALUE                           =
                                                                             By.xpath("(.//*[@id='UIQuickAddTask']//*[contains(@options,\"['00:00','00:30','01:00','01:30','02:00',"
                                                                                 + "'02:30','03:00','03:30','04:00','04:30','05:00','05:30','06:00','06:30','07:00',"
                                                                                 + "'07:30','08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00',"
                                                                                 + "'12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00',"
                                                                                 + "'17:30','18:00','18:30','19:00','19:30','20:00','20:30','21:00','21:30','22:00',"
                                                                                 + "'22:30','23:00','23:30','23:59']\")])[1]");

  public static final By              ELEMENT_TASK_LIST_HOUR_TO_DATE_VALUE                             =
                                                                           By.xpath("(.//*[@id='UIQuickAddTask']//*[contains(@options,\"['00:00','00:30','01:00','01:30','02:00',"
                                                                               + "'02:30','03:00','03:30','04:00','04:30','05:00','05:30','06:00','06:30','07:00',"
                                                                               + "'07:30','08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00',"
                                                                               + "'12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00',"
                                                                               + "'17:30','18:00','18:30','19:00','19:30','20:00','20:30','21:00','21:30','22:00',"
                                                                               + "'22:30','23:00','23:30','23:59']\")])[2]");

  // Add Task Form (more details )
  public static final By              ELEMENT_ADD_EDIT_TASK_NAME                                       =
                                                                 By.xpath("//*[@id='UITaskForm']//*[@name='eventName']");

  public static final By              ELEMENT_ADD_EDIT_TASK_NOTE                                       =
                                                                 By.xpath("//*[@id='UITaskForm']//*[@id='description']");

  public static final By              ELEMENT_ADD_EDIT_TASK_CALENDAR                                   =
                                                                     By.xpath("//*[@id='UITaskForm']//*[@name='calendar']");

  public static final By              ELEMENT_ADD_EDIT_TASK_CATEGORY                                   =
                                                                     By.xpath("//*[@id='UITaskForm']//*[@name='category']");

  public static final By              ELEMENT_ADD_EDIT_TASK_ALLDAY                                     =
                                                                   By.xpath("//*[@id='UITaskForm']//*[@name='allDay']");

  public static final By              ELEMENT_ADD_EDIT_TASK_FROM_DATE                                  =
                                                                      By.xpath("//*[@id='UITaskForm']//*[@name='from']");

  public static final By              ELEMENT_ADD_EDIT_TASK_TO_DATE                                    =
                                                                    By.xpath("//*[@id='UITaskForm']//*[@name='to']");

  public static final By              ELEMENT_ADD_EDIT_INPUT_TASK_FROM_TIME                            =
                                                                            By.xpath("//*[@id='UITaskForm']//*[@name='fromTime']");

  public static final By              ELEMENT_ADD_EDIT_INPUT_TASK_TO_TIME                              =
                                                                          By.xpath("//*[@id='UITaskForm']//*[@name='toTime']");

  public static final By              ELEMENT_ADD_EDIT_TASK_FROM_TIME_INPUT                            =
                                                                            By.xpath("//*[@id='UITaskForm']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");

  public static final By              ELEMENT_ADD_EDIT_TASK_TO_TIME_INPUT                              =
                                                                          By.xpath("//*[@id='UITaskForm']//*[@id='toTime']/..//*[@class='UIComboboxInput']");

  public static final String          ELEMENT_ADD_EDIT_TASK_SELECT_FROM_TIME                           =
                                                                             "//*[@id='UITaskForm']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final String          ELEMENT_ADD_EDIT_TASK_SELECT_TO_TIME                             =
                                                                           "//*[@id='UITaskForm']//*[@id='toTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

  public static final By              ELEMENT_BUTTON_TASK_MORE_DETAILS                                 =
                                                                       By.xpath("//*[@id='UIQuickAddTaskPopupWindow']//*[text()='More Details']");

  public static final By              ELEMENT_BUTTON_TASK_SAVE_DETAILS                                 =
                                                                       By.xpath("//*[@id='UITaskForm']//*[text()='Save']");

  public static final By              ELEMENT_TASK_FILE_INPUT                                          =
                                                              By.xpath("//*[@id='upload']//*[@name='file']");

  public static final By              ELEMENT_ADD_EDIT_TASK_STATUS                                     =
                                                                   By.xpath("//*[@id='UITaskForm']//*[@name='status']");

  public static final String          ELEMENT_ADD_EDIT_TASK_TO_DATE_VALUE                              =
                                                                          "//*[@id='UITaskForm']//*[@name='to'][contains(@value,'$value')]";

  public static final String          ELEMENT_ADD_EDIT_INPUT_TASK_FROM_TIME_VALUE                      =
                                                                                  "//*[@id='UITaskForm']//input[@id='fromTime'][contains(@value,'$time')]";

  public static final String          ELEMENT_ADD_EDIT_INPUT_TASK_TO_TIME_VALUE                        =
                                                                                "//*[@id='UITaskForm']//input[@id='fromTime'][contains(@value,'$time')]";

  public static final By              ELEMENT_BUTTON_TASK_CANCEL_DETAILS                               =
                                                                         By.xpath("//*[ @id='UITaskForm']//*[text()='Cancel']");

  public static final By              ELEMENT_ADD_EDIT_TASK_DELIGATION_ADDUSER_ICON                    =
                                                                                    By.xpath("//*[@name='delegation']/..//*[contains(@class,'uiIconPlus')]");

  // Attach file form
  public static final By              ELEMENT_SELECT_FILE                                              =
                                                          By.xpath("//*[@class='uploadButton']/*[@class='btn']");

  public static final By              ELEMENT_SELECT_MORE_FILE                                         =
                                                               By.className("moreFiles");

  public static final By              ELEMENT_TASK_ADD_ATTACHMENT                                      =
                                                                  By.xpath("//button[contains(@onclick,'AddAttachment')]");

  public static final By              ELEMENT_DISABLE_TASK_ADD_ATTACHMENT                              =
                                                                          By.xpath("//button[@class='btn disableIcon' and @data-original-title='You cannot attach more than 10 files']");

  public static final String          ELEMENT_TASK_ATTACHMENT                                          =
                                                              "//*[@id='UITaskForm']/..//a[@data-original-title='${file}']";

  public static final By              ELEMENT_UPLOAD_PROGRESS_BAR                                      =
                                                                  By.xpath(".//*[contains(@class,'progressBarFrame clearfix')]");

  public static final By              ELEMENT_SELECT_NO_FILE                                           =
                                                             By.xpath("//*[@class='uploadButton']/*[@class='noFile' and text()='No file selected']");

  // Popover contain
  public static final By              ELEMENT_TASK_POPOVER_TITLE_ELEMENT                               =
                                                                         By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'title')]");

  public static final By              ELEMENT_TASK_POPOVER_TIME_ELEMENT                                =
                                                                        By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'time')]");

  public static final By              ELEMENT_TASK_POPOVER_DESCRIPTION_ELEMENT                         =
                                                                               By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'description')]");

  public static final By              ELEMENT_TASK_POPOVER_LOCATION_ELEMENT                            =
                                                                            By.xpath("//*[contains(@class,'popover-content')]//*[@contains(@class,'location')]");

  public static final String          ELEMENT_TASK_POPOVER_TITLE_INFO                                  =
                                                                      "//*[contains(@class,'popover-content')]//*[contains(@class,'title')]//*[contains(text(),'$info')]";

  public static final String          ELEMENT_TASK_POPOVER_TIME_INFO                                   =
                                                                     "//*[contains(@class,'popover-content')]//*[contains(@class,'time')]//*[contains(text(),'$info')]";

  public static final String          ELEMENT_TASK_POPOVER_DESCRIPTION_INFO                            =
                                                                            "//*[contains(@class,'popover-content')]//*[contains(@class,'description') and contains(text(),'$info')]";

  public static final String          ELEMENT_TASK_POPOVER_LOCATION_INFO                               =
                                                                         "//*[contains(@class,'popover-content')]//*[contains(@class,'location')]//*[contains(text(),'$info')]";

  /***************************************
   * EMAIL NOTIFICATION
   ********************************************************************************************/
  // Invitation email
  public static final String          ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL                           =
                                                                             ".//b[contains(text(),'[Invitation] $eventTask')]";

  public static final String          ELEMENT_GMAIL_EMAIL_DETAILS                                      =
                                                                  ".//h3//*[contains(@name,'$email')]";

  public static final String          ELEMENT_GMAIL_EMAIL_ICS_FILE                                     =
                                                                   ".//span[contains(text(),'$file')]";

  public static final String          ELEMENT_GMAIL_EMAIL_DETAIL_LINK                                  =
                                                                      "//*[contains(@target,'_blank')][contains(text(),'$link')]";

  public static final String          ELEMENT_REFUSE_INVITATION_MESSAGE                                =
                                                                        "//*[contains(text(),'You have refused invitation from $user')]";

  public static final String          ELEMENT_MAYBE_INVITATION_MESSAGE                                 =
                                                                       "//*[contains(text(),'You have answered invitation from $user: Maybe')]";

  public static final String          ELEMENT_ACCEPT_INVITATION_MESSAGE                                =
                                                                        "//*[contains(text(),'You have accepted invitation from $user')]";

  // Reminder email
  public static final String          ELEMENT_GMAIL_CONTENT_REMINDER_TASK                              =
                                                                          "//span[contains(text(),'Task Summary: $task')]";

  public static final String          ELEMENT_GMAIL_CONTENT_REMINDER_EVENT                             =
                                                                           "//span[contains(text(),'Event Summary: $event')]";

  public static final SelenideElement ELEMENT_LIST_CATEGORY                                            =
                                                            $(byId("UIEventCategoryList"));

  public static final By              ELEMENT_DELETE_CATEGORY                                          =
                                                              byClassName("uiIconDelete ");

  public static final SelenideElement ELEMENT_CALENDAR_LIST_BUTTON                                     =
                                                                   $(byId("UIActionBar")).find(byXpath("//*[@id=\"UIActionBar\"]/div[3]/ul[1]/li[4]/a"));

  public static final SelenideElement ELEMENT_CALENDAR_WEEK_BUTTON                                     =
                                                                   $(byId("UIActionBar")).find(byXpath("//*[@id=\"UIActionBar\"]/div[3]/ul[1]/li[2]"));

  public static final SelenideElement ELEMENT_CALENDAR_DAY_BUTTON                                      =
                                                                  $(byId("UIActionBar")).find(byXpath("//*[@id=\"UIActionBar\"]/div[3]/ul[1]/li[1]"));

  public static final By              ELEMENT_CALENDAR_ICON_SETTINGS_OF_CALENDAR                       =
                                                                                 byClassName("uiIconCalSettingMini");

  public static final SelenideElement ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON                               =
                                                                         $(byId("UIListContainer")).find(byClassName("uiIconMiniArrowRight"));

  public static final SelenideElement ELEMENT_POUPUP_LIST_EVENT                                        =
                                                              $(byClassName("spliterResizableListArea"));

  public static final SelenideElement ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW                             = $(byId("UIWeekView"));

  public static final By ELEMENT_CHECK_CALENDAR=byClassName("iconCheckBox");

  public static final By ELEMENT_UNCHECK_CALENDAR=byClassName("iconUnCheckBox");
  public static final SelenideElement ELEMENT_ICON_ADD_PARTICIPANT=$(byXpath("//*[@id=\"eventShare\"]/table/tbody/tr[3]/td[2]/a/i"));

  public static final SelenideElement ELEMENT_LIST_CALENDAR=$(byId("UICalendars"));

  public static final SelenideElement ELEMENT_TASK_DETAIL =$(byClassName("titleList"));

  public static final SelenideElement ELEMENT_EVENT_DRAWER=  $(byId("ExoEventForm"));
  public static final SelenideElement ELEMENT_EVENT_DRAWER_TITLE=  $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[2]/form/div[1]/div/input"));
  public static final SelenideElement ELEMENT_EVENT_CATEGORY=  $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[2]/form/div[1]/div/span/select"));
  public static final SelenideElement ELEMENT_EVENT_LOCATION=  $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[2]/form/div[5]/div[2]/input"));
  public static final SelenideElement ELEMENT_EVENT_DESCRIPTION=  $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[2]/form/div[10]/div[2]/textarea"));
  public static final SelenideElement ELEMENT_EVENT_CANCEL_BUTTON=  $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[3]/div/button[2]"));
  public static final SelenideElement ELEMENT_EVENT_SAVE_BUTTON=  $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[3]/div/button[1]"));
  public static final SelenideElement ELEMENT_EVENT_CLEAR_BUTTON=  $(byXpath("//*[@id=\"ExoEventForm\"]/div[1]/div[3]/div/button[3]"));

}
