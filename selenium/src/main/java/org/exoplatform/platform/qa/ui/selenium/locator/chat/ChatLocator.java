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
package org.exoplatform.platform.qa.ui.selenium.locator.chat;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class ChatLocator {

  public static final SelenideElement ELEMENT_CHAT_MEETTING_ACTIONS_UPLOAD_FILE  = $(byId("meeting-action-upload-link"));

  public static final SelenideElement ELEMENT_CHAT_POPUP_UPLOAD                  = $(byClassName("popover-inner"));

  public static final SelenideElement ELEMENT_CHAT_PROGRESS_BAR                  = $(byClassName("progressBar"));

  public static final SelenideElement ELEMENT_CHAT_INPUT_UPLOAD                  = $(byId("chat-file-file"));

  public static final SelenideElement ELEMENT_CHAT_MESSAGE_CONTAINER             = $(byClassName("msLinkInMes"));

  public static final SelenideElement ELEMENT_CHAT_BUTTON_HIDE_OFF_LINE          = $(byClassName("uiIconChatMember"));

  public static final SelenideElement ELEMENT_ICON_CHAT                          = $(byId("chat-status"));

  public static final SelenideElement ELEMENT_CHAT_STATUS_AVAILABLE              = $(byClassName("chat-status-available"));

  public static final SelenideElement ELEMENT_CHAT_STATUS_DONOTDISTURB           = $(byClassName("chat-status-donotdisturb"));

  public static final SelenideElement ELEMENT_CHAT_STATUS_AWAY                   = $(byClassName("chat-status-away"));

  public static final SelenideElement ELEMENT_CHAT_STATUS_INVISIBLE              = $(byClassName("chat-status-invisible"));

  public static final SelenideElement ELEMENT_CHAT_ROOM_STARTSTOPMEETING         = $(byId("chat-record-button"));

  public static final SelenideElement ELEMENT_CHAT_LINK_TEXT_OPEN_WIKI_APP       = $(byLinkText("Open Wiki application"));

  public static final String          ELEMENT_STATUS_CHAT                        = "toggle-status-{status}";

  public static final String          ELEMENT_STATUS_CHAT_IN_PROFILE_PAGE        = "uiIconUser{status}";

  public static final String          ELEMENT_CHAT_UISTATUSPROFILEPORTLET        =
          ".//*[@id='UIStatusProfilePortlet']//*[@class='${icon}' and @data-original-title='${status}']";

  public static final String          ELEMENT_CHAT_TOOLTIP                       =
          "//*[contains(@class,'tooltip-inner') and contains(text(),'${tooltip}')]";

  public static final String          ELEMENT_CHAT_STATUS                        =
          "//a[@class='chat-status']/*[contains(text(),'${status}')]";

  public static final By              ELEMENT_CHAT_ICON                          = By.xpath(".//*[@id='chat-status']/a");

  public static final SelenideElement ELEMENT_CHAT_ICON_ADD_ROOM                 =
          $(byXpath("//*[@id=\"chat-users\"]/table/tbody/tr[4]/td")).parent()
                  .parent()
                  .find(byClassName("uiIconChatSimplePlusMini"));

  public static final SelenideElement ELEMENT_CHAT_INPUT_ROOM_NAME               = $(byXpath("//*[@id=\"team-modal-name\"]"));

  public static final SelenideElement ELEMENT_CHAT_INPUT_ROOM_USERS              =
          $(byXpath("//*[@id=\"team-modal-form\"]/div[2]/div[1]/div/div[2]/div/div/div/input"));

  public static final SelenideElement ELEMENT_CHAT_BUTTON_SAVE_ADD_ROOM          =
          $(byXpath("//*[@id=\"team-modal-form\"]/div[2]/div[2]/a[1]"));

  public static final SelenideElement ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN         = $(byId("chat-team-button-dropdown"));

  public static final SelenideElement ELEMENT_CHAT_ROOM_DELETE                   = $(byId("team-delete-button"));

  public static final SelenideElement ELEMENT_CHAT_ROOM_EDIT                     = $(byId("team-edit-button"));

  public static final SelenideElement ELEMENT_CHAT_CONFIRM_DELETE_ROOM           = $(byId("team-delete-button-ok"));

  public static final SelenideElement ELEMENT_CHAT_MESSAGE_INPUT                 = $(byId("msg"));

  public static final SelenideElement ELEMENT_CHAT_LIST_MSG                      = $(byId("chats"));



  public static final SelenideElement ELEMENT_CHAT_INPUT_SEARCH_USER             = $(byId("chat-search"));

  public static final SelenideElement ELEMENT_CHAT_RESULT_SEARCH_USER            =
          $(byAttribute("class",
                  "selectize-dropdown multi plugin-remove_button"));

  public static final SelenideElement ELEMENT_CHAT_MEETTING_ACTIONS              = $(byId("chat-msg-meeting-actions"));

  public static final SelenideElement ELEMENT_CHAT_INPUT_TASKNAME                = $(byId("task-add-task"));

  public static final SelenideElement ELEMENT_CHAT_INPUT_USERNAME_IN_ASSIGN_TASK =
          $(byXpath("//*[@id=\"chat-application\"]/div[8]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div[5]/div[1]/div[1]/input"));

  public static final SelenideElement ELEMENT_CHAT_INPUT_DUE_TASK_DATE           = $(byId("task-add-date"));

  public static final SelenideElement ELEMENT_BUTTON_ADD_TASK                    = $(byClassName("create-task-button"));

  public static final SelenideElement ELEMENT_CONTAINER_LIST_MESSAGES            = $(byId("chats"));

  public static final SelenideElement ELEMENT_ICON_PLUS_CHAT                    = $(byClassName("uiIconPlusCircled"));
  public static final SelenideElement ELEMENT_ADD_EVENT_CHAT                    = $(byClassName("uiIconChatCreateEvent"));
  public static final SelenideElement ELEMENT_SUMMARY_EVENT                     = $(byName("summary"));
  public static final SelenideElement ELEMENT_START_DATE_EVENT_CHAT             = $(byName("startDate"));
  public static final SelenideElement  ELEMENT_END_DATE_EVENT_CHAT              = $(byName("endDate"));
  public static final SelenideElement ELEMENT_LOCATION_EVENT_CHAT               = $(byName("location"));
  public static final SelenideElement ELEMENT_POST_ADD_EVENT_CHAT               = $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button"));
  public static final SelenideElement ELEMENT_VERIFY_ICON_EVENT_CHAT            = $(byXpath("//*[@id=\"1530580968497\"]/div[2]/div[2]/i[1]"));
  public static final SelenideElement ELEMENT_SEARCH_CALENDAR_EVENT_CHAT        = $(byXpath("//*[@id=\"value\"]"));
  public static final SelenideElement ELEMENT_SEARCH_CALENDAR_RESULT_CHAT       = $(byXpath("//*[@id=\"UIListView\"]"));
  public static final SelenideElement ELEMENT_CALENDAR_SPACE_CHAT               = $(byXpath("//*[@id=\"spaceMenuTab\"]/li[6]/a"));
  public static final SelenideElement ELEMENT_CLICK_SEARCH_BUTON_CHAT           = $(byXpath("//*[@id=\"UISearchForm\"]/div[2]/a/i"));
}
