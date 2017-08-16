package org.exoplatform.platform.qa.ui.answer.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.CommentManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

/**
 * Created by acherif on 28/06/2017.
 */

@Tag("smoke")
@Tag("answer")
public class AddonsAnswersCommentTestIT extends Base {

  HomePagePlatform   homePagePlatform;

  AnswerManagement   answerManagement;

  CommentManagement  commentManagement;

  QuestionManagement questionManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    answerManagement = new AnswerManagement(this);
    commentManagement = new CommentManagement(this);
    questionManagement = new QuestionManagement(this);
  }

  @Test
  public void test07_AddAComment() {
    String comment = "comment" + getRandomNumber();
    String question = "question" + getRandomNumber();

    info("Test 7: Add a comment");

    /*
     * Step number: 2 Step Name: Comment on a question Step Description: - Open that
     * question - Click on Comment link - Put values - Click on save Input Data:
     * Expected Outcome: - Comment successfully
     */

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, "", null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).click();
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    $(byText(comment)).waitUntil(Condition.appears, Configuration.timeout);
    questionManagement.deleteQuestion(question);

  }

  @Test
  public void test08_EditAComment() {
    String comment = "comment" + getRandomNumber();
    String question = "question" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();

    info("Test 8: Edit a comment");
    /*
     * Step number: 3 Step Name: Edit a comment Step Description: - Open a comment -
     * Click More Actions - -> Edit Comment - Input data into fields - Save Input
     * Data: Expected Outcome: - Edit comment successfully - New content of comment
     * is saved
     */

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, "", null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).click();
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    $(byText(comment)).waitUntil(Condition.appears, Configuration.timeout);
    commentManagement.goToActionOfCommentFromMoreAction(comment, CommentManagement.actionCommentOption.EDIT);
    commentManagement.inputDataToComment(newcomment);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    $(byText(comment+newcomment)).waitUntil(Condition.appears, Configuration.timeout);
    executeJavaScript("window.scrollBy(0,-250)");
    questionManagement.deleteQuestion(question);
  }

  @Test
  public void test09_DeleteAComment() {
    String comment = "comment" + getRandomNumber();
    String question = "question" + getRandomNumber();

    info("Test 9: Delete a comment");
    /*
     * Step number: 3 Step Name: Delete a comment Step Description: - Open a comment
     * - Click More Actions - -> Delete Comment - Click OK in the confirmation
     * message Input Data: Expected Outcome: - Delete a comment successfully
     */
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, "", null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).click();
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    $(byText(comment)).waitUntil(Condition.appears, Configuration.timeout);
    commentManagement.goToActionOfCommentFromMoreAction(comment, CommentManagement.actionCommentOption.DELETE);
    $(ELEMENT_COMMENT_DELETE_FORM_OK_BUTTON).click();
    questionManagement.deleteQuestion(question);

  }

}
