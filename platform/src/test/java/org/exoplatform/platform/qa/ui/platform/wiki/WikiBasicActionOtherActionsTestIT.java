package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_CANCEL_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_BUTTON_EVENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
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
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiPermission;

@Tag("wiki")
@Tag("sniff")
public class WikiBasicActionOtherActionsTestIT extends Base {

  HomePagePlatform       homePagePlatform;

  WikiHomePage           wikiHomePage;

  ManageLogInOut         manageLogInOut;

  WikiManagement         wikiManagement;

  RichTextEditor         richTextEditor;

  NavigationToolbar      navigationToolbar;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  WikiPermission         wikiPermission;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiManagement = new WikiManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    wikiPermission = new WikiPermission(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }

    manageLogInOut.signInCas("john", "gtngtn");

    richTextEditor = new RichTextEditor(this);

    spaceSettingManagement = new SpaceSettingManagement(this);

  }


  /**
   * <li>Case ID:122810.</li>
   * <li>Test Case Name: Move Page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> issue WIKI-976, wrong address in the mail
   * notification
   */
  @Test
  public void test02_MovePage_Intranet_MyWiki_Then__MyWiki_Intranet() {

    String title1 = "title1" + getRandomNumber();
    String title2 = "title1" + getRandomNumber();

    info("Create first new wiki pages");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    $(byText(title1)).should(Condition.exist);

    info("Create second new wiki pages");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    $(byText(title2)).should(Condition.exist);

    info("Move page 1 to page 2");
    wikiManagement.movePage(title1, title2);
    wikiHomePage.goToPageInformation(title2);
    $(byClassName("uiPageInfoHierarchy")).find(byText(title1)).should(Condition.exist);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    $(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("Move page to Mywiki");
    wikiManagement.selectSpaceDestination("My Wiki");
    navigationToolbar.goToMyWiki();
    $(byClassName("uiTreeExplorer")).find(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    wikiHomePage.deleteWiki(title1);


    navigationToolbar.goToMyWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    $(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("Move page to Intranet");
    wikiManagement.selectSpaceDestination("Intranet");
    homePagePlatform.goToWiki();
    $(byClassName("uiTreeExplorer")).find(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    wikiHomePage.deleteWiki(title2);

  }

  @Test
  @Tag("wabis")
  public void test04_MovePage_MyWiki_Space_Then_Space_MyWiki() {

    info("Create a space");
    String space = "space" + getRandomNumber();
    String title1 = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    info("Create a wiki page");
    navigationToolbar.goToMyWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    $(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("Move page to Space");
    wikiManagement.selectSpaceDestination(space);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    ELEMENT_SPACES_LIST.find(byText(space)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs).click();
    spaceHomePage.goToWikiTab();
    $(byClassName("uiTreeExplorer")).find(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    info("Create a wiki page in space");
    spaceManagement.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    $(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("Move page to MyWiki");
    wikiManagement.selectSpaceDestination("My Wiki");
    navigationToolbar.goToMyWiki();
    $(byClassName("uiTreeExplorer")).find(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    wikiHomePage.deleteWiki(title2);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test06_MovePage_Intranet_Space_Then_Space_Intranet() {

    info("Create a space");
    String space = "space" + getRandomNumber();
    String title1 = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();


    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    info("Create a wiki page ");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    $(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("Move page to Space");
    wikiManagement.selectSpaceDestination(space);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToWikiTab();
    $(byClassName("uiTreeExplorer")).find(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    info("Create a wiki page in space");
    spaceManagement.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    $(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("Move page to Intranet");
    wikiManagement.selectSpaceDestination("Intranet");
    homePagePlatform.goToWiki();
    $(byClassName("uiTreeExplorer")).find(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    wikiHomePage.deleteWiki(title2);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  @Tag("wabis")
  public void test08_MovePage_Space2_Space1() {

    info("Create a space");
    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2, 6000);

    info("Create a wiki page in space2");
    String title1 = "title1" + getRandomNumber();
    spaceManagement.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    $(byText(title1)).should(Condition.exist);

    info("Move page to Space1");

    wikiManagement.selectSpaceDestination(space1);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space1);
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceHomePage.goToWikiTab();
    $(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);

  }

  @Test
  public void test09_MovePageInSameSpace() {
    info("Test 09 Move page in same space");

    String space = "space" + getRandomNumber();

    String wiki1 = "wiki1" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();

    info("Create space 1 and wiki page ");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);

    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki1, wiki1);
    wikiManagement.saveAddPage();
    $(byText(wiki1)).should(Condition.exist);

    info("Add new wiki page 2 for space ");
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();
    $(byText(wiki2)).should(Condition.exist);

    info("Move wiki page 1 to wiki page 2");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToWikiTab();
    wikiManagement.movePage(wiki1, wiki2);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test10_MovePageInTheCases2DifferentSpaces() {

    info("Test 10 Move page in the cases 2 different spaces");
    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();

    String wiki1 = "wiki1" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki1, wiki1);
    wikiManagement.saveAddPage();
    $(byText(wiki1)).should(Condition.exist);

    info("Create space 2 and wiki page 2");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();
    $(byText(wiki2)).should(Condition.exist);

    info("Open wiki page 1");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceHomePage.goToWikiTab();

    info("Move page 1 to page 2");
    wikiManagement.movePageDiffDestination(wiki1, wiki2, space2);

    info("Open wiki page 1");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceHomePage.goToWikiTab();
    $(byText(wiki1)).shouldNot(Condition.exist);

    info("Open wiki page 2");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space2)).click();
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAPage(wiki2);
    $(byText(wiki1)).should(Condition.exist);

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);
  }

  @Test
  public void test11_MovePageHasTheSameNameWithPageInTargetSpace() {
    info("Test 11 Move page has the same name with page in target space");

    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    String wiki1 = "wiki1" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();

    info("mess:");

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    info("Add new wiki page 1 for space 1");
    spaceManagement.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki1, wiki1);
    wikiManagement.saveAddPage();
    $(byText(wiki1)).should(Condition.exist);

    info("Create space 2 and wiki page 2");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    info("Add new wiki page 2 for space 2");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();
    $(byText(wiki2)).should(Condition.exist);

    info("Add new wiki page 1 for space 2");
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki1, wiki1);
    wikiManagement.saveAddPage();
    $(byText(wiki1)).should(Condition.exist);

    info("Open wiki page 1");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceManagement.goToWikiTab();

    info("Move page 1 to page 2");
    wikiManagement.movePageDiffDestination(wiki1, wiki2, space2);
    ELEMENT_ALERT_MESSAGE.should(Condition.exist);
    wikiManagement.renameFromAlertMessageOfOnePage();
    richTextEditor.editSimplePage(newTitle, "");
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);
  }

  @Test
  public void test12_MoveWikisPageOfPortalToSpace() {
    info("Test 12 Move wiki's page of Portal to Space");

    String space = "space" + getRandomNumber();

    String wiki1 = "wiki1" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);

    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki1, wiki1);
    wikiManagement.saveAddPage();
    $(byText(wiki1)).should(Condition.exist);

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();
    $(byText(wiki2)).should(Condition.exist);

    info("Open Wiki page of portal");
    homePagePlatform.goToWiki();
    wikiManagement.movePageDiffDestination(wiki2, wiki1, space);

    info("Open wiki page 1");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAPage(wiki1);
    $(byText(wiki2)).should(Condition.exist);

    homePagePlatform.goToWiki();
    $(byText(wiki2)).shouldNot(Condition.exist);

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  /**
   * <li>Case ID:122831.</li>
   * <li>Test Case Name: Unwatch Page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Unwatch Page Step Description: - Open an
   * existing page by clicking on page name in navigation tree. - Click on More
   * icon on toolbar and select Unwatch action in menu Input Data: Expected
   * Outcome: Show message : "You stopped watching this page now." Selected page
   * is unwatched. Watch icon is switched to Unwatch icon
   */

  @Test
  public void test08_UnwatchPage_ExportPageAsPDF() {
    info("Test 8: Unwatch Page");
    String wiki = "wiki" + getRandomNumber();
    String mess1 = "You have started watching this page now.";
    info("mess1:" + mess1);
    String mess = "You have stopped watching this page now.";
    info("mess:" + mess);

    info("Add new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    $(byText(wiki)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    $(ELEMENT_MORE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_PDF_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("Watch the wiki");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(wiki);
    wikiManagement.watchAPage(mess1);
    wikiManagement.unWatchAPage(mess);
    info("Delete page");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);

  }

  /**
   * <li>Case ID:122826.</li>
   * <li>Test Case Name: Check permalink when user is member of page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Check permalink when user is member of page
   * Step Description: - Go to wiki page - Add new page ( with permission default:
   * have view permission) - Select More -> choose Permalink - Copy the link -
   * Connect with User B, can view page - Paste the permalink - Click Enter from
   * the keyboard Input Data: Expected Outcome: The user B can view the page
   * created by the manager
   */

  @Test
  @Tag("wabis")
  public void test04_CheckPermalinkWhenUserIsMemberOfPage() {
    info("Test 4: Check permalink when user is member of page");
    String title = "title" + getRandomNumber();

    info("Create a new wiki pages");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    wikiHomePage.goToPermalink();
    String perLink = ELEMENT_WIKI_PERMELINK.getValue();
    ELEMENT_BUTTON_CLOSE_PERMALINK.click();
    ELEMENT_BUTTON_CLOSE_PERMALINK.waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink);
    refresh();
    $(byText(title)).should(Condition.exist);
    info("Delete page");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122827.</li>
   * <li>Test Case Name: Check permalink when user is not member of page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Add new page when set permission for page
   * Step Description: - Login by admin - Go to wiki page - Add new page - Set
   * permission for page: remove any permission - Select More -> choose Permalink
   * - Copy the link Input Data: Expected Outcome: - Add new page have permission
   * successfully - Copy link successfully
   * Step number: 2 Step Name: Step 2: Check permalink when user is not member of
   * page Step Description: - Login by mary not a member in the page - Paste the
   * permalink - Click Enter from the keyboard Input Data: Expected Outcome: The
   * "Page Not found" is displayed, the user B cannot view the page
   */

  @Test
  @Tag("wabis")
  public void test05_CheckPermalinkWhenUserIsNotMemberOfPage() {
    info("Test 5: Check permalink when user is not member of page");
    String title = "title" + getRandomNumber();

    info("Create a new wiki pages");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    info("Un check view permission of any group");
    wikiManagement.unCheckViewAUserOfPage(ELEMENT_PERMISSION_VIEW_ANY);

    wikiHomePage.goToPermalink();
    String perLink = ELEMENT_WIKI_PERMELINK.getValue();
    ELEMENT_BUTTON_CLOSE_PERMALINK.click();
    ELEMENT_BUTTON_CLOSE_PERMALINK.waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink);
    refresh();
    $(ELEMENT_WIKI_HOME_PAGENOTFOUND).should(Condition.exist);

    info("Delete page");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122828.</li>
   * <li>Test Case Name: Check permalink when user is member of space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Add wiki page for space Step Description: -
   * Login with user A is space manager - Go to Space/wiki - Create a public page
   * Input Data: Expected Outcome: a public page is created by the manager of the
   * space
   * Step number: 2 Step Name: Step 2: Check permalink when user is member of
   * space Step Description: - From the list "More", choose the link "Permalink" -
   * Copy the link - Connect with User B, a member in the space - Paste the
   * permalink - Click Enter from the keyboard Input Data: Expected Outcome: The
   * member of space can view the page created by the manager
   */
  /**
   * <li>Case ID:122829.</li>
   * <li>Test Case Name: Check permalink when user is not member of space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Add new page for space when set permission
   * for page Step Description: - Login by admin<br /> - Add new space<br /> - Go
   * to space/wiki page<br /> - Add new page <br /> - Set permission for page:
   * remove any permission<br /> - Select More -> choose Permalink<br /> - Copy
   * the link<br /><br /> Input Data: Expected Outcome: - Add new page have
   * permission successfully - Copy link successfully
   * Step number: 2 Step Name: Step 2: Check permalink when user is not member of
   * space Step Description: - From the list "More", choose the link
   * "Permalink"<br /> - Copy the link<br /> - Connect with User B, not a member
   * in the space<br /> - Paste the permalink<br /> - Click Enter from the
   * keyboard<br /> Input Data: Expected Outcome: The "Page Not found" is
   * displayed, the user B cannot view the page
   */
  @Test
  @Tag("wabis")
  public void test06_CheckPermalinkWhenUserIsMemberOfSpaceThenWhenUserIsNotMemberOfSpace() {
    info("Test 6: Check permalink when user is member of space");
    String space = "space" + getRandomNumber();
    String wiki = "wiki" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    info("send request by user 2");
    spaceManagement.sendARequestToASpace(space);
    manageLogInOut.signIn("john", "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    ELEMENT_SPACES_LIST.waitUntil(Condition.visible,Configuration.timeout).find(byText(space)).waitUntil(Condition.visible,Configuration.timeout).click();
    refresh();
    spaceHomePage.goToSpaceSettingTab();
    info("accept request by user 1");
    spaceSettingManagement.goToMemberTabInSpaceSettingTab();
    spaceHomePage.goToWikiTab();
    $(byText(wiki)).click();
    wikiHomePage.goToPermalink();
    String perLink = ELEMENT_WIKI_PERMELINK.getValue();
    ELEMENT_BUTTON_CLOSE_PERMALINK.click();
    ELEMENT_BUTTON_CLOSE_PERMALINK.waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink);
    refresh();
    $(byText(wiki)).should(Condition.exist);
    manageLogInOut.signIn("john", "gtngtn");
    info("Delete page");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();
    wikiHomePage.goToPermalink();
    String perLink2 = ELEMENT_WIKI_PERMELINK.getValue();
    ELEMENT_BUTTON_CLOSE_PERMALINK.click();
    ELEMENT_BUTTON_CLOSE_PERMALINK.waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink2);
    refresh();
    $(ELEMENT_WIKI_HOME_PAGENOTFOUND).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete page");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  /**
   * <li>Case ID:122849.</li>
   * <li>Test Case Name: Check when change link is restricted.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Add new page is public Step Description: -
   * Go to wiki page - Add new page ( with permission default: have view
   * permission) Input Data: Expected Outcome: The user B can view the page
   * created by the manager
   * Step number: 2 Step Name: Step 2: Change link is restricted Step Description:
   * - Choose page above - Select More -> choose Permalink - Click the Restrict
   * button. Input Data: Expected Outcome: The form will show that the page is
   * changed into the restricted status and just the authorized users can view and
   * edit it.
   * Step number: 3 Step Name: Step 3:Check after change to restricted Step
   * Description: - Copy the link - Login by mary not a member in the page - Paste
   * the permalink - Click Enter from the keyboard Input Data: Expected Outcome:
   * The "Page Not found" is displayed, the user B cannot view the page<br />
   */
  /**
   * <li>Case ID:122850.</li>
   * <li>Test Case Name: Check when change link is public.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Add new page is public Step Description: -
   * Go to wiki page - Add new page with restricted permission ( ex: remove any
   * permission of page) Input Data: Expected Outcome: Add new page successfully
   * and only user belong to group can view/edit page
   * Step number: 2 Step Name: Step 2: Change link is public Step Description: -
   * Choose page above - Select More -> choose Permalink - Click Make it Public
   * button. Input Data: Expected Outcome: The form will show that the page is
   * changed into the public status, and someone can view and edit it.
   * Step number: 3 Step Name: Step 3: Check after change link Step Description: -
   * Copy the link - Connect with User B, can view page - Paste the permalink -
   * Click Enter from the keyboard Input Data: Expected Outcome: The user B can
   * view the page created by the manager
   */

  @Test
  public void test09_CheckWhenChangeLinkIsRestrictedThenWhenChangeLinkIsPublic() {
    info("Test 9: Check when change link is restricted");
    String title = "title" + getRandomNumber();

    info("Add new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToWiki();
    $(byText(title)).should(Condition.exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToPermalink();
    $(ELEMENT_MAKE_RESTRICT_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_MSG_MAKE_PUBLIC_RESTRICTED.shouldHave(Condition.text("restricted"));
    $(ELEMENT_PERMALINK_CLOSE).click();
    wikiHomePage.goToPermalink();
    String perLink = ELEMENT_WIKI_PERMELINK.getValue();
    $(ELEMENT_PERMALINK_CLOSE).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink);
    refresh();
    $(ELEMENT_WIKI_HOME_PAGENOTFOUND).waitUntil(Condition.appears, Configuration.timeout);

    info("Delete page");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);

    info("Add new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    wikiManagement.unCheckViewAUserOfPage(ELEMENT_PERMISSION_VIEW_ANY);

    wikiHomePage.goToPermalink();
    $(ELEMENT_MAKE_PUBLIC_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_MSG_MAKE_PUBLIC_RESTRICTED.shouldHave(Condition.text("public"));
    $(ELEMENT_PERMALINK_CLOSE).click();
    wikiHomePage.goToPermalink();
    String perLink2 = ELEMENT_WIKI_PERMELINK.getValue();
    $(ELEMENT_PERMALINK_CLOSE).click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink2);
    refresh();
    $(byText(title)).waitUntil(Condition.visible,Configuration.timeout);

    info("Delete page");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122851.</li>
   * <li>Test Case Name: Change permission for page in Permalink page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Change permission for page in Permalink
   * page Step Description: - Go to wiki page - Choose a page - Select More ->
   * choose Permalink - Click Manage Permission - Choose permission (
   * user/group/membership) which want to add - Click Add and Save button Input
   * Data: Expected Outcome: - Permission is updated and The user can view or not
   * the page edited
   */

  @Test
  @Tag("wabis")
  public void test11_ChangePermissionForPageInPermalinkPage() {
    info("Test 11 Change permission for page in Permalink page");
    String title = "title" + getRandomNumber();

    info("Add new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT),Condition.visible,1000);
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    wikiHomePage.goToPermalink();
    String perLink = ELEMENT_WIKI_PERMELINK.getValue();
    $(ELEMENT_PERMALINK_CLOSE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Un check view permission of any group");
    wikiManagement.unCheckViewAUserOfPage(ELEMENT_PERMISSION_VIEW_ANY);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    open(perLink);
    refresh();
    $(ELEMENT_WIKI_HOME_PAGENOTFOUND).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete page");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122859.</li>
   * <li>Test Case Name: Move wiki's page of space to portal.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Step 1: Add new space Step Description: - Login
   * portal - Click join a space - Add new space for "Space 1" Input Data:
   * Expected Outcome: New spaceis added successfully
   * Step number: 2 Step Name: Step 2: Add new page for wiki on space 1 Step
   * Description: - Go to Space 1/ wiki - Add new page for wiki with name "Page1"
   * Input Data: Expected Outcome: - Add new page successfully
   * Step number: 3 Step Name: Step 3: Open Page1 Step Description: - Open
   * "Page 1" Input Data: Expected Outcome: - Page 1 is displayed in the wiki
   * Step number: 4 Step Name: Step 4: Go to Move page Step Description: - Open
   * "More" Menu -> "Move Page" Input Data: Expected Outcome: - Move Page form
   * appear
   * Step number: 5 Step Name: Step 5: Check when move wiki's page of space to
   * wiki's page of portal Step Description: - Open Space switcher component -
   * Select "intranet" - Click on "Move Page" button Input Data: Expected Outcome:
   * - The list of space switcher options is displayed - Wiki's page of space is
   * moved to wiki's page of portal
   */

  @Test
  public void test13_MoveWikisPageOfSpaceToPortal() {
    info("Test 13 Move wiki's page of space to portal");
    String space = "space" + getRandomNumber();

    String wiki1 = "wiki1" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);

    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki1, wiki1);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();

    info("Open wiki page 1");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToWikiTab();

    info("Move page 1 to page 2");
    wikiManagement.movePageDiffDestination(wiki1, wiki2, "Intranet");
    info("Open wiki page 1");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToWikiTab();
    $(byText(wiki1)).shouldNot(Condition.exist);
    info("Open wiki page 2");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(wiki2);
    $(byText(wiki1)).should(Condition.exist);
    info("Delete data test");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  /**
   * <li>Case ID:122863.</li>
   * <li>Test Case Name: Move page when user has no edit permission at destination
   * page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> check issue WIKI-725 (bug -> the user mary can move
   * in the page without edit permission)
   * Step Number: 1 Step Name: Step 1: Add new space Step Description: - Login by
   * admin - Click join a space - Add new space for "Space 1" Input Data: Expected
   * Outcome: New spaceis added successfully
   * Step number: 2 Step Name: Step 2: Add new page for wiki on space Step
   * Description: - Go to Space 1 wiki - Add new page for wiki with name "Page1" ,
   * Page2 Input Data: Expected Outcome: - Add new page successfully
   * Step number: 3 Step Name: Step 3: Open Page1 of space 1 Step Description: -
   * Open "Page 1", set permission user2 can't edit Input Data: Expected Outcome:
   * - Page 1 is displayed in the wiki - User2 cannot edit the Page1
   * Step number: 4 Step Name: Step 4: Go to Move Page Step Description: -Login by
   * user2 - Open Wiki of Space 1 - Select Page 2 - Open "More" Menu ->
   * "Move Page" Input Data: Expected Outcome: - Move Page form appear
   * Step number: 5 Step Name: Step 5: Check when add relations from 2 different
   * spaces Step Description: - Open Destination component - Select "Page1" -
   * Click on "Move" button Input Data: Expected Outcome: - The list of space
   * switcher options is displayed - Wiki tree of "intranet" is displayed on the
   * container below the space switcher - Popup is closed - Show message alert
   * "You have no edit permission at destination page"
   */

  @Test
  @Tag("wabis")
  public void test02_MovePageWhenUserHasNoEditPermissionAtDestinationPage() {
    info("Test 02: Move page when user has no edit permission at destination page");
    String space = "space" + getRandomNumber();

    String wiki1 = "wiki1" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();
    String mess = "";
    info("mess:" + mess);

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    info("send request by user 2");
    spaceManagement.sendARequestToASpace(space);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToSpaceSettingTab();
    info("accept request by user 1");
    spaceSettingManagement.goToMemberTabInSpaceSettingTab();

    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki1, wiki1);
    wikiManagement.saveAddPage();

    info("Add permisison");
    wikiHomePage.goToPermissions();
    wikiPermission.addPermisisonByType(DATA_USER2);
    $(byText("* in " + space.substring(0, 1).toUpperCase() + space.substring(1))).parent()
                                                                                 .parent()
                                                                                 .findAll(byClassName("uiCheckbox"))
                                                                                 .get(1)
                                                                                 .click();
    wikiPermission.savePermisison();

    info("Add new wiki page 2 for space 2");
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();

    info("Add permisison");
    wikiHomePage.goToPermissions();
    wikiPermission.addPermisisonByType(DATA_USER2);
    wikiPermission.selectPermission(DATA_USER2, WikiPermission.permissionType.Edit_Pages);
    wikiPermission.savePermisison();

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToWikiTab();
    info("Open a wiki page ");
    $(byText(wiki2)).waitUntil(Condition.appears, Configuration.timeout).click();

    info("Click on More link");
    $(ELEMENT_MORE_LINK).click();
    info("Click on Move page link");
    $(ELEMENT_MOVE_LINK).hover().click();

    info("Move page popup is shown");
    $(byId("UIMoveTree")).find(byText(wiki1)).shouldNot(Condition.exist);
    $(ELEMENT_CANCEL_BUTTON).click();
    info("delete data");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }
}
