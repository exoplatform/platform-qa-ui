package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_STREAM_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;

@Tag("sniff")
@Tag("wiki")
public class WikiPublishActivityTestIT extends Base {
  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  RichTextEditor   richTextEditor;

  WikiValidattions wikiValidattions;

  SpaceHomePage    spaceHomePage;

  ActivityStream   activityStream;

  ManageLogInOut   manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    richTextEditor = new RichTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  /**
   * <li>Case ID:122869.</li>
   * <li>Test Case Name: Open Wiki page from wiki's activity</li>
   * <li>Pre-Condition: a wiki activity is already shred in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test03_OpenWikiPageFromWikiActivity() {
    info("Test 3: Open Wiki page from wiki's activity");

    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Step Description: - Connect to Intranet - From the
     * activity stream, click on the title of a wiki page displayed in activity of
     * wiki Input Data: Expected Outcome: - The wiki application is opened in the
     * correspond page.
     */
    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();

    info("Click on the title of wiki page");
    homePagePlatform.goToHomePage();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(title)).click();
    info("Verify that The wiki application is opened in the correspond page ");
    ELEMENT_WIKI_PAGE_CONTAINER.find(byText(title)).should(Condition.exist);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122865.</li>
   * <li>Test Case Name: Update activity - edit wiki page title</li>
   * <li>Pre-Condition: the wiki activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("wabis")
  public void test04_OpenWikiPageFromWikiActivity() {
    info("Test 04: Update activity - edit wiki page title");

    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    String newTitle = "newTitle" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Add new wiki page Step Description: - Login and
     * goto intranet - Click Wiki on left Navigation to go to Wiki Application -
     * Click add page-> Blank page - Fill the title and content and click save Input
     * Data: Expected Outcome: - Wiki page is created - An activity is added into
     * activity stream
     */
    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();

    info("Verify that an activity is added to the activity stream");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);

    /*
     * Step Number: 2 Step Name: Edit Wiki Page Step Description: - Goto Wiki page
     * and click Edit - Edit a title of a wiki page - Click save and check [Publish
     * Activity] - Go to the Homepage Input Data: Expected Outcome: - The wiki
     * activity is updated - A comment is added in the activity: Page's title has
     * been updated to: $value.
     */
    String comment = "Page's title has been updated to: " + newTitle;
    info("Go to Wiki porlet and select the wiki page created");
    homePagePlatform.goToWiki();
    $(byText(title)).click();
    info("Edit the title of the wiki page and check on published checkbox");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle, "");
    wikiManagement.publishPageWhenEditPage();
    wikiManagement.saveAddPage();
    info("The title of wiki page's activity is updated");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(newTitle);
    info("Verify that  A comment is added in the activity: Page's title has been updated to: " + newTitle);
    activityStream.checkCommentOfActivity(newTitle, comment);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }

  /**
   * <li>Case ID:122871.</li>
   * <li>Test Case Name: No comment is added to the activity when edit page not
   * checking Publish activiy</li>
   * <li>Pre-Condition: the wiki activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test05_NotAddCommentToActivityWhenEditPageIsNotCheckingPublishActivity() {
    info("Test 05: No comment is added to the activity when edit page not checking Publish activiy");

    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    String newTitle = "newTitle" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Add new wiki page Step Description: - Login and
     * goto intranet - Click Wiki on left Navigation to go to Wiki Application -
     * Click add page-> Blank page - Fill the title and content and click save Input
     * Data: Expected Outcome: - Wiki page is created - An activity is added into
     * activity stream
     */
    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();

    info("Verify that an activity is added to the activity stream");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);

    /*
     * Step Number: 2 Step Name: Edit wiki page and not check Publish activity Step
     * Description: - Go to Wiki application and open page above - Click [Edit] -
     * Edit title or content not check [Publish activity] - Save page - Go to
     * intranet homepage Input Data: Expected Outcome: - No comment is added to the
     * activity above.
     */
    String comment = "Page's title has been updated to: " + newTitle;
    info("Go to Wiki porlet and select the wiki page created");
    homePagePlatform.goToWiki();
    homePagePlatform.refreshUntil($(byText(title)),Condition.visible,1000);
    $(byText(title)).click();
    info("Edit the title of the wiki page and check on published checkbox");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle, "");
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);
    info("The title of wiki page's activity is updated");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(newTitle);
    info("Verify that No comment is added to the activity above");
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(comment)).shouldNot(Condition.exist);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }

  /**
   * <li>Case ID:122866.</li>
   * <li>Test Case Name: Update activity - edit wiki page with comments</li>
   * <li>Pre-Condition: the wiki activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("wabis")
  public void test06_EditWikiPageWithComments() {
    info("Test 06: Update activity - edit wiki page with comments");

    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    String newContent = "newContent" + getRandomNumber();
    String newContent1 = "newContent1" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Add new wiki page Step Description: - Login and
     * goto intranet - Click Wiki on left Navigation to go to Wiki Application -
     * Click add page-> Blank page - Fill the title and content and click save Input
     * Data: Expected Outcome: - Wiki page is created - An activity is added into
     * activity stream
     */
    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();

    info("Verify that an activity is added to the activity stream");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);

    /*
     * Step Number: 2 Step Name: Edit Wiki Page with comment Step Description: -
     * Goto Wiki page and click Edit - Edit wiki page with new content - Input
     * comment into Comment field - check [Publish Activity] and Click [save] - Go
     * to the Homepage Input Data: Expected Outcome: - The wiki activity is updated
     * - A comment is added to comment list containing the associated comment value
     */
    String comment1 = "Page's content has been edited.";
    info("Go to Wiki porlet and select the wiki page created");
    homePagePlatform.goToWiki();
    $(byText(title)).click();
    info("Edit the title of the wiki page and check on published checkbox");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title, newContent);
    wikiManagement.addComment(comment);
    wikiManagement.publishPageWhenEditPage();
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);
    info("The content of wiki page's activity is updated");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(newContent);
    info("Verify that only A comment that input above is added in the activity");
    activityStream.checkCommentOfActivity(title, comment);
    ELEMENT_WIKI_PAGE_CONTAINER.find(byText(comment1)).shouldNot(Condition.exist);

    /*
     * Step Number: 3 Step Name: Edit Wiki Page with no comment Step Description: -
     * goto Wiki page and click Edit - Edit wiki page with new content - Don't Input
     * comment into Comment field - check [Publish Activity] and Click [save] - Go
     * to the Homepage Input Data: Expected Outcome: - The wiki activity is updated
     * - A message is added: Page's content has been edited in comment list
     */
    info("Go to Wiki porlet and select the wiki page created");
    homePagePlatform.goToWiki();
    $(byText(title)).click();
    info("Edit the title of the wiki page and check on published checkbox");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title, newContent1);
    wikiManagement.publishPageWhenEditPage();
    wikiManagement.saveAddPage();
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    info("The content of wiki page's activity is updated");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(newContent1);

    info("Verify that only message is added in the activity");
    activityStream.checkCommentOfActivity(title, comment1);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122868.</li>
   * <li>Test Case Name: Update wiki's activity after moving a wiki page</li>
   * <li>Pre-Condition:the wiki activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("wabis")
  public void test07_UpdateWikiActivityAfterMovingAWikiPage() {
    info("Test 07: Update wiki's activity after moving a wiki page");

    String title1 = "" + getRandomNumber();
    String content1 = "" + getRandomNumber();

    String title2 = "" + getRandomNumber();
    String content2 = "" + getRandomNumber();
    String message = "Page has been moved to: intranet > Wiki Home > " + title2 + " > " + title1;
    /*
     * Step Number: 1 Step Name: Step Description: - Connect to Intranet - Open wiki
     * application - Move the wiki page to new place - Go to the Homepage Input
     * Data: Expected Outcome: - The wiki activity isn't updated - A comment is
     * added in the activity: Page has been moved to : $value. where $value is
     * :SpaceName>WikiRootPage>WikiRootPage2>..
     */
    info("Create first new wiki pages");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();

    info("Create second new wiki pages");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, content2);
    wikiManagement.saveAddPage();

    info("Move page 1 to page 2");
    wikiManagement.movePage(title1, title2);

    info("The wiki page's activity isnot updated");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title1);
    activityStream.checkActivity(content1);
    activityStream.checkCommentOfActivity(title1, message);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
  }

  @Tag("0")
  @Tag("WIKI-1392")
  @Test
  public void test08_CommentWikiActivityStream(){
    String wiki = "wiki" + getRandomNumber();
    String text= "text" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, text);
    wikiManagement.saveAddPage();
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(wiki);
    info("add the content of the wiki page as a comment on wiki activity stream");
    activityStream.commentWikiActivity(wiki, text);
    info("check that the comment is added successfully");
    activityStream.checkCommentOfActivity(wiki, text);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);
  }

  @Test
  @Tag("WIKI-1290")
  public void test08_checkFirst4linesDisplayedInAS() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String content1 = "content" + getRandomNumber();
    String content2 = "content" + getRandomNumber();
    String content3 = "content" + getRandomNumber();
    String content4 = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    if (ELEMENT_BUTTON_WIKI_RITCH_TEXT.is(Condition.visible)) {
      ELEMENT_BUTTON_WIKI_RITCH_TEXT.click();
    }
    $(ELEMENT_TITLE_WIKI_INPUT).setValue(title);
    switchTo().frame($(byClassName("gwt-RichTextArea")));
    $(byId("body")).sendKeys(content);
    $(byId("body")).pressEnter();
    $(byId("body")).sendKeys(content1);
    $(byId("body")).pressEnter();
    $(byId("body")).sendKeys(content2);
    $(byId("body")).pressEnter();
    $(byId("body")).sendKeys(content3);
    $(byId("body")).pressEnter();
    $(byId("body")).sendKeys(content4);
    $(byId("body")).pressEnter();
    switchTo().defaultContent();
    wikiManagement.saveAddPage();
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);
    activityStream.checkActivity(content);
    activityStream.checkActivity(content1);
    activityStream.checkActivity(content2);
    activityStream.checkActivity(content3);
    $(byText(content4)).shouldNot(Condition.exist);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);

  }
}
