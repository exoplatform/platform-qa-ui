package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("sniff")
@Tag("ecms")
public class EcmsSECollaborationTestIT extends Base {

  NavigationToolbar navigationToolbar;

  SiteExplorerHome  siteExplorerHome;

  CreateNewDocument createNewDocument;

  ManageLogInOut    manageLogInOut;

  ManageAlert       manageAlert;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    manageAlert = new ManageAlert(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");

  }

  /**
   * <li>Case ID:116564.</li>
   * <li>Test Case Name: Add translation for document/uploaded file.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Add translation for document/uploaded file Input Data: - Add some
   * documents in different languages, eg English, French... - Open document in
   * English - Click on Add Translation icon on the action bar - Navigate to
   * French document - Save - Click Relation icon on side bar Expected Outcome: -
   * Documents are created - French document is list in Languages list of English
   * document.When you add this content in a CLV or SCV, change language, you will
   * see effect
   */

  @Test
  public void test01_AddTranslationForDocumentuploadedFile() {
    info("Test 1: Add translation for document/uploaded file");
    String title = "title" + getRandomNumber() + "en";
    String content = "content" + getRandomNumber() + "en";
    String title2 = "title2" + getRandomNumber() + "fr";
    String content2 = "content2" + getRandomNumber() + "fr";
    String fileName = "eXo-Platform.png";

    info("Create content 1");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();

    siteExplorerHome.selectNode("documents");
    info("Create content 2");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(title2, content2);
    $(ELEMENT_FILEFORM_LANGUAGE).selectOption("fr");
    createNewDocument.saveAndClose();

    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title))).click();
    siteExplorerHome.addDocumentTranslation("General Drives/Sites Management/intranet/documents", title2);
    $(ELEMENT_SITEEXPLORER_LEFTBOX_RELATION).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_TITLE_TRANSLATION.replace("${title}", title2))).waitUntil(Condition.visible,
                                                                                                     Configuration.timeout);

    info("Delete all data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Vote for document/uploaded file");
    siteExplorerHome.uploadFile(fileName);
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", "eXo-Platform.png"))).click();
    siteExplorerHome.voteDocument();
    $(ELEMENT_SITEEXPLORER_VOTE_AVERAGE).click();
    $(ELEMENT_SITEEXPLORER_VOTEONDOCUMENT).waitUntil(Condition.visible, Configuration.timeout);
    siteExplorerHome.deleteData("eXo-Platform.png");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
    siteExplorerHome.deleteData(title2);
  }

  @Test
  public void test03_EditTagThenComment() {
    info("Edit Comment");
    info("Get data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String title0 = "title" + getRandomNumber();
    String content0 = "content" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    String content3 = "content3" + getRandomNumber();
    String content4 = "content4" + getRandomNumber();
    String content02 = "content02" + getRandomNumber();
    String content03 = "content03" + getRandomNumber();
    info("Finished getting data test");

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Add a new content");
    siteExplorerHome.goToAddNewContent();
    info("Select a document type");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    info("Create a new file");
    createNewDocument.addNewFile(title0, content0);
    info("Save and close the file");
    createNewDocument.saveAndClose();

    info("Add a tag to the Content");
    siteExplorerHome.selectNode(title0);
    siteExplorerHome.addTag(content02);
    info("Verify that the tag is shown in Tag tab of SE");
    $(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB).click();
    $(byId("UITagExplorer")).find(byText(content02)).should(Condition.visible);
    info("Edit a tag");
    siteExplorerHome.editTag(content02, content03);
    info("Delete a tag");
    siteExplorerHome.deleteTag(content03);

    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Add a new content");
    siteExplorerHome.goToAddNewContent();
    info("Select a document type");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    info("Create a new file");
    createNewDocument.addNewFile(title, content);
    info("Save and close the file");
    createNewDocument.saveAndClose();

    siteExplorerHome.addEditComment(content4, true);
    $(byClassName("showComments")).click();
    $(byText(content4)).should(Condition.visible);
    $(ELEMENT_SITEEXPLORER_COMMENT_DELETE).click();
    manageAlert.acceptAlert();
    $(byText(content4)).shouldNot(Condition.exist);

    siteExplorerHome.addEditComment(content2, true);
    info("Veriy that the comment is added");
    $(ELEMENT_SITEEXPLORER_COMMENT_SHOW).click();
    $(byText(content2)).should(Condition.exist);
    assertEquals($$(byText(content2)).size(), 1);
    info("Click on Show comment button on action bar");
    siteExplorerHome.addEditComment(content3, false);
    info("Click on Show comment button on action bar");
    $(ELEMENT_SITEEXPLORER_COMMENT_SHOW).click();
    info("Verify that the comment is edited");
    $(byText(content2)).shouldNot(Condition.exist);
    $(byText(content2 + content3)).should(Condition.exist);
    assertEquals($$(byText(content2 + content3)).size(), 1);
    info("The coment is edited successfully");
    siteExplorerHome.deleteData(title);
    siteExplorerHome.deleteData(title0);

  }

}
