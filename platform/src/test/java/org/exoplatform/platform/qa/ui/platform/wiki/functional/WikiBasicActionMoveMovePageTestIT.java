package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_CANCEL_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_WIKI_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 6/20/18.
 */

@Tag("functional")
@Tag("wiki")
public class WikiBasicActionMoveMovePageTestIT extends Base {

    HomePagePlatform homePagePlatform;
    WikiHomePage wikiHomePage;
    WikiManagement wikiManagement;
    SourceTextEditor sourceTextEditor;
    ManageLogInOut manageLogInOut;
    WikiValidattions wikiValidattions;
    WikiDraftPage wikiDraftPage;
    RichTextEditor richTextEditor;
    SpaceManagement spaceManagement;
    SpaceHomePage spaceHomePage;
    WikiPermission wikiPermission;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        sourceTextEditor = new SourceTextEditor(this);
        manageLogInOut = new ManageLogInOut(this);
        wikiValidattions = new WikiValidattions(this);
        wikiDraftPage = new WikiDraftPage(this);
        richTextEditor = new RichTextEditor(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        wikiPermission = new WikiPermission(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    /**
     * <li> Case ID:139576.</li>
     * <li> Test Case Name: Page's attachments should be move with the page.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination 2"
     * Wiki of "Space Move" has:
     * - Page A
     * - Page B
     * - Page with attachments (with two images in its content)
     * Wiki of "Space Destination 2" is empty.</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Select a wiki page of a space
     * Step Description:
     * - Go to a space, then select [Wiki]
     * - Select a "Page with attachments"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page with attachments" is displayed
     * <p>
     * Step Number: 2
     * Step Name: Step 2: Move page
     * Step Description:
     * - Click [More]
     * - Select [Move Page] from the drop-down menu
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move the page is displayed*
     * Step Number: 2
     * Step Name: Step 2: Move page
     * Step Description:
     * - Click [More]
     * - Select [Move Page] from the drop-down menu
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move the page is displayed*
     * Step Number: 3
     * Step Name: Step 3: Select destination
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination 2"
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination 2" tree
     * - New Location Path is displaying :
     * Space Destination 2> Wiki Home
     * Step Number: 4
     * Step Name: Step 4. Do "Move"Step 4: Select destination
     * Step Description:
     * - Click the [Move] button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page with attachments" is moved in the space "Space Destination 2" and directly displayed
     * Step Number: 5
     * Step Name: Step 5. Check
     * Step Description:
     * - Check content of the wiki page
     * Input Data:
     * <p>
     * Expected Outcome:
     * Page content is correctly displayed with the attachments
     */
    @Test
    public void test01_PageAttachmentShouldBeMoveWithThePage() {


        String space1 = "space1" + getRandomNumber();
        String space2 = "space2" + getRandomNumber();

        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();


        info("Test 1: Page's attachments should be move with the page");

        String link1 = "wiki_attachment.txt";

        String link2 = "testavatar.png";
        System.out.println(link2);


        String linkImage1 = "testavatar.png";
        String altText1 = "altText1" + getRandomNumber();

        String linkImage2 = "eXo-Platform.png";
        String altText2 = "altText2" + getRandomNumber();

        String width = "200";
        String height = "200";

        info("Create data test");
        info("Create Space 1 with a wiki page 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);
        info("Create wiki page with 2 images attachment and 2 images inserted to Space 1");
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        info("insert image 1 to page");
        richTextEditor.insertExternalImageLink(linkImage1, width, height, altText1);
        richTextEditor.selectAlign(RichTextEditor.alignType.Left);
        richTextEditor.goToInsertImage();
        wikiManagement.saveAddPage();
        info("insert image 2 to page");
        wikiHomePage.goToEditPage();
        richTextEditor.insertExternalImageLink(linkImage2, width, height, altText2);
        richTextEditor.selectAlign(RichTextEditor.alignType.Left);
        richTextEditor.goToInsertImage();
        wikiManagement.saveAddPage();
        info("Attach 2 images to page");
        wikiHomePage.goToEditPage();
        info("Upload link to wiki page");
        wikiManagement.goToSourceEditor();
        sourceTextEditor.attachFileInWiki(link1, 2);
        sourceTextEditor.attachFileInWiki(link2, 2);
        wikiManagement.saveAddPage();


        info("Create Space 2 with no wiki page");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);
        info("Verify 2 images are displayed in content od wiki page");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiValidattions.verifyTitleWikiPage(title);
        wikiHomePage.goToAPage(title);
        wikiValidattions.verifyAltTextImageInContentPage(altText1);
        wikiValidattions.verifyAltTextImageInContentPage(altText2);
        info("2 inserted images are inserted successfully in content of page");
        info("Verify 2 images are displayed in attach list of wiki page");
        wikiHomePage.goToAttachFiles("2");
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link1, true);
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link2, true);
        info("2 attached images are attached successfully in attache list of page");
        info("Move wiki page to space 2");
        wikiManagement.movePageDiffDestination(title, "Wiki Home", space2);
        info("Check to make sure wiki page does not exist in Space 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(title);
        info("Wiki page does not exist in Space 1");

        info("Check to make sure wiki page exists in Space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space2, "");
        spaceManagement.goToSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiValidattions.verifyTitleWikiPage(title);
        wikiHomePage.goToAPage(title);
        wikiValidattions.verifyAltTextImageInContentPage(altText1);
        wikiValidattions.verifyAltTextImageInContentPage(altText2);
        wikiHomePage.goToAttachFiles("2");
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link1, true);
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link2, true);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.searchSpace(space2, "");
        spaceManagement.deleteSpace(space2, false);
    }

    /**
     * <li> Case ID:139577.</li>
     * <li> Test Case Name: Page's sub-pages and attachments should be moved with the page.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination 2"
     * Wiki of "Space Move" has:
     * - Page A
     * - Page B
     * - Page with Sub-Pages
     * --- Sub-Page with attachments 1 (two images in its content)
     * --- Sub-Page with attachments 2 (two files as attachments)
     * Wiki of "Space Destination 2" has:
     * - Page with attachments (with two images in its content)</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page with Sub-Pages"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page with Sub-Pages" is displayed
     * <p>
     * Step Number: 2
     * Step Name: Step 2:
     * Step Description:
     * - Open "Edit" Menu
     * - Select "Move"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move the page is displayed
     * Step Number: 3
     * Step Name: Step 3:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination 2"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination 2" tree
     * Step Number: 4
     * Step Name: Step 4:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying : Space Destination 2 > Wiki Home*
     * Step Number: 5
     * Step Name: Step 5
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * "Page to with Sub-Pages" is moved in the space "Space Destination 2" and directly displayed
     * <p>
     * Step Number: 6
     * Step Name: Step 6
     * Step Description:
     * - Check content of the "Sub-Page with attachments 1"
     * Input Data:
     * <p>
     * Expected Outcome:
     * Page content is correctly displaying 2 images previously displayed
     */
    @Test
    public void test02_PageSubpageAndAttachmentsShouldBeMovedWithThePage() {
        info("Test 2: Page's sub-pages and attachments should be moved with the page.");

        String space1 = "space1" + getRandomNumber();
        String space2 = "space2" + getRandomNumber();

        String wiki = "wiki" + getRandomNumber();
        String wiki1 = "wiki1" + getRandomNumber();
        String wiki2 = "wiki2" + getRandomNumber();

        String wiki3 = "wiki3" + getRandomNumber();

        String link1 = "wiki_attachment.txt";
        System.out.println(link1);

        String link2 = "testavatar.png";
        System.out.println(link2);

        String linkImage1 = "testavatar.png";
        String altText1 = "altText1" + getRandomNumber();

        String linkImage2 = "eXo-Platform.png";
        String altText2 = "altText2" + getRandomNumber();

        String width = "200";
        String height = "200";

        info("Create data test");
        info("Create Space 1 with 1 wiki page and 2 sub pages");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);
        info("Create wiki page");
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki, wiki);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki);

        info("Create a sub page 1");
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToRichTextEditor();
        richTextEditor.addSimplePage(wiki1, wiki1);

        info("insert image 1 to page 1");
        richTextEditor.insertExternalImageLink(linkImage1, width, height, altText1);
        richTextEditor.selectAlign(RichTextEditor.alignType.Left);
        richTextEditor.goToInsertImage();
        wikiManagement.saveAddPage();

        info("insert image 2 to page 1");
        wikiHomePage.goToEditPage();
        richTextEditor.insertExternalImageLink(linkImage2, width, height, altText2);
        richTextEditor.selectAlign(RichTextEditor.alignType.Left);
        richTextEditor.goToInsertImage();
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki1);

        info("Create a sub page 2");
        wikiHomePage.goToAPage(wiki);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki2, wiki2);

        info("Attach 2 files for sub page 2");
        wikiManagement.goToSourceEditor();
        sourceTextEditor.attachFileInWiki(link1, 2);
        sourceTextEditor.attachFileInWiki(link2, 2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki2);


        info("Create Space 2 with 1 wiki page");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToRichTextEditor();
        richTextEditor.addSimplePage(wiki3, wiki3);

        info("insert image 1 to page 3");
        richTextEditor.insertExternalImageLink(linkImage1, width, height, altText1);
        richTextEditor.selectAlign(RichTextEditor.alignType.Left);
        richTextEditor.goToInsertImage();
        wikiManagement.saveAddPage();


        info("insert image 2 to page 3");
        wikiHomePage.goToEditPage();
        richTextEditor.insertExternalImageLink(linkImage2, width, height, altText2);
        richTextEditor.selectAlign(RichTextEditor.alignType.Left);
        richTextEditor.goToInsertImage();
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki3);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAPage(wiki);

        info("Verify 2 sub pages are in page wiki");
        wikiValidattions.verifyTitleWikiPage(wiki1);
        wikiValidattions.verifyTitleWikiPage(wiki2);

        info("Verify 2 inserted images to sub pages 1 ");
        wikiHomePage.goToAPage(wiki1);
        wikiValidattions.verifyAltTextImageInContentPage(altText1);
        wikiValidattions.verifyAltTextImageInContentPage(altText2);
        info("2 inserted images are inserted successfully in content of page 1");

        info("Verify 2 images are displayed in attach list of wiki page 2");
        wikiHomePage.goToAPage(wiki2);
        wikiHomePage.goToAttachFiles("2");
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link1, true);
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link2, true);
        info("2 attached images are attached successfully in attache list of page 2");
        info("Move wiki page to space 2");
        wikiHomePage.goToAPage(wiki);
        wikiManagement.movePageDiffDestination(wiki, "Wiki Home", space2);

        info("Check to make sure wiki page does not exist in Space 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceManagement.goToWikiTab();
        wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(wiki);
        info("Wiki page does not exist in Space 1");

        info("Check to make sure wiki page exists in Space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space2, "");
        spaceManagement.goToSpace(space2);
        spaceManagement.goToWikiTab();
        wikiValidattions.verifyTitleWikiPage(wiki);
        info("Wiki page exists in Space 2");
        wikiHomePage.goToAPage(wiki);
        wikiValidattions.verifyTitleWikiPage(wiki1);
        wikiValidattions.verifyTitleWikiPage(wiki2);
        wikiHomePage.goToAPage(wiki1);
        wikiValidattions.verifyAltTextImageInContentPage(altText1);
        wikiValidattions.verifyAltTextImageInContentPage(altText2);

		/*Step Number: 7
         *Step Name: Step 7
		 *Step Description:
			- Check content of the "Sub-Page with attachments 2"
		 *Input Data:

		 *Expected Outcome:
			The page has its two files attached correctly*/
        wikiHomePage.goToAPage(wiki2);
        wikiHomePage.goToAttachFiles("2");
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link1, true);
        wikiValidattions.VerifyAttachFilesAreDisplayedInAttachListOrNot(link2, true);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.searchSpace(space2, "");
        spaceManagement.deleteSpace(space2, false);
    }


    /**
     * <li> Case ID:139578.</li>
     * <li> Test Case Name: Page's sub-pages should be moved with the page.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page A
     * - Page B
     * - Page C Renamed
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * - Page D
     * --- Sub-Page level 1
     * ------ Sub-Page level 2
     * -------- Sub-Page level 3
     * - Page to move
     * --- Sub-Page to move
     * --- Sub-Page to move 2
     * ------ Sub-Page level 2 to move
     * <p>
     * Wiki of "Space Destination" has:
     * - Page 1
     * - Page B
     * - page b
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * - The complex page
     * --- Sub-Page level 1
     * ------ Sub-Page level 2
     * -------- Sub-Page level 3</li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * Step Name: Step 1
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page to move"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page to move" is displayed*
     * Step Number: 2
     * Step Name: Step 2
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed
     * <p>
     * Step Number: 3
     * Step Name: Step 3
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree
     * <p>
     * Step Number: 4
     * Step Name: Step 4
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying : Space Destination > Wiki Home
     * <p>
     * Step Number: 5
     * Step Name: Step 5
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * "Page to move" is moved in the space "Space Destination"
     */
    @Test
    public void test03_PageSubpageShouldBeMovedWithThePage() {
        info("Test 3: Page's sub-pages should be moved with the page");

        String space1 = "space1" + getRandomNumber();
        String space2 = "space2" + getRandomNumber();

        String wiki1 = "wiki1" + getRandomNumber();
        String subWiki1 = "subWiki1" + getRandomNumber();
        String subWiki2 = "subWiki2" + getRandomNumber();
        String subWiki3 = "subWiki3" + getRandomNumber();
        String subWiki4 = "subWiki4" + getRandomNumber();
        String subWiki5 = "subWiki5" + getRandomNumber();
        String[] subwiki = {subWiki1, subWiki2, subWiki3, subWiki4, subWiki5};

        String wiki2 = "wiki2" + getRandomNumber();
        String subPageLevel1 = "subPageLevel1" + getRandomNumber();
        String subPageLevel2 = "subPageLevel2" + getRandomNumber();
        String subPageLevel3 = "subPageLevel3" + getRandomNumber();
        String[] subwiki2 = {subPageLevel1, subPageLevel2, subPageLevel3};

        String wiki3 = "wiki3" + getRandomNumber();
        String subPage1wiki3level1 = "subPage1wiki3level1" + getRandomNumber();
        String subPage2wiki3level1 = "subPage2wiki3level1" + getRandomNumber();
        String subPagewiki3level2 = "subPagewiki3level2" + getRandomNumber();

        String wiki4 = "wiki4" + getRandomNumber();
        String subPage1wiki4level1 = "subPage1wiki4level1" + getRandomNumber();
        String subPage1wiki4level2 = "subPage1wiki4level2" + getRandomNumber();
        String subPage1wiki4level3 = "subPage1wiki4level3" + getRandomNumber();
        String subPage1wiki4level4 = "subPage1wiki4level4" + getRandomNumber();
        String subPage1wiki4level5 = "subPage1wiki4level5" + getRandomNumber();
        String[] subwiki4 = {subPage1wiki4level1, subPage1wiki4level2, subPage1wiki4level3, subPage1wiki4level4, subPage1wiki4level5};

        String wiki5 = "wiki5" + getRandomNumber();
        String subPagewiki5Level1 = "subPagewiki5Level1" + getRandomNumber();
        String subPagewiki5Level2 = "subPagewiki5Level2" + getRandomNumber();
        String subPagewiki5Level3 = "subPagewiki5Level3" + getRandomNumber();
        String[] subwiki5 = {subPagewiki5Level1, subPagewiki5Level2, subPagewiki5Level3};

        info("Create data test");
        info("Create Space 1 with 3 wiki pages");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create wiki page 1");
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki1, wiki1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki1);

        info("Create 5 sub pages for wiki page 1");
        for (int i = 0; i < 5; i++) {
            wikiHomePage.goToAPage(wiki1);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(subwiki[i], subwiki[i]);
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(subwiki[i]);
        }

        info("Create wiki page 2");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki2, wiki2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki2);

        info("Create subpages with 3 level for wiki page 2");
        for (int i = 0; i < 3; i++) {
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(subwiki2[i], subwiki2[i]);
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(subwiki2[i]);
        }

        info("Create wiki page 3");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki3, wiki3);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki3);

        info("Create level 1 sub page 1 for wiki page 3");
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subPage1wiki3level1, subPage1wiki3level1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subPage1wiki3level1);

        info("Create level 1 sub page 2 for wiki page 3");
        wikiHomePage.goToAPage(wiki3);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subPage2wiki3level1, subPage2wiki3level1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subPage2wiki3level1);

        info("Create level 2 for wiki page 3");
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subPagewiki3level2, subPagewiki3level2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subPagewiki3level2);

        info("Create Space 2 with 2 wiki pages");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create wiki page 4");
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki4, wiki4);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki4);

        info("Create 5 sub pages for wiki page 4");
        for (int i = 0; i < 5; i++) {
            wikiHomePage.goToAPage(wiki4);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(subwiki4[i], subwiki4[i]);
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(subwiki4[i]);
        }

        info("Create wiki page 5");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki5, wiki5);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki5);

        info("Create subpages with 3 level for wiki page 5");
        for (int i = 0; i < 3; i++) {
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(subwiki5[i], subwiki5[i]);
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(subwiki5[i]);
        }
        info("Move 3 wiki pages from space 1 to space 2");
        info("Move wiki page 1 to space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAPage(wiki1);
        wikiManagement.movePageDiffDestination(wiki1, "Wiki Home", space2);

        info("Move wiki page 2 to space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAPage(wiki2);
        wikiManagement.movePageDiffDestination(wiki2, "Wiki Home", space2);

        info("Move wiki page 3 to space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAPage(wiki3);
        wikiManagement.movePageDiffDestination(wiki3, "Wiki Home", space2);


        info("Check to make sure wiki page 1 does not exist in Space 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(wiki1);
        info("Wiki page 1 does not exist in Space 1");

        info("Check to make sure wiki page 2  does not exist in Space 1");
        wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(wiki2);
        info("Wiki page 2 does not exist in Space 1");

        info("Check to make sure wiki page 3  does not exist in Space 1");
        wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(wiki3);
        info("Wiki page 3 does not exist in Space 1");

        info("Check to make sure wiki page 1 and sub pages exist in Space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space2, "");
        spaceManagement.goToSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiValidattions.verifyTitleWikiPage(wiki1);
        wikiHomePage.goToAPage(wiki1);
        for (int i = 0; i < 5; i++)
            wikiValidattions.verifyTitleWikiPage(subwiki[i]);
        info("Wiki page 1 and subpages exist in Space 2");

        info("Check to make sure wiki page 2 and sub pages exist in Space 2");
        wikiHomePage.goToHomeWikiPage();
        wikiValidattions.verifyTitleWikiPage(wiki2);
        wikiHomePage.goToAPage(wiki2);
        for (int i = 0; i < 3; i++) {
            wikiValidattions.verifyTitleWikiPage(subwiki2[i]);
            wikiHomePage.goToAPage(subwiki2[i]);
        }
        info("Wiki page 2 and sub pages exist in Space 2");

        info("Check to make sure wiki page 3 and sub pages exist in Space 2");
        wikiHomePage.goToHomeWikiPage();
        wikiValidattions.verifyTitleWikiPage(wiki3);
        wikiHomePage.goToAPage(wiki3);
        wikiValidattions.verifyTitleWikiPage(subPage1wiki3level1);
        wikiValidattions.verifyTitleWikiPage(subPage2wiki3level1);
        wikiHomePage.goToAPage(subPage2wiki3level1);
        wikiValidattions.verifyTitleWikiPage(subPagewiki3level2);
        info("Wiki page 2 ans sub pages exist in Space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.searchSpace(space2, "");
        spaceManagement.deleteSpace(space2, false);

    }

    /**
     * <li> Case ID:139580.</li>
     * <li> Test Case Name: Space names displayed in location labels should be user friendly and not technical.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination 2"
     * Wiki of "Space Move" has:
     * - Page B</li>
     * <li> Post-Condition: </li>
     * <p>
     * Step Number: 1
     * Step Name: Step 1
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page to move"- Go to "Space Move" using left side bar navigation
     * - Open wiki application
     * - Open "Page B"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page B" is displayed
     * - Space navigation of "Space Move" is displayed
     * <p>
     * Step Number: 2
     * Step Name: Step 2
     * Step Description:
     * - Open "More" Menu
     * - Select "Move"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move the page is displayed
     * - Current location label is displaying : Space Move > Page B
     * <p>
     * Step Number: 3
     * Step Name: Step 3
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination 2"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination 2" tree
     * <p>
     * Step Number: 4
     * Step Name: Step 4
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying : Space Destination 2 > Wiki Home
     */
    @Test
    public void test04_SpaceNamesDisplayedInLocationLabelsShouldBeUserFriendlyAndNotTechnical() {
        info("Test 4: Space names displayed in location labels should be user friendly and not technical");

        String space1 = "space1" + getRandomNumber();
        String wiki1 = "wiki1" + getRandomNumber();

        String space2 = "space2" + getRandomNumber();

        info("Create data test");
        info("Create Space 1 with 1 wiki page");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create wiki page 1");
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki1, wiki1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki1);

        info("Create Space 2 with no wiki page");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);
        info("Move wiki page from space 1 to space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceHomePage.goToSpace(space1);
        spaceHomePage.goToWikiTab();

        info("Move wiki page 1 to space 2");
        wikiHomePage.goToAPage(wiki1);
        wikiManagement.movePageDiffDestination(wiki1, "Wiki Home", space2, true);

        info("Check to make sure wiki page 1 does not exist in Space 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.goToSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(wiki1);
        info("Wiki page 1 does not exist in Space 1");

        info("Check to make sure wiki page 1 exist in Space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space2, "");
        spaceManagement.goToSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiValidattions.verifyTitleWikiPage(wiki1);
        info("Wiki page 1 exist in Space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.searchSpace(space2, "");
        spaceManagement.deleteSpace(space2, false);
    }

    /**
     * <li> Case ID:139429.</li>
     * <li> Test Case Name: Move a page when user does not have edit permission on destination page.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * <p>
     * Step Number: 1
     * Step Name: Step 1: Create a page
     * Step Description:
     * - Click [Add Page] --> [Blank Page]/[From Template...]
     * - Put title, content
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successfully
     * <p>
     * Step Number: 2
     * Step Name: Step 2: Set permission for page
     * Step Description:
     * - Select page above
     * - Click [More] -->  [Page Permissions] to set permissions for this page
     * that some users/groups can not edit this page
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Permissions are added to the page
     * <p>
     * Step Number: 3
     * Step Name: Step 3: Open form to move page
     * Step Description:
     * - Login by any user who does not have permission to edit page
     * - Select a page that user have edit permission
     * - Click on [More] -> [Move Page]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Form to move page appears. User can see
     * at the step 1. But when click "Move" buton, popup with
     * content "You have no edit permission at the destination
     * page" appears
     */

    @Test
    public void test05_MoveAPageWhenUserDoesNotHaveEditPermissionOnDestinationPage() {
        info("Test 5: Move a page when user does not have edit permission on destination page");

        String wiki1 = "wiki1" + getRandomNumber();
        String wiki2 = "wiki2" + getRandomNumber();

        info("Create data test");
        info("Create wiki page 1 with full permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki1, wiki1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki1);

        info("add Edit Permission for Mary in wiki page 1");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(DATA_USER2);
        wikiPermission.selectPermission(DATA_USER2, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        info("Create wiki page 2");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki2, wiki2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki2);
        info("Un check edit permission of any group");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(DATA_USER2);
        wikiPermission.unSelectPermission(DATA_USER2, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        info("Log in as Mary");
        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        homePagePlatform.goToWiki();
        info("Move wiki page 1 to page 2");
        wikiHomePage.goToAPage(wiki1);
        ELEMENT_MORE_LINK.click();
        $(ELEMENT_MOVE_PAGE).click();
        $(byXpath(ELEMENT_WIKI_PAGE_MOVE_POPUP_NODE.replace("${name}", wiki2))).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_CANCEL_BUTTON).click();
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(wiki1);

    }

    /**
     * <li> Case ID:139430.</li>
     * <li> Test Case Name: Move a page when user doesn't have edit permission on page.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * <p>
     * Step Number: 1
     * Step Name: Step 1: Create a page
     * Step Description:
     * - Click [Add Page] --> [Blank Page]/[From Template...]
     * - Put title, content
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successfully
     * Step Number: 2
     * Step Name: Step 2: Set permission for page
     * Step Description:
     * - Select page above
     * - Click [More] -->  [Page Permissions] to set permission for this page
     * that some users/groups can not edit this page
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Permissions are added to the page
     * Step Number: 3
     * Step Name: Step 3: Open form to move page
     * Step Description:
     * - Login by any user who does not have permission to edit page
     * - Select the page at step 1
     * - Click on [More]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Move Page action is not displayed
     */
    @Test
    public void test06_MoveAPageWhenUserDoesNotHaveEditPermissionOnPage() {
        info("Test 6: Move a page when user doesn't have edit permission on page");

        String wiki1 = "wiki1" + getRandomNumber();
        info("Create wiki page 1 with full permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki1, wiki1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki1);
        info("Un check edit permission of any group");
        wikiManagement.unCheckViewAUserOfPage(ELEMENT_PERMISSION_EDIT_ANY);
        info("Log in as Mary");
        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        homePagePlatform.goToWiki();

        info("Move wiki page 1 to page 2");
        wikiHomePage.goToAPage(wiki1);
        wikiManagement.movePageWhenUserDoesNotHavePerMissionInDestination(wiki1, "", false);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(wiki1);


    }

    /**
     * <li> Case ID:139431.</li>
     * <li> Test Case Name: Move a page when user have edit permission on page.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Create a page
     * Step Description:
     * - Click [Add Page] --> [Blank Page]/[From Template...]
     * - Put title, content
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successfully
     * Step Number: 2
     * Step Name: Step 2: Set permission for page
     * Step Description:
     * - Select page above
     * - Click [More] -->  [Page Permissions] to set permission for this page
     * that any user/group can edit this page
     * Input Data:
     * <p>
     * Expected Outcome:
     * Page is added permission
     * Step Number: 3
     * Step Name: Step 3: Open form to move page
     * Step Description:
     * - Login by any user
     * - Select page at step 1
     * - Click on [More] --> [Move Page]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Form to move page appears
     * <p>
     * Step Number: 4
     * Step Name: Step 4: Move page
     * Step Description:
     * - Select the destination page
     * - Click on Move
     * Input Data:
     * <p>
     * Expected Outcome:
     * New page is moved to the destination page
     */
    @Test
    public void test07_MoveAPageWhenUserHasEditPermissionOnPage() {
        info("Test 7: Move a page when user have edit permission on page");

        String wiki1 = "wiki1" + getRandomNumber();
        String wiki2 = "wiki2" + getRandomNumber();
        info("Create wiki page 1 with full permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki1, wiki1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki1);

        info("add Edit Permission for Mary in wiki page 1");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(DATA_USER2);
        wikiPermission.selectPermission(DATA_USER2, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();

        info("Create wiki page 2 with full permission");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki2, wiki2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki2);

        info("add Edit Permission for Mary in wiki page 2");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(DATA_USER2);
        wikiPermission.selectPermission(DATA_USER2, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        info("Log in as Mary");
        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        homePagePlatform.goToWiki();

        info("Move wiki page 1 to page 2");
        wikiHomePage.goToAPage(wiki1);
        wikiManagement.movePage(wiki1, wiki2);

        info("Check to make sure wiki page 1 does not exist in wiki home");
        wikiHomePage.goToHomeWikiPage();
        wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(wiki1);
        info("Check to make sure wiki page 1 exists under wiki page 2");
        wikiHomePage.goToAPage(wiki2);
        wikiValidattions.verifyTitleWikiPage(wiki1);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(wiki2);

    }

    /**
     * <li> Case ID:139564.</li>
     * <li> Test Case Name: Check Control: complex sub-pages tree with name duplicated in the target space..</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page D
     * --- Sub-Page level 1
     * ------ Sub-Page level 2
     * -------- Sub-Page level 3
     * <p>
     * Wiki of "Space Destination" has:
     * - The complex page
     * --- Sub-Page level 1
     * ------ Sub-Page level 2
     * -------- Sub-Page level 3</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" using left side bar navigation
     * - Open wiki application
     * - Open "Page A"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page A" is displayed
     * - Space navigation of "Space Move" is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Open "More" Menu
     * - Select "Move Page"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move the page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination 2"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination 2" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination 2 > Wiki Home
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page A" is moved in the space "Space Destination"
     * - "Page A" is automatically displayed in "Space Destination 2" :
     * - Breadcrumb is displaying :Space Destination 2 > Wiki Home > Page A
     * - Space Navigation of "Space Destination 2" is displayed
     */
    @Test
    public void test08_CheckControlComplexSubpagesTreeWithNameDuplicatedInTheTargetSpace() {
        info("Test 15 Check Redirection after a move to another space");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);


        info("Create Page on the space");
        String page = "page" + getRandomNumber();
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, page);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);

        info("Create a space 2: destination space");
        String space2 = "Space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);


        info("Create pages and sub-pages for space 2");
        String page1 = "page1" + getRandomNumber();
        String subPage1 = "subPage1" + getRandomNumber();
        String subPage2 = "subPage2" + getRandomNumber();
        String image1 = "eXo-Platform.png";
        String image2 = "testavatar.png";
        String file1 = "wiki_attachment.txt";
        String file2 = "AnswerCategorie.zip";
        info("Create page");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1, "");
        info("Insert image 1");
        richTextEditor.goToAttachedImageLink();
        richTextEditor.insertImage(image1, true);
        wikiManagement.saveAddPage();
        info("Page is add/edited successfully");
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Insert image 2");
        wikiHomePage.goToAPage(page1);
        wikiHomePage.goToEditPage();
        richTextEditor.goToAttachedImageLink();
        richTextEditor.insertImage(image2, true);
        wikiManagement.saveAddPage();
        info("Page is add/edited successfully");
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create sub page1");
        wikiHomePage.goToAPage(page1);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subPage1, "");
        info("Insert file 1");
        richTextEditor.insertAttachedFileLink(file1, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(file1, "");
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subPage1);

        info("Insert file 2");
        wikiHomePage.goToAPage(subPage1);
        wikiHomePage.goToEditPage();
        richTextEditor.insertAttachedFileLink(file2, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(file2, "");
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subPage1);


        info("Create sub page2");
        wikiHomePage.goToAPage(page1);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subPage2, "");
        info("Insert image 1");
        richTextEditor.goToAttachedImageLink();
        richTextEditor.insertImage(image1, true);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subPage2);

        info("Insert image 2");
        wikiHomePage.goToAPage(subPage2);
        wikiHomePage.goToEditPage();
        richTextEditor.goToAttachedImageLink();
        richTextEditor.insertImage(image2, true);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subPage2);
        info("Move page of space 1 to Wiki Home of space 2");
        homePagePlatform.goToMySpaces();
        ELEMENT_SPACES_LIST.find(byText(space1)).click();
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(page, "Wiki Home", space2);


        info("[Page] is moved in space2");
        wikiValidattions.verifyTitleWikiPage(page);
        info("Breadcrumb is displaying :Space2 > Wiki Home >" + page);
        wikiValidattions.verifyBreadCrumbePath(space2, "Wiki Home", page);
        info("Space Navigation is not displayed anymore");
        $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space1, "");
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.searchSpace(space2, "");
        spaceManagement.deleteSpace(space2, false);

    }
}
