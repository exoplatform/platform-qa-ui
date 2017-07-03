package org.exoplatform.platform.qa.ui.platform.wiki;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TREE_WIKI_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.junit.jupiter.api.Test;


@Tag("wiki")

public class Wiki_Basic_Action_OtherActions extends Base{

	HomePagePlatform homePagePlatform;
	WikiHomePage wikiHomePage;
	ManageLogInOut manageLogInOut;
	WikiManagement wikiManagement;
	RichTextEditor richTextEditor;
	NavigationToolbar navigationToolbar;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform = new HomePagePlatform(this);
		wikiHomePage = new WikiHomePage(this);
		manageLogInOut = new ManageLogInOut(this);
		wikiManagement = new WikiManagement(this);
		navigationToolbar=new NavigationToolbar(this);
		try {
			richTextEditor = new RichTextEditor(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *<li> Case ID:122810.</li>
	 *<li> Test Case Name: Move Page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 * issue WIKI-976, wrong address in the mail notification
	 */
	@Test
	public  void test01_MovePage() {

		info("Test 1: Move Page");

		manageLogInOut.signInCas("john","gtngtn");
		String title1 = "title1" +getRandomNumber();
		String title2 =  "title2" +getRandomNumber();

		/*Step Number: 1
		 *Step Name: Step 1: Move Page
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Move Page action in menu
			- In Move form: Select new location and click on Move button
		 *Input Data: 

		 *Expected Outcome: 
			Selected Page is moved and displayed in new location*/ 

		info("Create first new wiki pages");
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1,title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);
		
		info("Create second new wiki pages");
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title2,title2);
		wikiManagement.saveAddPage();
		$(byText(title2)).should(Condition.exist);
		
		info("Move page 1 to page 2");
		wikiManagement.movePage(title1, title2);
		wikiHomePage.goToPageInformation(title2);
		$(byClassName("uiPageInfoHierarchy")).find(byText(title1)).should(Condition.exist);
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title2);
		
	}

	@Test
	public  void test02_MovePage_Intranet_MyWiki () {

		String title1 = "title1" +getRandomNumber();

 		manageLogInOut.signInCas("john","gtngtn");
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1,title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Mywiki");
		wikiManagement.selectSpaceDestination("My Wiki");
		navigationToolbar.goToMyWiki();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		wikiHomePage.deleteWiki(title1);

	}


	@Test
	public  void test03_MovePage_MyWiki_Intranet () {

		String title1 = "title1" +getRandomNumber();

		manageLogInOut.signInCas("john","gtngtn");
		navigationToolbar.goToMyWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1,title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Intranet");
		wikiManagement.selectSpaceDestination("Intranet");
		homePagePlatform.goToWiki();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		wikiHomePage.deleteWiki(title1);
	}


	}