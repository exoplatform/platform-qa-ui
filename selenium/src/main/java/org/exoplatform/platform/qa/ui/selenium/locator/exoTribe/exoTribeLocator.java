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

    public static final SelenideElement ELEMENT_TRIBE_STREAM_PAGE = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconStream iconStream null']"));

    public static final SelenideElement ELEMENT_TRIBE_SNAPSHOT_PAGE = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconHome iconHome null']"));

    public static final SelenideElement ELEMENT_TRIBE_TASKS_PAGE = $(byXpath("(//*[@class='caption color-title'])[1]"));

    //SPACE

    public static final SelenideElement GO_TO_SPACES_TRIBE_BTN = $(byXpath("//*[@class='uiIcon uiIconFile uiIconToolbarNavItem uiIconSpaces iconSpaces null']"));

    public static final SelenideElement ELEMENT_ADDNEWSPACE_TRIBE_BTN = $(byXpath("//*[@class='uiIconSocSimplePlus uiIconSocWhite']"));

    public static final SelenideElement ELEMENT_SPACENAME_TRIBE = $(byXpath("//*[@class='spaceMenuNavHeader']"));

    public static final String          ELEMENT_SPACE_TRIBE_CONFIRM_DELETE  = "Est-ce vous êtes certain de vouloir supprimer cet espace ? Ceci est définitif. Toutes les pages de ce groupe seront également supprimées.";

    public static final By              ELEMENT_TRIBE_DELETE_SPACE_OK_BUTTON = By.xpath("//*[text()='Valider']");

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

    //WIKI

    public static final By ELEMENT_ADD_PAGE_TRIBE = By.xpath("//div[@data-toggle and text()='Ajouter une Page']");

    public static final By ELEMENT_DELETE_LINK_TRIBE = By.xpath(".//*[text()='Supprimer']");

    public static final By ELEMENT_CONFIRM_WIKI_DELETE_TRIBE = By.xpath(".//*[@id='UIWikiDeletePageConfirm']//button[text()='Valider']");

    public static final By ELEMENT_CONTENT_WIKI_INPUT_TRIBE = By.xpath("//*[@id='body']");

    public static final By ELEMENT_TEMPLATE_TRIBE_SELECT_BTN = By.xpath("//*[@id='UIWikiSelectTemplateForm']//*[text()='Sélectionner']");

    public static final By ELEMENT_WIKI_TRIBE_MOVE_POPUP_SAVE = By.xpath(".//*[@id='UIWikiMovePageForm']//button[contains(text(),'Déplacer')]");

    public static final By ELEMENT_WIKI_TRIBE_COMPARE_VERSION_TITLE = By.xpath(".//h4[text()='Comparer les Versions']");

    public static final String ELEMENT_COMPARE_TRIBE_VERSION_NUMBER = ".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Version $num')]";

    public static final By ELEMENT_COMPARE_TRIBE_CURRENT_VERSION = By.xpath(".//*[@id='UIWikiPageVersionsCompare']//*[contains(text(),'Version courante')]");

    public static final By   ELEMENT_HOME_SPACE_TRIBE = By.xpath("//*[@class='uiIconAppSpaceHomePage uiIconDefaultApp']");

    public static final By ELEMENT_TRIBE_INFO_ADD_MORE_RELATIONS = By.xpath(".//*[@id='UIWikiPageInfo']//button[text()='Associer une Page']");

    public static final By ELEMENT_ADD_RELATED_TRIBE_POPUP_DROPDOWN = By.xpath(".//*[contains(text(),'Associer une Page')]");

    public static final By ELEMENT_ADD_RELATED_TRIBE_SLECTION = By.xpath("//*[contains(text(),'Ajouter une Page Associée')]/../..//*[@data-toggle='dropdown']");

    public static final String ELEMENT_ADD_RELATED_POPUP_TRIBE_LOCATION = ".//*[contains(text(),'Ajouter une Page Associée')]/../..//*[contains(text(),'${location}')]";

    public static final String ELEMENT_ADD_RELATED_POPUP_TRIBE_CONTENT = ".//*[contains(text(),'Ajouter une Page Associée')]/../..//*[contains(text(),'${page}')]";

    //TASK
    public static final By ELEMENT_POPUB_TRIBE_EDIT_PROJECT    =By.xpath("//*[@id='taskManagement']//*[@class='PopupContent popupContent']");

}



