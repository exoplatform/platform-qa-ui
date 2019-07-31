package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

/**
 * Created by exo on 5/11/18.
 */

@Tag("functional")
@Tag("wiki")

public class Wiki_BasicActionAddDraftOthersTemplateTestIT extends Base {

  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  WikiValidattions wikiValidattions;

  RichTextEditor   richTextEditor;

  ManageLogInOut   manageLogInOut;

  WikiDraftPage    wikiDraftPage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    richTextEditor = new RichTextEditor(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiDraftPage = new WikiDraftPage(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");

  }

  /**
   * <li>Case ID:118451.</li>
   * <li>Test Case Name: Add a page with link wiki page existed.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Step Description:
   * - Connect with the user A - Go to [Intranet] --> [Wiki] - Click [Add Page]
   * --> [Blank Page]/[From Template...] - Enter the required fields without
   * saving for 30s Input Data: Expected Outcome: The draft is saved after 30s
   * Step Number: 2 Step Name: Step 2: Step Description: - Connect with the user B
   * - Go to [Intranet] --> [Wiki] - From the menu "Browse", choose "My drafts"
   * Input Data: Expected Outcome: The draft created by the user A doesn't appear
   * in the list of drafts of the user B
   */
  @Test
  public void test01_ViewADraftForAnotherUser() {
    info("Test 1: View a draft for another user");

    info("Create a draft");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
    info("Verify draft exists in draft list");
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyDraftExistsInDraftListOrNot(title, true);
    info("Sign in with Mary");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("Go to Myfraft and verify that draft created in step 1 does not exist in draft list");
    homePagePlatform.goToWiki();
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyDraftExistsInDraftListOrNot(title, false);
    info("Clear Data");
    info("Sign in as John");

  }

  /**
   * <li>Case ID:118333.</li>
   * <li>Test Case Name: Preview a page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Open form to
   * create new page Step Description: - Choose path to add new page - Click [Add
   * Page] --> [Blank Page]/[From Template...] Input Data: Expected Outcome: Form
   * to create new page is shown and in the [Rich Text] editor Step Number: 2 Step
   * Name: Step 2: Create new page Step Description: - Put the title for this page
   * - Put the content of page Input Data: Expected Outcome: All fields are
   * inputed with values Step Number: 3 Step Name: Step 3: Preview a page Step
   * Description: - Click on Preview Input Data: Expected Outcome: Content of page
   * is shown
   */
  @Test
  @Tag("wabis")
  public void test02_PreviewAPage() {
    info("Test 2: Preview a page");
    info("Go to Add a Wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.PreviewASimplePage(title, content);
    $(ELEMENT_CLOSE_PREVIEW_WINDOW).click();
  }

  /**
   * <li>Case ID:122803.</li>
   * <li>Test Case Name: Preview a page with image by another user.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add Wiki page with
   * external image with Admin User Step Description: - Go to [Intranet] -->
   * [Wiki] - Click [Add Page] --> [Blank Page]/[From Template...] - Ensure the
   * page is in the [Rich Text] mode - Input data valid for Title page and Page's
   * content - Click Image in menu - Select [External Image...] - Type or paste
   * the address of the image to insert, e.g. 'www.example.com/image.png' - Click
   * [Image Settings] - Type the width/height of the image - Choose the way the
   * image is positioned in the text - Choose the way the image is vertically
   * aligned in the line of text - Click [Insert Image] button - Click [Save]
   * Input Data: Expected Outcome: - By default, the [Create Wiki page] is
   * displayed in the [Rich Text] mode - The attached file is added successfully
   * in content of page - Page is add/edited successfully Step Number: 2 Step
   * Name: Step 2: Another user can see the image inserted in page Step
   * Description: - Login by user/group has permission to edit/view page - Go to
   * [Intranet] --> [Wiki] - Click Wiki Page created in step 1 Input Data:
   * Expected Outcome: User can see the image inserted in this wiki page.
   */
  @Test
  public void test03_PreviewAPageWithImageByAnotherUser() {
    info("Test 3: Preview a page with image by another user");
    info("Go to Add a Wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String linkImage = "http://www.keejob.com/media/recruiter/recruiter_10926/logo-10926-20160713-102629.png";
    String altText = "altText" + getRandomNumber();
    String width = "200";
    String height = "200";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    richTextEditor.insertExternalImageLink(linkImage, width, height, altText);
    richTextEditor.selectAlign(RichTextEditor.alignType.Left);
    richTextEditor.goToInsertImage();
    wikiManagement.saveAddPage();
    info("Page is add/edited successfully");
    wikiValidattions.verifyTitleWikiPage(title);

    info("Add attach file is added successfully in content of page");
    wikiHomePage.goToAPage(title);
    wikiValidattions.verifyAltTextImageInContentPage(altText);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(title);
    wikiValidattions.verifyAltTextImageInContentPage(altText);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);

  }

  /**
   * <li>Case ID:118315.</li>
   * <li>Test Case Name: Create new page using existing template.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Open form to
   * create new page Step Description: - Choose path to add new page - Click [Add
   * Page] --> [From Template...] Input Data: Expected Outcome: - By default, the
   * [Create Wiki page] is displayed in the [Rich Text] mode Step Number: 2 Step
   * Name: Step 2: Create new page with Two Column layout Step Description: -
   * Choose template [Two Column layout] in list and click [Select] - Click
   * [Preview] if you want to see how your page looks like - Select [Source
   * Editor] to switch to [Source Editor] mode - Put the title for this page - Put
   * the content of page - Click [Save] Input Data: Expected Outcome: New page is
   * created successfully with two column layout Step Number: 3 Step Name: Step 3:
   * Create new page with Three Column layout Step Description: - Choose template
   * [Three Column layout] in list and click [Select] - Click [Preview] if you
   * want to see how your page looks like. - Select [Source Editor] to switch to
   * [Source Editor] mode - Put the title for this page - Put the content of page
   * - Click [Save] Input Data: Expected Outcome: New page is created successfully
   * with three column layout Step Number: 4 Step Name: Step 4: Create new page
   * with Status Meeting layout Step Description: - Choose template "Status
   * Meeting" in list and click [Select] - Click [Preview] if you want to see how
   * your page looks like. - Select [Source Editor] to switch to [Source Editor]
   * mode - Put the title for this page - Put the content of page - Click [Save]
   * Input Data: Expected Outcome: New page is created successfully with Status
   * Meeting layout Step Number: 5 Step Name: Step 5: Create new page with HOW-TO
   * Guide layout Step Description: - Choose template [HOW-TO Guide] layout in
   * list and click [Select] - Click [Preview] if you want to see how your page
   * looks like - Select [Source Editor] to switch to [Source Editor] mode - Put
   * the title for this page - Put the content of page - Click [Save] Input Data:
   * Expected Outcome: New page is created successfully with HOW-TO Guide layout
   * Step Number: 6 Step Name: Step 6: Create new page with Leave Planning layout
   * Step Description: - Choose template [Leave Planning] layout in list and click
   * [Select] - Click [Preview] if you want to see how your page looks like -
   * Select [Source Editor] to switch to [Source Editor] mode - Put the title for
   * this page - Put the content of page - Click [Save] Input Data: Expected
   * Outcome: New page is created successfully with Leave Planning layout
   */
  @Test
  public void test04_CreateNewPageUsingExistingTemplate() {
    info("Test 4: Create new page using existing template");
    SelenideElement template1 = ELEMENT_SELECT_TEMPLATE_HowToGuide;
    String title1 = "HOW-TO Guide";
    SelenideElement template2 = ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout;
    String title2 = "Three-Column Layout";
    SelenideElement template3 = ELEMENT_SELECT_TEMPLATE_StatusMeeting;
    String title3 = "Status Meeting";
    SelenideElement template4 = ELEMENT_SELECT_TEMPLATE_LeavePlanning;
    String title4 = "Leave Planning";
    SelenideElement template5 = ELEMENT_SELECT_TEMPLATE_TwoColumnLayout;
    String title5 = "Two-Column Layout";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(template1, title1);
    info("Page is add/edited successfully");
    wikiValidattions.verifyTitleWikiPage(title1);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(template2, title2);
    info("Page is add/edited successfully");
    wikiValidattions.verifyTitleWikiPage(title2);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(template3, title3);
    info("Page is add/edited successfully");
    wikiValidattions.verifyTitleWikiPage(title3);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(template4, title4);
    info("Page is add/edited successfully");
    wikiValidattions.verifyTitleWikiPage(title4);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(template5, title5);
    info("Page is add/edited successfully");
    wikiValidattions.verifyTitleWikiPage(title5);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title1);
    wikiHomePage.deleteWiki(title2);
    wikiHomePage.deleteWiki(title3);
    wikiHomePage.deleteWiki(title4);
    wikiHomePage.deleteWiki(title5);
  }

}
