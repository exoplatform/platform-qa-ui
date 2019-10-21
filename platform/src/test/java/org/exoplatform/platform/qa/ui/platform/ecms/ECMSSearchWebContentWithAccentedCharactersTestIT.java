package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_SEARCH_BTN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

/**
 * Created by esmaoui on 29/03/2018.
 */

@Tag("sniff")
@Tag("ecms")
public class ECMSSearchWebContentWithAccentedCharactersTestIT extends Base {

  ContentAdministration contentAdministration;

  NavigationToolbar     navigationToolbar;

  SiteExplorerHome      siteExplorerHome;

  CreateNewDocument     createNewDoc;

  ManageLogInOut        manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDoc = new CreateNewDocument(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }
  /*
   * Step Number: 1 Step Name: Prepare data: Create a web content with an accented
   * character: Expected Outcome: Web content is created successfully. Step
   * number: 2 Step Name: search for the the added web content: Expected Outcome:
   * The web content appeared in the list of search
   */
  @Tag("eabis")
  @Tag("ECMS-7688")
  @Test
  public void test_SearchWebContentWithAccentedCharacters() {
    String content = "content" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent("sécurité", content);
    createNewDoc.saveAndClose();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    ELEMENT_SEARCH_BTN.setValue("sécurité").pressEnter();
    sleep(Configuration.timeout);
    $(byId("hResult")).find(byXpath("//*[@id=\"SimpleSearchResult\"]/table/tbody/tr[1]/td[2]"))
                                 .find(byText("sécurité"))
                                 .shouldBe(Condition.visible);
    info("Delete web content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    siteExplorerHome.deleteData("sécurité");
  }
}
