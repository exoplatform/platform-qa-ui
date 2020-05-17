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

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
public final class ActivityStreamLocator {

    public static final String          ELEMENT_ACTIVITY_BOX                                 =
            "//*[@id='boxContainer']//*[contains(text(),\"${name}\")]/../../../..//*[@class='heading']";

    public static final String          ELEMENT_ACTIVITY_BOX_DELETE_BUTTON                   =
            "//*[@id='boxContainer']//*[contains(text(),\"${name}\")]/../../../..//*[contains(@class,'uiIconClose')]";

    public static final By ELEMENT_SPACE_MENU_ACTIVITY_PORTLET= byClassName("uiIconAppSpaceActivityStreamPortlet");

    public static final By              ELEMENT_SPACE_MENU_FORUM_PORTLET                     =
            By.xpath(".//*[@class='uiIconAppforum uiIconAppForumPortlet uiIconDefaultApp']");

    public static final By              ELEMENT_SPACE_MENU_DOCUMENT_PORTLET                  =
            By.xpath(".//*[@class='uiIconAppdocuments uiIconAppFileExplorerPortlet uiIconDefaultApp']");

    public static final By              ELEMENT_SPACE_MENU_AGENDA_PORTLET                    =
            By.xpath(".//*[@class='uiIconAppcalendar uiIconAppCalendarPortlet uiIconDefaultApp']");

    public static final By              ELEMENT_COMPOSER_ATTACH_LINK_BUTTON                  = By.xpath(".//*[@id='AttachButton']");

    public static final By              ELEMENT_COMPOSER_SHARE_BUTTON                        = By.xpath(".//*[@id='ShareButton']");

    // Task/Event activity
    public static final String          ELEMENT_ACTIVITY_TASK_EVENT_TITLE                    =
            "//*[@class='linkTitle' and text()='$name']";

    public static final String          ELEMENT_ACTIVITY_TASK_EVENT_COMMENT                  =
            "//*[@class='linkTitle' and text()='$name']/../../../..//*[@class='commentList']//*[contains(text(),'$comment')]";

    public static final String          ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_RECURRING_CANCEL =
            "//*[@class='linkTitle' and text()='$name']/../../../..//*[@class='commentList']//*[contains(text(),'Event cancelled for $date')]";

    public static final String          ELEMENT_ACTIVITY_EVENT_COMMENT_REPEAT_DAY            =
            "Event will be repeated every day, $number times";

    public static final String          ELEMENT_ACTIVITY_EVENT_COMMENT_CHECK_ALL_DAY         = "Event is now an all-day event";

    public static final String          ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_TITLE          = "Title has been updated to: $title";

    public static final String          ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_DES            =
            "Description has been updated to: $description";

    public static final String          ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_LOC            =
            "Location has been updated to: $location";

    public static final String          ELEMENT_ACTIVITY_WIKI_TITLE                          =
            "//*[@class='linkTitle' and text()='${title}']";

    public static final String          ELEMENT_ACTIVITY_WIKI_CONTENT                        =
            "//*[@class='linkTitle' and text()='${title}']/../../..//*[@class='contentWiki theContent']/*[@class='text']";

    public static final String          ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK               =
            ".//*[contains(text(),'$title')]/../../../..//*[contains(@class,'uiIconViewChange')]";

    public static final String          ELEMENT_QUESTION_ACTIVITY_UNACTIVATE_COMMENT         = "Question has been unactivated.";

    public static final String          ELEMENT_QUESTION_ACTIVITY_ACTIVATE_COMMENT           = "Question has been activated.";

    public static final String          ELEMENT_QUESTION_ACTIVITY_UPDAT_TITLE_COMMENT        = "Title has been updated to: $value";

    // Common activity
    public static final String          ELEMENT_ACTIVITY_COMMENT                             =
            ".//*[contains(text(),'${title}')]/../../../../..//*[contains(text(),\"${comment}\")]";

    public static final String          ELEMENT_ACTIVITY_NOT_ANY_COMMENT                     =
            ".//*[contains(text(),'$title')]/../../../../..//*[contains(@class,'commentList')][not(div)]";

    public static final String          ELEMENT_ACTIVITY_VIEW_A_NODE                         =
            "//*[@class='linkTitle' and contains(text(),'{$title}')]/../../../..//*[@class='uiIconWatch uiIconLightGray']";

    public static final String          ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM          =
            "//*[@id='boxContainer']//*[contains(text(),'${title}')]";

    public static final String          ELEMENT_ACTIVITY_TITLE                               =
            "//*[@id='boxContainer']//*[contains(text(),\"${text}\")]/../..//*[contains(text(),\"${file}\")]";

    public static final By              ELEMENT_PUBLICATION_FIRSTPOST_AUTHORAVATAR           =
            By.xpath("//div[1]/form//*[@class='activityAvatar avatarCircle']");

    public static final By              ELEMENT_PUBLICATION_FIRSTPOST_ACTIVITYTEXT           =
            By.xpath("//div[1]/form//*[@class='description']");

    public static final String          ELEMENT_ACTIVITY_WEBCONTENT_TITLE                    = ".//a[@title='${title}']";

    // Title box
    public static final String          ELEMENT_TITLE_BOX                                    =
            "//*[@id='boxContainer']//*[contains(text(),\"${title}\")]/../..//*[@class='linkTitle']";

    public static final String
            ELEMENT_PUBLICATION_SEEALLCOMMENTBTN =
            "//*[contains(text(),'${activity}')]/../..//*[contains(@class,'commentListInfo')]//a[@href and contains(text(),'View')]";
    public static final String
            ELEMENT_PUBLICATION_HIDEALLCOMMENTBTN =
            "//*[contains(text(),'${activity}')]/../..//*[contains(@class,'commentListInfo')]//a[@href and contains(text(),'Hide')]";

    public static final String          ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY               =
            ".//*[contains(text(),'${topic}')]/../../..//*[@class='uiIconSocLastestReply uiIconSocLightGray']";

    /**
     * Exit preview mode ELEMENT_PREVIEW_MODE_CROSS_ICON : "X" icon on the top-right
     */

    public static final String          ELEMENT_ACTIVITY_WEBCONTENT_CHECK_VERSION            =
            "//*[@class='uiIcon64x64Templateexo_webContent']/../..//*[@class='linkTitle' and contains(text(),'${title}')]/..//*[@class='versionFile' and contains(text(),'Version: {$version}')]";

    public static final String          ELEMENT_ACTIVITY_WEBCONTENT_CHECK_STATUS             =
            "//*[@class='uiIcon64x64Templateexo_webContent']/../..//*[@class='linkTitle' and contains(text(),'${title}')]/..//*[@class='versionFile' and contains(text(),'- {$status}')]";

  public static final By ELEMENT_ICONCOMMENT=byClassName("uiIconComment");
  public static final By ELEMENT_ICONLIKE=byClassName("uiIconThumbUp");
  public static final String ELEMENT_LINK_TEXT_VIEW_ALL_COMMENTS="//*[@id=\"CommentBlockBound{id}\"]/div[1]/a";
  public static final String ELEMENT_LIST_ALL_COMMENNTS="CommentBlockBound{id}";

    // Activity upload file

    public static final String ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE = "//*[contains(text(),'{$title}')]";
    public static final SelenideElement ELEMENT_ACTIVITY_INPUT_TEXT=$(byId("cke_1_contents"));
    public static final String ELEMENT_LIKE_BUTTON="//*[@id=\"LikeLink{id}\"]";
    public static final String ELEMENT_UNLIKE_BUTTON="//*[@id=\"UnLikeLink{id}\"]";
    public static final String ELEMENT_COMMENT_LINK="//*[@id=\"CommentLink{id}\"]";
    public static final String ELEMENT_COMMENT_INPUT="cke_CommentTextarea{id}";
    public static final String ELEMENT_COMMENT_BUTTON="//*[@id=\"CommentButton{id}\"]";
    public static final String ELEMENT_COMMENT_DELETE="DeleteCommentButtoncomment{id}";
    public static final SelenideElement ELEMENT_ACTIVITY_STREAM_CONTAINER=$(byId("UIActivitiesLoader"));
    public static final By ELEMENT_ICON_LIKE_ACTIVITY=byClassName("uiIconThumbUp");
    public static final SelenideElement ELEMENT_INPUT_ACTIVITY=$(byXpath("/html/body"));
    public static final SelenideElement ELEMENT_INPUT_COMMENT=$(byXpath("/html/body"));
    public static final String ELEMENT_INCON_LIKE_COMMENT="LikeCommentLinkcomment{id}";
    public static final String ELEMENT_INCON_LIKE_COMMENT_PREVIEW="likeCommentCount_comment{id}";
    public static final String ELEMENT_INCON_DELETE_COMMENT="DeleteCommentButtoncomment{id}";
    public static final String ELEMENT_lABEL_REPLY_COMMENT="CommentLinkcomment{id}";
    public static final String ELEMENT_VIEW_ALL_REPLIES_LINK="SubCommentShowAll_comment{id}";
    public static final SelenideElement ELEMENT_TOLLTIP_WHO_LIKE_COMMENT=$(byClassName("tooltip"));
    public static final SelenideElement ELEMENT_POPUP_WHO_LIKED_COMMENT= $(byId("likersPopup"));
    public static final SelenideElement ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT= $(byId("likersPopup")).find(byClassName("uiIconClose"));
    public static final SelenideElement ELEMENT_FIRST_USER_IN_POPUP_WHO_LIKE_COMMENT=ELEMENT_POPUP_WHO_LIKED_COMMENT.findAll(".likerName").get(0);
    public static final SelenideElement ELEMENT_SECOND_USER_IN_POPUP_WHO_LIKE_COMMENT=ELEMENT_POPUP_WHO_LIKED_COMMENT.findAll(".likerName").get(1);
    public static final SelenideElement ELEMENT_THIRD_USER_IN_POPUP_WHO_LIKE_COMMENT=ELEMENT_POPUP_WHO_LIKED_COMMENT.findAll(".likerName").get(2);
    public static final By ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT=byClassName("uiActionLike");
    public static final String ELEMENT_ACTIVITY_DROPDOWN="dropDownEditActivity{id}";
    public static final String ELEMENT_DELETE_ACTIVITY_LINK="DeleteActivityButton{id}";
    public static final String ELEMENT_EDIT_ACTIVITY_LINK="EditActivitylink{id}";
    public static final String ELEMENT_BUTTON_UPDATE_ACTIVITY="EditActivityButton{id}";
    public static final String ELEMENT_BUTTON_CANCEL_EDITACTIVITY="DeleteEditCommentButton{id}";
    public static final String ELEMENT_COMMENT_DROPDOWN="dropDownEditCommentcomment{id}";
    public static final String ELEMENT_EDIT_COMMENT_LINK="CommentActivitylinkcomment{id}";
    public static final String ELEMENT_BUTTON_UPDATE_COMMENT="EditCommentButtoncomment{id}";
    public static final String ELEMENT_BUTTON_CANCEL_EDITCOMMENT="DeleteEditCommentButtoncomment{id}";
    public static final String ELEMENT_ACTIVITY_TIME="EditActivityTime{id}";
    public static final String ELEMENT_COMMENT_TIME="EditCommentTimecomment{id}";
    public static final By ELEMENT_BUTTON_BOLD_ICON=byClassName("cke_button__bold_icon");
    public static final By ELEMENT_BUTTON_ITALIC_ICON=byClassName("cke_button__italic_icon");
    public static final By ELEMENT_BUTTON_REMOVE_FORMAT_ICON=byClassName("cke_button__removeformat_icon");
    public static final By ELEMENT_BLOCKQUOTE_ICON=byClassName("cke_button__blockquote_icon");
    public static final By ELEMENT_BULLETEDLIST_ICON=byClassName("cke_button__bulletedlist_icon");
    public static final By ELEMENT_NUMBERED_LIST_ICON=byClassName("cke_button__numberedlist_icon");
    public static final By ELEMENT_SIMPLELINK_ICON = byClassName("cke_button__simplelink_icon");
    public static final By ELEMENT_SELECTIMAGE_ICON= byClassName("cke_button__selectimage_icon");
    public static final String ELEMENT_LINK_VIEW_ALL_COMMENTS="//*[@id=\"CommentBlockBound{id}\"]/div[1]/a";
    public static final SelenideElement ELEMENT_ACTIVITY_STREAM_HEADING=$(byClassName("heading"));

}
