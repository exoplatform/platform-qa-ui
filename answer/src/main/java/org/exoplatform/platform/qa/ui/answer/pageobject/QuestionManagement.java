package org.exoplatform.platform.qa.ui.answer.pageobject;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class QuestionManagement {
  private final TestBase       testBase;

  public Button                button;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase
   */
  public QuestionManagement(TestBase testBase) {
    this.testBase = testBase;
    this.button = new Button(testBase);
    this.evt = testBase.getElementEventTestBase();

  }

  /**
   * Go to mange question form
   */
  public void goToManageQuestionForm() {
    info("Go to mange question");

    $(ELEMENT_MANAGE_QUESTION_BUTTON).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_MANAGE_QUESTION_BUTTON).click();
    $(ELEMENT_MANAGE_QUESTION_FORM).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * goToSubmitQuestion
   */
  public void goToSubmitQuestion() {
    info("Open submin question form");

    $(ELEMENT_SUBMIT_QUESTION).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_SUBMIT_QUESTION).click();
    $(ELEMENT_SUBMIT_QUESTION_FORM).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * input data to question form
   * 
   * @param title
   * @param content
   * @param language
   * @param pathFile
   */
  public void inputDataToQuestionForm(String title, String content, String language, String pathFile) {
    if (title != null && title != "") {
      info("input title");
      $(ELEMENT_SUBMIT_QUESTION_FORM_TITLE_INPUT).setValue(title);
    }

    if (content != null && content != "") {
      info("input content");
      switchTo().frame(0);
      ELEMENT_QUESTION_ANSWER_CONTENT_INPUT.click();
      ELEMENT_QUESTION_ANSWER_CONTENT_INPUT.sendKeys(content);
      switchTo().defaultContent();
    }

    if (language != null && language != "") {
      info("input language");
      $(ELEMENT_SUBMIT_QUESTION_FORM_LANGUAGE_SELECT_BOX).selectOption(language);
    }

    if (pathFile != null && pathFile != "") {
      info("input pathFile");
      String[] links = pathFile.split("/");
      evt.click(ELEMENT_SUBMIT_QUESTION_FORM_ATTACHMENT_BUTTON);
      WebElement eFile = evt.waitForAndGetElement(ELEMENT_QUESTION_FILE_INPUT, testBase.getDefaultTimeout(), 1, 2);
      ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].style.display = 'block';",
                                                                                     eFile);
      eFile.sendKeys(testBase.getAbsoluteFilePath(pathFile));
      evt.waitForAndGetElement(ELEMENT_ATTACHMENT_FORM_FILE_NAME.replace("$fileName", links[links.length - 1]));
      evt.click(ELEMENT_ATTACH_SAVE_BUTTON);
      evt.waitForAndGetElement(ELEMENT_ATTACH_FILE_NAME.replace("$fileName", links[links.length - 1]));
      evt.switchToParentWindow();
    }

  }

  /**
   * Execute action of question: COMMENT, ANSWER, EDIT, DELETE, MOVE, SEND
   * 
   * @param question
   * @param action action that needs to be done
   */
  public void goToActionOfQuestionByRightClick(String question, actionQuestionOption action) {
    info("Select action from menu");

    $(byText(question)).contextClick();
    switch (action) {
    case COMMENT:
      info("Comment question");
      evt.click(ELEMENT_QUESTION_COMMENT.replace("$question", question));
      evt.waitForAndGetElement(ELEMENT_QUESTION_COMMENT_FORM);
      break;
    case ANSWER:
      info("Answer question");
      evt.click(ELEMENT_QUESTION_ANSWER.replace("$question", question));
      evt.waitForAndGetElement(ELEMENT_QUESTION_ANSWER_FORM);
      break;
    case EDIT:
      info("Edit question");
      String idEdit = $(byText(question)).parent().parent().parent().parent().getAttribute("id").split("Question")[1];
      $(byXpath(ELEMENT_QUESTION_EDIT.replace("{idEdit}", idEdit))).click();
      $(ELEMENT_QUESTION_EDIT_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case DELETE:
      info("Delete question");
      ELEMENT_QUESTION_DELETE.click();
      $(ELEMENT_QUESTION_DELETE_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case MOVE:
      info("Move question");
      $(ELEMENT_QUESTION_MOVE.replace("$question", question));
      evt.waitForAndGetElement(ELEMENT_QUESTION_MOVE_FORM);
      break;
    case SEND:
      info("Send question");
      evt.click(ELEMENT_QUESTION_SEND.replace("$question", question));
      evt.waitForAndGetElement(ELEMENT_QUESTION_SEND_FORM);
      break;
    default:
      info("Do nothing");
      break;
    }
  }

  /**
   * Execute action of question: PRINT, EDIT, DELETE, MOVE, SEND
   * 
   * @param action action that needs to be done
   */
  public void goToActionOfQuestionFromMoreAction(actionQuestionOption action) {
    info("Select action from menu");
    $(ELEMENT_QUESTION_MORE_ACTION_BUTTON).waitUntil(Condition.appears, Configuration.timeout).click();
    switch (action) {
    case PRINT:
      info("PRINT question");
      evt.click(ELEMENT_QUESTION_PRINT_BUTTON);
      break;
    case EDIT:
      info("EDIT question");
      evt.click(ELEMENT_QUESTION_EDIT_BUTTON);
      evt.waitForAndGetElement(ELEMENT_QUESTION_EDIT_FORM);
      break;
    case DELETE:
      info("DELETE question");
      $(ELEMENT_QUESTION_DELETE_BUTTON).click();
      break;
    case MOVE:
      info("MOVE question");
      $(ELEMENT_QUESTION_MOVE_BUTTON).click();
      $(ELEMENT_QUESTION_MOVE_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case SEND:
      info("SEND question");
      evt.click(ELEMENT_QUESTION_SEND_BUTTON);
      evt.waitForAndGetElement(ELEMENT_QUESTION_SEND_FORM);
      break;
    default:
      info("Do nothing");
      break;
    }
  }

  /**
   * Delete question
   * 
   * @param question
   */
  public void deleteQuestion(String question) {
    info("Delete question");
    if ($(ELEMENT_QUESTION_MORE_ACTION_BUTTON).is(Condition.not(Condition.exist))) {
      $(byText(question)).click();
    }
    goToActionOfQuestionFromMoreAction(actionQuestionOption.DELETE);
    $(ELEMENT_QUESTION_CONFIRM_DELETE).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON).click();
    $(byText(question)).waitUntil(Condition.disappears, Configuration.timeout);
  }

  /**
   * Cancel to Delete question
   * 
   * @param question
   */
  public void cancelDeleteQuestion(String question) {
    info("Delete question");
    goToActionOfQuestionByRightClick(question, actionQuestionOption.DELETE);
    evt.waitForAndGetElement(ELEMENT_QUESTION_CONFIRM_DELETE);
    evt.click(ELEMENT_QUESTION_DELETE_FORM_CANCEL_BUTTON);
    evt.waitForAndGetElement(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question));
  }

  /**
   * Go to edit question from manage question form
   * 
   * @param question
   */
  public void goToEditQuestionFromManageQuestionForm(String question) {
    info("Go to edit question from manage question form");
    ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION.find(byText(question))
                                                  .parent()
                                                  .find(ELEMENT_ICON_EDIT_QUESTION_IN_MANAGE_QUESTION)
                                                  .click();
  }

  /**
   * Go to delete question from manage question form
   * 
   * @param question
   */
  public void goToDeleteQuestionFromManageQuestionForm(String question) {
    info("Go to delete question from manage question form");
    ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION.find(byText(question))
                                                  .parent()
                                                  .find(ELEMENT_ICON_DELETE_QUESTION_IN_MANAGE_QUESTION)
                                                  .click();
  }

  /**
   * Approve question or not from manage question form
   * 
   * @param question
   * @param isApprove true: check to approve false: uncheck to un-approve
   */
  public void approveQuestionFromManageQuestionForm(String question, Boolean isApprove) {
    if (isApprove) {
      info("Approve question");
      // vérify if element is not checked
      if (ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION.find(byText(question))
                                                        .parent()
                                                        .findAll(ELEMENT_CHECBOX_IN_MANAGE_QUESTION)
                                                        .get(0)
                                                        .has(Condition.not(attribute("checked"))))
        ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION.find(byText(question))
                                                      .parent()
                                                      .findAll(ELEMENT_CHECBOX_IN_MANAGE_QUESTION)
                                                      .get(0)
                                                      .parent()
                                                      .click();
    } else {
      info("Dis-approve question");
      if (ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION.find(byText(question))
                                                        .parent()
                                                        .findAll(ELEMENT_CHECBOX_IN_MANAGE_QUESTION)
                                                        .get(0)
                                                        .has(Condition.attribute("checked")))
        ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION.find(byText(question))
                                                      .parent()
                                                      .findAll(ELEMENT_CHECBOX_IN_MANAGE_QUESTION)
                                                      .get(0)
                                                      .parent()
                                                      .click();

    }
  }

  /**
   * Active question or not from manage question form
   * 
   * @param question
   * @param isActive true: check to active false: uncheck to un-active
   */
  public void activeQuestionFromManageQuestionForm(String question, Boolean isActive) {
    if (isActive) {
      info("Active question");
      if (evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE, 5000, 0) != null) {
        evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        while ((evt.waitForAndGetElement(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question),
                                         5000,
                                         0,
                                         2) == null)
            && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                    .getText()
                    .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
          evt.click(ELEMENT_NEXT_PAGE);
      }
      evt.check(By.xpath(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question)), 2);
    } else {
      info("Un-active question");
      if (evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE, 5000, 0) != null) {
        evt.click(ELEMENT_ANY_PAGE.replace("$page", "1"));
        while ((evt.waitForAndGetElement(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question),
                                         5000,
                                         0,
                                         2) == null)
            && !(evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE)
                    .getText()
                    .equals(evt.waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
          evt.click(ELEMENT_NEXT_PAGE);
      }
      evt.uncheck(By.xpath(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question)), 2);
    }
  }

  /**
   * Approve question or not
   * 
   * @param isApprove true: check to approve false: uncheck to un-approve
   */
  public void approveQuestion(Boolean isApprove) {
    if (isApprove) {
      info("Approve question");
      evt.check(ELEMENT_SUBMIET_QUESTION_APPROVE_CHECKBOX, 2);
    } else {
      info("Dis-approve question");
      evt.uncheck(ELEMENT_SUBMIET_QUESTION_APPROVE_CHECKBOX, 2);
    }
  }

  /**
   * Active question or not
   * 
   * @param isActive true: check to active false: uncheck to un-active
   */
  public void activeQuestion(Boolean isActive) {
    if (isActive) {
      info("Active question");
      evt.check(ELEMENT_SUBMIET_QUESTION_ACTIVE_CHECKBOX, 2);
    } else {
      info("Un-active question");
      evt.uncheck(ELEMENT_SUBMIET_QUESTION_ACTIVE_CHECKBOX, 2);
    }
  }

  /**
   * Rate question
   * 
   * @param number number of rating
   */
  public void rateQuestion(int number) {
    info("Rate a question");
    switch (number) {
    case 1:
      ELEMENT_RATE_QUESTION_1_UNVOTED.click();
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_1_VOTED.getCssValue("color"));
      break;
    case 2:
      ELEMENT_RATE_QUESTION_2_UNVOTED.click();
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_1_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_2_VOTED.getCssValue("color"));
      break;
    case 3:
      ELEMENT_RATE_QUESTION_3_UNVOTED.click();
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_1_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_2_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_3_VOTED.getCssValue("color"));
      break;
    case 4:
      ELEMENT_RATE_QUESTION_4_UNVOTED.click();
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_1_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_2_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_3_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_4_VOTED.getCssValue("color"));
      break;
    case 5:
      ELEMENT_RATE_QUESTION_5_UNVOTED.click();
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_1_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_2_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_3_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_4_VOTED.getCssValue("color"));
      assertEquals("rgba(255, 196, 13, 1)", ELEMENT_RATE_QUESTION_5_VOTED.getCssValue("color"));
      break;
    }
  }

  /**
   * Save or cancel all change of submit question form
   * 
   * @param isSave = true if want to save all changes = false if want to cancel
   *          all changes
   */
  public void saveCancelSubmitQuestion(Boolean isSave) {
    if (isSave) {
      info("Click on Save button");
      evt.click(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
      button.ok();
    } else {
      info("Click on Cancel button");
      evt.click(ELEMENT_SUBMIT_QUESTION_FORM_CANCEL_BUTTON);
    }
    evt.waitForElementNotPresent(ELEMENT_SUBMIT_QUESTION_FORM);
    info("Submint question form is closed");
  }

  /**
   * Open Answer form
   * 
   * @param question is the question that want to answer
   */
  public void goToAnswerForm(String question) {
    if (question != "" || question != null) {
      info("Click on Answer link");
      evt.click(ELEMENT_QUESTION_ANSWER_LINK.replace("$question", question));
      info("Respone form is shown");
      evt.waitForAndGetElement(ELEMENT_QUESTION_RESPONE_FORM);
      info("Answer form is shown successfully");
    }
  }

  /**
   * Check manage question form
   * 
   * @param isDisplay
   * @param ques list of question
   */
  public void checkMngQuesForm(boolean isDisplay, String... ques) {
    if (isDisplay) {
      evt.waitForAndGetElement(ELEMENT_MANAGE_QUESTION_BUTTON);
      evt.click(ELEMENT_MANAGE_QUESTION_BUTTON, 0, true);
      for (String q : ques) {
        evt.waitForAndGetElement(ELEMENT_MANAGE_QUESTION_EDIT_QUESTION.replace("$question", q));
        evt.waitForAndGetElement(ELEMENT_MANAGE_QUESTION_DELETE_QUESTION.replace("$question", q));
      }
    } else
      evt.waitForElementNotPresent(ELEMENT_MANAGE_QUESTION_BUTTON);
  }

  /**
   * action menu of question
   */
  public enum actionQuestionOption {
    COMMENT, ANSWER, EDIT, DELETE, MOVE, SEND, PRINT
  }

}
