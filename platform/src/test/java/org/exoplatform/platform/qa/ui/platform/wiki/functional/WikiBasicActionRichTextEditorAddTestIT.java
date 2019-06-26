package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

public class WikiBasicActionRichTextEditorAddTestIT extends Base {
    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    ManageLogInOut manageLogInOut;

    WikiManagement wikiManagement;

    NavigationToolbar navigationToolbar;

    RichTextEditor richTextEditor;

    WikiDraftPage wikidraftpage;

    SourceTextEditor sourceTextEditor;

    ActivityStream activityStream;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        manageLogInOut = new ManageLogInOut(this);
        wikiManagement = new WikiManagement(this);
        navigationToolbar = new NavigationToolbar(this);
        wikidraftpage = new WikiDraftPage(this);
        richTextEditor = new RichTextEditor(this);
        sourceTextEditor = new SourceTextEditor(this);
        activityStream = new ActivityStream(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas("root", "gtn");
    }

    @Test
    @Tag("Wiki-1415")
    // check the display of an image whe the title of the wiki contain the "`" char
    public void test01_checkImageDisplayInWikiWithTitelWithSpecialCharacter() {
        //1415
        String specialTitle = getRandomString() + "`a";
        String uploadedFile = "eXo-Platform.png";

        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(specialTitle, "");
        richTextEditor.goToAttachedImageLink();
        richTextEditor.insertImage(uploadedFile, false);
        wikiManagement.saveAddPage();
        wikiHomePage.goToAPage(specialTitle);
        $(byXpath("//img[@src and @alt='${uploadedFile}']".replace("${uploadedFile}", uploadedFile))).shouldBe(Condition.visible);
        wikiHomePage.deleteWiki(specialTitle);
    }

}
