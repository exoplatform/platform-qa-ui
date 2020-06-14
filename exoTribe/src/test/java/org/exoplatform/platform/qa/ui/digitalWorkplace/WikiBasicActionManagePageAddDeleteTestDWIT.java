package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.pageobject.*;
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
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    String title3 = "title3" + getRandomNumber();
    String title4 = "title4" + getRandomNumber();
    String title5 = "title4" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuide, title);
    tribeWikiHomePage.goToHomeWikiPage();
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout, title2);
    tribeWikiHomePage.goToHomeWikiPage();
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_StatusMeeting, title3);
    tribeWikiHomePage.goToHomeWikiPage();
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title4);
    tribeWikiHomePage.goToHomeWikiPage();
    tribeWikiHomePage.goToAddTemplateWikiPageDW();
    tribeWikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout, title5);
    info("Delete the space");
    homePagePlatform.goToStreamPageTribe();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);
  }

  /**
   * <li>Case ID:122834.</li>
   * <li>Test Case Name: Create page from template.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Create Page from template Step Description:
   * - Go to wiki porlet - Click on Add Page -> From Template icon in toolbar
   * action - Choose a template in list and click Select - Click on Save icon in
   * toolbar Input Data: Expected Outcome: Page is added successful and listed in
   * navigation tree Step Number: 1 Step Name: Step 1: Delete page with template
   * layout Step Description: - Open an existing page by clicking on page name in
   * navigation tree. - Click on More icon on toolbar and select Delete page
   * action in menu - Click on OK button on Confirm message form Input Data:
   * Expected Outcome: Delete page successfully
   */
  @Test
  public void test02_CreateDeletePageFromStatusMeetingTemplateOnSpace() {
    info("Test 04: Create page from template");
    SelenideElement template = ELEMENT_SELECT_TEMPLATE_StatusMeeting;
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
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
