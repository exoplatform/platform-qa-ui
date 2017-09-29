package org.exoplatform.platform.qa.ui.answer.pageobject;

import static com.codeborne.selenide.Selectors.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class CommentManagement {
  private final TestBase       testBase;

  public AnswerHomePage        aHome;

  public AnswerManagement      aMang;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param dr
   */
  public CommentManagement(TestBase testBase) {
    this.testBase = testBase;
    this.aHome = new AnswerHomePage(testBase);
    this.aMang = new AnswerManagement(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to COMMENT a question
   */
  public void goToCommentQuestion(String question) {
    info("Go to COMMENT a question");
    $(byText(question)).parent().parent().find(ELEMENT_COMMENT_BUTTON).click();
    $(ELEMENT_COMMENT_FORM).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Input data to COMMENT form
   * 
   * @param content
   * @param isApprove
   * @param isActive
   * @param related
   */
  public void inputDataToComment(String content) {
    info("Input data to COMMENT form");
    if (content != null && content != "") {
      info("input content");
      switchTo().frame(0);
      ELEMENT_QUESTION_ANSWER_CONTENT_INPUT.waitUntil(Condition.visible, Configuration.timeout).click();
      ELEMENT_QUESTION_ANSWER_CONTENT_INPUT.sendKeys(content);
      switchTo().defaultContent();
    }
  }

  /**
   * Execute action of COMMENT: EDIT, APPROVE, DISAPPROVE, ACTIVE, DEACTIVE,
   * DELETE
   * 
   * @param COMMENT
   * @param action action that needs to be done
   */
  public void goToActionOfCommentFromMoreAction(String comment, actionCommentOption action) {
    info("Select action from menu");
    ELEMENT_COMMENT_MORE_ACTION.click();

    switch (action) {
    case EDIT:
      info("EDIT COMMENT");
      ELEMENT_COMMENT_EDIT.parent().click();
      $(ELEMENT_COMMENT_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case PROMOTE:
      info("PROMOTE COMMENT");
      evt.click(ELEMENT_COMMENT_PROMOTE_TO_ANSWER_BUTTON.replace("$comment", comment));
      evt.waitForAndGetElement(ELEMENT_ANSWER_CONTENT.replace("$answer", comment));
      break;
    case DELETE:
      info("DELETE COMMENT");
      $(byText(comment)).parent().parent().parent().parent().parent().find(ELEMENT_COMMENT_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_COMMENT_DELETE_CONFIRM_POPUP).waitUntil(Condition.appears, Configuration.timeout);
      break;
    default:
      info("Do nothing");
      break;
    }
  }

  /**
   * Delete comment
   * 
   * @param comment
   */
  public void deleteComment(String comment) {
    info("Delete COMMENT");
    goToActionOfCommentFromMoreAction(comment, actionCommentOption.DELETE);
    evt.waitForAndGetElement(ELEMENT_COMMENT_CONFIRM_DELETE);
    evt.click(ELEMENT_COMMENT_DELETE_FORM_OK_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_COMMENT_CONTENT.replace("$comment", comment));
  }

  /**
   * action menu of comment
   */
  public enum actionCommentOption {
    EDIT, PROMOTE, DELETE
  }
}
