package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
@Tag("wiki")
@Tag("sniff")
public class WikiBasicActionManagePageAddDeleteTestDWIT extends BaseDW {


  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  TribeWikiHomePage tribeWikiHomePage;

  WikiManagement wikiManagement;

  TribeWikiManagement tribeWikiManagement;

  TribeSourceTextEditor tribeSourceTextEditor;

  WikiValidattions wikiValidattions;

  WikiHomePage wikiHomePage;

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
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    tribeWikiManagement = new TribeWikiManagement(this);
    tribeSourceTextEditor = new TribeSourceTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);

  }

  @Test
  public void test01_AutoSaveWhenAddingPageFromHowToGuideThreeColumnLayoutStatusMeetingLeavePlanningTwoColumnLayoutTemplatesOnSpace() {
    info("Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();
    String title3 = "title3" + getRandomNumber();
    String title4 = "title4" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuideDW, title);
    tribeWikiHomePage.goToHomeWikiPage();
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_StatusMeetingDW, title3);
    tribeWikiHomePage.goToHomeWikiPage();
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanningDW, title4);
    info("Delete the space");
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space);
  }

  @Test
  public void test02_CreateDeletePageFromStatusMeetingTemplateOnSpace() {
    info("Create page from template");
    SelenideElement template = ELEMENT_SELECT_TEMPLATE_StatusMeetingDW;
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimpleWikiPageByTemplate(template, title);
    $(byText(title)).should(Condition.exist);
    tribeWikiHomePage.deleteWikiDW(title);
    info("Test 10: Delete data");
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

}
