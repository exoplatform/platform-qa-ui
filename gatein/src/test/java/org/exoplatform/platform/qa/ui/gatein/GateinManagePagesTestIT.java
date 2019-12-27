package org.exoplatform.platform.qa.ui.gatein;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
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
    String num = getRandomNumber();
    String pageName = "pageName" + num;
    String title = "title" + num;
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

  /**
   * <li>Case ID:123101.</li>
   * <li>Test Case Name: Edit page of portal.</li>
   */

  @Test
  public void test10_EditPageOfPortal() {
    info("Test 10: Edit page of portal");
    String num = getRandomNumber();
    String pageName = "pageName" + num;
    String title = "title" + num;
    /*
     * int index = appLayData.getRandomIndexByType(1); String idName =
     * appLayData.newId.get(index); String name = appLayData.newTitle.get(index);
     */
    /*
     * Step Number: 1 Step Name: Step 1: Edit page of portal Step Description: - Go
     * to Administration/Portal/Pages - Select a page of portal and click [Edit
     * page] icon - Change something + Edit portlet, + View page properties + Drag &
     * drop container and application + Switch view mode - Click Save Input Data:
     * Expected Outcome: - The page is updated with the change value
     */
    navigationToolbar.goToPotalPages();
    portalmanagepages.addPage(pageName, title, "");
    portalmanagepages.editPage(title, "");
    pagecreationwizard.addApp("",
                              "Application Registry",
                              $(byId("Administration/portlet_ApplicationRegistryPortlet")),
                              $(byClassName("VIEW-PAGE")));
    info("Verify that all changes of page is saved");
    portalmanagepages.editPage(title, "");
    $(byText("Application Registry")).should(Condition.exist);
    pagecreationwizard.saveChangesPageEditor();
    portalmanagepages.deletePage(title, "");
  }

  /**
   * <li>Case ID:123102.</li>
   * <li>Test Case Name: Delete page of portal.</li>
   */
  @Test
  public void test03_DeletePageOfPortal() {
    info("Test 03: Delete page of portal");
    String num = getRandomNumber();
    String pageName = "pageName" + num;
    String title = "title" + num;
    /*
     * Step Number: 1 Step Name: Step 1: Delete page of portal Step Description: -
     * Go to Group/Administration/Page Management - Select a page of portal and
     * click [Delete page] Input Data: Expected Outcome: - The page is removed from
     * the list
     */
    navigationToolbar.goToPotalPages();
    portalmanagepages.addPage(pageName, title, "");
    portalmanagepages.deletePage(title, "");

  }

}
