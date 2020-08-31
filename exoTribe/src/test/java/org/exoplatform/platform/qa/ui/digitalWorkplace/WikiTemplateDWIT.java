package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiSettingPage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TEMPLATE_SEARCH_TEXTBOX;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
@Tag("wiki")
@Tag("sniff")
public class WikiTemplateDWIT extends BaseDW {

  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  TribeWikiHomePage tribeWikiHomePage;

  WikiManagement wikiManagement;

  AddUsers addUsers;

  TribeWikiManagement tribeWikiManagement;

  TribeSourceTextEditor tribeSourceTextEditor;

  TribeRichTextEditor tribeRichTextEditor;

  WikiValidattions wikiValidattions;

  NavigationToolbar navigationToolbar;

  WikiHomePage wikiHomePage;

  WikiSettingPage wikiSettingPage;

  SpaceHomePage spaceHomePage;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  TribeSpaceManagement tribeSpaceManagement;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);

    spaceHomePage = new SpaceHomePage(this);
    tribeWikiHomePage = new TribeWikiHomePage(this);
    addUsers = new AddUsers(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    wikiSettingPage = new WikiSettingPage(this);
    tribeWikiManagement = new TribeWikiManagement(this);
    tribeSourceTextEditor = new TribeSourceTextEditor(this);
    tribeRichTextEditor = new TribeRichTextEditor(this);
    navigationToolbar = new NavigationToolbar(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);

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
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);

    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingPageDW();

    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);

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
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);

    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingPageDW();
    info("Edit created template");
    info("Go to Wiki Settings");
    info("Add a new template");
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
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);

    info("Search template when the key is matched");
    info("Go to Wiki Settings");
    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToWikiSettingPageDW();

    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);

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