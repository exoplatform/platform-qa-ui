package org.exoplatform.platform.qa.ui.ecms.smoke;

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

@Tag("ecms")
@Tag("smoke")
public class EcmsSECreateTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar     navigationToolbar;

  SiteExplorerHome      siteExplorerHome;

  CreateNewDocument     createNewDoc;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDoc = new CreateNewDocument(this);
  }

  /**
   * <li>Case ID:116573.</li>
   * <li>Test Case Name: Create Web Content document.</li>
   */
  @Test
  public void test03_CreateWebContentDocument() {
    info("Test 3: Create Web Content document");
    info("Create data test");
    String name = "name CreateWebContentDocument" ;
    String content = "content CreateWebContentDocument" ;
    String content2 = "content2 CreateWebContentDocument" ;
    info("Finished creating data test");
    /*
     * Step Number: 1 Step Name: Step 1: Edit Web Content document Step Description:
     * - Click Edit on action bar, perform to edit it - Click Save & Close Input
     * Data: Expected Outcome: The Web Content document is edited successfully
     */
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(name, content);
    createNewDoc.saveAndClose();

  }
}
