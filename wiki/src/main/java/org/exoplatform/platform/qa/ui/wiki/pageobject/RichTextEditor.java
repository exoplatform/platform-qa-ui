package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class RichTextEditor {
  private final TestBase       testBase;

  public Button                but;

  public PlatformBase          plf;

  WikiManagement               wikiManagement;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */

  public RichTextEditor(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.but = new Button(testBase);
    this.plf = new PlatformBase(testBase);
    this.wikiManagement = new WikiManagement(testBase);
  }

  /**
   * Open Webpage link
   */
  public void goToWebPageLink() {
    info("Click on Link menu");
    $(ELEMENT_LINK).click();
    info("Click on Web Page Link menu");
    $(ELEMENT_WEB_PAGE_LINK_MENU).click();
  }

  /**
   * Open Edit link popup
   */
  public void goToEditLink() {
    info("Click on Link menu");
    $(ELEMENT_LINK).hover().click();
    info("Click on Edit Link menu");
    $(ELEMENT_EDIT_LINK_MENU).hover().click();
  }

  /**
   * Add a simple wiki page with rich text
   *
   * @param title updated title of the wiki page. Can not be <code>null</code>
   * @param content updated content of the wiki page. Can not be <code>null</code>
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
   $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.appears, Configuration.timeout);
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).is(Condition.not(Condition.exist))
        && (ELEMENT_BUTTON_WIKI_RITCH_TEXT.is(Condition.exist))) {
      ELEMENT_BUTTON_WIKI_RITCH_TEXT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    }
    if (!title.isEmpty()) {
      $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(title);
    }
    info("Input a content for the page");
    if (!content.isEmpty()) {
      SelenideElement frame=$(byClassName("gwt-RichTextArea")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
      $(byClassName("gwt-RichTextArea")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      switchTo().frame(frame);
      $(byId("body")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).sendKeys(content);
      switchTo().defaultContent();
    }
    }

  /**
   * Edit an attached file link
   *
   * @param fileName String
   * @param label String
   * @param tooltip String
   */
  public void editAttachedFileLink(String fileName, String label, String tooltip) {
    info("Go To Edit Link");
    goToEditLink();
    info("Select the image");
    $(byXpath(ELEMENT_CURRENT_TAB_ATTACHED_FILE_SELECTED.replace("$file",fileName))).click();
    info("click on Select button");
    $(ELEMENT_SELECT_BUTTON).click();
    info("Input Label for the page");
    inputLabel(label);
    info("Input Tooltip for the page");
    inputToolTip(tooltip);
    info("Click on Create link button");
    goToCreateLink();

  }

  /**
   * Open Attached File link
   */
  public void goToAttachedFileLink() {
    info("Click on Link menu");
    $(ELEMENT_LINK).hover().click();
    info("Click on Attached file Link menu");
    $(ELEMENT_ATTACHED_FILE_LINK_MENU).hover().click();
  }

  /**
   * Insert an attached file to the page
   *
   * @param attachedFile String
   * @param isPreEndKey Boolean
   */
  public void insertAttachedFileLink(String attachedFile, Boolean isPreEndKey) {
    info("Go to Attached file Link");
    goToAttachedFileLink();
    info("Input attached file link");
    uploadAttachedFile(attachedFile);
    info("Click on Create link button");
    goToCreateLink();
    if (isPreEndKey) {
      info("Move focus at the end of the line");
      evt.pressEndKey(this.testBase.getExoWebDriver().getWebDriver());
    }
  }

  /**
   * Open wiki page popup
   */
  public void goToWikiPageLink() {
    info("Click on Link menu");
    $(ELEMENT_LINK).hover().click();
    info("Click on Page Link menu");
    $(ELEMENT_WIKI_PAGE_LINK_MENU).hover().click();
  }

  /**
   * Input email address into EMail link popup
   *
   * @param address String
   */
  public void inputEmailAddress(String address) {
    if (!address.isEmpty()) {
      info("Input web address");
      $(ELEMENT_EMAIL_LINK_EMAIL_ADDRESS).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(address);
    }
  }

  /**
   * Insert email address into the page
   *
   * @param address String
   * @param label String
   * @param tooltip String
   * @param isPressEndKey Boolean
   */
  public void insertEmailLink(String address, String label, String tooltip, Boolean isPressEndKey) {
    info("Go to Email Link");
    goToEmailLink();
    info("Input Email for the page");
    inputEmailAddress(address);
    info("Input Label for the page");
    inputLabel(label);
    info("Input Tooltip for the page");
    inputToolTip(tooltip);
    info("Click on Create link button");
    goToCreateLink();
    if (isPressEndKey) {
      info("Move focus at the end of the line");
      evt.pressEndKey(this.testBase.getExoWebDriver().getWebDriver());
    }
  }

  /**
   * Insert a exist wiki page link into other page
   *
   * @param page String
   * @param label String
   * @param tooltip String
   * @param tab wikiPageLinkTab
   */
  public void insertExistWikiPageLink(String page, String label, String tooltip, wikiPageLinkTab tab) {
    switch (tab) {
    case My_Recent_Changes:
      info("Select My recent changes tab");
      goToMyRecentChangesTab();
      info("Select a page in the list");
      selectPageInMyRecentChangesTab(page);
      break;
    case All_pages:
      info("Select All pages tab");
      goToAllPagesTab();
      info("Expand WikiHome node");
      goToExplorerWikiHome();
      info("Select a page in the list");
      selectPageInAllPagesTab(page);
      break;
    case Search:
      info("Select Search tab and search the page");
      searchPage(page);
      info("Select a page");
      selectPageInSearchTab(page);
      break;
    }
    editWikiPageLink("", label,tooltip);

  }

  /**
   * Edit wiki page link
   *
   * @param label String
   * @param tooltip String
   */
  public void editWikiPageLink(String address, String label, String tooltip) {
    info("Input address of the link");
    inputEmailAddress(address);
    info("Input the label of the link");
    inputLabel(label);
    info("Input the tooltip of the link");
    inputToolTip(tooltip);
    info("Click on Create link button");
    goToCreateLink();
  }

    /**
     * Insert a new wiki page into other page
     *
     * @param page String
     * @param label String
     * @param tooltip String
     * @param tab wikiPageLinkTab
     * @param isPressEndKey Boolean
     */
  public void insertNewWikiPageLink(String page, String label, String tooltip, wikiPageLinkTab tab, Boolean isPressEndKey) {
    info("Open Wiki Page link popup");
    goToWikiPageLink();
    switch (tab) {
    case My_Recent_Changes:
      info("Select My recent changes tab");
      goToMyRecentChangesTab();
      info("Select a page in the list");
      addNewPageInMyRecentChangesTab(page);
      goToLinkSetting();
      break;
    case All_pages:
      info("Select All pages tab");
      goToAllPagesTab();
      info("Double click on Add New Page button");
      evt.doubleClickOnElement(ELEMENT_ALL_PAGES_TAB_ADD_NEW_PAGE_BTN);
      break;
    case Search:
      info("Open Seach tab");
      goToSearchTab();
      info("Create a new wiki page");
      addNewPageInSearchTab(page);
      goToLinkSetting();
      break;
    }
    info("Input the label of the link");
    inputLabel(label);
    info("Input the tooltip of the link");
    inputToolTip(tooltip);
    info("Click on Create link button");
    goToCreateLink();
    if (isPressEndKey) {
      info("Move focus at the end of the line");
      evt.pressEndKey(this.testBase.getExoWebDriver().getWebDriver());
    }
  }

  /**
   * Input web address
   *
   * @param address String
   */
  public void inputWebAddress(String address) {
    if (!address.isEmpty()) {
      info("Input web address");
      $(ELEMENT_WEB_PAGE_WEB_ADDRESS).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).val(address);
    }
  }

  /**
   * Open Edit Image link popup
   */
  public void goToEditImageLink() {
    info("Click on Link menu");
    $(ELEMENT_IMAGE_LINK).hover().click();
    info("Click on Edit image Link menu");
    $(ELEMENT_EDIT_IMAGE_LINK_MENU).hover().click();
  }

  /**
   * Insert a web link into the page
   *
   * @param address String
   * @param label String
   * @param tooltip String
   * @param isPressEndKey Boolean
   */
  public void insertWebLink(String address, String label, String tooltip, Boolean isPressEndKey) {
    info("Input web address for the page");
    inputWebAddress(address);
    info("Input Label for the page");
    inputLabel(label);
    info("Input Tooltip for the page");
    inputToolTip(tooltip);
    info("Click on Create link button");
    goToCreateLink();
    if (isPressEndKey) {
      info("Move focus at the end of the line");
      evt.pressEndKey(this.testBase.getExoWebDriver().getWebDriver());
    }
  }

  /**
   * Edit a simple wiki page with rich editor
   *
   * @param newTitle updated title of the wiki page.
   * @param newContent updated content of the wiki page. Can not be
   */
  public void editSimplePage(String newTitle, String newContent) {
    if(!$(ELEMENT_TITLE_WIKI_INPUT).exists())
    {
      do {
        //refresh();
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
      }while (!$(ELEMENT_TITLE_WIKI_INPUT).exists());
    }
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).is(Condition.not(Condition.exist))
        && (ELEMENT_BUTTON_WIKI_RITCH_TEXT.is(Condition.exist))) {
      ELEMENT_BUTTON_WIKI_RITCH_TEXT.waitUntil(Condition.visible,Configuration.timeout).click();
    }
    info("Input a new title for the page");
    if (!newTitle.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.timeout).clear();
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.timeout).val(newTitle);
    info("Input a new content for the page");
    if (!newContent.isEmpty()) {
      SelenideElement frame=$(byClassName("gwt-RichTextArea")).waitUntil(Condition.visible,Configuration.timeout);
      switchTo().frame(frame);
      $(byId("body")).waitUntil(Condition.visible,Configuration.timeout).clear();
      $(byId("body")).waitUntil(Condition.visible,Configuration.timeout).sendKeys(newContent);
      switchTo().defaultContent();
    }
  }

  /**
   * Open Email link popup
   */
  public void goToEmailLink() {
    info("Click on Link menu");
    $(ELEMENT_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).doubleClick();
    info("Click on Attached file Link menu");
    $(ELEMENT_EMAIL_LINK_MENU).shouldBe(Condition.visible);
    $(ELEMENT_EMAIL_LINK_MENU).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).doubleClick();
  }

  /**
   * Input a tooltip
   *
   * @param tooltip String
   */
  public void inputToolTip(String tooltip) {
    if (tooltip != null && tooltip != "") {
      $(ELEMENT_TOOLTIP_LINK_TEXTBOX).val(tooltip);
    }
  }

  /**
   * Input a label
   *
   * @param label String
   */
  public void inputLabel(String label) {
    if (label != null && label != "") {
      $(ELEMENT_LABEL_LINK_TEXTBOX).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).val(label);
    }
  }

  /**
   * Open External Image link popup
   */
  public void goToExternalImageLink() {
    info("Click on Link menu");
    $(ELEMENT_IMAGE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Click on Attached file Link menu");
    $(ELEMENT_EXTERNAL_IMAGE_LINK_MENU).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Input an external image into the content of the page
   *
   * @param link String
   * @param width String
   * @param height String
   * @param altText String
   */
  public void insertExternalImageLink(String link, String width, String height, String altText) {
    info("Go to External Image Link");
    goToExternalImageLink();
    info("Input a link");
    inputExternalImageLink(link);
    info("Click on Image Settings button");
    goToImageSettings();
    if (!width.isEmpty()) {
      info("Input width");
      $(ELEMENT_IMAGE_WIDTH).waitUntil(Condition.visible,Configuration.timeout).setValue(width);
    }
    if (!height.isEmpty()) {
      info("Input height");
      $(ELEMENT_IMAGE_HEIGHT).waitUntil(Condition.visible,Configuration.timeout).setValue(height);
    }
    if (!altText.isEmpty()) {
      info("Change alt text");
      $(ELEMENT_IMAGE_ALTERNATIVE_TEXT).waitUntil(Condition.visible,Configuration.timeout).setValue(altText);
    }
  }

  /**
   * Input an external image link
   *
   * @param link String
   */
  public void inputExternalImageLink(String link) {
    if (!link.isEmpty()) {
      info("Input external Image link");
      $(ELEMENT_EXTERNAL_IMAGE_INPUT_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs + Configuration.timeout).setValue(link);
    }
  }

  /**
   * Open Attached Image link popup
   */
  public void goToAttachedImageLink() {
    info("Click on Link menu");
    $(ELEMENT_IMAGE_LINK).hover().click();
    info("Click on Attached file Link menu");
    $(ELEMENT_ATTACHED_IMAGE_LINK_MENU).hover().click();
  }

  /**
   * Insert an image into the content of the page
   *
   * @param attachedFile String
   * @param width String
   * @param height String
   * @param altText String
   */
  public void insertImage(String attachedFile, String width, String height, String altText) {
    info("Open Current page tab");
    goToCurrentPageTab();
    info("Input attached file link");
    uploadImageFile(attachedFile);
    info("Click on Image Setting button");
    goToImageSettings();
    if (!width.isEmpty()) {
      info("Input width");
      $(ELEMENT_IMAGE_WIDTH).val(width);
    }
    if (!height.isEmpty()) {
      info("Input height");
      $(ELEMENT_IMAGE_HEIGHT).val(height);
    }
    if (!altText.isEmpty()) {
      info("Change alt text");
      $(ELEMENT_IMAGE_ALTERNATIVE_TEXT).val(altText);
    }

  }

  /**
   * Insert an image into the content of the page
   *
   * @param attachedFile String
   * @param isPressEndKey  Boolean
   */
  public void insertImage(String attachedFile, Boolean isPressEndKey) {
    info("Open Current page tab");
    goToCurrentPageTab();
    info("Input attached file link");
    uploadImageFile(attachedFile);
    info("Click on Insert Image button");
    goToInsertImage();
    if (isPressEndKey) {
      info("Move focus at the end of the line");
      evt.pressEndKey(this.testBase.getExoWebDriver().getWebDriver());
    }
  }

  /**
   * Delete an image in wiki page
   *
   * @param content String
   */
  public void removeImage(String content) {
    info("Click on Image link");
    $(ELEMENT_IMAGE_LINK).hover().click();
    info("Click on Remove Image link");
    $(ELEMENT_REMOVE_IMAGE_LINK_MENU).hover().click();
    info("Switch to the frame");
    testBase.getExoWebDriver().getWebDriver().switchTo().frame(evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
    info("Verify that the image is removed");
    $(byXpath(ELEMENT_CHECK_IMAGE.replace("${file}", content))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    info("Switch to the parent");
    evt.switchToParentWindow();
    info("click on Image link again");
    $(ELEMENT_IMAGE_LINK).hover().click();
    info("Verify that Remove Image link is not shown");
    $(ELEMENT_REMOVE_IMAGE_LINK_MENU).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Select algin of Image
   *
   * @param type alignType
   */
  public void selectAlign(alignType type) {
    switch (type) {
    case None:
      break;
    case Left:
      info("Select left align");
      $(ELEMENT_IMAGE_ALIGN_LEFT).click();
      break;
    case Center:
      info("Select center align");
      $(ELEMENT_IMAGE_ALIGN_CENTER).click();
      break;
    case Right:
      info("Select right align");
      $(ELEMENT_IMAGE_ALIGN_RIGHT).click();
      break;
    case Top:
      info("Select top align");
      $(ELEMENT_IMAGE_ALIGN_TOP).click();
      break;
    case Middle:
      info("Select middle align");
      $(ELEMENT_IMAGE_ALIGN_MIDDLE).click();
      break;
    case Bottom:
      info("Select bottom align");
      $(ELEMENT_IMAGE_ALIGN_BOTTOM).click();
      break;
    }
  }

  /**
   * Remove a link in wiki page
   *
   * @param content alignType
   */
  public void removeLink(String content) {
    info("Click on link");
    $(ELEMENT_LINK).click();
    info("Click on Remove link");
    $(ELEMENT_REMOVE_LINK_MENU).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_REMOVE_LINK_MENU).click();
    info("Switch to the frame");
    switchTo().frame($(ELEMENT_CONTENT_WIKI_FRAME).waitUntil(Condition.visible,Configuration.timeout));;
    info("Verify that the link is removed");
    $(byLinkText(content)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    info("Switch to the parent");
    evt.switchToParentWindow();
    info("click on link again");
    $(ELEMENT_LINK).hover().click();
    info("Verify that Remove link is not shown");
    $(ELEMENT_REMOVE_LINK_MENU).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Input a name for a new wiki page link
   *
   * @param page alignType
   */
  public void inputNameWikiPageLink(String page) {
    info("Input the name of the page");
    $(ELEMENT_INPUT_NAME_NEW_WIKI_PAGE).val(page);
  }

  /**
   * Click on Create link button on Wiki page popup
   */
  public void goToCreateLink() {
    $(ELEMENT_CREATE_LINK_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CREATE_LINK_BUTTON).waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);
  }

  /**
   * Open My Recent Changes tab
   */
  public void goToMyRecentChangesTab() {
    info("Click on My Recent Changes Tab");
    evt.click(ELEMENT_MY_RECENT_CHANGES_TAB);
  }

  /**
   * Add a new wiki page in My Recent changes tab
   *
   * @param page alignType
   */
  public void addNewPageInMyRecentChangesTab(String page) {
    info("Double click on Add New Page button");
    evt.doubleClickOnElement(ELEMENT_MY_RECENT_CHANGES_TAB_ADD_NEW_PAGE_BTN);
    info("Input the name of the page");
    inputNameWikiPageLink(page);
  }

  /**
   * Add a new wiki page in Search tab
   *
   * @param page alignType
   */
  public void addNewPageInSearchTab(String page) {
    info("Double click on Add New Page button");
    evt.doubleClickOnElement(ELEMENT_SEARCH_TAB_ADD_NEW_PAGE_BTN);
    info("Input the name of the page");
    inputNameWikiPageLink(page);
  }

  /**
   * Upload an attached file link in Attached File link popup
   *
   * @param link alignType
   */
  public void uploadAttachedFile(String link) {
    info("Double Click on Upload New file button");
    $(ELEMENT_CURRENT_PAGE_TAB_UPLOAD_NEW_FILE_BTN).doubleClick();
    $(byClassName("gwt-FileUpload")).uploadFromClasspath(link);
  }

  /**
   * Click on Link Setting button
   */
  public void goToLinkSetting() {
    info("Click on Link Setting button");
    evt.click(ELEMENT_WIKI_PAGE_LINK_LINK_SETTING_BTN);
  }

  /**
   * Open All Pages Tab
   */
  public void goToAllPagesTab() {
    info("Click on All Pages tab");
    $(ELEMENT_ALL_PAGE_TAB).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Open Search tab in Wiki page link popup
   */
  public void goToSearchTab() {
    info("Select Search tab");
    evt.click(ELEMENT_SEARCH_TAB);
    evt.waitForAndGetElement(ELEMENT_SEARCH_BUTTON);
    info("Search tab's content is shown");
  }

  /**
   * Search a page in Wiki page popup
   *
   * @param page alignType
   */
  public void searchPage(String page) {
    goToSearchTab();
    info("Input the page:" + page);
    $(ELEMENT_SEARCH_TEXTBOX_POPUP).val(page);
    info("Search the page");
    evt.click(ELEMENT_SEARCH_BUTTON);
  }

  /**
   * Open Current page tab in Attached file link popup
   */
  public void goToCurrentPageTab() {
    info("Click on Current Page Tab");
    evt.click(ELEMENT_CURRENT_PAGE_TAB);
  }

  /**
   * Upload an image file in Attached Image link popup
   *
   * @param link alignType
   */
  public void uploadImageFile(String link) {
    info("Double Click on Upload New file button");
    $(ELEMENT_CURRENT_PAGE_TAB_UPLOAD_IMAGE_BTN).doubleClick();
    $(byClassName("gwt-FileUpload")).uploadFromClasspath(link);
  }

  /**
   * Click on Image Settings button on Attached Image link popup
   */
  public void goToImageSettings() {
    info("Click on Insert Image");
    $(ELEMENT_IMAGE_MENU_IMAGE_SETTINGS_BTN).click();
    $(ELEMENT_IMAGE_MENU_IMAGE_SETTINGS_BTN).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * Expand Wiki Home node in All pages tab
   */
  public void goToExplorerWikiHome() {
    info("click on Wiki Home note");
    $(byClassName("gwt-TabPanel")).find(byText("intranet")).waitUntil(Condition.visible,Configuration.timeout);
    if($(ELEMENT_EXPLORER_WIKIHOME).is(Condition.not(Condition.visible))){
      info("click on Wiki Home note");
      $(byId("isc_1open_icon_0")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    }
  }

  /**
   * Click on Insert Image button
   */
  public void goToInsertImage() {
    info("Click on Insert Image");
   $(ELEMENT_IMAGE_MENU_INSERT_IMAGE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_IMAGE_MENU_INSERT_IMAGE_BTN).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Add a page with checking auto save after 30s
   *
   * @param title alignType
   * @param content alignType
   */
  public void addSimplePageWithAutoSaveStatus(String title, String content) {
    info("Input a title for the page");
    if(!$(ELEMENT_TITLE_WIKI_INPUT).exists())
    {
      do {
        //refresh();
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
      }while (!$(ELEMENT_TITLE_WIKI_INPUT).exists());
    }
    if (!title.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(title);
    info("Waiting 30s before saved all changes");
    $(ELEMENT_DRAFT_NOTIFY).waitUntil(Condition.appears, 41000, 1);
    info("Save all changes");

  }

  /**
   * Select a page from All pages tab
   *
   * @param page String
   */
  public void selectPageInAllPagesTab(String page) {
    info("Select the page");
    goToAllPagesTab();
    $(byClassName("gwt-TabPanel")).find(byText("intranet")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    if (!$(byClassName("gwt-TabPanel")).find(byText(page)).exists()){
      $(ELEMENT_EXPLORER_WIKIHOME).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    }
    ELEMENT_WIKI_UNPUT_LINK_EXISTED_PAGE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_WIKI_UNPUT_LINK_EXISTED_PAGE.setValue("intranet." + page);
    ELEMENT_POPUP_SELECT_WIKI_PAGE.find(byText(page)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Click on Select button");
    $(ELEMENT_SELECT_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  }

  /**
   * Check auto save essage
   * @param title String
   * @param content String
   */

  public void checkAutoSaveMessage(String title, String content) {
    info("Input a title for the page");
    if (!title.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).val(title);
    info("Input a content for the page");
    if (!content.isEmpty()) {
      switchTo().frame(0);
      $(byId("body")).sendKeys(content);
      switchTo().defaultContent();
    }
    info("Waiting 30s before saved all changes");
    $(ELEMENT_DRAFT_NOTIFY).waitUntil(Condition.appears, 31000, 1);
    info("Save all changes");
    info("Cancel adding page");
    $(ELEMENT_CANCEL_BUTTON_ADD_PAGE).click();
    $(ELEMENT_CONFIRMATION_POPUP_YES_BTN).click();
  }

  /**
   * Select a page in Search Tab
   *
   * @param page String
   */
  public void selectPageInSearchTab(String page) {
    if (evt.waitForAndGetElement(ELEMENT_SEARCH_TAB_PAGE_SELECTED.replace("${page}", page), 5000, 0) != null) {
      info("Select the page");
      evt.click(ELEMENT_SEARCH_TAB_PAGE_SELECTED.replace("${page}", page));
      info("Click on Select button");
      evt.click(ELEMENT_SELECT_BUTTON);
    }

  }

  /**
   * Select a page in My Recent Changes list
   *
   * @param page String
   */
  public void selectPageInMyRecentChangesTab(String page) {
    WebElement el = evt.waitForAndGetElement(ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED.replace("$title", page), 5000, 1, 2);
    evt.scrollToElement(el, this.testBase.getExoWebDriver().getWebDriver());
    if (evt.waitForAndGetElement(ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED.replace("$title", page), 5000, 0) != null) {
      info("Select the page");
      evt.click(ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED.replace("$title", page));
      info("Click on Select button");
      evt.click(ELEMENT_SELECT_BUTTON);
    }
  }

  /**
   * Edit a wiki page with auto save status
   *
   * @param newTitle String
   * @param newContent String
   */
  public void editSimplePageWithAutoSave(String newTitle, String newContent) {
    info("Input a new title for the page");
    if (!newTitle.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).val(newTitle);
    info("Input a new content for the page");
    if (!newContent.isEmpty()) {
      SelenideElement frame=$(byClassName("gwt-RichTextArea")).waitUntil(Condition.visible,Configuration.timeout);
      $(byClassName("gwt-RichTextArea")).click();
      switchTo().frame(frame);
      $(byId("body")).sendKeys(newContent);
      switchTo().defaultContent();
    }
    info("Waiting 30s before saved all changes");
    $(ELEMENT_DRAFT_NOTIFY).waitUntil(Condition.appears, 31000, 1);

  }

  /**
   * Add a new page that has auto save without save
   *
   * @param title String
   * @param content String
   */
  public void addSimplePageHasAutoSaveWithoutSave(String title, String content) {
    info("Input a title for the page");
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    if (!title.isEmpty())
      $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs + Configuration.collectionsTimeout).val(title);
    info("Input a content for the page");
    if (!content.isEmpty()) {
      SelenideElement frame=$(byClassName("gwt-RichTextArea")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
      $(byClassName("gwt-RichTextArea")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      switchTo().frame(frame);
      $(byId("body")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).sendKeys(content);
      switchTo().defaultContent();
    }
    info("Waiting 30s before saved all changes");
    $(ELEMENT_DRAFT_NOTIFY).waitUntil(Condition.appears, 31000, 1);
    info("Save all changes");
    info("Cancel adding page");
    $(ELEMENT_CANCEL_BUTTON_ADD_PAGE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CONFIRMATION_POPUP_YES_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Replace a new link for old link that inserted into the page
   *
   * @param label String
   */
  public void changeLink(String label) {
    info("Focus on the frame");
    plf.switchFrame(ELEMENT_CONTENT_WIKI_FRAME);
    selectLabelLink(label);
    evt.switchToParentWindow();
  }

  /**
   * Select the image
   *
   * @param altTextImage String
   */
  public void selectImage(String altTextImage) {
    info("Focus on the frame");
    switchTo().frame($(byClassName("gwt-RichTextArea")));
    $($(byXpath(ELEMENT_WIKI_CONTENT_IMAGE_ALT.replace("$alt", altTextImage)))).dragAndDropTo($(byId("body")));
    switchTo().defaultContent();
  }

  /**
   * Select the label's link that is inserted into the page
   *
   * @param label String
   */
  public void selectLabelLink(String label) {
    info("Select a line text");
    WebElement element = testBase.getExoWebDriver().getWebDriver().findElement(By.linkText(label));
    selectItems(element);
  }

  /**
   * Select element by click and hold
   *
   * @param el WebElement
   */
  public void selectItems(WebElement el) {
    testBase.action = new Actions(testBase.getExoWebDriver().getWebDriver());
    testBase.action.moveToElement(el).clickAndHold().perform();
    testBase.action.release();
  }

  public enum alignType {
    None, Left, Center, Right, Top, Middle, Bottom;
  }

  public enum wikiPageLinkTab {
    My_Recent_Changes, All_pages, Search;
  }
}
