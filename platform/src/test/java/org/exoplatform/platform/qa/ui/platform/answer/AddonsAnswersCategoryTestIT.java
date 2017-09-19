package org.exoplatform.platform.qa.ui.platform.answer;


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
import org.exoplatform.platform.qa.ui.selenium.user.UserDatabase;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;


public class AddonsAnswersCategoryTestIT extends Base {
	HomePagePlatform homePagePlatform;
	ManageLogInOut manageLogInOut;
	AnswerCategoryManagement answerCategoryManagement;
	QuestionManagement questionManagement;
	AnswerHomePage answerHomePage;
	ManageAlert manageAlert;
	Button button;
	AnswerManagement answerManagement;
	UserProfilePage userProfilePage;
	NavigationToolbar navigationToolbar;
	@BeforeEach
	public void setUpBeforeTest() throws Exception{

		navigationToolbar = new NavigationToolbar(this);
		userProfilePage = new UserProfilePage(this);
		manageLogInOut = new ManageLogInOut(this);
		homePagePlatform = new HomePagePlatform(this);
		answerCategoryManagement = new AnswerCategoryManagement(this);
		button = new Button(this);
		answerHomePage=new AnswerHomePage(this);
		questionManagement = new QuestionManagement(this);
		answerManagement = new AnswerManagement(this);
		manageAlert = new ManageAlert(this);

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
	 * Case ID:116809.
	 * Test Case Name: Drag and Drop category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_DragAndDropCategory() {
		String paCat1 = "paCat1"+getRandomNumber();
		String paDes1 = "paDes1"+getRandomNumber();;

		String paCat2 = "paCat2"+getRandomNumber();;
		String paDes2 ="paDes2"+getRandomNumber();;

		info("Create parent categories");
		homePagePlatform.goToAnswer();
		answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
		answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat1)).should(Condition.exist);
		answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
		answerCategoryManagement.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)).should(Condition.exist);

		info("Test 1: Drag and Drop category");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create categories after drag and drop them
		 *Input Data: 
			- Create some categories 
			- Drag and drop a category into another category
		 *Expected Outcome: 
			Category is dragged and dropped successfully to selected category*/ 
		//dragAndDropToObject(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1), ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat1)).dragAndDropTo(ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)));
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)).click();
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat1)).should(Condition.exist);
		
		info("Clear data");
		answerHomePage.goToHomeCategory();
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)).click();
		answerCategoryManagement.deleteCategory(paCat2);
	}
	/**
	 * Case ID:116810.
	 * Test Case Name: Export/Import category.
	 * Pre-Condition:
	 * Post-Condition:
	 */
	@Test
	public  void test02_ExportImportCategory() {
		String paCat1 = "paCat1"+getRandomNumber();
		String paDes1 = "paDes1"+getRandomNumber();
		String question1 = "question1"+getRandomNumber();
		String content1 ="content1"+getRandomNumber();
		String paCat2 = "paCat2"+getRandomNumber();
		String paDes2 = "paDes2"+getRandomNumber();
		String fileName = getRandomNumber();
		info("Test 2: Export/Import category");
		/*Step Number: 1
		 *Step Name: Create categories, questions
		 *Step Description:
			- Create some categories
			- Create some questions in categories
		 *Input Data:

		 *Expected Outcome:
			Category and its questions are exported/imported successfully*/
		info("Create category 1");
		homePagePlatform.goToAnswer();
		answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
		answerCategoryManagement.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat1)).should(Condition.exist);

		info("Create question 1");
		answerCategoryManagement.goToActionOfCategoryFromRightClick(paCat1, AnswerCategoryManagement.actionCategoryOption.SUBMITQUESTION);
		questionManagement.inputDataToQuestionForm(question1, content1, null, null);
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		clickByJavascript(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
		$(button.ELEMENT_OK_BUTTON_LINK).click();

		info("Create category 2");
		answerHomePage.goToHomeCategory();
		answerCategoryManagement.goToActionOfCategoryFromActionBar(AnswerCategoryManagement.actionCategoryOption.ADD);
		answerCategoryManagement.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)).should(Condition.exist);

		/*Step number: 2
		 *Step Name: Export categories
		 *Step Description:
			- Right click on 1 category and select Export
			- Put file's name and Export
		 *Input Data:

		 *Expected Outcome:
			Category and its questions are exported successfully*/
		answerHomePage.goToHomeCategory();
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat1)).click();
		answerCategoryManagement.exportAnswerCategory(fileName);
		answerCategoryManagement.deleteCategory(paCat1);

		/*Step number: 3
		 *Step Name: Import categories
		 *Step Description:
			- Remove the above category
			- Right click on another category and select Import
			- Choose the file which is exported above
		 *Input Data:

		 *Expected Outcome:
			- The category is removed successfully.
			- That category and its questions are imported successfully*/
		answerHomePage.goToHomeCategory();
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)).click();
		answerCategoryManagement.importAnswerCategory();
		answerHomePage.goToHomeCategory();
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)).click();
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" paCat1504411")).click();
		$(byText("question1125877")).should(Condition.exist);
		info("Clear data");
		answerHomePage.goToHomeCategory();
		answerHomePage.goToHomeCategory();
		ELEMENT_LIST_CATEGORIE.find(byLinkText(" "+paCat2)).click();
		answerCategoryManagement.deleteCategory(paCat2);
	}
	}