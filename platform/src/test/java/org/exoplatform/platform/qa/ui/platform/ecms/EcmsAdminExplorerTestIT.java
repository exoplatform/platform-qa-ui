package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

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
 * Created by ilyes on 11/09/17.
 */
@Tag("ecms")
@Tag("sniff")
public class EcmsAdminExplorerTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:116587.</li>
   * <li>Test Case Name: Add Drive.</li>
   * <li>Case ID:116623.</li>
   * <li>Test Case Name: Edit Drive.</li>
   * <li>Case ID:116624.</li>
   * <li>Test Case Name: Delete Drive.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1:Add Drive Input Data: - Go to Content
   * Administration/Explorer/ Drives - Click on Add Drive button - Put value in
   * required fields - Click Save button Expected Outcome: - A drive is created
   * successfully, when go to Site Explorer , you can see new drive
   */

  @Test
  public void test01_Add_Drive() {
    info("Test 01: Add Drive");
    info("Get data test");
    String title = "title" + getRandomNumber();
    String permission = "any";
    ContentAdministration.specificView[] view = { ContentAdministration.specificView.ADMIN };
    info("Finished getting data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DRIVES);
    contentAdministration.addDrives(title, permission, view);
    // delete drive
    contentAdministration.deleteDrives(title);

  }

  @Test
  public void test02_Edit_Drive() {
    info("Test 01: Add Drive");
    info("Get data test");
    String title = "title" + getRandomNumber();
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
    $(byXpath(ELEMENT_ECM_EXPLORER_DRIVES_VIEW_OF_VIEWS_LIST.replace("{$name}", title)
                                                            .replace("{$view}", newV[0]))).waitUntil(Condition.visible,
                                                                                                     Configuration.timeout);
    // delete drive
    contentAdministration.deleteDrives(title);

  }

  @Test
  public void test03Delete_Drive() {
    info("Test 01: Add Drive");
    info("Get data test");
    String title = "title" + getRandomNumber();
    String permission = "any";
    ContentAdministration.specificView[] view = { ContentAdministration.specificView.ADMIN };
    info("Finished getting data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DRIVES);
    contentAdministration.addDrives(title, permission, view);
    // delete drive
    contentAdministration.deleteDrives(title);

  }

  /**
   * <li>Case ID:116627.</li>
   * <li>Test Case Name: Add a View.</li>
   * <li>Case ID:116625.</li>
   * <li>Test Case Name: Edit a View.</li>
   * <li>Case ID:116600.</li>
   * <li>Test Case Name: View a View.</li>
   * <li>Case ID:116626.</li>
   * <li>Test Case Name: Delete a View.</li> Step Number: 1 Step Name: - Step
   * Description: Step 1:Add a View Input Data: - Go to Content Administration/
   * Explorer/ Views - Click Add View button - Put value in required field - Click
   * Add Tab, fill name and tick on action for tabs - Click Save button Expected
   * Outcome: - A new view is created successfully
   */

  @Test
  public void test04_Add__AView() {
    info("Test 02 : Add, edit, show and delete a View");
    String title = "title" + getRandomNumber();
    String tabName = "tabName" + getRandomNumber();
    String oldPermission = "mary";
    String[] tab = { "Add Category:" };

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    info("add a view");
    contentAdministration.addView(title, tabName, tab, oldPermission);
    info("delete a view");
    contentAdministration.deleteView(title);

  }

  @Test
  public void test05Edit_AView() {
    info("Test 02 : Add, edit, show and delete a View");
    String title = "title" + getRandomNumber();
    String tabName = "tabName" + getRandomNumber();
    String oldPermission = "mary";
    String[] tab = { "Add Category:" };
    String permission = "john";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    info("add a view");
    contentAdministration.addView(title, tabName, tab, oldPermission);
    info("edit a view");
    contentAdministration.editViewPermissionUser(title, "Mary", permission);
    $(byXpath(ELEMENT_ECM_EXPLORER_VIEW_PERMISSIONS_LIST.replace("{$name}", title)
                                                        .replace("{$permission}", "John"))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    info("delete a view");
    contentAdministration.deleteView(title);

  }

  @Test
  public void test06DeleteAView() {
    info("Test 02 : Add, edit, show and delete a View");
    String title = "title" + getRandomNumber();
    String tabName = "tabName" + getRandomNumber();
    String oldPermission = "mary";
    String[] tab = { "Add Category:" };

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    info("add a view");
    contentAdministration.addView(title, tabName, tab, oldPermission);
    info("delete a view");
    contentAdministration.deleteView(title);

  }

  @Test
  public void test07View_AView() {
    info("Test 02 : Add, edit, show and delete a View");
    String title = "title" + getRandomNumber();
    String tabName = "tabName" + getRandomNumber();
    String oldPermission = "mary";
    String[] tab = { "Add Category:" };

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    info("add a view");
    contentAdministration.addView(title, tabName, tab, oldPermission);
    info("show a view");
    $(byXpath(ELEMENT_ECM_EXPLORER_VIEW_SHOW_A_VIEW_LIST.replace("{$name}", title))).click();
    $(byXpath(ELEMENT_ECM_EXPLORER_NAME_VIEW_SHOW_VIEW.replace("{$name}", title))).waitUntil(Condition.visible,
                                                                                             Configuration.timeout);
    $(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
    $(byXpath(ELEMENT_ECM_EXPLORER_TAB_ICONS_LIST_SHOW_VIEW.replace("{$tab}",
                                                                    tab[0].subSequence(0, 11)))).waitUntil(Condition.visible,
                                                                                                           Configuration.timeout);
    $(ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE).click();
    info("delete a view");
    contentAdministration.deleteView(title);
  }

  /**
   * <li>Case ID:116598.</li>
   * <li>Test Case Name: Add Tags.</li>
   * <li>Case ID:116621.</li>
   * <li>Test Case Name: Edit Tags.</li>
   * <li>Case ID:116622.</li>
   * <li>Test Case Name: Delete Tags.</li>
   */
  @Test
  public void test08_AddTags() {
    info("Test 03: Add, edit and delete Tags");
    String title = "title" + getRandomNumber();
    String occurences = "1..*";
    String oldHtml = "font-size:12px;";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.TAGS);
    contentAdministration.addTags(title, occurences, oldHtml);
    contentAdministration.deleteTags(title);
  }

  @Test
  public void test09_Edit_Tags() {
    info("Test 03: Add, edit and delete Tags");
    String title = "title" + getRandomNumber();
    String occurences = "1..*";
    String html = "font-size:10px;";
    String oldHtml = "font-size:12px;";

    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.TAGS);
    contentAdministration.addTags(title, occurences, oldHtml);
    contentAdministration.updateTags(title, null, html);
    $(byXpath(ELEMENT_ECM_EXPLORER_TAGS_LIST_CHECK_HTML_CONTENT.replace("{$name}", title)
                                                               .replace("{$html}", html))).waitUntil(Condition.visible,
                                                                                                     Configuration.timeout);
    contentAdministration.deleteTags(title);
  }

  @Test
  public void test10_Delete_Tags() {
    info("Test 03: Add, edit and delete Tags");
    String title = "title" + getRandomNumber();
    String occurences = "1..*";
    String oldHtml = "font-size:12px;";
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.TAGS);
    contentAdministration.addTags(title, occurences, oldHtml);
    contentAdministration.deleteTags(title);
  }
}
