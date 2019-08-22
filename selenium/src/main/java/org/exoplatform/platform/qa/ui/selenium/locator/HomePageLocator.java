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
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public final class HomePageLocator {

  public static final SelenideElement ELEMENT_PLF_HOMEPAGE_DISPLAY                                         =
                                                                   $("li.navItemSelected");

  public static final SelenideElement ELEMENT_PLF_HOMEPAGE_ACTIVITY_PORTLET                                =
                                                                            $("#UIUserActivityStreamPortlet");

  public static final SelenideElement ELEMENT_PLF_HOMEPAGE_GADGET_PORTLET                                  =
                                                                          $("#OfficeRightMiddle");

  public static final By              ELEMENT_ADDNEWSPACE_BUTTON                                           =
                                                                 By.xpath("//button[contains(.,'Add New Space')]");

  public static final By              ELEMENT_PLF_OFFICE_RIGHT_COLUMN                                      =
                                                                      By.xpath(".//*[@id='OfficeRightMiddle']");

  // Left panel
  public static final By              ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON                                =
                                                                            byId("ActivitiesLoader");

  public static final By              ELEMENT_FORUM_LINK_PLF                                               =
                                                             By.xpath("//span[@data-original-title='Forums']");

  public static final By              ELEMENT_ANSWER_LINK_PLF                                              =
                                                              By.xpath("//*[@data-original-title='Answers']");

  public static final SelenideElement ELEMENT_WIKI_LINK_PLF                                                =
                                                            $("#MenuItemwiki");

  public static final SelenideElement ELEMENT_DOCUMENTS_LINK_PLF                                           =
                                                                 $("#MenuItemdocuments");

  public static final SelenideElement ELEMENT_PEOPLE_LINK_PLF                                           =
                                                    $("#MenuItemconnexions");

  public static final SelenideElement ELEMENT_HOME_LINK_PLF                                                =
                                                            $("#MenuItemhome");

  public static final SelenideElement ELEMENT_CALENDAR_LINK_PLF                                            =
                                                                $(byClassName("uiIconPLFCalendar"));

  public static final SelenideElement ELEMENT_TASKS_LINK_PLF                                               =
                                                             $(byClassName("uiCompanyNavigations")).find(byText("Tasks"));

  public static final By              ELEMENT_CONNECTION_LINK_PLF                                          =
                                                                  By.xpath("//*[@data-original-title='Connections']");

  public static final By              ELEMENT_HOME_LINK_PLF_IN_FRENCH                                      =
                                                                      By.xpath("//*[@data-original-title='Accueil']");

  public static final By              ELEMENT_CONNECTIONS_LINK_PLF                                         =
                                                                   By.xpath("//*[@class='uiCompanyNavigations']//*[contains(@class,'uiIconPLFMyConnection')]");

  public static final SelenideElement ELEMENT_SEARCH_SPACE                                                 =
                                                           $("#UISpaceNavigationPortlet div.searchInput input.searchText");

  /**
   * Get the list of spaces links in the left menu navigation
   */
  public static final ElementsCollection ELEMENT_SEARCH_SPACE_RESULTS = $$("#UISpaceNavigationPortlet ul.spaceNavigation li");

  public static final String          ELEMENT_RESULT_SEARCH_SPACE                                          =
                                                                  "//*[@id='UISpaceNavigationPortlet']//*[@class='spaceNavigation']//*[contains(text(),'{$space}')]";

  public static final String          ELEMENT_LEFT_PANEL                                                   =
                                                         "//*[@class='uiCompanyNavigations']//*[contains(text(),'{$name}')]";

  public static final SelenideElement ELEMENT_SPECIFIC_PANEL                                               =
                                                             $(byId("UISpaceNavigationPortlet"));

  // right panel
  public static final String          ELEMENT_SUGGESTIONS_USER                                             =
                                                               "//*[@class='uiBox uiSuggestions']//*[contains(text(),'{$user}')]";

  public static final String          ELEMENT_SPACE_BYNAME                                                 =
                                                           "//*[@data-original-title='${name}']";

  // Wiki activity
  public static final String          ELEMENT_WIKI_COMMENT_EDIT_TITLE                                      =
                                                                      "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'title has been updated to: ${title}')]";

  public static final String          ELEMENT_WIKI_COMMENT_EDIT_CONTENT                                    =
                                                                        "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'content has been edited')]";

  public static final String          ELEMENT_ACTIVITY_WIKI_TITLE                                          =
                                                                  "//*[@class='linkTitle' and text()='${title}']";

  public static final String          ELEMENT_ACTIVITY_WIKI_CONTENT                                        =
                                                                    "//*[@class='linkTitle' and text()='${title}']/../../..//*[@class='contentWiki theContent']/*[@class='text']";

  public static final String          ELEMENT_ACTIVITY_WIKI_VERSION                                        =
                                                                    "//*[@class='linkTitle' and text()='${title}']/../..//*[@class = 'pull-right versionLabel' and contains(text(), 'Version: ${version}')]";

  public static final String          ELEMENT_ACTIVITY_MOVE_WIKI_PAGE                                      =
                                                                      "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'Page has been moved to: ${path}')]";

  public static final SelenideElement ELEMENT_MY_SPACE_LINK_PLF                                            =
                                                                $("#MySpacesItem");

  public static final SelenideElement ELEMENT_SECOND_SPACE_MY_SPACE                                        =
                                                                    $(byClassName("spaceNavigation")).findAll(byClassName("spaceItem"))
                                                                                                     .get(1);

  public static final By              ELEMENT_ALL_SPACE_JOIN_LINK                                          =
                                                                  By.cssSelector("#UISpaceNavigationPortlet .uiIconPLFMan");

  public static final By              ELEMENT_ALL_SPACE_JOIN_LINK2                                         =
                                                                   By.xpath("//*[@id=\"UISpaceNavigationPortlet\"]/div/div[2]/a");

  public static final String          ELEMENT_SPECIFIC_SPACE_LINK_PLF                                      =
                                                                      "//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'{$space}')]";

  public static final String          ELEMENT_NUMBER_OF_SPACE_IN_LIST_LINK                                 =
                                                                           "//*[@class='spaceNavigation']/li[{$number}]/a";

  // Middle homepage panel
  public static final By              ELEMENT_HOMPAGE_MIDDLE_PANEL                                         =
                                                                   By.id("OfficeMiddle");

  public static final By              ELEMENT_HOMEPAGE_DROP_MENU_ARROW                                     =
                                                                       By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'uiIconMiniArrowDown')]");

  public static final By              ELEMENT_HOMEPAGE_DROP_MENU_MY_ACTIVITIES                             =
                                                                               By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'My Activities')]");

  public static final By              ELEMENT_HOMEPAGE_DROP_MENU_ALL_ACTIVITIES                            =
                                                                                By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'All Activities')]");

  public static final By              ELEMENT_HOMEPAGE_DROP_MENU_MY_SPACES                                 =
                                                                           By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'My Spaces')]");

  public static final By              ELEMENT_HOMEPAGE_DROP_MENU_CONNECTIONS                               =
                                                                             By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'Connections')]");

  // Site Explorer activities
  public static final By              ELEMENT_SITEMAPS_ACTIVITY                                            =
                                                                By.xpath(".//*[@data-original-title='sitemaps']/../../../..//div[@class='commentItem commentItemLast']//p[text()='File has been updated.']");

  // Tool bar
  public static final By              ELEMENT_TOOLBAR_ADMINISTRATION                                       =
                                                                     By.xpath("//*[@class='uiIconPLF24x24Setup']");

  // administration
  public static final By              ELEMENT_ADMINISTRATION_CONTENT                                       =
                                                                     By.xpath("//*[text()='Content']");

  public static final By              ELEMENT_ADMINISTRATION_SITEEXPLORER                                  =
                                                                          By.xpath("//*[text()='Sites Explorer']");

  // top panel
  // edit
  public static final By              ELEMENT_EDIT_PAGE                                                    =
                                                        By.xpath("//*[@id='UIAdminToolbarContainer']//*[@class='dropdown-submenu']//*[@href='#' and contains(text(), 'Page')]");

  public static final By              ELEMENT_EDIT_PAGE_SEO                                                =
                                                            By.xpath("//*[@data-original-title = 'SEO Management']");

  public static final By              ELEMENT_EDIT_PAGE_EDITLAYOUT                                         =
                                                                   By.xpath("//*[contains(text(), 'Edit Layout')]");

  public static final SelenideElement              ELEMENT_DELETE_ECM_ADMIN_PAGE_EDITLAYOUT                                         =
                                                                    $(byXpath("(//span[contains(text(),'ECM Admin')]/following::a[@data-original-title=\"Delete Portlet\"])[1]"));

  public static final By              ELEMENT_ECM_ADMIN_PORTLET_LAYOUT                                         =
                                                                    By.xpath("//div[@class=\"portletLayoutDecorator\" and contains(text(),\"ECM Admin\")]");
  // administration panel
  public static final By              ELEMENT_TOPBAR_ADMINISTRATION_BUTTON                                 =
                                                                           By.xpath("//*[@class='uiIconPLF24x24Setup']");

  public static final By              ELEMENT_TOPBAR_CONTENT                                               =
                                                             By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content')]");

  public static final By              ELEMENT_CONTENT_TOPBAR_ADMINISTRATION                                =
                                                                            By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content administration')]");

  public static final By              ELEMENT_CONTENT_TOPBAR_SITEEXPLORER                                  =
                                                                          By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Sites Explorer')]");

  public static final By              ELEMENT_TOPBAR_ADMINISTRATION_PORTAL                                 =
                                                                           By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Portal')]");

  public static final By              ELEMENT_PORTAL_TOPBAR_SITE                                           =
                                                                 By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[text()='Sites']");

  // User
  public static final By              ELEMENT_TOPBAR_AVATAR                                                =
                                                            By.xpath("//*[@alt='avatar']");

  public static final By              ELEMENT_AVATAR_CHANGELANGUAGE                                        =
                                                                    By.xpath("//*[@class='uiIconFlags']");

  public static final String          ELEMENT_CHANGELANGUAGE_LANGUAGE                                      =
                                                                      "//*[text()='${language}']";

  public static final String          ELEMENT_AVATAR_CHANGELANGUAGE_APPLY                                  =
                                                                          "//*[text()='${text}']";

  // Manage sites
  public static final By              ELEMENT_EDITNAVIG_ACME                                               =
                                                             By.xpath("//*[text()='acme']/../..//*[@class='uiIconNavigation uiIconLightGray']");

  public static final By              ELEMENT_EDITNAVIG_INTRANET                                           =
                                                                 By.xpath("//*[text()='intranet']/../..//*[@class='uiIconNavigation uiIconLightGray']");

  public static final String          ELEMENT_EDITSITE_SITE                                                =
                                                            "//*[@title='${name}']";

  public static final String          ELEMENT_EDITSITE_SITESUPPRIMER                                       =
                                                                     "//*[@class='uiIconDeleteNode']";

  // Edit panel
  public static final By              ELEMENT_EDIT_BUTTON                                                  =
                                                          By.xpath(".//*[@id='UIAdminToolbarContainer']//*[@class='uiIconPLF24x24Edit']");

  public static final By              ELEMENT_EDIT_CONTENT                                                 =
                                                           By.xpath("//*[@class='quickEditUnchecked']");

  public static final By              ELEMENT_EDIT_CONTENT_CHECK                                           =
                                                                 By.xpath("//*[@class='quickEditChecked']");

  public static final By              ELEMENT_HP_ACTIVITY_TEXTBOX                                          =
                                                                  By.xpath("//*[@id='DisplaycomposerInput']");

  public static final By              ELEMENT_SITE_TOP_LIST                                                =
                                                            By.xpath(".//*[@id='UIAdminToolbarContainer']/ul/li[3]/a");

  public static final By              ELEMENT_ADD_PAGE_DROP_LIST                                           =
                                                                 By.xpath(".//*[@id='UIAdminToolbarContainer']/ul/li[3]/ul[@class='dropdown-menu']/li[4]/a");

  public static final By              ELEMENT_EDITSITE_SAVEBTN                                             =
                                                               By.xpath("//*[@class='btn' and text()='Save']");

  public static final By              ELEMENT_HP_ACTIVITY_SHAREBTN                                         =
                                                                   By.xpath("//*[@id='ShareButton']");

  // SEO Management
  public static final By              ELEMENT_SEO_LANGUAGE_SHOW                                            =
                                                                By.xpath("//*[@onClick='eXo.ecm.WCMUtils.showSEOLanguage(true)']");

  public static final By              ELEMENT_SEO_LANGUAGE_SELECTBOX                                       =
                                                                     By.xpath("//*[@name='language']");

  public static final By              ELEMENT_SEO_TITLEBOX                                                 =
                                                           By.xpath("//*[@id='title']");

  public static final By              ELEMENT_SEO_DELETE                                                   =
                                                         By.xpath("//*[@title='Delete']");

  public static final By              ELEMENT_SEO_HELPDESC                                                 =
                                                           By.xpath("//*[text()='Description: ']/..//*[@id='DescriptionHelp']");

  public static final By              ELEMENT_SEO_HELPKEYWORD                                              =
                                                              By.xpath("//*[text()='Keywords: ']/..//*[@id='DescriptionHelp']");

  public static final By              ELEMENT_SEO_HELPPRIORITY                                             =
                                                               By.xpath("//*[@id='PriorityHelp']");

  // calendar gadget
  public static final By              ELEMENT_HP_CALENDARGADGET_BOX                                        =
                                                                    By.xpath("//*[@class='calendarPortletData uiBox']");

  public static final By              ELEMENT_HP_CALENDARGADGET_RIGHTARROW                                 =
                                                                           By.xpath("//*[@class='uiIconMiniArrowRight uiIconLightGray']");

  public static final By              ELEMENT_HP_CALENDARGADGET_LEFTARROW                                  =
                                                                          By.xpath("//*[@class='uiIconMiniArrowLeft uiIconLightGray']");

  public static final String          ELEMENT_HP_CALENDARGADGET_DISPLAYEDDAY                               =
                                                                             "//*[contains(text(), '${day}')]";

  public static final By              ELEMENT_HP_CALENDARGADGET_SETTINGS                                   =
                                                                         By.xpath("//*[@class='uiIconSetting uiIconLightGray']");

  public static final By              ELEMENT_HP_CALENDARGADGET_SETTINGS_DISPLAYEDCAL                      =
                                                                                      By.xpath("//*[contains(text(),'Displayed Calendars')]");

  public static final By              ELEMENT_HP_CALENDARGADGET_SETTINGS_SETTINGSCAL                       =
                                                                                     By.xpath("//*[contains(text(),'Displayed Calendars')]/../..//*[contains(text(),'Settings')]");

  public static final String          ELEMENT_HP_CALENDARGADGET_SETTINGS_REMOVECAL                         =
                                                                                   "//*[@data-original-title='${title}']/..//*[@class='uiIconDel']";

  public static final String          ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCALNAME                        =
                                                                                    "//*[text()='${title}']";

  public static final By              ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCAL                            =
                                                                                By.xpath("//*[@class='uiIconSimplePlusMini uiIconLightGray']");

  public static final By              ELEMENT_HP_CALENDARGADGET_SETTINGS_SEARCHCAL                         =
                                                                                   By.xpath("//*[@class='PLFcalendarSearchKey']");

  // Getting Started
  public static final By              ELEMENT_HP_GETTINGSTARTED_BOX                                        =
                                                                    By.xpath("//*[@class='UIGadgetThemes uiBox uiGettingStarted']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_TITLE                                      =
                                                                      By.xpath(".//*[@class='GettingStarted']//h6[text()='Getting Started']");

  public static final String          ELEMENT_HP_GETTINGSTARTED_TASKS                                      =
                                                                      ".//*[@class='GettingStarted']//*[contains(text(),'${name}')]";

  public static final By              ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS                          =
                                                                                  By.xpath("//*[text()='Connect to coworkers']");

  public static final String          ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERSBTN                       =
                                                                                     "//*[text()='${name}']/../../..//*[text()='Connect']";

  public static final By              ELEMENT_HP_GETTINGSTARTED_ACCEPTTOCOWORKERSBTN                       =
                                                                                     By.xpath("//*[text()='Confirm']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS_DONE                     =
                                                                                       By.xpath("//*[@class='done']/..//*[text()='Connect to coworkers']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_ADDPROFILEPIC                              =
                                                                              By.xpath("//*[text()='Add a profile picture']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_JOINSPACE                                  =
                                                                          By.xpath("//*[text()='Join a space']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_UPLOADDOC                                  =
                                                                          By.xpath("//*[text()='Upload a document']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_POSTACTIVITY                               =
                                                                             By.xpath("//*[text()='Post an activity ']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_PROFILEPAGE                                =
                                                                            By.xpath("//*[@class='uiIconAppprofile uiIconDefaultApp']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_CONNECTIONPAGE                             =
                                                                               By.xpath("//*[@class='uiIconAppconnections uiIconDefaultApp']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_CHECKJOINSPACE                             =
                                                                               By.xpath("//*[@class='active']//*[text()='All Spaces']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_CHECKUPLOADDOC                             =
                                                                               By.xpath("//*[@id='UIJCRExplorerPortlet']");

  public static final By              ELEMENT_HP_GETTINGSTARTED_PROGRESSRATE                               =
                                                                             By.xpath("//*[@id='progress-rate']/../../../..//*[contains(text(), '100 %')]");

  public static final By              ELEMENT_HP_GETTINGSTARTED_CLOSEBOX                                   =
                                                                         By.xpath("//*[@class='gadgetTitle title center']//*[@class='uiIconClose pull-right']");

  // Who's online gadget
  public static final SelenideElement ELEMENT_WHO_ON_LINE_GADGET                                           =
                                                                 $(byId("onlineContent"));

  public static final String          ELEMENT_WHO_ONLINE_ICON_PEOPLE_NUMBER                                =
                                                                            "//*[@id='onlineList']/li[{$number}]/a";

  public static final String          ELEMENT_WHO_ONLINE_PEOPLE_AVATAR                                     =
                                                                       ".//*[@id='onlineList']//*[contains(@href,'${name}')]";

  public static final String          ELEMENT_WHO_ONLINE_POP_UP_NAME                                       =
                                                                     "//*[@id='tipName']//*[contains(text(),'{$name}')]";

  public static final By              ELEMENT_WHO_ONLINE_DEFAULT_AVATAR                                    =
                                                                        By.xpath("//*[@id='tiptip_content']//*[@src='/eXoSkin/skin/images/system/UserAvtDefault.png']");

  public static final By              ELEMENT_WHO_ONLINE_CONNECT                                           =
                                                                 By.xpath("//*[@id='tiptip_content']//*[contains(text(),'Connect')]");

  public static final By              ELEMENT_WHO_ONLINE_CANCEL_CONNECT                                    =
                                                                        By.xpath("//*[@id='tiptip_content']/div//*[contains(text(),'Cancel Request')]");

  // invitation gadget
  public static final String          ELEMENT_INVITATIONS_NAME_OF_PEOPLE_WHO_SEND_REQUEST                  =
                                                                                          "//*[@id='InvitationsPortlet']//*[@class='peopleInviteName']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_INVITATIONS_PEOPLE_AVATAR                                    =
                                                                        ".//*[contains(text(),'${name}')]/../../../..//*[@class='peopleInvitePicture pull-left avatarXSmall']";

  public static final String          ELEMENT_INVITAITONS_SPACE_ICON                                       =
                                                                     ".//*[contains(text(),'${name}')]/../..//*[@class='spaceInvitePicture pull-left avatarXSmall']";

  public static final String          ELEMENT_INVITAITONS_SPACE_STATUS_MEMBERS                             =
                                                                               ".//*[contains(text(),'${name}')]/..//*[contains(text(),'${statusMember}')]";

  public static final String          ELEMENT_INVITATIONS_PEOPLE_ACCEPT_BTN                                =
                                                                            "//div[@id='InvitationsPortlet']//div[@class='peopleInviteInfo']//a[contains(@href,'${name}')]/../..//a[contains(text(),'Accept')]";

  public static final String          ELEMENT_INVITATIONS_PEOPLE_REFUSE_BTN                                =
                                                                            "//div[@id='InvitationsPortlet']//div[@class='peopleInviteInfo']//a[contains(@href,'${name}')]/../..//i[@class='uiIconClose']";

  public static final By              ELEMENT_INVITATIONS_GADGET                                           =
                                                                 By.id("InvitationsPortlet");

  public static final String          ELEMENT_INVITATIONS_NUMBER                                           =
                                                                 "//*[@id='InvitationsPortlet']//*[contains(text(),'${number}')]";

  // suggestion gadget
  public static final By              ELEMENT_SUGGESTION_BOX                                               =
                                                             By.xpath(".//*[@class='uiBox uiSuggestions']");

  public static final String          ELEMENT_SUGGESTION_NAME                                              =
                                                              "//*[@id='peopleSuggest']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SUGGESTION_PEOPLE_CANCEL_BTN                                 =
                                                                           "	//*[@id='peopleSuggest']//*[contains(text(),'${name}')]/../..//*[@class='uiIconClose']";

  public static final String          ELEMENT_SUGGESTION_PEOPLE_CONNECT_BTN                                =
                                                                            "//*[@id='peopleSuggest']//*[contains(text(),'${name}')]/../..//a[contains(text(),'Connect')]";

  public static final String          ELEMENT_SUGGESTION_SPACE                                             =
                                                               "//*[@id='spaceSuggest']//*[contains(text(),'${space}')]";

  public static final String          ELEMENT_SUGGESTION_SPACE_REQUEST_BTN                                 =
                                                                           "//*[@id='spaceSuggest']//*[contains(text(),'${space}')]/..//*[contains(text(),'Request')]";

  public static final String          ELEMENT_SUGGESTION_SPACE_CANCEL_BTN                                  =
                                                                          "//*[@id='spaceSuggest']//*[contains(text(),'${space}')]/..//i[@class='uiIconClose']";

  // main frame
  public static final String          ELEMENT_PUBLICATION_TITLE                                            =
                                                                "//*[@title='${title}']";

  public static final String          ELEMENT_PUBLICATION_LIKE                                             =
                                                               "//*[@title='${title}']/../../../..//*[@class='uiIconThumbUp uiIconLightGray']";

  public static final String          ELEMENT_PUBLICATION_LIKED                                            =
                                                                "//*[@title='${title}']/../../../..//*[@class='uiIconThumbUp uiIconBlue']";

  public static final By              ELEMENT_PUBLICATION_WHOLIKED                                         =
                                                                   By.xpath("//*[@class='listLiked']//*[@class='avatarXSmall']");

  public static final By              ELEMENT_PUBLICATION_WHOLIKEDPOPUP                                    =
                                                                        By.xpath("//*[@id='tiptip_content']");

  public static final String          ELEMENT_PUBLICATION_COMMENT_CONTENT                                  =
                                                                          "//*[contains(@title,'${title}')]/../../../..//*[@class='uiIconComment uiIconLightGray']";

  public static final String          ELEMENT_PUBLICATION_COMMENT_STATUS                                   =
                                                                         "//*[text()='${title}']/../../../..//*[@class='uiIconComment uiIconLightGray']";

  public static final String          ELEMENT_PUBLICATION_COMMENTTEXTBOX_CONTENT                           =
                                                                                 "//*[@title='${title}']/../../../..//*[@class='replaceTextArea editable']";

  public static final String          ELEMENT_PUBLICATION_COMMENTTEXTBOX_STATUS                            =
                                                                                "//*[text()='${title}']/../../../..//*[@class='replaceTextArea editable']";

  public static final String          ELEMENT_PUBLICATION_CONTENT_COMMENT                                  =
                                                                          "//*[@class='contentComment' and contains(text(),'${comment}')]";

  public static final By              ELEMENT_PUBLICATION_COMMENTBTN                                       =
                                                                     By.xpath("//*[@data-original-title='Comment']");

  public static final String          ELEMENT_PUBLICATION_COMMENTBTN_STATUS                                =
                                                                            "//*[text()='${title}']/..//*[text()='Comment']";

  public static final String          ELEMENT_PUBLICATION_AUTHOR                                           =
                                                                 "//*[@title='${title}']/../../../..//*[@class='author']";

  public static final String          ELEMENT_PUBLICATION_DELETE                                           =
                                                                 "//*[@title='${title}']/../../../..//*[@class='uiIconClose uiIconLightGray controllDelete']";

  public static final String          ELEMENT_PUBLICATION_COMMENTORDER                                     =
                                                                       "//div[1]/form/div[3]/div/div/div[4]/div[2]/div[${number}]/div//*[contains(text(),'${comment}')]";

  public static final By              ELEMENT_PUBLICATION_FIRSTPOST_ACTIONBAR                              =
                                                                              By.xpath("//div[1]/form//*[@class='actionBar clearfix']");

  public static final By              ELEMENT_PUBLICATION_ADDFILE                                          =
                                                                  By.xpath("//*[@class='actionIcon uidocactivitycomposer']");

  public static final By              ELEMENT_PUBLICATION_ADDFILE_NEWFOLDER                                =
                                                                            By.xpath("//*[@class='uiIconEcmsAddFolder uiIconEcmsLightGray']");

  public static final By              ELEMENT_PUBLICATION_SHAREDFILE                                       =
                                                                     By.xpath("//*[@class='uiBox roundedBottom introBox fileShare']");

  public static final By              ELEMENT_PUBLICATION_ADDLINK                                          =
                                                                  By.xpath("//*[@class='actionIcon uilinkactivitycomposer']");

  public static final By              ELEMENT_PUBLICATION_ADDLINK_INPUT                                    =
                                                                        By.xpath("//*[@id='InputLink']");

  public static final By              ELEMENT_PUBLICATION_ADDLINK_ATTCHBTN                                 =
                                                                           By.xpath("//*[@id='AttachButton']");

  public static final By              ELEMENT_PUBLICATION_SHAREDLINK                                       =
                                                                     By.xpath("//*[@class='uiBox roundedBottom introBox linkShare']");

  public static final By              ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES                        =
                                                                                    By.xpath(".//*[@id='UIUserActivitiesDisplay']//span[contains(text(),'All Activities')]");

  public static final By              ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE_OPTION                       =
                                                                                     By.xpath("//*[@class='OptionItem' and text()='My Spaces']");

  public static final By              ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE                              =
                                                                              By.xpath("//*[@id='DisplayModesDropDown']/../..//*[text()='My Spaces']");

  public static final By              ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION_OPTION                    =
                                                                                        By.xpath("//*[@class='OptionItem' and text()='Connections']");

  public static final By              ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION                           =
                                                                                 By.xpath("//*[@id='DisplayModesDropDown']/../..//*[text()='Connections']");

  public static final By              ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION                  =
                                                                                          By.xpath("//*[@class='OptionItem' and text()='My Activities']");

  public static final By              ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES                         =
                                                                                   By.xpath("//*[@id='DisplayModesDropDown']/../..//*[text()='My Activities']");

  // public static final String ELEMENT_PUBLICATION_DISPLAYORDER =
  // "//div[${number}]/form//*[contains(text(), '${title}')]";
  public static final String          ELEMENT_PUBLICATION_DISPLAYORDER                                     =
                                                                       "//*[contains(@id,'UIActivitiesContainer')]/div[${number}]//*[@class='description' and text()='${title}']";

  /*
   * public static final String ELEMENT_PUBLICATION_DISPLAYORDER_WEBCONTENT =
   * "//div[${number}]/form//*[@class='uiIcon64x64Templateexo_webContent']";
   * public static final String ELEMENT_PUBLICATION_DISPLAYORDER_FILE =
   * "//div[${number}]/form//*[@class='uiIcon64x64FileDefault uiIcon64x64nt_file uiIcon64x64texthtml']"
   * ; public static final String ELEMENT_PUBLICATION_DISPLAYORDER_PRODUCT =
   * "//div[${number}]/form//*[@class='uiIcon64x64Templateacme_product']";
   */
  public static final String          ELEMENT_PUBLICATION_DISPLAYORDER_PRODUCT                             =
                                                                               "//*[contains(@id,'UIActivitiesContainer')]/div[${number}]//*[@class='uiIcon64x64Templateacme_product']";

  public static final String          ELEMENT_PUBLICATION_DISPLAYORDER_FILE                                =
                                                                            "//*[contains(@id,'UIActivitiesContainer')]/div[${number}]//*[@class='uiIcon64x64FileDefault uiIcon64x64nt_file uiIcon64x64texthtml']";

  public static final String          ELEMENT_PUBLICATION_DISPLAYORDER_WEBCONTENT                          =
                                                                                  "//*[contains(@id,'UIActivitiesContainer')]/div[${number}]//*[@class='uiIcon64x64Templateexo_webContent']";

  public static final String          ELEMENT_PUBLICATION_DISPLAYORDER_PDF                                 =
                                                                           "//div[${number}]/form//*[@data-original-title='ECMS_DMS_SE_Upload_pdffile.pdf']//*[@class='pdfImageBorder']";

  // add file box
  public static final By              ELEMENT_PUBLICATION_ADDFILE_LOCATION                                 =
                                                                           By.xpath("//*[@class='uiIconGeneralDrive uiIconLightGray']");

  public static final By              ELEMENT_PUBLICATION_ADDFILE_LOCATION_GENERALDRIVES                   =
                                                                                         By.xpath("//*[@class='uiIconGeneralDrive uiIconLightGray']/../..//*[contains(text(),'General Drives')]");

  public static final By              ELEMENT_PUBLICATION_ADDFILE_LOCATION_GENERALDRIVES_MANAGEDSITES      =
                                                                                                      By.xpath("//*[@data-original-title='Managed Sites']");

  public static final String          ELEMENT_PUBLICATION_ADDFILE_LOCATION_GENERALDRIVES_MANAGEDSITES_FILE =
                                                                                                           "//*[@data-original-title='${title}']";

  public static final By              ELEMENT_PUBLICATION_ADDFILE_UPLOAD                                   =
                                                                         By.xpath("//*[@class='uiIconUpload uiIconLightGray']");

  // confirm popup

  public static final SelenideElement ELEMENT_DELETE_POPUP_OK                                              =
                                                              $(byXpath("//*[@id=\"UISocialPopupConfirmation\"]/div[2]/div[2]/div/a[1]"));

  public static final SelenideElement ELEMENT_WHO_LIKED_POPUP                                              =
                                                              $(byClassName("listLikedBox "));

  public static final String          ELEMENT_CONTAINER_ACTIVITY                                           =
                                                                 "activityContainer{id}";

  public static final String          ELEMENT_DATE_ACTIVITY                                                = "dateTime";

  public static final String          ELEMENT_DELETE_ACTIVITY                                              =
                                                              "DeleteActivityButton{id}";

  public static final SelenideElement ELEMENT_ICON_SEARCH                                                  =
                                                          $(byClassName("TRContainer")).find(byClassName("uiIconPLF24x24Search"));

  public static final SelenideElement ELEMENT_SEARCH_INPUT                                                 =
                                                           $(byClassName("showInputSearch"));

  public static final SelenideElement ELEMENT_SEARCH_RESULT                                                =
                                                            $(byId("quickSearchResult1"));

  public static final String          ELEMENT_COMMENT_BLOC                                                 = "CommentBlock{id}1";

  public static final SelenideElement ELEMENT_SPACE_FILE_TAB                                                     =
                                                       $(byXpath("//*[@id=\"ActivityComposerExt\"]/div[3]"));

  public static final SelenideElement ELEMENT_TAB_LINK                                                     =
                                                       $(byXpath("//*[@id=\"ActivityComposerExt\"]/div[2]"));

  public static final SelenideElement ELEMENT_CONTAINER_DOCUMENT                                           =
                                                                 $(byId("UIActivityComposerContainer"));

  public static final SelenideElement ELEMENT_INPUT_DOCUMENT                                               =
                                                             $(byClassName("multiploadFilesSelector")).find(byClassName("file"));

  public static final SelenideElement ELEMENT_BAR_PROGRESS                                                 =
                                                           $(byClassName("progress-striped"));

  public static final String          ELEMENT_DOCUMENT_PREVIEW                                             = "Preview{id}-0";

  public static final SelenideElement ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW                            =
          $(byClassName("commentTextInput"));

  public static final SelenideElement ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW                           =
                                                                                 $(byId("CommentButton"));

  public static final By              ELEMENT_ICON_LIKE_COMMENT                                            =
                                                                byClassName("uiIconThumbUp");

  public static final SelenideElement ELEMENT_CLOSE_DOCUMENT_PREVIEW                                       =
                                                                     $(byXpath("//*[@id=\"uiDocumentPreview\"]/div[1]/a"));

  public static final By              ELEMENT_DELETE_ACTIVITY_CONTAINS_DOC                                 =
                                                                           byClassName("uiIconClose");

  public static final SelenideElement ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW                         =
                                                                                   $(byId("commentArea"));

  public static final SelenideElement ELEMENT_ICON_NOTIFICATION                                            =
                                                                $(byXpath("//*[@id=\"UINotificationPopoverToolbarPortlet\"]/div[2]/a"));

  public static final SelenideElement ELEMENT_ALERT_NOTIFICATION                                           =
                                                                 $(byClassName("badgeNotification"));

  public static final SelenideElement ELEMENT_NOTIFICATION_POPUP                                           =
                                                                 $(byId("NotificationPopup"));

  public static final SelenideElement ELEMENT_HP_GETTING_STARTED_SET_YOUR_PROFILE_PICTURE                  =
                                                                                          $(byXpath("//*[@id=\"gsList\"]/li[1]/a"));

  public static final SelenideElement ELEMENT_HP_GETTING_STARTED_CONNECT_WITH_OTHERS                       =
                                                                                     $(byXpath("//*[@id=\"gsList\"]/li[2]/a"));

  public static final SelenideElement ELEMENT_HP_GETTING_STARTED_JOIN_A_SPACE                              =
                                                                              $(byXpath("//*[@id=\"gsList\"]/li[3]/a"));

  public static final SelenideElement ELEMENT_HP_GETTING_STARTED_POST_A_MESSAGE                            =
                                                                                $(byXpath("//*[@id=\"gsList\"]/li[4]/a"));

  public static final SelenideElement ELEMENT_HP_GETTING_STARTED_UPLOAD_A_DOCUMENT                         =
                                                                                   $(byXpath("//*[@id=\"gsList\"]/li[5]/a"));

  public static final String          ELEMENT_BUTTON_CONNECT_TO_USER                                       =
                                                                     "//*[@id=\"{idbtn}\"]/div[2]/button";

  public static final SelenideElement ELEMENT_BUTTON_EDIT_PROFILE                                          =
                                                                  $(byXpath("//*[@id=\"UIExperienceProfilePortlet\"]/div/button"));

  public static final SelenideElement ELEMENT_BUTTON_CHANGE_AVATAR                                         =
                                                                   $(byXpath("//*[@id=\"Avatar\"]/div[2]"));

  public static final SelenideElement ELEMENT_INPUT_UPLOAD_AVATAR                                          =
                                                                  $(byId("Uploader")).find(byClassName("file"));

  public static final SelenideElement ELEMENT_BUTTON_CONFIRM_UPLOAD                                        =
                                                                    $(byXpath("//*[@id=\"UIAvatarUploader\"]/div[3]/button[1]"));

  public static final SelenideElement ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR                                    =
                                                                        $(byXpath("//*[@id=\"UIAvatarUploadContent\"]/div[2]/button[1]"));

  public static final SelenideElement ELEMENT_CALENDAR_CONTAINER                                           =
                                                                 $(byClassName("currentDateContainer"));

  public static final SelenideElement ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT               = $(
                                                                                                               byClassName("DisplayedCalendarContainer")).find(byAttribute("data-original-title", "Content Management")).parent().find(byClassName("uiIconDel"));

  public static final SelenideElement ELEMENT_CONTAINER_NO_DISPALYED_CALENDAR                              =
                                                                              $(byId("nonDisplayedCalendarContainer"));

  public static final SelenideElement ELEMENT_SUGGETION_SPACE                                              =
                                                              $(byId("spaceSuggest"));

  public static final SelenideElement ELEMENT_GADGET_INVITATION                                            =
                                                                $(byId("InvitationsPortlet"));

  public static final By              ELEMENT_ICON_DELETE_ACTIVITY                                         =
                                                                   byClassName("uiIconClose");

  public static final SelenideElement ELEMENT_TAB_ADD_LINK                                                 =
                                                           $(byXpath("//*[@id=\"ActivityComposerExt\"]/div[3]/a"));

  public static final SelenideElement ELEMENT_INPUT_LINK                                                   = $(byId("InputLink"));

  public static final SelenideElement ELEMENT_BUTTON_ATTACH_LINK                                           =
                                                                 $(byId("AttachButton"));

  public static final By              ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET                              =
                                                                              byClassName("connect");

  public static final By              ELEMENT_BUTTON_CANCEL_SUGGESTION_USER_FROM_GADGET                    =
                                                                                        byClassName("uiIconClose");

  public static final SelenideElement ELEMENT_GADGET_USER_SUGGESTION                                       = $(byId("content"));

  public static final String          ELEMNT_BUTTON_REQUEST_SPACE_FROM_GADGET                              =
                                                                              "//*[@id=\"{id}\"]/div[2]/div[2]/div[1]/a[1]";

  public static final SelenideElement ELEMENT_GAGET_SUGGESTION_SPACE                                       =
                                                                     $(byId("spaceSuggest"));

  public static final SelenideElement ELEMENT_USER_PROFILE                                    =
          $(byXpath("//*[@id=\"UIUserPlatformToolBarPortlet\"]/ul/li[4]/a"));


  public static final SelenideElement ELEMENT_POPUP_LIST_OF_LANGUAGES                                      =
                                                                      $(byClassName("uiChangeLanguageForm"));

  public static final SelenideElement ELEMENT_BUTTON_CANCEL_CHANGE_LANGUAGES                               =
                                                                             $(byXpath("//*[@id=\"UIMaskWorkspace\"]/div/div/div/div[2]/a[2]"));

  public static final SelenideElement ELEMENT_BUTTON_SHOW_MORE_SPACES_IN_LEFT_NAVIGATION=$(byId("LeftNavigation")).find(byClassName("moreSpace")).find(byClassName("btn"));

  public static final SelenideElement ELEMENT_ICON_LIKE_IN_PREVIEW_MODE=$(byId("previewLikeLink"));
  public static final SelenideElement ELEMENT_LINK_TEXT_SELECT_FROM_EXISTING_UPLOAD=$(byXpath("//*[@id=\"DropFileBox\"]/span[5]/a"));

  public static final SelenideElement ELEMENT_TEXT_DOCUMENT_IN_DOC_ACTIVITY_POPUP=$(byXpath("//*[@id=\"BreadcumbsContainer\"]/li[2]/a"));

  public static final SelenideElement ELEMENT_SITE_MANAGEMENT_IN_DOC_ACTIVITY_POPUP=$(byClassName("uiIconEcms24x24DriveManagedSites"));

  public static final SelenideElement ELEMENT_POPUP_DOC_ACTIVITY=$(byId("UIDocumentSelectorTab"));

  public static final SelenideElement ELEMENT_BUTTON_ATTASH_FILE_IN_DOC_ACTIVITY_POPUP=$(byXpath("//*[@id=\"UIDocActivityPopup\"]/div[2]/div[2]/button[1]"));

public static final SelenideElement ELEMENT_ACTIVITY_STREAM_CONTAINER= $(byClassName("uiUserActivitiesContainer"));

public static final By ELEMENT_BOX_ACTIVITY=byId("boxContainer");
public static final SelenideElement ELEMENT_ACTIVITY_CONTAINER=$(byClassName("OfficeMiddleTDContainer"));
}
