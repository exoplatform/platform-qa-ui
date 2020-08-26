package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_INFOR_BAR_VERSION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.Assert.assertEquals;

/**
 * Created by exo on 5/15/18.
 */
@Tag("wiki")
@Tag("functional")

public class WikiBasicActionAddSourceEditorTestIT extends Base {


    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    WikiManagement wikiManagement;

    ManageLogInOut manageLogInOut;
    WikiValidattions wikiValidattions;
    SourceTextEditor sourceTextEditor;
    NavigationToolbar navigationToolbar;
    SpaceHomePage spaceHomePage;
    SpaceManagement spaceManagement;
    AddUsers addUsers;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        manageLogInOut = new ManageLogInOut(this);
        wikiManagement = new WikiManagement(this);
        sourceTextEditor = new SourceTextEditor(this);
        wikiValidattions = new WikiValidattions(this);
        navigationToolbar = new NavigationToolbar(this);
        spaceHomePage = new SpaceHomePage(this);
        addUsers = new AddUsers(this);
        spaceManagement = new SpaceManagement(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    /**
     * <li> Case ID:139408.</li>
     * <li> Test Case Name: Add new page when title is blank.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Open form to create new page
     * Step Description:
     * - Choose path to add new page
     * - Click [Add Page]
     * -
     * -> [Blank Page]/[From Template...]
     * - Select [Source Editor] to switch to [Source Editor] mode
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * Step number: 2
     * Step Name: Step 2: Create new page when click Cancel button
     * Step Description:
     * - Leave the title field is blank
     * - Click [Cancel]
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New page is not created
     * Step number: 3
     * Step Name: Step 3: Create new page when click confirm button
     * Step Description:
     * - Leave the title field blank
     * - Click [Save ]
     * -
     * -> [Confirm]
     * Input Data:
     * <p>
     * Expected Outcome:
     * New page is created with name is "Untitled page."
     */
    @Test
    public void test03_AddNewPageWhenContentIsBlankThenWhenTitleIsBlankThenHasTheSameTitleWithExistingPageThenUsingBlankTemplateThenCancelCreateANewPageThenWhenUserDoesNotHaveAddPagePermissionOnSpace() {
        info("Add new page when content is blank");
        info("Create a wiki page");
        String title = "Untitled";
        String title1 = "title1" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title1, "");
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);
        info("Verify that the content of the page is empty");
        wikiValidattions.verifyEmptyContentPage();
        info("Add new page when title is blank");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage("", "");
        wikiManagement.cancelAddPage();
        info("New page is not created");
        wikiValidattions.verifyNotTitleWikiPage(title);
        info("Create a wiki page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage("", "");
        wikiManagement.savePage();
        info("click on Confirm button");
        wikiHomePage.confirmWaringMessage(true);
        info("New page is created with name is 'Untitled page.''");
        wikiValidattions.verifyTitleWikiPage(title);
        info("Add new page has the same title with existing page");
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title2, content2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title2);
        info("Create a wiki page 2 with the same title");
        String mess = "The page title already exists. Please select another one.";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        sourceTextEditor.addSimplePage(title2, content2);
        wikiManagement.savePage();
        wikiValidattions.verifyWarningMessage(mess);
        $(ELEMENT_OK_BUTTON_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        info("Create new page using Blank Template");
        info("Create a wiki page");
        String title3 = "title3" + getRandomNumber();
        String content3 = "content3" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title3, content3);
        wikiManagement.saveAddPage();
        info("New page is created successfully. It is displayed in the destination path");
        wikiValidattions.verifyTitleWikiPage(title3);
        info("Cancel create a new page");
        info("Create a wiki page");
        String title4 = "title4" + getRandomNumber();
        String content4 = "content4" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title4, content4);
        wikiManagement.cancelAddPage();
        wikiHomePage.confirmWaringMessage(true);
        info("No page is created");
        wikiValidattions.verifyNotTitleWikiPage(title4);
        info("Add new page when user does not have add page permission on space");
        info("Create a new user");
        String username1 = "username" + getRandomString();
        String password = "Aa123456";
        String email1 = username1 + "@test.com";
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        info("Login by user/group does not have permission to add page onspace");
        manageLogInOut.signIn(username1, password);
        info("Can not see Add Page function");
        homePagePlatform.goToWiki();
        wikiValidattions.verifyNotShowAddPageBtn();
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title3);
        wikiHomePage.deleteWiki(title2);
        wikiHomePage.deleteWiki(title1);
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139416.</li>
     * <li> Test Case Name: Create new page with Table effect.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Open form to create new page
     * Step Description:
     * - Choose path to add new page
     * - Click [Add Page]
     * -
     * -> [Blank Page]/[From Template...]
     * - Select [Source Editor] to switch to [Source Editor] mode
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * <p>
     * Step number: 2
     * Step Name: Step 2: Create new page with Table tag
     * Step Description:
     * - Input |=Title 1|=Title 2|Word 1|Word 2
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The result is the table with 2 columms and 2 rows
     */
    @Test
    public void test07_CreateNewPageWithTableThenBoldThenBulletedListThenHeadingThenItalicThenLinkThenNumberedListThenStrikeThenUnderlineEffect() {
        info("Create new page with Table effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String title1 = "title1" + getRandomNumber();
        String content = "|= |= \n" +
                "| | ";
        String value = "value" + getRandomNumber();
        String content1 = "**" + value + "**";
        String title2 = "title2" + getRandomNumber();
        String value1 = "value" + getRandomNumber();
        String value2 = "value" + getRandomNumber();
        String value3 = "value" + getRandomNumber();
        String value4 = "value" + getRandomNumber();
        String content2 = "* " + value1 + " \n" + "** " + value2 + "\n" + "*** " + value3 + "\n" + "* " + value4;
        String title3 = "title3" + getRandomNumber();
        String value5 = "value" + getRandomNumber();
        String contentHeading1 = "=" + value5 + "=";
        String contentHeading3 = "===" + value5 + "===";
        String contentHeading5 = "=====" + value5 + "=====";
        String content3 = contentHeading1 + "\n" + contentHeading3 + "\n" + contentHeading5;
        String title4 = "title4" + getRandomNumber();
        String value6 = "value" + getRandomNumber();
        String content4 = "//" + value6 + "//";
        String title5 = "title5" + getRandomNumber();
        String value7 = "value" + getRandomNumber();
        String content5 = "[[" + value7 + "]]";
        String title6 = "title6" + getRandomNumber();
        String value8 = "value" + getRandomNumber();
        String content6 = "1. " + value8 + " \n" + "111. " + value8 + "\n" + "2111. " + value8 + "\n" + "31. " + value8;
        String title7 = "title7" + getRandomNumber();
        String value9 = "value" + getRandomNumber();
        String content7 = "--" + value9 + "--";
        String title8 = "title8" + getRandomNumber();
        String value10 = "value" + getRandomNumber();
        String content8 = "__" + value10 + "__";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("New page is created successfully. It is displayed in the destination path");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The result is the table with 2 columms and 2 rows");
        wikiValidattions.verifyTableInContentPage(2, 2);
        info("Create new page with Bold effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title1);
        info("The page is shown with Bold effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bold, value);
        info("Create new page with BulletedList effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title2, content2);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title2);
        info("The page is shown with Bullest list effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value1);
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value2);
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value3);
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value4);
        info("Create new page with Heading effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title3, content3);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title3);
        info("The page is shown with Heading 1 effect");
        $(byId("H" + value5)).shouldHave(Condition.text(value5));
        assertEquals($(byId("H" + value5)).getCssValue("font-size"), "36px");
        info("The page is shown with Heading 3 effect");
        $(byId("H" + value5 + "-1")).shouldHave(Condition.text(value5));
        assertEquals($(byId("H" + value5 + "-1")).getCssValue("font-size"), "24px");
        info("The page is shown with Heading 5 effect");
        $(byId("H" + value5 + "-2")).shouldHave(Condition.text(value5));
        assertEquals($(byId("H" + value5 + "-2")).getCssValue("font-size"), "14px");
        info("Create new page with  Italic effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title4, content4);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title4);
        info("The page is shown with Italic effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Italic, value6);
        info("Create new page with Link effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title5, content5);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title5);
        info("The page is shown with link effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Link, value7);
        info("Create new page with Numbered List effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title6, content6);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title6);
        info("The page is shown with Number list effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Number_List, value8);
        info("Create new page with Strike effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title7, content7);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title7);
        info("The page is shown with Strike effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Strike, value9);
        info("Create new page with UnderLine effect");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title8, content8);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title8);
        info("The page is shown with Underline effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Underline, value10);

        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);
        wikiHomePage.deleteWiki(title1);
        wikiHomePage.deleteWiki(title2);
        wikiHomePage.deleteWiki(title3);
        wikiHomePage.deleteWiki(title4);
        wikiHomePage.deleteWiki(title5);
        wikiHomePage.deleteWiki(title6);
        wikiHomePage.deleteWiki(title7);
        wikiHomePage.deleteWiki(title8);

    }

    @Test
    public void test16_CreateAPageWikiInASpace() {
        info("Test 16 Create a page wiki in a Space");
        info("Create a space");
        String space = "space" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 6000);
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToSpecificSpace(space);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("Verify the version on infor bar");
        $(byXpath(ELEMENT_INFOR_BAR_VERSION.replace("$version", "V1"))).waitUntil(Condition.visible, Configuration.timeout);
        info("Create a draft wiki page");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title1, content1);
        wikiHomePage.goToMyDraft();
        wikiValidattions.verifyTitleDrafPage(title1);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }

}
