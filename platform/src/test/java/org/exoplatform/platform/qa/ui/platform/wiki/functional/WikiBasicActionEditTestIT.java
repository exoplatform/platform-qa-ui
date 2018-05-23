package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_CLOSE_PREVIEW_WINDOW;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMETN_WIKI_STATUS_VERSION_TEXT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 5/21/18.
 */

@Tag("functional")
@Tag("wiki")
public class WikiBasicActionEditTestIT  extends Base {


    HomePagePlatform homePagePlatform;
    WikiHomePage wikiHomePage;
    WikiManagement wikiManagement;
    SourceTextEditor sourceTextEditor;
    WikiValidattions wikiValidattions;
    ManageLogInOut manageLogInOut;
    NavigationToolbar navigationToolbar;
    UserAndGroupManagement userAndGroupManagement;
    AddUsers addUsers;
    RichTextEditor richTextEditor;
    WikiDraftPage wikiDraftPage;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        manageLogInOut = new ManageLogInOut(this);
        wikiManagement = new WikiManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        wikiValidattions = new WikiValidattions(this);
        sourceTextEditor = new SourceTextEditor(this);
        navigationToolbar = new NavigationToolbar(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        richTextEditor = new RichTextEditor(this);
        wikiDraftPage = new WikiDraftPage(this);
        addUsers = new AddUsers(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    @Test
    public void test01_EditPage() {
        info("Test 1: Edit page");
		/*Step Number: 1
		*Step Name: Step 1: Create a page
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Select [Source Editor] to switch to [Source Editor] mode
			- Put title, content
			- Click [Save]
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			-New page is created successfully*/
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
		/*Step number: 2
		*Step Name: Step 2: Edit page
		*Step Description:
			- Select the page above
			- Click [Edit]
			- Change properties
			- Click [Save]
		*Input Data:

		*Expected Outcome:
			- The [Edit Page] is shown in [Source Editor] mode
			- Page is edited*/
        info("Edit a wiki page");
        String newTitle = "newTitle" + getRandomNumber();
        String newContent = "newContent" + getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.editSimplePage("test", newContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage("test");
    }

    /**
     * <li> Case ID:139428.</li>
     * <li> Test Case Name: Edit page when the title is the same with existing page.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test02_EditPageWhenTheTitleIsTheSameWithExistingPage() {
        info("Test 2: Edit page when the title is the same with existing page");
		/*Step Number: 1
		*Step Name: Step 1: Create a page
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Select [Source Editor] to switch to [Source Editor] mode
			- Put title, content
			- Click [Save]
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
			- If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
			- New page is created successfully*/
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);

		/*Step number: 2
		*Step Name: Step 2: Edit page
		*Step Description:
			- Select page above
			- Click [Edit]
			- Change the title of this page that is the same with an existing page
			- Click [Save]
		*Input Data:

		*Expected Outcome:
			- The [Edit Page] is shown in [Source Editor] mode
             - Page is edited*/

        info("Edit a wiki page");
        String newTitle = "newTitle" + getRandomNumber();
        String newContent = "newContent" + getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        sourceTextEditor.editSimplePage(newTitle, newContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(newTitle);

    }


    /**
     *<li> Case ID:139445.</li>
     *<li> Test Case Name: Edit a page with an existing draft.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test04_02_EditAPageWithAnExistingDraft() {
        info("Test 4: Edit a page with an existing draft");
		/*Step Number: 1
		*Step Name: Step 1: Add new page
		*Step Description:
			- Add a wiki page and save
			- Edit the page
			- Input a content
		*Input Data:

		*Expected Outcome:
			The draft is saved after 30s*/
        info("Create a wiki page");
        String title = "title"+getRandomNumber();
        String content = "content"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePageWithAutoSaveStatus(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);

        info("Edit the page");
        String newTitle = "newTitle"+getRandomNumber();
        String newContent = "newContent"+getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        richTextEditor.editSimplePageWithAutoSave(newTitle, newContent);
        wikiManagement.cancelAddPage();
        wikiHomePage.confirmWaringMessage(true);

		/*Step number: 2
		*Step Name: Step 2: Edit page
		*Step Description:
			- From the "Wiki Home", Open again the page
			- Edit the page
		*Input Data:

		*Expected Outcome:
			A warning message is displayed under the title:
			 "A draft of this page was saved on Month Day, Year HH:MM.
			 You can view your changes and decide to resume the draft or delete it.*/
        info("Edit the page again");
        String message = "message"+getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();

        info("A warning message is displayed under the title:");
        $(byText("View your Changes")).waitUntil(visible,Configuration.timeout);
        $(byText("Resume the Draft")).waitUntil(visible,Configuration.timeout);

		/*Step number: 5
		*Step Name: Step 5: Delete draft
		*Step Description:
			- Repeat the step 3
			- Click on the link [Delete]
		*Input Data:

		*Expected Outcome:
			The draft is deleted.*/
        wikiManagement.goToDeleteLinkOnStatus();
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyNotTitleDrafPage(title);

		/*Step number: 6
		*Step Name: Step 6: Check after delete draft
		*Step Description:
			Click open again the page and click edit
		*Input Data:

		*Expected Outcome:
			The warning message isn't displayed*/
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        info("The warning message isn't displayed");
        $(byText("View your Changes")).waitUntil(Condition.not(visible),Configuration.timeout);
        $(byText("Resume the Draf")).waitUntil(Condition.not(visible),Configuration.timeout);
        wikiHomePage.deleteWiki(title);

    }

    /**
     *<li> Case ID:139445.</li>
     *<li> Test Case Name: Edit a page with an existing draft.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test04_00_EditAPageWithAnExistingDraft() {
        info("Test 4: Edit a page with an existing draft");
		/*Step Number: 1
		*Step Name: Step 1: Add new page
		*Step Description:
			- Add a wiki page and save
			- Edit the page
			- Input a content
		*Input Data:

		*Expected Outcome:
			The draft is saved after 30s*/
        info("Create a wiki page");
        String title = "title"+getRandomNumber();
        String content = "content"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePageWithAutoSaveStatus(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);

        info("Edit the page");
        String newTitle = "newTitle"+getRandomNumber();
        String newContent = "newContent"+getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        richTextEditor.editSimplePageWithAutoSave(newTitle, newContent);
        wikiManagement.cancelAddPage();
        wikiHomePage.confirmWaringMessage(true);

		/*Step number: 2
		*Step Name: Step 2: Edit page
		*Step Description:
			- From the "Wiki Home", Open again the page
			- Edit the page
		*Input Data:

		*Expected Outcome:
			A warning message is displayed under the title:
			 "A draft of this page was saved on Month Day, Year HH:MM.
			 You can view your changes and decide to resume the draft or delete it.*/
        info("Edit the page again");
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();

        info("A warning message is displayed under the title:");
        info("A draft of this page was saved on Month Day, Year HH:MM. "
                + "You can view your changes and decide to resume the draft or delete it.");

        $(byText("View your Changes")).waitUntil(visible,Configuration.timeout);
        $(byText("Resume the Draft")).waitUntil(visible,Configuration.timeout);
		/*Step number: 3
		*Step Name: Step 3: Check view changes
		*Step Description:
			From the message click on the link [View your changes]
		*Input Data:

		*Expected Outcome:
			The page "Draft changes" is displayed*/
        info("The page 'Draft changes' is displayed");
        wikiManagement.goToViewChangesLinkOnStatus();
        $(byText(title)).shouldBe(visible);
        $(byText(newContent)).shouldBe(visible);
        $(byXpath("//*[@id=\"UIWikiMaskWorkspace\"]/div[1]/a/i")).click();


    }


    /**
     *<li> Case ID:139449.</li>
     *<li> Test Case Name: Resume a draft after Browser crash.</li>
     *<li> Pre-Condition: Add/Edit a page</li>
     *<li> Post-Condition: </li>
     * @throws Exception
     */
    @Test
    public  void test05_ResumeADraftAfterBrowserCrash() throws Exception {
        info("Test 5: Resume a draft after Browser crash");
		/*Step Number: 1
		*Step Name: Step 1: Edit page
		*Step Description:
			Edit a wiki page and add a content
		*Input Data:

		*Expected Outcome:
			The draft is added after 30s*/
        info("Create a wiki page");
        String title = "title"+getRandomNumber();
        String content = "content"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);

        info("Edit the page");
        String title1 = "title1"+getRandomNumber();
        String content1 = "content1"+getRandomNumber();
        wikiHomePage.goToEditPage();
        richTextEditor.editSimplePageWithAutoSave(title1, content1);
        wikiManagement.cancelAddPage();
        wikiHomePage.confirmWaringMessage(true);

		/*Step number: 2
		*Step Name: Step 2: Autosave page after Browser crash
		*Step Description:
			Force stop the Browser process from the task manager
		*Input Data:

		*Expected Outcome:
			The Browser is stopped*/
        info("The Browser is stopped");
        Selenide.close();
        open(Configuration.baseUrl);
        manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");
		/*Step number: 3
		*Step Name: Step 3: Page saved as draft
		*Step Description:
			Open the page againGo to "My drafts"
		*Input Data:

		*Expected Outcome:
			The draft version appears in the list*/
        info("The draft version appears in the list");
        homePagePlatform.goToWiki();
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyTitleDrafPage(title1);
    }


    /**
     *<li> Case ID:139534.</li>
     *<li> Test Case Name: Add web page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test09_AddWebPage() {
        info("Test 9: Add web page");
		/*Step Number: 1
		*Step Name: Step 1: Edit a web page
		*Step Description:
			- Select a page that has web page link
			- Ensure the page isin [Rich Text] editor
			- Choose a web page link
			- Click Link in menu
			- Choose [Edit link]
			- Type orther address of the web page to create the link to. (ex: www.google.com)
			- Input label and tooltip
			- Check or uncheck [Open in new window]
			- Click [Create Link]
			- Click [Save]
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
			- If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
			- Web page link is changed succesfully*/
        info("Create a wiki page 1");
        String title = "title"+getRandomNumber();
        String label = "label"+getRandomNumber();
        String address = "www.google.com";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title,"");
        richTextEditor.goToWebPageLink();
        richTextEditor.insertWebLink(address, label,"",true);
        wikiValidattions.verifyInsertedLinkIntoFrame(label,"");
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);

        info("Edit the page");
        String title1 = "title1"+getRandomNumber();
        String label1 = "label1"+getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        richTextEditor.editSimplePage(title1,"");
        richTextEditor.changeLink(label);
        richTextEditor.goToEditLink();
        richTextEditor.insertWebLink(address,label1,"",true);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);
    }

}