package org.exoplatform.platform.ui.qa.wiki;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_BUTTON_WIKI_RITCH_TEXT;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_CONTENT_WIKI_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;

@Tag("wiki")
@Tag("smoke")
public class WikiBasicActionAddRichTextTestIT extends Base {

  HomePagePlatform  homePagePlatform;

  WikiHomePage      wikiHomePage;

  RichTextEditor    richTextEditor;

  WikiValidattions  wikiValidattions;

  WikiManagement    wikiManagement;

  ArrayList<String> arrayPage;

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

    arrayPage = new ArrayList<String>();

  }


  /**
   * <li>Case ID:139525.</li>
   * <li>Test Case Name: Add web page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test04_AddWebPage() {
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
    String title1 = "title1 Add web page";
    String content1 = "content1 Add web page";
    String label = "label Add web page" + getRandomNumber();
    String tooltip = "tooltip Add web page" + getRandomNumber();
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

  }

}
