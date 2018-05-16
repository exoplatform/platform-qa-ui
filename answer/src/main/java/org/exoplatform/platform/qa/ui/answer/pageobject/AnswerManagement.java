package org.exoplatform.platform.qa.ui.answer.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class AnswerManagement {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase testBase
   */
  public AnswerManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to answer a question
   */
  public void goToAnswerQuestion(String question) {
    info("Go to answer a question");
    refresh();
    if ($(byText(question)).parent().parent().find(ELEMENT_QUESTION_MORE_ACTION_BUTTON).is(Condition.not(Condition.visible))) {
      $(byText(question)).click();
    }
    $(ELEMENT_ANSWER_BUTTON).click();
    $(ELEMENT_ANSWER_FORM).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Input data to answer form
   *
   * @param content string
   * @param isApprove string
   * @param isActive string
   * @param related string
   */
  public void inputDataToAnswer(String content, Boolean isApprove, Boolean isActive, String related) {
    info("Input data to answer form");
    if (content != null && content != "") {
      info("input content");
      switchTo().frame(0);
      ELEMENT_QUESTION_ANSWER_CONTENT_INPUT.click();
      ELEMENT_QUESTION_ANSWER_CONTENT_INPUT.sendKeys(content);
      switchTo().defaultContent();
    }
    if (isApprove != null) {
      info("approve or not");
      if (isApprove)
        $(ELEMENT_ANSWER_APPROVE_CHECKBOX).click();
      else
        $(ELEMENT_ANSWER_APPROVE_CHECKBOX).click();
    }
    if (isActive != null) {
      info("active or not");
      if (isActive)
        $(ELEMENT_ANSWER_ACTIVATE_CHECKBOX).click();
      else
        $(ELEMENT_ANSWER_ACTIVATE_CHECKBOX).click();
    }
    if (related != null && related != "") {
      info("input related questions");
    }
  }

  /**
   * Execute action of answer: EDIT, APPROVE, DISAPPROVE, ACTIVE, DEACTIVE, DELETE
   *
   * @param answer string
   * @param action action that needs to be done
   */
  public void goToActionOfAnswerFromMoreAction(String answer, actionAnswerOption action) {
    info("Select action from menu");
    ELEMENT_ANSWER_MORE_ACTION.waitUntil(Condition.visible,Configuration.timeout).click();
    switch (action) {
    case EDIT:
      info("EDIT answer");
      ELEMENT_ANSWER_EDIT.parent().click();
      $(ELEMENT_ANSWER_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case APPROVE:
      info("APPROVE answer");
      evt.click(ELEMENT_ANSWER_APPROVE_BUTTON.replace("$answer", answer));
      evt.click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
      evt.waitForAndGetElement(ELEMENT_ANSWER_DISAPPROVE_BUTTON);
      break;
    case DISAPPROVE:
      info("DISAPPROVE answer");
      evt.click(ELEMENT_ANSWER_DISAPPROVE_BUTTON.replace("$answer", answer));
      evt.click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
      evt.waitForAndGetElement(ELEMENT_ANSWER_APPROVE_BUTTON);
      break;
    case ACTIVE:
      info("ACTIVE answer");
      evt.click(ELEMENT_ANSWER_ACTIVE_BUTTON.replace("$answer", answer));
      evt.click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
      evt.waitForAndGetElement(ELEMENT_ANSWER_DEACTIVE_BUTTON);
      break;
    case DEACTIVE:
      info("DEACTIVE answer");
      evt.click(ELEMENT_ANSWER_DEACTIVE_BUTTON.replace("$answer", answer));
      evt.click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
      evt.waitForAndGetElement(ELEMENT_ANSWER_ACTIVE_BUTTON);
      break;
    case DELETE:
      info("DELETE answer");
      $(byText(answer)).parent().parent().parent().parent().parent().find(ELEMENT_ANSWER_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();

      $(ELEMENT_ANSWER_DELETE_CONFIRM_POPUP).waitUntil(Condition.appears, Configuration.timeout);
      break;
    default:
      info("Do nothing");
      break;

    }
  }

  /**
   * Delete answer
   *
   * @param answer string
   */
  public void deleteAnswer(String answer) {
    info("Delete answer");
    goToActionOfAnswerFromMoreAction(answer, actionAnswerOption.DELETE);
    evt.waitForAndGetElement(ELEMENT_ANSWER_CONFIRM_DELETE);
    evt.click(ELEMENT_ANSWER_DELETE_FORM_OK_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_ANSWER_CONTENT.replace("$answer", answer));
  }

  /**
   * function vote/unvote an answer
   *
   * @param answer string
   * @param rate boolean
   */
  public void rateAnswer(String answer, boolean rate) {
    if (rate) {
      info("Vote answer");
      $(byText(answer)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ANSWER).click();
    } else {
      info("Unvote answer");
      $(byText(answer)).parent().parent().parent().find(ELEMENT_ICON_UNLIKE_ANSWER).click();
    }
  }

  /**
   * Save or Cancel all changes of Answer form
   *
   * @param isSave = true if want to save all changes = false if want to cancel
   *          all changes
   */
  public void saveCancelAnswerForm(Boolean isSave) {
    if (isSave) {
      info("Click on Save button");
      evt.click(ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    } else {
      info("Click on Cancel button");
      evt.click(ELEMENT_ANSWER_FORM_CANCEL_BUTTON);
    }
    evt.waitForElementNotPresent(ELEMENT_ANSWER_FORM);
    info("Answer form is closed");
  }

  /**
   * Verify the answer that is added
   */
  public void verifyAnswerAdded(String answer) {
    info("Verify that the answer is added successfully");
    evt.waitForAndGetElement(ELEMENT_ANSWER_CONTENT.replace("$answer", answer));
    info("The answer is added successfully");
  }

  /**
   * action menu of answer
   */
  public enum actionAnswerOption {
    EDIT, APPROVE, DISAPPROVE, ACTIVE, DEACTIVE, DELETE
  }
}
