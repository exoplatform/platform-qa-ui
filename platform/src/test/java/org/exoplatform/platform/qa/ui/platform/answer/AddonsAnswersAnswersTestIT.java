package org.exoplatform.platform.qa.ui.platform.answer;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
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
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.CommentManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("answer")
@Tag("sniff")
public class AddonsAnswersAnswersTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  AnswerManagement   answerManagement;

  ManageLogInOut     manageLogInOut;

  QuestionManagement questionManagement;

  CommentManagement  commentManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    commentManagement = new CommentManagement(this);
    answerManagement = new AnswerManagement(this);
    questionManagement = new QuestionManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  /**
   * Case ID:116814. Test Case Name: Rate/Sort answers. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: - Step Description: Step 1: Rate
   * and Sort Answer Input Data: - Open a question having answers - Rate answers
   * by click on hand up or down icon - Sort answers by click sort icon Expected
   * Outcome: - Answer is rated with index - Answer is sorted by
   * ascending/descending
   */
  @Test
  public void test01_RateSortAnswers() {
    String answer1 = "answer1" + getRandomNumber();
    String answer2 = "answer2" + getRandomNumber();
    String answer3 = "answer3" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String fullName = "John Smith";
    info("Create question");
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question1, content1, null, null);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);
    $(byText(question1)).click();
    info("Create answer");
    homePagePlatform.goToAnswer();
    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer1, null, null, null);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    $(byText(answer1)).parent().parent().find(byText(fullName)).should(Condition.exist);
    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer2, null, null, null);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    $(byText(answer2)).parent().parent().find(byText(fullName)).should(Condition.exist);
    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer3, null, null, null);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    $(byText(answer3)).parent().parent().find(byText(fullName)).should(Condition.exist);
    info("Test 1: Rate/Sort answers");
    info("Rate answer");
    answerManagement.rateAnswer(answer3, true);
    answerManagement.rateAnswer(answer2, false);

    info("Sort by rate");
    click(ELEMENT_SORT_BY_RATE);
    ELEMENT_CONTENT_FIRST_ANSWER_IN_QUESTION.shouldHave(Condition.text(answer3));
    ELEMENT_CONTENT_SECOND_ANSWER_IN_QUESTION.shouldHave(Condition.text(answer1));
    ELEMENT_CONTENT_THIRD_ANSWER_IN_QUESTION.shouldHave(Condition.text(answer2));

    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question1);
  }

  /**
   * Case ID:116815. Test Case Name: Activate/Deactivate answer. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Prepare data: Create a question,
   * answer Step Description: - Login as admin - Go to Answers - Create a question
   * - Answer this question Input Data: Expected Outcome: - Question is created
   * successfully - Answer successfully Step number: 2 Step Name: Deactivate
   * answer Step Description: -Click More Actions - -> Deactivate Input Data:
   * Expected Outcome: Answer will be disappeared with normal users Step number: 3
   * Step Name: Activate answer Step Description: - Click More Actions - ->
   * Activate Input Data: Expected Outcome: Answer will be appeared with normal
   * users
   */
  @Test
  public void test02_ActivateDeactivateAnswer() {
    String answer = "answer" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String fullName = "John Smith";
    info("Test 2: Activate/Deactivate answer");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question1, content1, null, null);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);
    $(byText(question1)).click();
    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    $(byText(answer)).parent().parent().find(byText(fullName)).should(Condition.exist);

    answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.EDIT);
    answerManagement.inputDataToAnswer(null, null, false, null);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToAnswer();
    $(byText(question1)).click();
    $(byText(answer)).parent().parent().find(byText(fullName)).shouldNot(Condition.exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    if ($(byText(question1)).parent()
                            .parent()
                            .parent()
                            .find(ELEMENT_QUESTION_MORE_ACTION_BUTTON)
                            .is(Condition.not(Condition.exist))) {
      $(byText(question1)).click();
    }
    answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.EDIT);
    answerManagement.inputDataToAnswer(null, null, true, null);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToAnswer();
    if ($(byText(question1)).parent()
                            .parent()
                            .parent()
                            .find(ELEMENT_QUESTION_MORE_ACTION_BUTTON)
                            .is(Condition.not(Condition.exist))) {
      $(byText(question1)).click();
    }
    $(byText(answer)).parent().parent().find(byText(fullName)).shouldNot(Condition.exist);

    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question1);
  }

  /**
   * Case ID:116816. Test Case Name: Approve/ Disapprove answer. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Prepare data: Create a question,
   * answer Step Description: - Login as john - Go to Answers - Create a category,
   * with moderate Answer - Create a question in this category - Login as mary -
   * Answer this question Input Data: Expected Outcome: - Category is created
   * successfully. - Question is created successfully - Answer successfully, but
   * this answer is at Disapprove status and invisible with normal users Step
   * number: 2 Step Name: Approve answer Step Description: - Login as john - Click
   * on icon Approve corresponding to an answer - Login as mary Input Data:
   * Expected Outcome: - Answer is at Approved status, and visible with normal
   * users - See this answer Step number: 3 Step Name: Disapprove answer Step
   * Description: - Login as john - Click on icon Disapprove corresponding to an
   * answer - Login as mary Input Data: Expected Outcome: Answer will be invisible
   * with normal users - Cannot see this answer
   */
  @Test
  public void test03_ApproveDisapproveAnswer() {
    String answer = "answer" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String fullName = "John Smith";
    info("Test 3: Approve/ Disapprove answer");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question1, content1, null, null);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);
    $(byText(question1)).click();
    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer, false, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToAnswer();
    $(byText(question1)).click();
    $(byText(answer)).parent().parent().find(byText(fullName)).shouldNot(Condition.exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    click(By.xpath(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
    answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.EDIT);
    answerManagement.inputDataToAnswer(null, true, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToAnswer();
    $(byText(question1)).click();
    $(byText(answer)).parent().parent().find(byText(fullName)).should(Condition.exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    if ($(byText(question1)).parent()
                            .parent()
                            .parent()
                            .find(ELEMENT_QUESTION_MORE_ACTION_BUTTON)
                            .is(Condition.not(Condition.exist))) {
      $(byText(question1)).click();
    }
    answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.EDIT);
    answerManagement.inputDataToAnswer(null, false, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToAnswer();
    $(byText(question1)).click();
    $(byText(answer)).parent().parent().find(byText(fullName)).shouldNot(Condition.exist);
    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question1);
  }

  /**
   * Case ID:116823. Test Case Name: Promote a comment. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Prepare data: Create a question,
   * answer Step Description: - Go to Answers - Create a question - Answer this
   * question Input Data: Expected Outcome: - Question is created successfully -
   * Answer successfully Step number: 2 Step Name: Comment on a question Step
   * Description: - Open that question - Click on Comment link - Put values -
   * Click on save Input Data: Expected Outcome: - Comment successfully Step
   * number: 3 Step Name: Promote a comment Step Description: - Open a comment -
   * Click More Actions - -> Promote as Answer Input Data: Expected Outcome: This
   * comment becomes an answer
   */
  @Test
  public void test10_PromoteAComment() {
    String answer = "answer" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String fullName = "John Smith";
    info("Test 10 Promote a comment");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question1, content1, null, null);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);
    $(byText(question1)).click();

    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    $(byText(answer)).parent().parent().find(byText(fullName)).should(Condition.exist);

    commentManagement.goToCommentQuestion(question1);
    commentManagement.inputDataToComment(comment);
    // click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
    $(byText(comment)).parent().parent().find(byText(fullName)).should(Condition.exist);

    commentManagement.goToActionOfCommentFromMoreAction(comment, CommentManagement.actionCommentOption.PROMOTE);

    info("Clear data");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question1);
  }

  /**
   * Case ID:116832. Test Case Name: Answer a question in Manage Question form.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Add a question Step
   * Description: - Select 1 category and click on Submit question - Put values -
   * Save Input Data: Expected Outcome: - Question is added new and shown in
   * selected category Step number: 2 Step Name: -Answer a question Step
   * Description: - Click Manage Question in the main menu -Select Open Question
   * tab - Click Answer on question want to answer - Put values - Save Input Data:
   * Expected Outcome: - Answer is added to selected question - Question is not
   * show at tab "Open Question"
   */
  @Test
  public void test11_AnswerAQuestionInManageQuestionForm() {
    String answer = "answer" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String fullName = "John Smith";
    info("Test 11 Answer a question in Manage Question form");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question1, content1, null, null);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);
    $(byText(question1)).click();
    questionManagement.goToManageQuestionForm();
    click(ELEMENT_MANAGE_QUESTION_FORM_OPEN_QUESTION_TAB);
    ELEMENT_TAB_OPEN_QUESTION_IN_MANAGE_QUESTION.find(byText(question1))
                                                .parent()
                                                .find(ELEMENT_ICON_ANSWER_QUESTION_IN_MANAGE_QUESTION)
                                                .click();
    answerManagement.inputDataToAnswer(answer, true, true, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
    waitForElementNotPresent(ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    waitForAndGetElement(ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
    $(byText(question1)).click();
    if ($(byText(question1)).parent()
                            .parent()
                            .parent()
                            .find(ELEMENT_QUESTION_MORE_ACTION_BUTTON)
                            .is(Condition.not(Condition.exist))) {
      $(byText(question1)).click();
    }
    $(byText(answer)).parent().parent().find(byText(fullName)).should(Condition.exist);
    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question1);
  }
}
