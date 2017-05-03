package org.exoplatform.platform.ui.qa.wiki;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TREE_WIKI_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("wiki")
public class Wiki_Basic_Action_Manage_Page_Add_DeleteTestIT extends Base {
	HomePagePlatform homePagePlatform;

	WikiHomePage wikiHomePage;
	WikiManagement wikiManagement;
	RichTextEditor richTextEditor;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
	homePagePlatform=new HomePagePlatform(this);
	wikiHomePage=new WikiHomePage(this);
	wikiManagement=new WikiManagement(this);
		try {
	richTextEditor=new RichTextEditor(this);
	} catch (Exception e) {
			e.printStackTrace();
		}}
		/**
	 *<li> Case ID:122806.</li>
	 *<li> Test Case Name: Create page using Source Editor.</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122820.</li>
	 *<li> Test Case Name: Delete page with Source Editor.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test02_09_Create_Delete_PageUsingSourceEditor() {
		info("Test 02: Create page using Source Editor");
		String wiki = "wiki"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Step 1: Create Page with Source Editor
		 *Step Description:
			- Go to Wiki
			- Click on Add Page -> Blank Page icon in toolbar action
			- Input valid data for Title page and Page's content
			- Click on Save icon in toolbar

		 *Input Data:

		 *Expected Outcome:
			Page is added successful and listed in navigation tree*/
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(wiki, wiki);
		wikiManagement.saveAddPage();
$(byText(wiki)).should(Condition.exist);
		info("Test 08: Delete the page that is created by using source editor");
		/*Step Number: 1
		 *Step Name: Step 1: Delete page with Source Editor
		 *Step Description:
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Delete page action in menu
			- Click on OK button on Confirm message form


		 *Input Data:

		 *Expected Outcome:
			Delete page successfully*/
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(wiki);
	}

}