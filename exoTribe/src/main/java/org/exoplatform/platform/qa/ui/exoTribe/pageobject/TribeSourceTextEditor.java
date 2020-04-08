package org.exoplatform.platform.qa.ui.exoTribe.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_CONTENT_WIKI_INPUT_TRIBE;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class TribeSourceTextEditor {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public TribeSourceTextEditor(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Add a simple wiki page with source editor
   *
   * @param title String
   * @param content String
   */
  public void addSimplePage(String title, String content) {
    info("Input a title for the page");
    if(!$(ELEMENT_TITLE_WIKI_INPUT).exists())
    {
      do {
        //refresh();
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
      }while (!$(ELEMENT_TITLE_WIKI_INPUT).exists());
    }
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(title);

    info("Input a content for the page");
    sleep(1000);
    switchTo().frame($(byXpath("//*[@class='gwt-RichTextArea']")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs));
    $(ELEMENT_CONTENT_WIKI_INPUT_TRIBE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(content);
    switchTo().defaultContent();
  }

  /**
   * Add a page with checking auto save after 30s
   *
   * @param title String
   * @param content String
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
   * @param title String
   * @param content String
   */
  public void addSimplePageHasAutoSaveWithoutSave(String title, String content) {
    info("Input a title for the page");
    if(!$(ELEMENT_TITLE_WIKI_INPUT).exists())
    {
      do {
        //refresh();
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
        sleep(2000);
      }while (!$(ELEMENT_TITLE_WIKI_INPUT).exists());
    }
    String[] text;
    if (!title.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).setValue(title);
    info("Input a content for the page");
    if (!content.isEmpty()) {
      text = content.split("</br>");
      for (int i = 0; i < text.length; i++) {
        $(ELEMENT_CONTENT_WIKI_INPUT).setValue(content);
        $(ELEMENT_CONTENT_WIKI_INPUT).waitUntil(Condition.visible, Configuration.timeout).sendKeys(Keys.ENTER);
      }
    }
    info("Waiting 30s before saved all changes");
    $(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT).waitUntil(Condition.visible,31000);

    info("Cancel adding page");
    $(ELEMENT_CANCEL_BUTTON_ADD_PAGE).click();
    $(ELEMENT_CONFIRMATION_POPUP_YES_BTN).click();

  }

  /**
   * Edit a simple wiki page with source editor
   *
   * @param newTitle String
   * @param newContent String
   */
  public void editSimplePage(String newTitle, String newContent) {
    info("Input a title for the page");
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).clear();
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).sendKeys(newTitle);

    info("Input a content for the page");
    sleep(1000);
    switchTo().frame($(byXpath("//*[@class='gwt-RichTextArea']")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs));
    $(ELEMENT_CONTENT_WIKI_INPUT_TRIBE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).clear();
    $(ELEMENT_CONTENT_WIKI_INPUT_TRIBE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).sendKeys(newContent);
    switchTo().defaultContent();

  }

  /**
   * Edit a wiki page with auto save status
   *
   * @param newTitle String
   * @param newContent String
   */
  public void editSimplePageWithAutoSave(String newTitle, String newContent) {
    info("Input a title for the page");
    String[] text;
    if (!newTitle.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).setValue(newTitle);
    info("Input a content for the page");
    if (!newContent.isEmpty()) {
      text = newContent.split("</br>");
      for (int i = 0; i < text.length; i++) {
      $(ELEMENT_CONTENT_WIKI_INPUT).setValue(newContent);
       $(ELEMENT_CONTENT_WIKI_INPUT).waitUntil(Condition.visible, Configuration.timeout).sendKeys(Keys.ENTER);
      }
    }
    info("Waiting 30s before saved all changes");
    $(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT).waitUntil(Condition.visible,31000);
  }


  /**
   * Attach a file to a Wiki page
   *
   * @param link link of file that will be attached
   */
  public void attachFile(String link) {
    String fs = File.separator;
    String path = testBase.getAbsoluteFilePath(link.replace("/", fs));
    info("path in uploadRobot:" + path);

    ((JavascriptExecutor) testBase.getExoWebDriver()
                                  .getWebDriver()).executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");

    testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_UPLOAD_NAME).sendKeys(path);

  }

  /**
   * Attach a file to a Wiki page
   *
   * @param link link of file that will be attached
   * @param type optional parameter of this method.
   */
  public void attachFileInWiki(String link, Integer... type) {
    String fs = File.separator;
    WebElement elem = $(ELEMENT_UPLOAD_NAME);
    evt.scrollToElement(elem, testBase.getExoWebDriver().getWebDriver());
    $(byClassName("uploadInput")).find(byName("file")).uploadFromClasspath(link);
    $(By.linkText(link.substring(link.lastIndexOf(fs) + 1))).waitUntil(Condition.visible,Configuration.timeout);
  }

}
