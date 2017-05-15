package org.exoplatform.platform.qa.ui.ecms.smoke;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

/**
 * @author rosso
 */
@Tag("ecms")
@Tag("smoke")
public class EcmsAdminExplorerTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar     navigationToolbar;

  TestBase              testBase;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);

  }

  /**
   * <li>Case ID:116587.</li>
   * <li>Test Case Name: Add Drive.</li>
   * <li>Case ID:116623.</li>
   * <li>Test Case Name: Edit Drive.</li>
   * <li>Case ID:116624.</li>
   * <li>Test Case Name: Delete Drive.</li>
   */
  @Test
  public void test01_02_03_Add_Edit_Delete_Drive() {
    info("Test 01: Add Drive");
    info("Get data test");
    String title = "Atitle" + getRandomNumber();
    String permission = "any";
    ContentAdministration.specificView[] view = { ContentAdministration.specificView.ADMIN };
    ContentAdministration.specificView[] newView = { ContentAdministration.specificView.WEB };
    String[] newV = { "Web" };
    info("Finished getting data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DRIVES);
    contentAdministration.addDrives(title, permission, view);
    contentAdministration.editDrives(title, newView);
    $(byText(title)).waitUntil(Condition.appears, 10000);
    // delete drive
    contentAdministration.deleteDrives(title);
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1:Add Drive Input
     * Data: - Go to Content Administration/Explorer/ Drives - Click on Add
     * Drive button - Put value in required fields - Click Save button Expected
     * Outcome: - A drive is created successfully, when go to Site Explorer ,
     * you can see new drive
     */

  }

  /**
   * <li>Case ID:116627.</li>
   * <li>Test Case Name: Add a View.</li>
   * <li>Case ID:116625.</li>
   * <li>Test Case Name: Edit a View.</li>
   * <li>Case ID:116600.</li>
   * <li>Test Case Name: View a View.</li>
   * <li>Case ID:116626.</li>
   * <li>Test Case Name: Delete a View.</li>
   */

  public void test04_05_06_07_Add_Edit_Delete_View_AView() {
    info("Test 02 : Add, edit, show and delete a View");
    String title = "aatitle" + getRandomNumber();
    String tabName = "tabName" + getRandomNumber();
    String oldPermission = "demo";
    String[] tab = { "Add Category:" };
    String permission = "mary";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    refresh();
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    info("add a view");
    contentAdministration.addView(title, tabName, tab, oldPermission);
    info("show a view");
    $(byClassName("uiIconView")).click();
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
    // waitForAndGetElement(By.xpath(ELEMENT_ECM_EXPLORER_TAB_ICONS_LIST_SHOW_VIEW.replace("{$tab}",tab[0])));
    $(byText(tabName)).waitUntil(Condition.appears, Configuration.timeout);
    click(ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE);
    info("edit a view");
    contentAdministration.editViewPermissionUser(title, oldPermission, permission);
    $(byText("Mary")).should(Condition.exist);
    info("delete a view");
    contentAdministration.deleteView(title);

    /*
     * Step Number: 1 Step Name: - Step Description: Step 1:Add a View Input
     * Data: - Go to Content Administration/ Explorer/ Views - Click Add View
     * button - Put value in required field - Click Add Tab, fill name and tick
     * on action for tabs - Click Save button Expected Outcome: - A new view is
     * created successfully
     */

  }

}
