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
  // Activity portlet
  public static final By              ELEMENT_ACTIVITY_ICON_LIKE                                        =
                                                                 By.xpath(".//*[@id='UIUserActivityStreamPortlet']//*[contains(@class,'uiIconThumbUp ')]");

  // Notification popup list. Here, $name parameter is fullName or space's name
  public static final String          ELEMENT_NOTIFICATION_POPUP_INVITE_SPACE                           =
                                                                              ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'invited')]//*[contains(@class,'text-bold')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_JOIN_SPACE                             =
                                                                            ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'joined space')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_POPUP_REQUEST_JOIN_SPACE                     =
                                                                                    ".//*[@id='NotificationPopup']//*[@class='status'][contains(.,'requested')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER                        =
                                                                                 ".//*[contains(@class,'badgeNotification')][contains(text(),'$num')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_AVATAR                              =
                                                                           ".//*[@id='NotificationPopup']//*[contains(@class,'avatarXSmall')]//*[contains(@alt,'$lastUser')]";

  public static final By              ELEMENT_VIEW_ALL                                                  = By.linkText("View All");

  public static final String          ELEMENT_INTRANET_NOTIFICATION_STATUS                              =
                                                                           ".//*[@class='status'][contains(.,'$status')]//*[contains(@class,'user-name')][contains(text(),'$fullName')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_STATUS_SPACE                        =
                                                                                 ".//*[@class='status'][contains(.,\"$status\")]//*[contains(@class,'text-bold')][contains(text(),'$space')]";

  public static final By              ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ                    =
                                                                                     By.xpath("//*[@id=\"NotificationPopup\"]/li[3]/a");

  /*****************************************
   * User Status
   *******************************************/


  public static final SelenideElement ELEMENT_USER_STATUS_AVAILABLE =  $(By.xpath("//li[@class='user-available']"));

  public static final SelenideElement ELEMENT_USER_STATUS_DONOTDISTURB = $(By.xpath("//li[@class='user-donotdisturb']"));

  public static final SelenideElement ELEMENT_USER_STATUS_AWAY =  $(By.xpath("//li[@class='user-away']"));

  public static final SelenideElement ELEMENT_USER_STATUS_INVISIBLE =  $(By.xpath("//li[@class='user-invisible']"));

  // All notification page list
  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_LIKE                                =
                                                                         ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'likes')]//*[contains(@class,'user-name')][contains(text(),'$user')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_INVITE_SPACE                        =
                                                                                 ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'invited')]//*[contains(@class,'text-bold')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_JOIN_SPACE                          =
                                                                               ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'joined space')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final String          ELEMENT_NOTIFICATION_ALL_PAGE_REQUEST_JOIN_SPACE                  =
                                                                                       ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='status'][contains(.,'requested')]//*[contains(@href,'javascript:void(0)')][contains(text(),'$name')]";

  public static final By              ELEMENT_NOTIFICATION_UI_SPACE_ACCESS_PORTLET                      =
                                                                                   By.xpath(".//*[@id='UISpaceAccessPortlet']");

  // comment

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_TITLE                      =
                                                                                   ".//*[@id='NotificationPopup']//*[@class='content'][contains(.,'$title')]";

  // Connection
  public static final String          ELEMENT_CONNECT_ACCEPT_BUTTON                                     =
                                                                    ".//*[contains(text(),'$name')]/../..//*[contains(@class,'action-item')]";

  public static final String          ELEMENT_CONNECT_REFUSE_BUTTON                                     =
                                                                    ".//*[contains(text(),'$name')]/../..//*[contains(@class,'cancel-item')]";

  // All notification list
   public static final By              ELEMENT_ALL_NOTIFICATIONS                                         =
                                                              By.xpath(".//*[@id='UIIntranetNotificationsPortlet']//*[text()='All Notifications']");

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ALL_AVATAR                          =
                                                                               ".//*[@id='UIIntranetNotificationsPortlet']//*[contains(@class,'avatarXSmall')]//*[contains(@alt,'$lastUser')]";

  public static final String          ELEMENT_INTRANET_NOTIFICATION_ALL_ACTIVITY_TITLE                  =
                                                                                       ".//*[@id='UIIntranetNotificationsPortlet']//*[@class='content'][contains(.,'$title')]";

  /************************************************
   * ADMIN NOTIFICATION
   ****************************************************************************/
  // Enable notification's type
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

  // Notifications
  // New User
  public static final By              ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN                            =
                                                                             By.xpath(".//*[@id='NewUserPlugin']//*[contains(@class,'uiIconEdit')]");

  public static final By              ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX                      =
                                                                                   By.xpath(".//*[@id='MAIL_CHANNELNewUserPlugin']");

  public static final By              ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX                   =
                                                                                      By.xpath(".//*[@id='WEB_CHANNELNewUserPlugin']");

  public static final By              ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX_CHECKED              =
                                                                                           By.xpath(".//*[@id='MAIL_CHANNELNewUserPlugin'][contains(@checked,'checked')]");

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

  // My notification setting-->Selected box email notification
  public static final String          ELEMENT_NEW_USER_SELECTED_BOX_MAIL_ICON                           =
                                                                              "//*[@id='NewUserPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_CONNECTION_REQ_SELECTED_BOX_MAIL_ICON                     =
                                                                                    "//*[@id='RelationshipReceivedRequestPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_COMMENT_SELECTED_BOX_MAIL_ICON                            =
                                                                             "//*[@id='ActivityCommentPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final By              ELEMENT_MENTION_SELECTED_BOX_MAIL_ICON_ANY                        =
                                                                                 By.xpath("//*[@id='ActivityMentionPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']");

  public static final String          ELEMENT_LIKE_SELECTED_BOX_MAIL_ICON                               =
                                                                          "//*[@id='LikePlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final By              ELEMENT_POST_SELECTED_BOX_MAIL_ICON_ANY                           =
                                                                              By.xpath("//*[@id='PostActivityPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']");

  public static final String          ELEMENT_INVI_SPACE_SELECTED_BOX_MAIL_ICON                         =
                                                                                "//*[@id='SpaceInvitationPlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

  public static final String          ELEMENT_JOIN_SPACE_SELECTED_BOX_MAIL_ICON                         =
                                                                                "//*[@id='RequestJoinSpacePlugin']/../..//*[contains(@class,'status-true')]//*[@class='the-selectBox']//*[@class='uiIconPLFMail']/..//*[contains(text(),'$option')]";

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
  public static final By              ELEMENT_NEWUSER_ICON_INTRANET_NOTIFICATION                        =
                                                                                 By.xpath(".//*[contains(@for,'NewUserPlugin')]/../..//*[contains(@class,'uiIconPLFWeb')]");
  // Space invitation
  public static final By              ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_ICON               =
                                                                                          By.xpath(".//*[contains(@for,'SpaceInvitationPlugin')]/../..//*[contains(@class,'uiIconPLFWeb')]");

  // Activity comment
  public static final By              ELEMENT_ACTIVITY_COMMENT_ICON_INTRANET_NOTIFICATION               =
                                                                                          By.xpath(".//*[contains(@for,'ActivityCommentPlugin')]/../..//*[contains(@class,'uiIconPLFWeb')]");

  // disable notification's type
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

  public static final By              ELEMENT_WEB_VIEWMODE_FALSE                                        =
                                                                 By.xpath(".//*[contains(@class,'view-mode status-false')]//*[contains(@class,'PLFWeb')]");

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
  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_ICON                                   =
                                                                      By.xpath("//*[@id='RequestJoinSpacePlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX                          =
                                                                               By.xpath("//*[@id='MAIL_CHANNELRequestJoinSpacePlugin']");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX_CHECKED                  =
                                                                                       By.xpath("//*[@id='MAIL_CHANNELRequestJoinSpacePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_REQJOIN_SPACE_WEB_CHECKBOX                           =
                                                                              By.xpath("//*[@id='WEB_CHANNELRequestJoinSpacePlugin']");

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
  public static final By              ELEMENT_EDIT_LIKE_ICON                                            =
                                                             By.xpath("//*[@id='LikePlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_LIKE_MAIL_CHECKBOX                                   =
                                                                      By.xpath("//*[@id='MAIL_CHANNELLikePlugin']");

  public static final By              ELEMENT_EDIT_LIKE_MAIL_CHECKBOX_CHECKED                           =
                                                                              By.xpath("//*[@id='MAIL_CHANNELLikePlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_LIKE_WEB_CHECKBOX                                    =
                                                                     By.xpath("//*[@id='WEB_CHANNELLikePlugin']");

  public static final By              ELEMENT_EDIT_LIKE_LIST                                            =
                                                             By.id("MAIL_CHANNELLikePluginSelectBox");

  public static final By              ELEMENT_EDIT_LIKE_SAVE_BTN                                        =
                                                                 By.xpath("//button[@id='LikePlugin']");

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

  public static final By              ELEMENT_EDIT_COMMENT_LIST                                         =
                                                                By.id("MAIL_CHANNELActivityCommentPluginSelectBox");

  public static final By              ELEMENT_EDIT_COMMENT_SAVE_BTN                                     =
                                                                    By.xpath("//button[@id='ActivityCommentPlugin']");

  // Mention
  public static final By              ELEMENT_EDIT_MENTION_ICON                                         =
                                                                By.xpath("//*[@id='ActivityMentionPlugin']/..//*[@class='uiIconEdit uiIconLightGray']");

  public static final By              ELEMENT_EDIT_MENTION_MAIL_CHECKBOX                                =
                                                                         By.xpath("//*[@id='MAIL_CHANNELActivityMentionPlugin']");

  public static final By              ELEMENT_EDIT_MENTION_MAIL_CHECKBOX_CHECKED                        =
                                                                                 By.xpath("//*[@id='MAIL_CHANNELActivityMentionPlugin'][contains(@checked,'checked')]");

  public static final By              ELEMENT_EDIT_MENTION_WEB_CHECKBOX                                 =
                                                                        By.xpath("//*[@id='WEB_CHANNELActivityMentionPlugin']");

  public static final By              ELEMENT_EDIT_MENTION_LIST                                         =
                                                                By.id("MAIL_CHANNELActivityMentionPluginSelectBox");

  public static final By              ELEMENT_EDIT_MENTION_SAVE_BTN                                     =
                                                                    By.xpath("//button[@id='ActivityMentionPlugin']");

  /***********************************************
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
                                                                                     By.xpath("//*[@id=\"UIUserNavigationPortlet\"]/div[1]/ul/li[6]/a/div");

  /************************************************
   * USER PROFILE
   ****************************************************************************/
  public static final By              ELEMENT_EDIT_MY_PROFILE_LINK                                      =
                                                                   By.xpath("//*[@id=\"UIUserNavigationPortlet\"]/div[1]/div/div/ul/li/a");


  public static final By              ELEMENT_EDIT_PROFILE_FORM                                         =
                                                                By.id("UIEditUserProfileForm");

  public static final String          ELEMENT_NAME_OF_USER_TOP_RIGHT                                    =
                                                                     ".//*[@id='UIUserPlatformToolBarPortlet']//*[contains(normalize-space(),'${firstName} ${lastName}')]";

  // Left contact information
  public static final By              ELEMENT_UIBASICPROFILEPORTLET                                     =
                                                                    By.xpath(".//*[@id='UIBasicProfilePortlet']/h4[contains(text(),'Contact Information')]");

  public static final String          ELEMENT_EMAIL_INFO                                                =
                                                         ".//*[@class='uiEmail ellipsis' and @data-original-title='${email}']";

  public static final String          ELEMENT_PHONE_INFO                                                =
                                                         "//div[contains(text(),'${type}:')]/../*[@data-original-title='${phone}']";

  public static final String          ELEMENT_IM_INFO                                                   =
                                                      "//div[contains(text(),'${type}:')]/../*[@data-original-title='${im}']";

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

  // About me
  public static final By              ELEMENT_ABOUTME_TEXTAREA_EDIT                                     = By.id("aboutMe");

  public static final String          ELEMENT_UIEXPERIENCE_PROFILE_PORTLET                              =
                                                                           "//*[@id='UIExperienceProfilePortlet']//*[contains(text(),'${content}')]";

  // Basic information
  public static final By              ELEMENT_FIRST_NAME_TEXTBOX_EDIT                                   = By.id("firstName");

  public static final By              ELEMENT_LAST_NAME_TEXTBOX_EDIT                                    = By.id("lastName");

  public static final By              ELEMENT_EMAIL_TEXTBOX_EDIT                                        = By.id("email");

  // Contact
  public static final By              ELEMENT_CONTACT_GENDER_SELECTION                                  = byId("gender");

  public static final By              ELEMENT_CONTACT_JOB_TITLE                                         = By.name("position");

  public static final By              ELEMENT_CONTACT_IMS_ADD_ICON                                      =
                                                                   By.xpath("//*[@id='ims']//*[@data-original-title='Add Item']");

  public static final By              ELEMENT_CONTACT_PHONE_ADD_ICON                                    =
                                                                     By.xpath("//*[@id='phones']//*[@data-original-title='Add Item']");

  // Avatar
  public static final By              ELEMENT_CHANGE_AVATAR_LINK                                        =
                                                                 By.className("changeAvatar");

  // Experience
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

  // Save - Cancel button
  public static final By              ELEMENT_CONTACT_SAVE_BUTTON                                       =
                                                                  By.xpath(".//*[@id='UIEditUserProfileForm']//button[text()='Save']");

  public static final By              ELEMENT_CONTACT_CANCEL_BUTTON                                     =
                                                                    By.xpath(".//*[@id='UIEditUserProfileForm']//button[text()='Cancel']");

  // Recent activity
  public static final String          ELEMENT_RECENT_ACTIVITY_CONTENT                                   =
                                                                      "//*[@id='UIRecentActivitiesPortlet']//*[@class='activityCont']/div[${index}]//*[@class='status']/span[contains(text(),'${content}')]";

  public static final String          ELEMENT_RECENT_ACTIVITY_NO_CONTENT                                =
                                                                         "//*[@id='UIRecentActivitiesPortlet']//*[contains(text(),'${content}')]";

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

  // Connection status
  public static final By
                                      ELEMENT_ADD_CONNECT_PROFIL_STATUS                                =

                                                                                     By.xpath ("//*[@id=\"UIRelationshipAction\"]//*[@class='uiIconSocConnectUser']/..");
  public static final By

                                      ELEMENT_CLICK_DENY_USER_PROFIL_STATUS     =
                                                                                   By.xpath("//*[@id=\"UIRelationshipAction\"]/div/div[1]/ul/li/a");

  public static final By
                                      ELEMENT_ACCEPT_ADD_USER_PROFIL_STATUS    =
                                                                                      By.xpath("//*[@id=\"UIRelationshipAction\"]/div/div[1]/button[1]");
  public static final By
                                      ELEMENT_CANCEL_CONNECT_PROFILE_STATUS         =
                                                                                    By.xpath("//*[@id=\"UIRelationshipAction\"]//*[@class='uiIconClose']/..");

  public static final By
                                      ELEMENT_CANCEL_REQUEST_PROFIL_STATUS    =
                                                                                 By.xpath("//*[@id=\"UIRelationshipAction\"]/div/button");

  public static final By
                                      ELEMENT_ACCEPT_CONNECT_PROFIL_STATUS         =
                                                                                     By.xpath("//*[@id=\"UIRelationshipAction\"]//*[@class='btn-group btnStatusAnswer']/..");

  public static final By
                                      ELEMENT_DROPDOWN_DENY_PROFIL_STATUS          =
                                                                                     By.xpath("//*[@id=\"UIRelationshipAction\"]//*[@class='caret']/..");

  public static final By
                                      ELEMENT_DENY_PROFIL_STATUS        =
                                                                               By.xpath("//*[@id=\"UIRelationshipAction\"]//*[@class='dropdown-menu']/..");

  /****************************************************
   * SPACE HOME PAGE
   ***********************************************************************/
  // select menu (actvity stream, forum, agenda etc ..)
  public static final By              ELEMENT_SPACE_MENU_ACTIVITY_STREAM                                =
                                                                         By.xpath(".//*[@class='tabName' and contains(text(),' Activity Stream')]");

  // Navigation menu
  public static final By          ELEMENT_HOME_SPACE_TAB                                             =
                                                                 By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Home')]");

  public static final By              ELEMENT_ACTIVITY_STREAM_TAB                                       =
                                                                  By.xpath(".//*[contains(@class,'uiIconAppSpaceHomePage')]");

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

  /****************************************************
   * SPACE MANAGEMENTT
   ***********************************************************************/
  // Add form space
  public static final SelenideElement ELEMENT_ADDNEWSPACE_BUTTON                                        =
                                                                 $(byText("Add New Space"));

  public static final By              ELEMENT_ADDNEWSPACE_FORM                                          =
                                                               By.xpath("//span[@class='PopupTitle popupTitle' and text()='Add New Space']");

  // Search panel
  public static final By              ELEMENT_MY_SPACE_SEARCH_BTN                                       =
                                                                  By.xpath(".//*[@id='UISpaceSearch']//i[@class='uiIconSearch uiIconLightGray']");

  public static final String          ELEMENT_MY_SPACE_SEARCH_RESULT                                    =
                                                                     ".//*[@id='UIManageMySpaces']//*[contains(text(),'${name}')]";

  // Letter list
  public static final String          ELEMENT_MY_SPACE_LETTER_LIST                                      =
                                                                   ".//*[@class='letterList']//*[text()='${alpha}']";

  public static final SelenideElement              ELEMENT_SPACE_SAVE_BTN                                            =
                                                             $(byXpath(".//*[@id='UISpaceInfo']//button[text()='Save']"));

  public static final By              ELEMENT_SPACE_UPLOAD_CONFIRM_BTN                                  =
                                                                       By.xpath(".//*[@id='UIAvatarUploader']//button[text()='Confirm']");

  public static final By              ELEMENT_SPACE_UPLOAD_SAVE_BTN                                     =
                                                                    By.xpath(".//*[@id='UIAvatarUploadContent']//button[text()='Save']");

  // My space
  public static final SelenideElement ELEMENT_SPACE_DESCRIPTION                                         =
                                                                  $(By.xpath("//div[@id='spaceInfosApp']/p[@id='spaceDescription']"));

  public static final SelenideElement ELEMENT_SPACE_MANAGER_NAME                                             =
                                                                  $(By.xpath("//div[@id='spaceManagersList']/ul/li"));
  public static final By              ELEMENT_SPACE_AVATAR_DEFAULT                                      =
                                                                   By.xpath(".//*[@id='UISpaceInfo']//*[contains(@src,'SpaceAvtDefault.png')]");

  public static final String          ELEMENT_SPACE_LEAVE_BTN                                           =
                                                              "//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Leave']";

  public static final String          ELEMENT_SPACE_EDIT_BTN                                            =
                                                             "  //*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Edit']";

  public static final By              ELEMENT_SPACE_EDIT_SETTING_TAB                                    =
                                                                     By.xpath(".//*[contains(@data-target,'#UISpaceInfo-tab')]");

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

  public static final By              ELEMENT_MY_SPACE_JOIN_BTN                   =
                                                                                      byText("Join");

  public static final By              ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN                   =
                                                                                      byText("Request to Join");

  public static final String          ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING                       =
                                                                                  ".//*[contains(text(),'${space}')]/../../..//*[contains(text(),'Request Pending')]";

  public static final String          ELEMENT_ALL_SPACE_SPACE_NAME                                      =
                                                                   ".//*[contains(@class,'spaceBox')]//*[contains(@href,'$space')]";

  // Request pending tab
  public static final By              ELEMENT_MY_SPACE_REQUEST_PENDING_TAB                              =
                                                                           By.xpath("//*[contains(@href,'pendingSpace')]");

  // Members
  public static final By              ELEMENT_SPACE_MEMBERS                                             =
                                                            By.xpath("//*[@data-toggle='tab' and text()='Members']");

  public static final By              ELEMENT_SPACE_BTN_INVITE                                          =
                                                               By.xpath("//*[text()='Invite']");

  // Forum tab
  public static final By              ELEMENT_FORUM_START_BUTTON_UP                                     =
                                                                    By.xpath("(.//*[@id='UITopicContainer']//*[contains(@class,'uiIconForumCreateTopic ')])[1]");

  // Wiki tab
  public static final By              ELEMENT_WIKI_HOME_TITLE                                           =
                                                              By.xpath(".//*[@id='titleInfo']");

  // Document tab
  public static final By              ELEMENT_DOCUMENT_FOLDER_ADD_BTN                                   =
                                                                      By.xpath(".//*[contains(@class,'uiIconEcmsAddFolder ')]");

  public static final By              ELEMENT_ADDFOLDER_NAME                                            =
                                                             By.xpath("//*[@id='titleTextBox']");

  public static final By              ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON                              =
                                                                           By.xpath("//*[contains(@class,'addFolderButton')]");

  public static final String          ELEMENT_DOCUMENT_FOLDER_NAME                                      =
                                                                   ".//*[contains(@id,'UIDocumentInfo')]//*[contains(@data-original-title,'$name')]";

  public static final String          ELEMENT_DOCUMENT_FOLDER_UPLOADED_FILE                                      =
                                                                     "//div[@data-original-title='${file}']/following::a[@class='nodeLabel']";

  public static final String          ELEMENT_DOCUMENT_FOLDER_CHECK                                      =
                                                      "(//div[@data-original-title='${file}']/following::div[@class='columnCheckbox']/span)[1]";

  // Agenda tab
  public static final By              ELEMENT_AGENDA_EVENT_ADD_BTN                                      =
                                                                   By.xpath(".//*[@id='UIActionBarQuickAddEvent']");

  // Search user
  public static final By              ELEMENT_INPUT_SEARCH_USER_NAME                                    = By.id("searchTerm");

  public static final By              ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN                       =
                                                                                  By.xpath("//*[@id=\"UITabPanetab3\"]/div/button");

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

  public static final By              ELEMENT_ACCESS_INFO_OK_BTN                                        =
                                                                 By.xpath("//*[@class='PopupContent popupContent']//*[contains(text(),'OK')]");

  // Add application popup
  public static final By              ELEMENT_ADD_APPLICATION_POPUP_TITLE                               =
                                                                          By.xpath("//*[@class='UIPopupWindow uiPopup UIDragObject NormalStyle']//*[@class='PopupTitle popupTitle']");

  public static final By              ELEMENT_ADD_APPLICATION_POPUP_CLOSE_BTN                           =
                                                                              By.xpath(".//*[@id='UIAddApplication']//*[@class='uiIconClose pull-right']");

  // Settings tab
  public static final SelenideElement ELEMENT_SPACE_NAME_INPUT                                          = $(byId("displayName"));

  public static final SelenideElement ELEMENT_SPACE_DESCRIPTION_INPUT                                   = $(byId("description"));

  public static final By              ELEMENT_SPACE_CHANGE_AVATAR_BTN                                   =
                                                                      By.xpath(".//*[@id='UISpaceInfo']//button[text()='Change Picture']");

  public static final By              ELEMENET_SPACE_UPDATE_SAVE_BTN                                    =
                                                                     By.xpath(".//*[@id='UISpaceInfo']//button[contains(@onclick,'Save')]");

  // invitation member
  public static final String          ELEMENT_SPACE_INVITED_USER_TABLE                                  =
                                                                       ".//*[@id='UISpaceMember']//th[contains(text(),'Invited')]/../../..//*[contains(text(),'${user}')]";

  public static final String          ELEMENT_SPACE_MEMBERS_USER_TABLE                                  =
                                                                       ".//*[@id='UISpaceMember']//th[contains(text(),'Members')]/../../..//*[contains(text(),'${user}')]";

  // Invitation a group
  public static final By              ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_TAB                         =
                                                                                By.xpath(".//*[contains(@data-target,'#UISpaceGroupBound-tab')]");

  public static final By              ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX                    =
                                                                                     By.xpath(".//*[@id='UseExistingGroupCheckBox']");

  public static final String          ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECT_GROUP                =
                                                                                         ".//*[@id='UISocialGroupSelector']//*[contains(@title,'${name}')]";

  // Navigation tab
  public static final By              ELEMENT_SPACE_SETTING_NAVIGATION_TAB                              =
                                                                           By.xpath(".//*[contains(@data-target,'#UITabPanetab4-tab')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON                          =
                                                                               By.xpath(".//*[@id='UISpaceNavigationManagement']//button[text()='Add Node']");

  // Add/Edit page node popup
  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_NODE_TITLE                      =
                                                                                   By.xpath(".//*[@id='AddNode']//*[contains(.,'Add/ Edit Page Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_NAME                      =
                                                                                   By.xpath(".//*[@id='name']");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE                      =
                                                                                   By.xpath(".//*[@id='UIPageNodeForm']//button[text()='Save']");

  public static final By              ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LABEL                     =
                                                                                    By.xpath(".//*[@id='UIPageNodeForm']//*[contains(text(),'Label')]/..//input");

  // Context menu
  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT                        =
                                                                                 By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Edit this Node')]");

  public static final By              ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_DELETE                      =

                                                                                   By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Delete Node')]");

  // Access space information
  public static final By              ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE                         =
                                                                                By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Restricted Area']");

  public static final By              ELEMENT_SPACE_ACCESS_INFO                                         =
                                                                By.xpath(".//*[@class='spaceAccessInfo']");

  public static final By              ELEMENT_SPACE_ACCESS_JOIN_BTN                                     =
                                                                    By.xpath(".//*[@title='Join']");

  public static final By              ELEMENT_SPACE_ACCESS_REQUEST_JOIN_BTN                             =
                                                                            By.xpath(".//*[@title='Request to Join']");

  public static final By              ELEMENT_SPACE_ACCESS_SPACE_DENIED                                 =
                                                                        By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Access Denied']");

  public static final By              ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO                            =
                                                                             By.xpath(".//*[@class='spaceAccessInfo']");

  public static final String          ELEMENT_SPACE_ACCESS_SPACE_REQUEST_JOIN_MESSAGE                   =
                                                                                      ".//*[contains(text(),'You must be a member of the space')]//b[contains(text(),'$space')]/../..//*[contains(.,'to view this page')]";

  // Warining popup
  public static final String          ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER                             =
                                                                            ".//*[contains(text(),'${user}')]/..//*[@class='uiSwitchBtn']";


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

  public static final By              ELEMENT_CONFIRM_INFORMATION                                       =
                                                                  By.xpath(".//*[text() = 'OK']");

  public static final By              ELEMENT_SPACE_SPACE_SETTINGS                                      =
                                                                   By.xpath(".//*[@id='settings']");

  public static final By              ELEMENT_SPACE_WIKI_TAB                                            =
                                                             By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Wiki')]");

  public static final By              ELEMENT_SPACE_MENU_MORE                                           =
                                                              By.xpath(".//*[@id='spaceMenuTab']//*[contains(@class,'uiIconAppMoreButton')]");

  // Left and right Arrows "Tooltips"
  public static final SelenideElement ELEMENT_SPACE_TOOLTIP_LEFT                                            =
                                                                      $(byXpath("//a[@class='actionIcon prevDate pull-left']"));

  public static final SelenideElement ELEMENT_SPACE_TOOLTIP_RIGHT                                            =
                                                                      $(byXpath("//i[@class='uiIconMiniArrowRight uiIconLightGray']"));

  // Access and Edit tab form
  public static final String          ELEMENT_SPACE_TITLE                                               =
                                                          "//*[@class='spaceTitle']//*[text()='${space}']";

  public static final String          ELEMENT_SPACE_CONFIRM_DELETE                                      =
                                                                   "Are you sure you want to delete this space? This cannot be undone. All page navigations and this group will also be deleted";

  public static final By              ELEMENT_SPACE_DELETE_SPACE_OK_BUTTON                              =
                                                                           By.xpath("//*[text()='OK']");

  /**************************************************************
   * SPACE SETTING MANAGEMENT
   ********************************************************/
  public static final By              ELEMENT_SPACE_SPACE_SETTINGS_TITLE                                =
                                                                         By.xpath(".//*[@id='UISpaceSettingPortlet']/h3");

  // Members tab
  public static final SelenideElement ELEMENT_SPACE_SETTINGS_MEMBERS_TAB                                =
                                                                         $(byId("UISpaceMenu")).find(byClassName("uiIconAppmembers"));

  public static final SelenideElement ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB                 =
                                                                                        $(byId("UISpaceSettingPortlet")).find(byText("Members"));

  public static final By              ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB                  =
                                                                                       byClassName("uiIconValidate");

  public static final By              ELEMENT_ICON_DECLINE_SPACE_REQUEST_IN_MEMBERS_TAB                 =
                                                                                        byClassName("uiIconRemove");

  // Application tab
  public static final By              ELEMENT_SETTINGS_APP_TAB                                          =
                                                               By.xpath("//*[@id=\"UITabPane\"]/ul/li[4]");

  // Button create
  public static final SelenideElement ELEMENET_SPACE_CREATE_BUTTON                                      =
                                                                   $(byClassName("PopupContent")).find(byText("Create Space"));

  public static final SelenideElement ELEMENET_SPACE_SAVE_BUTTON                                      =
                                                                   $(byXpath("//div[@class='uiAction uiActionBorder']/button"));

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

  // CONNECTION REQUEST
  public static final SelenideElement ELEMENT_SPACE_MENU_TAB                                            = $(byId("spaceMenuTab"));

  // left menu
  public static final String          ELEMENT_SPACE_LEFT_MENU_SPACE_NAME                                =
                                                                         ".//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'${name}')]";

  public static final SelenideElement ELEMENT_MY_SPACE_SEARCH_TEXT                                      =
                                                                   $(byXpath("//*[@id=\"UISpaceSearch\"]/div[2]/div/div/div[1]/input"));

  public static final SelenideElement ELEMENT_SEARCHED_SPACE                                            =
                                                                    $(byXpath("//div/h4/a"));

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
                                                                         $(byId("UISpaceNavigationPortlet"));

  public static final SelenideElement ELEMENT_CONTENT_NAME_PROFILE                                      =
                                                                   $(byId("UIUserNavigationPortlet"));

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
  public static final SelenideElement ELEMENT_COLLABORATION_ACTIONS=  $(byClassName("uiIconPlusCircled"));
  public static final SelenideElement ELEMENT_SPACE_NAME_CONTAINER=$(byId("UISpaceMenuPortlet"));
  public static final SelenideElement ELEMENT_EDIT_ACTIVITY_ICON = $(byAttribute("for","EditActivityPlugin")).parent().parent().find(byClassName("uiIconEdit"));
  public static final SelenideElement ELEMENT_BUTTON_ON_MAIL_ACTIVITY = $(byId("MAIL_CHANNELEditActivityPlugin"));
  public static final SelenideElement ELEMENT_BUTTON_ON_MOBILE_ACTIVITY = $(byId("PUSH_CHANNELEditActivityPlugin"));
  public static final SelenideElement ELEMENT_BUTTON_ON_SITE_ACTIVITY = $(byId("WEB_CHANNELEditActivityPlugin"));
  public static final SelenideElement ELEMENT_EDIT_COMMENT_ICON_NOTIF = $(byAttribute("for","EditCommentPlugin")).parent().parent().find(byClassName("uiIconEdit"));
  public static final SelenideElement ELEMENT_BUTTON_ON_MAIL_COMMENT = $(byId("MAIL_CHANNELEditCommentPlugin"));
  public static final SelenideElement ELEMENT_BUTTON_ON_MOBILE_COMMENT = $(byId("PUSH_CHANNELEditCommentPlugin"));
  public static final SelenideElement ELEMENT_BUTTON_ON_SITE_COMMENT =   $(byId("WEB_CHANNELEditCommentPlugin"));
  public static final SelenideElement ELEMENT_ALL_NOTIFICATION=  $(byXpath("//*[@id=\"NotificationPopup\"]/li[5]/div/a"));
  public static final SelenideElement ELEMENT_NOTIFICATION_ACTIONS=  $(byClassName("notificationsActions"));
  public static final SelenideElement ELEMENT_SPACE_PORTLET =  $(By.id("UISpaceActivityStreamPortlet"));
  public static final SelenideElement ELEMENT_SPACE_MENU =  $(byXpath("//a[@class='chat-button btn']"));
  public static final By ELEMENT_EDITSPACE_MANAGE_ICON=  byClassName("uiIconEdit");
  public static final By ELEMENT_DELETESPACE_MANAGE_ICON=  byClassName("uiIconDeleteUser");
  public static final SelenideElement ELEMENT_SPACE_SEARCH_ICON= $(byClassName("showInputSearch"));
  public static final SelenideElement ELEMENT_SPACE_GRID=  $(byClassName("uiGrid"));
  public static final SelenideElement ELEMENT_SPACE_SETTING_PAGE=  $(byId("UISpaceSetting"));
  public static final SelenideElement ELEMENT_SPACE_NAME_PAGE= $(byId("displayName"));
  public static final SelenideElement ELEMENT_SPACE_DESCRIPTION_PAGE=   $(byId("description"));
  public static final SelenideElement ELEMENT_SPACEAVATAR_DEFAULT=  $(byAttribute("src", "/eXoSkin/skin/images/system/SpaceAvtDefault.png"));
  public static final SelenideElement ELEMENT_SPACEMANAGEMENT_CANCEL_POPUP= $(byClassName("modal-content")).find(byClassName("btn-primary"));
  public static final SelenideElement ELEMENT_SPACEMANAGEMENT_ClOSE_POPUP= $(byClassName("modal-content")).find(byClassName("uiIconClose"));
  public static final SelenideElement ELEMENT_SPACEMANAGEMENT_POPUP=$(byClassName("modal-content")).find(byClassName("btn"));
  public static final SelenideElement ELEMENT_SPACE_MANAGEMENT_SECTION=$(byText("Manage spaces"));
  public static final SelenideElement ELEMENT_PERMISSIONS_SECTION=$(byText("Permissions"));
  public static final SelenideElement ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE=$(byText("Create spaces"));
  public static final SelenideElement ELEMENT_PERMISSIONS_SECTION_Ability_SPACE=$(byText("Ability to create spaces"));
  public static final SelenideElement ELEMENT_PERMISSIONS_SECTION_Ability_EDIT_SPACE=$(byText("Ability to edit spaces"));
  public static final SelenideElement ELEMENT_SPACE_ADMIN_PAGE=$(byClassName("spacesAdministration"));

}

