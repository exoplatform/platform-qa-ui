package org.exoplatform.platform.qa.ui.platform.answer;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerCategoryManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.CommentManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * Created by ilyes on 07/09/17.
 */
@Tag("answer")
@Tag("sniff")
public class AnswerBasicTestsWithUserTestIT extends Base {
  HomePagePlatform         homePagePlatform;

  AnswerManagement         answerManagement;

  CommentManagement        commentManagement;

  QuestionManagement       questionManagement;

  AnswerCategoryManagement answerCategoryManagement;

  ManageLogInOut           manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    answerManagement = new AnswerManagement(this);
    commentManagement = new CommentManagement(this);
    questionManagement = new QuestionManagement(this);
    answerCategoryManagement = new AnswerCategoryManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * Case ID:116817. Test Case Name: Answer a question. Case ID:116818. Test Case
   * Name: Edit an answer. Case ID:116819. Test Case Name: Delete an answer.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Prepare data: Create
   * a category, a question, Step Description: - Login as john - Go to Answers -
   * Create a category - Create a question in this category Input Data: Expected
   * Outcome: - Category is created successfully. - Question is created
   * successfully Step number: 2 Step Name: Answer a question Step Description: -
   * Open that question -Click on Answer, - Put data into editor - Set other
   * fields - Save Input Data: Expected Outcome: - Answer a question successfully,
   * - Show the answer below this question
   */
  @Test
  public void test04_AddAnswerAQuestion() {
    String answer = "answer" + getRandomNumber();
    String newanswer = "newanswer" + getRandomNumber();
    String question = "question" + getRandomNumber();
    String fullName = "fullName" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 4: Answer a question");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, "", null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).click();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer, null, null, null);
    /// click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    $(byText(answer)).waitUntil(Condition.appears, Configuration.timeout);
    questionManagement.deleteQuestion(question);
  }

  /*
   * Step number: 3 Step Name: Edit an answer Step Description: - Open that
   * answer, click More Actions - -> Edit Answer - Input data into editor - Save
   * Input Data: Expected Outcome: - An "Edit answer" popup is shown. - New
   * content of answer is saved
   */
  @Test
  public void test05_EditAnswerAQuestion() {
    String newanswer = "newanswer" + getRandomNumber();
    String answer = "answer" + getRandomNumber();
    String fullName = "fullName" + getRandomNumber();
    String question = "question" + getRandomNumber();

    info("Test 5: Edit an answer");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, "", null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).click();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer, null, null, null);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).scrollTo().click();
    $(byText(answer)).waitUntil(Condition.appears, Configuration.timeout);

    answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.EDIT);
    answerManagement.inputDataToAnswer(newanswer, null, null, null);

    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).scrollTo().click();
    $(byText(answer + newanswer)).waitUntil(Condition.appears, Configuration.timeout);
    questionManagement.deleteQuestion(question);
  }

  /*
   * Step number: 3 Step Name: Delete an answer Step Description: - Open that
   * answer - Click More Actions - -> Delete Answer Input Data: Expected Outcome:
   * The answer is deleted successfully
   */
  @Test
  public void test06_DeleteAnswerAQuestion() {
    String newanswer = "newanswer" + getRandomNumber();
    String answer = "answer" + getRandomNumber();
    String fullName = "fullName" + getRandomNumber();
    String question = "question" + getRandomNumber();

    info("Test 6: Delete an answer");
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, "", null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).click();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer, null, null, null);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    $(byText(answer)).waitUntil(Condition.appears, Configuration.timeout);
    answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.DELETE);
    $(ELEMENT_ANSWER_DELETE_FORM_OK_BUTTON).click();
    questionManagement.deleteQuestion(question);

  }

  /*
   * Step number: 2 Step Name: Comment on a question Step Description: - Open that
   * question - Click on Comment link - Put values - Click on save Input Data:
   * Expected Outcome: - Comment successfully
   */
  @Test
  public void test07_AddAComment() {
    String comment = "comment" + getRandomNumber();
    String question = "question" + getRandomNumber();

    info("Test 7: Add a comment");

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

  /*
   * Step number: 3 Step Name: Edit a comment Step Description: - Open a comment -
   * Click More Actions - -> Edit Comment - Input data into fields - Save Input
   * Data: Expected Outcome: - Edit comment successfully - New content of comment
   * is saved
   */
  @Test
  public void test08_EditAComment() {
    String comment = "comment" + getRandomNumber();
    String question = "question" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();

    info("Test 8: Edit a comment");

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
    $(byText(comment + newcomment)).waitUntil(Condition.appears, Configuration.timeout);
    executeJavaScript("window.scrollBy(0,-250)");
    questionManagement.deleteQuestion(question);
  }

  /*
   * Step number: 3 Step Name: Delete a comment Step Description: - Open a comment
   * - Click More Actions - -> Delete Comment - Click OK in the confirmation
   * message Input Data: Expected Outcome: - Delete a comment successfully
   */
  @Test
  public void test09_DeleteAComment() {
    String comment = "comment" + getRandomNumber();
    String question = "question" + getRandomNumber();

    info("Test 9: Delete a comment");

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

  /**
   * Case ID:116828. Test Case Name: Add a question. Step Number: 1 Step Name: Add
   * a question Step Description: - Select 1 category and click on Submit question
   * - Put values - Save Input Data: Expected Outcome: - Question is added new and
   * shown in selected category Step number: 2 Step Name: Delete a question Step
   * Description: - Right click on a question and select Delete Input Data:
   * Expected Outcome: - Question is deleted and disappear in Answers page
   */
  @Test
  public void test02_AddAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 2: Add a question");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).should(Condition.exist);

    info("Test 4: Delete a question");

    questionManagement.deleteQuestion(question);
  }

  /**
   * . Case ID:116829. Test Case Name: Edit a question. Step Number: 1 Step Name:
   * Add a question Step Description: - Select 1 category and click on Submit
   * question - Put values - Save Input Data: Expected Outcome: - Question is
   * added new and shown in selected category Step number: 2 Step Name: Edit a
   * question Step Description: - Right click on this question, select Edit - Put
   * values into fields - Save Input Data: Expected Outcome: - Edit Question form
   * is shown - This question is updated successfully. Step number: 2 Step Name:
   * Delete a question Step Description: - Right click on a question and select
   * Delete Input Data: Expected Outcome: - Question is deleted and disappear in
   * Answers page
   */
  @Test
  public void test_03EditAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newquestion = "newquestion" + getRandomNumber();
    String newcontent = "newcontent" + getRandomNumber();

    info("Test 2: Add a question");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).should(Condition.exist);
    info("Test 3: Edit a question");

    questionManagement.goToActionOfQuestionByRightClick(question, QuestionManagement.actionQuestionOption.EDIT);
    questionManagement.inputDataToQuestionForm(newquestion, newcontent, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(byText(question)).shouldNot(Condition.exist);
    $(byText(newquestion)).should(Condition.exist);
    info("Test 4: Delete a question");

    questionManagement.deleteQuestion(newquestion);
  }

  /**
   * Case ID:116830. Test Case Name: Delete a question. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Add a question Step Description: -
   * Select 1 category and click on Submit question - Put values - Save Input
   * Data: Expected Outcome: - Question is added new and shown in selected
   * category Step number: 2 Step Name: Delete a question Step Description: -
   * Right click on a question and select Delete Input Data: Expected Outcome: -
   * Question is deleted and disappear in Answers page
   */
  @Test
  public void test04_DeleteAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 2: Add a question");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, "");
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).should(Condition.exist);

    info("Test 4: Delete a question");

    questionManagement.deleteQuestion(question);
  }

  /**
   * Case ID:116831. Test Case Name: Move a question. Pre-Condition:
   * Post-Condition:
   */
  @Test
  public void test07_MoveAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();
    ;
    String paCat1 = "paCat1" + getRandomNumber();
    ;
    String paDes1 = "paDes1" + getRandomNumber();
    ;

    String paCat2 = "paCat2" + getRandomNumber();
    ;
    String paDes2 = "paDes2" + getRandomNumber();
    ;

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, "", null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).should(Condition.exist);
    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    homePagePlatform.goToAnswer();
    $(byText(question)).click();
    questionManagement.goToActionOfQuestionFromMoreAction(QuestionManagement.actionQuestionOption.MOVE);
    $(byId("UIMoveQuestionForm")).find(byText(paCat1)).doubleClick();

    $(withText(paCat1)).click();
    $(byText(question)).should(Condition.exist);
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.DELETE);
    $(ELEMENT_CATEGORY_DELETE_OK_BUTTON).waitUntil(Condition.appears, Configuration.timeout).click();
  }

}
