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
     * <li> Case ID:139406.</li>
     * <li> Test Case Name: Add new page has the same title with existing page.</li>
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
     * - The page is switched to the [Source Editor] mode
     * Step number: 2
     * Step Name: Step 2: Create new page
     * Step Description:
     * - Put the title for this page has the same with existing pages
     * - Put the content of page
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * Show message alert that the page title is existing
     */
    @Test
    public void test01_AddNewPageHasTheSameTitleWithExistingPage() {
        info("Test 1: Add new page has the same title with existing page");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Create a wiki page 2 with the same title");
        String mess = "The page title already exists. Please select another one.";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.savePage();
        wikiValidattions.verifyWarningMessage(mess);
        $(ELEMENT_OK_BUTTON_LINK).click();
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139407.</li>
     * <li> Test Case Name: Add new page when content is blank.</li>
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
     * Step Name: Step 2: Create new page
     * Step Description:
     * - Put the title for this page
     * - Leave the content blank
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * New page is created successfully with blank content. It is displayed in the destination path
     */
    @Test
    public void test02_AddNewPageWhenContentIsBlank() {
        info("Test 2: Add new page when content is blank");

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, "");
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Verify that the content of the page is empty");
        wikiValidattions.verifyEmptyContentPage();
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);
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
    public void test03_AddNewPageWhenTitleIsBlank() {
        info("Test 3: Add new page when title is blank");
        info("Create a wiki page");
        String title = "Untitled";
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
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139409.</li>
     * <li> Test Case Name: Add new page when user does not have add page permission on space.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Open form to add permission for space
     * Step Description:
     * - Go to Browse
     * -
     * -> Space Permissions
     * Input Data:
     * <p>
     * Expected Outcome:
     * Space Permissions form appears
     * Step number: 2
     * Step Name: Step 2: Add permission for space
     * Step Description:
     * - Set permission for space that some user/group does not have permission to add page on this space
     * - Click on Add icon
     * Input Data:
     * <p>
     * Expected Outcome:
     * Space is added permission
     * Step number: 3
     * Step Name: Step 3: Add new page
     * Step Description:
     * Login by user/group does not have permission to add page onspace
     * Input Data:
     * <p>
     * Expected Outcome:
     * Can not see Add Page function
     */
    @Test
    public void test04_AddNewPageWhenUserDoesNotHaveAddPagePermissionOnSpace() {
        info("Test 4: Add new page when user does not have add page permission on space");
        info("Create a new user");
        String username1 = "username" + getRandomString();
        String password = "123456";
        String email1 = username1 + "@test.com";
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        ;
        info("Login by user/group does not have permission to add page onspace");
        manageLogInOut.signIn(username1, password);
        info("Can not see Add Page function");
        homePagePlatform.goToWiki();
        wikiValidattions.verifyNotShowAddPageBtn();
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    /**
     * <li> Case ID:139410.</li>
     * <li> Test Case Name: Cancel create a new page.</li>
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
     * Step Name: Step 2: Create new page
     * Step Description:
     * - Put the title for this page
     * - Put the content of page
     * Input Data:
     * <p>
     * Expected Outcome:
     * All fields are inputed with values
     * Step number: 3
     * Step Name: Step 3: Cancel create a page
     * Step Description:
     * Click on Cancel
     * Input Data:
     * <p>
     * Expected Outcome:
     * No page is created
     */
    @Test
    public void test05_CancelCreateANewPage() {
        info("Test 5: Cancel create a new page");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.cancelAddPage();
        wikiHomePage.confirmWaringMessage(true);
        info("No page is created");
        wikiValidattions.verifyNotTitleWikiPage(title);

    }

    /**
     * <li> Case ID:139414.</li>
     * <li> Test Case Name: Create new page using Blank Template.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Open form to create new page
     * Step Description:
     * - Choose path to add new page
     * - Click [Add Page]
     * -
     * -> [Blank Page]
     * - Select [Source Editor] to switch to [Source Editor] mode
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * <p>
     * Step number: 2
     * Step Name: Step 2: Create new page
     * Step Description:
     * - Put the title for this page
     * - Put the content of page
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * New page is created successfully. It is displayed in the destination path
     */
    @Test
    public void test06_CreateNewPageUsingBlankTemplate() {
        info("Test 6: Create new page using Blank Template");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("New page is created successfully. It is displayed in the destination path");
        wikiValidattions.verifyTitleWikiPage(title);
        homePagePlatform.goToWiki();
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
    public void test07_CreateNewPageWithTableEffect() {
        info("Test 7: Create new page with Table effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "|= |= \n" +
                "| | ";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("New page is created successfully. It is displayed in the destination path");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The result is the table with 2 columms and 2 rows");
        wikiValidattions.verifyTableInContentPage(2, 2);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139417.</li>
     * <li> Test Case Name: Create new page with Bold effect.</li>
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
     * Step Name: Step 2: Create new page with Bold tag
     * Step Description:
     * - Input text inside ** ** character in contentFor example: **bold**
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with Bold effect
     */
    @Test
    public void test08_CreateNewPageWithBoldEffect() {
        info("Test 8: Create new page with Bold effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value = "value" + getRandomNumber();
        String content = "**" + value + "**";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with Bold effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bold, value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139418.</li>
     * <li> Test Case Name: Create new page with Bulleted list effect.</li>
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
     * Step Name: Step 2: Create new page with Bold tag
     * Step Description:
     * - Input text inside start with * character in contentFor example: * item 1** item 2*** item 3* item 4
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The result is item 1 item 2 Item 3 item 4
     */
    @Test
    public void test09_CreateNewPageWithBulletedListEffect() {
        info("Test 9: Create new page with Bulleted list effect");

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value1 = "value" + getRandomNumber();
        String value2 = "value" + getRandomNumber();
        String value3 = "value" + getRandomNumber();
        String value4 = "value" + getRandomNumber();
        String content = "* " + value1 + " \n" + "** " + value2 + "\n" + "*** " + value3 + "\n" + "* " + value4;
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with Bullest list effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value1);
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value2);
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value3);
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value4);
        wikiHomePage.deleteWiki(title);
    }

    /**
     * <li> Case ID:139419.</li>
     * <li> Test Case Name: Create new page with Heading effect.</li>
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
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode*
     * Step number: 2
     * Step Name: Step 2: Create new page with Heading 1 tag
     * Step Description:
     * - Input text inside == character in contentFor example: =Heading1=
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with Bold effect like Heading1 (large heading)
     * Step number: 3
     * Step Name: Step 3: Create new page with Heading3 tag
     * Step Description:
     * - Input text inside == character in contentFor example: ===Heading3===
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with Bold effect like Heading3 (normal heading)
     * <p>
     * Step number: 4
     * Step Name: Step 4: Create new page with Heading5 tag
     * Step Description
     * - Input text inside == character in contentFor example: =====Heading5=====
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with Bold effect like Heading5 (small heading)
     */
    @Test
    public void test10_CreateNewPageWithHeadingEffect() {
        info("Test 10 Create new page with Heading effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value = "value" + getRandomNumber();
        ;
        String contentHeading1 = "=" + value + "=";
        String contentHeading3 = "===" + value + "===";
        String contentHeading5 = "=====" + value + "=====";
        String content = contentHeading1 + "\n" + contentHeading3 + "\n" + contentHeading5;
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with Heading 1 effect");
        $(byId("H" + value)).shouldHave(Condition.text(value));
        assertEquals($(byId("H" + value)).getCssValue("font-size"), "36px");
        info("The page is shown with Heading 3 effect");
        $(byId("H" + value + "-1")).shouldHave(Condition.text(value));
        assertEquals($(byId("H" + value + "-1")).getCssValue("font-size"), "24px");
        info("The page is shown with Heading 5 effect");
        $(byId("H" + value + "-2")).shouldHave(Condition.text(value));
        assertEquals($(byId("H" + value + "-2")).getCssValue("font-size"), "14px");
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139420.</li>
     * <li> Test Case Name: Create new page with italic effect.</li>
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
     * Step Name: Step 2: Create new page with italic tag
     * Step Description:
     * - Input text inside // // character in contentFor example: //italic //
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with 'italic' effect
     */
    @Test
    public void test11_CreateNewPageWithItalicEffect() {
        info("Test 11 Create new page with italic effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value = "value" + getRandomNumber();
        String content = "//" + value + "//";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with Italic effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Italic, value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);
    }

    /**
     * <li> Case ID:139421.</li>
     * <li> Test Case Name: Create new page with Link effect.</li>
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
     * Step Name: Step 2: Create new page with Link tag
     * Step Description:
     * - Input text inside [[ ]] character For example: [[Wiki Home]]
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with 'link' effect like Wiki Home
     */
    @Test
    public void test12_CreateNewPageWithLinkEffect() {
        info("Test 12 Create new page with Link effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value = "value" + getRandomNumber();
        String content = "[[" + value + "]]";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with link effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Link, value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139422.</li>
     * <li> Test Case Name: Create new page with Numbered list effect.</li>
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
     * Step Name: Step 2: Create new page with Numbered list tag
     * Step Description:
     * - Input text start with numberFor example: 1. item 111. item 2111. item 31. item 4
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The result is 1. item 1 1. item 2 1. item 3 2. item 4
     */
    @Test
    public void test13_CreateNewPageWithNumberedListEffect() {
        info("Test 13 Create new page with Numbered list effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value = "value" + getRandomNumber();
        String content = "1. " + value + " \n" + "111. " + value + "\n" + "2111. " + value + "\n" + "31. " + value;
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with Number list effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Number_List, value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139423.</li>
     * <li> Test Case Name: Create new page with strike effect.</li>
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
     * Step Name: Step 2: Create new page with strike tag
     * Step Description:
     * - Input text inside
     * -
     * -
     * -
     * - character in contentFor example:
     * -
     * -strike
     * -
     * -
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with 'strike' effect like strikes
     */

    @Test
    public void test14_CreateNewPageWithStrikeEffect() {
        info("Test 14 Create new page with strike effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value = "value" + getRandomNumber();
        String content = "--" + value + "--";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with Strike effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Strike, value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139424.</li>
     * <li> Test Case Name: Create new page with underline effect.</li>
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
     * Step Name: Step 2: Create new page with underline tag
     * Step Description:
     * - Input text inside__ __character in contentFor example: __underline__
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is shown with 'underline' effect like underline
     */
    @Test
    public void test15_CreateNewPageWithUnderlineEffect() {
        info("Test 15 Create new page with underline effect");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String value = "value" + getRandomNumber();
        String content = "__" + value + "__";
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        info("The page is created successfully");
        wikiValidattions.verifyTitleWikiPage(title);
        info("The page is shown with Underline effect");
        wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Underline, value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139511.</li>
     * <li> Test Case Name: Create a page wiki in a Space.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add new page for space
     * Step Description:
     * - Go to a space in [MY SPACES] list
     * - Click [Wiki] on the space navigation bar
     * - Click [Add Page]
     * -
     * -> [Blank Page]/[From Template...]
     * - Select [Source Editor] to switch to [Source Editor] mode
     * - Fill valid values for fields
     * Input Data:
     * <p>
     * Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - A wiki page is created successfully
     * - At the top of the page,"Restricted" is displayed
     */
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
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }

}
