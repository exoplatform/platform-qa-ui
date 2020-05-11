package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

@Tag("ecms")
@Tag("sniff")
public class EcmsSEAdminTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  SiteExplorerHome      siteExplorerHome;

  CreateNewDocument     createNewDocument;

  ManageLogInOut        manageLogInOut;

  ContentAdministration contentAdministration;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    contentAdministration = new ContentAdministration(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }


  @Test
  @Tag("eabis")
  public void test01_Add_Delete_Relation() {

    info("Add Relation");
    info("Create data test");
    String node1 = "node1" + getRandomNumber();
    String node2 = "node2" + getRandomNumber();
    String node3 = "node3" + getRandomNumber();
    String property = "exo:summary";

    info("Finished data test");
    // Declare a string array
    String[] nameContent = { node1 };

    info("Create content 1");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    $(byClassName("uiIconEcmsViewWeb")).waitUntil(Condition.visible,Configuration.timeout).click();
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node1, node1);
    createNewDocument.saveAndClose();

    siteExplorerHome.selectNode("documents");
    info("Create content 3");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node3, node3);
    createNewDocument.saveAndClose();
    siteExplorerHome.selectNode(node3);
    info("Click on More link ");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(byClassName("uiIconEcmsViewAdmin")).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ACTIONBAR_MORE).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Add relation");
    siteExplorerHome.goToManageRelation();
    siteExplorerHome.addRelation(nameContent, "sites/intranet/documents");
    info("Delete Relation");
    siteExplorerHome.deleteRelation(node1.toLowerCase());
    siteExplorerHome.closeAddRelationPopup();
    // Check Relation on left of sideBar
    siteExplorerHome.goToRelationSideBar();
    $(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}", node1))).shouldNot(Condition.exist);

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    sleep(Configuration.timeout);
    $(byClassName("uiIconEcmsViewWeb")).waitUntil(Condition.visible,Configuration.timeout).click();
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node1, node1);
    createNewDocument.saveAndClose();

    siteExplorerHome.selectNode("documents");

    info("Create content 2");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node2, node2);
    createNewDocument.saveAndClose();
    siteExplorerHome.selectNode(node2);
    info("Click on More link ");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(byClassName("uiIconEcmsViewAdmin")).click();
    $(ELEMENT_ACTIONBAR_MORE).click();
    info("Add relation");
    siteExplorerHome.goToManageRelation();
    siteExplorerHome.addRelation(nameContent, "sites/intranet/documents");
    siteExplorerHome.closeAddRelationPopup();

    info("Show/Hide Relation");
    // Show relation
    siteExplorerHome.goToRelationSideBar();
    $(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}", node1))).waitUntil(Condition.visible,
            Configuration.timeout);
    // Hide relation
    executeJavaScript("window.scrollBy(0,-2000)", "");
    $(byClassName("uiIconEcmsViewWeb")).click();
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();

    info("Select relation tab of SE");
    siteExplorerHome.goToRelationSideBar();
    $(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}", node1))).waitUntil(Condition.visible,
            Configuration.timeout);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}", node1))).click();

    info("Verify the file in reference section");
    $(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}", node2))).waitUntil(Condition.visible,
            Configuration.timeout);

    info("Delete all data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(node1);
    siteExplorerHome.deleteData(node2);
    siteExplorerHome.deleteData(node3);

  }

  /**
   * <li>Case ID:116578.</li>
   * <li>Test Case Name: Export a node.</li>
   * <li>Case ID:116655.</li>
   * <li>Test Case Name: Import a node</li> Step Number: 1 Step Name: Export a
   * node Step description: - Select one node - On action bar, click Export button
   * - Choose System View in Format - Click on Export button - Click Save in the
   * bottom message to export. Expected Outcome: - Node is exported successfully.
   */
  @Test
  @Tag("eabis")
  public void test02_ExportANode() {
    info("Test 4: Export a Node");
    info("Create data test");
    String node1 = "node1" + getRandomNumber();
    info("Finished data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    $(byXpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}", "Admin"))).click();
    $(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
    $(ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM).click();
    $(byId("exportNode")).parent().click();
    if ($(byId("exportNode")).is(Condition.not(Condition.selected))) {
      $(byId("exportNode")).parent().click();
    }
    $(ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Add New folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    sleep(Configuration.timeout);
    $(byClassName("uiIconEcmsViewWeb")).waitUntil(Condition.visible,Configuration.timeout).click();
    siteExplorerHome.goToAddNewFolder();
    info("Create Folder node");
    createNewDocument.createNewFolder(node1, CreateNewDocument.folderType.Content);
    info("Select folder");
    siteExplorerHome.selectNode(node1);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(byClassName("uiIconEcmsViewAdmin")).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Export a node");
    siteExplorerHome.goToExportNode();
    siteExplorerHome.exportNode(true, false);
    info("Delete all data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    $(byClassName("uiIconEcmsViewWeb")).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CONTENT_LIST.find(byLinkText(node1)).contextClick();
    info("Click on Delete link");
    $(ELEMENT_SITEEXPLORER_ACTION_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Click on Delete button on Confirm popup");
    $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();

  }

  @Test
  @Tag("eabis")
  public void test03_ImportNode() {
    info("Test 5: Import a Node");
    info("Create data test");
    String node2 = "node2" + getRandomNumber();
    String filePath = "sysview.xml";
    info("Finished data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    $(byXpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}", "Admin"))).click();
    $(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
    $(ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM).click();
    $(byId("importNode")).parent().click();
    if ($(byId("importNode")).is(Condition.not(Condition.selected))) {
      $(byId("importNode")).parent().click();
    }
    $(ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON).click();
    $(ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON).click();
    info("Add New folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    sleep(Configuration.timeout);
    $(byClassName("uiIconEcmsViewWeb")).waitUntil(Condition.visible,Configuration.timeout).click();
    siteExplorerHome.goToAddNewFolder();

    info("Create Folder node");
    createNewDocument.createNewFolder(node2, CreateNewDocument.folderType.Content);
    info("Select folder");
    siteExplorerHome.selectNode(node2);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(byClassName("uiIconEcmsViewAdmin")).click();
    info("Import a node");
    siteExplorerHome.goToImportNode();
    $(byId("upload")).find(byClassName("file")).uploadFromClasspath("ks-export-forum.xml");
    $(byId("upload")).find(byClassName("progressBarFrame")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON).click();
    $(Button.ELEMENT_OK_BUTTON).click();
    info("Delete all data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    $(byClassName("uiIconEcmsViewWeb")).click();
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    ELEMENT_CONTENT_LIST.find(byLinkText(node2)).contextClick();
    info("Click on Delete link");
    $(ELEMENT_SITEEXPLORER_ACTION_DELETE).click();
    info("Click on Delete button on Confirm popup");
    $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).click();
  }

  @Test
  @Tag("eabis")
  public void test04_Add_Delete_Category() {

    info("Create data test");
    String node1 = "node1" + getRandomNumber();
    String node2 = "node2" + getRandomNumber();
    String nameSelectedCategory = "intranet";
    info("Finished data test");

    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node1, node1);
    createNewDocument.saveAndClose();

    info("Add Category");
    click(ELEMENT_ACTIONBAR_MORE);
    siteExplorerHome.goToAddCategory();
    $(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB).click();
    $(byXpath("//*[@id=\"UITreeTaxonomyList\"]/div[2]/div[2]/div/div/div/div/div/a")).click();
    info("Delete a category");
    siteExplorerHome.deleteCategory(nameSelectedCategory);
    siteExplorerHome.closeAddCategoryPopup();

    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node2, node2);
    createNewDocument.saveAndClose();

    info("Add Category");
    click(ELEMENT_ACTIONBAR_MORE);
    siteExplorerHome.goToAddCategory();
    $(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB).click();
    $(byXpath("//*[@id=\"UITreeTaxonomyList\"]/div[2]/div[2]/div/div/div/div/div/a")).click();
    $(byXpath(ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY.replace("${nameCategory}",
            nameSelectedCategory))).waitUntil(Condition.visible,
            Configuration.timeout);
    siteExplorerHome.closeAddCategoryPopup();

    info("Delete all data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(node1);
    siteExplorerHome.deleteData(node2);

  }

  /**
   * <li>Case ID:116610.</li>
   * <li>Test Case Name: View Node Properties</li> Step Number: 1 Step Name: -
   * Step Description: - View node properties - Create a node - Select this node -
   * Click on Properties on action bar - Add properties - Go to Add new properties
   * Tab - Perform to add new a properties for this node Expected Outcome: -
   * Manage properties form is shown, and all properties of this node is shown
   * here. - New properties is added
   */
  @Test
  @Tag("ECMS-7688")
  public void test05_ViewNodePropertiesThenManagePublication() {
    info("Test 08: View Node Properties");

    info("Create data test");
    String node1 = "node1" + getRandomNumber();
    String node2 = "node2" + getRandomNumber();
    String property = "exo:summary";
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    Date date = new Date();
    String content = "content" + getRandomNumber();

    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    $(byClassName("uiIconEcmsViewWeb")).click();
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node1, node1);
    createNewDocument.saveAndClose();
    info("View Node Properties");
    siteExplorerHome.selectNode(node1);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    sleep(Configuration.timeout);
    $(byClassName("uiIconEcmsViewAdmin")).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ACTIONBAR_MORE).click();
    siteExplorerHome.goToProperties();
    siteExplorerHome.addProperty(property, property);

    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(node2, node2);
    createNewDocument.saveAndClose();
    info("Manage Publication");
    click(ELEMENT_ACTIONBAR_MORE);
    siteExplorerHome.goToManagePublishtation();
    siteExplorerHome.managePublication("Staged", dateFormat.format(date.getTime()), dateFormat.format(date.getTime()));

    info("Search Web Content With Accented Characters");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent("sécurité", content);
    createNewDocument.saveAndClose();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    ELEMENT_SEARCH_BTN.setValue("sécurité").pressEnter();
    sleep(Configuration.timeout);
    $(byClassName("uiSearchResult")).find(byXpath("//*[@id=\"SimpleSearchResult\"]/table/tbody/tr[1]/td[2]"))
            .find(byText("sécurité"))
            .shouldBe(Condition.visible);

    info("Delete all data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(node1);
    siteExplorerHome.deleteData(node2);
    siteExplorerHome.deleteData("sécurité");

  }

}
