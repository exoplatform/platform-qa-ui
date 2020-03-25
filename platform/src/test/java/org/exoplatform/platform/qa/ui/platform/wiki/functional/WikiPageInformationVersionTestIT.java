package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

/**
 * Created by exo on 6/26/18.
 */

@Tag("wiki")
@Tag("functional")

public class WikiPageInformationVersionTestIT extends Base {

  HomePagePlatform    homePagePlatform;

  WikiHomePage        wikiHomePage;

  WikiManagement      wikiManagement;

  SourceTextEditor    sourceTextEditor;

  ManageLogInOut      manageLogInOut;

  WikiValidattions    wikiValidattions;

  WikiDraftPage       wikiDraftPage;

  RichTextEditor      richTextEditor;

  WikiPageInformation wikiPageInformation;

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
    wikiPageInformation = new WikiPageInformation(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:139297.</li>
   * <li>Test Case Name: View content of current version while view content of
   * other version.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create new page
   * Step Description: - Click [Add Page] - -> [Blank Page]/[From Template...] -
   * Add values in required fields - Click Save Input Data:
   * <p>
   * Expected Outcome: - By default, the [Create Wiki page] is displayed in the
   * [Rich Text] mode - New page is created successful. The number of page's
   * version is 1. Step Number: 1 Step Name: Step 1: Create new page Step
   * Description: - Click [Add Page] - -> [Blank Page]/[From Template...] - Add
   * values in required fields - Click Save Input Data:
   * <p>
   * Expected Outcome: - By default, the [Create Wiki page] is displayed in the
   * [Rich Text] mode - New page is created successful. The number of page's
   * version is 1. Step number: 2 Step Name: Step 2: Add version for page Step
   * Description: - Select page above - Click on Edit - Change values - Click Save
   * Input Data:
   * <p>
   * Expected Outcome: Page is edited. The number of page's version is 2. Step
   * number: 3 Step Name: Step 3: Add more version for page Step Description: Do
   * step 2 Input Data:
   * <p>
   * Expected Outcome: The number of page's version is increase after each edit
   * Step number: 4 Step Name: Step 4: Open form to view all version of page Step
   * Description: - Open created page at step 1 - Move mouse at the end of page -
   * Click on Revisions link Input Data:
   * <p>
   * Expected Outcome: All versions of page are shown Step number: 5 Step Name:
   * Step 5: View content of 1 version Step Description: - Click on the version
   * link want to see Input Data:
   * <p>
   * Expected Outcome: Content of selected version is shown Step number: 6 Step
   * Name: Step 6: Back to current version Step Description: - Click on current
   * version link Input Data:
   * <p>
   * Expected Outcome: Show content of current version
   */
  @Test
  public void test_ViewContentOfCurrentVersionThenContentOfOtherVersionThenCompareVersionWhenDoNotSelectAnyVersionThenWhenSelect2VersionsThenWhenSelectOnly1VersionThenViewChangeOfPageThenContentOfCurrentVersionWhileViewingOneVersion() {
    info("View content of current version while view content of other version");
    info("Create new page 1");
    String page = "page" + getRandomNumber();
    String pageContent = "pageContent" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page, pageContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page);
    info("Verify that the number of page's verison is 1");
    wikiValidattions.verifyVersionPage("V1");
    info("Add version for page");
    String newPage = "newPage" + getRandomNumber();
    String newPageContent = "newPageContent" + getRandomNumber();
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newPage, newPageContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newPage);
    info("Verify that the number of page's verison is 2");
    wikiValidattions.verifyVersionPage("V2");
    info(" Add more version for page");
    String newPage1 = "newPage1" + getRandomNumber();
    String newPageContent1 = "newPageContent1" + getRandomNumber();
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newPage1, newPageContent1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newPage1);
    info("Verify that the number of page's verison is 3");
    wikiValidattions.verifyVersionPage("V3");
    info("View Change of page");
    wikiHomePage.goToViewChange();
    wikiValidattions.verifyCompareVersionPage(newPageContent, newPageContent1);
    info("Change compare version");
    wikiPageInformation.changeCompareVersions("1", "2");
    $(byXpath(ELEMENT_VIEW_CHANGE_VERSION.replace("${version}", "Version 1"))).waitUntil(Condition.visible,
            Configuration.timeout);
    $(byXpath(ELEMENT_VIEW_CHANGE_VERSION.replace("${version}", "Version 2"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info("View content of 1 version");
    wikiPageInformation.viewVersion(1);
    wikiValidattions.verifyContentOfVersion(pageContent);
    info("Back to current version");
    wikiPageInformation.viewCurrentVersion();
    info("Show content of current version");
    wikiValidattions.verifyContentPage(newPageContent1);
    wikiHomePage.goToPageInformation(newPage1);
    info(" View content of other version");
    wikiPageInformation.viewVersion(3);
    wikiValidattions.verifyContentPage(newPageContent1);
    wikiHomePage.goToPageInformation(newPage1);
    info(" View content of other version");
    wikiPageInformation.viewVersion(2);
    wikiValidattions.verifyContentOfVersion(newPageContent);
    info("Compare version when do not select any version");
    wikiHomePage.goToPageInformation(newPage1);
    info("Compare versions");
    wikiPageInformation.goToPageHistory();
    info("Button Compare Selected is disable, user can't click on it");
    $(ELEMENT_COMPARE_VERISON_BTN_DISABLED).waitUntil(Condition.visible, Configuration.timeout);
    info("Compare version when select 2 versions");
    wikiHomePage.goToPageInformation(newPage1);
    info("Compare versions");
    wikiPageInformation.goToPageHistory();
    wikiPageInformation.compareTwoReversion("v.1", "v.2");
    info("Compare version when select only 1 version");
    info("Compare versions");
    wikiHomePage.goToPageInformation(newPage1);
    wikiPageInformation.goToPageHistory();
    wikiPageInformation.compareTwoReversion("v.1", "");
    info("Button Compare Selected is disable, user can't click on it");
    $(ELEMENT_COMPARE_VERISON_BTN_DISABLED).waitUntil(Condition.visible, Configuration.timeout);
    info("View page history");
    wikiHomePage.goToAPage(newPage1);
    wikiHomePage.goToPageInformation(newPage1);
    wikiPageInformation.goToPageHistory();
    wikiValidattions.verifyVersionsInHistoryPage("v.1");
    wikiValidattions.verifyVersionsInHistoryPage("v.2");
    wikiValidattions.verifyVersionsInHistoryPage("v.3");
    wikiHomePage.goToPageInformation(newPage1);
    info(" View content of current version While Viewing one Version");
    wikiPageInformation.viewVersion(2);
    wikiValidattions.verifyContentOfVersion(newPageContent);
    info("View content of other version");
    wikiPageInformation.viewConentVersionByNextArrow();
    wikiValidattions.verifyContentOfVersion(newPageContent);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139304.</li>
   * <li>Test Case Name: Restore version to current version.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create new page
   * Step Description: - Click [Add Page] - -> [Blank Page]/[From Template...] -
   * Add values in required fields - Click Save Input Data:
   * <p>
   * Expected Outcome: - By default, the [Create Wiki page] is displayed in the
   * [Rich Text] mode - New page is created successful. The number of page's
   * version is 1. Step number: 2 Step Name: Step 2: Add version for page Step
   * Description: - Select page above - Click on Edit - Change values - Click Save
   * Input Data:
   * <p>
   * Expected Outcome: Page is edited. The number of page's version is 2. Step
   * number: 3 Step Name: Step 3: Add more version for page Step Description: Do
   * step 2 Input Data:
   * <p>
   * Expected Outcome: The number of page's version is increase after each edit
   * Step number: 4 Step Name: Step 4: Open form to view all version of page Step
   * Description: - Open created page at step 1 - Move mouse at the end of page -
   * Click on Revisions link Input Data:
   * <p>
   * Expected Outcome: All versions of page are shown Step number: 5 Step Name:
   * Step 5: Restore version to current version Step Description: Click Restore
   * link corresponding with version want to be current version Input Data:
   * <p>
   * Expected Outcome: Selected version become current version. New version is
   * auto created.
   */
  @Test
  public void test05_ViewLinkOfRevisionsAndRestoreVersionToCurrentVersion() {
    info("Test 5: Restore version to current version");
    info("Create new page 1");
    String page = "page" + getRandomNumber();
    String pageContent = "pageContent" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page, pageContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page);

    info("Verify that the number of page's verison is 1");
    wikiValidattions.verifyVersionPage("V1");
    info("Add version for page");
    String newPage = "newPage" + getRandomNumber();
    String newPageContent = "newPageContent" + getRandomNumber();
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newPage, newPageContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newPage);
    info("Verify that the number of page's verison is 2");
    wikiValidattions.verifyVersionPage("V2");
    info(" Click Link Revisions");
    wikiHomePage.goToRevisions("V2");
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInHistoryPage("v.1");
    wikiValidattions.verifyVersionsInHistoryPage("v.2");;
    info(" Add more version for page");
    String newPage1 = "newPage1" + getRandomNumber();
    String newPageContent1 = "newPageContent1" + getRandomNumber();
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newPage1, newPageContent1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newPage1);

    info("Verify that the number of page's verison is 3");
    wikiValidattions.verifyVersionPage("V3");
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info("Compare versions");
    wikiPageInformation.goToPageHistory();
    wikiPageInformation.restoreVersion("1");
    info("Selected version become current version. New version is auto created");
    wikiValidattions.verifyContentPage(pageContent);
    wikiValidattions.verifyVersionPage("V4");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

}
