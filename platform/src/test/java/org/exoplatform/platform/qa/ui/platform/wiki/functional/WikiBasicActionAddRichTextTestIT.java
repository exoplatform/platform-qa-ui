package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 5/14/18.
 */

@Tag("functional")
@Tag("wiki")

public class WikiBasicActionAddRichTextTestIT extends Base {
    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    WikiManagement wikiManagement;

    RichTextEditor richTextEditor;

    WikiValidattions wikiValidattions;

    ManageLogInOut manageLogInOut;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        richTextEditor = new RichTextEditor(this);
        wikiValidattions = new WikiValidattions(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");

    }

    /**
     * <li>Case ID:139522.</li>
     * <li>Test Case Name: Add a page with link wiki page existed.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add a page with
     * link wiki page Step Description: - Go to [Intranet] - -> [Wiki] - Click [Add
     * Page] - -> [Blank Page] - Ensure the page is in the [Rich Text]editor - Input
     * data valid for Title page and Page's content - Click [Link] in menu - Select
     * [Wiki Page] - Select [All pages] tab - Choose a page in list and click
     * [Select] - Input label and tooltip for link - Check or uncheck [Open in new
     * window] - Click [Create Link] - Click [Save] icon in toolbar Input Data:
     * Expected Outcome: - By default, the [Create Wiki page] is displayed in the
     * [Rich Text] mode - A new page is added successful and displayed with
     * properties - This page is listed with page containing the link Step number: 2
     * Step Name: Step 2: View link after add Step Description: - Click on name of
     * link Input Data: Expected Outcome: - Page is shown successfully
     */
    @Test
    public void test01_AddAPageWithLinkWikiPageExisted() {
        info("Test 1: Add a page with link wiki page existed");
        info("Create a wiki page 1");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);
        info("Create a wiki page 2");
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String tooltip = "tooltip" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title2, content2);
        richTextEditor.goToWikiPageLink();
        richTextEditor.insertExistWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.All_pages);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title2);
        info("Page is shown successfully");
        wikiHomePage.goToAPage(title2);
        wikiValidattions.verifyInsertedExistLink(label, title1);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title1);
        wikiHomePage.deleteWiki(title2);
    }

    /**
     * <li>Case ID:139523.</li>
     * <li>Test Case Name: Add a page with add new link.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add a page with
     * link wiki page Step Description: - Go to [Intranet] - -> [Wiki] - Click [Add
     * Page] - -> [Blank Page] - Input data valid for Title page and Page's content
     * -Click [Link] in menu - Select a wiki page - Select [My recent changes] -
     * Double click an item toand type the name of the page to be created. - Click
     * [Link Setting] - Input label and tooltip for link - Check or uncheck [Open in
     * new window] - Click Create Link - Click on Save icon in toolbar Input Data:
     * Expected Outcome: - By default, the [Create Wiki page] is displayed in the
     * [Rich Text] mode - A new page is added successful and displayed with
     * properties - This page is listed with page containing the link Step number: 2
     * Step Name: Step 2: View link after add Step Description: - Click on name of
     * link Input Data: Expected Outcome: - Show new page with name created above
     * and user can add content for it
     */
    @Test
    public void test02_AddAPageWithAddNewLink() {
        info("Test 2: Add a page with add new link");
        info("Create a wiki page 2");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String tooltip = "tooltip" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title2, content2);
        richTextEditor.insertNewWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.My_Recent_Changes, true);
        wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title2);
        info("Show new page with name created above");
        wikiHomePage.goToAPage(title2);
        $(byText(label)).click();
        info("user can add content for it");
        wikiManagement.addContentPage(content1);
        wikiManagement.saveAddPage();
        info("Verify that the content is added successfully");
        wikiValidattions.verifyContentPage(content1);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title2);
    }

    /**
     * <li>Case ID:139524.</li>
     * <li>Test Case Name: Search page to add link.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add a page with
     * link wiki page Step Description: - Go to [Intranet] - -> [Wiki] - Click [Add
     * Page] - -> [Blank Page] - Ensure the page is in the [Rich Text] editor -
     * Input data valid for Title page and Page's content - Click [Link] in menu -
     * Select [Wiki Page] - Select [Search] tab - Type page name which want to
     * search and click [Search] - Choose page which show in list and click Select -
     * Input label and tooltip for link - Check or uncheck [Open in new window] -
     * Click Create Link - Click on Save icon in toolbar Input Data: Expected
     * Outcome: - By default, the [Create Wiki page] is displayed in the [Rich Text]
     * mode - A new page is added successfully and displayed with properties - This
     * page is listed with page containing the link Step number: 2 Step Name: Step
     * 2: View link after add Step Description: - Click on name of link Input Data:
     * Expected Outcome: - Show new page successfully
     */
    @Test
    public void test03_SearchPageToAddLink() {
        info("Test 3: Search page to add link");
        info("Create a wiki page 1");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);

        info("Create a wiki page 2");
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String tooltip = "tooltip" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title2, content2);
        richTextEditor.goToWikiPageLink();
        richTextEditor.insertExistWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.Search);
        wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title2);
        info("Page is shown successfully");
        wikiHomePage.goToAPage(title2);
        wikiValidattions.verifyInsertedExistLink(label, title1);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title2);

    }

}
