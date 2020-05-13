package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

/**
 * @author eXo
 */
@Tag("sniff")
@Tag("wiki")
public class WikiSettingsTestIT extends Base {
  HomePagePlatform      homePagePlatform;

  SpaceManagement       spaceManagement;

  WikiHomePage          wikiHomePage;

  WikiManagement        wikiManagement;

  SourceTextEditor      sourceTextEditor;

  WikiValidattions      wikiValidattions;

  SpaceHomePage         spaceHomePage;

  ActivityStream        activityStream;

  WikiSettingManagement wikiSettingManagement;

  RichTextEditor        richTextEditor;

  PlatformPermission    platformPermission;

  ManageLogInOut        manageLogInOut;

  WikiPermission        wikiPermission;

  TestBase              testBase;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    wikiSettingManagement = new WikiSettingManagement(this);
    richTextEditor = new RichTextEditor(this);
    platformPermission = new PlatformPermission(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiPermission = new WikiPermission(this);
    testBase = new TestBase();
    if ($(ELEMENT_SKIP_BUTTON).is(exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  /*
   * Step Number: 1 Step Name: Step 1: Add new template Step Description: - Click
   * on Browse at top right and select Wiki Settings action - Click Template tab
   * -> Select Add More Templates button - Input all fields required and click
   * Save template Input Data: Expected Outcome: - Template is added successfully
   * and listed in Template table
   * <li>Case ID:122832.</li>
   * <li>Test Case Name: Edit template.</li>
   */
  @Test
  @Tag("wabis")
  public void test02_Add_EditTemplateThenWithoutTitleThenWithExistedTitle() {
    info("Test 2: Edit template");
    String defaulyTitle = "Sample Template Title...";
    String title = "title" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String description = "description" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String message="The page title already exists. Please select another one.";
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    ELEMENT_WIKI_BUTTON_ADD_MORE_TEMPLATE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    $(ELEMENT_SAVE_TEMPLATE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_WIKI_OK_SAVE_TEMPLATE.waitUntil(Condition.appears,Configuration.timeout).click();
    ELEMENT_WIKI_LISTE_TEMPLATE.find(byText(defaulyTitle)).should(exist);
    wikiSettingManagement.deleteTemplate(defaulyTitle);
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    ELEMENT_WIKI_BUTTON_ADD_MORE_TEMPLATE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    wikiSettingManagement.addPreviewTemplate(title, description, content);
    ELEMENT_WIKI_BUTTON_ADD_MORE_TEMPLATE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_TITLE_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).setValue(title);
    $(ELEMENT_DESCRIPTION_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).setValue(description);
    $(ELEMENT_CONTENT_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).setValue(content);
    $(ELEMENT_SAVE_TEMPLATE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byText(message)).should(exist);
    ELEMENT_BUTTON_OK_IN_WARNING_POPUB_TEMPLATE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CANCEL_TEMPLATE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    wikiHomePage.goToWikiSettingPage();
    ELEMENT_WIKI_LISTE_TEMPLATE.find(byText(title)).parent().parent().find(ELEMENT_WIKI_ICON_EDIT_TEMPLATE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    wikiSettingManagement.editTemplate("", newTitle, "", "");
    ELEMENT_WIKI_LISTE_TEMPLATE.find(byText(newTitle)).should(exist);
    wikiHomePage.goToWikiSettingPage();
    wikiSettingManagement.deleteTemplate(newTitle);

  }

  /**
   * <li>Case ID:122840.</li>
   * <li>Test Case Name: Add Permission for Wiki.</li>
   * <li>Pre-Condition: A wiki page is created at intranet wiki.</li>
   * <li>Case ID:122842.</li>
   * <li>Test Case Name: Edit permission for Wiki.</li>
   * <li>Case ID:122843.</li>
   * <li>Test Case Name: Delete permission for Wiki.</li>
   * Step Number: 1 Step Name: Step 1: Add Permission Step Description: - Check
   * the header (top bar) of the Wiki - Click Browse button at top right and
   * select Wiki Settings action, choose Permission tab - Click select user/select
   * group/select membership then click Add - Tick permission for her/him - Click
   * Save Input Data: Expected Outcome: New permission is added in list Step
   * number: 2 Step Name: Step 2: Check permission of added user Step Description:
   * - Login as user or member of the group above - Go to wiki application - Open
   * the wiki page at step 1 Input Data: Expected Outcome: - This user can do
   * actions as permission set in step 1.
   */

  @Test
  @Tag("wabis")
  public void test07_AddEditDeletePermissionForWiki() {
    info("Test 5: Add Permission for Wiki");
    String wiki = "wiki" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();

    wikiHomePage.goToPermissions();
    $(byText("any")).parent().parent().find(ELEMENT_WIKI_ICON_DELETE_PERMISSION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    wikiPermission.addPermisisonByType(DATA_USER2);
    ELEMENT_WIKI_ICON_EDIT_PERMISSION_FOR_MARY.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SAVE_PERMISSION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("Check if mary can see the page");
    homePagePlatform.goToWiki();
    wikiHomePage.selectAPage(wiki);
    // check if mary has the rights
    wikiHomePage.goToEditPage();
    info("Test 7: Delete permission for Wiki");
    // remove the permission for mary
    manageLogInOut.signIn(username, password);
    homePagePlatform.goToWiki();
    wikiHomePage.selectAPage(wiki);
    wikiHomePage.goToPermissions();
    $(byText(DATA_NAME_USER2)).parent().parent().find(ELEMENT_WIKI_ICON_DELETE_PERMISSION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SAVE_PERMISSION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToWiki();
    $(byText(wiki)).shouldNot(exist);

    // delete data
    manageLogInOut.signIn(username, DATA_PASS);
    homePagePlatform.goToWiki();
    wikiHomePage.selectAPage(wiki);
    wikiHomePage.deleteWiki(wiki);
  }

  /**
   * <li>Case ID:122845.</li>
   * <li>Test Case Name: Delete permission for space wiki.</li>
   * Step Number: 1 Step Name: Step 1: Add Permission Step Description: - Open a
   * page of the space that you want to set the permissions. - Click More -> Page
   * Permission - Choose select user/select group/select membership then click Add
   * - Click Save Input Data: Expected Outcome: New permission is added in list
   * Step number: 2 Step Name: Step 2: Check permission of added user Step
   * Description: - Login as user or member of the group above - Open the wiki
   * page at step 1 Input Data: Expected Outcome: - This user can do actions as
   * permission set in step 1.
   */

  @Test
  @Tag("wabis")
  public void test10_DeletePermissionForSpaceWiki() throws Exception {
    info("Test 8: Add Permission for space wiki");
    String space = "" + getRandomNumber();
    String wiki = "" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, "");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    wikiHomePage.goToPermalink();
    String perLink = ELEMENT_WIKI_PERMELINK.getValue();
    $(ELEMENT_PERMALINK_CLOSE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    wikiHomePage.goToPermissions();
    wikiPermission.addPermisisonByType(DATA_USER2);
    ELEMENT_WIKI_ICON_EDIT_PERMISSION_FOR_MARY.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SAVE_PERMISSION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink);
    refresh();
    wikiHomePage.goToEditPage();
    homePagePlatform.refreshUntil($(byText(wiki)),exist,2000);

    info("Test 9: Delete permission for space wiki");
    manageLogInOut.signIn(username, password);

    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    spaceHomePage.goToWikiTab();
    wikiHomePage.selectAPage(wiki);
    wikiHomePage.goToPermissions();
    $(byText(DATA_NAME_USER2)).parent().parent().find(ELEMENT_WIKI_ICON_DELETE_PERMISSION).click();
    $(ELEMENT_SAVE_PERMISSION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink);
    homePagePlatform.refreshUntil($(byText("Page Not Found")),exist,2000);
    // delete data

    manageLogInOut.signIn(username, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }
}
