package org.exoplatform.platform.qa.ui.platform.wiki;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 23/03/2018.
 */



@Tag("sniff")
@Tag("wiki")
public class WikiCommentActivityStreamTestIT extends Base{

    HomePagePlatform homePagePlatform;
    WikiManagement wikiManagement;
    RichTextEditor richTextEditor;
    SourceTextEditor sourceTextEditor;
    ManageLogInOut manageLogInOut;
    WikiHomePage wikiHomePage;
    ActivityStream activityStream;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform= new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        richTextEditor= new RichTextEditor(this);
        sourceTextEditor = new SourceTextEditor(this);
        activityStream= new ActivityStream(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }

        manageLogInOut.signInCas( DATA_USER1 , DATA_PASS2 );

}

    @Tag("WIKI-1392")
    @Test
    public void test_CommentWikiActivityStream(){
        String wiki = "wiki" + getRandomNumber();
        String text= "text" + getRandomNumber();

        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki, text);
        wikiManagement.saveAddPage();
        homePagePlatform.goToHomePage();
        activityStream.checkActivity(wiki);
        info("add the content of the wiki page as a comment on wiki activity stream");
        activityStream.commentWikiActivity(wiki, text);
        info("check that the comment is added successfully");
        activityStream.checkCommentOfActivity(wiki, text);
        info("Delete the page");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(wiki);

    }

}

