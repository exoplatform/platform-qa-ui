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
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
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
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
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

}
