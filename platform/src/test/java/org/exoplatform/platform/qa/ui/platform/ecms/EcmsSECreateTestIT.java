package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_SOURCE_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

@Tag("sniff")
@Tag("ecms")
public class EcmsSECreateTestIT extends Base {
  NavigationToolbar navigationToolbar;

  SiteExplorerHome siteExplorerHome;

  ManageLogInOut manageLogInOut;

  CreateNewDocument createNewDocument;

  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  CreateNewDocument createNewDoc;


  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    spaceManagement = new SpaceManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    siteExplorerHome = new SiteExplorerHome(this);
    manageLogInOut = new ManageLogInOut(this);
    createNewDocument = new CreateNewDocument(this);
    createNewDoc = new CreateNewDocument(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @Test
  @Tag("EXOGTN-2345")
  public void test01_CreateWebContentWithParticularCharacterThen_AddEditDelete_FileDocumentAndWebContentDocument() {
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    String title = "title" + getRandomNumber();
    String folderType = "Content Folder";
    String link = "google.fr";

    info("Create Web Content document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(name, "&#128522;");
    $(ELEMENT_SOURCE_CONTENT).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_CONTENT).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_CONTENT).setValue("&#128522;");
    createNewDoc.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(name);
    siteExplorerHome.verifyWebContentInformationCreatedSuccessfully("\uD83D\uDE0A");
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);

    info("Create a content folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewFolder();
    info("Create new file document");
    siteExplorerHome.createFolder(title, folderType);

    info("Test 10: Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    switchTo().frame($(byClassName("ECMIframe")));
    $(byText(content)).should(Condition.visible);
    switchTo().defaultContent();
    info("Edit a content");
    siteExplorerHome.selectNode(name);
    siteExplorerHome.editDocument("", content2);
    createNewDocument.saveAndClose();
    switchTo().frame($(byClassName("ECMIframe")));
    $(byText(content + content2)).should(Condition.visible);
    switchTo().defaultContent();
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);

    info("Create Web Content document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    $(byText(content)).should(Condition.visible);
    info("Edit the content");
    siteExplorerHome.selectNode(name);
    siteExplorerHome.editDocument("", content2);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    $(byText(content + content2)).should(Condition.visible);
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
    info("Insert documents/medias in a web content by FCK Content Selector");
    info("Add a new content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.addLinkInWebContent(link);
    createNewDocument.saveAndClose();
    click(By.xpath("//*[contains(text(),'google.fr')]"));
    refresh();
    assertEquals(Selenide.title(), "Google");
    back();
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }

  @Test
  @Tag("eabis")
  public void test02_Upload_AFileInContentExplorerThenInInIntranetDocumentThenInSpaceDocument() {

    String spaceName = "spaceName" + getRandomNumber();
    String content = "content" + getRandomNumber();

    info("Upload a file in Content explorer");
    info("Upload a file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.uploadFile("eXo-Platform.png", true);
    info("Delete a file");
    siteExplorerHome.deleteData("eXo-Platform.png");

    info("Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("eXo-Platform.png");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "eXo-Platform.png"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("eXo-Platform.png");

    info("Upload a file in Space/Document");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, content);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToSpace(spaceName);
    sleep(1000);
    $(byId("MultiUploadInputFiles")).uploadFromClasspath("eXo-Platform.png");
    sleep(2000);
    executeJavaScript("window.scrollBy(0,-300)");
    $(ELEMENT_ADDRESS_BAR_ICON_VIEW).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath(ELEMENT_SPACE_DRIVE_NODE_TREE_FILE.replace("${file}", "eXo-Platform.png"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.deleteData("eXo-Platform.png");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  @Tag("eabis")
  public void test03_Upload_AFileDOCThenODTThenPDFThenRTFThenXLSXThenDOCXInIntranetDocument() {
    info("Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("key_word.doc");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "key_word.doc"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("key_word.doc");

    info("Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoOdtTest.odt");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoOdtTest.odt"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("exoOdtTest.odt");

    info("Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("testavatar.pdf");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "testavatar.pdf"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("testavatar.pdf");

    info("Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoRtfTest.rtf");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoRtfTest.rtf"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("exoRtfTest.rtf");

    info("Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoXlsxTest.xlsx");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoXlsxTest.xlsx"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("exoXlsxTest.xlsx");

    info("Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoTest.docx");
    siteExplorerHome.goToPath("intranet/documents","Site Management");

    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoTest.docx"))).

            waitUntil(Condition.visible, Configuration.timeout);

    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents","Site Management");
    siteExplorerHome.deleteData("exoTest.docx");

  }

}
