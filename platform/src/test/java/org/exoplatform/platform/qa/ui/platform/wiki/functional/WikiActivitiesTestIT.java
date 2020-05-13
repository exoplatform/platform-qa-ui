package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("wiki")
@Tag("functional")

public class WikiActivitiesTestIT extends Base {

    /**
     * <li>Case ID:139186.</li>
     * <li>Test Case Name: Add a wiki's activity after create a wiki page in
     * portal.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    WikiManagement wikiManagement;

    SourceTextEditor sourceTextEditor;

    WikiValidattions wikiValidattions;

    ActivityStream activityStream;

    RichTextEditor richTextEditor;

    ManageLogInOut manageLogInOut;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    NavigationToolbar navigationToolbar;

    SpaceSettingManagement spaceSettingManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        sourceTextEditor = new SourceTextEditor(this);
        wikiValidattions = new WikiValidattions(this);
        activityStream = new ActivityStream(this);
        richTextEditor = new RichTextEditor(this);
        manageLogInOut = new ManageLogInOut(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        navigationToolbar = new NavigationToolbar(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");

    }

    /**
     * <li>Case ID:139189.</li>
     * <li>Test Case Name: Display "View Changes" page from wiki's activity.</li>
     * <li>Pre-Condition: the wiki page is not "version 1"</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add page for wiki
     * Step Description: - Connect to [Intranet] - Go to the [Homepage] - Click
     * [Wiki] to go to the Wiki app - Click [Add Page] - -> [Blank Page]/[From
     * Template...] - Enter title and content for the Wiki page - Click [Save] Input
     * Data: Expected Outcome: - A wiki page is created successfully - A wiki
     * activity is displayed Step number: 2 Step Name: Step 2: Edit wiki's page Step
     * Description: - Click [Edit] to edit the wiki page - Change content for page -
     * Click [Save] Input Data: Expected Outcome: - Page is edited successfully
     * /*Step number: 3 Step Name: Step Description: - Back to the Homepage Input
     * Data: Expected Outcome: - In the create page's activity a Wiki page's version
     * "View change" is displayed Step number: 4 Step Name: Step 3: Check compare
     * revision Step Description: - Click on the link "View changes" Input Data:
     * Expected Outcome: - The wiki application is opened in the view to compare
     * Version N -1 and Version N
     */

    @Test
    @Tag("wabis")
    public void test02_DisplayViewChangesPageFromWikisActivityAfterCreateAWikiPageInSpace() {
        info("Test 2: Display View Changes page from wiki's activity");
        info("Create a space");
        String space = "space" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 6000);
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        homePagePlatform.goToHomePage();
        activityStream.checkActivity(title);
        info("Edit the page");
        String editTitle = "editTitle" + getRandomNumber();
        String editContent = "editContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        sourceTextEditor.editSimplePage(editTitle, editContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(editTitle);
        info("In the create page's activity a Wiki page's version 'View change' is displayed");
        homePagePlatform.goToHomePage();
        activityStream.checkActivity(editTitle);
        activityStream.checkActivityWikiPage(editTitle, editContent, "2", true);
        activityStream.clickOnViewChange(editTitle);
        wikiValidattions.verifyCompareVersions("1");
        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        homePagePlatform.goToAllSpace();
        spaceManagement.goToSpace(space);
        spaceManagement.verifyMessageAccessToSpace(space);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(editTitle);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }

    /**
     * <li>Case ID:139192.</li>
     * <li>Test Case Name: Update wiki's activity after edit wiki page without
     * comment.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add new page Step
     * Description: - Connect to [Intranet] - Click [Wiki] to open the Wiki
     * application - Click [Add Page] - -> [Blank Page]/[From Template...] - Enter
     * title and content for the Wiki page - Click [Save] Input Data: Expected
     * Outcome: - New page is added successfully Step number: 2 Step Name: Step 2:
     * Edit content with active notification Step Description: - Click [Edit] to
     * change content of wiki page without any comment associated<br />
     * - Click [Publish Activity]<br />
     * - Click [Save] Input Data: Expected Outcome: - Content of page is updated -
     * The comment: "Page's content has been edited." is added to the activity Step
     * number: 3 Step Name: Step 3: Check wiki's activity after edit content with
     * active notification Step Description: - Go to the Homepage Input Data:
     * Expected Outcome: - The wiki activity is updated - A comment is added in the
     * activity: Page's content has been edited.
     */

    @Test
    public void test05_UpdateWikisActivityAfterEditWikiPage() {
        info("Test 5: Update wiki's activity after edit wiki page without comment");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Edit the page and publish the page");
        String editContent = "editContent" + getRandomNumber();
        String editTitle = "editTitle" + getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        sourceTextEditor.editSimplePage("", editContent);
        wikiManagement.publishPageWhenEditPage();
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Check the activity on Activity Stream");
        String comment = "";
        homePagePlatform.goToHomePage();
        activityStream.checkActivity(title);
        activityStream.checkComment(title, comment, null, ActivityStream.changeTypes.No_Value);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToEditPage();
        sourceTextEditor.editSimplePage(editTitle, "");
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(editTitle);
        info("Check the activity on Activity Stream");
        String comment1 = "Page's title has been updated to: " + editTitle;
        homePagePlatform.goToHomePage();
        activityStream.checkActivity(editTitle);
        activityStream.checkNotCommentOfActivity(title,title);
        activityStream.checkComment(editTitle, comment1, editTitle, ActivityStream.changeTypes.Has_One_Value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(editTitle);
    }

    /**
     * <li>Case ID:139195.</li>
     * <li>Test Case Name: Update wiki's activity after move of wiki page.</li>
     * <li>Pre-Condition: the wiki activity is already shared in the activity
     * stream</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add new page Step
     * Description: - Connect to [Intranet] - Click [Wiki] to open the Wiki
     * application - Click [Add Page] - -> [Blank Page]/[From Template...] - Enter
     * title and content for the Wiki page - Click [Save] Input Data: Expected
     * Outcome: - A page is added successful Step number: 2 Step Name: Step 2: Move
     * page Step Description: - Click [More], choose [Move Page] - Select
     * [Destination] and click [Move] Input Data: Expected Outcome: Page has been
     * moved to new place Step number: 3 Step Name: Step 3: Check wiki's activity
     * after move page Step Description: - Go to the Homepage Input Data: Expected
     * Outcome: - The wiki activity isn't updated - A comment is added in the
     * activity: Page has been moved to : $value. where $value is
     * :SpaceName>WikiRootPage>WikiRootPage2>..
     */
    @Test
    public void test08_UpdateWikisActivityAfterMoveOfWikiPage() {
        info("Check wiki's activity after create a wiki page for user");
        info("Create my wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        navigationToolbar.goToMyWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Check the Activity");
        homePagePlatform.goToHomePage();
        activityStream.checkNotShownActivity(title);
        navigationToolbar.goToMyWiki();
        wikiHomePage.deleteWiki(title);
        info("Update wiki's activity after move of wiki page");
        info("Create a wiki page 1");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);
        info("Like the activity");
        homePagePlatform.goToHomePage();
        activityStream.likeActivity(title1);
        info("Dislike the activity");
        activityStream.unlikeActivity(title1);
        info("Create a wiki page 2");
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(title2, content2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title2);
        info("Move the page");
        wikiHomePage.goToAPage(title1);
        wikiManagement.movePage(title1, title2);
        String comment = "";
        String value = title2 + " > " + title1;
        homePagePlatform.goToHomePage();
        activityStream.checkComment(title1, comment, value, ActivityStream.changeTypes.Has_One_Value);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title2);
    }

    /**
     * <li>Case ID:139203.</li>
     * <li>Test Case Name: Remove wiki's page of space.</li>
     * <li>Pre-Condition: Have a space</li>
     * <li>Post-Condition:</li> /*Step Number: 1 Step Name: Step 1: Add wiki for
     * space Step Description: - Connect to [Intranet] - Add a Wiki activity for
     * space Input Data: Expected Outcome: - The Wiki activity for spaceis displayed
     * in the activity stream Step number: 2 Step Name: Step 2: Check show delete
     * icon Step Description: - Back to activity stream - Move the mouse over the
     * Wiki activity for space Input Data: Expected Outcome: A (X) icon is displayed
     * in the top right panel of the activity Step number: 3 Step Name: Step 3:
     * Delete wiki page of space Step Description: - Go to [Space Setting] - Choose
     * [Application]tab - Select wiki and click (x) to delete wiki page Input Data:
     * Expected Outcome: - Wiki page is deleted successfully Step number: 4 Step
     * Name: Step 4: Check wiki's activity of space after delete wiki page Step
     * Description: - Back to activity stream Input Data: Expected Outcome: The Wiki
     * activity for spaceis removed from the activity stream
     */

    @Test
    @BugInPLF("WIKI-1440")
    public void test13_RemoveWikisPageOfSpace() {
        info("Test 13 Remove wiki's page of space");
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
        wikiValidattions.verifyTitleWikiPage(title);
        info("Remove Wiki application in the space");
        homePagePlatform.goToSpecificSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToApplicationTab();
        spaceSettingManagement.removeApplication("Wiki");
        info("Check on AS");
        homePagePlatform.goToHomePage();
        activityStream.checkNotShownActivity(title);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);

    }

    @Test
    public void test16_CheckLinksActivityStreamAfterRemoveAddWikiApplication() {
        //1448
        String space = "space" + getRandomNumber();
        String wiki = "wiki" + getRandomNumber();
        String app = "Wiki";
        String category = "Collaboration";
        String newTitle = "newTitle" + getRandomNumber();

        info("Create space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToApplicationTab();
        info("Remove application");
        spaceSettingManagement.removeApplication("Wiki");
        info("Add application");
        spaceSettingManagement.addApplication(category, app);
        info("Create wiki page");
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(wiki, wiki);
        wikiManagement.saveAddPage();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToEditPage();
        richTextEditor.editSimplePage(newTitle, newTitle);
        wikiManagement.publishPageWhenEditPage();
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleAndContentWikiPageInHomeSpace(newTitle,newTitle);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }
}
