package org.exoplatform.platform.qa.ui.platform.answer;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerCategoryManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerHomePage;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
@Tag("sniff")
@Tag("answer")
public class AddonsAnswersQuestionTestIT extends Base {
  HomePagePlatform         homePagePlatform;

  ManageLogInOut           manageLogInOut;

  QuestionManagement       questionManagement;

  AnswerCategoryManagement answerCategoryManagement;

  AnswerHomePage           answerHomePage;

  ManageAlert              manageAlert;

  Button                   button;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    questionManagement = new QuestionManagement(this);
    answerCategoryManagement = new AnswerCategoryManagement(this);
    answerHomePage = new AnswerHomePage(this);
    manageAlert = new ManageAlert(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * Case ID:116812. Test Case Name: Vote question. Pre-Condition: Post-Condition:
   * Step Number: 1 Step Name: - Step Description: Step 1: Vote Question Input
   * Data: - Open a question - Vote Question by select number of star Expected
   * Outcome: - Question is voted successful.The number of stars are selected set
   * yellow.
   */
  @Test
  public void test01_VoteQuestion() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create question");
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(title, content, null, null);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(button.ELEMENT_OK_BUTTON_LINK).click();
    $(byText(title)).should(Condition.exist);
    if ($(ELEMENT_QUESTION_MORE_ACTION_BUTTON).is(Condition.not(Condition.exist))) {
      $(byText(title)).click();
    }
    info("Test 1: Vote question");
    questionManagement.rateQuestion(4);
    info("clear data");
    questionManagement.goToActionOfQuestionFromMoreAction(QuestionManagement.actionQuestionOption.DELETE);
    $(ELEMENT_QUESTION_CONFIRM_DELETE).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON).click();
  }

  /**
   * Case ID:116833. Test Case Name: Edit a question in Manage Answer form. Case
   * ID:116834. Test Case Name: Delete a question in Manage Question form.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Add a question Step
   * Description: - Select 1 category and click on Submit question - Put values -
   * Save Input Data: Expected Outcome: - Question is added new and shown in
   * selected category Step number: 2 Step Name: -Answer a question Step
   * Description: - Click Manage Question in the main menu - Select All Question
   * tab - Click Edit on question want to edit - Put values - Save Input Data:
   * Expected Outcome: - This question is updated successfully Step number: 2 Step
   * Name: Delete a question Step Description: - Select All Question tab - Click
   * Delete on question want to delete Input Data: Expected Outcome: Question is
   * deleted successfully
   */
  @Test
  public void test05_EditAQuestionInManageAnswerForm() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newtitle = "newtitle" + getRandomNumber();
    String newcontent = "newcontent" + getRandomNumber();

    info("Add a question");
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(title, content, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(button.ELEMENT_OK_BUTTON_LINK).click();
    $(byText(title)).should(Condition.exist);

    info("Test 5: Edit a question in Manage Answer form");

    questionManagement.goToManageQuestionForm();
    questionManagement.goToEditQuestionFromManageQuestionForm(title);
    questionManagement.inputDataToQuestionForm(newtitle, newcontent, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(byText(title)).shouldNot(Condition.exist);
    $(byText(newtitle)).should(Condition.exist);
    info("Test 6: Delete a question in Manage Question form");
    questionManagement.goToDeleteQuestionFromManageQuestionForm(newtitle);
    $(ELEMENT_QUESTION_CONFIRM_DELETE).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON).click();
    $(byId("QuestionManagerment-tab")).find(byText(newtitle)).shouldNot(Condition.exist);
  }

  /**
   * Case ID:116834. Test Case Name: Delete a question in Manage Question form.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Add a question Step
   * Description: - Select 1 category and click on Submit question - Put values -
   * Save Input Data: Expected Outcome: - Question is added new and shown in
   * selected category Step number: 2 Step Name: Delete a question Step
   * Description: - Select All Question tab - Click Delete on question want to
   * delete Input Data: Expected Outcome: Question is deleted successfully
   */
  @Test
  public void test06_DeleteAQuestionInManageAnswerForm() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Add a question");
    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(title, content, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(button.ELEMENT_OK_BUTTON_LINK).click();
    $(byText(title)).should(Condition.exist);

    info("Test 5: Edit a question in Manage Answer form");

    questionManagement.goToManageQuestionForm();

    info("Test 6: Delete a question in Manage Question form");

    questionManagement.goToDeleteQuestionFromManageQuestionForm(title);
    $(ELEMENT_QUESTION_CONFIRM_DELETE).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON).click();
    ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION.find(byText(title)).shouldNot(Condition.exist);
  }

  /**
   * Case ID:116836. Test Case Name: Approve / Disapprove a question.
   * Pre-Condition: Post-Condition: Step Number: 1 Step Name: Add a question Step
   * Description: - Select 1 category and click on Submit question - Put values -
   * Save Input Data: Expected Outcome: - Question is added new and shown in
   * selected category Step number: 3 Step Name: Approve a question Step
   * Description: - Click Approve icon on disapproved question Input Data:
   * Expected Outcome: Questions is approved successfully
   */
  @Test
  public void test09_ApproveDisapproveAQuestion() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Test 9 Approve / Disapprove a question");

    homePagePlatform.goToAnswer();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(title, content, null, null);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    click(button.ELEMENT_OK_BUTTON_LINK);

    questionManagement.goToManageQuestionForm();
    questionManagement.approveQuestionFromManageQuestionForm(title, false);
    $(ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToAnswer();
    $(byText(title)).shouldNot(Condition.exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    questionManagement.goToManageQuestionForm();
    questionManagement.approveQuestionFromManageQuestionForm(title, true);
    $(ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToAnswer();
    $(byText(title)).should(Condition.exist);

    info("Clear data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToAnswer();
    questionManagement.deleteQuestion(title);
  }

}
