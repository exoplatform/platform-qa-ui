package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TEMPLATE_SEARCH_TEXTBOX;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiSettingPage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;

@Tag("functional")
@Tag("wiki")
public class WikiTemplateTestIT extends Base {
  HomePagePlatform homePagePlatform;

  WikiHomePage     wikiHomePage;

  WikiSettingPage  wikiSettingPage;

  WikiValidattions wikiValidattions;

  WikiManagement   wikiManagement;

  ManageLogInOut   manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiSettingPage = new WikiSettingPage(this);
    wikiValidattions = new WikiValidattions(this);
    wikiManagement = new WikiManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:139397.</li>
   * <li>Test Case Name: Create new template when using syntax.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Open form to
   * create new template Step Description: - Go to Browser -> Wiki Settings -
   * Select Template tab - Click on Add More Templates link Input Data: Expected
   * Outcome: Form to create new template appears* Step number: 2 Step Name: Step
   * 2: Create new template with Table tag Step Description: - Input |=Title
   * 1|=Title 2|Word 1|Word 2 - Click Save Template button Input Data: Expected
   * Outcome: The result is the table with 2 columms and 2 rows* Step number: 3
   * Step Name: Step 3: Create new template with Bold tag Step Description: -
   * Input text inside ** ** character in contentFor example: **bold** - Click
   * Save Template button Input Data: Expected Outcome: The template is shown with
   * Bold effect like Bold* Step number: 4 Step Name: Step 4: Create new template
   * with Bulleted list effect Step Description: - Input text inside start with *
   * character in contentFor example: * item 1** item 2*** item 3* item 4 - Click
   * Save Template button Input Data: Expected Outcome: The result is item 1 item
   * 2 Item 3 item 4* Step number: 5 Step Name: Step 5: Create new template with
   * italic tag Step Description: - Input text inside // // character in
   * contentFor example: //italic // - Click Save Template button Input Data:
   * Expected Outcome: The template is shown with Bold effect like italic Step
   * number: 6 Step Name: Step 6: Create new template with Heading tag Step
   * Description: - Input text inside == character in contentFor example:
   * =Heading1= - Click Save Template buttonOR - Input text inside == character in
   * contentFor example: ===Heading3=== - Click Save Template buttonOR - Input
   * text inside == character in contentFor example: =====Heading5===== - Click
   * Save Template button Input Data: Expected Outcome: - The template is shown
   * with Bold effect like Heading1 (large heading) - The template is shown with
   * Bold effect like Heading3 (normal heading) - The template is shown with Bold
   * effect like Heading5 (small heading)* Step number: 7 Step Name: Step 7:
   * Create new template with Link tag Step Description: - Input text inside [ ]]
   * character For example: [[Wiki Home]] - Click Save Template button Input Data:
   * Expected Outcome: The template is shown with link effect like Wiki Home* Step
   * number: 8 Step Name: Step 8: Create new template with Numbered list tag Step
   * Description: - Input text start with numberFor example: 1. item 111. item
   * 2111. item 31. item 4 - Click Save Template button Input Data: Expected
   * Outcome: The result is 1. item 1 1. item 2 1. item 3 2. item 4 Step number: 9
   * Step Name: Step 9: Create new template with strike tag Step Description: -
   * Input text inside - - - - character in contentFor example: - -strike - - -
   * Click Save Template button Input Data: Expected Outcome: The template is
   * shown with Bold effect like strikes Step number: 10 Step Name: Step 10:
   * Create new template with underline tag Step Description: - Input text
   * inside__ __character in contentFor example: __underline__ - Click Save
   * Template button Input Data: Expected Outcome: The template is shown with Bold
   * effect like underline
   */

  @Test
  public void test_CreateNewTemplateWhenUsingTableTagThenWhenUsingBoldTagThenWhenUsingBulletedListEffectThenWhenUsingItalicTagThenWhenUsingHeadingEffect() {
    info("Go to Wiki Settings");
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title1 = "title1" + getRandomNumber();
    String des1 = "des1" + getRandomNumber();
    String content1 = "|= |= \n" + "| | ";
    wikiSettingPage.addTemplate(title1, des1, content1);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title1);
    info("Verify that the template is a table with 2 column and 2 rows");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title1);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title1, title1);
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title1);
    info("The result is the table with 2 columms and 2 rows");
    wikiValidattions.verifyTableInContentPage(2, 2);
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title2 = "title2" + getRandomNumber();
    String des2 = "des2" + getRandomNumber();
    String value = "value" + getRandomNumber();
    String content2 = "**" + value + "**";
    wikiSettingPage.addTemplate(title2, des2, content2);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title2);
    info("Verify that the template has bold effect");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title2);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title2, title2);
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title2);
    info("The page is shown with Bold effect");
    wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bold, value);
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title3 = "title3" + getRandomNumber();
    String des3 = "des3" + getRandomNumber();
    String value1 = "value" + getRandomNumber();
    String value2 = "value" + getRandomNumber();
    String value3 = "value" + getRandomNumber();
    String value4 = "value" + getRandomNumber();
    String content3 = "* " + value1 + " \n" + "** " + value2 + "\n" + "*** " + value3 + "\n" + "* " + value4;
    wikiSettingPage.addTemplate(title3, des3, content3);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title3);
    info("The page is shown with Bullest list effect");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title3);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title3, "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title3);
    info("The page is shown with Bullest list effect");
    wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Bullest_List, value3);
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title4 = "title4" + getRandomNumber();
    String des4 = "des4" + getRandomNumber();
    String value5 = "value5" + getRandomNumber();
    String content4 = "//" + value5 + "//";
    wikiSettingPage.addTemplate(title4, des4, content4);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title4);
    info("The page is shown with Italic effect");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title4);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title4, "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title4);
    wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Italic, value5);
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title5 = "title5" + getRandomNumber();
    String des5 = "des5" + getRandomNumber();
    String value6 = "value6" + getRandomNumber();
    String contentHeading1 = "=" + value6 + "=";
    String contentHeading3 = "===" + value6 + "===";
    String contentHeading5 = "=====" + value6 + "=====";
    String content5 = contentHeading1 + "\n" + contentHeading3 + "\n" + contentHeading5;
    wikiSettingPage.addTemplate(title5, des5, content5);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title5);
    info("The page is shown with heading effects");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title5);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title5, title5);
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title5);
    info("The page is shown with Heading 1 effect");
    $(byId("H" + value6)).shouldHave(Condition.text(value6));
    assertEquals($(byId("H" + value6)).getCssValue("font-size"), "36px");
    info("The page is shown with Heading 3 effect");
    $(byId("H" + value6 + "-1")).shouldHave(Condition.text(value6));
    assertEquals($(byId("H" + value6 + "-1")).getCssValue("font-size"), "24px");
    info("The page is shown with Heading 5 effect");
    $(byId("H" + value6 + "-2")).shouldHave(Condition.text(value6));
    assertEquals($(byId("H" + value6 + "-2")).getCssValue("font-size"), "14px");
    wikiHomePage.goToWikiSettingPage();
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title1);
    wikiHomePage.deleteWiki(title2);
    wikiHomePage.deleteWiki(title3);
    wikiHomePage.deleteWiki(title4);
    wikiHomePage.deleteWiki(title5);
    wikiHomePage.goToWikiSettingPage();
    wikiSettingPage.deleteTemplate(title1);
    wikiSettingPage.deleteTemplate(title2);
    wikiSettingPage.deleteTemplate(title3);
    wikiSettingPage.deleteTemplate(title4);
    wikiSettingPage.deleteTemplate(title5);

  }

  @Test
  public void test_CreateNewTemplateWhenUsingLinkEffectThenWhenUsingNumberedEffectThenWhenUsingStrikeEffectThenWhenUsingUnderlineEffect() {

    info("Add a new template");
    String title6 = "title6" + getRandomNumber();
    String des6 = "des6" + getRandomNumber();
    String value7 = "value" + getRandomNumber();
    String content6 = "[[" + value7 + "]]";
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    wikiSettingPage.addTemplate(title6, des6, content6);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title6);
    info("The page is shown with link effect");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title6);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title6, "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title6);
    info("The page is shown with link effect");
    wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Link, value7);
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title7 = "title7" + getRandomNumber();
    String des7 = "des7" + getRandomNumber();
    String value8 = "value8" + getRandomNumber();
    String content7 = "1. " + value8 + " \n" + "111. " + value8 + "\n" + "2111. " + value8 + "\n" + "31. " + value8;
    wikiSettingPage.addTemplate(title7, des7, content7);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title7);
    info("The page is shown with Number list effect");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title7);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title7, "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title7);
    info("The page is shown with Number list effect");
    wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Number_List, value8);
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title8 = "title8" + getRandomNumber();
    String des8 = "des8" + getRandomNumber();
    String value9 = "value8" + getRandomNumber();
    String content8 = "--" + value9 + "--";
    wikiSettingPage.addTemplate(title8, des8, content8);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title8);
    info("The page is shown with Strike effect");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title8);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title8, "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title8);
    info("The page is shown with Strike effect");
    wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Strike, value9);
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title9 = "title9" + getRandomNumber();
    String des9 = "des9" + getRandomNumber();
    String value10 = "value10" + getRandomNumber();
    String content9 = "__" + value10 + "__";
    wikiSettingPage.addTemplate(title9, des9, content9);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title9);
    info("The page is shown with underline effect");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(title9);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(title9, "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title9);
    info("The page is shown with underline effect");
    wikiValidattions.verifyEffectsPageContent(WikiValidattions.effectTypes.Underline, value10);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title6);
    wikiHomePage.deleteWiki(title7);
    wikiHomePage.deleteWiki(title8);
    wikiHomePage.deleteWiki(title9);
    wikiHomePage.goToWikiSettingPage();
    wikiSettingPage.deleteTemplate(title6);
    wikiSettingPage.deleteTemplate(title7);
    wikiSettingPage.deleteTemplate(title8);
    wikiSettingPage.deleteTemplate(title9);

  }



    @Test
  public void test_SearchTemplateWhenTheKeyIsMatchedThenWhenKeyIsNotMatchedThenUsingTemplateToCreateNewPageThenSearchTemplateWhenSearchFieldIsBlank() {
    info("Using template to create new page");
    info("Go to Wiki Settings");
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    info("Add a new template");
    String title = "title" + getRandomNumber();
    String des = "des" + getRandomNumber();
    String content = "content" + getRandomNumber();
    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title);
    info("Edit a template");
    String newTitle = "newTitle" + getRandomNumber();
    String newDes = "newDes" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    wikiSettingPage.editTemplate(title, newTitle, newDes, newContent);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(newTitle);

    info("Search template when the key is not matched");
    info("Search template with search field is blank");
    String title2 = "title2" + getRandomNumber();
    $(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).setValue(title2).pressEnter();
    info("Verify that the searching is empty");
    wikiValidattions.verifyTemplateSearchEmpty();

    info("Search template when the key is matched");
    info("Search template with search field is blank");
    wikiSettingPage.searchTemplate(newTitle);
    info("Verify that any templates is shown in the list");
    wikiValidattions.verifyTemplateInList(newTitle);

    String title1 = "title1" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiSettingPage.searchTemplateByTitle(newTitle);
    wikiManagement.addSimpleWikiPageByTemplateBySearch(newTitle, title1);
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title1);
    wikiHomePage.deleteWiki(title1);
    info("Search template when Search field is blank");
    info("Go to Wiki Settings");
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    List arrayTemplate = new ArrayList();
    for (int i = 0; i < 3; i++) {
      info("Add a new template");
      String titleT = "title" + getRandomNumber();
      String desT = "des" + getRandomNumber();
      String contentT = "content" + getRandomNumber();
      wikiSettingPage.addTemplate(titleT, desT, contentT);
      wikiSettingPage.saveTemplate();
      wikiHomePage.confirmWaringMessage(true);
      info("Verify that new tempate is created. It'll be shown in template form");
      wikiValidattions.verifyTemplateInList(titleT);
      arrayTemplate.add(titleT);
      }
    info("Search template with search field is blank");
    wikiSettingPage.searchTemplate("");
    info("Verify that any templates is shown in the list");
    wikiValidattions.verifyTemplateInList(arrayTemplate.get(0).toString());
    wikiValidattions.verifyTemplateInList(arrayTemplate.get(1).toString());
    wikiValidattions.verifyTemplateInList(arrayTemplate.get(2).toString());
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    wikiSettingPage.deleteTemplateWithCanceling(newTitle);
    wikiSettingPage.deleteTemplate(newTitle);
    wikiSettingPage.deleteTemplate(arrayTemplate.get(0).toString());
    wikiSettingPage.deleteTemplate(arrayTemplate.get(1).toString());
    wikiSettingPage.deleteTemplate(arrayTemplate.get(2).toString());
  }

}
