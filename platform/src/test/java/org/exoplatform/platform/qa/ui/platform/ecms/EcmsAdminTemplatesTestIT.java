package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

/**
 * @author eXo
 */

@Tag("sniff")
@Tag("ecms")
public class EcmsAdminTemplatesTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ContentAdministration contentAdministration;

  ManageLogInOut        manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    contentAdministration = new ContentAdministration(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:116589.</li>
   * <li>Test Case Name: Edit Metadata.</li>
   * <li>Case ID:116630.</li>
   * <li>Test Case Name: View Metadata.</li>
   * <li>Case ID:116631.</li>
   * <li>Test Case Name: Delete Metadata.</li>
   * Step Number: 1 Step Name: - Step Description: Step 1: Edit Metadata Input
   * Data: - Go to Content Administration/Template/ Metadata - Click corresponding
   * Edit icon of Metadata you want to edit - Perform to edit - Click Apply
   * Expected Outcome: The Metadata is edited successfully
   */
  @Test
  public void test01_Edit_Metadata() {
    info("Test 1: Edit,view and delete Metadata");

    String title = "title" + getRandomNumber();
    String newName = "newName" + getRandomNumber();
    String superTypes = "exo:metadata";
    String permission = "any";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NODESTYPES);
    contentAdministration.addNodeType(title, superTypes, "true");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.METADATA);
    contentAdministration.editeMetadataNameAndPermission(title, newName, permission);
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_SHOW.replace("{$name}", newName))).click();
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_CHECK_MATADATA_INFORMATION.replace("{$metadata}",
                                                                                title))).waitUntil(Condition.visible,
                                                                                                   Configuration.timeout);
    $(ELEMENT_ECM_TEMPLATES_METADATA_CLOSE_VIEW).click();
    contentAdministration.deleteMetadata(title);
  }

  @Test
  public void test02_View_Metadata() {
    info("Test 1: Edit,view and delete Metadata");

    String title = "title" + getRandomNumber();
    String newName = "newName" + getRandomNumber();
    String superTypes = "exo:metadata";
    String permission = "any";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NODESTYPES);
    contentAdministration.addNodeType(title, superTypes, "true");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.METADATA);
    contentAdministration.editeMetadataNameAndPermission(title, newName, permission);
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_SHOW.replace("{$name}", newName))).click();
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_CHECK_MATADATA_INFORMATION.replace("{$metadata}",
                                                                                title))).waitUntil(Condition.visible,
                                                                                                   Configuration.timeout);
    $(ELEMENT_ECM_TEMPLATES_METADATA_CLOSE_VIEW).click();
    contentAdministration.deleteMetadata(title);
  }

  @Test
  public void test03DeleteMetadata() {
    info("Test 1: Edit,view and delete Metadata");

    String title = "title" + getRandomNumber();
    String newName = "newName" + getRandomNumber();
    String superTypes = "exo:metadata";
    String permission = "any";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NODESTYPES);
    contentAdministration.addNodeType(title, superTypes, "true");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.METADATA);
    contentAdministration.editeMetadataNameAndPermission(title, newName, permission);
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_SHOW.replace("{$name}", newName))).click();
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_CHECK_MATADATA_INFORMATION.replace("{$metadata}",
                                                                                title))).waitUntil(Condition.visible,
                                                                                                   Configuration.timeout);
    $(ELEMENT_ECM_TEMPLATES_METADATA_CLOSE_VIEW).click();
    contentAdministration.deleteMetadata(title);
  }

  /**
   * <li>Case ID:116599.</li>
   * <li>Test Case Name: Add Document Template.</li>
   * <li>Case ID:116632.</li>
   * <li>Test Case Name: Edit Document Template.</li>
   * <li>Case ID:116633.</li>
   * <li>Test Case Name: Delete Document Template.</li> Step Number: 1 Step Name:
   * - Step Description: Step 1: AddTemplate Input Data: Create new template when
   * put valid value in fields - Go to Content Administration/ Templates/
   * Documents - Click Add Template button - Put value in required fields - Click
   * Save button Expected Outcome: - Form to manage template is shown with: + 3
   * tabs are added to categorize templates by their nature : Documents, Actions
   * and Others - Template list is displayed with 4 columns: Icon, Template, Type,
   * Actions - A new template is created successfully Step Number: 1 Step Name: -
   * Step Description: Step 1: AddTemplate Input Data: Create new template when
   * put valid value in fields - Go to Content Administration/ Templates/
   * Documents - Click Add Template button - Put value in required fields - Click
   * Save button Expected Outcome: - Form to manage template is shown with: + 3
   * tabs are added to categorize templates by their nature : Documents, Actions
   * and Others - Template list is displayed with 4 columns: Icon, Template, Type,
   * Actions - A new template is created successfully Step Number: 1 Step Name: -
   * Step Description: Step 1: AddTemplate Input Data: Create new template when
   * put valid value in fields - Go to Content Administration/ Templates/
   * Documents - Click Add Template button - Put value in required fields - Click
   * Save button Expected Outcome: - Form to manage template is shown with: + 3
   * tabs are added to categorize templates by their nature : Documents, Actions
   * and Others - Template list is displayed with 4 columns: Icon, Template, Type,
   * Actions - A new template is created successfully
   */
  @Test
  @BugInPLF("ECMS-7841")
  public void test04_AddDocumentTemplate() {
    info("Test 1: Add, edit and delete Document Template");
    String title = "title" + getRandomNumber();
    String permission = "any";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DOCUMENTS);
    contentAdministration.addDocumentInTemplates(title, permission);
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    contentAdministration.deleteCategories(title);
  }

  @Test
  @BugInPLF("ECMS-7841")
  public void test05_EditTemplate() {
    info("Test 1: Add, edit and delete Document Template");
    String title = "title" + getRandomNumber();
    String permission = "any";
    String newTitle = "newTitle" + getRandomNumber();

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DOCUMENTS);
    contentAdministration.addDocumentInTemplates(title, permission);
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    contentAdministration.editDocumentInTemplates(title, newTitle);
    contentAdministration.deleteCategories(newTitle);
  }

  @Test
  @BugInPLF("ECMS-7841")
  public void test06_DeleteDocumentTemplate() {
    info("Test 1: Add, edit and delete Document Template");
    String title = "title" + getRandomNumber();
    String permission = "any";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DOCUMENTS);
    contentAdministration.addDocumentInTemplates(title, permission);
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    contentAdministration.deleteCategories(title);
  }

  /**
   * <li>Case ID:116634.</li>
   * <li>Test Case Name: Add List Template.</li>
   * <li>Case ID:116635.</li>
   * <li>Test Case Name: Edit List Template.</li>
   * <li>Case ID:116636.</li>
   * <li>Test Case Name: Delete List Template.</li> Step Number: 1 Step Name: -
   * Step Description: Step 1: Add Template Input Data: Create new template when
   * put valid value in fields - Go to Content Administration/ Templates/ List -
   * Click Add Template button - Put value in required fields - Click Save button
   * Expected Outcome: - Manage List template is shown with: + 3 tabs. One for
   * each type of clv template : Content for list templates, Navigation for
   * navigation templates and Paginator for paginator templates. All tree tabs
   * have the same table inside + In Document tab: 3 columns: Name, Template,
   * Actions - A new template is created successfully
   */
  @Test
  public void test07_Add_ListTemplate() {
    info("Test 7: Add, Edit and Delete List Template");
    String tempName = "tempName" + getRandomNumber();
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newName = "newName" + getRandomNumber();
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.LIST);
    contentAdministration.addTemplateInList(name, tempName, content);
    contentAdministration.deleteTemplateList(name);
  }

  @Test
  @Tag("ecmis")
  public void test08Edit_ListTemplate() {
    info("Test 7: Add, Edit and Delete List Template");
    String tempName = "tempName" + getRandomNumber();
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newName = "newName" + getRandomNumber();
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.LIST);
    contentAdministration.addTemplateInList(name, tempName, content);
    contentAdministration.editTemplateNameInList(name, newName);
    contentAdministration.deleteTemplateList(newName);
  }

  @Test
  public void test09_DeleteListTemplate() {
    info("Test 7: Add, Edit and Delete List Template");
    String tempName = "tempName" + getRandomNumber();
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newName = "newName" + getRandomNumber();
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.LIST);
    contentAdministration.addTemplateInList(name, tempName, content);
    contentAdministration.deleteTemplateList(name);
  }
}
