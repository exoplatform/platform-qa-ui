package org.exoplatform.platform.ui.qa.wiki;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("wiki")
@Tag("smoke")
public class WikiBasicActionAddEditTestIT extends Base {

  HomePagePlatform homePagePlatform;

  WikiHomePage wikiHomePage;

  RichTextEditor richTextEditor;

  SourceTextEditor sourceTextEditor;

  WikiValidattions wikiValidattions;

  WikiManagement wikiManagement;

  ArrayList<String> arrayPage;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiValidattions = new WikiValidattions(this);
    wikiManagement = new WikiManagement(this);
    try {
      richTextEditor = new RichTextEditor(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      sourceTextEditor = new SourceTextEditor(this);
    } catch (Exception e) {
      e.printStackTrace();
    }

    arrayPage = new ArrayList<String>();
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signIn(PLFData.username, PLFData.password);
  }

  /**
   * <li>Case ID:139522.</li>
   * <li>Test Case Name: Add a page with link wiki page existed.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_AddAPageWithLinkWikiPageExisted() {
    info("Add a page with link wiki page existed");
    /*
     * Step Number: 1 Step Name: Step 1: Add a page with link wiki page Step
     * Description: - Go to [Intranet] - -> [Wiki] - Click [Add Page] - -> [Blank
     * Page] - Ensure the page is in the [Rich Text]editor - Input data valid for
     * Title page and Page's content - Click [Link] in menu - Select [Wiki Page] -
     * Select [All pages] tab - Choose a page in list and click [Select] - Input
     * label and tooltip for link - Check or uncheck [Open in new window] - Click
     * [Create Link] - Click [Save] icon in toolbar Input Data: Expected Outcome: -
     * By default, the [Create Wiki page] is displayed in the [Rich Text] mode - A
     * new page is added successful and displayed with properties - This page is
     * listed with page containing the link
     */

    info("Create a wiki page 1");
    String title1 = "title" + getRandomNumber();
    String content1 = "content" + getRandomNumber();
    String title3 = "title" + getRandomNumber();
    String content3 = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title1);
    arrayPage.add(title1);

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.appears, Configuration.timeout);
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourceTextEditor.addSimplePage(title3, content3);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title3);
    arrayPage.add(title3);

    info("Edit a wiki page");
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    wikiHomePage.goToAPage(title3);
    wikiHomePage.goToEditPage();
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourceTextEditor.editSimplePage(newTitle, newContent);
    wikiManagement.saveAddPage();
    sleep(2000);
    wikiValidattions.verifyTitleWikiPage(newTitle);
    arrayPage.add(newTitle);

    info("Create a wiki page 2");
    String title2 = "title2" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    String label = "label" + getRandomNumber();
    String tooltip = "tooltip" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    if ($(ELEMENT_CONTENT_WIKI_INPUT).is((Condition.exist))) {
      ELEMENT_BUTTON_WIKI_RITCH_TEXT.click();
    }

    richTextEditor.addSimplePage(title2, content2);
    richTextEditor.goToWikiPageLink();
    sleep(2000);
    richTextEditor.insertExistWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.All_pages);
    sleep(2000);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title2);
    arrayPage.add(title2);

    /*
     * Step number: 2 Step Name: Step 2: View link after add Step Description: -
     * Click on name of link Input Data: Expected Outcome: - Page is shown
     * successfully
     */
    info("Page is shown successfully");
    wikiHomePage.goToAPage(title2);
    wikiValidattions.verifyInsertedExistLink(label, title1);
    wikiHomePage.deleteWiki(title1);
    wikiHomePage.deleteWiki(newTitle);
    wikiHomePage.deleteWiki(title2);

  }

  /**
   * <li>Case ID:139525.</li>
   * <li>Test Case Name: Add web page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test02_AddWebPage() {
    info("Test 4: Add web page");
    /*
     * Step Number: 1 Step Name: Step 1: Add web page Step Description: - Go to
     * [Intranet] - -> [Wiki] - Click Click [Add Page] - -> [Blank Page] - Input
     * data valid for Title page and Page's content -Click [Link] in menu - Select
     * [Web Page...] - Type the address of the web page to create the link to. (ex:
     * www.google.com) - Input label and tooltip for link - Check or uncheck [Open
     * in new window] - Click [Create Link] - Click [Save] icon in toolbar Input
     * Data: Expected Outcome: - By default, the [Create Wiki page] is displayed in
     * the [Rich Text] mode - A new page is added successfully and displayed with
     * properties - This page is listed with page containing the link
     */
    info("Create a wiki page 2");
    String title1 = "title1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String label = "label" + getRandomNumber();
    String tooltip = "tooltip" + getRandomNumber();
    String address = "www.google.com";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    richTextEditor.goToWebPageLink();
    richTextEditor.insertWebLink(address, label, tooltip, true);
    wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title1);
    arrayPage.add(title1);

    /*
     * Step number: 2 Step Name: Step 2: View link after add Step Description: -
     * Click on name of link Input Data: Expected Outcome: - Show page successfully
     */
    info("Page is shown successfully");
    wikiHomePage.goToAPage(title1);
    wikiManagement.viewInsertLink(label);
    String titlePage = this.getExoWebDriver().getWebDriver().getTitle();
    if (titlePage.contains("Google"))
      assert true;
    else
      assert false;
    back();
    wikiHomePage.deleteWiki(title1);
  }

}
