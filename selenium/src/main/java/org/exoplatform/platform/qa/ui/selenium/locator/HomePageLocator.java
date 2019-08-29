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

import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

/**
 * Location of elements of eXo Platform home page
 */
public final class HomePageLocator {

  public static final SelenideElement    ELEMENT_PLF_HOMEPAGE_DISPLAY                           = $("li.navItemSelected");

  public static final SelenideElement    ELEMENT_PLF_HOMEPAGE_ACTIVITY_PORTLET                  = $("#UIUserActivityStreamPortlet");

  public static final SelenideElement    ELEMENT_PLF_HOMEPAGE_GADGET_PORTLET                    = $("#OfficeRightMiddle");

  // Left panel
  public static final By                 ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON                  = byId("ActivitiesLoader");

  public static final By                 ELEMENT_FORUM_LINK_PLF                                 = By.xpath("//span[@data-original-title='Forums']");

  public static final By                 ELEMENT_ANSWER_LINK_PLF                                = By.xpath("//*[@data-original-title='Answers']");

  public static final SelenideElement    ELEMENT_WIKI_LINK_PLF                                  = $("#MenuItemwiki");

  public static final SelenideElement    ELEMENT_DOCUMENTS_LINK_PLF                             = $("#MenuItemdocuments");

  public static final SelenideElement    ELEMENT_PEOPLE_LINK_PLF                                = $("#MenuItemconnexions");

  public static final SelenideElement    ELEMENT_HOME_LINK_PLF                                  = $("#MenuItemhome");

  public static final SelenideElement    ELEMENT_CALENDAR_LINK_PLF                              = $(byClassName("uiIconPLFCalendar"));

  public static final SelenideElement    ELEMENT_TASKS_LINK_PLF                                 = $(byClassName("uiCompanyNavigations")).find(byText("Tasks"));

  public static final By                 ELEMENT_HOME_LINK_PLF_IN_FRENCH                        = By.xpath("//*[@data-original-title='Accueil']");

  public static final By                 ELEMENT_CONNECTIONS_LINK_PLF                           = By.xpath("//*[@class='uiCompanyNavigations']//*[contains(@class,'uiIconPLFMyConnection')]");

  public static final SelenideElement    ELEMENT_SEARCH_SPACE                                   = $("#UISpaceNavigationPortlet div.searchInput input.searchText");

  /**
   * Get the list of spaces links in the left menu navigation
   */
  public static final ElementsCollection ELEMENT_SEARCH_SPACE_RESULTS                           = $$("#UISpaceNavigationPortlet ul.spaceNavigation li");

  public static final SelenideElement    ELEMENT_SPECIFIC_PANEL                                 = $(byId("UISpaceNavigationPortlet"));

  public static final SelenideElement    ELEMENT_MY_SPACE_LINK_PLF                              = $("#MySpacesItem");

  public static final SelenideElement    ELEMENT_SECOND_SPACE_MY_SPACE                          = $(byClassName("spaceNavigation")).findAll(byClassName("spaceItem")).get(1);

  public static final By                 ELEMENT_ALL_SPACE_JOIN_LINK                            = By.cssSelector("#UISpaceNavigationPortlet .uiIconPLFMan");

  // Middle homepage panel
  public static final By                 ELEMENT_HOMPAGE_MIDDLE_PANEL                           = By.id("OfficeMiddle");

  public static final By                 ELEMENT_HOMEPAGE_DROP_MENU_ARROW                       = By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'uiIconMiniArrowDown')]");

  public static final By                 ELEMENT_HOMEPAGE_DROP_MENU_MY_ACTIVITIES               =
                                                                                  By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'My Activities')]");

  public static final By                 ELEMENT_HOMEPAGE_DROP_MENU_ALL_ACTIVITIES              =
                                                                                   By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'All Activities')]");

  public static final By                 ELEMENT_HOMEPAGE_DROP_MENU_MY_SPACES                   =
                                                                              By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'My Spaces')]");

  public static final By                 ELEMENT_HOMEPAGE_DROP_MENU_CONNECTIONS                 =
                                                                                By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'dropdown-menu')]//*[contains(text(),'Connections')]");

  // Tool bar
  public static final By                 ELEMENT_TOOLBAR_ADMINISTRATION                         = By.xpath("//*[@class='uiIconPLF24x24Setup']");

  // top panel
  // edit
  public static final By                 ELEMENT_EDIT_PAGE                                      =
                                                           By.xpath("//*[@id='UIAdminToolbarContainer']//*[@class='dropdown-submenu']//*[@href='#' and contains(text(), 'Page')]");

  public static final By                 ELEMENT_EDIT_PAGE_EDITLAYOUT                           = By.xpath("//*[contains(text(), 'Edit Layout')]");

  public static final SelenideElement    ELEMENT_DELETE_ECM_ADMIN_PAGE_EDITLAYOUT               =
                                                                                  $(byXpath("(//span[contains(text(),'ECM Admin')]/following::a[@data-original-title=\"Delete Portlet\"])[1]"));

  public static final By                 ELEMENT_ECM_ADMIN_PORTLET_LAYOUT                       = By.xpath("//div[@class=\"portletLayoutDecorator\" and contains(text(),\"ECM Admin\")]");

  // User
  public static final By                 ELEMENT_TOPBAR_AVATAR                                  = By.xpath("//*[@alt='avatar']");

  // Edit panel
  public static final By                 ELEMENT_EDIT_BUTTON                                    = By.xpath(".//*[@id='UIAdminToolbarContainer']//*[@class='uiIconPLF24x24Edit']");

  // calendar gadget
  public static final By                 ELEMENT_HP_CALENDARGADGET_BOX                          = By.xpath("//*[@class='calendarPortletData uiBox']");

  public static final By                 ELEMENT_HP_CALENDARGADGET_RIGHTARROW                   = By.xpath("//*[@class='uiIconMiniArrowRight uiIconLightGray']");

  public static final By                 ELEMENT_HP_CALENDARGADGET_LEFTARROW                    = By.xpath("//*[@class='uiIconMiniArrowLeft uiIconLightGray']");

  public static final By                 ELEMENT_HP_CALENDARGADGET_SETTINGS                     = By.xpath("//*[@class='uiIconSetting uiIconLightGray']");

  public static final By                 ELEMENT_HP_CALENDARGADGET_SETTINGS_DISPLAYEDCAL        = By.xpath("//*[contains(text(),'Displayed Calendars')]");

  public static final By                 ELEMENT_HP_CALENDARGADGET_SETTINGS_SETTINGSCAL         = By.xpath("//*[contains(text(),'Displayed Calendars')]/../..//*[contains(text(),'Settings')]");

  public static final By                 ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCAL              = By.xpath("//*[@class='uiIconSimplePlusMini uiIconLightGray']");

  public static final By                 ELEMENT_HP_CALENDARGADGET_SETTINGS_SEARCHCAL           = By.xpath("//*[@class='PLFcalendarSearchKey']");

  // Getting Started
  public static final By                 ELEMENT_HP_GETTINGSTARTED_BOX                          = By.xpath("//*[@class='UIGadgetThemes uiBox uiGettingStarted']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_TITLE                        = By.xpath(".//*[@class='GettingStarted']//h6[text()='Getting Started']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_ACCEPTTOCOWORKERSBTN         = By.xpath("//*[text()='Confirm']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS_DONE       = By.xpath("//*[@class='done']/..//*[text()='Connect to coworkers']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_PROFILEPAGE                  = By.xpath("//*[@class='uiIconAppprofile uiIconDefaultApp']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_CONNECTIONPAGE               = By.xpath("//*[@class='uiIconAppconnections uiIconDefaultApp']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_CHECKJOINSPACE               = By.xpath("//*[@class='active']//*[text()='All Spaces']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_CHECKUPLOADDOC               = By.xpath("//*[@id='UIJCRExplorerPortlet']");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_PROGRESSRATE                 = By.xpath("//*[@id='progress-rate']/../../../..//*[contains(text(), '100 %')]");

  public static final By                 ELEMENT_HP_GETTINGSTARTED_CLOSEBOX                     = By.xpath("//*[@class='gadgetTitle title center']//*[@class='uiIconClose pull-right']");

  // Who's online gadget
  public static final SelenideElement    ELEMENT_WHO_ON_LINE_GADGET                             = $(byId("onlineContent"));

  public static final By                 ELEMENT_WHO_ONLINE_CONNECT                             = By.xpath("//*[@id='tiptip_content']//*[contains(text(),'Connect')]");

  public static final By                 ELEMENT_WHO_ONLINE_CANCEL_CONNECT                      = By.xpath("//*[@id='tiptip_content']/div//*[contains(text(),'Cancel Request')]");

  // invitation gadget

  public static final String             ELEMENT_INVITATIONS_PEOPLE_AVATAR                      =
                                                                           ".//*[contains(text(),'${name}')]/../../../..//*[@class='peopleInvitePicture pull-left avatarXSmall']";

  public static final By                 ELEMENT_INVITATIONS_GADGET                             = By.id("InvitationsPortlet");

  // suggestion gadget
  public static final By                 ELEMENT_SUGGESTION_BOX                                 = By.xpath(".//*[@class='uiBox uiSuggestions']");

  public static final String             ELEMENT_SUGGESTION_NAME                                = "//*[@id='peopleSuggest']//*[contains(text(),'${name}')]";

  public static final By                 ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES          = By.xpath(".//*[@id='UIUserActivitiesDisplay']//span[contains(text(),'All Activities')]");

  public static final By                 ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE_OPTION         = By.xpath("//*[@class='OptionItem' and text()='My Spaces']");

  public static final By                 ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION_OPTION      = By.xpath("//*[@class='OptionItem' and text()='Connections']");

  public static final By                 ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION    = By.xpath("//*[@class='OptionItem' and text()='My Activities']");
  // confirm popup

  public static final SelenideElement    ELEMENT_DELETE_POPUP_OK                                = $(byXpath("//*[@id=\"UISocialPopupConfirmation\"]/div[2]/div[2]/div/a[1]"));

  public static final SelenideElement    ELEMENT_WHO_LIKED_POPUP                                = $(byClassName("listLikedBox "));

  public static final String             ELEMENT_CONTAINER_ACTIVITY                             = "activityContainer{id}";

  public static final String             ELEMENT_DATE_ACTIVITY                                  = "dateTime";

  public static final String             ELEMENT_DELETE_ACTIVITY                                = "DeleteActivityButton{id}";

  public static final SelenideElement    ELEMENT_ICON_SEARCH                                    = $(byClassName("TRContainer")).find(byClassName("uiIconPLF24x24Search"));

  public static final SelenideElement    ELEMENT_SEARCH_INPUT                                   = $(byClassName("showInputSearch"));

  public static final SelenideElement    ELEMENT_SEARCH_RESULT                                  = $(byId("quickSearchResult1"));

  public static final String             ELEMENT_COMMENT_BLOC                                   = "CommentBlock{id}1";

  public static final SelenideElement    ELEMENT_SPACE_FILE_TAB                                 = $(byXpath("//*[@id=\"ActivityComposerExt\"]/div[3]"));

  public static final SelenideElement    ELEMENT_TAB_LINK                                       = $(byXpath("//*[@id=\"ActivityComposerExt\"]/div[2]"));

  public static final SelenideElement    ELEMENT_CONTAINER_DOCUMENT                             = $(byId("UIActivityComposerContainer"));

  public static final SelenideElement    ELEMENT_INPUT_DOCUMENT                                 = $(byClassName("multiploadFilesSelector")).find(byClassName("file"));

  public static final SelenideElement    ELEMENT_BAR_PROGRESS                                   = $(byClassName("progress-striped"));

  public static final String             ELEMENT_DOCUMENT_PREVIEW                               = "Preview{id}-0";

  public static final SelenideElement    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW              = $(byClassName("commentTextInput"));

  public static final SelenideElement    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW             = $(byId("CommentButton"));

  public static final By                 ELEMENT_ICON_LIKE_COMMENT                              = byClassName("uiIconThumbUp");

  public static final SelenideElement    ELEMENT_CLOSE_DOCUMENT_PREVIEW                         = $(byXpath("//*[@id=\"uiDocumentPreview\"]/div[1]/a"));

  public static final SelenideElement    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW           = $(byId("commentArea"));

  public static final SelenideElement    ELEMENT_ICON_NOTIFICATION                              = $(byXpath("//*[@id=\"UINotificationPopoverToolbarPortlet\"]/div[2]/a"));

  public static final SelenideElement    ELEMENT_ALERT_NOTIFICATION                             = $(byClassName("badgeNotification"));

  public static final SelenideElement    ELEMENT_NOTIFICATION_POPUP                             = $(byId("NotificationPopup"));

  public static final SelenideElement    ELEMENT_HP_GETTING_STARTED_SET_YOUR_PROFILE_PICTURE    = $(byXpath("//*[@id=\"gsList\"]/li[1]/a"));

  public static final SelenideElement    ELEMENT_HP_GETTING_STARTED_CONNECT_WITH_OTHERS         = $(byXpath("//*[@id=\"gsList\"]/li[2]/a"));

  public static final SelenideElement    ELEMENT_HP_GETTING_STARTED_JOIN_A_SPACE                = $(byXpath("//*[@id=\"gsList\"]/li[3]/a"));

  public static final SelenideElement    ELEMENT_HP_GETTING_STARTED_POST_A_MESSAGE              = $(byXpath("//*[@id=\"gsList\"]/li[4]/a"));

  public static final SelenideElement    ELEMENT_HP_GETTING_STARTED_UPLOAD_A_DOCUMENT           = $(byXpath("//*[@id=\"gsList\"]/li[5]/a"));

  public static final String             ELEMENT_BUTTON_CONNECT_TO_USER                         = "//*[@id=\"{idbtn}\"]/div[2]/button";

  public static final SelenideElement    ELEMENT_BUTTON_CHANGE_AVATAR                           = $(byXpath("//*[@id=\"Avatar\"]/div[2]"));

  public static final SelenideElement    ELEMENT_INPUT_UPLOAD_AVATAR                            = $(byId("Uploader")).find(byClassName("file"));

  public static final SelenideElement    ELEMENT_BUTTON_CONFIRM_UPLOAD                          = $(byXpath("//*[@id=\"UIAvatarUploader\"]/div[3]/button[1]"));

  public static final SelenideElement    ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR                      = $(byXpath("//*[@id=\"UIAvatarUploadContent\"]/div[2]/button[1]"));

  public static final SelenideElement    ELEMENT_CALENDAR_CONTAINER                             = $(byClassName("currentDateContainer"));

  public static final SelenideElement    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT = $(byClassName("DisplayedCalendarContainer"))
                                                                                                                                              .find(byAttribute("data-original-title",
                                                                                                                                                                "Content Management"))
                                                                                                                                              .parent()
                                                                                                                                              .find(byClassName("uiIconDel"));

  public static final SelenideElement    ELEMENT_CONTAINER_NO_DISPALYED_CALENDAR                = $(byId("nonDisplayedCalendarContainer"));

  public static final SelenideElement    ELEMENT_SUGGETION_SPACE                                = $(byId("spaceSuggest"));

  public static final SelenideElement    ELEMENT_GADGET_INVITATION                              = $(byId("InvitationsPortlet"));

  public static final SelenideElement    ELEMENT_TAB_ADD_LINK                                   = $(byXpath("//*[@id=\"ActivityComposerExt\"]/div[3]/a"));

  public static final SelenideElement    ELEMENT_INPUT_LINK                                     = $(byId("InputLink"));

  public static final SelenideElement    ELEMENT_BUTTON_ATTACH_LINK                             = $(byId("AttachButton"));

  public static final By                 ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET                = byClassName("connect");

  public static final By                 ELEMENT_BUTTON_CANCEL_SUGGESTION_USER_FROM_GADGET      = byClassName("uiIconClose");

  public static final SelenideElement    ELEMENT_GADGET_USER_SUGGESTION                         = $(byId("content"));

  public static final String             ELEMNT_BUTTON_REQUEST_SPACE_FROM_GADGET                = "//*[@id=\"{id}\"]/div[2]/div[2]/div[1]/a[1]";

  public static final SelenideElement    ELEMENT_GAGET_SUGGESTION_SPACE                         = $(byId("spaceSuggest"));

  public static final SelenideElement    ELEMENT_USER_PROFILE                                   = $(byXpath("//*[@id=\"UIUserPlatformToolBarPortlet\"]/ul/li[4]/a"));

  public static final SelenideElement    ELEMENT_POPUP_LIST_OF_LANGUAGES                        = $(byClassName("uiChangeLanguageForm"));

  public static final SelenideElement    ELEMENT_BUTTON_CANCEL_CHANGE_LANGUAGES                 = $(byXpath("//*[@id=\"UIMaskWorkspace\"]/div/div/div/div[2]/a[2]"));

  public static final SelenideElement    ELEMENT_BUTTON_SHOW_MORE_SPACES_IN_LEFT_NAVIGATION     = $(byId("LeftNavigation")).find(byClassName("moreSpace")).find(byClassName("btn"));

  public static final SelenideElement    ELEMENT_ICON_LIKE_IN_PREVIEW_MODE                      = $(byId("previewLikeLink"));

  public static final SelenideElement    ELEMENT_LINK_TEXT_SELECT_FROM_EXISTING_UPLOAD          = $(byXpath("//*[@id=\"DropFileBox\"]/span[5]/a"));

  public static final SelenideElement    ELEMENT_TEXT_DOCUMENT_IN_DOC_ACTIVITY_POPUP            = $(byXpath("//*[@id=\"BreadcumbsContainer\"]/li[2]/a"));

  public static final SelenideElement    ELEMENT_SITE_MANAGEMENT_IN_DOC_ACTIVITY_POPUP          = $(byClassName("uiIconEcms24x24DriveManagedSites"));

  public static final SelenideElement    ELEMENT_POPUP_DOC_ACTIVITY                             = $(byId("UIDocumentSelectorTab"));

  public static final SelenideElement    ELEMENT_BUTTON_ATTASH_FILE_IN_DOC_ACTIVITY_POPUP       = $(byXpath("//*[@id=\"UIDocActivityPopup\"]/div[2]/div[2]/button[1]"));

  public static final SelenideElement    ELEMENT_ACTIVITY_STREAM_CONTAINER                      = $(byClassName("uiUserActivitiesContainer"));

  public static final By                 ELEMENT_BOX_ACTIVITY                                   = byId("boxContainer");

  public static final SelenideElement    ELEMENT_ACTIVITY_CONTAINER                             = $(byClassName("OfficeMiddleTDContainer"));
}
