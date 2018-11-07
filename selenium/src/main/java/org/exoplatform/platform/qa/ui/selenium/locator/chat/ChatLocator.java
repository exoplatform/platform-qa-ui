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


    public static final SelenideElement ELEMENT_CHAT_MEETTING_ACTIONS_UPLOAD_FILE = $(byId("meeting-action-upload-link"));

    public static final SelenideElement ELEMENT_CHAT_POPUP_UPLOAD = $(byClassName("popover-inner"));

    public static final SelenideElement ELEMENT_CHAT_PROGRESS_BAR = $(byClassName("progressBar"));

    public static final SelenideElement ELEMENT_CHAT_INPUT_UPLOAD = $(byId("chat-file-file"));

    public static final SelenideElement ELEMENT_CHAT_MESSAGE_CONTAINER = $(byClassName("msLinkInMes"));

    public static final SelenideElement ELEMENT_CHAT_BUTTON_HIDE_OFF_LINE = $(byClassName("uiIconChatMember"));

    public static final SelenideElement ELEMENT_ICON_CHAT = $(byId("chat-status"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_AVAILABLE = $(byClassName("user-available"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_DONOTDISTURB = $(byClassName("user-donotdisturb"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_AWAY = $(byClassName("user-away"));

    public static final SelenideElement ELEMENT_CHAT_STATUS_INVISIBLE = $(byClassName("user-invisible"));

    public static final SelenideElement ELEMENT_CHAT_ROOM_STARTSTOPMEETING = $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[1]/a"));

    public static final SelenideElement ELEMENT_CHAT_LINK_TEXT_OPEN_WIKI_APP = $(byLinkText("Open Wiki application"));

    public static final String ELEMENT_STATUS_CHAT = "toggle-status-{status}";

    public static final String ELEMENT_STATUS_CHAT_IN_PROFILE_PAGE = "uiIconUser{status}";

    public static final String ELEMENT_CHAT_UISTATUSPROFILEPORTLET =
            ".//*[@id='UIStatusProfilePortlet']//*[@class='${icon}' and @data-original-title='${status}']";

    public static final String ELEMENT_CHAT_TOOLTIP =
            "//*[contains(@class,'tooltip-inner') and contains(text(),'${tooltip}')]";

    public static final String ELEMENT_CHAT_STATUS =
            "//a[@class='chat-status']/*[contains(text(),'${status}')]";

    public static final By ELEMENT_CHAT_ICON = By.xpath(".//*[@id='chat-status']/a");

    public static final SelenideElement ELEMENT_CHAT_ICON_ADD_ROOM =
            $(byXpath("//*[@id=\"chat-users\"]/table/tbody/tr[4]/td")).parent()
                    .parent()
                    .find(byClassName("uiIconChatSimplePlusMini"));

    public static final SelenideElement ELEMENT_CHAT_INPUT_ROOM_NAME = $(byClassName("add-room-form")).find(by("type", "text"));

    public static final SelenideElement ELEMENT_CHAT_INPUT_ROOM_USERS =
            $(byXpath("//*[@id=\"chat-application\"]/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[1]/div[1]/div/input"));


    public static final SelenideElement ELEMENT_CREATE_ROOM = $(byClassName("uiIconSimplePlus"));
    public static final SelenideElement ELEMENT_POPUP_ROOM = $(byClassName("PopupContent"));
    public static final SelenideElement ELEMENT_ROOM_NAME = $(byClassName("add-room-form")).find(by("type", "text"));
    public static final SelenideElement ELEMENT_BUTTON_SAVE_ROOM = $(byClassName("btn-primary"));
    public static final SelenideElement ELEMENT_CONTACT_LIST = $(byId("chat-users"));

    public static final SelenideElement ELEMENT_CHAT_BUTTON_SAVE_ADD_ROOM =
            $(byXpath("//*[@id=\"chat-application\"]/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[2]/button[1]"));

    public static final SelenideElement ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN = $(byClassName("uiIconVerticalDots"));

    public static final SelenideElement ELEMENT_CHAT_ROOM_EDIT = $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[5]/a"));


    public static final SelenideElement ELEMENT_CHAT_MESSAGE_INPUT = $(byId("messageComposerArea"));

    public static final SelenideElement ELEMENT_CHAT_LIST_MSG = $(byId("chats"));

    public static final SelenideElement ELEMENT_CHAT_INPUT_SEARCH_USER = $(byId("chat-search"));

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

  public static final SelenideElement ELEMENT_CHAT_INPUT_TASKNAME                = $(byId("task-add-task"));

  public static final SelenideElement ELEMENT_CHAT_INPUT_USERNAME_IN_ASSIGN_TASK =
                                                                                 $(byXpath("//*[@id=\"chat-application\"]/div[8]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div[5]/div[1]/div[1]/input"));



    public static final SelenideElement ELEMENT_CHAT_INPUT_DUE_TASK_DATE = $(byId("task-add-date"));

    public static final SelenideElement ELEMENT_BUTTON_ADD_TASK = $(byClassName("create-task-button"));

    public static final SelenideElement ELEMENT_CONTAINER_LIST_MESSAGES = $(byId("chats"));
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
    public static final SelenideElement ELEMENT_CHAT_UPLOAD_FILE= $(byClassName("uiIconChatUpload"));
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


  public static final SelenideElement ELEMENT_MORE_ACTIONS=$(byClassName("uiIconVerticalDots"));
  public static final SelenideElement ELEMENT_CHAT_LEAVE_ROOM_BUTTON=  $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[5]/a"));
  public static final SelenideElement ELEMENT_CHAT_SEARCH_FIELD=  $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[5]/a"));
  public static final SelenideElement ELEMENT_CHAT_SETTING_NOTIFICATION=$(byClassName("uiIconGear"));
    public static final SelenideElement ELEMENT_CHAT_PREFERRENCE_POPUP=$(byClassName("uiIconGear"));
    public static final SelenideElement ELEMENT_CHAT_ROOM_NOTIFICATION=  $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[2]/a"));
    public static final SelenideElement ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP=$(byClassName("room-notification-modal"));
    public static final SelenideElement ELEMENT_CHAT_ALERT_ON=$(byValue("keywords"));

    public static final SelenideElement ELEMENT_CHAT_NOTIFICATION_KEYWORD=$(byClassName("notif-keyword"));

    public static final SelenideElement ELEMENT_NOTIFICATION_CONFIRM_BUTTON=  $(byXpath("//*[@id=\"room-detail\"]/div[3]/div/div[2]/div[2]/div[1]"));

    public static final SelenideElement ELEMENT_WIKI_CONTAINER = $(byId("UIWikiMiddleArea"));
    public static final SelenideElement ELEMENT_WIKI_DISCUSSION_CONTAINER = $(byClassName("section-container"));
    public static final SelenideElement ELEMENT_WIKI_ATTENDEE_NAME= $(byXpath("//*[@id=\"chat-application\"]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/div[1]"));
    public static final SelenideElement ELEMENT_WIKI_ATTENDEE_MESSAGE= $(byXpath("//*[@id=\\\"UIViewContentDisplay\\\"]/div[3]/p[3]\""));
    public static final SelenideElement ELEMENT_CHAT_MEETING_NOTE_SAVED = $(byClassName("meetingNotesSent"));
    public static final SelenideElement ELEMENT_CHAT_SHOW_PARTICIPANT= $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[4]/a"));
    public static final SelenideElement ELEMENT_CHAT_SHOW_PARTICIPANT_POPUP = $(byClassName("room-participants"));
}
