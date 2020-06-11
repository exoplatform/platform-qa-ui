package org.exoplatform.platform.ui.qa.wiki;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("wiki")
@Tag("smoke")
public class WikiPublishActivityTestIT extends Base {

  HomePagePlatform homePagePlatform;

  WikiHomePage wikiHomePage;

  WikiManagement wikiManagement;

  RichTextEditor richTextEditor;

  ActivityStream activityStream;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    activityStream = new ActivityStream(this);
    try {
      richTextEditor = new RichTextEditor(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  @Test
  @Tag("search")
  public void test01_Create_Delete_SearchWikiPageFromHomeSearchBar() {

    String title = "title" + getRandomNumber();
    String line1 = "line1" + getRandomNumber();
    String line2 = "line2" + getRandomNumber();
    String line3 = "line3" + getRandomNumber();
    String line4 = "line4" + getRandomNumber();
    String line5 = "line5" + getRandomNumber();
    String content = line1 + "</br>" + line2 + "</br>" + line3 + "</br>" + line4 + "</br>" + line5;

    String title1 = "title" + getRandomNumber();
    String content1 = "content" + getRandomNumber();
    String title2 = "title" + getRandomNumber();
    String content2 = "content" + getRandomNumber();
    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    homePagePlatform.goToHomePage();
    activityStream.checkActivityAddWikiPage(title, content, null);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, content2);
    wikiManagement.saveAddPage();
    info("Verify that the new wiki page is created successfully");
    $(byText(title2)).should(Condition.exist);

    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil(ELEMENT_ICON_SEARCH, Condition.visible, 2000);
    activityStream.checkActivity(title2);
    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(title);
    ELEMENT_SEARCH_RESULT.shouldHave(Condition.exactText(title));
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
    wikiHomePage.deleteWiki(title1);
    wikiHomePage.deleteWiki(title2);
    homePagePlatform.goToHomePage();
    $(byText(title2)).shouldNot(Condition.exist);
    $(byText(title1)).shouldNot(Condition.exist);
    $(byText(title)).shouldNot(Condition.exist);

  }
}
