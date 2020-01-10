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

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public final class NavigationToolBarLocator {
  // Tool bar
  public static final By              ELEMENT_TOOLBAR_ADMINISTRATION                      =
                                                                     By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//i");

  public static final By              ELEMENT_TOOLBAR_THEMELIGHT                          =
                                                                 By.xpath("(//div[@class=\"UIContainer UIToolbarContainer UIToolbarContainerLight  \"])[1]");

  public static final By              ELEMENT_TOOLBAR_THEMEDARK                          =
                                                                 By.xpath("(//div[@class=\"UIContainer UIToolbarContainer UIToolbarContainerDark  \"])[1]");

  public static final By              ELEMENT_HELP_TOOLBAR                                = By.className("uiIconPLF24x24Help");

  public static final By              ELEMENT_TOOLBAR_NOTIFICATION_LIST                   =
                                                                        By.xpath(".//*[@id='UINotificationPopoverToolbarPortlet']//*[contains(@class,'uiIconPLF24x24Bell')]");

  // Notificaiton list
  public static final By              ELEMENT_NOTIFICATION_DROPDOWN                       = By.cssSelector("#NotificationPopup");

  public static final String          ELEMENT_NOTIFICATION_LIST_USER                      =
                                                                     "//*[@id='NotificationPopup']/../..//*[contains(@class,'user-name text-bold')][contains(text(),'${user}')]/..";

  public static final By              ELEMENT_NOTIFICATION_REMOVE_ICON                    =
                                                                       By.xpath(".//*[@id='NotificationPopup']//i[contains(@class,'uiIconClose uiIconLightGray')]");

  public static final By              ELEMENT_INTRANET_NOTIFICATION_BELL                  =
                                                                         By.xpath("//*[@class='uiIconPLF24x24Bell']");

  public static final By              ELEMENT_POSITION_OF_INTRANET_NOTIFICATION           =
                                                                                By.xpath("//*[@class='UITableColumnContainer']//*[@class='UserInfoPortletTDContainer pull-left']/../*[@class='NotificationPopoverPortletTDContainer pull-left']");

  // Intranet notification
  public static final By              ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION =
                                                                                          By.xpath(".//*[@id='NotificationPopup']//*[contains(text(),'Mark all as read')]");

  public static final By              ELEMENT_VIEW_ALL_BUTTON                             =
                                                              By.xpath(".//*[@id='NotificationPopup']//a[text()='View All']");

  public static final By              ELEMENT_NO_NOTIFICATIONS                            =
                                                               By.xpath(".//*[@id='NotificationPopup']//*[@class='no-items' and text()='No notifications']");

  // administration Menu
  // users
  public static final By              ELEMENT_ADMINISTRATION_USERS                        =
                                                                   By.xpath("//*[contains(@href,'g/:platform:administrators/administration/newStaff') and text()='Community']");

  public static final By              ELEMENT_GROUP_AND_ROLE_LINK                         =
                                                                  By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//a[contains(@href,'management')]");

  // administration-->Portal
  public static final By              ELEMENT_ADMINISTRATION_PORTAL                       = By.xpath("//*[text()='Portal']");

  public static final By              ELEMENT_ADMINISTRATION_PORTAL_SITES                 = By.xpath("//*[text()='Sites']");

  public static final By              ELEMENT_ADMINISTRATION_PORTAL_EMAIL_NOTIFICATIONS   =
                                                                                        By.xpath(".//*[contains(@id,'UISetupPlatformToolBarPortlet')]//*[contains(@href,'notification')]");

  public static final By              ELEMENT_ADMINISTRATION_PORTAL_BRANDING              = By.xpath("//*[text()='Branding']");

  public static final By              ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES           = By.xpath("//*[text()='Group Sites']");

  // Administation-->Content
  public static final SelenideElement ELEMENT_LINK_CONTENT_ADMIN                          =
                                                                 $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[3]/ul/li[2]/a"));

  public static final By              ELEMENT_MENU_CONTENT_LINK                           =
                                                                By.xpath("//li[@class='dropdown-submenu']/a[text()='Content']");

  public static final By              ELEMENT_MENU_SITE_EXPLORER                          = By.xpath("(//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Administration')]/preceding::a[1])[1]");

  public static final By              ELEMENT_SEARCH_LINK                                 =
                                                          By.xpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[3]/ul/li[3]/a");

  // Setup icon
  public static final By              ELEMENT_LINK_SETUP                                  =
                                                         By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//*[@class='uiIconPLF24x24Setup']");

  // Edit menu
  public static final By              ELEMENT_LINK_EDIT                                   =
                                                        By.xpath("//*[@class='uiIconPLF24x24Edit']");

  public static final By              ELEMENT_MENU_PAGE_LINK                              =
                                                             By.xpath("//*[@tabindex='-1' and contains(text(),'Page')]");

  public static final By              ELEMENT_MENU_EDIT_LAYOUT                            =
                                                               By.xpath("//*[contains(text(),'Edit Layout')]");

  public static final By              ELEMENT_MENU_SEO_LINK                               =
                                                            By.xpath("//span[contains(text(), 'SEO')]");

  public static final By              ELEMENT_MENU_ADD_PAGE_LINK                          =
                                                                 By.xpath("//*[contains(text(), 'Add Page')]");

  public static final By              ELEMENT_EDIT_PAGE                                   =
                                                        By.xpath("//*[@id='UIAdminToolbarContainer']//*[@class='dropdown-submenu']//*[@href='#' and contains(text(), 'Page')]");

  public static final By              ELEMENT_EDIT_CONTENT                                =
                                                           By.xpath("//*[@class='quickEditUnchecked']");

  public static final By              ELEMENT_EDIT_CONTENT_CHECK                          =
                                                                 By.xpath("//*[@class='quickEditChecked']");

  // User Menu
  public static final By              ELEMENT_MY_PROFILE_LINK                             =
                                                              By.xpath("//i[@class='uiIconPLFProfile']/..");

  public static final By              ELEMENT_MY_DASHBOARD_LINK                           =
                                                                By.xpath("//i[@class='uiIconPLFDashboard']/..");

  public static final By              ELEMENT_MY_SETTINGS_LINK                            = By.className("uiIconSetting");

  public static final By              ELEMENT_MY_CONNECTION_LINK                          = By.className("uiIconPLFMyConnection");

  public static final By              ELEMENT_ACTIVITIES_LINK                             =
                                                              By.className("uiIconPLFActivityStream");

  public static final By              ELEMENT_TOPBAR_AVATAR                               = By.xpath("//*[@alt='avatar']");

  public static final By              ELEMENT_AVATAR_CHANGELANGUAGE                       = By.xpath("//*[@class='uiIconFlags']");

  public static final By              ELEMENT_MY_WIKI_LINK                                =
                                                           By.xpath("//i[@class='uiIconWikiMyWiki']/..");

  public static final By              ELEMENT_MY_NOTIFICATIONS_LINK                       =
                                                                    By.className("uiIconPLFNotifications");

  public static final By              ELEMENT_ACTIVITIES_PORTLET                          = By.id("UIUserActivityStreamPortlet");

  // administration-->Application
  public static final By              ELEMENT_ADMINISTRATION_APPLICATION                  =
                                                                         By.xpath(".//*[text()='Applications']");

  public static final By              ELEMENT_ADD_TOOTLBAR                                =
                                                           By.xpath("//*[@id='UICreatePlatformToolBarPortlet']//*[@class='uiIconPLF24x24Add']");

  public static final By              ELEMENT_ADD_WIKI_TOOLBAR                            =
                                                               By.xpath("//*[@id='UICreateList']//*[@class='uiIconWikiWiki']");

  public static final By              ELEMENT_ADD_POOL_TOOLBAR                            =
                                                               By.xpath("//*[@id='UICreateList']//*[@class='uiIconPoll']");

  public static final By              ELEMENT_ADD_TOPIC_TOOLBAR                           =
                                                                By.xpath("//*[@id='UICreateList']//*[@class='uiIconUIForms']");

  public static final By              ELEMENT_ADD_EVENT_CLASS_TOOLBAR                     =
                                                                      By.xpath("//*[@id='UICreateList']//*[@class='uiIconPLFEventTask']");

  public static final SelenideElement ELEMENT_ADD_TASK_CLASS_TOOLBAR                      =
                                                                     $(byXpath("//*[@id=\"UICreateList\"]/li[1]/a"));

  public static final By              ELEMENT_NEXT_BUTTON                                 =
                                                          By.xpath("//*[@id='UICreateList']//*[contains(text(),'Next')]");

  public static final By              ELEMENT_SAVE_BUTTON                                 =
                                                          By.xpath("//*[@id='UICreateList']//*[contains(text(),'Save')]");

  // add wiki from toolbar
  public static final By              ELEMENT_ADD_WIKI_SET_LOCATION                       =
                                                                    By.xpath("//*[@id='uiWikiSpaceSwitcher_CreateWiki']//*[@id='DisplayModesDropDown']/div");

  public static final String          ELEMENT_ADD_WIKI_CHOOSE_LOCATION                    =
                                                                       "//*[@class='spaceChooserPopup']//*[contains(text(),'{$location}')]";

  // add poll/topic
  public static final By              ELEMENT_ADD_POLL_SET_LOCATION                       =
                                                                    By.xpath("//*[@id='ScrollSelectlocation']//*[@class='btn dropdown-toggle']");

  public static final SelenideElement ELEMENT_SELECT_FORUM_COMBOBOX                       = $(byId("uiForumFilterforumId"));

  // event or task
  public static final By              ELEMENT_ADD_TITLE                                   = By.id("Title");

  // Quick search
  public static final By              ELEMENT_TOOLBAR_QUICKSEARCH                         =
                                                                  By.xpath("//*[@class='uiIconPLF24x24Search']");

  // toolbar--> upload file
  public static final SelenideElement ELEMENT_ADMINISTRATION_COMMUNITY                    =
                                                                       $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a"));

  public static final SelenideElement ELEMENT_ADMINISTRATION_SPACES                    =
                                                                       $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[5]/a"));

  public static final SelenideElement ELEMENT_ADMINISTRATION_MANAGE_COMMUNITY             =
                                                                              $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/ul/li[2]/a"));

  public static final SelenideElement ELEMENT_ADMINISTRATION_ADD_USERS                    =
                                                                       $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/ul/li[1]/a"));

  public static final SelenideElement ELEMENT_TASK_ADD_TITLE                              = $(byId("title"));

  public static final SelenideElement ELEMENT_TASK_BUTTON_ADD                             =
                                                              $(byXpath("//*[@id=\"QuickAddTaskContainer\"]/div[2]/a[1]"));

public static final By ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION=byClassName("action-item");
  public static final By ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION=byClassName("cancel-item");
 public static final SelenideElement ELEMENT_SPACE_ADMIN_LINK=$(byAttribute("href", "/portal/g/:platform:users/spacesAdministration"));
}


