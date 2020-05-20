package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

/**
 * @author eXo
 */
@Tag("sniff")
@Tag("ecms")
public class EcmsSEBasicActionTestIT extends Base {
    NavigationToolbar navigationToolbar;
    SiteExplorerHome siteExplorerHome;
    CreateNewDocument createNewDocument;
    ManageLogInOut manageLogInOut;
    ManageAlert manageAlert;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        siteExplorerHome = new SiteExplorerHome(this);
        createNewDocument = new CreateNewDocument(this);
        manageLogInOut = new ManageLogInOut(this);
        manageAlert = new ManageAlert(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    @Test
    @Tag("eabis")
    public void test01_AddSymlinkThenCopypasteThenCutpasteThenDragAndDropANodeForANode() {

        String destination = "intranet";
        String secondDestination = "shared";
        String titleCommonNode = "titleCommonNode" + getRandomNumber();
        String titleCommonNode2 = "titleCommonNode" + getRandomNumber();
        String titleCommonNode3 = "titleCommonNode" + getRandomNumber();
        String folderTitle = "folderTitle" + getRandomNumber();
        String node = folderTitle.toLowerCase();

        info("Add symlink for a node");
        navigationToolbar.goToSiteExplorer();
        siteExplorerHome.goToAddNewFolder();
        siteExplorerHome.createFolder(folderTitle, "Content Folder");
        siteExplorerHome.addSymlink(folderTitle);
        $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node + ".lnk"))).waitUntil(Condition.visible, Configuration.timeout);

        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", folderTitle))).click();
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(node, node);
        createNewDocument.saveAndClose();

        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", node + ".lnk"))).waitUntil(Condition.visible,Configuration.timeout).click();
        sleep(Configuration.timeout);
        $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        siteExplorerHome.deleteData(node + ".lnk");
        siteExplorerHome.deleteData(folderTitle);

        navigationToolbar.goToSiteExplorer();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();
        info("Copy/paste a node");
        siteExplorerHome.copyPasteNode(titleCommonNode, destination);
        info("delete data");
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))).click();
        siteExplorerHome.deleteData(titleCommonNode,false);
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination))).click();
        siteExplorerHome.deleteData(titleCommonNode);

        navigationToolbar.goToSiteExplorer();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode2, titleCommonNode2);
        createNewDocument.saveAndClose();
        info("Cut/paste a node");
        siteExplorerHome.cutPasteNode(titleCommonNode2, secondDestination);
        sleep(Configuration.timeout);
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination))).waitUntil(Condition.visible,Configuration.timeout).click();
        siteExplorerHome.deleteData(titleCommonNode2);

        navigationToolbar.goToSiteExplorer();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode3, titleCommonNode3);
        createNewDocument.saveAndClose();
        info("Drag and drop a node");
        sleep(Configuration.timeout);
        ELEMENT_CONTENT_LIST.find(byText(titleCommonNode3)).dragAndDropTo($(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))));
        manageAlert.acceptAlert();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))).click();
        siteExplorerHome.deleteData(titleCommonNode3);

    }

    /**
     * <li> Case ID:116580.</li>
     * <li> Test Case Name: Lock a node.</li>
     * <li> Case ID:116658.</li>
     * <li> Test Case Name: Unlock a node.</li>
     * Step Number: 1
     * Step Name: Step 1: Lock a node
     * Step Description:
     * - Right click on a node and select Lock
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Node is locked and the node has Lock icon on it. Other users can view and copy content of the locked node but cannot remove or make changes.
     */


    @Test
    @BugInPLF("ECMS-7673")
    public void test02_LockThenUnlockANode() {
        info("Test 6: Lock a node");

        String titleCommonNode = "titleCommonNode" + getRandomNumber();

        navigationToolbar.goToSiteExplorer();
        click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();
        siteExplorerHome.lockNode(titleCommonNode);

        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        navigationToolbar.goToSiteExplorer();
        $(byText(titleCommonNode)).contextClick();
        $(ELEMENT_SITEEXPLORER_LIST_LOCK_NODE).shouldNot(Condition.exist);
        $(ELEMENT_SITEEXPLORER_ACTION_DELETE).shouldNot(Condition.exist);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        info("Test 10 Unlock a node");
        navigationToolbar.goToSiteExplorer();
        siteExplorerHome.unlockNode(titleCommonNode);

        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        navigationToolbar.goToSiteExplorer();
        $(byText(titleCommonNode)).contextClick();
        $(ELEMENT_SITEEXPLORER_LIST_LOCK_NODE).should(Condition.visible);
        $(ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE).shouldNot(Condition.exist);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        siteExplorerHome.deleteData(titleCommonNode);
    }

    @Test
    @Tag("eabis")
    public void test03_PasteThenDeleteClipboard() {

        String titleCommonNode = "titlecommonnode" + getRandomNumber();
        String titleCommonNodeCopy = titleCommonNode + "(1)";
        String titleCommonNode1 = "titlecommonnode" + getRandomNumber();

        navigationToolbar.goToSiteExplorer();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).waitUntil(Condition.visible,Configuration.timeout);
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();
        sleep(3000);
        // clean the clipboard
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).waitUntil(Condition.visible,Configuration.collectionsTimeout).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        executeJavaScript("window.scrollBy(0,-500)");
        $(ELEMENT_CLIPBOARD_CLEAR_ALL).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).waitUntil(Condition.visible,Configuration.timeout).click();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).waitUntil(Condition.visible,Configuration.timeout).click();

        info("Paste Clipboard");
        $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).waitUntil(Condition.visible,Configuration.timeout).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byAttribute("data-original-title", "collaboration:/sites/" + titleCommonNode)).parent().parent().find(byClassName("uiIconEcmsPaste")).click();
        $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).waitUntil(Condition.visible,Configuration.timeout).click();

        // delete the past node
        $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byXpath("//span[@class='PopupTitle popupTitle' and contains(text(),'Delete')]")).dragAndDropTo($(byXpath("//div[@class='UITableColumnContainer']")));
        $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
        sleep(3000);
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNodeCopy))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).isDisplayed();
        siteExplorerHome.deleteData(titleCommonNodeCopy);

        navigationToolbar.goToSiteExplorer();
        click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode1, titleCommonNode1);
        createNewDocument.saveAndClose();

        // clean the clipboard
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode1))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).click();
        $(ELEMENT_CLIPBOARD_CLEAR_ALL).click();
        sleep(Configuration.timeout);
        $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).waitUntil(Condition.visible,Configuration.timeout).click();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();

        info("Delete Clipboard");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode1))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).click();
        $(byAttribute("data-original-title", "collaboration:/sites/" + titleCommonNode1)).parent().parent().find(byClassName("uiIconEcmsDelete")).click();
        $(byXpath(ELEMENT_CLIPBOARD_DELETE_NODE.replace("{$node}", titleCommonNode1))).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

        siteExplorerHome.deleteData(titleCommonNode1);
    }

}
