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

    public static final SelenideElement ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU = $(byId("app"));

    public static final SelenideElement ELEMENT_TRIBE_SIDEBAR_MENU_PROFILE_SECTION = $(byXpath("//*[@id='ProfileHamburgerNavigation']"));

    public static final SelenideElement ELEMENT_TRIBE_SIDEBAR_MENU_PROFILE_AVATAR = $(byXpath("//*[@id='ProfileHamburgerNavigation']//*[@class='v-responsive__content']"));

    public static final SelenideElement ELEMENT_TRIBE_SIDEBAR_MENU_PROFILE_FIRST_LAST_NAME = $(byXpath("(//*[@id='ProfileHamburgerNavigation']//*[@class='v-responsive__content']/following::div/div)[1]"));

    public static final SelenideElement ELEMENT_DW_ADMINISTRATION_PAGE = $(byXpath("//*[@class='uiIcon uiIconToolbarNavItem uiAdministrationIcon']"));

    public static final SelenideElement ELEMENT_TRIBE_STREAM_PAGE = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconStream iconStream null']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_PAGE = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconHome iconHome null']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[1]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_HOME_ICON = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[1]/following::div[1]//div/following::span[@class='UserPageHome'][1]"));

    public static final SelenideElement ELEMENT_TRIBE_STREAM_HOME_ICON = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[1]/following::div[1]//div/following::span[@class='UserPageHome'][2]"));

    public static final SelenideElement ELEMENT_TRIBE_SPACES_HOME_ICON = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[1]/following::div[1]//div/following::span[@class='UserPageHome'][3]"));

    public static final SelenideElement ELEMENT_TRIBE_PEOPLE_HOME_ICON = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[1]/following::div[1]//div/following::span[@class='UserPageHome'][4]"));

    public static final SelenideElement ELEMENT_TRIBE_CONFIRM_DEFAULT_PAGE_CHANGE_BTN = $(byXpath("//*[@class='v-card__actions']//button[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER = $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex xs6 d-flex justify-center align-center']//span)[2]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS = $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex xs6 d-flex justify-center align-center']//span)[7]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_CHART_USER_POINTS = $(byXpath("//*[@id='echartUserPoints']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS_TITLE = $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex xs6 d-flex justify-center align-center']//span)[8]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER_TITLE = $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex xs6 d-flex justify-center align-center']//span)[6]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_CONTACTS_NUMBER= $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex d-flex xs6 justify-center align-center']//span)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_CONTACTS_NUMBER_TITLE = $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex d-flex xs6 justify-center align-center']//span)[6]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK= $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex d-flex xs6 justify-center align-center']//span)[7]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK_TITLE= $(byXpath("(//*[@id='profile-stats-portlet']//*[@class='flex d-flex xs6 justify-center align-center']//span)[8]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_RANK_SECTION_TITLE= $(byXpath("(//*[@class='flex d-flex xs12 mt-n2 justify-center']//span)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_PODIUM_SECTION_TITLE= $(byXpath("//*[@class='layout podium-layout row wrap mx-0']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_FIRST_PODIUM_NAME= $(byXpath("(//*[@class='transparent mx-1 align-center'])[2]/a"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SECOND_PODIUM_NAME= $(byXpath("(//*[@class='transparent mx-1 align-center'])[1]/a"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_THIRD_PODIUM_NAME= $(byXpath("(//*[@class='transparent mx-1 align-center'])[3]/a"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_FIRST_PODIUM_POINTS= $(byXpath("(//*[@class='transparent mx-1 align-center'])[2]/a/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SECOND_PODIUM_POINTS= $(byXpath("(//*[@class='transparent mx-1 align-center'])[1]/a/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_THIRD_PODIUM_POINTS= $(byXpath("(//*[@class='transparent mx-1 align-center'])[3]/a/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_FOURTH_PODIUM_NAME= $(byXpath("(//*[@class='flex xs12']//*[@class='v-list-item__title body-2'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_FIFTH_PODIUM_NAME= $(byXpath("(//*[@class='flex xs12']//*[@class='v-list-item__title body-2'])[2]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SIXTH_PODIUM_NAME= $(byXpath("(//*[@class='flex xs12']//*[@class='v-list-item__title body-2'])[3]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_FOURTH_PODIUM_POINTS= $(byXpath("(//*[@class='flex xs12']//*[@class='v-list-item__title body-2']/following::div/span)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_FIFTH_PODIUM_POINTS= $(byXpath("(//*[@class='flex xs12']//*[@class='v-list-item__title body-2']/following::div/span)[3]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SIXTH_PODIUM_POINTS= $(byXpath("(//*[@class='flex xs12']//*[@class='v-list-item__title body-2']/following::div/span)[5]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_WALLET_TITLE= $(byXpath("(//*[@class='flex d-flex xs12']/div/div/span)[2]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_WALLET_SECTION= $(byXpath("//*[@id='walletBalancePortlet']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_PERKSTORE_SECTION= $(byXpath("//*[@id='perkstoreOrderPortlet']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_MY_ORDERS_TITLE= $(byXpath("(//*[@class='flex d-flex xs12']/div/div/span)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_WALLET_VALUE= $(byXpath("//*[@class='flex d-flex xs12 justify-center']//a"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_MY_ORDERS_VALUE= $(byXpath("//*[@class='flex d-flex xs12 justify-center pa-2']//a"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_TASK_TITLE= $(byXpath("(//*[@id='tasks']//*[@class='taskTitle'])[last()]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_LAST_NEWS= $(byXpath("//*[@id='latestNewsDetails']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_USER_STATS= $(byXpath("//*[@id='profile-stats-portlet']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SPACES= $(byXpath("//*[@id='rightbottom-spaces-container']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_TASKS= $(byXpath("//*[@id='tasks']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SCROLL_TO_RIGHT_IN_DOCUMENTS_WIDGET= $(byXpath("//*[@class='v-slide-group__next']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SCROLL_TO_LEFT_IN_DOCUMENTS_WIDGET= $(byXpath("//*[@class='v-slide-group__prev']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK= $(byXpath("(//*[@id='DocumentsContainer']//*[@class='tabsTitle'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_FAVORITE= $(byXpath("(//*[@id='DocumentsContainer']//*[@class='tabsTitle'])[2]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_SHARED_WITH_ME= $(byXpath("(//*[@id='DocumentsContainer']//*[@class='tabsTitle'])[3]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_EXPLORE= $(byXpath("(//*[@id='DocumentsContainer']//*[@class='tabsTitle'])[4]"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS= $(byXpath("//*[@id='DocumentsContainer']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_SLIDER= $(byXpath("//*[@id='SliderContainer']"));

    public static final SelenideElement ELEMENT_TRIBE_STREAM_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[2]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_SPACES_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[3]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_PEOPLE_CHECK_ORDER = $(byXpath("(//*[@id='SiteHamburgerNavigation']//i)[4]/following::div[1]//div"));

    public static final SelenideElement ELEMENT_TRIBE_LAST_VISITED_SPACES_CHECK_ORDER = $(byXpath("(//*[@class='flex']//i)[5]/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_RECENT_SPACES = $(byXpath("//*[@class='uiIcon uiArrowBackIcon']/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_FILTER_SPACES_SEARCH = $(byXpath("//input[@placeholder='Filter spaces here']"));

    public static final SelenideElement ELEMENT_TRIBE_SEARCH_SPACE = $(byXpath("//*[@class='uiIcon uiArrowBackIcon']/following::button[1]//i"));

    public static final SelenideElement ELEMENT_TRIBE_SETTINGS_CHECK_ORDER = $(byXpath("(//*[@class='flex']//i)[7]/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SETTINGS_DW_CHECK_ORDER = $(byXpath("(//*[@id='UserHamburgerNavigation']//div)[1]//div[2]"));

    public static final SelenideElement ELEMENT_TRIBE_LOGOUT_DW_CHECK_ORDER = $(byXpath("(//*[@id='UserHamburgerNavigation']//div)[4]//div[2]"));

    public static final SelenideElement ELEMENT_TRIBE_LOGOUT_CHECK_ORDER = $(byXpath("(//*[@class='flex']//i)[8]/following::div[1]"));

    public static final SelenideElement ELEMENT_TRIBE_BRANDING_TOPBAR = $(byXpath("//*[@id='brandingTopBar']"));

    public static final SelenideElement ELEMENT_TRIBE_SEARCH_TOPBAR = $(byXpath("//*[@class='uiIconPLF24x24Search']"));

    public static final SelenideElement ELEMENT_TRIBE_MINICHAT_TOPBAR = $(byXpath("//*[@id='miniChatDrawer']"));

    public static final SelenideElement ELEMENT_TRIBE_EDIT_ADMINISTRATION_TOPBAR = $(byXpath("//*[@id='DrawerEditAdministration']"));

    public static final SelenideElement ELEMENT_TRIBE_APPLICATIONS_TOPBAR = $(byXpath("//*[@id='appLauncher']"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATIONS_TOPBAR = $(byXpath("//*[@id='NotificationPopoverPortlet']"));

    public static final SelenideElement ELEMENT_TRIBE_SIDEBAR_MENU_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp '])[1]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_BRANDING_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[1]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_SEARCH_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[2]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_MINICHAT_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[3]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_APPLICATIONS_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[4]//div)[1]"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATIONS_CHECK_ORDER = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[5]//div)[1]"));

  public static final SelenideElement ELEMENT_TRIBE_SIDEBAR_MENU_CHECK_ORDER_DW = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[1]//div)[1]"));

  public static final SelenideElement ELEMENT_TRIBE_BRANDING_CHECK_ORDER_DW = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[2]//div)[1]"));

  public static final SelenideElement ELEMENT_TRIBE_SEARCH_CHECK_ORDER_DW = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[3]//div)[1]"));

  public static final SelenideElement ELEMENT_TRIBE_MINICHAT_CHECK_ORDER_DW = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[4]//div)[1]"));

  public static final SelenideElement ELEMENT_TRIBE_APPLICATIONS_CHECK_ORDER_DW = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[5]//div)[1]"));

  public static final SelenideElement ELEMENT_TRIBE_NOTIFICATIONS_CHECK_ORDER_DW = $(byXpath("((//*[@id='UITopBarContainer']//*[@class='VuetifyApp'])[6]//div)[1]"));

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

    public static final SelenideElement ELEMENT_EDIT_SPACE_BANNER_TRIBE_BUTTON  = $(byXpath("//*[@class='v-input__prepend-outer']//button"));

    public static final SelenideElement ELEMENT_SPACE_BANNER_TRIBE = $(byXpath("//*[@id='SpaceHeader']//*[@class='v-responsive__content']"));

    public static final By ELEMENT_ADDNEWSPACE_SECOND_TRIBE_FORM  = By.xpath("(//*[@id='spacesListToolbar']//button)[1]");

    public static final SelenideElement ELEMENT_SPACE_NAME_SECOND_TRIBE_INPUT      = $(byXpath("//form/input[@placeholder]"));

    public static final SelenideElement ELEMENT_SPACE_DESCRIPTION_SECOND_TRIBE_INPUT     = $(byXpath("//form/textarea[@placeholder]"));

    public static final SelenideElement ELEMENT_SPACE_DETAILS_TRIBE = $(byXpath("//*[@class='v-stepper__label' and contains(text(),\"Détails de l'espace\")]"));

    public static final SelenideElement ELEMENT_INVITE_USERS_TRIBE = $(byXpath("//*[@class='v-stepper__label' and contains(text(),'Inviter des utilisateurs')]"));

    public static final SelenideElement ELEMENT_INVITE_USERS_SPACE_MEMBERS_TRIBE = $(byXpath("//*[@class='v-list-item__content drawerTitle align-start text-header-title text-truncate' and contains(text(),'Inviter des utilisateurs')]"));

    public static final SelenideElement ELEMENT_INVITE_USERS_SPACE_MEMBERS_DW = $(byXpath("//*[@class='v-list-item__content drawerTitle align-start text-header-title text-truncate' and contains(text(),'Invite users')]"));

    public static final SelenideElement ELEMENT_SPACE_ACCESS_TRIBE = $(byXpath("//*[@class='v-stepper__label' and contains(text(),\"Accès à l'espace\")]"));

    public static final SelenideElement ELEMENT_SPACE_DETAILS_DW = $(byXpath("//*[@class='v-stepper__label' and contains(text(),'Space details')]"));

    public static final SelenideElement ELEMENT_INVITE_USERS_DW = $(byXpath("//*[@class='v-stepper__label' and contains(text(),'Invite users')]"));

    public static final SelenideElement ELEMENT_SPACE_ACCESS_DW = $(byXpath("//*[@class='v-stepper__label' and contains(text(),'Space access')]"));

    public static final SelenideElement ELEMENT_SPACE_INPUT_USER_TRIBE = $(byXpath("//input[@content-class='identitySuggesterContent']"));

    public static final SelenideElement ELEMENT_CREATE_SPACE_TRIBE = $(byXpath("//*[@class='d-flex']//button[2]/span"));

    public static final SelenideElement ELEMENT_INVITE_USER_SPACE_MEMBERS_TRIBE_BTN = $(byXpath("//*[@class='d-flex']//button[2]/span"));

    public static final SelenideElement ELEMENT_SPACES_PULLDOWN_FILTER_DW     = $(byXpath("//*[@class='v-text-field__slot']/following::select[1]"));

    public static final String ELEMENT_FULL_SPACES_NUMBER_DW     = "//*[@id='spacesListBody']//*[@class='pa-0 col-md-6 col-lg-4 col-xl-3 col-12'][{i}]";

    public static final SelenideElement ELEMENT_LOAD_MORE_SPACES_BTN_DW     = $(byXpath("//*[@id='spacesListFooter']//button"));

    public static final String ELEMENT_SPACE_STATUS_DW     = "(//*[@class='spaceAvatar']/following::a[contains(text(),'${space}')]/following::span[@class='spaceMembershipButtonText d-inline'])[1]";

    public static final String ELEMENT_SPACE_STATUS_BEFORE_ACCEPTING_INVITATION_DW     = "//*[@class='spaceDisplayName text-truncate d-block' and contains(text(),'${space}')]/following::*[@class='v-card__actions spaceCardActions']//*[@class='d-flex']";

    public static final SelenideElement ELEMENT_CANCEL_SPACE_TRIBE = $(byXpath("(//*[@class='d-flex']/button)[1]"));

    public static final SelenideElement ELEMENT_SPACES_TRIBE_SEARCH_TEXT = $(byXpath("(//*[@class='v-input__slot']//*[@class='v-text-field__slot']/input)[2]"));

    public static final SelenideElement ELEMENT_SEARCHED_SPACE_TRIBE   = $(byXpath("//*[@class='spaceDisplayName text-truncate d-block']"));

    public static final SelenideElement ELEMENT_JOIN_SPACE_DW   = $(byXpath("//*[@class='v-card__actions spaceCardActions']//button"));

    public static final String ELEMENT_SPACE_SIDEBAR_ORDER = "((//*[@class='flex']//i)[5]/following::div[@role='listbox'][1]//div[@class='v-list-item__content']/div)[{id}]";

    public static final String ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER = "((//*[@class='flex filterSpaces']//i)[2]/following::div[@role='listbox'][1]//div[@class='v-list-item__content']/div)[{id}]";

    public static final String ELEMENT_SELECTED_LAST_VISITED_SPACE = "(//*[@class='flex filterSpaces']//i)[2]/following::div[@role='listbox'][1]//div[@class='v-list-item__content']/div[contains(text(),'${space}')]";

    public static final String ELEMENT_SPACE_WIKI_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/wiki']";

    public static final String ELEMENT_SPACE_TASKS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/tasks']";

    public static final String ELEMENT_SPACE_FORUM_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/forum']";

    public static final String ELEMENT_SPACE_CALENDAR_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/calendar']";

    public static final String ELEMENT_SPACE_MEMBERS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/members']";

    public static final String ELEMENT_SPACE_SETTINGS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/settings']";

    public static final String ELEMENT_HOME_SPACE_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}']";

    public static final String ELEMENT_SPACE_DOCUMENTS_TAB_TOP_BAR = "//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/documents']";

    public static final String ELEMENT_SPACE_TABS_TOP_BAR_ORDER = "//*[@class='v-application--wrap']//*[@class='v-slide-group__wrapper']//*[@tabindex][{i}]";

    public static final SelenideElement ELEMENT_ACCEPT_JOIN_SPACE=  $(byXpath("(//*[@class='acceptToJoinSpaceButtonParent']//button)[1]"));

  //WIKI

    public static final SelenideElement ELEMENT_SPACE_HOME_TAB_DW = $(byXpath("(//*[@class='spaceNavigationTab v-tab'])[1]"));

    public static final SelenideElement ELEMENT_ADD_PAGE_DW = $(byXpath("(//*[@class='dropdown uiActionWithLabel'])[1]"));

    public static final By ELEMENT_ADD_PAGE_TRIBE = By.xpath("//div[@data-toggle and text()='Add Page']");

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

    public static final By   ELEMENT_HOME_SPACE_DW = By.xpath("//*[@id='UISpaceActivityStreamPortlet']");

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

    public static final SelenideElement ELEMENT_SPACE_INVITATION_NUMBER_BTN_DW       = $(byXpath("(//*[@id='SpacesOverview']/div/div/div/div)[1]//div[@class='spacesOverviewCount text-center pb-1']"));

    public static final SelenideElement ELEMENT_SPACE_SENT_REQUESTS_NUMBER_BTN_DW       = $(byXpath("(//*[@id='SpacesOverview']/div/div/div/div)[2]//div[@class='spacesOverviewCount text-center pb-1']"));

    public static final SelenideElement ELEMENT_SPACE_MEMBERS_SEARCH_TEXT = $(byXpath("(//*[@class='v-text-field__slot']/input)[2]"));

    public static final SelenideElement ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW          = $(byXpath("(//*[@id='peopleListToolbar']//div)[3]"));

    public static final SelenideElement ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW          = $(byXpath("//*[@class='v-text-field__slot']/following::select[1]"));

    public static final SelenideElement ELEMENT_INVITE_USER_SPACE_MEMBERS_DW          = $(byXpath("//*[@id='peopleListToolbar']//*[@class='d-none d-sm-inline']"));

    public static final String ELEMENT_ACCEPT_JOIN_SPACE_DW       = "//*[@class='drawerParent v-navigation-drawer v-navigation-drawer--absolute v-navigation-drawer--open v-navigation-drawer--right v-navigation-drawer--temporary theme--light']//*[@class='v-list-item__title']//a[contains(text(),'${space}')]/following::button[1]";

    public static final String ELEMENT_REFUSE_JOIN_SPACE_DW       = "//*[@class='drawerParent v-navigation-drawer v-navigation-drawer--absolute v-navigation-drawer--open v-navigation-drawer--right v-navigation-drawer--temporary theme--light']//*[@class='v-list-item__title']//a[contains(text(),'${space}')]/following::button[2]";

    public static final String ELEMENT_REMOVE_SPACE_SENT_REQUEST_DW       = "//*[@class='drawerParent v-navigation-drawer v-navigation-drawer--absolute v-navigation-drawer--open v-navigation-drawer--right v-navigation-drawer--temporary theme--light']//*[@class='v-list-item__title']//a[contains(text(),'${space}')]/following::button[1]";

    public static final SelenideElement ELEMENT_CLOSE_SPACES_INVITATIONS_DRAWER_DW       = $(byXpath("(//*[@class='drawerParent v-navigation-drawer v-navigation-drawer--absolute v-navigation-drawer--open v-navigation-drawer--right v-navigation-drawer--temporary theme--light']//button)[1]"));

    public static final SelenideElement ELEMENT_FIRST_SUGGESTION_DW       = $(byXpath("(//*[@class='v-list-item__content pb-3']//a)[1]"));

    public static final SelenideElement ELEMENT_SENT_REQUESTS_BTN_DW       = $(byXpath("//*[@id='PeopleOverview']/div/div/div/div[2]"));

    public static final String ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW       = "(//*[@class='v-list-item__title']//*[contains(text(),'${space}')]/following::div/button//span//span)[1]";

    public static final SelenideElement ELEMENT_CLOSE_SENT_REQUESTS_BTN_DW       = $(byXpath("//*[@class='v-list-item__action drawerIcons align-end d-flex flex-row']/button"));

    public static final String ELEMENT_SENT_REQUESTS_USERS_DW       = "//*[@class='layout column']//*[@class='v-list-item__title']//a[contains(text(),'${user}')]";

    public static final String ELEMENT_DELETE_SENT_REQUESTS_USERS_DW       = "//*[@class='layout column']//*[@class='v-list-item__title']//a[contains(text(),'${user}')]/following::button[1]";

    public static final SelenideElement ELEMENT_SECOND_SUGGESTION_DW       = $(byXpath("(//*[@class='v-list-item__content pb-3']//a)[2]"));

    public static final SelenideElement ELEMENT_ADD_FIRST_USER_SUGGESTION_DW       = $(byXpath("(//*[@class='uiIconInviteUser'])[1]"));

    public static final SelenideElement ELEMENT_ADD_SECOND_USER_SUGGESTION_DW       = $(byXpath("(//*[@class='uiIconInviteUser'])[2]"));

    public static final SelenideElement ELEMENT_ADD_FIRST_SPACE_SUGGESTION_DW       = $(byXpath("(//*[@class='uiIconPlusLight'])[1]"));

    public static final SelenideElement ELEMENT_ADD_SECOND_SPACE_SUGGESTION_DW       = $(byXpath("(//*[@class='uiIconPlusLight'])[2]"));

    public static final SelenideElement ELEMENT_DELETE_FIRST_SUGGESTION_DW       = $(byXpath("(//*[@class='uiIconCloseCircled'])[1]"));

    public static final SelenideElement ELEMENT_DELETE_SECOND_SUGGESTION_DW       = $(byXpath("(//*[@class='uiIconCloseCircled'])[2]"));

    public static final SelenideElement ELEMENT_LEADER_BOARD_TITLE_DW       = $(byXpath("//*[@id='usersLeaderboard']//*[@class='d-inline-block']"));

    public static final SelenideElement ELEMENT_CURRENT_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("//*[@class='v-avatar tertiary']/span"));

    public static final String ELEMENT_CURRENT_USER_LEADER_BOARD_POINTS_DW       = "(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[{id}]";

    public static final SelenideElement ELEMENT_FIRST_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[1]"));

    public static final SelenideElement ELEMENT_SECOND_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[2]"));

    public static final SelenideElement ELEMENT_THIRD_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[3]"));

    public static final SelenideElement ELEMENT_FOURTH_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[4]"));

    public static final SelenideElement ELEMENT_FIFTH_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[5]"));

    public static final SelenideElement ELEMENT_SIXTH_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[6]"));

    public static final SelenideElement ELEMENT_SEVENTH_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[7]"));

    public static final SelenideElement ELEMENT_EIGHTH_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[8]"));

    public static final SelenideElement ELEMENT_NINTH_USER_LEADER_BOARD_POSITION_DW       = $(byXpath("(//*[@class='v-list-item__title']//a)[9]"));

    public static final SelenideElement ELEMENT_FIRST_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[1]"));

    public static final SelenideElement ELEMENT_SECOND_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[2]"));

    public static final SelenideElement ELEMENT_THIRD_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[3]"));

    public static final SelenideElement ELEMENT_FOURTH_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[4]"));

    public static final SelenideElement ELEMENT_FIFTH_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[5]"));

    public static final SelenideElement ELEMENT_SIXTH_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[6]"));

    public static final SelenideElement ELEMENT_SEVENTH_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[7]"));

    public static final SelenideElement ELEMENT_EIGHTH_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[8]"));

    public static final SelenideElement ELEMENT_NINTH_USER_LEADER_BOARD_POINTS_DW       = $(byXpath("(//*[@class='v-list-item__title']//a/following::*[@class='v-list-item__action mr-4'])[9]"));

    public static final SelenideElement ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW       = $(byXpath("//*[@class='uiIconSocCancelConnectUser peopleRelationshipIcon d-inline']"));

    public static final SelenideElement ELEMENT_MY_CONNECTIONS_BTN_DW       = $(byXpath("//*[@id='peopleListToolbar']//select"));

    public static final SelenideElement ELEMENT_NOTIFICATIONS_BTN_DW       = $(byXpath("//*[@id='NotificationPopoverPortlet']"));

    public static final String ELEMENT_ACCEPT_JOIN_SPACE_NOTIFICATION_BTN_DW       = "(//*[@class='media']//*[contains(text(),'${space}')]/following::*[@class='btn btn-mini btn-primary action-item'])[1]";

    public static final String ELEMENT_REFUSE_JOIN_SPACE_NOTIFICATION_BTN_DW       = "(//*[@class='media']//*[contains(text(),'${space}')]/following::*[@class='btn btn-mini cancel-item'])[1]";

    public static final SelenideElement  ELEMENT_NOTIFICATION_DROPDOWN_DW   =   $(byXpath("//*[@class='notifDrawerItems']"));

    public static final SelenideElement  ELEMENT_NO_NOTIFICATION_DISPLAY_DW   =   $(byXpath("//*[@class='noNoticationWrapper']"));

    public static final SelenideElement ELEMENT_CLOSE_NOTIFICATIONS_DW       = $(byXpath("//*[@class='uiCloseIcon notifDrawerClose']"));

    public static final SelenideElement ELEMENT_CLOSE_NOTIFICATIONS_TRIBE       = $(byXpath("//*[@class='uiCloseIcon notifDrawerClose']"));

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

    public static final SelenideElement  ELEMENT_CHAT_DISCUSSIONS_FILTER_CLOSE_DW   =  $(byXpath("//*[@placeholder='Filter discussions']/following::button[1]"));

    public static final SelenideElement  ELEMENT_CHAT_DISCUSSIONS_FILTER_SEARCH_DW   =  $(byXpath("//*[@placeholder='Filter discussions']"));

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

    public static final SelenideElement  ELEMENT_CHAT_ADD_ROOM_SAVE_DW   =  $(byXpath("(//*[@class='btn btn-primary'])[1]"));

    public static final SelenideElement ELEMENT_ASSIGN_TASK_DW= $(byClassName("apps-composer-modal")).find(byText("Affecter une tâche"));

    public static final SelenideElement ELEMENT_ASSIGN_TASK_DIGITAL_WORKPLACE= $(byClassName("apps-composer-modal")).find(byText("Assign Task"));

  //SETTINGS
    public static final SelenideElement ELEMENT_TRIBE_EDIT_LANGUAGE = $(byXpath("(//*[@class='uiIconEdit uiIconLightBlue pb-2'])[1]"));

    public static final SelenideElement  ELEMENT_SETTINGS_APP_TAB_DW  = $(byXpath("(//*[contains(text(),'Applications')]/following::*[@class='v-btn__content'])[last()]"));

    public static final SelenideElement  ELEMENT_APPLICATION_TAB_ADD_APPLICATION_DW  = $(byXpath("(//*[@id='SpaceSettings']//*[@class='v-btn__content'])[last()-2]"));

    public static final SelenideElement  ELEMENT_APPLICATION_TAB_ADD_APPLICATION_TRIBE = $(byXpath("(//*[@id='SpaceSettings']//*[@class='v-btn__content'])[last()-1]"));

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

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL_STATUS = $(byXpath("(//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'][1]//input[1]"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATION_ON_SITE_STATUS = $(byXpath("(//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'][3]//input[1]"));

    public static final SelenideElement ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE_STATUS = $(byXpath("(//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'][2]//input[1]"));

    public static final By ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL = By.xpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'])[1]");

    public static final By ELEMENT_TRIBE_NOTIFICATION_ON_SITE = By.xpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'])[3]");

    public static final By ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE = By.xpath("((//*[@class='v-list-item__title title text-color'])[3]/following::*[@class='v-input--selection-controls__input'])[2]");

    public static final By ELEMENT_TRIBE_GENERAL_NOTIFICATION_ON_MOBILE = By.xpath("(//*[@class='v-input--selection-controls__input'])[2]");

    public static final By ELEMENT_TRIBE_GENERAL_NOTIFICATION_VIA_MAIL = By.xpath("(//*[@class='v-input--selection-controls__input'])[1]");

    public static final By ELEMENT_TRIBE_GENERAL_NOTIFICATION_ON_SITE = By.xpath("(//*[@class='v-input--selection-controls__input'])[3]");

    public static final SelenideElement ELEMENT_TRIBE_APPLY_EDIT_GENERAL_NOTIFICATIONS_BUTTON = $(byXpath("(//button[@class='btn btn-primary v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_CANCEL_EDIT_GENERAL_NOTIFICATIONS_BUTTON = $(byXpath("(//button[@class='btn mr-2 v-btn v-btn--contained theme--light v-size--default']//*[@class='v-btn__content'])[1]"));

    public static final SelenideElement ELEMENT_TRIBE_GENERAL_NOTIFICATIONS_SELECT_MAIL_SENDING_TYPE = $(By.xpath("//select[@name='EMAIL_DIGEST']"));

    public static final SelenideElement ELEMENT_TRIBE_MANAGE_NOTIFICATIONS = $(By.xpath("//*[contains(text(),'Gérer les notifications')]/following::button[1]"));

    public static final SelenideElement ELEMENT_DIGITALWORKPLACE_MANAGE_NOTIFICATIONS = $(By.xpath("(//*[@class='v-list-item__action'])[7]//button"));

    //Users Management
    public static final SelenideElement ELEMENT_ADD_USER_TRIBE_BTN= $(".uiIconAddUser");

    public static final SelenideElement ELEMENT_USERNAME_TRIBE_BTN= $(byXpath("(//*[@class='layout column']//*[@type='text'])[5]"));

    public static final SelenideElement ELEMENT_FIRSTNAME_TRIBE_BTN= $(byXpath("(//*[@class='layout column']//*[@type='text'])[6]"));

    public static final SelenideElement ELEMENT_LASTNAME_TRIBE_BTN= $(byXpath("(//*[@class='layout column']//*[@type='text'])[7]"));

    public static final SelenideElement ELEMENT_EMAIL_TRIBE_BTN= $(byXpath("//*[@type='email']"));

    public static final SelenideElement ELEMENT_PASSWORD_TRIBE_BTN= $(byXpath("(//*[@type='password'])[1]"));

    public static final SelenideElement ELEMENT_CONFIRM_PASSWORD_TRIBE_BTN= $(byXpath("(//*[@type='password'])[2]"));

    public static final SelenideElement ELEMENT_STATUS_TRIBE_BTN= $(byXpath("//input[@type='checkbox']"));

    public static final SelenideElement ELEMENT_SAVE_ADD_USER_TRIBE_BTN= $(byXpath("(//*[@class='d-flex statusField']/following::*[@class='v-btn__content'])[2]"));

    public static final SelenideElement ELEMENT_CANCEL_ADD_USER_TRIBE_BTN= $(byXpath("(//*[@class='d-flex statusField']/following::*[@class='v-btn__content'])[1]"));

    public static final SelenideElement ELEMENT_USER_TRIBE_SEARCH_TEXT = $(byXpath("//*[@placeholder='Filter by name or email']"));

    public static final SelenideElement ELEMENT_DELETE_USER_TRIBE = $(byXpath("//*[@class='uiIconTrash']"));

    public static final SelenideElement ELEMENT_CONFIRM_DELETE_USER_TRIBE = $(byXpath("(//*[@class='v-card__actions']//button)[1]"));

}



