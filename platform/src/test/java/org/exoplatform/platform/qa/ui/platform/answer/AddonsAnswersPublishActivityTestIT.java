package org.exoplatform.platform.qa.ui.platform.answer;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_NAME_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.answer.pageobject.*;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

public class AddonsAnswersPublishActivityTestIT extends Base {

  HomePagePlatform         homePagePlatform;

  AnswerCategoryManagement answerCategoryManagement;

  QuestionManagement       questionManagement;

  AnswerManagement         answerManagement;

  CommentManagement        commentManagement;

  ActivityStream           activityStream;

  AnswerHomePage           answerHomePage;

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
    answerHomePage = new AnswerHomePage(this);
    activityStream = new ActivityStream(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * Case ID:116838. Test Case Name: Create a question. Pre-Condition: - Create a
   * new page to add new answer portlet (call it Answer Page in this test case)
   * Post-Condition: Step Number: 1 Step Name: - Create a new question Step
   * Description: - Goto answer page - Create new Category - create new question
   * Input Data: Expected Outcome: - Question is created successfully - an
   * Activity is added into activity stream - Informations that are displayed in
   * the featured content :1. Question's title2. First 4 lines of question's
   * details3. Number of replies4. Number of comments5. Average rating of the
   * question Step Number: 1 Step Name: Step Description: - Connect to Intranet -
   * Open Answers application - Go to the question and click More Actions - ->
   * Delete - Click OK to summit - Back to the Hompage Input Data: Expected
   * Outcome: - The Answers activity related to the question is removed from the
   * activity stream
   */

  @Test
  public void test01_CreateAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "line1<br>line2<br>line3<br>line4<br>line5<br>";
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();
    String answer = "answer" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    info("Test 1: Create a question");
    homePagePlatform.goToAnswer();
    info("Create category");
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);

    info("Create question");
    answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1,
                                                                AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();

    info("Create answer");
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    $(byText(question)).click();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer, null, null, null);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    $(byText(answer)).parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);

    info("Create comment");
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    clickByJavascript(ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
    $(byText(comment)).parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);

    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    $(byText(question)).parent().parent().parent().parent().find(byText(comment)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText("Answer has been submitted: " + answer))
                       .should(Condition.exist);

    info("Test 7: Delete a question");
    homePagePlatform.goToAnswer();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    questionManagement.deleteQuestion(question);

    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).shouldNot(Condition.exist);
    info("Clear data test");
    homePagePlatform.goToAnswer();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.deleteCategory(paCat1);
    // qMang.deleteQuestion(question);
  }

  /**
   * Case ID:116844. Test Case Name: Delete a question. Pre-Condition: - Create a
   * new page to add new answer portlet (call it Answer Page in this test case)
   * Post-Condition: Step Number: 1 Step Name: - Create a new question Step
   * Description: - Goto answer page - Create new Category - create new question
   * Input Data: Expected Outcome: - Question is created successfully - an
   * Activity is added into activity stream - Informations that are displayed in
   * the featured content :1. Question's title2. First 4 lines of question's
   * details3. Number of replies4. Number of comments5. Average rating of the
   * question Step Number: 1 Step Name: Step Description: - Connect to Intranet -
   * Open Answers application - Go to the question and click More Actions - ->
   * Delete - Click OK to summit - Back to the Hompage Input Data: Expected
   * Outcome: - The Answers activity related to the question is removed from the
   * activity stream
   */

  @Test
  public void test_07_DeleteAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "line1<br>line2<br>line3<br>line4<br>line5<br>";
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();
    String answer = "answer" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    info("Test 1: Create a question");
    homePagePlatform.goToAnswer();
    info("Create category");
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);

    info("Create question");
    answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1,
                                                                AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();

    info("Create answer");
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    $(byText(question)).click();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer, null, null, null);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    $(byText(answer)).parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);

    info("Create comment");
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    clickByJavascript(ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
    $(byText(comment)).parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);

    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    $(byText(question)).parent().parent().parent().parent().find(byText(comment)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText("Answer has been submitted: " + answer))
                       .should(Condition.exist);

    info("Test 7: Delete a question");
    homePagePlatform.goToAnswer();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    questionManagement.deleteQuestion(question);

    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).shouldNot(Condition.exist);
    info("Clear data test");
    homePagePlatform.goToAnswer();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.deleteCategory(paCat1);
    // qMang.deleteQuestion(question);
  }

  /**
   * Case ID:116842. Test Case Name: Submit an answer. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: - Submit an answer Step
   * Description: - Connect to Intranet - Open Answers application - goto the
   * question and click [Answer] - Fill the answer and click [save] - Back to the
   * Hompage Input Data: Expected Outcome: - The activity content is updated in
   * the activity stream: the number of answers is updated - A comment is added:
   * Answer has been submitted: $value. where $value, is first 4 lines of the
   * answer. Step Number: 1 Step Name: - Submit a comment Step Description: -
   * Connect to Intranet - Open Answers application - Goto question and click
   * [Comment] to comment - Fill the comment and click [save] - Back to the
   * Hompage Input Data: Expected Outcome: - The activity content is updated in
   * the activity stream: The number of comments is updated - Corresponding
   * activity's comment is added Step Number: 1 Step Name: - Create a question
   * Step Description: - Goto answer page and create new question - make some
   * answers and comments - Check activity stream Input Data: Expected Outcome:
   * The number of comments attached to the activity is equal to the number of
   * comments + the number of answers to the question.For example, if there are 2
   * comments and 4 answers, it will display 6 comments for the activity.
   */

  @Test
  public void test02_CheckNumberOfCommentsAndAnswers() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();

    String answer1 = "line1<br>line2<br>line3<br>line4<br>line5<br>";
    String answer2 = "answer2" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    info("Create data test");
    homePagePlatform.goToAnswer();

    info("Create question");
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();

    info("Test 5: Submit an answer");

    info("Create answer");
    if ($(ELEMENT_QUESTION_MORE_ACTION_BUTTON).is(Condition.not(Condition.exist))) {
      $(byText(question)).click();
    }
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer1, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText("Answer has been submitted: " + answer1))
                       .should(Condition.exist);

    info("Test 6: Submit a comment");
    info("Create comment");
    homePagePlatform.goToAnswer();
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    info("Test 2: Check number of comments and answers");
    info("Create answer");
    homePagePlatform.goToAnswer();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer2, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byClassName("uiIconComment"))
                       .parent()
                       .shouldHave(Condition.text("3"));

    info("Clear data test");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question);
  }

  /**
   * Case ID:116842. Test Case Name: Submit an answer. Case ID:116839. Test Case
   * Name: Check number of comments and answers. Pre-Condition: Post-Condition:
   * Step Number: 1 Step Name: - Submit an answer Step Description: - Connect to
   * Intranet - Open Answers application - goto the question and click [Answer] -
   * Fill the answer and click [save] - Back to the Hompage Input Data: Expected
   * Outcome: - The activity content is updated in the activity stream: the number
   * of answers is updated - A comment is added: Answer has been submitted:
   * $value. where $value, is first 4 lines of the answer. Step Number: 1 Step
   * Name: - Submit a comment Step Description: - Connect to Intranet - Open
   * Answers application - Goto question and click [Comment] to comment - Fill the
   * comment and click [save] - Back to the Hompage Input Data: Expected Outcome:
   * - The activity content is updated in the activity stream: The number of
   * comments is updated - Corresponding activity's comment is added Step Number:
   * 1 Step Name: - Create a question Step Description: - Goto answer page and
   * create new question - make some answers and comments - Check activity stream
   * Input Data: Expected Outcome: The number of comments attached to the activity
   * is equal to the number of comments + the number of answers to the
   * question.For example, if there are 2 comments and 4 answers, it will display
   * 6 comments for the activity.
   */

  @Test
  public void test05_CheckNumberOfCommentsAndAnswers() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();

    String answer1 = "line1<br>line2<br>line3<br>line4<br>line5<br>";
    String answer2 = "answer2" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    info("Create data test");
    homePagePlatform.goToAnswer();

    info("Create question");
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();

    info("Test 5: Submit an answer");

    info("Create answer");
    if ($(ELEMENT_QUESTION_MORE_ACTION_BUTTON).is(Condition.not(Condition.exist))) {
      $(byText(question)).click();
    }
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer1, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText("Answer has been submitted: " + answer1))
                       .should(Condition.exist);

    info("Test 6: Submit a comment");
    info("Create comment");
    homePagePlatform.goToAnswer();
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    info("Test 2: Check number of comments and answers");
    info("Create answer");
    homePagePlatform.goToAnswer();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer2, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byClassName("uiIconComment"))
                       .parent()
                       .shouldHave(Condition.text("3"));

    info("Clear data test");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question);
  }

  /**
   * Case ID:116839. Test Case Name: Check number of comments and answers.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: - Submit an answer
   * Step Description: - Connect to Intranet - Open Answers application - goto the
   * question and click [Answer] - Fill the answer and click [save] - Back to the
   * Hompage Input Data: Expected Outcome: - The activity content is updated in
   * the activity stream: the number of answers is updated - A comment is added:
   * Answer has been submitted: $value. where $value, is first 4 lines of the
   * answer. Step Number: 1 Step Name: - Submit a comment Step Description: -
   * Connect to Intranet - Open Answers application - Goto question and click
   * [Comment] to comment - Fill the comment and click [save] - Back to the
   * Hompage Input Data: Expected Outcome: - The activity content is updated in
   * the activity stream: The number of comments is updated - Corresponding
   * activity's comment is added Step Number: 1 Step Name: - Create a question
   * Step Description: - Goto answer page and create new question - make some
   * answers and comments - Check activity stream Input Data: Expected Outcome:
   * The number of comments attached to the activity is equal to the number of
   * comments + the number of answers to the question.For example, if there are 2
   * comments and 4 answers, it will display 6 comments for the activity.
   */

  @Test
  public void test06_CheckNumberOfCommentsAndAnswers() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();

    String answer1 = "line1<br>line2<br>line3<br>line4<br>line5<br>";
    String answer2 = "answer2" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    info("Create data test");
    homePagePlatform.goToAnswer();

    info("Create question");
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();

    info("Test 5: Submit an answer");

    info("Create answer");
    if ($(ELEMENT_QUESTION_MORE_ACTION_BUTTON).is(Condition.not(Condition.exist))) {
      $(byText(question)).click();
    }
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer1, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText("Answer has been submitted: " + answer1))
                       .should(Condition.exist);

    info("Test 6: Submit a comment");
    info("Create comment");
    homePagePlatform.goToAnswer();
    commentManagement.goToCommentQuestion(question);
    commentManagement.inputDataToComment(comment);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    info("Test 2: Check number of comments and answers");
    info("Create answer");
    homePagePlatform.goToAnswer();
    answerManagement.goToAnswerQuestion(question);
    answerManagement.inputDataToAnswer(answer2, null, null, null);
    // click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byClassName("uiIconComment"))
                       .parent()
                       .shouldHave(Condition.text("3"));

    info("Clear data test");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question);
  }

  /**
   * Case ID:116840. Test Case Name: Unactivate a question. Case ID:116841. Test
   * Case Name: Activate a question. Pre-Condition: the question is already exist
   * in Answers application Post-Condition: Step Number: 1 Step Name: - Unactivate
   * a question Step Description: - Connect to Intranet - Open Answers application
   * - Goto [Manage Question] - Select question to unactivate and click Edit. - in
   * question form, uncheck [Activated] checkbox and click [Save] - Go to Homepage
   * to check activity stream Input Data: Expected Outcome: - The activity content
   * isn't updated in the activity stream - A comment is added: Question has been
   * unactivated. Step Number: 1 Step Name: - Unactivate a question Step
   * Description: - Connect to Intranet - Open Answers application - Goto [Manage
   * Question] - Select question to activate and click Edit. - in question form,
   * unchecked [Activate] checkbox and click [Save] - Goto Homepage to check
   * activity stream Input Data: Expected Outcome: - The activity content isn't
   * updated in the activity stream - A comment is added: Question has been
   * activated.
   */
  @Test
  public void test03_ActiveAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create data test");
    homePagePlatform.goToAnswer();
    info("Create question");
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);
    info("Test 3: Unactivate a question");

    questionManagement.goToManageQuestionForm();
    questionManagement.activeQuestionFromManageQuestionForm(question, false);
    $(ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText(ELEMENT_QUESTION_ACTIVITY_UNACTIVATE_COMMENT))
                       .should(Condition.exist);
    info("Test 4: Activate a question");

    homePagePlatform.goToAnswer();
    questionManagement.goToManageQuestionForm();

    questionManagement.activeQuestionFromManageQuestionForm(question, true);
    $(ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText(ELEMENT_QUESTION_ACTIVITY_ACTIVATE_COMMENT))
                       .should(Condition.exist);
    info("Clear data test");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question);
  }

  /**
   * Case ID:116840. Test Case Name: Unactivate a question. Case ID:116841. Test
   * Case Name: Activate a question. Pre-Condition: the question is already exist
   * in Answers application Post-Condition: Step Number: 1 Step Name: - Unactivate
   * a question Step Description: - Connect to Intranet - Open Answers application
   * - Goto [Manage Question] - Select question to unactivate and click Edit. - in
   * question form, uncheck [Activated] checkbox and click [Save] - Go to Homepage
   * to check activity stream Input Data: Expected Outcome: - The activity content
   * isn't updated in the activity stream - A comment is added: Question has been
   * unactivated.
   */
  @Test
  public void test04_UnactivateAQuestion() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create data test");
    homePagePlatform.goToAnswer();
    info("Create question");
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);
    info("Test 3: Unactivate a question");

    questionManagement.goToManageQuestionForm();
    questionManagement.activeQuestionFromManageQuestionForm(question, false);
    $(ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText(ELEMENT_QUESTION_ACTIVITY_UNACTIVATE_COMMENT))
                       .should(Condition.exist);
    info("Clear data test");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(question);
  }

  /**
   * Case ID:116845. Test Case Name: Edit question title. Pre-Condition: the
   * question is already exist in Answers application Post-Condition: Step Number:
   * 1 Step Name: - Update question title Step Description: - Connect to Intranet
   * - Open Answers application - Go to question and click More Actions - -> Edit
   * - Update question title and click [Save] - Back to the Hompage Input Data:
   * Expected Outcome: - The activity content is updated with the new title - A
   * comment is added: Title has been updated to: $value.
   */
  @Test
  public void test08_EditQuestionTitle() {
    String question = "question" + getRandomNumber();
    String newquestion = "newquestion" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Create data test");
    homePagePlatform.goToAnswer();

    info("Create question");
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(ELEMENT_OK_BUTTON_LINK);

    info("Test 8: Edit question title");

    questionManagement.goToActionOfQuestionByRightClick(question, QuestionManagement.actionQuestionOption.EDIT);
    questionManagement.inputDataToQuestionForm(newquestion, null, null, null);
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(newquestion)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(newquestion)).parent().parent().parent().parent().find(byText(content)).should(Condition.exist);
    $(byText(newquestion)).parent()
                          .parent()
                          .parent()
                          .parent()
                          .find(byText(ELEMENT_QUESTION_ACTIVITY_UPDAT_TITLE_COMMENT.replace("$value", newquestion)))
                          .should(Condition.exist);

    info("Clear data test");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(newquestion);
  }

  /**
   * Case ID:116846. Test Case Name: Open Answers application from Answer's
   * activity. Step Number: 1 Step Name: Step Description: - Connect to Intranet -
   * From the activity stream, click on the Question title in answers activity
   * Input Data: Expected Outcome: - The answer application is opened in the
   * corresponding question(if the application is into a space, the question is
   * opened under the correct space) Step Number: 1 Step Name: Step Description: -
   * Connect to Intranet - Open Answers application - Create a new category -
   * Create a new question in this category Input Data: Expected Outcome: -
   * Question is created in new category
   */

  /*
   * Step number: 2 Step Name: Step Description: - Delete category at step 1 -
   * Back to the Hompage Input Data: Expected Outcome: -Question activity is
   * deleted
   */
  @Test
  public void test09_OpenAnswersApplicationFromAnswersActivity() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();
    info("Create data test");
    homePagePlatform.goToAnswer();
    info("Create category");
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    info("Create question");
    answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1,
                                                                AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();

    info("Test 9: Open Answers application from Answer's activity");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).click();
    ELEMENT_QUESTION_CONTAINER.find(byText(question)).should(Condition.exist);
    ELEMENT_QUESTION_CONTAINER.find(byText(content)).should(Condition.exist);

    info("Test 10 Delete a question activity by deleting its category");

    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.deleteCategory(paCat1);
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).shouldNot(Condition.exist);
  }

  /**
   * Case ID:116847. Test Case Name: Delete a question activity by deleting its
   * category. Pre-Condition: an answers activity is already shared in the
   * activity stream Post-Condition: Step Number: 1 Step Name: Step Description: -
   * Connect to Intranet - From the activity stream, click on the Question title
   * in answers activity Input Data: Expected Outcome: - The answer application is
   * opened in the corresponding question(if the application is into a space, the
   * question is opened under the correct space) Step Number: 1 Step Name: Step
   * Description: - Connect to Intranet - Open Answers application - Create a new
   * category - Create a new question in this category Input Data: Expected
   * Outcome: - Question is created in new category
   */

  /*
   * Step number: 2 Step Name: Step Description: - Delete category at step 1 -
   * Back to the Hompage Input Data: Expected Outcome: -Question activity is
   * deleted
   */
  @Test
  public void test10_CheckAnswersApplicationFromAnswersActivityAfterDelete() {
    String question = "question" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();
    info("Create data test");
    homePagePlatform.goToAnswer();
    info("Create category");
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    info("Create question");
    answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1,
                                                                AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
    questionManagement.inputDataToQuestionForm(question, content, null, null);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();

    info("Test 9: Open Answers application from Answer's activity");
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(question)).click();
    ELEMENT_QUESTION_CONTAINER.find(byText(question)).should(Condition.exist);
    ELEMENT_QUESTION_CONTAINER.find(byText(content)).should(Condition.exist);

    info("Test 10 Delete a question activity by deleting its category");

    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.deleteCategory(paCat1);
    info("Check homepage activity");
    homePagePlatform.goToHomePage();
    $(byText(question)).shouldNot(Condition.exist);
  }

}
