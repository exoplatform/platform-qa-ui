package org.exoplatform.platform.qa.ui.platform.social;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;

@Tag("sniff")
@Tag("social")
public class SOCSpaceManageNavigationTestIT extends Base {
  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  ManageLogInOut         manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:121898.</li>
   * <li>Test Case Name: Add new node.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add new node Step
   * Description: - Access Space, select Setting tab/ Navigation tab + Click on
   * Add node, enter Node name and other information and click on save Input Data:
   * Expected Outcome: - Node is added and is showed on navigation of space.
   */
  @Test
  public void test01_AddNewNode() {
    info("Test 01: Add new node");
    String space = "space" + getRandomNumber();
    String node = "node" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Open Setting tab");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToNavigationTab();
    spaceSettingManagement.addANodeSimple(node);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  /**
   * <li>Case ID:121913.</li>
   * <li>Test Case Name:Edit a node.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Edit a node Step
   * Description: - User logs in system - Go to spaces page - Add new space -
   * Access space and select Space settings portlet or Click on Space setting icon
   * - Select Space navigation tab - Right click on node and select Edit this node
   * - Change the value of node and click on save Input Data: Expected Outcome: -
   * Node/Page's node is update with all changed value.
   */
  @Test
  public void test02_EditANode() {
    info("Test 02:Edit a node");
    String space = "space" + getRandomNumber();
    String node1 = "node1" + getRandomNumber();
    String node2 = "node2" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Open Setting tab");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToNavigationTab();
    spaceSettingManagement.addANodeSimple(node1);
    info("Edit node 1");
    spaceSettingManagement.editANodeSimple(node1, node2);
    spaceSettingManagement.deleteANode(node2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

}
