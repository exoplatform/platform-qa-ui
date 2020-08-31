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

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_STREAM_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_WIKI_PAGE_CONTAINER;
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

}
