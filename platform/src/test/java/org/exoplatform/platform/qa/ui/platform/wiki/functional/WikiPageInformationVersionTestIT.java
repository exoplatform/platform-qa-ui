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
  public void test01_ViewContentOfCurrentVersionWhileViewContentOfOtherVersion() {
    info("Test 1: View content of current version while view content of other version");
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
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139298.</li>
   * <li>Test Case Name: Compare version when do not select any version.</li>
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
   * Step 5: Compare versions Step Description: - Do not select any checkbox -
   * Click on Compare Selected Input Data:
   * <p>
   * Expected Outcome: Button Compare Selected is disable, user can't click on it
   */
  @Test
  public void test02_CompareVersionWhenDoNotSelectAnyVersion() {
    info("Test 2: Compare version when do not select any version");
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
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info("Compare versions");
    wikiPageInformation.goToPageHistory();
    info("Button Compare Selected is disable, user can't click on it");
    $(ELEMENT_COMPARE_VERISON_BTN_DISABLED).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139299.</li>
   * <li>Test Case Name: Compare version when select 2 versions.</li>
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
   * Step 5: Compare versions Step Description: - Tick on check -box of 2 versions
   * - Click on Compare Selected Input Data:
   * <p>
   * Expected Outcome: Content of 2 versions are compared
   */
  @Test
  public void test03_CompareVersionWhenSelect2Versions() {
    info("Test 3: Compare version when select 2 versions");
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
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info("Compare versions");
    wikiPageInformation.goToPageHistory();
    wikiPageInformation.compareTwoReversion("v.1", "v.2");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139300.</li>
   * <li>Test Case Name: Compare version when select only 1 version.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create new page
   * Step Description: - Click [Add Page] - -> [Blank Page]/[From Template...] -
   * Add values in required fields - Click Save Input Data:
   * <p>
   * Expected Outcome: - By default, the [Create Wiki page] is displayed in the
   * [Rich Text] mode - New page is created successfully. The number of page's
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
   * Step 5: Compare versions Step Description: - Tick on check -box of 1 version
   * - Click on Compare Selected Input Data:
   * <p>
   * Expected Outcome: Button Compare Selected is disable, user can't click on it
   */
  @Test
  public void test04_CompareVersionWhenSelectOnly1Version() {
    info("Test 4: Compare version when select only 1 version");
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
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info("Compare versions");
    wikiPageInformation.goToPageHistory();
    wikiPageInformation.compareTwoReversion("v.1", "");
    info("Button Compare Selected is disable, user can't click on it");
    $(ELEMENT_COMPARE_VERISON_BTN_DISABLED).waitUntil(Condition.visible, Configuration.timeout);
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
  public void test05_RestoreVersionToCurrentVersion() {
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

  /**
   * <li>Case ID:139305.</li>
   * <li>Test Case Name: View Change of page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create new page
   * Step Description: - Using Firefox, login as root - Click [Add Page] - ->
   * [Blank Page]/[From Template...] - Add values in required fields - Click Save
   * Input Data:
   * <p>
   * Expected Outcome: - By default, the [Create Wiki page] is displayed in the
   * [Rich Text] mode - New page is created successful. The number of page's
   * version is 1. Step number: 2 Step Name: Step 2: Default view change Step
   * Description: - Select page above - Check link view change Input Data:
   * <p>
   * Expected Outcome: View change link is not shown Step number: 3 Step Name:
   * Step 3: Add version for page Step Description: - Select page above - Click on
   * Edit - Change values - Click Save Input Data:
   * <p>
   * Expected Outcome: Page is edited. The number of page's version is 2. Step
   * number: 4 Step Name: Step 4: Add more version for page Step Description: Do
   * step 3 again Input Data:
   * <p>
   * Expected Outcome: The number of page's version is increase after each editEg:
   * This page has 3 versions Step number: 5 Step Name: Step 5: View change Step
   * Description: - Click link View change Input Data:
   * <p>
   * Expected Outcome: Display compare page between current version andthe before
   * version Step number: 6 Step Name: Step 6: Change compare version Step
   * Description: - Click link Changes from 1 to 2 Input Data:
   * <p>
   * Expected Outcome: Display compare page between version 1 and version 2 Step
   * number: 7 Step Name: Step 7:View page history Step Description: - Click link
   * View Page History Input Data:
   * <p>
   * Expected Outcome: - Display Page History with all version of page - User can
   * compare and restore version
   */
  @Test
  public void test06_ViewChangeOfPage() {
    info("Test 6: View Change of page");
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
    info("View change link is not shown");
    $(ELEMENT_INFOR_BAR_VIEW_CHANGE_LINK).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
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
    info("View change");
    wikiHomePage.goToViewChange();
    wikiValidattions.verifyCompareVersionPage(newPageContent, newPageContent1);
    info("Change compare version");
    wikiPageInformation.changeCompareVersions("1", "2");
    $(byXpath(ELEMENT_VIEW_CHANGE_VERSION.replace("${version}", "Version 1"))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    $(byXpath(ELEMENT_VIEW_CHANGE_VERSION.replace("${version}", "Version 2"))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    info("View page history");
    wikiHomePage.goToAPage(newPage1);
    wikiHomePage.goToPageInformation(newPage1);
    wikiPageInformation.goToPageHistory();
    wikiValidattions.verifyVersionsInHistoryPage("v.1");
    wikiValidattions.verifyVersionsInHistoryPage("v.2");
    wikiValidattions.verifyVersionsInHistoryPage("v.3");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139306.</li>
   * <li>Test Case Name: View content of current version.</li>
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
   * Step 5: View content of current version Step Description: - Click on current
   * version link Input Data:
   * <p>
   * Expected Outcome: Content of current version is shown
   */
  @Test
  public void test07_ViewContentOfCurrentVersion() {
    info("Test 7: View content of current version");
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
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info(" View content of current version");
    wikiPageInformation.viewVersion(3);
    wikiValidattions.verifyContentPage(newPageContent1);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139307.</li>
   * <li>Test Case Name: View content of other version.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Create new page Input Data: - Click [Add Page] - -> [Blank Page]/[From
   * Template...] - Add values in required fields - Click Save Expected Outcome: -
   * By default, the [Create Wiki page] is displayed in the [Rich Text] mode - New
   * page is created successful. The number of page's version is 1. Step number: 2
   * Step Name: - Step Description: Step 2: Add version for page Input Data: -
   * Select page above - Click on Edit - Change values - Click Save Expected
   * Outcome: Page is edited. The number of page's version is 2. Step number: 3
   * Step Name: - Step Description: Step 3: Add more version for page Input Data:
   * Do step 2 Expected Outcome: The number of page's version is increase after
   * each edit Step number: 4 Step Name: - Step Description: Step 4: Open form to
   * view all version of page Input Data: - Open created page at step 1 - Move
   * mouse at the end of page - Click on Revisions link Expected Outcome: All
   * versions of page are shown Step number: 5 Step Name: - Step Description: Step
   * 5: View content of other version Input Data: - Click on the version link want
   * to see Expected Outcome: Content of selected version is shown
   */
  @Test
  public void test08_ViewContentOfOtherVersion() {
    info("Test 8: View content of other version");
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
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info(" View content of current version");
    wikiPageInformation.viewVersion(2);
    wikiValidattions.verifyContentOfVersion(newPageContent);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139308.</li>
   * <li>Test Case Name: View content of other version while viewing 1
   * version.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Create new page Input Data: - Click [Add Page] - -> [Blank Page]/[From
   * Template...] - Add values in required fields - Click Save Expected Outcome: -
   * By default, the [Create Wiki page] is displayed in the [Rich Text] mode - New
   * page is created successful. The number of page's version is 1. Step number: 2
   * Step Name: - Step Description: Step 2: Add version for page Input Data: -
   * Select page above - Click on Edit - Change values - Click Save Expected
   * Outcome: Page is edited. The number of page's version is 2. info("Add version
   * for page"); Step number: 3 Step Name: - Step Description: Step 3: Add more
   * version for page Input Data: Do step 2 Expected Outcome: The number of page's
   * version is increase after each edit Step number: 4 Step Name: - Step
   * Description: Step 4: Open form to view all version of page Input Data: - Open
   * created page at step 1 - Move mouse at the end of page - Click on Revisions
   * link Expected Outcome: All versions of page are shown Step number: 5 Step
   * Name: - Step Description: Step 5: View content of 1 version Input Data: -
   * Click on the version link want to see Expected Outcome: Content of selected
   * version is shown Step number: 6 Step Name: - Step Description: Step 6: View
   * content of other version Input Data: - Click on Prev or Next link to view
   * content of other versions Expected Outcome: Content of pre/next version is
   * shown
   */
  @Test
  public void test09_ViewContentOfOtherVersionWhileViewing1Version() {
    info("Test 9: View content of other version while viewing 1 version");
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
    info("Open all version of the page");
    wikiHomePage.goToPageInformation(newPage1);
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInPage(1);
    wikiValidattions.verifyVersionsInPage(2);
    wikiValidattions.verifyVersionsInPage(3);
    info(" View content of current version");
    wikiPageInformation.viewVersion(2);
    wikiValidattions.verifyContentOfVersion(newPageContent);
    info("View content of other version");
    wikiPageInformation.viewConentVersionByNextArrow();
    wikiValidattions.verifyContentOfVersion(newPageContent);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }

  /**
   * <li>Case ID:139309.</li>
   * <li>Test Case Name: View Link of Revisions.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Create new page Input Data: - Click [Add Page] - -> [Blank Page]/[From
   * Template...] - Add values in required fields - Click Save Expected Outcome: -
   * By default, the [Create Wiki page] is displayed in the [Rich Text] mode - New
   * page is created successful. The number of page's version is 1. Step number: 2
   * Step Name: - Step Description: Step 2: Add version for page Input Data: -
   * Select page above - Click on Edit - Change values - Click Save Expected
   * Outcome: Page is edited. The number of page's version is 2. Step number: 3
   * Step Name: - Step Description: Step 3: Click Link Revisions Input Data: -
   * Click on Link revisions Expected Outcome: Corresponding information of all
   * versions is displayed in table format
   */
  @Test
  public void test10_ViewLinkOfRevisions() {
    info("Test 10 View Link of Revisions");
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
    wikiValidattions.verifyVersionsInHistoryPage("v.2");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage);
  }

  /**
   * <li>Case ID:139310.</li>
   * <li>Test Case Name: View Link of Revisions when edit page content by another
   * user.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: Step 1:
   * Create new page Input Data: - Using Firefox, login as root - Click [Add Page]
   * - -> [Blank Page]/[From Template...] - Add values in required fields - Click
   * Save Expected Outcome: - By default, the [Create Wiki page] is displayed in
   * the [Rich Text] mode - New page is created successful. The number of page's
   * version is 1. Step number: 2 Step Name: - Step Description: Step 2: Add
   * version for page Input Data: - Select page above - Click on Edit - Change
   * values - Click Save Expected Outcome: Page is edited. The number of page's
   * version is 2. Step number: 3 Step Name: - Step Description: Step 3: Open
   * another browser Input Data: - Using IE, login as john - Open page which has
   * just created Expected Outcome: Page is displayed. The number of page's
   * version is still 2. Step number: 4 Step Name: - Step Description: Step 4:
   * Edit page content again Input Data: - At root page, click Edit again - Change
   * values - Click Save Expected Outcome: Page is edited. The number of page's
   * version is 3.* Step number: 5 Step Name: - Step Description: Step 5: Click
   * Link Revisions Input Data: - At john page, Click on Link revisions Expected
   * Outcome: - The number of page's version is 3. - Corresponding information of
   * all versions is displayed in table format with newest version
   */
  @Test
  public void test11_ViewLinkOfRevisionsWhenEditPageContentByAnotherUser() {
    info("Test 11 View Link of Revisions when edit page content by another user");
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");

    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(newPage);

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
    info(" Click Link Revisions");
    wikiHomePage.goToRevisions("V3");
    info("Verify that all versions of page are shown");
    wikiValidattions.verifyVersionsInHistoryPage("v.1");
    wikiValidattions.verifyVersionsInHistoryPage("v.2");
    wikiValidattions.verifyVersionsInHistoryPage("v.3");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newPage1);

  }
}
