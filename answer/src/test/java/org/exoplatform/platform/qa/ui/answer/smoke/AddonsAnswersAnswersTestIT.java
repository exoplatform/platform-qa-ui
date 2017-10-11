package org.exoplatform.platform.qa.ui.answer.smoke;

import static com.codeborne.selenide.Selectors.byText;
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

import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.CommentManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

@Tag("smoke")
@Tag("answer")

public class AddonsAnswersAnswersTestIT extends Base {

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

  /**
   * Case ID:116817. Test Case Name: Answer a question. Case ID:116818. Test Case
   * Name: Edit an answer. Case ID:116819. Test Case Name: Delete an answer.
   * Pre-Condition: Post-Condition:
   */
  @Test
  public void test04_AddAnswerAQuestion() {
    String answer = "answer" + getRandomNumber();
    String newanswer = "newanswer AddAnswerAQuestion" ;
    String question = "question AddAnswerAQuestion" ;
    String fullName = "fullName AddAnswerAQuestion" ;
    String content = "content AddAnswerAQuestion" ;

    info("Test 4: Answer a question");
    /*
     * Step Number: 1 Step Name: Prepare data: Create a category, a question, Step
     * Description: - Login as john - Go to Answers - Create a category - Create a
     * question in this category Input Data: Expected Outcome: - Category is created
     * successfully. - Question is created successfully
     */

    /*
     * Step number: 2 Step Name: Answer a question Step Description: - Open that
     * question -Click on Answer, - Put data into editor - Set other fields - Save
     * Input Data: Expected Outcome: - Answer a question successfully, - Show the
     * answer below this question
     */
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
  }


}
