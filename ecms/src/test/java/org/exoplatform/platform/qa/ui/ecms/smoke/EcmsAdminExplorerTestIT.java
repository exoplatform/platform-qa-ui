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
   */
  @Test
  public void test01_AddDrive() {
    info("Test 01: Add Drive");
    info("Get data test");
    String title = "title AddDrive" ;
    String permission = "any";
    ContentAdministration.specificView[] view = { ContentAdministration.specificView.ADMIN };
    ContentAdministration.specificView[] newView = { ContentAdministration.specificView.WEB };
    String[] newV = { "Web" };
    info("Finished getting data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DRIVES);
    contentAdministration.addDrives(title, permission, view);
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);

    /*
     * Step Number: 1 Step Name: - Step Description: Step 1:Add Drive Input Data: -
     * Go to Content Administration/Explorer/ Drives - Click on Add Drive button -
     * Put value in required fields - Click Save button Expected Outcome: - A drive
     * is created successfully, when go to Site Explorer , you can see new drive
     */

  }

}
