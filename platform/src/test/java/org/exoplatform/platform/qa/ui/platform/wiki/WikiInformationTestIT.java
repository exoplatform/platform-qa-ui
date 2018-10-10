package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

@Tag("wiki")
@Tag("sniff")
public class WikiInformationTestIT extends Base {

  HomePagePlatform    homePagePlatform;

  WikiHomePage        wikiHomePage;

  ManageLogInOut      manageLogInOut;

  WikiManagement      wikiManagement;

  RichTextEditor      richTextEditor;

  SpaceHomePage       spaceHomePage;

  SpaceManagement     spaceManagement;

  WikiPageInformation wikiPageInformation;

  SourceTextEditor    sourceTextEditor;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiManagement = new WikiManagement(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    wikiPageInformation = new WikiPageInformation(this);
    sourceTextEditor = new SourceTextEditor(this);
    richTextEditor = new RichTextEditor(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }

    manageLogInOut.signInCas(username, password);
  }


  /*
   * Step Number: 1 Step Name: Step 1: Check page history Step Description: -
   * Create a new page under wiki home, eg WikiPage1 - Edit this page - Create 2
   * child page for it, child 1 and child 2 - Open this page - Click [More][Page
   * Info] Input Data: Expected Outcome: Page information is shown - Summary:
   * Title, author, Last changed by - Related page: list all related page. A
   * button [Add More Relation] - Hierarchy: list parent page and child - Recent
   * changes: list all revisions and a button [View Page History]
   */

  @Test
  public void test01_ViewPageInfo() {
    info("Test 01: View Page info");
    String title = "title" + getRandomNumber();
    String child1 = "child1" + getRandomNumber();
    String child2 = "child2" + getRandomNumber();

    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);

    info("Create child 1 for wiki page");
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(child1, child1);
    wikiManagement.saveAddPage();
    $(byText(child1)).should(Condition.exist);

    info("Create child 2 for wiki page");
    wikiHomePage.selectAPage(title);
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(child2, child2);
    wikiManagement.saveAddPage();
    $(byText(child2)).should(Condition.exist);

    info("Open Page info");
    String date = getDateByTextFormat("MM dd, yyyy");
    wikiHomePage.goToPageInformation(title);

    info("Summary: Title, author, Last changed by");
    $(byXpath("//*[@id=\"UIWikiPageInfo\"]/div[1]/div[1]/table/tbody/tr[1]/td[1]")).parent()
                                                                                   .parent()
                                                                                   .find(byText(title))
                                                                                   .should(Condition.exist);
    $(byText("Author")).parent().parent().find(byText("Root Root")).should(Condition.exist);
    $(byText("Last changed by")).parent().parent().find(byText("Root Root")).should(Condition.exist);

    info("Related page: list all related page. A button [Add More Relation]");
    $(byClassName("empty center")).find(byText("Empty data"));
    $(ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS).waitUntil(Condition.appears, Configuration.timeout);

    info("Hierarchy: list parent page and child");
    $(byClassName("uiPageInfoItem uiPageInfoHierarchy")).find(byClassName("nodeLabel")).find(byText("Wiki Home"));
    $(byClassName("uiPageInfoItem uiPageInfoHierarchy")).find(byClassName("nodeGroup")).find(byText("child1"));
    $(byClassName("uiPageInfoItem uiPageInfoHierarchy")).find(byClassName("nodeGroup")).find(byText("child2"));

    info("Recent changes: list all revisions and a button [View Page History]");
    $(byClassName("")).find(byText(""));
    $(ELEMENT_VIEW_PAGE_HISTORY).should(Condition.exist);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }
  /*
   * Step Number: 1 Step Name: Step 1: Add new space Step Description: - Login
   * portal - Click join a space - Add new space for "Space 1" and "Space 2" Input
   * Data: Expected Outcome: New space is added successfully Step Number: 2 Step
   * Name: Step 2: Add new page for wiki on space 1 and space 2 Step Description:
   * - Go to Space 1/ wiki and Space2/wiki - Add new page for wiki with name
   * "Page1" and "Page2" Input Data: Expected Outcome: Add new page successfully
   * Step Number: 3 Step Name: Step 3: Open Page1 Step Description: - Open
   * "Page 1" Input Data: Expected Outcome: Page 1 is displayed in the wiki Step
   * Number: 4 Step Name: Step 4: Go to Page Info Step Description: - Open "More"
   * Menu -> "Page Info" Input Data: Expected Outcome: Page infor of Page 1 are
   * diplayed Step Number: 5 Step Name: Step 5: Go to Related page form Step
   * Description: - Click "Add More Relations" on Related Page form Input Data:
   * Expected Outcome: The popup to select a related page is displayed Step
   * Number: 6 Step Name: Step 6: Check when add relations from intranet portal
   * Step Description: - Open Space switcher component - Select "intranet" - Click
   * on "Select" button Input Data: Expected Outcome: - The list of space switcher
   * options is displayed - Wiki tree of "Space 2" is displayed on the container
   * below the space switcher - Popup is closed - "intranet's portal" is added as
   * a related pages on page info layout
   */

  @Test
  public void test02_AddRelationWithIntranetPortal() {
    info("Test 02: Add relations with Intranet portal");
    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    String title1 = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    info("Add new wiki page for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    $(byText(title1)).should(Condition.exist);

    info("Create space 2 and wiki page 2");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    info("Add new wiki page for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    $(byText(title2)).should(Condition.exist);

    info("Open wiki page 1");
    homePagePlatform.goToSpecificSpace(space1);
    spaceHomePage.goToWikiTab();
    info("Open page 1 and Go to Page Info");
    wikiHomePage.goToAPage(title1);
    wikiHomePage.goToPageInformation(title1);

    info("Go to Related page form");
    wikiPageInformation.goToAddRelations();

    info("Wiki tree of 'Space 2' is displayed on the container below the space switcher");
    String spaces = space1 + "/" + space2;
    wikiManagement.checkAddRelationDropDownList(spaces);

    info("Check when add relations from 2 different spaces");
    wikiPageInformation.addRelations("Intranet", "Wiki Home");

    info("intranet's portal is added as a related pages on page info layout");

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);

  }

  /**
   * Step Number: 1 Step Name: Step 1: Create new wiki page Step Description: -
   * Add new wiki page Input Data: Expected Outcome: New wiki page is created with
   * version is V1 Step Number: 2 Step Name: Step 2: Edit title/wiki content by
   * click [Edit] icon Step Description: - Click edit icon - Edit title or content
   * or edit both - Save - View page version Input Data: Expected Outcome: New
   * version is created Step Number: 3 Step Name: Step 3: upload/delete attachment
   * Step Description: - Click attachment icon at information area at the top of
   * page - Add new attachment - Delete attachment - View page version Input Data:
   * Expected Outcome: No version is created
   */

  @Test
  public void test02_VersionCreation() {
    info("Test 02: Version creation");
    String title = "title" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String link = "link" + getRandomNumber();

    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);

    info("Verify that New wiki page is created with version is V1");
    wikiHomePage.viewInformationTable(title, "V1");

    info("Edit the page");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle, newTitle);
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);

    info("Verify that New wiki page is created with version is V2");
    wikiHomePage.viewInformationTable(newTitle, "V2");

    info("Click on attachment icon at information area at the top of page");
    $(byClassName("uiIconAttach")).waitUntil(Condition.appears, Configuration.timeout);
    $(byClassName("uiIconAttach")).click();
    $(byId("UIAttachmentContainer")).scrollTo().waitUntil(Condition.appears, Configuration.timeout);

    info("Add new attachment");
    File file = $(byClassName("uploadInput")).find(byName("file")).uploadFromClasspath("wiki_attachment.txt");
    assertTrue(file.exists());

    info("Delete attachment");
    wikiManagement.deleteAttachmentFile("wiki_attachment.txt");

    info("Verify that New wiki page is created with version still is V2. No version is created");
    wikiHomePage.viewInformationTable(newTitle, "V2");

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }

  /*
   * Step Number: 1 Step Name: Step 1: Check Page info Step Description: - Create
   * a new page - Edit this page - Open this page - Focus Page info at top area
   * Input Data: Expected Outcome: - The total number of revisions (eg V1, V2, V3)
   * and this reversion is clickable to open page history - Show full name of the
   * original creator of the page and time page is created (eg, Added by John
   * Smith at Mar 3, 2014 3:09 PM) - Last time the page was edited with a link to
   * view changes (Eg, Last modified by John Smith at Mar 4, 2014 3:09 PM ) -
   * Restricted link by default - The total number of attachments as a link to
   * open the attachments
   */

  @Test
  public void test03_ViewGeneralPageInformation() {
    info("Test 03: View General Page information");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();

    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);

    info("Verify that The total number of revisions (eg V1, V2, V3) and this reversion is clickable to open page history  ");
    wikiHomePage.viewInformationTable(title, "V1");

    info("Show full name of the original creator of the page");
    $(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_CONTENT).$(byText("Root Root")).should(Condition.exist);

    info("Edit the title of the wiki page");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle, "");
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);

    info("Restricted link by default: Restricted");
    $(ELEMENT_RESTRICTED_LINK).shouldHave(Condition.text("Restricted"));

    info("The total number of attachments as a link to open the attachments");
    $(ELEMENT_ATTACHMENT_TOTAL_NUMBER).shouldHave(Condition.text("0"));

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }
  /*
   * Step Number: 1 Step Name: Step 1: Check page history Step Description: - Add
   * new page - Edit this page some times - Open an existing page by clicking on
   * page name in navigation tree - Click More -> [Page info] - Click [View Page
   * History] - To compare two versions, select two check boxes corresponding to
   * each relevant version, click [Compare the selected versions] Input Data:
   * Expected Outcome: - A page which shows the changes between these two versions
   * will be displayed. - The changes between two versions will be marked with
   * colours: + Words/lines which are red-highlighted with strike-throughs
   * indicate that they were removed. + Words/lines highlighted in green indicate
   * that they were added.
   */

  @Test
  public void test03_ViewHistoryToCompareVersions() {
    info("Test 03: View Page history to compare versions");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String newTitle1 = "newTitle1" + getRandomNumber();
    String newTitle2 = "newTitle2" + getRandomNumber();

    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);

    info("Edit the page first time");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle, newTitle);
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);

    info("Edit the page second time");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle1, newTitle1);
    wikiManagement.saveAddPage();
    $(byText(newTitle1)).should(Condition.exist);

    info("Edit the page third time");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle2, newTitle2);
    wikiManagement.saveAddPage();
    $(byText(newTitle2)).should(Condition.exist);

    info("Open Page info");
    wikiHomePage.goToPageInformation(newTitle2);

    info("Open Page history");
    wikiPageInformation.goToPageHistory();

    info("Open Compare reversion page");
    wikiPageInformation.compareTwoReversion("v.1", "Current Version ( v.4)");

    info("Compare reversion page is shown");
    $(ELEMENT_WIKI_PAGE_COMPARE_REVERSION_TITLE).waitUntil(Condition.appears, Configuration.timeout);

    info("Verify that Words/lines which are red-highlighted with strike-throughs indicate that they were removed");

    assertEquals($(byClassName("diffmodifiedline")).find(byText(content)).getCssValue("background-color"),
                 "rgba(208, 123, 123, 1)");

    info("Verify that Words/lines highlighted in green indicate that they were added");

    assertEquals($(byClassName("diffmodifiedline")).find(byText(newTitle2+newTitle1+newTitle+content)).getCssValue("background-color"),
                 "rgba(108, 203, 174, 1)");

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle2);
  }

}
