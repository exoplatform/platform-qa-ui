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
  @Test
  @Tag("eabis")
  public void test_01_AddEditDelete_PermissionThenViewMetadata() {
    info("Add Permission");
    info("Get data test");
    String node1 = "node1" + getRandomNumber();
    String node2 = "node2" + getRandomNumber();
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
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    navigationToolbar.goToSiteExplorer();
    // Check if mary has edit, read on node1
    siteExplorerHome.selectNode(node1);
    // Delete data
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewFolder();
    info("Add permisison for a node");
    // Create node1, node2
    siteExplorerHome.createFolder(node2, folderType);
    info("Edit Permission");
    siteExplorerHome.selectNode(node2);
    siteExplorerHome.goToPermission();
    ecms_permission.changeRight("user", "Mary", false, false, true, "");
    $(By.xpath("//*[@checked='' and @name='maryremove']/..")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    info("Delete Permission");
    ecms_permission.deletePermissionNode("mary");
    ecms_permission.closePermission();
    info("Delete data test");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(node1);
    siteExplorerHome.deleteData(node2);
    info("View metadata");
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
