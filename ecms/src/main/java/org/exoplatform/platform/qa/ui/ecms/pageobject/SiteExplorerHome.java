package org.exoplatform.platform.qa.ui.ecms.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_FILEFORM_BLANK_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_SELECT_USER_ICON1;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

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

    $(byId("uiActionsBarContainer")).find(byText("Site Management")).click();

    $(byText(drive)).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(drive)).click();
    $(ELEMENT_SIDE_BAR_MAINTAB).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
    info("Go to folder");
    if (!path.isEmpty()) {
      String[] arrayPath = path.split("/");
      for (String arrayElement : arrayPath) {
        selectNode(arrayElement);
      }
    }
  }

  /**
   * Open Add a new folder popup
   */
  public void goToAddNewFolder() {
    info("Add a new folder");
    if (evt.waitForAndGetElement(ELEMENT_ACTIONBAR_ADDFOLDER, 7000, 0) == null) {
      info("Click on More menu");
      evt.click(ELEMENT_ACTIONBAR_MORE);
      evt.waitForAndGetElement(ELEMENT_ACTIONBAR_ADDFOLDER, testBase.getDefaultTimeout(), 1);
      info("Click on New folder on Action Bar");
      evt.click(ELEMENT_ACTIONBAR_ADDFOLDER);
    } else {
      evt.waitForAndGetElement(ELEMENT_ACTIONBAR_ADDFOLDER, testBase.getDefaultTimeout(), 1);
      info("Click on New folder on Action Bar");
      evt.click(ELEMENT_ACTIONBAR_ADDFOLDER);
    }
    info("Verify that Add folder popup is shown");
    evt.waitForAndGetElement(ELEMENT_ADDFOLDERBOX);
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

    $(ELEMENT_ACTIONBAR_ADDDOCUMENT).waitUntil(Condition.appears, Configuration.timeout);
    info("Click on New Document on Action Bar");
    $(ELEMENT_ACTIONBAR_ADDDOCUMENT).click();
    info("Verify that New content page is shown");

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
    boolean verify = (destination.length > 0 ? destination[0] : false);
    info("Click on File Explorer icon");
    // scroll de 50 pixel
    executeJavaScript("window.scrollBy(0,50);", "");
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();

    info("Right click on nodename");
    ELEMENT_CONTENT_LIST.find(byLinkText(title)).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    ELEMENT_CONTENT_LIST.find(byLinkText(title)).contextClick();
    info("Click on Delete link");
    $(ELEMENT_SITEEXPLORER_ACTION_DELETE).click();
    info("Click on Delete button on Confirm popup");
    $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).click();
    $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    info("Verify that the node is deleted");
    info("the node is deleted successfully");
  }

  /**
   * @param title String
   * @param destination String
   */
  public void copyPasteNode(String title, String destination) {
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title))).contextClick();
    $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination))).contextClick();
    $(ELEMENT_SITEEXPLORER_ACTION_PASTE).click();
    refresh();
    $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();

  }

  /**
   * Open list document's templates
   */
  public void openListDocumentTemplateByRightClick() {
    info("Select Document type");
    int repeat = 0;
    while (evt.waitForAndGetElement(ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS, 2000, 0) == null) {
      if (repeat > 5)
        break;
      if (evt.waitForAndGetElement(ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS, 2000, 0) != null)
        break;
      evt.rightClickOnElement(ELEMENT_THUMBNAIL_VIEW_ADMIN_VIEW);

      // Actions action = new Actions(this.driver);
      // action.moveToElement(waitForAndGetElement(ELEMENT_CONTEXT_MENU_ADD_DOCUMENT))
      // .doubleClick().perform();
      evt.click(ELEMENT_CONTEXT_MENU_ADD_DOCUMENT, 2);
      repeat++;
    }
    evt.waitForAndGetElement(ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS, 2000, 1);
    info("Document type is created");
  }

  /**
   * Cut and paste node
   *
   * @param title String
   * @param destination String
   */
  public void cutPasteNode(String title, String destination) {
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title))).contextClick();
    $(ELEMENT_SITEEXPLORER_ACTION_CUT).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination))).contextClick();
    $(ELEMENT_SITEEXPLORER_ACTION_PASTE).click();
    refresh();
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();

  }

  /**
   * Rename a node
   *
   * @param node String
   * @param newName String
   */
  public void renameNode(String node, String newName) {
    evt.rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node)));
    evt.click(ELEMENT_SITEEXPLORER_ACTION_RENAME);
    evt.type(ELEMENT_SITEEXPLORER_RENAME_FIELD, newName, true);
    evt.click(ELEMENT_SITEEXPLORER_RENAME_SAVE);

    evt.click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);

  }

  /**
   * Upload a file
   *
   * @param link String
   * @param params String
   */
  public void uploadFileWithDymanicPath(String link, Object... params) {
    Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
    Boolean prev = (Boolean) (params.length > 1 ? params[1] : false);
    if (evt.waitForAndGetElement(ELEMENT_UPLOAD_BUTTON, 10000, 0) == null) {
      evt.click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
    }
    evt.click(ELEMENT_UPLOAD_LINK);
    if (prev) {
      testBase.uploadFileUsingRobotDocumentPreview(link);
      info("Upload file " + ManageFileTestBase.getAbsoluteFilePathFromFile(link));
    } else {
      testBase.uploadFileUsingRobot(link);
      info("Upload file " + MFTB.getAbsoluteFilePath(link));
    }

    evt.waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR, 120000, 0);

    info("verify:" + verify);
    if (verify) {
      String links[] = link.split("/");
      int length = links.length;

      evt.waitForAndGetElement(By.xpath("//*[contains(text(),'" + links[length - 1] + "')]"));
    }

    info("Upload file successfully");

  }

  /**
   * Upload a file
   *
   * @param link String
   * @param params object
   */
  public void uploadFile(String link, Object... params) {
    Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
    if (evt.waitForAndGetElement(ELEMENT_UPLOAD_BUTTON, testBase.getDefaultTimeout(), 0) == null) {
      evt.click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
    }

    evt.click(ELEMENT_UPLOAD_LINK);
    MFTB.uploadFileUsingRobot(link);
    // waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR);

    info("verify:" + verify);
    if (verify) {
      String links[] = link.split("/");
      int length = links.length;

      evt.waitForAndGetElement(By.xpath("//*[contains(text(),'" + links[length - 1] + "')]"), 3000, 1);
    }

    info("Upload file successfully");

  }

  /**
   * Go to Edit page of a document
   */

  public void goToEditDocument() {
    evt.click(ELEMENT_ACTIONBAR_EDIT);

  }

  /**
   * Add tag to a Content
   *
   * @param tag String
   */
  public void addTag(String tag) {
    evt.waitForAndGetElement(ELEMENT_ACTIONBAR_MORE);
    info("Click on More menu");
    evt.click(ELEMENT_ACTIONBAR_MORE);
    info("Click on Tag link");
    evt.click(ELEMENT_ACTIONBAR_TAG);
    info("Input name of tag");
    evt.type(ELEMENT_TAG_FORM, tag, true);
    info("Click on Add button");
    evt.click(ELEMENT_ADD_TAG_FORM);
    info("The tag is created successfully");
    info("Close the popup");
    evt.click(ELEMENT_TAG_POPUP_CLOSE);
  }

  /**
   * Edit a Tag
   *
   * @param oldName String
   * @param newName String
   */
  public void editTag(String oldName, String newName) {
    info("Click on Tag Cloud tab of SE");
    evt.click(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB);
    evt.waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}", oldName));
    info("Click on Edit button of Tag Cloud");
    evt.click(ELEMENT_SIDEBAR_TAGCLOUD_EDIT);
    evt.waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE);
    info("Click on Edit button of the old tag");
    evt.click(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT.replace("${name}", oldName));
    evt.waitForAndGetElement(ELEMENT_TAG_POPUP_NAME_FIELD);
    info("Input new name of tag");

    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].setAttribute('value', '"
        + newName + "')", evt.waitForAndGetElement(ELEMENT_TAG_POPUP_NAME_FIELD));
    info("Save all changes");
    evt.clickByJavascript(ELEMENT_TAG_POPUP_SAVE);
    info("Verify that the new name of tag is changed");
    evt.waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}", newName));
    info("the new name of tag is changed successfully");
    evt.click(ELEMENT_TAGE_POPUP_CLOSE);
    info("The edit tag popup is closed");
  }

  /**
   * Delete a tag
   *
   * @param tag String
   */
  public void deleteTag(String tag) {
    info("Click on Tag Cloud tab of SE");
    evt.click(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB);
    evt.waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}", tag));
    info("Click on Edit button of Tag Cloud");
    evt.click(ELEMENT_SIDEBAR_TAGCLOUD_EDIT);
    evt.waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE);
    info("Click on Delete button of the old tag");
    evt.click(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE.replace("${name}", tag));
    alert.acceptAlert();
    info("Verify that tag is delete");
    evt.waitForElementNotPresent(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}", tag));
    info("The tag is deleted successfully");
    info("Close the popup");
    evt.click(ELEMENT_TAGE_POPUP_CLOSE);

  }

  /**
   * Edit a Document
   *
   * @param content String
   * @param newTitle String
   */
  public void editDocument(String newTitle, String content) {

    $(ELEMENT_ACTIONBAR_MORE).click();
    $(ELEMENT_ACTIONBAR_EDIT).click();

    if ($(ELEMENT_FILE_FORM_TITLE).is(Condition.enabled)) {
      $(ELEMENT_FILE_FORM_TITLE).setValue(newTitle);
    }
    $(ELEMENT_FILEFORM_BLANK_CONTENT).click();
    $(ELEMENT_FILEFORM_BLANK_CONTENT).sendKeys(content);
  }

  /**
   * Go to Intranet page
   */

  public void goToIntranet() {
    evt.click(ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET);
  }

  /**
   * Go to document
   */
  public void goToDocument() {
    evt.click(ELEMENT_SITEEXPLORER_LEFTBOX_DOCUMENT);
  }

  /**
   * Go to space
   */
  public void goToSpace(String spaceName) {
    evt.click(ELEMENT_ACTIONBAR_SHOWDRIVES);
    evt.click(By.xpath((ELEMENT_SELECTDRIVE_SPECIFICDRIVE).replace("${spaceName}", spaceName)));
  }

  /**
   * Open Setting drive page
   *
   * @param type enum
   * @param order enum
   */
  public void openSettingsDriver(selectDriverOption type, selectDriverOrder order) {
    evt.click(ELEMENT_ACTIONBAR_SETTINGS);
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

    $(ELEMENT_DRIVERSETTINGS_SAVE).click();
  }

  /**
   * Go to Permission popup
   */
  public void goToPermission() {
    info("Click on More link on Action bar");
    evt.click(ELEMENT_ACTIONBAR_MORE);
    info("Click on Permission on Action bar");
    evt.click(ELEMENT_ACTIONBAR_PERMISSION);
    info("Finished opening permission popup");
  }

  /**
   * Select a node by name
   *
   * @param nodeName String
   */
  public void selectNode(String nodeName) {
    info("Verify that nodeName:" + nodeName + " is shown");

    $(byText(nodeName)).waitUntil(Condition.appears, Configuration.timeout);
    info("Click on the nodeName:" + nodeName);
    $(byText(nodeName)).click();
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
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", name))).contextClick();
    $(ELEMENT_SITEEXPLORER_LIST_LOCK_NODE).click();
    $(byXpath(ELEMENT_SITEEXPLORER_LOCK_ICON.replace("$node", name))).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Unlock a Node
   *
   * @param name String
   */
  public void unlockNode(String name) {
    info("unlock node:" + name);
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", name))).contextClick();
    $(ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE).click();
    $(byXpath(ELEMENT_SITEEXPLORER_LOCK_ICON.replace("$node", name))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * By QuynhPT Open Add Relation popup
   */
  public void goToManageRelation() {
    evt.click(ELEMENT_ACTIONBAR_RELATION);

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
      evt.click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_SELECT_CONTENT_RIGHT_TREE.replace("${nameContent}", arrayElement));

    }
  }

  /**
   * Open Add Category popup By QuynhPT date 16/01/2015
   */
  public void goToAddCategory() {
    evt.click(ELEMENT_ACTIONBAR_CATEGORY);

  }

  /**
   * Add category for a file in SE
   *
   * @param categoryTreeName String
   * @param arrayCatePath String
   * @param nameSelectedCategory String
   */
  public void addCategory(String categoryTreeName, String[] arrayCatePath, String nameSelectedCategory) {
    info("select category");
    evt.click(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB);

    evt.select(ELEMENT_ADD_CATEGORY_POPUP_MENU, categoryTreeName);

    for (String cateName : arrayCatePath) {
      // click(ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE.replace("${nameTitle}",
      // cateName));
      evt.clickByJavascript(ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE.replace("${nameTitle}", cateName), 2);

    }
    evt.click(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_RIGHT_SIDE.replace("${nameCategory}", nameSelectedCategory));

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
    evt.click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB);

    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      evt.click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_NODE_LEFT_TREE.replace("${nameNode}", arrayElement));
    }
  }

  /**
   * By QuynhPT Close "Add Relation" popup
   */
  public void closeAddRelationPopup() {
    evt.click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_CLOSE_BUTTON);

  }

  /**
   * By QuynhPT Open Relation tab on SideBar
   */
  public void goToRelationSideBar() {
    evt.click(ELEMENT_SIDE_BAR_RELATION_ICON);

  }

  /**
   * Delete Relation By QuynhPT
   *
   * @param nameContent String
   */
  public void deleteRelation(String nameContent) {
    evt.click(By.xpath(ELEMENT_RELATION_POPUP_RELATION_LIST_DELETE_BUTTON.replace("${nameContent}", nameContent)));
    alert.waitForConfirmation(MESSAGE_DELETE_RELATION);
    alert.acceptAlert();
  }

  /**
   * Delete a category that is added to the file in SE By QuynhPT date 16/01/2015
   *
   * @param nameCategory String
   */
  public void deleteCategory(String nameCategory) {
    evt.click(By.xpath(ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY.replace("${nameCategory}", nameCategory)));
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
    evt.click(ELEMENT_ACTIONBAR_EXPORT_BUTTON);

  }

  /**
   * Select a value for behavior
   *
   * @param value String
   */
  public void selectBehavior(defineValueBehavior value) {
    switch (value) {
    case CREATE_NEW:
      evt.click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_CREATE_NEW);
      break;
    case REMOVE_EXISTING:
      evt.click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REMOVE_EXISTING);
      break;
    case REPLACE_EXISTING:
      evt.click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REPLACE_EXISTING);
      break;
    case THROW_EXEPTION:
      evt.click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_THROW_EXCEPTION);
      break;
    }
  }

  /**
   * Import a Node By QuynhPt date 16/01/2015
   *
   * @param linkFile String
   * @param behavior String
   * @param version boolean
   * @param linkVersion String
   */
  public void importNode(String linkFile, String behavior, boolean version, String linkVersion) {
    // Verify that the popup is shown
    evt.waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_TITLE);

    // upload the file
    WebElement upload = evt.waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_BUTTON, testBase.getDefaultTimeout(), 1, 2);

    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].style.display = 'block';",
                                                                                   upload);
    upload.sendKeys(testBase.getAbsoluteFilePath(linkFile));
    String[] nameFile = linkFile.split("/");
    evt.waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL.replace("${fileName}", nameFile[1]));

    // select a value for behavior
    evt.select(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR, behavior);

    if (version) {
      WebElement uploadVersion = evt.waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_VERSION_HISTORY_BUTTON,

                                                          testBase.getDefaultTimeout(),
                                                          1,
                                                          2);
      ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].style.display = 'block';",
                                                                                     uploadVersion);
      uploadVersion.sendKeys(testBase.getAbsoluteFilePath(linkVersion));
      String[] namefile = linkVersion.split("/");
      evt.waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL.replace("${fileName}", namefile[1]));
    }
    evt.switchToParentWindow();
    evt.click(ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON);

    evt.waitForMessage("Imported successfully.");
    evt.click(button.ELEMENT_OK_BUTTON);

  }

  /**
   * Export node By QuynhPT date 16/01/2015
   *
   * @param systemView boolean
   * @param zip boolean
   */
  public void exportNode(boolean systemView, boolean zip) {
    // Verify that the popup is shown
    evt.waitForAndGetElement(ELEMENT_EXPORT_NODE_POPUP_TITLE);
    // select doc view type
    if (!systemView)
      evt.click(ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW, 2);
    // select zip type
    if (zip)
      evt.click(ELEMENT_EXPORT_NODE_POPUP_ZIP, 2);

    evt.click(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON);
  }

  /**
   * Open Properties popup By QuynhPT date 19/01/2015
   */
  public void goToProperties() {
    evt.waitForAndGetElement(ELEMENT_ACTIONBAR_PROPERTIES);
    evt.click(ELEMENT_ACTIONBAR_PROPERTIES);
  }

  /**
   * Go to Properties popup By QuynhPT
   *
   * @param property String
   * @param value String
   */
  public void addProperty(String property, String value) {
    evt.waitForAndGetElement(ELEMENT_VIEWPROPERTIES_PROPERTIES_TAB);
    evt.click(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_TAB);

    evt.select(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_INPUT, property);
    evt.type(ELEMENT_VIEWPROPERTIES_VALUE_INPUT, value, true);
    button.save();

    // Check if a property is added successfully.
    evt.waitForAndGetElement(ELEMENT_VIEWPROPERTIES_PROPERTY.replace("{$property}", property).replace("{$value}", value));
    button.close();
  }

  /**
   * Open Manage Pulishtation popup By QuynhPT
   */
  public void goToManagePublishtation() {
    evt.waitForAndGetElement(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION);
    evt.click(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION);

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
    evt.click(bState);
    evt.waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_CURRENT_SPECIFIC_STATUS.replace("${status}", state));
    if (state.equals("Staged") & (date.length > 0)) {
      evt.click(ELEMENT_MANAGEPUBLICATION_SCHEDULE_TAB);
      if ((date1 != "" && date1 != null))
        evt.type(ELEMENT_MANAGEPUBLICATION_PUB_FROM_INPUT, date1, true);
      if ((date2 != "" && date2 != null))
        evt.type(ELEMENT_MANAGEPUBLICATION_PUB_TO_INPUT, date2, true);
      button.save();
      if (date.length > 2) {
        evt.waitForMessage(MSG_INVALID_DATE_TIME);
        button.ok();
        button.close();
        return;
      }
      evt.waitForMessage("Your new publication schedule was saved successfully.");
      button.ok();
      evt.click(ELEMENT_MANAGEPUBLICATION_REVISION_TAB);
    }
    evt.waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB);
    evt.waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_REVISION_TAB);

    evt.click(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB);
    evt.waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_HISTORY_ITEM.replace("${state}", state));

    button.close();

  }

  /**
   * Add a Translation
   */
  public void addTranslation() {
    info("Click on Add Tranlation button");
    if (evt.waitForAndGetElement(ELEMENT_ACTIONBAR_ADDTRANSLATION, 5000, 0) == null)
      evt.click(ELEMENT_ACTIONBAR_MORE);
    evt.click(ELEMENT_ACTIONBAR_ADDTRANSLATION);
  }

  /**
   * Vote document
   */
  public void voteDocument() {
    info("Click to vote document");
    if (evt.waitForAndGetElement(ELEMENT_ACTIONBAR_VOTE, 5000, 0) == null)
      evt.click(ELEMENT_ACTIONBAR_MORE);
    evt.click(ELEMENT_ACTIONBAR_VOTE);
  }

  /**
   * Add a document translation
   *
   * @param path String
   * @param content String
   */
  public void addDocumentTranslation(String path, String content) {
    addTranslation();
    evt.waitForAndGetElement(ELEMENT_ADDTRANSLATION_SELECTDOC);
    evt.click(ELEMENT_ADDTRANSLATION_SELECTDOC);

    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      evt.click(ELEMENT_SELECT_DOCUMENT_NODE_FOLDER.replace("${node}", arrayElement));
    }

    if (!content.isEmpty()) {
      evt.waitForAndGetElement(ELEMENT_SELECT_DOCUMENT_NODE_FILE.replace("${content}", content));
      evt.click(ELEMENT_SELECT_DOCUMENT_NODE_FILE.replace("${content}", content));
    }
    evt.click(ELEMENT_SAVE_BTN);

  }

  /**
   * Go to publication
   */
  public void goToPublication() {
    evt.click(ELEMENT_ACTIONBAR_MORE);
    evt.click(ELEMENT_ACTIONBAR_PUBLICATION);
  }

  /**
   * Add a category for a node
   *
   * @param node String
   * @param category String
   */
  public void addCategoryForNode(String node, String category) {
    info("Click on More menu");
    evt.click(ELEMENT_ACTIONBAR_MORE);

    evt.click(ELEMENT_ACTIONBAR_CATEGORY);

    evt.click(ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY);

    evt.select(ELEMENT_CATEGORY_SELECT_CATEGORY_TREE, category);

    evt.click(ELEMENT_CATEGORY_ADD_ROOT_NODE);

  }

  /**
   * Go to the publication status' form
   * @param status String
   */
  public void changeStatusPulication(String status) {
    evt.waitForAndGetElement(ELEMENT_PUBLICATION_STATUS.replace("${status}", status));
    evt.click((ELEMENT_PUBLICATION_STATUS).replace("${status}", status));

  }

  /**
   * Go to root drive as Site Management, Collaboration tabs... of sidebar
   */
  public void goToRootDrive() {
    evt.waitForAndGetElement(ELEMENT_SIDE_BAR_MAINTAB);
    evt.click(ELEMENT_SIDE_BAR_MAINTAB);

  }

  /**
   * Open View Metadata popup
   */
  public void viewMetadata() {
    info("View Metadata");
    info("Click on More link");
    evt.click(ELEMENT_ACTIONBAR_MORE);
    info("Click on Metadata link");
    evt.click(ELEMENT_ACTIONBAR_METADATA);
    info("Verify that View metadata popup is shown");
    evt.waitForAndGetElement(ELEMENT_METADATA_POPUP);
    info("Close the popup");
    evt.click(ELEMENT_METADATA_POPUP_CANCEL);
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
      evt.click(ELEMENT_ACTIONBAR_ADDCOMMENT);
    } else {
      info("Click on Edit comment button on action bar");
      evt.click(ELEMENT_SITEEXPLORER_COMMENT_EDIT);
    }
    info("Refresh the page");
    this.testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    info("Input a content to the frame");
    evt.inputDataToCKEditor(ELEMENT_FILEFORM_BLANK_CONTENT, content);
    info("Switch to parent window");
    evt.switchToParentWindow();
    info("Click on Save button");
    evt.click(ELEMENT_SITEEXPLORER_COMMENT_SAVE);
    info("Finish adding/Editing the Comment");
  }

  /**
   * Delete a file or node in SE by clicking a checkbox of that file
   *
   * @param file String
   */
  public void selectAndDeleteByCheckBox(String file) {
    evt.waitForAndGetElement(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}", file));
    evt.click(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}", file));
    evt.click(ELEMENT_ACTIONBAR_DELETE);
    evt.click(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE);
    evt.waitForElementNotPresent(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}", file));
  }

  /**
   * Open drive area
   */
  public void openDrives() {

    if (evt.waitForAndGetElement(ELEMENT_SHOW_DRIVES, 3000, 0) != null)
      evt.click(ELEMENT_SHOW_DRIVES);
    else
      evt.click(By.xpath("//*[@title = 'Show Drives']"));

  }

  /**
   * Go to a drive
   *
   * @param nameDrive String
   */
  public void selectADrive(String nameDrive) {
    info("Go to a folder of a drive");
    evt.waitForAndGetElement(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));
    evt.click(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));

  }

  /**
   * Go to a folder
   *
   * @param path String
   */
  public void goToAFolder(String path) {
    info("Go to a folder of a drive");

    WebElement pathInput = evt.waitForAndGetElement(ELEMENT_SITE_PATH, 2000, 1, 2);
    pathInput.clear();
    pathInput.sendKeys(path);

    Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    action.moveToElement(pathInput).sendKeys(Keys.ENTER).build().perform();
    action.moveToElement(pathInput).release();

  }

  /**
   * Go to a folder in Admin view
   *
   * @param name String
   */
  public void openAFolder(String name) {
    info("Click on the folder");
    evt.click(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", name)));

  }

  /**
   * Open Web view type
   */
  public void clickWebView() {
    info("Select a view type");

    evt.waitForAndGetElement(ELEMENT_WEB_VIEW);
    // click(ELEMENT_WEB_VIEW);
    evt.clickByJavascript(ELEMENT_WEB_VIEW, 2);

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
   * Open List view type
   */
  public void clickListView() {
    info("Select a view type");
    evt.waitForAndGetElement(ELEMENT_LIST_VIEW_ICON);
    evt.click(ELEMENT_LIST_VIEW_ICON);

  }

  /**
   * Select File Explorer tree on left panel
   */
  public void selectFileExplorer() {
    info("Select File Explorer");
    WebElement el = (new WebDriverWait(testBase.getExoWebDriver().getWebDriver(),
                                       30)).until(ExpectedConditions.presenceOfElementLocated(ELEMENT_FILE_EXPLORER_ICON));
    el.click();

  }

  /**
   * Click on Delete button
   */
  public void clickDeleteButton() {
    info("click on Delete button");
    evt.waitElementAndTryGetElement(ELEMENT_DELETE_ALL_BUTTON);
    WebElement el = evt.waitForAndGetElement(ELEMENT_DELETE_ALL_BUTTON);
    /*
     * WebElement el = (new WebDriverWait(driver, 30))
     * .until(ExpectedConditions.presenceOfElementLocated(
     * ELEMENT_DELETE_ALL_BUTTON));
     */
    el.click();
    dialog.deleteInDialog();

  }

  /**
   * Open a file from right panel
   *
   * @param filename String
   */
  public void selectAFile(String filename) {
    info("Waiting the file:" + filename + " is shown");
    evt.waitForAndGetElement(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename), 5000, 1);
    info("Select the file");
    evt.click(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename));

    info("The document is opened");
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
   * Select a new content in list
   *
   * @param nameContent String
   */
  public void selectAContentType(String nameContent) {
    info("Select a content");
    WebElement el =
                  (new WebDriverWait(testBase.getExoWebDriver().getWebDriver(),
                                     30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ELEMENT_SITE_EXPLORER_CONTENT_NAME.replace("${nameContent}", nameContent))));
    el.click();

  }

  /**
   * Delete all files in a folder under Admin view
   */
  public void deleteAllFiles() {
    info("Select Admin view type");
    clickAdminView();
    info("Select All checkbox");
    selectAllFiles();
  }

  /**
   * HEAD Check display of drive
   *
   * @param drive String
   * @param isDisplay boolean
   */
  public void checkDisplayOfDrive(String drive, boolean isDisplay) {
    info("check display of drive:" + drive);
    evt.click(ELEMENT_ACTIONBAR_SHOWDRIVES, 0, true);
    if (isDisplay)
      evt.waitForAndGetElement(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}", drive));
    else
      evt.waitForElementNotPresent(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}", drive));
  }

  /**
   * Check action in view
   *
   * @param view String
   * @param actions String
   */
  public void checkActionInView(String view, String[] actions) {
    info("check action:" + testBase.action + " in view" + view);
    evt.waitForAndGetElement(ELEMENT_ITEM_VIEW.replace("$view", view));
    evt.click(ELEMENT_ITEM_VIEW.replace("$view", view), 0, true);
    for (String action : actions) {
      evt.waitForAndGetElement(ELEMENT_ACTIONBAR_ACTION.replace("$action", action));
    }

  }

  /**
   * Check query in saved query
   *
   * @param queries list of queries
   */
  public void checkQueryInSavedQuery(String... queries) {
    info("check display of query");
    goToAdvancedSearch();
    evt.click(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_SAVEDQUERYTAB, 0, true);
    for (String query : queries) {
      evt.waitForAndGetElement(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERY.replace("${name}", query));
    }
  }

  /**
   * Check user selector of Documents/Permission
   *
   * @param user String
   * @param isPresent boolean
   */
  public void checkUserSelectorECM(String user, boolean isPresent) {
    if (evt.waitForAndGetElement(ELEMENT_ACTIONBAR_PERMISSION, testBase.getDefaultTimeout(), 0) == null) {
      evt.click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
    }
    evt.click(ELEMENT_ACTIONBAR_PERMISSION, 0, true);

    info("check user selector");
    evt.click(ELEMENT_SELECT_USER_ICON1, 0, true);
    plfPerm.checkUserSelector(user, isPresent);
  }

  /**
   * Check personal file
   *
   * @param file String
   * @param isPresent boolean
   */
  public void checkFileInPersonal(String file, boolean isPresent) {
    info("check file in personal document");
    evt.waitForAndGetElement(ELEMENT_FOLDERSELECTOR_PATH.replace("${path}", file), 3000, 1);
  }

  /**
   * Check SE file
   *
   * @param file String
   * @param isPresent boolean
   */
  public void checkFileInSE(String file, boolean isPresent) {
    info("check file in SE");
    evt.waitForAndGetElement((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", file), 3000, 1);
  }

  /**
   * Watch a document
   */
  public void watchDocument() {
    info("watch a document");
    evt.click(ELEMENT_ACTIONBAR_WATCH, 0, true);

    evt.click(ELEMENT_ACTIONBAR_WATCH_RADIO, 0, true);
    evt.click(ELEMENT_ACTIONBAR_WATCH_BUTTON, 0, true);
    evt.waitForAndGetElement(ELEMENT_ACTIONBAR_WATCH_NOTICE);

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
   * Upload file/files to a specific folder
   *
   * @param folderName String
   * @param uploadFiles String
   * @throws Exception exception
   */
  public void uploadFileToFolder(String folderName, ArrayList<String> uploadFiles) throws Exception {
    info("Upload file to folder");
    evt.click(ELEMENT_DOCUMENT_SELECTED_FOLDER.replace("${folderName}", folderName));

    for (String fileName : uploadFiles) {
      uploadFile("TestData/" + fileName);
    }
  }

  /**
   * Share one document to many spaces
   *
   * @param fileName String
   * @param spaceList String
   * @param comment String
   */
  public void shareDocumentToManySpaces(String fileName, ArrayList<String> spaceList, String comment) {
    info("Share document to space");
    for (String spaceElement : spaceList) {
      // Click space list
      evt.click(ELEMENT_SPACE_LIST);
      // Select a space
      evt.click(ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceElement));
    }
    // Comment
    if (comment != null && !comment.isEmpty()) {
      evt.type(ELEMENT_SHARE_DOCUMENT_COMMENT, comment, false);
    }

    // Click Share button
    evt.click(ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Share"));
  }

  /**
   * Activity about shared document is displayed in Intranet Activity Stream and
   * Space Activity Stream
   *
   * @param shareOwner String
   * @param fileName String
   * @param spaceName String
   */
  public void checkDisplayOfSharedDocument(String shareOwner, String fileName, String spaceName) {
    SocialLocator socLocator = new SocialLocator();
    // Check Shared content in Intranet AS
    evt.waitForAndGetElement(ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", shareOwner).replace("${spaceName}", spaceName));

    // Open space displayed in shared document
    evt.click(ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", shareOwner).replace("${spaceName}", spaceName));
    evt.waitForAndGetElement(socLocator.ELEMENT_SPACE_NAME.replace("${name}", spaceName));
    // Check Shared content in Space AS
    evt.waitForAndGetElement(ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE.replace("${author}", shareOwner));
  }

  /**
   * Symlink of shared file is displayed in shared folder
   *
   * @param fileName String
   * @param spaceName String
   */
  public void checkSharedFileSymlink(String fileName, String spaceName) {
    SpaceManagement spaceManage = new SpaceManagement(testBase);
    // Go to Documents of Space
    spaceManage.goToDocumentTab();
    evt.waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
    // Open shared folder
    evt.doubleClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
    // Find the shared file
    evt.waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK.replace("${spaceName}", spaceName)
                                                                               .replace("${fileName}", fileName));
  }

  /**
   * Create a space with 2 users
   *
   * @param spaceName String
   * @param user1 String
   * @param user2 String
   * @param user2FullName String
   * @param password String
   */
  public void initSpaceWithUsers(String spaceName, String user1, String user2, String user2FullName, String password) {
    ManageLogInOut manageLoginOut = new ManageLogInOut(testBase);
    HomePagePlatform homepage = new HomePagePlatform(testBase);
    SpaceManagement spaceManage = new SpaceManagement(testBase);
    SpaceHomePage spaceHome = new SpaceHomePage(testBase);
    SpaceSettingManagement spaceSetting = new SpaceSettingManagement(testBase);

    info("User A login");
    manageLoginOut.signIn(user1, password);

    info("User A creates a space");
    homepage.goToAllSpace();

    spaceManage.goToCreateSpace();
    spaceManage.addNewSpaceSimple(spaceName, "");

    info("User A invites UserB to the space");
    homepage.goToSpecificSpace(spaceName);
    spaceHome.goToSpaceSettingTab();
    spaceSetting.goToMemberTab();
    spaceSetting.inviteUser(user2, true, user2FullName);

    info("User B login");
    manageLoginOut.signOut();
    manageLoginOut.signIn(user2, password);

    info("User B accepted to join the space");
    homepage.goToAllSpace();

    spaceManage.acceptAInvitation(spaceName);

    manageLoginOut.signOut();
  }

  /**
   * Upload and Share a document to a space
   *
   * @param fileName String
   * @param spaceName String
   * @param comment String
   */
  public void uploadAndShareDocumentToSpace(String fileName, String spaceName, String comment) {
    NavigationToolbar navTool = new NavigationToolbar(testBase);

    info("Share document to space");
    navTool.goToSiteExplorer();

    uploadFile("TestData/" + fileName);

    evt.waitForAndGetElement(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileName));
    // Share file to space
    evt.click(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileName));
    shareDocument();
    // Click space list
    evt.click(ELEMENT_SPACE_LIST);
    // Select a space
    evt.click(ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName));

    // Comment
    if (comment != null && !comment.isEmpty()) {
      evt.type(ELEMENT_SHARE_DOCUMENT_COMMENT, comment, false);
    }

    // Click Share button
    evt.click(ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Share"));
  }

  /**
   * Date: Oct 7, 2015 Delete symlink of shared document in space documents
   * @param spaceName String
   * @param fileName String
   */
  public void deleteSymlink(String spaceName, String fileName) {
    HomePagePlatform homepage = new HomePagePlatform(testBase);
    SpaceManagement spaceManage = new SpaceManagement(testBase);

    homepage.goToSpecificSpace(spaceName);

    spaceManage.goToDocumentTab();
    evt.waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
    // Open shared folder
    evt.doubleClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
    // Find the shared file
    evt.waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK.replace("${spaceName}", spaceName)
                                                                               .replace("${fileName}", fileName));
    // Delete symlink
    evt.rightClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK.replace("${spaceName}", spaceName)
                                                                              .replace("${fileName}", fileName));
    evt.waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
    evt.click(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
    // confirm
    evt.waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));
    evt.click(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));
    evt.waitForElementNotPresent(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK.replace("${spaceName}", spaceName)
                                                                                   .replace("${fileName}", fileName));
  }

  public void checkShareActivityAfterDeleted(String spaceName, boolean verifyInSpace) {
    HomePagePlatform homepage = new HomePagePlatform(testBase);
    SpaceManagement spaceManage = new SpaceManagement(testBase);

    evt.waitForElementNotPresent(ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", PLFData.DATA_NAME_USER1)
                                                               .replace("${spaceName}", spaceName));
    if (verifyInSpace) {
      homepage.goToSpecificSpace(spaceName);
      spaceManage.goToActivityStreamTab();
      evt.waitForElementNotPresent(ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE.replace("${author}", PLFData.DATA_NAME_USER1));
    }
  }

  /**
   * Date: Oct 8, 2015 Documents -Icons button
   */
  public void clickIconView() {
    info("Select a view type");
    evt.waitForAndGetElement(ELEMENT_ADDRESS_BAR_ICON_VIEW);
    evt.click(ELEMENT_ADDRESS_BAR_ICON_VIEW);

  }

  /**
   * The symlink does not exist in Shared folder Date: Oct 21, 2015
   * @param fileName String
   * @param spaceName  String
   */
  public void checkSharedFileSymlinkAfterDeleted(String fileName, String spaceName) {
    SpaceManagement spaceManage = new SpaceManagement(testBase);
    // Go to Documents of Space
    spaceManage.goToDocumentTab();
    evt.waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
    // Open shared folder
    evt.doubleClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
    // Find the shared file
    evt.waitForElementNotPresent(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK.replace("${spaceName}", spaceName)
                                                                                   .replace("${fileName}", fileName));
  }

  /**
   * Share document with access rights: Can view/Can edit Date: Oct 22, 2015
   * @param spaceName String
   * @param accessRight String
   * @param comment  String
   */
  public void shareDocumentToSpaceWithAccessRight(String spaceName, String accessRight, String comment) {
    shareDocument();
    evt.click(ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", accessRight));
    // Click space list
    evt.click(ELEMENT_SPACE_LIST);
    // Select a space
    evt.click(ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName));

    // Comment
    if (comment != null && !comment.isEmpty()) {
      evt.type(ELEMENT_SHARE_DOCUMENT_COMMENT, comment, false);
    }
    // Click Share button
    evt.click(ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Share"));
  }

  /**
   * Go to drive - folder Date: Oct 27, 2015
   * @param path String
   * @param drive  String
   */
  public void goToPathOfDrive(String path, String drive) {
    info("Go to selected Drive");
    evt.waitForAndGetElement(ELEMENT_ACTIONBAR_SHOWDRIVES);
    evt.click(ELEMENT_ACTIONBAR_SHOWDRIVES);
    evt.waitForAndGetElement(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}", drive));
    evt.click(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}", drive));
    info("Go to folder");
    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      selectNode(arrayElement);
    }
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

  /**
   * Select a value for behavior by quynhpt date 16/01/2015
   */
  public enum defineValueBehavior {
    CREATE_NEW, REMOVE_EXISTING, REPLACE_EXISTING, THROW_EXEPTION;
  }
}
