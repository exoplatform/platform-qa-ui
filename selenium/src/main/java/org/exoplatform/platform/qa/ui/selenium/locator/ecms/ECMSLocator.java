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

  public static final By ELEMENT_ADDDOCUMENT_PRODUCT_FILE =
          By.xpath(".//*[@class='uiIcon64x64Templateacme_product']");

  public static final By ELEMENT_ADDDOCUMENT_WEBLINK =
          By.xpath(".//*[@class='uiIcon64x64Templateexo_link']");

  public static final By ELEMENT_ADDDOCUMENT_PRODUCT =
          By.xpath("//*[@class='uiIcon64x64Templateacme_product']");

  public static final By ELEMENT_ADDDOCUMENT_NEXT_PAGE =
          By.xpath(".//*[@id='UISelectDocumentForm']//*[@data-original-title='Next Page']");

  public static final By ELEMENT_DOCFORM_BLANK_TITLE =
          By.xpath("//*[@id='title0']");

  public static final By ELEMENT_DOCFORM_BLANK_DESC =
          By.xpath("//*[@id='description0']");

  public static final By ELEMENT_DOCFORM_BLANK_CREATOR =
          By.xpath("//*[@id='creator0']");

  public static final By ELEMENT_DOCFORM_BLANK_SOURCE =
          By.xpath("//*[@id='source0']");

  // New file form
  public static final By ELEMENT_FILEFORM_BLANK_CONTENT2 =
          By.xpath("//*[@id='cke_1_contents']/iframe");

  // public static final By ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE =
  // By.xpath("//*[@class='btn' and text()='Save & Close']");
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

  public static final By ELEMENT_DOCUMENT_VIEW_TAB =
          By.xpath(".//*[@id='UIDocumentContainer']//*[contains(@data-original-title,'Document View')]");

  // New folder popup
  public static final By ELEMENT_ADD_NEW_FOLDER_POPUP_TITLE =
          By.xpath(".//*[@id='UIPopupWindow']//span[text()='New Folder']");

  public static final By ELEMENT_USE_CUSTOM_TYPE_FOLDER =
          By.id("customTypeCheckBox");

  public static final By ELEMENT_FOLDER_TITLE_TEXTBOX =
          By.id("titleTextBox");

  public static final By ELEMENT_FOLDER_TYPE_OPTION =
          By.name("customTypeSelectBox");

  public static final String ELEMENT_CONTENT_FOLDER_TYPE = "nt:unstructured";

  public static final String ELEMENT_DOCUMENT_FOLDER_TYPE = "nt:folder";

  public static final By ELEMENT_DOCUMENT_FOLDER_TYPE_XPATH =
          By.xpath("//option[text()='Document Folder']");

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

  public static final By ELEMENT_DRIVE_SELECT_MEMBERSHIP_POPUP =
          By.xpath(".//*[contains(@id,'UIDrivePermissionSelector')]");

  public static final String ELEMENT_DRIVE_SELECT_RIGHT_PARENT_GROUP =
          "//*[contains(@id,'UIDrivePermissionSelector')]//a[contains(.,'$group')]";

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

  public static final By ELEMENT_TAG_SELECT_MEMBERSHIP_ADD_BTN =
          By.xpath("//*[@id='UITagPermissionForm']//*[contains(.,'Add')][contains(@class,'btn-primary')]");

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
          By.xpath("//*[@name='language']");

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
  public static final By ELEMENT_SITEEXPLORER_WORKING_PANEL =
          By.xpath("//*[@class='navItemSelected' and text()='Content Explorer']");

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

  public static final By ELEMENT_ADDRESS_BAR_LIST_VIEW =
          By.xpath(".//*[@id='UIAddressBar']//*[@class='uiIconEcmsViewDefault uiIconEcmsViewList uiIconEcmsLightGray']");

  public static final By ELEMENT_SITE_PATH =
          By.cssSelector("#address");

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

  public static final String ELEMENT_ACTIONBAR_ACTION =
          "//*[@class='actionIcon'][contains(.,'$action')]";

  public static final By ELEMENT_ACTIONBAR_SHOWDRIVES = By.id("driveAction");

  public static final By ELEMENT_ACTIONBAR_DELETE =
          By.xpath(".//*[@id='ECMContextMenu']//*[@class='uiIconEcmsDelete']");

  public static final By ELEMENT_SITE_EXPLORER_ALL_CHECKBOX =
          By.xpath("//input[@type='checkbox' and @name= 'UIFileViewCheckBox']");

  public static final By ELEMENT_DELETE_ALL_BUTTON =
          By.xpath(".//*[@id='JCRContextMenu']//i[@class='uiIconEcmsDelete']");

  public static final By ELEMENT_ACTIONBAR_WATCH =
          By.xpath(".//*[contains(@class,'uiIconEcmsWatchDocument')]");

  public static final By ELEMENT_ACTIONBAR_WATCH_RADIO =
          By.xpath("//*[@class='uiRadio']/*[@id='notificationType']/../*[contains(.,'Email')]");

  public static final By ELEMENT_ACTIONBAR_WATCH_BUTTON =
          By.xpath("//*[@class='btn'][contains(.,'Watch')]");

  public static final By ELEMENT_ACTIONBAR_WATCH_NOTICE =
          By.xpath("//*[@id='wcm-notice'][contains(.,'You are watching this document.')]");

  public static final By ELEMENT_ACTIONBAR_SHARE =
          By.xpath("//*[@class='uiIconEcmsShareDocuments uiIconEcmsLightGray']");

  // Add Category popup
  public static final By ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB =
          By.xpath(".//*[@id='UICategoryManager']//a[text()='Select Category']");

  public static final By ELEMENT_ADD_CATEGORY_POPUP_MENU =
          By.name("taxonomyTree");

  public static final String ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE =
          ".//*[@id='UIOneTaxonomySelector']//i[@title='${nameTitle}']";

  public static final String ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_RIGHT_SIDE =
          ".//*[@id='UISelectTaxonomyPanel']//div[contains(.,'${nameCategory}')]//../..//i[@class='uiIconValidate uiIconLightGray']";

  public static final By ELEMENT_ADD_CATEGORY_POPUP_CLOSED_BUTTON =
          By.xpath(".//*[@id='UICategoryManager']//button[text()='Close']");

  public static final String ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY =
          ".//*[@id='UICategoriesAddedList']//td[contains(.,'${nameCategory}')]/../..//i[@class='uiIconDelete uiIconLightGray']";

  // Import Node popup
  public static final By ELEMENT_IMPORT_NODE_POPUP_TITLE =
          By.xpath(".//*[@id='UIPopupWindow']//span[text()='Import']");

  public static final By ELEMENT_IMPORT_NODE_POPUP_UPLOAD_BUTTON = By.name("file");

  public static final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR = By.name("behavior");

  public static final By ELEMENT_IMPORT_NODE_POPUP_VERSION_HISTORY_BUTTON =
          By.xpath("//div[@id='versionHistory']//input[@name='file']");

  public static final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_CREATE_NEW =
          By.xpath(".//*[@id='UIImportNode']//option[text()='Create New']");

  public static final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REMOVE_EXISTING =
          By.xpath(".//*[@id='UIImportNode']//option[text()='Remove Existing']");

  public static final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REPLACE_EXISTING =
          By.xpath(".//*[@id='UIImportNode']//option[text()='Replace Existing']");

  public static final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_THROW_EXCEPTION =
          By.xpath(".//*[@id='UIImportNode']//option[text()='Throw Exception']");

  public static final String ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL =
          "//div[@class='fileNameLabel' and contains(text(),'${fileName}')]";

  public static final By ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON =
          By.xpath(".//*[@id='UIImportNode']//button[text()='Import']");

  // Export Node popup
  public static final By ELEMENT_EXPORT_NODE_POPUP_TITLE =
          By.xpath(".//*[@id='UIPopupWindow']//span[text()='Export']");

  public static final By ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW =
          By.xpath(".//*[@id='UIExportNode']//input[@value='docview']");

  public static final By ELEMENT_EXPORT_NODE_POPUP_SYS_VIEW =
          By.xpath(".//*[@id='UIExportNode']//input[@value='sysview']");

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

  // go to Show drives
  public static final By ELEMENT_SHOW_DRIVES =
          By.cssSelector("#driveAction");

  public static final String ELEMENT_SELECTED_DRIVE =
          ".//*[@data-original-title='${nameDrive}']";

  // Drive area
  public static final String ELEMENT_ACTIONBAR_SELECTED_DRIVE =
          ".//*[@id='UIDrivesArea']//*[contains(@data-original-title,'${driver}')]";

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
  public static final By ELEMENT_ACTIONBAR_ADDTAG =
          By.xpath("//*[@class='uiIconEcmsTaggingDocument uiIconEcmsLightGray']");

  public static final By ELEMENT_ADDTRANSLATION_SELECTDOC =
          By.xpath("//*[@title='Select Document']");

  public static final String ELEMENT_FOLDERSELECTOR_PATH =
          "//*[@class='nodeName'][contains(.,'${path}')]";

  public static final String ELEMENT_FOLDERSELECTOR_CONTENTDETAIL_FINALPATH =
          "//*[@class='OddItem']//*[text()='${name}']";

  public static final By ELEMENT_SAVE_BTN =
          By.xpath("//*[text()='Save']");

  public static final By ELEMENT_ADD_BTN =
          By.xpath("//*[text()='Add']");

  public static final By ELEMENT_CLOSE_BTN =
          By.xpath("//*[text()='Close']");

  public static final By ELEMENT_OK_BTN =
          By.xpath("//*[text()='OK']");

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
  public static final By ELEMENT_ACTIONBAR_UPLOAD =
          By.xpath("//*[@class='uiIconEcmsUpload uiIconEcmsLightGray']");

  public static final By ELEMENT_MORE_LINK_WITHOUT_BLOCK =
          By.xpath("//*[@id='uiActionsBarContainer']//*[contains(text(), 'More')]");

  // public static final By ELEMENT_UPLOAD_LINK =
  // By.id("MultiUploadInputFiles");
  public static final By ELEMENT_UPLOAD_LINK =
          By.xpath(".//*[@id='UploadButtonDiv']//*[contains(@class,'uiIconEcmsUpload')]");

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

  public static final By ELEMENT_THUMBNAIL_VIEW_ADMIN_VIEW =
          By.xpath(".//*[contains(@class,'uiThumbnailsView')]");

  public static final By ELEMENT_CONTEXT_MENU_ADD_DOCUMENT =
          By.xpath("(.//*[@id='JCRContextMenu']//*[contains(@href,'javascript:void(0)')])[3]");                                                                                                      // By.xpath(".//*[@id='JCRContextMenu']//*[contains(@class,'uiIconEcmsAddDocument')]");

  public static final By ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS =
          By.xpath(".//*[@id='UIDocumentFormController']");

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

  public static final By ELEMENT_SITEEXPLORER_ACTION_RENAME =
          By.xpath("//*[@class='uiIconEcmsRename']");

  public static final By ELEMENT_SITEEXPLORER_ACTION_OPEN_IN_MS_OFFICE =
          By.xpath("//*[@class='uiIconDownload uiIconLightGray']");

  public static final By ELEMENT_SITEEXPLORER_ACTION_ADDSYMLINK =
          By.xpath("//*[@class='uiIconEcmsAddSymLink']");

  // Left explorer box
  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE =
          By.xpath("//*[@class='uiIconEcmsHome uiIconEcmsLightGray']");

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_RELATION =
          By.xpath("//*[@class='uiIconEcmsRelationMini uiIconEcmsLightGray']");

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_EXPLORER =
          By.xpath("//*[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");

  public static final String ELEMENT_SITEEXPLORER_LEFTBOX_TITLE_TRANSLATION =
          "//*[text()='fr (${title})']";

  public static final By ELEMENT_SITEEXPLORER_LIST_LOCK_NODE =
          By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsLock']");

  public static final By ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE =
          By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsUnlock']");

  public static final String ELEMENT_SITEEXPLORER_LOCK_ICON =
          "//*[contains(@class,'iconLockedsmall')]/../../*[contains(.,'$node')]";

  // Left panel of SE
  public static final By ELEMENT_FILE_EXPLORER =
          By.xpath("//*[@data-original-title = 'File Explorer']");

  public static final By ELEMENT_FILE_EXPLORER_ICON =
          By.xpath(".//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");

  public static final String ELEMENT_FILE_TITLE_RIGHT_PANEL =
          ".//*[@class='nodeGroup']//span[text()='${fileName}']";

  // View detail a content in SE
  public static final By ELEMENT_CONTENT_THUMBNAIL =
          By.xpath(".//*[@class='iconContainer']/i");

  public static final String ELEMENT_WEBCONTENT_NAME =
          ".//*[@id='UIDocumentContainer']//h6[text()='${nameFile}']";

  public static final By ELEMENT_CONTENT_MESSAGE_NOT_AVAILABLE =
          By.xpath(".//h4[text()='The preview of this document is not available.']");

  public static final By ELEMENT_CONTENT_MESSAGE_TOO_MANY_PAGES =
          By.xpath(".//h4[text()='The preview is not available for content with over 99 pages.']");

  public static final By ELEMENT_CONTENT_MESSAGE_OVER_SIZE =
          By.xpath(".//h4[text()='The preview is not available for content larger than 5 MB.']");

  public static final By ELEMENT_CONTENT_DOWNLOAD_BUTTON =
          By.xpath(".//*[@class='btn btn-primary']");

  public static final By ELEMENT_CONTENT_OPEN_DESKTOP =
          By.xpath(".//*[@class='btn'][text()='Open on Desktop']");

  // advanced search
  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAME =
          By.xpath("//*[@id='keyword']");

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SEARCHBTN =
          By.xpath("//*[@id='tab-UIContentNameSearch']//*[@class='btn' and text()='Search']");

  public static final By ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB =
          By.xpath("//*[text()='New Query']");

  public static final By ELEMENT_SITEXPLORER_ADVANCEDSEARCH_SAVEDQUERYTAB =
          By.xpath("//*[text()='Saved Query']");

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY =
          By.xpath("//*[@id='name']");

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN =
          By.xpath("//*[@id='UIJCRAdvancedSearch']//*[@class='btn' and text()='Save']");

  public static final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN =
          "//*[text()='${name}']/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERYBTN =
          By.xpath("//*[@class='uiIconEcmsExecute']");

  public static final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERY =
          "//*[text()='${name}']/../..//*[@class='uiIconEcmsExecute']";

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

  // upload file form
  public static final String ELEMENT_ATTACHMENT_FORM_FILE_NAME =
          "//*[text()='$fileName']";

  public static final By ELEMENT_EVENT_FILE_INPUT =
          By.xpath("//*[@id='upload']//*[@name='file']");

  public static final By ELEMENT_ATTACHMENT_SAVE_BUTTON =
          By.xpath("//*[@id='UIAttachFileForm']//*[text()='Save']");

  public static final String ELEMENT_ATTACH_FILE_NAME =
          "//*[@data-original-title='$fileName']";

  public static final By ELEMENT_UPLOAD_PROGRESS_BAR =
          By.xpath(".//*[contains(@class,'progress progress-striped pull-right')]");

  public static final By ELEMENT_UPLOAD_BUTTON =
          By.xpath("//a[@class='actionIcon' and contains(text(),'Upload')]");

  // Permission
  public static final By ELEMENT_PERMISSION_USER =
          By.xpath("//*[@class='uiIconSelectUser uiIconLightGray']");

  public static final By ELEMENT_PERMISSION_GROUP =
          By.xpath("//*[@class='uiIconSelectMember uiIconLightGray']");

  public static final By ELEMENT_PERMISSION_ANY =
          By.xpath("//*[@class='uiIconAddAny uiIconLightGray']");

  public static final By ELEMENT_SEARCH_USER_INPUT =
          By.id("Quick Search");

  public static final By ELEMENT_SELECT_SEARCH = By.name("filter");

  public static final String ELEMENT_USER_CHECKBOX =
          "//*[text()='${user}']/../..//*[@type='checkbox']";

  public static final By ELEMENT_QUICK_SEARCH_BUTTON =
          By.xpath("//div[@id='SimpleSearchControl']/a[@data-original-title='Quick Search']");

  public static final By ELEMENT_ADD_USERS_BUTTON =
          By.xpath("//*[@id='UIUserSelector']//*[text()='Add']");

  // comment
  public static final String ELEMENT_SITEEXPLORER_COMMENT =
          "//*[text()=' ${number} Comment(s)']";

  public static final By ELEMENT_SITEEXPLORER_COMMENT_SHOW =
          By.xpath("//*[text()='Show comments']");

  public static final By ELEMENT_SITEEXPLORER_COMMENT_EDIT =
          By.xpath("//*[@class='uiIconEdit uiIconLightGray']");

  public static final By ELEMENT_SITEEXPLORER_COMMENT_DELETE =
          By.xpath("//*[@class='uiIconTrash uiIconLightGray']");

  public static final String ELEMENT_SITEEXPLORER_COMMENT_CONTENT =
          "//*[text()='${content}']";

  public static final By ELEMENT_SITEEXPLORER_COMMENT_SAVE =
          By.xpath(".//*[@id='UICommentForm']//button[text()='Save']");

  // clipboard
  public static final By ELEMENT_SITEEXPLORER_CLIPBOARD =
          By.xpath("//*[@id='UISideBar']//*[@class='uiIconEcmsClipboardMini uiIconEcmsLightGray']");

  public static final String ELEMENT_CLIPBOARD_PASTE_NODE =
          "//*[@id='UISideBar']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconEcmsPaste uiIconEcmsLightGray']";

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
  public static final By ELEMENT_SITEEXPLORER_TAG_DELETE =
          By.xpath("//*[@class='uiIconClose']");

  public static final By ELEMENT_SITEEXPLORER_TAG_NAME =
          By.xpath("//*[@id='names']");

  public static final String ELEMENT_SITEEXPLORER_TAG_EXISTING =
          "//*[@class='actionField']//*[contains(text(),'${name}')]";

  // public static final By ELEMENT_SITEEXPLORER_TAG_INPUT=
  // By.xpath("//*[@id='tagName']");
  public static final By ELEMENT_TAG_FORM =
          By.xpath("//*[@id='names']");

  public static final By ELEMENT_ADD_TAG_FORM =
          By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Add')]");

  public static final By ELEMENT_TAG_POPUP_CLOSE =
          By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Close')]");

  public static final String ELEMENT_TAG_POPUP_LINK_TAGS =
          ".//*[@id='UITaggingForm']//*[contains(text(),'${name}')]";

  // Add category
  public static final By ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY =
          By.xpath("//*[@id='UICategoryManager']//*[contains(text(),'Select Category')]");

  public static final By ELEMENT_CATEGORY_SELECT_CATEGORY_TREE =
          By.xpath("//*[@name='taxonomyTree']");

  public static final By ELEMENT_CATEGORY_ADD_ROOT_NODE =
          By.xpath("//*[@class='uiIconAddRootNode uiIconLightGray']");

  public static final String ELEMENT_DOCUMENT_VIEW =
          "//*[@id='UITabContent']//*[contains(text(),'{$content}')]";

  public static final By ELEMENT_SITEEXPLORER_RENAME_FIELD =
          By.xpath("//*[@id='renameField']");

  public static final By ELEMENT_SITEEXPLORER_RENAME_SAVE =
          By.xpath("//*[@id='renameLink']");

  public static final By ELEMENT_CHECK_OPEN_WEBCONTENT_IN_MSOFFICE =
          By.xpath("//*[@id='main']//*[contains(text(),'css')]");

  // SideBar
  public static final String ELEMENT_SE_NODE =
          "//*[@title='${node}']";

  public static final By ELEMENT_SIDE_BAR_MAINTAB =
          By.xpath(".//*[@id='UISideBar']//h6[@class='title']");

  // public static final By ELEMENT_SIDEBAR_SITES_MANAGEMENT =
  // By.xpath("//*[@data-original-title = 'Sites Management' or @title = 'Sites
  // Management']");
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

  public static final By ELEMENT_SITEEXPLORER_LEFTBOX_SPACE =
          By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_portalFolder' and @title='intranet']");

  // public static final String ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME =
  // "//*[@class='nodeName' and text()='${title}']";
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
  public static final String ELEMENT_SIDEBAR_TAGCLOUD_NAME =
          ".//*[@id='UITagExplorer']//a[text()='${name}']";

  public static final By ELEMENT_SIDEBAR_TAGCLOUD_EDIT =
          By.xpath(".//*[@class='uiIconEdit uiIconLightGray']");

  public static final By ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE =
          By.xpath(".//*[@id='UIPopupWindow']//span[text()='Edit Tag']");

  public static final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT =
          ".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconEditTag uiIconLightGray']";

  public static final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE =
          ".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconRemoveTag uiIconLightGray']";

  // Tag Cloud-->Edit-->Tag popup
  public static final By ELEMENT_TAG_POPUP_TITLE =
          By.xpath(".//*[@id='TagPopup']//span[text()='Tag']");

  public static final By ELEMENT_TAG_POPUP_NAME_FIELD = By.id("tagName");

  public static final By ELEMENT_TAG_POPUP_SAVE =
          By.xpath(".//*[@id='UITagForm']//*[text()='Save']");

  public static final By ELEMENT_TAGE_POPUP_CLOSE =
          By.xpath(".//span[text()='Edit Tag']/..//*[@title='Close Window']");

  // SEO folder
  public static final By ELEMENT_SEO_FOLDER_FILE =
          By.xpath("//*[@class='text']//*[@data-original-title='sitemaps']");

  // Personal document
  public static final String ELEMENT_PERSONAL_DOCUMENT_FILE =
          ".//*[@id='UIDocumentNodeList']//*[contains(.,'${file}')]";

  // Grid list
  public static final String ELEMENT_GRID_LIST_CONTENT =
          ".//*[@class='uiListGrid']//*[text()='${file}']";

  public static final String ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX =
          ".//*[@id='UIDocumentNodeList']//span[text()='${file}']/../../..//span/input";

  // Space drive
  public static final String ELEMENT_SPACE_DRIVE_FILE =
          ".//*[@id='UIDocumentNodeList']//span[text()='${file}']";

  public static final String ELEMENT_SPACE_DRIVE_CHECKBOX =
          ".//*[@id='UIDocumentNodeList']//span[text()='${file}']/../../..//*[@type='checkbox']";

  public static final String ELEMENT_SPACE_DRIVE_NODE_TREE_FILE =
          ".//*[@class='nodeGroup']//*[contains(text(),'${file}')]";

  // Publication box
  public static final String ELEMENT_PUBLICATION_STATUS =
          "//*[text()='${status}']/..//*[@class='node']";

  // Right column content
  public static final String ELEMENT_SITE_EXPLORER_RIGHT_COLUMN_CONTENT =
          ".//*[@id='UITabContent']//a[contains(text(),'${title}')]";

  // View detail a content
  public static final String ELEMENT_CONTENT_NAME =
          ".//*[@id='UIDocumentContainer']//span[text()='${nameFile}']";

  // View icons
  public static final By ELEMENT_LIST_VIEW_ICON =
          By.xpath("//*[@data-original-title = 'List']");

  public static final By ELEMENT_ADMIN_VIEW_ICON =
          By.xpath("//*[@data-original-title = 'Admin']");

  public static final By ELEMENT_ICONS_VIEW =
          By.xpath("//*[@data-original-title = 'Icons']");

  public static final By ELEMENT_WEB_VIEW =
          By.xpath("//*[@data-original-title = 'Web']");

  public static final By ELEMENT_CATEGORIES_VIEW =
          By.xpath("//*[@data-original-title = 'Categories']");

  public static final String ELEMENT_ITEM_VIEW =
          "//*[@data-original-title = '$view']";

  // Add new content
  public static final String ELEMENT_SITE_EXPLORER_CONTENT_NAME =
          ".//*[@id='UISelectDocumentForm']//i[@data-original-title='${nameContent}']";

  // Share document to space
  public static final By ELEMENT_SPACE_LIST =
          By.xpath("//*[@id='DisplayModesDropDown']");

  public static final String ELEMENT_SHARE_DOCUMENT_POPUP =
          "//*[@id='tipName']//a[contains(text(),'${author}')]";

  public static final String ELEMENT_PROFILE_NAME =
          "//*[@id='UIStatusProfilePortlet']//span[contains(text(),'${author}')]";

  public static final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_COMMENT =
          "//*[@class='author']//a[contains(text(),'${author}')]"
                  + "/..//*[contains(text(),'shared a document')]"
                  + "/../..//*[@class='dataInfor']//a[contains(text(),'${spaceName}')]"
                  + "/../../..//*[@class='description'][contains(text(),'${comment}')]";

  public static final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_COMMENT_IN_SPACE =
          "//*[@class='author']//a[contains(text(),'${author}')]"
                  + "/..//*[contains(text(),'shared a document')]"
                  + "/../../..//*[@class='description'][contains(text(),'${comment}')]";

  public static final By ELEMENT_SHARE_DOCUMENT_FILE_PREVIEW =
          By.xpath(".//*[@id='UIDocumentPreview']//*[@class='title']");

  public static final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_ICON =
          "//*[@class='author']//a[contains(text(),'${author}')]"
                  + "/..//*[contains(text(),'shared a document')]/../../..//"
                  + "img[contains(@src,'thumbnailImage') and contains(@src,'${fileName}')]";

  public static final String ELEMENT_SELECTED_SPACE =
          "//*[@class='spaceList']//*[text()='${spaceName}']";

  public static final String ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON =
          "//*[@class='uiActionBorder']//*[text()='${name}']";

  public static final String ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE =
          "//*[@class='author']//a[contains(text(),'${author}')]/..//*[contains(text(),'shared a document')]";

  public static final String ELEMENT_SHARE_DOCUMENT_AUTHOR =
          "//*[@class='author']//a[contains(text(),'${author}')]";

  public static final By ELEMENT_SHARE_DOCUMENT_COMMENT =
          By.xpath("//*[@id='DisplaytextAreaInput']");

  public static final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_FILE_NAME =
          "//*[@class='author']//a[contains(text(),'${author}')]"
                  + "/..//*[contains(text(),'shared a document')]"
                  + "/../../..//a[contains(text(),'${fileName}')]";

  public static final By ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER =
          By.xpath(".//*[@id='UIDocumentInfo']//*[contains(text(),'Shared')]");

  public static final String ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK =
          "//*[@id='UIDocumentInfo']//*[contains(@symlink,'${spaceName}')]"
                  + "/../../..//*[@class='uiThumbnailsView UIDocumentInfo']//*[contains(text(),'${fileName}')]";

  public static final By ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE =
          By.xpath(".//*[@id='ECMContextMenu']/div/ul/li[7]/a");

  public static final String ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION =
          "//*[@class='uiAction uiActionBorder']//*[contains(text(),'${action}')]";

  // Notifications
  public static final String ELEMENT_SHARE_DOCUMENT_CONTENT =
          "//*[@class='author']//a[contains(text(),'${author}')]/..//*[contains(text(),'shared a document')]/../..//*[@class='dataInfor']//a[contains(text(),'${spaceName}')]";

  public static final By ELEMENT_SHARE_DOCUMENT_ACTIVITY_NOT_FOUND =
          By.xpath(".//*[@id='UIUserActivityStreamPortlet']//*[contains(text(),'Activity not found')]");

  public static final By ELEMENT_DOCUMENT_SHARE =
          By.xpath("//*[@class='uiIconEcmsShareDocuments']");

  public static final String ELEMENT_DOCUMENT_SELECTED_FOLDER =
          "//*[@id='UIDocumentNodeList']//*[contains(text(),'${folderName}')]";

  public static final String ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX =
          "//*[@id='UIDocumentNodeList']//*[@data-original-title='${fileName}']/../..//*[@class='uiCheckbox']";

  public static final String ELEMENT_DOCUMENT_FILE_CHECKBOX =
          ".//*[@id='FileViewBreadcrumb']//*[@class='uiCheckbox']";

  public static final String ELEMENT_DOCUMENT_SELECTED_FILE =
          "//*[@id='UIDocumentNodeList']//*[@data-original-title='${fileName}']";

  public static final String ELEMENT_DOCUMENT_PARENT_FOLDER =
          ".//*[@id='UIDocumentNodeList']//*[contains(text(),'${folderName}')]";

  public static final String ELEMENT_DOCUMENT_FILE_NAME =
          ".//*[@class='nodeName' and text()='${fileName}']";

  public static final String ELEMENT_SELECTED_SPACE_TO_SHARE =
          "//*[@class='uiMention' and text()= '${spaceName}']//*[contains(@onclick, 'RemoveSpace')]";

  public static final String ELEMENT_SELECTED_SPACE_TO_REMOVE =
          "//*[@class='uiIconClose uiIconLightGray' and contains(@onclick,'objectId=/spaces/${spaceName}')]";

  public static final By ELEMENT_DOCUMENT_SHARE_DIALOG_TITLE =
          By.xpath("//*[@class='PopupTitle popupTitle' and contains(text(), 'Share')]");

  public static final By ELEMENT_DOCUMENT_SHARE_SPACE_DROPDOWN =
          By.xpath("//*[@id='UIShareDocumentSpaceMention']//*[contains(text(), 'Share with:')]/..//*[@data-toggle='dropdown']//*[contains(text(),'Select a Space')]");

  public static final By ELEMENT_DOCUMENT_SHARE_COMMENT_BOX =
          By.xpath("//*[@id='DisplaytextAreaInput']/..//*[@class='placeholder' and contains(text(), 'Add a comment about to this file...')]");

  public static final String ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION =
          "//*[@class='accessSpaceMember clearfix']//*[contains(text(), 'Access:')]/..[contains(text(), 'Space Members')]/..//*[@id='permissionDropDown']//option[contains(text(), '${option}')]";

  public static final By ELEMENT_DOCUMENT_SHARE_CLOSE_BUTTON =
          By.xpath("(//*[@class='uiIconClose pull-right'])[1]");

  public static final By ELEMENT_DOCUMENT_SHARE_SPACE_FILTER =
          By.xpath("//input[@placeholder='Filter Spaces']");

  public static final By ELEMENT_DOCUMENT_SHARE_EDIT_FORM =
          By.xpath("//*[@id='EditFormController']");

  public static final String ELEMENT_DOCUMENT_SHARE_UPDATED_USER =
          "//*[@class='nodeName' and text() = '${fileName}']/../..//*[@class='fileInfoBottom' and contains(text(), 'Updated') and contains(text(), 'by ${user}')]";

  public static final String ELEMENT_DOCUMENT_SHARE_UPDATED_TITLE =
          "//*[@id='title0'][@value='${title}']";

  public static final String ELEMENT_DOCUMENT_SHARE_UPDATED_CONTENT =
          "//*[@id='content' and contains(text(), '${content}')]";

  public static final By ELEMENT_DOCUMENT_PERMISSION_DIALOG_TITLE =
          By.xpath("//*[@class='PopupTitle popupTitle' and contains(text(), 'Permission Management')]");

  public static final String ELEMENT_DOCUMENT_PERMISSION =
          "//*[@id='*:/spaces/${spaceName}${permission}' and @checked='']";

  public static final By ELEMENT_DOCUMENT_CONTENT =
          By.xpath("//*[@id='content']");

  public static final String SHARE_ACCESS_CAN_VIEW = "Can View";

  public static final String SHARE_ACCESS_CAN_EDIT = "Can Edit";

  public static final SelenideElement ElEMENT_PRESENTATION_CONTAINER =
          $(byClassName("UIPresentationContainer"));

  public static final SelenideElement ELEMENT_INCON_ADD_PATH =
          $(byClassName("uiIconAddPath"));

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

  public static final SelenideElement ELEMENT_BUTTON_COPY =
          $(byId("ECMContextMenu")).find(byClassName("uiIconEcmsCopy"));

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


  public static final SelenideElement ELEMENT_CLOSE_PUBLICATION_POPUP = $(byXpath("//*[@id=\"UIPublicationContainer\"]/div[2]/button"));

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
  public static final SelenideElement ELEMENT_FILE_DESCRIPTION = $(byClassName("description"));
  public static final SelenideElement ELEMENT_BUTTON_DELETE_FIRST = $(byXpath("//*[@id=\"ECMContextMenu\"]/div/ul/li[7]/a"));
  public static final SelenideElement ELEMENT_NOTIFICATION = $(byClassName("UIControl"));
  public static final SelenideElement ELEMENT_CHECK = $(byClassName("uiCheckbox"));

  public static final SelenideElement ELEMENT_BUTTON_CLOSE_SEARCH = $(byXpath("//*[@id=\"UIPopupWindow\"]/div[1]/a"));
}
