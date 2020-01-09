package org.exoplatform.platform.qa.ui.ecms.pageobject;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
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
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class CreateNewDocument {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public PlatformBase          plf;

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
   * @param type enum
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
        sleep(Configuration.timeout);
        executeJavaScript("window.scrollBy(0,200);", "");
        sleep(Configuration.timeout);
        $(byXpath("//div[@class='PageAvailable']//li/a[text()='2']")).waitUntil(Condition.visible,Configuration.timeout).click();
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
   * @param title String
   * @param type String
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
      $(ELEMENT_USE_CUSTOM_TYPE_FOLDER).parent().click();
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
   * @param title String
   * @param content String
   */
  public void addNewFile(String title, String content) {
    $(ELEMENT_FILEFORM_BLANK_NAME).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_FILEFORM_BLANK_NAME).click();
    $(ELEMENT_FILEFORM_BLANK_NAME).setValue(title);
    $(ELEMENT_FILEFORM_BLANK_CONTENT).click();
    $(ELEMENT_FILEFORM_BLANK_CONTENT).click();
    switchTo().frame($(ELEMENT_FILEFORM_BLANK_CONTENT));
    $(byXpath("/html/body")).sendKeys(content);
    switchTo().defaultContent();
  }

  /**
   * Add a new Webcontent
   *
   * @param title String
   * @param content String
   */
  public void addNewWebContent(String title, String content) {
    $(ELEMENT_FILEFORM_BLANK_NAME).click();
    $(ELEMENT_FILEFORM_BLANK_NAME).setValue(title);
    $(ELEMENT_FILEFORM_BLANK_CONTENT).waitUntil(Condition.visible,Configuration.timeout).click();
    switchTo().frame($(ELEMENT_FILEFORM_BLANK_CONTENT));
    sleep(Configuration.timeout);
    $(byXpath("/html/body")).sendKeys(content);
    switchTo().defaultContent();

  }

  /**
   * Save and close a file form
   */

  public void saveAndClose() {
    info("save and close");
    executeJavaScript("window.scrollBy(0,-250)");
    $(ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(Configuration.timeout);

  }

  /**
   * Add a link into webcontent
   *
   * @param url String
   */
  public void addLinkInWebContent(String url) {
    $(ELEMENT_WEBCONTENTFORM_BUTTON_LINK).click();
    $(ELEMENT_WEBCONTENTFORM_LINK_ADRESS).setValue(url);
    $(ELEMENT_WEBCONTENTFORM_LINK_OK).click();
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
