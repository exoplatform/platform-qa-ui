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
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
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
    public void test03_DeleteDraftByTheCancelOfThePageThenBySavingThePage() {
        info("Delete draft by the cancel of the page");
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
        info("Delete the draft by saving the page");
        info("Create a draf wiki page");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title1, content1);
        info("The draft is shown from the list of draft");
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyTitleDrafPage(title1);
        wikiDraftPage.resumeADraft(title1);
        wikiManagement.saveAddPage();
        info("The page is saved");
        wikiValidattions.verifyTitleWikiPage(title1);
        info("The draft version become the published version");
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyNotTitleDrafPage(title1);
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title2, content2);
        info("View the Draft by another User");
        manageLogInOut.signIn(DATA_USER2, "gtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyNotTitleDrafPage(title2);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        homePagePlatform.goToWiki();
        info("The draft is shown from the list of draft");
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyTitleDrafPage(title2);
        info("A pop up is displayed 'Are you sure you want to delete this draft?'");
        wikiDraftPage.deleteDraft();
        info("Delete page when cancel on confirm message");
        info("Page isNOT deleted");
        homePagePlatform.goToWiki();
        wikiHomePage.cancelDeleteWiki(title);
        homePagePlatform.goToWiki();
        wikiValidattions.verifyTitleWikiPage(title);
        wikiHomePage.deleteWiki(title);

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
    public void test07_DeleteImageThenRemoveLinkOfAnAttachedFileThenRemoveLinkOfAnEmailAddressThenRemoveLinkOfWebPageThenRemoveWikiPageLink() {
        info("Delete image");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String attachedFilePicture = "eXo-Platform.png";
        String attachedFileTxt = "wiki_attachment.txt";
        String tooltip = "tooltip" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String address = "test" + "@gmail.com";
        String label1 = "label" + getRandomNumber();
        String tooltip1 = "tooltip" + getRandomNumber();
        String address1 = "www.google.com";
        String title1 = "title1" + getRandomNumber();
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        String label2 = "label" + getRandomNumber();
        String tooltip2 = "tooltip" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, "");
        richTextEditor.goToAttachedImageLink();
        richTextEditor.insertImage(attachedFilePicture, false);
        richTextEditor.removeImage(attachedFilePicture);
        info("Remove link of an attached file");
        getExoWebDriver().getWebDriver().navigate().refresh();
        richTextEditor.insertAttachedFileLink(attachedFileTxt, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(attachedFileTxt, "");
        richTextEditor.removeLink(attachedFileTxt);
        info("Remove link of an email address");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title2, content2);
        richTextEditor.insertEmailLink(address, label, tooltip, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
        richTextEditor.removeLink(label);
        info("Remove link of web page");
        getExoWebDriver().getWebDriver().navigate().refresh();
        richTextEditor.goToWebPageLink();
        richTextEditor.insertWebLink(address1, label1, tooltip1, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(label1, tooltip1);
        richTextEditor.removeLink(label1);
        info("Remove wiki page link");
        getExoWebDriver().getWebDriver().navigate().refresh();
        richTextEditor.insertNewWikiPageLink(title1, label2, tooltip2, RichTextEditor.wikiPageLinkTab.My_Recent_Changes, false);
        wikiValidattions.verifyInsertedLinkIntoFrame(label2, tooltip2);
        richTextEditor.removeLink(label2);
        homePagePlatform.goToWiki();
    }

}
