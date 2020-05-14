package org.exoplatform.platform.qa.ui.platform.ecms;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
@Tag("ecms")
@Tag("sniff")
public class EcmsSESearchTestIT extends Base {
  NavigationToolbar navigationToolbar;
  SiteExplorerHome  siteExplorerHome;
  ManageLogInOut    manageLogInOut;
  ManageAlert       manageAlert;
  CreateNewDocument createNewDocument;
  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    manageAlert = new ManageAlert(this);
    manageLogInOut = new ManageLogInOut(this);
    createNewDocument = new CreateNewDocument(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }
  @Test
  public void test01_CreateEditQueryInAdvancedSearch() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Create query in Advanced search");
    String name = "name" + getRandomNumber();
    String name1 = "name" + getRandomNumber();
    info("Set up browsing Preferences");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.openSettingsDriver(SiteExplorerHome.selectDriverOption.MODIFIEDDATE,
            SiteExplorerHome.selectDriverOrder.DESCENDING);
    $(By.xpath("//*[@class='nodeLabel']//*[text()='intranet']")).waitUntil(Condition.visible, Configuration.timeout);
    siteExplorerHome.openSettingsDriver(SiteExplorerHome.selectDriverOption.ALPHABETICAL,
            SiteExplorerHome.selectDriverOrder.ASCENDING);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY).setValue(name);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN).click();
    $(byXpath("//*[@id='UISavedQuery']//*[text()='" + name + "']")).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath((ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN).replace("${name}", name))).click();
    manageAlert.acceptAlert();
    $(byXpath("//*[text()='" + name + "']")).shouldNot(Condition.visible);
    ELEMENT_BUTTON_CLOSE_SEARCH.click();
    sleep(2000);
    info("Execute query in Advanced search");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY).setValue(name);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN).click();
    $(byXpath("//*[text()='" + name + "']")).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERYBTN).click();
    $(byXpath("//*[@class='active']//*[@href='#tab-AdvancedSearchResult']")).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_BUTTON_CLOSE_SEARCH.click();
    info("Edit query in Advanced search");
    sleep(1000);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY).setValue(name1);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN).click();
    $(byXpath(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT.replace("${name}", name1))).waitUntil(Condition.visible,
            Configuration.timeout);
    $(byXpath((ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EDITQUERYBTN).replace("${name}", name1))).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_QUERYTYPE).selectOption("xPath");
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEEDITQUERYBTN).click();
    $(byXpath(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT1.replace("${name}", name1))).waitUntil(Condition.visible,
            Configuration.timeout);
    $(byXpath((ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN).replace("${name}", name1))).click();
    manageAlert.acceptAlert();
    $(byXpath(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT.replace("${name}", name1))).shouldNot(Condition.visible);
    ELEMENT_BUTTON_CLOSE_SEARCH.click();
    info("Simple Search");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    sleep(Configuration.openBrowserTimeoutMs);
    $(ELEMENT_ACTIONBAR_SEARCHBAR).setValue(title).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).pressEnter();
    sleep(2000);
    $(byId("SimpleSearchResult")).find(byText(title)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }
}
