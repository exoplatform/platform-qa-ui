package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TEMPLATE_SEARCH_TEXTBOX;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("wiki")
@Tag("sniff")
public class WikiTemplateDWIT extends BaseTribe {
  HomePagePlatform homePagePlatform;

  WikiHomePage wikiHomePage;

  WikiManagement wikiManagement;

  SourceTextEditor sourceTextEditor;

  WikiValidattions wikiValidattions;

  ActivityStream activityStream;

  RichTextEditor richTextEditor;

  ManageLogInOut manageLogInOut;

  SpaceManagement spaceManagement;

  SpaceHomePage spaceHomePage;

  NavigationToolbar navigationToolbar;

  SpaceSettingManagement spaceSettingManagement;

  TribeWikiHomePage tribeWikiHomePage;

  TribeWikiValidattions tribeWikiValidattions;

  TribeWikiManagement tribeWikiManagement;

  WikiPageInformation wikiPageInformation;

  TribeWikiPageInformation tribeWikiPageInformation;

  TribeSourceTextEditor tribeSourceTextEditor;

  TribeActivityStream tribeActivityStream;

  WikiSettingPage wikiSettingPage;

  TribeSpaceManagement tribeSpaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    activityStream = new ActivityStream(this);
    richTextEditor = new RichTextEditor(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    navigationToolbar = new NavigationToolbar(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    tribeWikiHomePage = new TribeWikiHomePage(this);
    tribeWikiValidattions = new TribeWikiValidattions(this);
    tribeWikiManagement = new TribeWikiManagement(this);
    wikiPageInformation = new WikiPageInformation(this);
    tribeWikiPageInformation = new TribeWikiPageInformation(this);
    tribeSourceTextEditor = new TribeSourceTextEditor(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    wikiSettingPage = new WikiSettingPage(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInTribe(tribe_username, tribe_password);
  }

  @Test
  public void test01_CreateNewTemplate() {

    info("Add a new template");
    String title = "title" + getRandomNumber();
    String des = "des" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create new template");
    info("Create a space");
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title);
    wikiSettingPage.deleteTemplate(title);

    info("Delete the space");
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test02_EditCreatedTemplate() {
    info("Add a new template");
    String title = "title" + getRandomNumber();
    String des = "des" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create new template");
    info("Create a space");
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    info("Edit created template");
    info("Go to Wiki Settings");
    info("Add a new template");
    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title);
    info("Edit a template");
    String newTitle = "newTitle" + getRandomNumber();
    String newDes = "newDes" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    wikiSettingPage.editTemplate(title, newTitle, newDes, newContent);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(newTitle);
    wikiSettingPage.deleteTemplate(newTitle);

    info("Delete the space");
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test03_SearchACreatedTemplate() {
    info("Add a new template");
    String title = "title" + getRandomNumber();
    String des = "des" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create new template");
    info("Create a space");
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title);

    info("Search template");
    wikiSettingPage.searchTemplate(title);
    info("Verify that any templates is shown in the list");
    wikiValidattions.verifyTemplateInList(title);
    wikiSettingPage.deleteTemplate(title);

    info("Delete the space");
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test04_CreateNewTemplateWhenUsingItalicWritingContent() {

    info("Create a space");
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    info("Add a new template");
    String title4 = "title4" + getRandomNumber();
    String des4 = "des4" + getRandomNumber();
    String value5 = "value5" + getRandomNumber();
    String content4 = "//" + value5 + "//";
    wikiSettingPage.addTemplate(title4, des4, content4);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title4);
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    wikiSettingPage.searchTemplate(title4);
    tribeWikiManagement.addSimpleWikiPageByTemplate($(byValue(title4)), "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title4);
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Italic, value5);
    tribeWikiHomePage.deleteWikiDW(title4);
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    wikiSettingPage.deleteTemplate(title4);

  }

  @Test
  public void test05_CreateNewTemplateWhenUsingHeadingEffectWritingContent() {

    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

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
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title5);
    info("The page is shown with heading effects");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    wikiSettingPage.searchTemplate(title5);
    tribeWikiManagement.addSimpleWikiPageByTemplate($(byValue(title5)), "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title5);
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Heading1, value6);
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Heading3, value6);
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Heading5, value6);
    tribeWikiHomePage.deleteWikiDW(title5);
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    wikiSettingPage.deleteTemplate(title5);

  }

  @Test
  public void test06_CreateNewTemplateWhenUsingLinkEffectWritingContent() {

    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    info("Add a new template");
    String title6 = "title6" + getRandomNumber();
    String des6 = "des6" + getRandomNumber();
    String value7 = "value" + getRandomNumber();
    String content6 = "[[" + value7 + "]]";
    wikiSettingPage.addTemplate(title6, des6, content6);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title6);
    info("The page is shown with link effect");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    wikiSettingPage.searchTemplate(title6);
    tribeWikiManagement.addSimpleWikiPageByTemplate($(byValue(title6)), "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title6);
    info("The page is shown with link effect");
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Link, value7);
    tribeWikiHomePage.deleteWikiDW(title6);
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    wikiSettingPage.deleteTemplate(title6);

  }

  @Test
  public void test07_CreateNewTemplateWhenUsingNumberedEffectWritingContent() {

    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    info("Add a new template");
    String title7 = "title7" + getRandomNumber();
    String des7 = "des7" + getRandomNumber();
    String value8 = "value8" + getRandomNumber();
    String content7 = "1. " + value8 + " \n" + "111. " + value8 + "\n" + "2111. " + value8 + "\n" + "31. " + value8;
    wikiSettingPage.addTemplate(title7, des7, content7);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title7);
    info("The page is shown with Number list effect");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    wikiSettingPage.searchTemplate(title7);
    tribeWikiManagement.addSimpleWikiPageByTemplate($(byValue(title7)), "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title7);
    info("The page is shown with Number list effect");
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Number_List, value8);
    tribeWikiHomePage.deleteWikiDW(title7);
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    wikiSettingPage.deleteTemplate(title7);

  }

  @Test
  public void test08_CreateNewTemplateWhenUsingStrikeEffectWritingContent() {

    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    info("Add a new template");
    String title8 = "title8" + getRandomNumber();
    String des8 = "des8" + getRandomNumber();
    String value9 = "value8" + getRandomNumber();
    String content8 = "--" + value9 + "--";
    wikiSettingPage.addTemplate(title8, des8, content8);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title8);
    info("The page is shown with Strike effect");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    wikiSettingPage.searchTemplate(title8);
    tribeWikiManagement.addSimpleWikiPageByTemplate($(byValue(title8)), "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title8);
    info("The page is shown with Strike effect");
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Strike, value9);
    tribeWikiHomePage.deleteWikiDW(title8);
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    wikiSettingPage.deleteTemplate(title8);

  }

  @Test
  public void test09_CreateNewTemplateWhenUsingUnderlineEffectWritingContent() {

    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    info("Add a new template");
    String title9 = "title9" + getRandomNumber();
    String des9 = "des9" + getRandomNumber();
    String value10 = "value10" + getRandomNumber();
    String content9 = "__" + value10 + "__";
    wikiSettingPage.addTemplate(title9, des9, content9);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title9);
    info("The page is shown with underline effect");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    wikiSettingPage.searchTemplate(title9);
    tribeWikiManagement.addSimpleWikiPageByTemplate($(byValue(title9)), "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title9);
    info("The page is shown with underline effect");
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Underline, value10);
    tribeWikiHomePage.deleteWikiDW(title9);
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    wikiSettingPage.deleteTemplate(title9);

  }
  @Test
  public void test10_CheckTheSearchOfADeletedTemplate() {

    info("Add a new template");
    String title = "title" + getRandomNumber();
    String des = "des" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create new template");
    info("Create a space");
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title);

    wikiSettingPage.searchTemplate(title);
    wikiSettingPage.deleteTemplate(title);

    info("Search A Deleted Template");
    getExoWebDriver().getWebDriver().navigate().refresh();
    $(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).val(title);
    $(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).pressEnter();

    info("Verify that the searching is empty");
    $(byId("UIWikiTemplateGrid")).find(byText(title)).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    wikiValidattions.verifyTemplateSearchEmpty();

    info("Delete the space");
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test11_UseACreatedTemplateToAddNewPage() {
    info("Add a new template");
    String title = "title" + getRandomNumber();
    String des = "des" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create new template");
    info("Create a space");
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Using template to create new page");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);
    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title);
    String title1 = "title1" + getRandomNumber();

    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave($(byValue(title)), title1);

    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title1);
    tribeWikiHomePage.deleteWikiDW(title1);
    info("Delete the space");
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test12_CreateNewTemplateWhenUsingBulletedListEffectWritingContent() {
    info("Add a new template");
    String title3 = "title3" + getRandomNumber();
    String des3 = "des3" + getRandomNumber();
    String value1 = "value" + getRandomNumber();
    String value2 = "value" + getRandomNumber();
    String value3 = "value" + getRandomNumber();
    String value4 = "value" + getRandomNumber();
    String content3 = "* " + value1 + " \n" + "** " + value2 + "\n" + "*** " + value3 + "\n" + "* " + value4;

    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();

    wikiSettingPage.addTemplate(title3, des3, content3);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessageTribe(true);

    info("Verify that new tempate is created. It'll be shown in template form");
    wikiValidattions.verifyTemplateInList(title3);
    info("The page is shown with Bullest list effect");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    wikiSettingPage.searchTemplate(title3);
    tribeWikiManagement.addSimpleWikiPageByTemplate($(byValue(title3)), "");
    info("New page is created successfully. It is displayed in the destination path");
    wikiValidattions.verifyTitleWikiPage(title3);
    info("The page is shown with Bullest list effect");
    wikiValidattions.verifyEffectsPageContentDW(WikiValidattions.effectTypes.Bullest_List, value3);
    tribeWikiHomePage.deleteWikiDW(title3);
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingTribe();
    wikiSettingPage.deleteTemplate(title3);

  }

}
