package org.exoplatform.platform.qa.ui.gatein;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * @author eXo
 */
@Tag("gatein")
@Tag("smoke")
public class GateinPortalNavigationEditNavigationTestIT extends Base {
  NavigationToolbar navigationToolbar;

  PortalManageSites portalmanagesites;

  NavigationManagement navigationmanagement;

  PageCreationWizard pagecreationwizard;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    portalmanagesites = new PortalManageSites(this);
    pagecreationwizard = new PageCreationWizard(this);
    navigationToolbar = new NavigationToolbar(this);
    navigationmanagement = new NavigationManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  /**
   * <li>Case ID:123124.</li>
   * <li>Test Case Name:Edit node.</li>
   */
  @Test
  public void test01_EditNode() {
    info("Test 04: Edit Node");
    String portalName = "portalName" + getRandomNumber();
    String nodeName = "nodeName" + getRandomNumber();
    String newNodeName = "newNodeName" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Step 1: Add node for portal Step Description: - Go
     * to Administration/Portal/ Sites/Edit navigation - Select a node and choose
     * Edit node by right click - Change values in fields of the current node,
     * except the Node Name. - Click Save Input Data: Expected Outcome: The node is
     * updated with the change value
     */

    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation();
    info("Add a new node");
    navigationmanagement.addNode(nodeName, "");
    navigationmanagement.saveNode();

    info("Edit a node");
    portalmanagesites.goToEditNavigation();
    navigationmanagement.editThisNode(nodeName);
    navigationmanagement.inputInfoNodeSetting(true, "", newNodeName, true, false);
    navigationmanagement.saveNode();

    info("Verify that the node is changed with new name");
    portalmanagesites.goToEditNavigation();

    $(byText(newNodeName)).should(Condition.exist);
    navigationmanagement.editThisNode(newNodeName);
    info("Verify that label mode is not checked");
    $(byText("Extended label mode:")).shouldNotBe(Condition.selected);

    info("Verify that language box is not shown");
    $(ELEMENT_NODE_SETTING_LANGUAGE_BOX).shouldNot(Condition.exist);
    info("Verify that visible is not checked");
    $(byText("Visible:")).shouldNotBe(Condition.selected);
    info("Verify that publish date and time is not shown");
    $(ELEMENT_NODE_SETTING_PUBLISH_DATE_TIME).shouldNot(Condition.exist);
    navigationmanagement.saveNode();

  }

  /**
   * *
   * <li>Case ID:123122.</li>
   * <li>Test Case Name:Delete a node.</li>
   * <li>Case ID:123123.</li>
   * <li>Test Case Name:Add node for portal.</li>
   * <li>Case ID:123041.</li>
   * <li>Test Case Name:Edit node's page properties.</li>
   */
  @Test
  public void test02_Add_Edit_Delete_NodePage() {
    info("Test 01: Add new node");
    String portalName = "portalName" + getRandomNumber();
    String nodeName = "nodeName" + getRandomNumber();
    String namePage = "namePage" + getRandomNumber();
    String titlePage = "titlePage" + getRandomNumber();

    String newTitlePage = "newTitlePage" + getRandomNumber();
    String groupPath = "Development";
    String memberships = "author";
    /*
     * Step Number: 1 Step Name: Step 1: Add node for portal Step Description: - Go
     * to Administration/Portal/ Sites/Edit navigation - Add new node by click add
     * new node button or right click - Input value for Page Node Setting and Page
     * Selector form - Click Save Input Data: Expected Outcome: Add node
     * successfully
     */
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation();
    info("Add a new node");
    navigationmanagement.addNode(nodeName, "");
    navigationmanagement.inputInfoPageSelector(namePage, titlePage, true, false, false);
    navigationmanagement.saveNode();

    info("Test 02: Edit node Page");
    /*
     * Step Number: 1 Step Name: Step 1: Edit node's page properties Step
     * Description: - Go to Administration/Portal/Sites/Edit navigation - Select a
     * node - Right click and choose Edit node's page - Choose View Page properties
     * on Page Editor - Edit some changes - Click Save button Input Data: Expected
     * Outcome: Page Setting, Permission setting tab are updated successfully with
     * new changes
     */
    portalmanagesites.goToEditNavigation();
    navigationmanagement.editNodePage(nodeName);// go to declaration de automate
    // right click
    pagecreationwizard.viewProperties();
    pagecreationwizard.changeProperties(newTitlePage, groupPath, memberships, true, false);

    info("Verify that the changs are updated");
    pagecreationwizard.viewProperties();
    info("Page settings is updated");
    String titleActual = pagecreationwizard.getOldTitle();
    info("titleActual:" + titleActual);
    info("newtitle:" + newTitlePage);
    info("Permission setting tab is updated");
    click(ELEMENT_VIEW_PROPERTIES_PERMISSION_TAB);

    $(byText("/developers")).waitUntil(Condition.appears, 10000);
    pagecreationwizard.saveChangeProperties();
    pagecreationwizard.saveChangesPageEditor();
    if (!titleActual.equals(newTitlePage))
      assert false : "The title:" + newTitlePage + " is not updated";
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation();
    info("Test 03: Delete node");
    /*
     * Step Number: 1 Step Name: Step 1: Delete Node Step Description: - Go to
     * Administration/Portal/ Sites/Edit navigation - Select a node and choose
     * Delete node by right click - Click OK in the confirmation message to accept
     * your deletion. - Click Save Input Data: Expected Outcome: - The node is
     * removed from the list
     */
    navigationmanagement.deleteNode(nodeName);

  }

}
