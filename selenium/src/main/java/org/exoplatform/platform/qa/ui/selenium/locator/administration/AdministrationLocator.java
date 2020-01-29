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
package org.exoplatform.platform.qa.ui.selenium.locator.administration;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class AdministrationLocator {

  /***************************
   * CONTENT ADMINISTRATION
   *****************************************************************************/
  public static final By              ELEMENT_ADVANCED_CATEGORIES_ECM_FUNCTIONS                    =
                                                                                By.xpath("//*[@class='ecmAdminPanel pull-left']//*[@class='accordion-toggle collapsed']/a[contains(text(),'Advanced')]");

  public static final SelenideElement ELEMENT_EXPLORER_CATEGORIES_ECM_FUNCTIONS                    =
                                                                                $(byXpath("//*[@id=\"accordion\"]/div[2]/div[1]/div/a"));

  public static final By              ELEMENT_TEMPLATE_CATEGORIES_ECM_FUNCTIONS                    =
                                                                                By.xpath("//*[@class='ecmAdminPanel pull-left']//*[@class='accordion-toggle collapsed']/a[contains(text(),'Templates')]");

  public static final By              ELEMENT_RESPONSITORY_CATEGORIES_ECM_FUNCTIONS                =
                                                                                    By.xpath("//*[@class='ecmAdminPanel pull-left']//*[@class='accordion-toggle collapsed']/a[contains(text(),'Repository')]");

  public static final By              ELEMENT_ECMS_FUNCTIONS_DRIVES                                =
                                                                    By.xpath("//*[@class='uiIconEcmsDriveManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_ACTIONS                               =
                                                                     By.xpath("//*[@class='uiIconEcmsActionManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_SCRIPTS                               =
                                                                     By.xpath("//*[@class='uiIconEcmsScriptManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_QUERIES                               =
                                                                     By.xpath("//*[@class='uiIconEcmsQueriesManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_CATEGORIES                            =
                                                                        By.xpath("//*[@class='uiIconEcmsTaxonomyManagerTrees uiIconEcmsLightGray']");

  // function Advanced, ACTIONS
  public static final By              ELEMENT_ADD_ACTION_TYPE                                      =
                                                              By.xpath("//*[@id='UIActionManager']//*[@class='btn']");

  public static final By              ELEMENT_ECM_ACTION_NAME_FORM                                 =
                                                                   By.xpath("//*[@class='uiForm UIActionTypeForm']//*[@id='name']");

  public static final By              ELEMENT_ECM_ACTION_SCRIPT_FORM                               =
                                                                     By.xpath("//*[@class='uiForm UIActionTypeForm']//*[@class='selectbox']");

  public static final By              ELEMENT_ECM_ACTION_VARIABLES_FORM                            =
                                                                        By.xpath("//*[@class='uiForm UIActionTypeForm']//*[@id='variables0']");

  public static final By              ELEMENT_ECM_ACTION_SAVE_FORM                                 =
                                                                   By.xpath("//*[@id='UIActionTypeForm']//*[contains(text(),'Save')]");

  public static final String          ELEMENT_ECM_ACTION_LIST                                      =
                                                              "//*[@id='UIActionTypeList']//div[contains(text(),'{$name}')]";

  public static final String          ELEMENT_ECM_ACTION_DELETE_LIST                               =
                                                                     "//*[@id='UIActionTypeList']//div[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_ECM_ACTION_EDIT_LIST                                 =
                                                                   "//*[@id='UIActionTypeList']//div[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";

  // function Advanced, Scripts
  public static final By              ELEMENT_ECM_ADVANCED_SCRIPT_ADD_SCRIPT                       =
                                                                             By.xpath("//*[@id='UIScriptList']//*[contains(text(),'Add Script')]");

  public static final By              ELEMENT_ECM_ADVANCED_SCRIPT_NAME_FORM                        =
                                                                            By.xpath("//*[@id='scriptLabel']");

  public static final By              ELEMENT_ECM_ADVANCED_SCRIPT_CONTENT_FORM                     =
                                                                               By.xpath("//*[@id='scriptContent']");

  public static final By              ELEMENT_ECM_ADVANCED_SCRIPT_SCRIPT_FORM                      =
                                                                              By.xpath(".//*[@id='scriptName']");

  public static final By              ELEMENT_ECM_ADVANCED_SCRIPT_SAVE_FORM                        =
                                                                            By.xpath("//*[@id='ScriptContainerPopup']//*[contains(text(),'Save')]");

  public static final String          ELEMENT_ECM_ADVANCED_SCRIPT_EDIT_LIST                        =
                                                                            "//*[contains(text(),'{$name}')]/../..//*[@title='Edit']";

  public static final String          ELEMENT_ECM_ADVANCED_SCRIPT_DELETE_LIST                      =
                                                                              ".//*[contains(text(),'{$name}')]/../..//*[@title='Delete']";

  public static final String          ELEMENT_ECM_ADVANCED_SCRIPT_LIST                             =
                                                                       ".//*[@id='UIScriptList']//*[contains(text(),'{$name}')]";

  // function Advanced, queries
  public static final By              ELEMENT_ECM_ADVANCED_QUERIES_ADD_QUERIES                     =
                                                                               By.xpath("//*[@id='UIQueriesList']//*[contains(text(),'Add Query')]");

  public static final By              ELEMENT_ECM_ADVANCED_QUERIES_NAME_FORM                       =
                                                                             By.xpath(".//*[@id='UIQueriesForm']//*[@id='name']");

  public static final By              ELEMENT_ECM_ADVANCED_QUERIES_QUERY_TYPE_FORM                 =
                                                                                   By.xpath(".//*[@class='selectbox' and @name='type']");

  public static final By              ELEMENT_ECM_ADVANCED_QUERIES_STATEMENT_FORM                  =
                                                                                  By.xpath(".//*[@id='statement']");

  public static final By              ELEMENT_ECM_ADVANCED_QUERIES_PERMISSION_FORM                 =
                                                                                   By.xpath(".//*[@class='uiIconAddPermission uiIconLightGray']");

  public static final By              ELEMENT_PERMISSION_ANY                                       =
                                                             By.xpath(".//*[@class='uiIconAddAnyPermission uiIconLightGray']");

  public static final By              ELEMENT_ECM_ADVANCED_QUERIES_SAVE_FORM                       =
                                                                             By.xpath(".//*[@id='UIQueriesForm']//*[contains(text(),'Save')]");

  public static final String          ELEMENT_ECM_ADVANCED_QUERIES_EDIT_BUTTON                     =
                                                                               "//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";

  public static final String          ELEMENT_ECM_ADVANCED_QUERIES_TYPE_LIST                       =
                                                                             "//*[contains(text(),'{$name}')]/../../td[2]//*[contains(text(),'{$type}')]";

  public static final String          ELEMENT_ECM_ADVANCED_QUERIES_DELETE_BUTTON                   =
                                                                                 "//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_ECM_ADVANCED_QUERIES_LIST                            =
                                                                        ".//*[@id='UIQueriesList']//*[contains(text(),'{$name}')]";

  // functions Advanced, categories
  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_CATEGORIES               =
                                                                                     By.xpath(".//*[@id='UITaxonomyTreeList']//*[contains(text(),'Add Category Tree')]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_FORM                    =
                                                                                By.xpath(".//*[@id='TaxoTreeName']");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_1STPAGE_FORM            =
                                                                                        By.xpath(".//*[@id='TaxonomyTreeMainForm']//*[contains(text(),'Next')]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_2NDPAGE_FORM            =
                                                                                        By.xpath(".//*[@id='UIPermissionTreeForm']//*[contains(text(),'Next')]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE             =
                                                                                       By.xpath("//*[@id='UIPermissionTreeForm']//*[contains(text(),'Previous')]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_3RDPAGE             =
                                                                                       By.xpath("//*[@id='UIActionTaxonomyManager']//*[contains(text(),'Previous')]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_4THPAGE             =
                                                                                       By.xpath("//*[@id='UITaxonomyTreeCreateChild']//*[contains(text(),'Previous')]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_ACTION_FORM             =
                                                                                       By.xpath(".//*[@id='actionName']");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_LIFECYCLE_FORM               =
                                                                                     By.xpath(".//*[@id='UIActionForm']//*[@class='selectbox']");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_OPEN_TARGETPATH_ACTION_FORM  =
                                                                                                  By.xpath(".//*[@id='UIActionForm']//*[@id='targetPath']/../a[1]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM        =
                                                                                            By.xpath(".//*[@class='uiIconAddRootNode uiIconLightGray']");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_SAVE_FORM                    =
                                                                                By.xpath("//*[@id='UIActionTaxonomyManager']//*[contains(text(),'Save')]");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_CLOSE_FORM                   =
                                                                                 By.xpath("//*[@class='uiTaxonomyTreeWizard']//*[contains(text(),'Close')]");

  public static final String          ELEMENT_ECM_ADVANCED_CATEGORIES_EDIT_FORM                    =
                                                                                ".//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_SELECT_FORM        =
                                                                                            By.xpath("//*[@id='TaxonomyTreeMainForm']//*[@name='TaxoTreeWorkspace']");

  public static final By              ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_HOME_PATH_FORM           =
                                                                                         By.xpath(".//*[@id='TaxonomyTreeMainForm']//*[@class='uiIconAddPath uiIconLightGray']");

  public static final String          ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_LIST               =
                                                                                     ".//*[contains(text(),'{$name}')]/../../td[2]//*[contains(text(),'{$workspace}')]";

  public static final String          ELEMENT_SELECT_CATEGORY                                      =
                                                              ".//*[@class='explorerTree']//*[contains(@title,'${name}')]";

  public static final By              ELEMENT_ADD_SUB_CAT_BUTTON                                   =
                                                                 By.xpath("//*[@data-original-title='Add' or @title='Add']");

  public static final By              ELEMENT_NAME_CAT_TEXTBOX                                     = By.id("taxonomyName");

  public static final By              ELEMENT_ADD_CAT_SAVE_BUTTON                                  =
                                                                  By.xpath("//*[@id='UITaxonomyTreeCreateChildForm']//*[@class='btn' and text()='Save']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_VIEWS                                 =
                                                                   By.xpath("//*[@class='uiIconEcmsViewManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_TAGS                                  =
                                                                  By.xpath("//*[@class='uiIconEcmsFolksonomyManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_NODES                                 =
                                                                   By.xpath("//*[@class='uiIconEcmsNodeTypeManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_NAMESPACES                            =
                                                                        By.xpath("//*[@class='uiIconEcmsNamespaceManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_LOCKS                                 =
                                                                   By.xpath("//*[@class='uiIconEcmsUnLockManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_DOCUMENTS                             =
                                                                       By.xpath("//*[@class='uiIconEcmsTemplatesManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_LIST                                  =
                                                                  By.xpath("//*[@class='uiIconEcmsCLVTemplatesManager uiIconEcmsLightGray']");

  public static final By              ELEMENT_ECMS_FUNCTIONS_METADATA                              =
                                                                      By.xpath("//*[@class='uiIconEcmsMetadataManager uiIconEcmsLightGray']");

  // Explorer, drives
  public static final SelenideElement ELEMENT_ECM_EXPLORER_DRIVES_ADD_DRIVES                       =
                                                                             $(byXpath("//*[@id=\"UIDriveList\"]/div[4]/button"));

  public static final SelenideElement ELEMENT_TAB_ADD_DRIVE_POPUP                                  =
                                                                  $(byXpath("//*[@id=\"AddDriveManagerPopup\"]/div[2]/div/ul/li[1]/a"));

  public static final By              ELEMENT_ECM_EXPLORER_NAME_DRIVES_FORM                        = By.xpath("//*[@id='name']");

  public static final SelenideElement ELEMENT_ECM_EXPLORER_APPLY_VIEWS_FORM                        =
                                                                            $(byXpath("//*[@id=\"AddDriveManagerPopup\"]/div[2]/div/ul/li[2]"));

  public static final SelenideElement ELEMENT_ECM_EXPLORER_APPLY_VIEWS_FORM_EDIT                   =
                                                                                 $(byXpath("//*[@id=\"EditDriveManagerPopup\"]/div[2]/div/ul/li[2]"));

  public static final SelenideElement ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN              =
                                                                                      $(byXpath("//*[@id=\"ViewsInputSet-tab\"]/div/div/div/div[1]/div/span"));

  public static final By              ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES         =
                                                                                           By.xpath("//*[@class='UIFormInputSet']//*[@id='Categories']");

  public static final By              ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS              =
                                                                                      By.xpath("//*[@class='UIFormInputSet']//*[@id='Icons']");

  public static final By              ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST               =
                                                                                     By.xpath("//*[@class='UIFormInputSet']//*[@id='List']");

  public static final By              ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB                =
                                                                                    By.xpath("//*[@class='UIFormInputSet']//*[@id='Web']");

  public static final String          ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ITEM               =
                                                                                     "//*[@class='UIFormInputSet']//*[@id='$item']";

  public static final SelenideElement ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM                        =
                                                                            $(byXpath("//*[@id=\"UIDriveForm\"]/div[4]/button[1]"));

  public static final String          ELEMENT_ECM_EXPLORER_DRIVES_EDIT_LIST                        =
                                                                            "//*[@id='UIDriveList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";

  public static final String          ELEMENT_ECM_EXPLORER_DRIVES_VIEW_OF_VIEWS_LIST               =
                                                                                     "//*[@id='UIDriveList']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$view}')]";

  // Explorer, views
  public static final By              ELEMENT_ECM_EXPLORER_VIEWS_ADD_VIEWS                         =
                                                                           By.xpath("//*[@class='UIViewList']//*[contains(text(),'Add View')]");

  public static final By              ELEMENT_ECM_EXPLORER_NAME_VIEW_FORM                          =
                                                                          By.xpath("//*[@id='viewName']");

  public static final By              ELEMENT_ECM_EXPLORE_TAB_NAME_VIEW_FORM                       =
                                                                             By.xpath("//*[@id='tabName']");

  public static final By              ELEMENT_ECM_EXPLORE_SAVE_TAB_VIEW_FORM                       =
                                                                             By.xpath("//*[@id='UITabForm']//*[contains(text(),'Save')]");

  public static final By              ELEMENT_ECM_EXPLORER_GO_TO_PERMISSION_FORM                   =
                                                                                 By.xpath("//*[@id='UIViewFormTabPane']//*[contains(text(),'Permission')]");

  public static final By              ELEMENT_ECM_EXPLORER_ADD_PERMISSION_FORM                     =
                                                                               By.xpath("//*[@class='permission']//*[contains(text(),'Add')]");

  public static final By              ELEMENT_ECM_EXPLORER_USER_PERMISSION_ADD                     =
                                                                               By.xpath("//*[@id='UIViewPermissionForm']//*[@class='uiIconSelectUser uiIconLightGray']");

  public static final String          ELEMENT_ECM_EXPLORER_SELECT_USER_LIST_PERMISSION             =
                                                                                       "//*[@id='UIListUsers']//*[@class='text' and contains(text(),'{$user}')]/../../td[5]/a";

  public static final By              ELEMENT_ECM_EXPLORER_SAVE_FORM_ADD_VIEW                      =
                                                                              By.xpath("//*[@id='UIViewFormTabPane']//*[contains(text(),'Save')]");

  public static final String          ELEMENT_ECM_EXPLORER_DELETE_PERMISSION_USER                  =
                                                                                  "//*[@id='UIViewPermissionList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST                          =
                                                                          "//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";

  public static final String          ELEMENT_ECM_EXPLORER_VIEW_DELETE_LIST                        =
                                                                            "//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_ECM_EXPLORER_VIEW_PERMISSIONS_LIST                   =
                                                                                 "//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$permission}')]";

  public static final String          ELEMENT_ECM_EXPLORER_VIEW_SHOW_A_VIEW_LIST                   =
                                                                                 "//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconView uiIconLightGray']";

  public static final String          ELEMENT_ECM_EXPLORER_NAME_VIEW_SHOW_VIEW                     =
                                                                               "//*[@id='viewName' and @value='{$name}']";

  public static final String          ELEMENT_ECM_EXPLORER_TAB_ICONS_LIST_SHOW_VIEW                =
                                                                                    "//*[@id='UITabList']//*[contains(text(),'{$tab}')]";

  // Explorer,Views-->Actions tab
  public static final By              ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM                       =
                                                                             By.xpath("//*[@id='UIViewFormTabPane']//*[contains(text(),'Action')]");

  public static final By              ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM                   =
                                                                                 By.xpath(".//*[@id='UITabList']//i[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_ECM_EXPLORER_ADD_ACTION_VIEW_FORM                    =
                                                                                By.xpath(".//*[@id='UITabContainer']//button[text()='Add']");

  // Explorer,Views-->Actions tab-->Add/Edit popup
  public static final By              ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE                         =
                                                                           By.xpath(".//*[@id='UIViewFormTabPane']//*[contains(text(),'Close')]");

  public static final String          ELEMENT_ECM_EXPLORER_EDIT                                    =
                                                                ".//*[@data-original-title='${nameView}']/../..//i[@class='uiIconEditInfo uiIconLightGray']";

  public static final By              ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON                  =
                                                                                  By.xpath(".//*[@id='UIViewFormTabPane']//button[text()='Save']");

  // Expolorer,Views-->Actions tab-->Add/Edit popup
  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_CATEGORY              = By.id("addCategory");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_TRANSLATION           = By.id("addLocalizationLink");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_CONTENT_NAVIGATION        = By.id("contentNavigation");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_IMPORT_NOTE               = By.id("importNode");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_CATEGORIES         = By.id("manageCategories");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_RELATIONS          = By.id("manageRelations");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_APPROVE_CONTENT           =
                                                                                         By.id("publicationApproveContent");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_SHOW_JCR_STRUCTURE        = By.id("showJCRStructure");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_METADATA             = By.id("viewMetadatas");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_PROPERTIES           = By.id("viewProperties");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_DOCUMENT              = By.id("addDocument");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_SYMLINK               = By.id("addSymLink");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_EDIT_DOCUMENT             = By.id("editDocument");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_ACTIONS            = By.id("manageActions");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_HIDE_SHOW_CONTENT         = By.id("manageHidden");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_VERSIONS           = By.id("manageVersions");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_PUBLISH                   = By.id("publicationPublish");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_TAG_DOCUMENT              = By.id("taggingDocument");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_NODE_TYPE            = By.id("viewNodeType");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VOTE                      = By.id("vote");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_FOLDER                = By.id("addFolder");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_COMMENT                   = By.id("comment");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_EXPORT_NODE               = By.id("exportNode");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_AUDITING           = By.id("manageAuditing");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_PUBLICATION        = By.id("managePublications");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_OVERLOAD_THUMBNAIL        = By.id("overloadThumbnail");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_REQUEST_APPROVAL          =
                                                                                          By.id("publicationRequestApproval");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_UPLOAD                    = By.id("upload");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_PERMISSIONS          = By.id("viewPermissions");

  public static final By              ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_WATCH_DOCUMENT            = By.id("watchDocument");

  public static final By              ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON                =
                                                                                    By.xpath(".//*[@id='UITabForm']//button[text()='Save']");

  // explorer, tags
  public static final By              ELEMENT_ECM_EXPLORER_TAGS_TAG_PERM_TAB                       =
                                                                             By.xpath("//*[contains(@data-target,'UITagPermissionManager')]");

  public static final By              ELEMENT_ECM_EXPLORER_TAGS_ADD_STYLE_BUTTON                   =
                                                                                 By.xpath("//*[@id='UITagManager']//*[contains(text(),'Add Style')]");

  public static final By              ELEMENT_ECM_EXPLORER_TAGS_ADD_NAME_FORM                      =
                                                                              By.xpath("//*[@id='styleName']");

  public static final By              ELEMENT_ECM_EXPLORER_TAGS_NUMBER_OCCURENCE_FORM              =
                                                                                      By.xpath("//*[@id='documentRange']");

  public static final By              ELEMENT_ECM_EXPLORER_TAGS_HTML_STYLE_FORM                    =
                                                                                By.xpath("//*[@id='styleHTML']");

  public static final By              ELEMENT_ECM_EXPLORER_TAGS_UPDATE_FORM                        =
                                                                            By.xpath("//*[@id='UITagStyleForm']//*[contains(text(),'Update')]");

  public static final String          ELEMENT_ECM_EXPLORER_TAGS_EDIT_LIST                          =
                                                                          "//*[@id='UITagManager']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";

  public static final String          ELEMENT_ECM_EXPLORER_TAGS_DELETE_LIST                        =
                                                                            "//*[@id='UITagManager']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconRemove uiIconLightGray']";

  public static final String          ELEMENT_ECM_EXPLORER_TAGS_LIST_CHECK_HTML_CONTENT            =
                                                                                        "//*[@id='UITagManager']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$html}')]";

  public static final By              ELEMENT_ECM_EXPLORER_TAGS_PERM_TAB                           =
                                                                         By.xpath(".//*[contains(@data-target,'TagPermissionManager')]");

  // common element
  public static final By              ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON                     =
                                                                               By.xpath(".//*[@class='uiIconAddPermission uiIconLightGray']");

  public static final By              ELEMENT_ECM_COMMON_ANY_PERMISSION                            =
                                                                        By.xpath("//*[@class='uiIconAddAnyPermission uiIconLightGray']");

  // repository, nodes type
  public static final By              ELEMENT_ECM_REPOSITORY_NODES_ADD                             =
                                                                       By.xpath("//*[@id='ListNodeType']//*[contains(text(),'Add')]");

  public static final By              ELEMENT_ECM_REPOSITORY_NODES_NAME_FORM                       =
                                                                             By.xpath("//*[@id='nodeTypeName']");

  public static final By              ELEMENT_ECM_REPOSITORY_NODES_SUPER_TYPES_FORM                =
                                                                                    By.xpath("//*[@id='superTypes']");

  public static final By              ELEMENT_ECM_REPOSITORY_NODES_MIXIN_TYPES                     =
                                                                               By.xpath("//*[@name='mixinType']");

  public static final By              ELEMENT_ECM_REPOSITORY_NODES_SAVE_FORM                       =
                                                                             By.xpath("//*[@id='UINodeTypeForm']//*[contains(text(),'Save')]");

  public static final By              ELEMENT_ECM_REPOSITORY_NODES_SEARCH_NODE                     =
                                                                               By.xpath("//*[@id='NodeTypeText']");

  public static final String          ELEMENT_ECM_REPOSITORY_NODES_SHOW_SPECIFIC_NODE              =
                                                                                      ".//*[@id='ListNodeType']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconPreview uiIconLightGray']";

  public static final String          ELEMENT_ECM_REPOSITORY_NODES_CHECK_SUPER_TYPES               =
                                                                                     "//*[@id='superTypes' and @value='{$types}']";

  public static final By              ELEMENT_ECM_REPOSITORY_NODES_CLOSE_FORM                      =
                                                                              By.xpath("//*[@id='UINodeTypeForm']//*[contains(text(),'Close')]");

  public static final By              ELEMENT_ECM_REPOSITORY_NODES_OK_FORM                         =
                                                                           By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(),'OK')]");

  // repository, namespaces
  public static final By              ELEMENT_ECM_REPOSITORY_NAMESPACES_ADD                        =
                                                                            By.xpath("//*[@id='UINamespaceManager']//*[contains(text(),'Register')]");

  public static final By              ELEMENT_ECM_REPOSITORY_NAMESPACES_FORM_NAME                  =
                                                                                  By.xpath("//*[@id='namespace']");

  public static final By              ELEMENT_ECM_REPOSITORY_NAMESPACES_URI_FORM                   = By.xpath("//*[@id='uri']");

  public static final By              ELEMENT_ECM_REPOSITORY_NAMESPACES_SAVE_FORM                  =
                                                                                  By.xpath("//*[@id='UINamespaceForm']//*[contains(text(),'Save')]");

  public static final String          ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX  =
                                                                                                  ".//*[@id='UINamespaceList']//*[contains(text(),'{$prefix}')]/../..//*[contains(text(),'{$url}')]";

  // repository Locks
  public static final By              ELEMENT_ECM_REPOSITORY_LOCKS_DEVELOPMENT_GROUP               =
                                                                                     By.xpath("//*[@id='UIPermissionSelector']//*[contains(text(),'Development')]");

  public static final By              ELEMENT_ECM_REPOSITORY_LOCKS_ALL_GROUP                       =
                                                                             By.xpath("//*[@id='UIPermissionSelector']//*[contains(text(),'*')]");

  public static final String          ELEMENT_ECM_REPOSITORY_CHECK_LOCK_PERMISSION                 =
                                                                                   "//*[@id='UILockHolderList']//*[contains(text(),'{$group}')]";

  public static final String          ELEMENT_ECM_REPOSITORY_DELETE_LOCK_PERMISSION                =
                                                                                    "//*[@id='UILockHolderList']//*[contains(text(),'{$group}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By              ELEMENT_ECM_REPOSITORY_MANAGE_LOCK                           =
                                                                         By.xpath("//*[@id='UIUnLockManager']//*[contains(text(),'Manage Lock')]");

  public static final String          ELEMENT_ECM_REPOSITORY_UNLOCK_NODE_LIST                      =
                                                                              "//*[@id='UILockNodeList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconUnlockMini uiIconLightGray']";

  // templates, Documents
  public static final SelenideElement ELEMENT_ECM_TEMPLATES_DOCUMENTS_ADD_DOCUMENT                 =
                                                                                   $(byXpath("//*[@id=\"UITemplateContainer\"]/div[2]/div/button"));

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_LABEL_FORM                   = By.xpath("//*[@id='label']");

  public static final SelenideElement ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_FORM                    =
                                                                                $(byXpath("//*[@id=\"UITemplateForm\"]/div[3]/button[1]"));

  public static final String          ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST                         =
                                                                           "//*[@id='UITemplateList']//*[contains(text(),'{$name}')]";

  public static final String          ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_EDIT                    =
                                                                                "//*[@id='UITemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";

  public static final SelenideElement ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_EDIT_FORM               =
                                                                                     $(byXpath("//*[@id=\"UITemplateEditForm\"]/div[3]/button[1]"));

  public static final String          ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_DELETE                  =
                                                                                  "//*[@id='UITemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_TAB                   =
                                                                                 By.xpath("//*[@data-toggle='tab'][contains(.,'Dialog')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_EDIT                  =
                                                                                  By.xpath("//*[@id='DialogList']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_REMOVE_PERM           =
                                                                                         By.xpath(".//*[@id='DialogForm']//*[contains(@class,'uiIconRemovePermission')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_ADD_PERM              =
                                                                                      By.xpath(".//*[@id='DialogForm']//*[contains(@class,'uiIconAddPermission')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_SAVE                  =
                                                                                  By.xpath(".//*[@id='DialogForm']//*[contains(.,'Save')][@class='btn']");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_TAB                     =
                                                                               By.xpath("//*[@data-toggle='tab'][contains(.,'View')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_EDIT                    =
                                                                                By.xpath("//*[@id='VewList']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_REMOVE_PERM             =
                                                                                       By.xpath("//*[@id='ViewForm']//*[contains(@class,'uiIconRemovePermission')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_ADD_PERM                =
                                                                                    By.xpath("//*[@id='ViewForm']//*[contains(@class,'uiIconAddPermission')]");

  public static final By              ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_SAVE                    =
                                                                                By.xpath("//*[@id='ViewForm']//*[contains(.,'Save')][@class='btn']");

  // templates, List
  public static final By              ELEMENT_ECM_TEMPLATES_LIST_ADD_LIST                          =
                                                                          By.xpath("//*[@id='ContentTemplateContainer']//*[contains(text(),'Add Template')]");

  public static final By              ELEMENT_ECM_TEMPLATES_LIST_TEMPLATE_NAME_FORM                =
                                                                                    By.xpath("//*[@id='template']");

  public static final By              ELEMENT_ECM_TEMPLATES_LIST_NAME_FORM                         = By.xpath("//*[@id='title']");

  public static final By              ELEMENT_ECM_TEMPLATES_LIST_CONTENT_FORM                      =
                                                                              By.xpath("//*[@id='content']");

  public static final By              ELEMENT_ECM_TEMPLATES_LIST_SAVE_FORM                         =
                                                                           By.xpath("//*[@id='UICLVTemplateForm_ContentTemplateContainer']//*[contains(text(),'Save')]");

  public static final String          ELEMENT_ECM_TEMPLATES_LIST_CHECK_LIST                        =
                                                                            "//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$template}')]";

  public static final String          ELEMENT_ECM_TEMPLATES_LIST_CHECK_BY_NAME                     =
                                                                               "//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]";

  public static final String          ELEMENT_ECM_TEMPLATES_LIST_EDIT_LIST                         =
                                                                           "//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";

  public static final String          ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST                       =
                                                                             "//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_ECM_TEMPLATES_METADATA_FORM_EDIT                     =
                                                                               "//*[@id='UIMetadataList']//*[contains(text(),'{$name}')]/..//*[@class='uiIconEdit uiIconLightGray']";

  public static final By              ELEMENT_ECM_TEMPLATES_METEDATA_FORM_EDIT_LABEL               =
                                                                                     By.xpath("//*[@id='metadataLabel']");

  public static final By              ELEMENT_ECM_TEMPLATES_METADATA_FORM_APPLY                    =
                                                                                By.xpath("//*[@id='UIMetadataForm']//*[contains(text(),'Apply')]");

  public static final String          ELEMENT_ECM_TEMPLATES_METADATA_FORM_SHOW                     =
                                                                               "//*[@id='UIMetadataList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconView uiIconLightGray']";

  public static final String          ELEMENT_ECM_TEMPLATES_METADATA_CHECK_MATADATA_INFORMATION    =
                                                                                                "//*[@class='metadataInfoDetails']//*[contains(text(),'{$metadata}')]";

  public static final By              ELEMENT_ECM_TEMPLATES_METADATA_CLOSE_VIEW                    =
                                                                                By.xpath("//*[@id='ViewMetadataPopup']//*[contains(text(),'Close')]");

  public static final String          ELEMENT_ECM_TEMPLATES_METADATA_FORM_DELETE                   =
                                                                                 "//*[@id='UIMetadataList']//*[contains(text(),'{$name}')]/..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By              ELEMENT_ECM_TEMPLATES_METADATA_FORM_OK_FORM                  =
                                                                                  By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(),'OK')]");

  /*****************************************************
   * CHANGE LANGUAGES
   ****************************************************************************************/
  public static final By              ELEMENT_CHANGE_LANGUAGE_POPUP_TITLE                          =
                                                                          By.xpath(".//*[@id='UIMaskWorkspace']//h5");


  public static final String          ELEMENT_CHANGELANGUAGE_LANGUAGE                              = "//*[text()='${language}']";

  public static final String          ELEMENT_AVATAR_CHANGELANGUAGE_APPLY                          = "//*[text()='${text}']";

  /*****************************************************
   * CONTENT SEARCH ADMINISTRATION
   *****************************************************************************/
  // table
  public static final By              ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_TITLE             =
                                                                                       By.xpath(".//*[@id='searchAdmin']//th[text()='Content Type']");

  public static final By              ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_DESCRIPTION       =
                                                                                             By.xpath(".//*[@id='searchAdmin']//th[text()='Content Type']");

  public static final By              ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_ACTION            =
                                                                                        By.xpath(".//*[@id='searchAdmin']//th[text()='Content Type']");

  /***************************************************
   * MANAGE LAYOUT
   ***********************************************************************************************/
  public static final By              ELEMENT_PERMISSION_PUBLIC_CHECKBOX                           =
                                                                         By.xpath(".//*[contains(@id,'UIListPermissionSelector')]//input[@id='publicMode']");

  public static final By              ELEMENT_PERMISSION_GRID                                      =
                                                              By.xpath(".//*[@id='PermissionGrid']");

  // *=========================================================EDIT/SITE/LAYOUT==============================================*\
  // Edit Portlet popup
  public static final By              ELEMENT_PORTLET_POPUP_PERMISSION_TAB                         =
                                                                           By.xpath(".//*[@data-target='#PortletPermission-tab']");

  public static final By              ELEMETN_PORTLET_POPUP_SAVE_BTN                               = By.xpath(".//*[@id='Save']");

  // HOME PAGE LAYOUT
  public static final By              ELEMENT_EDIT_PORTLET_ABORT                                   =
                                                                 By.xpath("//*[@data-original-title='Abort']");
  // *=============================================================EDIT/PAGE/EDIT
  // LAYOUT===========================================*\
  public static final By              ELEMENT_PAGE_EDIT_LAYOUT_CONTAINER_TAB                       =
                                                                             By.xpath(".//*[@data-target='#contList']");

  // EDIT CONTAINER POPUP
  public static final By              ELEMENT_CONTAINER_POPUP_PERMISSION_TAB                       =
                                                                             By.xpath(".//*[@data-target='#UIContainerPermission-tab']");

  // *=============================================================*\\
  // Site permission
  public static final String          ELEMENT_PERMISSION_ADD_USER_BTN                              =
                                                                      "//*[contains(@id,'${tabName}')]//*[@class='uiIconAddUser uiIconWhite']";

  public static final String          ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME      =
                                                                                              ".//*[contains(@id, '${tabName}')]//*[@class='PopupContent popupContent']//*[@title='${name}']";

  public static final String          ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME                 =
                                                                                   ".//*[contains(@id, '${tabName}')]//*[@id='PermissionGrid']//*[contains(text(),'${group}')]";

  public static final String          ELEMENT_PORTLET_SELECT_PERMISSION_POPUP_TITLE                =
                                                                                    ".//*[contains(@id, '${tabName}')]//*[@class='PopupTitle popupTitle' and (text()='${popupTitle}')]";
  // Site permission

  public static final By              ELEMENT_BTN_DELETE_DRIVE                                     = byClassName("uiIconDelete");

  public static final By ELEMENT_ICON_NEXT_ARROW=byClassName("uiIconNextArrow");

}
