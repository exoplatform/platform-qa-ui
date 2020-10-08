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

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class ConnectionsLocator {
  public static final By              ELEMENT_CONNECTION_EVERYONE_TITLE       =
                                                                        By.xpath(".//*[@id='UIAllPeople']//*[contains(text(),'Everyone')]");

  public static final SelenideElement ELEMENT_CONNECTION_CANCEL_BTN           = $(byXpath("(//*[@class='v-card__actions peopleCardActions']//button)[1]"));

  public static final SelenideElement ELEMENT_CONNECTION_CANCEL_DW_BTN           = $(byXpath("//*[@class='uiIconSocCancelConnectUser peopleRelationshipIcon d-inline']"));

  public static final SelenideElement ELEMENT_CONNECTION_REMOVE_DW_BTN           = $(byXpath("//*[@class='uiIconSocCancelConnectUser peopleRelationshipIcon d-inline']"));

  public static final SelenideElement ELEMENT_CONNECTION_REVOVE_BTN           = $(byText("Remove Connection"));

  public static final SelenideElement ELEMENT_PROFILE_AVATAR_DW           = $(byXpath("(//*[@id='ProfileHeader']//*[@class='v-image__image v-image__image--cover'])[2]"));

  public static final SelenideElement ELEMENT_PROFILE_COVER_DW           = $(byXpath("(//*[@id='ProfileHeader']//*[@class='v-image__image v-image__image--cover'])[1]"));

  public static final SelenideElement ELEMENT_PROFILE_FULLNAME_DW           = $(byXpath("(//*[@class='profileHeader']/div/div/div)[1]"));

  public static final SelenideElement ELEMENT_PROFILE_JOB_DW           = $(byXpath("(//*[@class='profileHeader']/div/div/div)[2]"));

  public static final SelenideElement ELEMENT_CONTACT_INFORMATIONS_EDIT_BTN_DW           = $(byXpath("//*[@id='ProfileContactInformation']//*[@class='uiIconEdit uiIconLightBlue pb-2']"));

  public static final SelenideElement ELEMENT_CONTACT_FIRST_NAME_EDIT_BTN_DW           = $(byXpath("(//*[@class='v-card__text d-flex fullnameFields py-0']//input)[1]"));

  public static final SelenideElement ELEMENT_CONTACT_LAST_NAME_EDIT_BTN_DW           = $(byXpath("(//*[@class='v-card__text d-flex fullnameFields py-0']//input)[2]"));

  public static final SelenideElement ELEMENT_CONTACT_EMAIL_EDIT_BTN_DW           = $(byXpath("(//*[@class='v-card__text d-flex emailField py-0']//input)[1]"));

  public static final SelenideElement ELEMENT_CONTACT_JOB_TITLE_EDIT_BTN_DW           = $(byXpath("(//*[@class='v-card__text d-flex positionField py-0']//input)[1]"));

  public static final SelenideElement ELEMENT_CONTACT_COMPANY_TITLE_EDIT_BTN_DW           = $(byXpath("(//*[@class='v-card__text d-flex positionField py-0']//input)[2]"));

  public static final SelenideElement ELEMENT_CONTACT_PHONE_TITLE_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::input[1]"));

  public static final SelenideElement ELEMENT_CONTACT_PHONE_TYPE_SELECT_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::select[1]"));

  public static final SelenideElement ELEMENT_CONTACT_NEW_PHONE_TYPE_SELECT_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::select[2]"));

  public static final SelenideElement ELEMENT_CONTACT_INSTANT_MESSAGING_TITLE_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::input[2]"));

  public static final SelenideElement ELEMENT_CONTACT_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW     = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::select[2]"));

  public static final SelenideElement ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW     = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::select[4]"));

  public static final SelenideElement ELEMENT_CONTACT_URL_TITLE_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::input[3]"));

  public static final SelenideElement ELEMENT_CONTACT_EDIT_SAVE_BTN_DW           = $(byXpath("(//*[@class='layout column']//*[@class='d-flex']//button)[7]"));

  public static final SelenideElement ELEMENT_CONTACT_RECEIVED_KUDOS_DW           = $(byXpath("//*[@id='kudosOverviewCardsParent']//*[@class='kudosOverviewCard col'][1]/div/div[2]"));

  public static final SelenideElement ELEMENT_CONTACT_SENT_KUDOS_DW           = $(byXpath("//*[@id='kudosOverviewCardsParent']//*[@class='kudosOverviewCard col'][2]/div/div[2]"));

  public static final SelenideElement ELEMENT_CONTACT_GAINED_CAURIS_DW           = $(byXpath("(//*[@id='WalletOverview']//div)[8]"));

  public static final SelenideElement ELEMENT_CONTACT_GAINED_CAURIS_INFORMATION_DW           = $(byXpath("(//*[@id='WalletOverview']//button)[1]"));

  public static final SelenideElement ELEMENT_CONTACT_CAURIS_GAMIFICATION_DRAWER_DW           = $(byXpath("//*[@class='layout column']//*[@id='plugin-gamification']"));

  public static final SelenideElement ELEMENT_CONTACT_CAURIS_GAMIFICATION_CLOSE_DRAWER_BTN_DW           = $(byXpath("//*[@id='WalletOverview']//*[@class='layout column']//button"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_TITLE_DW           = $(byXpath("//*[@id='ProfileContactInformation']//*[@class='text-header-title text-sub-title']"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_FULLNAME_DW           = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[1]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW           = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[2]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW           = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[3]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW           = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[4]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_PHONE_DW           = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[5]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION__FIRST_PHONE_DW     = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[5]//div[1]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_PHONE_DW      = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[5]//div[2]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_FIRST_INSTANT_MESSAGING_DW    = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[7]//div[1]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_INSTANT_MESSAGING_DW    = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[7]//div[2]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION__FIRST_URL_DW     = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[9]//div[1]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_URL_DW      = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[9]//div[2]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_INSTANT_MESSAGING_DW           = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[6]"));

  public static final SelenideElement ELEMENT_PROFILE_CONTACT_INFORMATION_URL_DW           = $(byXpath("(//*[@id='ProfileContactInformation']//*[@class='flex d-flex']//div[2])[7]"));

  public static final SelenideElement ELEMENT_ADD_CONTACT_NEW_PHONE_DW  = $(byXpath("(//*[@class='v-card__text d-flex positionField py-0'][8]/following::*[@class='d-flex']/div[2])[1]/button"));

  public static final SelenideElement ELEMENT_ADD_CONTACT_NEW_INSTANT_MESSAGING_DW  = $(byXpath("(//*[@class='v-card__text d-flex positionField py-0'][8]/following::*[@class='d-flex']/div[2])[2]/button"));

  public static final SelenideElement ELEMENT_ADD_CONTACT_NEW_URL_DW  = $(byXpath("(//*[@class='v-card__text d-flex positionField py-0'][8]/following::*[@class='d-flex']/div[2])[3]/button"));

  public static final SelenideElement ELEMENT_CONTACT_NEW_PHONE_TITLE_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::input[2]"));

  public static final SelenideElement ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TITLE_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::input[4]"));

  public static final SelenideElement ELEMENT_CONTACT_NEW_URL_TITLE_EDIT_BTN_DW           = $(byXpath("//*[@class='v-card__text d-flex positionField py-0'][8]/following::input[6]"));

  public static final SelenideElement ELEMENT_TRIBE_ADD_CONTACT_BTN_DW           = $(byXpath("//*[@class='uiIconSocConnectUser peopleRelationshipIcon d-inline']"));

  public static final SelenideElement ELEMENT_CONTACT_AVATAR_DW           = $(byXpath("//*[@class='peopleAvatar']//*[@class='v-image__image v-image__image--cover']"));

  public static final SelenideElement ELEMENT_ADD_CONTACT_TITLE_DW           = $(byXpath("//*[@class='d-inline peopleRelationshipButtonText']"));

  public static final SelenideElement ELEMENT_ADD_CONTACT_JOB_DW           = $(byXpath("//*[@class='v-card__subtitle userPositionLabel text-truncate py-0']"));

  public static final SelenideElement ELEMENT_ADD_CONTACT_FULLNAME_DW           = $(byXpath("//*[@class='v-card__text peopleCardBody align-center pt-2 pb-1']//a"));

  public static final SelenideElement ELEMENT_PEOPLE_SHOWING_RESULTS_DW          = $(byXpath("//*[@class='showingPeopleText text-sub-title ml-3 d-none d-sm-flex']"));

  public static final SelenideElement ELEMENT_PEOPLE_PULLDOWN_FILTER_DW          = $(byXpath("//*[@class='v-text-field__slot']/following::select[1]"));

  public static final String ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW          = "//*[@class='v-card__text peopleCardBody align-center pt-2 pb-1']//a[contains(text(),'${user}')]";

  public static final SelenideElement ELEMENT_ADD_CONTACT_COVER_DW           = $(byXpath("//*[@class='peopleCardFront']/div/div/div[@class='v-image__image v-image__image--cover']"));

  public static final SelenideElement ELEMENT_ADD_CONTACT_COVER_WIDTH_DW           = $(byXpath("//*[@class='peopleCardFront']/div/div/div[@class='v-image__image v-image__image--cover']/following::div[@class='v-responsive__content'][1]"));

  public static final String          ELEMENT_CONNECTION_IGNORE_BTN           =
                                                                    " //a[contains(@href,'${user}')]/../..//*[text()='Ignore']";

  public static final String ELEMENT_CONNECTION_CONFIRM_BTN          = " //a[contains(@href,'${user}')]/../..//*[text()='Confirm']";

  public static final String          ELEMENT_CONNECTION_USER_NAME            =
                                                                   "//a[contains(@href,'${user}') and @data-key='title']";

  public static final By              ELEMENT_ALL_CONNECTIONS_TAB             =
                                                                  By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'all-people')]");

  public static final SelenideElement
                                      ELEMENT_ICON_CHAT_PROFIL_STATUS            =
                                                                 $(byId("UIUserNavigationPortlet")).find(byClassName("uiIconBannerChat"));
  public static final SelenideElement
                                       ELEMENT_ICON_CALL_PROFIL_STATUS    =
          $(byId("UIUserNavigationPortlet")).find(byClassName("callButtonIconVideo"));
  public static final By              ELEMENT_MY_CONNECTIONS_TAB              =
                                                                 By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'network')]");

  public static final By              ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB =
                                                                              By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'receivedInvitations')]");

  public static final By              ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB =
                                                                              By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'pendingRequests')]");

  public static final By              ELEMENT_POSITIONS_OF_PEOPLE             = By.id("position");

  public static final By              ELEMENT_SKILL_OF_PEOPLE                 = By.id("skills");

  public static final By              ELEMENT_SEARCH_BUTTON                   = By.id("SearchButton");

  public static final SelenideElement ELEMENT_NAME_OF_PEOPLE                  =
                                                             $(byXpath("//*[@id='uiTableProfileUserSearchInput']/div[1]/div/div/div[1]/input"));

  public static final SelenideElement ELEMENT_SEARCHED_NAME_OF_PEOPLE         =   $(byXpath("//*[@id='uiTableProfileUserSearchInput']/div[1]/div/div/div[1]/div"));

  public static final SelenideElement ELEMENT_CONTENT_PEOPLE                  = $(byXpath("//*[@id='UIAllPeople']/div[2]/div"));

  public static final SelenideElement ELEMENT_USER_RESULT_SEARCH                = $(byClassName(" spaceBox"));

  public static final SelenideElement ELEMENT_USER_PROFILE                = $(byClassName("uiIconAppprofile"));

  public static final String ELEMENT_USER_SEARCH_TITLE                = "//*[@id='searchDialog']//a[@title='${user}']";

  public static final String ELEMENT_SPACE_SEARCH_TITLE                = "//*[@class='spaceCardFront']//*[contains(text(),'${space}')]";

  public static final String ELEMENT_ACTIVITY_SEARCH_TITLE        = "//*[@class='searchMatchExcerpt' and text()='${activity}']";

  public static final String ELEMENT_TASK_SEARCH_TITLE        = "//*[@class='searchMatchExcerpt' and text()='${task}']";

  public static final String ELEMENT_APPLICATION_SEARCH_TITLE        = "//*[@title='${application}']";

  public static final String ELEMENT_APPLICATION_SEARCH_DESCRIPTION        = "//*[@title='${applicationDescription}']";

  public static final SelenideElement ELEMENT_APPLICATION_SEARCH_PICTURE       = $(byXpath("//*[@class='v-image v-responsive appImage theme--light']"));

  public static final SelenideElement ELEMENT_WALLET_APPLICATION_PAGE       = $(byXpath("//*[@id='WalletApp']"));

  public static final SelenideElement ELEMENT_DRAWER_TASK_NAME      =   $(byXpath("//*[@id='task-Drawer']//*[@id='task-name']"));

  public static final String ELEMENT_FILE_SEARCH_TITLE        = "//*[@class='searchMatchExcerpt' and text()='${file}']";

  public static final SelenideElement ELEMENT_ACCESS_TO_SEARCHED_ACTIVITY       =   $(byXpath("(//*[@class='v-list-item__content']//*[@class='v-list-item__title'])[1]"));

  public static final SelenideElement ELEMENT_ACTIVITY_TITLE       =   $(byXpath("//*[@id='boxContainer']//*[@class='description']//p"));

  public static final SelenideElement ELEMENT_COMMENT_TITLE       =   $(byXpath("//*[@id='boxContainer']//*[@class='contentComment']//p"));


}
