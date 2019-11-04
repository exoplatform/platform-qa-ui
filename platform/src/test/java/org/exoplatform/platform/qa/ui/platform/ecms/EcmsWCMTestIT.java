package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_AVATAR_CHANGELANGUAGE;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOPBAR_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.exoplatform.platform.qa.ui.platform.chat.ChatOnSiteNotificationTestIT;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.commons.pageobject.Platform;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SEOManagement;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ChangeLanguages;

@Tag("ecms")
@Tag("sniff")
public class EcmsWCMTestIT extends Base {
  NavigationToolbar    navigationToolbar;

  SiteExplorerHome     siteExplorerHome;

  CreateNewDocument    createNewDocument;

  PageCreationWizard   pageCreationWizard;

  PortalManageSites    portalManageSites;

  PortalManagePages    portalManagePages;

  NavigationManagement navigationManagement;

  ManageLogInOut       manageLogInOut;

  ContentList          contentList;

  ChangeLanguages      changeLanguages;

  SEOManagement        seoManagement;

  HomePagePlatform     homePagePlatform;


  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    pageCreationWizard = new PageCreationWizard(this);
    portalManageSites = new PortalManageSites(this);
    portalManagePages = new PortalManagePages(this);
    navigationManagement = new NavigationManagement(this);
    contentList = new ContentList(this);
    changeLanguages = new ChangeLanguages(this);
    seoManagement = new SEOManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");

  }

  /**
   * <li>Case ID:116568.</li>
   * <li>Test Case Name: Create new Content List viewer page with mode "By
   * Folder".</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create new Content
   * List viewer page with mode "By Folder" Step Description: - Login acme by
   * admin/web contributor - Go to Administration - -> Content - -> Sites
   * Explorer. - Add some web contents in Sites Management/acme/web contents (or
   * documents folder) - Go to acme homepage, select Edit/ Page/Add Page to create
   * new page.+ Fill node name+ Click Next -> Next. + Drag and drop
   * Content/Content list portlet from page editor+ Click Edit portlet icon of
   * this Content list portlet+ Choose By Folder option+ Click [Select folder
   * path] icon to some folder that you want to view+ Click [Save] button in
   * Multiple content selector panel+ Click [Save] button in Content list viewer
   * configuration form+ Click [Close] button inContent list viewer configuration
   * form -Click Finish icon in Page editor - Make created web content public.
   * Input Data: Expected Outcome: - All web contents/documents in selected folder
   * are listed in Content List Viewer portlet in this page
   */
  @Test
  @Tag("eabis")
  public void test01_CreateNewContentListViewerPageWithModeByFolder() {
    info("Test 1: Create new Content List viewer page with mode By Folder");
    String content = "content" + getRandomNumber();

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    // Create node
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content);
    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true, false);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    pageCreationWizard.addApplication($(ELEMENT_APPLICATION_CONTENT_TAB), $(byId("Content/ContentListViewerPortlet")));
    pageCreationWizard.addContentlistByFolder("General Drives/Sites Management", "intranet");
    navigationToolbar.goToEditContent();
    sleep(Configuration.timeout);
    ($(byXpath("(//a[contains(text(),'${content}')]/following::a[text()='Read more'])[1]".replace("${content}",content.substring(0,11))))).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible, Configuration.timeout);
    info("Delete Data test");
    info("Delete created file");
    sleep(Configuration.timeout);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content, true);
    info("Delete created page");
    navigationToolbar.goToPotalPages();
    portalManagePages.deletePage(content, "");
  }

  /**
   * <li>Case ID:116570.</li>
   * <li>Test Case Name: Create new Content List Viewer page with mode "By
   * Contents".</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create new Content
   * List Viewer page with mode "By Contents" Step Description: - Login acme by
   * admin/web contributor - Go to content explorer, add some web contents Sites
   * Management/acme/web contents (or documents folder) - Go to acme home page,
   * select Edit/ Page/Add Page to create new page. + Fill node name+ Next+ Next +
   * Drag and drop Content/Content list portlet from page editor+ Click edit
   * portlet icon of this Content list portlet+ Choose By Contents option+ Click
   * [Select folder path] icon to some web contents/documents that you want to
   * view+ Click [Save] button in Multiple content selector panel+ Click [Save]
   * button in Content list viewer configuration form+ Click [Close] button
   * inContent list viewer configuration form -Click Finish icon in Page editor
   * Input Data: Expected Outcome: All selected web content/documents are
   * displayed as list in List Content Viewer page
   */
  @Test
  @Tag("eabis")
  public void test02_CreateNewContentListViewerPageWithModeByContents() {
    info("Test 2: Create new Content List Viewer page with mode By Contents");
    String content = "content" + getRandomNumber();

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    // Create node
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content);
    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true, false);
    sleep(Configuration.timeout);
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    pageCreationWizard.addApplication($(ELEMENT_APPLICATION_CONTENT_TAB), $(byId("Content/portlet_ContentListViewerPortlet")));
    pageCreationWizard.addContentListByContent("General Drives/Sites Management/intranet", content);
    navigationToolbar.goToEditContent();
    ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible, Configuration.timeout);
    info("Delete Data test");
    info("Delete created file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content, true);
    info("Delete created page");
    navigationToolbar.goToPotalPages();
    portalManagePages.deletePage(content, "");
  }

  /**
   * <li>Case ID:116571.</li>
   * <li>Test Case Name: Create Single Content Viewer page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create Single
   * Content Viewer page Step Description: - Login acme by Admin/Web contributor -
   * Choose Edit/Page/Add Page+ Fill name+ Next+ Next+ Drag and drop Content/
   * Content Detail portlet to this Page+ Click Edit icon to edit this porlet+
   * Select [Content Path] where stores these web contents/documents+ Click icon
   * in Action column in the right to select one of them+ Click Save+ Click Close+
   * Click Finish icon in page editor Input Data: Expected Outcome: - The selected
   * web content/document is displayed
   */
  @Test
  @Tag("eabis")
  public void test03_CreateSingleContentViewerPage() {
    info("Test 3: Create Single Content Viewer page");
    String content = "content" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    // Create node
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content);
    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true, false);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    pageCreationWizard.addContentDetail("General Drives/Sites Management/intranet", content);
    navigationToolbar.goToEditContent();
    ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible, Configuration.timeout);
    info("Delete Data test");
    info("Delete created file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content, true);
    info("Delete created page");
    navigationToolbar.goToPotalPages();
    portalManagePages.deletePage(content, "");
  }

  /**
   * <li>Case ID:116606.</li>
   * <li>Test Case Name: Show draft/public content from page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Show draft /public
   * content from page Step Description: - Login acme by Admin/web contributor -
   * Go to Content Explorer/Sites Management/acme - Add new document/web content -
   * Go to acme home page, add a SCV or CLV page with these contents - Open page
   * contains document/web content above - Change to Edit mode [1] - Return to
   * Content Explorer/Sites Management/acme - Select the document above - Click
   * Publications in Action bar - Choose Publish status. - Go to page contains
   * document/web content above - Change to Public mode [2] Input Data: Expected
   * Outcome: - [1] Selected document/web content is displayed into this page with
   * draft - [2] Selected document/web content is published into this page with
   * published status
   */
  @Test
  @Tag("eabis")
  public void test07_ShowDraftpublicContentFromPage() {
    info("Test 7: Show draft/public content from page");
    String content = "content" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    // Create node
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content, content);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content);
    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true, false);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    pageCreationWizard.addApplication($(ELEMENT_APPLICATION_CONTENT_TAB), $(byId("Content/portlet_ContentListViewerPortlet")));
    pageCreationWizard.addContentListByContent("General Drives/Sites Management/intranet", content);
    navigationToolbar.goToEditContent();
    // Verify that Selected document/web content is displayed into this page with
    // draft
    ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible, Configuration.timeout);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode(content);
    siteExplorerHome.goToPublication();
    siteExplorerHome.changeStatusPulication("Published");
    navigationToolbar.goToUnEditContent();
    info("Delete Data test");
    info("Delete created file");
    refresh();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content, true);
    info("Delete created page");
    navigationToolbar.goToPotalPages();
    portalManagePages.deletePage(content, "");

  }

  /**
   * <li>Case ID:116612.</li>
   * <li>Test Case Name: Edit Content List Viewer page with mode "By
   * Contents".</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Create new Content List Viewer page with mode "By Contents" Input Data: -
   * Login acme by admin/web contributor - Go to content explorer, add some web
   * contents Sites Management/acme/web contents (or documents folder) - Go to
   * acme home page, click Edit/ Page/Add Page to create new page. + Fill node
   * name+ Next+ Next + Drag and drop Content/Content list portlet from page
   * editor+ Click edit portlet icon of this Content list portlet+ Choose By
   * Contents option+ Click [Select folder path] icon to some web
   * contents/documents that you want to view+ Click [Save] button in Multiple
   * content selector panel+ Click [Save] button in Content list viewer
   * configuration form+ Click [Close] button inContent list viewer configuration
   * form -Click Finish icon in Page editor Expected Outcome: All selected web
   * content/documents are displayed as list in List Content Viewer page Step
   * number: 2 Step Name: Step Description: Step 2: Edit Content List Viewer page
   * with mode "By Contents" Input Data: - Open CLV above - Click Edit/ Page/Page
   * Layout to edit page + Click edit portlet icon of this Content list portlet+
   * Choose By Contents option+ Click [Select folder path] icon to some web
   * contents/documents that you want to view+ Click [Save] button in Multiple
   * content selector panel+ Click [Save] button in Content list viewer
   * configuration form+ Click [Close] button inContent list viewer configuration
   * form -Click Finish icon in Page editor Expected Outcome: All selected web
   * content/documents you've chosen are displayed as list in List Content Viewer
   * page
   */
  @Test
  @Tag("eabis")
  public void test08_EditContentListViewerPageWithModeByContents() {
    info("Test 8: Edit Content List Viewer page with mode By Contents");
    String title = "title" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    info("Create webcontent1");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content1, content1);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content1);
    info("Select acme folder");
    siteExplorerHome.selectNode("intranet");
    info("Create webcontent2");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content2, content2);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content2);
    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(title, true, "English", title, true, false);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    pageCreationWizard.addApplication($(ELEMENT_APPLICATION_CONTENT_TAB), $(byId("Content/portlet_ContentListViewerPortlet")));
    pageCreationWizard.addContentlistByFolder("General Drives/Sites Management", "intranet");
    navigationToolbar.goToEditContent();
    // Verify that all webcontents are shown in Content list View page
    ELEMENT_LIST_CONTENT.find(byText(content1)).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_LIST_CONTENT.find(byText(content2)).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_EDIT_CLV).hover();
    $(ELEMENT_EDIT_PREFERENCE).click();
    check(ELEMENT_CONTENT_LIST_BY_CONTENT_MODE, 2);
    contentList.selectFolderContent("General Drives/Sites Management/intranet", content1);
    $(ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN).click();
    $(ELEMENT_CONTENT_LIST_PREFERENCE_SAVE_BTN).click();
    // Verify that all webcontents are shown in Content list View page
    $(byXpath(ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}", content1))).waitUntil(Condition.visible,
                                                                                           Configuration.timeout);
    $(byXpath(ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}", content2))).waitUntil(Condition.not(Condition.visible),
                                                                                           Configuration.timeout);
    info("Delete create files");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content1, true);
    siteExplorerHome.deleteData(content2, true);
    info("Delete created page");
    navigationToolbar.goToPotalPages();
    portalManagePages.deletePage(title, "");

  }

  /**
   * <li>Case ID:116613.</li>
   * <li>Test Case Name: Edit Content List viewer page with mode "By Folder".</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Create new Content List viewer page with mode "By Content" Input Data: -
   * Login acme by admin/web contributor - Go to content explorer, add some web
   * contents Sites Management/acme/web contents (or documents folder) - Go to
   * acme home page, select Edit/ Page/Add Page to create new page. + Fill node
   * name+ Next+ Next + Drag and drop Content/Content list portlet from page
   * editor+ Click edit portlet icon of this Content list portlet+ Choose By
   * Folder option+ Click [Select folder path] icon to some folder that you want
   * to view+ Click [Save] button in Multiple content selector panel+ Click [Save]
   * button in Content list viewer configuration form+ Click [Close] button
   * inContent list viewer configuration form -Click Finish icon in Page editor
   * Expected Outcome: - All web contents/documents in selected folder are listed
   * in Content List Viewer portlet in this page Step number: 2 Step Name: Step
   * Description: Step 2: Edit Content List viewer page with mode "By Folder"
   * Input Data: - Open CLV above - Click Edit/ Page/Page Layout to edit page+
   * Click edit portlet icon of this Content list portlet+ Choose By Folder
   * option+ Click [Select folder path] icon to some folder that you want to view+
   * Click [Save] button in Multiple content selector panel+ Click [Save] button
   * in Content list viewer configuration form+ Click [Close] button inContent
   * list viewer configuration form -Click Finish icon in Page editor Expected
   * Outcome: - All web contents/documents in selected folder are listed in
   * Content List Viewer portlet in this page
   */

  @Test
  @Tag("eabis")
  public void test09_EditContentListViewerPageWithModeByFolder() {
    info("Test 9: Edit Content List viewer page with mode By Folder");
    String title = "title" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");

    info("Create webcontent1");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content1, content1);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content1);

    info("Select acme folder");
    siteExplorerHome.selectNode("intranet");

    info("Create webcontent2");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content2, content2);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content2);
    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(title, true, "English", title, true, false);
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    pageCreationWizard.addApplication($(ELEMENT_APPLICATION_CONTENT_TAB), $(byId("Content/portlet_ContentListViewerPortlet")));
    pageCreationWizard.addContentListByContent("General Drives/Sites Management/intranet", content1);

    navigationToolbar.goToEditContent();
    ELEMENT_LIST_CONTENT.find(byText(content1)).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_LIST_CONTENT.find(byText(content2)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(ELEMENT_EDIT_CLV).hover();
    $(ELEMENT_EDIT_PREFERENCE).click();
    check(ELEMENT_CONTENT_LIST_BY_FOLDER_MODE, 2);
    contentList.selectFolderContent("General Drives/Sites Management", "intranet");
    click(ELEMENT_CONTENT_LIST_PREFERENCE_SAVE_BTN);
    ELEMENT_LIST_CONTENT.find(byText(content1)).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_LIST_CONTENT.find(byText(content2)).waitUntil(Condition.visible, Configuration.timeout);
    info("Delete created files");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content1, true);
    siteExplorerHome.deleteData(content2, true);
    info("Delete created page");
    navigationToolbar.goToPotalPages();
    portalManagePages.deletePage(title, "");

  }

  /**
   * <li>Case ID:116614.</li>
   * <li>Test Case Name: Edit Single Content Viewer page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Create Single Content Viewer page Step
   * Description: - Login acme by Admin/Web contributor - Choose Edit/Page/Add
   * Page+ Fill name+ Next+ Next+ Drag and drop Content/ Content Detail portlet to
   * this Page+ Click Edit icon to edit this porlet+ Select [Content Path] where
   * stores these web contents/documents+ Click icon in Action column in the right
   * to select one of them+ Click Save+ Click Close+ Click Finish icon in page
   * editor Input Data: Expected Outcome: - The selected web content/document is
   * displayed
   */
  /*
   * Step number: 2 Step Name: Step 2: Edit Single Content Viewer page Step
   * Description: - Login acme by Admin/Web contributor - Open page above - Click
   * Edit/ Page/ Page Layout+ Click Edit icon to edit this porlet+ Select [Content
   * Path] where stores these web contents/documents+ Click icon in Action column
   * in the right to select one of them+ Click Save+ Click Close+ Click Finish
   * icon in page editor Input Data: Expected Outcome: - The new selected web
   * content/document is displayed
   */
  @Test
  @Tag("eabis")
  public void test10_EditSingleContentViewerPage() {
    info("Test 10 Edit Single Content Viewer page");
    String title = "title" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    info("Create webcontent 1");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content1, content1);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content1);
    info("Select acme folder");
    siteExplorerHome.selectNode("intranet");
    info("Create webcontent2");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(content2, content2);
    createNewDocument.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content2);
    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(title, true, "English", title, true, false);
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    pageCreationWizard.addContentDetail("General Drives/Sites Management/intranet", content1);
    $(byTitle("Portlet Mode")).click();
    $(byTitle("Edit")).click();
    sleep(Configuration.timeout);
    contentList.selectFolderContent("intranet", content2);
    sleep(Configuration.timeout);
    executeJavaScript("window.scrollBy(0,-350);", "");
    $(byText("Done")).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete created files");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content1, true);
    $(byText(content2)).waitUntil(Condition.appears, Configuration.timeout);
    siteExplorerHome.deleteData(content2, true);
    info("Delete create page");
    navigationToolbar.goToPotalPages();
    portalManagePages.deletePage(title, "group");

  }

  /**
   * <li>Case ID:116661.</li>
   * <li>Test Case Name: Manage the title.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Manage the title
   * Step Description: - Log in acme home page as admin - Go to Edit/ Page/ SEO -
   * Enter the title that you want. - Click Save Input Data: Expected Outcome: You
   * can see the new title will be displayed on TITLE element of your web page.
   */
  @Test
  public void test11_ManageTheTitle() {
    info("Test 15 Manage the title");
    String title = "title" + getRandomNumber();
    navigationToolbar.goToSEO();
    $(ELEMENT_SEO_TITLEBOX).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(title);
    $(ELEMENT_SEO_SAVE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SEO_CLOSE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    refresh();
    // Verify that the title of the page is changed
    assertEquals(Selenide.title(), title);
    navigationToolbar.goToSEO();
    $(byClassName("uiIconDelete")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    switchTo().alert().accept();
    $(ELEMENT_SEO_SAVE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SEO_CLOSE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    refresh();

  }

  /**
   * <li>Case ID:116662.</li>
   * <li>Test Case Name: Check SEO tool tips.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Check SEO tool
   * tips Step Description: - Log in acme home page as admin - Go to Edit/ Page/
   * SEO - Mouse over ? icon beside Description, Keywords, Priority Input Data:
   * Expected Outcome: Tool tips is shown for user to understand the SEO
   */
  @Test
  public void test12_CheckSEOToolTips() {
    info("Test 11 Check SEO tool tips");
    navigationToolbar.goToSEO();
    ELEMENT_SEO_HELPDESC.hover();
    $(ELEMENT_SEO_HELP_POPOVER).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SEO_HELPKEYWORD).hover();
    $(ELEMENT_SEO_HELP_POPOVER).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SEO_HELPPRIORITY).hover();
    $(ELEMENT_SEO_HELP_POPOVER).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SEO_CLOSE).click();
  }

  /**
   * <li>Case ID:116637.</li>
   * <li>Test Case Name: Add SEO metadas with localization.</li>
   * <li>Pre-Condition: <title> ...
   * </title><meta name="description" content=" ... " /><meta name="keywords"
   * content=" ... " /><meta name="robots" content=" ... " /></li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add SEO metadas
   * with localization Step Description: - Log in acme home page as admin - Go to
   * Edit/ Page/ SEO - Select a language to add SEO metadata for it from select
   * box on the top of panel. - Fill data for all the fields - Click Save -
   * Refresh page - Change to the language selected for adding SEO - Right click
   * to view page source Input Data: Expected Outcome: - SEO information is saved
   * successfully - Selected language is listed on the left of SEO - Page source
   * will be shown likein precondition - Go to Sites management/acme/ SEO folder
   * there will be sitemap.xml file
   */
  @Test
  @Tag("eabis")
  public void test13_AddSEOMetadasWithLocalization() {
    info("Test 12 Add SEO metadas with localization");
    String title = "title" + getRandomNumber();
    String language1 = "French";
    info("language1 is:" + language1);
    String apply1 = "Apply";
    String language2 = "English";
    info("language2 is:" + language2);
    String apply2 = "Appliquer";
    navigationToolbar.goToSEO();
    $(ELEMENT_SEO_LANGUAGE_SHOW).click();
    $(ELEMENT_SEO_LANGUAGE_SELECTBOX).selectOption(language1);
    $(ELEMENT_SEO_TITLEBOX).setValue(title);
    $(ELEMENT_SEO_SAVE).click();
    $(ELEMENT_SEO_CLOSE).click();
    refresh();
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage(language1, apply1);
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage(language2, apply2);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/SEO", "Site Management");
    // Verify that sitemaps is created in SEO folder
    $(byXpath(ELEMENT_SE_NODE.replace("${node}", "sitemaps"))).waitUntil(Condition.visible, Configuration.timeout);
    info("Delete SEO folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/SEO", "Site Management");
    siteExplorerHome.deleteData("SEO");
    homePagePlatform.goToHomePage();
    info("Detete added language on SEO management");
    navigationToolbar.goToSEO();
    seoManagement.deleteLanguage(language1);
  }

  /**
   * <li>Case ID:116638.</li>
   * <li>Test Case Name: Update SEO metadatas with localization.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:116660.</li>
   * <li>Test Case Name: Delete localize SEO metadatas.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Edit SEO metadata
   * with localization Step Description: - Log in acme home page as admin - Go to
   * Edit/ Page/ SEO - Select a language that you want to update SEO metadata form
   * languages list - Update fields that you want to modify - Save Input Data:
   * Expected Outcome: - SEO information is saved - Language is updated - View
   * page source, updated SEO information is shown - Sitemap.xml is updated
   */

  @Test
  @Tag("eabis")
  public void test14_UpdateSEOMetadatasWithLocalization() {
    info("Test 14_15 Update SEO metadatas with localization");
    String title = "title" + getRandomNumber();
    String language1 = "French";
    info("language1 is:" + language1);
    String apply1 = "Apply";
    String language2 = "English";
    info("language2 is:" + language2);
    String apply2 = "Appliquer";
    navigationToolbar.goToSEO();
    $(ELEMENT_SEO_LANGUAGE_SHOW).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    $(ELEMENT_SEO_LANGUAGE_SELECTBOX).selectOption(language1);
    $(ELEMENT_SEO_TITLEBOX).setValue(title);
    $(ELEMENT_SEO_SAVE).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    $(ELEMENT_SEO_CLOSE).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    refresh();
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage(language1, apply1);
    navigationToolbar.goToChangeLanguage();
    info("Changed language is:" + language2);
    changeLanguages.changeLanguage(language2, apply2);
    // Verify that sitemaps file is updated
    $(byText("sitemaps")).waitUntil(Condition.visible, Configuration.timeout);
    info("Delete SEO folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/SEO", "Site Management");
    siteExplorerHome.deleteData("SEO");
    homePagePlatform.goToHomePage();
    navigationToolbar.goToSEO();
    seoManagement.deleteLanguage(language1);
  }

  @Test
  @Tag("eabis")
  public void test15_DeleteSEOMetadatasWithLocalization() {
    info("Test 14_15 Update SEO metadatas with localization");
    String title = "title" + getRandomNumber();
    String language1 = "French";
    info("language1 is:" + language1);
    String apply1 = "Apply";
    String language2 = "English";
    info("language2 is:" + language2);
    String apply2 = "Appliquer";
    navigationToolbar.goToSEO();
    $(ELEMENT_SEO_LANGUAGE_SHOW).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
    $(ELEMENT_SEO_LANGUAGE_SELECTBOX).sendKeys(language1);
    $(ELEMENT_SEO_TITLEBOX).setValue(title);
    $(ELEMENT_SEO_SAVE).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_SEO_CLOSE).waitUntil(Condition.visible,Configuration.timeout).click();
    refresh();
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage(language1, apply1);
    navigationToolbar.goToChangeLanguage();
    info("Changed language is:" + language2);
    changeLanguages.changeLanguage(language2, apply2);
    // Verify that sitemaps file is updated
    sleep(Configuration.timeout);
    $(byText("sitemaps")).waitUntil(Condition.visible, Configuration.collectionsTimeout);
    info("Delete SEO folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/SEO", "Site Management");
    siteExplorerHome.deleteData("SEO");
    homePagePlatform.goToHomePage();
    navigationToolbar.goToSEO();
    seoManagement.deleteLanguage(language1);
  }
}
