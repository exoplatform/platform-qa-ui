package org.exoplatform.platform.qa.ui.selenium.platform.answer;

import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;

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
    if (evt.waitForAndGetElement(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question), 5000, 0) != null)
      evt.click(By.xpath(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question)));
    evt.waitForAndGetElement(By.xpath(ELEMENT_QUESTION_SELECTED_ITEM.replace("$question", question)));
    evt.click(ELEMENT_COMMENT_BUTTON);
    evt.waitForAndGetElement(ELEMENT_COMMENT_FORM);
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
      evt.inputDataToCKEditor(ELEMENT_COMMENT_FORM_DATA_FRAME_INPUT, content);
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
    evt.click(ELEMENT_COMMENT_MORE_ACTION_BUTTON.replace("$comment", comment));
    switch (action) {
    case EDIT:
      info("EDIT COMMENT");
      evt.click(ELEMENT_COMMENT_EDIT_BUTTON.replace("$comment", comment));
      evt.waitForAndGetElement(ELEMENT_COMMENT_FORM);
      break;
    case PROMOTE:
      info("PROMOTE COMMENT");
      evt.click(ELEMENT_COMMENT_PROMOTE_TO_ANSWER_BUTTON.replace("$comment", comment));
      evt.waitForAndGetElement(ELEMENT_ANSWER_CONTENT.replace("$answer", comment));
      break;
    case DELETE:
      info("DELETE COMMENT");
      evt.waitForAndGetElement(ELEMENT_COMMENT_DELETE_BUTTON, testBase.getDefaultTimeout(), 1);
      evt.click(ELEMENT_COMMENT_DELETE_BUTTON.replace("$comment", comment));
      evt.waitForAndGetElement(ELEMENT_COMMENT_DELETE_CONFIRM_POPUP);
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
