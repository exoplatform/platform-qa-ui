package org.exoplatform.platform.qa.ui.wiki.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class WikiHomePage {
    private final TestBase       testBase;

    public Dialog                dialog;

    public ManageAlert           alert;

    private ElementEventTestBase evt;

    public HomePagePlatform homePagePlatform;

    /**
     * constructor
     *
     * @param testBase TestBase
     */
    public WikiHomePage(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.dialog = new Dialog(testBase);
        this.homePagePlatform=new HomePagePlatform(testBase);
        this.alert = new ManageAlert(testBase);
    }

    /**
     * Go to "Add blank wiki page"
     */
    public void goToAddBlankPage() {
        info("--Go to add blank wiki page--");
        homePagePlatform.refreshUntil($(ELEMENT_ADD_PAGE_LINK),Condition.visible,Configuration.timeout);
        sleep(2000);
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        sleep(Configuration.timeout);
        $(ELEMENT_BLANK_PAGE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        sleep(2000);
        info("Blank wiki page is shown");
    }

    /**
     * Go to "Add template wiki page"
     */
    public void goToAddTemplateWikiPage() {
        info("--Go to add template wiki page--");
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        $(ELEMENT_FROM_TEMPLATE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    }

    /**
     * Go to "Add blank wiki page"
     */
    public void goToEditPage() {
        info("--Go to edit page--");
        sleep(Configuration.timeout);
        $(ELEMENT_EDIT_PAGE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    }

    /**
     * Go to Home Wiki Page
     */
    public void goToHomeWikiPage() {
        info("-- Go to wiki home page --");
        sleep(2000);
        $(ELEMENT_WIKI_HOME_PAGE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    }

    /**
     * Select any page
     *
     * @param title String
     */
    public void goToAPage(String title) {
        info("-- Go to wiki page --");
        $(byText(title)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        $(ELEMENT_WIKI_HOME_PAGE_TEXT).shouldNot(Condition.exist);
    }

    /**
     * Select any page
     *
     * @param title String
     */
    public void deleteWiki(String title) {
        homePagePlatform.refreshUntil($(byText(title)),Condition.visible,1000);
        info("Select the wiki page to delete");
        selectAPage(title);
        sleep(2000);
        info("Click on More link");
        $(ELEMENT_MORE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        sleep(2000);
        $(ELEMENT_DELETE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        sleep(2000);
        $(ELEMENT_CONFIRM_WIKI_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byText(title)).shouldNot(Condition.exist);

    }

    /**
     * Select any page
     *
     * @param title String
     */
    public void cancelDeleteWiki(String title) {
        if ($(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", title))).waitUntil(Condition.visible,Configuration.timeout) != null) {
            info("Go to delete wiki page...");
            info("Select the wiki page to delete");
            selectAPage(title);
            info("Click on More link");
            $(ELEMENT_MORE_LINK).click();
            if ($(ELEMENT_DELETE_LINK).waitUntil(Condition.visible,Configuration.timeout) == null) {
            } else {
                $(ELEMENT_DELETE_LINK).click();
            }
            $(ELEMENT_CANCEL_WIKI_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
            $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", title))).waitUntil(Condition.visible,Configuration.timeout);
        }
    }

    /**
     * Select a page
     *
     * @param page String
     */
    public void selectAPage(String page) {
        info("Go to a wiki page...");
        info("Select the wiki page");
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
        $(byXpath("//*[@id='iconTreeExplorer']//*[contains(text(),'${page}')]".replace("${page}",page))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        info("The page is shown");
    }

    /**
     * Go to "Go to My Draft"
     */
    public void goToMyDraft() {
        info("Click on Browser drop down");
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        info("Select wiki settings label");
        $(ELEMENT_SEARCH_BROWSERS_MY_DRAFT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    }

    /**
     * Open search page with a text
     *
     * @param text String
     */
    public void goTosearchPage(String text) {
        info("Input a text to search field");
        $(ELEMENT_SEARCH_TEXTBOX_POPUP).val(text);
        $(ELEMENT_SEARCH_BTN).click();
    }

    /**
     * Open Wiki Settings page
     */
    public void goToWikiSettingPage() {
        do{
        info("Click on Browser drop down");
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        info("Select wiki settings label");
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
            testBase.getExoWebDriver().getWebDriver().navigate().refresh();
        } while (!$(ELEMENT_WIKI_SETTING_ADD_MORE_TEMPALTE).exists());
    }

    /**
     * Open permission tab
     */
    public void openPermTab() {
        info("Open Permission tab");
        $(ELEMENT_WIKI_SETTING_PERM_TAB).click();
    }

    /**
     * Open permissions for the wiki
     */
    public void goToPermissions() {
        info("Permissions page");
        $(ELEMENT_MORE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        info("The permission popup is shown");
    }

    /**
     * Confirm messages
     *
     * @param isConfirm = true if want to click on Confirm button = false if want to
     *          click on Cancel button
     */
    public void confirmWaringMessage(Boolean isConfirm) {
        if (isConfirm) {
            if ($(ELEMENT_CONFIRM_POPUP_OK_BTN).is(Condition.visible)) {
                info("Click on OK button");
                $(ELEMENT_CONFIRM_POPUP_OK_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
            }
            if ($(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN).is(Condition.visible)) {

                info("Click on Confirm button");
                $(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN).click();
            }
            if ($(ELEMENT_CONFIRM_POPUP_YES_BTN).is(Condition.visible)) {
                info("Click on Yes button");
                $(ELEMENT_CONFIRM_POPUP_YES_BTN).click();
            }
            if ($(ELEMENT_CONFIRM_POPUP_YES_BTN).is(Condition.visible)) {
                info("Click on Yes button");
                $(ELEMENT_CONFIRM_POPUP_YES_BTN).click();
            }
            if ($(ELEMENT_WARNING_OK_BTN).is(Condition.visible)) {
                info("Click OK button");
                $(ELEMENT_WARNING_OK_BTN).click();
            }
        } else {
            if ($(ELEMENT_CONFIRM_POPUP_CANCEL_BTN).is(Condition.visible)) {
                info("Click on Cancel button");
                $(ELEMENT_CONFIRM_POPUP_CANCEL_BTN).click();
            }

            if ($(ELEMENT_CONFIRM_POPUP_NO_BTN).is(Condition.visible)) {
                info("Click on No button");
                $(ELEMENT_CONFIRM_POPUP_NO_BTN).click();
            }

        }
    }

    /**
     * Get a permalink of the page
     *
     */
    public void goToPermalink() {
        info("Go to permalink");
        $(ELEMENT_MORE_LINK).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        $(ELEMENT_PERMALINK_LINK).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    }

    public void goToPermalinkForSimpleUserNotAdmin() {
        info("Go to permalink");
        $(byXpath("(//*[@id=\"UIWikiPageControlArea_PageToolBar\"]/ul/li)[2]")).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        $(ELEMENT_PERMALINK_LINK).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    }

    /**
     * Restricted a page from infor bar or More menu
     *
     * @param opParams Boolean
     */
    public void restrictedPage(Boolean... opParams) {
        info("Make Restricted page");
        Boolean useRestrictLink = (opParams.length > 0 ? opParams[0] : false);
        if (useRestrictLink) {
            $(ELEMENT_PUBLIC_WIKI_ICON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
            $(ELEMENT_PUBLIC_WIKI_ICON).click();
        } else {
            goToPermalink();
        }
        $(ELEMENT_MAKE_RESTRICT_BUTTON).click();
        dialog.closeMessageDialog();
    }

    /**
     * Public a page from infor bar or More menu
     *
     * @param opParams Boolean
     */
    public void publicPage(Boolean... opParams) {
        info("Make Public page");
        Boolean useRestrictLink = (Boolean) (opParams.length > 0 ? opParams[0] : false);
        if (useRestrictLink) {
            $(ELEMENT_RESTICT_WIKI_ICON).waitUntil(Condition.visible, Configuration.timeout);
            $(ELEMENT_RESTICT_WIKI_ICON).click();
        } else {
            goToPermalink();
        }
        $(ELEMENT_MAKE_PUBLIC_BUTTON).click();
        $(ELEMENT_MAKE_RESTRICT_BUTTON).waitUntil(Condition.visible, Configuration.timeout);
        dialog.closeMessageDialog();
    }

    /**
     * Gets a permanent link by a given value.
     *
     * @return The value.
     */
    public String getPermalink() {
        return evt.getValue(ELEMENT_PERMALINK_TEXT);
    }

    /**
     * Close permalink popup
     */
    public void closePermalinkPopup() {
        info("Click on Close button");
        $(ELEMENT_PERMALINK_CLOSE).click();
        info("Permalink popup is closed");
    }

    /**
     * Go to attach files in Wiki Home page
     *
     * @param number String
     */
    public void goToAttachFiles(String number) {
        info("Click attach file link");
        $(byXpath(ELEMENT_PAGE_ATTACHFILE_NUMBER.replace("${number}", number))).click();
    }

    /**
     * Delete attach file in View mode in Wiki Homepage or in edit mode when editing
     * a wiki page
     *
     * @param fileName String
     */
    public void DeleteAttachFiles(String fileName) {
        info("Delete attach files");

        if (evt.waitForAndGetElement(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName), 5000, 0) != null) {
            $(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName)).click();
        } else {
            $(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName)).click();
        }
    }

    /**
     * Go to Wiki Home of the space
     *
     * @param space    String
     * @param userWiki String
     */
    public void goToWikiHomeOfSpaceFromBreadcrumb(String space, String userWiki) {
        goToSpaceSwitcher();
        if (!space.isEmpty()) {
            info("Select the space");
            $(byXpath(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", space))).click();
        }
    }

    /**
     * Open Space switcher
     */
    public void goToSpaceSwitcher() {
        info("Click on drop down");
        $(ELEMENT_SPACE_DROP_DOWN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    }

    /**
     * Input and search a space in space switcher
     *
     * @param text String
     */
    public void inputSpaceSwitcher(String text) {
        SelenideElement spaceSwitcherInput = $(ELEMENT_SPACE_SWITCHER_INPUT);
        spaceSwitcherInput.clear();
        spaceSwitcherInput.click();
        spaceSwitcherInput.sendKeys(text);
    }

    /**
     * Close space switcher
     */
    public void closeSpaceWitcher() {
        info("Click on Close button");
        $(ELEMENT_SPACE_SWITCHER_CLOSE_BTN).click();
        evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
    }

    /**
     * Close space switcher of the breadcrumb on wiki home page by clicking on
     * outside
     */
    public void closeSpaceSwitcherByClickOutSide() {
        info("Click on outside to close space switcher");
        $(ELEMENT_SPACE_SWITCHER_OUTSIDE).click();
        evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
    }

    /**
     * Close space switcher of move page popup by clicking on outside
     */
    public void closeSpaceSwitcherMovePopupByClickOutside() {
        info("Click on outside to close space switcher");
        $(ELEMENT_SPACE_SWITCHER_OUTSIDE).click();
        $(ELEMENT_SPACE_SWITCHER_INPUT_MOVE_PAGE_POPUP).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    }

    /**
     * Open Page information
     * @param wiki String
     */
    public void goToPageInformation(String wiki) {
        info("Go to Page Information");
        $(byText(wiki)).click();
        $(ELEMENT_MORE_LINK).click();
        ELEMENT_PAGE_INFO.click();
    }

    /**
     * Open information version table
     *
     * @param version String
     */
    public void goToRevisions(String version) {
        info("Click on Version");
        $(byXpath(ELEMENT_WIKI_PAGE_INFOMATION_VERSION.replace("${version}", version))).click();
        info("Verify that the table is shown");
        $(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE).waitUntil(Condition.visible,Configuration.timeout);
    }

    /**
     * Open information table
     *
     * @param page String
     * @param version String
     */
    public void viewInformationTable(String page, String version) {
        info("Open a wiki page 1");
        $(byText(page)).waitUntil(Condition.appears, Configuration.timeout);
        executeJavaScript("window.scrollBy(0,-550)");
        $(byText(page)).click();
        info("Open information table");
        $(byClassName("txtFeed")).find(byText(version)).should(Condition.exist);
        $(byClassName("txtFeed")).find(byText(version)).click();

        info("Verify that the table is shown");
        $(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE).waitUntil(Condition.appears, Configuration.timeout);
    }

    /**
     * Open compare version page by clicking on View Changes link
     */
    public void goToViewChange() {
        info("Click on View change link on the information bar");
        $(ELEMENT_INFOR_BAR_VIEW_CHANGE_LINK).click();
        info("Verify that compare version page is shown");
        evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_COMPARE_VERSION_TITLE);
    }

    /**
     * Export a Wiki Page
     */
    public void exportWikiPage() {
        info("Export a Wiki Page");
        $(ELEMENT_MORE_LINK).click();
        $(ELEMENT_PDF_LINK).click();
    }

    /**
     * Go to Move page form
     */
    public void goToMovePageForm() {
        info("Go to Move page form");
        info("Click on More link");
        $(ELEMENT_MORE_LINK).click();
        info("Click on Move page link");
        if (evt.waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null) {
            evt.mouseOverAndClick(ELEMENT_MOVE_PAGE);
        } else {
            $(ELEMENT_MOVE_PAGE).click();
        }
    }

    public void cancelPermissions() {
        info("Permissions page");
        $(ELEMENT_CANCEL_PERMISSION).click();
    }

    /**
     * Go to Export a page
     */
    public void goToExportPage() {
        info("Go to Export a page");
        info("Click on More link");
        $(ELEMENT_MORE_LINK).click();
        info("Click on Move page link");
        if (evt.waitForAndGetElement(ELEMENT_PDF_LINK, 5000, 0) == null) {
            evt.mouseOverAndClick(ELEMENT_PDF_LINK);
        } else {
            $(ELEMENT_PDF_LINK).click();
        }
    }
}
