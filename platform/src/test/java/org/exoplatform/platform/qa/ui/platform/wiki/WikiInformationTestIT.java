package org.exoplatform.platform.qa.ui.platform.wiki;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("wiki")
@Tag("smoke")

public class WikiInformationTestIT extends Base {

	HomePagePlatform homePagePlatform;

	WikiHomePage wikiHomePage;

	ManageLogInOut manageLogInOut;

	WikiManagement wikiManagement;

	RichTextEditor richTextEditor;

	SpaceHomePage spaceHomePage;

	SpaceManagement spaceManagement;

	WikiPageInformation wikiPageInformation;

	SourceTextEditor sourceTextEditor;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform = new HomePagePlatform(this);
		wikiHomePage = new WikiHomePage(this);
		manageLogInOut = new ManageLogInOut(this);
		wikiManagement = new WikiManagement(this);
		spaceManagement = new SpaceManagement(this);
		spaceHomePage = new SpaceHomePage(this);
		wikiPageInformation = new WikiPageInformation(this);
		sourceTextEditor = new SourceTextEditor(this);

		richTextEditor = new RichTextEditor(this);

	}

	/**
	 *<li> Case ID:122853.</li>
	 *<li> Test Case Name: Add relations from 2 different spaces</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test
	@BugInPLF("WIKI-1313")
	public void test07_AddRelationFrom2DiffenentSpaces() {
		info("Test 07: Add relations from 2 different spaces");
		String space1 = "space1"+getRandomNumber();
		String space2 = "space2"+getRandomNumber();
		String title1 = "title1"+getRandomNumber();
		String title2 = "title2"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Step 1: Add new space
		 *Step Description:
			- Login portal
			- Click join a space
			- Add new space for "Space 1" and "Space 2"
		 *Input Data:

		 *Expected Outcome:
			New space  is added successfully*/

		/*Step Number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1 and space 2
		 *Step Description:
			- Go to Space 1/ wiki and Space2/wiki
			- Add new page for wiki with name "Page1" and "Page2"
		 *Input Data:

		 *Expected Outcome:
			Add new page successfully*/

		info("Create space 1 and wiki page 1");
		homePagePlatform.goToMySpaces();
		spaceManagement.addNewSpaceSimple(space1,space1);
		info("Add new wiki page for space 1");
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1,title1);
		wikiManagement.saveAddPage();

		info("Create space 2 and wiki page 2");
		homePagePlatform.goToMySpaces();
		spaceManagement.addNewSpaceSimple(space2,space2);
		info("Add new wiki page for space 2");
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title2,title2);
		wikiManagement.saveAddPage();

		/*Step Number: 3
		 *Step Name: Step 3: Open Page1
		 *Step Description:
			- Open "Page 1"
		 *Input Data:

		 *Expected Outcome:
			 Page 1 is displayed in the wiki*/

		/*Step Number: 4
		 *Step Name: Step 4: Go to Page Info
		 *Step Description:
			- Open "More" Menu -> "Page Info"
		 *Input Data:

		 *Expected Outcome:
			Page infor of Page 1 are diplayed*/

		info("Open wiki page 1");
		homePagePlatform.goToSpecificSpace(space1);
		spaceHomePage.goToWikiTab();
		info("Open page 1 and Go to Page Info");
		wikiHomePage.goToAPage(title1);
		wikiHomePage.goToPageInformation();

		/*Step Number: 5
		 *Step Name: Step 5: Go to Related page form
		 *Step Description:
			-  Click "Add More Relations" on Related Page form
		 *Input Data:

		 *Expected Outcome:
			The popup to select a related page is displayed*/
		info("Go to Related page form");
		wikiPageInformation.goToAddRelations();

		/*Step Number: 6
		 *Step Name: Step 6: Check when add relations from 2 different spaces
		 *Step Description:
			- Open Space switcher component
			- Select "Space 2"
			- Select "Page 2"
			- Click on "Select" button

		 *Input Data:

		 *Expected Outcome:
			- The list of space switcher options is displayed
			- Wiki tree of "Space 2" is displayed on the container below the space switcher
			- Popup is closed
			- "Page 2" is added as a related pages on page info layout*/

		info("Wiki tree of 'Space 2' is displayed on the container below the space switcher");
		String spaces =space1+"/"+space2;
		wikiManagement.checkAddRelationDropDownList(spaces);
		info("Check when add relations from 2 different spaces");
		wikiPageInformation.addRelations(space2,title2);
		info("intranet's portal is added as a related pages on page info layout");
	//	waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT.replace("${col1}",space2).replace("${col2}",title2),2000,0);

		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);
		spaMg.deleteSpace(space2,false);*/

	}

}