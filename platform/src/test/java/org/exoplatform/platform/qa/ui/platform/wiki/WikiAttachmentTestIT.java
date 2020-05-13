package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

@Tag("sniff")
@Tag("wiki")
public class WikiAttachmentTestIT extends Base {

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
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }


  /**
   * <li>Case ID:122812.</li>
   * <li>Test Case Name: Upload Attachment.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:122816.</li>
   * <li>Test Case Name: Download attachment.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:122873.</li>
   * <li>Test Case Name: Delete attachment.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Upload Attachment in Page Step Description:
   * - Open Add/Edit page form - Upload attachments Input Data: Expected Outcome:
   * - Attachment(s) is uploaded successful and listed in Attachment table with
   * properties
   */

  @Test
  @Tag("wabis")
  public void test01_RenamePageAndUploadDownloadDeleteAttachment() {
    info("Test 1: Upload Attachment");
    String rand = getRandomNumber();
    String title = "title" + rand;
    String content = "content" + rand;
    String content1 = "content1" + rand;
    String title1 = "title1" + rand;
    String title2 = "title2" + rand;

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    $(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    info("Double click on the title of the page");
    wikiHomePage.selectAPage(title1);
    wikiManagement.renamePageByDoubleClick(title1, title2);
    $(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    sourceTextEditor.addSimplePage(title, content);
    $(byClassName("uploadInput")).find(byName("file")).uploadFromClasspath("wiki_attachment.txt");
    ELEMENT_SAVE_BUTTON_ADD_PAGE.waitUntil(Condition.enabled, Configuration.timeout);
    wikiManagement.saveAddPage();
    $(ELEMENT_PAGE_ATTACHFILE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    refresh();
    $(ELEMENT_PAGE_DOWNLOADATTACHFILE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_PAGE_DELETEATTACHFILE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_PAGE_DOWNLOADATTACHFILE).waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
    wikiHomePage.deleteWiki(title2);

  }
}
