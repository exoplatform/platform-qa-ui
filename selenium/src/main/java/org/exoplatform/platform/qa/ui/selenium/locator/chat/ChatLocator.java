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

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class ChatLocator {


    public static final SelenideElement ELEMENT_CHAT_INPUT_UPLOAD = $(byId("chat-file-file"));

    public static final SelenideElement ELEMENT_CHAT_BUTTON_HIDE_OFF_LINE = $(byClassName("uiIconChatMember"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_AVAILABLE = $(byClassName("user-available"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_DONOTDISTURB = $(byClassName("user-donotdisturb"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_AWAY = $(byClassName("user-away"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_INVISIBLE = $(byClassName("chat-status-invisible"));
  public static final SelenideElement ELEMENT_MORE_ACTION                   = $(byClassName("uiIconVerticalDots"));
  public static final SelenideElement ELEMENT_LEAVEROOM_ACTION           = $(byClassName("room-setting-action-leaveRoom"));

    public static final SelenideElement ELEMENT_CHAT_ROOM_STARTSTOPMEETING = $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[1]/a"));

    public static final SelenideElement ELEMENT_CHAT_LINK_TEXT_OPEN_WIKI_APP = $(byLinkText("Open Wiki application"));

    public static final String ELEMENT_STATUS_CHAT_IN_PROFILE_PAGE = "uiIconUser{status}";

    public static final String ELEMENT_CHAT_UISTATUSPROFILEPORTLET =
                                                         "//*[@class='uiIconLightGray uiIconUser{icon}' and @data-original-title='${status}']";

    public static final SelenideElement ELEMENT_CHAT_INPUT_ROOM_NAME = $(byClassName("add-room-form")).find(by("type", "text"));
    public static final SelenideElement ELEMENT_CREATE_ROOM = $(byClassName("uiIconSimplePlus"));
    public static final SelenideElement ELEMENT_POPUP_ROOM = $(byClassName("PopupContent"));
    public static final SelenideElement ELEMENT_ROOM_NAME = $(byClassName("add-room-form")).find(by("type", "text"));
    public static final SelenideElement ELEMENT_BUTTON_SAVE_ROOM = $(byXpath("//button[text()='Save']"));
    public static final SelenideElement ELEMENT_CONTACT_LIST = $(byId("chat-users"));
    public static final SelenideElement ELEMENT_CHAT_BUTTON_SAVE_ADD_ROOM =
            $(byXpath("//*[@id=\"chat-application\"]/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[2]/button[1]"));
  public static final String          ELEMENT_CHAT_STATUS                        =
                                                          "user-${status}";

  public static final By              ELEMENT_CHAT_ICON                          = byClassName("status-dropdown");

    public static final SelenideElement ELEMENT_CHAT_INPUT_ROOMUSERSS =
            $(byClassName("selectize-input")).find(by("type", "text"));



    public static final SelenideElement ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN = $(byClassName("uiIconVerticalDots"));

    public static final SelenideElement ELEMENT_CHAT_ROOM_EDIT = $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[5]/a"));


    public static final SelenideElement ELEMENT_CHAT_MESSAGE_INPUT = $(byId("messageComposerArea"));

    public static final SelenideElement ELEMENT_CHAT_LIST_MSG = $(byId("chats"));

    public static final SelenideElement ELEMENT_CHAT_INPUT_SEARCH_USER = $(By.xpath("//input[@placeholder='Filter discussions']"));

    public static final SelenideElement ELEMENT_CHAT_RESULT_SEARCH_USER =
            $(byAttribute("class",
                    "selectize-dropdown multi"));


    public static final SelenideElement ELEMENT_CHAT_MEETTING_ACTIONS = $(byId("chat-msg-meeting-actions"));
    public static final SelenideElement ELEMENT_CHAT_RESULT_SEARCH_ASSIGNEE= $(byAttribute("class","selectize-dropdown multi large plugin-remove_button"));
  public static final SelenideElement ELEMENT_CHAT_ASSIGNEE_TASK=  $(byId("taskAssignee")).parent().parent().find(byClassName("selectize-input")).find(by("type","text"));
  public static final SelenideElement ELEMENT_CHAT_CREATE_TASK= $(byClassName("uiIconChatCreateTask"));
  public static final SelenideElement ELEMENT_ASSIGN_TASK_WINDOW=$(byClassName("apps-composer-modal")).find(byText("Assign Task"));
  public static final SelenideElement ELEMENT_ASSIGN_TASK_CONTAINER= $(byClassName("apps-composer-modal"));
  public static final SelenideElement ELEMENT_CHAT_TASK_NAME= $(byId("taskTitle"));
  public static final SelenideElement ELEMENT_CHAT_DUE_DATE_TASK= $(byId("taskDueDate"));
  public static final SelenideElement ELEMENT_CHAT_CURRENT_DATE_TASK= $(byClassName("today"));
  public static final SelenideElement ELEMENT_CHAT_POST_TASK_BUTTON= $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button"));
  public static final SelenideElement ELEMENT_CHAT_CANCEL_TASK_BUTTON= $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/div"));
  public static final SelenideElement ELEMENT_CHAT_CLOSE_ICON= $(byXpath("//*[@id=\"chat-application\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div/div[1]/a"));
  public static final SelenideElement ELEMENT_CHAT_NOTIFICATION=   $(byClassName("dropdown-menu")).find(byXpath("//*[@id=\"chat-notifications-details\"]/a"));
  public static final SelenideElement ELEMENT_ASSIGNEE_TASK=$(byXpath("//*[@id=\"taskManagement\"]/div[3]/div[2]/div/div/div[4]/div[2]/div/span/a[2]"));
    public static final SelenideElement ELEMENT_CONTAINER_LIST_MESSAGES = $(byXpath("//div[@id='chats']"));
    public static final SelenideElement ELEMENT_CHAT_ICON_STATUS=   $(byClassName("uiIconStatus"));
    public static final SelenideElement ELEMENT_CHAT_CONTACT = $(byClassName("chat-contact"));
    public static final SelenideElement ELEMENT_DELETE_ROOM = $(byClassName("room-setting-action-deleteRoom"));
    public static final SelenideElement ELEMENT_CONFIRM_BUTTON_DELETE_ROOM = $(byId("team-delete-button-ok"));
    public static final SelenideElement ELEMENT_CHAT_ADD_EVENT = $(byClassName("uiIconChatCreateEvent"));
    public static final SelenideElement ELEMENT_COLLABORATION_ACTIONS=  $(byClassName("uiIconPlusCircled"));
    public static final SelenideElement ELEMENT_ADD_EVENT_WINDOW =$(byClassName("apps-composer-modal")).find(byText("Add Event"));
    public static final SelenideElement ELEMENT_ADD_EVENT =$(byXpath("//*[@id=\"appComposerForm\"]/div[1]/input[1]"));
    public static final SelenideElement ELEMENT_CHAT_EVENT_FROM_DATE_= $(byXpath("//*[@id=\"appComposerForm\"]/div[1]/div/div[1]/input"));
    public static final SelenideElement ELEMENT_CHAT_EVENT_TO_DATE_= $(byXpath("//*[@id=\"appComposerForm\"]/div[1]/div/div[2]/input"));
    public static final SelenideElement ELEMENT_CHAT_EVENT_LOCATION =$(byXpath("//*[@id=\"appComposerForm\"]/div[1]/input[2]"));
    public static final SelenideElement ELEMENT_CHAT_POST_EVENT =$(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button"));
    public static final SelenideElement ELEMENT_CHAT_SHARE_LINK = $(byClassName("uiIconChatLink"));
    public static final SelenideElement ELEMENT_CHAT_UPLOAD_FILE= $(byXpath("//div[text()='Upload File']"));
    public static final SelenideElement ELEMENT_CHAT_ASK_QUESTION= $(byClassName("uiIconChatQuestion"));
    public static final SelenideElement ELEMENT_CHAT_ASK_BUTTON= $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button"));
    public static final SelenideElement ELEMENT_CHAT_CANCEL_BUTTON= $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/div"));
    public static final SelenideElement ELEMENT_CHAT_RAISE_HAND= $(byClassName("uiIconChatRaiseHand"));
    public static final SelenideElement ELEMENT_CHAT_COLLABORATION_MENU= $(byClassName("apps-container"));
    public static final SelenideElement ELEMENT_POPUP_CONTAINER= $(byClassName("apps-composer-modal"));
    public static final SelenideElement ELEMENT_RAISE_HAND_BUTTON= $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button"));
    public static final SelenideElement ELEMENT_CHAT_EVENT_CALENDAR = $(byClassName("uiCalendarComponent"));
    public static final SelenideElement ELEMENT_CHAT_SELECT_FILE= $(byXpath("//*[@id=\"appComposerForm\"]/div[1]/div[2]/a[1]"));
    public static final SelenideElement ELEMENT_CHAT_CANCEL_UPLOAD_FILE_BUTTON= $(byXpath(" //*[@id=\"appComposerForm\"]/div[1]/div[2]/a[2]"));
    public static final SelenideElement ELEMENT_CHAT_SHARE_LINK_BUTTON= $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button"));
    public static final SelenideElement ELEMENT_CHAT_NOTIFICATION_NUMBER = $(byClassName("notif-total"));
    public static final SelenideElement ELEMENT_MINI_CHAT = $(byClassName("mini-chat"));
  public static final SelenideElement ELEMENT_CLOSE_WINDOW_ROOM=$(byXpath("//*[@id=\"room-detail\"]/div[4]/div/div[1]/a"));
  public static final SelenideElement ELEMENT_NO_BUTTON_LEAVEROOM = $(byXpath("//*[@id=\"team-delete-button-cancel\"]"));
  public static final SelenideElement ELEMENT_CHAT_EMOTICON = $(byClassName("uiIconChatSmile"));
  public static final SelenideElement ELEMENT_CHAT_COMPOSER_EMOTICON= $(byClassName("composer-emoji-panel"));
    public static final SelenideElement ELEMENT_CHAT_SEARCH_FIELD=  $(byXpath("//*[@id=\"chat-application\"]/div[1]/div[2]/div[1]/input"));
    public static final SelenideElement ELEMENT_CHAT_SETTING_NOTIFICATION=$(byClassName("uiIconGear"));

    public static final SelenideElement ELEMENT_CHAT_CONFIRM_BUTTON_NOTIFICATION=$(byXpath("//*[@id=\"chatPreferences\"]/div/div[2]/div/div[1]"));

    public static final SelenideElement ELEMENT_CHAT_BADGE_NOTIFICATION=  $(byXpath("//*[@id=\"chatApplicationNotification\"]/div[1]/a/div/span"));
    public static final SelenideElement ELEMENT_CHAT_DO_NOT_DISTURB_BUTTON_NOTIFICATION=   $(byId("notifyDonotdisturb"));

    public static final SelenideElement ELEMENT_CHAT_DESKTOP_NOTIFICATION_BUTTON_=   $(byId("notifyDesktop"));

   public static final SelenideElement ELEMENT_CHAT_DESKTOP_NOTIFICATION_STATUS=   $(byXpath("(//input[@id='notifyDesktop']/following::span)[1]"));

   public static final SelenideElement ELEMENT_CHAT_BIP_NOTIFICATION_BUTTON=   $(byId("notifyBip"));

    public static final SelenideElement ELEMENT_CHAT_ON_SITE_NOTIFICATION_BUTTON=   $(byId("notifyOnSite"));
    public static final SelenideElement ELEMENT_CHAT_NOTIFICATION_DETAIL=   $(byId("chat-notifications-details"));

    public static final SelenideElement ELEMENT_CHAT_BUTTON_USER_PROFILE=  $(byXpath("//*[@class='chat-button btn']"));

    public static final SelenideElement ELEMENT_CHAT_TIP_CONTENT=   $(byId("tiptip_content"));
    public static final SelenideElement ELEMENT_CHAT_ICON_TIP_CONTENT=  $(byXpath("//*[@id=\"tiptip_content\"]/div/a/i"));
    public static final SelenideElement ELEMENT_MINI_CHAT_MINIMIZE_ICON= $(byClassName("uiIconMinimize"));
    public static final SelenideElement ELEMENT_MINI_CHAT_MAXIMIZE_ICON= $(byClassName("uiIconMaximize"));
    public static final SelenideElement ELEMENT_MINI_CHAT_CLOSE_ICON= $(byXpath("//a[@data-original-title='Close']"));
    public static final SelenideElement ELEMENT_MINI_CHAT_POPOUT_ICON= $(byClassName("uiIconChatPopOut"));
    public static final SelenideElement ELEMENT_MINI_CHAT_MESSAGE_LIST=  $(byClassName("chat-message-list"));
    public static final SelenideElement ELEMENT_CHAT_ROOM_PARTICIPANTS=  $(byClassName("room-participants"));

}



