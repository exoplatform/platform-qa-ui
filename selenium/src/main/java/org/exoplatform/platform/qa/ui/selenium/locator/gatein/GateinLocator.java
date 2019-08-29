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
package org.exoplatform.platform.qa.ui.selenium.locator.gatein;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public final class GateinLocator {

  // Permission tab
  public static final By              ELEMENT_ANSWER_PERMISSION_TAB                           = By.xpath("//*[@data-target='#PortletPermission-tab']");

  public static final By              ELEMENT_ANSWER_PERMISSION_TAB_PUBLIC_MODE               = By.id("publicMode");

  // Display mode tab
  public static final By              ELEMENT_DISPLAY_MODE_TAB                                = By.xpath("//button[text()='Display Mode']");

  public static final By              ELEMENT_SELECT_DISPLAY_MODE                             = By.name("display-mode");

  public static final By              ELEMENT_SELECT_ORDER_BY_MODE                            = By.name("order-by");

  public static final By              ELEMENT_SELECT_DIRECTION_MODE                           = By.name("order-type");

  public static final By              ELEMENT_ENABLE_VOTE_COMMENT                             = By.id("enableVotComment");

  public static final By              ELEMENT_ENABLE_RSS                                      = By.id("enableRSS");

  public static final By              ELEMENT_VIEW_AVATAR                                     = By.id("enableViewAvatar");

  public static final By              ELEMENT_POST_QUESTION_IN_ROOT                           = By.id("isPostQuestionInRootCategory");

  // Category scoping tab
  public static final By              ELEMENT_CATEGORY_SCOPING_TAB                            = By.xpath("//button[text()='Category Scoping']");

  public static final String          ELEMENT_CATEGORY_IN_SCOPE_TAB                           = "//*[contains(text(),'${catName}')]/..//input[@type='checkbox']";

  // Discussion tab
  public static final By              ELEMENT_DISCUSSION_TAB                                  = By.xpath("//button[text()='Discussion']");

  public static final By              ELEMENT_ENABLE_DISCUSSION_CHECKBOX                      = By.id("EnableDiscuss");

  public static final By              ELEMENT_ADD_FORUM                                       = By.xpath("//*[@data-original-title='Select Forum']");

  public static final String          ELEMENT_CATEGORY_EXPAND_ITEM                            = "//*[@class='uiIconNode expandIcon nodeSelected']/*[text()='$name']";

  public static final String          ELEMENT_CATEGORY_COLLAPSE_ITEM                          = "//*[@class='uiIconNode collapseIcon']/*[text()='$name']";

  public static final String          ELEMENT_CATEGORY_NODE_ITEM                              = "//*[@class='uiIconNode uiIconEmpty']/*[text()='$name']";

  // Email tab
  public static final By              ELEMENT_MAIL_NOTIFICATION_TEMPLATE_TAB                  = By.xpath("//button[text()='Email Notifications']");

  public static final By              ELEMENT_MAIL_NEW_QUESTION_TAB                           = By.xpath("//button[text()='New Question']");

  public static final By              ELEMENT_MAIL_EDIT_ANSWER_TAB                            = By.xpath("//button[text()='Edit/answer']");

  public static final By              ELEMENT_MAIL_MOVE_QUESTION_TAB                          = By.xpath("//button[text()='Move Question']");

  public static final By              ELEMENT_MAIL_MOVE_QUESTION_FRAME                        = By.xpath("//div[@id='cke_EmailMoveQuestion']//iframe");

  public static final By              ELEMENT_MAIL_EDIT_ANSWER_FRAME                          = By.xpath("//div[@id='cke_EmailEditQuestion']//iframe");

  public static final By              ELEMENT_MAIL_NEW_QUESTION_FRAME                         = By.xpath("//div[@id='cke_EmailAddNewQuestion']//iframe");

  public static final By              ELEMENT_EDIT_ANSWER_RELOAD_DEFAULT_EMAIL                = By.xpath("//*[@for='EmailEditQuestion']/..//*[@class='uiIconRefresh uiIconLightGray']");

  public static final By              ELEMENT_NEW_QUESTION_RELOAD_DEFAULT_EMAIL               = By.xpath("//*[@for='EmailAddNewQuestion']/..//*[@class='uiIconRefresh uiIconLightGray']");

  public static final By              ELEMENT_MOVE_QUESTION_RELOAD_DEFAULT_EMAIL              = By.xpath("//*[@for='EmailMoveQuestion']/..//*[@class='uiIconRefresh uiIconLightGray']");

  public static final By              ELEMENT_MANAGE_APPLICATION_BUTTON                       = By.xpath("//*[@class='uiIconManageApplication uiIconLightGray']");

  public static final By              ELEMENT_APPLICATION_REGISTRY_ADD_CATEGORY_BTN           = By.xpath(".//*[contains(@class,'uiIconManageCategory uiIconLightGray')]");

  public static final String          ELEMENT_SELECT_RIGHT_PARENT_GROUP                       = "//*[@title='$group']";

  // Add category page
  public static final By              ELEMENT_ADD_CATEGORY_NAME                               = By.id("name");

  public static final By              ELEMENT_ADD_CATEGORY_DISPLAY_NAME                       = By.id("displayName");

  public static final By              ELEMENT_ADD_CATEGORY_DESCRIPTION                        = By.id("description");

  public static final By              ELEMENT_ADD_CATEGORY_SAVE_BTN                           = By.xpath(".//*[@id='UICategoryForm']//button[text()='Save']");

  public static final By              ELEMENT_ADD_CATEGORY_PERMISSION_TAB                     = By.xpath(".//*[contains(@data-target,'#categoryPermission-tab')]");

  public static final By              ELEMENT_ADD_CATEGORY_PERMISSION_PUBLIC_CHECKBOX         = By.xpath(".//*[@id='publicMode']");

  public static final By              ELEMENT_ADD_CATEGORY_ADD_PERMISSION_BTN                 = By.xpath("//*[contains(text(),'Add Permission')]");

  public static final String          ELEMENT_EDIT_PORTLET_DELETE_PERMISSION_ICON             = "//*[@id='PermissionGrid']//*[contains(@onclick,'$group')]/*[contains(@class,'uiIconDelete')]";

  // Application registry page
  public static final By              ELEMENT_SHOW_IMPORT_APPLICATION                         = By.id("showImport");

  public static final By              ELEMENT_IMPORT_ALL_APPLICATION                          = By.xpath("//*[@class='uiIconImport uiIconLightGray']");

  public static final By              ELEMENT_APPLICATION_GADGETBTN                           = By.cssSelector(".uiIconGadgets.uiIconLightGray");

  // Left panel
  public static final String          ELEMENT_LEFT_PANEL_ADD_APPLICATION_BTN                  = ".//*[contains(@href,'#${category}')]/../..//*[@class='uiIconPlus uiIconLightGray']";

  public static final String          ELEMENT_LEFT_PANEL_APPLICATION_NAME                     = ".//*[@id='${category}']//*[contains(@data-original-title,'${application}')]";

  public static final String          ELEMENT_LEFT_PANEL_APPLICATION_DELETE_BTN               =
                                                                                ".//*[contains(@data-original-title,'${application}')]/..//*[contains(@class,'uiIconTrashMini uiIconLightGray')]";

  public static final String          ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB             = ".//*[@id='ApplicationRegistryCategory']//*[@href='#${category}']";

  public static final String          ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN      = ".//*[@href='#${category}']/../..//*[contains(@class,'uiIconDelete uiIconLightGray')]";

  public static final String          ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_EDIT_BTN        = ".//*[@href='#${category}']/../..//*[contains(@class,'uiIconEdit uiIconLightGray')]";

  // Right panel Add Application
  public static final By              ELEMENT_RIGHT_PANEL_ADD_APPLICATION_DISPLAY_FILED       = By.id("displayName");

  public static final By              ELEMENT_RIGHT_PANEL_ADD_APPLICATION_SAVE_BTN            = By.xpath(".//*[@id='UIAddApplicationForm']//button[text()='Add']");

  public static final String          ELEMENT_RIGHT_PANEL_ADD_APPLICATION_RADIOBTN            =
                                                                                   ".//*[contains(@id,'description')][text()='${des}']/../..//*[contains(@id,'label')][text()='${name}']/../..//input[@type='radio']";

  public static final String          ELEMENT_RIGHT_PANEL_ADD_APPLICATION_NAME                = ".//*[contains(@id,'description')][text()='${des}']/../..//*[contains(@id,'label')][text()='${name}']";

  public static final By              ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED                = By.xpath(".//*[@data-original-title='Next Page' and @href='javascript:void(0);']");

  // view detail a porlet
  public static final String          ELEMENT_DETAIL_PORTLET_BREADCRUMB                       = ".//*[contains(@class,'breadcrumb')]//*[contains(text(),'${disName}')]";

  public static final String          ELEMENT_DETAIL_PORTLET_DISPLAY_NAME                     = ".//strong[contains(@title,'${disName}')]";

  public static final String          ELEMENT_DETAIL_PORTLET_APPLICATION_NAME                 = ".//*[contains(@title,'${appName}')]";

  public static final String          ELEMENT_DETAIL_PORTLET_DESCRIPTION                      = ".//span[contains(@title,'${des}')]";

  public static final By              ELEMENT_PERMISSION_FORM                                 = By.cssSelector(".UIPermissionForm");

  /*************************************
   * CONTENT LIST
   ***************************************************************/
  public static final By              ELEMENT_EDIT_CLV                                        = By.xpath("//*[@class='clv uiBox']");

  public static final By              ELEMENT_EDIT_PREFERENCE                                 = By.xpath("//*[@class='UIPageBody']//*[@data-original-title='Preferences']");

  /*************************************
   * EDIT PORTLET
   ***************************************************************/
  // Edit portlet form
  public static final By              ELEMENT_EDIT_PORTLET_FORM                               = By.id("tab-UIPortletForm");

  public static final By              ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON                  = By.xpath(".//*[@id='Close']");

  public static final By              ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON                   = By.xpath(".//*[@id='Save']");

  public static final By              ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON           = By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Save Settings']");

  public static final By              ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE                 = By.xpath("//*[@id='resultsPerPage']");

  public static final SelenideElement ELEMENT_CLOSE_PORTLET                                   = $(byClassName("uiIconClose"));

  // Permission tab
  public static final By              ELEMENT_PORTLET_ACCESS_PERMISSION_TAB                   = By.xpath(".//*[@id='tab-UIPortletForm']//*[@data-target='#PortletPermission-tab']");

  public static final String          ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN      = ".//*[@id='PermissionGrid']//*[contains(text(),'$group')]/../..//*[contains(@class,'uiIconDelete')]";

  public static final String          ELEMENT_CONFIRM_DELETE_MESSAGE                          = "Are you sure you want to delete this Access Group?";

  public static final By              ELEMENT_PORTLET_ACCESS_PERMISSION_ADD_BTN               =
                                                                                By.xpath(".//*[contains(@class,'uiAccessGroup')]//*[contains(@class,'uiAction')]//*[contains(@class,'uiIconAddUser')]");

  public static final By              ELEMENT_PORTLET_SELECT_PERMISSION_POPUP                 = By.xpath(".//*[contains(@class,'PopupContent')]");

  public static final String          ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME = ".//*[contains(@class,'PopupContent')]//*[@title='$name']";

  public static final String          ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME            = ".//*[@id='PermissionGrid']//*[contains(text(),'$group')]";

  /*************************************
   * FQA PAGE
   ***************************************************************/
  // Template tab
  public static final By              ELEMENT_FAQ_EDIT_TEMPLATE_TAB                           = By.xpath("//button[contains(text(), 'Edit Template')]");

  public static final By              ELEMENT_FAQ_EDIT_TEMP_INPUT                             = By.id("ContentTemplate");

  public static final By              ELEMENT_EDIT_SAVE_BUTTON                                = By.xpath("//*[text()='Save']");

  /*************************************
   * GADGET MANAGEMENT
   ***************************************************************/
  // Remote gadget on righ panel
  public static final By              ELEMENT_REMOTE_GADGETBTN                                = By.xpath(".//*[@id='UIGadgetManagement']//a[contains(.,'Add a remote gadget')]");

  public static final By              ELEMENT_REMOTE_GADGET_URL                               = By.id("url");

  public static final By              ELEMENT_REMOTE_GADGET_ADDBTN                            = By.xpath(".//*[@id='UIAddGadget']//button[text()='Add']");

  public static final By              ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK            = By.xpath(".//*[@id='UIGadgetInfo']//a[contains(text(),'Click here to add into categories')]");

  public static final By              ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES                  = By.xpath(".//*[@id='GadgetCategory']//div[@class='controls-full']");

  public static final String          ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_CHECKBOX         = ".//*[@id='categoryName'][contains(text(),'${category}')]/../..//input[@type='checkbox']";

  public static final By              ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_SAVEBTN          = By.xpath(".//*[@id='GadgetCategory']//button[text()='Save']");

  // left panel
  public static final String          ELEMENT_REMOTE_GADGET_LEFT_CONTENT                      = ".//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN                   = ".//*[contains(text(),'${name}')]/../../../..//*[@class='uiIconDelete uiIconLightGray']";

  // Create new gadget
  public static final By              ELEMENT_CREATE_NEW_GADGET_BTN                           = By.cssSelector(".uiIconCreateNewGadget.uiIconLightGray");

  public static final By              ELEMENT_CREATE_NEW_GADGET_INPUT_NAME_FIELD              = By.id("name");

  public static final By              ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD                  = By.id("source");

  public static final By              ELEMENT_CREATE_NEW_GADGET_SAVE_BTN                      = By.xpath(".//button[text()='Save']");

  // View detail of a Gadget
  public static final By              ELEMENT_GADGET_EDIT_BTN                                 = By.cssSelector(".uiIconEdit.uiIconLightGray");

  /*************************************
   * MY DASHBOARD
   ***************************************************************/
  public static final By              ELEMENT_DASHBOARD_WORKSPACE_POPUP_TITLE                 = By.xpath(".//*[@class='PopupTitle popupTitle'][contains(text(),'Dashboard Workspace')]");

  public static final By              ELEMENT_DASHBOARD_WORKSPACE_POPUP_CLOSE                 =
                                                                              By.xpath(".//*[contains(@class,'UIPopupWindow')]//*[contains(text(),'Dashboard Workspace')]/..//*[contains(@class,'uiIconClose')]");

  public static final String          ELEMENT_MYDASH_TAB_NAME                                 = "//*[@id='${name}']";

  public static final String          ELEMENT_MYDASH_DEFAULT_TAB_EDIT                         = "//li[@class=\"active last editing\"]/a/input";

  public static final String          ELEMENT_MYDASH_BTN_DELETETAB                            = "//*[@id='${name}']/../../..//*[contains(@class,'uiIconClose')]";

  public static final By              ELEMENT_MYDASH_BTN_ADDGADGET                            = By.xpath(".//*[@id='GadgetContainer']//*[contains(text(),'Add Gadgets')]");

  public static final String          ELEMENT_MYDASH_ADDED_GADGET_IN_DASHBOARD                = ".//*[@class='gadgetTitle'][contains(text(),'${name}')]";

  public static final String          ELEMENT_MYDASH_DELETE_GADGET                            = ".//*[@class='gadgetTitle'][contains(text(),'${name}')]/..//*[contains(@class,'uiIconClose')][1]";

  public static final By              ELEMENT_MYDASH_GADGET_BYURL                             = By.xpath("//*[@id='url']");

  public static final By              ELEMENT_MYDASH_GADGET_ADDBTN                            = By.xpath(".//*[@id='UIAddGadgetForm']//*[contains(@class,'uiIconPlus')]");

  /*************************************
   * NAVIGATION MANAGEMENT
   ***************************************************************/

  public static final By              ELEMENT_MANAGESITES_TITLE                               = By.xpath(".//*[@id='UIPortalNavigationPortlet']//h5[text()='Manage Sites']");

  public static final String          ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON                =
                                                                               ".//*[@class='managementBlock']//div[text()='${site}']/../..//*[@class='uiIconNavigation uiIconLightGray']";

  public static final String          ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON                    = ".//*[@class='managementBlock']//div[text()='${site}']/../..//*[contains(@class,'uiIconEditLayout')]";

  public static final String          ELEMENT_MANAGESITES_EDIT_CONFIG_ICON                    =
                                                                           ".//*[@class='managementBlock']//div[text()='${site}']/../..//*[contains(@class,'uiIconEditPortalConfig')]";

  public static final By              ELEMENT_MANAGESITES_ADD_NEW_BTN                         = By.cssSelector("#UISiteManagement .btn");

  public static final By              ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN         = By.cssSelector(".PageProfileIcon");

  public static final String          ELEMENT_ADD_NAVIGATION_BUTTON                           = "//*[contains(text(),'Add Navigation')]";

  // Navigation Management popup
  public static final By              ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE               = By.xpath(".//*[@class='PopupTitle popupTitle'][text()='Navigation Management']");

  public static final SelenideElement ELEMENT_BUTTON_EDIT_NAVIGATION                          = $(byXpath("//*[@id=\"UISiteManagement\"]/table/tbody/tr[2]/td[3]/a"));

  public static final String          ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME                 = ".//*[@title='${name}']";

  public static final By              ELEMENT_NAVIGATION_MANAGEMENT_SAVE                      = By.xpath(".//*[text()='Save']");

  public static final SelenideElement ELEMENT_NAVIGATION_NODE_CHECK                           = $(byId("UINavigationNodeSelector"));

  public static final SelenideElement ELEMENT_LEFT_NAVIGATION_NODE_CHECK                      = $(byClassName("uiCompanyNavigations"));

  // Add new portal popup
  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_NAME                       = By.cssSelector("#name");

  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_LABEL                      = By.cssSelector("#label");

  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_DESC                       = By.cssSelector("#description");

  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN                   = By.xpath(".//*[@id='UIPortalForm']//button[text()='Save']");

  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_PUBLIC_PERMISSION          = By.cssSelector("#publicMode");

  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_PERMISSION_TAB             = By.xpath(".//*[contains(@data-target,'#PermissionSetting-tab')]");

  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_EDIT_PERMISSITION_SETTINGS = By.xpath(".//*[contains(text(),'Edit Permission Settings')]");

  public static final By              ELEMENT_ADD_NEW_PORTAL_POPUP_SELECT_PERMISSION_BTN      = By.xpath(".//*[contains(text(),'Select Permission')]");

  // Permission selector
  public static final String          ELEMENT_PERMISSION_SELECTOR_POPUP_GROUP                 = ".//*[contains(@class,'uiIconNode')][contains(@title,'${group}')]";

  public static final String          ELEMENT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP            = ".//*[@id='PermissionSelector']//*[contains(@title,'${member}')]";

  // Contextmenu
  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_DELETE_ICON             = By.xpath(".//*[@id='NavigationNodePopupMenu']//*[@class='uiIconDeleteNode']");

  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_ICON               = By.cssSelector(".ContainerConfigOptions .uiIconEditSelectedNode");

  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_COPY_ICON               = By.cssSelector(".ContainerConfigOptions .uiIconCopyNode");

  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_PASTE_ICON              = By.cssSelector(".ContainerConfigOptions .uiIconPasteNode");

  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_CUT_ICON                = By.cssSelector(".ContainerConfigOptions .uiIconCutNode");

  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_CLONE_ICON              = By.cssSelector(".ContainerConfigOptions .uiIconCloneNode");

  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_UP_ICON            = By.cssSelector(".ContainerConfigOptions .uiIconMoveUp");

  public static final By              ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_DOWN_ICON          = By.cssSelector(".ContainerConfigOptions .uiIconMoveDown");

  public static final By              ELEMENT_UP_LEVEL_PATH_NODE                              = By.xpath("//*[@id='UINavigationNodeSelector']//*[@class='uiIconUpLevel uiIconLightGray']");

  public static final By              ELEMENT_ADD_NODE                                        = By.xpath("//*[@id='UINavigationManagement']/..//*[contains(text(),'Add Node')]");

  public static final By              ELEMENT_SAVE_NODE                                       = By.xpath("//*[@id='UINavigationManagement']/..//*[contains(text(),'Save')]");

  public static final By              ELEMENT_NODE_NAME                                       = By.id("name");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_TAB                          = By.cssSelector("a[data-target='#UIPageSelector-tab']");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGES_BTN             = By.className("uiIconSelectPage");

  public static final By              ELEMENT_NODE_SETTING_LANGUAGE_BOX                       = By.cssSelector(".selectbox");

  public static final By              ELEMENT_NODE_SETTING_LABEL_FIELD_1                      = By.cssSelector("#i18nizedLabel");

  public static final By              ELEMENT_NODE_SETTING_LABEL_FIELD_2                      = By.cssSelector("#label");

  public static final By              ELEMENT_NODE_SETTING_PUBLISH_DATE_TIME                  = By.cssSelector("#showPublicationDate");

  // new node-->Page selector
  public static final By              ELEMENT_NODE_PAGE_SELECTOR_PAGE_NAME                    = By.cssSelector("#pageName");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_PAGE_TITLE                   = By.cssSelector("#pageTitle");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_CREATE_PAGE_BTN              = By.xpath(".//*[@id='UIPageSelector']//button[1]");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGE_BTN              = By.xpath(".//*[@id='UIPageSelector']//button[2]");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_CLEAR_PAGE_BTN               = By.xpath(".//*[@id='UIPageSelector']//button[3]");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_TITLE_FIELD                  = By.cssSelector("#UIPageSearchForm #pageTitle");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_SITES_NAME_FIELD             = By.cssSelector("#UIPageSearchForm #siteName");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_TYPE_DROPBOX                 = By.cssSelector("#UIPageSearchForm .selectbox");

  public static final By              ELEMENT_NODE_PAGE_SELECTOR_SEARCH_BUTTON                = By.cssSelector("#UIPageSearchForm .uiIconSearch");

  /*************************************
   * PAGE CREATION WINZARD
   ***************************************************************/
  // Common
  public static final By              ELEMENT_PAGE_CREATION_WIZARD                            = By.id("UIPageCreationWizard");

  public static final By              ELEMENT_PAGE_NEXT_BUTTON                                = By.xpath("//*[@id='UIPageCreationWizard']//*[text()='Next']");

  public static final By              ELEMENT_SAVE_BTN_2                                      = By.xpath(".//*[@id='UIContainerForm']//*[text()='Save']");

  public static final By              ELEMENT_PAGE_NAME_INPUT                                 = By.id("pageName");

  public static final By              ELEMENT_PAGE_MODE_CHECKBOX                              = By.id("switchmode");

  public static final By              ELEMENT_PAGE_LANGUAGE_SELECT_BOX                        = By.name("languages");

  public static final By              ELEMENT_PAGE_DISPLAY_NAME_INPUT                         = By.id("i18nizedLabel");

  public static final By              ELEMENT_PAGE_VISIBLE_CHECKBOX                           = By.id("visible");

  public static final By              ELEMENT_PAGE_PUBLICATION_DATE_CHECKBOX                  = By.id("showPublicationDate");

  public static final By              ELEMENT_PAGE_UP_LEVEL                                   = By.xpath("//*[@class='uiIconUpLevel uiIconLightGray']");

  public static final String          ELEENT_NODE_NAME                                        = "//*[@title='$name']";

  // Page Editor left side bar header
  public static final By              ELEMENT_PAGE_FINISH_BTN                                 = By.xpath("//*[@class='uiIconSave uiIconDarkGray pull-right']");

  // Application panel
  public static final By              ELEMENT_APPLICATION_TAB                                 = By.xpath(".//*[@data-target='#appList']");

  public static final By              ELEMENT_APPLICATION_TAB_ACTIVE                          = By.xpath("//*[@class='active']/*[@data-target='#appList']");

  public static final By              ELEMENT_APPLICATION_CONTENT_TAB                         = By.xpath("//*[@title='Content']");

  public static final String          ELEMENT_APPLICATION_SUB_TAB                             = ".//*[@id='UIApplicationList']//*[contains(@title,'${tabName}')]";

  // Container panel
  public static final By              ELEMENT_CONTAINER_TAB                                   = By.linkText("Containers");

  public static final By              ELEMENT_SWITCH_VIEW_MODE                                = By.linkText("Switch View mode");

  public static final By              ELEMENT_VIEW_PROPERTIES                                 = By.cssSelector(".PageProfileIcon");

  // Container popup editor
  public static final By              ELEMENT_CONTAINER_POPUP_TITLE                           = By.name("title");

  public static final String          ELEMENT_CONTAINER_TITLE                                 = "//*[@class='UIRowContainer']//span[text()='${title}']";

  public static final By              ELEMENT_CONTAINER_POPUP_WIDTH                           = By.name("width");

  public static final By              ELEMENT_CONTAINER_POPUP_HEIGHT                          = By.name("height");

  // View properties popup
  public static final By              ELEMENT_VIEW_PROPERTIES_POPUP                           = By.cssSelector(".MaskContainer");

  public static final By              ELEMENT_VIEW_PROPERTIES_TITLE                           = By.id("title");

  public static final String          ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_GROUP            = ".//*[@id='PermissionSelector']//*[contains(@class,'uiIconNode')][contains(@title,'${group}')]";

  public static final String          ELEMENT_EDIT_PERMISSION_MOVE_APPS_SELECT                = "//*[contains(@id,'UIListMoveAppsPermissionSelector')]//*[contains(@title,'${group}')]";

  public static final String          ELEMENT_EDIT_PERMISSION_MOVE_CONTAINERS_SELECT          = "//*[contains(@id,'UIListMoveContainersPermissionSelector')]//*[contains(@title,'${group}')]";

  public static final String          ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP        = "//*[contains(@id,'UIListPermissionSelector')]//*[contains(@title,'${member}')]";

  public static final String          ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP       = ".//*[@id='PermissionSelector']//*[contains(@title,'${member}')]";

  public static final By              ELEMENT_VIEW_PROPERTIES_SHOW_MAX_WINDOW                 = By.cssSelector("#showMaxWindow");

  public static final By              ELEMENT_VIEW_PROPERTIES_SAVE_BTN                        = By.xpath(".//*[@id='UIPageForm']//button[text()='Save']");

  public static final String          ELEMENT_VIEW_PROPERTIES_GROUP_REMOVE_BTN                = ".//*[@id='PermissionGrid']//*[contains(text(),'${group}')]/../..//*[contains(@class,'uiIconDelete')]";

  public static final By              ELEMENT_VIEW_PROPERTIES_DELETE_EDIT_PERMISSION_BTN      = By.xpath(".//*[@id='UIPermissionSelector']//*[contains(text(),'Delete Permission')]");

  public static final By              ELEMENT_VIEW_PROPERTIES_PERMISSION_TAB                  = By.xpath(".//*[contains(@data-target,'#PermissionSetting-tab')]");

  public static final By              ELEMENT_VIEW_PROPERTIES_EDIT_PERMISSITION_SETTINGS      = By.xpath(".//*[@id='PermissionSetting']//a[contains(.,'Edit')]");

  public static final By              ELEMENT_VIEW_PROPERTIES_SELECT_PERMISSION_BTN           = By.xpath(".//*[contains(text(),'Select Permission')]");

  public static final By              ELEMENT_VIEW_PROPERTIES_ADD_PERMISSION_BTN              = By.cssSelector(".uiIconAddUser");

  public static final By              ELEMENT_PAGEEDITOR_FINISHBTN                            = By.xpath("//*[contains(@class,'uiIconSave')]");

  public static final By              ELEMENT_SWITCH_VIEW_MODE_NAME_APPLICATION_CLASS         = By.xpath(".//*[contains(@class,'portletName')]");

  public static final String          ELEMENT_APPLICATION_IN_LAYOUT_PAGE                      = "//*[contains(@class,'LAYOUT-PORTLET')]//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_APPLICATION_EDIT_ICON                           = "//span[contains(text(),'${name}')]/../../../..//*[contains(@class,'uiIconEdit')]";

  public static final String          ELEMENT_APPLICATION_DELETE_ICON                         = "//span[contains(text(),'${name}')]/../../../..//*[contains(@class,'uiIconTrash')]";

  public static final By              ELEMENT_DROP_SOURCE_HAS_LAYOUT                          = By.xpath("//div[@class='UIRowContainer EmptyContainer']");

  public static final String          ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME                  = "//span[contains(text(),'${name}')]/../../../..//*[contains(@class,'UIRowContainer')]";

  public static final By              ELEMENT_EDIT_CONTAINER_ICON                             = By.xpath(".//*[@data-original-title='Edit Container']//*[contains(@class,'uiIconEdit')]");

  public static final String          ELEMENT_EDIT_CONTAINER_ICON_BY_NAME                     = "//span[contains(text(),'${name}')]/..//*[contains(@class,'uiIconEdit')]";

  public static final String          ELEMENT_DELETE_CONTAINER_ICON_BY_NAME                   = "//span[contains(text(),'${name}')]/..//*[contains(@class,'uiIconTrash')]";

  public static final String          ELEMENT_APPLICATION_HOLDER_MOVE                         = "//span[contains(text(),'${name}')]/..//*[contains(@class,'uiIconDragDrop')]";

  public static final String          ELEMENT_DELETE_CONTAINER_ICON_BY_ID                     = "//*[@id='${id}']//*[@data-original-title='Delete Container']";

  public static final String          ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID                    = ".//*[@class='UIRowContainer']//*[@id='${id}']";

  // Application popup
  public static final By              ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TAB              = By.xpath(".//*[@id='tab-UIPortletForm']//*[contains(@data-target,'PortletSetting')]");

  public static final By              ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TITLE            = By.id("title");

  public static final By              ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_WIDTH            = By.id("width");

  public static final By              ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_HEIGHT           = By.id("height");

  public static final By              ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_SAVE             = By.id("Save");

  public static final By              ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX               = By.xpath("//*[contains(@id,'UIListPermissionSelector')]//*[@id='publicMode']");

  public static final By              ELEMENT_PAGEEDITOR_MOVE_APPS_PUBLIC_CHECKBOX            = By.xpath(".//*[contains(@id,'UIListMoveAppsPermissionSelector')]//*[@id='publicMode']");

  public static final By              ELEMENT_PAGEEDITOR_MOVE_CONTAINERS_PUBLIC_CHECKBOX      = By.xpath("//*[contains(@id,'UIListMoveContainersPermissionSelector')]//*[@id='publicMode']");

  // Edit properties of page
  public static final String          ELEMENT_VIEW_PAGE_PROPERTIES                            = ".//*[@id='UIPageEditor']//*[contains(text(),'View Page properties')]";

  public static final By              ELEMENT_VIEWPAGE_PAGETITLE                              = By.id("title");

  public static final String          ELEMENT_PERMISSION_SETTING_TAB                          = "//*[@data-target='#PermissionSetting-tab']";

  public static final String          ELEMENT_CONTAINER_PERMISSION_SETTING_TAB                = "//*[@data-target='#UIContainerPermission-tab']";

  public static final String          ELEMENT_EDIT_PERMISSION_SETTING                         = ".//*[@id='PermissionSetting']//*[contains(text(),'Edit')]";

  public static final String          ELEMENT_MOVE_APPS_PERMISSION_SETTING                    = ".//*[@id='PermissionSetting']//a[contains(text(),'Move Apps')]";

  public static final String          ELEMENT_MOVE_CONTAINERS_PERMISSION_SETTING              = ".//*[@id='PermissionSetting']//a[contains(text(),'Move Containers')]";

  public static final By              ELEMENT_SELECT_PERMISSION_BUTTON                        = By.xpath("//*[@id=\"UIPermissionSelector\"]/div[3]/a[2]");

  public static final By              ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN                  = By.xpath("//*[contains(@class,'uiIconAddUser')]");

  public static final String          ELEMENT_SELECT_EDIT_GROUP_ITEM                          = ".//*[@id='PermissionSelector']//*[@title='${group}']/i";

  public static final String          ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM                     = ".//*[@id='PermissionSelector']//*[@title='${membership}']";

  public static final String          ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP             =
                                                                                  ".//*[@id='UIPermissionSelector']//*[contains(text(),'Membership')]/../*[contains(text(),'${membership}')]";

  /*************************************
   * PAGE EDITOR
   ***************************************************************/
  // Common
  public static final By              ELEMENT_EDIT_PORTLET_ICON                               = By.xpath("//*[@data-original-title='Edit Portlet']");

  public static final String          ELEMENT_EDIT_PORTLET                                    = "//*[@data-original-title='Edit Portlet']/../*[contains(.,'${portlet}')]";

  public static final String          ELEMENT_DELETE_PORTLET                                  = "//*[@data-original-title='Edit Portlet']/../*[contains(.,'${portlet}')]";

  public static final String          ELEMENT_EDITOR_PAGE_APPLICATION_PORTLET                 = ".//*[@class='portletLayoutDecorator'][contains(text(),'${name}')]";

  // Application
  public static final String          ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_RIGHT   = "//*[@title='$category']/*[contains(@class,'uiIconArrowRight')]";

  public static final String          ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_DOWN    = "//*[@title='$category']/*[contains(@class,'uiIconArrowDown')]";

  public static final String          ELEMENT_EDIT_PORTLET_APPLICATION_ID                     = "//*[contains(@id,'$portlet')]";

  public static final String          ELEMENT_APPLICATION_NAME                                = "//*[@class='txtLeft'][contains(.,'$app')]";

  // Finish and Abort button
  public static final By              ELEMENT_EDIT_PORTLET_FINISH                             = By.xpath("//*[@data-original-title='Finish']");

  // View page properties
  public static final By              ELEMENT_PAGE_EDITOR_VIEW_PAGE_PROPERTIES                = By.xpath("//*[@class='btn btn-primary PageProfileIcon']");

  public static final By              ELEMENT_PERMISSTION_SETTING_TAB                         = By.xpath("//*[@data-target='#PermissionSetting-tab']");

  public static final By              ELEMENT_PUBLIC_MODE                                     = By.id("publicMode");

  // Middle container
  public static final By              ELEMENT_EDIT_PAGE_PAGE                                  = By.id("UIPage");

  public static final By              ELEMENT_PAGE_EDITOR_SAVE_BUTTON                         = By.xpath("//*[@id='UIPageForm']//*[text()='Save']");

  public static final By              ELEMENT_FRAME_CONTAIN_PORTLET                           = By.xpath("//div[contains(@id,'UIPortlet')]");

  public static final By              ELEMENT_PAGE_OK_BUTTON                                  = By.xpath("//*[contains(@class,'UIPopupWindow')]//a[text()='OK']");

  // Container
  public static final String          ELEMENT_CONTAINER_ID                                    = ".//*[@id='${id}']/div/div[1]/div/div";

  /*************************************
   * PORTAL BRANDING
   ***************************************************************/
  public static final By              ELEMENT_UPLOAD_LINK                                     = By.name("file");

  public static final By              ELEMENT_BUTTON_UPLOAD                                   = By.xpath("//*[@id='btUpload']");

  /*************************************
   * PORTAL GROUP NAVIGATION
   ***************************************************************/
  public static final String          ELEMENT_GROUP_SELECT_ADD_NAVIGATION                     = "//*[contains(text(),'${groupName}')]/..//a[contains(text(),'Add Navigation')]";

  public static final By              ELEMENT_CANCEL_BUTON                                    = By.linkText("Cancel");

  public static final String          ELEMENT_GROUP_NAME                                      = ".//*[@id='UIGroupNavigationGrid']//*[contains(text(),'${groupName}')]";

  public static final String          ELEMENT_DELETE_NAVIGATION_ICON                          = "//*[contains(text(),'${groupName}')]/../..//i[@class='uiIconTrash uiIconLightGray']";

  public static final String          ELEMENT_EDIT_PROPERTIES_ICON                            = "//*[text()='${groupName}']/../..//*[@class='uiIconEditPortalConfig uiIconLightGray']";

  public static final String          ELEMENT_GROUP_NAVIGATION_PRIORITY                       = "//*[@name='priority']";

  public static final String          ELEMENT_SAVE_BTN                                        = "//*[text()='Save']";

  public static final String          ELEMENT_EDIT_NAVIGATION                                 = "//*[text()='${groupName}']/../..//i[@class='uiIconNavigation uiIconLightGray']";

  public static final String          ELEMENT_TITLE_NAVIGATION_MANAGEMENT                     = "//*[text()='Navigation Management']";

  public static final By              ELEMENT_MANAGEPAGES_TITLE_FIELD                         = By.id("pageTitle");

  public static final By              ELEMENT_MANAGEPAGES_SITES_NAME_FIELD                    = By.id("siteName");

  public static final By              ELEMENT_MANAGEPAGES_TYPE_DROPBOX                        = By.xpath(".//*[@name='searchOption']");

  public static final By              ELEMENT_MANAGEPAGES_SEARCH_BUTTON                       = By.xpath(".//*[@class='uiIconSearch uiIconLightGray']");

  public static final By              ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN                    = By.xpath(".//*[@id='UIPageBrowser']//*[text()='Add New Page']");

  public static final By              ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE          = By.xpath(".//*[@class='uiIconDelete uiIconLightGray']");

  public static final By              ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT            = By.xpath(".//*[@class='uiIconEditInfo uiIconLightGray']");

  public static final By              ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN        =
                                                                                       By.cssSelector("#UIMaskWorkspace .btn[onclick~=\"javascript:eXo.webui.UIForm.submitForm('UIPageForm','Save',true)\"]");

  public static final By              ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME        = By.cssSelector("#UIMaskWorkspace #name");

  public static final By              ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE            = By.cssSelector("#UIMaskWorkspace #title");

  public static final By              ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX     = By.xpath(".//*[@name='ownerType']");

  public static final String          ELEMENT_SELECT_SEARCH_OPTION                            = "//*[@class='selectbox' and @name='searchOption']";

  /*************************************
   * PORTAL MANAGE SITES
   ***************************************************************/
  // Add New Portal
  public static final String          ELEMENT_ADD_NEW_PORTAL_LINK                             = ".//*[@id='UISiteManagement']//a[contains(text(),'Add New Site')]";

  public static final By              ELEMENT_INPUT_NAME                                      = By.id("name");

  public static final By              ELEMENT_PORTAL_LABEL                                    = By.id("label");

  public static final By              ELEMENT_PORTAL_DESCRIPTION                              = By.id("description");

  public static final String          ELEMENT_SELECT_LOCALE                                   = "//*[@class='selectbox' and contains(@name,'locale')]";

  public static final String          ELEMENT_SELECT_SKIN                                     = "//*[@class='selectbox' and contains(@name,'skin')]";

  public static final String          ELEMENT_PROPERTIES_TAB                                  = "//a[contains(text(),'Properties')]";

  public static final String          ELEMENT_SELECT_SESSION_ALIVE                            = "//*[@class='selectbox' and contains(@name,'sessionAlive')]";

  public static final By              ELEMENT_CHECKBOX_PUBLIC_MODE                            = By.id("publicMode");

  public static final By              ELEMENT_ADD_PERMISSION_BUTTON                           = By.xpath("//*[contains(@class,'uiIconAddUser')]");

  public static final String          ELEMENT_PORTAL_TEMPLATE_TAB                             = "//a[contains(text(),'Portal Templates')]";

  public static final String          ELEMENT_SELECT_ACCESS_GROUP_ITEM                        = "//a[contains(@title,'${group}')]/i";

  public static final String          ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM                   = "//a[contains(@title,'${membership}')]";

  public static final String          ELEMENT_SELECTED_ACCESS_PERMISSION_MEMBERSHIP           = ".//*[@id='PermissionGrid']//span[contains(text(),'${membership}')]";

  public static final String          ELEMENT_SELECT_EDIT_PERMISSION_MEMBERSHIP               = ".//*[@id='PermissionSelector']//a[contains(.,'${membership}')]";

  public static final By              ELEMENT_POPUP_ADD_PORTAL                                = By.id("UIMaskWorkspace");

  public static final String          ELEMENT_PORTAL_DELETE_ICON                              = "//*[contains(text(),'${portalName}')]/../..//i[@class='uiIconTrash uiIconLightGray']";

  public static final String          ELEMENT_NEW_PORTAL_ADD                                  = "//*[@class='siteName' and text()='${portalName}']";

  public static final String          ELEMENT_NEW_PORTAL_SWITCH                               = "//img[contains(@src, 'sites/${portalName}')]";

  public static final By              ELEMENT_NEW_PORTAL_LOGOUT                               = By.xpath("//*[@id='AcmeWebSiteLogInLogOut']");

  public static final String          ELEMENT_USER_DELETE_ICON                                = ".//*[contains(text(),'${username}')]/../..//*[@data-original-title='Delete User']/i";

  public static final By              ELEMENT_SAVE_ADD_USER                                   = By.xpath("//*[@id='UIAccountForm']//*[text()='Save']");

  public static final By              ELEMENT_SAVE_PASSWORD                                   = By.xpath(".//*[@id='UIAccountChangePass']//*[text()='Save']");

  public static final By              ELEMENT_INPUT_SEARCH_USER_NAME                          = By.id("searchTerm");

  public static final SelenideElement ELEMENT_BTN_SEARCH_USER                                 = $(byTitle("Quick Search"));

  public static final SelenideElement ELEMENT_BTN_USER_TAB                                    = $(byXpath("//*[@id=\"UIOrganizationPortlet\"]/div[2]/div[1]/ul/li[1]/a"));

  public static final String          ELEMENT_SEARCH_ICON_USERS_MANAGEMENT                    = "//*[contains(@title,'Quick Search')]";

  public static final String          ELEMENT_MSG_CHANGE_PASS_WORD                            = "The password has been changed.";

  // change passWord
  public static final By              ELEMENT_CHANGE_PASSWORD_LINK                            = By.linkText("Change Password");

  public static final By              ELEMENT_CURRENT_PASSWOR                                 = By.id("currentpass");

  public static final By              ELEMENT_NEW_PASSWORD                                    = By.id("newpass");

  public static final By              ELEMENT_CONFIRM_NEW_PASSWORD                            = By.id("confirmnewpass");

  public static final String          ELEMENT_USER_MANAGEMENT_TAB                             = "//a[contains(@class,'actionIcon userButton')]/i";

  public static final String          ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP                   = ".//*[@class='groupNavigationContainer']//*[contains(@title,'${name}')]";

  public static final String          ELEMENT_TAB_MEMBERSHIP_MANAGEMENT                       = "//a[contains(@class,'actionIcon membershipButton')]/i";

  public static final String          ELEMENT_MEMBERSHIP_MANAGEMENT_GRID                      = "//*[contains(text(), 'Add/Edit Membership')]";

  public static final By              ELEMENT_INPUT_GROUP_NAME                                = By.id("groupName");

  public static final By              ELEMENT_INPUT_LABEL                                     = By.id("label");

  public static final By              ELEMENT_TEXTAREA_DESCRIPTION                            = By.name("description");

  public static final By              ELEMENT_GROUP_SEARCH_USER_ICON                          = By.className("uiIconSelectUser uiIconLightGray");

  public static final String          ELEMENT_GROUP_SEARCH_USER_ICON_2                        = "//*[contains(@class, 'uiIconSelectUser')]";

  public static final By              ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT                  = By.id("Quick Search");

  public static final String          ELEMENT_GROUP_SEARCH_USER_OPTION                        = "//*[@class='selectbox' and @name='filter']";

  public static final String          ELEMENT_GROUP_SEARCH_USER_SEARCH_ICON                   = ".//*[@data-original-title='Quick Search']/i";

  public static final String          ELEMENT_ADDED_GROUP_USER_IN_TABLE                       = "//*[@id='UIGridUser']//span[contains(text(),'${username}')]";

  public static final String          ELEMENT_ADDED_GROUP_USER_IN_TABLE1                      =
                                                                         "//*[@id='UIGridUser']//span[contains(text(),'$mem')]/../../*[@headers='userName']/*[contains(.,'$user')]";

  public static final String          ELEMENT_EDIT_USER_MEM_IN_TABLE_ICON                     = "//*[@headers='userName']/*[contains(.,'$user')]/../..//*[contains(@class,'uiIconEdit')]";

  public static final String          ELEMENT_EDIT_USER_MEM_FORM                              = "//*[contains(@class,'UIGroupEditMembershipForm')][contains(.,'$mem')]";

  public static final String          ELEMENT_SELECT_MEMBERSHIP                               = "//*[@class='selectbox' and @name='membership']";

  public static final By              ELEMENT_SAVE_BUTTON_2                                   = By.xpath(".//*[@id='UIGroupMembershipForm']//*[contains(@class,'btn')]");

  public static final String          ELEMENT_MEMBERSHIP_EDIT_ICON                            = "//span[contains(text(),'${membership}')]/../..//*[contains(@data-original-title,'Edit Membership')]/i";

  public static final String          ELEMENT_MEMBERSHIP_DELETE_ICON                          =
                                                                     "//span[contains(text(),'${membership}')]/../..//*[contains(@data-original-title,'Delete Membership')]/i";

  public static final SelenideElement ELEMENT_GROUP_SEARCH_USER_SEARCH                        = $(byXpath("//a[@data-original-title='Quick Search']"));

  public static final String          ELEMENT_USER_NOT_FOUND                                  = "User ${user}not found in group";

  public static final String          ELEMENT_MEMBERSHIHP                                     = "//*[@id='UIGrid']//span[text()='${membershipName}']";

  public static final String          ELEMENT_MEMBERSHIP_DESCRIPTION                          = "//*[@id='UIGrid']//span[text()='$des']/../../*[@headers='name']/*[contains(.,'$mem')]";

  public static final String          ELEMENT_MEMBERSHIP_INPUT                                = "//input[@value='${membershipName}']";

  // Paging control
  public static final By              ELEMENT_PAGINATION_CONTROL                              = By.xpath(".//*[contains(@class,'pagination')]");

  public static final By              ELMEMENT_PAGINATION_CONTROL_DISBALED_NEXT_ARROW         = By.xpath(".//*[@class='disabled']//*[contains(@class,'uiIconNextArrow')]");

  public static final By              ELEMENT_PAGINATION_TOTAL_PAGE                           = By.xpath("//*[contains(@class,'pagesTotalNumber')]");

  public static final By              ELEMENT_PAGINATION_ENABLED_NEXT_ARROW                   = By.xpath(".//*[@ data-original-title='Next Page']//*[@class='uiIconNextArrow']");

  // User tab
  public static final String          ELEMENT_USER_TAB                                        = ".//*[@id='UIOrganizationPortlet']//*[contains(@class,'uiIconUser uiIconLightGray')]";

  public static final String          ELEMENT_USER_DELETE_ICON1                               = "//*[@data-original-title='Delete User']/i";

  public static final String          ELEMENT_USER_NAME_IN_USER_LIST                          = ".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]";

  public static final String          ELEMENT_MSG_TOTAL_PAGES                                 = "Total pages";

  public static final String          ELEMENT_MSG_CONFIRM_DELETE_MEMBERSHIP                   = "Are you sure you want to delete this membership?";

  public static final String          ELEMENT_MSG_CONFIRM_DELETE1                             = "Are you sure you want to delete";

  public static final By              ELEMENT_OK_BUTTON                                       = By.xpath("//*[contains(text(),'OK')]");

  public static final String          ELEMENT_MSG_CANNOT_DELETE                               = "You cannot delete this membership because it is mandatory.";

  // Edit user profile Tab
  public static final By              ELEMENT_USER_PROFILE_TAB                                = By.xpath("//*[@data-target='#UIUserProfileInputSet-tab']");

  public static final By              ELEMENT_GIVEN_NAME                                      = By.id("user.name.given");

  public static final By              ELEMENT_FAMILY_NAME                                     = By.id("user.name.family");

  public static final By              ELEMENT_NICK_NAME                                       = By.id("user.name.nickName");

  public static final By              ELEMENT_BIRTHDAY                                        = By.id("user.bdate");

  public static final By              ELEMENT_GENDER                                          = By.xpath("//*[@name='user.gender']");

  public static final By              ELEMENT_GENDER_EDIT_PROFILE                             = By.xpath("//*[@name='gender']");

  public static final By              ELEMENT_EMPLOYER                                        = By.id("user.employer");

  public static final By              ELEMENT_DEPARTMENT                                      = By.id("user.department");

  public static final By              ELEMENT_JOB_TITLE                                       = By.id("user.jobtitle");

  public static final By              ELEMENT_LANGUAGE                                        = By.name("user.language");

  // Group management
  public static final String          ELEMENT_USER_REMOVE_MEMBER_ICON                         = ".//*[contains(text(),'${userName}')]/../..//*[contains(@class,'uiIconDeleteUser')]";

  // Disable Users
  public static final String          ELEMENT_DISABLE_USER_HANDLE_BTN                         =
                                                                      ".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[contains(@class,'switchBtnHandle')]";

  public static final String          ELEMENT_DISBALE_USER_ENABLED                            =
                                                                   ".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[@data-original-title='Disable User']";

  public static final String          ELEMENT_DISBALE_USER_DISABLED                           =
                                                                    ".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[@data-original-title='Enable User']";

  public static final By              ELEMENT_DISABLE_USER_COLUMN                             = By.xpath(".//th[@id='DisableEnableUser'][contains(.,'Enabled')]");

  public static final By              ELEMENT_DISABLE_USER_STATUS_DISABLED                    = By.xpath("//*[@id='UIListUsers-userStatusFilter']/*[contains(.,'Disabled')]");

  public static final By              ELEMENT_DISABLE_USER_STATUS_ALL                         = By.xpath("//*[@id='UIListUsers-userStatusFilter']/*[contains(.,'All')]");

  public static final String          ELEMENT_DISABLE_USER_STATUS_SELECTED                    = "//*[@id='UIListUsers-userStatusFilter']/*[contains(.,'$status')][@selected='selected']";

  public static final String          ELEMENT_DISABLE_USER_TOGGLE_NO                          =
                                                                     "//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[contains(@class,'switchBtnLabelOff')]//*[contains(text(),'No')]";

  public static final String          ELEMENT_DISABLE_USER_TOGGLE_YES                         =
                                                                      "//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[contains(@class,'switchBtnLabelOn')]//*[contains(text(),'Yes')]";

  // Social networks tab
  public static final By              ELEMENT_USER_SOCIAL_NETWORKS_TAB                        = By.xpath("//*[@data-target='#UIAccountSocial-tab']");

  public static final By              ELEMENT_EDITING_CONTAINER_POPUP                         = By.xpath("//*[@id='UIMaskWorkspace']//*[contains(text(),'Container Setting')]");

  public static final String          ELEMENT_PERMISSION_SELECTOR_BUTTON                      = ".//*[@class='uiAction uiActionBorder']//*[contains(text(),'Permission Selector')]";

  /*************************************
   * CONTENT DETAIL
   ***************************************************************/
  // Edit popup
  public static final By              ELEMENT_CONTENT_DETAIL_EDIT_BTN                         = By.xpath("//*[@class='uiIconEdit uiIconWhite']");

  public static final By              ELEMENT_CONTENT_DETAIL_SAVE_BTN                         = By.xpath("//*[@class='btn' and text()='Save']");

  public static final By              ELEMENT_CONTENT_DETAIL_CLOSE_BTN                        = By.id("Close");

  public static final By              ELEMENT_CONTENT_DETAIL_ADDPATH_BTN                      = By.xpath("//*[@class='uiIconAddPath']");

  // Select Content popup
  public static final String          ELEMENT_SELECT_CONTENT_POPUP_NODE_FOLDER                = ".//span[contains(text(),'${node}')]";

  public static final String          ELEMENT_SELECT_CONTENT_POPUP_FILE                       = ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";

  // Edit popup
  public static final By              ELEMENT_CONTENT_LIST_EDIT_BTN                           = By.xpath("//*[@class='uiIconEdit uiIconWhite']");

  public static final By              ELEMENT_CONTENT_LIST_SAVE_BTN                           = By.xpath("//*[@class='btn' and text()='Save']");

  public static final By              ELEMENT_CONTENT_LIST_CLOSE_BTN                          = By.id("Close");

  public static final By              ELEMENT_CONTENT_LIST_ADDPATH_BTN                        = By.xpath("//*[@class='uiIconAddPath uiIconLightGray']");

  public static final By              ELEMENT_CONTENT_LIST_BY_CONTENT_MODE                    = By.xpath("//*[@class='radio' and @value='ManualViewerMode']");

  public static final By              ELEMENT_CONTENT_LIST_BY_FOLDER_MODE                     = By.xpath("//*[@class='radio' and @value='AutoViewerMode']");

  // Conntent list Preference popup
  public static final By              ELEMENT_CONTENT_LIST_PREFERENCE_SAVE_BTN                = By.xpath(".//*[@id='UICLVConfig']//a[text()='Save']");

  public static final String          ELEMENT_CONTENT_LIST_CONTENT_TITLE                      = "//*[@title='${title}']";

  // Multiple content selector popup
  public static final String          ELEMENT_MULTIPLE_CONTENT_POPUP_NODE_FOLDER              = ".//span[contains(text(),'${node}')]";

  public static final String          ELEMENT_MULTIPLE_CONTENT_POPUP_FILE                     = ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";

  public static final By              ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN                 = By.xpath(".//*[@id='tab-UIContentBrowsePanelMulti']//button[text()='Save']");

  public static final By              ELEMENT_ADDNEWPAGE_BTNNEXT                              = By.xpath("//*[@class='btn btn-primary' and text()='Next']");

  public static final By              ELEMENT_PAGEEDITOR_FORUM                                = By.xpath("//*[@class='LAYOUT-BLOCK LAYOUT-PORTLET']");

  public static final By              ELEMENT_PAGEEDITOR_EDITELEMENT                          = By.xpath("//*[@class='uiIconEdit uiIconWhite']");

  public static final By              ELEMENT_PAGEEDITOR_SAVEBTN                              = By.xpath("//*[@class='btn' and text()='Save']");

  public static final By              ELEMENT_PAGEEDITOR_OKBTN                                = By.xpath("//*[@class='btn' and text()='OK']");

  public static final By              ELEMENT_PAGEEDITOR_CLOSEBTN                             = By.xpath("//*[@class='btn' and text()='Close']");

  public static final By              ELEMENT_PAGEEDITOR_FINISHLIGHTBTN                       = By.xpath("//*[@class='uiIconSave uiIconLightGray pull-right']");

  /*************************************
   * DISABLE USER
   ***************************************************************/
  // Drop box
  public static final By              ELEMENT_DISABLE_USER_DROP_BOX                           = By.id("UIListUsers-userStatusFilter");

  /*************************************
   * USER ADD MANAGEMENT
   ***************************************************************/
  public static final By              ELEMENT_USERNAME                                        = By.id("username");

  public static final By              ELEMENT_PASSWORD                                        = By.id("password");

  public static final By              ELEMENT_CONFIRM_PASSWORD                                = By.id("Confirmpassword");

  public static final By              ELEMENT_EMAIL                                           = By.id("email");

  public static final By              ELEMENT_FIRSTNAME                                       = By.id("firstName");

  public static final By              ELEMENT_LASTNAME                                        = By.id("lastName");

  public static final By              ELEMENT_DISPLAY_NAME                                    = By.id("displayName");

  public static final SelenideElement ELEMENT_BTN_SAVE_EDIT_USER                              = $(byXpath("//*[@id=\"UIUserInfo\"]/div[4]/button[1]"));

  // Layout
  public static final By              ELEMENT_PAGEEDITOR_VIEWPAGE                             = By.xpath("//*[@class='VIEW-PAGE']");

  public static final SelenideElement ELEMENT_GROUP_TAB                                       = $(byXpath("//*[@id=\"UIOrganizationPortlet\"]/div[2]/div[1]/ul/li[2]/a"));

  public static final SelenideElement ELEMENT_TITLE_GROUP_INFO                                = $(byXpath("//*[@id=\"UIOrganizationPortlet\"]/div[2]/div[2]/div/div[2]/div[2]/div/div/div/h5"));

  public static final By              ELEMENT_ICON_EDIT_USER                                  = byClassName("uiIconViewUserInfo");

  public static final SelenideElement ELEMENT_SELECT_BOX_USERS                                = $(byId("searchOption"));

  public static final SelenideElement ELEMENT_TABLE_LIST_USERS                                = $(byXpath("//*[@id=\"UIListUsersGird\"]/table"));

  public static final By              ELEMENT_LINE_IN_TABLE_LIST_USERS                        = byClassName("enabled");

  public static final SelenideElement ELEMENT_ICON_CLOSE_EDIT_LAYOUT                          = $(byXpath("//*[@id=\"UIPageEditor\"]/div[1]/a[1]"));

  public static final SelenideElement ELEMENT_LIST_CONTENT                                    = $(byId("PAGEBODY-VIEW-BLOCK"));

  public static final String          ELEMENT_SAVE_BUTTON                                     = "//button[contains(text(),'Save')]";

  public static final String          ELEMENT_CLOSE_MESSAGE                                   = "//*[contains(@title,'Close Window')]";

  public static final String          ELEMENT_MSG_UPDATE_USER_PROFILE                         = "The user profile has been updated.";

}
