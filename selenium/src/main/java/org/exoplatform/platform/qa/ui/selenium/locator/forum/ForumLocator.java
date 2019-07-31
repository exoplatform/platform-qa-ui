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
package org.exoplatform.platform.qa.ui.selenium.locator.forum;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class ForumLocator {

  /***************************************************
   * HOME PAGE
   ************************************************************************/
  // Action bar
  public static final By ELEMENT_ACTIONBAR_USER =
          By.xpath("//*[@class='uiIconUser uiIconLightGray']");

  public static final By ELEMENT_ACTIONBAR_SETTINGS =
          By.xpath("//*[@class='uiIconSetting uiIconLightGray']");

  public static final By ELEMENT_ACTIONBAR_ADMINISTRATION =
          By.xpath(".//*[@id='Administrations']//div[@data-toggle='dropdown']");

  public static final By ELEMENT_ACTIONBAR_BOOKMARK_MANAGER =
          By.xpath("//*[@id=\"OpenBookMark\"]/a");

  public static final By ELEMENT_ACTIONBAR_BOOKMARK_ICON =
          By.xpath(".//*[@id='UITopicDetail' or @id='UITopicContent' or @id='UICategory']//*[@class='actionIcon']/*[@class='uiIconBookmark uiIconLightGray']");

  public static final By ELEMENT_ACTIONBAR_MANAGECAT =
          By.xpath("//*[@class='uiIconForumManageCategory uiIconForumLightGray']");

  public static final By ELEMENT_ACTIONBAR_PRIVATE_MESSAGE =
          By.xpath(".//*[@id='uiRightActionBar']//a[contains(.,'Private Messages')]");

  public static final By ELEMENT_ACTIONBAR_TOPIC_TAGDELETE =
          By.xpath("//*[@id=\"UITopicDetail\"]/div[2]/div[2]/ul[1]/li/span/i");

  public static final By ELEMENT_FORUM_TOPIC_LOCK =
          By.xpath("//*[@class='uiIconLockMedium']");

  public static final By ELEMENT_FORUM_TOPIC_UNLOCK =
          By.xpath("//*[@class='uiIconUnlockMedium']");

  public static final By ELEMENT_FORUM_TOPIC_POSTLOCKED =
          By.xpath("//*[@class='uiLockIcon btn disabled']/../../../div[2]//*[@class='pull-left actionContainer']");

  public static final By ELEMENT_FORUM_TOPIC_POSTLOCKEDMESSAGE =
          By.xpath("//*[text()='You cannot reply to this topic.']");

  public static final String ELEMENT_FORUM_TOPIC_MARKAVERAGE = "//*[@data-original-title='${rate}']";

  public static final String ELEMENT_FORUM_NAVIGATION_BREADCRUMB =
          "//*[@class='breadcrumb']//*[text()='${name}']";

  public static final By ELEMENT_MY_SUBSCRIPTIONS_EMAIL_UPDATE = By.id("EmailAddress");

  // Private Message
  public static final By ELEMENT_PRIVATE_MESSAGE_COMPOSE_MESSAGE_TAB =
          By.xpath(".//*[@id='UIPrivateMessageForm']//*[contains(text(),'Compose New Message')]");

  public static final By ELEMENT_COMPOSE_NEW_MESSAGE_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");

  public static final String ELEMENT_COMPOSE_NEW_MESSAGE_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";

  public static final String ELEMENT_COMPOSE_NEW_MESSAGE_SEARCH_ICON =
          ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";

  public static final By ELEMENT_PRIVATE_MESSAGE_CANCEL =
          By.xpath(".//*[@id='UIPrivateMessageForm']//*[contains(text(),'Cancel')]");

  public static final By ELEMENT_COMPOSE_NEW_MESSAGE_USER_SELECTOR =
          By.xpath(".//*[@id='MessageTab']//*[@class='uiIconUser uiIconLightGray']");

  public static final By ELEMENT_COMPOSE_NEW_MESSAGE_CLOSE_USER_SELETOR =
          By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");

  // Manage User
  public static final By ELEMENT_FORUM_USER_LIST =
          By.xpath(".//*[@id='ManageModerator']//*[contains(@class,'uiIconUser')]");

  public static final By ELEMENT_MANAGE_USER_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='SearchUser']");

  public static final String ELEMENT_MANAGE_USER_SEARCH_ICON =
          ".//*[@id='UIModeratorManagementForm']//*[contains(@class,'uiIconSearch uiIconLightGray')]";

  public static final By ELEMENT_USER_FORM_CLOSE =
          By.xpath(".//*[@id='UIModeratorManagementForm']//*[contains(text(),'Close')]");

  // Add Category popup
  public static final By ELEMENT_ADDCATEGORY_POPUP_CATEGORY_TAB =
          By.xpath(".//*[@id='UICategoryForm']//a[text()='Category']");

  public static final By ELEMENT_ADDCATEGORY_POPUP_PERMISSION_TAB =
          By.xpath(".//*[@id='UICategoryForm']//a[text()='Permissions']");

  // Add forum popup
  public static final By ELEMENT_ADDFORUM_POPUP_ADDFORUM_TAB =
          By.xpath(".//*[@id='UIForumForm']//a[text()='Add Forum']");

  // Home page of forum
  public static final String ELEMENT_FORUM_HOME_TOPIC_TILTE =
          ".//*[@id='UICategories']//*[@class='uiIconForumTopic uiIconForumLightGray']/..//*[contains(text(),'${topic}')]";

  // Forum content
  public static final String ELEMENT_DETAIL_FORUM_CATEGORY_TITLE =
          ".//*[@id='UIForumDescription']//strong[text()='${title}']";

  public static final String ELEMENT_FORUM_TITLE_LINK =
          ".//*[contains(@id,'UIContextPopupMenu') and contains(text(),'${name}')]";

  public static final String ELEMENT_TOPIC_TITLE_LINK =
          ".//*[contains(@id,'UIContextPopupMenu') and contains(text(),'${name}')]/i";

  public static final String ELEMENT_FORUM_DETAIL_FORUM_NAME_LINK = ".//*[text()='${name}']";

  public static final By ELEMENT_FORUM_WHAT_GOING_ON =
          By.xpath("//div[contains(text(),'Going on?')]");

  public static final String ELEMENT_SELECT_FORUM_TOPIC = "//*[contains(text(),'${link}')]";

  public static final By ELEMENT_FORUM_MOREACTIONS_MOVE = By.xpath("//*[@class='uiIconMove']");

  public static final By ELEMENT_FORUM_MOREACTIONS_ADDPOLL = By.xpath("//*[text()='Add Poll']");

  public static final String ELEMENT_FORUM_MOVE_CATEGORYCOLLAPSE =
          "//*[@class='uiIconNode collapseIcon' and contains(.,'${forum}')]";

  public static final String ELEMENT_FORUM_MOVE_CATEGORYEXPAND =
          "//*[@class='uiIconNode expandIcon' and contains(.,'${forum}')]";

  public static final String ELEMENT_FORUM_MOVE_FORUM =
          "//*[@class='uiIconUIForms uiIconLightGray' ]/../..//*[contains(text(),'${forum}')]";

  // Form add poll
  public static final By ELEMENT_FORUM_ADDPOLL_QUESTION = By.xpath("//*[@id='Question']");

  public static final By ELEMENT_FORUM_ADDPOLL_OPTION0 = By.xpath("//*[@id='Option0']");

  public static final By ELEMENT_FORUM_ADDPOLL_OPTION1 = By.xpath("//*[@id='Option1']");

  public static final By ELEMENT_FORUM_ADDPOLL_CLOSE = By.xpath("//*[@id='TimeOut']");

  public static final By ELEMENT_FORUM_POLL_SUBMIT = By.xpath("//*[text()='Submit Poll']");

  public static final By ELEMENT_FORUM_ICON_EDIT = By.xpath("//*[text()='Edit']");

  public static final By ELEMENT_FORUM_POLL_DELETE =
          By.xpath("//*[@class='uiIconDelete uiIconLightGray']");

  public static final By ELEMENT_FORUM_POLL_DELETECONFIRM =
          By.xpath("//*[contains(text(),'Are you sure you want to delete this poll ?')]/../../..//*[@class='btn actionOK']");

  public static final String ELEMENT_SELECT_TOPIC = "//*[contains(text(),'{$topic}')]";

  // reply on topic
  public static final By ELEMENT_TOPIC_REPLY =
          By.xpath("//*[@class='pull-left actionContainer']//*[@class='uiPostReplyIcon btn btn-primary']");

  public static final By ELEMENT_TOPIC_REPLY_TITLE = By.xpath("//*[@id='PostTitle']");

  public static final String ELEMENT_TOPIC_PRIVATE_BUTTON =
          ".//*[contains(text(),'${post}')]/../../../.././/*[contains(@class,'btn')]//*[contains(text(),'Private')]";

  public static final By MORE_ACTIONS_TOPIC =
          By.xpath("//*[@id=\"UITopicDetail\"]/div[4]/div[1]/ul/li[3]/div");

  // administration
  public static final By ELEMENT_ACTIONBAR_ADMIN_BANIP =
          By.xpath(".//*[@id='Administrations']//a[contains(.,'Banned IPs')]");

  public static final By ELEMENT_ACTIONBAR_ADMIN_BBCODE =
          By.xpath(".//*[@id='Administrations']//a[contains(.,'BBCodes')]");

  public static final By ELEMENT_ACTIONBAR_ADMIN_IMPORT =
          By.xpath(".//*[@id='Administrations']//a[contains(.,'Import')]");

  public static final By ELEMENT_ACTIONBAR_ADMIN_EXPORT =
          By.xpath(".//*[@id='Administrations']//a[contains(.,'Export')]");

  // export category popup
  public static final By ELEMENT_EXPORTCAT_EXPORTALL = By.xpath("//*[@id='checkAll']");

  public static final String ELEMENT_EXPORTCAT_EXPORT =
          "//*[contains(text(),'${title}')]/..//*[@class='uiCheckbox']//*[@class='checkbox']";

  // add BBCODE
  public static final By ELEMENT_ADMIN_BBCODE_ADDBBCODE = By.xpath("//*[text()='Add BBCode']");

  public static final By ELEMENT_BBCODE_ADDBBCODEFORM_TAG = By.xpath("//*[@id='TagName']");

  public static final By ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT = By.xpath("//*[@id='Replacement']");

  public static final By ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION = By.xpath("//*[@id='Description']");

  public static final By ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE = By.xpath("//*[@id='Example']");

  public static final By ELEMENT_BBCODE_EDITBBCODE =
          By.xpath("//*[@id=\"UIBBCodeManagerForm\"]/div[2]/table/tbody/tr[28]/td[4]/a[1]/i");

  public static final By ELEMENT_BBCODE_DELETEBBCODE =
          By.xpath("//*[@id=\"UIBBCodeManagerFormConfirm27\"]/i");

  // Breadcumb

  public static final By ELEMENT_CATEGORY_BREADCUMB_HOME =
          By.xpath("//*[@id=\"UIBreadcumbs\"]/div[1]/ul/li[1]/a");

  public static final String ELEMENT_CATEGORY_FORUM_BREAD =
          "//*[text()='${category}']/../..//*[text()='${forum}']";

  // Contextmenu by right clicking
  public static final By ELEMENT_WATCH =
          By.xpath("//*[contains(text(),' Watch')]/../..//*[@class='uiIconWatch uiIconLightGray']");

  public static final By ELEMENT_UNWATCH =
          By.xpath("//*[contains(text(),' Unwatch')]/../..//*[@class='uiIconWatch uiIconLightGray']");

  // Message and popup inform
  public static final String MESSAGE_WATCH = "You are now watching this item.";

  public static final By ELEMENT_OK_INFOR_POPUP =
          By.xpath("//div[@class='UIPopupWindow UIDragObject uiPopup']/.//a[text()='OK']");

  public static final String MESSAGE_UNWATCH =
          "You are no longer watching this item.";

  // Bookmark

  public static final By ELEMENT_TOPIC_BOOKMARK =
          By.xpath("//*[@id=\"UITopicDetail\"]/div[4]/div[1]/ul/li[3]/div/ul/li[12]/a");

  public static final By ELEMENT_FORUM_BOOKMARK_NAME = By.className("uiShowBookMarkForm");

  public static final By ELEMENT_FORUM_BOOKMARK_DELETE =
          By.xpath("//*[@id=\"UIShowBookMarkForm\"]/div[2]/div/table/tbody/tr/td[3]/a/i");

  public static final By ELEMENT_FORUM_BOOKMARK_CLOSE_ICON =
          By.xpath("//*[@id=\"UIForumPopupWindow\"]/div[1]/a");

  // Category right click option
  public static final String ELEMENT_FORUM_CONTEXT_MENU_BOOKMARK =
          ".//a[contains(text(),'${name}')]/..//a[contains(.,'Bookmarks')]";

  // Forum/Category portlets
  public static final By ELEMENT_FORUM_PORTLET = By.id("UIForumPortlet");

  public static final String ELEMENT_PORTLET_CONTENT_LINK = ".//*[contains(text(),'${topic}')]";

  // Topic
  public static final String ELEMENT_TOPIC_LAST_REPLY =
          ".//*[contains(text(),'${reply}')]/../../../../following::div[7][@class='uiBox forumQuickReply uiCollapExpand']";

  public static final String ELEMENT_GMAIL_CONTENT_TOPIC = ".//span[contains(.,'\"{$title}\"')]";

  // Button
  public static final By ELEMENT_OK_BTN = By.xpath("//*[@class='btn actionOK']");

  public static final String ELEMENT_BBCODE_TAG_VERIFY = "//*[contains(text(),'${tag}')]";

  public static final By ELEMENT_BBCODE_USE_OPTION = By.id("UseOption']");

  public static final By ELEMENT_BBCODE_CONFIRM_DELETETAG =
          By.xpath("//*[text()='Are you sure you want to delete this BB Code ?']/../../..//*[@class='btn actionOK']");

  // Settings
  public static final By ELEMENT_FORUM_SETTINGS =
          By.xpath(".//*[@id='EditProfile']//*[contains(@class,'uiIconSetting uiIconLightGray')]");

  public static final By ELEMENT_FORUM_SETTINGS_FORUMSETTINGS =
          By.xpath("//*[text()='Forum Settings']");

  public static final By ELEMENT_FORUM_SETTINGS_MYSUSCRIB =
          By.xpath("//*[text()='My Subscriptions']");

  public static final By ELEMENT_FORUM_SETTINGS_SCREENNAME = By.xpath("//*[@id='ScreenName']");

  public static final By ELEMENT_FORUM_SETTINGS_MAXTHREADS =
          By.xpath("//*[@name='MaximumThreads']");

  public static final By ELEMENT_FORUM_SETTINGS_EMAILADRESS = By.xpath("//*[@id='EmailAddress']");

  public static final By ELEMENT_FORUM_SETTINGS_UPDATE = By.xpath("//*[text()='Update']");

  public static final By ELEMENT_FORUM_SETTINGS_SAVE = By.xpath("//*[text()='Save']");

  public static final By ELEMENT_FORUM_SETTINGS_SUBMIT = By.xpath("//*[text()='Submit']");

  public static final By ELEMENT_FORUM_USERS_FORUMSETTINGS =
          By.xpath("//*[@data-target='#ForumUserOption-tab']");

  public static final By ELEMENT_FORUM_USERS_BAN = By.xpath("//*[text()='Ban User']");

  public static final By ELEMENT_FORUM_USERS_TOPICS =
          By.xpath("//*[@id=\"UIModeratorManagementForm\"]/ul/li[4]/a");

  public static final By ELEMENT_FORUM_USERS_POSTS =
          By.xpath("//*[@id=\"UIModeratorManagementForm\"]/ul/li[5]/a");

  public static final By ELEMENT_FORUM_CLOSEBTN =
          By.xpath("//*[@class='btn' and text()='Close']");

  public static final String ELEMENT_FORUM_VERIFY_USER = "//*[text()='${user}']";

  public static final String ELEMENT_MY_SUBSCRIPTION_TITLE =
          "//*[@id='ForumUserWatches-tab']//*[contains(text(),'${link}')]";

  // forum & category
  public static final String ELEMENT_FORUM_TITLECAT = "//*[text()='${title}']";

  public static final String ELEMENT_CATEGORY_TITLE =
          ".//*[contains(@class,'textTitleCategories pull-left')]//*[contains(text(),'${title}')]";

  // add forum
  public static final By ELEMENT_FORUM_FORUM_NAME = By.xpath("//*[@id='ForumTitle']");

  // users
  public static final String ELEMENT_FORUM_USERS_EDIT =
          "//*[text()='${name}']/..//*[@class='uiIconEdit uiIconLightGray']";

  public static final SelenideElement ELEMENT_FORUM_EDIT_PROFILE =
          $(By.xpath("//*[@id=\"PermissionInfo\"]/tbody/tr/td[5]/a[1]/i"));

  public static final By ELEMENT_FORUM_USERS_POPUP_SEARCH_FIELD = By.xpath(".//*[@id='SearchUser']");

  public static final By ELEMENT_FORUM_MESSAGE =
          By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");

  // search
  public static final By ELEMENT_SEARCH_TEXTBOX = By.xpath("//*[@id='inputValue']");

  public static final By ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX = By.xpath("//*[@id='SearchValue']");

  public static final String ELEMENT_SEARCH_FORUM_POST =
          "//*[text()='Post']/../..//*[text()='${name}']";

  public static final String ELEMENT_SEARCH_FORUM_TOPIC =
          "//*[text()='Topic']/../..//*[text()='${name}']";

  public static final String ELEMENT_SEARCH_FORUM_FORUM =
          "//*[text()='Forum']/../..//*[text()='${name}']";

  public static final String ELEMENT_SEARCH_FORUM_CATEGORY =
          "//*[text()='Category']/../..//*[text()='${name}']";

  public static final By ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH =
          By.xpath("//*[text()='Advanced Search']");

  public static final By ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION = By.xpath("//*[@name='SearchType']");

  public static final By ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH =
          By.xpath("//*[@class='btn' and text()='Search']");

  public static final By ELEMENT_FILENAME_INPUT = By.id("FileName");

  public static final By ELEMENT_DELETE_ICON =
          By.xpath("//*[@class='uiIconDelete uiIconLightGray']");

  public static final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");

  public static final By ELEMENT_SAVE_IMPORT =
          By.xpath("//*[@id=\"UIImportForm\"]/div[3]/button[1]");

  public static final SelenideElement ELEMENT_BUTTON_OK_IMPORT =
          $(byClassName("UIPopupWindow")).find(byClassName("btn"));

  public static final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");
  public static final By ELEMENT_UPDATE_BUTTON = By.xpath("//*[@id=\"EditActivityButton3\"]");
  // BBcode popup
  public static final By ELEMENT_EDITSITE_SAVEBTN =
          By.xpath("//*[@class='btn' and text()='Save']");

  public static final By ELEMENT_SAVE_BBCODE =
          By.xpath("//*[@id=\"UIAddBBCodeForm\"]/div[3]/button[1]");

  public static final By ELEMENT_BBCODE_POPUP_CLOSEBTN =
          By.xpath("//*[@id=\"BBCodeManagerForm\"]/div/div[2]/button[3]");

  /***************************************************
   * FORUM MANAGEMENT
   ************************************************************************/
  // Home page of forum
  public static final String ELEMENT_FORUM_FORUM_NAME_LINK = ".//*[text()='${name}']";

  public static final String ELEMENT_FORUM_CATEGORY_TITLE =
          ".//*[@id='UIForumDescription']//strong[text()='${title}']";

  public static final By ELEMENT_FORUM_START_TOPIC_DISABLE =
          By.xpath("//*[@id='UITopicContainer']//*[@data-original-title='Forum is closed for posting.']");

  public static final By ELEMENT_FORUM_START_TOPIC_BUTTON =
          By.xpath("//*[@class='btn btn-primary pull-left']");

  // Action bar
  public static final By ELEMENT_ACTIONBAR_ADDFORUM =
          By.xpath("//*[@class='uiIconForumCreateForum uiIconForumLightGray']");

  // Add/Edit forum popup
  public static final By ELEMENT_EDITFORUM_POPUP_TITLE =
          By.xpath("//span[@class='PopupTitle popupTitle' and text()='Forum']");

  public static final By ELEMENT_ADDFORUM_POPUP_TITLE = By.id("ForumTitle");

  public static final By ELEMENT_ADDFORUM_POPUP_ORDER = By.id("ForumOrder");

  public static final By ELEMENT_ADDFORUM_POPUP_DESCRIPTION = By.id("Description");

  public static final By ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON =
          By.xpath(".//*[@id='UIForumForm']//button[text()='Save']");

  public static final By ELEMENT_ADDFORUM_POPUP_CANCEL_BUTTON =
          By.xpath(".//*[@id='UIForumForm']//button[text()='Cancel']");

  public static final By ELEMENT_FORUM_PERMISSION_TAB =
          By.xpath(".//*[@id='UIForumForm']//*[contains(text(),'Permissions')]");

  public static final By ELEMENT_FORUM_PERMISSION_TAB_USER_SELECTOR =
          By.xpath(".//*[@id='forumPermission']//*[@class='uiIconUser uiIconLightGray']");

  public static final By ELEMENT_FORUM_CANCEL =
          By.xpath(".//*[@id='UIForumForm']//*[contains(text(),'Cancel')]");

  public static final By ELEMENT_FORUM_PERMISSION_CLOSE_USER_SELETOR =
          By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");

  public static final By ELEMENT_FORUM_PERMISSION_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");

  public static final String ELEMENT_FORUM_PERMISSION_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";

  public static final String ELEMENT_FORUM_PERMISSION_SEARCH_ICON =
          ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";

  // More Action menu
  public static final By ELEMENT_MORE_ACTION =
          By.xpath("//*[@data-toggle='dropdown']/*[@class='uiIconSettings uiIconLightGray']");
  ;

  public static final By ELEMENT_MODERATOR =
          By.xpath("//*[contains(@class,'uiIconForumModerator')]");

  public static final By ELEMENT_EDIT_FORUM =
          By.xpath("//*[contains(@href, 'EditForum')]");

  public static final By ELEMENT_DELETE_FORUM =
          By.xpath("//*[contains(@data-action, 'RemoveForum')]");

  public static final By ELEMENT_MOVE_FORUM =
          By.xpath("//*[contains(@href, 'MoveForum')]");

  public static final By ELEMENT_START_TOPIC_BUTTON =
          By.xpath("//*[contains(@href, 'AddTopic')]");

  public static final By ELEMENT_START_TOPIC_BTN =
          By.xpath(".//*[contains(@class,'uiIconForumCreateTopic')]");

  public static final By ELEMENT_LOCK_FORUM = By.className("uiIconLockMedium");

  public static final By ELEMENT_UNLOCK_FORUM = By.className("uiIconUnlockMedium");

  public static final By ELEMENT_CLOSE_FORUM =
          By.xpath("//a[contains(@href,'SetCloseForum')]");

  public static final By ELEMENT_OPEN_FORUM =
          By.xpath("//a[contains(@href,'SetOpenForum')]");

  // Start topic popup
  public static final By ELEMENT_START_TOPIC_POPUP_TITLE =
          By.xpath(".//*[@id='UIForumPopupWindow']//span[@class='PopupTitle popupTitle']");

  // Popup confirmation
  public static final By ELEMENT_OK_DELETE =
          By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");

  // Move forum popup
  public static final By ELEMENT_POPUP_MOVE_FORUM =
          By.xpath("//span[@class='PopupTitle popupTitle' and text()='Move Forum']");

  /***************************************************
   * CATEGORY MANAGEMENT
   ************************************************************************/
  // Home page
  public static final String ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK = ".//*[text()='${name}']";

  // Action bar
  public static final By ELEMENT_ACTIONBAR_ADDCATEGORY =
          By.xpath("//*[@class='uiIconAddCategory uiIconLightGray']");

  // Manage menu

  public static final By ELEMENT_MENU_MANAGE_CATEGORY =
          By.xpath("//*[@id=\"UICategory\"]/table/caption/ul/li[5]/div");

  public static final By ELEMENT_EDIT_CATEGORY =
          By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[1]/a");

  public static final By ELEMENT_EXPORT_FORUM =
          By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[2]/a");

  public static final By ELEMENT_IMPORT_FORUM =
          By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[3]/a");

  public static final By ELEMENT_DELETE_CATEGORY =
          By.xpath("//*[@id='UICategoryConfirm0' and contains(text(),'Delete')]");

  // Edit category
  public static final By ELEMENT_PERM_TAB =
          By.xpath("//*[contains(@data-toggle,'tab')][contains(.,'Permissions')]");

  public static final By ELEMENT_EDITCATEGORY_PERM_ADD_BTN =
          By.xpath("//*[contains(@id,'Permission')]//*[contains(@class,'addButton')]");

  // Add category popup
  public static final By ELEMENT_ADDCATEGORY_POPUP_TITLE = By.id("CategoryTitle");

  public static final By ELEMENT_ADDCATEGORY_POPUP_ORDER = By.id("CategoryOrder");

  public static final By ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION = By.id("Description");

  public static final By ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON =
          By.xpath(".//*[@id='UICategoryForm']//button[text()='Save']");

  public static final By ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON =
          By.xpath(".//*[@id='UICategoryForm']//button[text()='Cancel']");

  public static final By ELEMENT_ADDCATEGORY_RESTRICTED_AUDIENCE =
          By.xpath(".//*[@id='DetailTab']//*[@class='uiIconUser uiIconLightGray']");

  public static final By ELEMENT_RESTRICTED_AUDIENCE_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");

  public static final String ELEMENT_RESTRICTED_AUDIENCE_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";

  public static final String ELEMENT_RESTRICTED_AUDIENCE_SEARCH_ICON =
          ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";

  public static final By ELEMENT_RESTRICTED_AUDIENCE_CLOSE_USER_SELETOR =
          By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");

  public static final By ELEMENT_CATEGORY_CANCEL =
          By.xpath(".//*[@id='UICategoryForm']//*[contains(text(),'Cancel')]");

  public static final By ELEMENT_CATEGORY_PERMISSION_TAB =
          By.xpath(".//*[@id='UICategoryForm']//*[contains(text(),'Permissions')]");

  public static final By ELEMENT_CATEGORY_PERMISSION_TAB_USER_SELECTOR =
          By.xpath(".//*[@id='PermissionTab']//*[@class='uiIconUser uiIconLightGray']");

  public static final String ELEMENT_CATEGORY_DELETE_CONFIRM_MSG =
          "Are you sure you want to delete this category ?";

  // Export forum popup
  public static final By ELEMENT_EXPORT_FORUM_EXPORTALL = By.id("checkAll");

  public static final String ELEMENT_EXPORT_FORUM_EXPORT =
          "//*[contains(text(),'${title}')]/..//*[@class='uiCheckbox']//*[@class='checkbox']";

  /***************************************************
   * FORUM PERMISSION
   ************************************************************************/
  public static final By ELEMENT_PERM_MOD_CHECKBOX =
          By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[2]//input");

  public static final By ELEMENT_PERM_STARTTOP_CHECKBOX =
          By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[3]//input");

  public static final By ELEMENT_PERM_POSTREPLY_CHECKBOX =
          By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[4]//input");

  public static final By ELEMENT_PERM_VIEWPOST_CHECKBOX =
          By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[5]//input");

  public static final By ELEMENT_PERM_ROLE_ICON =
          By.xpath(".//*[contains(@id,'Permission')]//*[contains(@class,'uiIconMembership')]");

  public static final By ELEMENT_RESTRICTED_ROLE_ICON =
          By.xpath(".//*[@id='DetailTab']//*[contains(@class,'uiIconMembership')]");

  public static final By ELEMENT_MESSAGE_ROLE_ICON =
          By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconMembership')]");

  public static final By ELEMENT_SELECT_MEMBERSHIP_POPUP =
          By.xpath(".//*[@id='UIPopupActionChildPopupWindow']");

  public static final By ELEMENT_RESTRICTED_SELECT_MEMBERSHIP_POPUP =
          By.xpath("//*[@id='UIForumChildPopupWindow']//*[contains(@class,'PopupContent')]");

  public static final By ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[@id='UIPopupWindow']");

  public static final String ELEMENT_SELECT_RIGHT_PARENT_GROUP =
          "//*[contains(@id,'UIPopupActionChildPopupWindow')]//a[contains(.,'$group')]";

  public static final String ELEMENT_RESTRICTED_SELECT_RIGHT_PARENT_GROUP =
          "//*[@id='UIForumChildPopupWindow']//*[contains(@class,'PopupContent')]//a[contains(.,'$group')]";

  public static final String ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP =
          ".//*[@id='UIPopupWindow']//a[contains(.,'$group')]";

  public static final By ELEMENT_PERM_ADD_BTN =
          By.xpath("//*[contains(@id,'Permission')]//*[contains(@class,'addButton')]");

  /***************************************************
   * TOPIC MANAGEMENT
   ************************************************************************/
  public static final By ELEMENT_POST_REPLY =
          By.xpath("//*[@id='UITopicDetail']//*[@class='pull-left actionContainer']/a[contains(text(),'Post Reply')]");

  public static final String ELEMENT_TOPIC_POST_TITLE =
          "//*[@class='postViewTitle']/..//*[contains(text(),'${name}')]";

  public static final String ELEMENT_TOPIC_POST_CONTENT =
          "//*[@class='postContentContainer']/..//*[contains(text(),'${name}')]";

  // Action bar
  public static final By ELEMENT_ACTIONBAR_TOPIC_TAG =
          By.xpath("//*[@class='uiIconTag uiIconLightGray']");

  public static final By ELEMENT_ACTIONBAR_TOPIC_TAGNAME = By.xpath("//*[@id='AddTag']");

  public static final By ELEMENT_ACTIONBAR_TOPIC_RATE =
          By.xpath("//*[@class='uiIconForumStar uiIconForumLightGray']");

  public static final String ELEMENT_ACTIONBAR_TOPIC_TAGPRESENT =
          ".//*[@id='UITopicDetail']//a[@data-original-title='${tag}']";

  // Tag of topic

  public static final By ELEMENT_FORUM_TOPIC_ADD_TAG =
          By.xpath("//*[@id=\"UITopicDetail\"]/div[4]/div[1]/ul/li[2]/div/ul/li/div/a");

  // Rate

  public static final By ELEMENT_FORUM_VOTE_MARK =
          By.xpath("//*[@id=\"UIRatingForm\"]/div[2]/div/div[1]/i[3]");

  public static final String ELEMENT_POST_TITLE =
          ".//*[@class='postViewTitle'][contains(text(),'${title}')]";

  // More Action menu

  public static final By ELEMENT_TOPIC_RATE =
          By.xpath("//*[@id=\"UITopicDetail\"]/div[4]/div[1]/ul/li[3]/div/ul/li[13]/a/i");

  public static final By ELEMENT_EDIT_TOPIC =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Edit')]");

  public static final By ELEMENT_DELETE_TOPIC =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Delete')]");

  public static final By ELEMENT_MOVE_TOPIC =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Move')]");

  public static final By ELEMENT_CLOSE_TOPIC =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Close')]");

  public static final By ELEMENT_OPEN_TOPIC =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Open')]");

  public static final By ELEMENT_LOCK_TOPIC =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Lock')]");

  public static final By ELEMENT_UNLOCK_TOPIC =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Unlock')]");

  public static final By ELEMENT_ADD_POLL =
          By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Add Poll')]");
  // public static final By ELEMENT_FORUM_ICON_CLOSE =
  // By.xpath("//*[@class='uiIconMinus']");

  // Post on a topic
  public static final By ELEMENT_TOPIC_POST_A_REPLY_TITLE = By.id("PostTitle");

  public static final By ELEMENT_TOPIC_CANCEL_A_POST =
          By.xpath("//*[@id='UIPostForm']//*[contains(text(),'Cancel')]");

  public static final By ELEMENT_FORUM_ADDPOST =
          By.xpath("//*[@class='pull-left actionContainer']//*[@class='uiPostReplyIcon btn btn-primary']");

  public static final By ELEMENT_FORUM_POST_TITLE = By.xpath("//*[@id='PostTitle']");

  public static final By ELEMENT_FORUM_ADDTOPIC =
          By.xpath(".//*[@id='UITopicContainer']//*[contains(@class,'uiIconForumCreateTopic ')][1]");

  public static final By ELEMENT_FORUM_TOPIC_TITLE = By.xpath("//*[@id='ThreadTitle']");

  public static final String ELEMENT_FORUM_TOPIC_LINK = ".//*[contains(text(),'${name}')]";

  // Start Topic popup
  public static final By ELEMENT_START_TOPIC_POPUP_TITLE_FILED = By.id("ThreadTitle");

  public static final By ELEMENT_TOPIC_PERMISSION_TAB =
          By.xpath(".//*[@id='UITopicForm']//*[contains(text(),'Permissions')]");

  public static final By ELEMENT_TOPIC_PERMISSION_TAB_USER_SELECTOR =
          By.xpath(".//*[@id='ThreadPermission']//*[@class='uiIconUser uiIconLightGray']");

  public static final By ELEMENT_TOPIC_PERMISSION_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");

  public static final String ELEMENT_TOPIC_PERMISSION_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";

  public static final String ELEMENT_TOPIC_PERMISSION_SEARCH_ICON =
          ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";

  public static final SelenideElement ELEMENT_TOPIC_CANCEL =
          $(byId("UIForumPopupWindow")).find(byText("Cancel"));

  public static final By ELEMENT_TOPIC_PERMISSION_CLOSE_USER_SELETOR =
          By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");

  // reply post form
  public static final By ELEMENT_TITLE_POST = By.id("PostTitle");

  public static final By ELEMENT_POST_CONTENT =
          By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");

  public static final By ELEMENT_POST_FORM_SUBMIT =
          By.xpath("//*[@id=\"UIPostForm\"]/div[3]/button[1]");

  public static final By ELEMENT_POST_IN_TOPIC =
          By.xpath("//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@class='postContent']//*[contains(text(),'{$content}')]");

  public static final String ELEMENT_POST_IN_TOPIC_QUOTE =
          "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@class='contentQuote']//*[contains(text(),'{$content}')]";

  public static final String ELEMENT_POST_IN_TOPIC_PRIVATE =
          "//*[@class='postViewTitle' and contains(text(),'{$title}')]//*[contains(text(),'Post Private')]/../../..//*[contains(text(),'{$content}')]";

  public static final By ELEMENT_TOPIC_POST_REPLY_BOTTOM =
          By.xpath(".//*[@id='UITopicDetail']/div[5]//a[text()='Post Reply']");

  public static final By ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE =
          By.xpath(".//*[@id='UITopicDetail']//*[@data-original-title='You cannot reply to this topic.']");

  // New post popup
  public static final By ELEMENT_TOPIC_NEW_POST_TITLE =
          By.xpath(".//*[@id='UIForumPopupWindow']//span[text()='New Post']");

  public static final By ELEMENT_TOPIC_NEW_POST_TITLE_FIELD = By.id("PostTitle");

  public static final By ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR =
          By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");

  public static final By ELEMENT_START_TOPIC_ATTACH_FILE =
          By.xpath("//*[@id='ThreadContent']//*[@class='btn']");

  public static final By ELEMENT_UPLOAD_POPUP_FILE =
          By.xpath("//span[@class='PopupTitle popupTitle' and text()='Attach File']");

  public static final String ELEMENT_TOPIC_REPPLY_CONTENT = ".//*[contains(text(),'${content}')]";

  // foot page of post
  public static final String ELEMENT_QUOTE_POST =
          "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Reply with Quote']";
  public static final By ELEMENT_DELETE_POST =
          By.xpath("//*[@id=\"UITopicDetailConfirm6\"]");
  public static final String ELEMENT_PRIVATE_POST =
          "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Private Reply']";
  public static final By ELEMENT_DELETE_BOX_CONFIRMATION =
          By.xpath("//*[@id='UIForumPopupConfirmation']//button[@type='button' and text()='OK']");
  // Poll
  public static final By ELEMENT_POLL_QUESTION = By.id("Question");
  public static final By ELEMENT_POLL_OPTIONS0 = By.id("Option0");
  public static final By ELEMENT_POLL_OPTIONS1 = By.id("Option1");
  public static final By ELEMENT_POLL_SUBMIT =
          By.xpath("//*[@id=\"UIPollForm\"]/div[3]/button[1]");
  public static final By ELEMENT_MORE_ACTIONS_POLL =
          By.xpath("//*[@id=\"UITopicPoll\"]/div[2]/div[1]/ul/li[2]/div/div/i");
  public static final By ELEMENT_EDIT_POLL =
          By.xpath("//*[@id='UITopicPoll']//*[contains(text(),'Edit')]");
  public static final By ELEMENT_REMOVE_POLL =
          By.xpath("//*[@id='UITopicPoll']//*[contains(text(),'Remove')]");
  public static final By ELEMENT_CLOSE_POLL =
          By.xpath(".//*[@id='UITopicPoll']//a[contains(text(),'Close')]");
  public static final By ELEMENT_OPEN_POLL =
          By.xpath(".//*[@id='UITopicPoll']//a[contains(text(),'Reopen')]");
  public static final By ELEMENT_POLL_POPUP_TITLE =
          By.xpath(".//*[@id='UIForumPopupWindow']//span[text()='Poll']");
  public static final String ELEMENT_FORUM_POLL_DISPLAYOPT = "//*[contains(text(),'${opt}')]";
  public static final By ELEMENT_FORUM_POLL_GRID =
          By.xpath("//*[@class='uiGrid table no-border-cell rounded-corners table-striped tableVoting']");
  public static final By ELEMENT_FORUM_POLL_GRIDCLOSE =
          By.xpath("//*[@class='uiGrid table rounded-corners table-striped tableVoted']");
  // move a topic
  public static final String ELEMENT_UI_POPUP_MOVE_TOPIC =
          "//*[@id='MoveTopicForm']//*[@class='node']//*[contains(text(),'{$forum}')]";
  public static final String ELEMENT_MOVE_POPUP_COLLASPE_NODE =
          ".//*[@class='uiIconNode collapseIcon'][contains(.,'${category}')]";
  /***************************************************
   * PRIVATE MESSAGE MANAGEMENT
   ************************************************************************/
  // tab elements
  public static final By ELEMENT_TABS_SENT_MESSAGES =
          By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Sent Messages')]");
  public static final By ELEMENT_TABS_INBOX =
          By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Inbox')]");
  public static final By ELEMENT_TABS_COMPOSE_MESSAGE =
          By.xpath("//*[@id='UIPrivateMessageForm']//*[text()='Compose New Message']");
  public static final By ELEMENT_PRIVATE_MESSAGE_CANCEL_BUTTON =
          By.xpath("//*[@id='UIPrivateMessageForm']//*[contains(text(),'Cancel')]");
  // send messages
  public static final By ELEMENT_SEND_TO_MESSAGE = By.id("SendTo");
  public static final By ELEMENT_TITLE_MESSAGE = By.id("MailTitle");
  public static final By ELEMENT_MESSAGE_CONTENT =
          By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
  public static final By ELEMENT_SEND_BUTTON =
          By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='uiAction']//*[contains(text(),'Send')]");
  public static final By ELEMENT_COMPOSE_NEW_MESSAGE_GROUP_SELECTOR =
          By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconGroup')]");
  public static final String ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP = ".//*[contains(@title,'${name}')]";
  public static final String ELEMENT_PRIVATE_MESSAGE_SELECT_A_GROUP =
          ".//*[@id='UIGroupSelector']//*[contains(text(),'Select this Group')]";
  public static final String ELEMENT_PRIVATE_MESSAGE_SEND_SUCCESSFULLY = "Your message was sent successfully.";
  public static final By ELEMENT_COMPOSE_NEW_MESSAGE_MEMBERSHIP_SELECTOR =
          By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconMembership uiIconLightGray')]");
  public static final String ELEMENT_PRIVATE_MESSAGE_SELECT_A_MEMBERSHIP =
          ".//*[@id='UIMemberShipSelector']//*[contains(text(),'${membership}')]";
  // inbox
  public static final String ELEMENT_TITLE_AUTHORS_INBOX =
          "//*[@id='UIListInBoxPrivateMessage']//*[contains(text(),'{$author}')]/../..//*[contains(text(),'{$title}')]";
  public static final String ELEMENT_CONTACT_INBOX =
          "//*[@id='PermissionInfo']//*[contains(text(),'{$contact}')]";
  public static final String ELEMENT_CONTENT_INBOX =
          "//*[@id='uiViewPrivateMessage']//*[contains(text(),'{$content}')]";
  public static final By ELEMENT_REPLY =
          By.xpath("//*[@id='uiViewPrivateMessage']//*[@class='uiIconReply uiIconLightGray']");
  public static final String ELEMENT_DELETE_MESSAGE =
          "//*[@id='UIListInBoxPrivateMessage']//*[contains(text(),'{$title}')]/../../..//*[contains(text(),'{$contact}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
  // outbox
  public  static final SelenideElement ELEMENT_DELETE_ATTACHMENT_FILE = $(byId("UIEditTopicContainer")).find(byClassName("uiIconDelete"));


  public static final String          ELEMENT_FORWARD_MESSAGE                            =
                                                              "//*[@id='UIListSentPrivateMessage']//*[contains(text(),'{$title}')]/../../..//*[contains(text(),'{$contact}')]/../..//*[@class='uiIconForumForward uiIconForumLightGray']";
  public static final SelenideElement ELEMENT_ICON_SEARCH                                =
                                                          $(byXpath("//*[@id=\"QuickSearchForm\"]/div[2]/a/i"));
  public static final By              ELEMENT_CONFIRM                                    =
                                                      By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'OK')]");
  public static final SelenideElement ELEMENT_POLL_CANCEL                                =
                                                          $(byXpath("//*[@id=\"UIPollForm\"]/div[3]/button[3]"));
  public static final SelenideElement ELEMENT_SEARCH_RESULT                              =
                                                            $(byXpath("//*[@id=\"UIForumListSearch\"]/div[3]/table"));
  public static final SelenideElement ELEMENT_CLOSE_POPUP                                =
                                                          $(byXpath("//*[@id=\"UIViewPost\"]/div[3]/button[1]"));
  public static final SelenideElement ELEMENT_HOME_FORUM                                 =
                                                         $(byId("UIBreadcumbs")).find(byText("Home"));
  public static final SelenideElement ELEMENT_CATEGORY_CONTAINER                         = $(byId("UICategory"));
  public static final SelenideElement ELEMENT_BTN_OK                                     =
                                                     $(byClassName("infoIcon")).parent()
                                                                               .parent()
                                                                               .parent()
                                                                               .find(byClassName("btn"));
  public static final SelenideElement ELEMENT_CAT_CONTAINER                              = $(byId("UICategoryContainer"));
  public static final SelenideElement ELEMENT_PROGRESS_BAR                               = $((byClassName("progressBarFrame")));
  public static final SelenideElement ELEMENT_CANCEL_PRIVATE_MSG                         =
                                                                 $(byXpath("//*[@id=\"UIPrivateMessageForm\"]/div[3]/button"));
  public static final SelenideElement ELEMENT_FORUM_OK_SEND_MSG                          =
                                                                $(byText("Your message was sent successfully.")).parent()
                                                                                                                .parent()
                                                                                                                .parent()
                                                                                                                .find(byClassName("btn"));
  public static final SelenideElement ELEMENT_CONTENT_SEARCH_RESULT                      = $(byClassName("searchResult"));
  public static final By              ELEMENT_ICON_REPLAY_POST_FROM_ACTIVITY             = byClassName("uiIconReply");
  public static final By              ELEMENT_BUTTON_DELETE_POST                         = byAttribute("data-original-title","Delete This Post");
  public static final SelenideElement ELEMENT_CHECKBOX_ALL_FORUM_CATEGORIE               = $(byId("checkAll")).parent();
  public static final SelenideElement ELEMENT_CHECKBOX_SELECT_ONE_FORUM_CATEGORIE        =
                                                                                  $(byId("UIExportForm")).findAll(byClassName("uiCheckbox"))
                                                                                                         .get(1);
  public static final SelenideElement ELEMENT_SELECT_FORUM_TO_MOVE                       =
                                                                   $(byXpath("//*[@id=\"UIMoveTopicForm\"]/div[2]/div[1]/div/div/ul/li[1]/ul/li/a"));
  public static final By              ELEMENT_BUTTON_FORWARD_MESSAGE                     = byClassName("uiIconForumForward");
  public static final By              ELEMENT_BUTTON_DELETE_MESSAGE                      = byClassName("uiIconDelete");
  public static final SelenideElement ELEMENT_UPLOAD_CATEGORIE_FORUM                     =
                                                                     $(byId("FileUpload")).find(byClassName("file"));
  public final String                 ELEMENT_EDIT_POST                                  =
                                                        "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Edit This Post']";
  public final By                     ELEMENT_MOVE_TOPIC_POPUP                           = By.id("UIForumPopupWindow");

  public static final SelenideElement ELEMENT_BUTTON_SUBMIT_POST= $(byXpath("//*[@id=\"UIPostForm\"]/div[3]/button[1]"));
 public static final SelenideElement ELEMENT_TOPIC_QUOTE= $(byClassName("btnDetailTopic")).find(byText("Quote"));
public static final SelenideElement ELEMENT_MORE_IN_ACTION_BAR=$(byClassName("moreItem"));
}
