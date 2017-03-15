package org.exoplatform.platform.qa.ui.selenium.platform.ecms;

import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_FILEFORM_BLANK_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_FILEFORM_BLANK_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
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
    Utils.pause(2000);
    info("Go to type " + type);
    switch (type) {
    case FILE:
      info("Select File type");
      evt.click(ELEMENT_ADDDOCUMENT_FILE);
      break;
    case WEBCONTENT:
      info("Select WebContent type");
      if ((evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBCONTENT, 3000, 0) == null)
          && (evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 3000, 0) != null)) {
        evt.click(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 2);
        evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBCONTENT, testBase.getDefaultTimeout(), 1);
        evt.click(ELEMENT_ADDDOCUMENT_WEBCONTENT, 2);
      } else
        evt.click(ELEMENT_ADDDOCUMENT_WEBCONTENT, 2);
      break;
    case ACCESSIBLEMEDIA:
      info("Select Accessiblemedia type");
      evt.click(ELEMENT_ADDDOCUMENT_ACCESSIBLE_MEDIA);
      break;
    case ANNOUNCEMENT:
      info("Select Announcement type");
      evt.click(ELEMENT_ADDDOCUMENT_ANNOUNCEMENT);
      break;
    case CSSFILE:
      info("Select Css file type");
      evt.click(ELEMENT_ADDDOCUMENT_CSS_FILE);
      break;
    case CONTACTUS:
      info("Select Contact us type");
      evt.click(ELEMENT_ADDDOCUMENT_CONTACT_US);
      break;
    case HTMLFILE:
      info("Select HTML file type");
      evt.click(ELEMENT_ADDDOCUMENT_HTML_FILE);
      break;
    case ILLUSTRATEDWEBCONTENT:
      info("Select Illustrated webcontent type");
      evt.click(ELEMENT_ADDDOCUMENT_ILLUSTRATED_WEB_CONTENT);
      break;
    case WEBLINK:
      info("Select Weblink type");
      if ((evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBLINK, 3000, 0) == null)
          && (evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 3000, 0) != null)) {
        evt.click(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 2);
        evt.waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBLINK, testBase.getDefaultTimeout(), 1);
        evt.click(ELEMENT_ADDDOCUMENT_WEBLINK, 2);
      } else
        evt.click(ELEMENT_ADDDOCUMENT_WEBLINK);
      break;
    case PRODUCT:
      info("Select Product type");
      evt.click(ELEMENT_ADDDOCUMENT_PRODUCT);
      break;
    case JAVASCRIPTFILE:
      info("Select Javascript file type");
      evt.click(ELEMENT_ADDDOCUMENT_JAVASCRIPT_FILE);
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
    Utils.pause(2000);
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
    Utils.pause(2000);
    evt.waitForAndGetElement(ELEMENT_FILEFORM_BLANK_NAME, testBase.getDefaultTimeout(), 1);
    evt.type(ELEMENT_FILEFORM_BLANK_NAME, title, true);
    plf.inputFrame(ELEMENT_FILEFORM_BLANK_CONTENT, content);
  }

  /**
   * Add a new Webcontent
   * 
   * @param title
   * @param content
   */
  public void addNewWebContent(String title, String content) {
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    Utils.pause(1000);
    evt.type(ELEMENT_FILEFORM_BLANK_NAME, title, true);
    testBase.inputDataToCKEditor(ELEMENT_FILEFORM_BLANK_CONTENT, content);
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
    evt.clickByJavascript(ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE);
    Utils.pause(2000);
    evt.waitForElementNotPresent(ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE);

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
