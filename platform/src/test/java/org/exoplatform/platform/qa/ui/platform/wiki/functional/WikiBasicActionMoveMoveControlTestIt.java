package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getListData;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_WIKI_TAB;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 6/6/18.
 */
public class WikiBasicActionMoveMoveControlTestIt extends Base {


    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    WikiManagement wikiManagement;

    SourceTextEditor sourceTextEditor;

    ManageLogInOut manageLogInOut;

    WikiValidattions wikiValidattions;

    SpaceManagement spaceManagement;

    WikiDraftPage wikiDraftPage;
    RichTextEditor richTextEditor;
    SpaceHomePage spaceHomePage;

    @BeforeEach
    public void setupBeforeMethod() {

        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        sourceTextEditor = new SourceTextEditor(this);
        manageLogInOut = new ManageLogInOut(this);
        spaceManagement = new SpaceManagement(this);
        wikiDraftPage = new WikiDraftPage(this);
        wikiValidattions = new WikiValidattions(this);
        richTextEditor = new RichTextEditor(this);
        spaceHomePage =  new SpaceHomePage(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    @Test
    public  void test01_CheckControlCaseSensitiveOneToFiveSubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 1: Check Control case sensitive: 1 to 5 sub-pages name duplicated in the target space.");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space Move" wiki
			- Select "Page C Renamed"
		*Input Data:

		*Expected Outcome:
			- "Page C Renamed" is displayed*/
        info("Create a space 1: moving space");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1,6000);

        info("Create a space 2: destination space");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2,space2,6000);

        info("Create pages and sub-pages for space 1");
        String pageCRenamed = "pageCRenamed"+getRandomNumber();
        String pageC = "pageC"+getRandomNumber();
        ArrayList<String> listSubPages= getListData("Page",6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageCRenamed,pageCRenamed);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageCRenamed);

        for(int i=0;i<5;i++){
            info("Create sub page C"+i);
            wikiHomePage.goToAPage(pageCRenamed);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i),listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }


        info("Create pages and sub-pages for space 2");
        info("Create page D");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC,pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for(int i=0;i<5;i++){
            info("Create sub page C"+i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i),listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Click on More Menu
			- Select Move Option
		*Input Data:

		*Expected Outcome:
			- The popup to move a page is displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open Space switcher
			- Select "Space Destination"
		*Input Data:

		*Expected Outcome:
			- The destination container is displaying "Space Destination" tree*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- In destination container select "Wiki Home"
		*Input Data:

		*Expected Outcome:
			- New Location Path is displaying :Space Destination > Wiki Home*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Click on Move Button
		*Input Data:

		*Expected Outcome:
			- A message is displayed bellow the space switcher:
			-Page 1 already exist. Rename* Sub
			-Page 2 already exist. Rename* Sub
			-Page 3 already exist. Rename* Sub
			-Page 4 already exist. Rename* Sub
			-Page 5 already exist. Rename*/
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageCRenamed,"Wiki Home",space2);

        info("Verify that the message is shown");
         String mess = "mess"+getRandomNumber();
        wikiValidattions.verifyMessageManyPagesHaveSameTitleInMovingPage(mess,listSubPages);
    }

    /**
     *<li> Case ID:139558.</li>
     *<li> Test Case Name: Check Control case sensitive: main page has the same name in the target space.</li>
     User is member of "Space Move" and "Space Destination" Wiki of "Space Move" has:
     - page 1

     Wiki of "Space Destination" has:
     - Page 1
     </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test02_CheckControlCaseSensitiveMainPageHasTheSameNameInTheTargetSpace() {
        info("Test 2: Check Control case sensitive: main page has the same name in the target space");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space Move" wiki
			- Select "page 1"
		*Input Data:

		*Expected Outcome:
			- Page 1 is displayed*/
        info("Create a space 1: moving space");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space1,space1,6000);


        info("Create a space 2: destination space");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space2,space2,6000);

        info("Create pages and sub-pages for space 1");
        String page1 = "page1"+getRandomNumber();
        String Page1=page1.toLowerCase();
        info("Create page 1");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,page1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create pages for space 2");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(Page1,Page1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(Page1);


		/*Step number: 2
		*Step Name:
		*Step Description:
			- Click on More Menu
			- Select Move Option
		*Input Data:

		*Expected Outcome:
			- The popup to move a page is displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open Space switcher
			- Select "Space Destination"
		*Input Data:

		*Expected Outcome:
			- The destination container is displaying "Space Destination" tree*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- In destination container select "Wiki Home"
		*Input Data:

		*Expected Outcome:
			- New Location Path is displaying :Space Destination > Wiki Home*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Click on Move Button
		*Input Data:

		*Expected Outcome:
			- The Page is moved to the wiki of "Space Destination"*/
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(page1,"Wiki Home",space2);
        info("The Page is moved to the wiki of Space 2");
        wikiValidattions.verifyTitleWikiPage(page1);

    }


    /**
     *<li> Case ID:139559.</li>
     *<li> Test Case Name: Check Control case sensitive: more than 5 sub-pages name duplicated in the target space..</li>
     *<li> Pre-Condition: User is member of "Space Move" and "Space Destination" Wiki of "Space Move" has:
     - Page C Renamed
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
     */
    @Test
    public  void test03_CheckControlCaseSensitiveMoreThanFiveSubpagesNameDuplicatedInTheTargetSpace() {
        info("Test 3: Check Control case sensitive: more than 5 sub-pages name duplicated in the target space.");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space Move" wiki
			- Select "Page C Renamed"
		*Input Data:

		*Expected Outcome:
			- "Page C Renamed" is displayed*/

        info("Create a space 1: moving space");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create a space 2: destination space");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space2,space2);

        info("Create pages and sub-pages for space 1");
        String pageCRenamed = "pageCRenamed"+getRandomNumber();
        String pageC = "pageC"+getRandomNumber();
        ArrayList<String> listSubPages= getListData("Page",8);
        info("Create page C renamed");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageCRenamed,pageCRenamed);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageCRenamed);

        for(int i=0;i<7;i++){
            info("Create sub page C"+i);
            wikiHomePage.goToAPage(pageCRenamed);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i),listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC,pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for(int i=0;i<7;i++){
            info("Create sub page C"+i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i),listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Click on More Menu
			- Select Move Option
		*Input Data:

		*Expected Outcome:
			- The popup to move a page is displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open Space switcher
			- Select "Space Destination"
		*Input Data:

		*Expected Outcome:
			- The destination container is displaying "Space Destination" tree*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- In destination container select "Wiki Home"
		*Input Data:

		*Expected Outcome:
			- New Location Path is displaying :Space Destination > Wiki Home*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Click on Move Button
		*Input Data:

		*Expected Outcome:
			- A message is displayed bellow the space switcher:
			-Page 1 already exist. Rename* Sub
			-Page 2 already exist. Rename* Sub
			-Page 3 already exist. Rename* Sub
			-Page 4 already exist. Rename* Sub
			-Page 5 already exist. Rename* and more...*/
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageCRenamed,"Wiki Home",space2);

        info("Verify that the message is shown");
       // String mess = wikiWarningData.getDataContentByArrayTypeRandom(7);
      //  wikiValidattions.verifyMessageManyPagesHaveSameTitleInMovingPage(mess,listSubPages);
    }
    /**
     *<li> Case ID:139569.</li>
     *<li> Test Case Name: Check Redirection after a move to "Intranet".</li>
     *<li> Pre-Condition: User is member of "Space Move"
     Wiki of "Space Move" has:
     - Page B
     - Page company</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test13_CheckRedirectionAfterAMoveToIntranet() {
        info("Test 13 Check Redirection after a move to Intranet");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space Move" using left side bar navigation
			- Open wiki application
			- Open "Page company"
		*Input Data:

		*Expected Outcome:
			- "Page companyi" is displayed
			- Space navigation of "Space Move" is displayed*/
        info("Create a space 1: moving space");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create Page company on the space");
        String pageCompany="pageCompany"+getRandomNumber();
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageCompany,pageCompany);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageCompany);
		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Move"
		*Input Data:

		*Expected Outcome:
			- The popup to move the page is displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open Space switcher
			- Select "Intranet"
		*Input Data:

		*Expected Outcome:
			- The destination container is displaying "Intranet" tree*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- In destination container select "Wiki Home"
		*Input Data:

		*Expected Outcome:
			- New Location Path is displaying :Intranet > Wiki Home*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Click on Move Button
		*Input Data:

		*Expected Outcome:
			- "Page company" is moved in "Intranet"
			- "Page company" is automatically displayed in "Intranet" :
			- Breadcrumb is displaying :Intranet > Wiki Home > Page my wiki
			- Space Navigation is not displayed anymore. Wiki on the Company menu is selected*/
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageCompany,"Wiki Home","Intranet");

        info("[Page company] is moved in [Intranet]");
        wikiValidattions.verifyTitleWikiPage(pageCompany);
        info("Breadcrumb is displaying :Intranet > Wiki Home >"+pageCompany);
        wikiValidattions.verifyBreadCrumbePath("Intranet","Wiki Home",pageCompany);
        info("Space Navigation is not displayed anymore");
        $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        info("Wiki on the Company menu is selected");
        wikiValidattions.verifyContentPage(pageCompany);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1,false);

    }

    /**
     *<li> Case ID:139570.</li>
     *<li> Test Case Name: Check Redirection after a move to "My Wiki".</li>
     *<li> Pre-Condition: User is member of "Space Move" and "Space Destination 2"
     Wiki of "Space Move" has:
     - Page B
     - Page my wiki</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test14_CheckRedirectionAfterAMoveToMyWiki() {
        info("Test 14 Check Redirection after a move to My Wiki");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space Move" using left side bar navigation
			- Open wiki application
			- Open "Page my wiki"
		*Input Data:

		*Expected Outcome:
			- "Page my wiki" is displayed
			- Space navigation of "Space Move" is displayed*/
        info("Create a space 1: moving space");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create Page company on the space");
        String page="page"+getRandomNumber();
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,page);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);


		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Move"
		*Input Data:

		*Expected Outcome:
			- The popup to move the page is displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open Space switcher
			- Select "My Wiki"
		*Input Data:

		*Expected Outcome:
			- The destination container is displaying "My Wiki" tree*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- In destination container select "Wiki Home"
		*Input Data:

		*Expected Outcome:
			- New Location Path is displaying :My Wiki > Wiki Home*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Click on Move Button
		*Input Data:

		*Expected Outcome:
			- "Page my wiki" is moved in "My Wiki"
			- "Page my wiki" is automatically displayed in "My Wiki" :
			- Breadcrumb is displaying :My Wiki > Wiki Home > Page my wiki
			- User Navigation is displayed, with "My Wiki" selected*/
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(page,"Wiki Home","My Wiki");

        info("[Page] is moved in [My Wiki]");
        wikiValidattions.verifyTitleWikiPage(page);
        info("Breadcrumb is displaying :Intranet > Wiki Home >"+page);
        wikiValidattions.verifyBreadCrumbePath("My Wiki","Wiki Home",page);
        info("Space Navigation is not displayed anymore");
        $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        info("Wiki on the Company menu is selected");
        wikiValidattions.verifyContentPage(page);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1,false);
}
    /**
     *<li> Case ID:139587.</li>
     *<li> Test Case Name: User should be able to move a page in the same wiki.</li>
     *<li> Pre-Condition: User is member of "Space Move"
     Wiki of "Space Move" has:
     - Page A
     - Page B
     - Page C
     --- Sub-Page 1
     --- Sub-Page 2
     --- Sub-Page 3
     --- Sub-Page 4
     --- Sub-Page 5</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test21_UserShouldBeAbleToMoveAPageInTheSameWiki() {
        info("Test 21 User should be able to move a page in the same wiki");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space Move" wiki
			- Select "Page C"
		*Input Data:

		*Expected Outcome:
			- Page C is displayed*/
        info("Create a space 1: moving space");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create pages and sub-pages for space 1");
        String pageA = "pageA"+getRandomNumber();
        String pageB = "pageB"+getRandomNumber();
        String pageC = "pageC"+getRandomNumber();
        ArrayList<String> listSubPages= getListData("Page",6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA,pageA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);


        info("Create page B");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB,pageB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);

        info("Create page C");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC,pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for(int i=0;i<5;i++){
            info("Create sub page C"+i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i),listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Click on More Menu
			- Select Move Option
		*Input Data:

		*Expected Outcome:
			- The popup to move a page is displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- In destination container select "Page A"
		*Input Data:

		*Expected Outcome:
			- New Location Path is displaying :Space Move > Wiki Home > Page A*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- Click on Move Button
		*Input Data:

		*Expected Outcome:
			- Page C is move below Page A*/
        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePage(pageC, pageA);
        info("Page C is move below Page A");
        wikiValidattions.verifyParentChildNode(pageA, pageC);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1,false);
    }
    @Test
    public  void test22_UserShouldBeAbleToRenameASubpage() {
        info("Test 22 User should be able to rename a sub-page");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space Move" wiki
			- Select "Page C"
		*Input Data:

		*Expected Outcome:
			- Page C is displayed*/
        info("Create a space 1: moving space");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create a space 2: destination space");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2,space2);

        info("Create pages and sub-pages for space 1");
        String pageC = "pageC"+getRandomNumber();
        ArrayList<String> listSubPages= getListData("Page",6);
        info("Create page A");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC,pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for(int i=0;i<5;i++){
            info("Create sub page C"+i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i),listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

        info("Create pages and sub-pages for space 2");
        info("Create page C");
        homePagePlatform.goToSpecificSpace(space2);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC,pageC);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);

        for(int i=0;i<5;i++){
            info("Create sub page C"+i);
            wikiHomePage.goToAPage(pageC);
            wikiHomePage.goToAddBlankPage();
            richTextEditor.addSimplePage(listSubPages.get(i),listSubPages.get(i));
            wikiManagement.saveAddPage();
            wikiValidattions.verifyTitleWikiPage(listSubPages.get(i));
        }

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Click on More Menu
			- Select Move Option
		*Input Data:

		*Expected Outcome:
			- The popup to move a page is displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open Space switcher
			- Select "Space Destination"
		*Input Data:

		*Expected Outcome:
			- The destination container is displaying "Space Destination" tree*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- In destination container select "Wiki Home"
		*Input Data:

		*Expected Outcome:
			- New Location Path is displaying :Space Destination > Wiki Home*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Click on Move Button
		*Input Data:

		*Expected Outcome:
			- Messages with warning icons are displayed bellow the space switcher:
			 * "Another page with the same title already exist in the selected space. Rename"* "Sub
			-Page 1 already exist. Rename"* "Sub
			-Page 2 already exist. Rename"* "Sub
			-Page 3 already exist. Rename"* "Sub
			-Page 4 already exist. Rename"* "Sub
			-Page 5 already exist. Rename"*/

        info("Move page C of space 1 to Wiki Home of space 2");
        homePagePlatform.goToSpecificSpace(space1);
        spaceManagement.goToWikiTab();
        wikiManagement.movePageDiffDestination(pageC,"Wiki Home",space2);

        info("Verify that the message is shown");
      //  String messParrent = wikiWarningData.getDataContentByArrayTypeRandom(6);
      //  String messChild = wikiWarningData.getDataContentByArrayTypeRandom(7);
    //    wikiValidattions.verifyMessageOnePageHasSameTitleInMovingPage(messParrent);
     //   wikiValidattions.verifyMessageManyPagesHaveSameTitleInMovingPage(messChild,listSubPages);

		/*Step number: 6
		*Step Name:
		*Step Description:
			- Put your mouse over "rename" next to "Sub
			-Page 3 already exist"
		*Input Data:

		*Expected Outcome:
			- Tooltip "Rename the page to move" is displayed*/

		/*Step number: 7
		*Step Name:
		*Step Description:
			- Click on Rename
		*Input Data:

		*Expected Outcome:
			- The "Sub
			-Page 3" of "Space Move" wiki is opened in edition mode.*/
     //   wikiManagement.renameFromAlertMessageOfManyPages(messChild,listSubPages.get(2));
        wikiValidattions.verifyEditModeOpenning(listSubPages.get(2));

    }
}