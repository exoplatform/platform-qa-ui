package org.exoplatform.platform.qa.ui.wiki.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_WIKI_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_WIKI_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class WikiValidattions {

    private final TestBase testBase;

    public WikiHomePage wHome;

    private ElementEventTestBase evt;

    /**
     * constructor
     *
     * @param testBase TestBase
     */

    public WikiValidattions(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.wHome = new WikiHomePage(testBase);
    }

    /**
     * Verify a draf page
     *
     * @param title String
     */
    public void verifyTitleDrafPage(String title) {
        info("Verify that a draf page with the title:" + "title" + " is shown in draf table");

        $(byXpath(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title))).waitUntil(Condition.visible,
                Configuration.collectionsTimeout);
    }

    /**
     * Verify that Draf page is not shown
     *
     * @param title String
     */
    public void verifyNotTitleDrafPage(String title) {
        info("Verify that a draf page with the title:" + title + " is shown in draf table");

    }

    /**
     * Verify resuming a draf page
     *
     * @param titleBeforeDraf String
     */
    public void verifyResumADraf(String titleBeforeDraf) {
        info("Get current title in iput field");
        $(ELEMENT_TITLE_WIKI_INPUT).shouldHave(Condition.value(titleBeforeDraf));

    }

    /**
     * Verify that a wiki page link is inserted to the page
     *
     * @param label   String
     * @param tooltip String
     */
    public void verifyInsertedLinkIntoFrame(String label, String tooltip) {
        WebElement e = $(ELEMENT_CONTENT_WIKI_FRAME).waitUntil(Condition.visible, Configuration.timeout);
        testBase.getExoWebDriver().getWebDriver().switchTo().frame(e);
        if (label != null && label != "")
            $(By.linkText(label)).waitUntil(Condition.visible, Configuration.timeout);
        if (tooltip != null && tooltip != "")
            $(byXpath("//*[@title='" + tooltip + "']")).waitUntil(Condition.visible, Configuration.timeout);
        evt.switchToParentWindow();
    }

    /**
     * Verify the alert message when editing same page
     *
     * @param status   String
     * @param fullName String
     */
    public void verifyMessageWhenEditingSamePage(String status, String fullName) {
        info("Verify the message");
        $(byXpath(ELEMENT_WIKI_STATUS_EDITTING_SAME_PAGE.replace("$status", status).replace("$fullName", fullName))).waitUntil(Condition.visible,Configuration.timeout);
    }

    /**
     * Verify Confirmation message
     *
     * @param mess String
     */
    public void verifyWarningMessage(String mess) {
        info("Verify that the warning message is shown");
        $(byXpath(ELEMENT_MESSAGES_TEXT.replace("$mess", mess))).waitUntil(Condition.visible, Configuration.timeout);

    }

    /**
     * Verify effects of Page's content
     *
     * @param type    String
     * @param content String
     */
    public void verifyEffectsPageContent(effectTypes type, String content) {
        switch (type) {
            case Bold:
                info("Verify Bold effect");
                $(byXpath(ELEMENT_EFFECT_BOLD.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Bullest_List:
                info("Verify Bullest list");
                $(byXpath(ELEMENT_EFFECT_BULLET_LIST.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Number_List:
                info("Verify Number list");
                $(byXpath(ELEMENT_EFFECT_NUMBER_LIST.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Heading1:
                info("Verify Heading1 effect");
                $(byXpath(ELEMENT_EFFECT_HEADING_1.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Heading3:
                info("Verify Heading3 effect");
                $(byXpath(ELEMENT_EFFECT_HEADING_3.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Heading2:
                info("Verify Heading3 effect");
                $(byXpath(ELEMENT_EFFECT_HEADING_2.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Heading5:
                info("Verify Heading4 effect");
                $(byXpath(ELEMENT_EFFECT_HEADING_5.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Italic:
                info("Verify Italic effect");
                $(byXpath(ELEMENT_EFFECT_ITALIC.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Link:
                info("Verify Link effect");
                $(byXpath(ELEMENT_EFFECT_LINK.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Strike:
                info("Verify Strike effect");
                $(byXpath(ELEMENT_EFFECT_STRIKE.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case Underline:
                info("Verify Underline effect");
                $(byXpath(ELEMENT_EFFECT_UNDERLINE.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
                break;
        }
    }

    /**
     * Verify that Add page button isnot shown
     */
    public void verifyNotShowAddPageBtn() {
        info("Verify that Add Page button isnot shown");
        evt.waitForElementNotPresent(ELEMENT_ADD_PAGE_LINK);
        info("The button is not shown");
    }

    /**
     * Verify the page isnot created and shown in the list
     *
     * @param title String
     */
    public void verifyNotTitleWikiPage(String title) {
        info("Verify that the wiki page isnot created and shown in the list");
        ELEMENT_WIKI_PAGE_LINK.find(byText(title)).shouldNot(Condition.exist);
        info("The wiki page isnot created successfully");
    }

    /**
     * Verify that the page is shown detail
     *
     * @param pageName    String
     * @param pageContent String
     */
    public void verifyPageContent(String pageName, String pageContent) {
        refresh();
        info("Verify the page's name");
        $(byXpath(ELEMENT_WIKI_HOME_PAGE_TITLE.replace("${title}", pageName))).waitUntil(Condition.visible,Configuration.timeout);
        info("Verify the page's content");
        $(byXpath(ELEMENT_CONTENT_WIKI_PAGE.replace("$content", pageContent))).waitUntil(Condition.visible,Configuration.timeout);
    }

    /**
     * Verify that a table is added to the content of the page
     *
     * @param col int
     * @param row int
     */
    public void verifyTableInContentPage(int col, int row) {
        info("Verify that the table is shown into the content of the page");
        $(ELEMENT_PAGE_CONTENT_TABLE_MODE).waitUntil(Condition.visible, Configuration.timeout);
        for (int i = 1; i <= col; i++) {
            info("A table is shown with the col:" + col);
            $(byXpath(ELEMETN_PAGE_CONTENT_TABLE_COL_NUM.replace("$col", String.valueOf(col)))).waitUntil(Condition.visible, Configuration.timeout);
        }
        for (int i = 1; i <= row; i++) {
            info("A table is shown with the row:" + row);
            $(byXpath(ELEMETN_PAGE_CONTENT_TABLE_ROW_NUM.replace("$row", String.valueOf(row)))).waitUntil(Condition.visible, Configuration.timeout);
        }
        info("A table isnot shown with the col:" + col + 1);
        $(byXpath(ELEMETN_PAGE_CONTENT_TABLE_COL_NUM.replace("$col", String.valueOf(col + 1)))).shouldNotBe(Condition.visible);
        info("A table isnot shown with the row:" + row + 1);
        $(byXpath(ELEMETN_PAGE_CONTENT_TABLE_ROW_NUM.replace("$row", String.valueOf(row + 1)))).shouldNotBe(Condition.visible);
    }

    /**
     * Verify that Draft changes page is shown when click [View Draft changes] link
     * on status
     */
    public void verifyTitleDraftChangesPage() {
        info("Verify that Draf changes page is shown");
        evt.waitForAndGetElement(ELEMETN_WIKI_DRAFT_CHANGES_PAGE_TITLE);
    }

    /**
     * Check versions on Compare version page
     *
     * @param oldVersion String
     */
    public void verifyCompareVersions(String oldVersion) {
        info("The compare version page is shown");
        evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_COMPARE_VERSION_TITLE);
        info("Verify that Version N-1 and current version is shown on the page");
        sleep(Configuration.timeout);
        $(byXpath(ELEMENT_COMPARE_VERSION_VERSION_NUMBER.replace("$num", oldVersion))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
        evt.waitForAndGetElement(ELEMENT_COMPARE_VERSION_CURRENT_VERSION);
    }

    /**
     * Verify the content of a page after created successfully
     *
     * @param content String
     */
    public void verifyContentPage(String content) {
        info("Verify that the content page is added successfully");
        $(byXpath(ELEMENT_CONTENT_WIKI_PAGE.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
        info("The content also is added successfully");
    }

    /**
     * Verify draf in out date version status with text: "Your version is outdated,
     * a version of this content has been updated by another user. You can [view
     * your changes] and [Continue Editing] or [delete] your draft."
     *
     * @param message String
     */
    public void verifyDraftInOutDateVersionStatus(String message) {
        info("Verify status text");
       $(byXpath(ELEMETN_WIKI_STATUS_VERSION_TEXT.replace("$status", message))).waitUntil(Condition.visible,Configuration.timeout);
        info("Verify status with View Changes link");
        $(ELEMENT_WIKI_STATUS_VERSION_VIEW_CHANGES_LINK).waitUntil(Condition.visible,Configuration.timeout);;
        info("Verify status with Continue Editting link");
        $(ELEMENT_WIKI_STATUS_VERSION_CONTINUE_EDITTING_LINK).waitUntil(Condition.visible,Configuration.timeout);;
        info("Verify status with Delete link");
        $(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK).waitUntil(Condition.visible,Configuration.timeout);;
    }

    /**
     * The page's content is empty
     */
    public void verifyEmptyContentPage() {
        info("Verify that the content page is empty");
        $(ELEMENT_CONTENT_WIKI_PAGE_EMPTY).parent().waitUntil(Condition.visible,Configuration.timeout);
        info("the page's content is empty");
    }

    /**
     * Verify that the system redirects to the wiki page link that is inserted
     *
     * @param label    String
     * @param pageLink String
     */
    public void verifyInsertedExistLink(String label, String pageLink) {
        info("The page link is shown");
        $(byText(pageLink)).waitUntil(Condition.appears, Configuration.timeout);
    }

    /**
     * Verify that the page is published
     */
    public void verifyPublishedPage() {
        info("Verify that the page is published");
        evt.waitForAndGetElement(ELEMENT_PUBLIC_WIKI_ICON);
    }

    /**
     * Verify that the page is restricted
     */
    public void verifyRestrictedPage() {
        info("Verify that the page is restricted");
        evt.waitForAndGetElement(ELEMENT_RESTRICTED_WIKI_ICON);
    }

    /**
     * Verify that edit permission is checked or not
     *
     * @param userGroupMembership is username/Group or Membership
     * @param isChecked           = true if want to verify that is checked = false if want to
     *                            verify that is not checked
     */
    public void verifyEditPermisison(String userGroupMembership, boolean isChecked) {
        if (isChecked) {
            info("Verify that edit permission is checked");
            evt.waitForAndGetElement(ELEMENT_PERMISSION_EDIT_USER_CHECKED.replace("$userGroup", userGroupMembership), 2000, 1, 2);
        } else {
            info("Verify that edit permission isnot checked");
            evt.waitForElementNotPresent(ELEMENT_PERMISSION_EDIT_USER_CHECKED.replace("$userGroup", userGroupMembership), 2000, 1, 2);
        }
    }

    /**
     * Verify that view permission is checked or not
     *
     * @param userGroupMembership is username/Group or Membership
     * @param isChecked           = true if want to verify that is checked = false if want to
     *                            verify that is not checked
     */
    public void verifyViewPermisison(String userGroupMembership, boolean isChecked) {
        if (isChecked) {
            info("Verify that view permission is checked");
            $(byXpath(ELEMENT_PERMISSION_VIEW_USER_CHECKED.replace("$userGroup", userGroupMembership))).parent().waitUntil(Condition.visible, Configuration.timeout);
        } else {
            info("Verify that view permission isnot checked");
            $(byXpath(ELEMENT_PERMISSION_VIEW_USER_CHECKED.replace("$userGroup", userGroupMembership))).parent().waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        }
    }

    /**
     * Verify that the page is not found
     */
    public void verifyPageNotFound() {
        info("Verify that the page is not found");
        $(ELEMENT_WIKI_PAGE_NOT_FOUND).waitUntil(Condition.visible, Configuration.timeout);
        info("The page is not found");
    }
    /**
     * Verify a parent page has not permission for a user
     */
    public void verifyRestrictedPageHasChildPage() {
        info("Verify that parent page is shown under the title: restricted on the left tree");
        sleep(Configuration.collectionsTimeout);
        (ELEMENT_WIKI_LEFT_TREE_RESTRICTED_PAGE_TITLE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
        info("Verify the tooltip of the page as:[this page is restricted, you don't have permissions to view it]");
        $(ELEMENT_WIKI_TOOLTIP_RESTRICTED_PAGE_TITLE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).exists();
        info("Verify that cannot click on parent page");
        $(ELEMENT_WIKI_PARENT_PAGE_UN_LINK).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    }

    /**
     * Verify breadcrumb path of a page
     *
     * @param locator1 String
     * @param locator2 String
     * @param page String
     */
    public void verifyBreadCrumbePath(String locator1, String locator2, String page) {
        info("Verify that the page is at the path:" + locator1 + "->" + locator2 + "->" + page);
        $(byXpath(ELEMENT_WIKI_HOME_BREADCRUMB_PATH.replace("$locator1", locator1)
                .replace("$locator2", locator2)
                .replace("$page", page))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
        info("The page is at correct path");
    }

    /**
     * Verify breadcrumb path of a page
     *
     * @param locator1 String
     * @param locator2 String
     */
    public void verifyBreadCrumbePath(String locator1, String locator2) {
        info("Verify that the page is at the path:" + locator1 + "->" + locator2);
        $(byId("UIWikiBreadCrumb")).shouldHave(Condition.text(locator1 + " " + locator2));
        info("The page is at correct path");
    }

    /**
     * Verify alt Text of image is changed
     *
     * @param altText String
     */
    public void verifyAltTextImageInContentPage(String altText) {
        info("Verify that alt text is changed");
        $(byXpath(ELEMENT_INSERTED_IMAGE_ALT_TEXT.replace("$alt", altText))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Verify attach files are displayed in attach list or not when clicking in
     * attach files number
     *
     * @param fileName String
     * @param display  boolean
     */
    public void VerifyAttachFilesAreDisplayedInAttachListOrNot(String fileName, boolean display) {
        info("Verify attach files are displayed in attach list");

        if (display) {
            if ($(byXpath(ELEMENT_PAGE_ATTACHFILE_1.replace("${fileName}", fileName))).is(Condition.visible));
            $(byXpath(ELEMENT_PAGE_ATTACHFILE_2.replace("${fileName}", fileName))).is(Condition.visible);
            info("Attach files are dilsplayed in attach list");
        } else {
            if ($(byXpath(ELEMENT_PAGE_ATTACHFILE_1.replace("${fileName}", fileName))).waitUntil(Condition.not(Condition.visible),Configuration.timeout) != null)
                $(byXpath(ELEMENT_PAGE_ATTACHFILE_2.replace("${fileName}", fileName))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
            info("Attach files are not displayed in attach list");
        }
    }

    /**
     * Verify the page is created and shown in the list
     *
     * @param title String
     */
    public void verifyTitleWikiPage(String title) {
        info("Verify that the wiki page is created and shown in the list");
        $(byXpath("//div[@id='iconTreeExplorer']//*[contains(text(),'${title}')]".replace("${title}", " " + title))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs + Configuration.openBrowserTimeoutMs).click();
        $(byXpath("//div[@id='UITreeExplorer']/following::div[@id='titleInfo' and text()='${title}']".replace("${title}",title))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
        info("The wiki page is created successfully");
    }
    /**
     * Verify the page is created and shown in the list
     *
     * @param title String
     * @param content String
     */
    public void verifyTitleAndContentWikiPageInHomeSpace(String title, String content) {
        info("Verify that the wiki page is created and shown in ");
        info("Wiki page title is displayed and correct");
        $(byXpath(ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}",title))).isDisplayed();
        info("Wiki page content is displayed and correct");
        $(byXpath(ELEMENT_ACTIVITY_WIKI_CONTENT.replace("${title}",content))).isDisplayed();
        info("The wiki page is published successfully");
    }
    /**
     * Verify the page is not displayed in Wiki Home
     *
     * @param title String
     */
    public void verifyWikiPageNotDisplayedInWikiHome(String title) {
        info("Verify the page is not displayed in Wiki Home");
        ELEMENT_WIKI_PAGE_LINK.find(byText(title)).shouldNot(Condition.exist);
        info("The wiki page is not displayed in Wiki Home");
    }

    /**
     * Verify that a template is shown in the list
     *
     * @param template String
     */
    public void verifyTemplateInList(String template) {
        if ($(ELEMENT_WIKI_SETTING_PAGE_TOTAL_NUMBER).is(Condition.visible)) {
            String total = evt.waitForAndGetElement(ELEMENT_WIKI_SETTING_PAGE_TOTAL_NUMBER).getText();
            int totalNum = Integer.parseInt(total);
            for (int i = 0; i < totalNum; i++) {
                if (evt.waitForAndGetElement(ELEMENT_WIKI_SETTINGS_RESULTS.replace("${template}", template), 2000, 0) == null) {
                    info("Click on next button");
                    evt.click(ELEMENT_WIKI_SETTING_PAGE_NEXT_BUTTON);
                } else {
                    info("Verify that the template is shown in the list");
                    evt.waitForAndGetElement(ELEMENT_WIKI_SETTINGS_RESULTS.replace("${template}", template));
                    info("The template is shown successfully");
                    break;
                }
            }
        } else {
            info("Verify that the template is shown in the list");
            $(byXpath(ELEMENT_WIKI_SETTINGS_RESULTS.replace("${template}", template))).waitUntil(Condition.visible, Configuration.timeout);
            info("The template is shown successfully");
        }
    }

    /**
     * Verify that searching is empty
     */
    public void verifyTemplateSearchEmpty() {
        info("Verify that the searching is empty");
        $(ELEMENT_WIKI_SETTING_SEARCH_EMPTY).waitUntil(Condition.visible, Configuration.timeout);
        info("Searching is empty successfully");
    }

    /**
     * Verify that a space is shown in space switcher
     *
     * @param space String
     */
    public void verifyPresentSpaceSwitcher(String space) {
        if (!space.isEmpty()) {
            info("Verify that the space is shown");
            if (!$(byXpath(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", space))).isDisplayed()) {
                testBase.getExoWebDriver().getWebDriver().navigate().refresh();
            } else {
                $(byXpath(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", space))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
            }
        }

    }

    /**
     * Verify that a space isnot shown in space switcher
     *
     * @param space String
     */
    public void verifyNotPresentSpaceSwitcher(String space) {
        if (!space.isEmpty()) {
            info("Verify that the space is shown");
            $(byXpath(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", space))).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        }
    }

    /**
     * Verify that no any spaces is listed in space switcher list
     */
    public void verifyNoAnySpaces() {
        info("Verify that no any spaces is listed in space switcher list");
        evt.waitForAndGetElement(ELEMENT_SPACE_SWITCHET_EMPTY_LIST_SPACE);
    }

    /**
     * Verify that a space has a position in the list
     *
     * @param i     int
     * @param space String
     */
    public void verifyPositionOfASpaceInList(int i, String space) {
        info("Verify that the space has:" + i + " position in the list");
        $(byXpath(ELEMENT_SPACE_SWITCHER_SPACE_POSITION.replace("$num", String.valueOf(i)).replace("$space", space))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Verify page's version
     *
     * @param version String
     */
    public void verifyVersionPage(String version) {
        info("Verify that page's version is:" + version);
        $(byXpath(ELEMENT_WIKI_PAGE_INFOMATION_VERSION.replace("${version}", version))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Verify that vesion is listed in recent changes table
     *
     * @param num int
     */
    public void verifyVersionsInPage(int num) {
        info("Verify that the version is list in recent changes table");
        $(byXpath(ELEMENT_PAGE_INFO_RECENT_CHANGES_VERSION.replace("$num", String.valueOf(num)))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Verify that the content of the version is shown
     *
     * @param content String
     */
    public void verifyContentOfVersion(String content) {
        info("Verify that the content of the version is shown");
        $(byXpath(ELEMENT_PAGE_INFO_VIEW_CONTENT_OF_VERSION.replace("$content", content))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Verify compare version page's content
     *
     * @param oldContent String
     * @param newContent String
     */
    public void verifyCompareVersionPage(String oldContent, String newContent) {
        info("Verify that  Words/lines which are red-highlighted with strike-throughs indicate that they were removed");
        $(byXpath(ELEMENT_PAGE_HISTORY_COMPARE_CONTENT.replace("${text}", oldContent))).getCssValue("background-color").contentEquals("rgba(208, 123, 123, 1)");

        info("Verify that Words/lines highlighted in green indicate that they were added");
        $(byXpath(ELEMENT_PAGE_HISTORY_COMPARE_CONTENT.replace("${text}", newContent))).getCssValue("background-color").contentEquals("rgba(108, 203, 174, 1)");
    }

    /**
     * Verify that version is listed in history page
     *
     * @param version String
     */
    public void verifyVersionsInHistoryPage(String version) {
        info("Verify that vesion is listed in history page");
        $(byXpath(ELEMENT_PAGE_HISTORY_VERSION.replace("$version", version))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Verify that Draft exists n Draft list or not
     *
     * @param title String
     * @param exist String
     */
    public void verifyDraftExistsInDraftListOrNot(String title, boolean exist) {
        info("Verify that Draft exists in Draft list or Not");
        if (exist) {

            info("Draft exists in Draft list");
        } else {

            info("Draft does not exist in Draft list");
        }

    }

    /**
     * Define effect types in Source Editor
     */
    public enum effectTypes {
        Bold, Bullest_List, Number_List, Heading1, Heading3, Heading2, Heading5, Italic, Link, Strike, Underline;
    }
}