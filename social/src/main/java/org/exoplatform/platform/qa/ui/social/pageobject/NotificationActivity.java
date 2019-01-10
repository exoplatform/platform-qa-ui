package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TREE_WIKI_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class NotificationActivity {
  private final TestBase       testBase;

  // public WikiHomePage wikiHome;

  public PlatformBase          plf;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase TestBase
   */
  public NotificationActivity(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    // this.wikiHome = new WikiHomePage(testBase);
    this.plf = new PlatformBase(testBase);
  }

  /**
   * Check notification's comment type in notification list popup
   * 
   * @param users String
   * @param comment String
   * @param actTitle String
   */
  public void checkCommentActivityNotificationFormat(ArrayList<String> users, String comment, String actTitle) {
    int lastIndex = users.size() - 1;
    info("users.size():" + users.size());
    info("Verify that last user's avatar is shown in list");
    evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_AVATAR.replace("$lastUser", users.get(lastIndex)));

    info("Verify that last user is shown in list");
    evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_USER.replace("$user", users.get(lastIndex)));
    info("users.size():" + users.size());
    if (users.size() > 0) {
      info("Verify that second last user is shown in list");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_USER.replace("$user", users.get(lastIndex - 1)), 2000, 2);
      if (users.size() > 2) {
        info("Verify the activity message for more 2 users comments");
        evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_COMMENTS_CONTENT.replace("$comment", comment)
                                                                               .replace("$number", users.get(lastIndex - 1)),
                                 2000,
                                 2);
      } else {
        info("Verify the activity message for 2 or 1 comment(s)");
        evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_COMMENTS_CONTENT.replace("$comment", comment), 2000, 2);
      }
    }

    if (!actTitle.isEmpty()) {
      info("Verify the activity's title is shown in the list");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_TITLE.replace("$title", actTitle), 2000, 2);
    }

  }

  /**
   * Verify that the comment is expanded and highlight or not highlight
   * 
   * @param comment is comment's content
   * @param isHighlight = true if wants to check the comment is highlighted =
   *          false if wants to check the comment is not highlighted or ignore
   *          this case
   */
  public void checkCommentExpand(String comment, boolean isHighlight) {
    if (!comment.isEmpty()) {
      info("Verify that all comments are expanded");
      $(byXpath(ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_COMMENT_CONTENT.replace("$comment", comment))).waitUntil(Condition.visible, Configuration.timeout);

      if (isHighlight) {
        info("Verify that the last comment is highlighted");
        $(byXpath(ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_COMMENT_HIGHLIGHT.replace("$comment", comment))).waitUntil(Condition.visible,Configuration.timeout);
      }
    }
  }

  /**
   * function: check comment in activity viewer
   * 
   * @param userName
   * @param comment
   * @param highlighted (true if you need to check the comment is highlighted)
   */
  public void checkCommentInActivityViewer(String comment, String userName, boolean highlighted) {
    info("Check comment in activity viewer");

    if (highlighted) {
      info("Check comment in Activity viewer to make sure it's highlighted");
      evt.waitForAndGetElement(ELEMENT_COMMENT_HIGHLIGHTED.replace("${comment}", comment));
    } else {
      info("Check to make sure all comments are shown in activity viewer");
      evt.waitForAndGetElement(ELEMENT_COMMENT_ACTIVITY_VIEWER.replace("${comment}", comment).replace("${userName}", userName));
    }
  }

  /**
   * function: check like in activity viewer
   * 
   * @param number number of like
   */
  public void checkLikeInActivityViewer(String number) {
    info("Check like in Activity viewer");

    assert (evt.waitForAndGetElement(ELEMENT_LIKE_IN_ACTIVITY_VIEWER).getText().contains(number));
  }

  /**
   * function: check mention in activity viewer
   * 
   * @param Activity the activity you are mentioned
   */
  public void checkMentionInActivityViewer(String Activity) {
    info("Check Mention notification in Activity Viewr");

    evt.waitForTextPresent(Activity);
  }

  /**
   * Verify that the activity is shown with correct content
   * 
   * @param text is the title of the activity
   */
  public void checkTitleActivityExpand(String text) {
    if (!text.isEmpty()) {
      info("Verifyt that the Activity is shown with correct it's content");
      $(byXpath(ELEMENT_NOTIFICATION_ACTIVITY_TITLE_CONTENT.replace("$text", text))).is(Condition.visible);
    }
  }

  /**
   * Verify that the activity is not found
   */
  public void verifyActivityNotFound() {
    info("Activity not found text is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_NOT_FOUND);
  }

  /**
   * Verify detail Activity's format
   * 
   * @param isCommentBox =true if wants to check comment's box =false if don't
   *          want
   * @param content is activity's title
   */
  public void checkFormatDetailActivity(Boolean isCommentBox, String content) {
    info("Verify that author's avatar is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_AUTHOR_NAME);
    info("Verify that author's name is shown");
    evt.waitForAndGetElement(ELEMENT_ACITIVITY_AUTHOR_AVATAR);
    info("Verify that the activity is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_TITLE_CONTENT.replace("$text", content));
    info("Verify that like's icon is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_ICON_LIKE);
    info("Verify that comment's icon is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_ICON_COMMENT);
    if (isCommentBox) {
      info("Verify that comment's box is shown");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_BOX);
    }
  }

  /**
   * Verify the title of the page
   * 
   * @param expectedTitle is the title's content
   */
  public void verifyTitlePage(String expectedTitle) {
    info("Verify that the title of the page is shown with correct data");
    String actualTitle = testBase.getExoWebDriver().getWebDriver().getTitle();
    info("Actual title as:" + actualTitle);
    if (expectedTitle.equals(actualTitle))
      assert true;
    else
      assert false : "The title is not correct";
  }

  /**
   * Reply an activity
   * 
   * @param comment is the content of the comment
   */
  public void reply(String comment) {
    if (!comment.isEmpty()) {
      WebElement commentText = evt.waitForAndGetElement(ELEMENT_COMMENTBOX);
      WebElement commentButton = evt.waitForAndGetElement(ELEMENT_COMMENT_BUTTON);
      WebElement workingLabel = evt.waitForAndGetElement(ELEMENT_ACTIVITY_ADD_YOUR_COMMENTLABEL);

      ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].textContent = '';",
                                                                                     workingLabel);
      ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript(
                                                                                     "arguments[0].textContent = '" + comment
                                                                                         + "';",
                                                                                     commentText);
      ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].disabled = false;",
                                                                                     commentButton);
      ((JavascriptExecutor) testBase.getExoWebDriver()
                                    .getWebDriver()).executeScript("arguments[0].className = 'btn pull-right btn-primary';",
                                                                   commentButton);
      evt.click(ELEMENT_COMMENT_BUTTON);
      info("Verify comment successfully");
      evt.waitForAndGetElement(ELEMENT_DELETE_COMMENT_BUTTON.replace("${commentText}", comment),
                               testBase.getDefaultTimeout(),
                               1,
                               2);
      info("Add comment successfully");
    }

  }

  /**
   * Verify information of Answer activity
   * 
   * @param numPoint is the number of the point
   * @param numAnswer is the number of the answer
   * @param numCom is the number of the comment
   */
  public void verifyAnswerActivity(String numPoint, String numAnswer, String numCom) {
    if (numPoint != "" || numPoint != null) {
      info("Verify that the point's number is correct");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_ANSWER_POINT_NUMBER.replace("$number", numPoint));
    }

    if (numAnswer != "" || numAnswer != null) {
      info("Verify that the answer's number is correct");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_ANSWER_NUMBER.replace("$number", numAnswer));
    }

    if (!numCom.isEmpty()) {
      info("Verify that the comment's number is correct");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_ANSWER_COMMENT_NUMBER.replace("$number", numCom));
    } else {
      info("Verify that has no comment's number");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_ANSWER_COMMENT_NO);
    }

    info("Verify that rate's number doesn't have");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_ANSWER_RATE_NOT_VALUE);
  }

  /**
   * Verify information of uploaded file activity
   * 
   * @param fileName
   */
  public void verifyActivityFileUpload(String fileName) {
    if (!fileName.isEmpty()) {
      info("Verify that the file name is shown");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_UPLOAD_FILE_NAME.replace("$fileName", fileName));
    }
    info("Verify that the thumbnail is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_UPLOAD_FILE_THUMBNAIL);
    info("Verify that the View icon is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_UPLOAD_FILE_VIEW_ICON);
    info("Verify that the Download icon is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_UPLOAD_FILE_DOWNLOAD);
    info("Verify that file size is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_UPLOADED_FILE_SIZE);
  }

  /**
   * Click on View button
   */
  public void viewDetailActivityByViewBtn() {
    info("Click on View button");
    evt.click(ELEMENT_ACTIVITY_UPLOAD_FILE_VIEW_ICON);

  }

  /**
   * Click on Image thumbnail
   */
  public void viewDetailActivityByImageThumbnail() {
    info("Click on Image Thumbnail");
    evt.click(ELEMENT_ACTIVITY_UPLOAD_FILE_THUMBNAIL);

  }

  /**
   * Get download file url
   * 
   * @return url
   */
  public String getDownloadFileUrl() {
    info("Click on Download icon");
    String url = evt.waitForAndGetElement(ELEMENT_ACTIVITY_UPLOAD_FILE_DOWNLOAD).getAttribute("href").toString();
    return url;
  }

  /**
   * Verify that default comment is expanded and highlight or not highlight
   * 
   * @param comment is comment's content
   * @param value is the value of the comment that sent to default comment as:
   *          Answer has been submitted: $comment,....
   * @param isHighlight = true if wants to check the comment is highlighted =
   *          false if wants to check the comment is not highlighted or ignore
   *          this case
   */
  public void checkCommentExpand(String comment, String value, boolean isHighlight) {
    if (!comment.isEmpty()) {
      info("Verify that all comments are expanded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("$comment", comment).replace("$comment", value), 2000, 1);

      if (isHighlight) {
        info("Verify that the last comment is highlighted");
        evt.waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_HIGHLIGHT.replace("$comment", comment).replace("$comment", value),
                                 2000,
                                 1);
      }
    }
  }

  /**
   * Click on Reply button of the topic
   */
  public void goToReplyBtnTopicActivity() {
    info("Click on Reply button");
    evt.click(ELEMENT_ACTIVITY_TOPIC_REPLY_ICON);

  }

  /**
   * Input information for Reply form
   * 
   * @param title String
   * @param content String
   */
  public void replyTopic(String title, String content) {
    String titleWindows = this.testBase.getExoWebDriver().getWebDriver().getTitle();
    info("titleWinodws:" + titleWindows);
    if (!title.isEmpty())
      evt.type(ELEMENT_TITLE_POST, title, true);
    inputFrame(ELEMENT_POST_CONTENT, content, titleWindows);
    evt.click(ELEMENT_POST_FORM_SUBMIT);
    info("Verify that the post is created");
    evt.waitForAndGetElement(ELEMENT_POST_IN_TOPIC.replace("{$title}", title).replace("{$content}", content));
  }

  /**
   * Type a text to a Frame using for CKEDITOR By QuynhPT
   * 
   * @param frameLocator By
   * @param content  String
   * @param titleWindow  String
   */
  public void inputFrame(By frameLocator, String content, String titleWindow) {
    info("Finding the frameLocator:" + frameLocator);
    WebElement e = evt.waitForAndGetElement(frameLocator, testBase.getDefaultTimeout(), 1, 2);
    info("Switch to the frame:" + frameLocator);
    testBase.getExoWebDriver().getWebDriver().switchTo().frame(e);
    WebElement inputsummary = testBase.getExoWebDriver().getWebDriver().switchTo().activeElement();
    info("focus on the text area");
    inputsummary.click();
    info("Input the content:" + content);
    inputsummary.clear();
    inputsummary.sendKeys(content);
    plf.switchBetweenBrowsers(titleWindow);
  }

  /**
   * Click on view last reply button of the topic
   */
  public void goToViewLastReplyBtnTopicActivity() {
    info("Click on view last reply button");
    evt.click(ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY_ICON);

  }

  /**
   * Verify topic is shown
   * 
   * @param title String
   * @param description String
   */
  public void verifyReplyPostTopic(String title, String description) {
    if (!title.isEmpty() && !description.isEmpty()) {
      info("Verify that the post is shown");
      evt.waitForAndGetElement(ELEMENT_POST_IN_TOPIC.replace("{$title}", title).replace("{$content}", description));
    }
  }

  /**
   * Verify activity's format of Topic activity
   * 
   * @param desTopic String
   * @param numReply String
   */
  public void verifyActivityTopic(String desTopic, String numReply) {
    if (!desTopic.isEmpty()) {
      info("Verify that topic's description is shown");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_TOPIC_DESCRIPTION.replace("$des", desTopic));
    }

    if (!numReply.isEmpty()) {
      info("Verify that topic's reply's number is shown");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_TOPIC_REPLY_NUMBER.replace("$number", numReply));
    } else {
      info("Verify that has not any replying");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_TOPIC_NO_REPLY);
    }

    info("Verify that reply's icon is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_TOPIC_REPLY_ICON);

    info("Verify that view last reply's icon is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY_ICON);
  }

  /**
   * Check version and description of Wiki activity
   * 
   * @param description String
   */
  public void verifyActivityWiki(String description) {
    if (!description.isEmpty()) {
      info("Verify that the description of wiki page is shown");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_WIKI_DESCRIPTION.replace("$des", description));
    }

    info("Verify that the wiki's version is shown");
    evt.waitForAndGetElement(ELEMENT_ACTIVITY_WIKI_VERSION);

  }

  /**
   * Go to detail wiki page via clicking wiki's activity's title
   * 
   * @param title String
   */
  public void goToDetailWikiPage(String title) {
    if (!title.isEmpty()) {
      info("click on Title of Wiki activity");
      evt.click(ELEMENT_ACTIVITY_TITLE_CONTENT.replace("$text", title));
      evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", title), 2000, 1);
    }

  }

  /**
   * Verify that many comments are expanded and the last comment is highlighted or
   * not highlighted
   * 
   * @param comments is an array of comments
   * @param isHighlight = true to check last comment that is highlighted = false
   *          to check last comment is not highlighted or ignore this case
   */
  public void checkCommentsExpand(ArrayList<String> comments, boolean isHighlight) {
    if (comments.size() > 0) {
      for (int i = 0; i < comments.size(); i++) {
        info("Verify that all comments are expanded");
        evt.waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("$comment", comments.get(i)), 2000, 1);

        if (isHighlight) {
          info("Verify that the last comment is highlighted");
          evt.waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("$comment", comments.get(comments.size() - 1)),
                                   2000,
                                   1);
        }
      }
    }
  }
}
