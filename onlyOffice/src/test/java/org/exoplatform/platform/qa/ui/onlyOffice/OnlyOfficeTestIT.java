package org.exoplatform.platform.qa.ui.onlyOffice;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMPOSER_SHARE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_BAR_PROGRESS;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_CLOSE_DOCUMENT_PREVIEW1;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;


public class OnlyOfficeTestIT extends Base {

  HomePagePlatform            homePagePlatform;
  NavigationToolbar           navigationToolbar;
  SiteExplorerHome            siteExplorerHome;
  ManageLogInOut              manageLogInOut;
  ConnectionsManagement       connectionsManagement;
  ActivityStream              activityStream;
  SpaceManagement             spaceManagement;
  TestBase                    testBase;


  @BeforeEach
  public void setupBeforeMethod() {

    homePagePlatform            =new HomePagePlatform(this);
    navigationToolbar           =new NavigationToolbar(this);
    siteExplorerHome =new SiteExplorerHome(this);
    manageLogInOut = new ManageLogInOut(this);
    //manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    spaceManagement             =new SpaceManagement(this);

    activityStream      =new ActivityStream(this);
    manageLogInOut.signInCas("root", "gtn");
    connectionsManagement = new ConnectionsManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
  }

  @Test
  public void EditOnline_BTN_US01 () {
    String Document = "OO_test";
    info("Upload document");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(Document + ".docx");
    info("Open document");
    siteExplorerHome.checkButtonDocument(Document);
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    siteExplorerHome.clickEditOnine();
    switchTo().window(2);

    // check toolbar here
    $(byText(Document + ".docx")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    // check document editor here
    $(byClassName("editor")).should(Condition.exist);
    switchTo().frame("frameEditor");
    $(byId("title-user-name")).shouldHave(Condition.exactText("Root Root"));

    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    info("Delete document");
    siteExplorerHome.checkButtonDocument(Document);
    siteExplorerHome.clickDeleteButtonDocument();
    manageLogInOut.signOut();
  }


  @Test
  public void EditOnline_BTN_US02 () {
    String Document = "OO_test.docx";
    String space_test = "space_1_"+getRandomNumber();
    info("Edit Online from document from AS");
    activityStream.UploadFileFromAS(Document);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc",Document))),Condition.visible,Configuration.timeout);
    ELEMENT_EDIT_ONLINE_BUTTON_FROM_AS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    switchTo().window(1);
    $(byText(Document)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    info("Edit Online from document preview");
    ELEMENT_OPEN_DOCUMENT_PREVIEW1.waitUntil(Condition.visible,30000).doubleClick();
    ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    switchTo().window(1);
    sleep(Configuration.timeout);
    switchTo().window(1).close();
    switchTo().window(0);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW1.waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    activityStream.DeleteDocumentFromAS(Document);
    info("Edit Online document from Space AS");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space_test,"test",5000);
    activityStream.UploadFileFromAS(Document);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc",Document))),Condition.visible,Configuration.timeout);
    ELEMENT_EDIT_ONLINE_BUTTON_FROM_AS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    switchTo().window(1);
    $(byText(Document)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space_test,false);
    info("Edit Online document from Document Application");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(Document);
    siteExplorerHome.checkButtonDocument("OO_test");
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    siteExplorerHome.clickEditOnine();
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    siteExplorerHome.checkButtonDocument("OO_test");
    siteExplorerHome.clickDeleteButtonDocument();
    manageLogInOut.signOut();

  }


}

