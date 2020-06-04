package org.exoplatform.platform.qa.ui.ecms.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * @author rosso
 */
@Tag("ecms")
@Tag("smoke")
public class EcmsAdminExplorerTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar navigationToolbar;

  ManageLogInOut manageLogInOut;

  TestBase testBase;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
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

  @Test
  public void test01_Add_Edit_Delete_View_AView() {
    info("Add, edit, show and delete a View");
    String title = "aatitle" + getRandomNumber();
    String tabName = "tabName" + getRandomNumber();
    String oldPermission = "demo";
    String[] tab = {"Add Category:"};
    String permission = "mary";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    sleep(2000);
    info("add a view");
    contentAdministration.addView(title, tabName, tab, oldPermission);
    info("show a view");
    $(byClassName("uiIconView")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byText(title)).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byText(tabName)).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    info("edit a view");
    contentAdministration.editViewPermissionUser(title, "Demo", permission);
    $(byText("Mary")).should(Condition.exist);
    info("delete a view");
    contentAdministration.deleteView(title);

    /*
     * Step Number: 1 Step Name: - Step Description: Step 1:Add a View Input Data: -
     * Go to Content Administration/ Explorer/ Views - Click Add View button - Put
     * value in required field - Click Add Tab, fill name and tick on action for
     * tabs - Click Save button Expected Outcome: - A new view is created
     * successfully
     */

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
  public void test02_AddEditDelete_Drive() {
    info("Test 01: Add Drive");
    info("Get data test");
    String title = "Atitle" + getRandomNumber();
    String permission = "any";
    ContentAdministration.specificView[] view = {ContentAdministration.specificView.ADMIN};
    ContentAdministration.specificView[] newView = {ContentAdministration.specificView.WEB};
    info("Finished getting data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DRIVES);
    contentAdministration.addDrives(title, permission, view);
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    $(byText("Admin")).waitUntil(Condition.appears, 10000);
    contentAdministration.editDrives(title, newView);
    $(byText(title)).waitUntil(Condition.appears, 10000);
    $(byText("Web")).waitUntil(Condition.appears, 10000);

    // delete drive
    contentAdministration.deleteDrives(title);
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1:Add Drive Input Data: -
     * Go to Content Administration/Explorer/ Drives - Click on Add Drive button -
     * Put value in required fields - Click Save button Expected Outcome: - A drive
     * is created successfully, when go to Site Explorer , you can see new drive
     */
  }

}
