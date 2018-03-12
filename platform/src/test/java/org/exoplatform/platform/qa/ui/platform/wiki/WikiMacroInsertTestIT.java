package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_PAGE_WIKI_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

/**
 * Created by exo on 12/03/18.
 */
@Tag("wiki")
@Tag("sniff")
public class WikiMacroInsertTestIT extends Base {
  HomePagePlatform homePagePlatform;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  SourceTextEditor sourceTextEditor;

  ManageLogInOut   manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @Test
  @Tag("WIKI-1292")
  public void test01_InsertSectionMacro() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, "{{section}}" + content + "{{/section}}");
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    $(byText(title)).click();
    ELEMENT_PAGE_WIKI_CONTENT.shouldHave(Condition.text(content));
    wikiHomePage.deleteWiki(title);

  }
}
