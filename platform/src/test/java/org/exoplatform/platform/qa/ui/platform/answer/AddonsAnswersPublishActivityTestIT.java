package org.exoplatform.platform.qa.ui.platform.answer;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_NAME_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.ELEMENT_COMMENT_AUTHOR;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.answer.pageobject.*;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class AddonsAnswersPublishActivityTestIT extends Base {

	HomePagePlatform homePagePlatform;
	AnswerCategoryManagement answerCategoryManagement;
	QuestionManagement questionManagement;
	AnswerManagement answerManagement;
	CommentManagement commentManagement;
	ActivityStream activityStream;
	AnswerHomePage answerHomePage;



	/**
	 * Case ID:116838.
	 * Test Case Name: Create a question.
	 * Case ID:116844.
	 * Test Case Name: Delete a question.
	 * Pre-Condition: - Create a new page to add new answer portlet (call it Answer Page in this test case)
	 * Post-Condition: 
	 */
	@Test
	public  void test01_07_CreateDeleteAQuestion() {
		String question = "question"+getRandomNumber();
		String content = "line1<br>line2<br>line3<br>line4<br>line5<br>";
		String paCat1 ="paCat1"+getRandomNumber();
		String paDes1 = "paDes1"+getRandomNumber();
		String answer = "answer"+getRandomNumber();
		String comment = "comment"+getRandomNumber();
		info("Test 1: Create a question");
		/*Step Number: 1
		 *Step Name: - Create a new question
		 *Step Description: 
			- Goto answer page
			- Create new Category
			- create new question
		 *Input Data: 

		 *Expected Outcome: 
			- Question is created successfully
			- an Activity is added into activity stream
			- Informations that are displayed in the featured content :1. Question's title2. First 4 lines of question's details3. Number of replies4. Number of comments5. Average rating of the question*/ 
		homePagePlatform.goToAnswer();
		info("Create category");
		answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
		answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).should(Condition.exist);

		info("Create question");
		answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1, AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
		questionManagement.inputDataToQuestionForm(question, content, null, null);
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		$(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
		$(ELEMENT_OK_BUTTON_LINK).click();

		info("Create answer");
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + paCat1)).click();
		answerManagement.goToAnswerQuestion(question);
		answerManagement.inputDataToAnswer(answer, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		$(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
		$(byText(answer)).parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);

		info("Create comment");
		commentManagement.goToCommentQuestion(question);
		commentManagement.inputDataToComment(comment);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		$(byText(comment)).parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);

		info("Check homepage activity");
		homePagePlatform.goToHomePage();
		waitForAndGetElement(ELEMENT_QUESTION_ACTIVITY_TITLE.replace("$user", DATA_USER1).replace("$question", question));
		waitForAndGetElement(ELEMENT_QUESTION_ACTIVITY_RATING.replace("$user", DATA_USER1).replace("$question", question));
		waitForAndGetElement(ELEMENT_QUESTION_ACTIVITY_ANSWER_NUMBER.replace("$user", DATA_USER1).replace("$question", question).replace("$number", "1"));
		waitForAndGetElement(ELEMENT_QUESTION_ACTIVITY_COMMENT_NUMBER.replace("$user", DATA_USER1).replace("$question", question).replace("$number", "1"));
		String conAct=waitForAndGetElement(ELEMENT_QUESTION_ACTIVITY_CONTENT.replace("$user", DATA_USER1).replace("$question", question)).getText();
		info(conAct);
		activityStream.checkContentOfActivity(conAct,content);

		info("Test 7: Delete a question");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- Go to the question and click More Actions 
			-
			-> Delete
			- Click OK to summit
			- Back to the Hompage
		 *Input Data: 

		 *Expected Outcome: 
			- The Answers activity related to the question is removed from the activity stream*/ 
		homePagePlatform.goToAnswer();
		answerHomePage.goToHomeCategory();
		click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		questionManagement.deleteQuestion(question);

		info("Check homepage activity");
		homePagePlatform.goToHomePage();
		waitForElementNotPresent(ELEMENT_QUESTION_ACTIVITY_TITLE.replace("$user", DATA_USER1).replace("$question", question));

		info("Clear data test");
		homePagePlatform.goToAnswer();
		answerHomePage.goToHomeCategory();
		click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		answerCategoryManagement.deleteCategory(paCat1);
		//qMang.deleteQuestion(question);
	}


}