package org.exoplatform.platform.qa.ui.ecms.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_FILEFORM_BLANK_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_SELECT_USER_ICON1;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;

public class SiteExplorerHome {
  private final TestBase       testBase;

  public ManageAlert           alert;

  public Button                button;

  public CreateNewDocument     CreNewDoc;

  public Dialog                dialog;

  public PlatformPermission    plfPerm;

  public ManageFileTestBase    MFTB;

  public PlatformBase          plf;

  private ElementEventTestBase evt;

  public SiteExplorerHome(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.CreNewDoc = new CreateNewDocument(testBase);
    this.button = new Button(testBase);
    this.dialog = new Dialog(testBase);
    this.plfPerm = new PlatformPermission(testBase);
    this.MFTB = new ManageFileTestBase(testBase);
    this.plf = new PlatformBase(testBase);
  }

  /**
   * Go to a folder by a path in SE Example: go to Site management drive--a
   * folder
   *
   * @param path String
   * @param drive String
   */
  public void goToPath(String path, String drive) {
    info("Go to selected Drive");

    $(byId("uiActionsBarContainer")).find(byId("driveAction")).click();

    $(byText(drive)).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(drive)).click();
    $(byId("uiActionsBarContainer")).find(byId("driveAction")).waitUntil(Condition.have(Condition.text(drive)),Configuration.timeout);
    if( $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).is(Condition.not(Condition.visible))){
      sleep(Configuration.timeout);
      $(byClassName("uiIconEcmsViewWeb")).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    sleep(Configuration.timeout);
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Go to folder");
    if (!path.isEmpty()) {
      String[] arrayPath = path.split("/");
      for (String arrayElement : arrayPath) {
        selectNode(arrayElement);
      }
    }
    $(byId("address")).waitUntil(Condition.hasValue("/"+path),Configuration.timeout);
    sleep(Configuration.timeout);
  }

  /**
   * Open Add a new folder popup
   */
  public void goToAddNewFolder() {
    info("Add a new folder");
    if ($(ELEMENT_ACTIONBAR_ADDFOLDER).is(Condition.not(Condition.visible))) {
      info("Click on More menu");
      $(ELEMENT_ACTIONBAR_MORE).click();
      $(ELEMENT_ACTIONBAR_ADDFOLDER).waitUntil(Condition.visible,Configuration.timeout);
      info("Click on New folder on Action Bar");
      $(ELEMENT_ACTIONBAR_ADDFOLDER).click();
    } else {
      info("Click on New folder on Action Bar");
      $(ELEMENT_ACTIONBAR_ADDFOLDER).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    info("Verify that Add folder popup is shown");
    $(ELEMENT_ADDFOLDERBOX).waitUntil(Condition.visible,Configuration.timeout);
    info("The folder is shown successfully");
  }

  /**
   * Create a new folder. Input the title and folder type
   *
   * @param title String
   * @param folderType String
   */
  public void createFolder(String title, String folderType) {
    info("Type a title:" + title + " for the folder");
    $(ELEMENT_ADDFOLDER_NAME).setValue(title);
    if (!folderType.isEmpty()) {
      info("Select folder type:" + folderType);
      $(ELEMENT_ADDFOLDER_FOLDERTYPE).selectOption(folderType);
    }
    info("Click on Create folder button");
    $(ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON).click();
    info("Verify that the folder is created");
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title))).waitUntil(Condition.visible,Configuration.timeout);
    info("The folder is created successfully");
  }


  /**
   * Go to New content page
   */
  public void goToAddNewContent() {

    info("Click on New Document on Action Bar");
    $(ELEMENT_ACTIONBAR_ADDDOCUMENT).waitUntil(Condition.appears, Configuration.collectionsTimeout).click();
    info("Verify that New content page is shown");
    sleep(Configuration.timeout);
    $(ELEMENT_ADDDOCUMENT_CHOICETYPE).waitUntil(Condition.appears, Configuration.timeout);
    info("New content page is shown successfully");
  }

  /**
   * Add Symlink for a node
   *
   * @param node String
   */
  public void addSymlink(String node) {
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node))).contextClick();
    $(ELEMENT_SITEEXPLORER_ACTION_ADDSYMLINK).click();

  }

  /**
   * Delete data by title
   *
   * @param title String
   * @param destination boolean
   */
  public void deleteData(String title, boolean... destination) {
    info("Click on File Explorer icon");
    // scroll de 50 pixel
    executeJavaScript("window.scrollBy(0,50);", "");
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(Configuration.collectionsTimeout);
    info("Right click on nodename");
    executeJavaScript("window.scrollBy(0,-250)", "");
    $(byXpath("//div[@id='UITreeExplorer']//i[@title='${title}']".replace("${title}",title))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CONTENT_LIST.find(byLinkText(title)).waitUntil(Condition.visible,Configuration.timeout).contextClick();
    executeJavaScript("window.scrollBy(0,100);", "");
    info("Click on Delete link");
    sleep(Configuration.timeout);
    $(ELEMENT_SITEEXPLORER_ACTION_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
    info("Click on Delete button on Confirm popup");
    $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    refresh();
    if(destination.equals(true))
    $(byText(title)).shouldNot(Condition.exist);
    info("Verify that the node is deleted");
    info("the node is deleted successfully");
    executeJavaScript("window.scrollBy(0,-550);", "");
  }



  /**
   * @param title String
   * @param destination String
   */
  public void copyPasteNode(String title, String destination) {
    $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title))).waitUntil(Condition.visible,Configuration.timeout).contextClick();
    $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination))).contextClick();
    $(ELEMENT_SITEEXPLORER_ACTION_PASTE).click();
    refresh();
    $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();

  }

  /**
   * Cut and paste node
   *
   * @param title String
   * @param destination String
   */
  public void cutPasteNode(String title, String destination) {
    sleep(2000);
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    sleep(3000);
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title))).waitUntil(Condition.visible,2000).contextClick();
    sleep(Configuration.timeout);
    executeJavaScript("window.scrollBy(0,250)", "");
    sleep(2000);
    $(ELEMENT_SITEEXPLORER_ACTION_CUT).click();
    sleep(2000);
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    sleep(2000);
    executeJavaScript("window.scrollBy(0,-1000)", "");
    sleep(2000);
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination))).waitUntil(Condition.visible,Configuration.timeout).contextClick();
    sleep(3000);
    $(ELEMENT_SITEEXPLORER_ACTION_PASTE).click();
    sleep(2000);
    refresh();
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
    sleep(3000);

  }

  /**
   * Upload a file
   *
   * @param link String
   * @param params object
   */
  public void uploadFile(String link, Object... params) {
    Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
    sleep(2000);
    $(byId("MultiUploadInputFiles")).uploadFromClasspath(link);
    sleep(Configuration.collectionsTimeout);
    refresh();
    info("verify:" + verify);
    if (verify) {
      String links[] = link.split("/");
      int length = links.length;

      evt.waitForAndGetElement(By.xpath("//*[contains(text(),'" + links[length - 1] + "')]"), 3000, 1);
    }

    info("Upload file successfully");

  }

  /**
   * Add tag to a Content
   *
   * @param tag String
   */
  public void addTag(String tag) {
    sleep(Configuration.timeout);
    info("Click on More menu");
    executeJavaScript("window.scrollBy(0,-400);", "");
    $(ELEMENT_ACTIONBAR_MORE).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Click on Tag link");
    $(ELEMENT_ACTIONBAR_TAG).click();
    info("Input name of tag");
    $(ELEMENT_TAG_FORM).setValue(tag);
    info("Click on Add button");
    $(ELEMENT_ADD_TAG_FORM).click();
    info("The tag is created successfully");
    info("Close the popup");
    $(ELEMENT_TAG_POPUP_CLOSE).click();
  }

  /**
   * Edit a Tag
   *
   * @param oldName String
   * @param newName String
   */
  public void editTag(String oldName, String newName) {
    info("Click on Tag Cloud tab of SE");
    $(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB).click();
    $(byId("UITagExplorer")).find(byText(oldName)).should(Condition.visible);
    info("Click on Edit button of Tag Cloud");
    $(ELEMENT_SIDEBAR_TAGCLOUD_EDIT).click();
    $(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE).waitUntil(Condition.visible,Configuration.timeout);
    info("Click on Edit button of the old tag");
    $(byXpath(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT.replace("${name}", oldName))).click();
    $(ELEMENT_TAG_POPUP_NAME_FIELD).waitUntil(Condition.visible,Configuration.timeout);
    info("Input new name of tag");

    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].setAttribute('value', '"
        + newName + "')", evt.waitForAndGetElement(ELEMENT_TAG_POPUP_NAME_FIELD));
    info("Save all changes");
    $(ELEMENT_TAG_POPUP_SAVE).click();
    info("Verify that the new name of tag is changed");
    $(byId("UITagExplorer")).find(byText(newName)).should(Condition.visible);
    info("the new name of tag is changed successfully");
    $(ELEMENT_TAGE_POPUP_CLOSE).click();
    info("The edit tag popup is closed");
  }

  /**
   * Delete a tag
   *
   * @param tag String
   */
  public void deleteTag(String tag) {
    info("Click on Tag Cloud tab of SE");
    $(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB).click();
    $(byId("UITagExplorer")).find(byText(tag)).should(Condition.visible);
    info("Click on Edit button of Tag Cloud");
    $(ELEMENT_SIDEBAR_TAGCLOUD_EDIT).click();
    $(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE).waitUntil(Condition.visible,Configuration.timeout);
    info("Click on Delete button of the old tag");
    $(byXpath(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE.replace("${name}", tag))).click();
    alert.acceptAlert();
    info("Verify that tag is delete");
    $(byId("UITagExplorer")).find(byText(tag)).should(Condition.not(Condition.visible));
    info("The tag is deleted successfully");
    info("Close the popup");
    $(ELEMENT_TAGE_POPUP_CLOSE).click();

  }

  /**
   * Edit a Document
   *
   * @param content String
   * @param newTitle String
   */
  public void editDocument(String newTitle, String content) {

    $(ELEMENT_ACTIONBAR_MORE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_ACTIONBAR_EDIT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    if ($(ELEMENT_FILE_FORM_TITLE).is(Condition.enabled)) {
      $(ELEMENT_FILE_FORM_TITLE).sendKeys(newTitle);
    }
    $(ELEMENT_FILEFORM_BLANK_CONTENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    switchTo().frame($(ELEMENT_FILEFORM_BLANK_CONTENT));
    $(byXpath("/html/body")).sendKeys(content);
    switchTo().defaultContent();
  }

  /**
   * Go to space
   */
  public void goToSpace(String spaceName) {
    $(ELEMENT_ACTIONBAR_SHOWDRIVES).click();
    $(byXpath(ELEMENT_SELECTDRIVE_SPECIFICDRIVE.replace("${spaceName}", spaceName))).click();
    $(ELEMENT_ACTIONBAR_SHOWDRIVES).waitUntil(Condition.have(Condition.text(spaceName)),Configuration.timeout);
  }

  /**
   * Open Setting drive page
   *
   * @param type enum
   * @param order enum
   */
  public void openSettingsDriver(selectDriverOption type, selectDriverOrder order) {
    $(ELEMENT_ACTIONBAR_SETTINGS).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Go to type " + type);
    switch (type) {
    case ALPHABETICAL:
      $(ELEMENT_DRIVERSETTINGS_SORTBY).selectOption("Alphabetical");
      break;

    case TYPE:
      evt.select(ELEMENT_DRIVERSETTINGS_SORTBY, "Type");
      break;

    case CREATEDDATE:
      evt.select(ELEMENT_DRIVERSETTINGS_SORTBY, "Created Date");
      break;

    case MODIFIEDDATE:
      $(ELEMENT_DRIVERSETTINGS_SORTBY).selectOption("Modified Date");
      break;
    }
    info("Go to type " + order);
    switch (order) {
    case ASCENDING:
      $(ELEMENT_DRIVERSETTINGS_ORDER).selectOption("Ascending");
      break;

    case DESCENDING:
      $(ELEMENT_DRIVERSETTINGS_ORDER).selectOption("Descending");
      break;
    }
    $(ELEMENT_DRIVERSETTINGS_SAVE).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Close Setting drive page
   *
   */
  public void closeSettingsDriver() {
    $(ELEMENT_ACTIONBAR_SETTINGS).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_DOCUMENT_SHARE_CLOSE_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Go to Permission popup
   */
  public void goToPermission() {
    info("Click on More link on Action bar");
    if($(ELEMENT_ACTIONBAR_PERMISSION).is(Condition.not(Condition.visible)))
    $(ELEMENT_ACTIONBAR_MORE).click();
    info("Click on Permission on Action bar");
    $(ELEMENT_ACTIONBAR_PERMISSION).click();
    info("Finished opening permission popup");
  }

  /**
   * Select a node by name
   *
   * @param nodeName String
   */
  public void selectNode(String nodeName) {
    info("Verify that nodeName:" + nodeName + " is shown");
    info("Click on the nodeName:" + nodeName);
    sleep(Configuration.timeout);
    ELEMENT_CONTENT_LIST.find(byText(nodeName)).shouldBe( Condition.visible);
    ELEMENT_CONTENT_LIST.find(byText(nodeName)).waitUntil(Condition.appears, Configuration.timeout).click();

    info("Finished selecting nodeName:" + nodeName);
  }

  /**
   * Go to Advanced Search page
   */
  public void goToAdvancedSearch() {
    evt.click(ELEMENT_SITEEXPLORER_LEFTBOX_SAVEDSEARCH);

    evt.click(ELEMENT_SITEEXPLORER_LEFTBOX_ADVANCEDSEARCH);
  }

  /**
   * Lock a Node
   *
   * @param name String
   */
  public void lockNode(String name) {
    info("lock node:" + name);
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", name))).waitUntil(Condition.visible,Configuration.collectionsTimeout).contextClick();
    $(ELEMENT_SITEEXPLORER_LIST_LOCK_NODE).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    $(byXpath(ELEMENT_SITEEXPLORER_LOCK_ICON.replace("$node", name))).waitUntil(Condition.visible,Configuration.collectionsTimeout);
  }

  /**
   * Unlock a Node
   *
   * @param name String
   */
  public void unlockNode(String name) {
    info("unlock node:" + name);
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", name))).waitUntil(Condition.visible,Configuration.timeout).contextClick();
    $(ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byXpath(ELEMENT_SITEEXPLORER_LOCK_ICON.replace("$node", name))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * By QuynhPT Open Add Relation popup
   */
  public void goToManageRelation() {
    $(ELEMENT_ACTIONBAR_RELATION).click();

  }

  /**
   * Add a Relation for many files
   *
   * @param nameContent String
   * @param path String
   */
  public void addRelation(String[] nameContent, String path) {
    for (String arrayElement : nameContent) {
      goToPathHasFiles(path);
      sleep(Configuration.timeout);
      $(byXpath(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_SELECT_CONTENT_RIGHT_TREE.replace("${nameContent}", arrayElement))).waitUntil(Condition.visible,Configuration.timeout).click();

    }
  }

  /**
   * Open Add Category popup By QuynhPT date 16/01/2015
   */
  public void goToAddCategory() {
    sleep(Configuration.timeout);
    $(ELEMENT_ACTIONBAR_CATEGORY).waitUntil(Condition.visible,Configuration.timeout).click();

  }

  /**
   * Close Add Category popup By QuynhPT date 16/01/2015
   */
  public void closeAddCategoryPopup() {
    evt.click(ELEMENT_ADD_CATEGORY_POPUP_CLOSED_BUTTON);

  }

  /**
   * Go to the path that include content files to create relation
   *
   * @param path String
   */
  public void goToPathHasFiles(String path) {
    // Open "Select Relation" tab
    $(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB).click();

    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      $(byXpath(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_NODE_LEFT_TREE.replace("${nameNode}", arrayElement))).click();
    }
  }

  /**
   * By QuynhPT Close "Add Relation" popup
   */
  public void closeAddRelationPopup() {
    $(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_CLOSE_BUTTON).click();

  }

  /**
   * By QuynhPT Open Relation tab on SideBar
   */
  public void goToRelationSideBar() {
    $(ELEMENT_SIDE_BAR_RELATION_ICON).click();

  }

  /**
   * Delete Relation By QuynhPT
   *
   * @param nameContent String
   */
  public void deleteRelation(String nameContent) {
    $(byXpath(ELEMENT_RELATION_POPUP_RELATION_LIST_DELETE_BUTTON.replace("${nameContent}", nameContent))).click();
    alert.waitForConfirmation(MESSAGE_DELETE_RELATION);
    alert.acceptAlert();
  }

  /**
   * Delete a category that is added to the file in SE By QuynhPT date 16/01/2015
   *
   * @param nameCategory String
   */
  public void deleteCategory(String nameCategory) {
    $(byXpath(ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY.replace("${nameCategory}", nameCategory))).click();
    alert.acceptAlert();
  }

  /**
   * Go to Import Node popup By QuynhPT date 16/01/2015
   */
  public void goToImportNode() {
    evt.click(ELEMENT_ACTIONBAR_IMPORT_BUTTON);

  }

  /**
   * Open Export Node popup By QuynhPT date 16/01/2015
   */
  public void goToExportNode() {
    $(ELEMENT_ACTIONBAR_EXPORT_BUTTON).click();

  }

  /**
   * Export node By QuynhPT date 16/01/2015
   *
   * @param systemView boolean
   * @param zip boolean
   */
  public void exportNode(boolean systemView, boolean zip) {
    // Verify that the popup is shown
   $(ELEMENT_EXPORT_NODE_POPUP_TITLE).waitUntil(Condition.visible,Configuration.timeout);
    // select doc view type
    if (!systemView)
      $(ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW).click();
    // select zip type
    if (zip)
      $(ELEMENT_EXPORT_NODE_POPUP_ZIP).click();

    $(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON).click();
    $(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Open Properties popup By QuynhPT date 19/01/2015
   */
  public void goToProperties() {
    $(ELEMENT_ACTIONBAR_PROPERTIES).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_ACTIONBAR_PROPERTIES).click();
  }

  /**
   * Go to Properties popup By QuynhPT
   *
   * @param property String
   * @param value String
   */
  public void addProperty(String property, String value) {
    $(ELEMENT_VIEWPROPERTIES_PROPERTIES_TAB).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_TAB).click();
    $(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_INPUT).selectOption(property);
    $(ELEMENT_VIEWPROPERTIES_VALUE_INPUT).setValue(value);
    button.save();
    // Check if a property is added successfully.
    $(byXpath(ELEMENT_VIEWPROPERTIES_PROPERTY.replace("{$property}", property).replace("{$value}", value))).waitUntil(Condition.visible,Configuration.timeout);
    button.close();
  }

  /**
   * Open Manage Pulishtation popup By QuynhPT
   */
  public void goToManagePublishtation() {
    $(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION).click();

  }

  /**
   * Manage Publication popup By thunt Update QuynhPT
   *
   * @param state String
   * @param date String
   */
  public void managePublication(String state, String... date) {
    By bState = By.xpath(ELEMENT_MANAGEPUBLICATION_STATE.replace("{$state}", state));
    String date1 = (String) (date.length > 0 ? date[0] : "");
    String date2 = (String) (date.length > 1 ? date[1] : "");
    // select State
    $(bState).click();
    $(byXpath(ELEMENT_MANAGEPUBLICATION_CURRENT_SPECIFIC_STATUS.replace("${status}", state))).waitUntil(Condition.visible,Configuration.timeout);
    if (state.equals("Staged") & (date.length > 0)) {
      $(ELEMENT_MANAGEPUBLICATION_SCHEDULE_TAB).click();
      if ((date1 != "" && date1 != null))
        $(ELEMENT_MANAGEPUBLICATION_PUB_FROM_INPUT).setValue(date1);
      if ((date2 != "" && date2 != null))
        $(ELEMENT_MANAGEPUBLICATION_PUB_TO_INPUT).setValue(date2);
      button.save();
      if (date.length > 2) {
        $(byText(MSG_INVALID_DATE_TIME)).waitUntil(Condition.visible,Configuration.timeout);
        button.ok();
        button.close();
        return;
      }
      $(byText("Your new publication schedule was saved successfully.")).waitUntil(Condition.visible,Configuration.timeout);
      button.ok();
      $(ELEMENT_MANAGEPUBLICATION_REVISION_TAB).click();
    }
    evt.waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB);
    evt.waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_REVISION_TAB);

   $(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB).click();
    $(byXpath(ELEMENT_MANAGEPUBLICATION_HISTORY_ITEM.replace("${state}", state))).waitUntil(Condition.visible,Configuration.timeout);

    button.close();

  }

  /**
   * Add a Translation
   */
  public void addTranslation() {
    info("Click on Add Tranlation button");
    if ($(ELEMENT_ACTIONBAR_ADDTRANSLATION).is(Condition.not(Condition.visible)))
      $(ELEMENT_ACTIONBAR_MORE).click();
    $(ELEMENT_ACTIONBAR_ADDTRANSLATION).click();
  }

  /**
   * Vote document
   */
  public void voteDocument() {
    info("Click to vote document");
    if ($(ELEMENT_ACTIONBAR_VOTE).is(Condition.not(Condition.visible)))
      $(ELEMENT_ACTIONBAR_MORE).click();
    $(ELEMENT_ACTIONBAR_VOTE).click();
  }

  /**
   * Add a document translation
   *
   * @param path String
   * @param content String
   */
  public void addDocumentTranslation(String path, String content) {
    addTranslation();
    $(ELEMENT_ADDTRANSLATION_SELECTDOC).waitUntil(Condition.visible,Configuration.timeout).click();

    String[] arrayPath = path.split("/");

    for (int i=0;i<=arrayPath.length-2;i++) {
      if($(byXpath(ELEMENT_SELECT_DOCUMENT_NODE_FOLDER.replace("${node}", arrayPath[i+1]))).waitUntil(Condition.visible,2000)==null)
      $(byXpath(ELEMENT_SELECT_DOCUMENT_NODE_FOLDER.replace("${node}", arrayPath[i]))).click();
    }
    $(byXpath(ELEMENT_SELECT_DOCUMENT_NODE_FOLDER.replace("${node}", arrayPath[arrayPath.length-1]))).click();
    if (!content.isEmpty()) {
      $(byXpath(ELEMENT_SELECT_DOCUMENT_NODE_FILE.replace("${content}", content))).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    $(ELEMENT_SAVE_BTN).click();

  }

  /**
   * Go to publication
   */
  public void goToPublication() {
    sleep(Configuration.timeout);
    if (!$(ELEMENT_ACTIONBAR_MORE).exists()) {
      do {
        executeJavaScript("window.scrollBy(0,-2000)");
      }while ($(ELEMENT_ACTIONBAR_MORE).exists());
    }
    $(ELEMENT_ACTIONBAR_MORE).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ACTIONBAR_PUBLICATION).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Add a category for a node
   *
   * @param node String
   * @param category String
   */
  public void addCategoryForNode(String node, String category) {
    info("Click on More menu");
    if( $(ELEMENT_ACTIONBAR_CATEGORY).is(Condition.not(Condition.visible)))
    $(ELEMENT_ACTIONBAR_MORE).waitUntil(Condition.visible,Configuration.timeout).click();

    $(ELEMENT_ACTIONBAR_CATEGORY).waitUntil(Condition.visible,Configuration.timeout).click();

    $(ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY).waitUntil(Condition.visible,Configuration.timeout).click();

    $(ELEMENT_CATEGORY_SELECT_CATEGORY_TREE).waitUntil(Condition.visible,Configuration.timeout).selectOption(category);

    $(ELEMENT_CATEGORY_ADD_ROOT_NODE).waitUntil(Condition.visible,Configuration.timeout).click();

  }

  /**
   * Go to the publication status' form
   * @param status String
   */
  public void changeStatusPulication(String status) {
    $(byXpath(ELEMENT_PUBLICATION_STATUS.replace("${status}", status))).waitUntil(Condition.visible,Configuration.timeout);
    $(byXpath(ELEMENT_PUBLICATION_STATUS.replace("${status}", status))).click();
    $(Button.ELEMENT_CLOSE_BUTTON).click();

  }

  /**
   * Open View Metadata popup
   */
  public void viewMetadata() {
    info("View Metadata");
    info("Click on More link");
    if ( $(ELEMENT_ACTIONBAR_METADATA).is(Condition.not(Condition.visible)))
    $(ELEMENT_ACTIONBAR_MORE).click();
    info("Click on Metadata link");
    $(ELEMENT_ACTIONBAR_METADATA).click();
    info("Verify that View metadata popup is shown");
    $(ELEMENT_METADATA_POPUP).waitUntil(Condition.visible,Configuration.timeout);
    info("Close the popup");
    $(ELEMENT_METADATA_POPUP_CANCEL).click();
    info("Metadata popup is shown successfully");
  }

  /**
   * Add/Edit a comment
   *
   * @param content String
   * @param  isAdd boolean
   */
  public void addEditComment(String content, boolean isAdd) {
    info("Add/Edit a comment");
    if (isAdd == true) {
      info("Click on Add comment on action bar");
      $(ELEMENT_ACTIONBAR_ADDCOMMENT).click();
    } else {
      info("Click on Edit comment button on action bar");
      $(ELEMENT_SITEEXPLORER_COMMENT_EDIT).click();
    }
    info("Refresh the page");
    refresh();
    info("Input a content to the frame");
    $(byId("comment")).sendKeys(content);
    info("Click on Save button");
    $(ELEMENT_SITEEXPLORER_COMMENT_SAVE).click();
    info("Finish adding/Editing the Comment");
  }

  /**
   * Open Admin view type
   */
  public void clickAdminView() {
    info("Select a view type");
    evt.waitForAndGetElement(ELEMENT_ADMIN_VIEW_ICON);
    evt.click(ELEMENT_ADMIN_VIEW_ICON);

  }

  /**
   * Click on Delete button
   */
  public void clickDeleteButton() {
    info("click on Delete button");
    evt.waitElementAndTryGetElement(ELEMENT_DELETE_ALL_BUTTON);
    WebElement el = evt.waitForAndGetElement(ELEMENT_DELETE_ALL_BUTTON);
    el.click();
    dialog.deleteInDialog();

  }

  /**
   * Select all files in folder under admin view
   */
  public void selectAllFiles() {
    info("Select all file");
    WebElement el =
                  (new WebDriverWait(testBase.getExoWebDriver().getWebDriver(),
                                     30)).until(ExpectedConditions.presenceOfElementLocated(ELEMENT_SITE_EXPLORER_ALL_CHECKBOX));
    if (evt.waitForAndGetElement(ELEMENT_DOCUMENT_LIST_ROW_CONTENT, 5000, 0) != null) {
      info("check on the checkbox");
      // el.click();
      evt.clickByJavascript(el, 2);

      info("Click on Delete button");
      clickDeleteButton();
    }

  }

  /**
   * Share document
   */
  public void shareDocument() {
    info("Click to share document");
    if (evt.waitForAndGetElement(ELEMENT_ACTIONBAR_SHARE, 5000, 0) == null)
      evt.click(ELEMENT_ACTIONBAR_MORE);
    evt.click(ELEMENT_ACTIONBAR_SHARE);
  }

  /**
   * Delete all files in a folder under Admin view
   *
   * @param title String
   */
  public void verifyContentCreatedSuccessfully(String title) {
    info("Verify Content was created successfully");
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    info("Content was created successfully");
  }

  public void verifyWebContentInformationCreatedSuccessfully(String Content){
    assertEquals(Content,$(byClassName("rightContainer")).find(byId("UIDocumentWorkspace")).find(byId("myTabContent")).find(byClassName("content-display")).getText());
  }

  /**
   * Select Drive by option
   */
  public enum selectDriverOption {
    ALPHABETICAL, TYPE, CREATEDDATE, MODIFIEDDATE
  }

  /**
   * Select Drive by order
   */

  public enum selectDriverOrder {
    ASCENDING, DESCENDING
  }
}
