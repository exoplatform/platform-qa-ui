package org.exoplatform.platform.qa.ui.answer.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerCategoryManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.AnswerHomePage;
import org.exoplatform.platform.qa.ui.answer.pageobject.CommentManagement;
import org.exoplatform.platform.qa.ui.answer.pageobject.QuestionManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.DownloadFileControl.driver;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("answer")
public class Addons_Answers_Question extends Base {

HomePagePlatform homePagePlatform;
QuestionManagement questionManagement;
AnswerCategoryManagement answerCategoryManagement;
AnswerHomePage answerHomePage;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform=new HomePagePlatform(this);
		questionManagement=new QuestionManagement(this);
		answerCategoryManagement=new AnswerCategoryManagement(this);
		answerHomePage=new AnswerHomePage(this);
	}
	/**
	 * Case ID:116828.
	 * Test Case Name: Add a question.
	 * Case ID:116829.
	 * Test Case Name: Edit a question.
	 * Case ID:116830.
	 * Test Case Name: Delete a question.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
@Test
	public  void test02_03_04_AddEditDeleteAQuestion() {
		String question = "question"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String newquestion = "newquestion"+getRandomNumber();
		String newcontent ="newcontent"+getRandomNumber();

		info("Test 2: Add a question");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/
		homePagePlatform.goToAnswer();
		questionManagement.goToSubmitQuestion();
		questionManagement.inputDataToQuestionForm(question, content, null, "");
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		$(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
		$(ELEMENT_OK_BUTTON_LINK).click();
		$(byText(question)).should(Condition.exist);
		info("Test 3: Edit a question");
		/*Step number: 2
		 *Step Name: Edit a question
		 *Step Description: 
			- Right click on this question, select Edit
			- Put values into fields
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Edit Question form is shown
			- This question is updated successfully.*/
		questionManagement.goToActionOfQuestionByRightClick(question, QuestionManagement.actionQuestionOption.EDIT);
		questionManagement.inputDataToQuestionForm(newquestion, newcontent, null, "");
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		$(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
		$(byText(question)).shouldNot(Condition.exist);
		$(byText(newquestion)).should(Condition.exist);
		info("Test 4: Delete a question");
		/*Step number: 2
		 *Step Name: Delete a question
		 *Step Description: 
			- Right click on a question and select Delete
		 *Input Data: 

		 *Expected Outcome: 
			- Question is deleted and disappear in Answers page*/
		questionManagement.deleteQuestion(newquestion);
	}

	/**
	 * Case ID:116831.
	 * Test Case Name: Move a question.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
@Test
	public  void test07_MoveAQuestion() {
		String title = "title"+getRandomNumber();
		String content = "content"+getRandomNumber();;
		String paCat1 = "paCat1"+getRandomNumber();;
		String paDes1 = "paDes1"+getRandomNumber();;

		String paCat2 = "paCat2"+getRandomNumber();;
		String paDes2 = "paDes2"+getRandomNumber();;

		info("Create parent categories");
		homePagePlatform.goToAnswer();
		answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
		answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		$(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
		$(byId("UIAnswersPortlet")).find(byText(paCat1)).should(Condition.exist);
		answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
		answerCategoryManagement.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		$(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON).click();
	    $(byText(paCat2)).waitUntil(Condition.appears, Configuration.timeout);
		info("Test 7: Move a question");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/
		homePagePlatform.goToAnswer();
		answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1, AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
		questionManagement.inputDataToQuestionForm(title, content, null, null);
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		$(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
		$(ELEMENT_OK_BUTTON_LINK).click();
		
		/*Step number: 2
		 *Step Name: Move a question
		 *Step Description: 
			- Right click on question and select Move to 
			- Select destination category
		 *Input Data: 

		 *Expected Outcome: 
			- Question is moved to destination category*/ 
	    $(byText(paCat1)).click();
		/*questionManagement.goToActionOfQuestionByRightClick(title, QuestionManagement.actionQuestionOption.MOVE);
		answerCategoryManagement.moveCategory(paCat2);
		answerHomePage.goToHomeCategory();
		click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		waitForAndGetElement(By.xpath(ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		answerHomePage.goToHomeCategory();
		click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		waitForElementNotPresent(By.xpath(ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		
		info("Clear data");
		answerHomePage.goToHomeCategory();
		click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		answerCategoryManagement.deleteCategory(paCat1);
		click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		answerCategoryManagement.deleteCategory(paCat2);*/
	}


}