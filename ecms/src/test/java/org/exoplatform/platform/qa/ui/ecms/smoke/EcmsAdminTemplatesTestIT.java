package org.exoplatform.platform.qa.ui.ecms.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * @author eXo
 */
@Tag("ecms")
@Tag("smoke")
public class EcmsAdminTemplatesTestIT extends Base {
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
   * <li>Case ID:116632.</li>
   * <li>Test Case Name: Edit Document Template.</li>
   */
  @Test
  @Tag("ECMS-7841")
  public void test01_AddEdit_DocumentInTemplate() {
    info("Test 1: Add, edit and delete Document Template");
    String title = "title" + getRandomNumber();
    String permission = "any";
    String newTitle = "newTitle" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: AddTemplate Input Data:
     * Create new template when put valid value in fields - Go to Content
     * Administration/ Templates/ Documents - Click Add Template button - Put value
     * in required fields - Click Save button Expected Outcome: - Form to manage
     * template is shown with: + 3 tabs are added to categorize templates by their
     * nature : Documents, Actions and Others - Template list is displayed with 4
     * columns: Icon, Template, Type, Actions - A new template is created
     * successfully
     */
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DOCUMENTS);
    contentAdministration.addDocumentInTemplates(title, permission);
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    contentAdministration.editDocumentInTemplates(title, newTitle);
    contentAdministration.deleteCategories(newTitle);
  }

}
