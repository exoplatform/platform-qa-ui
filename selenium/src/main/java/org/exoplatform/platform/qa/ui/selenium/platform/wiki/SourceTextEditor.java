package org.exoplatform.platform.qa.ui.selenium.platform.wiki;

import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class SourceTextEditor {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param dr
   */
  public SourceTextEditor(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Add a simple wiki page with source editor
   * 
   * @param title
   * @param content
   */
  public void addSimplePage(String title, String content) {
    info("Input a title for the page");
    String[] text;
    if (!title.isEmpty())
      evt.type(ELEMENT_TITLE_WIKI_INPUT, title, true);
    info("Input a content for the page");
    if (!content.isEmpty()) {
      text = content.split("</br>");
      for (int i = 0; i < text.length; i++) {
        evt.type(ELEMENT_CONTENT_WIKI_INPUT, text[i], false);
        evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).sendKeys(Keys.ENTER);
      }
    }
  }

  /**
   * Add a page with checking auto save after 30s
   * 
   * @param title
   * @param content
   */
  public void addSimplePageWithAutoSaveStatus(String title, String content) {
    info("Input a title for the page");
    String[] text;
    if (!title.isEmpty())
      evt.type(ELEMENT_TITLE_WIKI_INPUT, title, true);
    info("Input a content for the page");
    if (!content.isEmpty()) {
      text = content.split("</br>");
      for (int i = 0; i < text.length; i++) {
        evt.type(ELEMENT_CONTENT_WIKI_INPUT, text[i], false);
        evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).sendKeys(Keys.ENTER);
      }
    }
    info("Waiting 30s before saved all changes");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT, 31000, 1);

  }

  /**
   * Add a new page that has auto save without save
   * 
   * @param title
   * @param content
   */
  public void addSimplePageHasAutoSaveWithoutSave(String title, String content) {
    info("Input a title for the page");
    String[] text;
    if (!title.isEmpty())
      evt.type(ELEMENT_TITLE_WIKI_INPUT, title, true);
    info("Input a content for the page");
    if (!content.isEmpty()) {
      text = content.split("</br>");
      for (int i = 0; i < text.length; i++) {
        evt.type(ELEMENT_CONTENT_WIKI_INPUT, text[i], false);
        evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).sendKeys(Keys.ENTER);
      }
    }
    info("Waiting 30s before saved all changes");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT, 31000, 1);
    info("Cancel adding page");
    evt.click(ELEMENT_CANCEL_BUTTON_ADD_PAGE, 0, true);
    evt.click(ELEMENT_CONFIRMATION_POPUP_YES_BTN);
    Utils.pause(2000);
  }

  /**
   * Edit a simple wiki page with source editor
   * 
   * @param newTitle
   * @param newContent
   */
  public void editSimplePage(String newTitle, String newContent) {
    info("Input a title for the page");
    String[] text;
    if (!newTitle.isEmpty())
      evt.type(ELEMENT_TITLE_WIKI_INPUT, newTitle, true);
    info("Input a content for the page");
    if (!newContent.isEmpty()) {
      evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).clear();
      text = newContent.split("</br>");
      for (int i = 0; i < text.length; i++) {
        evt.type(ELEMENT_CONTENT_WIKI_INPUT, text[i], false);
        evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).sendKeys(Keys.ENTER);
      }
    }

  }

  /**
   * Modify Wiki content with Source editor
   * 
   * @param title updated title of the wiki page. Can not be <code>null</code>
   * @param content updated content of the wiki page. Can not be
   *          <code>null</code>
   */
  public void inputDataToPage(String title, String content, Boolean isClearTitle, Boolean isClearContent) {
    String[] text;
    info("Modify data with source editor");
    if (title != null) {
      if (isClearTitle)
        evt.type(ELEMENT_TITLE_WIKI_INPUT, title, true);
      else
        evt.type(ELEMENT_TITLE_WIKI_INPUT, title, false);
    }
    if (content != null) {
      if (isClearContent) {
        evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).clear();
        text = content.split("</br>");
        for (int i = 0; i < text.length; i++) {
          evt.type(ELEMENT_CONTENT_WIKI_INPUT, text[i], false);
          evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).sendKeys(Keys.ENTER);
        }
      } else {
        text = content.split("</br>");
        for (int i = 0; i < text.length; i++) {
          evt.type(ELEMENT_CONTENT_WIKI_INPUT, text[i], false);
          evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).sendKeys(Keys.ENTER);
        }
      }
    }
    Utils.pause(2000);
  }

  /**
   * Edit a wiki page with auto save status
   * 
   * @param newTitle
   * @param newContent
   */
  public void editSimplePageWithAutoSave(String newTitle, String newContent) {
    info("Input a title for the page");
    String[] text;
    if (!newTitle.isEmpty())
      evt.type(ELEMENT_TITLE_WIKI_INPUT, newTitle, true);
    info("Input a content for the page");
    if (!newContent.isEmpty()) {
      text = newContent.split("</br>");
      for (int i = 0; i < text.length; i++) {
        evt.type(ELEMENT_CONTENT_WIKI_INPUT, text[i], false);
        evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).sendKeys(Keys.ENTER);
      }
    }
    info("Waiting 30s before saved all changes");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT, 31000, 0);
  }

  /**
   * Attach a file to a Wiki page
   * 
   * @param link link of file that will be attached
   */
  /**
   * Attach a file to a Wiki page
   * 
   * @param link link of file that will be attached
   * @param type optional parameter of this method.
   */
  public void attachFile(String link) {
    // String fs = File.separator;
    // WebElement elem = waitForAndGetElement(ELEMENT_UPLOAD_NAME, 5000, 1, 2);
    String fs = File.separator;
    String path = testBase.getAbsoluteFilePath(link.replace("/", fs));
    info("path in uploadRobot:" + path);
    // doubleClickOnElement(ELEMENT_UPLOAD_NAME);

    Utils.pause(2000);
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");
    Utils.pause(2000);
    testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_UPLOAD_NAME).sendKeys(path);
    /*
     * scrollToElement(elem, driver); click(elem, 2, true);
     * uploadFileUsingRobot(link); waitForAndGetElement(By
     * .linkText(link.substring(link.lastIndexOf(fs) + 1)));
     */
    Utils.pause(3000);
  }

  /**
   * Attach many files to a wiki page
   */
  public void attachMultiFiles(String link) {
    String[] upload = link.split(";");
    for (int i = 0; i < upload.length; i++) {
      attachFile("TestData/" + upload[i]);
    }
  }

  /**
   * Attach a file to a Wiki page
   * 
   * @param link link of file that will be attached
   */
  /**
   * Attach a file to a Wiki page
   * 
   * @param link link of file that will be attached
   * @param type optional parameter of this method.
   */
  public void attachFileInWiki(String link, Integer... type) {
    String fs = File.separator;
    WebElement elem = evt.waitForAndGetElement(ELEMENT_UPLOAD_NAME, 5000, 1, 2);
    evt.scrollToElement(elem, testBase.getExoWebDriver().getWebDriver());
    evt.click(elem, 2, true);
    testBase.uploadFileUsingRobot(link);
    evt.waitForAndGetElement(By.linkText(link.substring(link.lastIndexOf(fs) + 1)));
  }

}
