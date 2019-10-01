package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

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

  public HomePagePlatform homePagePlatform;

  private ElementEventTestBase evt;

  /**
   * constructor
   * @param testBase TestBase
   */
  public WikiManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.but = new Button(testBase);
    this.plf = new PlatformBase(testBase);
    this.homePagePlatform=new HomePagePlatform(testBase);
  }

  /**
   * Select template to create page
   * @param eTemplate SelenideElement
   */
  public void selectTemplateWikiPage(SelenideElement eTemplate) {
    info("--Select  template--");
    $(ELEMENT_TEMPLATE_SELECT_FORM).waitUntil(Condition.appears, Configuration.timeout);
    eTemplate.selectRadio(eTemplate.getValue());
  }

  /**
   * Change to Source Editor mode
   */
  public void goToSourceEditor() {

    homePagePlatform.refreshUntil($(ELEMENT_TITLE_WIKI_INPUT),Condition.visible,1000);
    if(!$(ELEMENT_TITLE_WIKI_INPUT).exists())
    {
      do {
        //refresh();
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
        sleep(2000);
      }while (!$(ELEMENT_TITLE_WIKI_INPUT).exists());
    }
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SOURCE_EDITOR_BUTTON).click();
      $(ELEMENT_CONTENT_WIKI_INPUT).waitUntil(Condition.appears, Configuration.timeout);
    }
  }

  /**
   * Change to Rich Text Mode
   */
  public void goToRichTextEditor() {
    if($(ELEMENT_SOURCE_EDITOR_BUTTON).is(Condition.visible)){
      info("Go to Rich Text Mode");
      $(ELEMENT_SOURCE_EDITOR_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
        $(ELEMENT_RICHTEXT_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    sleep(Configuration.timeout);
    $(ELEMENT_CONTENT_WIKI_FRAME).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Add a content for a wiki page
   *
   * @param content String
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
    sleep(Configuration.timeout);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    ELEMENT_SAVE_BUTTON_ADD_PAGE.waitUntil(Condition.visible,Configuration.timeout).click();
   $(ELEMENT_SAVE_BUTTON_ADD_PAGE).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    info("Wiki page simple is created successfully");

  }

  /**
   * Click on save add Page without verifying
   */
  public void savePage() {
    info("Save all changes");
    $(ELEMENT_SAVE_BUTTON_ADD_PAGE).click();
  }

  /**
   * Cancel add page
   */
  public void cancelAddPage() {
    info("Click on Cancel button");
    $(ELEMENT_CANCEL_BUTTON_ADD_PAGE).click();
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

    $(byText(paragraphTitle)).click();
    ELEMENT_WIKI_CONTENT_PAGE.find(byText(paragraphTitle)).hover().parent().find(ELEMENT_WIKI_PAGE_EDIT_PARAGRAPH_BTN).click();
    $(ELEMENT_CONTENT_WIKI_INPUT).setValue(paragraphContent);
  }

  /**
   * input a comment to new wiki page
   *
   * @param comment String
   */
  public void addComment(String comment) {
    info("Input a comment to wiki page");
    $(ELEMENT_WIKI_PAGE_INPUT_COMMENT).setValue(comment);
  }

  /**
   * Move page 1 to page 2
   *
   * @param page1 String
   * @param page2 String
   */
  public void movePage(String page1, String page2) {
    info("Open a wiki page 1");
    $(byText(page1)).waitUntil(Condition.appears, Configuration.timeout).click();

    info("Click on More link");
    $(ELEMENT_MORE_LINK).click();
    info("Click on Move page link");
    ELEMENT_MOVE_PAGE.hover().click();

    info("Move page popup is shown");
    $(byText(page2)).waitUntil(Condition.appears, Configuration.timeout);
    $(byId("UIMoveTree")).find(byText(page2)).click();
    $(ELEMENT_WIKI_PAGE_MOVE_POPUP_SAVE).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_WIKI_PAGE_MOVE_POPUP_SAVE).click();
    info("The page 1 is moved to page 2");
  }

  /**
   * Move page 1 to page 2 when user does not have edit permission in destination
   *
   * @param page1 String
   * @param page2 String
   * @param destination boolean
   */
  public void movePageWhenUserDoesNotHavePerMissionInDestination(String page1, String page2, boolean destination) {
    info("Open a wiki page 1");
    // evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", page1),
    // 2000, 0).click();
    info("Click on More link");
    $(ELEMENT_MORE).click();
    if (destination) {
      info("Click on Move page link");
      if ($(ELEMENT_MOVE_PAGE).waitUntil(Condition.visible,Configuration.timeout) == null) {
        $(ELEMENT_MOVE_PAGE).click();
      } else {
        $(ELEMENT_MOVE_PAGE).click();
      }
      info("Move page popup is shown. User can see the page");
      $(byXpath(ELEMENT_WIKI_PAGE_MOVE_POPUP_NODE.replace("${name}", page2))).waitUntil(Condition.visible, Configuration.timeout).click();
      info("Click on Move button");
      $(ELEMENT_MOVE_BTNMOVE).waitUntil(Condition.visible,Configuration.timeout).click();
      info("A pop up appears");
      alert.verifyAlertMessage(ELEMENT_MESSAGE_USER_DOES_NOT_HAVE_EDIT_PERMMISSON);
    } else {
      info("");
     $(ELEMENT_MOVE_PAGE).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    }
  }

  /**
   * Move a page1 of destination 1 to a page 2 of destination 2
   *
   * @param page1 String
   * @param page2 String
   * @param locator Boolean
   * @param checkLocation boolean
   */
  public void movePageDiffDestination(String page1, String page2, String locator, Boolean... checkLocation) {
    boolean check = (checkLocation.length > 0 ? checkLocation[0] : false);
    info("Open a wiki page 1");

    $(byText(page1)).waitUntil(Condition.appears, Configuration.timeout).click();

    info("Click on More link");
    $(ELEMENT_MORE_LINK).click();
    info("Click on Move page link");
    ELEMENT_MOVE_PAGE.click();

    info("Move page popup is shown");
    info("Click on Drop down");
    $(ELEMENT_MOVE_SPACESWITCHER).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_MOVE_SPACESWITCHER).click();

    info("Select a location");

    info("Select a page in the list");

    $(byId("UIWikiMovePageForm")).find(byText(locator)).waitUntil(Condition.appears, Configuration.timeout).click();
    $(byClassName("sideBarContent")).find(byText(page2)).waitUntil(Condition.appears, Configuration.timeout).click();

    info("Save all changes");
    if (check) {
      info("Check New Location Home");
      $(byXpath(ELEMENT_MOVE_PAGE_POPUP_NEW_LOCATION_HOME.replace("${spaceName}", locator))).waitUntil(Condition.visible,Configuration.timeout);
    }
    $(ELEMENT_MOVE_BTNMOVE).waitUntil(Condition.visible,Configuration.timeout).click();
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
   * @param destination String
   */
  public void selectSpaceDestination(String destination) {
    info("Select a space destination in the list");
    info("Click on More link");
    $(ELEMENT_MORE_LINK).click();
    info("Click on Move page link");
    $(ELEMENT_MOVE_PAGE).hover().click();
    ELEMENT_SELECT_DESTINATION.click();
    ELEMENT_POPUP_SELECT_DESTINATION.find(byText(destination)).click();
    $(ELEMENT_WIKI_PAGE_MOVE_POPUP_SAVE).click();
  }

  /**
   * Delete an attachment file
   * @param file String
   */
  public void deleteAttachmentFile(String file) {
    info("Click on detele button");
    $(byText(file)).parent().parent().find(byClassName("uiIconDelete ")).click();
    info("Verify that the file is removed");
    $(ELEMENT_PAGE_DOWNLOADATTACHFILE).waitUntil(Condition.disappears, Configuration.timeout);
  }

  /**
   * Check options in Add Relations drop down list
   *
   * @param spaces String
   */
  public void checkAddRelationDropDownList(String spaces) {

    info("Click on Drop down");
    $(ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN).click();

    info("Verify that Intranet location is shown is the list");
    $(byId("UISpaceSwitcher_/portal/intranet")).should(Condition.exist);

    info("Verify that My wiki location is shown is the list");
    $(byId("UISpaceSwitcher_/user/root")).should(Condition.exist);

    if (!spaces.isEmpty()) {
      String[] arraySpace = spaces.split("/");
      for (String space : arraySpace) {
        info("Verify that " + space + " location is shown is the list");
        $(byClassName("spaceChooserPopup")).find(byText(space));

      }

    }
    $(byXpath("(//div[@class='uiAction']/button)[2]")).waitUntil(Condition.appears, Configuration.timeout).click();
    info("All options are checked");
  }

  /**
   * Preview a template
   *
   * @param template String
   */
  public void previewATemplate(String template) {
    info("Preview the template");
    $(ELEMENT_TEMPLATE_PREVIEW_BTN).click();
    info("Verify that the layout is shown");
    $(ELEMENT_PREVIEW_TEMPLATE_CONTENT).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_TEMPLATE_PREVIEW_PAGE_CLOSE_BTN).click();
    evt.click(ELEMENT_TEMPLATE_CANCEL_BTN);

  }

  /**
   * Rename the title of the page by double-click on the title field
   *
   * @param title String
   * @param newTitle String
   */
  public void renamePageByDoubleClick(String title, String newTitle) {
    info("Open the page");
    Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    $(byId("UIWikiPageControlArea")).find(byText(title)).doubleClick();
    $(ELEMENT_WIKI_PAGE_TITLE_RENAME_FIELD).setValue(newTitle);
    Actions actionEnter = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    actionEnter.sendKeys(Keys.ENTER).perform();
    actionEnter.release();

  }

  /**
   * Watch a page
   * @param mess String
   */
  public void watchAPage(String mess) {
    info("Click on More link");
    if ($(ELEMENT_MORE_LINK).is(Condition.exist)) {
      $(ELEMENT_MORE_LINK).click();
    } else {
      ELEMENT_MORE_LINK_WITH_USER.click();
    }
    info("Click on watch link");
    $(ELEMENT_WATCH_LINK).click();
    info("Show message :'You started watching this page now.'");
    $(byText(mess)).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_BTN_OK).click();

  }

  /**
   * un-Watch a page
   * @param mess String
   */
  public void unWatchAPage(String mess) {
    info("Click on More link");
    if ($(ELEMENT_MORE_LINK).is(Condition.exist)) {
      $(ELEMENT_MORE_LINK).click();
    } else {
      ELEMENT_MORE_LINK_WITH_USER.click();
    }
    info("Click on watch link");
    $(ELEMENT_UNWATCH_LINK).click();
    info("Show message : 'You stopped watching this page now.'");
    $(byText(mess)).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_BTN_OK).click();

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
   * @param locator object
   */
  public void unCheckViewAUserOfPage(By locator) {
    info("Click on More link");
    $(ELEMENT_MORE_LINK).click();
    info("Click on permission link");
    $(ELEMENT_PERMISSION_LINK).click();
    $(locator).parent().waitUntil(Condition.exist,Configuration.timeout);
    info("Uncheck view permission checkbox");
    if($(locator).is(Condition.checked))
      $(locator).parent().click();
    info("Click on save button");
    $(ELEMENT_PERMISSION_BUTTON_SAVE).click();
  }

  /**
   * Rename a page from alert message when move a page to a destination that has
   * same name
   */
  public void renameFromAlertMessageOfOnePage() {
    info("Click on Rename link on the alert message area");
    $(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME).click();

  }

  public void renameFromAlertMessageOfManyPages(String mess, String page) {
    info("Click on Rename link of the page");
    evt.click(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_LINK.replace("$message", mess).replace("$page", page));

  }

  /**
   * Click on inserted link in the wiki page
   *
   * @param label String
   */
  public void viewInsertLink(String label) {
    info("Verify that the inserted link is shown in the page");
    $(By.linkText(label)).waitUntil(Condition.visible,Configuration.timeout);
    info("Click on the label");
    $(By.linkText(label)).click();
  }

  /**
   * Click on View Changes link on the Status when editting a wiki
   */
  public void goToViewChangesLinkOnStatus() {
    info("Click on View Changes link on the status");
    $(ELEMENT_WIKI_STATUS_VERSION_VIEW_CHANGES_LINK).click();

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
    $(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK).click();
    info("The status is not shown and Edit page is shown");
    $(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Click on Resume the Draf link on the Status when editting a page
   */
  public void goToResumDrafLinkOnStatus() {
    info("Click on Resume the Draf link");
    $(ELEMENT_WIKI_STATUS_RESUME_THE_DRAF_LINK).click();

  }

  /**
   * Add a simple wiki page by template format
   *
   * @param template SelenideElement
   * @param newTitle String
   */
  public void addSimpleWikiPageByTemplate(SelenideElement template, String newTitle) {
    info("Select a template");
    sleep(Configuration.timeout);
    selectTemplateWikiPage(template);
    sleep(2000);
    $(ELEMENT_TEMPLATE_SELECT_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
    if(!$(ELEMENT_TITLE_WIKI_INPUT).exists())
    {
      do {
        //refresh();
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
        sleep(2000);
      }while (!$(ELEMENT_TITLE_WIKI_INPUT).exists());
    }
    if (!newTitle.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).setValue(newTitle);
    info("Save all changes");
    saveAddPage();
  }

  /**
   * Add a simple wiki page with How To Guide template with auto save status
   *
   * @param template SelenideElement
   * @param newTitle String
   */
  public void addSimplePageByTemplateWithAutoSave(SelenideElement template, String newTitle) {
    info("Select a template");
    selectTemplateWikiPage(template);
    $(ELEMENT_TEMPLATE_SELECT_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
    refresh();
    sleep(Configuration.collectionsTimeout);
    if (!newTitle.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).should(Condition.visible).setValue(newTitle);
    info("Waiting 30s before saved all changes");
    try {
      Thread.sleep(31000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    info("Save all changes");
    saveAddPage();
  }

  /**
   * Preview a simple page
   *
   * @param title String
   * @param content String
   */
  public void PreviewASimplePage(String title, String content) {
    info("Preview a simple page");
    goToPreviewPage();
    // evt.waitForAndGetElement(ELEMENT_PREVIEW_TEMPLATE_CONTENT.replace("${template}",
    // title), testBase.getDefaultTimeout(), 1);
    $(byXpath(ELEMENT_PREVIEW_PAGE_CONTENT.replace("${content}", content))).waitUntil(Condition.visible, Configuration.timeout);
  }
}
