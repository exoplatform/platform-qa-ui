package org.exoplatform.platform.qa.ui.gatein;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.MyDashBoard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManagePages;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

/**
 * @author eXo
 */
@Tag("gatein")
@Tag("smoke")
public class GateinManagePagesTestIT extends Base {

  NavigationToolbar  navigationToolbar;

  HomePagePlatform   homePagePlatform;

  PortalManagePages  portalmanagepages;

  PageCreationWizard pagecreationwizard;

  MyDashBoard        myDashoard;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    navigationToolbar = new NavigationToolbar(this);
    portalmanagepages = new PortalManagePages(this);
    pagecreationwizard = new PageCreationWizard(this);
    myDashoard = new MyDashBoard(this);
  }

  /**
   * <li>Case ID:123032.</li>
   * <li>Test Case Name: Add page of portal using Page Management.</li>
   */
  @Test
  public void test02_AddPageOfPortalUsingPageManagement() {
    info("Test 02:Add page of portal using Page Management");

    String pageName = "AddPageOfPortal" ;
    String title = "title AddPageOfPortalUsingPageManagement" ;
    /*
     * Step Number: 1 Step Name: Step 1: Add page of portal Step Description: - Go
     * to Administration/Portal/Pages - Click Add new page - Choose Owner type is
     * portal - Add some fields required - Click Save Input Data: Expected Outcome:
     * - Add page successfully
     */
    navigationToolbar.goToPotalPages();
    portalmanagepages.addPage(pageName, title, "");
    info("Verify that the page is added successfully");
    portalmanagepages.searchPage(title, "", "", true);
  }
}
