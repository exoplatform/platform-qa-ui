package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ApplicationRegistry {
  private final TestBase       testBase;

  public NavigationToolbar     navTool;

  public PageEditor            pagEditor;

  public HomePagePlatform      hp;

  public ManageAlert           alert;

  public PortalManagePages     pagMag;

  private ElementEventTestBase evt;

  public ApplicationRegistry(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.navTool = new NavigationToolbar(testBase);
    this.pagEditor = new PageEditor(testBase);
    this.hp = new HomePagePlatform(testBase);
    this.alert = new ManageAlert(testBase);
    this.pagMag = new PortalManagePages(testBase);
  }

  /**
   * Show import application
   *
   * @param isShow Boolean
   */
  public void showImportApplication(Boolean isShow) {
    info("Show import application");
    if (isShow != null) {
      if (isShow) {
        evt.check(ELEMENT_SHOW_IMPORT_APPLICATION, 2);
      } else {
        evt.uncheck(ELEMENT_SHOW_IMPORT_APPLICATION, 2);
      }
    }
  }

  /**
   * Show import application button
   */
  public void displayImportApplicaions() {
    info("Show all import application");
    navTool.goToApplication();
    navTool.goToEditLayout();
    pagEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
    showImportApplication(true);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
    pagEditor.finishEditLayout();
    info("Verify that import all application is shown");
    evt.waitForAndGetElement(ELEMENT_IMPORT_ALL_APPLICATION, 3000, 0);
  }

  /**
   * Import all applications
   */
  public void importAllApplications() {
    info("click on the import Applications button");
    evt.click(ELEMENT_IMPORT_ALL_APPLICATION);
    alert.acceptAlert();

    info("All applications are imported");
  }

  /**
   * Delete a category
   *
   * @param nameCategory String
   */
  public void deleteCategory(String nameCategory) {
    info("Click on Delete button");
    evt.click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN.replace("${category}", nameCategory));
    alert.acceptAlert();
    info("Verify that the category is deleted");
    evt.waitForElementNotPresent(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN.replace("${category}", nameCategory),
                                 2000,
                                 0);
  }

}
