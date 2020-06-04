package org.exoplatform.platform.ui.qa.wiki;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SOURCE_EDITOR_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TITLE_WIKI_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("wiki")
@Tag("smoke")
public class WikiBasicActionManagePageAddDeleteTestIT extends Base {
  HomePagePlatform homePagePlatform;

  WikiHomePage wikiHomePage;

  WikiManagement wikiManagement;

  SourceTextEditor sourceTextEditor;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    try {
      sourceTextEditor = new SourceTextEditor(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  /**
   * <li>Case ID:122806.</li>
   * <li>Test Case Name: Create page using Source Editor.</li>
   * <li>Post-Condition:</li>
   */

  @Test
  public void test01CreateDelete_PageUsingSourceEditor() {
    info("Create page using Source Editor");
    String wiki = "wiki" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Step 1: Create Page with Source Editor Step
     * Description: - Go to Wiki - Click on Add Page -> Blank Page icon in toolbar
     * action - Input valid data for Title page and Page's content - Click on Save
     * icon in toolbar Input Data: Expected Outcome: Page is added successful and
     * listed in navigation tree
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.appears, Configuration.timeout);
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourceTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    $(byText(wiki)).should(Condition.exist);
    info("Delete the page that is created by using source editor");
    /*
     * Step Number: 1 Step Name: Step 1: Delete page with Source Editor Step
     * Description: - Open an existing page by clicking on page name in navigation
     * tree. - Click on More icon on toolbar and select Delete page action in menu -
     * Click on OK button on Confirm message form Input Data: Expected Outcome:
     * Delete page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);
  }

}
