package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.ecms.ECMS_Permission;

@Tag("ecms")
@Tag("sniff")
public class EcmsSEInfoTestIT extends Base {
  NavigationToolbar navigationToolbar;

  SiteExplorerHome  siteExplorerHome;

  ECMS_Permission   ecms_permission;

  ManageLogInOut    manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    ecms_permission = new ECMS_Permission(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:116591.</li>
   * <li>Test Case Name: Add Permission.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> *
   * <li>Case ID:116639.</li>
   * <li>Test Case Name: Edit Permission.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> *
   * <li>Case ID:116640.</li>
   * <li>Test Case Name: Delete Permission.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Add Permission Input Data: - Select one node - Click on Permissions icon
   * on action bar - Add permission for node - Choose user/group to add permission
   * - Click Save - Log out and log in with an user who match with the permission
   * set above Expected Outcome: - The node is added permission - User will take
   * action on it as his rights
   */
  @Test
  @Tag("eabis")
  public void test01_AddPermission() {
    info("Test 1: Add Permission");
    info("Get data test");
    String node1 = "node1" + getRandomNumber();
    String folderType = "Content Folder";
    info("Finished getting data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewFolder();
    info("Add permisison for a node");
    // Create node1, node2
    siteExplorerHome.createFolder(node1, folderType);
    siteExplorerHome.selectNode(node1);
    // Add permission "read", "write" for mary
    siteExplorerHome.goToPermission();
    ecms_permission.deletePermissionNode("mary");
    ecms_permission.changeRight("user", "Mary", true, true, false, "");
    ecms_permission.closePermission();
    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    navigationToolbar.goToSiteExplorer();
    // Check if mary has edit, read on node1
    siteExplorerHome.selectNode(node1);
    // Delete data
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    info("Test 02: Edit Permission");
    siteExplorerHome.selectNode(node1);
    siteExplorerHome.goToPermission();
    ecms_permission.changeRight("user", "Mary", false, false, true, "");
    $(By.xpath("//*[@checked='' and @name='maryremove']/..")).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 03: Delete Permission");
    ecms_permission.deletePermissionNode("mary");
    ecms_permission.closePermission();
    info("Delete data test");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(node1);
  }

  @Test
  @Tag("eabis")
  public void test02_EditPermission() {
    info("Test 1: Add Permission");
    info("Get data test");
    String node1 = "node1" + getRandomNumber();
    String folderType = "Content Folder";
    info("Finished getting data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewFolder();
    info("Add permisison for a node");
    // Create node1, node2
    siteExplorerHome.createFolder(node1, folderType);
    siteExplorerHome.selectNode(node1);
    // Add permission "read", "write" for mary
    siteExplorerHome.goToPermission();
    ecms_permission.deletePermissionNode("mary");
    ecms_permission.changeRight("user", "Mary", true, true, false, "");
    ecms_permission.closePermission();
    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    navigationToolbar.goToSiteExplorer();
    // Check if mary has edit, read on node1
    siteExplorerHome.selectNode(node1);
    // Delete data
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    info("Test 02: Edit Permission");
    siteExplorerHome.selectNode(node1);
    siteExplorerHome.goToPermission();
    ecms_permission.changeRight("user", "Mary", false, false, true, "");
    $(By.xpath("//*[@checked='' and @name='maryremove']/..")).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 03: Delete Permission");
    ecms_permission.deletePermissionNode("mary");
    ecms_permission.closePermission();
    info("Delete data test");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(node1);
  }

  @Test
  @Tag("eabis")
  public void test_03DeletePermission() {
    info("Test 1: Add Permission");
    info("Get data test");
    String node1 = "node1" + getRandomNumber();
    String folderType = "Content Folder";
    info("Finished getting data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewFolder();
    info("Add permisison for a node");
    // Create node1, node2
    siteExplorerHome.createFolder(node1, folderType);
    siteExplorerHome.selectNode(node1);
    // Add permission "read", "write" for mary
    siteExplorerHome.goToPermission();
    ecms_permission.deletePermissionNode("mary");
    ecms_permission.changeRight("user", "Mary", true, true, false, "");
    ecms_permission.closePermission();
    manageLogInOut.signIn(DATA_USER2, "gtngtngtn");
    navigationToolbar.goToSiteExplorer();
    // Check if mary has edit, read on node1
    siteExplorerHome.selectNode(node1);
    // Delete data
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    info("Test 02: Edit Permission");
    siteExplorerHome.selectNode(node1);
    siteExplorerHome.goToPermission();
    ecms_permission.changeRight("user", "Mary", false, false, true, "");
    $(By.xpath("//*[@checked='' and @name='maryremove']/..")).waitUntil(Condition.visible, Configuration.timeout);
    info("Test 03: Delete Permission");
    ecms_permission.deletePermissionNode("mary");
    ecms_permission.closePermission();
    info("Delete data test");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(node1);
  }

  /**
   * <li>Case ID:116609.</li>
   * <li>Test Case Name: View metadata.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: View metadata Input Data: - Upload a file - Select it - Click Metadata
   * Expected Outcome: View matadata form is shown
   */
  @Test
  @Tag("eabis")
  public void test04_ViewMetadata() {
    info("Test 04: View metadata");

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
    $(byClassName("uiIconEcmsViewWeb")).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
    siteExplorerHome.uploadFile("testavatar.pdf");
    siteExplorerHome.selectNode("testavatar.pdf");
    $(byClassName("uiIconEcmsViewAdmin")).click();
    siteExplorerHome.viewMetadata();
    info("Delete data test");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    refresh();
    siteExplorerHome.deleteData("testavatar.pdf");
  }

}
