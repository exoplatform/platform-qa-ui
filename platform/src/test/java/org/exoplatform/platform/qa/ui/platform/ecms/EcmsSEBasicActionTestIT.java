package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER2;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
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
import org.exoplatform.platform.qa.ui.core.PLFData;
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

    /**
     * <li> Case ID:116565.</li>
     * <li> Test Case Name: Add symlink for a node.</li>
     * Step Number: 1
     * Step Name: -
     * Step Description:
     * Step 1: Add symlink for a node
     * Input Data:
     * - Select a node (folder)
     * - Right click and select Add Symlink icon
     * - Select a node to add symlink
     * - Click Save
     * Expected Outcome:
     * Symlink for a node is added successfully. Show symlink node as a child node
     * <p>
     * Step number: 2
     * Step Name:
     * Step Description:
     * Step 2 : Move Symlink
     * Input Data:
     * - Go back to destination symlink node
     * Expected Outcome:
     * - Destination symlink node is opened
     * <p>
     * Step number: 3
     * Step Name:
     * Step Description:
     * Step 3 : Add file in the node
     * Input Data:
     * - Add a web content file
     * Expected Outcome:
     * The webcontent file created must appears in the Symlink
     */
    @Test
    public void test01_AddSymlinkForANode() {
        info("Test 1: Add symlink for a node");


        getRandomNumber();
        String folderTitle = "folderTitle" + getRandomNumber();
        String node = folderTitle.toLowerCase();

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

        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", node + ".lnk"))).click();
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node))).click();
        siteExplorerHome.deleteData(node + ".lnk");
        siteExplorerHome.deleteData(folderTitle);
    }

    /**
     * <li> Case ID:116567.</li>
     * <li> Test Case Name: Copy/paste a node.</li>
     * <li> Case ID:116574.</li>
     * <li> Test Case Name: Cut/paste a node.</li>
     * <li> Case ID:116576.</li>
     * <li> Test Case Name: Drag and drop a node.</li>
     * Step Number: 1
     * Step Name: -
     * Step Description:
     * Step 1: Copy/paste a node
     * Input Data:
     * - Right click on a node and select Copy
     * - Right click on destination node and select Paste
     * Expected Outcome:
     * Node is pasted into destination node
     */
    @Test
    public void test02_CopypasteANode() {

        String destination = "intranet";
        String secondDestination = "shared";

        String titleCommonNode = "titleCommonNode" + getRandomNumber();

        navigationToolbar.goToSiteExplorer();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();
        info("Test 2: Copy/paste a node");
        siteExplorerHome.copyPasteNode(titleCommonNode, destination);
        info("delete data");
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))).click();
        siteExplorerHome.deleteData(titleCommonNode,false);
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination))).click();
        siteExplorerHome.deleteData(titleCommonNode);
    }

    @Test
    public void test03_CutpasteANodeANode() {

        String destination = "intranet";
        String secondDestination = "shared";

        String titleCommonNode = "titleCommonNode" + getRandomNumber();

        navigationToolbar.goToSiteExplorer();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();
        info("Test 3: Cut/paste a node");
        siteExplorerHome.cutPasteNode(titleCommonNode, secondDestination);
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination))).click();
        siteExplorerHome.deleteData(titleCommonNode);
    }

    @Test
    public void test_04_DragAndDropANode() {

        String destination = "intranet";
        String secondDestination = "shared";

        String titleCommonNode = "titleCommonNode" + getRandomNumber();

        navigationToolbar.goToSiteExplorer();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();
        info("Test 4: Drag and drop a node");
        ELEMENT_CONTENT_LIST.find(byText(titleCommonNode)).dragAndDropTo($(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))));
        manageAlert.acceptAlert();
        $(ELEMENT_OK_BUTTON_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))).click();
        siteExplorerHome.deleteData(titleCommonNode);

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
    public void test06_LockANode_() {
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
    @BugInPLF("ECMS-7673")
    public void test07_UnlockANode() {
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

    /**
     * <li> Case ID:116585.</li>
     * <li> Test Case Name: Delete Clipboard.</li>
     * <li> Case ID:116659.</li>
     * <li> Test Case Name: Paste Clipboard.</li>
     * Step Number: 1
     * Step Name: -
     * Step Description:
     * Step 1: Delete Clipboard
     * Input Data:
     * - Perform cut/copy a node
     * - Go to other node
     * - Go to Clipboard : Click Clipboard icon on side bar
     * - Click corresponding Delete icon of a action
     * Expected Outcome:
     * Clipboard pane is shown . When delete a action (copy/cut) the action will be not shown any more at Clipboard
     */

    @Test
    public void test09_DeleteClipboard() {

        String titleCommonNode = "titlecommonnode" + getRandomNumber();

        navigationToolbar.goToSiteExplorer();
        click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();

        // clean the clipboard
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).click();
        $(ELEMENT_CLIPBOARD_CLEAR_ALL).click();
        $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
        info("Test 9: Delete Clipboard");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).click();
        $(byAttribute("data-original-title", "collaboration:/sites/" + titleCommonNode)).parent().parent().find(byClassName("uiIconEcmsDelete")).click();
        $(byXpath(ELEMENT_CLIPBOARD_DELETE_NODE.replace("{$node}", titleCommonNode))).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        siteExplorerHome.deleteData(titleCommonNode);
    }

    @Test
    public void test10_PasteClipboard() {

        String titleCommonNode = "titlecommonnode" + getRandomNumber();

        navigationToolbar.goToSiteExplorer();
        click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
        siteExplorerHome.goToAddNewContent();
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
        createNewDocument.saveAndClose();

        // clean the clipboard
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).click();
        $(ELEMENT_CLIPBOARD_CLEAR_ALL).click();
        $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
        $(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();

        info("Test 10 Paste Clipboard");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_COPY).click();
        $(ELEMENT_SITEEXPLORER_CLIPBOARD).click();
        $(byAttribute("data-original-title", "collaboration:/sites/" + titleCommonNode)).parent().parent().find(byClassName("uiIconEcmsPaste")).click();
        $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();

        // delete the past node
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).contextClick();
        $(ELEMENT_SITEEXPLORER_ACTION_DELETE).click();
        $(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).click();
        $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).waitUntil(Condition.visible, Configuration.timeout);

        siteExplorerHome.deleteData(titleCommonNode);
    }

}
