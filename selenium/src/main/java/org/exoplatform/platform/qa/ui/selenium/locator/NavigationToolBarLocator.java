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

import org.openqa.selenium.By;

public final class NavigationToolBarLocator {

  // Tool bar
  public static final By ELEMENT_TOOLBAR_ADMINISTRATION =
          By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//i");

  public static final By ELEMENT_TOOLBAR_THEMELIGHT =
          By.xpath("//*[@class='UIContainer UIToolbarContainer UIToolbarContainerLight']");

  public static final By ELEMENT_UPLOAD_FILE_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");

  public static final By ELEMENT_HELP_TOOLBAR = By.className("uiIconPLF24x24Help");

  public static final By ELEMENT_TOOLBAR_NOTIFICATION_LIST =
          By.xpath(".//*[@id='UINotificationPopoverToolbarPortlet']//*[contains(@class,'uiIconPLF24x24Bell')]");

  public static final String ELEMENT_TOOLBAR_NOTIFICATION_NUMEBER =
          ".//*[contains(@class,'badgeNotification')][contains(text(),'${num}')]";

  // Notificaiton list
  public static final By ELEMENT_NOTIFICATION_DROPDOWN = By.cssSelector("#NotificationPopup");

  public static final String
          ELEMENT_NOTIFICATION_LIST_COMMENT_ACTIVITY =
          ".//*[@id='NotificationPopup']//*[contains(@class,'user-name')][contains(text(),'${user}')]/../..//*[contains(.,'${des}')]/..//*[contains(.,'${act}')]";

  public static final String
          ELEMENT_NOTIFICATION_LIST_CONNECT_USER =
          ".//*[@id='NotificationPopup']//*[contains(@class,'user-name')][contains(text(),'${user}')]/../..//*[contains(.,' ${des}')]";

  public static final By ELEMENT_NOTIFICATION_LIST_CONNECT_USER_STATUS =
          By.xpath(".//*[@id='NotificationPopup']//*[contains(text(),'Accept')]/../../..//*[contains(@class,'status')]");

  public static final String
          ELEMENT_NOTIFICATION_LIST_INVITATION_SPACE_STATUS =
          ".//*[@id='NotificationPopup']//*[contains(@class,'text-bold')][contains(text(),'${space}')]/..";

  public static final String
          ELEMENT_NOTIFICATION_LIST_USER =
          "//*[@id='NotificationPopup']/../..//*[contains(@class,'user-name text-bold')][contains(text(),'${user}')]/..";

  public static final By ELEMENT_NOTIFICATION_REMOVE_ICON =
          By.xpath(".//*[@id='NotificationPopup']//i[contains(@class,'uiIconClose uiIconLightGray')]");

  public static final By ELEMENT_INTRANET_NOTIFICATION_BELL = By.xpath("//*[@class='uiIconPLF24x24Bell']");

  public static final By ELEMENT_INTRANET_NOTIFICATION_NEAR_USER_AVATAR =
          By.xpath(".//*[contains(@class,'NotificationPopoverPortletTDContainer')]/..//following-sibling::*//img[@alt='avatar']");

  public static final By ELEMENT_POSITION_OF_INTRANET_NOTIFICATION =
          By.xpath(
                  "//*[@class='UITableColumnContainer']//*[@class='UserInfoPortletTDContainer pull-left']/../*[@class='NotificationPopoverPortletTDContainer pull-left']");

  public static final By ELEMENT_DOC_EXO_OF_HOME_GETTING_STARTED =
          By.xpath(".//*[@id='newBreadcrumbs']//*[contains(text(),'Getting Started')]");

  // Intranet notification
  public static final String
          ELEMENT_BADGE_NUMBER_DISPLAY =
          "//*[contains(@class,'badgeDefault') and @style='display: inline;' and text()='${number}']";

  public static final By ELEMENT_BADGE_NUMBER_NOT_DISPLAY =
          By.xpath("//*[contains(@class,'badgeDefault') and text()='0']");

  public static final By ELEMENT_BADGE_NUMBER =
          By.xpath("//*[@class='badgeDefault badgePrimary mini badgeNotification']");

  public static final By ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION =
          By.xpath(".//*[@id='NotificationPopup']//*[contains(text(),'Mark all as read')]");

  public static final By ELEMENT_VIEW_ALL_BUTTON =
          By.xpath(".//*[@id='NotificationPopup']//a[text()='View All']");

  public static final By ELEMENT_NO_NOTIFICATIONS =
          By.xpath(".//*[@id='NotificationPopup']//*[@class='no-items' and text()='No notifications']");

  public static final String
          ELEMENT_CONNECT_NOTIFICATION_POSITION =
          "//li[${position}]//*[contains(@alt,'${fullName}')]/../..//*[contains(text(),'${fullName}')]/../..//*[contains(.,'wants to connect with you')]";

  public static final String
          ELEMENT_COMMENT_JUST_NOW_READ =
          "//*[@class='read clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";

  public static final String
          ELEMENT_COMMENT_POSITION_ONE_MINUTE_READ =
          "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";

  public static final String
          ELEMENT_COMMENT_MARK_ALL_AS_READ =
          "//*[@class='clearfix']//*[contains(@alt,'${userName}')]/../..//*[@class='status' and contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]";

  public static final String
          ELEMENT_COMMENT_JUST_NOW_UNREAD =
          "//*[@class='unread clearfix']//[contains(@alt,'${userName}')]/../..//*[@class='status' and contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";

  public static final String
          ELEMENT_COMMENT_POSITION_ONE_MINUTE_UNREAD =
          "//*[@class='unread clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_JUST_NOW_READ =
          "//*[@class='read clearfix']//*[contains(@alt,'${userName}')]/../..//*[.contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_ONE_MINUTE_READ =
          "//*[@class='read clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_MARK_ALL_AS_READ =
          "//*[@class='clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_JUST_NOW_UNREAD =
          "//*[@class='unread clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_ONE_MINUTE_UNREAD =
          "//*[@class='unread clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";

  public static final String
          ELEMENT_COMMENT_ONE_MINUTE_DELETE =
          "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]/../../../..//*[@class='uiIconClose uiIconLightGray']";

  public static final String
          ELEMENT_COMMENT_JUST_NOW_DELETE =
          "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]/../../../..//*[@class='uiIconClose uiIconLightGray']";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_ONE_MINUTE_DELETE =
          "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]/../../../..//*[@class='uiIconClose uiIconLightGray']";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_JUST_NOW_DELETE =
          "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]/../../../..//*[@class='uiIconClose uiIconLightGray']";

  public static final String
          ELEMENT_LIKE_NOTIFICATION_DELETE =
          "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime']/../../../..//*[@class='uiIconClose uiIconLightGray']";

  public static final String
          ELEMENT_CONNECT_NOTIFICATION_DELETE =
          "//*[contains(@alt,'${fullName}')]/../..//*[ contains(text(),'${fullName}')]/../..//*[contains(.,'wants to connect with you')]/../../../..//*[@class='uiIconClose uiIconLightGray']";

  public static final String
          ELEMENT_NEW_USER_NOTIFICATION_DELETE =
          "//*[contains(@alt,'${userName}')]/../..//*[contains(text(),'${userName}')]/../..//*[contains(.,'has joined eXo')]/../../../..//*[@class='uiIconClose uiIconLightGray']";

  public static final By ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON = By.xpath(".//input[@type='file']");

  // administration Menu
  // users
  public static final By ELEMENT_ADMINISTRATION_USERS =
          By.xpath("//*[contains(@href,'g/:platform:administrators/administration/newStaff') and text()='Community']");

  public static final By ELEMENT_ADMINISTRATION_PORTAL_ADD_USERS =
          By.xpath("//*[contains(@href,'g/:platform:administrators/administration/newStaff') and text()='Add Users']");

  public static final By ELEMENT_GROUP_AND_ROLE_LINK =
          By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//a[contains(@href,'management')]");

  // administration-->Portal
  public static final By ELEMENT_ADMINISTRATION_PORTAL = By.xpath("//*[text()='Portal']");

  public static final By ELEMENT_ADMINISTRATION_PORTAL_SITES = By.xpath("//*[text()='Sites']");

  public static final By ELEMENT_ADMINISTRATION_PORTAL_PAGES = By.xpath("//*[text()='Pages']");

  public static final By ELEMENT_ADMINISTRATION_PORTAL_EMAIL_NOTIFICATIONS =
          By.xpath(".//*[contains(@id,'UISetupPlatformToolBarPortlet')]//*[contains(@href,'notification')]");

  public static final By ELEMENT_ADMINISTRATION_PORTAL_BRANDING = By.xpath("//*[text()='Branding']");

  public static final By ELEMENT_ADMINISTRATION_PORTAL_IDE = By.xpath("//*[text()='IDE']");

  public static final By ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES = By.xpath("//*[text()='Group Sites']");

  // Administation-->Content
  public static final By ELEMENT_LINK_CONTENT_ADMIN = By.xpath("//*[text()='Content administration']");

  public static final By ELEMENT_MENU_CONTENT_LINK =
          By.xpath("//li[@class='dropdown-submenu']/a[text()='Content']");

  public static final By ELEMENT_MENU_SITE_EXPLORER = By.linkText("Sites Explorer");

  public static final By ELEMENT_SITE_EXPLORER_HOME = By.className("uiIconEcmsHome");

  public static final By ELEMENT_NEW_FOLDER_LINK =
          By.xpath("//*[@class='actionIcon']//*[contains(@class, 'uiIconEcmsAddFolder')]");

  public static final By ELEMENT_SEARCH_LINK =
          By.xpath("//*[@class='dropdown-menu']//*[text()='Search']");

  // administration panel
  public static final By ELEMENT_TOPBAR_ADMINISTRATION_BUTTON = By.xpath("//*[@class='uiIconPLF24x24Setup']");

  public static final By ELEMENT_TOPBAR_CONTENT =
          By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content')]");

  public static final By ELEMENT_CONTENT_TOPBAR_ADMINISTRATION =
          By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content administration')]");

  // Setup icon
  public static final By ELEMENT_LINK_SETUP =
          By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//*[@class='uiIconPLF24x24Setup']");

  public static final By ELEENT_LINK_APPLICATION =
          By.xpath("//*[contains(@href,'/portal/g/:platform:administrators/administration/registry')]");

  // Edit menu
  public static final By ELEMENT_LINK_EDIT = By.xpath("//*[@class='uiIconPLF24x24Edit']");

  public static final By ELEMENT_MENU_PAGE_LINK =
          By.xpath("//*[@tabindex='-1' and contains(text(),'Page')]");

  public static final By ELEMENT_MENU_SITE_LINK =
          By.xpath("//*[@tabindex='-1' and contains(text(),'Site')]");

  public static final By ELEMENT_MENU_EDIT_LAYOUT = By.xpath("//*[contains(text(),'Edit Layout')]");

  public static final By ELEMENT_MENU_LAYOUT =
          By.xpath("//*[@tabindex='-1' and contains(text(),'Site')]/..//*[contains(text(),'Layout')]");

  public static final By ELEMENT_MENU_SEO_LINK = By.xpath("//span[contains(text(), 'SEO')]");

  public static final By ELEMENT_MENU_ADD_PAGE_LINK = By.xpath("//*[contains(text(), 'Add Page')]");

  public static final By ELEMENT_EDIT_PAGE =
          By.xpath("//*[@id='UIAdminToolbarContainer']//*[@class='dropdown-submenu']//*[@href='#' and contains(text(), 'Page')]");

  public static final By ELEMENT_EDIT_PAGE_SEO =
          By.xpath("//*[@data-original-title = 'SEO Management']");

  public static final By ELEMENT_EDIT_CONTENT = By.xpath("//*[@class='quickEditUnchecked']");

  public static final By ELEMENT_EDIT_CONTENT_CHECK = By.xpath("//*[@class='quickEditChecked']");

  // Edit->site
  public static final By ELEMENT_MENU_EDIT_SITES =
          By.xpath("//*[contains(@href,'#')][contains(text(),'Site')]");

  public static final By ELEMENT_MENU_EDIT_SITES_NAV =
          By.xpath("//*[@id='UIAdminToolbarContainer']//a[contains(text(),'Navigation')]");

  public static final By ELEMENT_MENU_EDIT_SITE_LAYOUT =
          By.xpath(".//*[contains(@href,'#')][contains(text(),'Site')]/..//*[contains(text(),'Layout')]");

  public static final By ELEMENT_MENU_EDIT_ADDSITE = By.linkText("Add Site");

  public static final By ELEMENT_MENU_EDIT_CONTENT_TEXT = By.linkText("Content");

  public static final By ELEMENT_NAVIGATION_MANAGE_POPUP = By.xpath(".//*[@id='UINavigationManagement']");

  public static final By ELEMENT_ADDSITE_MANAGE_POPUP =
          By.xpath(".//*[@id='UIMaskWorkspace']//*[contains(@class,'MaskContainer')]");

  // User Menu
  public static final By ELEMENT_MY_PROFILE_LINK = By.xpath("//i[@class='uiIconPLFProfile']/..");

  public static final By ELEMENT_MY_DASHBOARD_LINK = By.xpath("//i[@class='uiIconPLFDashboard']/..");

  public static final By ELEMENT_MY_SETTINGS_LINK = By.className("uiIconSetting");

  public static final By ELEMENT_MY_CONNECTION_LINK = By.className("uiIconPLFMyConnection");

  public static final By ELEMENT_ACTIVITIES_LINK = By.className("uiIconPLFActivityStream");

  public static final By ELEMENT_TOPBAR_AVATAR = By.xpath("//*[@alt='avatar']");

  public static final By ELEMENT_AVATAR_CHANGELANGUAGE = By.xpath("//*[@class='uiIconFlags']");

  public static final By ELEMENT_MY_WIKI_LINK = By.xpath("//i[@class='uiIconWikiMyWiki']/..");

  public static final By ELEMENT_MY_NOTIFICATIONS_LINK = By.className("uiIconPLFNotifications");

  public static final By ELEMENT_ACTIVITIES_PORTLET = By.id("UIUserActivityStreamPortlet");

  // administration-->Application
  public static final By ELEMENT_ADMINISTRATION_APPLICATION = By.xpath(".//*[text()='Applications']");

  public static final By ELEMENT_ADD_TOOTLBAR =
          By.xpath("//*[@id='UICreatePlatformToolBarPortlet']//*[@class='uiIconPLF24x24Add']");

  public static final By ELEMENT_ADD_WIKI_TOOLBAR =
          By.xpath("//*[@id='UICreateList']//*[@class='uiIconWikiWiki']");

  public static final By ELEMENT_ADD_POOL_TOOLBAR =
          By.xpath("//*[@id='UICreateList']//*[@class='uiIconPoll']");

  public static final By ELEMENT_ADD_TOPIC_TOOLBAR =
          By.xpath("//*[@id='UICreateList']//*[@class='uiIconUIForms']");

  public static final By ELEMENT_ADD_EVENT_CLASS_TOOLBAR =
          By.xpath("//*[@id='UICreateList']//*[@class='uiIconPLFEventTask']");

  public static final By ELEMENT_UPLOAD_FILE_TOOLBAR =
          By.xpath("//*[@id='UICreateList']//*[@class='uiIconUpload']");

  public static final By ELEMENT_NEXT_BUTTON =
          By.xpath("//*[@id='UICreateList']//*[contains(text(),'Next')]");

  public static final By ELEMENT_SAVE_BUTTON =
          By.xpath("//*[@id='UICreateList']//*[contains(text(),'Save')]");

  // add wiki from toolbar
  public static final By ELEMENT_ADD_WIKI_SET_LOCATION =
          By.xpath("//*[@id='uiWikiSpaceSwitcher_CreateWiki']//*[@id='DisplayModesDropDown']/div");

  public static final String ELEMENT_ADD_WIKI_CHOOSE_LOCATION =
          "//*[@class='spaceChooserPopup']//*[contains(text(),'{$location}')]";

  // add poll/topic
  public static final By ELEMENT_ADD_POLL_SET_LOCATION =
          By.xpath("//*[@id='ScrollSelectlocation']//*[@class='btn dropdown-toggle']");

  public static final By ELEMENT_SELECT_FORUM_COMBOBOX =
          By.xpath(".//*[@id='uiForumFilterforumId']//div[@class='btn dropdown-toggle']");

  public static final String ELEMENT_SELECT_FORUM_NAME =
          ".//*[@id='uiForumFilterforumId']//*[contains(text(),'${forum}')]";

  // event or task
  public static final By ELEMENT_ADD_EVENT_RADIO_BUTTON =
          By.xpath("//*[@id='QuickAddEventContainer']//*[@class='radio' and @value='Event']");

  public static final By ELEMENT_ADD_TASK_RADIO_BUTTON =
          By.xpath("//*[@id='QuickAddEventContainer']//*[@class='radio' and @value='Task']");

  public static final By ELEMENT_ADD_TITLE = By.id("Title");

  public static final String ELEMENT_CHECK_NAME_UPLOADED_FILE =
          "//*[@id='ListRecords']//*[contains(text(),'{$name}')]";

  // Quick search
  public static final By ELEMENT_TOOLBAR_QUICKSEARCH = By.xpath("//*[@class='uiIconPLF24x24Search']");

  // Notifications
  // Get the content of the first notification
  public static final String
          ELEMENT_SPACE_DOCUMENTS_SHARED_NOTIFICATION =
          "//*[@id='NotificationPopup']/li[4]/ul//*[contains(text(),'${author}')]"
                  + "/../..//*[contains(text(),'has posted an activity in the')]/../..//*[contains(text(),'${spaceName}')]";

  // toolbar--> upload file
  public static final By ELEMENT_UPLOAD_FILE_TOOLBAR_PERSONNAL_DOCUMENTS =
          By.xpath("//*[@id='ListRecords']//*[contains(text(),'Personal Documents')]");

  public static final By ELEMENT_UPLOAD_FILE_GO_TO_UPLOAD =
          By.xpath("//*[@id='UIDocumentSelector']//*[@class='UIDSUploadInput']");

}
