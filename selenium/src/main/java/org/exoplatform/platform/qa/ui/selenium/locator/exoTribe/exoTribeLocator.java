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
package org.exoplatform.platform.qa.ui.selenium.locator.exoTribe;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class exoTribeLocator {


    public static final SelenideElement ELEMENT_TRIBE_COMMUNITY_NAVIGATION_SIGN_IN= $(byXpath("(//*[@class='commnuityNavigation']//*[@class='btn btn-primary '])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_COMMUNITY_NAVIGATION_REGISTER= $(byXpath("(//*[@class='commnuityNavigation']//*[@class='btn btn-primary '])[2]"));

    public static final SelenideElement FIRSTNAME_TRIBE_REGISTER = $(By.xpath("//*[@id='firstName']"));

    public static final SelenideElement LASTNAME_TRIBE_REGISTER = $(By.xpath("//*[@id='lastName']"));

    public static final SelenideElement EMAIL_TRIBE_REGISTER = $(By.xpath("//*[@id='emailAddress']"));

    public static final SelenideElement PASSWORD_TRIBE_REGISTER = $(By.xpath("//*[@id='password']"));

    public static final SelenideElement USERNAME_TRIBE = $(By.xpath("//*[@id='username']"));

    public static final SelenideElement PASSWORD_TRIBE = $(byXpath("//*[@id='password']"));

    public static final SelenideElement USERNAME_ATLASSIAN = $(By.xpath("//*[@id='j_username']"));

    public static final SelenideElement PASSWORD_ATLASSIAN = $(By.xpath("//*[@id='j_password']"));

    public static final SelenideElement ELEMENT_ATLASSIAN_SIGN_IN = $(byXpath("//*[@value='Log in']"));

    public static final SelenideElement ELEMENT_ATLASSIAN_CONTINUE = $(byXpath("//*[text()='Continue']"));

    public static final SelenideElement ELEMENT_TRIBE_SIGN_IN = $(byXpath("//*[@class='btn btn-primary']"));

    public static final SelenideElement ELEMENT_TRIBE_SIGN_IN_WITH_GOOGLE = $(byXpath("//*[@id='googleBtn']"));

    public static final SelenideElement MAIL_TRIBE = $(byXpath("//*[@id='identifierId']"));

    public static final SelenideElement PASSWORD_MAIL_TRIBE = $(byXpath("//*[@type='password']"));

    public static final SelenideElement NEXT_MAIL_TRIBE = $(byXpath("//*[@id='identifierNext']"));

    public static final SelenideElement NEXT_PASSWORD_MAIL_TRIBE = $(byXpath("//*[@id='passwordNext']/span"));

    public static final SelenideElement ELEMENT_TRIBE_TOOLBAR = $(byXpath("//*[@class='UITableColumnContainer']"));

    public static final SelenideElement ELEMENT_TRIBE_SIGN_OUT = $(byXpath("//*[@class='uiIcon uiIconToolbarNavItem logoutIcon']"));

    public static final SelenideElement ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU = $(byXpath("//*[@class='HamburgerNavigationMenuLink']"));

    public static final SelenideElement ELEMENT_DW_ADMINISTRATION_PAGE = $(byXpath("//*[@class='uiIcon uiIconToolbarNavItem uiAdministrationIcon']"));

    public static final SelenideElement ELEMENT_TRIBE_STREAM_PAGE = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconStream iconStream null']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_PAGE = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconHome iconHome null']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[1]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_STREAM_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[2]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_SPACES_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[3]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_PEOPLE_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[4]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_LAST_VISITED_SPACES_CHECK_ORDER = $(byXpath("(//*[@class='flex']//i)[5]/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_RECENT_SPACES = $(byXpath("//*[@class='uiIcon uiArrowBackIcon']/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_FILTER_SPACES_SEARCH = $(byXpath("//input[@placeholder='Filter spaces here']"));

    public static final SelenideElement ELEMENT_TRIBE_SEARCH_SPACE = $(byXpath("//*[@class='uiIcon uiArrowBackIcon']/following::button[1]//i"));

    public static final SelenideElement ELEMENT_TRIBE_SETTINGS_CHECK_ORDER = $(byXpath("(//*[@class='flex']//i)[7]/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_LOGOUT_CHECK_ORDER = $(byXpath("(//*[@class='flex']//i)[8]/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_BRANDING_TOPBAR = $(byXpath("//*[@id='brandingTopBar']"));

    public static final SelenideElement ELEMENT_TRIBE_SEARCH_TOPBAR = $(byXpath("//*[@class='uiIconPLF24x24Search']"));

    public static final SelenideElement ELEMENT_TRIBE_MINICHAT_TOPBAR = $(byXpath("//*[@id='miniChatDrawer']"));

    public static final SelenideElement ELEMENT_TRIBE_APPLICATIONS_TOPBAR = $(byXpath("//*[@id='appLauncher']"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATIONS_TOPBAR = $(byXpath("//*[@id='NotificationPopoverPortlet']"));

    public static final SelenideElement ELEMENT_TRIBE_SIDEBAR_MENU_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[1]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_BRANDING_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[2]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SEARCH_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[3]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_MINICHAT_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[4]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_APPLICATIONS_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[5]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATIONS_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[6]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_PEOPLE_PAGE = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconPeople iconPeople null']"));

    public static final SelenideElement ELEMENT_CONNECTIONS_PAGE_FROM_HOME = $(byXpath("(//*[@class='v-card__text pa-1 subtitle-1 text-color'])[2]"));

    public static final SelenideElement ELEMENT_TRIBE_SETTINGS_PAGE = $(byXpath("//*[@class='uiIcon uiIconToolbarNavItem settingsIcon']"));

    public static final SelenideElement ELEMENT_TRIBE_TASKS_PAGE = $(byXpath("(//*[@class='caption color-title'])[1]"));

    //STREAM

    public static final SelenideElement ELEMENT_TRIBE_POST_ACTIVITY_BTN = $(byXpath("//*[@class='uiIconEdit']"));

    public static final By ELEMENT_TRIBE_POST_ACTIVITY_BUTTON = By.xpath("//*[@class='btn btn-primary ignore-vuetify-classes btnStyle']");

    public static final SelenideElement ELEMENT_DW_POST_ACTIVITY_BUTTON  = $(By.xpath("//*[@class='activityComposer']//*[@class='uiIconEdit']"));

    public static final SelenideElement ELEMENT_DW_ADD_FILE_BUTTON  = $(By.xpath("//*[@class='addFileComposerIcon']"));

    public static final SelenideElement ELEMENT_DW_UPLOAD_MANUALLY_BUTTON  = $(By.xpath("(//*[@class='uploadButton'])[1]"));

    public static final String ELEMENT_DW_CHECKING_ATTACHED_FILE  = "//*[@class='attachment']//*[@class='fileNameLabel' and contains(text(),'${file}')]";

    public static final SelenideElement ELEMENT_DW_APPLY_BUTTON  = $(By.xpath("//*[@class='btn btn-primary ignore-vuetify-classes']"));

    public static final String ELEMENT_DW_PREVIEW_UPLOADED_FILE  = "(//*[@class='previews Previews{id}'])[1]//*[@class='infoFile']";

    public static final String ELEMENT_DW_PREVIEW_HOVER_ON_UPLOADED_FILE  = "(//*[@class='previews Previews{id}'])[1]";

    public static final SelenideElement ELEMENT_DW_ATTACHED_FILE_PREVIEW_DROPDOWN  = $(byXpath("//*[@class='uiVerticalDots']"));

    public static final SelenideElement ELEMENT_DW_ATTACHED_FILE_PREVIEW_OPEN_IN_DOCUMENTS  = $(byXpath("//*[@class='openBtn']"));

    public static final SelenideElement ELEMENT_DW_ATTACHED_FILE_PREVIEW_DOWNLOAD  = $(byXpath("//*[@class='downloadBtn']"));

    public static final SelenideElement ELEMENT_DW_DOCUMENT_EDIT_IN_ONLYOFFICE  = $(byXpath("//*[@class='hidden-tabletL editorButton']"));

  //SPACE

    public static final SelenideElement GO_TO_SPACES_TRIBE_BTN = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconSpaces iconSpaces null']"));

    public static final SelenideElement ELEMENT_ADDNEWSPACE_TRIBE_BTN = $(byXpath("//*[@class='uiIconSocSimplePlus uiIconSocWhite']"));

    public static final SelenideElement ELEMENT_SPACENAME_TRIBE = $(byXpath("//*[@class='spaceMenuNavHeader']"));

    public static final String          ELEMENT_SPACE_TRIBE_CONFIRM_DELETE  = "Est-ce vous êtes certain de vouloir supprimer cet espace ? Ceci est définitif. Toutes les pages de ce groupe seront également supprimées.";

    public static final By              ELEMENT_TRIBE_DELETE_SPACE_OK_BUTTON = By.xpath("//*[text()='Valider']");

    public static final By              ELEMENT_TRIBE_MANAGE_SPACE_BUTTON = By.xpath("(//*[@class='spaceCardFront']//*[@class='v-btn__content'])[3]");

    public static final By              ELEMENT_TRIBE_REMOVE_SPACE_BUTTON = By.xpath("//*[@class='uiIcon uiIconTrash']");

    public static final By              ELEMENT_TRIBE_REMOVE_SPACE_OK_BUTTON = By.xpath("(//*[@class='v-card__actions']/button)[1]");

    public static final SelenideElement ELEMENT_NO_SPACES_LIST  = $(byXpath("//*[@class='ma-auto noSpacesYet']"));

    public static final By ELEMENT_ADDNEWSPACE_TRIBE_FORM  = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Créer un nouvel espace']");

    public static final SelenideElement ELEMENET_SPACE_CREATE_TRIBE_BUTTON = $(byClassName("PopupContent")).find(byText("Créer"));

    public static final SelenideElement ELEMENT_ADDNEWSPACE_SECOND_TRIBE_BUTTON  = $(byXpath("//*[@id='spacesListToolbar']//*[@class='d-none d-sm-inline']"));

    public static final By ELEMENT_ADDNEWSPACE_SECOND_TRIBE_FORM  = By.xpath("//*[@class='v-navigation-drawer__content']//*[contains(text(),'Add Space')]");

    public static final SelenideElement ELEMENT_SPACE_NAME_SECOND_TRIBE_INPUT      = $(byXpath("//*[@placeholder='Display name']"));

    public static final SelenideElement ELEMENT_SPACE_DESCRIPTION_SECOND_TRIBE_INPUT     = $(byXpath("//*[@placeholder='Description']"));

    public static final SelenideElement ELEMENT_SPACE_DETAILS_TRIBE = $(byXpath("//*[@class='v-stepper__label' and contains(text(),'Space details')]"));

    public static final SelenideElement ELEMENT_INVITE_USERS_TRIBE = $(byXpath("//*[@class='v-stepper__label' and contains(text(),'Invite users')]"));

    public static final SelenideElement ELEMENT_SPACE_ACCESS_TRIBE = $(byXpath("//*[@class='v-stepper__label' and contains(text(),'Space access')]"));

    public static final SelenideElement ELEMENT_SPACE_INPUT_USER_TRIBE = $(byXpath("//input[@content-class='identitySuggesterContent']"));

    public static final SelenideElement ELEMENT_CREATE_SPACE_TRIBE = $(byXpath("//*[@class='v-btn__content' and contains(text(),'Create')]"));

    public static final SelenideElement ELEMENT_CANCEL_SPACE_TRIBE = $(byXpath("//*[@class='v-btn__content' and contains(text(),'Cancel')]"));

    public static final SelenideElement ELEMENT_SPACES_TRIBE_SEARCH_TEXT = $(byXpath("(//*[@class='v-input__slot']//*[@class='v-text-field__slot']/input)[2]"));

    public static final SelenideElement ELEMENT_SEARCHED_SPACE_TRIBE   = $(byXpath("//*[@class='spaceDisplayName text-truncate d-block']"));

    public static final String ELEMENT_SPACE_SIDEBAR_ORDER = "((//*[@class='flex']//i)[5]/following::div[@role='listbox'][1]//div[@class='v-list-item__content']/div)[{id}]";

    public static final String ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER = "((//*[@class='flex filterSpaces']//i)[2]/following::div[@role='listbox'][1]//div[@class='v-list-item__content']/div)[{id}]";

    public static final String ELEMENT_SELECTED_LAST_VISITED_SPACE = "(//*[@class='flex filterSpaces']//i)[2]/following::div[@role='listbox'][1]//div[@class='v-list-item__content']/div[contains(text(),'${space}')]";

    public static final String ELEMENT_SPACE_WIKI_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/wiki']";

    public static final String ELEMENT_SPACE_TASKS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/tasks']";

    public static final String ELEMENT_SPACE_FORUM_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/forum']";

    public static final String ELEMENT_SPACE_MEMBERS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/members']";

    public static final String ELEMENT_SPACE_SETTINGS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/settings']";

    public static final String ELEMENT_HOME_SPACE_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}']";

    public static final String ELEMENT_SPACE_DOCUMENTS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/documents']";

    public static final String ELEMENT_SPACE_TABS_TOP_BAR_ORDER = "//*[@class='v-application--wrap']//*[@class='v-slide-group__wrapper']//*[@tabindex][{i}]";

  //WIKI

    public static final SelenideElement ELEMENT_SPACE_HOME_TAB_DW = $(byXpath("(//*[@class='spaceNavigationTab v-tab'])[1]"));

    public static final SelenideElement ELEMENT_ADD_PAGE_DW = $(byXpath("(//*[@class='dropdown uiActionWithLabel'])[1]"));

    public static final By ELEMENT_ADD_PAGE_TRIBE = By.xpath("//div[@data-toggle and text()='Ajouter une Page']");

    public static final By ELEMENT_DELETE_LINK_TRIBE = By.xpath(".//*[text()='Supprimer']");

    public static final By ELEMENT_DELETE_WIKI_PAGE_DW = By.xpath("(//*[@class='open']//ul//li)[5]");

    public static final By ELEMENT_CONFIRM_WIKI_PAGE_DELETE_DW = By.xpath("(//*[@id='UIWikiDeletePageConfirm']//button)[1 ]");

    public static final By ELEMENT_CONFIRM_WIKI_DELETE_TRIBE = By.xpath(".//*[@id='UIWikiDeletePageConfirm']//button[text()='Valider']");

    public static final By ELEMENT_CONTENT_WIKI_INPUT_TRIBE = By.xpath("(//*[@class='UIWikiEditorToolbar']/following::div)[1]");

    public static final By ELEMENT_CONTENT_WIKI_INPUT_DW = By.xpath("//*[@id='Markup']");

    public static final By ELEMENT_TEMPLATE_TRIBE_SELECT_BTN = By.xpath("//*[@id='UIWikiSelectTemplateForm']//*[text()='Sélectionner']");

    public static final By ELEMENT_TEMPLATE_DW_SELECT_BTN = By.xpath("//*[@class='btn selectTemplateAction']");

    public static final By ELEMENT_WIKI_TRIBE_MOVE_POPUP_SAVE = By.xpath("//*[@id='UIWikiMovePageForm']//button[@class='btn btn-primary']");

    public static final By ELEMENT_WIKI_TRIBE_COMPARE_VERSION_TITLE = By.xpath(".//h4[text()='Comparer les Versions']");

    public static final String ELEMENT_COMPARE_TRIBE_VERSION_NUMBER = ".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Version $num')]";

    public static final By ELEMENT_COMPARE_TRIBE_CURRENT_VERSION = By.xpath(".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Version courante')]");

    public static final By   ELEMENT_HOME_SPACE_TRIBE = By.xpath("//*[@class='uiIconAppSpaceHomePage uiIconDefaultApp']");

    public static final By   ELEMENT_HOME_SPACE_DW = By.xpath("//*[@class='uiSocApplication uiSpaceActivityStreamPortlet']");

    public static final By ELEMENT_TRIBE_INFO_ADD_MORE_RELATIONS = By.xpath(".//*[@id='UIWikiPageInfo']//button[text()='Associer une Page']");

    public static final By ELEMENT_ADD_RELATED_TRIBE_POPUP_DROPDOWN = By.xpath(".//*[contains(text(),'Associer une Page')]");

    public static final By ELEMENT_ADD_RELATED_TRIBE_SLECTION = By.xpath("//*[@class='PopupTitle popupTitle']/../..//*[@data-toggle='dropdown']");

    public static final String ELEMENT_ADD_RELATED_POPUP_TRIBE_LOCATION = "//*[@class='PopupTitle popupTitle']/../..//*[contains(text(),'${location}')]";

    public static final String ELEMENT_ADD_RELATED_POPUP_TRIBE_CONTENT = "//*[@class='PopupTitle popupTitle']/../..//*[contains(text(),'${page}')]";

    //TASK
    public static final By ELEMENT_POPUB_TRIBE_EDIT_PROJECT    =By.xpath("//*[@id='taskManagement']//*[@class='PopupContent popupContent']");

    public static final SelenideElement ELEMENT_ICON_ADD_IMAGE_IN_COMMENT_DW = $(byXpath("//*[@class='cke_button_icon cke_button__selectimage_icon']"));

    public static final SelenideElement ELEMENT_INPUT_COMMENT_TASK_DW  = $(byXpath("//*[@class='cke_wysiwyg_frame cke_reset']"));

  //PEOPLE
    public static final SelenideElement ELEMENT_PEOPLE_TRIBE_SEARCH_TEXT = $(byXpath("(//*[@class='v-text-field__slot']/input)[2]"));

    public static final SelenideElement ELEMENT_CONNECTION_CONFIRM_BTN_DW       = $(byXpath("(//*[@class='acceptToConnectButtonParent']//button)[1]"));

    public static final SelenideElement ELEMENT_CONNECTION_REFUSE_SHOW_BTN_DW       = $(byXpath("(//*[@class='acceptToConnectButtonParent']//button)[2]"));

    public static final SelenideElement ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW       = $(byXpath("//*[@class='uiIconSocCancelConnectUser peopleRelationshipIcon d-inline']"));

    public static final SelenideElement ELEMENT_MY_CONNECTIONS_BTN_DW       = $(byXpath("//*[@id='peopleListToolbar']//select"));

    public static final SelenideElement ELEMENT_NOTIFICATIONS_BTN_DW       = $(byXpath("//*[@id='NotificationPopoverPortlet']"));

    public static final String ELEMENT_ACCEPT_JOIN_SPACE_NOTIFICATION_BTN_DW       = "(//*[@class='media']//*[contains(text(),'${space}')]/following::*[@class='btn btn-mini btn-primary action-item'])[1]";

    public static final String ELEMENT_REFUSE_JOIN_SPACE_NOTIFICATION_BTN_DW       = "(//*[@class='media']//*[contains(text(),'${space}')]/following::*[@class='btn btn-mini cancel-item'])[1]";

    public static final SelenideElement  ELEMENT_NOTIFICATION_DROPDOWN_DW   =   $(byXpath("//*[@class='notifDrawerItems']"));

    public static final SelenideElement ELEMENT_CLOSE_NOTIFICATIONS_DW       = $(byXpath("//*[@class='uiCloseIcon notifDrawerClose']"));

    public static final SelenideElement  ELEMENT_NOTIFICATION_TITLE_DW   =   $(byXpath("//*[@class='notifDrawerTitle']"));

    public static final SelenideElement  ELEMENT_NOTIFICATION_SETTINGS_DW   =   $(byXpath("//*[@class='notifDrawerTitle']/following::div[1]//i[1]"));

    public static final SelenideElement  ELEMENT_SEE_ALL_NOTIFICATIONS   =  $(byXpath("//*[@class='notifDrawerItems']/following::button[2]"));

    public static final SelenideElement  ELEMENT_MARK_AS_READ   =  $(byXpath("//*[@class='notifDrawerItems']/following::button[1]"));

    public static final SelenideElement  ELEMENT_NOTIFICATION_ACTIONS_DW   =  $(byXpath("//*[@id='UIIntranetNotificationsPortlet']//*[@class='notificationsActions']"));

    public static final SelenideElement  ELEMENT_ALL_NOTIFICATIONS_DISPLAYED_DW   =  $(byXpath("//*[@id='UIIntranetNotificationsPortlet']//*[@class='displayItems']"));

    public static final SelenideElement  ELEMENT_DELETE_FIRST_NOTIFICATION_DW   =  $(byXpath("(//*[@class='uiIconClose uiIconLightGray'])[1]"));

    public static final SelenideElement  ELEMENT_KUDOS_MESSAGE_DW   =  $(byXpath("//*[@id='kudosMessage']"));

    public static final SelenideElement  ELEMENT_SEND_KUDOS_BTN_DW   =  $(byXpath("(//*[@class='v-card__actions']//button)[1]"));

    public static final SelenideElement  ELEMENT_CLOSE_SEND_KUDOS_BTN_DW   =  $(byXpath("(//*[@class='v-card__actions']//button)[2]"));


  //CHAT
    public static final SelenideElement  ELEMENT_CHAT_CONTACT_LINK_DW   =  $(byXpath("(//*[@class='chat-contact']/div)[1]"));

    public static final SelenideElement  ELEMENT_CHAT_DISCUSSIONS_FILTER_DW   =  $(byXpath("//*[@class='searchDrawer']/following::button[2]"));

    public static final SelenideElement  ELEMENT_CLOSE_CHAT_DRAWER_DW   =  $(byXpath("//*[@class='searchDrawer']/following::button[4]"));

    public static final SelenideElement  ELEMENT_OPEN_CHAT_BUTTON_DW   =  $(byXpath("//*[@class='searchDrawer']/following::button[3]"));

    public static final SelenideElement  ELEMENT_CHAT_CONTACT_LIST_DW   =  $(byXpath("//*[@id='chat-users']"));

    public static final String  ELEMENT_CHAT_CONTACT_DW   =  "//*[@title='${title}']//*[@class='chat-contact']";

    public static final SelenideElement  ELEMENT_CHAT_EMOTICON_DW   =  $(byXpath("//*[@class='action-emoji']//i"));

    public static final SelenideElement  ELEMENT_CHAT_APPS_DW   =  $(byXpath("//*[@class='action-apps']//i"));

    public static final SelenideElement  ELEMENT_CHAT_MESSAGE_AREA_DW   =  $(byXpath("//*[@id='messageComposerArea']"));

    public static final SelenideElement  ELEMENT_CHAT_SEND_BUTTON_DW   =  $(byXpath("//*[@class='action-send']//i"));

    public static final SelenideElement  ELEMENT_BACK_TO_CHAT_CONTACT_LIST_DW   =  $(byXpath("//*[@class='leftHeaderDrawer']//button"));

    public static final SelenideElement  ELEMENT_OPEN_CHAT_CONTACT_BUTTON_DW   =  $(byXpath("//*[@class='leftHeaderDrawer']/following::button[3]"));

    public static final SelenideElement  ELEMENT_CLOSE_CHAT_CONTACT_BUTTON_DW   =  $(byXpath("//*[@class='leftHeaderDrawer']/following::button[4]"));

    public static final SelenideElement  ELEMENT_CHAT_PAGE_DISPLAYED_DW   =  $(byXpath("//*[@id='chat-application']"));

  //SETTINGS
    public static final SelenideElement ELEMENT_TRIBE_EDIT_LANGUAGE = $(byXpath("(//*[@class='uiIconEdit uiIconLightBlue pb-2'])[1]"));

    public static final SelenideElement  ELEMENT_SETTINGS_APP_TAB_DW  = $(byXpath("(//*[contains(text(),'Applications')]/following::*[@class='v-btn__content'])[last()]"));

    public static final SelenideElement  ELEMENT_APPLICATION_TAB_ADD_APPLICATION_DW  = $(byXpath("(//*[@id='SpaceSettings']//*[@class='v-btn__content'])[last()-1]"));

    public static final SelenideElement  ELEMENT_REMOVE_APPLICATION_DW  = $(byXpath("(//*[@class='v-list-item__title'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_EDIT_TIME_ZONE = $(byXpath("(//*[@class='uiIconEdit uiIconLightBlue pb-2'])[2]"));

    public static final SelenideElement ELEMENT_TRIBE_EDIT_PASSWORD = $(byXpath("(//*[@class='v-list-item__action']/button)[4]"));

    public static final SelenideElement ELEMENT_TRIBE_OLD_PASSWORD = $(byXpath("(//input[@type='password'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_NEW_PASSWORD = $(byXpath("(//input[@type='password'])[2]"));

    public static final SelenideElement ELEMENT_TRIBE_NEW_PASSWORD_CONFIRM = $(byXpath("(//input[@type='password'])[3]"));

    public static final SelenideElement ELEMENT_TRIBE_EDIT_NOTIFICATIONS_GENERAL = $(byXpath("(//*[@class='uiIconEdit uiIconLightBlue pb-2'])[1]"));

    public static final String          ELEMENT_TRIBE_CHANGE_LANGUAGE     = "//*[contains(text(),'${language}')]";

    public static final String          ELEMENT_TRIBE_CHANGE_TIMEZONE    = "//*[contains(text(),'${timeZone}')]";

    public static final SelenideElement ELEMENT_TRIBE_APPLY_CHANGE_LANGUAGE_BUTTON = $(byXpath("(//button[@class='btn btn-primary v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_CANCEL_CHANGE_LANGUAGE_BUTTON = $(byXpath("(//button[@class='btn mr-2 v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_CANCEL_CHANGE_TIMEZONE_BUTTON = $(byXpath("(//button[@class='btn mr-2 v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[2]"));

    public static final SelenideElement ELEMENT_TRIBE_APPLY_CHANGE_TIMEZONE_BUTTON = $(byXpath("(//button[@class='btn btn-primary v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[2]"));

    public static final SelenideElement ELEMENT_TRIBE_CANCEL_EDIT_PASSWORD = $(byXpath("(//input[@type='password'])[3]/following::*[@class='v-btn__content'][1]"));

    public static final SelenideElement ELEMENT_TRIBE_CONFIRM_EDIT_PASSWORD = $(byXpath("(//input[@type='password'])[3]/following::*[@class='v-btn__content'][2]"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL_STATUS = $(byXpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input']//input)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATION_ON_SITE_STATUS = $(byXpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input']//input)[2]"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE_STATUS = $(byXpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input']//input)[3]"));

    public static final By ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL = By.xpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'])[1]");

    public static final By ELEMENT_TRIBE_NOTIFICATION_ON_SITE = By.xpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'])[2]");

    public static final By ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE = By.xpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'])[3]");

    public static final By ELEMENT_TRIBE_GENERAL_NOTIFICATION_ON_MOBILE = By.xpath("(//*[@class='v-input--selection-controls__input'])[2]");

    public static final By ELEMENT_TRIBE_GENERAL_NOTIFICATION_VIA_MAIL = By.xpath("(//*[@class='v-input--selection-controls__input'])[1]");

    public static final By ELEMENT_TRIBE_GENERAL_NOTIFICATION_ON_SITE = By.xpath("(//*[@class='v-input--selection-controls__input'])[3]");

    public static final SelenideElement ELEMENT_TRIBE_APPLY_EDIT_GENERAL_NOTIFICATIONS_BUTTON = $(byXpath("(//button[@class='btn btn-primary v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_CANCEL_EDIT_GENERAL_NOTIFICATIONS_BUTTON = $(byXpath("(//button[@class='btn mr-2 v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE = $(By.xpath("//select[@name='EMAIL_DIGEST']"));

    public static final SelenideElement ELEMENT_TRIBE_MANAGE_NOTIFICATIONS = $(By.xpath("(//*[@class='v-list-item__action'])[6]//button"));

    public static final SelenideElement ELEMENT_DIGITALWORKPLACE_MANAGE_NOTIFICATIONS = $(By.xpath("(//*[@class='v-list-item__action'])[7]//button"));


}



