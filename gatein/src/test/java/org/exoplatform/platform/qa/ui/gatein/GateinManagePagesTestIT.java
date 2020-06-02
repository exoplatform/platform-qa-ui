package org.exoplatform.platform.qa.ui.gatein;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.MyDashBoard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManagePages;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * @author eXo
 */
@Tag("gatein")
@Tag("smoke")
public class GateinManagePagesTestIT extends Base {

  NavigationToolbar navigationToolbar;

  HomePagePlatform homePagePlatform;

  PortalManagePages portalmanagepages;

  PageCreationWizard pagecreationwizard;

  MyDashBoard myDashoard;

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
   * <li>Case ID:123101.</li>
   * <li>Test Case Name: Edit page of portal.</li>
   */

  @Test
  public void test01_AddEditDelete_PageOfPortal() {
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
            $(byId("Administration/ApplicationRegistryPortlet")),
            $(byClassName("VIEW-PAGE")));
    info("Verify that all changes of page is saved");
    portalmanagepages.editPage(title, "");
    $(byText("Application Registry")).should(Condition.exist);
    pagecreationwizard.saveChangesPageEditor();
    portalmanagepages.deletePage(title, "");

  }


}
