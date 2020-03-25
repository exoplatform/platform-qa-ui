package org.exoplatform.platform.qa.ui.selenium.locator.wiki;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_NAME_USER2;

public class WikiLocators {

    // *====================================================HOME
    // PAGE=================================================================*\\
    public static final SelenideElement ELEMENT_WIKI_PAGE_LINK = $(byId("UITreeExplorer"));

    public static final By ELEMENT_WIKI_HOME_PAGE_LINK =
            By.xpath(".//*[@id='UIWikiBreadCrumb']//*[contains(text(),'Wiki Home')]");

    public static final By ELEMENT_WIKI_HOME_PAGE_TEXT =
            By.xpath("//*[@id='titleInfo' and text()='Wiki Home']");

    public static final By ELEMENT_ADD_PAGE_LINK =
            By.xpath("//div[@data-toggle and text()='Add Page']");

    public static final By ELEMENT_FROM_TEMPLATE_LINK =
            By.xpath("//i[@class='uiIconAddPageFromTemplate']");

    public static final By ELEMENT_BLANK_PAGE_LINK =
            By.xpath("//i[@class='uiIconAddPage']");

    public static final By ELEMENT_WIKI_PAGE_TITLE_RENAME_FIELD =
            By.xpath(".//*[@id='EdiableInput']");

    public static final String ELEMENT_INFOR_BAR_VERSION =
            ".//*[@id='UIWikiPageInfoArea']//*[contains(@class,'label')]//*[@href][text()='$version']";

    public static final By ELEMENT_INFOR_BAR_VIEW_CHANGE_LINK =
            By.xpath(".//*[@id='UIWikiPageInfoArea']//*[contains(@href,'#CompareRevision_changes')]");

    public static final String ELEMENT_WIKI_HOME_PAGE_TITLE =
            "//*[@id='titleInfo' and text()='${title}']";

    public static final String ELEMENT_WIKI_HOME_BREADCRUMB_PATH =
            ".//*[@id='UIWikiBreadCrumb']//*[contains(text(),'$locator1')]/../../../..//*[contains(text(),'$locator2')]/../..//*[contains(text(),'$page')]";

    public static final By ELEMENT_SPACE_SWITCHER_INPUT =
            By.xpath("//*[@id='uiSpaceSwitcher_BreadCrumb']//li[contains(@class, 'spaceSearch')]//input[contains(@placeholder, 'Filter Spaces')]");

    public static final By ELEMENT_SPACE_SWITCHER_CLOSE_BTN =
            By.xpath(".//*[@id='uiSpaceSwitcher_BreadCrumb']//*[contains(@class,'uiIconClose')]");

    public static final By ELEMENT_SPACE_SWITCHET_EMPTY_LIST_SPACE =
            By.id("UISpaceSwitcher_nospace");

    public static final By ELEMENT_SPACE_SWITCHER_SEARCH_FIELD_PLACEHOLDER_TEXT =
            By.xpath(".//*[@id='uiSpaceSwitcher_BreadCrumb']//*[contains(@placeholder,'Filter Spaces')]");

    public static final String ELEMENT_SPACE_SWITCHER_SPACE_AVATAR =
            ".//*[@id='UISpaceSwitcher_/spaces/$space']//img[contains(@src,'SpaceAvtDefault.png')]";

    public static final By ELEMENT_SPACE_SWITCHER_TITLE =
            By.xpath(".//*[@id='uiSpaceSwitcher_BreadCrumb']//*[contains(@class,'title')][contains(.,'Select location')]");

    public static final By ELEMENT_SPACE_SWITCHER_OUTSIDE =
            By.xpath("//div[@id='UIWikiPageControlArea']");

    public static final String ELEMENT_SPACE_SWITCHER_SPACE_POSITION =
            "(//*[contains(@id,'UISpaceSwitcher_/space')])[$num]//*[contains(@alt,'$space')]";

    public static final By ELEMENT_SPACE_SWITCHER_INTRANET_POSITION =
            By.xpath("(//*[contains(@id,'UISpaceSwitcher')])[1][contains(@id,'portal')]");

    public static final By ELEMENT_SPACE_SWITCHER_INTRANET_ICON =
            By.xpath(".//*[@id='UISpaceSwitcher_/portal/intranet']//*[contains(@class,'uiIconWikiWiki')]");

    public static final By ELEMENT_SPACE_SWITCHER_MY_WIKI_POSITION =
            By.xpath("(//*[contains(@id,'UISpaceSwitcher')])[2][contains(@id,'user')]");

    public static final By ELEMENT_SPACE_SWITCHER_MY_WIKI_ICON =
            By.xpath(".//*[contains(@id,'UISpaceSwitcher_/user')]//*[contains(@class,'uiIconWikiMyWiki')]");

    public static final String ELEMENT_WIKI_PAGE_LEFTBOX =
            "//*[@id='iconTreeExplorer']//*[contains(text(),'${title}')]";

    public static final By ELEMENT_EDIT_PAGE_LINK =
            By.xpath("//*[@class='uiIconEditPage uiIconLightGray']");

    public static final By ELEMENT_WIKI_HOME_PAGENOTFOUND =
            By.xpath("//*[text()='Page Not Found']");

    public static final By ELEMENT_BTN_OK = By.xpath("//*[text()='OK']");

    // Confirm message
    public static final String ELEMENT_MESSAGES_TEXT =
            "//*[contains(text(),'$mess')]";

    public static final By ELEMENT_WARNING_OK_BTN =
            By.xpath(".//*[@class='btn'][text()='OK']");

    public static final By ELEMENT_CONFIRM_POPUP_CONFIRM_BTN =
            By.xpath("//button[text()='Confirm']");

    public static final By ELEMENT_CONFIRM_POPUP_CANCEL_BTN =
            By.xpath("//button[text()='Cancel']");

    public static final By ELEMENT_CONFIRM_POPUP_OK_BTN =
            By.xpath("//button[text()='OK']");

    public static final By ELEMENT_CONFIRM_POPUP_YES_BTN =
            By.xpath("//button[text()='Yes']");

    public static final By ELEMENT_CONFIRM_POPUP_NO_BTN =
            By.xpath("//button[text()='No']");

    // More menu

    public static final SelenideElement ELEMENT_MORE_LINK =
            $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[3]/div"));


  public static final SelenideElement ELEMENT_MORE                                          =
          $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[2]/div"));
    public static final By ELEMENT_DELETE_LINK =
            By.xpath(".//*[text()='Delete']");

    public static final By ELEMENT_CONFIRM_WIKI_DELETE =
            By.xpath(".//*[@id='UIWikiDeletePageConfirm']//button[text()='OK']");



  public static final SelenideElement ELEMENT_MOVE_PAGE                                          =
                                                        $(byClassName("uiIconMovePage"));

  public static final By              ELEMENT_MOVE_LINK                                          =
                                                        By.xpath("//*[@class='uiIconMovePage']");


    public static final By ELEMENT_WATCH_LINK =
            By.xpath("//*[@class='uiIconWatchPage']");

    public static final By ELEMENT_UNWATCH_LINK =
            By.xpath("//*[@class='uiIconUnWatchPage']");

    public static final By ELEMENT_PERMISSION_LINK =
            By.xpath("//*[@class='uiIconPagePermission']");

    public static final By ELEMENT_PDF_LINK =
            By.xpath("//*[@class='uiIconExportAsPDF']");

    // Permalink page
    public static final By ELEMENT_PERMALINK_LINK =
            By.xpath("//*[@class='uiIconPermalink']");

    public static final By ELEMENT_PERMALINK_CLOSE =
            By.xpath(".//*[@id='UIWikiPopupWindowL1']//*[@class='uiIconClose pull-right']");

    public static final By ELEMENT_RESTRICTED_WIKI_ICON =
            By.xpath("//*[@id='UIWikiPageInfoArea']//*[contains(@class,'uiIconLockMini')]");

    public static final By ELEMENT_MAKE_PUBLIC_BUTTON =
            By.xpath("//*[contains(@onclick,'MakePublic')]");

    public static final SelenideElement ELEMENT_MSG_MAKE_PUBLIC_RESTRICTED =
            $(byXpath("//*[@id=\"UIWikiPermalinkForm\"]/div[3]/b"));

    public static final By ELEMENT_MAKE_RESTRICT_BUTTON =
            By.xpath("//*[contains(@onclick,'Restrict')]");

    public static final By ELEMENT_PUBLIC_WIKI_ICON =
            By.xpath("//*[@id='UIWikiPageInfoArea']//*[contains(@class,'uiIconUnlockMini')]");

    // permission page
    public static final By ELEMENT_PERMISSION_EDIT_ANY =
            By.xpath("//*[@id='EDITPAGEany']");

    public static final String ELEMENT_PERMISSION_EDIT_CHECKBOX =
            "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'EDITPAGE')]";

    public static final String ELEMENT_PERMISSION_VIEW_CHECKBOX =
            "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'VIEWPAGE')]";

    public static final String ELEMENT_PERMISSION_ADMPAGE_CHECKBOX =
            "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'ADMINPAGE')]";

    public static final String ELEMENT_PERMISSION_ADMWIKI_CHECKBOX =
            "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'ADMINSPACE')]";

    public static final By ELEMENT_PERMISSION_BUTTON_SAVE = By.xpath("//*[text()='Save']");

    public static final By ELEMENT_PERMISSION_VIEW_ANY =
            By.xpath("//*[@id='VIEWPAGEany']");

    public static final String ELEMENT_PERMISSION_EDIT_USER_CHECKED =
            ".//*[@id='EDITPAGE$userGroup'][@checked='']";

    public static final String ELEMENT_PERMISSION_VIEW_USER_CHECKED =
            ".//*[@id='VIEWPAGE$userGroup'][@checked='']";

    // move wiki
    public static final By ELEMENT_MOVE_SPACESWITCHER =
            By.xpath("//*[@id='uiSpaceSwitcher_UIWikiMovePageForm']/..//*[@class='btn dropdown-toggle']");

    public static final By ELEMENT_MOVE_BTNMOVE =
            By.xpath("//*[@class='btn btn-primary' and contains(text(),'Move')]");

    public static final By EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME =
            By.xpath(".//*[@class='alert']//*[contains(@href,'Rename')]");

    public static final String ELEMENT_MOVE_PAGE_POPUP_NEW_LOCATION_HOME =
            ".//*[@id='newLocation']//*[@class='active']/a[contains(text(),'Wiki Home')]/../..//a[contains(text(),'${spaceName}')]";

  public static final String          ELEMENT_MESSAGE_USER_DOES_NOT_HAVE_EDIT_PERMMISSON         =
                                                                                         "You have no edit permission at the destination page";

    // Content of page
    public static final By ELEMENT_PAGE_ATTACHFILE =
            By.xpath("//*[contains(.,'1')]//*[@class='uiIconAttach']");

    public static final By ELEMENT_PAGE_DOWNLOADATTACHFILE =
            By.xpath("//*[@data-original-title='Download Attachment']");

    public static final By ELEMENT_PAGE_DELETEATTACHFILE =
            By.xpath("//*[@class='uiIconDelete uiIconLightGray']");

    public static final By ELEMENT_PAGE_CONTENT_TABLE_MODE =
            By.xpath(".//*[@id='UIViewContentDisplay']/table");

    public static final String ELEMETN_PAGE_CONTENT_TABLE_COL_NUM =
            "(.//*[@id='UIViewContentDisplay']/table//th)[$col]";

    public static final String ELEMETN_PAGE_CONTENT_TABLE_ROW_NUM =
            "(.//*[@id='UIViewContentDisplay']/table//td)[$row]";

    public static final String ELEMENT_PAGE_ATTACHFILE_1 =
            ".//*[@id='UIWikiAttachmentUploadListForm']//*[@title='Download Attachment' and contains(@href,'${fileName}')]/..//i";

    public static final String ELEMENT_PAGE_ATTACHFILE_2 =
            ".//*[@id='UIWikiAttachmentUploadListForm']//*[@data-original-title='Download Attachment' and contains(@href,'${fileName}')]/..//i";

    public static final String ELEMENT_PAGE_ATTACHFILE_NUMBER =
            "//*[contains(.,'${number}')]//*[@class='uiIconAttach']";

    public static final By ELEMENT_SAVE_PERMISSION =
            By.xpath(".//*[@id='UIWikiPagePermissionForm']//*[contains(text(),'Save')]");

    public static final By ELEMENT_ADD_PERMISSION =
            By.xpath("//*[@id='uiWikiPermissionOwner']//*[contains(text(),'Add')]");

    // Action bar
    public static final By ELEMENT_SEARCH_BTN =
            By.xpath(".//*[@id='UIWikiSearchBox']//*[@class='uiIconSearch uiIconLightGray']");

    public static final By ELEMENT_SPACE_DROP_DOWN =
            By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'uiIconMiniArrowDown')]");

    public static final String ELEMENT_SPACE_SWITCHER_SELECTED_SPACE =
            "//ul[@class ='spaceChooserPopup']//a[text()='$space']";

    // Browsers
    public static final By ELEMENT_SEARCH_BROWSERS_DROPDOWN =
            By.xpath("//*[@class='uiActionWithLabel']/..//*[text()='Browse']");

    public static final By ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS =
            By.xpath(".//*[@class='dropdown-menu']//*[text()='Wiki Settings']");

    public static final By ELEMENT_SEARCH_BROWSERS_MY_DRAFT =
            By.xpath(".//*[@class='dropdown-menu']//*[text()='My Drafts']");

    // tree explorer
    public static final String ELEMENT_TREE_WIKI_NAME =
            ".//*[@id='iconTreeExplorer']//*[contains(text(),'${name}')]";

    // *===================================================================WIKI
    // MANAGEMENT===========================================================*\\
    public static final String ELEMENT_CONTENT_WIKI_PAGE =
            ".//*[@id='UIViewContentDisplay']//*[contains(text(),'$content')]";

    public static final By ELEMENT_CONTENT_WIKI_PAGE_EMPTY =
            By.xpath(".//*[@id='UIViewContentDisplay']//*[not(//p)]");

    // Source editor
    public static final By ELEMENT_TITLE_WIKI_INPUT = By.id("titleInput");

    public static final By ELEMENT_CONTENT_WIKI_INPUT = By.id("Markup");

    public static final By ELEMENT_PUBLISH_ACTIVITY_CHECKBOX = By.id("PublishActivityUpper");

    public static final By ELEMENT_PREVIEW_BUTTON =
            By.xpath("//*[@class='uiIconWatchPage']");

    public static final By ELEMENT_PREVIEW_SCREEN =
            By.xpath("//div[@class='popupTitle' and text()='Preview']");

    public static final By ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT =
            By.xpath(".//*[@id='UIWikiPageEditForm']//*[contains(text(),'Draft saved')]");

    public static final SelenideElement ELEMENT_SAVE_BUTTON_ADD_PAGE =
            $(byId("UISubmitToolBarUpper_SavePage_"));

    public static final By ELEMENT_CANCEL_BUTTON_ADD_PAGE =
            By.id("UISubmitToolBarBottom_Cancel_");

    public static final By ELEMENT_RICHTEXT_BUTTON =
            By.xpath("//*[contains(text(),'Rich Text')]");

    public static final By ELEMENT_UPLOAD_NAME = By.name("file");

    // Draft notification
    public static final By ELEMENT_DRAFT_NOTIFY =
            By.xpath("//*[contains(@class, 'uiWikiPageEditForm') and contains(text(), 'Draft saved at')]");

    // Comfirmation popup
    public static final By ELEMENT_CONFIRMATION_POPUP_YES_BTN =
            By.xpath(".//*[@id='UIPortalApplication']//button[text()='Yes']");

    // Add page from template
    public static final String ELEMENT_TEMPLATE_PREVIEW_BTN =
            "(//*[text()='${template}']/following::*[@class='actionIcon'])[1]";

    public static final By ELEMENT_TEMPLATE_SELECT_FORM =
            By.id("UIWikiSelectTemplateForm");

  public static final By ELEMENT_TEMPLATE_SELECT_RADIO_BTN =
          By.xpath("//*[@class='uiRadio']");

    public static final By ELEMENT_TEMPLATE_SELECT_BTN =
            By.xpath(".//*[@id='UIWikiSelectTemplateForm']//*[text()='Select']");

    public static final By ELEMENT_TEMPLATE_CANCEL_BTN =
            By.xpath(".//*[@id='UIWikiSelectTemplateForm']//*[text()='Cancel']");

    // Preview page
    public static final By ELEMENT_PREVIEW_TEMPLATE_CONTENT =
            By.xpath("//*[@id=\"UIWikiMaskWorkspace\"]/div[2]");
    public static final String ELEMENT_PREVIEW_PAGE_CONTENT =
            ".//*[@id='UIPreviewContentDisplay']//*[contains(text(),'${content}')]";

    public static final By ELEMENT_CLOSE_PREVIEW_WINDOW =
            By.xpath("//div[text()='Preview']/..//*[contains(@class,'uiIconClose')]");

    public static final By ELEMENT_TEMPLATE_PREVIEW_PAGE_CLOSE_BTN =
            By.xpath(".//*[@id='UIWikiMaskWorkspace']//*[@class='uiIconClose uiIconLightGray']");

    // comment field
    public static final By ELEMENT_WIKI_PAGE_INPUT_COMMENT = By.id("Comment");

    // Move page popup
    public static final By ELEMENT_WIKI_PAGE_MOVE_POPUP_SAVE =
            By.xpath(".//*[@id='UIWikiMovePageForm']//button[contains(text(),'Move')]");

    public static final String ELEMENT_WIKI_PAGE_MOVE_POPUP_NODE =
            ".//*[@id='UIMoveTree']//*[contains(text(),'${name}')]";

    // Information table
    public static final String ELEMENT_WIKI_PAGE_INFOMATION_VERSION =
            ".//*[@id='UIWikiPageInfoArea']//*[text()='${version}']";

    public static final By ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE =
            By.xpath(".//*[@id='UIWikiPageVersionsList2']//*[text()='Page History']");

    public static final By ELEMENT_WIKI_PAGE_INFORMATION_TABLE_CONTENT =
            By.xpath("//*[@id='UIWikiPageInfoArea']/div");

    // Page info
    public static final By ELEMENT_PAGE_INFO_VIEW_PAGE_INFO_BTN =
            By.xpath(".//button[text()='View Page History']");

    public static final By ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS =
            By.xpath(".//*[@id='UIWikiPageInfo']//button[text()='Add More Relations']");

    public static final String ELEMENT_PAGE_INFO_RECENT_CHANGES_VERSION =
            ".//*[@id='UIWikiPageInfo']//*[contains(@href,'#ViewRevision_$num')]";

    public static final String ELEMENT_PAGE_INFO_VIEW_CONTENT_OF_VERSION =
            ".//*[@id='UIViewContentDisplay']//*[contains(text(),'$content')]";

    public static final By ELEMENT_PAGE_INFOR_VIEW_CONTENT_TITLE =
            By.xpath("//*[contains(@class,'titleInfo')][contains(text(),'View Version')]");

    // View content of the version
    public static final By ELEMENT_PAGE_INFO_VIEW_CONTENT_CURRENT_VERSION_LINK =
            By.xpath(".//*[@id='UIWikiVersionSelect']//*[contains(text(),'current version')]");

    // Page History
    public static final By ELEMENT_WIKI_PAGE_PAGE_HISTORY_TITLE =
            By.xpath(".//h4[text()='Page History']");

    public static final By ELEMENT_WIKI_PAGE_PAGE_HISTORY_COMPARE_BTN =
            By.xpath(".//button[text()='Compare the selected versions']");

    public static final String ELEMENT_PAGE_HISTORY_VERSION =
            ".//a[contains(text(),'$version')]";

    // Compare reversion
    public static final By ELEMENT_WIKI_PAGE_COMPARE_REVERSION_TITLE =
            By.xpath(".//h4[text()='Compare Versions']");

    public static final String ELEMENT_PAGE_HISTORY_COMPARE_CONTENT =
            ".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'${text}')]";

    public static final By ELEMENT_COMPARE_VERSION_CURRENT_VERSION =
            By.xpath(".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Current version')]");

    public static final String ELEMENT_COMPARE_VERSION_VERSION_NUMBER =
            ".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Version $num')]";

    public static final By ELEMENT_WIKI_PAGE_COMPARE_VERSION_TITLE =
            By.xpath(".//h4[text()='Compare Versions']");

    public static final By ELEMENT_COMPARE_VERISON_BTN_DISABLED =
            By.xpath(".//*[@id='UIWikiHistorySpaceArea_UIWikiPageVersionsList']//button[contains(@disabled,'disabled')]");

    public static final String ELEMENT_RESTORE_LINK =
            "//*[contains(text(), 'v.{$version}')]/../../..//*[@class='uiIconRestore uiIconLightGray']";

    public static final By ELEMENT_REVISION_LINK =
            By.xpath("//*[@id='UIWikiPageInfo']//div[@class='actionCenter']");

    public static final String ELEMENT_CHANGES_COMPARE_VERSION =
            "//*[text()='${1stNumber}']/../b[text()='${2ndNumber}']/../..//a[@class='changes']";

    public static final String ELEMENT_VIEW_CHANGE_VERSION =
            "//*[@id='UIWikiPageVersionsCompare']//b[text()='${version}']";

    public static final By ELEMENT_VIEW_VERSION_NEXT_BTN =
            By.xpath(".//*[@id='UIWikiVersionSelect']//*[contains(@href,'#ViewRevision')][contains(text(),'Next')]");

    // Add more relations
    public static final By ELEMENT_ADD_RELATED_PAGE_POPUP_TITLE =
            By.xpath(".//*[contains(text(),'Add Related Page')]");

    public static final By ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN =
            By.xpath(".//*[contains(text(),'Add Related Page')]/../..//*[@data-toggle='dropdown']");

    public static final String ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION =
            ".//*[contains(text(),'Add Related Page')]/../..//*[contains(text(),'${location}')]";

    public static final String ELEMENT_ADD_RELATED_POPUP_CONTENT =
            ".//*[contains(text(),'Add Related Page')]/../..//*[contains(text(),'${page}')]";

    // Content page
    public static final By ELEMENT_WIKI_PAGE_EDIT_PARAGRAPH_BTN = byClassName("uiIconEdit");

    // *==============================================================WIKI SEARCH
    // MANAGEMENT===========================================================*\\
    // Search page
    public static final By ELEMENT_ADVANCED_SEARCH_FILTER =
            By.xpath(".//*[@id='wikis']//input");

    public static final By ELEMENET_ADVANCED_SEARCH_DROP_DOWN =
            By.xpath(".//*[@id='wikis']//*[@id='DisplayModesDropDown']//*[contains(@class,'uiIconMiniArrowDown ')]");

    public static final By ELEMENT_SEARCH_NORESULT =
            By.xpath("//*[@class='resultInfo noResult']");

    public static final By ELEMENT_SEARCH_ADVANCED_SEARCH_BTN =
            By.xpath(".//*[@id='UIWikiAdvanceSearchForm']/button[text()='Search']");

    public static final By ELEMENT_WIKI_ADVANCED_SEARCH_SEARCH_FIELD = By.xpath(".//*[@id='text']");

    // *==============================================================WIKI SETTING
    // MANAGEMENT ========================================================*\\
    public static final By ELEMENT_TEMPLATE_SEARCH_TEXTBOX =
            By.xpath(".//*[@id='TemplateSeachBox']");

    public static final By ELEMENT_TEMPLATE_SEARCH_BTN =
          By.xpath(".//*[@class='uiSearchInput pull-left']/a");

  public static final String ELEMENT_WIKI_SETTINGS_RESULTS =
            ".//*[@id='UIWikiTemplateGrid']//*[text()='${template}']";

    public static final String ELEMENT_EDIT_TEMPLATE =
            ".//*[@id='UIWikiTemplateGrid']//*[contains(text(),'{$template}')]/../..//*[@class='uiIconEditTemplate uiIconLightGray']";

    public static final String ELEMENT_DELETE_TEMPLATE =
            "//*[@id='UIWikiTemplateGrid']//*[contains(text(),'{$template}')]/../..//*[@class='uiIconDeleteTemplate uiIconLightGray']";

    public static final By ELEMENT_TITLE_TEMPLATE = By.id("titleInput");

    public static final By ELEMENT_DESCRIPTION_TEMPLATE = By.id("Description");

    public static final By ELEMENT_CONTENT_TEMPLATE = By.id("Markup");

    public static final By ELEMENT_SAVE_TEMPLATE =
            By.id("UISubmitToolBarUpper_SaveTemplate_");

    public static final By ELEMENT_CANCEL_TEMPLATE =
            By.id("UISubmitToolBarUpper_Cancel_");

    public static final By ELEMENT_WIKI_SETTING_ADD_MORE_TEMPALTE =
            By.xpath(".//*[contains(@onclick,'#AddTemplate')]");

    public static final By ELEMENT_WIKI_SETTING_SEARCH_EMPTY =
            By.xpath(".//*[@id='UIWikiTemplateGrid']//*[contains(@class,'empty')]");

    public static final By ELEMENT_WIKI_SETTING_PAGE_TOTAL_NUMBER =
            By.xpath(".//*[contains(@id,'TemplateSetting')]//*[contains(@class,'pagesTotalNumber')]");

    public static final By ELEMENT_WIKI_SETTING_PAGE_NEXT_BUTTON =
            By.xpath(".//*[contains(@id,'TemplateSetting')]//*[contains(@class,'uiIconNextArrow')]");

    // *==============================================================WIKI DRAFF
    // PAGE===================================================================*\\
    // Manage Draft screen
    public static final String ELEMENT_DRAFT_OF_NEW_PAGE =
            "//*[@id='UIWikiDraftGrid']//*[contains(text(),'${title}')]";

    // *===============================================================RICHTEXT
    // EDITOR
    // ====================================================================*\\
    // Richtext mode
    public static final By ELEMENT_SOURCE_EDITOR_BUTTON =
            By.xpath("//*[contains(text(),'Source Editor')]");

    public static final By ELEMENT_CONTENT_WIKI_FRAME =
            By.xpath("//div[@class='xRichTextEditor']/iframe");

    public static final String ELEMENT_INSERTED_IMAGE_ALT_TEXT =
            ".//*[@id='UIViewContentDisplay']//*[contains(@alt,'$alt')]";

    public static final String ELEMENT_CHECK_IMAGE =
            "//img[contains(@alt, '${file}')]";

    public static final String ELEMETN_WIKI_STATUS_VERSION_TEXT =
            ".//*[contains(text(),'$status')]";

    public static final By ELEMENT_WIKI_STATUS_VERSION_VIEW_CHANGES_LINK =
            By.xpath("//*[@href and contains(text(),'View your Changes')]");

    public static final By ELEMENT_WIKI_STATUS_VERSION_CONTINUE_EDITTING_LINK =
            By.xpath("//*[@href and contains(text(),'Continue Editing')]");

    public static final By ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK =
            By.xpath("//*[@href and contains(text(),'Delete')]");

    public static final By ELEMENT_WIKI_STATUS_RESUME_THE_DRAF_LINK =
            By.xpath("//*[@href and contains(text(),'Resume the Draft')]");

    public static final String ELEMENT_WIKI_STATUS_EDITTING_SAME_PAGE =
            ".//*[contains(text(),'$status')]//b[contains(text(),'$fullName')]";

    public static final String ELEMENT_WIKI_CONTENT_IMAGE_ALT = ".//img[@alt='$alt']";

    public static final By ELEMETN_WIKI_DRAFT_CHANGES_PAGE_TITLE =
            By.xpath(".//*[@id='UIWikiMaskWorkspace']//*[contains(text(),'Draft Changes')]");

    // Link menu
    public static final By ELEMENT_LINK = By.xpath("//div[@class='gwt-MenuItemLabel' and text()='Link']");

    public static final By ELEMENT_WIKI_PAGE_LINK_MENU =
            By.xpath("//*[text()='Wiki Page...']");

    public static final By ELEMENT_WEB_PAGE_LINK_MENU =
            By.xpath("//*[text()='Web Page...']");

    public static final By ELEMENT_ATTACHED_FILE_LINK_MENU =
            By.xpath("//*[text()='Attached File...']");

    public static final By ELEMENT_REMOVE_LINK_MENU =
            By.xpath("//*[text()='Remove Link']");

    public static final By ELEMENT_EMAIL_LINK_MENU =
            By.xpath("//div[@class='gwt-MenuItemLabel' and text()='Email Address...']");

    public static final By ELEMENT_EDIT_LINK_MENU =
            By.xpath("//*[text()='Edit Link...']");

    // Image menu
    public static final By ELEMENT_IMAGE_LINK =
            By.xpath("//*[text()='Image']");

    public static final By ELEMENT_ATTACHED_IMAGE_LINK_MENU =
            By.xpath("//*[text()='Attached Image...']");

    public static final By ELEMENT_EXTERNAL_IMAGE_LINK_MENU =
            By.xpath("//*[text()='External Image...']");

    public static final By ELEMENT_EDIT_IMAGE_LINK_MENU =
            By.xpath("//*[text()='Edit Image...']");

    public static final By ELEMENT_REMOVE_IMAGE_LINK_MENU =
            By.xpath("//*[text()='Remove Image']");

    public static final By ELEMENT_IMAGE_MENU_INSERT_IMAGE_BTN =
            By.xpath("//*[text()='Insert Image']");

    public static final By ELEMENT_IMAGE_MENU_IMAGE_SETTINGS_BTN =
            By.xpath("//*[text()='Image Settings']");

    // Image link popup
    public static final By ELEMENT_IMAGE_WIDTH =
            By.xpath("//div[contains(text(), 'Width')]/..//input[1]");

    public static final By ELEMENT_IMAGE_HEIGHT =
            By.xpath("//div[contains(text(), 'Height')]/..//input[2]");

    public static final By ELEMENT_IMAGE_ALTERNATIVE_TEXT =
            By.xpath("//div[contains(text(), 'Alternative text')]/..//input[1]");

    public static final By ELEMENT_IMAGE_ALIGN_LEFT =
            By.xpath(".//*[@value='LEFT']");

    public static final By ELEMENT_IMAGE_ALIGN_CENTER =
            By.xpath(".//*[@value='CENTER']");

    public static final By ELEMENT_IMAGE_ALIGN_RIGHT =
            By.xpath(".//*[@value='RIGHT']");

    public static final By ELEMENT_IMAGE_ALIGN_TOP = By.xpath(".//*[@value='TOP']");

    public static final By ELEMENT_IMAGE_ALIGN_MIDDLE =
            By.xpath(".//*[@value='MIDDLE']");

    public static final By ELEMENT_IMAGE_ALIGN_BOTTOM =
            By.xpath(".//*[@value='BOTTOM']");

    public static final By ELEMENT_EXTERNAL_IMAGE_INPUT_LINK =
            By.xpath("//*[@title='Image location']");

    // Add wiki page link popup
    public static final By ELEMENT_SEARCH_TEXTBOX_POPUP = By.id("wikiSearchValue");

    public static final By ELEMENT_SEARCH_BUTTON =
            By.xpath("//button[text()='Search']");

    public static final String ELEMENT_SEARCH_TAB_PAGE_SELECTED =
            "//*[contains(@class,'xPagesSearch')]//*[@title='${page}']";

    public static final By ELEMENT_SELECT_BUTTON =
            By.xpath("//*[text()='Select']");

    public static final By ELEMENT_CREATE_LINK_BUTTON =
            By.xpath("//*[text()='Create Link']");

    public static final By ELEMENT_LABEL_LINK_TEXTBOX =
            By.xpath("//input[@title='Type the label of the created link.']");

    public static final By ELEMENT_TOOLTIP_LINK_TEXTBOX =
            By.xpath("//input[@title='Type the tooltip of the created link, which appears when mouse is over the link.']");

    public static final By ELEMENT_ALL_PAGE_TAB =
            By.xpath("//div[contains(text(), 'All pages')]");

    public static final By ELEMENT_MY_RECENT_CHANGES_TAB =
            By.xpath("//div[contains(text(), 'My recent changes')]");

    public static final By ELEMENT_SEARCH_TAB =
            By.xpath("//div[text()='Search']");

    public static final SelenideElement ELEMENT_EXPLORER_WIKIHOME =
            $(byText("WikiHome")).parent().find(byId("isc_1open_icon_2"));

    public static final String ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED =
            ".//*[@class='xPagePreview']//*[text()='$title']";

    public static final By ELEMENT_MY_RECENT_CHANGES_TAB_ADD_NEW_PAGE_BTN =
            By.xpath("//*[contains(@class,'xPagesRecent')]//*[contains(@class,'xNewPagePreview')]");

    public static final By ELEMENT_SEARCH_TAB_ADD_NEW_PAGE_BTN =
            By.xpath("//*[contains(@class,'xPagesSearch')]//*[contains(@class,'xNewPagePreview')]");

    public static final By ELEMENT_ALL_PAGES_TAB_ADD_NEW_PAGE_BTN =
            By.xpath("//*[contains(@class,'listTable')]//*[contains(text(),'New page')]");

    public static final By ELEMENT_INPUT_NAME_NEW_WIKI_PAGE =
            By.xpath("//input[contains(@class,'gwt-TextBox')]");

    public static final By ELEMENT_WIKI_PAGE_LINK_LINK_SETTING_BTN =
            By.xpath("//button[contains(text(),'Link Settings')]");

    public static final String ELEMENT_CURRENT_TAB_ATTACHED_FILE_SELECTED =
            "//*[contains(text(),'$file')]";

    // Web page link popup
    public static final By ELEMENT_WEB_PAGE_WEB_ADDRESS =
            By.xpath("//input[@title='Web page address']");

    // Email link popup
    public static final By ELEMENT_EMAIL_LINK_EMAIL_ADDRESS =
            By.xpath("//input[@title='Email address']");

    // Attached file link popup
    public static final By ELEMENT_CURRENT_PAGE_TAB =
            By.xpath("//div[contains(text(), 'Current page')]");

    public static final By ELEMENT_CURRENT_PAGE_TAB_UPLOAD_NEW_FILE_BTN =
            By.xpath("//*[contains(@class,'xNewFilePreview')]");

    // Image link popup
    public static final By ELEMENT_CURRENT_PAGE_TAB_UPLOAD_IMAGE_BTN =
            By.xpath("//*[contains(@class,'xNewImagePreview')]");

    // *=============================================================SOURCE
    // EDITOR==========================================================*\\
    public static final String ELEMENT_EFFECT_BOLD =
            "//strong[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_BULLET_LIST =
            "//ul/li[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_NUMBER_LIST =
            "//ol/li[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_HEADING_1 =
            "//h1//*[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_HEADING_3 =
            "//h3//*[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_HEADING_2 =
            "//h2//*[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_HEADING_5 =
            "//h5//*[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_ITALIC =
            "//em[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_LINK =
            "//*[contains(@href,'$content')]";

    public static final String ELEMENT_EFFECT_STRIKE =
            "//del[contains(text(),'$content')]";

    public static final String ELEMENT_EFFECT_UNDERLINE =
            "//ins[contains(text(),'$content')]";

    // *============================================================= WIKI
    // PERMISSION =======================================================*\\
    public static final String ELEMENT_DELETE_PERMISSION =
            "//*[contains(text(),'$user')]/../..//*[contains(@class,'uiIconDelete')]";

    public static final By ELEMENT_PERMISSION_TYPE_INPUT = By.id("PermissionOwner");

    public static final By ELEMENT_PERMISSION_ADD_BUTTON = By.xpath("//*[text()='Add']");

    public static final By ELEMENT_PERMISSION_SELECT_USER =
            By.xpath("//a[contains(@onclick, 'OpenSelectUserForm')]");

    public static final By ELEMENT_PERMISSION_SELECT_MEMBERSHIP =
            By.className("uiIconMembership");

    public static final By ELEMENT_WIKI_PAGE_NOT_FOUND =
            By.xpath(".//*[@class='uiWikiPageNotFound']");

    public static final By ELEMENT_MANAGER_PERMISSION_BTN =
            By.xpath(".//*[contains(@onclick,'ManagePermisisons')]");

    public static final SelenideElement ELEMENT_WIKI_LEFT_TREE_RESTRICTED_PAGE_TITLE =
            $(byXpath(".//*[@id='iconTreeExplorer']//*[contains(text(),'restricted')]"));

    public static final By ELEMENT_WIKI_TOOLTIP_RESTRICTED_PAGE_TITLE =
            By.xpath(".//*[@id='iconTreeExplorer']//em[contains(@data-original-title,'Access to this page is restricted, you do not have permission to view it.')]");

    public static final By ELEMENT_WIKI_PARENT_PAGE_UN_LINK =
            By.xpath(".//*[@id='iconTreeExplorer']//em[contains(@onclick,'')]");

    // *============================================================= WIKI SETTING
    // MANAGEMENT =======================================================*\\
    public static final By ELEMENT_VIEW_PAGE_HISTORY =
            By.xpath("//*[@id='UIWikiPageInfo']/div[2]/div[2]/table/tbody/tr[2]/td/div/button");

    public static final SelenideElement ELEMENT_WIKI_UNPUT_LINK_EXISTED_PAGE =
            $(byXpath("//*[@id=\"isc_Class_S1398_0_Input\"]"));

    public static final SelenideElement ELEMENT_BUTTON_WIKI_RITCH_TEXT =
            $(byId("UIEditorTabs")).find(byText("Rich Text"));

    public static final SelenideElement ELEMENT_WIKI_CONTENT_PAGE =
            $(byId("UIViewContentDisplay"));

    public static final SelenideElement ELEMENT_WIKI_PAGE_CONTAINER = $(byId("UIWikiPageContainer"));

    public static final SelenideElement ELEMENT_POPUP_SELECT_WIKI_PAGE = $(byId("isc_2"));

    public static final SelenideElement ELEMENT_WIKI_PERMELINK = $(byId("PermalinkText"));

    public static final SelenideElement ELEMENT_ALERT_MESSAGE = $(byClassName("alert"));

    public static final SelenideElement ELEMENT_ATTACHMENT_TOTAL_NUMBER =
            $(byId("UIWikiPageControlArea")).find(byXpath("//*[@id=\"UIWikiPageInfoArea\"]/div/a[6]"));

    public static final By ELEMENT_CANCEL_WIKI_DELETE =
            By.xpath(".//*[@id='UIWikiDeletePageConfirm']//button[text()='Cancel']");

    public static final SelenideElement ELEMENT_PAGE_INFO =
            $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[3]/ul/li[6]/a[1]"));

    public static final SelenideElement ELEMENT_SELECT_DESTINATION =
            $(byId("uiSpaceSwitcher_UIWikiMovePageForm"));

    public static final SelenideElement ELEMENT_POPUP_SELECT_DESTINATION =
            $(byClassName(("uiWikiMovePageForm")));

    // select HOW-TO Guide template
    public static final SelenideElement ELEMENT_SELECT_TEMPLATE_HowToGuide =
            $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[2]/td[1]/div/input"));

    // select Three-Column Layout template
    public static final SelenideElement ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout =
            $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[3]/td[1]/div/input"));

    // select Status Meeting template
    public static final SelenideElement ELEMENT_SELECT_TEMPLATE_StatusMeeting =
            $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[4]/td[1]/div/input"));

    // select Leave Planning template
    public static final SelenideElement ELEMENT_SELECT_TEMPLATE_LeavePlanning =
            $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[5]/td[1]/div/input"));

    // select Two-Column Layout template
    public static final SelenideElement ELEMENT_SELECT_TEMPLATE_TwoColumnLayout =
            $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[6]/td[1]/div/input"));

    public static final SelenideElement ELEMENT_TREE_NAME_WIKI =
            $(byClassName("uiLeftContainerArea"));

    public static final SelenideElement ELEMENT_DRAFT_NEW_PAGE = $(byId("UIWikiDraftGrid"));

    public static final SelenideElement ELEMENT_MORE_LINK_WITH_USER = $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[2]/div"));

    public static final SelenideElement ELEMENT_WIKI_BUTTON_ADD_MORE_TEMPLATE =
            $(byXpath("//*[@id=\"UIWikiSettingContainer_TemplateSetting_\"]/div[2]/button"));

    public static final SelenideElement ELEMENT_WIKI_OK_SAVE_TEMPLATE =
            $(byClassName("infoIcon")).parent()
                    .parent()
                    .parent()
                    .find(byClassName("btn"));

    public static final SelenideElement ELEMENT_WIKI_LISTE_TEMPLATE = $(byId("myTabContent"));

    public static final By ELEMENT_WIKI_ICON_DELETE_TEMPLATE =
            byClassName("uiIconDeleteTemplate");

    public static final By ELEMENT_WIKI_ICON_EDIT_TEMPLATE =
            byClassName("uiIconEditTemplate");

    public static final SelenideElement ELEMENT_WIKI_PREVIEW_TEMPLATE =
            $(byXpath("//*[@id=\"UIEditorTabs\"]/button[1]"));

    public static final By ELEMENT_WIKI_ICON_DELETE_PERMISSION = byClassName("uiIconDelete");

    public static final SelenideElement ELEMENT_WIKI_ICON_EDIT_PERMISSION_FOR_MARY =
            $(byText(DATA_NAME_USER2)).parent()
                    .parent()
                    .findAll(byClassName("uiCheckbox"))
                    .get(1);


    public static final SelenideElement ELEMENT_BUTTON_OK_IN_WARNING_POPUB_TEMPLATE = $(byClassName("warningIcon")).parent().parent().parent().find(byClassName("btn"));

  public static final SelenideElement ELEMENT_PAGE_WIKI_CONTENT=$(byId("UIViewContentDisplay"));

  public static final SelenideElement ELEMENT_BUTTON_CLOSE_PERMALINK=$(byXpath("//*[@id=\"UIWikiPopupWindowL1\"]/div[1]/a"));
  public static final SelenideElement ELEMENT_BUTTON_CLOSE_PREVIEW_MODE=$(byClassName("actionIcon"));
  public static final SelenideElement ELEMENT_BUTTON_SELECT_RELATION=$(byXpath("//*[@id=\"UIWikiSelectPageForm\"]/div[3]/button[1]"));

}
