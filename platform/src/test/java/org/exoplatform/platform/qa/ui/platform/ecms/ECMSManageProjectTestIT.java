package org.exoplatform.platform.qa.ui.platform.ecms;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.DocumentManagement;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
/**
 * Created by ilyes on 30/10/17.
 */
@Tag("sniff")
@Tag("ecms")
public class ECMSManageProjectTestIT extends Base {
  HomePagePlatform homePagePlatform;
  DocumentManagement documentManagement;
  ManageLogInOut manageLogInOut;
  NavigationToolbar navigationToolbar;
  SiteExplorerHome siteExplorerHome;
  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    documentManagement = new DocumentManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
  }
  @Test
  public void test06_AddRenameDeleteFolderInIconsView() {
    String folder = "folder" + getRandomNumber();
    String folder1 = "folder" + getRandomNumber();
    String newfolder = "newfolder" + getRandomNumber();
    String folder2 = "folder" + getRandomNumber();
    String folder3 = "folder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToIconsView();
    documentManagement.createFolder("/", folder1, false);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder1)).should(Condition.exist);
    info("Rename Folder In Icons View");
    documentManagement.renameFolderInAdminView("/", folder1, newfolder, true);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(newfolder)).should(Condition.visible);
    refresh();
    documentManagement.createFolder("/", folder2, false);
    documentManagement.createFolder("/" + folder2, folder3, true);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder3)).should(Condition.exist);
    documentManagement.deleteFolder("/" + folder2, folder3, true);
    documentManagement.deleteFolder("/", folder2, true);
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    documentManagement.createFolder("/", folder, true);
    documentManagement.deleteFolder("/", folder, true);
    documentManagement.deleteFolder("/", newfolder, true);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder)).shouldNot(Condition.exist);
  }
  @Test
  @Tag("ECMS-7763")
  public void test09_UploadDeleteFileThenCheckSubContentDisplayedWhenClickingPlusIconThenCloseButtonFunctionalityBrowsingPreference() {
    //8352
    info("test09 delete File");
    homePagePlatform.goToDocuments();
    documentManagement.goToListView();
    $(byId("MultiUploadInputFiles")).uploadFromClasspath("eXo-Platform.png");
    $(byClassName("progress")).waitUntil(Condition.visible, Configuration.timeout);
    $(byClassName("progress")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    refresh();
    ELEMENT_LIST_DOCUMENT_FOLDER.find(byText("eXo-Platform")).waitUntil(Condition.visible, Configuration.timeout);
    refresh();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    ELEMENT_INPUT_PATH.setValue("/").pressEnter();
    documentManagement.deleteFile("/", "eXo-Platform", false);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText("eXo-Platform")).shouldNot(Condition.exist);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("", "Site Management");
    $(ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET).parent().parent().parent().parent().find(ELEMENT_EXPAND_ICON).click();
    $(ELEMENT_SITEEXPLORER_LEFTBOX_DOCUMENT).shouldBe(Condition.visible);
    $(ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET).parent()
            .parent()
            .parent()
            .shouldHave(Condition.attribute("class", "collapseIcon SelectedNode SelectedNode"));
    info("Check close button in the browsing preference");
    homePagePlatform.goToDocuments();
    siteExplorerHome.closeSettingsDriver();
    $(byXpath("//span[text()='Documents Browsing Preferences']")).shouldNot(Condition.visible);
  }
}
