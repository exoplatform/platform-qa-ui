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

  @Test
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

  @Test
  public void test01_AddViewUnlockANode() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String name = "name" + getRandomNumber();
    String superTypes = "exo:calendar";
    String prefix = "prefix" + getRandomNumber();
    String url = "www.exo-fqa-test" + getRandomNumber() + ".com";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NAMESPACES);
    info("Namespace registry");
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
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.lockNode(title);
    navigationToolbar.goToContentAdministration();
    info("Add Node and show it");
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NODESTYPES);
    contentAdministration.addNodeType(name, superTypes);
    contentAdministration.searchNodeAndCheckIt(name, superTypes + ", nt:base");
    info("Unlock a Node");
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.LOCKS);
    $(byXpath(ELEMENT_ECM_REPOSITORY_UNLOCK_NODE_LIST.replace("{$name}", title))).click();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);

  }

}
