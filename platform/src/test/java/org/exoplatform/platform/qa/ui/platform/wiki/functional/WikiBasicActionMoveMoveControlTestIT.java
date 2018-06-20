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
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_WIKI_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_WIKI_BUTTON_CANCEL;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 6/6/18.
 */
@Tag("functional")
@Tag("wiki")
public class WikiBasicActionMoveMoveControlTestIT extends Base {


    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    WikiManagement wikiManagement;

    SourceTextEditor sourceTextEditor;

    ManageLogInOut manageLogInOut;

    WikiValidattions wikiValidattions;

    SpaceManagement spaceManagement;

    RichTextEditor richTextEditor;
    SpaceHomePage spaceHomePage;
    NavigationToolbar navigationToolbar;
    AddUsers addUsers;
    WikiPermission wikiPermission;
    SpaceSettingManagement spaceSettingManagement;


    @BeforeEach
    public void setupBeforeMethod() {

        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        sourceTextEditor = new SourceTextEditor(this);
        manageLogInOut = new ManageLogInOut(this);
        spaceManagement = new SpaceManagement(this);
        wikiValidattions = new WikiValidattions(this);
        richTextEditor = new RichTextEditor(this);
        navigationToolbar = new NavigationToolbar(this);
        spaceHomePage = new SpaceHomePage(this);
        wikiPermission = new WikiPermission(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        addUsers = new AddUsers(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    /**
     * <li> Case ID:139557.</li>
     * <li> Test Case Name: Check Control case sensitive: 1 to 5 sub-pages name duplicated in the target space..</li>
     * <li> Pre-Condition:
     * User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page C Renamed
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- sub-page 5
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C Renamed"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page C Renamed" is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher:
     * -Page 1 already exist. Rename* Sub
     * -Page 2 already exist. Rename* Sub
     * -Page 3 already exist. Rename* Sub
     * -Page 4 already exist. Rename* Sub
     * -Page 5 already exist. Rename*
     */
    @Test
    public void test01_CheckControlCaseSensitiveOneToFiveSubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 1: Check Control case sensitive: 1 to 5 sub-pages name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String pageCRenamed = "pageCRenamed" + getRandomNumber();
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageCRenamed, pageCRenamed);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageCRenamed);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageCRenamed);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }


        info("Create pages and sub-pages for space 2");
        info("Create page D");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageCRenamed, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }

    /**
     * <li> Case ID:139558.</li>
     * <li> Test Case Name: Check Control case sensitive: main page has the same name in the target space.</li>
     * User is member of "Space Move" and "Space Destination" Wiki of "Space Move" has:
     * - page 1
     * <p>
     * Wiki of "Space Destination" has:
     * - Page 1
     * </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "page 1"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page 1 is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The Page is moved to the wiki of "Space Destination"*
     */
    @Test
    public void test02_CheckControlCaseSensitiveMainPageHasTheSameNameInTheTargetSpace() {
        info("Test 2: Check Control case sensitive: main page has the same name in the target space");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);


        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String page1 = "page1" + getRandomNumber();
        String Page1 = page1.toLowerCase();
        info("Create page 1");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1, page1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create pages for space 2");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(Page1, Page1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(Page1);

        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(page1, "Wiki Home", space2);
        info("The Page is moved to the wiki of Space 2");
        wikiValidattions.verifyTitleWikiPage(page1);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);

    }


    /**
     * <li> Case ID:139559.</li>
     * <li> Test Case Name: Check Control case sensitive: more than 5 sub-pages name duplicated in the target space..</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination" Wiki of "Space Move" has:
     * - Page C Renamed
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- sub-page 5
     * --- Sub-Page 6
     * --- Sub-Page 7
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * --- Sub-Page 6
     * --- Sub-Page 7</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C Renamed"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page C Renamed" is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * /*Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree*
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher:
     * -Page 1 already exist. Rename* Sub
     * -Page 2 already exist. Rename* Sub
     * -Page 3 already exist. Rename* Sub
     * -Page 4 already exist. Rename* Sub
     * -Page 5 already exist. Rename* and more...*
     */
    @Test
    public void test03_CheckControlCaseSensitiveMoreThanFiveSubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 3: Check Control case sensitive: more than 5 sub-pages name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String pageCRenamed = "pageCRenamed" + getRandomNumber();
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 8);
        info("Create page C renamed");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageCRenamed, pageCRenamed);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageCRenamed);

        for (int i = 0; i < 7; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageCRenamed);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 7; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageCRenamed, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);
    }

    /**
     * <li> Case ID:139560.</li>
     * <li> Test Case Name: Check Control case sensitive: The main page and 1 to 5 sub-pages name duplicated in the target space..</li>
     * <li> Pre-Condition:User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- sub-page 5
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page C is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree*
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher:
     * "Another page with the same title already exist in the selected space. Rename"* Sub
     * -Page 1 already exist. Rename* Sub
     * -Page 2 already exist. Rename* Sub
     * -Page 3 already exist. Rename* Sub
     * -Page 4 already exist. Rename* Sub
     * -Page 5 already exist. Rename*
     */
    @Test
    public void test04_CheckControlCaseSensitiveTheMainPageAndOneToFiveSubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 4: Check Control case sensitive: The main page and 1 to 5 sub-pages name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageC, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }

    /**
     * <li> Case ID:139561.</li>
     * <li> Test Case Name: Check Control case sensitive: The main page and more than 5 sub-pages name duplicated in the target space..</li>
     * <li> Pre-Condition:User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- sub-page 5
     * --- Sub-Page 6
     * --- Sub-Page 7
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * --- Sub-Page 6
     * --- Sub-Page 7</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page C is displayed
     * <p>
     * /*Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher:
     * "Another page with the same title already exist in the selected space. Rename"* Sub
     * -Page 1 already exist. Rename* Sub
     * -Page 2 already exist. Rename* Sub
     * -Page 3 already exist. Rename* Sub
     * -Page 4 already exist. Rename* Sub
     * -Page 6 already exist. Rename* and more...
     */
    @Test
    public void test05_CheckControlCaseSensitiveTheMainPageAndMoreThanFiveSubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 5: Check Control case sensitive: The main page and more than 5 sub-pages name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 8);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 7; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 7; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageC, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }

    /**
     * <li> Case ID:139562.</li>
     * <li> Test Case Name: Check Control permissions: Move to a destination where user has no edit permissions.</li>
     * <li> Pre-Condition: User is Member of "Space Move"
     * User is Member of "Space View"
     * Wiki of "Space Move" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * Wiki of "Space View" has:
     * - Page A,user has no permissions to edit pages in the page</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page C" is displayed
     * <p>
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space View"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space View" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space View > Wiki Home
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * An error message is displayed with the message "You have no edit permission at destination page"
     */
    @Test
    public void test06_CheckControlPermissionsMoveToADestinationWhereUserHasNoEditPermissions() {
        info("Test 6: Check Control permissions: Move to a destination where user has no edit permissions");
        info("Create 2 new users");
        String username1 = "username1" + getRandomString();
        String username2 = "username2" + getRandomString();
        String firstname = "test" + getRandomString();
        String firstname2 = "testt" + getRandomString();
        String password = "123456";
        String email1 = username1 + "@test.com";
        String email2 = username2 + "@test.com";
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, firstname, firstname);
        addUsers.addUser(username2, password, email2, firstname2, firstname2);
        info("Login with user 1");

        manageLogInOut.signIn(username1, "123456");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create pages and sub-pages for space 1");
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Login with user 2");

        manageLogInOut.signIn(username2, "123456");

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Invite user 1 to space 2");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username1, true, username1);


        info("Create pages for space 2");
        String pageA = "pageA" + getRandomNumber();
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA, pageA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);

        info("Set permisison for PageD");
        String groupUsers = "*:/platform/administrators";
        info("groupUsers:" + groupUsers);
        wikiHomePage.goToAPage(pageA);
        wikiHomePage.goToPermissions();
        wikiPermission.deletePermission(groupUsers);

        info("Add View permission to User A");
        wikiPermission.addPermisisonByType(username1);
        wikiValidattions.verifyEditPermisison(username1, false);
        wikiValidattions.verifyViewPermisison(username1, true);
        wikiPermission.savePermisison();

        info("Login with user 1");
        manageLogInOut.signIn(username1, "123456");

        info("Accept invitation of the space 2");
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space2);
        info("Move the page C to the page D of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageC, pageA, space2);

        info("Verify that an error message is displayed");
        $(byText("You have no edit permission at destination page ")).waitUntil(Condition.visible, Configuration.timeout);
        wikiHomePage.confirmWaringMessage(true);
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
     * - Go to "Space Move" wiki
     * - Select "Page D"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page D" is displayed*
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree*
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher: * Sub
     * -Page level 1 already exist. Rename* Sub
     * -Page level 2 already exist. Rename* Sub
     * -Page level 3 already exist. Rename*
     */
    @Test
    public void test08_CheckControlComplexSubpagesTreeWithNameDuplicatedInTheTargetSpace() {
        info("Test 8: Check Control: complex sub-pages tree with name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);
        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);


        info("Create pages and sub-pages for space 1");
        String pageA = "pageA" + getRandomNumber();
        String pageD = "pageD" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 4);
        info("Create page D");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageD, pageD);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageD);

        info("Create sub page D level 1");
        wikiHomePage.goToAPage(pageD);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(listSubPages.get(0), listSubPages.get(0));
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(listSubPages.get(0));


        info("Create sub page D level 2");
        wikiHomePage.goToAPage(listSubPages.get(0));
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(listSubPages.get(1), listSubPages.get(1));
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(listSubPages.get(1));

        info("Create sub page D level 3");
        wikiHomePage.goToAPage(listSubPages.get(1));
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(listSubPages.get(2), listSubPages.get(2));
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(listSubPages.get(2));

        info("Create pages and sub-pages for space 2");
        info("Create page D");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA, pageA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);

        info("Create sub page A level 1");
        wikiHomePage.goToAPage(pageA);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(listSubPages.get(0), listSubPages.get(0));
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(listSubPages.get(0));


        info("Create sub page A level 2");
        wikiHomePage.goToAPage(listSubPages.get(0));
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(listSubPages.get(1), listSubPages.get(1));
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(listSubPages.get(1));

        info("Create sub page A level 3");
        wikiHomePage.goToAPage(listSubPages.get(1));
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(listSubPages.get(2), listSubPages.get(2));
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(listSubPages.get(2));
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageD, "Wiki Home", space2);

        info("Verify that the message is shown");

        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);
    }

    /**
     * <li> Case ID:139565.</li>
     * <li> Test Case Name: Check Control: main page has the same name in the target space.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page B
     * <p>
     * Wiki of "Space Destination" has:
     * - Page B</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page B"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page B is displayed*
     * <p>
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree*
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher:
     * "Another page with the same title already exists in the selected space"*
     */
    @Test
    public void test09_CheckControlMainPageHasTheSameNameInTheTargetSpace() {
        info("Test 9: Check Control: main page has the same name in the target space");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space2, space2);


        info("Create pages and sub-pages for space 1");
        String pageB = "pageB" + getRandomNumber();

        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB, pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);


        info("Create pages and sub-pages for space 2");
        info("Create page B");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB, pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageB, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);
    }

    /**
     * <li> Case ID:139569.</li>
     * <li> Test Case Name: Check Redirection after a move to "Intranet".</li>
     * <li> Pre-Condition: User is member of "Space Move"
     * Wiki of "Space Move" has:
     * - Page B
     * - Page company</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" using left side bar navigation
     * - Open wiki application
     * - Open "Page company"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page companyi" is displayed
     * - Space navigation of "Space Move" is displayed
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Open "More" Menu
     * - Select "Move"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move the page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Intranet"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Intranet" tree*
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Intranet > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page company" is moved in "Intranet"
     * - "Page company" is automatically displayed in "Intranet" :
     * - Breadcrumb is displaying :Intranet > Wiki Home > Page my wiki
     * - Space Navigation is not displayed anymore. Wiki on the Company menu is selected*
     */
    @Test
    public void test13_CheckRedirectionAfterAMoveToIntranet() {
        info("Test 13 Check Redirection after a move to Intranet");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create Page company on the space");
        String pageCompany = "pageCompany" + getRandomNumber();
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageCompany, pageCompany);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageCompany);
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageCompany, "Wiki Home", "Intranet");

        info("[Page company] is moved in [Intranet]");
        wikiValidattions.verifyTitleWikiPage(pageCompany);
        info("Breadcrumb is displaying :Intranet > Wiki Home >" + pageCompany);
        wikiValidattions.verifyBreadCrumbePath("Intranet", "Wiki Home", pageCompany);
        info("Space Navigation is not displayed anymore");
        $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.not(visible), Configuration.timeout);
        info("Wiki on the Company menu is selected");
        wikiValidattions.verifyContentPage(pageCompany);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);

    }

    /**
     * <li> Case ID:139570.</li>
     * <li> Test Case Name: Check Redirection after a move to "My Wiki".</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination 2"
     * Wiki of "Space Move" has:
     * - Page B
     * - Page my wiki</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" using left side bar navigation
     * - Open wiki application
     * - Open "Page my wiki"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page my wiki" is displayed
     * - Space navigation of "Space Move" is displayed
     * <p>
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Open "More" Menu
     * - Select "Move"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move the page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "My Wiki"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "My Wiki" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :My Wiki > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page my wiki" is moved in "My Wiki"
     * - "Page my wiki" is automatically displayed in "My Wiki" :
     * - Breadcrumb is displaying :My Wiki > Wiki Home > Page my wiki
     * - User Navigation is displayed, with "My Wiki" selected
     */
    @Test
    public void test14_CheckRedirectionAfterAMoveToMyWiki() {
        info("Test 14 Check Redirection after a move to My Wiki");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create Page company on the space");
        String page = "page" + getRandomNumber();
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, page);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(page, "Wiki Home", "My Wiki");

        info("[Page] is moved in [My Wiki]");
        wikiValidattions.verifyTitleWikiPage(page);
        info("Breadcrumb is displaying :Intranet > Wiki Home >" + page);
        wikiValidattions.verifyBreadCrumbePath("My Wiki", "Wiki Home", page);
        info("Space Navigation is not displayed anymore");
        $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.not(visible), Configuration.timeout);
        info("Wiki on the Company menu is selected");
        wikiValidattions.verifyContentPage(page);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }

    /**
     * <li> Case ID:139582.</li>
     * <li> Test Case Name: Tooltip must be displayed on the message displayed when main page has the same name in the target space.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page A
     * - Page B
     * <p>
     * Wiki of "Space Destination" has:
     * - Page 1
     * - Page B
     * - page b</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page B"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page B is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree*
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message with a warning icon are displayed bellow the space switcher: "Another page with the same title already exist in the selected space. Rename"
     */
    @Test
    public void test16_TooltipMustBeDisplayedOnTheMessageDisplayedWhenMainPageHasTheSameNameInTheTargetSpace() {
        info("Test 16 Tooltip must be displayed on the message displayed when main page has the same name in the target space");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);


        info("Create Page on the space");
        String pageA = "pageA" + getRandomNumber();
        String pageB = "pageB" + getRandomNumber();
        String page1 = "page1" + getRandomNumber();
        String pageb = "pageb" + getRandomNumber();
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA, pageA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);

        info("Create page B");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB, pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);
        info("Create page 1");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1, page1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create page B");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB, pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);

        info("Create page b");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageb, pageb);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageb);
        info("Move page of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageB, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);
    }

    /**
     * <li> Case ID:139583.</li>
     * <li> Test Case Name: Tooltips must be displayed on messages displayed when 1 to 5 sub-pages name duplicated in the target space..</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page C Renamed
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- sub-page 5
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C Renamed"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - "Page C Renamed" is displayed
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed
     * <p>
     * /*Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher:
     * -Page 1 already exist. Rename* Sub
     * -Page 2 already exist. Rename* Sub
     * -Page 3 already exist. Rename* Sub
     * -Page 4 already exist. Rename* Sub
     * -Page 5 already exist. Rename
     * <p>
     * Step number: 6
     * Step Name:
     * Step Description:
     * - Put your mouse over "Rename" next to the message "Sub
     * -Page 1 already exist"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A tooltip is displayed with the message "Rename the sub
     * -page to move"
     */
    @Test
    public void test17_TooltipsMustBeDisplayedOnMessagesDisplayedWhen1To5SubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 17 Tooltips must be displayed on messages displayed when 1 to 5 sub-pages name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String pageCRenamed = "pageCRenamed" + getRandomNumber();
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);

        info("Create page C renamed");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageCRenamed, pageCRenamed);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageCRenamed);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageCRenamed);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageCRenamed, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        info("Verify the tooltip");
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }
    /**
     *<li> Case ID:139584.</li>
     *<li> Test Case Name: Tooltips must be displayed on messages displayed when more than 5 sub-pages name duplicated in the target space..</li>
     *<li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     Wiki of "Space Move" has:
     - Page C
     --- Sub-Page 1
     --- Sub-Page 2
     --- Sub-Page 3
     --- Sub-Page 4
     --- sub-page 5
     --- Sub-Page 6
     --- Sub-Page 7

     Wiki of "Space Destination" has:
     - Page C
     --- Sub-Page 1
     --- Sub-Page 2
     --- Sub-Page 3
     --- Sub-Page 4
     --- Sub-Page 5
     --- Sub-Page 6
     --- Sub-Page 7</li>
     *<li> Post-Condition: </li>
     *Step Number: 1
     *Step Name:
     *Step Description:
     - Go to "Space Move" wiki
     - Select "Page C"
     *Input Data:

     *Expected Outcome:
     - Page C is displayed

     *Step number: 2
     *Step Name:
     *Step Description:
     - Click on More Menu
     - Select Move Option
     *Input Data:

     *Expected Outcome:
     - The popup to move a page is displayed

     Step number: 3
     *Step Name:
     *Step Description:
     - Open Space switcher
     - Select "Space Destination"
     *Input Data:

     *Expected Outcome:
     - The destination container is displaying "Space Destination" tree*
     *Step number: 4
     *Step Name:
     *Step Description:
     - In destination container select "Wiki Home"
     *Input Data:

     *Expected Outcome:
     - New Location Path is displaying :Space Destination > Wiki Home

     *Step number: 5
     *Step Name:
     *Step Description:
     - Click on Move Button
     *Input Data:

     *Expected Outcome:
     - A message is displayed bellow the space switcher:
     * "Another page with the same title already exist in the selected space. Rename"* Sub
     -Page 1 already exist. Rename* Sub
     -Page 2 already exist. Rename* Sub
     -Page 3 already exist. Rename* Sub
     -Page 4 already exist. Rename* Sub
     -Page 5 already exist. Rename* and more...
     */

    /**
     * <li> Case ID:139584.</li>
     * <li> Test Case Name: Tooltips must be displayed on messages displayed when more than 5 sub-pages name duplicated in the target space..</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- sub-page 5
     * --- Sub-Page 6
     * --- Sub-Page 7
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * --- Sub-Page 6
     * --- Sub-Page 7</li>
     * <li> Post-Condition: </li>
     * Step number: 6
     * Step Name:
     * Step Description:
     * - Put your mouse over "Rename" next to the message "Sub
     * -Page 1 already exist"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A tooltip is displayed with the message "Rename the sub
     * -page to move"
     */
    @Test
    public void test18_TooltipsMustBeDisplayedOnMessagesDisplayedWhenMoreThan5SubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 18 Tooltips must be displayed on messages displayed when more than 5 sub-pages name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);
        info("Create pages and sub-pages for space 1");
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 8);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 7; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }


        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 7; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageC, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        info("Verify the tooltip");
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }

    /**
     * <li> Case ID:139585.</li>
     * <li> Test Case Name: Tooltips must be displayed on messages displayed when the main page and 1 to 5 sub-pages name duplicated in the target space..</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- sub-page 5
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page C is displayed
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message is displayed bellow the space switcher:
     * "Another page with the same title already exist in the selected space. Rename"* Sub
     * -Page 1 already exist. Rename* Sub
     * -Page 2 already exist. Rename* Sub
     * -Page 3 already exist. Rename* Sub
     * -Page 4 already exist. Rename* Sub
     * -Page 5 already exist. Rename
     * <p>
     * Step number: 6
     * Step Name:
     * Step Description:
     * - Put your mouse over "Rename" next to the message "Another page with the same title already exist in the selected page."
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A tooltip is displayed with the message "Rename the page to move"
     * <p>
     * Step number: 7
     * Step Name:
     * Step Description:
     * - Put your mouse over "Rename" next to the message "Sub
     * -Page 1 already exist"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A tooltip is displayed with the message "Rename the sub
     * -page to move"
     */

    @Test
    public void test19_TooltipsMustBeDisplayedOnMessagesDisplayedWhenTheMainPageAnd1To5SubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 19 Tooltips must be displayed on messages displayed when the main page and 1 to 5 sub-pages name duplicated in the target space.");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space2, space2);


        info("Create pages and sub-pages for space 1");
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageC, "Wiki Home", space2);

        info("Verify that the message is shown");

        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        info("Verify the tooltip");
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }


    /**
     * <li> Case ID:139587.</li>
     * <li> Test Case Name: User should be able to move a page in the same wiki.</li>
     * <li> Pre-Condition: User is member of "Space Move"
     * Wiki of "Space Move" has:
     * - Page A
     * - Page B
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page C is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - In destination container select "Page A"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Move > Wiki Home > Page A
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page C is move below Page A
     */
    @Test
    public void test21_UserShouldBeAbleToMoveAPageInTheSameWiki() {
        info("Test 21 User should be able to move a page in the same wiki");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create pages and sub-pages for space 1");
        String pageA = "pageA" + getRandomNumber();
        String pageB = "pageB" + getRandomNumber();
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA, pageA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);


        info("Create page B");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB, pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);

        info("Create page C");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePage(pageC, pageA);
        info("Page C is move below Page A");
        wikiValidattions.verifyParentChildNode(pageA, pageC);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }

    /**
     * <li> Case ID:139588.</li>
     * <li> Test Case Name: User should be able to rename a sub-page.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * <p>
     * <p>
     * Wiki of "Space Destination" has:
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page C"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page C is displayed
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Messages with warning icons are displayed bellow the space switcher:
     * "Another page with the same title already exist in the selected space. Rename"* "Sub
     * -Page 1 already exist. Rename"* "Sub
     * -Page 2 already exist. Rename"* "Sub
     * -Page 3 already exist. Rename"* "Sub
     * -Page 4 already exist. Rename"* "Sub
     * -Page 5 already exist. Rename"*
     * <p>
     * Step number: 6
     * Step Name:
     * Step Description:
     * - Put your mouse over "rename" next to "Sub
     * -Page 3 already exist"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Tooltip "Rename the page to move" is displayed*
     * <p>
     * Step number: 7
     * Step Name:
     * Step Description:
     * - Click on Rename
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The "Sub
     * -Page 3" of "Space Move" wiki is opened in edition mode.
     */
    @Test
    public void test22_UserShouldBeAbleToRenameASubpage() {
        info("Test 22 User should be able to rename a sub-page");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String pageC = "pageC" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageC, "Wiki Home", space2);

        info("Verify that the message is shown");

        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 1 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 2 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 3 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 4 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Page 5 already exists.")).parent().find(byLinkText("Rename")).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_WIKI_BUTTON_CANCEL).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }

    /**
     * <li> Case ID:139589.</li>
     * <li> Test Case Name: User should be able to rename the main page.</li>
     * <li> Pre-Condition: User is member of "Space Move" and "Space Destination"
     * Wiki of "Space Move" has:
     * - Page B
     * - Page C
     * --- Sub-Page 1
     * --- Sub-Page 2
     * --- Sub-Page 3
     * --- Sub-Page 4
     * --- Sub-Page 5
     * <p>
     * Wiki of "Space Destination" has:
     * - Page B
     * </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name:
     * Step Description:
     * - Go to "Space Move" wiki
     * - Select "Page B"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Page B is displayed
     * <p>
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * - Click on More Menu
     * - Select Move Option
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The popup to move a page is displayed*
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * - Open Space switcher
     * - Select "Space Destination"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The destination container is displaying "Space Destination" tree*
     * <p>
     * Step number: 4
     * Step Name:
     * Step Description:
     * - In destination container select "Wiki Home"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New Location Path is displaying :Space Destination > Wiki Home*
     * <p>
     * Step number: 5
     * Step Name:
     * Step Description:
     * - Click on Move Button
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A message with a warning icon are displayed bellow the space switcher: "Another page with the same title already exist in the selected space. Rename"
     * <p>
     * Step number: 6
     * Step Name:
     * Step Description:
     * Put your mouse over "rename" in the warning message
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Tooltip "Rename the page to move" is displayed
     */

		/*Step number: 7
        *Step Name:
		*Step Description:
			- Click on Rename
		*Input Data:

		*Expected Outcome:
			- The "Page B" of "Space Move" wiki is opened in edition mode.
     */
    @Test
    public void test23_UserShouldBeAbleToRenameTheMainPage() {
        info("Test 23 User should be able to rename the main page");
        info("Create a space 1: moving space");
        String space1 = "space1" + getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info("Create a space 2: destination space");
        String space2 = "space2" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2, space2);

        info("Create pages and sub-pages for space 1");
        String pageC = "pageC" + getRandomNumber();
        String pageB = "pageB" + getRandomNumber();
        ArrayList<String> listSubPages = getListData("Page", 6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC, pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        info("Create page B");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB, pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);

        for (int i = 0; i < 5; i++) {
            info("Create sub page C" + i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i), listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page B");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB, pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);
        info("Move page B of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageB, "Wiki Home", space2);

        info("Verify that the message is shown");
        $(byText("Another page with the same title already exists in the selected space.")).parent().find(byLinkText("Rename")).waitUntil(visible, Configuration.timeout);
        wikiManagement.renameFromAlertMessageOfOnePage();
        wikiValidattions.verifyEditModeOpenning(pageB);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }

}