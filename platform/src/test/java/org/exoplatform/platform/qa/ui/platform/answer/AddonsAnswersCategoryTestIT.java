package org.exoplatform.platform.qa.ui.platform.answer;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerCategoryManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerHomePage;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
@Tag("sniff")
@Tag("answer")
public class AddonsAnswersCategoryTestIT extends Base {
  HomePagePlatform         homePagePlatform;

  ManageLogInOut           manageLogInOut;

  AnswerCategoryManagement answerCategoryManagement;

  QuestionManagement       questionManagement;

  AnswerHomePage           answerHomePage;

  ManageAlert              manageAlert;

  Button                   button;

  AnswerManagement         answerManagement;

  UserProfilePage          userProfilePage;

  NavigationToolbar        navigationToolbar;

  @BeforeEach
  public void setUpBeforeTest() throws Exception {

    navigationToolbar = new NavigationToolbar(this);
    userProfilePage = new UserProfilePage(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    answerCategoryManagement = new AnswerCategoryManagement(this);
    button = new Button(this);
    answerHomePage = new AnswerHomePage(this);
    questionManagement = new QuestionManagement(this);
    answerManagement = new AnswerManagement(this);
    manageAlert = new ManageAlert(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }



  /**
   * Case ID:116809. Test Case Name: Drag and Drop category. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: - Step Description: Step 1: Create
   * categories after drag and drop them Input Data: - Create some categories -
   * Drag and drop a category into another category Expected Outcome: Category is
   * dragged and dropped successfully to selected category
   */
  @Test
  @BugInPLF("ANS-122")
  public void test01_DragAndDropCategory() {
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();
    String paCat2 = "paCat2" + getRandomNumber();
    String paDes2 = "paDes2" + getRandomNumber();

    info("Create parent categories");
    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).should(Condition.exist);

    info("Test 1: Drag and Drop category");

    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).dragAndDropTo(ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)));
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);

    info("Clear data");
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).click();
    answerCategoryManagement.deleteCategory(paCat2);
  }

  /**
   * Case ID:116810. Test Case Name: Export/Import category. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Create categories, questions Step
   * Description: - Create some categories - Create some questions in categories
   * Input Data: Expected Outcome: Category and its questions are
   * exported/imported successfully Step number: 2 Step Name: Export categories
   * Step Description: - Right click on 1 category and select Export - Put file's
   * name and Export Input Data: Expected Outcome: Category and its questions are
   * exported successfully Step number: 3 Step Name: Import categories Step
   * Description: - Remove the above category - Right click on another category
   * and select Import - Choose the file which is exported above Input Data:
   * Expected Outcome: - The category is removed successfully. - That category and
   * its questions are imported successfully
   */
  @Test
  public void test02_ExportImportCategory() {
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();
    String question1 = "question1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String paCat2 = "paCat2" + getRandomNumber();
    String paDes2 = "paDes2" + getRandomNumber();
    String fileName = getRandomNumber();
    info("Test 2: Export/Import category");

    info("Create category 1");
    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);

    info("Create question 1");
    answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1,
                                                                AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
    questionManagement.inputDataToQuestionForm(question1, content1, null, null);
    clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
    $(button.ELEMENT_OK_BUTTON_LINK).click();

    info("Create category 2");
    answerHomePage.goToHomeCategory();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).should(Condition.exist);

    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.exportAnswerCategory(fileName);
    answerCategoryManagement.deleteCategory(paCat1);

    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).click();
    answerCategoryManagement.importAnswerCategory();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" paCat1504411")).click();
    $(byText("question1125877")).should(Condition.exist);
    info("Clear data");
    answerHomePage.goToHomeCategory();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).click();
    answerCategoryManagement.deleteCategory(paCat2);
  }

  /**
   * . Test Case Name: Delete a category. Pre-Condition: Post-Condition: Step
   * Number: 1 Step Name: Add a category Step Description: - Go to Answers page -
   * Select Category, then Add Category - Put values - Save Input Data: Expected
   * Outcome: - Category is added new and shown in Answer page Step number: 2 Step
   * Name: Delete a category Step Description: - Open this category - Select
   * Category, then Delete Input Data: Expected Outcome: - Category is deleted
   * successfully
   */
  @Test
  public void test06_DeleteACategory() {
    String cat = "cat" + getRandomNumber();
    String des = "des" + getRandomNumber();

    info("Test 4: Add a category");

    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(cat, null, des, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).should(Condition.exist);

    info("Test 6: Delete a category");

    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).click();
    answerCategoryManagement.deleteCategory(cat);
  }

  /**
   * Case ID:116824. Test Case Name: Add a category. Step Number: 1 Step Name: Add
   * a category Step Description: - Go to Answers page - Select Category, then Add
   * Category - Put values - Save Input Data: Expected Outcome: - Category is
   * added new and shown in Answer page Step number: 2 Step Name: Delete a
   * category Step Description: - Open this category - Select Category, then
   * Delete Input Data: Expected Outcome: - Category is deleted successfully
   */
  @Test
  public void test04_AddACategory() {
    String cat = "cat" + getRandomNumber();
    String des = "des" + getRandomNumber();

    info("Test 4: Add a category");

    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(cat, null, des, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).should(Condition.exist);

    info("Test 6: Delete a category");

    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).click();
    answerCategoryManagement.deleteCategory(cat);
  }

  /**
   * Test Case Name: Edit a category. Pre-Condition: Post-Condition: Step Number:
   * 1 Step Name: Add a category Step Description: - Go to Answers page - Select
   * Category, then Add Category - Put values - Save Input Data: Expected Outcome:
   * - Category is added new and shown in Answer page Step number: 2 Step Name:
   * Edit a category Step Description: - Open this category - Select Category,
   * then Edit - Input some data into fields - Save Input Data: Expected Outcome:
   * - Category is edited successfully Step number: 2 Step Name: Delete a category
   * Step Description: - Open this category - Select Category, then Delete Input
   * Data: Expected Outcome: - Category is deleted successfully
   */

  @Test
  public void test05_EditACategory() {
    String cat = "cat" + getRandomNumber();
    String des = "des" + getRandomNumber();

    String newcat = "newcat" + getRandomNumber();
    String newdes = "newdes" + getRandomNumber();

    info("Test 4: Add a category");

    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(cat, null, des, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).should(Condition.exist);

    info("Test 5: Edit a category");

    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).click();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.EDIT);
    answerCategoryManagement.inputDataToSettingTab(newcat, null, newdes, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).shouldNot(Condition.exist);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + newcat)).should(Condition.exist);

    info("Test 6: Delete a category");

    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + newcat)).click();
    answerCategoryManagement.deleteCategory(newcat);
  }

  /**
   * Case ID:116827. Test Case Name: Move a category. Pre-Condition:
   * Post-Condition: Step Number: 1 Step Name: Add a category Step Description: -
   * Go to Answers page - Select Category, then Add Category - Put values - Save
   * Input Data: Expected Outcome: - Category is added new and shown in Answer
   * page Step number: 2 Step Name: Move a category Step Description: - Right
   * click on this category - Select Move - Double clicka destination category
   * Input Data: Expected Outcome: - Move category form is shown. -Category is
   * moved to a destination category
   */
  @Test
  public void test07_MoveACategory() {
    String paCat1 = "paCat1" + getRandomNumber();
    String paDes1 = "paDes1" + getRandomNumber();

    String paCat2 = "paCat2" + getRandomNumber();
    String paDes2 = "paDes2" + getRandomNumber();

    String chCat = "chCat" + getRandomNumber();
    String chDes = "chDes" + getRandomNumber();

    info("Create parent categories");
    homePagePlatform.goToAnswer();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);

    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
    click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).should(Condition.exist);
    info("Test 7: Move a category");

    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
    answerCategoryManagement.inputDataToSettingTab(chCat, null, chDes, null, null, null);
    $(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + chCat)).should(Condition.exist);

    answerCategoryManagement.goToActionOfCategoryFromRightClick(chCat, AnswerCategoryManagement.actionCategoryOption.MOVE);
    answerCategoryManagement.moveCategory(paCat2);
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + chCat)).should(Condition.exist);
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + chCat)).shouldNot(Condition.exist);

    info("Clear data");
    answerHomePage.goToHomeCategory();
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
    answerCategoryManagement.deleteCategory(paCat1);
    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat2)).click();
    answerCategoryManagement.deleteCategory(paCat2);
  }
}
