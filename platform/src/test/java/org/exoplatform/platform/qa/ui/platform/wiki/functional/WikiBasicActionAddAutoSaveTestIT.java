package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

/**
 * Created by exo on 5/11/18.
 */

@Tag("wiki")
@Tag("functional")

public class WikiBasicActionAddAutoSaveTestIT extends Base {

  HomePagePlatform homePagePlatform;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  SourceTextEditor sourceTextEditor;

  ManageLogInOut   manageLogInOut;

  WikiValidattions wikiValidattions;

  SpaceManagement  spaceManagement;

  WikiDraftPage    wikiDraftPage;

  @BeforeEach
  public void setupBeforeMethod() {

    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    manageLogInOut = new ManageLogInOut(this);
    spaceManagement = new SpaceManagement(this);
    wikiDraftPage = new WikiDraftPage(this);
    wikiValidattions = new WikiValidattions(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:139436.</li>
   * <li>Test Case Name: Add a Draft to Wiki Intranet.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add new page Step
   * Description: - Login portal - Connect to [Intranet] - -> [Wiki] - Click [Add
   * page] - -> [Blank Page] - Enter title and content for the Wiki page Input
   * Data: Expected Outcome: - An empty template is displayed - The draft is
   * created after 30sA message is displayed "Draft saved at HH:MM"
   */
  @Test
  @Tag("wabis")
  public void test01_AddADraftToWikiIntranetThenWithoutTitle() {
    info("Test 1: Add a Draft to Wiki Intranet");

    info("Create a draf wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage(title);
    info("Resume the Draft");
    wikiDraftPage.resumeADraft(title);
    wikiManagement.goToSourceEditor();
    wikiValidattions.verifyResumADraf(title);
    info("Create a draf wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageHasAutoSaveWithoutSave("", content1);
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage("");
    wikiDraftPage.deleteDraft();
    wikiValidattions.verifyTitleDrafPage(title);
    wikiDraftPage.deleteDraft();
  }

}
