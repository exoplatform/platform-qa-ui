package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
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

  /**
   * <li>Case ID:116566.</li>
   * <li>Test Case Name: Advanced search.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_AdvancedSearch() {
    info("Test 1: Advanced search");

    String name = "name" + getRandomNumber();

    navigationToolbar.goToSiteExplorer();
    /*
     * Step Number: 1 Step Name: Step 1: Advanced search Step Description: - Go to
     * Sites Explorer - Click Saved Searched icon on the sidebar - Click Advanced
     * Search icon - Choose Constraint and fill value to search, click Add - Click
     * Search button Input Data: Expected Outcome: Search Result tab is shown with
     * result
     */
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAME).setValue(name);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SEARCHBTN).click();
    $(By.xpath("//*[@class='active']//*[@href='#tab-AdvancedSearchResult']")).waitUntil(Condition.visible, Configuration.timeout);

  }

  /**
   * <li>Case ID:116594.</li>
   * <li>Test Case Name: Create query in Advanced search.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:116647.</li>
   * <li>Test Case Name: Delete query in Advanced search.</li>
   * <li>Pre-Condition: A query is created.</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Create query in Advanced search Input Data: - Go to Sites Explorer - Click
   * Saved search icon on the sidebar - Click advanced search - Go to New query
   * tab - Perform to create a query Expected Outcome: Query is created
   * successfully
   */
  @Test
  public void test02_CreateQueryInAdvancedSearch() {
    info("Test 2: Create query in Advanced search");
    String name = "name" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY).setValue(name);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN).click();
    $(byXpath("//*[@id='UISavedQuery']//*[text()='" + name + "']")).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath((ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN).replace("${name}", name))).click();
    manageAlert.acceptAlert();
    $(byXpath("//*[text()='" + name + "']")).shouldNot(Condition.visible);
  }

  @Test
  public void test04_DeleteQueryInAdvancedSearch() {
    info("Test 2: Create query in Advanced search");
    String name = "name" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY).setValue(name);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN).click();
    $(byXpath("//*[@id='UISavedQuery']//*[text()='" + name + "']")).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath((ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN).replace("${name}", name))).click();
    manageAlert.acceptAlert();
    $(byXpath("//*[text()='" + name + "']")).shouldNot(Condition.visible);
  }

  /**
   * <li>Case ID:116607.</li>
   * <li>Test Case Name: Simple Search.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @BugInPLF("ECMS-7683")
  public void test03_SimpleSearch() {
    info("Test 3: Simple Search");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Simple Search Input
     * Data: - Go to Content Explorer - Input keyword into Search field above action
     * bar - Click Quick Search or press Enter Expected Outcome: The result appears:
     * all documents which has title or content including search keyword appear
     */
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    $(ELEMENT_ACTIONBAR_SEARCHBAR).setValue(title).pressEnter();
    $(byId("SimpleSearchResult")).find(byText(title)).should(Condition.visible);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116648.</li>
   * <li>Test Case Name: Execute query in Advanced search.</li>
   * <li>Pre-Condition: A query is created.</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Execute query in Advanced search Input Data: - Go to Sites Explorer -
   * Click Saved search icon on the sidebar - Click advanced search - Go to Saved
   * Query tab - Click Execute icon of a saved query Expected Outcome: Query is
   * executed successfully
   */
  @Test
  public void test05_ExecuteQueryInAdvancedSearch() {
    info("Test 5: Execute query in Advanced search");
    String name = "name" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY).setValue(name);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN).click();
    $(byXpath("//*[text()='" + name + "']")).waitUntil(Condition.visible, Configuration.timeout);

    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERYBTN).click();
    $(byXpath("//*[@class='active']//*[@href='#tab-AdvancedSearchResult']")).waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * <li>Case ID:116649.</li>
   * <li>Test Case Name: Edit query in Advanced search.</li>
   * <li>Pre-Condition: A query is created.</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Edit query in Advanced search Input Data: - Go to Sites Explorer - Click
   * Saved search icon on the sidebar - Click advanced search - Go to Saved Query
   * tab - Click on corresponding Edit icon of a query - Perform to edit the query
   * Expected Outcome: Query is edited successfully
   */
  @Test
  public void test06_EditQueryInAdvancedSearch() {
    info("Test 6: Edit query in Advanced search");
    String name = "name" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAdvancedSearch();
    $(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY).setValue(name);
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN).click();
    $(byXpath(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT.replace("${name}", name))).waitUntil(Condition.visible,
                                                                                              Configuration.timeout);
    $(byXpath((ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EDITQUERYBTN).replace("${name}", name))).click();
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_QUERYTYPE).selectOption("xPath");
    $(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEEDITQUERYBTN).click();
    $(byXpath(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT1.replace("${name}", name))).waitUntil(Condition.visible,
                                                                                               Configuration.timeout);
    $(byXpath((ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN).replace("${name}", name))).click();
    manageAlert.acceptAlert();
    $(byXpath(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT.replace("${name}", name))).shouldNot(Condition.visible);
  }
}
