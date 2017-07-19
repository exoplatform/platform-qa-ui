package org.exoplatform.platform.ui.qa.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

@Tag("wiki")
@Tag("smoke")
public class WikiPublishActivityTestIT extends Base {

  HomePagePlatform homePagePlatform;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  RichTextEditor   richTextEditor;

  ActivityStream   activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    activityStream = new ActivityStream(this);
    try {
      richTextEditor = new RichTextEditor(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * <li>Case ID:122864.</li>
   * <li>Test Case Name: Create new wiki page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_CreateNewWikiPage() {
    info("Test 01: Create new wiki page");
    String title = "title" + getRandomNumber();
    String line1 = "line1" + getRandomNumber();
    String line2 = "line2" + getRandomNumber();
    String line3 = "line3" + getRandomNumber();
    String line4 = "line4" + getRandomNumber();
    String line5 = "line5" + getRandomNumber();
    String content = line1 + "</br>" + line2 + "</br>" + line3 + "</br>" + line4 + "</br>" + line5;

    /*
     * Step Number: 1 Step Name: Step 1: Add new wiki page Step Description: -
     * Login and goto intranet - Click Wiki on left Navigation to go to Wiki
     * Application - Click add page-> Blank page - Fill the title and content
     * and click save Input Data: Expected Outcome: Wiki page is created.
     */
    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    info("Verify that the new wiki page is created successfully");
    $(byText(title)).should(Condition.exist);
    /*
     * Step Number: 2 Step Name: Step 2: Check activity stream Step Description:
     * - Goto homepage and check stream Input Data: Expected Outcome: - An
     * activity is added into stream for wiki page - Informations that are
     * displayed in the featured content : 1. Wiki page's title 2. Wiki page's
     * version 3. First 4 lines of the wiki page
     */
    homePagePlatform.goToHomePage();
    activityStream.checkActivityAddWikiPage(title, content, null);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122867.</li>
   * <li>Test Case Name: Delete wiki page</li>
   * <li>Pre-Condition: the wiki activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test02_DeleteWikiPage() {
    info("Test 2: Delete wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Step 1: Add new wiki page Step Description: -
     * Login and goto intranet - Click Wiki on left Navigation to go to Wiki
     * Application - Click add page-> Blank page - Fill the title and content
     * and click save Input Data: Expected Outcome: Wiki page is created and an
     * activity is added into activity stream.
     */
    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    info("Verify that the new wiki page is created successfully");
    $(byText(title)).should(Condition.exist);
    info("Verify that an activity is added to the activity stream");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);

    /*
     * Step number: 2 Step Name: Step 2: Delete Wiki Page Step Description: -
     * Goto Wiki page and click [More] -> [Delete Page] - Go to the Homepage to
     * check Input Data: Expected Outcome: Activity of the wiki page is removed
     * from the activity stream
     */
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
    homePagePlatform.goToHomePage();
    $(byText(title)).shouldNot(Condition.exist);
  }

}
