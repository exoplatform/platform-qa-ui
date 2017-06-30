package org.exoplatform.platform.qa.ui.platform.wiki;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SOURCE_EDITOR_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("wiki")
public class WikiBasicTestWithUser extends Base {

    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    RichTextEditor richTextEditor;

    SourceTextEditor sourcetexteditor;

    WikiValidattions wikiValidattions;

    WikiManagement wikiManagement;

    UserAddManagement userAddManagement;

    ArrayList<String> arrayPage;

    NavigationToolbar navigationToolbar;

    ManageLogInOut manageLogInOut;

    UserAndGroupManagement userAndGroupManagement;



    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        wikiValidattions = new WikiValidattions(this);
        wikiManagement = new WikiManagement(this);
        wikiHomePage = new WikiHomePage(this);


        try {
            sourcetexteditor = new SourceTextEditor(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            richTextEditor = new RichTextEditor(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        arrayPage = new ArrayList<String>();
        userAddManagement=new UserAddManagement(this);
        navigationToolbar=new NavigationToolbar(this);
        manageLogInOut=new ManageLogInOut(this);
        userAndGroupManagement=new UserAndGroupManagement(this);
    }

    @Test
    public void test01_AddAPageWithLinkWikiPageExisted() {
        info("Test 1: Add a page with link wiki page existed");

        info("Create a wiki page 1");
        String title1 = "title" + getRandomNumber();
        String content1 = "content" + getRandomNumber();

        manageLogInOut.signInCas("john","gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);
        arrayPage.add(title1);

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
        arrayPage.add(title2);
        info("Page is shown successfully");
        wikiHomePage.goToAPage(title2);
        wikiValidattions.verifyInsertedExistLink(label, title1);
        wikiHomePage.deleteWiki(title1);
        wikiHomePage.deleteWiki(title2);

    }

    @Test
    public void test02_AddWebPage() {
        info("Test 2: Add web page");

        info("Create a wiki page 2");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        String label = "label" + getRandomNumber();
        String tooltip = "tooltip" + getRandomNumber();
        String address = "www.google.com";
        manageLogInOut.signInCas("john","gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        richTextEditor.goToWebPageLink();
        richTextEditor.insertWebLink(address, label, tooltip, true);
        wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);
        arrayPage.add(title1);

        info("Page is shown successfully");
        wikiHomePage.goToAPage(title1);
        wikiManagement.viewInsertLink(label);
        String titlePage = this.getExoWebDriver().getWebDriver().getTitle();
        if (titlePage.contains("Google"))
            assert true;
        else
            assert false;

    }

    @Test
    public void test03_EditPage() {
        info("Test 3: Edit page");

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        manageLogInOut.signInCas("john","gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourcetexteditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();

        wikiValidattions.verifyTitleWikiPage(title);
        arrayPage.add(title);

        info("Edit a wiki page");
        String newTitle = "newTitle" + getRandomNumber();
        String newContent = "newContent" + getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
            wikiManagement.goToSourceEditor();
        }
        sourcetexteditor.editSimplePage(newTitle, newContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(newTitle);
        arrayPage.add(newTitle);
        wikiHomePage.deleteWiki(newTitle);

    }
    @Test
    public void test04_Create_Delete_PageUsingSourceEditor() {
        info("Test 4: Create page using Source Editor");
        String wiki = "wiki" + getRandomNumber();
        manageLogInOut.signInCas("john","gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki, wiki);
        wikiManagement.saveAddPage();
        $(byText(wiki)).should(Condition.exist);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(wiki);
    }


}
