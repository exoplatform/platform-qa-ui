package org.exoplatform.platform.qa.ui.onlyOffice;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

public class OnlyOfficeTestIT extends Base {

  HomePagePlatform            homePagePlatform;
  NavigationToolbar           navigationToolbar;
  SiteExplorerHome            siteExplorerHome;
  ManageLogInOut              manageLogInOut;
  ConnectionsManagement       connectionsManagement;

  @BeforeEach
  public void setupBeforeMethod() {

    homePagePlatform            =new HomePagePlatform(this);
    navigationToolbar           =new NavigationToolbar(this);
    siteExplorerHome            =new SiteExplorerHome(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas("root", "gtn");
    connectionsManagement = new ConnectionsManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
  }

  @Test
  public void EditOnline_BTN_US01 () {
    String wordDocument = "OO_test";
    info("Upload document");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(wordDocument + ".docx");
    info("Open document");
    siteExplorerHome.checkButtonDocument(wordDocument);
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    siteExplorerHome.clickEditOnine();
    switchTo().window(2);
    $(byText(wordDocument + ".docx")).should(Condition.visible);
    switchTo().window(0);
    info("Delete document");
    siteExplorerHome.checkButtonDocument(wordDocument);
    siteExplorerHome.clickDeleteButtonDocument();
  }

}
