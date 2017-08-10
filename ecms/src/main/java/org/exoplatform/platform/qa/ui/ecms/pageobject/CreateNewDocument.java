package org.exoplatform.platform.qa.ui.ecms.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_FILEFORM_BLANK_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_FILEFORM_BLANK_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class CreateNewDocument {

  private final TestBase       testBase;

  public PlatformPermission    per;

  public ManageAlert           alert;

  public PlatformBase          plf;

  public SiteExplorerHome      SEHome;

  private ElementEventTestBase evt;

  public CreateNewDocument(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.plf = new PlatformBase(testBase);
  }

  /**
   * Create a new document
   *
   * @param type
   */
  public void createNewDoc(selectDocumentType type) {
    info("Go to type " + type);
    switch (type) {
    case FILE:
      info("Select File type");
      $(ELEMENT_ADDDOCUMENT_FILE).click();
      break;
    case WEBCONTENT:
      info("Select WebContent type");
      if ($(ELEMENT_ADDDOCUMENT_WEBCONTENT).is(Condition.exist)) {
        $(ELEMENT_ADDDOCUMENT_WEBCONTENT).click();
      } else {
        executeJavaScript("window.scrollBy(0,100);", "");
        // move ti page 2 for the list of template
        $(byClassName("pagination")).find(byText("2")).click();
        // scroll up
        executeJavaScript("window.scrollBy(0,-400);", "");
        $(ELEMENT_ADDDOCUMENT_WEBCONTENT).click();
      }
      break;
    case ACCESSIBLEMEDIA:
      info("Select Accessiblemedia type");
      $(ELEMENT_ADDDOCUMENT_ACCESSIBLE_MEDIA).click();
      break;
    case ANNOUNCEMENT:
      info("Select Announcement type");
      $(ELEMENT_ADDDOCUMENT_ANNOUNCEMENT).click();
      break;
    case CSSFILE:
      info("Select Css file type");
      $(ELEMENT_ADDDOCUMENT_CSS_FILE).click();
      break;
    case CONTACTUS:
      info("Select Contact us type");
      $(ELEMENT_ADDDOCUMENT_CONTACT_US).click();
      break;
    case HTMLFILE:
      info("Select HTML file type");
      $(ELEMENT_ADDDOCUMENT_HTML_FILE).click();
      break;
    case ILLUSTRATEDWEBCONTENT:
      info("Select Illustrated webcontent type");
      $(ELEMENT_ADDDOCUMENT_ILLUSTRATED_WEB_CONTENT).click();
      break;
    case WEBLINK:
      info("Select Weblink type");
      if ((evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBLINK, 3000, 0) == null)
          && (evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 3000, 0) != null)) {
        $(ELEMENT_ADDDOCUMENT_NEXT_PAGE).click();
        $(ELEMENT_ADDDOCUMENT_WEBLINK).waitUntil(Condition.appears, Configuration.timeout);
        $(ELEMENT_ADDDOCUMENT_WEBLINK).click();
      } else
        $(ELEMENT_ADDDOCUMENT_WEBLINK).click();
      break;
    case PRODUCT:
      info("Select Product type");
      $(ELEMENT_ADDDOCUMENT_PRODUCT).click();
      break;
    case JAVASCRIPTFILE:
      info("Select Javascript file type");
      $(ELEMENT_ADDDOCUMENT_JAVASCRIPT_FILE).click();
      break;
    }
  }

  /**
   * Add and select the type of new Content Folder By QuynhPT Date 16/01/2015
   *
   * @param title
   * @param type
   */
  public void createNewFolder(String title, folderType type) {
    info("-- Creating a new folder --");
    info("Verify that the popup is shown");
    evt.waitForAndGetElement(ELEMENT_ADD_NEW_FOLDER_POPUP_TITLE, 2000, 0);
    info("Verify that has check box element is shown on the popup");
    WebElement checkBox = evt.waitForAndGetElement(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 5000, 0);
    info("if check box is avaiabled and unchecked, so, check it.");
    if (checkBox != null && !checkBox.isSelected()) {
      info("Check on check box");
      evt.click(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 2);
    }
    info("Select a type of new folder");
    switch (type) {
    case Content:
      info("Type a text to title field of Content type");
      evt.type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
      break;
    case Document:
      info("Type a text to title field of Document type");
      evt.type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
      info("Select Document type");
      plf.selectOption(ELEMENT_FOLDER_TYPE_OPTION, ELEMENT_DOCUMENT_FOLDER_TYPE);
      break;
    default:
      break;
    }
    info("Save the changes");
    evt.click(ELEMENT_CREATE_FOLDER_BUTTON);

  }

  /**
   * Add a new file only with title and content string update QuynhPT date
   * 13/01/2015
   *
   * @param title
   * @param content
   */
  public void addNewFile(String title, String content) {
    this.testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    $(ELEMENT_FILEFORM_BLANK_NAME).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_FILEFORM_BLANK_NAME).setValue(title);
    $(ELEMENT_FILEFORM_BLANK_CONTENT).click();
    $(ELEMENT_FILEFORM_BLANK_CONTENT).waitUntil(Condition.appears, Configuration.timeout).sendKeys(content);
  }

  /**
   * Add a new Webcontent
   *
   * @param title
   * @param content
   */
  public void addNewWebContent(String title, String content) {
    $(ELEMENT_FILEFORM_BLANK_NAME).click();
    $(ELEMENT_FILEFORM_BLANK_NAME).setValue(title);
    $(ELEMENT_FILEFORM_BLANK_CONTENT).click();
    $(ELEMENT_FILEFORM_BLANK_CONTENT).sendKeys(content);

  }

  /**
   * Add a new product
   *
   * @param title
   * @param summary
   */
  public void addNewProduct(String title, String summary) {
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    evt.type(ELEMENT_FILEFORM_BLANK_NAME, title, true);
    plf.inputFrame(ELEMENT_FILEFORM_BLANK_CONTENT, summary);
    evt.switchToParentWindow();
  }

  /**
   * Save and close a file form
   */

  public void saveAndClose() {
    info("save and close");
    // scroll up
    executeJavaScript("window.scrollBy(0,-250)");
    $(ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE).click();

  }

  /**
   * Add a link into webcontent
   *
   * @param url
   */
  public void addLinkInWebContent(String url) {
    evt.click(ELEMENT_WEBCONTENTFORM_BUTTON_LINK);
    evt.type(ELEMENT_WEBCONTENTFORM_LINK_ADRESS, url, true);
    evt.click(ELEMENT_WEBCONTENTFORM_LINK_OK);
  }

  /**
   * Create a Advanced Document
   *
   * @param name
   * @param content
   * @param title
   * @param desc
   * @param creator
   * @param source
   */
  public void createAdvancedDocument(String name, String content, String title, String desc, String creator, String source) {

    evt.type(ELEMENT_FILEFORM_BLANK_NAME, title, true);
    plf.inputFrame(ELEMENT_FILEFORM_BLANK_CONTENT, content);
    evt.switchToParentWindow();
    if (title != "" && title != null) {
      evt.type(ELEMENT_DOCFORM_BLANK_TITLE, title, true);
    }
    if (desc != "" && desc != null) {
      evt.type(ELEMENT_DOCFORM_BLANK_DESC, desc, true);
    }
    if (creator != "" && creator != null) {
      evt.type(ELEMENT_DOCFORM_BLANK_CREATOR, creator, true);
    }
    if (source != "" && source != null) {
      evt.type(ELEMENT_DOCFORM_BLANK_SOURCE, source, true);
    }
  }

  /**
   * Select a document by type
   */
  public enum selectDocumentType {
    FILE, WEBCONTENT, ACCESSIBLEMEDIA, ANNOUNCEMENT, CSSFILE, CONTACTUS, HTMLFILE, ILLUSTRATEDWEBCONTENT, JAVASCRIPTFILE, PRODUCT, WEBLINK
  }

  /**
   * Define Folder types
   */
  public enum folderType {
    None, Content, Document, Css;
  }
}
