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
package org.exoplatform.platform.qa.ui.selenium.testbase;

/**
 * Created by mgreau on 01/02/2017.
 */
public final class DefaultDataTestBase {

  /* ========Default System Property============= */
  public static final String  DEFAULT_SSOTYPE                            = "";

  public static final String  DEFAULT_NATIVE_EVENT                       = "true";

  // firefox,
  // chrome

  public static final Boolean DEFAULT_ISRANDOM                           = true;
  // ubuntu

  public static final Boolean DEFAULT_ISUSEFILE                          = true;

  public static final String  DEFAULT_JDBCDRIVER                         = "com.mysql.jdbc.LegacySeleniumDriver";

  public static final String  DEFAULT_DBURL                              = "jdbc:mysql://localhost:3306/selenium";

  public static final String  DEFAULT_USERMYSQL                          = "root";

  public static final String  DEFAULT_USERPASS                           = "exo";

  public static final String  DEFAULT_SQLWIKI                            = "select * from wiki order by id asc";

  public static final String  DEFAULT_SQLATTACHMENT                      = "select * from space order by id asc";

  public static final String  DEFAULT_SQLUSER                            =
                                              "select type,username,password,email from user order by id asc";

  public static final String  DEFAULT_SQLCONTENT                         = "select * from textbox order by id asc";

  public static final String  DEFAULT_SHEET                              = "sheet1";

  public static final String  DEFAULT_USERFILEURL                        = "DataDriven/" + "user.xls";

  public static final String  DEFAULT_ATTACHMENTFILEURL                  = "DataDriven/" + "attachment_file.xls";

  public static final String  DEFAULT_TEXTBOXFILEURL                     = "DataDriven/" + "textbox.xls";

  public static final String  DEFAULT_WIKITEMPLATEFILEURL                = "DataDriven/" + "wiki_template.xls";

  public static final String  DEFAULT_PERMISSIONURL                      = "DataDriven/" + "permission.xls";

  public static final String  DEFAULT_SITEEXPLORERDRIVE                  = "DataDriven/" + "se_drive.xls";

  public static final String  DEFAULT_SITEEXPLORERPATH                   = "DataDriven/" + "se_path.xls";

  public static final String  DEFAULT_DATATESTPATH                       = "DataDriven/" + "dataTest_folder_fath.xls";

  public static final String  DEFAULT_SITE_EXPLORER_CONTENT_TYPE_PATH    = "DataDriven/" + "se_content_types.xls";

  public static final String  DEFAULT_VIDEO_EMBBED_LINKS_PATH            = "DataDriven/" + "video_embbed_links.xls";

  public static final String  DEFAULT_CONTACTIMURL                       = "DataDriven/" + "contact_im.xls";

  public static final String  DEFAULT_CONTACTPHONEURL                    = "DataDriven/" + "contact_phone.xls";

  public static final String  DEFAULT_ACTIVITYMESSAGEURL                 = "DataDriven/" + "activity_message.xls";

  public static final String  DEFAULT_CONNECTSTATUSURL                   = "DataDriven/" + "connect_status.xls";

  public static final String  DEFAULT_WIKIRICHTEXTFILEURL                = "DataDriven/" + "wiki_richtext.xls";

  public static final String  DEFAULT_CHANGELANGUADATAURL                = "DataDriven/" + "ChangeLanguage.xls";

  public static final String  DEFAULT_REMOTEGADGETURL                    = "DataDriven/" + "remote_gadget_links.xls";

  public static final String  DEFAULT_APPGATEINURL                       = "DataDriven/" + "application_gatein.xls";

  public static final String  DEFAULT_GETTINGSTARTEDURL                  = "DataDriven/" + "getting_started.xls";

  public static final String  DEFAULT_WIKIMESSAGEURL                     = "DataDriven/" + "wiki_message.xls";

  public static final String  DEFAULT_GADGETURL                          = "DataDriven/" + "gatein_gadget.xls";

  public static final String  DEFAULT_CONTAINERURL                       = "DataDriven/" + "containers_layout.xls";

  public static final String  DEFAULT_LANGUAGEURL                        = "DataDriven/" + "language.xls";

  public static final String  DEFAULT_SELECTPATHURL                      = "DataDriven/" + "selectPath.xls";

  public static final String  DEFAULT_MEMBERSHIPURL                      = "DataDriven/" + "membership.xls";

  public static final String  DEFAULT_APPLAYOUTURL                       = "DataDriven/" + "applications_layout.xls";

  public static final String  DEFAULT_APPLISTGETINURL                    = "DataDriven/" + "gatein_applications_list.xls";

  public static final String  DEFAULT_APPADDGATEINURL                    = "DataDriven/" + "gatein_applications_add.xls";

  public static final String  DEFAULT_CREATENEWGATEINURL                 = "DataDriven/" + "create_new_gatein.xls";

  public static final String  DEFAULT_CATEGORIGATEINURL                  = "DataDriven/" + "gatein_categories.xls";

  public static final String  DEFAULT_PAGE_MANAGEMENT_LIST_URL           = "DataDriven/" + "gatein_page_management_list.xls";

  public static final String  DEFAULT_PORTAL_DEFAULT_GATEIN_URL          = "DataDriven/" + "gatein_portal_defaults.xls";

  public static final String  DEFAULT_PORTAL_PERMISSION_GROUP_URL        = "DataDriven/" + "gatein_portal_groups.xls";

  public static final String  DEFAULT_PORTAL_PERMISSION_MEMBERSHIPS_URL  = "DataDriven/" + "gatein_portal_memberships.xls";

  public static final String  DEFAULT_USERINFOURL                        = "DataDriven/" + "userinfo.xls";

  public static final String  DEFAULT_MAILSUFFIXURL                      = "DataDriven/" + "mail_suffix.xls";

  public static final String  DEFAULT_USERSEARCHOPTIONURL                = "DataDriven/" + "gatein_user_search_options.xls";

  public static final String  DEFAULT_GATEINDEFAULTGROUPSURL             = "DataDriven/" + "gatein_default_groups.xls";

  public static final String  DEFAULT_GATEINNODESURL                     = "DataDriven/" + "gatein_nodes.xls";

  public static final String  DEFAULT_LINKSURL                           = "DataDriven/" + "links.xls";

  public static final String  DEFAULT_GROUPNAMEURL                       = "DataDriven/" + "gatein_group_name.xls";

  public static final String  DEFAULT_CHATSTATUS_URL                     = "DataDriven/" + "chat_status.xls";

  // Social
  public static final String  DEFAULT_SPACE_UI_URL                       = "DataDriven/" + "space_GUI.xls";

  public static final String  DEFAULT_SPACEVISIBLEFILEURL                = "DataDriven/" + "space_visibility.xls";

  public static final String  DEFAULT_SPACEREGISTRATIONFILEURL           = "DataDriven/" + "space_registration.xls";

  public static final String  DEFAULT_SPACEAPPLICATIONURL                = "DataDriven/" + "space_application.xls";

  public static final String  DEFAULT_SPACE_NAVIGATION_DEFAULT_NODES_URL = "DataDriven/" + "space_navigation_default_nodes.xls";

  public static final String  DEFAULT_SPACE_GROUPS_URL                   = "DataDriven/" + "space_groups.xls";

  public static final String  DEFAULT_SPACE_WARNING_MESSAGE_URL          = "DataDriven/" + "space_warning_message.xls";

  public static final String  DEFAULT_NOTIFICATION_INTRANET_URL          = "DataDriven/" + "soc_notification_intranet.xls";

  public static final String  DEFAULT_NOTIFICATION_EMAIL_URL             = "DataDriven/" + "soc_notification_email.xls";

  public static final String  DEFAULT_ACTIVITY_COMMENT_URL               = "DataDriven/" + "activity_comment.xls";

  public static final String  DEFAULT_NOTIFICATION_CATEGORY_URL          = "DataDriven/" + "soc_notification_category.xls";

  public static final String  DEFAULT_NOTIFICATION_FORMAT_EMAIL_URL      = "DataDriven/" + "soc_notification_format_email.xls";

  public static final String  DEFAULT_NOTIFICATION_LABLE_URL             = "DataDriven/" + "soc_notification_label.xls";

  public static final String  DEFAULT_NOTIFICATION_MESSAGE_URL           = "DataDriven/" + "soc_notification_message.xls";

  // Calendar
  public static final String  DEFAULT_CALENDAR_GROUP_NAME_URL            = "DataDriven/" + "cal_group.xls";

  public static final String  DEFAULT_CALENDAR_TAB_NAME_URL              = "DataDriven/" + "cal_tabs.xls";

  public static final String  DEFAULT_CALENDAR_COMMENTS_URL              = "DataDriven/" + "cal_comments.xls";

  public static final String  DEFAULT_CALENDAR_REMOTE_URL                = "DataDriven/" + "cal_remote.xls";

  // Task Management
  public static final String  DEFAULT_COLOR_FILE_URL                     = "DataDriven/" + "color.xls";

  // Wiki
  public static final String  DEFAULT_WIKI_IMAGE_LINKS_FILE_URL          = "DataDriven/" + "wiki_image_links.xls";

  public static final String  DEFAULT_WIKI_WARNING_FILE_URL              = "DataDriven/" + "wiki_warnings.xls";

  public static final String  DEFAULT_SOURCE_TEXT_EFFECT                 = "DataDriven/" + "wiki_sourceEditor_effects.xls";

  public static final String  DEFAULT_WIKI_MACRO_FILE_URL                = "DataDriven/" + "wiki_macro.xls";

  // Permission
  public static final String  DEFAULT_PERMISSION_GROUP_FILE_URL          = "DataDriven/" + "permission_groups.xls";

  public static final String  DEFAULT_PERMISSION_MEMBERSHIP_FILE_URL     = "DataDriven/" + "permission_memberships.xls";

  private DefaultDataTestBase() {
  }
}
