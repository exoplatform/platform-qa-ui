package org.exoplatform.platform.qa.ui.social.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_PLF_HOMEPAGE_DISPLAY;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.Set;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class EmailNotifications {
  private final TestBase       testBase;

  public UserProfilePage       userProPage;

  public HomePagePlatform      hpPlat;

  public SpaceHomePage         spaceHome;

  public ActivityStream        hpAct;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public EmailNotifications(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    userProPage = new UserProfilePage(testBase);
    hpPlat = new HomePagePlatform(testBase);
    spaceHome = new SpaceHomePage(testBase);
    hpAct = new ActivityStream(testBase);
  }

  /**
   * Get list all Browsers
   */
  public void getAllChildWindows() {
    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
      testBase.getExoWebDriver().getWebDriver().manage().window().maximize();
    }
  }

  /**
   * Close all child drivers
   *
   * @param parentWindow is the tilte of parent browsers
   */
  public void closeChildBrowsers(String parentWindow) {
    info("parentWindow:" + parentWindow);
    Set<String> handlers = testBase.getExoWebDriver().getWebDriver().getWindowHandles();
    // Handler will have all the three window handles
    for (String windowHandle : handlers) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("windowHandle" + windowHandle);
      // If it is not the parent window it will close the child window
      if (!windowHandle.contains(parentWindow)) {
        info("close driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());

        testBase.getExoWebDriver().getWebDriver().close();
      }

    }
  }

  /**
   * Verify email notification's title is shown
   *
   * @param fullName is full name of the user
   * @param content is the title of email notification
   */
  public void verifyPresentTitlePostMyASEmail(String fullName, String content) {
    info("Verify that email notificaiton is sent to user's inbox");
    evt.waitForAndGetElement(ELEMENT_GMAIL_POST_IN_ACTIVITY_STREAM.replace("$fullName", fullName).replace("$content", content),
                             30000,
                             1);
  }

  /**
   * Verify email notification's title is shown
   *
   * @param fullName is full name of the user
   * @param spaceName is space
   * @param content is the title of email notification
   */
  public void verifyPresentTitlePostSpaceASEmail(String fullName, String spaceName, String content) {
    info("Verify that email notificaiton is sent to user's inbox");
    evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE_POST_IN_SPACE_AS.replace("$fullName", fullName)
                                                                 .replace("$spaceName", spaceName)
                                                                 .replace("$content", content),
                             30000,
                             1);
  }

  /**
   * Verify email notification's title is not shown
   *
   * @param fullName is full name of the user
   * @param content is the title of email notification
   */
  public void verifyNOTPresentTitlePostMyASEmail(String fullName, String content) {
    info("Verify that email notificaiton is sent to user's inbox");
    evt.waitForElementNotPresent(ELEMENT_GMAIL_POST_IN_ACTIVITY_STREAM.replace("$fullName", fullName).replace("$content",
                                                                                                              content));
  }

  /**
   * Verify email notification's title of comment an Activity is shown
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   */
  public void verifyPresentTitleCommentASEmailNoti(String title, String fullName) {
    info("Verify that email notificaiton is sent to user's inbox");
    evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName), 30000, 1);
  }

  /**
   * Verify email notification's title of Comment an Activity is not shown
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   */
  public void verifyNOTPresentTitleCommentASEmailNoti(String title, String fullName) {
    info("Verify that email notificaiton isnot sent to user's inbox");
    evt.waitForElementNotPresent(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName), 30000, 1);
  }

  /**
   * Click on Reply button
   */
  public void clickOnReplyBtnActivity() {
    info("Click on Reply button");
    evt.click(ELEMENT_GMAIL_REPLY_BTN);

  }

  /**
   * Click on View Discussion Button
   */
  public void clickOnViewDiscussBtn() {
    info("Click on View Full Discusion button");
    evt.click(ELEMENT_GMAIL_VIEW_FULL_BTN);

  }

  /**
   * Verify email notification's title of comment an Activity is shown
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   * @param content is the content of email notification
   * @param isParams String
   */
  public void verifyPresentEmailActivityNotifications(String title, String fullName, String content, String... isParams) {
    if (!content.isEmpty()) {
      info("Verify that email notificaiton is sent to user's inbox");
      evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName).replace("$content",
                                                                                                                   content),
                               30000,
                               1);
    } else {
      if (isParams.length > 0) {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE_WITH_INDEX.replace("$title", title)
                                                               .replace("$fullName", fullName)
                                                               .replace("$num", isParams[0]),
                                 30000,
                                 1);
      } else {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName), 30000, 1);
      }
    }
  }

  /**
   * Verify email notification's title of comment an Activity is shown
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   * @param content is the content of email notification
   */
  public void verifyNotPresentEmailActivityNotifications(String title, String fullName, String content, String... isParams) {
    if (!content.isEmpty()) {
      info("Verify that email notificaiton is sent to user's inbox");
      evt.waitForElementNotPresent(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName).replace("$content",
                                                                                                                       content),
                                   30000,
                                   1);
    } else {
      if (isParams.length > 0) {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForElementNotPresent(ELEMENT_GMAIL_TITLE_WITH_INDEX.replace("$title", title)
                                                                   .replace("$fullName", fullName)
                                                                   .replace("$num", isParams[0]),
                                     30000,
                                     1);
      } else {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForElementNotPresent(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName), 30000, 1);
      }
    }
  }

  /**
   * Verify email notification's title of comment an Activity is shown
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   * @param content is the title of email notification
   * @param spaceName String
   * @param isParams String
   */
  public void verifyPresentEmailPostActivityInSpaceASNotifications(String title,
                                                                   String fullName,
                                                                   String spaceName,
                                                                   String content,
                                                                   String... isParams) {
    if (!content.isEmpty()) {
      info("Verify that email notificaiton is sent to user's inbox");
      evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE.replace("$title", title)
                                                  .replace("$fullName", fullName)
                                                  .replace("$spaceName", spaceName)
                                                  .replace("$content", content),
                               30000,
                               1);
    } else {
      if (isParams.length > 0) {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE_WITH_INDEX.replace("$title", title)
                                                               .replace("$fullName", fullName)
                                                               .replace("$spaceName", spaceName)
                                                               .replace("$num", isParams[0]),
                                 30000,
                                 1);
      } else {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForAndGetElement(ELEMENT_GMAIL_TITLE.replace("$title", title)
                                                    .replace("$spaceName", spaceName)
                                                    .replace("$fullName", fullName),
                                 30000,
                                 1);
      }
    }
  }

  /**
   * Verify email notification's title of an Activity is not shown
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   * @param content is the title of email notification
   * @param isParams String
   */
  public void verifyNOTPresentTitleASEmailNoti(String title, String fullName, String content, String... isParams) {
    if (!content.isEmpty()) {
      info("Verify that email notificaiton is sent to user's inbox");
      evt.waitForElementNotPresent(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName).replace("$content",
                                                                                                                       content));
    } else {
      if (isParams.length > 0) {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForElementNotPresent(ELEMENT_GMAIL_TITLE_WITH_INDEX.replace("$title", title)
                                                                   .replace("$fullName", fullName)
                                                                   .replace("$num", isParams[0]));
      } else {
        info("Verify that email notificaiton is sent to user's inbox");
        evt.waitForElementNotPresent(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName));
      }
    }
  }

  /**
   * Open detail an email Notification
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   * @param content  String
   */
  public void goToDetailEmailNoti(String title, String fullName, String content) {
    if (!content.isEmpty()) {
      info("Go to detail detail Activity via email notification");
      evt.click(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName).replace("$content", content));
    } else {
      info("Go to detail detail Activity via email notification");
      evt.click(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$fullName", fullName));
    }

  }

  /**
   * Open detail an email Notification
   *
   * @param title is the title of email notification
   * @param fullName is full name of the user
   * @param spaceName String
   * @param content String
   */
  public void goToDetailEmailNotiOfSpacePost(String title, String fullName, String spaceName, String content) {
    if (!content.isEmpty()) {
      info("Go to detail detail Activity via email notification");
      evt.click(ELEMENT_GMAIL_TITLE.replace("$title", title)
                                   .replace("$fullName", fullName)
                                   .replace("$spaceName", spaceName)
                                   .replace("$content", content));
    } else {
      info("Go to detail detail Activity via email notification");
      evt.click(ELEMENT_GMAIL_TITLE.replace("$title", title).replace("$spaceName", spaceName).replace("$fullName", fullName));
    }

  }

  /**
   * Ch detail an email Notification
   *
   * @param userName is the title of email notification
   * @param spaceName is full name of the user
   * @param content String
   */
  public void checkDetailEmailNotiOfSpacePost(String userName, String spaceName, String content) {
    evt.waitForAndGetElement(ELEMENT_GMAIL_HEADER_POST_IN_SPACE_AS.replace("$spaceName", spaceName));
    evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_POST_IN_SPACE_AS_1.replace("$userName", userName).replace("$spaceName",
                                                                                                             spaceName));
    evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_POST_IN_SPACE_AS_2.replace("$content", content));
  }

  /**
   * Go to previous page
   */
  public void goToPreviousPage() {
    info("Back to the previous page");
    testBase.getExoWebDriver().getWebDriver().navigate().back();

  }

  /**
   * Verify email notificaiton's format for Activity
   *
   * @param emailTitle String
   * @param firstName String
   * @param fullName String
   * @param emailContent String
   * @param actTitle String
   * @param link String
   */
  public void verifyFormatEmailNotifcation(String emailTitle,
                                           String firstName,
                                           String fullName,
                                           String emailContent,
                                           String actTitle,
                                           String... link) {
    if (!emailTitle.isEmpty()) {
      info("Verify Email notificaiton's title");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_TITLE.replace("$title", emailTitle));
    }

    if (!firstName.isEmpty()) {
      info("Verify Openning email");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_OPENNING_SUB.replace("$firstName", firstName));
    }

    if (!emailContent.isEmpty() && !fullName.isEmpty()) {
      info("Verify the email's content as: activity's content");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_CONTENT.replace("$fullName", fullName).replace("$content", emailContent),
                               3000,
                               1);
    }

    if (!actTitle.isEmpty()) {
      info("Verify the activity's title");
      if (evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_ACTIVITY_TITLE_1.replace("$title", actTitle), 3000, 0) != null)
        evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_ACTIVITY_TITLE_1.replace("$title", actTitle));
      else
        evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_ACTIVITY_TITLE.replace("$title", actTitle));
    }
    if (link.length > 0 && link[0] != null && link[0] != "") {
      info("Verify the activity's link");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_ACTIVITY_LINK.replace("$link", link[0]));
    }
    info("Verify Reply button");
    evt.waitForAndGetElement(ELEMENT_GMAIL_REPLY_BTN);
    info("Verify View last Disscussion button");
    evt.waitForAndGetElement(ELEMENT_GMAIL_VIEW_FULL_BTN);
  }

  /**
   * Verify format of email notification for connection request
   *
   * @param emailTitle String
   * @param firstName String
   * @param emailContent String
   * @param userName String
   * @param isNewUser boolean
   */
  public void verifyFormatEmailNotifcation(String emailTitle,
                                           String firstName,
                                           String userName,
                                           String emailContent,
                                           Boolean... isNewUser) {
    if (!emailTitle.isEmpty()) {
      info("Verify Email notificaiton's title");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_TITLE.replace("$title", emailTitle), 3000, 1);
    }

    if (!firstName.isEmpty()) {
      info("Verify Openning email");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_OPENNING_SUB.replace("$firstName", firstName));
    }

    if (!emailContent.isEmpty() && !userName.isEmpty()) {
      info("Verify the email's content as: connection request's content");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_CONTENT_CONNECTION_REQUEST.replace("$username", userName)
                                                                              .replace("$content", emailContent),
                               3000,
                               1);
    }

    info("Verify user's avatar");
    evt.waitForAndGetElement(ELEMENT_GMAIL_USER_AVARTAR);
    if (isNewUser.length > 0 && isNewUser[0] == true) {
      info("Verify Connection now button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_CONNECT_NOW);
    } else {
      info("Verify Accept button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_ACCEPT_BTN);
      info("Verify Refuse button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_REFUSE_BTN);
    }
  }

  /**
   * Verify Bottom Content of Email notification
   *
   * @param content1 String
   * @param content2 String
   */
  public void verifyBottomContentOfEmailNotifcation(String content1, String content2) {
    info("Verify Bottom Content of Email notification");
    info("Verify Bottom content");
    evt.waitForAndGetElement(ELEMENT_GMAIL_BOTTOM_CONTENT.replace("$content1", content1).replace("$content2", content2),
                             testBase.getDefaultTimeout(),
                             0);
    info("Verify click here link");
    evt.waitForAndGetElement(ELEMENT_GMAIL_BOTTOM_CLICK_HERE_LINK, testBase.getDefaultTimeout(), 0);
  }

  /**
   * go To My Notification Setting By Clik Here in Bottom Cotnent of Email
   * Notification
   * @param fullName String
   * @param link String
   * @param linkFile String
   * @param space String
   * @param spaceCase boolean
   * @param textDes String
   * @param userName String
   *
   */
  public void checkLinkInEmailNotification(linkEmailNotification link,
                                           String userName,
                                           String fullName,
                                           String space,
                                           String textDes,
                                           String linkFile,
                                           boolean... spaceCase) {
    switch (link) {
    case Click_Here_Link:
      info("go To My Notification Setting By Clik Here in Bottom Cotnent of Email Notification");
      evt.waitForAndGetElement(ELEMENT_GMAIL_BOTTOM_CLICK_HERE_LINK, testBase.getDefaultTimeout(), 0);
      evt.click(ELEMENT_GMAIL_BOTTOM_CLICK_HERE_LINK);
      getAllChildWindows();
      evt.waitForAndGetElement(ELEMENT_NOTIFICATION_SETTINGS_TITLE, testBase.getDefaultTimeout(), 0);
      break;
    case NewUser_Link:
      info("go To User Profile Page By click on User name link");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_USER_LINK.replace("$userName", userName).replace("$fullName", fullName),
                               testBase.getDefaultTimeout(),
                               0);
      evt.click(ELEMENT_GMAIL_FORMAT_USER_LINK.replace("$userName", userName).replace("$fullName", fullName));
      getAllChildWindows();
      evt.waitForAndGetElement(ELEMENT_USER_NAME_PAGE.replace("$fullName", fullName), testBase.getDefaultTimeout(), 0);
      break;
    case Portal_Link:
      info("go To Intranet Home Page By click on portal link");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_PORTAL_LINK, testBase.getDefaultTimeout(), 0);
      evt.click(ELEMENT_GMAIL_FORMAT_PORTAL_LINK);
      getAllChildWindows();
      evt.waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_DISPLAY, testBase.getDefaultTimeout(), 0);
      break;
    case Space_Link:
      info("go To Space Home Page By click on Space link");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_SPACE_LINK.replace("$space", space), testBase.getDefaultTimeout(), 0);
      evt.click(ELEMENT_GMAIL_FORMAT_SPACE_LINK.replace("$space", space));
      getAllChildWindows();
      evt.waitForAndGetElement(ELEMENT_SPACE_ACTIVITY_TAB_ACTIVE, testBase.getDefaultTimeout(), 0);
      break;
    case Watch_This_Video:
      info("go To Activity By click watch the video");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_WATCH_VIDEO_LINK, testBase.getDefaultTimeout(), 0);
      evt.click(ELEMENT_GMAIL_FORMAT_WATCH_VIDEO_LINK);
      getAllChildWindows();
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_TITLE.replace("${text}", textDes).replace("${file}", linkFile));
      break;

    }
  }

  /**
   * Verify Recipient and Sender Email Notification
   *
   * @param recipient Boolean
   * @param userName boolean
   * @param email boolean
   */
  public void verifyRecipientAndSenderEmailNotifcation(Boolean recipient, String userName, String email) {
    info("Verify recipient or sender for email notification");
    evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_SHOW_DETAIL_BTN, testBase.getDefaultTimeout(), 0);
    evt.click(ELEMENT_GMAIL_FORMAT_SHOW_DETAIL_BTN);
    if (recipient) {
      info("Verify recipient for email notification");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_RECIPIENT.replace("$email", email).replace("$userName", userName));
    } else {
      info("Verify sender for email notification");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_SENDER.replace("$email", email).replace("$userName", userName));
    }
  }

  /**
   * Verify format email notification for space
   *
   * @param emailTitle String
   * @param firstName String
   * @param userName String
   * @param emailContent String
   * @param space String
   * @param isNewUser Boolean
   */
  public void verifyFormatEmailNotifcationForSpace(String emailTitle,
                                                   String firstName,
                                                   String userName,
                                                   String emailContent,
                                                   String space,
                                                   Boolean... isNewUser) {
    if (!emailTitle.isEmpty()) {
      info("Verify Email notificaiton's title");
      if (evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_TITLE.replace("$title", emailTitle), 3000, 0) != null) {
        evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_TITLE.replace("$title", emailTitle), 3000, 1);
      } else {
        evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_TITLE.replace("$title", emailTitle).replace("$space", space), 3000, 1);
      }
    }
    if (!firstName.isEmpty()) {
      info("Verify Openning email");
      evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_OPENNING_SUB.replace("$firstName", firstName));
    }
    if (isNewUser.length > 0 && isNewUser[0] == true) {
      info("Verify the email's content as: Join space request");
      if (!emailContent.isEmpty() && !firstName.isEmpty()) {
        evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_CONTENT_SPACE_REQUEST.replace("$username", userName)
                                                                           .replace("$content", emailContent)
                                                                           .replace("$space", space),
                                 3000,
                                 1);
      }
      info("Verify user's avatar");
      evt.waitForAndGetElement(ELEMENT_GMAIL_USER_AVARTAR);
      info("Verify validate button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_ACCEPT_BTN_SPACE_JOIN_REQUEST);
      info("Verify Refuse button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_REFUSE_JOIN_SPACE_BTN);
    }
    if (isNewUser.length > 0 && isNewUser[0] == false) {
      info("Verify the email's content as: Post in my space");
      if (!emailContent.isEmpty() && !firstName.isEmpty()) {
        evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_CONTENT_SPACE_POST_IN_MYSPACE.replace("$username", userName)
                                                                                   .replace("$content", emailContent)
                                                                                   .replace("$space", space),
                                 3000,
                                 1);
      }

      info("Verify validate button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_POST_IN_AC_REPLY_BTN);
      info("Verify Refuse button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_POST_IN_AC_VIEW_FULL_BTN);
    } else {
      info("Verify the email's content as: Space Invitation");
      if (!emailContent.isEmpty() && !space.isEmpty()) {
        evt.waitForAndGetElement(ELEMENT_GMAIL_FORMAT_CONTENT_SPACE_INVITATION.replace("$content", emailContent)
                                                                              .replace("$space", space),
                                 3000,
                                 1);
      }
      info("Verify user's avatar");
      evt.waitForAndGetElement(ELEMENT_GMAIL_SPACE_AVARTAR);
      info("Verify Accept button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_ACCEPT_SPACE_BTN);
      info("Verify Refuse button");
      evt.waitForAndGetElement(ELEMENT_GMAIL_REFUSE_SPACE_INVITATION_BTN);
    }
  }

  /**
   * Click on Accept on Space Invitation notification
   */
  public void clickAcceptBtnSpaceInvitation() {
    info("Click on Accept button");
    evt.click(ELEMENT_GMAIL_ACCEPT_SPACE_BTN);

  }

  /**
   * Click on Refuse on Space Invitation notification
   */
  public void clickRefuseBtnSpaceInvitation() {
    info("Click on Accept button");
    evt.click(ELEMENT_GMAIL_REFUSE_SPACE_INVITATION_BTN);

  }

  /**
   * Click on Accept on Space Invitation notification
   */
  public void clickAcceptBtnSpaceJoinReqest() {
    info("Click on Accept button");
    evt.click(ELEMENT_GMAIL_ACCEPT_BTN_SPACE_JOIN_REQUEST);

  }

  /**
   * Click on Refuse on Space Invitation notification
   */
  public void clickRefuseBtnSpaceJoinRequest() {
    info("Click on Accept button");
    evt.click(ELEMENT_GMAIL_REFUSE_JOIN_SPACE_BTN);

  }

  /**
   * Click on Accept button
   */
  public void clickAcceptBtnActivity() {
    info("Click on Accept button");
    evt.click(ELEMENT_GMAIL_ACCEPT_BTN);

  }

  /**
   * Click on Refuse button
   */
  public void clickRefuseBtn() {
    info("Click on Refuse button");
    evt.click(ELEMENT_GMAIL_REFUSE_BTN);

  }

  /**
   * Verify the feedback message refuse connection request
   *
   * @param message Boolean
   * @param fullName Boolean
   * @param space String
   */
  public void verifyFeedBackMessageRefuseConnection(String message, String fullName, String... space) {
    info("Verify the feedback message refuse connection request");
    if (space.length > 0) {
      evt.waitForAndGetElement(ELEMENT_NOTIFICATION_EMAIL_FEEDBACK_MESSAGE_SPACE.replace("$mess", message)
                                                                                .replace("$fullName", fullName)
                                                                                .replace("$space", space[0]));
    } else
      evt.waitForAndGetElement(ELEMENT_NOTIFICATION_EMAIL_FEEDBACK_MESSAGE_SPACE.replace("$mess", message).replace("$fullName",
                                                                                                                   fullName));
  }

  /**
   * Click on Connect Now button
   */
  public void clickConnectNowBtn() {
    info("Click on Connect Now button");
    evt.click(ELEMENT_GMAIL_CONNECT_NOW);

  }

  public enum linkEmailNotification {
    NewUser_Link, Space_Link, Portal_Link, Click_Here_Link, Watch_This_Video;
  }

}
