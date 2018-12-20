package org.exoplatform.platform.qa.ui.platform.answer;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
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

import org.exoplatform.platform.qa.ui.answer.pageobject.*;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
@Tag("sniff")
@Tag("answer")
public class AddonsAnswersSearchTestIT extends Base {
  HomePagePlatform         homePagePlatform;

  QuestionManagement       questionManagement;

  AnswerCategoryManagement answerCategoryManagement;

  AnswerManagement         answerManagement;

  AnswerHomePage           answerHomePage;

  CommentManagement        commentManagement;

  ManageLogInOut           manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    answerHomePage = new AnswerHomePage(this);
    homePagePlatform = new HomePagePlatform(this);
    answerManagement = new AnswerManagement(this);
    commentManagement = new CommentManagement(this);
    questionManagement = new QuestionManagement(this);
    answerCategoryManagement = new AnswerCategoryManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * Case ID:116806. Test Case Name: Quick search. Pre-Condition: Post-Condition:
   * Step Number: 1 Step Name: Quick Search Step Description: - Enter a search
   * keyword - Click quick search icon or press Enter Input Data: Expected
   * Outcome: Return search result with items matched key word search
   */
  @Test
  public void test01_QuickSearch() {
    info("Test 1: Quick search");
    String paCat1 = "paCat1" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String question2 = "question2" + getRandomNumber();
    String answer1 = "answer1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();

    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paCat1, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question1, content1, null, "");
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question1)).should(Condition.exist);

    if ($(byText(question1)).parent().find(ELEMENT_ICON_MORE_ACTIONS_QUESTION).is(Condition.not(Condition.exist))) {
      $(byText(question1)).click();
    }
    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer1, null, null, null);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    $(byText(answer1)).waitUntil(Condition.appears, Configuration.timeout);

    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question2, question2, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question2)).should(Condition.exist);

    if ($(byText(question2)).parent().find(ELEMENT_ICON_MORE_ACTIONS_QUESTION).is(Condition.not(Condition.exist))) {
      $(byText(question2)).click();
    }
    commentManagement.goToCommentQuestion(question2);
    commentManagement.inputDataToComment(comment2);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    $(byText(comment2)).waitUntil(Condition.appears, Configuration.timeout);

    executeJavaScript("window.scrollBy(0,-1000)");
    info("Search category");
    answerHomePage.doQuickSearch(paCat1);
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(paCat1)).should(Condition.exist);
    $(byXpath(ELEMENT_QUICK_SEARCH_CLOSE)).click();

    info("Search question");
    answerHomePage.doQuickSearch(question2);
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question2)).should(Condition.exist);
    $(byXpath(ELEMENT_QUICK_SEARCH_CLOSE)).click();
    info("Search answer");
    answerHomePage.doQuickSearch(answer1);
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question1)).should(Condition.exist);
    $(byXpath(ELEMENT_QUICK_SEARCH_CLOSE)).click();

    info("Search comment");
    answerHomePage.doQuickSearch(comment2);
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question2)).should(Condition.exist);
    $(byXpath(ELEMENT_QUICK_SEARCH_CLOSE)).click();

    info("Search content");
    answerHomePage.doQuickSearch(content1);
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question1)).should(Condition.exist);
    $(byXpath(ELEMENT_QUICK_SEARCH_CLOSE)).click();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.deleteCategory(paCat1);
    questionManagement.deleteQuestion(question1);
    questionManagement.deleteQuestion(question2);

  }

  /**
   * Case ID:116807. Test Case Name: Advanced search. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Advanced Search Step Description: -
   * Open Advanced Search form by clicking on icon "Advanced search" - Set value
   * to fields - Enter text into boxes - Hit "Search" button Input Data: Expected
   * Outcome: Return search result with items matched key word and conditions
   * search
   */
  @Test
  public void test02_AdvancedSearch() {
    info("Test 2: Advanced search");
    String paCat1 = "paCat1" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String question2 = "question2" + getRandomNumber();
    String answer1 = "answer1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();

    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paCat1, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question1, content1, null, "");
    // click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question1)).should(Condition.exist);
    refresh();
    if ($(byText(question1)).parent().find(ELEMENT_ICON_MORE_ACTIONS_QUESTION).is(Condition.not(Condition.exist))) {
      $(byText(question1)).click();
    }
    answerManagement.goToAnswerQuestion(question1);
    answerManagement.inputDataToAnswer(answer1, null, null, null);
    $(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
    $(byText(answer1)).waitUntil(Condition.appears, Configuration.timeout);
    refresh();
    questionManagement.goToSubmitQuestion();
    questionManagement.inputDataToQuestionForm(question2, question2, null, "");
    $(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
    $(ELEMENT_OK_BUTTON_LINK).click();
    $(byText(question2)).should(Condition.exist);
    refresh();
    if ($(byText(question2)).parent().find(ELEMENT_ICON_MORE_ACTIONS_QUESTION).is(Condition.not(Condition.exist))) {
      $(byText(question2)).click();
    }
    commentManagement.goToCommentQuestion(question2);
    commentManagement.inputDataToComment(comment2);
    $(ELEMENT_COMMENT_FORM_SAVE_BUTTON).click();
    $(byText(comment2)).waitUntil(Condition.appears, Configuration.timeout);

    executeJavaScript("window.scrollBy(0,-1000)");

    answerHomePage.goToAdvanceSearch();
    info("Search category");
    $(ELEMENT_ADVANCE_SEARCH_KEY_INPUT).setValue(paCat1);
    $(byXpath(ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON)).click();
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(paCat1)).should(Condition.exist);

    info("Search question");
    $(ELEMENT_ADVANCE_SEARCH_KEY_INPUT).setValue(question2);
    $(byXpath(ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON)).click();
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question2)).should(Condition.exist);

    info("Search answer");
    $(ELEMENT_ADVANCE_SEARCH_KEY_INPUT).setValue(answer1);
    $(byXpath(ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON)).click();
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question1)).should(Condition.exist);

    info("Search comment");
    $(ELEMENT_ADVANCE_SEARCH_KEY_INPUT).setValue(comment2);
    $(byXpath(ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON)).click();
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question2)).should(Condition.exist);

    info("Search content");
    $(ELEMENT_ADVANCE_SEARCH_KEY_INPUT).setValue(content1);
    $(byXpath(ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON)).click();
    ELEMENT_TABLE_RESULT_SEARCH.find(byText(question1)).should(Condition.exist);
    $(byXpath(ELEMENT_ADVANCE_SEARCH_ADVANCE_CLOSE_BUTTON)).click();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.deleteCategory(paCat1);
    questionManagement.deleteQuestion(question1);
    questionManagement.deleteQuestion(question2);
  }

}
