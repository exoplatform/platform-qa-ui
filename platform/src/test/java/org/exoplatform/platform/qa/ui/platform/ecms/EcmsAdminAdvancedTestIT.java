package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
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
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

/**
 * @author ilyes
 */

@Tag("sniff")
@Tag("ecms")
public class EcmsAdminAdvancedTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ContentAdministration contentAdministration;

  ManageLogInOut        manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    contentAdministration = new ContentAdministration(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(username, password);

  }

  /**
   * <li>Case ID:116581.</li>
   * <li>Test Case Name: Add Action Type.</li>
   * <li>Case ID:116663.</li>
   * <li>Test Case Name: Edit Action Type.</li>
   * <li>Case ID:116664.</li>
   * <li>Test Case Name: Delete Action Type.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Manage Action Input Data: - Go to Content
   * Administration/Advanced /Action - Click on Add Action Type button - Input
   * required fields - Click Save Expected Outcome: The action is created
   */
  @Test
  public void test01_Add_ActionType() {
    info("Test 01: Add Action Type");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.ACTIONS);
    contentAdministration.addActionType(title, "", "");
    info("Verify that the title is replaced");
    $(byXpath(ELEMENT_ECM_ACTION_LIST.replace("{$name}", title))).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 03: Delete Action Type");
    contentAdministration.deleteAction(title);
  }

  @Test
  public void test02_Edit_ActionType() {
    info("Test 01: Add Action Type");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String Newtitle = "Newtitle" + getRandomNumber();
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.ACTIONS);
    contentAdministration.addActionType(title, "", "");
    info("Verify that the title is replaced");
    $(byXpath(ELEMENT_ECM_ACTION_LIST.replace("{$name}", title))).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 02: Edit Action Type");
    contentAdministration.editActionType(title, Newtitle, "", "");
    info("Test 03: Delete Action Type");
    contentAdministration.deleteAction(Newtitle);
  }

  @Test
  public void test03_Delete_ActionType() {
    info("Test 01: Add Action Type");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.ACTIONS);
    contentAdministration.addActionType(title, "", "");
    info("Verify that the title is replaced");
    $(byXpath(ELEMENT_ECM_ACTION_LIST.replace("{$name}", title))).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 03: Delete Action Type");
    contentAdministration.deleteAction(title);
  }

  /**
   * <li>Case ID:116595.</li>
   * <li>Test Case Name: Add Query.</li>
   * <li>Case ID:116617.</li>
   * <li>Test Case Name: Edit Query.</li>
   * <li>Case ID:116618.</li>
   * <li>Test Case Name: Delete Query.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Add query Input Data: - Go to Content Administration/
   * Advanced/ Queries - Click on Add Query button - Input all required field -
   * Click Save Expected Outcome: The query is added successfully
   */
  @Test
  public void test04_Add_Query() {
    info("Test 04: Add Query");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String permission = "any";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.QUERIES);
    contentAdministration.addQueries(title, "", "", permission);
    info("Verify that the query is added");
    $(byXpath(ELEMENT_ECM_ADVANCED_QUERIES_TYPE_LIST.replace("{$name}", title)
                                                    .replace("{$type}", ""))).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 06: Delete Query");
    contentAdministration.deleteQueries(title);

  }

  @Test
  public void test05_Edit_Query() {
    info("Test 04: Add Query");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String type = "SQL";
    String permission = "any";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.QUERIES);
    contentAdministration.addQueries(title, "", "", permission);
    info("Test 05: Edit Query");
    contentAdministration.editQueries(title, type, "", "");
    info("Verify that the query is edited with new title");
    $(byXpath(ELEMENT_ECM_ADVANCED_QUERIES_TYPE_LIST.replace("{$name}", title).replace("{$type}",
                                                                                       "sql"))).waitUntil(Condition.visible,
                                                                                                          Configuration.timeout);
    info("Test 06: Delete Query");
    contentAdministration.deleteQueries(title);

  }

  @Test
  public void test06_Delete_Query() {
    info("Test 04: Add Query");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String permission = "any";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.QUERIES);
    contentAdministration.addQueries(title, "", "", permission);
    info("Test 06: Delete Query");
    contentAdministration.deleteQueries(title);
  }

  /**
   * <li>Case ID:116597.</li>
   * <li>Test Case Name: Add Script.</li>
   * <li>Case ID:116619.</li>
   * <li>Test Case Name: Edit Script.</li>
   * <li>Case ID:116620.</li>
   * <li>Test Case Name: Delete Script.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Add Script Input Data: - Go to Content
   * Administration/Advanced/ Scripts - Click Add button and input information for
   * new scrip - Click Save Expected Outcome: The script is Added successfully
   */
  @Test
  public void test10_Add_Script() {
    info("Test 10: Add Script");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String script = "script" + "script" + getRandomNumber() + ".groovy";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.SCRIPTS);
    contentAdministration.addScripts(title, content, script);
    info("Verify that the script is added in the list");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", title))).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 12: Delete Script");
    contentAdministration.deleteScripts(title);
  }

  @Test
  public void test11_Edit_Script() {
    info("Test 10: Add Script");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String Newtitle = "Newtitle" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String script = "script" + "script" + getRandomNumber() + ".groovy";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.SCRIPTS);
    contentAdministration.addScripts(title, content, script);
    info("Verify that the script is added in the list");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", title))).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 11: Edit Script");
    contentAdministration.EditScripts(title, Newtitle, "", "");
    info("Test 12: Delete Script");
    contentAdministration.deleteScripts(Newtitle);
  }

  @Test
  public void test12Delete_Script() {
    info("Test 10: Add Script");
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String script = "script" + "script" + getRandomNumber() + ".groovy";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.SCRIPTS);
    contentAdministration.addScripts(title, content, script);
    info("Verify that the script is added in the list");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", title))).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 12: Delete Script");
    contentAdministration.deleteScripts(title);
  }

  /**
   * <li>Case ID:116615.</li>
   * <li>Test Case Name: Add Categories.</li>
   * <li>Case ID:116616.</li>
   * <li>Test Case Name: Edit Categories.</li>
   * <li>Case ID:116583.</li>
   * <li>Test Case Name: Delete Categories.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1: Add categories Input Data: - Go to Content
   * Administration/ Advanced/ Categories - Click on [Add Category] button - Input
   * required fields - Click [Save] - Add/copy/cut/paste/delete category in
   * category tree Expected Outcome: The Category is created successfully
   */
  @BugInPLF("ECMS-7819")
  public void test07_Add_Categories() {
    info("Test 07: Add Categories");
    info("Get the data test");
    String name = "name" + getRandomNumber();
    String lifeCycle = "Content Addition";
    String nameAction = "nameAction" + getRandomNumber();
    String targetPath = "root";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.CATEGORIES);
    contentAdministration.addCategories(name, nameAction, lifeCycle, targetPath);
    info("Test 09: Delete Categories");
    contentAdministration.deleteCategories(name);
  }
  @BugInPLF("ECMS-7819")
  public void test08_Edit_Categories() {
    info("Test 07: Add Categories");
    info("Get the data test");
    String name = "" + getRandomNumber();
    String lifeCycle = "Content Addition";
    String nameAction = "" + getRandomNumber();
    String targetPath = "root";
    String workspace = "knowledge";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.CATEGORIES);
    contentAdministration.addCategories(name, nameAction, lifeCycle, targetPath);
    info("Test 08: Edit Categories");
    contentAdministration.editCategories(name, "", "", "", workspace, targetPath);
    info("Verify that the category is edited with new changes");
    $(byXpath(ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_LIST.replace("{$name}",
                                                                     name)
                                                            .replace("{$workspace}", workspace))).waitUntil(Condition.visible,
                                                                                                            Configuration.timeout);
    info("Test 09: Delete Categories");
    contentAdministration.deleteCategories(name);
  }
  @BugInPLF("ECMS-7819")
  public void test09_Delete_Categories() {
    info("Test 07: Add Categories");
    info("Get the data test");
    String name = "name" + getRandomNumber();
    String lifeCycle = "Content Addition";
    String nameAction = "nameAction" + getRandomNumber();
    String targetPath = "root";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.CATEGORIES);
    contentAdministration.addCategories(name, nameAction, lifeCycle, targetPath);
    info("Test 09: Delete Categories");
    contentAdministration.deleteCategories(name);
  }
}
