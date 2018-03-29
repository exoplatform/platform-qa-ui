package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.DocumentManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * Created by ilyes on 30/10/17.
 */
@Tag("smoke")
@Tag("ecms")
public class ECMSManageProjectTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  DocumentManagement documentManagement;

  ManageLogInOut     manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    documentManagement = new DocumentManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
  }

  @Test
  public void test01_createFolderInListView() {
    info("test01 create Folder In List View");
    String folder = "folder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToListView();
    documentManagement.createFolder("/", folder, false);
    ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(folder)).should(Condition.exist);
    documentManagement.deleteFolder("/", folder, false);
  }

  @Test
  public void test02_deleteFolderInListView() {
    info("test02 delete Folder In List View");
    String folder = "folder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToListView();
    documentManagement.createFolder("/", folder, false);
    documentManagement.deleteFolder("/", folder, false);
    ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(folder)).shouldNot(Condition.exist);
  }

  @Test
  public void test03_renameFolderInListView() {
    info("test03 rename Folder In List View");
    String folder = "folder" + getRandomNumber();
    String newfolder = "newfolder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToListView();
    documentManagement.createFolder("/", folder, false);
    documentManagement.renameFolderInAdminView("/", folder, newfolder, false);
    ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(newfolder)).should(Condition.visible);
    documentManagement.deleteFolder("/", newfolder, false);
  }

  @Test
  public void test04_addfolderInIconsViewFromActionBar() {
    info("test04 add folder In Icons View From Action Bar");
    String folder = "folder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToIconsView();
    documentManagement.createFolder("/", folder, false);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder)).should(Condition.exist);
    documentManagement.deleteFolder("/", folder, true);
  }

  @Test
  public void test05_addfolderInIconsViewWithRighClick() {
    info("test05 add folder In Icons View With Righ Click");
    String folder = "folder" + getRandomNumber();
    String folder1 = "folder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToIconsView();
    documentManagement.createFolder("/", folder1, false);
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    ELEMENT_INPUT_PATH.setValue("/").pressEnter();
    documentManagement.createFolder("/", folder, true);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder)).should(Condition.exist);
    documentManagement.deleteFolder("/", folder, true);
    documentManagement.deleteFolder("/", folder1, true);
  }

  @Test
  public void test06_deleteforderInIconsView() {
    info("test06 delete forder In Icons View");
    String folder = "folder" + getRandomNumber();
    String folder1 = "folder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToIconsView();
    documentManagement.createFolder("/", folder1, false);
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    documentManagement.createFolder("/", folder, true);
    documentManagement.deleteFolder("/", folder, true);
    documentManagement.deleteFolder("/", folder1, true);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder)).shouldNot(Condition.exist);
  }

  @Test
  public void test07_renameFolderInIconsView() {
    info("test07 rename Folder In Icons View");
    String folder = "folder" + getRandomNumber();
    String newfolder = "newfolder" + getRandomNumber();
    homePagePlatform.goToDocuments();
    documentManagement.goToIconsView();
    documentManagement.createFolder("/", folder, false);
    documentManagement.renameFolderInAdminView("/", folder, newfolder, true);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(newfolder)).should(Condition.visible);
    refresh();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    ELEMENT_INPUT_PATH.setValue("/").pressEnter();
    documentManagement.deleteFolder("/", newfolder, true);
  }

  @Test
  public void test08_uploadFile() {
    info("test08 upload File");
    homePagePlatform.goToDocuments();
    documentManagement.goToListView();
    $(byId("MultiUploadInputFiles")).uploadFromClasspath("eXo-Platform.png");
    $(byClassName("progress")).waitUntil(Condition.visible, Configuration.timeout);
    $(byClassName("progress")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    refresh();
    ELEMENT_LIST_DOCUMENT_FOLDER.find(byText("eXo-Platform")).waitUntil(Condition.visible, Configuration.timeout);
    refresh();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    ELEMENT_INPUT_PATH.setValue("/").pressEnter();
    documentManagement.deleteFile("/", "eXo-Platform", false);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText("eXo-Platform")).shouldNot(Condition.exist);

  }

  @Test
  public void test09_deleteFile() {
    info("test09 delete File");
    homePagePlatform.goToDocuments();
    documentManagement.goToListView();
    $(byId("MultiUploadInputFiles")).uploadFromClasspath("eXo-Platform.png");
    $(byClassName("progress")).waitUntil(Condition.visible, Configuration.timeout);
    $(byClassName("progress")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    refresh();
    ELEMENT_LIST_DOCUMENT_FOLDER.find(byText("eXo-Platform")).waitUntil(Condition.visible, Configuration.timeout);
    refresh();
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    ELEMENT_INPUT_PATH.setValue("/").pressEnter();
    documentManagement.deleteFile("/", "eXo-Platform", false);
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText("eXo-Platform")).shouldNot(Condition.exist);

  }
}
