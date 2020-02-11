package org.exoplatform.platform.qa.ui.answer.smoke;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerCategoryManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerHomePage;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

@Tag("smoke")
@Tag("answer")
public class AddonsAnswersQuestionTestIT extends Base {

  HomePagePlatform         homePagePlatform;

  QuestionManagement       questionManagement;

  AnswerCategoryManagement answerCategoryManagement;

  AnswerHomePage           answerHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    questionManagement = new QuestionManagement(this);
    answerCategoryManagement = new AnswerCategoryManagement(this);
    answerHomePage = new AnswerHomePage(this);
  }

  /**
   * Case ID:116828. Test Case Name: Add a question.
   */
  @Test
  public void test02_AddAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 2: Add a question");
    /*
     * Step Number: 1 Step Name: Add a question Step Description: - Select 1
     * category and click on Submit question - Put values - Save Input Data:
     * Expected Outcome: - Question is added new and shown in selected category
     */
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).should(Condition.exist);

    info("Test 4: Delete a question");
    /*
     * Step number: 2 Step Name: Delete a question Step Description: - Right click
     * on a question and select Delete Input Data: Expected Outcome: - Question is
     * deleted and disappear in Answers page
     */
    questionManagement.deleteQuestion(question);
  }

  /**
   * . Case ID:116829. Test Case Name: Edit a question.
   */
  @Test
  public void test_03EditAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newquestion = "newquestion" + getRandomNumber();
    String newcontent = "newcontent" + getRandomNumber();

    info("Test 2: Add a question");
    /*
     * Step Number: 1 Step Name: Add a question Step Description: - Select 1
     * category and click on Submit question - Put values - Save Input Data:
     * Expected Outcome: - Question is added new and shown in selected category
     */
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).should(Condition.exist);
    info("Test 3: Edit a question");
    /*
     * Step number: 2 Step Name: Edit a question Step Description: - Right click on
     * this question, select Edit - Put values into fields - Save Input Data:
     * Expected Outcome: - Edit Question form is shown - This question is updated
     * successfully.
     */
    questionManagement.goToActionOfQuestionByRightClick(question, QuestionManagement.actionQuestionOption.EDIT);
    questionManagement.inputDataToQuestionForm(newquestion, newcontent, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(byText(question)).shouldNot(Condition.exist);
    $(byText(newquestion)).should(Condition.exist);
    info("Test 4: Delete a question");
    /*
     * Step number: 2 Step Name: Delete a question Step Description: - Right click
     * on a question and select Delete Input Data: Expected Outcome: - Question is
     * deleted and disappear in Answers page
     */
    questionManagement.deleteQuestion(newquestion);
  }

  /**
   * Case ID:116830. Test Case Name: Delete a question. Pre-Condition:
   * Post-Condition:
   */
  @Test
  public void test04_DeleteAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 2: Add a question");
    /*
     * Step Number: 1 Step Name: Add a question Step Description: - Select 1
     * category and click on Submit question - Put values - Save Input Data:
     * Expected Outcome: - Question is added new and shown in selected category
     */
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question)).should(Condition.exist);

    info("Test 4: Delete a question");
    /*
     * Step number: 2 Step Name: Delete a question Step Description: - Right click
     * on a question and select Delete Input Data: Expected Outcome: - Question is
     * deleted and disappear in Answers page
     */
    questionManagement.deleteQuestion(question);
  }

  /**
   * Case ID:116831. Test Case Name: Move a question. Pre-Condition:
   * Post-Condition:
   */
  @Test
  public void test07_MoveAQuestion() {
    String question = "question" + getRandomNumber();
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();
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
