package org.exoplatform.platform.qa.ui.selenium.locator.wiki;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_NAME_USER2;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class WikiLocators {

  // *====================================================HOME
  // PAGE=================================================================*\\
  public static final SelenideElement ELEMENT_WIKI_PAGE_LINK                                     = $(byId("UITreeExplorer"));

  public static final By              ELEMENT_WIKI_HOME_PAGE_LINK                                =
                                                                  By.xpath(".//*[@id='UIWikiBreadCrumb']//*[contains(text(),'Wiki Home')]");

  public static final By              ELEMENT_WIKI_HOME_PAGE_TEXT                                =
                                                                  By.xpath("//*[@id='titleInfo' and text()='Wiki Home']");

  public static final By              ELEMENT_ADD_PAGE_LINK                                      =
                                                            By.xpath("//*[@id='UIWikiPageControlArea_PageToolBar']//div[contains(text(),'Add Page')]");

  public static final By              ELEMENT_FROM_TEMPLATE_LINK                                 =
                                                                 By.xpath("//i[@class='uiIconAddPageFromTemplate']");

  public static final By              ELEMENT_BLANK_PAGE_LINK                                    =
                                                              By.xpath("//i[@class='uiIconAddPage']");

  public static final By              ELEMENT_WIKI_PAGE_TITLE_RENAME_FIELD                       =
                                                                           By.xpath(".//*[@id='EdiableInput']");

  public static final String          ELEMENT_INFOR_BAR_VERSION                                  =
                                                                ".//*[@id='UIWikiPageInfoArea']//*[contains(@class,'label')]//*[@href][text()='$version']";

  public static final By              ELEMENT_INFOR_BAR_VIEW_CHANGE_LINK                         =
                                                                         By.xpath(".//*[@id='UIWikiPageInfoArea']//*[contains(@href,'#CompareRevision_changes')]");

  public static final String          ELEMENT_WIKI_HOME_PAGE_TITLE                               =
                                                                   "//*[@id='titleInfo' and text()='${title}']";

  public static final String          ELEMENT_WIKI_HOME_BREADCRUMB_PATH                          =
                                                                        ".//*[@id='UIWikiBreadCrumb']//*[contains(text(),'$locator1')]/../../../..//*[contains(text(),'$locator2')]/../..//*[contains(text(),'$page')]";

  public static final String          ELEMENT_WIKI_HOME_BREADCRUMB_PATH_HOME                     =
                                                                             ".//*[@id='UIWikiBreadCrumb']//*[contains(text(),'$locator1')]/../../../..//*[contains(text(),'$locator2')]";

  public static final By              ELEMENT_SPACE_SWITCHER_INPUT                               =
                                                                   By.xpath("//*[@id='uiSpaceSwitcher_BreadCrumb']//li[contains(@class, 'spaceSearch')]//input[contains(@placeholder, 'Filter Spaces')]");

  public static final By              ELEMENT_SPACE_SWITCHER_INPUT_MOVE_PAGE_POPUP               =
                                                                                   By.xpath(".//*[@id='uiSpaceSwitcher_UIWikiMovePageForm']//li[contains(@class, 'spaceSearch')]//input[contains(@placeholder, 'Filter Spaces')]");

  public static final String          ELEMENT_SPACE_NAME_SELECTED                                =
                                                                  "//*[contains(@id,'UISpaceSwitcher_/spaces/${space}')]/a";

  public static final By              ELEMENT_SPACE_SWITCHER_CLOSE_BTN                           =
                                                                       By.xpath(".//*[@id='uiSpaceSwitcher_BreadCrumb']//*[contains(@class,'uiIconClose')]");

  public static final By              ELEMENT_SPACE_SWITCHET_EMPTY_LIST_SPACE                    =
                                                                              By.id("UISpaceSwitcher_nospace");

  public static final By              ELEMENT_SPACE_SWITCHER_SEARCH_FIELD_PLACEHOLDER_TEXT       =
                                                                                           By.xpath(".//*[@id='uiSpaceSwitcher_BreadCrumb']//*[contains(@placeholder,'Filter Spaces')]");

  public static final String          ELEMENT_SPACE_SWITCHER_SPACE_AVATAR                        =
                                                                          ".//*[@id='UISpaceSwitcher_/spaces/$space']//img[contains(@src,'SpaceAvtDefault.png')]";

  public static final By              ELEMENT_SPACE_SWITCHER_TITLE                               =
                                                                   By.xpath(".//*[@id='uiSpaceSwitcher_BreadCrumb']//*[contains(@class,'title')][contains(.,'Select location')]");

  public static final By              ELEMENT_SPACE_SWITCHER_OUTSIDE                             =
                                                                     By.xpath("//div[@id='UIWikiPageControlArea']");

  public static final String          ELEMENT_SPACE_SWITCHER_SPACE_POSITION                      =
                                                                            "(//*[contains(@id,'UISpaceSwitcher_/space')])[$num]//*[contains(@alt,'$space')]";

  public static final By              ELEMENT_SPACE_SWITCHER_INTRANET_POSITION                   =
                                                                               By.xpath("(//*[contains(@id,'UISpaceSwitcher')])[1][contains(@id,'portal')]");

  public static final By              ELEMENT_SPACE_SWITCHER_INTRANET_ICON                       =
                                                                           By.xpath(".//*[@id='UISpaceSwitcher_/portal/intranet']//*[contains(@class,'uiIconWikiWiki')]");

  public static final By              ELEMENT_SPACE_SWITCHER_MY_WIKI_POSITION                    =
                                                                              By.xpath("(//*[contains(@id,'UISpaceSwitcher')])[2][contains(@id,'user')]");

  public static final By              ELEMENT_SPACE_SWITCHER_MY_WIKI_ICON                        =
                                                                          By.xpath(".//*[contains(@id,'UISpaceSwitcher_/user')]//*[contains(@class,'uiIconWikiMyWiki')]");

  public static final By              ELEMENT_TITLE_WIKI_HOME_LINK                               =
                                                                   By.xpath("//*[@class='titleWikiBox']/*[contains(text(), 'Wiki Home')]");

  public static final String          ELEMENT_WIKI_PAGE_LEFTBOX                                  =
                                                                "//*[@id='iconTreeExplorer']//*[contains(text(),'${title}')]";

  public static final By              ELEMENT_EDIT_PAGE_LINK                                     =
                                                             By.xpath("//*[@class='uiIconEditPage uiIconLightGray']");

  public static final By              ELEMENT_WIKI_HOME_PAGENOTFOUND                             =
                                                                     By.xpath("//*[text()='Page Not Found']");

  public static final By              ELEMENT_WIKI_HOME_PAGE_LOCATION_MYWIKI                     =
                                                                             By.xpath("//*[@class='btn dropdown-toggle']//*[text()='My Wiki']");

  public static final By              ELEMENT_BTN_OK                                             = By.xpath("//*[text()='OK']");

  public static final By              ELEMENT_UNWATCH_CONFIRM                                    =
                                                              By.xpath("//*[contains(text(),'You have stopped watching this page now.')]");

  public static final By              ELEMENT_WIKI_HOME_LEFTBOX_WIKIHOME                         =
                                                                         By.xpath("//*[text()=' Wiki Home']");

  // Confirm message
  public static final String          ELEMENT_MESSAGES_TEXT                                      =
                                                            "//*[contains(text(),'$mess')]";

  public static final By              ELEMENT_WARNING_OK_BTN                                     =
                                                             By.xpath(".//*[@class='btn'][text()='OK']");

  public static final By              ELEMENT_CONFIRM_POPUP_CONFIRM_BTN                          =
                                                                        By.xpath("//button[text()='Confirm']");

  public static final By              ELEMENT_CONFIRM_POPUP_CANCEL_BTN                           =
                                                                       By.xpath("//button[text()='Cancel']");

  public static final By              ELEMENT_CONFIRM_POPUP_OK_BTN                               =
                                                                   By.xpath("//button[text()='OK']");

  public static final By              ELEMENT_CONFIRM_POPUP_YES_BTN                              =
                                                                    By.xpath("//button[text()='Yes']");

  public static final By              ELEMENT_CONFIRM_POPUP_NO_BTN                               =
                                                                   By.xpath("//button[text()='No']");

  // More menu

  public static final SelenideElement ELEMENT_MORE_LINK                                          =
                                                        $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[3]/div"));
  public static final SelenideElement ELEMENT_MORE                                          =
          $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[2]/div"));

  public static final By              ELEMENT_DELETE_LINK                                        =
                                                          By.xpath(".//*[text()='Delete']");

  public static final By              ELEMENT_DELETE_LINK_2                                      =
                                                            By.className("uiIconDeletePage");

  public static final By              ELEMENT_CONFIRM_WIKI_DELETE                                =
                                                                  By.xpath(".//*[@id='UIWikiDeletePageConfirm']//button[text()='OK']");

  public static final SelenideElement ELEMENT_MOVE_PAGE                                          =
                                                        $(byClassName("uiIconMovePage"));

  public static final SelenideElement ELEMENT_MOVE                                         =
                                                   $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[3]/div"));
  public static final By              ELEMENT_MOVE_LINK                                          =
                                                        By.xpath("//*[@class='uiIconMovePage']");

  public static final By              ELEMENT_WATCH_LINK                                         =
                                                         By.xpath("//*[@class='uiIconWatchPage']");

  public static final By              ELEMENT_UNWATCH_LINK                                       =
                                                           By.xpath("//*[@class='uiIconUnWatchPage']");

  public static final By              ELEMENT_PERMISSION_LINK                                    =
                                                              By.xpath("//*[@class='uiIconPagePermission']");

  public static final By              ELEMENT_PDF_LINK                                           =
                                                       By.xpath("//*[@class='uiIconExportAsPDF']");

  // Permalink page
  public static final By              ELEMENT_PERMALINK_LINK                                     =
                                                             By.xpath("//*[@class='uiIconPermalink']");

  public static final By              ELEMENT_PERMALINK_MANAGEPERM                               =
                                                                   By.xpath("//*[text()='Manage Permissions']");

  public static final String          ELEMENT_PERMALINK_STATUS                                   =
                                                               ".//*[@id='UIWikiPermalinkForm']//*[text()='${status}']";

  public static final By              ELEMENT_PERMALINK_CLOSE                                    =
                                                              By.xpath(".//*[@id='UIWikiPopupWindowL1']//*[@class='uiIconClose pull-right']");

  public static final By              ELEMENT_RESTRICTED_WIKI_ICON                               =
                                                                   By.xpath("//*[@id='UIWikiPageInfoArea']//*[contains(@class,'uiIconLockMini')]");

  public static final By              ELEMENT_MAKE_PUBLIC_BUTTON                                 =
                                                                 By.xpath("//*[contains(@onclick,'MakePublic')]");

  public static final SelenideElement ELEMENT_MSG_MAKE_PUBLIC_RESTRICTED                         =
                                                                         $(byXpath("//*[@id=\"UIWikiPermalinkForm\"]/div[3]/b"));

  public static final By              ELEMENT_MAKE_RESTRICT_BUTTON                               =
                                                                   By.xpath("//*[contains(@onclick,'Restrict')]");

  public static final By              ELEMENT_PERMALINK_NOTIFY                                   =
                                                               By.xpath("//*[@id='UIWikiPermalinkForm']/*[@class='permalinkNotify']");

  public static final By              ELEMENT_PERMALINK_TEXT                                     = By.id("PermalinkText");

  public static final By              ELEMENT_PERMALINK_POPUP                                    = By.id("UIWikiPermalinkForm");

  public static final By              ELEMENT_PUBLIC_WIKI_ICON                                   =
                                                               By.xpath("//*[@id='UIWikiPageInfoArea']//*[contains(@class,'uiIconUnlockMini')]");

  public static final By              ELEMENT_RESTICT_WIKI_ICON                                  =
                                                                By.xpath(".//*[@id='UIWikiPageInfoArea']//*[contains(@data-original-title,'This page is restricted. Click to share.')]");

  // Permalink page
  public static final By              ELEMENT_PERMALINK_LINKCOPY                                 =
                                                                 By.xpath("//*[@id='PermalinkText']");

  public static final By              ELEMENT_PERMALINK_MAKEPUBLIC                               =
                                                                   By.xpath("//*[text()='Make Public']");

  public static final By              ELEMENT_PERMALINK_RESTRICT                                 =
                                                                 By.xpath("//*[text()='Restrict']");

  public static final By              ELEMENT_PAGE_PERMISSIONS                                   =
                                                               By.xpath(".//*[text()='Page Permissions']");

  public static final By              ELEMENT_PERMALINK                                          =
                                                        By.xpath("//*[@id='UIWikiPageControlArea_PageToolBar']//*[text()='Permalink']");

  public static final By              ELEMENT_CANCEL_PERMISSION                                  =
                                                                By.xpath(".//*[@id='UIWikiPagePermissionForm']//*[contains(text(),'Cancel')]");

  // Action bar
  public static final String          ELEMENT_PAGE_INFO_ADDED_BY                                 =
                                                                 ".//*[@id='UIWikiPageInfoArea']//*[contains(.,'Added by')]//a[contains(text(),'{$name}')]";

  public static final String          ELEMENT_PAGE_INFO_LAST_MODIFIED_BY                         =
                                                                         ".//*[@id='UIWikiPageInfoArea']//*[contains(.,'Last modified by')]//a[contains(text(),'{$name}')]";

  // permission page
  public static final By              ELEMENT_PERMISSION_EDIT_ANY                                =
                                                                  By.xpath("//*[@id='EDITPAGEany']");

  public static final String          ELEMENT_PERMISSION_EDIT_USER                               = "//*[@id='EDITPAGE${user}']";

  public static final String          ELEMENT_PERMISSION_VIEW_USER                               = "//*[@id='VIEWPAGE${user}']";

  public static final String          ELEMENT_PERMISSION_EDIT_CHECKBOX                           =
                                                                       "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'EDITPAGE')]";

  public static final String          ELEMENT_PERMISSION_VIEW_CHECKBOX                           =
                                                                       "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'VIEWPAGE')]";

  public static final String          ELEMENT_PERMISSION_ADMIN_PAGE_CHECKBOX                     =
                                                                             "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'ADMINPAGE')]";

  public static final String          ELEMENT_PERMISSION_ADMIN_WIKI_CHECKBOX                     =
                                                                             "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'ADMINSPACE')]";

  public static final String          ELEMENT_PERMISSION_ADMPAGE_CHECKBOX                        =
                                                                          "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'ADMINPAGE')]";

  public static final String          ELEMENT_PERMISSION_ADMWIKI_CHECKBOX                        =
                                                                          "//*[contains(text(),'$userGroup')]/../..//*[contains(@name,'ADMINSPACE')]";

  public static final By              ELEMENT_PERMISSION_BUTTON_SAVE                             = By.xpath("//*[text()='Save']");

  public static final By              ELEMENT_PERMISSION_BUTTON_OK                               = By.xpath("//*[text()='OK']");

  public static final By              ELEMENT_PERMISSION_VIEW_ANY                                =
                                                                  By.xpath("//*[@id='VIEWPAGEany']");

  public static final String          ELEMENT_PERMISSION_REMOVE_USER_GROUP                       =
                                                                           ".//*[@id='UIPermissionGrid']//*[contains(text(),'${name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By              ELEMENT_PAGE_PERMISSION_POPUP                              =
                                                                    By.id("UIWikiPagePermissionForm");

  public static final String          ELEMENT_PERMISSION_EDIT_USER_CHECKED                       =
                                                                           ".//*[@id='EDITPAGE$userGroup'][@checked='']";

  public static final String          ELEMENT_PERMISSION_VIEW_USER_CHECKED                       =
                                                                           ".//*[@id='VIEWPAGE$userGroup'][@checked='']";

  public static final By              ELEMENT_PERMISSION_INPUT_SEARCH_USER_NAME                  = By.id("Quick Search");

  public static final By              ELEMENT_PERMISSION_SELECT_SEARCH_OPTION                    = By.id("filter");

  public static final By              ELEMENT_PERMISSION_SEARCH_ICON                             =
                                                                     By.xpath(".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch')]");

  // move wiki
  public static final By              ELEMENT_MOVE_SPACESWITCHER                                 =
                                                                 By.xpath("//*[@id='uiSpaceSwitcher_UIWikiMovePageForm']/..//*[@class='btn dropdown-toggle']");

  public static final By              ELEMENT_MOVE_SPACESWITCHER_MYWIKI                          =
                                                                        By.xpath("//*[@class='uiIconWikiMyWiki uiIconWikiLightGray']");

  public static final String          ELEMENT_MOVE_SPACESWITCHER_OTHERSPACE                      = "//*[text()='${name}']";

  public static final By              ELEMENT_MOVE_BTNMOVE                                       =
                                                           By.xpath("//*[@class='btn btn-primary' and contains(text(),'Move')]");

  public static final String          ELEMENT_MOVE_SPACESWITCHER_OTHERPAGE                       =
                                                                           "//*[@id='UIMoveTree']/../..//*[contains(text(),'${title}')]";

  public static final By              ELEMENT_MOVE_RENAMEWIKI                                    =
                                                              By.xpath("//*[text()='Rename']");

  public static final By              ELEMENT_MOVE_RESTRICTED                                    =
                                                              By.xpath("//*[@class='warningIcon' and contains(text(),'You have no edit permission at the destination page')]");

  public static final By              ELEMENT_MOVE_PAGE_POPUP                                    =
                                                              By.xpath(".//*[@id='UIWikiMovePageForm']");

  public static final String          ELEMENT_MOVE_PAGE_SPACE_SWITCHER_DROP_DOWN_VALUE_SELECTED  =
                                                                                                ".//*[@id='uiSpaceSwitcher_UIWikiMovePageForm']//*[@id='DisplayModesDropDown']//*[contains(text(),'$destination')]";

  public static final String          ELEMENT_MOVE_PAGE_POPUP_DROP_DOWN_LOCATOR                  =
                                                                                ".//*[@id='UIWikiPopupWindowL1']//*[contains(text(),'Move Page')]/../..//*[contains(text(),'${locator}')]";

  public static final String          ELEMENT_MOVE_PAGE_TREE_SELECTED_PAGE                       =
                                                                           ".//*[@id='UIWikiMovePageForm']//*[@id='iconTreeExplorer']//*[contains(text(),'$page')]";

  public static final String          ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_SAME_NAME            =
                                                                                      ".//*[@class='alert'][contains(.,'${message}')]";

  public static final String          EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_LINK          =
                                                                                        ".//*[@class='alert'][contains(.,'$message')]//*[contains(@href,'Rename')]";

  public static final By              ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_AND_MORE             =
                                                                                     By.xpath(".//*[@class='alert'][contains(.,'and more')]");

  public static final By              EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME               =
                                                                                   By.xpath(".//*[@class='alert']//*[contains(@href,'Rename')]");

  public static final By              ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_TOOLTIP       =
                                                                                           By.xpath(".//*[@class='alert']//*[contains(@title,'Rename the page to move')]");

  public static final String          EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_TOOLTIP       =
                                                                                           ".//*[@class='alert'][contains(.,'$message')]//*[contains(@title,'Rename the sub-page to move')]";

  public static final String          EMENENT_MOVE_ONE_PAGE_POPUP_ALERT_MESSAGE_RENAME_TOOLTIP   =
                                                                                               ".//*[@class='alert'][contains(.,'$message')]//*[contains(@title,'Rename the page to move')]";

  public static final By              ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME               =
                                                                                   By.xpath(".//*[@class='alert']/a[text()='Rename']");

  public static final String          ELEMENT_MOVE_PAGE_POPUP_NEW_LOCATION_HOME                  =
                                                                                ".//*[@id='newLocation']//*[@class='active']/a[contains(text(),'Wiki Home')]/../..//a[contains(text(),'${spaceName}')]";


  public static final String          ELEMENT_MESSAGE_USER_DOES_NOT_HAVE_EDIT_PERMMISSON         =
                                                                                         "You have no edit permission at the destination page";

  public static final By              ELEMENT_MOVE_PAGE_SELECT_THE_DESTINATION_LABEL             =
                                                                                     By.xpath(".//*[@id='UIWikiMovePageForm']//*[@id='uiSpaceSwitcher_UIWikiMovePageForm']/../*[contains(text(),'Select the destination')]");

  public static final By              ELEMENT_MOVE_PAGE_DESTINATION_LABEL                        =
                                                                          By.xpath(".//*[@id='UIWikiMovePageForm']//*[@class='sideContent']/../*[@class='barContent' and contains(text(),'Destination')]");

  // public static final By ELEMENT_MOVE_PAGE_POPUP =
  // By.id("UIWikiPopupWindowL1");
  public static final By              ELEMENT_MOVE_PAGE_TITLE                                    =
                                                              By.xpath(".//*[@id='UIWikiPopupWindowL1']//*[contains(@class,'PopupTitle') and contains(text(),'Move Page')]");

  public static final By              ELEMENT_MOVE_PAGE_SPACE_SWITCHER_LIST                      =
                                                                            By.xpath(".//*[@id='uiSpaceSwitcher_UIWikiMovePageForm']//*[@class='spaceChooserPopup']");

  public static final String          ELEMENT_MOVE_PAGE_SELECTED_SPACE                           =
                                                                       ".//*[@id='UIWikiMovePageForm']//*[contains(text(),'Select the destination')]/..//*[@class='btn dropdown-toggle']//*[text()='${space}']";

  // Content of page
  public static final String          ELEMENT_MARCRO_COLOR                                       =
                                                           "//*[@style='color:${color};' and contains(text(),'${message}')]";

  public static final By              ELEMENT_PAGE_TITLE_INFO                                    = By.id("titleInfo");

  public static final String          ELEMENT_PAGE_TITLE                                         =
                                                         ".//*[@id='titleInfo'][text()='${title}']";

  public static final By              ELEMENT_PAGE_TITLE_EDIT_TEXTBOX                            = By.id("EdiableInput");

  public static final By              ELEMENT_PAGE_ATTACHFILE                                    =
                                                              By.xpath("//*[contains(.,'1')]//*[@class='uiIconAttach']");

  public static final By              ELEMENT_PAGE_DOWNLOADATTACHFILE                            =
                                                                      By.xpath("//*[@data-original-title='Download Attachment']");

  public static final By              ELEMENT_PAGE_DOWNLOADATTACHFILE_2                          =
                                                                        By.xpath(".//*[@id='UIWikiAttachmentUploadListForm']//*[@title='Download Attachment']/..//i");

  public static final By              ELEMENT_PAGE_DELETEATTACHFILE                              =
                                                                    By.xpath("//*[@class='uiIconDelete uiIconLightGray']");

  public static final By              ELEMENT_PAGE_CONTENT_TABLE_MODE                            =
                                                                      By.xpath(".//*[@id='UIViewContentDisplay']/table");

  public static final String          ELEMETN_PAGE_CONTENT_TABLE_COL_NUM                         =
                                                                         "(.//*[@id='UIViewContentDisplay']/table//th)[$col]";

  public static final String          ELEMETN_PAGE_CONTENT_TABLE_ROW_NUM                         =
                                                                         "(.//*[@id='UIViewContentDisplay']/table//td)[$row]";

  public static final String          ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE                    =
                                                                              "//*[@title='Download Attachment' and text()='${fileName}']/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2                  =
                                                                                "//*[@data-original-title='Download Attachment' and text()='${fileName}']/../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_PAGE_ATTACHFILE_1                                  =
                                                                ".//*[@id='UIWikiAttachmentUploadListForm']//*[@title='Download Attachment' and contains(@href,'${fileName}')]/..//i";

  public static final String          ELEMENT_PAGE_ATTACHFILE_2                                  =
                                                                ".//*[@id='UIWikiAttachmentUploadListForm']//*[@data-original-title='Download Attachment' and contains(@href,'${fileName}')]/..//i";

  public static final String          ELEMENT_PAGE_ATTACHFILE_NUMBER                             =
                                                                     "//*[contains(.,'${number}')]//*[@class='uiIconAttach']";

  public static final By              ELEMENT_SAVE_PERMISSION                                    =
                                                              By.xpath(".//*[@id='UIWikiPagePermissionForm']//*[contains(text(),'Save')]");

  public static final By              ELEMENT_ADD_PERMISSION                                     =
                                                             By.xpath("//*[@id='uiWikiPermissionOwner']//*[contains(text(),'Add')]");

  public static final String          ELEMENT_CHECK_PERMISSION_EDIT_PAGE                         =
                                                                         ".//*[@id='UIPermissionGrid']/table//*[contains(text(),'{$name}')]/../..//*[@id='EDITPAGE{$name}']";

  public static final String          ELEMENT_REMOVE_PERMISSION                                  =
                                                                ".//*[@id='UIPermissionGrid']/table//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

  // Action bar
  public static final String          ELEMENT_ATTACHMENT_NUMBER                                  =
                                                                "//*[@id='UIWikiPageInfoArea']//a[contains(text(),'${No}')]/*[@class='uiIconAttach']";

  public static final By              ELEMENT_ATTACHMENT_ICON                                    =
                                                              By.xpath("//*[@id='UIWikiPageInfoArea']//*[@class='uiIconAttach']");

  public static final By              ELEMENT_SEARCH_TEXTBOX                                     =
                                                             By.xpath("//*[@id='wikiSearchValue']");

  public static final By              ELEMENT_SEARCH_BTN                                         =
                                                         By.xpath(".//*[@id='UIWikiSearchBox']//*[@class='uiIconSearch uiIconLightGray']");

  public static final By              ELEMENT_SPACE_DROP_DOWN                                    =
                                                              By.xpath(".//*[@id='DisplayModesDropDown']//*[contains(@class,'uiIconMiniArrowDown')]");

  public static final String          ELEMENT_SPACE_SWITCHER_SELECTED_SPACE                      =
                                                                            "//*[contains(@class,'spaceChooserPopup')]//*[contains(@alt,'$space')]";

  // Browsers
  public static final By              ELEMENT_SEARCH_BROWSERS_DROPDOWN                           =
                                                                       By.xpath("//*[@class='uiActionWithLabel']/..//*[text()='Browse']");

  public static final By              ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS                      =
                                                                            By.xpath(".//*[@class='dropdown-menu']//*[text()='Wiki Settings']");

  public static final By              ELEMENT_SEARCH_BROWSERS_MY_DRAFT                           =
                                                                       By.xpath(".//*[@class='dropdown-menu']//*[text()='My Drafts']");

  // tree explorer
  public static final String          ELEMENT_TREE_WIKI_NAME                                     =
                                                             ".//*[@id='iconTreeExplorer']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_TREE_WIKI_PARENT_NODE_CHILD_NODE                   =
                                                                               ".//*[@id='iconTreeExplorer']//*[contains(text(),'$parent')]/../../..//*[contains(text(),'$child')]";

  // Permission
  public static final By              ELEMENT_PERMISSION_NAMEORGROUP                             =
                                                                     By.xpath("//*[@id='PermissionOwner']");

  public static final By              ELEMENT_PERMISSION_BTNADD                                  = By.xpath("//*[text()='Add']");

  // *===================================================================WIKI
  // MANAGEMENT===========================================================*\\
  public static final String          ELEMENT_PAGE_INFOR_RECENT_CHANES                           =
                                                                       ".//*[contains(text(),'v.1')]/../..//*[contains(text(),'John Smith')]";

  public static final String          ELEMENT_PAGE_INFOR_HIERARCHY_CHILD_PAGES                   =
                                                                               ".//*[contains(text(),'Child Pages')]/..//*[contains(text(),'${child}')]";

  public static final String          ELEMENT_TITLE_INFO                                         =
                                                         "//*[@id='titleInfo' and text()= '${title}']";

  public static final String          ELEMENT_CONTENT_WIKI_PAGE                                  =
                                                                ".//*[@id='UIViewContentDisplay']//*[contains(text(),'$content')]";

  public static final By              ELEMENT_CONTENT_WIKI_PAGE_EMPTY                            =
                                                                      By.xpath(".//*[@id='UIViewContentDisplay']//*[not(//p)]");

  public static final String          ELEMENT_EMAIL_LINK_EMAIL_FORMAT                            =
                                                                      ".//*[@id='UIViewContentDisplay']//*[contains(@href,'mailto:$email')]";

  // Source editor
  public static final By              ELEMENT_TITLE_WIKI_INPUT                                   = By.id("titleInput");

  public static final By              ELEMENT_CONTENT_WIKI_INPUT                                 = By.id("Markup");

  public static final By              ELEMENT_PUBLISH_ACTIVITY_CHECKBOX                          = By.id("PublishActivityUpper");

  public static final By              ELEMENT_PREVIEW_BUTTON                                     =
                                                             By.xpath("//*[@class='uiIconWatchPage']");

  public static final By              ELEMENT_PREVIEW_SCREEN                                     =
                                                             By.xpath("//div[@class='popupTitle' and text()='Preview']");

  public static final By              ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT                  =
                                                                                By.xpath(".//*[@id='UIWikiPageEditForm']//*[contains(text(),'Draft saved')]");

  public static final SelenideElement ELEMENT_SAVE_BUTTON_ADD_PAGE                               =
                                                                   $(byId("UISubmitToolBarUpper_SavePage_"));

  public static final By              ELEMENT_CANCEL_BUTTON_ADD_PAGE                             =
                                                                     By.id("UISubmitToolBarBottom_Cancel_");

  public static final By              ELEMENT_RICHTEXT_BUTTON                                    =
                                                              By.xpath("//*[contains(text(),'Rich Text')]");

  public static final By              ELEMENT_UPLOAD_FILE_BUTTON                                 =
                                                                 By.xpath("//*[text()='Upload New File']");

  public static final By              ELEMENT_UPLOAD_NAME                                        = By.name("file");

  public static final By              ELEMENT_BODY_CONTAINER                                     =
                                                             By.xpath("//*[@class='uiRightContainerArea']");

  // Draft notification
  public static final By              ELEMENT_DRAFT_NOTIFY                                       =
                                                           By.xpath("//*[contains(@class, 'uiWikiPageEditForm') and contains(text(), 'Draft saved at')]");

  // Comfirmation popup
  public static final By              ELEMENT_CONFIRMATION_POPUP_YES_BTN                         =
                                                                         By.xpath(".//*[@id='UIPortalApplication']//button[text()='Yes']");

  public static final String          ELEMENT_POPUP_MESSAGE_CONTENT                              =
                                                                    ".//*[contains(text(),'${message}')]";

  // Add page from template
  public static final String          ELEMENT_SELECT_TEMPLATE_LINK                               =
                                                                   ".//*[contains(text(),'${template}')]/../..//input";

  public static final By              ELEMENT_TEMPLATE_PREVIEW_BTN                              =
          By.xpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[2]/td[4]/a[2]/i");

  public static final By              ELEMENT_TEMPLATE_SELECT_FORM                               =
                                                                   By.id("UIWikiSelectTemplateForm");

  public static final By              ELEMENT_TEMPLATE_SELECT_BTN                                =
                                                                  By.xpath(".//*[@id='UIWikiSelectTemplateForm']//*[text()='Select']");

  public static final By              ELEMENT_TEMPLATE_CANCEL_BTN                                =
                                                                  By.xpath(".//*[@id='UIWikiSelectTemplateForm']//*[text()='Cancel']");

  // Preview page
  public static final By              ELEMENT_PREVIEW_TEMPLATE_CONTENT                          =
          By.xpath("//*[@id=\"UIWikiMaskWorkspace\"]/div[2]");
  public static final String          ELEMENT_PREVIEW_PAGE_CONTENT                               =
                                                                   ".//*[@id='UIPreviewContentDisplay']//*[contains(text(),'${content}')]";

  public static final By              ELEMENT_CLOSE_PREVIEW_WINDOW                               =
                                                                   By.xpath("//div[text()='Preview']/..//*[contains(@class,'uiIconClose')]");

  public static final By              ELEMENT_TEMPLATE_PREVIEW_PAGE_CLOSE_BTN                    =
                                                                              By.xpath(".//*[@id='UIWikiMaskWorkspace']//*[@class='uiIconClose uiIconLightGray']");

  // comment field
  public static final By              ELEMENT_WIKI_PAGE_INPUT_COMMENT                            = By.id("Comment");

  // Move page popup
  public static final By              ELEMENT_WIKI_PAGE_MOVE_POPUP_SAVE                          =
                                                                        By.xpath(".//*[@id='UIWikiMovePageForm']//button[contains(text(),'Move')]");

  public static final String          ELEMENT_WIKI_PAGE_MOVE_POPUP_NODE                          =
                                                                        ".//*[@id='UIMoveTree']//*[contains(text(),'${name}')]";

  // Information table
  public static final String          ELEMENT_WIKI_PAGE_INFOMATION_VERSION                       =
                                                                           ".//*[@id='UIWikiPageInfoArea']//*[text()='${version}']";

  public static final By              ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE                  =
                                                                                By.xpath(".//*[@id='UIWikiPageVersionsList2']//*[text()='Page History']");

  public static final By              ELEMENT_WIKI_PAGE_INFORMATION_TABLE_CONTENT               =
          By.xpath("//*[@id='UIWikiPageInfoArea']/div");
  // Information area
  public static final String          ELEMENT_WIKI_PAGE_INFORMATION_AREA_EDIT_INFOR              =
                                                                                    ".//*[@id='UIWikiPageInfoArea']//*[contains(.,'${info}')]";

  public static final String          ELEMENT_WIKI_PAGE_INFORMATION_AREA_TOTAL_ATTACHEDFILES     =
                                                                                             ".//*[@id='UIWikiPageInfoArea']//*[contains(text(),'${number}')]";

  public static final String          ELEMENT_WIKI_PAGE_INFORMATION_AREA_RESTRICTED_STATUS       =
                                                                                           ".//*[@id='UIWikiPageInfoArea']//*[contains(text(),'${status}')]";

  // Page info
  public static final String          ELEMENT_WIKI_PAGE_PAGE_INFO_TITLE                          =
                                                                        ".//*[@id='UIWikiPageContainer']/h4[text()='Page Info']";

  public static final By              ELEMENT_PAGE_INFO_VIEW_PAGE_INFO_BTN                       =
                                                                           By.xpath(".//button[text()='View Page History']");

  public static final By              ELEMENT_PAGE_INFO_SUMMARY_TABLE                            =
                                                                      By.xpath("//*[contains(@class,'uiPageInfoSummary')]");

  public static final String          ELEMENT_PAGE_INFOR_SUMMARY_TITLE                           =
                                                                       ".//*[@id='UIWikiPageInfo']//*[contains(text(),'Title')]/../..//*[contains(text(),'${content}')]";

  public static final String          ELEMENT_PAGE_INFOR_SUMMARY_AUTHOR                          =
                                                                        ".//*[@id='UIWikiPageInfo']//*[contains(text(),'Author')]/../..//*[contains(text(),'${fullname}')]/..//*[contains(text(),'${date}')]";

  public static final String          ELEMENT_PAGE_INFOR_SUMMARY_LAST_CHANGED                    =
                                                                              ".//*[@id='UIWikiPageInfo']//*[contains(text(),'Last changed by')]/../..//*[contains(text(),'${fullname}')]/..//*[contains(text(),'${date}')]";

  public static final By              ELEMENT_PAGE_INFO_RELATED_TABLE                            =
                                                                      By.xpath("//*[contains(@class,'uiPageInfoRelatedPage')]");

  public static final By              ELEMENT_PAGE_INFOR_RELATED                                 =
                                                                 By.xpath(".//*[@id='UIWikiPageInfo']//*[contains(text(),'Wiki')]/..//*[contains(text(),'Related Pages')]/..//*[contains(text(),'Actions')]");

  public static final By              ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS                       =
                                                                           By.xpath(".//*[@id='UIWikiPageInfo']//button[text()='Add More Relations']");

  public static final String          ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT                    =
                                                                              ".//*[@id='UIWikiPageInfo']//*[contains(text(),'${col1}')]/..//*[contains(text(),'${col2}')]";

  public static final String          ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN                 =
                                                                                 ".//*[contains(text(),'${name}')]/../../../../..//*[@class='uiIconDelete uiIconLightGray']";

  public static final By              ELEMENT_PAGE_INFO_HIERARCHY_TABLE                          =
                                                                        By.xpath("//*[contains(@class,'uiPageInfoHierarchy')]");

  public static final By              ELEMENT_PAGE_INFOR_HIERARCHY_PARENT_PAGES                  =
                                                                                By.xpath(".//*[contains(text(),'Parent Page')]/..//*[contains(text(),'Wiki Home')]");

  public static final By              ELEMENT_PAGE_INFO_RECENT_CHANGES_TABLE                     =
                                                                             By.xpath("//*[contains(@class,'uiPageInfoChanges')]");

  public static final String          ELEMENT_PAGE_INFO_RELATED_PAGE_LINK                        =
                                                                          ".//*[@id='UIWikiRelatedPages']//*[contains(text(),'$page')]//*[contains(@class,'uiIconFileMini')]";

  public static final String          ELEMENT_PAGE_INFO_RECENT_CHANGES_VERSION                   =
                                                                               ".//*[@id='UIWikiPageInfo']//*[contains(@href,'#ViewRevision_$num')]";

  public static final String          ELEMENT_PAGE_INFO_VIEW_CONTENT_OF_VERSION                  =
                                                                                ".//*[@id='UIViewContentDisplay']//*[contains(text(),'$content')]";

  public static final By              ELEMENT_PAGE_INFOR_VIEW_CONTENT_TITLE                      =
                                                                            By.xpath("//*[contains(@class,'titleInfo')][contains(text(),'View Version')]");

  // View content of the version
  public static final By              ELEMENT_PAGE_INFO_VIEW_CONTENT_CURRENT_VERSION_LINK        =
                                                                                          By.xpath(".//*[@id='UIWikiVersionSelect']//*[contains(text(),'current version')]");

  // Page History
  public static final By              ELEMENT_WIKI_PAGE_PAGE_HISTORY_TITLE                       =
                                                                           By.xpath(".//h4[text()='Page History']");

  public static final String          ELEMENT_WIKI_PAGE_PAGE_HISTORY_CHECKBOX                    =
                                                                              ".//a[contains(text(),'${reversion}')]/../../..//input";

  public static final By              ELEMENT_WIKI_PAGE_PAGE_HISTORY_COMPARE_BTN                 =
                                                                                 By.xpath(".//button[text()='Compare the selected versions']");

  public static final String          ELEMENT_PAGE_HISTORY_VERSION                               =
                                                                   ".//a[contains(text(),'$version')]";

  // Compare reversion
  public static final By              ELEMENT_WIKI_PAGE_COMPARE_REVERSION_TITLE                  =
                                                                                By.xpath(".//h4[text()='Compare Versions']");

  public static final String          ELEMENT_PAGE_HISTORY_COMPARE_CONTENT                       =
                                                                           ".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'${text}')]";

  public static final By              ELEMENT_COMPARE_VERSION_CURRENT_VERSION                    =
                                                                              By.xpath(".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Current version')]");

  public static final String          ELEMENT_COMPARE_VERSION_VERSION_NUMBER                     =
                                                                             ".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Version $num')]";

  public static final By              ELEMENT_WIKI_PAGE_COMPARE_VERSION_TITLE                    =
                                                                              By.xpath(".//h4[text()='Compare Versions']");

  public static final By              ELEMENT_COMPARE_VERISON_BTN_DISABLED                       =
                                                                           By.xpath(".//*[@id='UIWikiHistorySpaceArea_UIWikiPageVersionsList']//button[contains(@disabled,'disabled')]");

  public static final String          ELEMENT_RESTORE_LINK                                       =
                                                           "//*[contains(text(), 'v.{$version}')]/../../..//*[@class='uiIconRestore uiIconLightGray']";

  public static final By              ELEMENT_REVISION_LINK                                      =
                                                            By.xpath("//*[@id='UIWikiPageInfo']//div[@class='actionCenter']");

  public static final String          ELEMENT_CHANGES_COMPARE_VERSION                            =
                                                                      "//*[text()='${1stNumber}']/../b[text()='${2ndNumber}']/../..//a[@class='changes']";

  public static final String          ELEMENT_VIEW_CHANGE_VERSION                                =
                                                                  "//*[@id='UIWikiPageVersionsCompare']//b[text()='${version}']";

  public static final By              ELEMENT_VIEW_VERSION_NEXT_BTN                              =
                                                                    By.xpath(".//*[@id='UIWikiVersionSelect']//*[contains(@href,'#ViewRevision')][contains(text(),'Next')]");

  public static final By              ELEMENT_VIEW_VERSION_PREVIOUS_BTN                          =
                                                                        By.xpath(".//*[@id='UIWikiVersionSelect']//*[contains(@href,'#ViewRevision')][contains(text(),'Prev')]");

  // Add more relations
  public static final By              ELEMENT_ADD_RELATED_PAGE_POPUP_TITLE                       =
                                                                           By.xpath(".//*[contains(text(),'Add Related Page')]");

  public static final By              ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN                    =
                                                                              By.xpath(".//*[contains(text(),'Add Related Page')]/../..//*[@data-toggle='dropdown']");

  public static final String          ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION                =
                                                                                  ".//*[contains(text(),'Add Related Page')]/../..//*[contains(text(),'${location}')]";

  public static final String          ELEMENT_ADD_RELATED_POPUP_CONTENT                          =
                                                                        ".//*[contains(text(),'Add Related Page')]/../..//*[contains(text(),'${page}')]";

  public static final By              ELEMENT_ADD_RELATED_POPUP_SELECT_BTN                       =
                                                                           By.xpath(".//button[text()='Select']");

  public static final By              ELEMENT_ADD_RELATED_POPUP_CLOSE_BTN                        =
                                                                          By.xpath(".//*[contains(text(),'Add Related Page')]/..//*[@class='uiIconClose pull-right']");

  public static final String          ELEMENT_ADD_RELATED_POPUP_DROPDOWN_VALUE                   =
                                                                               ".//*[@id='uiSpaceSwitcher_UIWikiSelectPageForm']//*[contains(text(),'$space')]";

  // Space swithcher drop down
  public static final By              ELEMENT_SPACE_SWITHCHER_DROPDOWN_CLOSE                     =
                                                                             By.xpath(".//*[@id='uiSpaceSwitcher_UIWikiSelectPageForm']/.//*[@title='Close']");

  public static final By              ELEMENT_ADD_RELATED_POPUP_DROPDOWN_NOSPACE                 =
                                                                                 By.xpath(".//*[@id='UISpaceSwitcher_nospace'][text()='No Spaces']");

  // Content page
  public static final String          ELEMENT_WIKI_PAGE_CONTENT                                  =
                                                                ".//*[@id='UIViewContentDisplay']//*[contains(text(),'${text}')]";

  public static final By              ELEMENT_WIKI_PAGE_EDIT_PARAGRAPH_BTN                       = byClassName("uiIconEdit");

  // Email notification
  public static final By              ELEMENT_GMAIL_PREVIOUS_EMAIL                               =
                                                                   By.xpath(".//*[@class='gE hI']");

  public static final String          ELEMENT_GMAIL_CONTENT_LINK_WIKI                            =
                                                                      ".//a[contains(@href,'${page}')]";

  public static final String          ELEMENT_GMAIL_CONTENT_WIKI                                 =
                                                                 ".//span[contains(.,'\"${title}\" page was modified')]";

  // *==============================================================WIKI SEARCH
  // MANAGEMENT===========================================================*\\
  // Search page
  public static final String          ELEMENT_SEARCH_RESULT                                      =
                                                            "//*[@class='uiIconFile']/..//*[contains(text(),'${title}')]";

  public static final By              ELEMENT_SEARCH_DROPDOWNSPACE                               =
                                                                   By.xpath("//*[@id='wikis']/..//*[@id='DisplayModesDropDown']");

  public static final By              ELEMENT_ADVANCED_SEARCH_FILTER                             =
                                                                     By.xpath(".//*[@id='wikis']//input");

  public static final By              ELEMENET_ADVANCED_SEARCH_DROP_DOWN                         =
                                                                         By.xpath(".//*[@id='wikis']//*[@id='DisplayModesDropDown']//*[contains(@class,'uiIconMiniArrowDown ')]");

  public static final String          ELEMENT_ADVANCED_SEARCH_SPACE_SWITCHER                     =
                                                                             ".//*[@id='wikis']//*[contains(@alt,'$space')]";

  public static final String          ELEMENT_SEARCH_DROPDOWNSPACE_LOCATION                      = "//*[@title='${location}']";

  public static final By              ELEMENT_SEARCH_NORESULT                                    =
                                                              By.xpath("//*[@class='resultInfo noResult']");

  public static final By              ELEMENT_SEARCH_ADVANCED_SEARCH_BTN                         =
                                                                         By.xpath(".//*[@id='UIWikiAdvanceSearchForm']/button[text()='Search']");

  public static final By              ELEMENT_WIKI_SEARCH_FIELD                                  =
                                                                By.xpath(".//*[@id='wikiSearchValue']");

  public static final String          ELEMENT_WIKI_SEARCH_RESULT_PAGE_LINK                       =
                                                                           ".//*[@id='UIWikiAdvanceSearchResult']/ul//*[contains(text(),'$page')]";

  public static final By              ELEMENT_WIKI_ADVANCED_SEARCH_SEARCH_FIELD                  = By.xpath(".//*[@id='text']");

  public static final By              ELEMENT_WIKI_SEARCH_EMPTY_RESULTS                          =
                                                                        By.xpath(".//*[@id='UIWikiAdvanceSearchResult']//*[contains(@class,'noResult')]");

  public static final By              ELEMENT_WIKI_QUICK_SEARCH_BTN                              =
                                                                    By.xpath(".//*[@id='UIWikiSearchBox']//*[contains(@class,'uiIconSearch ')]");

  // *==============================================================WIKI SETTING
  // MANAGEMENT ========================================================*\\
  public static final By              ELEMENT_TEMPLATE_SEARCH_TEXTBOX                            =
                                                                      By.xpath(".//*[@id='TemplateSeachBox']");

  public static final By              ELEMENT_WIKI_SETTINGS_TITLE                                =
                                                                  By.xpath(".//*[@id='UIWikiSettingContainer']/h4[text()='Wiki Settings']");

  public static final String          ELEMENT_WIKI_SETTINGS_RESULTS                              =
                                                                    ".//*[@id='UIWikiTemplateGrid']//*[text()='${template}']";

  public static final String          ELEMENT_EDIT_TEMPLATE                                      =
                                                            ".//*[@id='UIWikiTemplateGrid']//*[contains(text(),'{$template}')]/../..//*[@class='uiIconEditTemplate uiIconLightGray']";

  public static final String          ELEMENT_DELETE_TEMPLATE                                    =
                                                              "//*[@id='UIWikiTemplateGrid']//*[contains(text(),'{$template}')]/../..//*[@class='uiIconDeleteTemplate uiIconLightGray']";

  public static final By              ELEMENT_TITLE_TEMPLATE                                     = By.id("titleInput");

  public static final By              ELEMENT_DESCRIPTION_TEMPLATE                               = By.id("Description");

  public static final By              ELEMENT_CONTENT_TEMPLATE                                   = By.id("Markup");

  public static final By              ELEMENT_SAVE_TEMPLATE                                      =
                                                            By.id("UISubmitToolBarUpper_SaveTemplate_");

  public static final By              ELEMENT_CANCEL_TEMPLATE                                    =
                                                              By.id("UISubmitToolBarUpper_Cancel_");

  public static final By              ELEMENT_WIKI_SETTING_TEMPLATE_TAB                          =
                                                                        By.xpath(".//*[contains(@href,'TemplateSetting')]");

  public static final By              ELEMENT_WIKI_SETTING_PERMISSION_TAB                        =
                                                                          By.xpath(".//*[contains(@href,'PermissionSetting')]");

  public static final By              ELEMENT_WIKI_SETTING_ADD_MORE_TEMPALTE                     =
                                                                             By.xpath(".//*[contains(@onclick,'#AddTemplate')]");

  public static final By              ELEMENT_WIKI_SETTING_SEARCH_EMPTY                          =
                                                                        By.xpath(".//*[@id='UIWikiTemplateGrid']//*[contains(@class,'empty')]");

  public static final By              ELEMENT_WIKI_SETTING_PAGE_TOTAL_NUMBER                     =
                                                                             By.xpath(".//*[contains(@id,'TemplateSetting')]//*[contains(@class,'pagesTotalNumber')]");

  public static final By              ELEMENT_WIKI_SETTING_PAGE_NEXT_BUTTON                      =
                                                                            By.xpath(".//*[contains(@id,'TemplateSetting')]//*[contains(@class,'uiIconNextArrow')]");

  // *==============================================================WIKI DRAFF
  // PAGE===================================================================*\\
  // Manage Draft screen
  public static final String          ELEMENT_DRAFT_PAGE_TITLE                                   =
                                                               ".//*[@id='UIWikiMyDraftsForm']//*[@class='titleInfo' and contains(text(),'My drafts')]";

  public static final String          ELEMENT_DELETE_DRAFT_MESSAGE                               =
                                                                   "Are you sure you want to delete this draft?";

  public static final String          ELEMENT_DRAFT_OF_NEW_PAGE                                  =
                                                                "//*[@id='UIWikiDraftGrid']//*[contains(text(),'${title}')]";

  public static final By              ELEMENT_DELETE_DRAFT                                      =
          By.xpath("//*[@id=\"UIWikiDraftGrid\"]/table/tbody/tr/td[4]/a[2]/i");

  public static final String          ELEMENT_DRAFT_OF_EDIT_PAGE                                 =
                                                                 "//*[@id='UIWikiDraftGrid']//*[text()='${title}']";

  public static final By              ELEMENT_DRAFT_CONFIRM_POPUP                                =
                                                                  By.xpath("//div[@class='confirmMessage' and contains(text(), 'The draft has been created. Do you want to keep it?')]");

  public static final By              ELEMENT_DRAFT_NO_BUTTON                                    =
                                                              By.xpath("//*[contains(text(),'No')]");

  public static final String          ELEMENT_DRAFT_TITLE                                        =
                                                          "//*[contains(text(), '${title}')]";

  // *===============================================================RICHTEXT
  // EDITOR
  // ====================================================================*\\
  // Richtext mode
  public static final By              ELEMENT_SOURCE_EDITOR_BUTTON                               =
                                                                   By.xpath("//*[contains(text(),'Source Editor')]");

  public static final By              ELEMENT_SOURCE_EDITOR_BUTTON_PLF4_1                        =
                                                                          By.xpath("//button[contains(text(),'Source Editor')]");

  public static final By              ELEMENT_CONTENT_WIKI_FRAME                                 =
                                                                 By.xpath("//div[@class='xRichTextEditor']/iframe");

  public static final By              ELEMENT_CONTENT_WIKI_IMG                                   =
                                                               By.xpath("//div[@id='UIViewContentDisplay']/../..//img");

  public static final By              ELEMENT_TWO_LAYOUT_RIGHT                                   =
                                                               By.xpath("//div[@style='float:left;width:49.2%;padding-right:1.5%;']");

  public static final By              ELEMENT_TWO_LAYOUT_LEFT                                    =
                                                              By.xpath("//div[@style='float:left;width:49.2%;']");

  public static final By              ELEMENT_THREE_LAYOUT_RIGHT                                 =
                                                                 By.xpath("//div[@style='float:left;width:32.300000000000004%;padding-right:1.5%;'][1]");

  public static final By              ELEMENT_THREE_LAYOUT_MID                                   =
                                                               By.xpath("//div[@style='float:left;width:32.300000000000004%;padding-right:1.5%;'][2]");

  public static final By              ELEMENT_THREE_LAYOUT_LEFT                                  =
                                                                By.xpath("//div[@style='float:left;width:32.300000000000004%;']");

  public static final String          EMENENT_STATUS_LAYOUT                                      =
                                                            "//th[contains(text(), '${title}')]";

  public static final String          EMENENT_HOW_LAYOUT                                         =
                                                         "//a[contains(text(), '${title}')]";

  public static final By              EMENENT_LEAVE_PLANING_LAYOUT                               =
                                                                   By.xpath("//*[contains(text(), 'The Confluence team uses tables to communicate scheduled leave times')]");

  public static final String          ELEMENT_INSERTED_IMAGE_SIZE                                =
                                                                  ".//*[@id='UIViewContentDisplay']//*[contains(@width,'$width')][contains(@height,'$height')]";

  public static final String          ELEMENT_INSERTED_IMAGE_ALT_TEXT                            =
                                                                      ".//*[@id='UIViewContentDisplay']//*[contains(@alt,'$alt')]";

  public static final String          ELEMENT_CHECK_IMAGE                                        =
                                                          "//img[contains(@alt, '${file}')]";

  public static final String          ELEMENT_CHECK_ATTACHED_FILE_LINK                           =
                                                                       ".//*[contains(@href,'$file')]";

  public static final String          ELEMETN_WIKI_STATUS_VERSION_TEXT                           =
                                                                       ".//*[contains(text(),'$status')]";

  public static final By              ELEMENT_WIKI_STATUS_VERSION_VIEW_CHANGES_LINK              =
                                                                                    By.xpath("//*[@href and contains(text(),'View your Changes')]");

  public static final By              ELEMENT_WIKI_STATUS_VERSION_CONTINUE_EDITTING_LINK         =
                                                                                         By.xpath("//*[@href and contains(text(),'Continue Editing')]");

  public static final By              ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK                    =
                                                                              By.xpath("//*[@href and contains(text(),'Delete')]");

  public static final By              ELEMENT_WIKI_STATUS_RESUME_THE_DRAF_LINK                   =
                                                                               By.xpath("//*[@href and contains(text(),'Resume the Draft')]");

  public static final String          ELEMENT_WIKI_STATUS_EDITTING_SAME_PAGE                     =
                                                                             ".//*[contains(text(),'$status')]//b[contains(text(),'$fullName')]";

  public static final String          ELEMENT_WIKI_CONTENT_IMAGE_ALT                             = ".//img[@alt='$alt']";

  public static final By              ELEMETN_WIKI_DRAFT_CHANGES_PAGE_TITLE                      =
                                                                            By.xpath(".//*[@id='UIWikiMaskWorkspace']//*[contains(text(),'Draft Changes')]");

  // Macro
  public static final By              ELEMENT_MACRO_LINK                                         =
                                                         By.xpath("//*[text()='Macro']");

  public static final By              ELEMENT_INSERT_MACRO_LINK                                  =
                                                                By.xpath("//*[text()='Insert Macro...']");

  public static final By              ELEMENT_EDIT_MACRO_LINK                                    =
                                                              By.xpath("//*[text()='Edit Macro Properties...']");

  public static final By              ELEMENT_MACRO_CATEGORY_SELECT                              =
                                                                    By.xpath("//select[@title='Select a macro category']");

  public static final By              ELEMENT_MACRO_TYPE_FILTER                                  =
                                                                By.xpath("//input[@title='Type to filter']");

  public static final String          ELEMENT_MACRO_LABEL                                        = "//*[text()='${macro}']";

  public static final By              ELEMENT_RICHTEXTMODE_FRAME                                 = By.id("gwt-RichTextArea");

  public static final String          ELEMENT_MACRO_BOX                                          =
                                                        "//div[@class='box']/*[contains(.,'${macro}')]";

  public static final String          ELEMENT_MACRO_EXCERPT                                      =
                                                            "//*[@class='ExcerptClass' and contains(text(),'${macro}')]";

  public static final String          ELEMENT_MACRO_INFO_MESSAGE                                 =
                                                                 "//*[@class='box infomessage' and contains(text(),'${macro}')]";

  public static final String          ELEMENT_MACRO_TABLE_CONTENT                                =
                                                                  "//span[@class='macro-placeholder' and contains(.,'toc')]";

  public static final String          ELEMENT_MACRO_TIP_MESSAGE                                  =
                                                                "//*[@class='box tipmessage' and contains(text(),'${macro}')]";

  public static final String          ELEMENT_MACRO_ERROR_MESSAGE                                =
                                                                  "//*[@class='box errormessage' and contains(text(),'${macro}')]";

  public static final String          ELEMENT_MACRO_SUCCESS_MESSAGE                              =
                                                                    "//*[@class='box successmessage' and contains(text(),'${macro}')]";

  public static final String          ELEMENT_MACRO_TEXT                                         =
                                                         "//*[contains(@style,'${color}') and contains(text(),'${text}')]";

  public static final String          ELEMENT_MACRO_WARNING_MESSAGE                              =
                                                                    "//*[@class='box warningmessage' and contains(text(),'${macro}')]";

  public static final String          ELEMENT_MACRO_CHART                                        = "//img[@alt='${title}']";

  public static final By              ELEMENT_MACRO_RSS_TITLE                                    =
                                                              By.xpath("//p[@class='rssitemtitle']");

  public static final By              ELEMENT_MACRO_COLLAPSE_LINK                                =
                                                                  By.xpath("//div[@class='gwt-MenuItemLabel' and text()='Collapse All']");

  public static final By              ELEMENT_MACRO_EXPAND_LINK                                  =
                                                                By.xpath("//div[@class='gwt-MenuItemLabel' and text()='Expand All']");

  public static final String          ELEMENT_MACRO_COLLAPSED_LINK                               =
                                                                   "//span[@class='macro-placeholder' and text()='${macro}']";

  // Link menu
  public static final By              ELEMENT_LINK                                               = By.xpath("//*[text()='Link']");

  public static final By              ELEMENT_WIKI_PAGE_LINK_MENU                                =
                                                                  By.xpath("//*[text()='Wiki Page...']");

  public static final By              ELEMENT_WEB_PAGE_LINK_MENU                                 =
                                                                 By.xpath("//*[text()='Web Page...']");

  public static final By              ELEMENT_ATTACHED_FILE_LINK_MENU                            =
                                                                      By.xpath("//*[text()='Attached File...']");

  public static final By              ELEMENT_REMOVE_LINK_MENU                                   =
                                                               By.xpath("//*[text()='Remove Link']");

  public static final By              ELEMENT_EMAIL_LINK_MENU                                    =
                                                              By.xpath("//*[text()='Email Address...']");

  public static final By              ELEMENT_EDIT_LINK_MENU                                     =
                                                             By.xpath("//*[text()='Edit Link...']");

  // Image menu
  public static final By              ELEMENT_IMAGE_LINK                                         =
                                                         By.xpath("//*[text()='Image']");

  public static final By              ELEMENT_ATTACHED_IMAGE_LINK_MENU                           =
                                                                       By.xpath("//*[text()='Attached Image...']");

  public static final By              ELEMENT_EXTERNAL_IMAGE_LINK_MENU                           =
                                                                       By.xpath("//*[text()='External Image...']");

  public static final By              ELEMENT_EDIT_IMAGE_LINK_MENU                               =
                                                                   By.xpath("//*[text()='Edit Image...']");

  public static final By              ELEMENT_REMOVE_IMAGE_LINK_MENU                             =
                                                                     By.xpath("//*[text()='Remove Image']");

  public static final By              ELEMENT_IMAGE_MENU_INSERT_IMAGE_BTN                        =
                                                                          By.xpath("//*[text()='Insert Image']");

  public static final By              ELEMENT_IMAGE_MENU_IMAGE_SETTINGS_BTN                      =
                                                                            By.xpath("//*[text()='Image Settings']");

  // Image link popup
  public static final String          ELEMENT_IMAGE_LINK_IMAGE_THUMBNAIL                         =
                                                                         "//*[contains(@title,'$image')]";

  public static final By              ELEMENT_IMAGE_WIDTH                                        =
                                                          By.xpath("//div[contains(text(), 'Width')]/..//input[1]");

  public static final By              ELEMENT_IMAGE_HEIGHT                                       =
                                                           By.xpath("//div[contains(text(), 'Height')]/..//input[2]");

  public static final By              ELEMENT_IMAGE_ALTERNATIVE_TEXT                             =
                                                                     By.xpath("//div[contains(text(), 'Alternative text')]/..//input[1]");

  public static final By              ELEMENT_IMAGE_ALIGN_LEFT                                   =
                                                               By.xpath(".//*[@value='LEFT']");

  public static final By              ELEMENT_IMAGE_ALIGN_CENTER                                 =
                                                                 By.xpath(".//*[@value='CENTER']");

  public static final By              ELEMENT_IMAGE_ALIGN_RIGHT                                  =
                                                                By.xpath(".//*[@value='RIGHT']");

  public static final By              ELEMENT_IMAGE_ALIGN_TOP                                    = By.xpath(".//*[@value='TOP']");

  public static final By              ELEMENT_IMAGE_ALIGN_MIDDLE                                 =
                                                                 By.xpath(".//*[@value='MIDDLE']");

  public static final By              ELEMENT_IMAGE_ALIGN_BOTTOM                                 =
                                                                 By.xpath(".//*[@value='BOTTOM']");

  public static final By              ELEMENT_EXTERNAL_IMAGE_INPUT_LINK                          =
                                                                        By.xpath("//*[@title='Image location']");

  // Add wiki page link popup
  // public By ELEMENT_SEARCH_TEXTBOX_POPUP = By.xpath("//input[@title='Type a
  // keyword to search for a wiki page']");
  public static final By              ELEMENT_SEARCH_TEXTBOX_POPUP                               = By.id("wikiSearchValue");

  public static final By              ELEMENT_SEARCH_BUTTON                                      =
                                                            By.xpath("//button[text()='Search']");

  public static final String          ELEMENT_PAGE_SELECTED                                      =
                                                            "//*[@class='xPagePreview' and @title='${page}']";

  public static final String          ELEMENT_SEARCH_TAB_PAGE_SELECTED                           =
                                                                       "//*[contains(@class,'xPagesSearch')]//*[@title='${page}']";

  public static final By              ELEMENT_SELECT_BUTTON                                      =
                                                            By.xpath("//*[text()='Select']");

  public static final By              ELEMENT_CREATE_LINK_BUTTON                                 =
                                                                 By.xpath("//*[text()='Create Link']");

  public static final String          ELEMENT_PAGE_SELECTED_PLF41                                =
                                                                  "//*[@class='xPagesSelector xPagesSearch']//*[@class='xPagePreview' and @title='${page}']";

  public static final By              ELEMENT_LABEL_LINK_TEXTBOX                                 =
                                                                 By.xpath("//input[@title='Type the label of the created link.']");

  public static final By              ELEMENT_TOOLTIP_LINK_TEXTBOX                               =
                                                                   By.xpath("//input[@title='Type the tooltip of the created link, which appears when mouse is over the link.']");

  public static final By              ELEMENT_REMOVE_LINK                                        =
                                                          By.xpath("//div[text()='Remove Link']");

  public static final By              ELEMENT_EDIT_LINK                                          =
                                                        By.xpath("//div[text()='Edit Link...']");

  public static final By              ELEMENT_ADD_NEW_LINKPAGE_TEXTBOX                           =
                                                                       By.xpath("//input[@title='Type the name of the page to be created. The final name of the page may vary since some characters are filtered.']");

  public static final By              ELEMENT_ALL_PAGE_TAB                                       =
                                                           By.xpath("//div[contains(text(), 'All pages')]");

  public static final By              ELEMENT_ADD_NEW_PAGE_LINK                                  =
                                                                By.xpath("//*[@class='gwt-Label xNewPagePreview']");

  public static final By              ELEMENT_ADD_WIKI_PAGE_FRAME                                =
                                                                  By.xpath("//iframe[@class='gwt-RichTextArea']");

  public static final By              ELEMENT_OPEN_NEW_WINDOW_CHECKBOX                           =
                                                                       By.xpath(".//*[contains(@class,'xLinkConfig')]//input[contains(@id,'gwt-uid')]");

  public static final By              ELEMENT_MY_RECENT_CHANGES_TAB                              =
                                                                    By.xpath("//div[contains(text(), 'My recent changes')]");

  public static final By              ELEMENT_SEARCH_TAB                                         =
                                                         By.xpath("//div[text()='Search']");

  public static final SelenideElement ELEMENT_EXPLORER_WIKIHOME                                  =
                                                                $(byText("WikiHome")).parent().find(byId("isc_1open_icon_2"));

  public static final SelenideElement ELEMENT_ICON_OPEN_INTRANET_IN_ALL_PAGE_TAB                 = $(byId("isc_1open_icon_0"));

  public static final String          ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED                =
                                                                                  ".//*[@class='xPagePreview']//*[text()='$title']";

  public static final By              ELEMENT_MY_RECENT_CHANGES_TAB_ADD_NEW_PAGE_BTN             =
                                                                                     By.xpath("//*[contains(@class,'xPagesRecent')]//*[contains(@class,'xNewPagePreview')]");

  public static final By              ELEMENT_SEARCH_TAB_ADD_NEW_PAGE_BTN                        =
                                                                          By.xpath("//*[contains(@class,'xPagesSearch')]//*[contains(@class,'xNewPagePreview')]");

  public static final By              ELEMENT_ALL_PAGES_TAB_ADD_NEW_PAGE_BTN                     =
                                                                             By.xpath("//*[contains(@class,'listTable')]//*[contains(text(),'New page')]");

  public static final By              ELEMENT_INPUT_NAME_NEW_WIKI_PAGE                           =
                                                                       By.xpath("//input[contains(@class,'gwt-TextBox')]");

  public static final By              ELEMENT_WIKI_PAGE_LINK_LINK_SETTING_BTN                    =
                                                                              By.xpath("//button[contains(text(),'Link Settings')]");

  public static final String          ELEMENT_CURRENT_TAB_ATTACHED_FILE_SELECTED                 =
                                                                                 "//*[contains(text(),'$file')]";

  // Web page link popup
  public static final By              ELEMENT_WEB_PAGE_WEB_ADDRESS                               =
                                                                   By.xpath("//input[@title='Web page address']");

  // Email link popup
  public static final By              ELEMENT_EMAIL_LINK_EMAIL_ADDRESS                           =
                                                                       By.xpath("//input[@title='Email address']");

  // Attached file link popup
  public static final By              ELEMENT_CURRENT_PAGE_TAB                                   =
                                                               By.xpath("//div[contains(text(), 'Current page')]");

  public static final By              ELEMENT_CURRENT_PAGE_TAB_UPLOAD_NEW_FILE_BTN               =
                                                                                   By.xpath("//*[contains(@class,'xNewFilePreview')]");

  public static final By              ELEMENT_CURRENT_PAGE_TAB_UPLOAD_NAME                       = By.name("filepath");

  public static final String          ELEMENT_ALL_PAGE_SELECT_ATTACHEMENT_FILE_PAGE              =
                                                                                    ".//*[@class='listTable']//*[contains(text(),'Attachments ($page)')]";

  // Image link popup
  public static final By              ELEMENT_CURRENT_PAGE_TAB_UPLOAD_IMAGE_BTN                  =
                                                                                By.xpath("//*[contains(@class,'xNewImagePreview')]");

  // Table
  public static final By              ELEMENT_TABLE_LINK                                         =
                                                         By.xpath("//*[text()='Table']");

  public static final By              ELEMENT_INSERT_TABLE_LINK                                  =
                                                                By.xpath("//*[text()='Insert Table...']");

  public static final By              ELEMENT_ROW_TEXTBOX                                        =
                                                          By.xpath("//*[@title='Row count']");

  public static final By              ELEMENT_COLUMN_TEXTBOX                                     =
                                                             By.xpath("//*[@title='Column count']");

  // Macro: Color
  public static final By              ELEMENT_COLOR_TEXTBOX                                      = By.id("pd-name-input");

  public static final By              ELEMENT_COLOR_MESSAGE                                      = By.id("pd-content-input");

  // Macro: Box
  public static final By              ELEMENT_MACRO_BOX_CSSCLASS_FIELD                           = By.id("pd-cssClass-input");

  public static final By              ELEMENT_MACRO_BOX_IMAGE_FIELD                              = By.id("pd-image-input");

  public static final By              ELEMENT_MACRO_BOX_TITLE_FIELD                              = By.id("pd-title-input");

  public static final By              ELEMENT_MACRO_BOX_WIDTH_FIELD                              = By.id("pd-width-input");

  public static final By              ELEMENT_MACRO_BOX_CONTENT_FIELD                            = By.id("pd-content-input");

  // Macro:children
  public static final By              ELEMENT_MACRO_CHILD_CHILDNUM_FIELD                         = By.id("pd-childrenNum-input");

  public static final By              ELEMENT_MACRO_CHILD_DEPTH_FIELD                            = By.id("pd-depth-input");

  public static final By              ELEMENT_MACRO_CHILD_DESCENDANT_FIELD                       = By.id("pd-descendant-input");

  public static final By              ELEMENT_MACRO_CHILD_EXCERPT_FIELD                          = By.id("pd-excerpt-input");

  public static final By              ELEMENT_MACRO_CHILD_PARENT_FIELD                           = By.id("pd-parent-input");

  // Macro: Code
  public static final By              ELEMENT_MACRO_CODE_CSSCLASS_FIELD                          = By.id("pd-cssClass-input");

  public static final By              ELEMENT_MACRO_CODE_IMAGE_FIELD                             = By.id("pd-image-input");

  public static final By              ELEMENT_MACRO_CODE_LANGUAGE_FIELD                          = By.id("pd-language-input");

  public static final By              ELEMENT_MACRO_CODE_TITLE_FIELD                             = By.id("pd-title-input");

  public static final By              ELEMENT_MACRO_CODE_WIDTH_FIELD                             = By.id("pd-width-input");

  public static final By              ELEMENT_MACRO_CODE_CONTENT_FIELD                           = By.id("pd-content-input");

  // Macro: Excerpt
  public static final By              ELEMENT_MACRO_EXCERPT_DROPBOX                              = By.id("pd-hidden-input");

  public static final By              ELEMENT_MACRO_EXCERPT_CONTENT_FIELD                        = By.id("pd-content-input");

  // Macro: Message
  public static final By              ELEMENT_MACRO_MESSAGE_CONTENT_FIELD                        = By.id("pd-content-input");

  // Macro: FootNode
  public static final By              ELEMENT_MACRO_FOOTNODE_CONTENT_FIELD                       = By.id("pd-content-input");

  public static final String          ELEMENT_MACRO_FOOTNOTE                                     =
                                                             "//li[contains(.,'${macro}')]//a[text()='^']";

  // Macro: Table of content
  public static final By              ELEMENT_MACRO_TABLE_OF_CONTENT_DEPTH_FIELD                 = By.id("pd-depth-input");

  public static final By              ELEMENT_MACRO_TABLE_OF_CONTENT_NUMBERED_FIELD              = By.id("pd-numbered-input");

  public static final By              ELEMENT_MACRO_TABLE_OF_CONTENT_SCOPE_FIELD                 = By.id("pd-scope-input");

  public static final By              ELEMENT_MACRO_TABLE_OF_CONTENT_START_FIELD                 = By.id("pd-start-input");

  // Macro:IFrame
  public static final By              ELEMENT_MACRO_IFRAME_HEIGHT_FIELD                          = By.id("pd-height-input");

  public static final By              ELEMENT_MACRO_IFRAME_SRC_FIELD                             = By.id("pd-src-input");

  public static final By              ELEMENT_MACRO_IFRAME_WIDTH_FIELD                           = By.id("pd-width-input");

  public static final String          ELEMENT_MACRO_IFRAME_IN_CONTENT_PAGE                       =
                                                                           "//*[@id='UIViewContentDisplay']/iframe[contains(@src,'$src')]";

  // Macro:JIRA
  public static final By              ELEMENT_MACRO_JIRA_URL_FIELD                               = By.id("pd-URL-input");

  public static final By              ELEMENT_MACRO_JIRA_FIELD_NAMES_FIELD                       = By.id("pd-fieldNames-input");

  public static final By              ELEMENT_MACRO_JIRA_FIELDS_FIELD                            = By.id("pd-fields-input");

  public static final By              ELEMENT_MACRO_JIRA_SOURCE_FIELD                            = By.id("pd-source-input");

  public static final By              ELEMENT_MACRO_JIRA_STYLE_FIELD                             = By.id("pd-style-input");

  public static final By              ELEMENT_MACRO_JIRA_CONTENT_FIELD                           = By.id("pd-content-input");

  public static final String          ELEMENT_MARCO_HEADER_TABLE_JIRA                            =
                                                                      ".//*[@id='UIViewContentDisplay']/table//*[contains(text(),'$header')]";

  // Macro:HTML
  public static final By              ELEMENT_MACRO_HTML_CLEAN_FIELD                             = By.id("pd-clean-input");

  public static final By              ELEMENT_MACRO_HTML_WIKI_NAMES_FIELD                        = By.id("pd-wiki-input");

  public static final By              ELEMENT_MACRO_HTML_CONENT_FIELD                            = By.id("pd-content-input");

  public static final String          ELEMENT_MACRO_HTML_TABLE_INTO_CONTENT_PAGE                 =
                                                                                 ".//*[@id='UIViewContentDisplay']/table//*[contains(text(),'$text')]";

  // Macro:RSS
  public static final By              ELEMENT_MACRO_RSS_COUNT_FIELD                              = By.id("pd-count-input");

  public static final By              ELEMENT_MACRO_RSS_DECORATION_FIELD                         = By.id("pd-decoration-input");

  public static final By              ELEMENT_MACRO_RSS_FEED_FIELD                               = By.id("pd-feed-input");

  public static final By              ELEMENT_MACRO_RSS_IMAGE_FIELD                              = By.id("pd-image-input");

  public static final By              ELEMENT_MACRO_RSS_WIDTH_FIELD                              = By.id("pd-width-input");

  public static final By              ELEMENT_MACRO_RSSS_CONTENT_FIELD                           = By.id("pd-content-input");

  public static final By              ELEMENT_MACRO_RSS_IN_CONTENT_PAGE                          =
                                                                        By.xpath("//div[@class='box rssfeed']");

  public static final By              ELEMENT_CREATE_MACRO_BUTTON                                =
                                                                  By.xpath("//button[text()='Insert Macro']");

  public static final String          ELEMENT_MACRO_CLASS_INSERT_INTO_FRAME                      =
                                                                            ".//*[contains(@class,'$macro')]";

  // Macro JIRA
  public static final By              ELEMENT_JIRA_URL                                           = By.id("pd-URL-input");

  public static final By              ELEMENT_JIRA_CONTENT                                       = By.id("pd-content-input");

  // public String ELEMENT_JIRA_MACRO_LINK =
  // ".//*[@id='body']//*[contains(@href,'${content}')]//*[@class='wikimodel-verbatim'
  // and text()='${content}'] ";
  // public String ELEMENT_JIRA_MACRO_LINK = "//*[text()='${content}']";
  public static final String          ELEMENT_JIRA_MACRO_LINK                                    =
                                                              ".//*[@id='body']//*[@class='macro-output']";

  // public String ELEMENT_JIRA_MACRO_LINK = ".//*[@id='body']//table";
  public static final By              ELEMENT_JIRA_TABLE                                         =
                                                         By.xpath("//*[@class='wikimodel-verbatim'][contains(text(),'Type')]");

  public static final By              ELMENET_MACRO_JIRA_EDIT_FORM_APPLY_BTN                     =
                                                                             By.xpath("//button[contains(text(),'Apply')]");

  // public String ELEMENT_JIRA_TABLE = "//table";
  // *=============================================================SOURCE
  // EDITOR==========================================================*\\
  public static final String          ELEMENT_EFFECT_BOLD                                        =
                                                          "//strong[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_BULLET_LIST                                 =
                                                                 "//ul/li[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_NUMBER_LIST                                 =
                                                                 "//ol/li[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_HEADING_1                                   =
                                                               "//h1//*[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_HEADING_3                                   =
                                                               "//h3//*[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_HEADING_2                                   =
                                                               "//h2//*[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_HEADING_5                                   =
                                                               "//h5//*[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_ITALIC                                      =
                                                            "//em[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_LINK                                        =
                                                          "//*[contains(@href,'$content')]";

  public static final String          ELEMENT_EFFECT_STRIKE                                      =
                                                            "//del[contains(text(),'$content')]";

  public static final String          ELEMENT_EFFECT_UNDERLINE                                   =
                                                               "//ins[contains(text(),'$content')]";

  // *============================================================= WIKI
  // PERMISSION =======================================================*\\
  public static final String          ELEMENT_DELETE_PERMISSION                                  =
                                                                "//*[contains(text(),'$user')]/../..//*[contains(@class,'uiIconDelete')]";

  public static final String          MSG_PERMISSION_SAVE                                        =
                                                          "The permission setting has been saved successfully.";

  public static final By              ELEMENT_PERMISSION_TYPE_INPUT                              = By.id("PermissionOwner");

  public static final By              ELEMENT_PERMISSION_ADD_BUTTON                              = By.xpath("//*[text()='Add']");

  public static final By              ELEMENT_PERMISSION_SELECT_USER                             =
                                                                     By.xpath("//a[contains(@onclick, 'OpenSelectUserForm')]");

  public static final By              ELEMENT_PERMISSION_SELECT_GROUP                            = By.className("uiIconGroup");

  public static final By              ELEMENT_PERMISSION_SELECT_MEMBERSHIP                       =
                                                                           By.className("uiIconMembership");

  public static final By              ELEMENT_WIKI_PAGE_NOT_FOUND                                =
                                                                  By.xpath(".//*[@class='uiWikiPageNotFound']");

  public static final By              ELEMENT_MANAGER_PERMISSION_BTN                             =
                                                                     By.xpath(".//*[contains(@onclick,'ManagePermisisons')]");

  public static final By              ELEMENT_WIKI_LEFT_TREE_RESTRICTED_PAGE_TITLE               =
                                                                                   By.xpath(".//*[@id='iconTreeExplorer']//em[contains(text(),'restricted')]");

  public static final By              ELEMENT_WIKI_TOOLTIP_RESTRICTED_PAGE_TITLE                 =
                                                                                 By.xpath(".//*[@id='iconTreeExplorer']//em[contains(@data-original-title,'This page is restricted, you do not have permission to view it.')]");

  public static final By              ELEMENT_WIKI_PARENT_PAGE_UN_LINK                           =
                                                                       By.xpath(".//*[@id='iconTreeExplorer']//em[contains(@onclick,'')]");

  public static final By              ELEMENT_WIKI_SETTING_PERM_TAB                              =
                                                                    By.xpath("//*[contains(@href,'PermissionSetting')]");

  // *============================================================= WIKI SETTING
  // MANAGEMENT =======================================================*\\
  public static final By              ELEMENT_WIKI_SETTINGS_PERMISSION                           =
                                                                       By.xpath(".//*[@id='myTab']//*[contains(text(),'Permission')]");

  public static final By              ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_USER_ICON          =
                                                                                        By.xpath("//*[@class='uiIconUser uiIconLightGray']");

  public static final By              ELEMENT_WIKI_SETTINGS_PERMISSION_INPUT_SEARCH_USER_NAME    =
                                                                                              By.xpath(".//input[@id='Quick Search']");

  public static final String          ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_SEARCH_OPTION      =
                                                                                            "//*[contains(@name,'filter')]";

  public static final String          ELEMENT_WIKI_SETTINGS_PERMISSION_SEARCH_ICON               =
                                                                                   ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";

  public static final String          ELEMENT_WIKI_SETTINGS_PERMISSION_USER_NAME_IN_USER_LIST    =
                                                                                              ".//*[@id='UIListUsers']//*[contains(text(),'$userName')]";

  public static final By              ELEMENT_WIKI_SETTINGS_CLOSE_USER_SELETOR                   =
                                                                               By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");

  public static final By              ELEMENT_VIEW_PAGE_HISTORY                                  =
                                                                By.xpath("//*[@id='UIWikiPageInfo']/div[2]/div[2]/table/tbody/tr[2]/td/div/button");

  public static final SelenideElement ELEMENT_WIKI_UNPUT_LINK_EXISTED_PAGE                       =
                                                                           $(byXpath("//*[@id=\"isc_Class_S1398_0_Input\"]"));

  public static final SelenideElement ELEMENT_BUTTON_WIKI_RITCH_TEXT                             =
                                                                     $(byId("UIEditorTabs")).find(byText("Rich Text"));

  public static final SelenideElement ELEMENT_WIKI_CONTENT_PAGE                                  =
                                                                $(byId("UIViewContentDisplay"));

  public static final SelenideElement ELEMENT_WIKI_PAGE_CONTAINER                                = $(byId("UIWikiPageContainer"));

  public static final String          ELEMENT_WIKI_SETTING_PERMISSION_SEARCH_GROUP_USER_IN_TABLE =
                                                                                                 "//*[@id='UIListUsers']//span[contains(text(),'${username}')]";

  public static final SelenideElement ELEMENT_DELETE_LINK2                                       = $(byText("Delete Page"));

  public static final SelenideElement ELEMENT_POPUP_SELECT_WIKI_PAGE                             = $(byId("isc_2"));

  public static final SelenideElement ELEMENT_WIKI_PERMELINK                                     = $(byId("PermalinkText"));

  public static final SelenideElement ELEMENT_ALERT_MESSAGE                                      = $(byClassName("alert"));

  public static final SelenideElement ELEMENT_RESTRICTED_LINK                                    =
                                                              $(byId("UIWikiPageControlArea")).find(byXpath("//*[@id=\"UIWikiPageInfoArea\"]/div/a[5]"));

  public static final SelenideElement ELEMENT_ATTACHMENT_TOTAL_NUMBER                            =
                                                                      $(byId("UIWikiPageControlArea")).find(byXpath("//*[@id=\"UIWikiPageInfoArea\"]/div/a[6]"));

  public static final By              ELEMENT_CANCEL_WIKI_DELETE                                 =
                                                                 By.xpath(".//*[@id='UIWikiDeletePageConfirm']//button[text()='Cancel']");

  public static final SelenideElement ELEMENT_PAGE_INFO                                          =
                                                        $(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[3]/ul/li[6]/a[1]"));

  public static final String          ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED                         =
                                                                         ".//*[@class='listTable']//*[contains(text(),'$title')]";

  public static final SelenideElement ELEMENT_SELECT_DESTINATION                                 =
                                                                 $(byId("uiSpaceSwitcher_UIWikiMovePageForm"));

  public static final SelenideElement ELEMENT_POPUP_SELECT_DESTINATION                           =
                                                                       $(byClassName(("uiWikiMovePageForm")));

  // select HOW-TO Guide template
  public static final SelenideElement ELEMENT_SELECT_TEMPLATE_HowToGuide                         =
                                                                         $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[2]/td[1]/div/input"));

  // select Three-Column Layout template
  public static final SelenideElement ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout                  =
                                                                                $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[3]/td[1]/div/input"));

  // select Status Meeting template
  public static final SelenideElement ELEMENT_SELECT_TEMPLATE_StatusMeeting                      =
                                                                            $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[4]/td[1]/div/input"));

  // select Leave Planning template
  public static final SelenideElement ELEMENT_SELECT_TEMPLATE_LeavePlanning                      =
                                                                            $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[5]/td[1]/div/input"));

  // select Two-Column Layout template
  public static final SelenideElement ELEMENT_SELECT_TEMPLATE_TwoColumnLayout                    =
                                                                              $(byXpath("//*[@id=\"UIWikiTemplateGrid\"]/tbody/tr[6]/td[1]/div/input"));

  public static final SelenideElement ELEMENT_TREE_NAME_WIKI                                     =
                                                             $(byClassName("uiLeftContainerArea"));

  public static final SelenideElement ELEMENT_DRAFT_NEW_PAGE                                     = $(byId("UIWikiDraftGrid"));

  public static final SelenideElement ELEMENT_MORE_LINK_WITH_USER=$(byXpath("//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li[2]/div"));

  public static final SelenideElement ELEMENT_WIKI_BUTTON_ADD_MORE_TEMPLATE                      =
                                                                            $(byXpath("//*[@id=\"UIWikiSettingContainer_TemplateSetting_\"]/div[2]/button"));

  public static final SelenideElement ELEMENT_WIKI_OK_SAVE_TEMPLATE                              =
                                                                    $(byClassName("infoIcon")).parent()
                                                                                              .parent()
                                                                                              .parent()
                                                                                              .find(byClassName("btn"));

  public static final SelenideElement ELEMENT_WIKI_LISTE_TEMPLATE                                = $(byId("myTabContent"));

  public static final By              ELEMENT_WIKI_ICON_DELETE_TEMPLATE                          =
                                                                        byClassName("uiIconDeleteTemplate");

  public static final By              ELEMENT_WIKI_ICON_EDIT_TEMPLATE                            =
                                                                      byClassName("uiIconEditTemplate");

  public static final SelenideElement ELEMENT_WIKI_PREVIEW_TEMPLATE                              =
                                                                    $(byXpath("//*[@id=\"UIEditorTabs\"]/button[1]"));

  public static final By              ELEMENT_WIKI_ICON_DELETE_PERMISSION                        = byClassName("uiIconDelete");

  public static final SelenideElement ELEMENT_WIKI_ICON_EDIT_PERMISSION_FOR_MARY                 =
                                                                                 $(byText(DATA_NAME_USER2)).parent()
                                                                                                           .parent()
                                                                                                           .findAll(byClassName("uiCheckbox"))
                                                                                                           .get(1);

 

  public static final SelenideElement ELEMENT_BUTTON_OK_IN_WARNING_POPUB_TEMPLATE=$(byClassName("warningIcon")).parent().parent().parent().find(byClassName("btn"));

  public static final SelenideElement ELEMENT_PAGE_WIKI_CONTENT=$(byId("UIViewContentDisplay"));

}
