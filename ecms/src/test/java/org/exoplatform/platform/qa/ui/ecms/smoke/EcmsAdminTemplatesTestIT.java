package org.exoplatform.platform.qa.ui.ecms.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
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
 * @author eXo
 */
@Tag("ecms")
@Tag("smoke")
public class EcmsAdminTemplatesTestIT extends Base {
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
   * <li>Case ID:116599.</li>
   * <li>Test Case Name: Add Document Template.</li>
   * <li>Case ID:116632.</li>
   * <li>Test Case Name: Edit Document Template.</li>
   * <li>Case ID:116633.</li>
   * <li>Test Case Name: Delete Document Template.</li>
   */
  @Test
  public void test04_AddDocumentTemplate() {
    info("Test 1: Add, edit and delete Document Template");
    String title = "title" + getRandomNumber();
    String permission = "any";
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
  }

}
