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
package org.exoplatform.platform.qa.ui.selenium.locator.ecms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class ECMSLocator {

  /*******************************************
   * CREATE NEW DOCUMENT
   **************************************************************************************/

  // template form
  public static final By ELEMENT_ADDDOCUMENT_FILE =
          By.xpath("//*[@class='uiIcon64x64Templatent_file']");

  public static final By ELEMENT_ADDDOCUMENT_WEBCONTENT =
          By.xpath("//*[@class='uiIcon64x64Templateexo_webContent']");

  public static final By ELEMENT_ADDDOCUMENT_ACCESSIBLE_MEDIA =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_accessibleMedia']");

  public static final By ELEMENT_ADDDOCUMENT_ANNOUNCEMENT =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_announcement']");

  public static final By ELEMENT_ADDDOCUMENT_CSS_FILE =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_cssFile']");

  public static final By ELEMENT_ADDDOCUMENT_CONTACT_US =
          By.xpath(".//*[@class='uiIcon64x64Templateacme_contact_us']");

  public static final By ELEMENT_ADDDOCUMENT_HTML_FILE =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_htmlFile']");

  public static final By ELEMENT_ADDDOCUMENT_ILLUSTRATED_WEB_CONTENT =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_pictureOnHeadWebcontent']");

  public static final By ELEMENT_ADDDOCUMENT_JAVASCRIPT_FILE =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_jsFile']");

  public static final By ELEMENT_ADDDOCUMENT_WEBLINK =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_link']");

  public static final By ELEMENT_ADDDOCUMENT_PRODUCT =
          By.xpath("//*[@class='uiIcon64x64Templateacme_product']");

  public static final By ELEMENT_ADDDOCUMENT_NEXT_PAGE =
          By.xpath(".//*[@id='UISelectDocumentForm']//*[@data-original-title='Next Page']");

  // New file form
  public static final By ELEMENT_FILEFORM_BLANK_CONTENT2 =
          By.xpath("//*[@id='cke_1_contents']/iframe");

  public static final SelenideElement ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE =
          $(byId("UIDocumentForm")).find(byText("Save & Close"));

  public static final By ELEMENT_FILEFORM_LANGUAGE =
          By.xpath("//*[@name='content-lang']");

  // New Web content form
  public static final By ELEMENT_WEBCONTENTFORM_BUTTON_LINK =
          By.xpath("//*[@class='cke_button_icon cke_button__link_icon']");

  public static final By ELEMENT_WEBCONTENTFORM_LINK_ADRESS =
          By.xpath("//*[text()='URL']/../..//*[contains(@id,'textInput')]");

  public static final By ELEMENT_WEBCONTENTFORM_LINK_OK =
          By.xpath("//*[@class='cke_dialog_body']//*[text()='OK']");

  // New folder popup
  public static final By ELEMENT_ADD_NEW_FOLDER_POPUP_TITLE =
          By.xpath(".//*[@id='UIPopupWindow']//span[text()='New Folder']");

  public static final By ELEMENT_USE_CUSTOM_TYPE_FOLDER =
          By.id("customTypeCheckBox");

  public static final By ELEMENT_FOLDER_TITLE_TEXTBOX =
          By.id("titleTextBox");

  public static final By ELEMENT_FOLDER_TYPE_OPTION =
          By.name("customTypeSelectBox");

  public static final String ELEMENT_DOCUMENT_FOLDER_TYPE = "nt:folder";

  public static final By ELEMENT_CREATE_FOLDER_BUTTON =
          By.xpath("//*[text()='Create Folder']");

  /*******************************************
   * PERMISSION
   **************************************************************************************/
  public static final By ELEMENT_PERMISSION_SELECTUSER =
          By.xpath("//*[@class='uiIconSelectUser uiIconLightGray']");

  public static final By ELEMENT_PERMISSION_SELECTMEMBERSHIP =
          By.xpath("//*[@class='uiIconSelectMember uiIconLightGray']");

  public static final By ELEMENT_PERMISSION_SELECTEVERYONE =
          By.xpath("//*[@class='uiIconAddAny uiIconLightGray']");

  public static final String ELEMENT_PERMISSION_USER_ADDUSER =
          "//*[text()='${name}']/../..//*[@class='actionIcon']";

  public static final By ELEMENT_PERMISSION_TEXTBOXUSER =
          By.xpath("//*[@id='userOrGroup']");

  public static final By ELEMENT_PERMISSION_CHECKBOXREAD =
          By.xpath("//*[@class='checkbox' and @for='read']");

  public static final By ELEMENT_PERMISSION_CHECKBOXMODIFY =
          By.xpath("//*[@class='checkbox' and @for='add_node']");

  public static final By ELEMENT_PERMISSION_CHECKBOXREMOVE =
          By.xpath("//*[@class='checkbox' and @for='remove']");

  public static final By ELEMENT_PERMISSION_SAVE =
          By.xpath("//*[text()='Save']");

  public static final By ELEMENT_PERMISSION_CLOSE =
          By.xpath("//*[text()='Close']");

  public static final String ELEMENT_PERMISSION_DELETE =
          "//*[text()='${name}']/../..//*[@class='actionIcon']";

  public static final String ELEMENT_PERMISSION_USER_OR_GROUP_NAME =
          ".//*[@id='PermissionInfo']//*[text()='${name}']";

  public static final By ELEMENT_PERMISSION_ADD =
          By.xpath("//*[@id='UIQueriesForm']//*[contains(@class,'uiIconAddPermission')]");

  public static final By ELEMENT_CAT_SELECT_MEMBERSHIP_POPUP =
          By.xpath(".//*[contains(@id,'SelectUserOrGroup')]");

  public static final String ELEMENT_CAT_SELECT_RIGHT_PARENT_GROUP =
          "//*[contains(@id,'SelectUserOrGroup')]//a[contains(.,'$group')]";

  public static final By ELEMENT_LOCK_SELECT_MEMBERSHIP_POPUP =
          By.xpath(".//*[contains(@id,'UIPermissionSelector')]");

  public static final String ELEMENT_LOCK_SELECT_RIGHT_PARENT_GROUP =
          "//*[contains(@id,'UIPermissionSelector')]//a[contains(.,'$group')]";

  public static final By ELEMENT_TAG_SELECT_MEMBERSHIP_POPUP =
          By.xpath(".//*[contains(@id,'UIGroupMemberSelector')]");

  public static final String ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP =
          "//*[contains(@id,'UIGroupMemberSelector')]//a[contains(.,'$group')]";

  /*******************************************
   * SEO MANAGEMENT
   **************************************************************************************/
  public static final By ELEMENT_SEO_SAVE =
          By.xpath(".//*[@id='RightContainer']//button[text()='Save']");

  public static final By ELEMENT_SEO_CLOSE =
          By.xpath(".//*[@id='UISEOPopupWindow']//a[@class='uiIconClose pull-right']");

  public static final By ELEMENT_SEO_LANGUAGE_SHOW =
          By.xpath("//*[@class='uiIconPlus uiIconLightGray']");

  public static final By ELEMENT_SEO_LANGUAGE_SELECTBOX =
          By.xpath("//select[@name='language']");

  public static final By ELEMENT_SEO_TITLEBOX =
          By.xpath("//*[@id='title']");

  public static final By ELEMENT_SEO_DELETE =
          By.xpath("//*[@title='Delete']");

  public static final SelenideElement ELEMENT_SEO_HELPDESC =
          $(byId("description")).parent().find(byClassName("uiIconQuestion"));

  public static final SelenideElement ELEMENT_SEO_HELPKEYWORD =
          $(byId("keywords")).parent().find(byClassName("uiIconQuestion"));

  public static final SelenideElement ELEMENT_SEO_HELPPRIORITY =
          $(byId("priority")).parent().find(byClassName("uiIconQuestion"));

  public static final By ELEMENT_SEO_HELP_POPOVER =
          By.xpath("//*[@class='popover-content']");

  public static final String ELEMENT_SEO_SELECTED_LANGUAGE =
          ".//*[@id='LeftContainer']//*[contains(text(),'${language}')]";

  /*******************************************
   * SITE EXPLORER HOME
   **************************************************************************************/
  public static final By ELEMENT_DOCUMENT_LIST_ROW_CONTENT =
          By.xpath(".//*[@id='UIDocumentNodeList']//*[contains(@class,'rowView')]");

  // Address Bar
  public static final SelenideElement ELEMENT_ADDRESS_BAR_ICON_VIEW =
          $(byClassName("uiIconEcmsViewIcons"));

  public static final SelenideElement COPY_URL_TO_CLIPBOARD_BUTTON =
          $(byClassName("uiIconEcmsCopyUrlToClipboard"));

  public static final SelenideElement OPEN_IN_DOCUMENT_ICON =
          $(byClassName("uiIconGotoFolder"));

  public static final SelenideElement GO_BACK_ICON =
          $(byClassName("uiIconEcmsGoBack"));

  // Action Bar
  public static final By ELEMENT_ACTIONBAR_ADDDOCUMENT =
          By.xpath("//*[@class='uiIconEcmsAddDocument uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_ADDFOLDER =
          By.xpath("//*[@class='uiIconEcmsAddFolder uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_SETTINGS =
          By.xpath("//*[@class='setupPreferencesButton actionIcon pull-right']");

  public static final By ELEMENT_ACTIONBAR_PERMISSION =
          By.xpath("//*[@class='uiIconEcmsViewPermissions uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_SEARCHBAR =
          By.xpath("//*[@id='simpleSearch']");

  public static final By ELEMENT_ACTIONBAR_MORE =
          By.xpath("//*[@class='dropdown pull-right listHiddenActionsContainer']/..//*[text()='More ']");

  public static final By ELEMENT_ACTIONBAR_METADATA =
          By.xpath(".//*[@class='uiIconEcmsViewMetadatas uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_ADDTRANSLATION =
          By.xpath("//*[@class='uiIconEcmsAddLocalizationLink uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_ADDCOMMENT =
          By.xpath("//*[@class='uiIconEcmsComment uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_PUBLICATION =
          By.xpath("//*[@class='uiIconEcmsManagePublications uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_VOTE =
          By.xpath("//*[@class='uiIconEcmsVote uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_RELATION =
          By.xpath(".//i[@class='uiIconEcmsManageRelations uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_IMPORT_BUTTON =
          By.xpath(".//i[@class='uiIconEcmsImportNode uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_EXPORT_BUTTON =
          By.xpath(".//*[@class='uiIconEcmsExportNode uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_PROPERTIES =
          By.xpath(".//i[@class='uiIconEcmsViewProperties uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION =
          By.xpath(".//i[@class='uiIconEcmsManagePublications uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_CATEGORY =
          By.xpath("//*[@class='uiIconEcmsManageCategories uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_TAG =
          By.xpath("//*[@class='uiIconEcmsTaggingDocument uiIconEcmsLightGray']");

  public static final By ELEMENT_ACTIONBAR_SHOWDRIVES = By.id("driveAction");

  public static final By ELEMENT_DELETE_ALL_BUTTON =
          By.xpath(".//*[@id='JCRContextMenu']//i[@class='uiIconEcmsDelete']");

  // Add Category popup
  public static final By ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB =
          By.xpath(".//*[@id='UICategoryManager']//a[text()='Select Category']");

  public static final By ELEMENT_ADD_CATEGORY_POPUP_CLOSED_BUTTON =
          By.xpath(".//*[@id='UICategoryManager']//button[text()='Close']");

  public static final String ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY =
          ".//*[@id='UICategoriesAddedList']//td[contains(.,'${nameCategory}')]/../..//i[@class='uiIconDelete uiIconLightGray']";

  // Import Node popup
  public static final By ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON =
          By.xpath(".//*[@id='UIImportNode']//button[text()='Import']");

  // Export Node popup
  public static final By ELEMENT_EXPORT_NODE_POPUP_TITLE =
          By.xpath(".//*[@id='UIPopupWindow']//span[text()='Export']");

  public static final By ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW =
          By.xpath(".//*[@id='UIExportNode']//input[@value='docview']");

  public static final By ELEMENT_EXPORT_NODE_POPUP_ZIP = By.name("zip");

  public static final By ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON =
          By.xpath("//button[text()='Export']");

  // Relation popup
  public static final By ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB =
          By.xpath(".//*[@id='UIRelationManager']//a[text()='Select Relation']");

  public static final String ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_NODE_LEFT_TREE =
          ".//*[@id='UIOneNodePathSelector']//i[@title='${nameNode}']";

  public static final String ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_SELECT_CONTENT_RIGHT_TREE =
          ".//*[@id='UISelectPathPanel']//div[contains(.,'${nameContent}')]/../..//*[@class='uiIconValidate uiIconLightGray']";

  public static final By ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_CLOSE_BUTTON =
          By.xpath(".//*[@id='UIRelationManager']//button[text()='Close']");

  public static final String ELEMENT_RELATION_POPUP_RELATION_LIST_DELETE_BUTTON =
          ".//*[@id='RelateAddedList']//span[contains(.,'${nameContent}')]/../..//i[@class='uiIconDelete uiIconLightGray']";

  public static final String MESSAGE_DELETE_RELATION =
          "Are you sure you want to delete this relation?";

  // Metadata popup
  public static final By ELEMENT_METADATA_POPUP_CANCEL =
          By.xpath(".//*[@id='UIViewMetadataContainer']//button[text()='Cancel']");

  public static final By ELEMENT_METADATA_POPUP =
          By.xpath("//*[@id='UIViewMetadataManager']");

  // View Properties form
  public static final By ELEMENT_VIEWPROPERTIES_PROPERTIES_TAB =
          By.linkText("Properties");

  public static final By ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_TAB =
          By.linkText("Add New Property");

  public static final By ELEMENT_VIEWPROPERTIES_VALUE_INPUT =
          By.xpath("//input[contains(@id,'value')]");

  public static final By ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_INPUT =
          By.name("property_select");

  public static final String ELEMENT_VIEWPROPERTIES_PROPERTY =
          "//td[text()='{$property}']/..//div[contains(text(),'{$value}')]";

  // Manage Publishtation popup
  public static final String ELEMENT_MANAGEPUBLICATION_STATE =
          "//p[contains(text(),'{$state}')]/../a[@class='node']";

  public static final String ELEMENT_MANAGEPUBLICATION_CURRENT_SPECIFIC_STATUS =
          "//*[@class='currentStatus']/p[contains(text(),'${status}')]";

  public static final By ELEMENT_MANAGEPUBLICATION_SCHEDULE_TAB =
          By.xpath("//a[text()='Scheduled']");

  public static final By ELEMENT_MANAGEPUBLICATION_PUB_FROM_INPUT =
          By.name("UIPublicationPanelStartDateInput");

  public static final By ELEMENT_MANAGEPUBLICATION_PUB_TO_INPUT =
          By.name("UIPublicationPanelEndDateInput");

  public static final String MSG_INVALID_DATE_TIME =
          "The date format is invalid. Please check again.";

  public static final By ELEMENT_MANAGEPUBLICATION_REVISION_TAB =
          By.linkText("Revision");

  public static final By ELEMENT_MANAGEPUBLICATION_HISTORY_TAB =
          By.linkText("History");

  public static final String ELEMENT_MANAGEPUBLICATION_HISTORY_ITEM =
          "//div[text()='${state}']";

  // add translation popup
  public static final By ELEMENT_ADDTRANSLATION_SELECTDOC =
          By.xpath("//*[@title='Select Document']");

  public static final By ELEMENT_SAVE_BTN =
          By.xpath("//*[text()='Save']");

  public static final By ELEMENT_CLOSE_BTN =
          By.xpath("//*[text()='Close']");

  // Select document popup
  public static final String ELEMENT_SELECT_DOCUMENT_NODE_FOLDER =
          ".//*[@id='LeftWorkspace']//span[contains(.,'${node}')]";

  public static final String ELEMENT_SELECT_DOCUMENT_NODE_FILE =
          ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";

  // settings driver display
  public static final By ELEMENT_DRIVERSETTINGS_SORTBY =
          By.xpath("//*[@class='selectbox' and @name='sortBy']");

  public static final By ELEMENT_DRIVERSETTINGS_ORDER =
          By.xpath("//*[@class='selectbox' and @name='order']");

  public static final By ELEMENT_DRIVERSETTINGS_SAVE =
          By.xpath("//*[@class='btn btn-primary' and text()='Save']");

  // upload
  public static final By ELEMENT_ACTIONBAR_EDIT =
          By.xpath("//*[@class='uiIconEcmsEditDocument uiIconEcmsLightGray']");

  public static final By ELEMENT_FILE_FORM_TITLE =
          By.xpath("//*[@id='title0']");

  // Drive selection
  public static final String ELEMENT_SELECTDRIVE_SPECIFICDRIVE =
          "//*[@class='driveLabel' and @data-original-title='${spaceName}']";

  // Add document
  public static final By ELEMENT_ADDDOCUMENT_CHOICETYPE =
          By.xpath("//*[@class='templateTitle']");

  // Add folder
  public static final By ELEMENT_ADDFOLDERBOX =
          By.xpath("//*[@class='PopupTitle popupTitle']");

  public static final By ELEMENT_ADDFOLDER_NAME =
          By.xpath("//*[@id='titleTextBox']");

  public static final By ELEMENT_ADDFOLDER_FOLDERTYPE =
          By.xpath("//*[@class='selectbox']");

  public static final By ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON =
          By.xpath("//*[@class='btn addFolderButton']");

  public static final By ELEMENT_SITEEXPLORER_ACTION_COPY =
          By.xpath("//*[@class='uiIconEcmsCopy']");

  public static final By ELEMENT_SITEEXPLORER_ACTION_CUT =
          By.xpath("//*[@class='uiIconEcmsCut']");

  public static final By ELEMENT_SITEEXPLORER_ACTION_PASTE =
          By.xpath("//*[@class='uiIconEcmsPaste']");

  public static final By ELEMENT_SITEEXPLORER_ACTION_ADDSYMLINK =
          By.xpath("//*[@class='uiIconEcmsAddSymLink']");

  // Left explorer box
  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE =
          By.xpath("//*[@class='uiIconEcmsHome uiIconEcmsLightGray']");

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_RELATION =
          By.xpath("//*[@class='uiIconEcmsRelationMini uiIconEcmsLightGray']");

  public static final String ELEMENT_SITEEXPLORER_LEFTBOX_TITLE_TRANSLATION =
          "//*[text()='fr (${title})']";

  public static final By ELEMENT_SITEEXPLORER_LIST_LOCK_NODE =
          By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsLock']");

  public static final By ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE =
          By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsUnlock']");

  public static final String ELEMENT_SITEEXPLORER_LOCK_ICON =
          "//*[contains(@class,'iconLockedsmall')]/../../*[contains(.,'$node')]";

  // advanced search
  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAME =
          By.xpath("//*[@id='keyword']");

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SEARCHBTN =
          By.xpath("//*[@id='tab-UIContentNameSearch']//*[@class='btn' and text()='Search']");

  public static final By ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB =
          By.xpath("//*[text()='New Query']");

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY =
          By.xpath("//*[@id='name']");

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN =
          By.xpath("//*[@id='UIJCRAdvancedSearch']//*[@class='btn' and text()='Save']");

  public static final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN =
          "//*[text()='${name}']/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERYBTN =
          By.xpath("//*[@class='uiIconEcmsExecute']");

  public static final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EDITQUERYBTN =
          "//*[text()='${name}']/../..//*[@class='uiIconEdit uiIconLightGray']";

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_QUERYTYPE =
          By.xpath("//*[@class='uiForm EditQueryForm']/..//*[@class='selectbox']");

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEEDITQUERYBTN =
          By.xpath("//*[@id='EditQueryForm']//*[@class='btn' and text()='Save']");

  public static final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT =
          "//*[text()='${name}']";

  public static final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT1 =
          "//*[@id='UISavedQuery']//*[text()='${name}']/../..//*[text()='xpath']";

  // Confirm delete box
  public static final By ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE =
          By.xpath("//*[@class='uiAction uiActionBorder']//*[text()='Delete']");

  // Permission
  public static final By ELEMENT_SEARCH_USER_INPUT =
          By.id("Quick Search");

  public static final By ELEMENT_QUICK_SEARCH_BUTTON =
          By.xpath("//div[@id='SimpleSearchControl']/a[@data-original-title='Quick Search']");

  public static final By ELEMENT_SITEEXPLORER_COMMENT_SHOW =
          By.xpath("//*[text()='Show comments']");

  public static final By ELEMENT_SITEEXPLORER_COMMENT_EDIT =
          By.xpath("//*[@class='uiIconEdit uiIconLightGray']");

  public static final By ELEMENT_SITEEXPLORER_COMMENT_DELETE =
          By.xpath("//*[@class='uiIconTrash uiIconLightGray']");

  public static final By ELEMENT_SITEEXPLORER_COMMENT_SAVE =
          By.xpath(".//*[@id='UICommentForm']//button[text()='Save']");

  // clipboard
  public static final By ELEMENT_SITEEXPLORER_CLIPBOARD =
          By.xpath("//*[@id='UISideBar']//*[@class='uiIconEcmsClipboardMini uiIconEcmsLightGray']");

  public static final String ELEMENT_CLIPBOARD_DELETE_NODE =
          "//*[@id='UISideBar']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconEcmsDelete uiIconEcmsLightGray']";

  public static final By ELEMENT_CLIPBOARD_CLEAR_ALL =
          By.xpath("//*[@id='UIClipboard']//*[contains(text(),'Clear All')]");

  // vote
  public static final By ELEMENT_SITEEXPLORER_VOTE_AVERAGE =
          By.xpath("//*[@data-original-title='Average']");

  public static final By ELEMENT_SITEEXPLORER_VOTEONDOCUMENT =
          By.xpath("//*[@class='uiVote clearfix']");

  // Tag
  public static final By ELEMENT_TAG_FORM =
          By.xpath("//*[@id='names']");

  public static final By ELEMENT_ADD_TAG_FORM =
          By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Add')]");

  public static final By ELEMENT_TAG_POPUP_CLOSE =
          By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Close')]");

  // Add category
  public static final By ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY =
          By.xpath("//*[@id='UICategoryManager']//*[contains(text(),'Select Category')]");

  public static final By ELEMENT_CATEGORY_SELECT_CATEGORY_TREE =
          By.xpath("//*[@name='taxonomyTree']");

  public static final By ELEMENT_CATEGORY_ADD_ROOT_NODE =
          By.xpath("//*[@class='uiIconAddRootNode uiIconLightGray']");

  // SideBar
  public static final String ELEMENT_SE_NODE =
          "//*[@title='${node}']";

  public static final By ELEMENT_SIDEBAR_SITES_MANAGEMENT =
          By.xpath("//*[@data-original-title='Site Management']");

  public static final By ELEMENT_SIDE_BAR_RELATION_ICON =
          By.xpath(".//*[@id='UISideBar']//i[@class='uiIconEcmsRelationMini uiIconEcmsLightGray']");

  public static final String ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE =
          ".//*[@id='UISideBar']//a[text()='${nameContent}']";

  public static final By ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON =
          By.xpath(".//*[@id='UISideBar']//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET =
          By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_portalFolder' and @title='intranet']");

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_DOCUMENT =
          By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_documentFolder' and @title='documents']");

  public static final String ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME =
          ".//*[@class='nodeGroup']//*[@class='nodeName' and text()='${title}']";

  public static final By ELEMENT_SITEEXPLORER_ACTION_DELETE =
          By.xpath("//*[@class='uiIconEcmsDelete']");

  public static final By ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB =
          By.xpath(".//*[@class='uiIconEcmsTagExplorerMini uiIconEcmsLightGray']");

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_SAVEDSEARCH =
          By.xpath("//*[@class='uiIconEcmsSavedSearchesMini uiIconEcmsLightGray']");

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_ADVANCEDSEARCH =
          By.xpath("//*[@class='actionIcon advancedSearchIcon pull-right']//*[@class='uiIconSearch uiIconLightGray']");

  // Side bar-->Tag cloud

  public static final By ELEMENT_SIDEBAR_TAGCLOUD_EDIT =
          By.xpath(".//*[@class='uiIconEdit uiIconLightGray']");

  public static final By ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE =
          By.xpath(".//*[@id='UIPopupWindow']//span[text()='Edit Tag']");

  public static final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT =
          ".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconEditTag uiIconLightGray']";

  public static final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE =
          ".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconRemoveTag uiIconLightGray']";

  // Tag Cloud-->Edit-->Tag popup

  public static final By ELEMENT_TAG_POPUP_NAME_FIELD = By.id("tagName");

  public static final By ELEMENT_TAG_POPUP_SAVE =
          By.xpath(".//*[@id='UITagForm']//*[text()='Save']");

  public static final By ELEMENT_TAGE_POPUP_CLOSE =
          By.xpath(".//span[text()='Edit Tag']/..//*[@title='Close Window']");

  // Grid list
  public static final String ELEMENT_GRID_LIST_CONTENT =
          ".//*[@class='uiListGrid']//*[text()='${file}']";

  // Space drive
  public static final String ELEMENT_SPACE_DRIVE_NODE_TREE_FILE =
          ".//*[@class='nodeGroup']//*[contains(text(),'${file}')]";

  // Publication box
  public static final String ELEMENT_PUBLICATION_STATUS =
          "//*[text()='${status}']/..//*[@class='node']";

  // Notifications
  public static final By ELEMENT_DOCUMENT_SHARE_CLOSE_BUTTON =
          By.xpath("(//*[@class='uiIconClose pull-right'])[1]");

  public static final SelenideElement ELEMENT_CONTENT_LIST =
          $(byId("UITreeExplorer"));

  public static final SelenideElement ELEMENT_INPUT_PATH = $(byId("address"));

  public static final SelenideElement ELEMENT_ACTION_BAR_MENU =
          $(byId("UIActionBar"));

  public static final By ELEMENT_BUTTON_ADD_FOLDAR =
          byClassName("uiIconEcmsAddFolder");

  public static final SelenideElement ELEMENT_INPUT_NAME_FOLDER =
          $(byId("titleTextBox"));

  public static final SelenideElement ELEMENT_BUTTON_CONFIRM_ADD_FOLDER =
          $(byClassName("addFolderButton"));

  public static final SelenideElement ELEMENT_LIST_DOCUMENT_FOLDER =
          $(byId("UIDocumentNodeList"));

  public static final By ELEMENT_ICON_CHECKBOX =
          byClassName("checkbox");

  public static final SelenideElement ELEMENT_BUTTON_DELETE_FOLDER_DOCUMENT =
          $(byId("ECMContextMenu")).find(byClassName("uiIconEcmsDelete"));

  public static final SelenideElement ELEMENT_BUTTON_CONFIRM_DELETE =
          $(byClassName("UIDeleteFolderConfirmMessage")).find(byClassName("OK"));

  public static final SelenideElement ELEMENT_BUTTON_RENAME_FOLDER =
          $(byId("ECMContextMenu")).find(byClassName("uiIconEcmsRename"));

  public static final SelenideElement ELEMENT_INPUT_REMANE_FOLDER =
          $(byId("renameField"));

  public static final SelenideElement ELEMENT_BUTTON_CONFIRM_RENAME =
          $(byId("renameLink"));

  public static final SelenideElement ELEMENT_ICON_LIST_VIEW =
          $(byClassName("uiIconEcmsViewList"));

  public static final SelenideElement ELEMENT_ICON_DEFAULT_VIEW =
          $(byClassName("uiIconEcmsViewIcons"));

  public static final SelenideElement ELEMENT_POPUP_RIGHT_MENU_CONTAINER =
          $(byClassName("uiRightPopupMenuContainer"));

  public static final By ELEMENT_DELETE_BUTTON_IN_RIGHT_CLICK_CONTAINER =
          byClassName("uiIconEcmsDelete");

  public static final By ELEMENT_RENAME_BUTTON_IN_RIGHT_CLICK =
          byClassName("uiIconEcmsRename");

  public static final SelenideElement ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW =
          $(byId("UIDocumentWorkspace"));

  public static final SelenideElement ELEMENT_BUTTON_CONFIRM_DELETE_FILE =
          $(byClassName("UIDeleteFileConfirmMessage")).find(byClassName("OK"));

  public static final SelenideElement ELEMENT_FOLDER_DOCUMENT =
          $(byXpath("//*[@id=\"UIDocumentNodeList\"]/div/div[1]/div[4]/a"));

  public static final SelenideElement ELEMENT_LIST_DOCUMENTS =
          $(byClassName("uiListGrid"));

  public static final SelenideElement ELEMENT_LIST_DOCUMENTS_IN_SPACE =
          $(byId("UIDocumentContainer"));

  public static final SelenideElement ELEMENT_SEARCH_BTN = $(byId("simpleSearch"));

  public static final String ELEMENT_GO_BACK_PREVIOUS_PATH = "//td/a[@class='backIcon actionIcon pull-left']";

  public static final By ELEMENT_EXPAND_ICON =
          byClassName("expandIcon");

  public static final String ELEMENT_CHECKBOX_MODIFY_PERMISSION =
          "*:/spaces/{user}addNode";

  public static final SelenideElement ELEMENT_BUTTON_UPLOAD_NEW_VERSION =
          $(byClassName("uiIconEcmsUploadNewVersion"));

  public static final SelenideElement ELEMENT_BUTTON_CREATE_NEW_VERSION =
          $(byClassName("uiIconEcmsCreateNewVersion"));

  public static final SelenideElement ELEMENT_BUTTON_PERMISSION =
          $(byId("uiActionsBarContainer")).find(byClassName("uiIconEcmsViewPermissions"));

  public static final SelenideElement ELEMENT_BUTTON_PERMISSION_IN_MORE_DROP_DOWN_MENU =
          $(byId("ListHideContainer")).find(byClassName("uiIconEcmsViewPermissions"));
  public static final By ELEMENT_PUBLIC_FOLDER =
          By.xpath("//*[@id=\"UIDocumentNodeList\"]/div/div[9]/div[4]/a/span");
  public static final SelenideElement ELEMENT_PUBLIC_LIST_VIEW = $(byClassName("uiListGrid"));
  public static final SelenideElement ELEMENT_BUTTON_DELETE_FIRST = $(byXpath("//*[@id=\"ECMContextMenu\"]/div/ul/li[7]/a"));
  public static final SelenideElement ELEMENT_NOTIFICATION = $(byClassName("UIControl"));
  public static final SelenideElement ELEMENT_BUTTON_CLOSE_SEARCH = $(byXpath("//*[@id=\"UIPopupWindow\"]/div[1]/a"));
}
