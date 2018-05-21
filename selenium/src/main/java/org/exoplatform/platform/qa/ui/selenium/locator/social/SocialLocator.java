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
package org.exoplatform.platform.qa.ui.selenium.locator.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public final class SocialLocator {
  /*****************************************
   * EMAIL NOTIFICATION
   *******************************************/
  // Email's format
  public static final String          ELEMENT_GMAIL_FORMAT_TITLE                                        =
                                                                 ".//*[@valign='middle']//*[contains(text(),'$title')]";

  public static final String          ELEMENT_GMAIL_FORMAT_OPENNING_SUB                                 =
                                                                        ".//*[text()='Hi $firstName,']";

  public static final String          ELEMENT_GMAIL_FORMAT_CONTENT                                      =
                                                                   ".//strong//*[contains(text(),'$fullName')]/../../..//*[contains(.,'$content')]";

  public static final String          ELEMENT_GMAIL_FORMAT_CONTENT_CONNECTION_REQUEST                   =
                                                                                      "//*[contains(@href,'$username')]/../..//*[contains(.,'$content')]";

  public static final String          ELEMENT_GMAIL_FORMAT_CONTENT_SPACE_REQUEST                        =
                                                                                 "//*[contains(@href,'$username')]/../..//*[contains(.,'$content')]//a[contains(text(),'$space')]";

  public static final String          ELEMENT_GMAIL_FORMAT_CONTENT_SPACE_POST_IN_MYSPACE                =
                                                                                         "//*[contains(@href,'$username')]/../../..//*[contains(.,'$content')]//a[contains(text(),'$space')]";

  public static final String          ELEMENT_GMAIL_FORMAT_CONTENT_SPACE_INVITATION                     =
                                                                                    "//p[contains(text(),\"$content\")]//a[contains(text(),'$space')]";

  public static final String          ELEMENT_GMAIL_FORMAT_ACTIVITY_TITLE                               =
                                                                          ".//*[contains(text(),'$title')]";

  public static final String          ELEMENT_GMAIL_FORMAT_ACTIVITY_TITLE_1                             = ".//*[text()='$title']";

  public static final String          ELEMENT_GMAIL_FORMAT_ACTIVITY_LINK                                =
                                                                         "//*[contains(@href,'$link') and contains(@style,'display:inline')]";

  public static final String          ELEMENT_GMAIL_FORMAT_LINK_FILE                                    =
                                                                     "//a[text()='$linkFile']";

  public static final String          ELEMENT_GMAIL_FORMAT_SHOW_DETAIL_BTN                              =
                                                                           ".//*[contains(@data-tooltip,'Show details')]";

  public static final String          ELEMENT_GMAIL_FORMAT_RECIPIENT                                    =
                                                                     "//*[contains(text(),'to:')]/../..//*[@email='$email' and contains(text(),'$userName')]";

  public static final String          ELEMENT_GMAIL_FORMAT_SENDER                                       =
                                                                  "//*[contains(text(),'from:')]/../..//*[@email='$email' and contains(text(),'$userName')]";

  public static final String          ELEMENT_GMAIL_FORMAT_USER_LINK                                    =
                                                                     "(//*[contains(@href,'$userName') and text()='$fullName'])[1]";

  public static final String          ELEMENT_GMAIL_FORMAT_PORTAL_LINK                                  =
                                                                       "(//*[contains(@href,'eXo')])[2]";

  public static final String          ELEMENT_GMAIL_FORMAT_SPACE_LINK                                   =
                                                                      "//*[contains(@href,'space') and contains(text(),'$space')]";

  public static final By              ELEMENT_GMAIL_FORMAT_WATCH_VIDEO_LINK                             =
                                                                            By.xpath("//*[contains(@href,'view_full_activity') and contains(normalize-space(),'Watch the video')]");

  // New User notification
  public static final String          ELEMENT_GMAIL_NEWUSER                                             =
                                                            ".//span[contains(.,'${title} ${title} has joined eXo')]";

  // Post in friend's Activity Stream
  public static final String          ELEMENT_GMAIL_POST_IN_ACTIVITY_STREAM                             =
                                                                            ".//span[contains(.,'$fullName has posted on your activity stream: $content')]";

  public static final String          ELEMENT_GMAIL_POST_IN_AC_USER_LINK                                =
                                                                         ".//*[contains(@href,'$username')]";

  public static final By              ELEMENT_GMAIL_POST_IN_AC_REPLY_BTN                                =
                                                                         By.xpath(".//*[contains(@href,'reply_activity')]");

  public static final By              ELEMENT_GMAIL_POST_IN_AC_VIEW_FULL_BTN                            =
                                                                             By.xpath(".//*[contains(@href,'view_full_activity')]");

  // Post in Space activity stream
  public static final String          ELEMENT_GMAIL_TITLE_POST_IN_SPACE_AS                              =
                                                                           ".//h2[contains(.,'$fullName has posted an activity in the $spaceName space: $content')]";

  public static final String          ELEMENT_GMAIL_HEADER_POST_IN_SPACE_AS                             =
                                                                            ".//td[contains(text(),'New post in $spaceName')]";

  public static final String          ELEMENT_GMAIL_CONTENT_POST_IN_SPACE_AS_1                          =
                                                                               ".//a[contains(@href,'$userName')]/../../..//*[contains(.,'has posted an activity in the')]//*[contains(text(),'$spaceName')]/../../..//*[contains(.,'space. See the post below:')]";

  public static final String          ELEMENT_GMAIL_CONTENT_POST_IN_SPACE_AS_2                          =
                                                                               ".//*[text()='$content']";

  // Comment an Activity Email notification
  public static final String          ELEMENT_GMAIL_TITLE                                               =
                                                          ".//span[contains(.,\"$title\")]";

  public static final String          ELEMENT_GMAIL_TITLE_WITH_INDEX                                    =
                                                                     "(.//span[contains(.,'$title')])[$num]";

  public static final By              ELEMENT_GMAIL_REPLY_BTN                                           =
                                                              By.xpath(".//*[contains(@href,'reply_activity')]");

  public static final By              ELEMENT_GMAIL_VIEW_FULL_BTN                                       =
                                                                  By.xpath(".//*[contains(@href,'view_full_activity')]");

  // Activity portlet
  public static final String          ELEMENT_ACTIVITY_TITLE_CONTENT                                    =
                                                                     ".//*[@id='boxContainer']//*[contains(text(),'$text')]";

  public static final By              ELEMENT_ACITIVITY_AUTHOR_AVATAR                                   =
                                                                      By.xpath(".//*[@id='UIUserActivityStreamPortlet']//img");

  public static final By              ELEMENT_ACTIVITY_AUTHOR_NAME                                      =
                                                                   By.xpath(".//*[@id='UIUserActivityStreamPortlet']//*[contains(@class,'author')]");

  public static final By              ELEMENT_ACTIVITY_ICON_COMMENT                                     =
                                                                    By.xpath(".//*[@id='UIUserActivityStreamPortlet']//*[contains(@class,'uiIconComment')]");

  public static final By              ELEMENT_ACTIVITY_ICON_LIKE                                        =
                                                                 By.xpath(".//*[@id='UIUserActivityStreamPortlet']//*[contains(@class,'uiIconThumbUp ')]");

  public static final By              ELEMENT_ACTIVITY_COMMENT_BOX                                      =
                                                                   By.xpath(".//*[contains(@class,'commentList ')]");

  public static final String          ELEMENT_ACTIVITY_COMMENT_HIGHLIGHT                                =
                                                                         ".//*[@class='contentComment'][contains(.,'$comment')]/../../..[contains(@style,'rgb(240, 240, 240)')]";

  public static final String          ELEMENT_ACTIVITY_COMMENT_CONTENT                                  =
                                                                       ".//*[@class='contentComment'][contains(.,'$comment')]";

  public static final By              ELEMENT_COMMENTBOX                                                =
                                                         By.xpath("//*[@class='replaceTextArea editable']");

  public static final By              ELEMENT_COMMENT_BUTTON                                            =
                                                             By.xpath("//button[contains(@id,'CommentButton')]");

  public static final By              ELEMENT_ACTIVITY_ADD_YOUR_COMMENTLABEL                            =
                                                                             By.xpath("//*[contains(@id,'DisplayCommentTextarea')]/../div[@class='placeholder']");

  public static final String          ELEMENT_DELETE_COMMENT_BUTTON                                     =
                                                                    "//*[@class='contentComment'  and contains(text(),\"${commentText}\")]/../../a[contains(@id,'DeleteCommentButton')]";

  public static final By              ELEMENT_ACTIVITY_NOT_FOUND                                        =
                                                                 By.xpath(".//*[@id='UIUserActivityStreamPortlet']//*[contains(text(),'Activity not found')]");

  // Activity answer
  public static final String          ELEMENT_ACTIVITY_ANSWER_POINT_NUMBER                              =
                                                                           ".//*[contains(@class,'sumaryPoint')]//*[contains(text(),'$number')]";

  public static final By              ELEMENT_ACTIVITY_ANSWER_RATE_NOT_VALUE                            =
                                                                             By.xpath(".//*[@data-original-title='no value yet']");

  public static final String          ELEMENT_ACTIVITY_ANSWER_NUMBER                                    =
                                                                     ".//*[contains(text(),'$number answer')]";

  public static final By              ELEMENT_ACTIVITY_ANSWER_COMMENT_NO                                =
                                                                         By.xpath(".//*[contains(text(),'No Comment')]");

  public static final String          ELEMENT_ACTIVITY_ANSWER_COMMENT_NUMBER                            =
                                                                             ".//*[contains(text(),'$number Comment')]";

  // Activity has uploaded file
  public static final By              ELEMENT_ACTIVITY_UPLOADED_FILE_SIZE                               =
                                                                          By.xpath(".//*[@class='versionFile'][contains(text(),'File Size')]");

  public static final String          ELEMENT_ACTIVITY_UPLOAD_FILE_NAME                                 =
                                                                        ".//*[contains(@class,'text')]//*[@data-original-title='$fileName']";

  public static final By              ELEMENT_ACTIVITY_UPLOAD_FILE_THUMBNAIL                            =
                                                                             By.xpath(".//*[contains(@class,'mediaContent')]//img");

  public static final By              ELEMENT_ACTIVITY_UPLOAD_FILE_VIEW_ICON                            =
                                                                             By.xpath(".//*[contains(@class,'uiIconWatch')]");

  public static final By              ELEMENT_ACTIVITY_UPLOAD_FILE_DOWNLOAD                             =
                                                                            By.xpath(".//*[contains(@class,'actionBar')]//*[contains(@href,'portal')]");

  // Activity Topic
  public static final String          ELEMENT_ACTIVITY_TOPIC_DESCRIPTION                                =
                                                                         ".//*[contains(@class,'text')][contains(text(),'$des')]";

  public static final By              ELEMENT_ACTIVITY_TOPIC_REPLY_ICON                                 =
                                                                        By.xpath(".//*[contains(@class,'uiIconReply')]");

  public static final By              ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY_ICON                       =
                                                                                  By.xpath(".//*[contains(@class,'uiIconSocLastestReply')]");

  public static final By              ELEMENT_ACTIVITY_TOPIC_NO_REPLY                                   =
                                                                      By.xpath(".//*[contains(@class,'contentForum')]//*[contains(text(),'No reply')]");

  public static final String          ELEMENT_ACTIVITY_TOPIC_REPLY_NUMBER                               =
                                                                          ".//*[contains(@class,'contentForum')]//*[contains(text(),'$number reply')]";

  public static final By              ELEMENT_TITLE_POST                                                = By.id("PostTitle");

  public static final By              ELEMENT_POST_CONTENT                                              =
                                                           By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");

  public static final By              ELEMENT_POST_FORM_SUBMIT                                          =
                                                               By.xpath("//*[@id='UIPostForm']//*[contains(text(),'Submit')]");

  public static final String          ELEMENT_POST_IN_TOPIC                                             =
                                                            "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@class='postContent']//*[contains(text(),'{$content}')]";

  // Activiti Wiki
  public static final By              ELEMENT_ACTIVITY_WIKI_VERSION                                     =
                                                                    By.xpath(".//*[contains(@class,'versionLabel')]");

  public static final String          ELEMENT_ACTIVITY_WIKI_DESCRIPTION                                 =
                                                                        ".//*[@class='text'][contains(text(),'$des')]";

  /*****************************************
   * INTRANET NOTIFICATION
   *******************************************/
  public static final By              ELEMENT_NOTIFICATION_POP_UP                                       =
                                                                  By.id("NotificationPopup");

  public static final String          ELEMENT_USER_AVATAR                                               =
                                                          "//*[contains(@alt,'${userName}')]";

  // Notification popup list. Here, $name parameter is fullName or space's name
  public static final String          ELEMENT_NOTIFICATION_POPUP_MENTION                                =
                                                                         ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'mentioned')]//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_REQUEST_CONNECT                        =
                                                                                 ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'connect')]//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_COMMENT                                =
                                                                         ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'commented')]/../..//*[@class='content'][contains(.,'$activity')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_NEW_USER_JOIN_INTRANET                 =
                                                                                        ".//*[@id='NotificationPopup']//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_LIKE                                   =
                                                                      ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'likes')]//*[contains(@class,'user-name')][contains(text(),'$user')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_ACCEPT_REQUEST_CONNECT                 =
                                                                                        ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'connected')]//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_ACCEPT_INVITE_SPACE                    =
                                                                                     ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'joined space')]//*[contains(@class,'text-bold')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_INVITE_SPACE                           =
                                                                              ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'invited')]//*[contains(@class,'text-bold')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_JOIN_SPACE                             =
                                                                            ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'joined space')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_POST_IN_SPACE                          =
                                                                               ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'posted')]/.//*[contains(text(),'$space')]/..//*[contains(@href,'javascript:void(0)')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_POST_IN_MY_ACTIVITY                    =
                                                                                     ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'posted')][contains(text(),'activity stream')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_REQUEST_JOIN_SPACE                     =
                                                                                    ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'requested')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_USER                                =
                                                                         ".//*[@id='NotificationPopup']//*[contains(@class,'user-name')][contains(text(),'$user')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER                        =
                                                                                 ".//*[contains(@class,'badgeNotification')][contains(text(),'$num')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_AVATAR                              =
                                                                           ".//*[@id='NotificationPopup']//*[contains(@class,'avatarXSmall')]//*[contains(@alt,'$lastUser')]";

  public static final By              ELEMENT_VIEW_ALL                                                  = By.linkText("View All");

  public static final String          ELEMENT_INTRANET_NOTIFICATION_STATUS                              =
                                                                           ".//*[@class='status'][contains(.,'$status')]//*[contains(@class,'user-name')][contains(text(),'$fullName')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_STATUS_SPACE                        =
                                                                                 ".//*[@class='status'][contains(.,\"$status\")]//*[contains(@class,'text-bold')][contains(text(),'$space')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_STATUS_ORDER                        =
                                                                                 "(.//*[@class='status'])[$num][contains(.,'$status')]//*[contains(text(),'$fullName')]";

  public static final String          ELEMENT_NOTIFICATION_SETTINGS_TITLE                               =
                                                                          ".//*[@id='uiNotificationSetting']//h3[text()='Notification Settings']";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_UNREAD                              =
                                                                           ".//*[contains(@class,'unread')][contains(.,'$status')]//*[contains(@class,'user-name')][contains(text(),'$fullName')]";

  public static final By              ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ                    =
                                                                                     By.xpath("//*[@id=\"NotificationPopup\"]/li[3]/a");

  public static final String          ELEMENT_INTRANET_NOTIFICATION_REMOVE_ICON                         =
                                                                                "(.//*[@id='NotificationPopup']//*[contains(@class,'uiIconClose')])[$num]";

  public static final By              ELEMENT_INTRANET_NOTIFICATION_EMPTY_LIST                          =
                                                                               By.xpath(".//*[@id='NotificationPopup']//*[contains(@class,'no-items')][contains(text(),'No notifications')]");

  // All notification page list
  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_MENTION                             =
                                                                            ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'mentioned')]//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_REQUEST_CONNECT                     =
                                                                                    ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'connect')]//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_COMMENT                             =
                                                                            ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'commented')]/../..//*[@class='content'][contains(.,'$activity')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_NEW_USER_JOIN_INTRANET              =
                                                                                           ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'has joined')]//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_LIKE                                =
                                                                         ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'likes')]//*[contains(@class,'user-name')][contains(text(),'$user')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_ACCEPT_REQUEST_CONNECT              =
                                                                                           ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'connected')]//*[contains(@class,'user-name')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_ACCEPT_INVITE_SPACE                 =
                                                                                        ".//*[@id='UIIntranetNotificationsPortlet']//*[contains(.,'joined')]//*[contains(text(),'$space')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_INVITE_SPACE                        =
                                                                                 ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'invited')]//*[contains(@class,'text-bold')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_JOIN_SPACE                          =
                                                                               ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'joined space')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_POST_IN_SPACE                       =
                                                                                  ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'posted')]/.//*[contains(text(),'$space')]/..//*[contains(@href,'javascript:void(0)')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_POST_IN_MY_ACTIVITY                 =
                                                                                        ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'posted')][contains(text(),'activity stream')]//*[contains(@href,'javascript:void(0)')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_REQUEST_JOIN_SPACE                  =
                                                                                       ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'requested')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  // Activity detail
  public static final By              ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER                           =
                                                                              By.xpath(".//*[@id='UIActivitiesLoader']");

  public static final By              ELEMENT_NOTIFICATION_UI_SPACE_ACCESS_PORTLET                      =
                                                                                   By.xpath(".//*[@id='UISpaceAccessPortlet']");

  public static final String          ELEMENT_NOTIFICATION_ACTIVITY_TITLE_CONTENT                       =
                                                                                  ".//*[@id='boxContainer']//*[contains(text(),'$text')]";

  // comment
  public static final String          ELEMENT_INTRANET_NOTIFICATION_COMMENTS_CONTENT                    =
                                                                                     ".//*[@id='NotificationPopup']//*[contains(@class,'status')][contains(.,'$comment')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_TITLE                      =
                                                                                   ".//*[@id='NotificationPopup']//*[@class='content'][contains(.,'$title')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_COMMENT_HIGHLIGHT          =
                                                                                               ".//*[@class='contentComment'][contains(.,'$comment')]/../../..[contains(@style,'rgb(240, 240, 240)')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_COMMENT_CONTENT            =
                                                                                             ".//*[@class='contentComment'][contains(.,'$comment')]";

  public static final String          ELEMENT_COMMENT_HIGHLIGHTED                                       =
                                                                  "//*[@style = 'background-color: rgb(240, 240, 240);']//*[contains(text(),'${comment}')]";

  public static final String          ELEMENT_COMMENT_ACTIVITY_VIEWER                                   =
                                                                      "//*[contains(text(),'${comment}')]/../..//*[@class='author']/*[contains(@href,'${userName}')]";

  // Connection
  public static final String          ELEMENT_CONNECT_NOTIFICATION                                      =
                                                                   "//*[contains(text(),'${fullName}')]/../..//*[contains(.,'wants to connect with you')]";

  public static final String          ELEMENT_CONNECT_ACCEPT_BUTTON                                     =
                                                                    ".//*[contains(text(),'$name')]/../..//*[contains(@class,'action-item')]";

  public static final String          ELEMENT_CONNECT_REFUSE_BUTTON                                     =
                                                                    ".//*[contains(text(),'$name')]/../..//*[contains(@class,'cancel-item')]";

  // Space Notification
  public static final String          ELEMENT_SPACE_INVITATION                                          =
                                                               "//*[contains(@alt,'${space}')]/../..//*[contains(text(),'${space}')]";

  public static final String          ELEMENT_MSG_SPACE_INVITATION_CLICK_ON_NOTIFICATION                =
                                                                                         "You are invited to join the space ${space} by the administrator.";

  // Request to join space
  public static final String          ELEMENT_REQUEST_JOIN_SPACE_JUST_NOW                               =
                                                                          "//*[contains(@alt,'${userName}')]/../..//*[contains(text(),'has requested access to')]//*[contains(text(),'${space}')]/../..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";

  public static final String          ELEMENT_RESQUEST_JOIN_SPACE                                       =
                                                                  "{username} has requested access to {space} space.";

  public static final String          ELEMENT_REQUEST_JOIN_SPACE_NO_TIME                                =
                                                                         "//*[contains(@alt,'${userName}')]/../..//*[contains(text(),'has requested access to')]//*[contains(text(),'${space}')]";

  public static final String          ELEMENT_LIKE_IN_ACTIVITY_VIEWER                                   =
                                                                      "//*[contains(@id,'LikeLink')]";

  // All notification list
  public static final By              ELEMENT_ALL_NOTIFICATIONS                                         =
                                                                By.xpath(".//*[@id='UIIntranetNotificationsPortlet']//*[text()='All Notifications']");

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ALL_AVATAR                          =
                                                                               ".//*[@id='UIIntranetNotificationsPortlet']//*[contains(@class,'avatarXSmall')]//*[contains(@alt,'$lastUser')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ALL_USER                            =
                                                                             ".//*[@id='UIIntranetNotificationsPortlet']//*[contains(@class,'user-name')][contains(text(),'$user')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ALL_COMMENTS_CONTENT                =
                                                                                         ".//*[@id='UIIntranetNotificationsPortlet']//*[contains(@class,'status')][contains(.,'$comment')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ALL_ACTIVITY_TITLE                  =
                                                                                       ".//*[@id='NotificationPopup']//*[@class='content'][contains(.,'$title')]";

  public static final By              ELEMENT_NOTIFICATION_SETTINGS_LINK                                =
                                                                         By.xpath(".//*[@id='UIIntranetNotificationsPortlet']//*[contains(text(),'Notification Settings')]");

  // Detail an activity
  public static final String          ELEMENT_INTRANET_NOTIFICATION_DETAIL_ACTIVITY_DES                 =
                                                                                        ".//*[@class='description'][contains(text(),'$des')]";

  /************************************************
   * ADMIN NOTIFICATION
   ****************************************************************************/
  public static final String          ELEMENT_BELONGS_TO_CATEGORY                                       =
                                                                  "//*[contains(text(),'$category')]/following::*//*[@for='$notification']";

  // Disable notification's type
  public static final By              ELEMENT_ADMIN_NOTIFICATION_NEW_USER_DISBALE                       =
                                                                                  By.xpath(".//*[@id='NewUserPlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_DISBALE             =
                                                                                            By.xpath(".//*[@id='RelationshipReceivedRequestPlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_DISBALE               =
                                                                                          By.xpath(".//*[@id='ActivityCommentPlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_DISBALE                  =
                                                                                       By.xpath(".//*[@id='LikePlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_DISBALE               =
                                                                                          By.xpath(".//*[@id='ActivityMentionPlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_POST_DISBALE                  =
                                                                                       By.xpath(".//*[@id='PostActivityPlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_INVITATION_DISBALE               =
                                                                                          By.xpath(".//*[@id='SpaceInvitationPlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_JOIN_DISBALE                     =
                                                                                    By.xpath(".//*[@id='RequestJoinSpacePlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_POST_DISBALE                     =
                                                                                    By.xpath(".//*[@id='PostActivitySpaceStreamPlugin']//*[@class=\"\"]//*[contains(text(),'No notifications')]");

  // Enable notification's type
  public static final By              ELEMENT_ADMIN_NOTIFICATION_NEW_USER_ENABLE_EMAIL                  =
                                                                                       By.xpath(".//*[@id='NewUserPlugin']//*[@class=\"\"]//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_ENABLE_EMAIL        =
                                                                                                 By.xpath(".//*[@id='RelationshipReceivedRequestPlugin']//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_ENABLE_EMAIL          =
                                                                                               By.xpath(".//*[@id='ActivityCommentPlugin']//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_ENABLE_EMAIL             =
                                                                                            By.xpath(".//*[@id='LikePlugin']//*[@class=\"\"]//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_ENABLE_EMAIL          =
                                                                                               By.xpath(".//*[@id='ActivityMentionPlugin']//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_POST_ENABLE_EMAIL             =
                                                                                            By.xpath(".//*[@id='PostActivityPlugin']//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_INVITATION_ENABLE_EMAIL          =
                                                                                               By.xpath(".//*[@id='SpaceInvitationPlugin']//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_JOIN_ENABLE_EMAIL                =
                                                                                         By.xpath(".//*[@id='RequestJoinSpacePlugin']//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_POST_ENABLE_EMAIL                =
                                                                                         By.xpath(".//*[@id='PostActivitySpaceStreamPlugin']//*[@class='MAIL_CHANNEL ']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_NEW_USER_ENABLE_INTRANET               =
                                                                                          By.xpath(".//*[@id='NewUserPlugin']//*[@class=\"\"]//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_ENABLE_INTRANET     =
                                                                                                    By.xpath(".//*[@id='RelationshipReceivedRequestPlugin']//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_ENABLE_INTRANET       =
                                                                                                  By.xpath(".//*[@id='ActivityCommentPlugin']//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_ENABLE_INTRANET          =
                                                                                               By.xpath(".//*[@id='LikePlugin']//*[@class=\"\"]//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_ENABLE_INTRANET       =
                                                                                                  By.xpath(".//*[@id='ActivityMentionPlugin']//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_POST_ENABLE_INTRANET          =
                                                                                               By.xpath(".//*[@id='PostActivityPlugin']//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_INVITATION_ENABLE_INTRANET       =
                                                                                                  By.xpath(".//*[@id='SpaceInvitationPlugin']//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_JOIN_ENABLE_INTRANET             =
                                                                                            By.xpath(".//*[@id='RequestJoinSpacePlugin']//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SPACE_POST_ENABLE_INTRANET             =
                                                                                            By.xpath(".//*[@id='PostActivitySpaceStreamPlugin']//*[@class='WEB_CHANNEL ']//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE                            =
                                                                             By.xpath("//*[@id='notificationAdmin']/h3");

  public static final By              ELEMENT_NOTIFICATION_GRID_TITLE                                   =
                                                                      By.xpath("//*[@id='notificationAdmin']//th[contains(text(),'Notification')]");

  public static final By              ELEMENT_TITLE_NOTIFICATION_GRID                                   =
                                                                      By.xpath("//*[@id='notificationAdmin']//th[contains(text(),'Title')]");

  public static final By              ELEMENT_ENABLE_NOTIFICATION_GRID                                  =
                                                                       By.xpath("//*[@id='notificationAdmin']//th[contains(text(),'Enable')]");

  // Notification sender area
  public static final By              ELEMENT_ADMIN_NOTIFICATION_SENDER_NAME                            =
                                                                             By.xpath(".//*[@id='senderName']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SENDER_ADDRESS                         =
                                                                                By.xpath(".//*[@id='senderEmail']");

  public static final By              ELEMENT_ADMIN_NOTIFICATION_SENDER_SAVE_BTN                        =
                                                                                 By.xpath(".//*[@id='btSetSender']");

  public static final By              ELEMENT_NOTIFICATION_SENDER_ERROR_MESSAGE_INVALID_EMAIL           =
                                                                                              By.xpath(".//*[@id='confirmMessage']//*[contains(text(),'Cannot update the sender information: empty value or the format is not correct.')]");

  public static final String          ELEMENT_NOTIFICATION_SENDER_SUCCESS_MESSAGE                       =
                                                                                  ".//*[@id='confirmMessage']//*[contains(text(),'Notifications will now be sent to your users from \"$name\"<$email>')]";

  // Notifications
  // New User
  public static final By              ELEMENT_CHECK_BUTTON_CONNECT_NOTIFICATION                         =
                                                                                By.xpath("//*[@name='RelationshipReceivedRequestPlugin']");

  public static final By              ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN                            =
                                                                             By.xpath(".//*[@id='NewUserPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX                      =
                                                                                   By.xpath(".//*[@id='MAIL_CHANNELNewUserPlugin']");

  public static final By              ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX                   =
                                                                                      By.xpath(".//*[@id='WEB_CHANNELNewUserPlugin']");

  public static final By              ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX_CHECKED              =
                                                                                           By.xpath(".//*[@id='MAIL_CHANNELNewUserPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX_CHECKED           =
                                                                                              By.xpath(".//*[@id='WEB_CHANNELNewUserPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE                         =
                                                                                By.xpath(".//*[@id='NewUserPlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE                      =
                                                                                   By.xpath(".//*[@id='NewUserPlugin']//*[contains(@class,'WEB_CHANNEL')]");

  // Connection request
  public static final By              ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN                            =
                                                                             By.xpath(".//*[@id='btActionNewUserPlugin']");

  public static final By              ELEMENT_CONNECTION_REQUEST_EDIT_BTN                               =
                                                                          By.xpath(".//*[@id='RelationshipReceivedRequestPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX            =
                                                                                             By.xpath(".//*[@id='MAIL_CHANNELRelationshipReceivedRequestPlugin']");

  public static final By              ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED    =
                                                                                                     By.xpath(".//*[@id='MAIL_CHANNELRelationshipReceivedRequestPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX         =
                                                                                                By.xpath(".//*[@id='WEB_CHANNELRelationshipReceivedRequestPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED =
                                                                                                        By.xpath(".//*[@id='WEB_CHANNELRelationshipReceivedRequestPlugin']");

  public static final By              ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_TITLE               =
                                                                                          By.xpath(".//*[@id='RelationshipReceivedRequestPlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_TITLE            =
                                                                                             By.xpath(".//*[@id='RelationshipReceivedRequestPlugin']//*[contains(@class,'WEB_CHANNEL')]");

  public static final By              ELEMENT_CONNECTION_REQUEST_SAVE_BTN                               =
                                                                          By.xpath(".//*[@id='btActionRelationshipReceivedRequestPlugin']");

  // Activity comment
  public static final By              ELEMENT_ACTIVITY_COMMENT_EDIT_BTN                                 =
                                                                        By.xpath(".//*[@id='ActivityCommentPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX              =
                                                                                           By.xpath(".//*[@id='MAIL_CHANNELActivityCommentPlugin']");

  public static final By              ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX_CHECKED      =
                                                                                                   By.xpath(".//*[@id='MAIL_CHANNELActivityCommentPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_ACTIVITY_COMMENT_SAVE_BTN                                 =
                                                                        By.xpath(".//*[@id='btActionActivityCommentPlugin']");

  public static final By              ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_TITLE                 =
                                                                                        By.xpath(".//*[@id='ActivityCommentPlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX           =
                                                                                              By.xpath(".//*[@id='WEB_CHANNELActivityCommentPlugin']");

  public static final By              ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX_CHECKED   =
                                                                                                      By.xpath(".//*[@id='WEB_CHANNELActivityCommentPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_TITLE              =
                                                                                           By.xpath(".//*[@id='ActivityCommentPlugin']//*[contains(@class,'WEB_CHANNEL')]");

  // Space invitation
  public static final By              ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN                    =
                                                                                     By.xpath(".//*[@id='SpaceInvitationPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX_CHECKED      =
                                                                                                   By.xpath(".//*[@id='MAIL_CHANNELSpaceInvitationPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX              =
                                                                                           By.xpath(".//*[@id='MAIL_CHANNELSpaceInvitationPlugin']");

  public static final By              ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN                    =
                                                                                     By.xpath(".//*[@id='btActionSpaceInvitationPlugin']");

  public static final By              ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_TITLE                 =
                                                                                        By.xpath(".//*[@id='SpaceInvitationPlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX_CHECKED   =
                                                                                                      By.xpath(".//*[@id='WEB_CHANNELSpaceInvitationPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX           =
                                                                                              By.xpath(".//*[@id='WEB_CHANNELSpaceInvitationPlugin']");

  public static final By              ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_TITLE              =
                                                                                           By.xpath(".//*[@id='SpaceInvitationPlugin']//*[contains(@class,'WEB_CHANNEL')]");

  /************************************************
   * MY NOTIFICATION
   ****************************************************************************/
  public static final By              ELEMENT_TITLE_NOTIFICATION_SETTING_PAGE                           =
                                                                              By.xpath(".//*[@id='uiNotificationSetting']//h3");

  public static final String          ELEMENT_NOTIFICATION_LABEL_NAME                                   =
                                                                      ".//*[contains(text(),'$label')]";

  // Form my notification setting
  public static final By              ELEMENT_MY_NOTIFICATION_SETTING_FORM                              =
                                                                           By.id("uiNotificationSetting");

  public static final By              ELEMENT_GENERAL_JOIN_INTRANET_GRID                                =
                                                                         By.xpath("//*[@id='uiNotificationSetting']//*[contains(text(),'Someone joins the social intranet')]");

  public static final By              ELEMENT_GENERAL_SEND_CONNECTION_GRID                              =
                                                                           By.xpath("//*[@id='uiNotificationSetting']//*[contains(text(),'Someone sends me a connection request')]");

  public static final By              ELEMENT_RESET_BTN                                                 =
                                                        By.xpath(".//button[@id='Reset']");

  public static final By              ELEMENT_RESET_CONFIRM                                             =
                                                            By.xpath(".//*[@id='UISocialPopupConfirmation']/..//a[contains(text(),'Confirm')]");

  public static final By              ELEMENT_RESET_CANCEL                                              =
                                                           By.xpath(".//*[@id='UISocialPopupConfirmation']/..//a[contains(text(),'Cancel')]");

  public static final By              ELEMENT_RESET_CONFIRM_MSG_ELEMENT                                 =
                                                                        By.xpath("//*[@id='UISocialPopupConfirmation']//*[@class='confirmationIcon contentMessage']");

  public static final String          ELEMENT_RESET_CONFIRM_MSG                                         =
                                                                "All your notification settings will be reset to default values. Your previous settings will be lost.";

  // My notification setting-->Selected box email notification
  public static final String          ELEMENT_NEW_USER_SELECTED_BOX_MAIL_ICON                           =
                                                                              "//*[@id='NewUserPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_CONNECTION_REQ_SELECTED_BOX_MAIL_ICON                     =
                                                                                    "//*[@id='RelationshipReceivedRequestPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_COMMENT_SELECTED_BOX_MAIL_ICON                            =
                                                                             "//*[@id='ActivityCommentPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_MENTION_SELECTED_BOX_MAIL_ICON                            =
                                                                             "//*[@id='ActivityMentionPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final By              ELEMENT_MENTION_SELECTED_BOX_MAIL_ICON_ANY                        =
                                                                                 By.xpath("//*[@id='ActivityMentionPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']");

  public static final String          ELEMENT_LIKE_SELECTED_BOX_MAIL_ICON                               =
                                                                          "//*[@id='LikePlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_POST_SELECTED_BOX_MAIL_ICON                               =
                                                                          "//*[@id='PostActivityPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final By              ELEMENT_POST_SELECTED_BOX_MAIL_ICON_ANY                           =
                                                                              By.xpath("//*[@id='PostActivityPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']");

  public static final String          ELEMENT_INVI_SPACE_SELECTED_BOX_MAIL_ICON                         =
                                                                                "//*[@id='SpaceInvitationPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_JOIN_SPACE_SELECTED_BOX_MAIL_ICON                         =
                                                                                "//*[@id='RequestJoinSpacePlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_POST_SPACE_SELECTED_BOX_MAIL_ICON                         =
                                                                                "//*[@id='PostActivitySpaceStreamPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final By              ELEMENT_POST_SPACE_SELECTED_BOX_MAIL_ICON_ANY                     =
                                                                                    By.xpath("//*[@id='PostActivitySpaceStreamPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']");

  // My notification setting-->Checked box email notification
  public static final By              ELEMENT_NEW_USER_MAIL_ICON                                        =
                                                                 By.xpath("//*[@id='NewUserPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_CONNECTION_REQ_MAIL_ICON                                  =
                                                                       By.xpath("//*[@id='RelationshipReceivedRequestPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_COMMENT_MAIL_ICON                                         =
                                                                By.xpath("//*[@id='ActivityCommentPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_MENTION_MAIL_ICON                                         =
                                                                By.xpath("//*[@id='ActivityMentionPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_LIKE_MAIL_ICON                                            =
                                                             By.xpath("//*[@id='LikePlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_POST_MAIL_ICON                                            =
                                                             By.xpath("//*[@id='PostActivityPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_INVITATION_SPACE_MAIL_ICON                                =
                                                                         By.xpath("//*[@id='SpaceInvitationPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_JOIN_REQ_SPACE_MAIL_ICON                                  =
                                                                       By.xpath("//*[@id='RequestJoinSpacePlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  public static final By              ELEMENT_POST_SPACE_MAIL_ICON                                      =
                                                                   By.xpath("//*[@id='PostActivitySpaceStreamPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-checkbox']//*[@class='uiIconPLFMail']");

  // My notification setting-->intranet notificaiton
  public static final By              ELEMENT_NEW_USER_INTRANET_ICON                                    =
                                                                     By.xpath("//*[@id='NewUserPlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_CONNECTION_REQ_INTRANET_ICON                              =
                                                                           By.xpath("//*[@id='RelationshipReceivedRequestPlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_COMMENT_INTRANET_ICON                                     =
                                                                    By.xpath("//*[@id='ActivityCommentPlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_MENTION_INTRANET_ICON                                     =
                                                                    By.xpath("//*[@id='ActivityMentionPlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_LIKE_INTRANET_ICON                                        =
                                                                 By.xpath("//*[@id='LikePlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_POST_INTRANET_ICON                                        =
                                                                 By.xpath("//*[@id='PostActivityPlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_INVITATION_SPACE_INTRANET_ICON                            =
                                                                             By.xpath("//*[@id='SpaceInvitationPlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON                              =
                                                                           By.xpath("//*[@id='RequestJoinSpacePlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  public static final By              ELEMENT_POST_SPACE_INTRANET_ICON                                  =
                                                                       By.xpath("//*[@id='PostActivitySpaceStreamPlugin']/../..//*[contains(@class,'status-true')]//*[@class='uiIconPLFWeb']");

  // Someones join the social intranet
  public static final By              ELEMENT_MY_NOTIFICATION_NEW_USER_PLUGIN                           =
                                                                              By.xpath(".//*[@for='NewUserPlugin']");

  public static final By              ELEMENT_NEWUSER_ICON_EMAIL_NOTIFICATION                           =
                                                                              By.xpath(".//*[contains(@for,'NewUserPlugin')]/../..//*[contains(@class,'uiIconPLFMail')]");

  public static final By              ELEMENT_NEWUSER_ICON_INTRANET_NOTIFICATION                        =
                                                                                 By.xpath(".//*[contains(@for,'NewUserPlugin')]/../..//*[contains(@class,'uiIconPLFWeb')]");

  // Connection request
  public static final By              ELEMENT_MY_NOTIFICATION_CONNECTION_REQUEST_PLUGIN                 =
                                                                                        By.xpath(".//*[@for='RelationshipReceivedRequestPlugin']");

  public static final By              ELEMENT_CONNECTION_REQUEST_ICON_EMAIL_NOTIFICATION                =
                                                                                         By.xpath(".//*[contains(@for,'RelationshipReceivedRequestPlugin')]/../..//*[contains(@class,'uiIconPLFMail')]");

  public static final By              ELEMENT_CONNECTION_REQUEST_ICON_INTRANET_NOTIFICATION             =
                                                                                            By.xpath(".//*[contains(@for,'RelationshipReceivedRequestPlugin')]/../..//*[contains(@class,'uiIconPLFWeb')]");

  // Space invitation
  public static final By              ELEMENT_MY_NOTIFICATION_SPACE_INVITATION_PLUGIN                   =
                                                                                      By.xpath(".//*[@for='SpaceInvitationPlugin']");

  public static final By              ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_ICON                  =
                                                                                       By.xpath(".//*[contains(@for,'SpaceInvitationPlugin')]/../..//*[contains(@class,'uiIconPLFMail')]");

  public static final By              ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_ICON               =
                                                                                          By.xpath(".//*[contains(@for,'SpaceInvitationPlugin')]/../..//*[contains(@class,'uiIconPLFWeb')]");

  // Activity comment
  public static final By              ELEMENT_MY_NOTIFICATION_ACTIVITY_COMMENT_PLUGIN                   =
                                                                                      By.xpath(".//*[@for='ActivityCommentPlugin']");

  public static final By              ELEMENT_ACTIVITY_COMMENT_ICON_INTRANET_NOTIFICATION               =
                                                                                          By.xpath(".//*[contains(@for,'ActivityCommentPlugin')]/../..//*[contains(@class,'uiIconPLFWeb')]");

  // SWITCH ON/OFF EMAIL/INTRANET notification
  public static final By              ELEMENT_SWITCH_ONOFF_MAIL_BTN                                     =
                                                                    By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelMAIL_CHANNEL']/..");

  public static final By              ELEMENT_SWITCH_ONOFF_EMAIL_ON_AND_LABEL                           =
                                                                              By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelMAIL_CHANNEL'][contains(@checked,'checked')]/../../..//*[contains(text(),'Notify me by email')]");

  public static final By              ELEMENT_SWITCH_ONOFF_WEB_ON_AND_LABEL                             =
                                                                            By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelWEB_CHANNEL'][contains(@checked,'checked')]/../../..//*[contains(text(),'Notify me on-site')]");

  // disable notification's type
  public static final By              ELEMENT_SWITCH_ONOFF_WEB_BTN                                      =
                                                                   By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelWEB_CHANNEL']/..");

  public static final By              ELEMENT_SWITCH_ONOFF_MAIL_ON                                      =
                                                                   By.xpath("//*[@class='uiSwitchBtn']//input[@name='channelMAIL_CHANNEL'][contains(@checked,'checked')]");

  public static final By              ELEMENT_SWITCH_ONOFF_MAIL_OFF                                     =
                                                                    By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelMAIL_CHANNEL']");

  public static final By              ELEMENT_SWITCH_ONOFF_WEB_ON                                       =
                                                                  By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelWEB_CHANNEL'][contains(@checked,'checked')]");

  public static final By              ELEMENT_SWITCH_ONOFF_WEB_OFF                                      =
                                                                   By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelWEB_CHANNEL']");

  public static final By              ELEMENT_TABLE_FOLLOWING_SWITCH_ONOFF                              =
                                                                           By.xpath("//*[@class='uiSwitchBtn']/*[@name='channelWEB_CHANNEL']/../../../../../..//following::*//*[contains(@class,'table-striped')]");

  public static final By              ELEMENT_SWITCH_ONOFF_WEB_LABEL                                    =
                                                                     By.xpath("//*[@name='channelWEB_CHANNEL']/../../..//*[contains(text(),'Notify me on-site')]");

  public static final By              ELEMENT_SWITCH_ONOFF_MAIL_LABEL                                   =
                                                                      By.xpath("//*[@name='channelMAIL_CHANNEL']/../../..//*[contains(text(),'Notify me by email')]");

  public static final By              ELEMENT_MAIL_VIEWMODE_FALSE                                       =
                                                                  By.xpath(".//*[contains(@class,'view-mode status-false')]//*[contains(@class,'Mail')]");

  public static final By              ELEMENT_WEB_VIEWMODE_FALSE                                        =
                                                                 By.xpath(".//*[contains(@class,'view-mode status-false')]//*[contains(@class,'PLFWeb')]");

  public static final By              ELEMENT_MAIL_VIEWMODE_TRUE                                        =
                                                                 By.xpath(".//*[contains(@class,'view-mode status-true')]//*[contains(@class,'Mail')]");

  public static final By              ELEMENT_WEB_VIEWMODE_TRUE                                         =
                                                                By.xpath(".//*[contains(@class,'view-mode status-true')]//*[contains(@class,'PLFWeb')]");

  public static final By              ELEMENT_COLUMN_NOTIFYME                                           =
                                                              By.xpath("//*[@id='uiNotificationSetting']//*[contains(text(),'Notify me when')]");

  public static final By              ELEMENT_COLUMN_HOWTO                                              =
                                                           By.xpath("//*[@id='uiNotificationSetting']//*[contains(text(),'How to get notifications')]");

  // New user
  public static final By              ELEMENT_EDIT_NEWUSER_ICON                                         =
                                                                By.xpath("//*[@id='NewUserPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX                                =
                                                                         By.xpath("//*[@for='MAIL_CHANNELNewUserPlugin']");

  public static final By              ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX_CHECKED                        =
                                                                                 By.xpath("//*[@id='MAIL_CHANNELNewUserPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX                                 =
                                                                        By.xpath("//*[@id='WEB_CHANNELNewUserPlugin']");

  public static final By              ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX_CHECKED                         =
                                                                                By.xpath("//*[@id='WEB_CHANNELNewUserPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_NEWUSER_LIST                                         =
                                                                By.id("MAIL_CHANNELNewUserPluginSelectBox");

  public static final By              ELEMENT_EDIT_NEWUSER_SAVE_BTN                                     =
                                                                    By.xpath("//button[@id='NewUserPlugin']");

  public static final By              ELEMENT_EDIT_NEWUSER_LIST_DAILY                                   =
                                                                      By.xpath("//*[@id='MAIL_CHANNELNewUserPluginSelectBox']/*[contains(text(),'Daily')]");

  public static final By              ELEMENT_EDIT_NEWUSER_LIST_WEEKLY                                  =
                                                                       By.xpath("//*[@id='MAIL_CHANNELNewUserPluginSelectBox']/*[contains(text(),'Weekly')]");

  public static final By              ELEMENT_EDIT_NEWUSER_LIST_NEVER                                   =
                                                                      By.xpath("//*[@id='MAIL_CHANNELNewUserPluginSelectBox']/*[contains(text(),'Never')]");

  // Connections
  public static final By              ELEMENT_EDIT_RECREQ_ICON                                          =
                                                               By.xpath("//*[@id='RelationshipReceivedRequestPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_RECREQ_MAIL_CHECKBOX                                 =
                                                                        By.xpath("//*[@id='MAIL_CHANNELRelationshipReceivedRequestPlugin']");

  public static final By              ELEMENT_EDIT_RECREQ_MAIL_CHECKBOX_CHECKED                         =
                                                                                By.xpath("//*[@id='MAIL_CHANNELRelationshipReceivedRequestPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_RECREQ_WEB_CHECKBOX                                  =
                                                                       By.xpath("//*[@id='WEB_CHANNELRelationshipReceivedRequestPlugin']");

  public static final By              ELEMENT_EDIT_RECREQ_WEB_CHECKBOX_CHECKED                          =
                                                                               By.xpath("//*[@id='WEB_CHANNELRelationshipReceivedRequestPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_RECREQ_LIST                                          =
                                                               By.id("MAIL_CHANNELRelationshipReceivedRequestPluginSelectBox");

  public static final By              ELEMENT_EDIT_RECREQ_SAVE_BTN                                      =
                                                                   By.xpath("//button[@id='RelationshipReceivedRequestPlugin']");

  // Spaces
  public static final By              ELEMENT_MY_NOTIFICATION_SPACE_REQJOIN_PLUGIN                      =
                                                                                   By.xpath(".//*[@for='RequestJoinSpacePlugin']");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_ICON                                   =
                                                                      By.xpath("//*[@id='RequestJoinSpacePlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX                          =
                                                                               By.xpath("//*[@id='MAIL_CHANNELRequestJoinSpacePlugin']");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX_CHECKED                  =
                                                                                       By.xpath("//*[@id='MAIL_CHANNELRequestJoinSpacePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_WEB_CHECKBOX                           =
                                                                              By.xpath("//*[@id='WEB_CHANNELRequestJoinSpacePlugin']");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_WEB_CHECKBOX_CHECKED                   =
                                                                                      By.xpath("//*[@id='WEB_CHANNELRequestJoinSpacePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_LIST                                   =
                                                                      By.id("MAIL_CHANNELRequestJoinSpacePluginSelectBox");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_SAVE_BTN                               =
                                                                          By.xpath("//button[@id='RequestJoinSpacePlugin']");

  public static final By              ELEMENT_EDIT_INVI_SPACE_ICON                                      =
                                                                   By.xpath("//*[@id='SpaceInvitationPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_INVI_SPACE_MAIL_CHECKBOX                             =
                                                                            By.xpath("//*[@id='MAIL_CHANNELSpaceInvitationPlugin']");

  public static final By              ELEMENT_EDIT_INVI_SPACE_MAIL_CHECKBOX_CHECKED                     =
                                                                                    By.xpath("//*[@id='MAIL_CHANNELSpaceInvitationPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_INVI_SPACE_WEB_CHECKBOX                              =
                                                                           By.xpath("//*[@id='WEB_CHANNELSpaceInvitationPlugin']");

  public static final By              ELEMENT_EDIT_INVI_SPACE_WEB_CHECKBOX_CHECKED                      =
                                                                                   By.xpath("//*[@id='WEB_CHANNELSpaceInvitationPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_INVI_SPACE_LIST                                      =
                                                                   By.id("MAIL_CHANNELSpaceInvitationPluginSelectBox");

  public static final By              ELEMENT_EDIT_INVI_SPACE_SAVE_BTN                                  =
                                                                       By.xpath("//button[@id='SpaceInvitationPlugin']");

  public static final By              ELEMENT_MY_NOTIFICATION_SPACE_POST_PLUGIN                         =
                                                                                By.xpath(".//*[@id='PostActivitySpaceStreamPlugin']");

  // Post on my Space
  public static final By              ELEMENT_EDIT_POST_SPACE_ICON                                      =
                                                                   By.xpath("//*[@id='PostActivitySpaceStreamPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_POST_SPACE_MAIL_CHECKBOX                             =
                                                                            By.xpath("//*[@id='MAIL_CHANNELPostActivitySpaceStreamPlugin']");

  public static final By              ELEMENT_EDIT_POST_SPACE_MAIL_CHECKBOX_CHECKED                     =
                                                                                    By.xpath("//*[@id='MAIL_CHANNELPostActivitySpaceStreamPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_POST_SPACE_WEB_CHECKBOX                              =
                                                                           By.xpath("//*[@id='WEB_CHANNELPostActivitySpaceStreamPlugin']");

  public static final By              ELEMENT_EDIT_POST_SPACE_WEB_CHECKBOX_CHECKED                      =
                                                                                   By.xpath("//*[@id='WEB_CHANNELPostActivitySpaceStreamPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_POST_SPACE_LIST                                      =
                                                                   By.id("MAIL_CHANNELPostActivitySpaceStreamPluginSelectBox");

  public static final By              ELEMENT_EDIT_POST_SPACE_SAVE_BTN                                  =
                                                                       By.xpath("//button[@id='PostActivitySpaceStreamPlugin']");

  // Activity Stream
  public static final By              ELEMENT_MY_NOTIFICATION_ACTIVITY_LIKE_PLUGIN                      =
                                                                                   By.xpath(".//*[@id='LikePlugin']");

  public static final By              ELEMENT_EDIT_LIKE_ICON                                            =
                                                             By.xpath("//*[@id='LikePlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_LIKE_MAIL_CHECKBOX                                   =
                                                                      By.xpath("//*[@id='MAIL_CHANNELLikePlugin']");

  public static final By              ELEMENT_EDIT_LIKE_MAIL_CHECKBOX_CHECKED                           =
                                                                              By.xpath("//*[@id='MAIL_CHANNELLikePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_LIKE_WEB_CHECKBOX                                    =
                                                                     By.xpath("//*[@id='WEB_CHANNELLikePlugin']");

  public static final By              ELEMENT_EDIT_LIKE_WEB_CHECKBOX_CHECKED                            =
                                                                             By.xpath("//*[@id='WEB_CHANNELLikePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_LIKE_LIST                                            =
                                                             By.id("MAIL_CHANNELLikePluginSelectBox");

  public static final By              ELEMENT_EDIT_LIKE_SAVE_BTN                                        =
                                                                 By.xpath("//button[@id='LikePlugin']");

  public static final By              ELEMENT_MY_NOTIFICATION_ACTIVITY_POST_PLUGIN                      =
                                                                                   By.xpath(".//*[@id='PostActivityPlugin']");

  // Post on Activity stream
  public static final By              ELEMENT_EDIT_POST_ICON                                            =
                                                             By.xpath("//*[@id='PostActivityPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_POST_MAIL_CHECKBOX                                   =
                                                                      By.xpath("//*[@id='MAIL_CHANNELPostActivityPlugin']");

  public static final By              ELEMENT_EDIT_POST_MAIL_CHECKBOX_CHECKED                           =
                                                                              By.xpath("//*[@id='MAIL_CHANNELPostActivityPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_POST_WEB_CHECKBOX                                    =
                                                                     By.xpath("//*[@id='WEB_CHANNELPostActivityPlugin']");

  public static final By              ELEMENT_EDIT_POST_WEB_CHECKBOX_CHECKED                            =
                                                                             By.xpath("//*[@id='WEB_CHANNELPostActivityPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_POST_LIST                                            =
                                                             By.id("MAIL_CHANNELPostActivityPluginSelectBox");

  public static final By              ELEMENT_EDIT_POST_SAVE_BTN                                        =
                                                                 By.xpath("//button[@id='PostActivityPlugin']");

  public static final By              ELEMENT_EDIT_COMMENT_ICON                                         =
                                                                By.xpath("//*[@id='ActivityCommentPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_COMMENT_MAIL_CHECKBOX                                =
                                                                         By.xpath("//*[@id='MAIL_CHANNELActivityCommentPlugin']");

  public static final By              ELEMENT_EDIT_COMMENT_MAIL_CHECKBOX_CHECKED                        =
                                                                                 By.xpath("//*[@id='MAIL_CHANNELActivityCommentPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_COMMENT_WEB_CHECKBOX                                 =
                                                                        By.xpath("//*[@id='WEB_CHANNELActivityCommentPlugin']");

  public static final By              ELEMENT_EDIT_COMMENT_WEB_CHECKBOX_CHECKED                         =
                                                                                By.xpath("//*[@id='WEB_CHANNELActivityCommentPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_COMMENT_LIST                                         =
                                                                By.id("MAIL_CHANNELActivityCommentPluginSelectBox");

  public static final By              ELEMENT_EDIT_COMMENT_SAVE_BTN                                     =
                                                                    By.xpath("//button[@id='ActivityCommentPlugin']");

  public static final By              ELEMENT_MY_NOTIFICATION_MENTION_PLUGIN                            =
                                                                             By.xpath(".//*[@id='ActivityMentionPlugin']/..");

  // Mention
  public static final By              ELEMENT_EDIT_MENTION_ICON                                         =
                                                                By.xpath("//*[@id='ActivityMentionPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_MENTION_MAIL_CHECKBOX                                =
                                                                         By.xpath("//*[@id='MAIL_CHANNELActivityMentionPlugin']");

  public static final By              ELEMENT_EDIT_MENTION_MAIL_CHECKBOX_CHECKED                        =
                                                                                 By.xpath("//*[@id='MAIL_CHANNELActivityMentionPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_MENTION_WEB_CHECKBOX                                 =
                                                                        By.xpath("//*[@id='WEB_CHANNELActivityMentionPlugin']");

  public static final By              ELEMENT_EDIT_MENTION_WEB_CHECKBOX_CHECKED                         =
                                                                                By.xpath("//*[@id='WEB_CHANNELActivityMentionPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_MENTION_LIST                                         =
                                                                By.id("MAIL_CHANNELActivityMentionPluginSelectBox");

  public static final By              ELEMENT_EDIT_MENTION_SAVE_BTN                                     =
                                                                    By.xpath("//button[@id='ActivityMentionPlugin']");

  // Categories
  public static final String          ELEMENT_MYNOTIFICATION_SETTING_GROUP                              =
                                                                           "(//*[@class='left'])[$number]//*[contains(text(),'$groupName')]";

  public static final String          ELEMENT_MYNOTIFICATION_SETTING_TYPE                               =
                                                                          "(//*[@class='left'])[$number]//*[@for='$id']";

  /************************************************
   * USER PAGE BASE
   ****************************************************************************/
  // Navigation menu
  public static final By              ELEMENT_HORIZONTAL_TOOLBAR                                        =
                                                                 By.xpath("//*[@id='UIUserNavigationPortlet']/ul");

  public static final By              ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE                      =
                                                                                   By.xpath("//*[@id='UIUserNavigationPortlet']/ul/li[1]//*[@class='uiIconAppprofile uiIconDefaultApp']");

  public static final By              ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES                  =
                                                                                       By.xpath("//*[@id=\"UIUserNavigationPortlet\"]/div[1]/ul/li[2]/a/div");

  public static final By              ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS                  =
                                                                                       By.xpath("//*[@id='UIUserNavigationPortlet']/ul/li[3]//*[@class='uiIconAppconnections uiIconDefaultApp']");

  public static final By              ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI                         =
                                                                                By.xpath("//*[@id=\"UIUserNavigationPortlet\"]/div[1]/ul/li[4]/a/div");

  public static final By              ELEMENT_HORIZONTAL_TOOLBAR_FIFTH_APP_DASHBOARD                    =
                                                                                     By.xpath("//*[@id=\"UIUserNavigationPortlet\"]/div[1]/ul/li[5]/a/div");

  public static final By              ELEMENT_MORE_TAB                                                  =
                                                       By.xpath("//*[@class='nav nav-tabs userNavigation']//*[@class='uiIconAppMoreButton']");

  /************************************************
   * USER PROFILE
   ****************************************************************************/
  public static final String          ELEMENT_USER_NAME_PAGE                                            =
                                                             ".//*[@id='UIBreadCrumbsNavigationPortlet']//*[contains(text(),'$fullName')]";

  public static final By              ELEMENT_EDIT_MY_PROFILE_LINK                                      =
                                                                   By.xpath("//*[@id=\"UIUserNavigationPortlet\"]/div[1]/div/div/ul/li/a");

  public static final By              ELEMENT_EDIT_MY_PROFILE_BUTTON                                    =
                                                                     By.xpath("//*[@id='UIExperienceProfilePortlet']//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_PROFILE_FORM                                         =
                                                                By.id("UIEditUserProfileForm");

  public static final String          ELEMENT_NAME_OF_PROFILE_TOP_LEFT                                  =
                                                                       "//*[@id='UIBreadCrumbsNavigationPortlet']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_NAME_OF_USER_TOP_LEFT                                     =
                                                                    "//*[@id='UIBreadCrumbsNavigationPortlet']//*[contains(text(),'{$name}')]";

  public static final String          ELEMENT_NAME_OF_USER_TOP_RIGHT                                    =
                                                                     ".//*[@id='UIUserPlatformToolBarPortlet']//*[contains(normalize-space(),'${firstName} ${lastName}')]";

  public static final String          ELEMENT_PROFILE_TITLE                                             =
                                                            ".//*[@id='UIStatusProfilePortlet']//*[contains(text(),'${fullName}')]";

  // Left contact information
  public static final By              ELEMENT_UIBASICPROFILEPORTLET                                     =
                                                                    By.xpath(".//*[@id='UIBasicProfilePortlet']/h4[contains(text(),'Contact Information')]");

  public static final String          ELEMENT_FULLNAME_INFO                                             =
                                                            ".//*[@id='UIStatusProfilePortlet']//span[text()='${fullname}']";

  public static final String          ELEMENT_EMAIL_INFO                                                =
                                                         ".//*[@class='uiEmail ellipsis' and @data-original-title='${email}']";

  public static final String          ELEMENT_JOB_TITLE_INFO                                            =
                                                             ".//*[@class='uiPosition ellipsis' and @data-original-title='${job}']";

  public static final String          ELEMENT_GENDER_INFO                                               =
                                                          ".//*[@class='uiGender ellipsis' and @data-original-title='${gender}']";

  public static final String          ELEMENT_PHONE_INFO                                                =
                                                         "//div[contains(text(),'${type}:')]/../*[@data-original-title='${phone}']";

  public static final String          ELEMENT_IM_INFO                                                   =
                                                      "//div[contains(text(),'${type}:')]/../*[@data-original-title='${im}']";

  public static final String          ELEMENT_URL_INFO                                                  =
                                                       "//*[@class='uiUrls']/*[@data-original-title='${url}']";

  // Middle experience information
  public static final String          ELEMENT_COMPANY_INFO                                              =
                                                           "//*[@class='company clearfix']//*[@data-original-title='${company}']";

  public static final String          ELEMENT_POSITION_INFO                                             =
                                                            "//*[@class='position clearfix']//*[@data-original-title='${position}']";

  public static final String          ELEMENT_JOB_DETAIL_INFO                                           =
                                                              "//*[@class='description clearfix']//*[@data-original-title='${description}']";

  public static final String          ELEMENT_SKILL_INFO                                                =
                                                         "//*[@class='skills clearfix']//*[@data-original-title='${skill}']";

  public static final String          ELEMENT_STARTDATE_INFO                                            =
                                                             "//*[@class='startDate clearfix']//*[@data-original-title='${date}']";

  public static final String          ELEMENT_ENDDATE_INFO                                              =
                                                           "//*[@class='endDate clearfix']//*[@data-original-title='${date}']";

  // Navigation tabs
  public static final By              ELEMENT_MY_PROFILE_TAB                                            =
                                                             By.xpath("//*[@class='nav nav-tabs userNavigation']//*[@class='uiIconAppprofile uiIconDefaultApp']");

  public static final By              ELEMETN_ACTIVITY_TAB                                              =
                                                           By.xpath(".//*[contains(@class,'uiIconAppactivities')]");

  // Current position
  public static final By              ELEMENT_EDIT_POSITION                                             =
                                                            By.xpath("//*[@id='UIHeaderSection']//*[@class='uiIconEdit']");

  public static final By              ELEMENT_POSITION_TEXTBOX_EDIT                                     = By.id("position");

  public static final By              ELEMENT_EDIT_POSITION_SAVE_BUTTON                                 = By.id("savePosition");

  // About me
  public static final By              ELEMENT_ABOUTME_TEXTAREA_EDIT                                     = By.id("aboutMe");

  public static final String          ELEMENT_UIEXPERIENCE_PROFILE_PORTLET                              =
                                                                           "//*[@id='UIExperienceProfilePortlet']//*[contains(text(),'${content}')]";

  public static final By              ELEMENT_UIEXPERIENCE_PORLET                                       =
                                                                  By.xpath("//*[@id='UIExperienceProfilePortlet']/*[text()='About me']");

  // Basic information
  public static final By              ELEMENT_EDIT_BASIC_INFORMATION                                    =
                                                                     By.xpath("//*[@id='UIBasicInfoSection']//*[@class='uiIconEdit']");

  public static final By              ELEMENT_FIRST_NAME_TEXTBOX_EDIT                                   = By.id("firstName");

  public static final By              ELEMENT_LAST_NAME_TEXTBOX_EDIT                                    = By.id("lastName");

  public static final By              ELEMENT_EMAIL_TEXTBOX_EDIT                                        = By.id("email");

  public static final By              ELEMENT_EDIT_BASIC_INFO_SAVE_BUTTON                               =
                                                                          By.xpath(".//*[@id='UIEditUserProfileForm']//*[contains(@class,'btn-save')]");

  // Contact
  public static final By              ELEMENT_CONTACT_EDIT_ICON                                         =
                                                                By.xpath(".//*[@id='UIContactSection']//*[contains(text(),'Contact')]/..//*[@class='uiIconEdit']");

  public static final By              ELEMENT_CONTACT_GENDER_SELECTION                                  = byId("gender");

  public static final By              ELEMENT_CONTACT_JOB_TITLE                                         = By.name("position");

  public static final String          ELEMENT_CONTACT_IMS_OPTION                                        =
                                                                 "//*[@id='ims']/div[${index}]//*[contains(@name,'selectKey_ims')]";

  public static final String          ELEMENT_CONTACT_IMS_INPUT                                         =
                                                                "//*[@id='ims']/div[${index}]//*[contains(@name,'inputKey_ims')]";

  public static final String          ELEMENT_CONTACT_IMS_INPUT_LIST                                    = "//*[@id='ims']/div";

  public static final By              ELEMENT_CONTACT_IMS_ADD_ICON                                      =
                                                                   By.xpath("//*[@id='ims']//*[@data-original-title='Add Item']");

  public static final String          ELEMENT_CONTACT_IMS_REMOVE_ICON                                   =
                                                                      "/*[@id='ims']/div[${index}]//*[@data-original-title='Remove Item']";

  public static final String          ELEMENT_CONTACT_PHONE_OPTION                                      =
                                                                   "//*[@id='phones']/div[${index}]//*[contains(@name,'selectKey_phones')]";

  public static final String          ELEMENT_CONTACT_PHONE_INPUT                                       =
                                                                  "//*[@id='phones']/div[${index}]//*[contains(@name,'inputKey_phones')]";

  public static final String          ELEMENT_CONTACT_PHONE_INPUT_LIST                                  = "//*[@id='phones']/div";

  public static final By              ELEMENT_CONTACT_PHONE_ADD_ICON                                    =
                                                                     By.xpath("//*[@id='phones']//*[@data-original-title='Add Item']");

  public static final String          ELEMENT_CONTACT_PHONE_REMOVE_ICON                                 =
                                                                        "/*[@id='phones']/div[${index}]//*[@data-original-title='Remove Item']";

  public static final String          ELEMENT_CONTACT_URL_INPUT                                         =
                                                                "//*[@class='multiValueContainer']/li[${index}]//*[contains(@name,'urls')]";

  public static final String          ELEMENT_CONTACT_URL_INPUT_LIST                                    =
                                                                     "//*[@class='multiValueContainer']/li";

  public static final By              ELEMENT_CONTACT_URL_ADD_ICON                                      =
                                                                   By.xpath("//*[@class='multiValueContainer']//*[@data-original-title='Add Item']");

  public static final String          ELEMENT_CONTACT_URL_REMOVE_ICON                                   =
                                                                      "/*[@class='multiValueContainer']/div[${index}]//*[@data-original-title='Remove Item']";

  // Avatar
  public static final By              ELEMENT_CHANGE_AVATAR_LINK                                        =
                                                                 By.className("changeAvatar");

  public static final By              ELEMENT_CHOOSE_AVATAR_IMAGE                                       =
                                                                  By.className("fileNameLabel");

  public static final By              ELEMENT_SELECT_AVATAR                                             =
                                                            By.xpath(".//*[@id='Uploader']//*[text()='Select File']");

  public static final By              ELEMENT_UPLOAD_NAME                                               = By.name("file");

  public static final By              ELEMENT_CONFIRM                                                   =
                                                      By.xpath(".//*[@id='UIAvatarUploader']//*[text()='Confirm']");

  public static final By              ELEMENT_CANCEL                                                    =
                                                     By.xpath(".//*[@id='UIAvatarUploader']//*[text()='Cancel']");

  public static final By              ELEMENT_SAVE_AVATAR                                               =
                                                          By.xpath(".//*[@id='UIAvatarUploadContent']//*[text()='Save']");

  public static final By              ELEMENT_CANCEL_AVATAR                                             =
                                                            By.xpath(".//*[@id='UIAvatarUploadContent']//*[text()='Cancel']");

  // Experience
  public static final By              ELEMENT_NO_EXPERIENCE                                             = By.id("infoExperien");

  public static final By              ELEMENT_ADD_MORE_EXP_ICON                                         =
                                                                By.xpath("//*[@data-original-title='Add more experience' or @title='Add more experience']");

  public static final String          ELEMENT_EXPERIENCE_LIST                                           =
                                                              ".//*[starts-with(@id,'ExperienceSection')]";

  public static final String          ELEMENT_EXPERIENCE_COMPANY_INPUT                                  =
                                                                       "//*[@id='companyExperienceSection${index}']";

  public static final String          ELEMENT_EXPERIENCE_POSITION_INPUT                                 =
                                                                        "//*[@id='positionExperienceSection${index}']";

  public static final String          ELEMENT_EXPERIENCE_DESCRIPTION_INPUT                              =
                                                                           "//*[@id='descriptionExperienceSection${index}']";

  public static final String          ELEMENT_EXPERIENCE_SKILL_INPUT                                    =
                                                                     "//*[@id='skillsExperienceSection${index}']";

  public static final String          ELEMENT_EXPERIENCE_START_DATE_INPUT                               =
                                                                          "//*[@name='startDateExperienceSection${index}']";

  public static final String          ELEMENT_EXPERIENCE_END_DATE_INPUT                                 =
                                                                        "//*[@name='endDateExperienceSection${index}']";

  public static final String          ELEMENT_EXPERIENCE_CURRENT_CHECKBOX                               =
                                                                          "//*[@id='isCurrentExperienceSection${index}']";

  public static final String          ELEMENT_EXPERIENCE_CLOSE                                          =
                                                               "//*[@id='ExperienceSection${index}']//../*[@title='Remove this experience']";

  // Save - Cancel button
  public static final By              ELEMENT_CONTACT_SAVE_BUTTON                                       =
                                                                  By.xpath(".//*[@id='UIEditUserProfileForm']//button[text()='Save']");

  public static final By              ELEMENT_CONTACT_SAVE_BUTTON_DISABLE                               =
                                                                          By.xpath(".//*[@id='UIEditUserProfileForm']//button[text()='Save' and @disabled='disabled']");

  public static final By              ELEMENT_CONTACT_CANCEL_BUTTON                                     =
                                                                    By.xpath(".//*[@id='UIEditUserProfileForm']//button[text()='Cancel']");

  public static final By              ELEMENT_SAVE_UPDATE_INFO                                          =
                                                               By.xpath("//*[@id='UIProfile']//../*[contains(text(), 'Save')]");

  public static final By              ELEMENT_CANCEL_UPDATE_INFO                                        =
                                                                 By.xpath("//*[@id='UIProfile']//../*[contains(text(), 'Cancel')]");

  // Recent activity
  public static final String          ELEMENT_RECENT_ACTIVITY_CONTENT                                   =
                                                                      "//*[@id='UIRecentActivitiesPortlet']//*[@class='activityCont']/div[${index}]//*[@class='status' and contains(text(),'${content}')]";

  public static final String          ELEMENT_RECENT_ACTIVITY_NO_CONTENT                                =
                                                                         "//*[@id='UIRecentActivitiesPortlet']//*[contains(text(),'${content}')]";

  public static final String          ELEMENT_RECENT_ACTIVITY_ALL_CONTENT                               =
                                                                          "//*[@id='UIRecentActivitiesPortlet']//*[@class='activityCont']//*[@class='content']/*[contains(normalize-space(),'${content}')]";

  public static final By              ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN                               =
                                                                          By.xpath(".//*[@id='UIRecentActivitiesPortlet']//button[contains(text(),'View All')]");

  // Connection part
  public static final String          ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT                            =
                                                                             "//*[@id='UIMiniConnectionsPortlet']/*[contains(text(),'${content}')]";

  public static final By              ELEMENT_UIMINICONNECTIONS_PORTLET_FIND                            =
                                                                             By.xpath("//*[@id='UIMiniConnectionsPortlet']/..//*[contains(text(),'Find connections')]");

  public static final String          ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL                         =
                                                                                "//*[@id='UIMiniConnectionsPortlet']/..//*[contains(text(),'View all') and contains(text(),'${num}')]";

  public static final String          ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION                =
                                                                                         ".//*[@id='UIMiniConnectionsPortlet']//*[@class='borderContainer']/*[@class='avatarXSmall']";

  public static final String          ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR                           =
                                                                              ".//*[@id='UIMiniConnectionsPortlet']//*[@class='borderContainer']/*[@class='avatarXSmall' and contains(@href,'${username}')]";

  public static final String          ELEMENT_UIMINICONNECTIONS_PORLET_HOVER_POPUP_AVATAR               =
                                                                                          "//*[@id='tipName']//*[contains(@href,'${username}')]/img";

  public static final String          ELEMENT_UIMINICONNECTIONS_PORLET_HOVER_POPUP_USERNAME             =
                                                                                            "//*[@id='tipName']//*[contains(@href,'${username}') and contains(text(),'${fullname}')]";

  // Connection status
  public static final By              ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS                   =
                                                                                      By.xpath(".//*[@id='UIRelationshipAction']//*[@class='uiIconStatusConnect']/..");

  public static final By              ELEMENT_UIMINICONNECTIONS_PORLET_CANCEL_STATUS                    =
                                                                                     By.xpath(".//*[@id='UIRelationshipAction']//*[text()='Cancel Request']");

  public static final By              ELEMENT_UIMINICONNECTIONS_PORLET_ACCEPT_STATUS                    =
                                                                                     By.xpath(".//*[@id='UIRelationshipAction']//*[@class='uiIconStatusAccept']/..");

  public static final By              ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS                 =
                                                                                        By.xpath(".//*[@id='UIActionProfilePortlet']//*[@class='btn show-default']/*[@class='uiIconStatusConnected']");

  public static final By              ELEMENT_UIMINICONNECTIONS_PORLET_DISCONNECTED_STATUS              =(byClassName("uiIconStatusDisconnect"));


  public static final By              ELEMENT_UIMINICONNECTIONS_PORTLET_DENY_STATUS                     =
                                                                                    By.xpath(".//*[@id='UIRelationshipAction']//*[@class='uiIconStatusDeny']/..");

  public static final By              ELEMENT_UIMINICONNECTIONS_PORTLET_TITLE                           =
                                                                              By.xpath(".//*[@id='UIMiniConnectionsPortlet']/h4[contains(text(),'Connections')]");

  /****************************************************
   * SPACE HOME PAGE
   ***********************************************************************/
  public static final By              ELEMENT_SPACE_PANEL                                               =
                                                          By.xpath(".//*[@id='UIMySpacesPortlet']");

  // select menu (actvity stream, forum, agenda etc ..)
  public static final By              ELEMENT_SPACE_MENU_ACTIVITY_STREAM                                =
                                                                         By.xpath(".//*[@class='tabName' and contains(text(),' Activity Stream')]");

  public static final By              ELEMENT_SPACE_MENU_AGENDA                                         =
                                                                By.xpath(".//*[@id='calendar' and contains(text(),'Agenda')]");

  public static final By              ELEMENT_SPACE_MENU_FORUMS                                         =
                                                                By.xpath(".//*[@class='tabName' and contains(text(),'Forums')]");

  public static final By              ELEMENT_SPACE_MENU_WIKI                                           =
                                                              By.xpath(".//*[@class='tabName' and contains(text(),'Wiki')]");

  public static final By              ELEMENT_SPACE_MENU_DOCUMENTS                                      =
                                                                   By.xpath(".//*[@class='tabName' and contains(text(),'Documents')]");

  public static final By              ELEMENT_SPACE_MENU_SETTINGS                                       =
                                                                  By.xpath(".//*[@class='tabName' and contains(text(),'Space Settings')]");

  public static final By              ELEMENT_SPACE_MENU_ANSWER                                         =
                                                                By.xpath(".//*[@class='tabName' and contains(text(),'answer')]");

  public static final String          ELEMENT_SPACE_NAME                                                =
                                                         ".//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='name'][contains(text(),'${name}')]";

  public static final String          ELEMENT_SPACE_MENU_DISPLAYORDER_ID                                =
                                                                         ".//*[@id='spaceMenuTab']/li[${number}]//*[contains(@class,'${tab}')]";

  public static final String          ELEMENT_SPACE_MENU_DISPLAYORDER                                   =
                                                                      ".//*[@id='spaceMenuTab']/li[${number}]//*[contains(text(),'${tab}')]";

  // Navigation menu
  public static final By              ELEMENT_HORIZOLTAL_MENU_BAR                                       =
                                                                  By.xpath(".//*[@id='spaceMenuTab']");

  public static final String          ELEMENT_SPACE_TAB_NAME                                            =
                                                             ".//*[@id='spaceMenuTab']//*[contains(text(),'${name}')]";

  public static final By              ELEMENT_ACTIVITY_STREAM_TAB                                       =
                                                                  By.xpath(".//*[contains(@class,'uiIconAppSpaceActivityStreamPortlet')]");

  public static final By              ELEMENT_FORUM_TAB                                                 =
                                                        By.xpath(".//*[contains(@class,'uiIconAppForumPortlet')]");

  public static final By              ELEMENT_WIKI_TAB                                                  =
                                                       By.xpath(".//*[contains(@class,'uiIconAppWikiPortlet')]");

  public static final By              ELEMENT_DOCUMENT_TAB                                              =
                                                           By.xpath(".//*[contains(@class,'uiIconAppFileExplorerPortlet')]");

  public static final By              ELEMENT_AGENDA_TAB                                                =
                                                         By.xpath(".//*[contains(@class,'uiIconAppCalendarPortlet')]");

  public static final By              ELEMENT_MEMBER_TAB                                                =
                                                         By.xpath(".//*[@id='spaceMenuTab']//*[contains(@class,'uiIconAppMembersPortlet ')]");

  public static final By              ELEMENT_NAVIGATION_SPACE_SETTING_TAB                              =
                                                                           By.xpath(".//*[contains(@class,'uiIconAppSpaceSettingPortlet')]");

  public static final By              ELEMENT_MYDASH_BTN_ADDGADGET                                      =
                                                                   By.xpath(".//*[@id='GadgetContainer']//*[contains(text(),'Add Gadgets')]");

  /****************************************************
   * SPACE MANAGEMENTT
   ***********************************************************************/
  // Add form space
  public static final SelenideElement ELEMENT_ADDNEWSPACE_BUTTON                                        =
                                                                 $(byText("Add New Space"));

  public static final By              ELEMENT_ADDNEWSPACE_FORM                                          =
                                                               By.xpath("//span[@class='PopupTitle popupTitle' and text()='Add New Space']");

  public static final By              ELEMENT_ADDNEWSPACE_ICON                                          =
                                                               By.xpath("//*[contains(@class, 'uiIconSocSimplePlus')]");

  // Search panel
  public static final By              ELEMENT_MY_SPACE_SEARCH_TEXT_BOX                                  =
                                                                       By.xpath(".//*[@id='SpaceSearch']");

  public static final By              ELEMENT_MY_SPACE_SEARCH_BTN                                       =
                                                                  By.xpath(".//*[@id='UISpaceSearch']//i[@class='uiIconSearch uiIconLightGray']");

  public static final String          ELEMENT_MY_SPACE_SEARCH_RESULT                                    =
                                                                     ".//*[@id='UIManageMySpaces']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_MY_SPACE_SEARCH_RESULT_NUMBER                             =
                                                                            ".//*[@id='UIManageMySpaces']//*[@class='number'][text()='${number}']";

  // Letter list
  public static final String          ELEMENT_MY_SPACE_LETTER_LIST                                      =
                                                                   ".//*[@class='letterList']//*[text()='${alpha}']";

  public static final By              ELEMENT_UPLOAD_POPUP_SELECT_FILE_BTN                              =
                                                                           By.xpath(".//*[@id='Uploader']//label[text()='Select File']");

  public static final By              ELEMENT_SPACE_SAVE_BTN                                            =
                                                             By.xpath(".//*[@id='UISpaceInfo']//button[text()='Save']");

  public static final By              ELEMENT_SPACE_UPLOAD_CONFIRM_BTN                                  =
                                                                       By.xpath(".//*[@id='UIAvatarUploader']//button[text()='Confirm']");

  public static final By              ELEMENT_SPACE_UPLOAD_SAVE_BTN                                     =
                                                                    By.xpath(".//*[@id='UIAvatarUploadContent']//button[text()='Save']");

  // My space
  public static final By              ELEMENT_SPACE_MY_SPACE_TAB                                        =
                                                                 By.xpath(".//*[@id='UIManageAllSpaces']//*[contains(text(),'My Spaces')]");

  public static final String          ELEMENT_SPACE_DESCRIPTION                                         =
                                                                ".//*[@id='UIManageMySpaces']//*[@class='content limitText'][text()='${des}']";

  public static final By              ELEMENT_SPACE_AVATAR_DEFAULT                                      =
                                                                   By.xpath(".//*[@id='UISpaceInfo']//*[contains(@src,'SpaceAvtDefault.png')]");

  public static final String          ELEMENT_SPACE_DELETE_BUTTON                                       =
                                                                  "//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Delete']";

  public static final String          ELEMENT_SPACE_LEAVE_BTN                                           =
                                                              "//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Leave']";

  public static final String          ELEMENT_SPACE_EDIT_BTN                                            =
                                                             "  //*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Edit']";

  public static final By              ELEMENT_SPACE_EDIT_SETTING_TAB                                    =
                                                                     By.xpath(".//*[contains(@data-target,'#UISpaceInfo-tab')]");

  public static final String          ELEMENT_SPACE_MEMBER_INFOR                                        =
                                                                 "//*[@class='spaceTitle']//*[text()='${space}']/../..//*[contains(@class,'membersCount')]";

  public static final String          ELEMENT_SPACE_DESC_INFOR                                          =
                                                               "//*[@class='spaceTitle']//*[text()='${space}']/../..//*[contains(@class,'content')]";

  public static final String          ELEMENT_SPACE_MANAGER_STATUS                                      =
                                                                   "//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[contains(@class,'statusLabel') and text()='Manager']";

  // Invitations received tab
  public static final By              ELEMENT_MY_SPACE_INVITATION_RECEIVED                              =
                                                                           By.xpath(".//a[text()='Invitations Received']");

  public static final String          ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN                   =
                                                                                      ".//*[contains(text(),'${space}')]/../../..//button[text()='Accept']";

  public static final String          ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN                   =
                                                                                      ".//*[contains(text(),'${space}')]/../../..//button[text()='Ignore']";

  // All Spaces tab
  public static final By              ELEMENT_MY_SPACE_ALL_SPACES_TAB                                   =
                                                                      By.xpath(".//*[@id='UIPage']//*[contains(@href,'all-spaces')]");

  public static final By              ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN                   =
                                                                                      byText("Request to Join");

  public static final String          ELEMENT_MY_SPACE_ALL_SPACES_JOIN_BTN                              =
                                                                           ".//*[contains(text(),'${space}')]/../../..//button[text()='Join']";

  public static final String          ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING                       =
                                                                                  ".//*[contains(text(),'${space}')]/../../..//*[contains(text(),'Request Pending')]";

  public static final By              ELEMENT_ALL_SPACE_ACTIVE_TAB                                      =
                                                                   By.xpath(".//*[@id='UIManageAllSpaces']//*[contains(@class,'active')]//*[contains(@href,'all-spaces')]");

  public static final String          ELEMENT_ALL_SPACE_SPACE_NAME                                      =
                                                                   ".//*[contains(@class,'spaceBox')]//*[contains(@href,'$space')]";

  // Request pending tab
  public static final By              ELEMENT_MY_SPACE_REQUEST_PENDING_TAB                              =
                                                                           By.xpath("//*[contains(@href,'pendingSpace')]");

  public static final String          ELEMENT_SPACE_CANCEL_BUTTON                                       =
                                                                  "//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Cancel']";

  // Members
  public static final By              ELEMENT_SPACE_GOWIKI                                              =
                                                           By.xpath("//*[@class='uiIconAppWikiPortlet uiIconDefaultApp']/..//*[@id='wiki']");

  public static final By              ELEMENT_SPACE_MEMBERS                                             =
                                                            By.xpath("//*[@data-toggle='tab' and text()='Members']");

  public static final By              ELEMENT_SPACE_GOSETTINGS                                          =
                                                               By.xpath("//*[@id='settings']");

  public static final By              ELEMENT_SPACE_TEXTBOX_USER                                        =
                                                                 By.xpath("//*[@id='user']");

  public static final By              ELEMENT_SPACE_TEXTBOX_USER_SUGGEST                                =
                                                                         By.xpath("//*[@class='text' and text()='Mary Williams']");

  public static final By              ELEMENT_SPACE_BTN_INVITE                                          =
                                                               By.xpath("//*[text()='Invite']");

  public static final String          ELEMENT_SPACE_BTN_MANAGER                                         =
                                                                "//*[text()='${name}']/..//*[@class='switchBtnLabelOff']";

  public static final String          ELEMENT_SPACE_MEMBER_USER_MANAGER                                 =
                                                                        ".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]/..//*[@class='switchBtnHandle' and contains(@style,'left: 41px;')]";

  public static final String          ELEMENT_SPACE_MEMBER_USER_MEMBER                                  =
                                                                       ".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]/..//*[@class='switchBtnHandle' and not(contains(@style,'left: 41px;'))]";

  public static final By              ELEMENT_SPACE_BTN_ACCEPT_INVITE                                   =
                                                                      By.xpath("//*[text()='Accept']");

  public static final By              ELEMENT_SPACE_ALLSPACES                                           =
                                                              By.xpath("//*[text()='All Spaces']");

  // Request to join a space
  public static final String          ELEMENT_REQUEST_TO_JOIN_SPACE_BTN                                 =
                                                                        "//*[contains(text(),'${space}')]/../../..//button[text()='Request to Join']";

  public static final String          ELEMENT_REQUEST_PENDING                                           =
                                                              "//*[contains(text(),'${space}')]/../../..//*[text()='Request Pending']";

  // Forum tab
  public static final By              ELEMENT_FORUM_START_BUTTON_UP                                     =
                                                                    By.xpath("(.//*[@id='UITopicContainer']//*[contains(@class,'uiIconForumCreateTopic ')])[1]");

  // Wiki tab
  public static final By              ELEMENT_WIKI_HOME_TITLE                                           =
                                                              By.xpath(".//*[@id='titleInfo']");

  // Document tab
  public static final By              ELEMENT_DOCUMENT_FOLDER_ADD_BTN                                   =
                                                                      By.xpath(".//*[contains(@class,'uiIconEcmsAddFolder ')]");

  public static final By              ELEMENT_ACTIONBAR_ADDFOLDER                                       =
                                                                  By.xpath("//*[contains(@id,'uiActionsBarContainer')]//*[contains(@class,'uiIconEcmsAddFolder')]");

  public static final By              ELEMENT_ACTIONBAR_MORE                                            =
                                                             By.xpath("//*[contains(@class,'listHiddenActionsContainer')]/..//*[text()='More ']");

  public static final By              ELEMENT_ADDFOLDERBOX                                              =
                                                           By.xpath("//*[contains(@id,'UIPopupContainer')]//*[contains(@class,'PopupTitle')]");

  public static final By              ELEMENT_ADDFOLDER_NAME                                            =
                                                             By.xpath("//*[@id='titleTextBox']");

  public static final By              ELEMENT_ADDFOLDER_FOLDERTYPE                                      =
                                                                   By.xpath("//*[@class='selectbox']");

  public static final By              ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON                              =
                                                                           By.xpath("//*[contains(@class,'addFolderButton')]");

  public static final String          ELEMENT_DOCUMENT_FOLDER_NAME                                      =
                                                                   ".//*[contains(@id,'UIDocumentInfo')]//*[contains(@data-original-title,'$name')]";

  public static final String          ELMENT_DOCUMENT_FOLDER_ADDRESS                                    =
                                                                     ".//*[@id='address'][contains(@value,'/$name')]";

  // Agenda tab
  public static final By              ELEMENT_AGENDA_EVENT_ADD_BTN                                      =
                                                                   By.xpath(".//*[@id='UIActionBarQuickAddEvent']");

  // Member tab
  public static final By              ELEMENT_MEMBER_USER_INFOR                                         =
                                                                By.xpath(".//*[@id='spaceManagerListBox']");

  public static final By              ELEMENT_MEMBER_USER_SEARCH                                        =
                                                                 By.xpath(".//*[@id='UIProfileUserSearch']");

  public static final By              ELEMENT_MEMBER_USER_CONTACT_LIST                                  =
                                                                       By.xpath(".//*[@id='spaceMemberListBox']");

  public static final String          ELEMENT_MEMBER_USER_NAME                                          =
                                                               ".//*[@id='spaceMemberListBox']//*[contains(@data-text,'${fullName}')]";

  public static final String          ELEMENT_MANAGER_USER_NAME                                         =
                                                                ".//*[@id='spaceManagerListBox']//*[contains(@data-text,'${fullName}')]";

  public static final By              ELEMENT_SEARCH_INPUT_USER_NAME                                    =
                                                                     By.xpath(".//*[@id='Quick Search']");

  public static final By              ELEMENT_SEARCH_USERS_ICON                                         =
                                                                By.xpath("//*[@id=\"SearchButton\"]");

  public static final By              ELEMENT_INPUT_USER                                                =
                                                         By.xpath(".//*[@id='user']");

  public static final By              ELEMENT_SELECT_USER_FROM_GROUP                                    =
                                                                     By.xpath(".//*[@id='UISpaceMember']//*[contains(@class,'uiIconGroup')]");

  public static final By              ELEMENT_ACCESS_ONLY_ONE_MANAGER_NUMBER                            =
                                                                             By.xpath("(.//*[@id='existingUsersTable']//*[contains(@class,'uiSwitchBtn')]//input[@checked='checked'])[1]");

  public static final By              ELEMENT_ACCESS_MORE_ONE_MANAGER_NUMBER                            =
                                                                             By.xpath("(.//*[@id='existingUsersTable']//*[contains(@class,'uiSwitchBtn')]//input[@checked='checked'])[2]");

  public static final By              ELEMENT_MEMBER_TABLE                                              =
                                                           By.xpath("(.//*[@id='existingUsersTable']");

  // Search user
  public static final String          ELEMENT_CLOSE_MESSAGE                                             =
                                                            "//*[contains(@title,'Close Window')]";

  public static final By              ELEMENT_INPUT_SEARCH_USER_NAME                                    = By.id("searchTerm");

  public static final String          ELEMENT_SELECT_SEARCH_OPTION                                      =
                                                                   "//*[contains(@name,'filter')]";

  public static final String          ELEMENT_SEARCH_ICON_USERS_MANAGEMENT                              =
                                                                           "//*[contains(@class,'uiIconSearch uiIconLightGray')]";

  public static final By              ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN                       =
                                                                                  By.xpath("//*[@id=\"UITabPanetab3\"]/div/button");

  public static final String          ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT                  =
                                                                                       ".//*[@id='UISpaceApplication']//strong[contains(text(),'${app}')]";

  public static final String          ELEMENT_APPLICATION_TAB_APPLICATION_DELETE_BTN                    =
                                                                                     ".//*[@id='UISpaceApplication']//strong[contains(text(),'${app}')]/../..//*[@class='uiIconClose pull-right']";

  public static final By              ELEMENT_APPLICATION_TAB_LIST_APPLICATIONS                         =
                                                                                By.xpath(".//*[@id='UISpaceApplication']");

  // Access and Edit tab
  public static final By              ELEMENT_ACCESS_AND_EDIT_TAB                                       =
                                                                  By.xpath(".//*[contains(@data-target,'#UITabPanetab1-tab')]");

  public static final By              ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP                              =
                                                                           By.xpath(".//*[contains(@data-target,'#UISpaceVisibility-tab')]");

  public static final By              ELEMENT_ACCESS_HIDDEN_RADIO                                       =
                                                                  By.xpath("//input[@value='hidden']");

  public static final String          ELEMENT_ACCESS_PERMISSION_RADIO                                   =
                                                                      "//input[@value='${right}']";

  public static final By              ELEMENT_ACCESS_PERMISSION_SAVE_BTN                                =
                                                                         By.xpath(".//*[@id='UISpacePermission']//button[text()='Save']");

  public static final By              ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP_CREATE_BTN                   =
                                                                                      By.xpath(".//button[text()='Create']");

  public static final By              ELEMENT_ACCESS_ALERTS_POPUP_OK_BTN                                =
                                                                         By.xpath(".//*[@class='PopupTitle popupTitle'][contains(text(),'Alerts')]/../..//*[@class='btn']");

  public static final By              ELEMENT_ACCESS_INFO_OK_BTN                                        =
                                                                 By.xpath("//*[@class='PopupContent popupContent']//*[contains(text(),'OK')]");

  public static final By              ELEMENT_ACCESS_VISIBILITY_RADIO_CHECKED                           =
                                                                              By.xpath(".//*[@id='UISpacePermission']//input[@value='private' and @checked='checked']");

  public static final By              ELEMENT_ACCESS_VALIDATION_RADIO_CHECKED                           =
                                                                              By.xpath(".//*[@id='UISpacePermission']//input[@value='validation' and @checked='checked']']");

  // Add application popup
  public static final By              ELEMENT_ADD_APPLICATION_POPUP_TITLE                               =
                                                                          By.xpath("//*[contains(text(),'Space Application Installer')]");

  public static final String          ELEMENT_ADD_APPLICATION_POPUP_CATEGOGY                            =
                                                                             ".//*[@id='${category}']";

  public static final String          ELEMENT_ADD_APPLICATION_POPUP_APPLICATION_ADD_BTN                 =
                                                                                        ".//*[@id='UIApplicationListSelector']//*[contains(text(),'${app}')]/../..//*[contains(text(),'Add')]";

  public static final By              ELEMENT_ADD_APPLICATION_POPUP_CLOSE_BTN                           =
                                                                              By.xpath(".//*[@id='UIAddApplication']//*[@class='uiIconClose pull-right']");

  // Settings tab
  public static final SelenideElement ELEMENT_SPACE_NAME_INPUT                                          = $(byId("displayName"));

  public static final SelenideElement ELEMENT_SPACE_DESCRIPTION_INPUT                                   = $(byId("description"));

  public static final By              ELEMENT_SPACE_SETTING_TAB                                         =
                                                                By.xpath(".//*[contains(@data-target,'#UISpaceInfo-tab')]");

  public static final By              ELEMENT_SPACE_CHANGE_AVATAR_BTN                                   =
                                                                      By.xpath(".//*[@id='UISpaceInfo']//button[text()='Change Picture']");

  public static final By              ELEMENET_SPACE_UPDATE_SAVE_BTN                                    =
                                                                     By.xpath(".//*[@id='UISpaceInfo']//button[contains(@onclick,'Save')]");

  // invitation member
  public static final String          ELEMENT_SPACE_INVITED_USER_TABLE                                  =
                                                                       ".//*[@id='UISpaceMember']//th[contains(text(),'Invited')]/../../..//*[contains(text(),'${user}')]";

  public static final String          ELEMENT_SPACE_MEMBERS_USER_TABLE                                  =
                                                                       ".//*[@id='UISpaceMember']//th[contains(text(),'Members')]/../../..//*[contains(text(),'${user}')]";

  public static final String          ELEMENT_USER_IN_MEMBER_TABLE                                      =
                                                                   ".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]";

  // Invitation a group
  public static final By              ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_TAB                         =
                                                                                By.xpath(".//*[contains(@data-target,'#UISpaceGroupBound-tab')]");

  public static final By              ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX                    =
                                                                                     By.xpath(".//*[@id='UseExistingGroupCheckBox']");

  public static final String          ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECT_GROUP                =
                                                                                         ".//*[@id='UISocialGroupSelector']//*[contains(@title,'${name}')]";

  public static final By              ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECTED_LINK               =
                                                                                          By.xpath(".//*[@id='UISocialGroupSelector']//*[contains(@data-placement,'bottom')]");

  public static final By              ELEMENT_SPACE_INVITE_USERS_FROM_GROU_SELECTED_GROUP_INFO          =
                                                                                               By.xpath(".//*[@id='groupId']");

  public static final By              ELEMENT_SPACE_INVITED_GROUP_BTN                                   =
                                                                      By.xpath(".//*[@id='UISpaceMember']//*[contains(@class,'uiIconGroup')]");

  public static final String          ELEMENT_SPACE_INVITED_GROUP_NAME                                  =
                                                                       "//*[contains(@title,'$name')]";

  public static final By              ELEMENT_SPACE_INVITED_SELECT_GROUP                                =
                                                                         By.xpath(".//*[@id='UIUsersInGroupSelector']//*[contains(@data-placement,'bottom')]");

  // Navigation tab
  public static final By              ELEMENT_SPACE_SETTING_NAVIGATION_TAB                              =
                                                                           By.xpath(".//*[contains(@data-target,'#UITabPanetab4-tab')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON                          =
                                                                               By.xpath(".//*[@id='UISpaceNavigationManagement']//button[text()='Add Node']");

  public static final By              ELEMENT_SPACE_NAVIGATION_UP_LEVEL_BUTTON                          =
                                                                               By.xpath(".//*[@id='UISpaceNavigationNodeSelector']//*[@class='uiIconUpLevel uiIconLightGray']");

  public static final By              ELEMENT_SPACE_NAVIGATION_NODE_LIST                                =
                                                                         By.xpath(".//*[@id='UISpaceNavigationNodeSelector']");

  public static final By              ELEMENT_SPACE_APPLICATION_TAB_ADD_BTN                             =
                                                                            By.xpath(".//*[@id='UISpaceApplication-tab']//button");

  // Add/Edit page node popup
  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_NODE_TITLE                      =
                                                                                   By.xpath(".//*[@id='AddNode']//*[contains(.,'Add/ Edit Page Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_NAME                      =
                                                                                   By.xpath(".//*[@id='name']");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE                      =
                                                                                   By.xpath(".//*[@id='UIPageNodeForm']//button[text()='Save']");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LABEL                     =
                                                                                    By.xpath(".//*[@id='UIPageNodeForm']//*[contains(text(),'Label')]/..//input");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LANGUAGE                  =
                                                                                       By.xpath(".//*[@id='PageNodeSetting-tab']//*[@class='selectbox' and @name='languages']");

  public static final By              ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE                              = By.id("switchmode");

  public static final By              ELEMENT_INPUT_LABEL                                               = By.id("i18nizedLabel");

  public static final By              ELEMENT_PAGE_SELECTOR_TAB                                         =
                                                                By.xpath(".//*[@id='AddNode']//a[text()='Page Selector']");

  public static final By              ELEMENT_INPUT_PAGE_NAME                                           = By.id("pageName");

  public static final By              ELEMENT_INPUT_PAGE_TITLE                                          = By.id("pageTitle");

  public static final By              ELEMENT_CREATE_PAGE_LINK                                          =
                                                               By.xpath(".//*[@id='UIPageSelector']//*[@class='uiIconAddPage uiIconWhite']");

  public static final By              ELEMENT_SEARCH_SELECTOR_PAGE_LINK                                 =
                                                                        By.xpath(".//*[@id='UIPageSelector']//*[@class='uiIconSelectPage']");

  // Context menu
  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT                        =
                                                                                 By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Edit this Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_DELETE                      =
                                                                                   By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Delete Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_ADD_NEW_NODE                =
                                                                                         By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Add new Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT_NODE_PAGE              =
                                                                                           By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//*[@class='uiIconEditPageNode']");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_COPY_NODE                   =
                                                                                      By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Copy Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_PASTE_NODE                  =
                                                                                       By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Paste Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_CLONE_NODE                  =
                                                                                       By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Clone Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_CUT_NODE                    =
                                                                                     By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Cut Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_UP                     =
                                                                                    By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Move Up')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_DOWN                   =
                                                                                      By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Move Down')]");

  public static final String          ELEMENT_SPACE_NAVIGATION_NODE_POSITION                            =
                                                                             "//*[@class='childrenContainer nodeGroup']/li[${position}]//a[contains(text(),'${nodeName}')]";

  // Access space information
  public static final By              ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE                         =
                                                                                By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Restricted Area']");

  public static final By              ELEMENT_SPACE_ACCESS_INFO                                         =
                                                                By.xpath(".//*[@class='spaceAccessInfo']");

  public static final By              ELEMENT_SPACE_ACCESS_JOIN_BTN                                     =
                                                                    By.xpath(".//*[@title='Join']");

  public static final By              ELEMENT_SPACE_ACCESS_REQUEST_JOIN_BTN                             =
                                                                            By.xpath(".//*[@title='Request to Join']");

  public static final By              ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_TITLE                        =
                                                                                 By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Space not found']");

  public static final By              ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_INFO                         =
                                                                                By.xpath(".//*[@id='UISpaceAccessPortlet']//*[contains(text(),'No space is available at this URL.')]");

  public static final By              ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_FIND_BTN                     =
                                                                                    By.xpath(".//*[@id='UISpaceAccessPortlet']//a[text()='Find Spaces']");

  public static final By              ELEMENT_SPACE_ACCESS_SPACE_DENIED                                 =
                                                                        By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Access Denied']");

  public static final By              ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO                            =
                                                                             By.xpath(".//*[@class='spaceAccessInfo']");

  public static final String          ELEMENT_SPACE_ACCESS_SPACE_REQUEST_JOIN_MESSAGE                   =
                                                                                      ".//*[contains(text(),'You must be a member of the space')]//b[contains(text(),'$space')]/../..//*[contains(.,'to view this page')]";

  // message
  public static final String          ELEMENT_SPACE_NAVIGATION_COPY_AT_SAME_LEVEL                       =
                                                                                  "This node name already exists.";

  // Warining popup
  public static final String          ELEMENT_SPACE_WARNING_MESSAGE                                     =
                                                                    ".//*[contains(@class,'UIPopupWindow')]//*[contains(text(),'${warningText}')]";

  public static final By              ELEMENT_GMAIL_USER_AVARTAR                                        =
                                                                 By.xpath("//img[contains(@src,'UserAvtDefault.png')]");

  public static final By              ELEMENT_GMAIL_SPACE_AVARTAR                                       =
                                                                  By.xpath("//img[contains(@src,'SpaceAvtDefault.png')]");

  public static final String          ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER                             =
                                                                            ".//*[contains(text(),'${user}')]/..//*[@class='uiSwitchBtn']";

  public static final String          ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER_DISABLE                     =
                                                                                    "//*[contains(text(),'${user}')]/..//*[contains(@class,'switchBtnDisabled')]";

  public static final String          ELEMENT_SPACE_DELETE_USER_BTN                                     =
                                                                    ".//*[contains(@onclick,'${user}')]/..//*[@class='uiIconDelete uiIconLightGray']";

  public static final String          ELEMENT_SPACE_REMOVE_USER_BTN_MEMBER_TABLE                        =
                                                                                 ".//*[contains(@class,'uiIconDelete')]/../../../*[contains(text(),'${fullName}')]";

  public static final String          ELEMENT_SPACE_MEMBERS_TAB_VALIDATE_REQUEST_jOINT                  =
                                                                                       ".//*[contains(@onclick,'${user}')]/..//*[@class='uiIconValidate uiIconLightGray']";

  public static final String          ELEMENT_SPACE_MEMBERS_TAB_DECLINE_REQUEST_jOINT                   =
                                                                                      ".//*[contains(text(),'${user}')]/..//*[contains(@class,'uiIconRemove')]";

  public static final By              ELEMENT_GMAIL_ACCEPT_BTN                                          =
                                                               By.xpath("//*[contains(@href,'confirmInvitationToConnect')]");

  public static final By              ELEMENT_GMAIL_REFUSE_BTN                                          =
                                                               By.xpath("//*[contains(@href,'ignoreInvitationToConnect')]");

  public static final By              ELEMENT_GMAIL_CONNECT_NOW                                         =
                                                                By.xpath("//*[contains(@href,'inviteToConnect')]");

  public static final By              ELEMENT_GMAIL_ACCEPT_SPACE_BTN                                    =
                                                                     By.xpath("//*[contains(@href,'acceptInvitationToJoinSpace')]");

  public static final By              ELEMENT_GMAIL_REFUSE_SPACE_INVITATION_BTN                         =
                                                                                By.xpath("//*[contains(@href,'ignoreInvitationToJoinSpace')]");

  public static final By              ELEMENT_GMAIL_REFUSE_JOIN_SPACE_BTN                               =
                                                                          By.xpath("//*[contains(@href,'refuseRequestToJoinSpace')]");

  public static final By              ELEMENT_GMAIL_ACCEPT_BTN_SPACE_JOIN_REQUEST                       =
                                                                                  By.xpath("//*[contains(@href,'validateRequestToJoinSpace')]");

  public static final String          ELEMENT_GMAIL_BOTTOM_CONTENT                                      =
                                                                   "//*[contains(text(),'$content1')]//*[contains(@href,'notification_settings')]/../..//*[contains(.,'$content2')]";

  public static final String          ELEMENT_GMAIL_BOTTOM_CLICK_HERE_LINK                              =
                                                                           "//a[contains(@href,'notification_setting') and text()='click here']";

  /************************************************
   * ADD USER
   ****************************************************************************/
  public static final By              ELEMENT_USERNAME                                                  = By.id("username");

  public static final By              ELEMENT_PASSWORD                                                  = By.id("password");

  public static final By              ELEMENT_CONFIRM_PASSWORD                                          =
                                                               By.id("Confirmpassword");

  public static final By              ELEMENT_EMAIL                                                     = By.id("email");

  public static final By              ELEMENT_FIRSTNAME                                                 = By.id("firstName");

  public static final By              ELEMENT_LASTNAME                                                  = By.id("lastName");

  public static final By              ELEMENT_SAVE                                                      =
                                                   By.xpath("//*[@id='UIAccountForm']//*[contains(text(),'Save')]");

  public static final SelenideElement ELEMENT_INPUT_SEARCH_USER                                         =
                                                                $(byTitle("Quick Search"));

  public static final SelenideElement ELEMENT_BUTTON_DELETE_USER                                        =
                                                                 $(byClassName("uiIconDeleteUser"));

  public static final SelenideElement ELEMENT_POPUP_NO_RESULT_FOUNF                                     =
                                                                    $(byText("No result found."));

  public static final SelenideElement ELEMENT_POPUP_MESSAGE_USER_ADDED                                  =
                                                                       $(byText("You have registered a new account."));

  public static final String          ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST                            =
                                                                             ".//*[@id='UISpaceNavigationNodeSelector']//*[contains(text(),'${name}')]";

  public static final String          ELEMENT_SPACE_NAVIGAION_ADD_NODE_CHILDREN_UNDER_PARENT            =
                                                                                             ".//*[@id='UISpaceNavigationNodeSelector']//*[contains(text(),'${childrenNode}')]/../../..//*[contains(text(),'${parentNode}')]";

  public static final By              ELEMENT_CONFIRM_INFORMATION                                       =
                                                                  By.xpath(".//*[text() = 'OK']");

  public static final By              ELEMENT_SPACE_SPACE_SETTINGS                                      =
                                                                   By.xpath(".//*[@id='settings']");

  public static final By              ELEMENT_SPACE_WIKI_TAB                                            =
                                                             By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Wiki')]");

  public static final By              ELEMENT_SPACE_MEMBERS_TAB                                         =
                                                                By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Members')]");

  public static final By              ELEMENT_SPACE_WIKI_TAB_CONTENT                                    =
                                                                     By.xpath("//*[@id='UIWikiPortlet']");

  public static final By              ELEMENT_SPACE_WIKI_TAB_ACTIVE                                     =
                                                                    By.xpath("//*[@class='active item']/*[@data-toggle='tab']/*[contains(text(),'Wiki')]");

  public static final By              ELEMENT_SPACE_ACTIVITY_TAB_ACTIVE                                 =
                                                                        By.xpath("//*[@class='active item']/*[@data-toggle='tab']/*[contains(text(),'Activity')]");

  public static final By              ELEMENT_SPACE_MENU_MORE                                           =
                                                              By.xpath(".//*[@id='spaceMenuTab']//*[contains(@class,'uiIconAppMoreButton')]");

  public static final By              ELEMENT_SPACE_MENU_DASHBOARD                                      =
                                                                   By.xpath(".//*[@id='spaceMenuTab']//*[contains(@class,'uiIconAppDashboardPortlet')]");

  // Space portlets
  public static final By              ELEMENT_SPACE_MY_SPACE_PORTLET                                    =
                                                                     By.id("UIMySpacesPortlet");

  public static final By              ELEMENT_SPACE_ALL_SPACE_PORTLET                                   =
                                                                      By.id("UIAllSpacesPortlet");

  public static final By              ELEMENT_SPACE_INVITATION_SPACE_PORTLET                            =
                                                                             By.id("UIInvitationSpacesPortlet");

  public static final By              ELEMENT_SPACE_PENDING_SPACE_PORTLET                               =
                                                                          By.id("UIPendingSpacesPortlet");

  // Add new space buttons
  public static final By              ELEMENT_ADD_NEW_SPACE_BUTTON                                      =
                                                                   By.xpath("//*[@class='uiIconSocSimplePlus uiIconSocWhite']");

  public static final By              ELEMENT_ADD_SPACE_FORM                                            =
                                                             By.id("UIPopupAddSpace");

  // Access and Edit tab form
  public static final By              ELEMENT_SPACE_ACCESS_EDIT_TAB                                     =
                                                                    By.xpath("//*[@data-target='#UISpacePermission-tab']");

  public static final By              ELEMENT_SPACE_VISIBILITY_VISIBLE_CHECKBOX                         =
                                                                                By.xpath("//*[@value='private']");

  public static final By              ELEMENT_SPACE_VISIBILITY_HIDDEN_CHECKBOX                          =
                                                                               By.xpath("//*[@value='hidden']");

  public static final By              ELEMENT_SPACE_REGISTRATION_OPEN_CHECKBOX                          =
                                                                               By.xpath("//*[@value='open']");

  public static final By              ELEMENT_SPACE_REGISTRATION_CLOSED_CHECKBOX                        =
                                                                                 By.xpath("//*[@value='close']");

  public static final By              ELEMENT_SPACE_REGISTRATION_VALIDATION_CHECKBOX                    =
                                                                                     By.xpath("//*[@value='validation']");

  // Access and Edit tab form
  public static final By              ELEMENT_SPACE_INVITE_GROUP_USER_TAB                               =
                                                                          By.xpath("//*[@data-target='#UISpaceGroupBound-tab']");

  public static final By              ELEMENT_SPACE_SELECT_EXIST_GROUP_CHECKBOX                         =
                                                                                By.id("UseExistingGroupCheckBox");

  public static final String          ELEMENT_SPACE_TITLE                                               =
                                                          "//*[@class='spaceTitle']//*[text()='${space}']";

  public static final String          ELEMENT_SPACE_CONFIRM_DELETE                                      =
                                                                   "Are you sure you want to delete this space? This cannot be undone. All page navigations and this group will also be deleted";

  public static final By              ELEMENT_SPACE_DELETE_SPACE_OK_BUTTON                              =
                                                                           By.xpath("//*[text()='OK']");

  public static final String          ELEMENT_SPACE_NAME_BREADCUMB                                      =
                                                                   "//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='name' and contains(text(),'{$name}')]";

  /**************************************************************
   * SPACE SETTING MANAGEMENT
   ********************************************************/
  public static final By              ELEMENT_SPACE_SPACE_SETTINGS_TITLE                                =
                                                                         By.xpath(".//*[@id='UISpaceSettingPortlet']/h3[text()='Space Configuration']");

  // Members tab
  public static final SelenideElement ELEMENT_SPACE_SETTINGS_MEMBERS_TAB                                =
                                                                         $(byId("UISpaceMenu")).find(byText("Members"));

  public static final SelenideElement ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB                 =
                                                                                        $(byId("UISpaceSettingPortlet")).find(byText("Members"));

  public static final By              ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB                  =
                                                                                       byClassName("uiIconValidate");

  public static final By              ELEMENT_ICON_DECLINE_SPACE_REQUEST_IN_MEMBERS_TAB                 =
                                                                                        byClassName("uiIconRemove");

  public static final By              ELEMENT_SPACE_MEMBERS_SELECT_USER                                 =
                                                                        By.xpath("//*[@id='UISpaceMember']//*[@class='uiIconUser uiIconLightGray']");

  public static final By              ELEMENT_ADD                                                       =
                                                  By.xpath("//*[@id='UIUserSelector']//*[contains(text(),'Add')]");

  public static final By              ELEMENT_SPACE_MEMBERS_INVITE                                      =
                                                                   By.xpath("//*[@id='UISpaceMember']//*[contains(text(),'Invite')]");

  public static final String          ELEMENT_MSG_SEARCH_USER_NAME                                      = "User Name";

  // Application tab
  public static final By              ELEMENT_SETTINGS_APP_TAB                                          =
                                                               By.xpath("//*[@id=\"UITabPane\"]/ul/li[4]");

  public static final String          ELEMENT_DELETE_APP_FROM_TOPBAR                                    =
                                                                     ".//*[@id='UISpaceApplication']//*[contains(text(),'{$application}')]/../..//*[@class='uiIconClose pull-right']";

  // Button create
  public static final SelenideElement ELEMENET_SPACE_CREATE_BUTTON                                      =
                                                                   $(byClassName("PopupContent")).find(byText("Create Space"));

  // Activity like
  public static final By              ELEMENT_ACTIVITY_LIKE_EDIT_BTN                                    =
                                                                     By.xpath(".//*[@id='LikePlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX                 =
                                                                                        By.xpath(".//*[@id='MAIL_CHANNELLikePlugin']");

  public static final By              ELEMENT_ACTIVITY_LIKE_SAVE_BTN                                    =
                                                                     By.xpath(".//*[@id='btActionLikePlugin']");

  public static final By              ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_TITLE                    =
                                                                                     By.xpath(".//*[@id='LikePlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX              =
                                                                                           By.xpath(".//*[@id='WEB_CHANNELLikePlugin']");

  public static final By              ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_TITLE                 =
                                                                                        By.xpath(".//*[@id='LikePlugin']//*[contains(@class,'WEB_CHANNEL')]");

  public static final By              ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX_CHECKED      =
                                                                                                   By.xpath(".//*[@id='WEB_CHANNELLikePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX_CHECKED         =
                                                                                                By.xpath(".//*[@id='MAIL_CHANNELLikePlugin'][contains(@checked,'checked')]");

  // Activity mention
  public static final By              ELEMENT_ACTIVITY_MENTION_EDIT_BTN                                 =
                                                                        By.xpath(".//*[@id='ActivityMentionPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX              =
                                                                                           By.xpath(".//*[@id='MAIL_CHANNELActivityMentionPlugin']");

  public static final By              ELEMENT_ACTIVITY_MENTION_SAVE_BTN                                 =
                                                                        By.xpath(".//*[@id='btActionActivityMentionPlugin']");

  public static final By              ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_TITLE                 =
                                                                                        By.xpath(".//*[@id='ActivityMentionPlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX           =
                                                                                              By.xpath(".//*[@id='WEB_CHANNELActivityMentionPlugin']");

  public static final By              ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_TITLE              =
                                                                                           By.xpath(".//*[@id='ActivityMentionPlugin']//*[contains(@class,'WEB_CHANNEL')]");

  public static final By              ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX_CHECKED      =
                                                                                                   By.xpath(".//*[@id='MAIL_CHANNELActivityMentionPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX_CHECKED   =
                                                                                                      By.xpath(".//*[@id='WEB_CHANNELActivityMentionPlugin'][contains(@checked,'checked')]");

  // Activity Post on My Stream
  public static final By              ELEMENT_ACTIVITY_POST_EDIT_BTN                                    =
                                                                     By.xpath(".//*[@id='PostActivityPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX                 =
                                                                                        By.xpath(".//*[@id='MAIL_CHANNELPostActivityPlugin']");

  public static final By              ELEMENT_ACTIVITY_POST_SAVE_BTN                                    =
                                                                     By.xpath(".//*[@id='btActionPostActivityPlugin']");

  public static final By              ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_TITLE                    =
                                                                                     By.xpath(".//*[@id='PostActivityPlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX              =
                                                                                           By.xpath(".//*[@id='WEB_CHANNELPostActivityPlugin']");

  public static final By              ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_TITLE                 =
                                                                                        By.xpath(".//*[@id='PostActivityPlugin']//*[contains(@class,'WEB_CHANNEL')]");

  public static final By              ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED         =
                                                                                                By.xpath(".//*[@id='MAIL_CHANNELPostActivityPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED      =
                                                                                                   By.xpath(".//*[@id='WEB_CHANNELPostActivityPlugin'][contains(@checked,'checked')]");

  // Space post on My space
  public static final By              ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN                          =
                                                                               By.xpath(".//*[@id='PostActivitySpaceStreamPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX                    =
                                                                                     By.xpath(".//*[@id='MAIL_CHANNELPostActivitySpaceStreamPlugin']");

  public static final By              ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN                          =
                                                                               By.xpath(".//*[@id='btActionPostActivitySpaceStreamPlugin']");

  public static final By              ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_TITLE                       =
                                                                                  By.xpath(".//*[@id='PostActivitySpaceStreamPlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX                 =
                                                                                        By.xpath(".//*[@id='WEB_CHANNELPostActivitySpaceStreamPlugin']");

  public static final By              ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE                    =
                                                                                     By.xpath(".//*[@id='PostActivitySpaceStreamPlugin']//*[contains(@class,'WEB_CHANNEL')]");

  public static final By              ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED            =
                                                                                             By.xpath(".//*[@id='MAIL_CHANNELPostActivitySpaceStreamPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED         =
                                                                                                By.xpath(".//*[@id='WEB_CHANNELPostActivitySpaceStreamPlugin'][contains(@checked,'checked')]");

  // Space join request
  public static final By              ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN                          =
                                                                               By.xpath(".//*[@id='RequestJoinSpacePlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX_CHECKED            =
                                                                                             By.xpath(".//*[@id='MAIL_CHANNELRequestJoinSpacePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX_CHECKED         =
                                                                                                By.xpath(".//*[@id='WEB_CHANNELRequestJoinSpacePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN                          =
                                                                               By.xpath(".//*[@id='btActionRequestJoinSpacePlugin']");

  public static final By              ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX                    =
                                                                                     By.xpath(".//*[@id='MAIL_CHANNELRequestJoinSpacePlugin']");

  public static final By              ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX                 =
                                                                                        By.xpath(".//*[@id='WEB_CHANNELRequestJoinSpacePlugin']");

  public static final By              ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_TITLE                       =
                                                                                  By.xpath(".//*[@id='RequestJoinSpacePlugin']//*[contains(@class,'MAIL_CHANNEL')]");

  public static final By              ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_TITLE                    =
                                                                                     By.xpath(".//*[@id='RequestJoinSpacePlugin']//*[contains(@class,'WEB_CHANNEL')]");

  public static final String          ELEMENT_SPACE_SELECT_USER_IN_FORM                                 =
                                                                        "//*[@id='UIListUsers']//*[contains(text(),'{$name}')]/../..//*[@class='uiCheckbox']//input";

  // CONNECTION REQUEST
  public static final String          ELEMENT_NOTIFICATION_EMAIL_FEEDBACK_MESSAGE_SPACE                 =
                                                                                        ".//*[@id='feedbackMessageInline']//*[contains(@class,'message')][contains(text(),\"$mess\")]";

  public static final SelenideElement ELEMENT_SPACE_MENU_TAB                                            = $(byId("spaceMenuTab"));

  // left menu
  public static final String          ELEMENT_SPACE_LEFT_MENU_SPACE_NAME                                =
                                                                         ".//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'${name}')]";

  public static final SelenideElement ELEMENT_MY_SPACE_SEARCH_TEXT                                      =
                                                                   $(byXpath("//*[@id=\"UISpaceSearch\"]/div[2]/div/div/div[1]/input"));

  public static final SelenideElement ELEMENT_SPACES_LIST                                               =
                                                          $(byClassName("tab-content"));

  public static final SelenideElement ELEMENT_SPACE_FORUMS_TAB                                          =
                                                               $(byId("spaceMenuTab")).find(byText("Forums"));

  public static final SelenideElement ELEMENT_INPUT_INVITE_USER                                         =
                                                                $(byXpath("//*[@id=\"UIUserInvitation\"]/div[2]/div[2]/div/div[1]/input"));

  public static final SelenideElement ELEMENT_ALERT_NOTIFICATION_EXIST                                  =
                                                                       $(byXpath("//*[@id=\"UINotificationPopoverToolbarPortlet\"]/div[2]/a/i/span"));

  public static final By              ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION                            =
                                                                             byAttribute("class", "btn btn-primary pull-right");

  public static final SelenideElement ELEMENT_LIST_MY_SPACES_IN_LEFT_NAVIGATION                         =
                                                                                $(byId("UISpaceNavigationPortlet"));

  public static final SelenideElement ELEMENT_SPACE_NAME_LEFT_NAVIGATION                                =
                                                                         $(byId("UIBreadCrumbsNavigationPortlet"));

  public static final SelenideElement ELEMENT_CONTENT_NAME_PROFILE                                      =
                                                                   $(byId("UIUserNavigationPortlet"));

  public static final SelenideElement ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE                         =
                                                                                $(byXpath("//*[@id=\"spaceMenuTab\"]/li[7]/ul"));

  public static final SelenideElement ELEMENT_LIST_OF_EXISTED_APPLICATION_IN_APPLICATION_TAB            =
                                                                                             $(byId("UITabPanetab3"));

  public static final By              ELEMENT_ICON_DELETE_APPLICATION_FROM_SPACE                        =
                                                                                 byClassName("uiIconClose");

  public static final By              ELEMENT_BUTTON_JOIN_SPACE                                         = byClassName("btn");

  public static final SelenideElement ELEMENT_TAB_ACCESS_AND_EDIT                                       =
                                                                  $(byXpath("//*[@id=\"UITabPane\"]/ul/li[2]/a"));

  public static final SelenideElement ELEMENT_BUTTON_SAVE_IN_ACCESS_AND_EDIT_TAB                        =
                                                                                 $(byXpath("//*[@id=\"UISpacePermission\"]/div[3]/button"));

  public static final SelenideElement ELEMENT_OK_BUTTON                                                 =
                                                        $(byClassName("infoIcon")).parent()
                                                                                  .parent()
                                                                                  .parent()
                                                                                  .find(byClassName("btn"));

  public static final SelenideElement ELEMENT_NAME_PROFILE_OF_USERS                                     =
                                                                    $(byId("UIUserNavigationPortlet"));

  public static final SelenideElement ELEMENT_MINI_CONNECTION_PORTLET= $(byId("UIMiniConnectionsPortlet"));
  public static final By              ELEMENT_TASK_TAB                                                  =
                                                       byClassName("uiIconAppTaskManagementApplication");

  public static final SelenideElement ELEMENT_CONTAINER_ACTIVITY                                        =
                                                                 $(byClassName("MediaName"));
  public static final SelenideElement ELEMENT_ABOUT_ME_PORTLET=$(byId("UIExperienceProfilePortlet"));
  public static final By ELEMENT_BUTTON_ACCEPT_INVITATION=byClassName("action-item");
  public static final By ELEMENT_BUTTON_CANCEL_INVITATION=byClassName("cancel-item");
  public static final By ELEMENT_EDIT_PROFILE= byClassName("uiIconEdit");
  public static final By ELEMENT_TITLE_INPUT= byClassName("textarea");
  public static final By ELEMENT_BUTTON_SAVE= byXpath("//*[@id=\"UIEditUserProfileForm\"]/div[3]/button[1]");
  public static final By ELEMENT_ABOUT_ME= byClassName("simpleBox");
  public static final ElementsCollection ELEMENT_LIST_OF_MEMBERS_IN_SPACE=$(byId("spaceMemberListBox")).findAll(byClassName("itemContainer"));
  public static final SelenideElement ELEMENT_BUTTON_ADD_GADGET=$(byClassName("AddIcon"));
  public static final SelenideElement ELEMENT_GADGET_LAST_FORUM_POST=$(byId("Adoption/gadget_LatestForumPosts"));
  public static final SelenideElement ELEMENT_CONTAINER_GADGETS=$(byId("UIDashboardContainer"));
  public static final SelenideElement ELEMENT_BUTTON_CLOSE_GADGETS=$(byClassName("UIPopupWindow")).find(byClassName("uiIconClose"));
  public static final SelenideElement ELEMENT_BUTTON_CLOSE_SPECIFIC_GADGET=$(byClassName("gadgets-gadget")).parent().parent().parent().parent().find(byClassName("uiIconClose"));
}
