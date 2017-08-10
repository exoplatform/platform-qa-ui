package org.exoplatform.platform.ui.qa.wiki;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SOURCE_EDITOR_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TITLE_WIKI_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;

@Tag("wiki")
@Tag("smoke")
public class WikiBasicActionEditTestIT extends Base {

  HomePagePlatform  homePagePlatform;

  WikiHomePage      wikiHomePage;

  WikiManagement    wikiManagement;

  SourceTextEditor  sourcetexteditor;

  WikiValidattions  wikiValidattions;

  ArrayList<String> arrayPage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    wikiValidattions = new WikiValidattions(this);
    wikiManagement = new WikiManagement(this);
    wikiHomePage = new WikiHomePage(this);
    try {
      sourcetexteditor = new SourceTextEditor(this);
    } catch (Exception e) {
      e.printStackTrace();
    }

    arrayPage = new ArrayList<String>();

  }

  /**
   * <li>Case ID:139427.</li>
   * <li>Test Case Name: Edit page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_EditPage() {
    info("Test 1: Edit page");
    /*
     * Step Number: 1 Step Name: Step 1: Create a page Step Description: - Click
     * [Add Page] - -> [Blank Page]/[From Template...] - Select [Source Editor] to
     * switch to [Source Editor] mode - Put title, content - Click [Save] Input
     * Data: Expected Outcome: - By default, the [Create Wiki page] is displayed in
     * the [Rich Text] mode -New page is created successfully
     */
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.appears, Configuration.timeout);
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourcetexteditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    arrayPage.add(title);

    /*
     * Step number: 2 Step Name: Step 2: Edit page Step Description: - Select the
     * page above - Click [Edit] - Change properties - Click [Save] Input Data:
     * Expected Outcome: - The [Edit Page] is shown in [Source Editor] mode - Page
     * is edited
     */
    info("Edit a wiki page");
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourcetexteditor.editSimplePage(newTitle, newContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newTitle);
    arrayPage.add(newTitle);
    wikiHomePage.deleteWiki(newTitle);

  }

}
