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
package org.exoplatform.platform.qa.ui.selenium.locator.calendar;

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

  // Calendar left panel-->Calendar list
  public static final By              ELEMENT_CALENDAR_PANEL                                           =
                                                             By.xpath("//div[@class='uiBox uiCalendars']");

  public static final By              ELEMENT_CALENDAR_MENU_ACTIONS_ICON                               =
                                                                         By.xpath("//*[@class='uiIconCalSimplePlus uiIconLightGray']");

  public static final String          ELEMENT_SHARED_CALENDAR_LIST_ITEM                                =
                                                                        "//*[@id='UICalendars']//*[text()='Shared Calendars']/..//*[text()='$calendar']";

  public static final String          ELEMENT_CALENDAR_LIST_ITEM                                       =
                                                                 "//*[@id='UICalendars']//*[contains(text(),'$calendar')]";
  // VIEW GENERAL-->Header bar
  public static final By              ELEMENT_NEXT_BUTTON_ANY_VIEW                                     =
                                                                   By.xpath("//*[@class='title']//*[contains(@class,'uiIconMiniArrowRight')]");

  public static final By              ELEMENT_PREVIOUS_BUTTON_ANY_VIEW                                 =
                                                                       By.xpath("//*[@class='title']//*[contains(@class,'uiIconMiniArrowLeft')]");

  public static final By              ELEMENT_CATEGORY_OPTION                                          =
                                                              By.xpath("//*[@name='eventCategories']");

  public static final By              ELEMENT_EVENT_TASK_DELETE_BUTTON                                 =
                                                                       By.xpath(".//*[contains(@data-original-title,'Delete')]//*[contains(@class,'uiIconDelete')]");
  // LIST VIEW-->Header bar
  public static final By              ELEMENT_CALENDAR_LIST_TAB_SELECT_ALL_CHECKBOX                    =
                                                                                    By.xpath(".//*[@id='UIListUsers']//*[contains(@data-original-title,'Select All')]//input");
  // LIST VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_LIST_VIEW                                     =
                                                                   "//*[@id='UIListView']//*[@class='uiListViewRow']//*[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW                   =
                                                                                     "//*[@id='UIListView']//*[contains(text(),'$name')]/../..//td[5][contains(text(),'$date')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_LIST_VIEW                              =
                                                                          ".//*[@id='UIPreview']//*[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_CHECKBOX_LIST_VIEW                            =
                                                                            ".//*[contains(text(),'$name')]/../..//input";

  public static final By              ELMENT_CALENDAR_TAB_LIST_EMPTY                                   =
                                                                     By.xpath(".//*[@id='UIListUsers']//*[contains(@class,'empty')]");

  public static final String          ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL                        =
                                                                                "(.//*[@id='UIPreview']//*[contains(@class,'columnLeft')]//*[@class='thumbnail'])[$number]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_CONTAINER              =
                                                                                          ".//*[@class='thumbnailContainer'][$number]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_VIEW                   =
                                                                                     ".//*[@class='thumbnailContainer'][1]//*[@class='view']";

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

  public static final String          ELEMENT_EVENT_TASK_DATE_TIME_VALUE                               =
                                                                         ".//*[@id='UIDayViewGrid']//td[contains(@startfull,'$time')]";
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

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY                      =
                                                                                  "//*[@id='UIWeekViewGrid']//*[contains(@startfull,'$date')]";
  // MONTH VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_MONTH_VIEW                                    =
                                                                    "//*[@id='UIMonthView']//span[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW                        =

                                                                    "//*[@id='UIMonthView']//*[@class='eventMonthContent']//*[@class='rowContainerDay']//*[contains(text(),'$name')]/preceding::*[contains(@starttimefull,'$date')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE                   =
                                                                                     "//*[@id='UIMonthView']//*[@class='eventMonthContent']//*[@class='moreEventContainer']//*[contains(@starttimefull,'$date')]//span[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON              =
                                                                                          "//*[@id='UIMonthView']//*[contains(@starttimefull,'$date')]/..//*[@class='moreEvent' and not(contains(@style, 'display'))]/*[@class='moreEventLabel']";

  public static final String          ELEMENT_CELL_TO_MONTH_WORKING_PANEL                              =
                                                                          "//td[contains(@starttimefull,'$date')]";

  public static final String          ELEMENT_EVENT_TASK_DAY_BY_INDEX_MONTH_VIEW                       =
                                                                                 ".//*[@cindex='$col' and @rindex='$row']";

  // WORK WEEK VIEW-->Grid
  public static final String          ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY                        =
                                                                                "//*[@id='UIWeekView']//*[@class='eventAllDay']//*[contains(@class,'eventContainer')]//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY                        =
                                                                                "//*[@id='UIWeekView']//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY            =
                                                                                            "//*[@id='UIWeekViewGrid']//*[contains(@startfull,'$date')]//div[contains(text(),'$name')]";

  public static final String          ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY            =
                                                                                            "//*[@id='UIWeekViewGridAllDay']//*[contains(@starttimefull,'$date')]//div[contains(text(),'$name')]";

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

  public static final String          ELEMENT_LIST_EDIT_EVENT_BUTTON                                   =
                                                                     ".//*[@id='UIEventCategoryList']//span[contains(text(),'${categoryName}')]/parent::td/parent::tr//a[@data-original-title='Edit']/i[@class='uiIconEdit uiIconLightGray']";

  public static final By              ELEMENT_EDIT_EVENT_CATEGORY_BUTTON_UPDATE                        =
                                                                                By.id("btnEventCategoryFormContainer");

  public static final By              ELEMENT_TOOLBAR_MINI_CALENDAR                                    =
                                                                    By.xpath("//*[@class='weekDays']");

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

  public static final By              ELEMENT_SETTING_FORM_SAVE_BUTTON                                 =
                                                                       By.xpath(".//*[@id='UICalendarSettingForm']//button[1]");

  public static final By              ELEMENT_SETTING_FORM_CANCEL_BUTTON                               =
                                                                         By.xpath(".//*[@id='UICalendarSettingForm']//button[2]");

  // Calendar setting - Displayed calendar form

  public static final String          ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED                            =
                                                                            ".//*[@id='UICalendarPopupWindow']//*[contains(text(),'$calendar')]/..//*[contains(@class,'iconCheckBox')]";

  public static final String          ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED                          =
                                                                              ".//*[@id='UICalendarPopupWindow']//*[contains(text(),'$calendar')]/..//*[contains(@class,'iconUnCheckBox')]";

  public static final String          ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX                =
                                                                                        "//*[@id='UIPopupCalendarSettingContainer']//*[text()='Group Calendars']/../..//*[@class='calendarName' and contains(text(),'$name')]/../..//*[@class='iconCheckBox checkbox' or @class='checkbox iconCheckBox']";

  // Calendar setting - Feed tab

  public static final By              ELEMENT_FEED_TAB_SAVE_BUTTON                                     =
                                                                   By.xpath("//*[@id='feedTab']//*[text()='Add']");

  public static final By              ELEMENT_FEED_EDIT_FEED_FORM                                      =
                                                                  By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']");

  public static final By              ELEMENT_FEED_EDIT_FEED_SAVE_FORM                                 =
                                                                       By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[contains(@onclick,'Save')]");

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

  public static final By              ELEMENT_CALENDAR_ADD_GROUP_BUTTON                                =
                                                                        By.xpath("//*[@class='addGroup']//*[text()='Add']");

  public static final SelenideElement ELEMENT_CALENDAR_ADD_SAVE_BUTTON                                 =
                                                                       $(byXpath("//*[@id=\"UICalendarForm\"]/div[4]/button[1]"));

  public static final By              ELEMENT_CALENDAR_ADD_CANCEL_BUTTON                               =
                                                                         By.xpath("//*[@id='UICalendarForm']//*[text()='Cancel']");

  public static final String          ELEMENT_CALENDAR_COLOR_SELECT                                    =
                                                                    "//*[@id='UICalendarForm']//a[contains(@class,'${color}')]";

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

  public static final String          ELEMENT_PREVIEW_TASK_EVENT_NAME                                  =
                                                                      "//*[@id='UIEventPreview']//*[text()='$name']";

  public static final By              ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM                            =
                                                                            By.xpath("//*[@id='UIEventPreview']//*[text()='Close']");

  // Confirm popup Task/Event
  public static final By              ELEMENT_CONFIRM_POPUP_OK                                         =
                                                               By.xpath(".//*[@id='UIConfirmation']//*[contains(text(),'Yes')]");

  public static final By              ELEMENT_CONFIRM_POPUP_DELETE                                     =
                                                                   By.xpath(".//*[contains(@class,'uiConfirmForm')]//button[1]");

  // ADD QUICK TASK FORM
  public static final By              ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM                             = By.id("UIQuickAddTask");

  // Delete task/event warning
  public static final String          ELEMENT_CONFIRM_DELETE_TASK_MSG                                  =
                                                                      "Are you sure you want to delete this task?";

  public static final String          ELEMENT_CONFIRM_DELETE_EVENT_MSG                                 =
                                                                       "Are you sure you want to delete this event?";

  // quick and advance search of Task/Event
  public static final By              ELEMENT_QUICK_SEARCH_INPUT                                       = By.id("value");

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
                                                                    By.xpath("//div[@id='ExoCalendarEventForm']/div[1]");

  public static final By              ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY                              =
                                                                          By.xpath("//*[@id=\"allday\"]");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_FROM_DATE                              =
                                                                          By.xpath("(//*[@id=\"ExoCalendarEventForm\"]//following::input[@class='date'])[1]");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_TO_DATE                                =
                                                                        By.xpath("(//*[@id=\"ExoCalendarEventForm\"]//following::input[@class='date'])[2]");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_FROM_TIME                              =
                                                                          By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='cbb_input'])[1]");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_TO_TIME                                =
                                                                        By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='cbb_input'])[2]");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT                        =
                                                                                By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='cbb_input'])[1]");

  public static final By              ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT                          =
                                                                              By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='cbb_input'])[2]");

  public static final SelenideElement ELEMENT_BUTTON_EVENT_SAVE                                        =
                               $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]"));

  public static final By              ELEMENT_ADD_EDIT_EVENT_NAME                                      =
                                                                  By.xpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input");

  public static final By              ELEMENT_ADD_EDIT_EVENT_NOTE                                      =
                                                                  By.xpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[6]/div[2]/following::textarea");

  public static final By              ELEMENT_ADD_EDIT_EVENT_LOCATION                                  =
                                                                      By.xpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div/following::input[@class='location']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_CATEGORY                                  =
                                                                      By.xpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select");

  public static final By              ELEMENT_ADD_EDIT_EVENT_ALLDAY                                    =
                                                                    By.xpath("//*[@id=\"allday\"]");

  public static final By              ELEMENT_ADD_EDIT_EVENT_FROM_DATE                                 =
                                                                       By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='date'])[1]");

  public static final By              ELEMENT_ADD_EDIT_EVENT_TO_DATE                                   =
                                                                     By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='date'])[2]");

  public static final By              ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME                           =
                                                                             By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='cbb_input'])[1]");

  public static final By              ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME                             =
                                                                           By.xpath("(//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form//div[@class='control-label']/following::input[@class='cbb_input'])[2]");

  public static final By              ELEMENT_ADD_EDIT_EVENT_FROM_TIME_INPUT                           =
                                                                             By.xpath("//*[@id='UIEventForm']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_TO_TIME_INPUT                             =
                                                                           By.xpath("//*[@id='UIEventForm']//*[@id='toTime']/..//*[@class='UIComboboxInput']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX                           = By.xpath("//div[@class='repeat']//div[@class='uiSwitchBtn']");

  public static final By              ELEMENT_ADD_EDIT_EVENT_REMINDER_CHECKBOX                           = By.xpath("//div[@class='reminder pull-left']//div[@class='uiSwitchBtn']");

  public static final By              ELEMENT_BUTTON_EVENT_MORE_DETAILS                                =
                                                                        By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='More Details']");

  public static final By              ELEMENT_BUTTON_EVENT_CANCEL_DETAILS                              =
                                                                          By.xpath("//*[@class=\"uiAction\"]//*[text()='Cancel']");

  public static final String          ELEMENT_ATTACH_FILE_NAME                                         =
                                                               "//*[@data-original-title='$fileName']";

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

  public static final By              ELEMENT_EVENT_ADD_ATTACHMENT                                     =
                                                                   By.xpath("//*[@id=\"ExoEventForm\"]/div[1]/div[2]/form/div[2]/div[7]/div[2]/div/div[1]/label");


  public static final By              ELEMENT_ATTACHMENT_SAVE_BUTTON                                   =
                                                                     By.xpath("//*[@id='UIAttachFileForm']//*[text()='Save']");


  public static final String          ELEMENT_ATTACHMENT_FORM_FILE_NAME                                =
                                                                        "//*[@class='fileNameLabel' and text()='$fileName']";

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

  public static final By              ELEMETN_INVITATION_SAVE_BUTTON                                   =
                                                                     By.xpath("//*[@id='UIInvitationContainer']//*[text()='Save']");

  public static final String          ELEMENT_INVITATION_PARTICIPANTS_REMOVE_BTN                       =
                                                                                 ".//*[@id='UIParticipantList']//*[contains(text(),'$fullName')]/../..//*[contains(@class,'uiIconDelete')]";

  // Reminder tab

  public static final By              ELEMENT_REMINDER_DROP_BOX                                        =
                                                                By.xpath(".//*[contains(@name,'mailReminderTime')]");

  public static final By              ELEMENT_REMINDER_ADDMORE_ICON                                    =
                                                                    By.xpath("//*[@class='reminderByEmail']//*[contains(@class,'uiIconPlus')]");

  /* Recurring event form */
  public static final By              ELEMENT_RECURRING_FORM                                           =
                                                             By.id("UIRepeatEventForm");

  public static final By              ELEMENT_NEVER_END_RECURRING_EVENT                                = By.id("endNever");

  public static final By              ELEMENT_BY_THIS_DATE_END_RECURRING_EVENT                         = By.id("endByDate");

  public static final By              ELEMENT_DATE_TIME_PICKER                                         =
                                                               By.xpath("//*[contains(@id, 'DateTimePicker')]");

  public static final By              ELEMENT_IS_REPEAT_CHECKBOX                                       = By.id("isRepeat");

  public static final By              ELEMENT_SAVE_EVENT_OCCURRING                                     =
                                                                   By.xpath("//div[@class='uiForm uiRepeatEventForm']//*[contains(text(),'Save')]");

  public static final By              ELEMENT_RECURRING_SAVE_BTN                                       =
                                                                 By.xpath(".//*[@id='UIRepeatEventForm']//button[1]");

  public static final By              ELEMENT_RECURRING_CANCEL_BTN                                     =
                                                                   By.xpath(".//*[@id='UIRepeatEventForm']//button[2]");

  public static final By              ELEMENT_EDIT_RECURRING_EVENT_FORM_SAVE_BTN                       =
                                                                                 By.xpath(".//*[@id='UIConfirmFormUpdate']//button[1]");

  public static final By              ELEMENT_RECURRING_REPEAT_BTN                                     =
                                                                   By.xpath(".//*[@id='eventDetail']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_REPEAT_BY_DAY_OF_WEEK                                    =
                                                                    By.xpath(".//*[@id='UIRepeatEventForm']//input[@value='monthlyByDay']");

  public static final By              ELEMENT_REPEAT_BY_DAY_OF_MONTH                                   =
                                                                     By.xpath(".//*[@id='UIRepeatEventForm']//input[@value='monthlyByMonthDay']");

  /* Delete recurring event form */
  public static final By              ELEMENT_DELETE_RECURRING_EVENT_FORM                              =
                                                                          By.xpath("//*[@class='uiConfirmForm']");

  public static final By              ELEMENT_DELETE_ONE_EVENT                                    =
                                                                    By.xpath("//*[@value='save_one']");

  public static final By              ELEMENT_EDIT_ONE_EVENT                                    =
                                                                    By.xpath("//*[@value='ONE']");

  public static final By              ELEMENT_EDIT_RECURRING_EVENT_SAVE                                    =
                                                                    By.xpath("(//div[@class=\"uiAction uiActionBorder\"]/button[contains(text(),\"Save\")])[2]");

  public static final By              ELEMENT_DELETE_FOLLOWING_EVENT                              =
                                                                          By.xpath("//*[@value='save_follow']");

  public static final By              ELEMENT_EDIT_FOLLOWING_EVENT                              =
                                                                          By.xpath("//*[@value='FOLLOWING']");

  public static final By              ELEMENT_DELETE_ALL_EVENT                                    =
                                                                    By.xpath("//*[@value='save_all']");

  public static final By              ELEMENT_EDIT_ALL_EVENT                                    =
                                                                    By.xpath("//*[@value='ALL']");

  public static final By              ELEMENT_CONFIRM_DELETE_BUTTON                                    =
                                                                    By.xpath("//*[@class='uiConfirmForm']//button[1]");

  public static final By              ELEMENT_CONFIRM_CANCEL_BUTTON                                    =
                                                                    By.xpath("//*[@class='uiConfirmForm']//button[2]");

  public static final String          ELEMENT_CONFIRM_DELETE_MESSAGE                                   =
                                                                     "Would you like to delete only this event, all events in the series, or this and all following events in the series?";

  public static final By              ELEMENT_CONFIRM_DELETE_RECURRING_EVENT                      =
                                                                                  By.xpath("(//*[@class='media-body'])[2]");

  public static final By              ELEMENT_CONFIRM_EDIT_RECURRING_EVENT                      =
                                                                                    By.xpath("//*[@class='media-body']");

  public static final By              ELEMENT_CONFIRM_EDIT_RECURRING_EVENT_CANCEL_BTN                  =
                                                                                      By.xpath("//*[@id='UIConfirmFormUpdate']//button[2]");

  /* Delete recurring event */
  public static final By              ELEMENT_CONFIRM_EDIT_BUTTON                                      =
                                                                  By.xpath("(//div[@class='uiPopup']//button[@type='button'])[5]");

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

  public static final By              ELEMENT_ADD_EDIT_TASK_STATUS                                     =
                                                                   By.xpath("//*[@id='UITaskForm']//*[@name='status']");

  public static final By              ELEMENT_BUTTON_TASK_CANCEL_DETAILS                               =
                                                                         By.xpath("//*[ @id='UITaskForm']//*[text()='Cancel']");

  public static final By              ELEMENT_ADD_EDIT_TASK_DELIGATION_ADDUSER_ICON                    =
                                                                                    By.xpath("//*[@name='delegation']/..//*[contains(@class,'uiIconPlus')]");

  // Attach file form

  public static final By              ELEMENT_TASK_ADD_ATTACHMENT                                      =
                                                                  By.xpath("//button[contains(@onclick,'AddAttachment')]");

  public static final String          ELEMENT_TASK_ATTACHMENT                                          =
                                                              "//*[@id='UITaskForm']/..//a[@data-original-title='${file}']";

  /***************************************
   * EMAIL NOTIFICATION
   ********************************************************************************************/
  // Invitation email
  public static final String          ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL                           =
                                                                             ".//b[contains(text(),'[Invitation] $eventTask')]";

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

  public static final SelenideElement ELEMENT_CALENDAR_TODAY_FIRST_TIME                             = $(byXpath("(//td[@class='tdLine today none'])[1]"));

  public static final By ELEMENT_CHECK_CALENDAR=byClassName("iconCheckBox");

  public static final By ELEMENT_UNCHECK_CALENDAR=byClassName("iconUnCheckBox");

  public static final SelenideElement ELEMENT_LIST_CALENDAR=$(byId("UICalendars"));

  public static final SelenideElement ELEMENT_TASK_DETAIL =$(byClassName("titleList"));

  public static final SelenideElement ELEMENT_EVENT_DRAWER=  $(byId("ExoCalendarEventForm"));
  public static final SelenideElement ELEMENT_EVENT_DRAWER_TITLE=  $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input"));
  public static final SelenideElement ELEMENT_EVENT_CATEGORY=  $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/span/select"));
  public static final SelenideElement ELEMENT_EVENT_LOCATION=  $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[2]/div[4]/div[2]/input"));
  public static final SelenideElement ELEMENT_EVENT_DESCRIPTION=  $(byXpath("//textarea[@placeholder='Add a description']"));
  public static final SelenideElement ELEMENT_EVENT_CANCEL_BUTTON=  $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[2]"));
  public static final SelenideElement ELEMENT_EVENT_SAVE_BUTTON=  $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[1]"));
  public static final SelenideElement ELEMENT_EVENT_CLEAR_BUTTON=  $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[3]/div/button[3]"));
  public static final SelenideElement ELEMENT_EVENT_TITLE_DRAWER=   $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[2]/form/div[1]/div[2]/input"));
  public static final SelenideElement ELEMENT_EVENT_SELECT_FROM=   $(By.xpath("(//div[@class=\"periodDate pull-left\"]/input)[1]"));
  public static final SelenideElement ELEMENT_EVENT_SELECT_TO=   $(By.xpath("(//div[@class=\"periodDate pull-left\"]/input)[2]"));
  public static final SelenideElement ELEMENT_EVENT_CURRENT_DAY=   $(By.xpath("//td/a/following::a[@class=\"highLight today\"]"));
  public static final SelenideElement ELEMENT_EVENT_NEXT_DAY=   $(By.xpath("(//td/a[@class=\"highLight today\"]/following::td/a[@href=\"#SelectDate\"])[1]"));
  public static final SelenideElement ELEMENT_CLOSE_BUTTON_DRAWER=   $(byXpath("//*[@id=\"ExoCalendarEventForm\"]/div[1]/div[1]/a"));
  public static final SelenideElement ELEMENT_EVENT_ADD_PARTICIPANT=   $(byXpath("//input[@placeholder='Participants']/following::div[2]/input"));
  public static final SelenideElement ELEMENT_CANCEL_CLOSE_ADD_EVENT=   $(byXpath("//div[@id=\"confirmClose\"]/div/button[2]"));


  public static final SelenideElement ELEMENT_REPEAT_PREFERRENCE_IMPROVE_POPUP=$(byClassName("recurring-popup"));
  public static final SelenideElement ELEMENT_REMINDER_POPUP=   $(byXpath("//div[@class='reminder pull-left']//div[@class='uiPopup']"));

  public static final SelenideElement ELEMENT_SAVE_REMINDER_BUTTON=$(byXpath("//*[@id='eventReminder']/div[3]/button[1]"));
  public static final SelenideElement ELEMENT_CANCEL_REMINDER_BUTTON=$(byXpath("//*[@id='eventReminder']/div[3]/button[2]"));
  public static final SelenideElement ELEMENT_REPEAT_LABEL=$(byXpath("//div[@class='iphoneCheckbox']/following::a[text()='Repeat']"));
  public static final SelenideElement ELEMENT_FIND_TIME_BUTTON= $(byClassName("findtime"));
  public static final SelenideElement ELEMENT_FIND_TIME_POPUP= $(byClassName("findtime-popup"));
  public static final SelenideElement ELEMENT_CHECK_TIME_ICON=  $(byId("checkTime"));

  public static final SelenideElement ELEMENT_FIND_TIME_SAVE_BUTTON= $(byXpath("//*[@id=\"eventAttender-tab\"]/div[2]/button[2]"));



}
