package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_EMAIL_LINK_EMAIL_ADDRESS;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_WEB_PAGE_WEB_ADDRESS;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 5/18/18.
 */
@Tag("wiki")
@Tag("functional")

public class WikiBasicActionDeleteTestIT extends Base {

    HomePagePlatform homePagePlatform;
    WikiHomePage wikiHomePage;
    WikiManagement wikiManagement;
    SourceTextEditor sourceTextEditor;
    ManageLogInOut manageLogInOut;
    WikiValidattions wikiValidattions;
    WikiDraftPage wikiDraftPage;
    RichTextEditor richTextEditor;


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
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    /**
     * <li> Case ID:139425.</li>
     * <li> Test Case Name: Delete page when agree on confirm message.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Create a page
     * Step Description:
     * - Click [Add Page]
     * -
     * -> [Blank Page]/[From Template...]
     * - Select [Source Editor] to switch to [Source Editor] mode
     * - Put title, content
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful.
     * Step number: 2
     * Step Name: Step 2: Delete page when OK on confirm message
     * Step Description:
     * - Select page above
     * - Click [More]
     * -
     * -> [Delete Page]
     * - Click OK on confirm message
     * Input Data:
     * <p>
     * Expected Outcome:
     * Page is deleted successfully
     */

    @Test
    public void test01_DeletePageWhenAgreeOnConfirmMessage() {
        info("Test 1: Delete page when agree on confirm message");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Page is deleted successfully");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139426.</li>
     * <li> Test Case Name: Delete page when cancel on confirm message.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Create a page
     * Step Description:
     * - Click [Add Page]
     * -
     * -> [Blank Page]/[From Template...]
     * - Select [Source Editor] to switch to [Source Editor] mode
     * - Put title, content
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful.
     * <p>
     * Step number: 2
     * Step Name: Step 2: Delete page when cancel on confirm message
     * Step Description:
     * - Select page above
     * - Click [More]
     * -
     * -> [Delete Page]
     * - Click [Cancel] on confirm message
     * Input Data:
     * <p>
     * Expected Outcome:
     * Page is not deleted
     */
    @Test
    public void test02_DeletePageWhenCancelOnConfirmMessage() {
        info("Test 2: Delete page when cancel on confirm message");

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Page isNOT deleted");
        homePagePlatform.goToWiki();
        wikiHomePage.cancelDeleteWiki(title);

    }

    /**
     * <li> Case ID:139441.</li>
     * <li> Test Case Name: Delete draft by the cancel of the page.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1. Verify the manager draft
     * Step Description:
     * - Select a wiki page
     * - Click [Edit]
     * - Make changes on the wiki page without saving for 30s
     * Input Data:
     * <p>
     * Expected Outcome:
     * The draft is saved after 30s
     * Step number: 2
     * Step Name: Step 2. Cancel a page in edit mode
     * Step Description:
     * - Click on the link [Cancel]
     * Input Data:
     * <p>
     * Expected Outcome:
     * A pop up appears "The draft was created. Do you want to keep it?"
     * <p>
     * <p>
     * Step number: 3
     * Step Name: Step 3. Cancel a page in edit mode
     * Step Description:
     * - Click on the button [No] in the confirmation message
     * Input Data:
     * <p>
     * Expected Outcome:
     * The Draft is cancelled The Wiki Home page is displayed
     * Step number: 4
     * Step Name: Step 4. Verify the manager draft
     * Step Description:
     * - Click [Browse]
     * -
     * -> [My Drafts]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The draft is deleted from the list of draft
     */
    @Test
    public void test03_DeleteDraftByTheCancelOfThePage() {
        info("Test 3: Delete draft by the cancel of the page");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Edit the page");
        String newTitle = "newTitle" + getRandomNumber();
        String newContent = "newContent" + getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.editSimplePageWithAutoSave(newTitle, newContent);
        info("The draft was created. Do you want to keep it?");
        String mess = "";
        wikiManagement.cancelAddPage();
        wikiValidattions.verifyWarningMessage(mess);
        wikiHomePage.confirmWaringMessage(true);
        info("The draft is deleted from the list of draft");
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyNotTitleDrafPage(newTitle);
        wikiValidattions.verifyNotTitleDrafPage(title);

    }

    /**
     * <li> Case ID:139442.</li>
     * <li> Test Case Name: Delete draft from the menu "My Draft".</li>
     * <li> Pre-Condition: Draft exist on the list "My Draft"</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Show My Draft list
     * Step Description:
     * From the menu [Browse], choose[My Drafts]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The list of drafts is displayed
     * Step number: 2
     * Step Name: Step 2: Delete the draft
     * Step Description:
     * - Select a draft page
     * - From the column [Action], click on the icon [Trash]
     * Input Data:
     * <p>
     * Expected Outcome:
     * A pop up is displayed "Are you sure you want to delete this draft?"
     * Step number: 3
     * Step Name: Step 3: Click OK to confirm
     * Step Description:
     * Click on the button [OK]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The Draft is removed from the list
     */
    @Test
    @Tag("wabis")
    public void test04_DeleteDraftFromTheMenuMyDraft() {
        info("Test 4: Delete draft from the menu My Draft");
        info("Create a draf wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
        info("The draft is shown from the list of draft");
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyTitleDrafPage(title);
        info("A pop up is displayed 'Are you sure you want to delete this draft?'");
        wikiDraftPage.deleteDraft();

    }

    /**
     * <li> Case ID:139443.</li>
     * <li> Test Case Name: Delete the draft by saving the page.</li>
     * <li> Pre-Condition: edit page already saved as draft</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Edit a draft page by Saving
     * Step Description:
     * - Go to [Browse]
     * -
     * -> [My Draft]
     * - Select a draft page
     * - Click a draft to edit it
     * - Click on the button [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The page is saved
     * - The draft version become the published version
     * Step number: 2
     * Step Name: Step 2: Verification on the draft manager
     * Step Description:
     * From the menu [Browse], choose [My Drafts]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page title isn't displayed on the list of drafts
     */
    @Test
    public void test05_DeleteTheDraftBySavingThePage() {
        info("Test 5: Delete the draft by saving the page");
        info("Create a draf wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);

        info("The draft is shown from the list of draft");
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyTitleDrafPage(title);
        wikiDraftPage.resumeADraft(title);
        wikiManagement.saveAddPage();
        info("The page is saved");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The draft version become the published version");
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyNotTitleDrafPage(title);
    }

    /**
     * <li> Case ID:139456.</li>
     * <li> Test Case Name: View a draft for another user.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add a draft page for user A
     * Step Description:
     * - Connect with the user A
     * - Go to [Intranet]
     * -
     * -> [Wiki]
     * - Click [Add Page]
     * -
     * -> [Blank Page]/[From Template...]
     * - Input values for the page without saving for 30s
     * Input Data:
     * <p>
     * Expected Outcome:
     * The draft is saved after 30s
     * <p>
     * Step number: 2
     * Step Name: Step 2: View Draft page by user B
     * Step Description:
     * - Connect with the user B
     * - Go to [Intranet]
     * -
     * -> [Wiki]
     * - From the menu [Browse], choose [My Drafts]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The draft created by the user A doesn't appear in the list of drafts of the user B
     */
    @Test
    @Tag("wabis")
    public void test06_ViewADraftForAnotherUser() {
        info("Test 6: View a draft for another user");

        info("Create a draf wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
        manageLogInOut.signIn(DATA_USER2, "gtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyNotTitleDrafPage(title);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToMyDraft();
        wikiDraftPage.deleteDraft();


    }

    /**
     * <li> Case ID:139539.</li>
     * <li> Test Case Name: Delete image.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Remove image
     * Step Description:
     * - Add new page or edit a page
     * - Ensure the page is in the [Rich Text] editor
     * - Click an image in page
     * - Click [Image] in menu and select [Remove Image]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
     * - If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
     * - Image is removed successfully
     */
    @Test
    public void test07_DeleteImage() {
        info("Test 7: Delete image");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String attachedFile = "eXo-Platform.png";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, "");
        richTextEditor.goToAttachedImageLink();
        richTextEditor.insertImage(attachedFile, false);
        richTextEditor.removeImage(attachedFile);

    }

    /**
     * <li> Case ID:139540.</li>
     * <li> Test Case Name: Remove link of an attached file.</li>
     * <li> Pre-Condition: - A wiki page is created
     * - Add attachment(s) to the page</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Remove attach file
     * Step Description:
     * - Select a page which has an attached file
     * - Click [Edit]
     * - Ensure the page is in the [Rich Text] editor
     * - Select the attached file
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Add a link for one of the attachments
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Go to [Link] in menu
     * - Select [Remove Link]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The link is removed successfully in thein page.
     * - The title of Link still exists.
     */
    @Test
    public void test08_RemoveLinkOfAnAttachedFile() {
        info("Test 8: Remove link of an attached file");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String attachedFile = "wiki_attachment.txt";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, "");
        richTextEditor.insertAttachedFileLink(attachedFile, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(attachedFile, "");
        richTextEditor.removeLink(attachedFile);

    }

    /**
     * <li> Case ID:139541.</li>
     * <li> Test Case Name: Remove link of an email address.</li>
     * <li> Pre-Condition: - A wiki page is created
     * - The wiki page contains link to email address already</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Remove Email Address
     * Step Description:
     * - Select a page have email address
     * - Click [Edit]
     * - Ensure the page is in the [Rich Text]editor
     * - Select email address link in content
     * -Click Link in menu and select [Remove Link]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
     * - If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
     * - Link is removed successfully in page and the alternative text of Email Address still exists.
     */
    @Test
    public void test09_RemoveLinkOfAnEmailAddress() {
        info("Test 9: Remove link of an email address");
        info("Create a wiki page 2");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        String tooltip = "tooltip" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String address = "test" + "@gmail.com";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        richTextEditor.insertEmailLink(address, label, tooltip, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
        richTextEditor.removeLink(label);
    }

    /**
     * <li> Case ID:139542.</li>
     * <li> Test Case Name: Remove link of web page.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Remove link of web page
     * Step Description:
     * - Select a page have web page
     * - Click [Edit]
     * - Ensure the page is in the [Rich Text]editor
     * - Choose a web page link
     * - Click [Link] in menu
     * - Choose [Remove Link]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
     * - If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
     * - Web page link is removed successfully and title of web page still exists.
     */
    @Test
    public void test10_RemoveLinkOfWebPage() {
        info("Test 10 Remove link of web page");
        info("Create a wiki page 2");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String tooltip = "tooltip" + getRandomNumber();
        String address = "www.google.com";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        richTextEditor.goToWebPageLink();
        $(ELEMENT_WEB_PAGE_WEB_ADDRESS).waitUntil(Condition.visible,2000).val(address);
        sleep(2000);
        info("Input Label for the page");
        richTextEditor.inputLabel(label);
        sleep(2000);
        info("Input Tooltip for the page");
        richTextEditor.inputToolTip(tooltip);
        sleep(2000);
        info("Click on Create link button");
        richTextEditor.goToCreateLink();
        wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
        richTextEditor.removeLink(label);
    }

    /**
     * <li> Case ID:139543.</li>
     * <li> Test Case Name: Remove wiki page link.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Remove wiki page link
     * Step Description:
     * - Select a page have wiki page link
     * - Click [Edit]
     * - Ensure the page is in the [Rich Text]editor
     * - Select the wiki page link in content
     * -Click [Link] in menu and choose [Remove Link]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Link is removed successfully and title of wiki page still exists
     */
    @Test
    public void test11_RemoveWikiPageLink() {
        info("Test 11 Remove wiki page link");

        info("Create a wiki page 2");
        String title1 = "title1" + getRandomNumber();
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String tooltip = "tooltip" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title2, content2);
        richTextEditor.insertNewWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.My_Recent_Changes, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
        richTextEditor.removeLink(label);
        homePagePlatform.goToWiki();
    }


}
