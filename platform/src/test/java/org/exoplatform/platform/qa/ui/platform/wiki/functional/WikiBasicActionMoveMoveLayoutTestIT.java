package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_MOVE_PAGE_DESTINATION_LABEL;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_MOVE_PAGE_SELECT_THE_DESTINATION_LABEL;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_MOVE_SPACESWITCHER;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("functional")
@Tag("wiki")
public class WikiBasicActionMoveMoveLayoutTestIT extends Base {

    HomePagePlatform homePagePlatform;
    SpaceManagement spaceManagement;
    WikiHomePage wikiHomePage;
    WikiManagement wikiManagement;
    RichTextEditor richEditor;
    SpaceHomePage spaceHomePage;



    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        spaceHomePage = new SpaceHomePage(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        spaceManagement = new SpaceManagement(this);
        richEditor = new RichTextEditor(this);
    }

    @Test
    public void test01_MoveLayoutIsDisplayingSelectTheDestiNationLabel() {
        String space1 = "space1" + getRandomNumber();
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        info("Create data test");
        info("Create Space 1 with a wiki page 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        /*Step Number: 1
         *Step Name: Step 1
		 *Step Description:
		 *- Go to the wiki of the "Space Move"
		 *Input Data:
         *Expected Outcome:
	     *- Wiki is displayed*/
		/*Step Number: 2
		 *Step Name: Step 2
		 *Step Description:
		 *- Create a new Page with the title Page A
		 *- Save
		 *Input Data:
         *Expected Outcome:
		 *- Page A is displayed*/
        info("Create wiki page in sapce1");
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();

        /*Step Number: 3
		 *Step Name: Step 3
		 *Step Description:
		 *- Click on [More] on menu
		 *Input Data:
         *Expected Outcome:
		 *- Move option is available*/
        wikiHomePage.goToMovePageForm();

		/*Step Number: 4
		 *Step Name: Step 4
		 *Step Description:
		 *- Click on Move options
		 *Input Data:
         *Expected Outcome:
		 *- A popup is displayed with the space switcher
		 *- Label "Select the destination:" is displayed before the space switcher*/
        info("Test 1: Move Layout is displaying Select the destination label");
        info("Label Select the destination is displayed before the space switcher");
        $(ELEMENT_MOVE_PAGE_SELECT_THE_DESTINATION_LABEL).waitUntil(Condition.visible, Configuration.timeout);

        info("Delete the page");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }


    @Test
    public  void test02_MoveLayoutIsDisplayingSelectSpaceSwitcher() {
        String space1 = "space1" + getRandomNumber();
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        info("Create data test");
        info("Create Space 1 with a wiki page 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);
         /*Step Number: 1
         *Step Name: Step 1
         *Step Description:
         *- Go to the wiki of the "Space Move"
         *Input Data:
         *Expected Outcome:
         *- Wiki is displayed*/
         /*Step Number: 2
         *Step Name: Step 2
         *Step Description:
         *- Create a new Page with the title Page A
         *- Save
         *Input Data:
         *Expected Outcome:
         *- Page A is displayed*/
        info("Create wiki page in sapce1");
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
         /*Step Number: 3
         *Step Name: Step 3
         *Step Description:
         *- Click on [More] on menu
         *Input Data:
         *Expected Outcome:
         *- Move option is available*/
        wikiHomePage.goToMovePageForm();
         /*Step Number: 5
         *Step Name: Step 5
         *Step Description:
         *- Click on Move options
         *Input Data:
         *Expected Outcome:
         *- A popup is displayed with the space switcher*/
        info("Test 2: Move Layout is displaying space switcher");
        info("Space switcher is displayed");
        $(ELEMENT_MOVE_SPACESWITCHER).waitUntil(Condition.visible,Configuration.timeout);

        info("Delete the page");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }

    @Test
    public  void test03_MoveLayoutIsDisplayingSelectTheDestinationLabels(){
        String space1 = "space1"+ getRandomNumber();
        String title = "title" +getRandomNumber();
        String content =  "content"+ getRandomNumber();
        info("Create data test");
        info("Create Space 1 with a wiki page 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);
         /*Step Number: 1
         *Step Name: Step 1
         *Step Description:
         *- Go to the wiki of the "Space Move"
         *Input Data:
         *Expected Outcome:
         *- Wiki is displayed*/
         /*Step Number: 2
         *Step Name: Step 2
         *Step Description:
         *- Create a new Page with the title Page A
         *- Save
         *Input Data:
         *Expected Outcome:
         *- Page A is displayed*/
        info("Create wiki page in sapce1");
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        /*Step Number: 3
        *Step Name: Step 3
        *Step Description:
        *- Click on [More] on menu
        *Input Data:
        *Expected Outcome:
        *- Move option is available*/
        wikiHomePage.goToMovePageForm();
        /*Step Number: 5
         *Step Name: Step 5
         *Step Description:
         *- Click on Move options
         *Input Data:
         *Expected Outcome:
         *- A popup is displayed with the space switcher*/
         /*Step Number: 6
         *Step Name: Step 6
         *Step Description:
         *- Click on Move options
         *Input Data:
         *Expected Outcome:
         *- A popup is displayed to move the page
         *- Label "Destination:" is displayed outside and above the destination container.*/
        info("Test 3: Move layout should display destination labels outside destination container");
        info("Label Destination is displayed outside and above the destination container");
        $(ELEMENT_MOVE_PAGE_DESTINATION_LABEL).waitUntil(Condition.visible,Configuration.timeout);

        info("Delete the page");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }
}