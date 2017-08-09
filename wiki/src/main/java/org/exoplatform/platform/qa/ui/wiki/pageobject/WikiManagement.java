package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public Button                but;

  public PlatformBase          plf;

  private ElementEventTestBase evt;

  /**
   * constructor
   */
  public WikiManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.but = new Button(testBase);
    this.plf = new PlatformBase(testBase);
  }

  /**
   * Select template to create page
   */
  public void selectTemplateWikiPage(String template) {
    info("--Select a template--");
    evt.waitForAndGetElement(ELEMENT_TEMPLATE_SELECT_FORM);
    By eTemplate = By.xpath(ELEMENT_SELECT_TEMPLATE_LINK.replace("${template}", template));
    info("eTemplate:" + eTemplate.toString());
    evt.click(eTemplate, 2, true);
  }

  /**
   * Change to Source Editor mode
   */
  public void goToSourceEditor() {
    $(ELEMENT_SOURCE_EDITOR_BUTTON).click();
    $(ELEMENT_CONTENT_WIKI_INPUT).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Change to Rich Text Mode
   */
  public void goToRichTextEditor() {
    if (ELEMENT_BUTTON_WIKI_RITCH_TEXT.waitUntil(Condition.appears, Configuration.timeout).is(Condition.exist)) {
      info("Go to Rich Text Mode");
      ELEMENT_BUTTON_WIKI_RITCH_TEXT.click();
    }
    $(ELEMENT_CONTENT_WIKI_FRAME).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Add a content for a wiki page
   *
   * @param content
   */
  public void addContentPage(String content) {
    info("Input a content for the page");
    if (!content.isEmpty())
      plf.inputFrame(ELEMENT_CONTENT_WIKI_FRAME, content);

  }

  /**
   * Save add page
   */
  public void saveAddPage() {
    info("Save all changes");
    ELEMENT_SAVE_BUTTON_ADD_PAGE.click();
    ELEMENT_SAVE_BUTTON_ADD_PAGE.waitUntil(Condition.disappears, Configuration.timeout);
    info("Wiki page simple is created successfully");
  }

  /**
   * Click on save add Page without verifying
   */
  public void savePage() {
    info("Save all changes");
    evt.click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
  }

  /**
   * Cancel add page
   */
  public void cancelAddPage() {
    info("Click on Cancel button");
    evt.click(ELEMENT_CANCEL_BUTTON_ADD_PAGE, 0, true);
  }

  /**
   * publish page
   */
  public void publishPageWhenEditPage() {
    info("check on publish checkbox");
    evt.check(ELEMENT_PUBLISH_ACTIVITY_CHECKBOX, 2);
  }

  /**
   * unpublish page
   */
  public void unPublishPageWhenEditPage() {
    evt.uncheck(ELEMENT_PUBLISH_ACTIVITY_CHECKBOX, 2);

  }

  /**
   * Go to Preview Page
   */
  public void goToPreviewPage() {
    evt.click(ELEMENT_PREVIEW_BUTTON);
    evt.waitForAndGetElement(ELEMENT_PREVIEW_SCREEN);
  }

  /**
   * Edit paragraph in a Wiki page
   *
   * @param paragraphTitle input paragraph title without space character. Can not
   *          be <code>null</code>.
   * @param paragraphContent input paragraph content with heading followed help
   *          tips. Can not be <code>null</code>.
   */
  public void editParagraph(String paragraphTitle, String paragraphContent) {
    info("-- Editing a paragraph... " + paragraphTitle);

    String ELEMENT_PARAGRAPH_ID = "H" + paragraphTitle;
    evt.mouseOver(By.id(ELEMENT_PARAGRAPH_ID), true);
    WebElement element = evt.waitForAndGetElement(By.xpath("//*[@data-original-title='Edit Section: " + paragraphTitle + "']"));
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].click();", element);

    testBase.getExoWebDriver().getWebDriver().navigate().refresh();

    evt.type(ELEMENT_CONTENT_WIKI_INPUT, paragraphContent, true);
    evt.switchToParentWindow();
  }

  /**
   * input a comment to new wiki page
   *
   * @param comment
   */
  public void addComment(String comment) {
    info("Input a comment to wiki page");
    evt.type(ELEMENT_WIKI_PAGE_INPUT_COMMENT, comment, true);
  }

  /**
   * Move page 1 to page 2
   *
   * @param page1
   * @param page2
   */
  public void movePage(String page1, String page2) {
    info("Open a wiki page 1");
    evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", page1), 2000, 0).click();
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    info("Click on Move page link");
    if (evt.waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null) {
      evt.mouseOverAndClick(ELEMENT_MOVE_PAGE);
    } else {
      evt.click(ELEMENT_MOVE_PAGE);
    }
    info("Move page popup is shown");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_MOVE_POPUP_NODE.replace("${name}", page2), 2000, 0).click();
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_MOVE_POPUP_SAVE, 2000, 0).click();
    evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", page1), 2000);
    info("The page 1 is moved to page 2");
  }

  /**
   * Move page 1 to page 2 when user does not have edit permission in destination
   *
   * @param page1
   * @param page2
   */
  public void movePageWhenUserDoesNotHavePerMissionInDestination(String page1, String page2, boolean destination) {
    info("Open a wiki page 1");
    evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", page1), 2000, 0).click();
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    if (destination) {
      info("Click on Move page link");
      if (evt.waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null) {
        evt.mouseOverAndClick(ELEMENT_MOVE_PAGE);
      } else {
        evt.click(ELEMENT_MOVE_PAGE);
      }
      info("Move page popup is shown. User can see the page");
      evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_MOVE_POPUP_NODE.replace("${name}", page2), 2000, 0).click();
      info("Click on Move button");
      evt.waitForAndGetElement(ELEMENT_MOVE_BTNMOVE, 2000, 0).click();
      info("A pop up appears");
      alert.verifyAlertMessage(ELEMENT_MESSAGE_USER_DOES_NOT_HAVE_EDIT_PERMMISSON);
    } else {
      info("");
      evt.waitForElementNotPresent(ELEMENT_MOVE_PAGE, testBase.getDefaultTimeout(), 0);
    }
  }

  /**
   * Move a page1 of destination 1 to a page 2 of destination 2
   *
   * @param page1
   * @param page2
   * @param locator
   */
  public void movePageDiffDestination(String page1, String page2, String locator, Boolean... checkLocation) {
    boolean check = (checkLocation.length > 0 ? checkLocation[0] : false);
    info("Open a wiki page 1");
    evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", page1), 2000, 0).click();
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    info("Click on Move page link");
    if (evt.waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null) {
      evt.mouseOverAndClick(ELEMENT_MOVE_PAGE);
    } else {
      evt.click(ELEMENT_MOVE_PAGE);
    }
    info("Move page popup is shown");
    info("Click on Drop down");
    evt.waitForAndGetElement(ELEMENT_MOVE_SPACESWITCHER, 2000, 0).click();
    info("Select a location");
    evt.click(ELEMENT_MOVE_PAGE_POPUP_DROP_DOWN_LOCATOR.replace("${locator}", locator));
    info("Select a page in the list");
    evt.waitForAndGetElement(ELEMENT_MOVE_PAGE_TREE_SELECTED_PAGE.replace("$page", page2), 2000, 0).click();
    info("Save all changes");
    if (check) {
      info("Check New Location Home");
      evt.waitForAndGetElement(ELEMENT_MOVE_PAGE_POPUP_NEW_LOCATION_HOME.replace("${spaceName}", locator), 5000);
    }
    evt.waitForAndGetElement(ELEMENT_MOVE_BTNMOVE, 2000, 0).click();
  }

  /**
   * Open space switcher in move page popup
   */
  public void goToSpaceSwitcher() {
    info("Click on Drop down");
    evt.click(ELEMENT_MOVE_SPACESWITCHER);

  }

  /**
   * Select a space destination in move page popup
   *
   * @param destination
   */
  public void selectSpaceDestination(String destination) {
    info("Select a space destination in the list");
    evt.click(ELEMENT_MOVE_PAGE_POPUP_DROP_DOWN_LOCATOR.replace("${locator}", destination));
    evt.waitForAndGetElement(ELEMENT_MOVE_PAGE_SPACE_SWITCHER_DROP_DOWN_VALUE_SELECTED.replace("$destination", destination));
  }

  /**
   * Delete an attachment file
   */
  public void deleteAttachmentFile() {
    info("Click on detele button");
    evt.click(ELEMENT_PAGE_DELETEATTACHFILE);
    info("Verify that the file is removed");
    evt.waitForElementNotPresent(ELEMENT_PAGE_DOWNLOADATTACHFILE);
  }

  /**
   * Check options in Add Relations drop down list
   *
   * @param spaces
   */
  public void checkAddRelationDropDownList(String spaces) {
    info("Click on Drop down");
    $(ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN).click();
    info("Verify that Intranet location is shown is the list");
    evt.waitForAndGetElement(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}", "Intranet"), 2000, 0);
    info("Verify that My wiki location is shown is the list");
    evt.waitForAndGetElement(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}", "My Wiki"), 2000, 0);
    if (!spaces.isEmpty()) {
      String[] arraySpace = spaces.split("/");
      for (String space : arraySpace) {
        info("Verify that " + space + " location is shown is the list");
        evt.waitForAndGetElement(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}", space), 2000, 0);
      }

    }
    evt.waitForAndGetElement(ELEMENT_SPACE_SWITHCHER_DROPDOWN_CLOSE, 2000, 0).click();
    info("All options are checked");
  }

  /**
   * Preview a template
   *
   * @param template
   */
  public void previewATemplate(String template) {
    info("Preview the template");
    evt.click(ELEMENT_TEMPLATE_PREVIEW_BTN.replace("${template}", template));
    info("Verify that the layout is shown");
    evt.waitForAndGetElement(ELEMENT_PREVIEW_TEMPLATE_CONTENT.replace("${template}", template), 2000, 0);
    evt.click(ELEMENT_TEMPLATE_PREVIEW_PAGE_CLOSE_BTN);

    evt.click(ELEMENT_TEMPLATE_CANCEL_BTN);

  }

  /**
   * Rename the title of the page by double-click on the title field
   *
   * @param title
   * @param newTitle
   */
  public void renamePageByDoubleClick(String title, String newTitle) {
    info("Open the page");
    Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    action.doubleClick(evt.waitForAndGetElement(ELEMENT_PAGE_TITLE.replace("${title}", title), 2000, 0)).perform();
    evt.type(ELEMENT_WIKI_PAGE_TITLE_RENAME_FIELD, newTitle, true);
    Actions actionEnter = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    actionEnter.sendKeys(Keys.ENTER).perform();
    actionEnter.release();

  }

  /**
   * Watch a page
   */
  public void watchAPage(String mess) {
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    info("Click on watch link");
    evt.click(ELEMENT_WATCH_LINK);
    info("Show message :'You started watching this page now.'");
    evt.waitForAndGetElement(ELEMENT_POPUP_MESSAGE_CONTENT.replace("${message}", mess), 2000, 0);
    evt.click(ELEMENT_BTN_OK);

  }

  /**
   * un-Watch a page
   */
  public void unWatchAPage(String mess) {
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    info("Click on watch link");
    evt.click(ELEMENT_UNWATCH_LINK);
    info("Show message : 'You stopped watching this page now.'");
    evt.waitForAndGetElement(ELEMENT_POPUP_MESSAGE_CONTENT.replace("${message}", mess), 2000, 0);
    evt.click(ELEMENT_BTN_OK);

  }

  /**
   * function: check content of mail then delete mail
   *
   * @param title title of the page
   */
  public void checkEmailNotification(String title) {
    info("Check and delete mail");

    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
    }
    evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_WIKI.replace("${title}", title), 30000, 0);
    info("Found notify mail");

    info("ELEMENT_GMAIL_CONTENT:" + ELEMENT_GMAIL_CONTENT_WIKI.replace("${title}", title));
    info("Open email");
    evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_WIKI.replace("${title}", title)).click();
    String defaultLink = testBase.getExoWebDriver().getBaseUrl() + "/intranet/wiki/" + title;
    // Store childs and parent windows
    Object[] allWindows = testBase.getExoWebDriver().getWebDriver().getWindowHandles().toArray();
    // Get parent window
    String paWindow = allWindows[0].toString();
    // Get child window 1. Here is gmail browser
    String chilwindow1 = allWindows[1].toString();
    // Get child window 2. Here is last child window when click on subtitle of
    // email notification
    String chilwindow2 = allWindows[2].toString();
    // Focus on Child window2
    testBase.getExoWebDriver().getWebDriver().switchTo().window(chilwindow2);
    info("Verify that the link is shown as correct format:");
    evt.click(ELEMENT_GMAIL_PREVIOUS_EMAIL);
    info("Check the link's format");
    String link = evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT_LINK_WIKI.replace("${page}", title), 3000, 0)
                     .getAttribute("href")
                     .toString();
    // close child window 2
    testBase.getExoWebDriver().getWebDriver().close();
    // Focus on child window 1
    testBase.getExoWebDriver().getWebDriver().switchTo().window(chilwindow1);
    // close child window 1
    testBase.getExoWebDriver().getWebDriver().close();
    // Focus on parent window
    testBase.getExoWebDriver().getWebDriver().switchTo().window(paWindow);

    info("link:" + link);
    info("default:" + defaultLink);
    if (link.contentEquals(defaultLink) == true)
      assert true;
    else
      assert false : "the link's format is incorrect";

  }

  /**
   * Un check view permission for a user or a group
   *
   * @param locator
   */
  public void unCheckViewAUserOfPage(Object locator) {
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    info("Click on permission link");
    evt.click(ELEMENT_PERMISSION_LINK);
    info("Uncheck view permission checkbox");
    evt.uncheck(locator, 2);
    info("Click on save button");
    evt.click(ELEMENT_PERMISSION_BUTTON_SAVE);
  }

  /**
   * Rename a page from alert message when move a page to a destination that has
   * same name
   */
  public void renameFromAlertMessageOfOnePage() {
    info("Click on Rename link on the alert message area");
    evt.click(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME);

  }

  public void renameFromAlertMessageOfManyPages(String mess, String page) {
    info("Click on Rename link of the page");
    evt.click(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_LINK.replace("$message", mess).replace("$page", page));

  }

  /**
   * Click on inserted link in the wiki page
   *
   * @param label
   */
  public void viewInsertLink(String label) {
    info("Verify that the inserted link is shown in the page");
    evt.waitForAndGetElement(By.linkText(label));
    info("Click on the label");
    evt.click(By.linkText(label));
  }

  /**
   * Click on View Changes link on the Status when editting a wiki
   */
  public void goToViewChangesLinkOnStatus() {
    info("Click on View Changes link on the status");
    evt.click(ELEMENT_WIKI_STATUS_VERSION_VIEW_CHANGES_LINK);

  }

  /**
   * Click on Continue link on the Status when editting a wiki
   */
  public void goToContinueEdittingLinkOnStatus() {
    info("Click on Continue Editting link on the status");
    evt.click(ELEMENT_WIKI_STATUS_VERSION_CONTINUE_EDITTING_LINK);
    info("The status is not shown and Edit page is shown");
    evt.waitForElementNotPresent(ELEMENT_WIKI_STATUS_VERSION_CONTINUE_EDITTING_LINK);
    evt.waitForAndGetElement(ELEMENT_TITLE_WIKI_INPUT);
  }

  /**
   * Click on Delete on The status when editing a wiki
   */
  public void goToDeleteLinkOnStatus() {
    info("Click on Continue Editting link on the status");
    evt.click(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK);
    info("The status is not shown and Edit page is shown");
    evt.waitForElementNotPresent(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK);
  }

  /**
   * Click on Resume the Draf link on the Status when editting a page
   */
  public void goToResumDrafLinkOnStatus() {
    info("Click on Resume the Draf link");
    evt.click(ELEMENT_WIKI_STATUS_RESUME_THE_DRAF_LINK);

  }

  /**
   * Add a simple wiki page by template format
   *
   * @param template
   */
  public void addSimpleWikiPageByTemplate(String template, String newTitle) {
    info("Select a template");
    selectTemplateWikiPage(template);
    evt.click(ELEMENT_TEMPLATE_SELECT_BTN);
    if (!newTitle.isEmpty())
      evt.type(ELEMENT_TITLE_WIKI_INPUT, newTitle, true);

    info("Save all changes");
    saveAddPage();
  }

  /**
   * Add a simple wiki page with template with auto save status
   *
   * @param template
   */
  public void addSimplePageByTemplateWithAutoSave(String template, String newTitle) {
    info("Select a template");
    selectTemplateWikiPage(template);
    evt.click(ELEMENT_TEMPLATE_SELECT_BTN);

    if (!newTitle.isEmpty())
      evt.type(ELEMENT_TITLE_WIKI_INPUT, newTitle, true);
    info("Waiting 30s before saved all changes");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT, 31000, 0);
    info("Save all changes");
    saveAddPage();
  }

  /**
   * Preview a simple page
   *
   * @param title
   * @param content
   */
  public void PreviewASimplePage(String title, String content) {
    info("Preview a simple page");
    goToPreviewPage();
    evt.waitForAndGetElement(ELEMENT_PREVIEW_TEMPLATE_CONTENT.replace("${template}", title), testBase.getDefaultTimeout(), 1);
    evt.waitForAndGetElement(ELEMENT_PREVIEW_PAGE_CONTENT.replace("${content}", content), testBase.getDefaultTimeout(), 1);
  }
}
