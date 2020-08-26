package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_BUTTON_CLOSE_PREVIEW_MODE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by exo on 5/21/18.
 */

@Tag("functional")
@Tag("wiki")
public class WikiBasicActionEditTestIT extends Base {


  HomePagePlatform homePagePlatform;
  WikiHomePage wikiHomePage;
  WikiManagement wikiManagement;
  SourceTextEditor sourceTextEditor;
  WikiValidattions wikiValidattions;
  ManageLogInOut manageLogInOut;
  NavigationToolbar navigationToolbar;
  UserAndGroupManagement userAndGroupManagement;
  AddUsers addUsers;
  RichTextEditor richTextEditor;
  WikiDraftPage wikiDraftPage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiManagement = new WikiManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiValidattions = new WikiValidattions(this);
    sourceTextEditor = new SourceTextEditor(this);
    navigationToolbar = new NavigationToolbar(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    richTextEditor = new RichTextEditor(this);
    wikiDraftPage = new WikiDraftPage(this);
    addUsers = new AddUsers(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li> Case ID:139444.</li>
   * <li> Test Case Name: Draft in an outdate version status.</li>
   * <li> Pre-Condition: Make changes on a page with two usersCreate a page</li>
   * <li> Post-Condition: </li>
   * Step Number: 1
   * Step Name: Step 1: Edit an outdated version
   * Step Description:
   * - Connect with the User A
   * - Add a page with name is Test
   * - Add a content and save
   * Input Data:
   * <p>
   * Expected Outcome:
   * The page is created
   * Step number: 2
   * Step Name: Step 2: Edit page
   * Step Description:
   * - Connect with the User B who have edit permission on "Test" page
   * - Edit the page "Test" and add a content
   * Input Data:
   * <p>
   * Expected Outcome:
   * A draft is saved after 30s
   * Step number: 3
   * Step Name: Step 3: Edit page by User A
   * Step Description:
   * - Connect with the user "A"
   * - Edit the page "Test" and add a content
   * - Save the page
   * Input Data:
   * <p>
   * Expected Outcome:
   * A new version of the page is created
   * Step number: 4
   * Step Name: Step 4: Edit page by User B
   * Step Description:
   * - Connect with the User B:
   * - Open the page "Test" and click [Edit]
   * Input Data:
   * <p>
   * Expected Outcome:
   * A page is opened in edit mode and a warning message is displayed:
   * Your version is outdated, a version of this content has been updated
   * by another user.
   * You can [view your changes] and continue editing or [delete] your draft. "
   * Step number: 7
   * Step Name: Step 7: Check delete draft
   * Step Description:
   * - re
   * -open the page and click [Edit]
   * - From the message, click on the link [delete]
   * Input Data:
   * <p>
   * Expected Outcome:
   * The draft is deleted from the list" My drafts"
   */

  @Test
  public void test03_02_DraftInAnOutdateVersionStatusThenSaveADraftOfCurrentlySavedPage() {
    info("Draft in an outdate version status");
    info("Create 2 new users");
    String username1 = "username1" + getRandomString();
    String username2 = "username2" + getRandomString();
    String firstname = "test" + getRandomString();
    String firstname2 = "testt" + getRandomString();
    String username3 = "username3" + getRandomString();
    String username4 = "username4" + getRandomString();
    String firstname3 = "test" + getRandomString();
    String firstname4 = "testt" + getRandomString();
    String password0 = "Aa123456";
    String email3 = username3 + "@test.com";
    String email4 = username4 + "@test.com";
    String password = "Aa123456";
    String email1 = username1 + "@test.com";
    String email2 = username2 + "@test.com";
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, firstname, firstname);
    addUsers.addUser(username2, password, email2, firstname2, firstname2);
    addUsers.addUser(username3, password0, email3, firstname3, firstname3);
    addUsers.addUser(username4, password0, email4, firstname4, firstname4);
    info("Add 2 users to Admin groups");
    String groupsPath = "Platform/Administration";
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToGroupTab();
    userAndGroupManagement.selectGroup(groupsPath);
    userAndGroupManagement.addUsersToGroup(username1, "*", false, false);
    userAndGroupManagement.addUsersToGroup(username2, "*", false, false);
    info("Login with User A");
    manageLogInOut.signIn(username1, "Aa123456");
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Login with User B");
    manageLogInOut.signIn(username2, "Aa123456");
    info("Edit the page");
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.editSimplePageWithAutoSave(newTitle, newContent);
    wikiManagement.cancelAddPage();
    wikiHomePage.confirmWaringMessage(true);
    info("The draft is saved");
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage(newTitle);
    info("Login with User A");
    manageLogInOut.signIn(username1, "Aa123456");
    info("Edit the page");
    String newTitle1 = "newTitle1" + getRandomNumber();
    String newContent1 = "newContent1" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.editSimplePage(newTitle1, newContent1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newTitle1);
    info("A new version of the page is created");
    wikiHomePage.viewInformationTable(newTitle1, "V2");
    info("Login with User B");
    manageLogInOut.signIn(username2, "Aa123456");
    info("Edit the page");
    String message = "Your version is outdated. A version of this content has been updated by another user. You can ";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(newTitle1);
    wikiHomePage.goToEditPage();
    info("A page is opened in edit mode and a warning message is displayed:");
    info("Your version is outdated, a version of this content has been updated by another user. "
            + "You can [view your changes] and continue editing or [delete] your draft.");
    wikiValidattions.verifyDraftInOutDateVersionStatus(message);
    info("The Comparison view is open");
    wikiManagement.goToViewChangesLinkOnStatus();
    wikiValidattions.verifyTitleDraftChangesPage();
    ELEMENT_BUTTON_CLOSE_PREVIEW_MODE.click();
    wikiManagement.goToDeleteLinkOnStatus();
    info("The draft is deleted from the list of draft");
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyNotTitleDrafPage(newTitle1);
    wikiValidattions.verifyNotTitleDrafPage(title);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle1);
    info("Save a draft of currently saved page");
    info("Create 2 new users");
    navigationToolbar.goToAddUser();
    info("Add 2 users to Admin groups");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToGroupTab();
    userAndGroupManagement.selectGroup(groupsPath);
    userAndGroupManagement.addUsersToGroup(username3, "*", false, false);
    userAndGroupManagement.addUsersToGroup(username4, "*", false, false);
    info("Login with User C");
    manageLogInOut.signIn(username3, "Aa123456");
    info("Create a wiki page");
    String title0 = "title0" + getRandomNumber();
    String content0 = "content0" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title0, content0);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title0);
    info("Edit the page");
    String title1 = "title1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    wikiHomePage.goToEditPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.editSimplePageWithAutoSave(title1, content1);
    info("Login with User D");
    manageLogInOut.signIn(username4, "Aa123456");
    info("Edit the page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(title0);
    wikiHomePage.goToEditPage();
    info("A warning message is displayed:");
    info("This Page is currently being edited by ");
    String fullName = firstname3 + " " + firstname3;
    String status = "This page is currently being edited by ";
    wikiValidattions.verifyMessageWhenEditingSamePage(status, fullName);
    wikiHomePage.confirmWaringMessage(true);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title0);

  }

  /**
   * <li> Case ID:139445.</li>
   * <li> Test Case Name: Edit a page with an existing draft.</li>
   * <li> Pre-Condition: </li>
   * <li> Post-Condition: </li>
   * Step Number: 1
   * Step Name: Step 1: Add new page
   * Step Description:
   * - Add a wiki page and save
   * - Edit the page
   * - Input a content
   * Input Data:
   * <p>
   * Expected Outcome:
   * The draft is saved after 30s
   * Step number: 2
   * Step Name: Step 2: Edit page
   * Step Description:
   * - From the "Wiki Home", Open again the page
   * - Edit the page
   * Input Data:
   * <p>
   * Expected Outcome:
   * A warning message is displayed under the title:
   * "A draft of this page was saved on Month Day, Year HH:MM.
   * You can view your changes and decide to resume the draft or delete it.
   * Step number: 5
   * Step Name: Step 5: Delete draft
   * Step Description:
   * - Repeat the step 3
   * - Click on the link [Delete]
   * Input Data:
   * <p>
   * Expected Outcome:
   * The draft is deleted.
   * Step number: 6
   * Step Name: Step 6: Check after delete draft
   * Step Description:
   * Click open again the page and click edit
   * Input Data:
   * <p>
   * Expected Outcome:
   * The warning message isn't displayed
   */
  @Test
  public void test04_02_EditAPageWithAnExistingDraftThenWhenSameTitleWithExistingPage() {
    info("Edit a page with an existing draft");
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageWithAutoSaveStatus(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Edit the page");
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePageWithAutoSave(newTitle, newContent);
    wikiManagement.cancelAddPage();
    wikiHomePage.confirmWaringMessage(true);
    info("Edit the page again");
    String message = "message" + getRandomNumber();
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    info("A warning message is displayed under the title:");
    $(byText("View your Changes")).waitUntil(visible, Configuration.timeout);
    $(byText("Resume the Draft")).waitUntil(visible, Configuration.timeout);
    wikiManagement.goToDeleteLinkOnStatus();
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyNotTitleDrafPage(title);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    info("The warning message isn't displayed");
    $(byText("View your Changes")).waitUntil(Condition.not(visible), Configuration.timeout);
    $(byText("Resume the Draf")).waitUntil(Condition.not(visible), Configuration.timeout);
    wikiManagement.cancelAddPage();
    wikiHomePage.deleteWiki(title);

    info("Edit an other page with an existing draft");
    info("Create a wiki page");
    String title4 = "title4" + getRandomNumber();
    String content4 = "content4" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageWithAutoSaveStatus(title4, content4);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title4);

    info("Edit the page");
    String newTitle4 = "newTitle4" + getRandomNumber();
    String newContent4 = "newContent4" + getRandomNumber();
    wikiHomePage.goToAPage(title4);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePageWithAutoSave(newTitle4, newContent4);
    wikiManagement.cancelAddPage();
    wikiHomePage.confirmWaringMessage(true);
    info("Edit the page again");
    wikiHomePage.goToAPage(title4);
    wikiHomePage.goToEditPage();

    info("A warning message is displayed under the title:");
    info("A draft of this page was saved on Month Day, Year HH:MM. "
            + "You can view your changes and decide to resume the draft or delete it.");

    $(byText("View your Changes")).waitUntil(visible, Configuration.timeout);
    $(byText("Resume the Draft")).waitUntil(visible, Configuration.timeout);
    info("The page 'Draft changes' is displayed");
    wikiManagement.goToViewChangesLinkOnStatus();
    $(byText(title4)).shouldBe(visible);
    $(byText(newContent4)).shouldBe(visible);
    $(byXpath("//*[@id=\"UIWikiMaskWorkspace\"]/div[1]/a/i")).click();
    wikiManagement.goToResumDrafLinkOnStatus();
    wikiValidattions.verifyResumADraf(newTitle4);

    info("Edit page when the title is the same with existing page");
    info("Create a wiki page");
    String title0 = "title0" + getRandomNumber();
    String content0 = "content0" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title0, content0);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title0);
    info("Edit a wiki page");
    String newTitle0 = "newTitle0" + getRandomNumber();
    String newContent0 = "newContent0" + getRandomNumber();
    wikiHomePage.goToAPage(title0);
    wikiHomePage.goToEditPage();
    sourceTextEditor.editSimplePage(newTitle0, newContent0);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newTitle0);

    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle0);
    wikiHomePage.deleteWiki(title4);

  }

  /**
   * <li> Case ID:139449.</li>
   * <li> Test Case Name: Resume a draft after Browser crash.</li>
   * <li> Pre-Condition: Add/Edit a page</li>
   * <li> Post-Condition: </li>
   * Step Number: 1
   * Step Name: Step 1: Edit page
   * Step Description:
   * Edit a wiki page and add a content
   * Input Data:
   * <p>
   * Expected Outcome:
   * The draft is added after 30s
   *
   * @throwsException Step number: 2
   * Step Name: Step 2: Autosave page after Browser crash
   * Step Description:
   * Force stop the Browser process from the task manager
   * Input Data:
   * <p>
   * Expected Outcome:
   * The Browser is stopped
   * Step number: 3
   * Step Name: Step 3: Page saved as draft
   * Step Description:
   * Open the page againGo to "My drafts"
   * Input Data:
   * <p>
   * Expected Outcome:
   * The draft version appears in the list
   */
  @Test
  @Tag("wabis")
  public void test05_ResumeADraftAfterBrowserCrashThenAfterCloseWithoutSaving() throws Exception {
    info("Resume a draft after Browser crash");
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Edit the page");
    String title1 = "title1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePageWithAutoSave(title1, content1);
    wikiManagement.cancelAddPage();
    wikiHomePage.confirmWaringMessage(true);
    info("The Browser is stopped");
    Selenide.close();
    open(baseUrl);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    info("The draft version appears in the list");
    homePagePlatform.goToWiki();
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage(title1);

    info("Resume a draft after close without saving");
    info("Create a wiki page");
    String title0 = "title0" + getRandomNumber();
    String content0 = "content0" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title0, content0);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title0);

    info("Edit the page");
    String title2 = "title2" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePageWithAutoSave(title2, content2);
    wikiManagement.cancelAddPage();
    wikiHomePage.confirmWaringMessage(true);
    info("The Browser is closed");
    Selenide.close();
    open(baseUrl);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    info("The draft version appears in the list");
    homePagePlatform.goToWiki();
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage(title2);
    info("The page in edit mode is displayed");
    $(byId("UIWikiDraftGrid")).find(byText(title2)).waitUntil(visible, Configuration.collectionsTimeout).click();
    wikiValidattions.verifyResumADraf(title2);

    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
    wikiHomePage.deleteWiki(title0);

  }

  /**
   * <li> Case ID:139535.</li>
   * <li> Test Case Name: Edit wiki page link.</li>
   * <li> Pre-Condition: </li>
   * <li> Post-Condition: </li>
   * Step Number: 1
   * Step Name: Step 1: Open attach file link form
   * Step Description:
   * - Add new page or edit a page that has a wiki page link
   * - Ensure the page isin [Rich Text] editor
   * - Select an attached file link in content
   * -Click [Link] in menu and select [Edit Link]
   * Input Data:
   * <p>
   * Expected Outcome:
   * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
   * - If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
   * - If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
   * - Attached File form appear
   * Step number: 2
   * Step Name: Step 2: Change wiki page link
   * Step Description:
   * - Choose other page link
   * - Click [Select]
   * - Input tooltip for file
   * - Click [Create Link] button
   * - Click [Save]
   * Input Data:
   * <p>
   * Expected Outcome:
   * The attached wiki page link is changed successfully in content of page
   * - Page is add/edited successfully
   * Step number: 3
   * Step Name: Step 3: View the wiki page link
   * Step Description:
   * - Click on name wiki page link
   * Input Data:
   * <p>
   * Expected Outcome:
   * Content of wiki page link is shown
   */
  @Test
  @Tag("wabis")
  public void test10_EditWikiPageLink() {
    info("Test 10 Edit wiki page link");
    info("Create a wiki page 1");
    String title1 = "title1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title1);
    info("Create a wiki page 2");
    String title2 = "title2" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, content2);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title2);
    info("Create a wiki page 3");
    String title3 = "title3" + getRandomNumber();
    String label = "label" + getRandomNumber();
    String tooltip = "tooltip" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title3, "");
    richTextEditor.goToWikiPageLink();
    richTextEditor.insertExistWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.All_pages);
    wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
    info("Move focus at the end of the line");
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title3);
    info("Edit the page");
    String title4 = "title4" + getRandomNumber();
    String label4 = "label4" + getRandomNumber();
    String tooltip4 = "tooltip4" + getRandomNumber();
    wikiHomePage.goToAPage(title3);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title4, "");
    richTextEditor.changeLink(label);
    richTextEditor.goToEditLink();
    richTextEditor.editWikiPageLink("", label4, tooltip4);
    richTextEditor.removeLink(content2);
    getExoWebDriver().getWebDriver().navigate().refresh();
    richTextEditor.goToWikiPageLink();
    richTextEditor.insertExistWikiPageLink(title2, label4, tooltip4, RichTextEditor.wikiPageLinkTab.All_pages);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title3);
    info("Content of wiki page link is shown");
    wikiHomePage.goToAPage(title3);
    wikiManagement.viewInsertLink(label4);
    wikiValidattions.verifyPageContent(title2, content2);
    wikiHomePage.deleteWiki(title2);
    wikiHomePage.deleteWiki(title3);
    wikiHomePage.deleteWiki(title1);

  }

  /**
   * <li> Case ID:139536.</li>
   * <li> Test Case Name: Edit email address.</li>
   * <li> Pre-Condition: </li>
   * <li> Post-Condition: </li>
   * Step Number: 1
   * Step Name: Step 1: Open Email address form
   * Step Description:
   * - Add new page or edit a page
   * - Ensure the page isin [Rich Text] editor
   * - Select email address link in content
   * -Click [Link] in menu and select [Edit Link]
   * Input Data:
   * <p>
   * Expected Outcome:
   * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
   * - If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
   * - If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
   * - Attached File form appear
   * Step number: 2
   * Step Name: Step 2: Change attached file
   * Step Description:
   * - Change email address
   * - Click [Select]
   * - Input tooltip
   * - Click [Create Link] button
   * - Click [Save]
   * Input Data:
   * <p>
   * Expected Outcome:
   * Add attach file is changed successfully in content of page
   * - Page is add/edited successfully
   * Step number: 3
   * Step Name: Step 3: View the email address
   * Step Description:
   * - Click the email address
   * Input Data:
   * <p>
   * Expected Outcome:
   * - The Launch Application is shown that allows selecting an email app to be redirected to the email
   */
  @Test
  @Tag("wabis")
  public void test11_EditEmailAddressThenAddWebPageThenEditAttachedFile() {
    info("Edit email address");
    info("Create a wiki page with email address");
    String title1 = "title1" + getRandomNumber();
    String tooltip = "tooltip" + getRandomNumber();
    String label = "label" + getRandomNumber();
    String address = getRandomString() + "@gmail.com";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, "");
    richTextEditor.insertEmailLink(address, label, tooltip, true);
    wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title1);
    info("Edit the page with change email address");
    String title2 = "title2" + getRandomNumber();
    String label2 = "label2" + getRandomNumber();
    String address2 = getRandomString() + "@gmail.com";
    wikiHomePage.goToAPage(title1);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, "");
    richTextEditor.changeLink(label);
    richTextEditor.goToEditLink();
    richTextEditor.editWikiPageLink(address2, label2, "");
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title2);
    info("Click on email link to verify that the link is avaiable");
    wikiHomePage.goToAPage(title2);
    info("Verify that the inserted link is shown in the page");
    $(By.linkText(label2)).waitUntil(Condition.visible, Configuration.timeout);
    info("Add web page");
    info("Create a wiki page 1");
    String title0 = "title0" + getRandomNumber();
    String label0 = "label0" + getRandomNumber();
    String address0 = "www.google.com";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title0, "");
    richTextEditor.goToWebPageLink();
    richTextEditor.insertWebLink(address0, label0, "", true);
    wikiValidattions.verifyInsertedLinkIntoFrame(label0, "");
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title0);
    info("Edit the page");
    String title3 = "title3" + getRandomNumber();
    String label3 = "label3" + getRandomNumber();
    wikiHomePage.goToAPage(title0);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title3, "");
    richTextEditor.changeLink(label0);
    richTextEditor.goToEditLink();
    richTextEditor.insertWebLink(address0, label3, "", true);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title3);
    info("Edit attached file");
    info("Create a wiki page");
    String title4 = "title4" + getRandomNumber();
    String tooltip5 = "tooltip5" + getRandomNumber();
    String attachedFile = "eXo-Platform.png";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title4, "");
    richTextEditor.insertAttachedFileLink(attachedFile, true);
    wikiValidattions.verifyInsertedLinkIntoFrame(attachedFile, "");
    richTextEditor.editAttachedFileLink(attachedFile, "", tooltip5);
    wikiValidattions.verifyInsertedLinkIntoFrame("", tooltip5);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title4);
    info("The file is attached in the page");
    wikiHomePage.goToAPage(title4);
    wikiManagement.viewInsertLink(attachedFile);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
    wikiHomePage.deleteWiki(title3);
    wikiHomePage.deleteWiki(title4);

  }

  /**
   * <li> Case ID:139537.</li>
   * <li> Test Case Name: Edit image.</li>
   * <li> Pre-Condition: </li>
   * <li> Post-Condition: </li>
   * Step Number: 1
   * Step Name: Step 1: Open upload image form
   * Step Description:
   * - Add new page or edit a page
   * - Ensure the page is in [Rich Text]editor
   * - Choose image in page
   * -Click [Image] in menu and select [Edit Image...]
   * Input Data:
   * <p>
   * Expected Outcome:
   * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
   * - If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
   * - If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
   * - Attached File form appear
   * Step number: 2
   * Step Name: Step 2: Edit Attach image
   * Step Description:
   * - Select an image to insert from the list below, by clicking it or upload new image
   * - Click Image Settings
   * - Type the width/height of the image
   * - Choose the way the image is positioned in the text
   * - Choose the way the image is vertically aligned in the line of text
   * - Click Insert Image button
   * - Click [Save]
   * Input Data:
   * <p>
   * Expected Outcome:
   * - The attached file is added successfully in content of page
   * - Page is add/edited successfully
   */

  public void test12_EditImage() {
    info("Test 12 Edit image");
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String attachedFile = "eXo-Platform.png";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, "");
    richTextEditor.goToAttachedImageLink();
    richTextEditor.insertImage(attachedFile, "200", "200", "");
    richTextEditor.goToInsertImage();
    info("Move focus at the end of the line");
    wikiManagement.saveAddPage();
    info("Page is add/edited successfully");
    wikiValidattions.verifyTitleWikiPage(title);
    info("Edit the page with change image");
    String title2 = "title2" + getRandomNumber();
    String attachedFile2 = "testavatar.png";
    String altText2 = "altText2" + getRandomNumber();
    String width2 = "200";
    String height2 = "200";
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, "");
    richTextEditor.selectImage(attachedFile);
    richTextEditor.goToEditImageLink();
    richTextEditor.insertImage(attachedFile2, width2, height2, altText2);
    richTextEditor.goToInsertImage();
    info("Move focus at the end of the line");
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title2);
    info("Add attach file is added successfully in content of page");
    wikiHomePage.goToAPage(title2);
    wikiValidattions.verifyAltTextImageInContentPage(altText2);


  }

  /**
   * <li> Case ID:139607.</li>
   * <li> Test Case Name: Edit Paragraph When The Level Of Header Is Equal To Paragraph Below.</li>
   * <li> Pre-Condition: </li>
   * <li> Post-Condition: </li>
   * Step Number: 1
   * Step Name: Create New Wiki Page with 2 paragraphs with the same header
   * Step Description:
   * - Go to [Wiki]
   * - Click [Add Page]
   * -
   * -> [Blank Page]/[From Template...]
   * - Select [Source Editor] to switch to [Source Editor] mode
   * - Put title.
   * - Put content with 2 paragraphs with the same header (ex:= paragraph1 == paragraph2 =)
   * - Click [Save]
   * Input Data:
   * <p>
   * Expected Outcome:
   * - New page with 2 paragraphs with the same header is created successfully
   * Step number: 2
   * Step Name: Edit paragraph 1
   * Step Description:
   * - Click to Wiki page added in step 1
   * - Click to edit button for paragraph 1 (This button is a hidden button, only appear when hover in paragraph)
   * - Change Title of paragraph 1
   * - Click [Save]
   * Input Data:
   * <p>
   * Expected Outcome:
   * - Wiki page is in Edit screen, but only paragraph 1 appears in edit content.
   * - Paragraph 1 is edited title successfully. There is no change for paragraph 2.
   */
  @Test
  public void test14_EditParagraphWhenTheLevelOfHeaderIsEqualToParagraphBelowThenWhenTheLevelOfHeaderGreaterThanParagraphBelow() {
    info("Edit Paragraph When The Level Of Header Is Equal To Paragraph Below");
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String value1 = "value1" + getRandomNumber();
    String value2 = "value2" + getRandomNumber();
    String value3 = "value3" + getRandomNumber();
    String contentHeading1 = "=" + value1 + "=";
    String contentHeading2 = "==" + value2 + "==";
    String contentHeading3 = "===" + value3 + "===";
    String content = contentHeading1 + "\n" + contentHeading3;
    String title1 = "title1" + getRandomNumber();
    String content1 = contentHeading1 + "\n" + contentHeading2;


    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    info("The page is created successfully");
    wikiValidattions.verifyTitleWikiPage(title);
    info("The page is shown with Heading 1 effect");
    $(byId("H" + value1)).shouldHave(Condition.text(value1));
    assertEquals($(byId("H" + value1)).getCssValue("font-size"), "36px");
    info("The page is shown with Heading 1 effect");
    $(byId("H" + value3)).shouldHave(Condition.text(value3));
    assertEquals($(byId("H" + value3)).getCssValue("font-size"), "24px");
    info("Edit paragraph");
    wikiManagement.editParagraph(value1, contentHeading2);
    wikiManagement.saveAddPage();
    info("The page is shown with Heading 3 effect");
    $(byId("H" + value2)).shouldHave(Condition.text(value2));
    assertEquals($(byId("H" + value2)).getCssValue("font-size"), "30px");
    info("Edit Paragraph When The Level Of Header Greater Than Paragraph Below");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    info("The page is created successfully");
    wikiValidattions.verifyTitleWikiPage(title1);
    info("The page is shown with Heading 1 effect");
    $(byId("H" + value1)).shouldHave(Condition.text(value1));
    assertEquals($(byId("H" + value1)).getCssValue("font-size"), "36px");
    info("The page is shown with Heading 2 effect");
    $(byId("H" + value2)).shouldHave(Condition.text(value2));
    assertEquals($(byId("H" + value2)).getCssValue("font-size"), "30px");
    info("Edit paragraph");
    wikiManagement.editParagraph(value2, contentHeading3);
    wikiManagement.saveAddPage();
    info("The page is shown with Heading 3 effect");
    $(byId("H" + value3)).shouldHave(Condition.text(value3));
    assertEquals($(byId("H" + value3)).getCssValue("font-size"), "24px");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
    wikiHomePage.deleteWiki(title1);
  }

}