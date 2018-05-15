package org.exoplatform.platform.qa.ui.platform.gatein;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("gatein")
@Tag("sniff")

public class GateinPortalEditNavigationTestIT extends Base {
  NavigationToolbar    navigationToolbar;

  PortalManageSites    portalmanagesites;

  NavigationManagement navigationmanagement;

  PageCreationWizard   pagecreationwizard;

  ManageLogInOut       manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    portalmanagesites = new PortalManageSites(this);
    pagecreationwizard = new PageCreationWizard(this);
    navigationToolbar = new NavigationToolbar(this);
    navigationmanagement = new NavigationManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:123134.</li>
   * <li>Test Case Name:Copy and Paste node.</li> Step Number: 1 Step Name: Step
   * 1: Copy and Paste node Step Description: - Go to
   * Administration/Portal/Sites/Edit navigation - Select a node - Right click on
   * node and choose Copy from the drop-down menu. - Right-click the position you
   * want to paste this node and select Paste Node. Input Data: Expected Outcome:
   * - Node is copied to new place
   */
  @Test
  public void test14_CopyAndPasteNode() {
    info("Test 14: Copy and Paste node");
    String portalName = "intranet";
    String nodeName1 = "nodeName1" + getRandomNumber();
    String nodeName2 = "nodeName1" + getRandomNumber();

    info("Add a new node 1");
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName1, "");
    navigationmanagement.saveNode();

    info("Add a new node 2");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName2, "");
    navigationmanagement.saveNode();

    info("Copy and paste a node");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.copyNode(nodeName1);
    navigationmanagement.pasteNode(nodeName2);
    info("Verify that node 2 has only one children is node1");
    $(byXpath(ELEMENT_NAVIGATION_PARENT_CHILD_NODE.replace("${parent}", nodeName2).replace("${child}", nodeName1)));
    $(ELEMENT_NAVIGATION_NUMBER_CHILD_NODES.replace("${parent}", nodeName2).replace("${numberChild}", "1"));
    navigationmanagement.closeNavigationManagementPopup();

    info("Delete a node1 and node2");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.deleteNode(nodeName1);
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.deleteNode(nodeName2);
  }

  /**
   * <li>Case ID:123135.</li>
   * <li>Test Case Name:Cut and Paste node.</li> Step Number: 1 Step Name: Step 1:
   * Cut and Paste node Step Description: - Go to Administration/Portal/Sites/Edit
   * navigation - Select a node - Right click on node and choose Cut from the
   * drop-down menu. - Right-click the position you want to paste this node and
   * select Paste Node. Input Data: Expected Outcome: - Node is cut to new place
   */
  @Test
  public void test15_CutAndPasteNode() {

    info("Test 15: Cut and Paste node");
    String portalName = "Intranet";
    String nodeName1 = "nodeName1" + getRandomNumber();
    String nodeName2 = "nodeName2" + getRandomNumber();

    info("Add a new node 1");
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName1, "");
    navigationmanagement.saveNode();

    info("Add a new node 2");
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName2, "");
    navigationmanagement.saveNode();

    info("Cut and paste a node");
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.cutNode(nodeName1);
    navigationmanagement.pasteNode(nodeName2);

    info("Verify that node 2 has only one children is node1");
    $(byXpath(ELEMENT_NAVIGATION_PARENT_CHILD_NODE.replace("${parent}", nodeName2)
                                                  .replace("${child}", nodeName1))).waitUntil(Condition.visible,
                                                                                              Configuration.timeout);
    $(byXpath(ELEMENT_NAVIGATION_NUMBER_CHILD_NODES.replace("${parent}", nodeName2)
                                                   .replace("${numberChild}", "1"))).waitUntil(Condition.visible,
                                                                                               Configuration.timeout);
    navigationmanagement.closeNavigationManagementPopup();

    info("Verify that node 1 is only one avaiable");
    portalmanagesites.goToEditNavigation(portalName);
    $(byXpath(ELEMENT_NAVIGATION_PARENT_NODE.replace("${parent}", nodeName2))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    $(byXpath(ELEMENT_NAVIGATION_PARENT_NODE.replace("${parent}", nodeName1))).waitUntil(Condition.not(Condition.visible),
                                                                                         Configuration.timeout);
    navigationmanagement.closeNavigationManagementPopup();

    info("Delete a node1 and node2");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.deleteNode(nodeName2);
  }

  /**
   * <li>Case ID:123136.</li>
   * <li>Test Case Name:Clone and Paste Node.</li> Step Number: 1 Step Name: Step
   * 1: Clone and Paste node Step Description: - Go to
   * Administration/Portal/Sites/Edit navigation - Select a node - Right click on
   * node and choose Clone Node from the drop-down menu. - Right-click the
   * position that you want to paste this node and select Paste Node Input Data:
   * Expected Outcome: Node is clone successfully and have properties is the same
   * with node is copied
   */
  @Test
  public void test16_CloneAndPasteNode() {
    info("Test 16: Clone and Paste Node");
    String portalName = "Intranet";
    String nodeName1 = "nodeName1" + getRandomNumber();
    String nodeName2 = "nodeName2" + getRandomNumber();

    info("Add a new node 1");
    navigationToolbar.goToPotalSites();
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName1, "");
    navigationmanagement.saveNode();

    info("Add a new node 2");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName2, "");
    navigationmanagement.saveNode();

    info("Clone and paste a node");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.cloneNode(nodeName1);
    navigationmanagement.pasteNode(nodeName2);

    info("Verify that node 2 has only one children is node1");
    $(byXpath(ELEMENT_NAVIGATION_PARENT_CHILD_NODE.replace("${parent}", nodeName2)
                                                  .replace("${child}", nodeName1))).waitUntil(Condition.visible,
                                                                                              Configuration.timeout);
    $(byXpath(ELEMENT_NAVIGATION_NUMBER_CHILD_NODES.replace("${parent}", nodeName2)
                                                   .replace("${numberChild}", "1"))).waitUntil(Condition.visible,
                                                                                               Configuration.timeout);
    navigationmanagement.closeNavigationManagementPopup();

    info("Verify that node 1 is more one avaiable");
    portalmanagesites.goToEditNavigation(portalName);
    $(byXpath(ELEMENT_NAVIGATION_PARENT_NODE.replace("${parent}", nodeName1))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    navigationmanagement.closeNavigationManagementPopup();

    info("Delete a node1 and node2");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.deleteNode(nodeName1);
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.deleteNode(nodeName2);

  }

  /**
   * <li>Case ID:123052.</li>
   * <li>Test Case Name: Change node order.</li> Step Number: 1 Step Name: Step 1:
   * Change node order Step Description: - Go to Group Sites/Edit navigation -
   * Select a node - Select Move Up or Move Down from the drop-down menu - Click
   * Save to accept your changes. Input Data: Expected Outcome: - Position of node
   * is changed successfully
   */
  @Test
  public void test17_ChangeNodeOrder() {
    String portalName = "Intranet";
    String nodeName1 = "nodeName1" + getRandomNumber();
    String nodeName2 = "nodeName2" + getRandomNumber();

    info("Go to Group Sites/Edit navigation");
    navigationToolbar.goToPotalSites();
    info("Add a new node 1");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName1, "");
    navigationmanagement.saveNode();

    info("Add a new node 2");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.addNode(nodeName2, "");
    navigationmanagement.saveNode();

    info("move up node2 to node 1");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.moveUpNode(nodeName2);

    info("Verify that node 2 is moved up node1");
    $(byXpath(ELEMENT_NAVIGATION_PREVIOUS_NODE.replace("${currentNode}", nodeName2)
                                              .replace("${previousNode}", nodeName1))).waitUntil(Condition.not(Condition.visible),
                                                                                                 Configuration.timeout);
    $(byXpath(ELEMENT_NAVIGATION_NEXT_NODE.replace("${currentNode}", nodeName2)
                                          .replace("${nextNode}", nodeName1))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);

    info("move down node 2 to node 1");
    navigationmanagement.moveDownNode(nodeName2);
    info("Verify that node 2 is moved up node1");
    $(byXpath(ELEMENT_NAVIGATION_PREVIOUS_NODE.replace("${currentNode}", nodeName2)
                                              .replace("${previousNode}", nodeName1))).waitUntil(Condition.appears,
                                                                                                 Configuration.timeout);
    $(byXpath(ELEMENT_NAVIGATION_NEXT_NODE.replace("${currentNode}", nodeName2)
                                          .replace("${nextNode}", nodeName1))).waitUntil(Condition.not(Condition.visible),
                                                                                         Configuration.timeout);
    navigationmanagement.closeNavigationManagementPopup();

    info("Delete a node1 and node2");
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.deleteNode(nodeName1);
    portalmanagesites.goToEditNavigation(portalName);
    navigationmanagement.deleteNode(nodeName2);

  }
}
