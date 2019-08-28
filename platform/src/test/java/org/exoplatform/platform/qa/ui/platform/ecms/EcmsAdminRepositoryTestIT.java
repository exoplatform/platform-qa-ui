package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

/**
 * @author eXo
 */
@Tag("sniff")
@Tag("ecms")
public class EcmsAdminRepositoryTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  SiteExplorerHome      siteExplorerHome;

  CreateNewDocument     createNewDocument;

  ContentAdministration contentAdministration;

  ManageLogInOut        manageLogInOut;

  HomePagePlatform homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    contentAdministration = new ContentAdministration(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform=new HomePagePlatform(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");

  }

  /**
   * <li>Case ID:116588.</li>
   * <li>Test Case Name: Unlock a Node.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Unlock a node Input Data: - Go to Site explorer :
   * perform to lock a node - Go to Administration/Repository/ Locks - Go to
   * Locked node tab - Click corresponding Unlock icon of locked node Expected
   * Outcome: - Node is unlocked
   */
  @Test
  public void test01_UnlockANode() {
    info("Test 1: Unlock a Node");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.lockNode(title);
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.LOCKS);
    $(byXpath(ELEMENT_ECM_REPOSITORY_UNLOCK_NODE_LIST.replace("{$name}", title))).click();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116590.</li>
   * <li>Test Case Name: Add Node types.</li>
   * <li>Case ID:116628.</li>
   * <li>Test Case Name: View Node types.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Add Node types Input Data: Add new node type - Go to
   * Content Administration/Repository/Node Types - Click on Add button - Input
   * Node Type Name - Select super type - Click Save button Expected Outcome: New
   * node type is created
   */
  @Test
  public void test02_AddNodeTypes() {
    info("Test 2: Add Node and show it");

    String name = "name" + getRandomNumber();
    String superTypes = "exo:calendar";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NODESTYPES);
    contentAdministration.addNodeType(name, superTypes);
    contentAdministration.searchNodeAndCheckIt(name, superTypes + ", nt:base");

  }

  @Test
  public void test03_ViewNode() {
    info("Test 2: Add Node and show it");

    String name = "name" + getRandomNumber();
    String superTypes = "exo:calendar";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NODESTYPES);
    contentAdministration.addNodeType(name, superTypes);
    contentAdministration.searchNodeAndCheckIt(name, superTypes + ", nt:base");
  }

  /**
   * <li>Case ID:116601.</li>
   * <li>Test Case Name: Namespace registry.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Namespace registry Input Data: - Go to Content
   * Administration/Repository/Namespace - Click on Register button - Put value in
   * required fields - Click Save button Expected Outcome: New namespace is
   * registered successfully.
   */ // *[@id="UINamespaceList"]/div[1]/ul
  @Test
  public void test04_NamespaceRegistry() {
    info("Test 3: Namespace registry");
    String prefix = "prefix" + getRandomNumber();
    String url = "www.exo-fqa-test" + getRandomNumber() + ".com";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NAMESPACES);
    contentAdministration.registerNamespace(prefix, url);
    $(byXpath(ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX.replace("{$url}", "http://www.exoplatform.com/plf/acme/4.0/").replace("{$prefix}", "acme"))).waitUntil(Condition.visible,Configuration.timeout);
    for (int i = 1; i < 9; i++) {
      homePagePlatform.refreshUntil($(byXpath(ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX.replace("{$url}", "Namespace URI").replace("{$prefix}", "Prefix"))).waitUntil(Condition.visible,Configuration.timeout),Condition.visible,1000);
      if ($(byXpath(ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX.replace("{$url}", url)
                                                                               .replace("{$prefix}", prefix)))
                                                                                                              .is(Condition.not(Condition.visible))) {
        $(ELEMENT_ICON_NEXT_ARROW).click();
      }
      if ($(byXpath(ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX.replace("{$url}", url)
                                                                               .replace("{$prefix}", prefix)))
                                                                                                              .is(Condition.visible)) {
        break;
      }
    }
    $(byXpath(ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX.replace("{$url}", url)
                                                                         .replace("{$prefix}", prefix)))
                                                                                                        .should(Condition.visible);
  }

  /**
   * <li>Case ID:116629.</li>
   * <li>Test Case Name: Manage lock.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Manage lock Input Data: - Go to
   * Administration/Repository/ Locks - Go to Manage lock tab - Perform to add
   * some group/ users which have permission to unlock a locked node - Can Delete
   * permission of group/ user by click Delete icon Expected Outcome: - Group is
   * added permission. All users In the group will be able unlock a locked node -
   * Group is removed permission
   */
  @Test
  @Tag("ecmis")
  public void test05_ManageLock() {
    info("Test 5: Manage lock");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String group = "*:/developers";

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.lockNode(title);
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.LOCKS);
    $(ELEMENT_ECM_REPOSITORY_MANAGE_LOCK).click();
    $(ELEMENT_ECM_REPOSITORY_LOCKS_DEVELOPMENT_GROUP).click();
    $(ELEMENT_ECM_REPOSITORY_LOCKS_ALL_GROUP).click();
    $(byXpath(ELEMENT_ECM_REPOSITORY_CHECK_LOCK_PERMISSION.replace("{$group}", group))).waitUntil(Condition.visible,
                                                                                                  Configuration.timeout);
    $(byXpath(ELEMENT_ECM_REPOSITORY_DELETE_LOCK_PERMISSION.replace("{$group}", group))).click();
    $(byXpath(ELEMENT_ECM_REPOSITORY_CHECK_LOCK_PERMISSION.replace("{$group}", group))).shouldNot(Condition.visible);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
  }
}
