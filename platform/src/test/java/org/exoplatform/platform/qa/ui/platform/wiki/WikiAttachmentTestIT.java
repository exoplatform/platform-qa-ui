package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
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
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(username, password);
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
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
  public void test01_UploadDownloadDeleteAttachment() {
    info("Test 1: Upload Attachment");
    String rand = getRandomNumber();
    String title = "title" + rand;
    String content = "content" + rand;

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    $(byClassName("uploadInput")).find(byName("file")).uploadFromClasspath("wiki_attachment.txt");
    ELEMENT_SAVE_BUTTON_ADD_PAGE.waitUntil(Condition.enabled, Configuration.timeout);
    wikiManagement.saveAddPage();
    waitForAndGetElement(ELEMENT_PAGE_ATTACHFILE, 3000, 1);

    click(ELEMENT_PAGE_ATTACHFILE);
    refresh();
    click(ELEMENT_PAGE_DOWNLOADATTACHFILE);

    click(ELEMENT_PAGE_DELETEATTACHFILE);
    waitForElementNotPresent(ELEMENT_PAGE_DOWNLOADATTACHFILE);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }
}
