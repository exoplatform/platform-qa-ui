package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class MyNotificationsSetting {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param dr
   */
  public MyNotificationsSetting(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();

  }

  /**
   * Disable notification
   *
   * @param notifToDisable
   */
  public void disableNotification(myNotiType notifToDisable) {
    int repeat = 0;
    switch (notifToDisable) {
    case NewUser_email:
      info("Click on Edit button");
      evt.click(ELEMENT_EDIT_NEWUSER_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_NEWUSER_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_MAIL_ICON, 3000, 1);
      break;
    case NewUser_intranet:
      info("Click on Edit button");
      evt.click(ELEMENT_EDIT_NEWUSER_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_NEWUSER_SAVE_BTN);
      info("Verify that intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_INTRANET_ICON, 3000, 1);
      break;
    case ConnectionRequest_email:
      evt.click(ELEMENT_EDIT_RECREQ_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_RECREQ_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_RECREQ_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_RECREQ_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQ_MAIL_ICON, 3000, 1);
      break;
    case ConnectionRequest_intranet:
      evt.click(ELEMENT_EDIT_RECREQ_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_RECREQ_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_RECREQ_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_RECREQ_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQ_INTRANET_ICON, 3000, 1);
      break;
    case AS_Comment_email:
      evt.click(ELEMENT_EDIT_COMMENT_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_COMMENT_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_COMMENT_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_COMMENT_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_COMMENT_MAIL_ICON, 3000, 1);
      break;
    case AS_Comment_intranet:
      evt.click(ELEMENT_EDIT_COMMENT_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_COMMENT_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_COMMENT_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_COMMENT_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_COMMENT_INTRANET_ICON, 3000, 1);
      break;
    case AS_Like_email:
      evt.click(ELEMENT_EDIT_LIKE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_LIKE_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_LIKE_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_LIKE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_LIKE_MAIL_ICON, 3000, 1);
      break;
    case AS_Like_intranet:
      evt.click(ELEMENT_EDIT_LIKE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_LIKE_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_LIKE_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_LIKE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_LIKE_INTRANET_ICON, 3000, 1);
      break;
    case AS_Post_email:
      evt.click(ELEMENT_EDIT_POST_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_POST_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_POST_MAIL_ICON, 3000, 1);
      break;
    case AS_Post_intranet:
      evt.click(ELEMENT_EDIT_POST_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_POST_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_POST_INTRANET_ICON, 3000, 1);
      break;
    case AS_Mention_email:
      evt.click(ELEMENT_EDIT_MENTION_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_MENTION_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_MENTION_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_MENTION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_MENTION_MAIL_ICON, 3000, 1);
      break;
    case AS_Mention_intranet:
      evt.click(ELEMENT_EDIT_MENTION_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_MENTION_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_MENTION_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_MENTION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_MENTION_INTRANET_ICON, 3000, 1);
      break;
    case Space_Post_email:
      evt.click(ELEMENT_EDIT_POST_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_SPACE_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_POST_SPACE_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SPACE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_POST_SPACE_MAIL_ICON, 3000, 1);
      break;
    case Space_Post_intranet:
      evt.click(ELEMENT_EDIT_POST_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_SPACE_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_POST_SPACE_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SPACE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_POST_SPACE_INTRANET_ICON, 3000, 1);
      break;
    case Space_Join_Req_email:
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_JOIN_REQ_SPACE_MAIL_ICON, 3000, 1);
      break;
    case Space_Join_Req_intranet:
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_REQJOIN_SPACE_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_REQJOIN_SPACE_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON, 3000, 1);
      break;
    case Space_Invitation_email:
      evt.click(ELEMENT_EDIT_INVI_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_INVI_SPACE_MAIL_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_INVI_SPACE_MAIL_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_INVI_SPACE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_INVITATION_SPACE_MAIL_ICON, 3000, 1);
      break;
    case Space_Invitation_Intranet:
      evt.click(ELEMENT_EDIT_INVI_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_INVI_SPACE_WEB_CHECKBOX_CHECKED, 2000, 0, 2) != null) {
        if (repeat > 5)
          break;
        info("Click on checkbox");
        evt.click(ELEMENT_EDIT_INVI_SPACE_WEB_CHECKBOX);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_INVI_SPACE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_INVITATION_SPACE_INTRANET_ICON, 3000, 1);
      break;
    }
  }

  /**
   * Enable notification
   *
   * @param notifToEnable
   */
  public void enableNotification(myNotiType notifToEnable, Object... opParams) {

    String opt = (String) (opParams.length > 0 ? opParams[0] : "");
    int repeat = 0;
    switch (notifToEnable) {
    case NewUser_email:
      info("Click on Edit button");
      evt.click(ELEMENT_EDIT_NEWUSER_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_NEWUSER_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_NEWUSER_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_NEW_USER_MAIL_ICON, 3000, 1);
      break;
    case NewUser_intranet:
      info("Click on Edit button");
      evt.click(ELEMENT_EDIT_NEWUSER_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_NEWUSER_SAVE_BTN);
      info("Verify that intranet notification is shown");
      evt.waitForAndGetElement(ELEMENT_NEW_USER_INTRANET_ICON, 3000, 1);
      break;
    case ConnectionRequest_email:
      evt.click(ELEMENT_EDIT_RECREQ_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_RECREQ_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_RECREQ_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_RECREQ_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_RECREQ_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_MAIL_ICON, 3000, 1);
      break;
    case ConnectionRequest_intranet:
      evt.click(ELEMENT_EDIT_RECREQ_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_RECREQ_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_RECREQ_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_RECREQ_SAVE_BTN);
      info("Verify that Intranet notification is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_INTRANET_ICON, 3000, 1);
      break;
    case AS_Comment_email:
      evt.click(ELEMENT_EDIT_COMMENT_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_COMMENT_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_COMMENT_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_COMMENT_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_COMMENT_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_COMMENT_MAIL_ICON, 3000, 1);
      break;
    case AS_Comment_intranet:
      evt.click(ELEMENT_EDIT_COMMENT_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_COMMENT_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_COMMENT_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_COMMENT_SAVE_BTN);
      info("Verify that Intranet notification is shown");
      evt.waitForAndGetElement(ELEMENT_COMMENT_INTRANET_ICON, 3000, 1);
      break;
    case AS_Like_email:
      evt.click(ELEMENT_EDIT_LIKE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_LIKE_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_LIKE_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_LIKE_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_LIKE_SAVE_BTN);
      info("Verify that like notification is shown");
      evt.waitForAndGetElement(ELEMENT_LIKE_MAIL_ICON, 3000, 1);
      break;
    case AS_Like_intranet:
      evt.click(ELEMENT_EDIT_LIKE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_LIKE_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_LIKE_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      $(ELEMENT_EDIT_LIKE_SAVE_BTN).click();
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_LIKE_INTRANET_ICON, 3000, 1);
      break;
    case AS_Post_email:
      evt.click(ELEMENT_EDIT_POST_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_POST_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_POST_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_MAIL_ICON, 3000, 1);
      break;
    case AS_Post_intranet:
      evt.click(ELEMENT_EDIT_POST_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_POST_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_INTRANET_ICON, 3000, 1);
      break;
    case AS_Mention_email:
      evt.click(ELEMENT_EDIT_MENTION_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_MENTION_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_MENTION_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_MENTION_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_MENTION_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_MENTION_MAIL_ICON, 3000, 1);
      break;
    case AS_Mention_intranet:
      evt.click(ELEMENT_EDIT_MENTION_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_MENTION_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_MENTION_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_MENTION_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_MENTION_INTRANET_ICON, 3000, 1);
      break;
    case Space_Post_email:
      evt.click(ELEMENT_EDIT_POST_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_SPACE_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_POST_SPACE_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_POST_SPACE_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SPACE_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_SPACE_MAIL_ICON, 3000, 1);
      break;
    case Space_Post_intranet:
      evt.click(ELEMENT_EDIT_POST_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_POST_SPACE_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_POST_SPACE_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_POST_SPACE_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_SPACE_INTRANET_ICON, 3000, 1);
      break;
    case Space_Join_Req_email:
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_REQJOIN_SPACE_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_REQJOIN_SPACE_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_MAIL_ICON, 3000, 1);
      break;
    case Space_Join_Req_intranet:
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_REQJOIN_SPACE_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_REQJOIN_SPACE_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_REQJOIN_SPACE_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON, 3000, 1);
      break;
    case Space_Invitation_email:
      evt.click(ELEMENT_EDIT_INVI_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_INVI_SPACE_MAIL_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_INVI_SPACE_MAIL_CHECKBOX, 2);
        repeat++;
      }
      if (!opt.isEmpty())
        evt.select(ELEMENT_EDIT_INVI_SPACE_LIST, opt);
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_INVI_SPACE_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_MAIL_ICON, 3000, 1);
      break;
    case Space_Invitation_Intranet:
      evt.click(ELEMENT_EDIT_INVI_SPACE_ICON);
      while (evt.waitForAndGetElement(ELEMENT_EDIT_INVI_SPACE_WEB_CHECKBOX_CHECKED, 2000, 0) == null) {
        if (repeat > 5)
          break;
        info("Check on the checkbox");
        evt.check(ELEMENT_EDIT_INVI_SPACE_WEB_CHECKBOX, 2);
        repeat++;
      }
      info("Click on Save button");
      evt.click(ELEMENT_EDIT_INVI_SPACE_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_INTRANET_ICON, 3000, 1);
      break;
    }
  }

  /**
   * turn on/off email notification true for on, false for off
   */
  public void turnOnOffNotiEmail(boolean on) {
    if (on) {
      if (evt.isElementPresent(ELEMENT_SWITCH_ONOFF_MAIL_ON))
        info("Email notification is already ON");
      else
        evt.click(ELEMENT_SWITCH_ONOFF_MAIL_OFF, 2);
    } else {
      if (evt.isElementPresent(ELEMENT_SWITCH_ONOFF_MAIL_ON))
        evt.click(ELEMENT_SWITCH_ONOFF_MAIL_ON, 2);
      else
        info("Email notification is already OFF");
    }
  }

  /**
   * turn on/off intranet notification true for on, false for off
   */
  public void turnOnOffNotiIntranet(boolean on) {
    if (on == true) {
      if (evt.isElementPresent(ELEMENT_SWITCH_ONOFF_WEB_ON))
        info("Intranet notification is already ON");
      else {
        evt.click(ELEMENT_SWITCH_ONOFF_WEB_OFF, 2);
      }
    } else {
      if (evt.isElementPresent(ELEMENT_SWITCH_ONOFF_WEB_ON)) {
        evt.click(ELEMENT_SWITCH_ONOFF_WEB_ON, 2);
      } else
        info("Intranet notification is already OFF");

    }
  }

  /**
   * confirm Reset all notification setting
   */
  public void confirmResetNotificationSetting() {
    info("Click on Reset button");
    evt.click(ELEMENT_RESET_BTN);
    info("Click on Confirm button");
    evt.click(ELEMENT_RESET_CONFIRM);

  }

  /**
   * Cancel reseting all notification settng
   */
  public void cancelResetNotiSetting() {
    info("click on reset button");
    evt.click(ELEMENT_RESET_BTN);
    info("click on cancel button");
    evt.click(ELEMENT_RESET_CANCEL);

  }

  /**
   * Check the title of Notification settings page
   */
  public void verifyTilePage() {
    info("Verify the title of Notification settings page");
    evt.waitForAndGetElement(ELEMENT_TITLE_NOTIFICATION_SETTING_PAGE);
    info("The page is shown");
  }

  /**
   * Verify that a notification belongs to a category
   *
   * @param category is the category's name
   * @param type is notification's type
   */
  public void verifyNotiBelongToCategory(String category, notiMode type) {
    switch (type) {
    case NewUser:
      info("Verify that New user notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "NewUserPlugin"));
      info("The notification is shown with correct category");
      break;
    case ConnectionRequest:
      info("Verify that Connection Request notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category)
                                                          .replace("$notification", "RelationshipReceivedRequestPlugin"));
      info("The notification is shown with correct category");
      break;
    case Space_Join:
      info("Verify that Space Join Request notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "RequestJoinSpacePlugin"));
      info("The notification is shown with correct category");
      break;
    case Space_Invitation:
      info("Verify that Space invitation notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "SpaceInvitationPlugin"));
      info("The notification is shown with correct category");
      break;
    case Space_Post:
      info("Verify that Post on My Spaces notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category)
                                                          .replace("$notification", "PostActivitySpaceStreamPlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Post:
      info("Verify that Post on My Stream notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "PostActivityPlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Like:
      info("Verify that Like notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification", "LikePlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Mention:
      info("Verify that Mention notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "ActivityMentionPlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Comment:
      info("Verify that Comment notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "ActivityCommentPlugin"));
      info("The notification is shown with correct category");
      break;
    }

  }

  /**
   * Verify that a notification is enabled
   *
   * @param type as NewUser_email, NewUser_intranet,...
   */
  public void verifyNotificationTypeEnable(NotificationsAdminSeting.notificationType type) {
    switch (type) {
    case NewUser_email:
      info("Verify that email for new user notification is shown");
      evt.waitForAndGetElement(ELEMENT_NEW_USER_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case NewUser_intranet:
      info("Verify that email for new user notification is shown");
      evt.waitForAndGetElement(ELEMENT_NEW_USER_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Comment_email:
      info("Verify that email for comment notification is shown");
      evt.waitForAndGetElement(ELEMENT_COMMENT_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Comment_intranet:
      info("Verify that intranet for comment notification is shown");
      evt.waitForAndGetElement(ELEMENT_COMMENT_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Like_email:
      info("Verify that email for like notification is shown");
      evt.waitForAndGetElement(ELEMENT_LIKE_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Like_intranet:
      info("Verify that intranet for like notification is shown");
      evt.waitForAndGetElement(ELEMENT_LIKE_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Mention_email:
      info("Verify that email for mention notification is shown");
      evt.waitForAndGetElement(ELEMENT_MENTION_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Mention_intranet:
      info("Verify that intranet for mention notification is shown");
      evt.waitForAndGetElement(ELEMENT_MENTION_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Post_email:
      info("Verify that email for post notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case AS_Post_intranet:
      info("Verify that email for post notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case ConnectionRequest_email:
      info("Verify that email for connection request notification is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case ConnectionRequest_intranet:
      info("Verify that intranet for connection request notification is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case Space_Invitation_email:
      info("Verify that email for space invitation notification is shown");
      evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case Space_Invitation_intranet:
      info("Verify that intranet for space invitation notification is shown");
      evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case Space_Join_email:
      info("Verify that email for space joint request notification is shown");
      evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case Space_Join_intranet:
      info("Verify that intranet for space joint request notification is shown");
      evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    case Space_Post_email:
      info("Verify that email for space post notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_SPACE_MAIL_ICON);
      info("The notification is shown successfully");
      break;
    case Space_Post_intranet:
      info("Verify that intranet for space post notification is shown");
      evt.waitForAndGetElement(ELEMENT_POST_SPACE_INTRANET_ICON);
      info("The notification is shown successfully");
      break;
    }
  }

  /**
   * Verify that notification's type is disabled all
   *
   * @param type
   */
  public void verifyNotificationTypeDisable(NotificationsAdminSeting.notificationType type) {
    switch (type) {
    case NewUser_email:
      info("Verify that New user's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_MAIL_ICON);
      info("Verify that daily email is shown");
      evt.waitForAndGetElement(ELEMENT_NEW_USER_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                               selectBoxMailType.Daily.name().toString()));
      break;
    case NewUser_intranet:
      info("Verify that New user's notification's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_INTRANET_ICON);
      break;
    case ConnectionRequest_email:
      info("Verify that Connection request's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQ_MAIL_ICON);
      break;
    case ConnectionRequest_intranet:
      info("Verify that Connection request's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQ_INTRANET_ICON);
      break;
    case AS_Comment_email:
      info("Verify that Activity Comment's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_COMMENT_MAIL_ICON);
      break;
    case AS_Comment_intranet:
      info("Verify that Activity like's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_COMMENT_INTRANET_ICON);
      break;
    case AS_Mention_email:
      info("Verify that Activity mention's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_MENTION_MAIL_ICON);
    case AS_Mention_intranet:
      info("Verify that Activity mention's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_MENTION_INTRANET_ICON);
      break;
    case AS_Like_email:
      info("Verify that Activity like's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_LIKE_MAIL_ICON);
    case AS_Like_intranet:
      info("Verify that Activity like's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_LIKE_INTRANET_ICON);
      break;
    case AS_Post_email:
      info("Verify that Activity post's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_POST_MAIL_ICON);
      break;
    case AS_Post_intranet:
      info("Verify that Activity post's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_POST_INTRANET_ICON);
      break;
    case Space_Invitation_email:
      info("Verify that Space invitation's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_INVITATION_SPACE_MAIL_ICON);
      break;
    case Space_Invitation_intranet:
      info("Verify that Space invitation's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_INVITATION_SPACE_INTRANET_ICON);
      break;
    case Space_Join_email:
      info("Verify that Space join's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_JOIN_REQ_SPACE_MAIL_ICON);
      break;
    case Space_Join_intranet:
      info("Verify that Space join's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON);
      break;
    case Space_Post_email:
      info("Verify that Space post's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_POST_SPACE_MAIL_ICON);
      break;
    case Space_Post_intranet:
      info("Verify that Space post's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_POST_SPACE_INTRANET_ICON);
      break;
    }
  }

  /**
   * Verify that notification by default
   *
   * @param type
   */
  public void verifyNotificationDefault(NotificationsAdminSeting.notificationType type) {
    switch (type) {
    case NewUser_email:
      info("Verify that New user's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_MAIL_ICON);
      info("Verify that daily email is shown");
      evt.waitForAndGetElement(ELEMENT_NEW_USER_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                               selectBoxMailType.Daily.name().toString()));
      break;
    case NewUser_intranet:
      info("Verify that New user's notification's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_INTRANET_ICON);
      break;
    case ConnectionRequest_email:
      info("Verify that Connection request's email is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_MAIL_ICON);
      info("Verify that daily email is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                     selectBoxMailType.Daily.name().toString()));
      break;
    case ConnectionRequest_intranet:
      info("Verify that Connection request's email is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_INTRANET_ICON);
      break;
    case AS_Comment_email:
      info("Verify that Activity Comment's email is shown");
      evt.waitForAndGetElement(ELEMENT_COMMENT_MAIL_ICON);
      info("Verify that daily email is shown");
      evt.waitForAndGetElement(ELEMENT_COMMENT_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                              selectBoxMailType.Daily.name().toString()));
      break;
    case AS_Comment_intranet:
      info("Verify that Activity like's intranet is shown");
      evt.waitForAndGetElement(ELEMENT_COMMENT_INTRANET_ICON);
      break;
    case AS_Mention_email:
      info("Verify that Activity mention's email is shown");
      evt.waitForAndGetElement(ELEMENT_MENTION_MAIL_ICON);
      info("Verify that any selected box email is disabled");
      evt.waitForElementNotPresent(ELEMENT_MENTION_SELECTED_BOX_MAIL_ICON_ANY);
    case AS_Mention_intranet:
      info("Verify that Activity mention's intranet is disabled");
      evt.waitForAndGetElement(ELEMENT_MENTION_INTRANET_ICON);
      break;
    case AS_Like_email:
      info("Verify that Activity like's email is disabled");
      evt.waitForElementNotPresent(ELEMENT_LIKE_MAIL_ICON);
      info("Verify that weekly email is shown");
      evt.waitForAndGetElement(ELEMENT_LIKE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                           selectBoxMailType.Weekly.name().toString()));
    case AS_Like_intranet:
      info("Verify that Activity like's intranet is disabled");
      evt.waitForElementNotPresent(ELEMENT_LIKE_INTRANET_ICON);
      break;
    case AS_Post_email:
      info("Verify that Activity post's email is shown");
      evt.waitForAndGetElement(ELEMENT_POST_MAIL_ICON);
      info("Verify that any selected box email is disabled");
      evt.waitForElementNotPresent(ELEMENT_POST_SELECTED_BOX_MAIL_ICON_ANY);
      break;
    case AS_Post_intranet:
      info("Verify that Activity post's intranet is shown");
      evt.waitForAndGetElement(ELEMENT_POST_INTRANET_ICON);
      break;
    case Space_Invitation_email:
      info("Verify that Space invitation's email is shown");
      evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_MAIL_ICON);
      info("Verify that weekly email is shown");
      evt.waitForAndGetElement(ELEMENT_INVI_SPACE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                 selectBoxMailType.Weekly.name().toString()));
      break;
    case Space_Invitation_intranet:
      info("Verify that Space invitation's intranet is shown");
      evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_INTRANET_ICON);
      break;
    case Space_Join_email:
      info("Verify that Space join's email is shown");
      evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_MAIL_ICON);
      info("Verify that weekly email is shown");
      evt.waitForAndGetElement(ELEMENT_JOIN_SPACE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                 selectBoxMailType.Weekly.name().toString()));
      break;
    case Space_Join_intranet:
      info("Verify that Space join's intranet is shown");
      evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON);
      break;
    case Space_Post_email:
      info("Verify that Space post's email is disabled");
      evt.waitForAndGetElement(ELEMENT_POST_SPACE_MAIL_ICON);
      info("Verify that any selected box email is disabled");
      evt.waitForElementNotPresent(ELEMENT_POST_SPACE_SELECTED_BOX_MAIL_ICON_ANY);
      break;
    case Space_Post_intranet:
      info("Verify that Space post's intranet is disabled");
      evt.waitForAndGetElement(ELEMENT_POST_SPACE_INTRANET_ICON);
      break;
    }
  }

  /**
   * Verify the label of notification's types
   *
   * @param label as: Someone comments on one of my activities,...
   */
  public void verifyLabelNotificationType(String label) {
    info("Verify that the label of Comment notification is correct");
    evt.waitForAndGetElement(ELEMENT_NOTIFICATION_LABEL_NAME.replace("$label", label));
    info("the label is correct");

  }

  /**
   * Verify Email notification toggle
   */
  public void checkEmailNotifiToggle() {
    info("Verify that the table is followng switch on-off buttons");
    evt.waitForAndGetElement(ELEMENT_TABLE_FOLLOWING_SWITCH_ONOFF);
    info("Verify that the switch on-off label is shown correct");
    evt.waitForAndGetElement(ELEMENT_SWITCH_ONOFF_MAIL_LABEL);
    info("Verify that switch on-off is enabled by default");
    evt.waitElementAndTryGetElement(ELEMENT_SWITCH_ONOFF_MAIL_ON);
  }

  /**
   * Verify Intranet notification toggle
   */
  public void checkIntranetNotifiToggle() {
    info("Verify that the table is followng switch on-off buttons");
    evt.waitForAndGetElement(ELEMENT_TABLE_FOLLOWING_SWITCH_ONOFF);
    info("Verify that the switch on-off label is shown correct");
    evt.waitForAndGetElement(ELEMENT_SWITCH_ONOFF_WEB_LABEL);
    info("Verify that switch on-off is enabled by default");
    evt.waitElementAndTryGetElement(ELEMENT_SWITCH_ONOFF_WEB_ON);
  }

  /**
   * Verify all email notification is disabled when turn-off Email Notification
   * toggle
   */
  public void veriftyAllEmailNotiDefaultDisable() {
    info("Verify that daily email is disabled");
    evt.waitForElementNotPresent(ELEMENT_NEW_USER_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                 selectBoxMailType.Daily.name().toString()));
    info("Verify that Connection request's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQ_MAIL_ICON);
    evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQ_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                       selectBoxMailType.Daily.name()
                                                                                                              .toString()));
    info("Verify that Activity Comment's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_COMMENT_MAIL_ICON);
    evt.waitForElementNotPresent(ELEMENT_COMMENT_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                selectBoxMailType.Daily.name().toString()));
    info("Verify that Activity mention's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_MENTION_MAIL_ICON);
    info("Verify that Activity like's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_LIKE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                             selectBoxMailType.Weekly.name().toString()));
    info("Verify that Activity post's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_POST_MAIL_ICON);
    info("Verify that Space invitation's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_INVITATION_SPACE_MAIL_ICON);
    evt.waitForElementNotPresent(ELEMENT_INVI_SPACE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                   selectBoxMailType.Weekly.name().toString()));
    info("Verify that Space join request's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_JOIN_REQ_SPACE_MAIL_ICON);
    evt.waitForElementNotPresent(ELEMENT_JOIN_SPACE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                   selectBoxMailType.Weekly.name().toString()));
    info("Verify that Space post's email is disabled");
    evt.waitForElementNotPresent(ELEMENT_POST_SPACE_MAIL_ICON);
  }

  /**
   * Verify all email notification is enabled when turn-off Email Notification
   * toggle
   */
  public void veriftyAllEmailNotiDefaultEnabled() {
    info("Verify that daily email is disabled");
    evt.waitForAndGetElement(ELEMENT_NEW_USER_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                             selectBoxMailType.Daily.name().toString()));
    info("Verify that Connection request's email is disabled");
    evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_MAIL_ICON);
    evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                                   selectBoxMailType.Daily.name().toString()));
    info("Verify that Activity Comment's email is disabled");
    evt.waitForAndGetElement(ELEMENT_COMMENT_MAIL_ICON);
    evt.waitForAndGetElement(ELEMENT_COMMENT_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                            selectBoxMailType.Daily.name().toString()));
    info("Verify that Activity mention's email is disabled");
    evt.waitForAndGetElement(ELEMENT_MENTION_MAIL_ICON);
    info("Verify that Activity like's email is disabled");
    evt.waitForAndGetElement(ELEMENT_LIKE_SELECTED_BOX_MAIL_ICON.replace("$option", selectBoxMailType.Weekly.name().toString()));
    info("Verify that Activity post's email is disabled");
    evt.waitForAndGetElement(ELEMENT_POST_MAIL_ICON);
    info("Verify that Space invitation's email is disabled");
    evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_MAIL_ICON);
    evt.waitForAndGetElement(ELEMENT_INVI_SPACE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                               selectBoxMailType.Weekly.name().toString()));
    info("Verify that Space join request's email is disabled");
    evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_MAIL_ICON);
    evt.waitForAndGetElement(ELEMENT_JOIN_SPACE_SELECTED_BOX_MAIL_ICON.replace("$option",
                                                                               selectBoxMailType.Weekly.name().toString()));
    info("Verify that Space post's email is disabled");
    evt.waitForAndGetElement(ELEMENT_POST_SPACE_MAIL_ICON);
  }

  /**
   * Verify all intranet notifications is disabled when turn-off intranet
   * Notification toggle
   */
  public void veriftyAllIntranetNotiDefaultDisable() {
    info("Verify that New user's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_NEW_USER_INTRANET_ICON);
    info("Verify that Connection request's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQ_INTRANET_ICON);
    info("Verify that Activity Comment's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_COMMENT_INTRANET_ICON);
    info("Verify that Activity mention's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_MENTION_INTRANET_ICON);
    info("Verify that Activity like's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_LIKE_INTRANET_ICON);
    info("Verify that Activity post's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_POST_INTRANET_ICON);
    info("Verify that Space invitation's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_INVITATION_SPACE_MAIL_ICON);
    info("Verify that Space join's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON);
    info("Verify that Space post's intranet is disabled");
    evt.waitForElementNotPresent(ELEMENT_POST_SPACE_INTRANET_ICON);
  }

  /**
   * Verify all intranet notifications is enabled when turn-off intranet
   * Notification toggle
   */
  public void veriftyAllIntranetNotiDefaultEnabled() {
    info("Verify that Connection request's intranet is enabled");
    evt.waitForAndGetElement(ELEMENT_CONNECTION_REQ_INTRANET_ICON);
    info("Verify that Activity Comment's intranet is enabled");
    evt.waitForAndGetElement(ELEMENT_COMMENT_INTRANET_ICON);
    info("Verify that Activity mention's intranet is enabled");
    evt.waitForAndGetElement(ELEMENT_MENTION_INTRANET_ICON);
    info("Verify that Activity post's intranet is enabled");
    evt.waitForAndGetElement(ELEMENT_POST_INTRANET_ICON);
    info("Verify that Space invitation's intranet is enabled");
    evt.waitForAndGetElement(ELEMENT_INVITATION_SPACE_INTRANET_ICON);
    info("Verify that Space join's intranet is enabled");
    evt.waitForAndGetElement(ELEMENT_JOIN_REQ_SPACE_INTRANET_ICON);
    info("Verify that Space post's intranet is enabled");
    evt.waitForAndGetElement(ELEMENT_POST_SPACE_INTRANET_ICON);
  }

  public enum myNotiType {
    NewUser_email, NewUser_intranet, ConnectionRequest_email, ConnectionRequest_intranet, AS_Comment_email, AS_Comment_intranet, AS_Like_email, AS_Like_intranet, AS_Post_email, AS_Post_intranet, AS_Mention_email, AS_Mention_intranet, Space_Post_email, Space_Post_intranet, Space_Join_Req_email, Space_Join_Req_intranet, Space_Invitation_email, Space_Invitation_Intranet;
  }

  /**
   * Define notification plugin is disable all
   */
  public enum notiMode {
    NewUser, ConnectionRequest, AS_Comment, AS_Like, AS_Mention, AS_Post, Space_Invitation, Space_Join, Space_Post;
  }

  /**
   * Define email's type in selected box as Never, Daily or Weekly
   */
  public enum selectBoxMailType {
    Never, Daily, Weekly;
  }

}
