package org.exoplatform.platform.qa.ui.wiki.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
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
                Configuration.openBrowserTimeoutMs);
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
     * Verify that a macro is inserted into the content of the page
     *
     * @param macroType String
     */
    public void verifyMacroIntoFrame(String macroType) {
        WebElement e = evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME, testBase.getDefaultTimeout(), 1, 2);
        testBase.getExoWebDriver().getWebDriver().switchTo().frame(e);
        evt.waitForAndGetElement(ELEMENT_MACRO_CLASS_INSERT_INTO_FRAME.replace("$macro", macroType));
        evt.switchToParentWindow();
    }

    /**
     * Verify that macro Iframe is inserted into the content of the page
     *
     * @param src String
     */
    public void verifyMacroIFrame(String src) {
        info("Verify that IFrame is inserted");
        evt.waitForAndGetElement(ELEMENT_MACRO_IFRAME_IN_CONTENT_PAGE.replace("$src", src));
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
     * Check display of Admin Pages permission
     *
     * @param isDisplay boolean
     */
    public void checkDisplayOfAdmPage(boolean isDisplay) {
        info("check display of admin pages");
        evt.waitForAndGetElement(ELEMENT_ADD_PAGE_LINK);
        evt.click(ELEMENT_MORE_LINK, 0, true);
        if (isDisplay) {
            evt.waitForAndGetElement(ELEMENT_PERMISSION_LINK);
        } else {
            evt.waitForElementNotPresent(ELEMENT_PERMISSION_LINK);
        }
    }

    /**
     * Check display of Admin Wiki permission
     *
     * @param isDisplay boolean
     */
    public void checkDisplayOfAdmWiki(boolean isDisplay) {
        info("check display of admin wiki");
        evt.waitForAndGetElement(ELEMENT_ADD_PAGE_LINK);
        evt.click(ELEMENT_MORE_LINK, 0, true);
        evt.waitForAndGetElement(ELEMENT_PERMISSION_LINK);
        evt.click(ELEMENT_SEARCH_BROWSERS_DROPDOWN);
        if (isDisplay) {
            evt.waitForAndGetElement(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);
        } else {
            evt.waitForElementNotPresent(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);
        }
    }

    /**
     * Check display of edit page permission
     *
     * @param title     String
     * @param isDisplay boolean
     */
    public void checkDisplayOfEditPage(String title, boolean isDisplay) {
        info("check display of edit page");
        wHome.selectAPage(title);
        if (isDisplay) {
            evt.waitForAndGetElement(ELEMENT_ADD_PAGE_LINK);
            evt.waitForAndGetElement(ELEMENT_EDIT_PAGE_LINK);
            evt.click(ELEMENT_MORE_LINK, 0, true);
            evt.waitForElementNotPresent(ELEMENT_PERMISSION_LINK);
        } else {
            evt.waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
        }
    }

    /**
     * Check display of view page permission
     *
     * @param title     String
     * @param isDisplay boolean
     */
    public void checkDisplayOfViewPage(String title, boolean isDisplay) {
        info("check display of view page");
        if (isDisplay) {
            wHome.selectAPage(title);
            evt.waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
            evt.click(ELEMENT_MORE_LINK, 0, true);
            evt.waitForElementNotPresent(ELEMENT_PERMISSION_LINK);
        } else {

        }
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
        evt.waitForAndGetElement(ELEMENT_COMPARE_VERSION_VERSION_NUMBER.replace("$num", oldVersion));
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
     * Verify email format of the email link after inserted a email link to the page
     *
     * @param address String
     */
    public void verifyEmailFormatLink(String address) {
        info("Verify that email format of the link is correct");
        evt.waitForAndGetElement(ELEMENT_EMAIL_LINK_EMAIL_FORMAT.replace("$email", address));
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
     * Verify that the system redirects to the wiki page that is created
     *
     * @param label    String
     * @param pageLink String
     */
    public void verifyInsertNewLink(String label, String pageLink) {
        info("The page link is shown");
        String actualTitle = this.testBase.getExoWebDriver()
                .getWebDriver()
                .findElement(ELEMENT_TITLE_WIKI_INPUT)
                .getAttribute("value")
                .toString();
        if (actualTitle.contains(pageLink))
            assert true;
        else
            assert false;
    }

    /**
     * Verify the size of the image in the page's content
     *
     * @param width  String
     * @param height String
     */
    public void verifySizeImageInContentPage(String width, String height) {
        info("Verify that the size of image is changed");
        evt.waitForAndGetElement(ELEMENT_INSERTED_IMAGE_SIZE.replace("$width", width).replace("$height", height));
    }

    /**
     * Verify status when edit a page that has existing a draf
     *
     * @param message String
     * @param date    String
     */
    public void verifyStatusWhenEditPageHasExistingDraf(String message, String date) {
        info("Verify status text");
        evt.waitForAndGetElement(ELEMETN_WIKI_STATUS_VERSION_TEXT.replace("$status", message).replace("$date", date));
        info("Verify status with View Changes link");
        evt.waitForAndGetElement(ELEMENT_WIKI_STATUS_VERSION_VIEW_CHANGES_LINK);
        info("Verify status with Resume the draf link");
        evt.waitForAndGetElement(ELEMENT_WIKI_STATUS_RESUME_THE_DRAF_LINK);
        info("Verify status with Delete link");
        evt.waitForAndGetElement(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK);
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
        sleep(Configuration.timeout);
        (ELEMENT_WIKI_LEFT_TREE_RESTRICTED_PAGE_TITLE).waitUntil(Condition.visible, Configuration.timeout).hover();
        info("Verify the tooltip of the page as:[this page is restricted, you don't have permissions to view it]");
        sleep(2000);
        $(ELEMENT_WIKI_TOOLTIP_RESTRICTED_PAGE_TITLE).exists();
        sleep(Configuration.timeout);
        info("Verify that cannot click on parent page");
        $(ELEMENT_WIKI_PARENT_PAGE_UN_LINK).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Verify that the message for many pages have same title in moving page
     *
     * @param mess  String
     * @param pages String
     */
    public void verifyMessageManyPagesHaveSameTitleInMovingPage(String mess, ArrayList<String> pages) {
        if (!pages.isEmpty()) {
            for (int i = 0; i < pages.size(); i++) {
                if (i > 4) {
                    info("i5:" + i + "with:" + pages.get(i));
                    evt.waitForElementNotPresent(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_LINK.replace("$message", mess)
                                    .replace("$page", pages.get(i)),
                            2000,
                            1);
                    evt.waitForAndGetElement(ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_AND_MORE);
                } else {
                    info("i0:" + i + "with:" + pages.get(i));
                    evt.waitForAndGetElement(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_LINK.replace("$message", mess)
                                    .replace("$page", pages.get(i)),
                            2000,
                            1);
                }
            }
        }

    }

    /**
     * Verify the message for one page has same title in moving page
     *
     * @param mess String
     */
    public void verifyMessageOnePageHasSameTitleInMovingPage(String mess) {
        if (!mess.isEmpty()) {
            info("Verify that the message is shown");
            evt.waitForAndGetElement(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_LINK.replace("$message", mess));
            info("The message is shown");
        }
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
     * Verify that the tooltip of rename in the message is shown for one page has
     * same title in moving page
     *
     * @param mess String
     */
    public void verifyToolTipMessageOnePageHasSameTitleInMovingPage(String mess) {
        if (!mess.isEmpty()) {
            info("Verify that The tooltip of the message is shown");
            evt.waitForAndGetElement(EMENENT_MOVE_ONE_PAGE_POPUP_ALERT_MESSAGE_RENAME_TOOLTIP.replace("$message", mess));
            info("The tooltip is shown");
        }
    }

    /**
     * Verify that the tooltip of the message for many pages have same title in
     * moving page
     *
     * @param mess  String
     * @param pages String
     */
    public void verifyToolTipMessageManyPagesHaveSameTitleInMovingPage(String mess, ArrayList<String> pages) {
        if (!pages.isEmpty()) {
            for (int i = 0; i < pages.size(); i++) {
                if (i > 4) {
                    info("i5:" + i + "with:" + pages.get(i));
                    evt.waitForElementNotPresent(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_TOOLTIP.replace("$message", mess)
                                    .replace("$page", pages.get(i)),
                            2000,
                            1);
                    evt.waitForAndGetElement(ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_AND_MORE);
                } else {
                    info("i0:" + i + "with:" + pages.get(i));
                    evt.waitForAndGetElement(EMENENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME_TOOLTIP.replace("$message", mess)
                                    .replace("$page", pages.get(i)),
                            2000,
                            1);
                }
            }
        }
    }

    /**
     * Verify that a page is parent of other page
     *
     * @param parentNode String
     * @param childNode  String
     */
    public void verifyParentChildNode(String parentNode, String childNode) {
        info("Verify that page A is under page B or page A is parent of the page B");
        evt.waitForAndGetElement(ELEMENT_TREE_WIKI_PARENT_NODE_CHILD_NODE.replace("$parent", parentNode).replace("$child",
                childNode));
        info("The page" + parentNode + " is parent of" + childNode + "");
    }

    /**
     * Verify that edit mode is opening
     *
     * @param oldTitle String
     */
    public void verifyEditModeOpenning(String oldTitle) {
        info("Verify that input tilte field is shown");
        evt.waitForAndGetElement(ELEMENT_TITLE_WIKI_INPUT);
        info("Verify that the value of input title field has correct value of old title");
        String currentTitle = this.testBase.getExoWebDriver()
                .getWebDriver()
                .findElement(ELEMENT_TITLE_WIKI_INPUT)
                .getAttribute("value")
                .toString();
        if (currentTitle.contains(oldTitle))
            assert true;
        else
            assert false;
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
        sleep(Configuration.collectionsTimeout);
        $(byXpath("//div[@id='iconTreeExplorer']/a[text()='${title}']".replace("${title}", " " + title))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
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
     * Verify that a template isnot shown in the list
     *
     * @param template String
     */
    public void verifyNotTemplateInList(String template) {
        if (evt.waitForAndGetElement(ELEMENT_WIKI_SETTING_PAGE_TOTAL_NUMBER, 2000, 0) != null) {
            String total = evt.waitForAndGetElement(ELEMENT_WIKI_SETTING_PAGE_TOTAL_NUMBER).getText();
            int totalNum = Integer.parseInt(total);
            for (int i = 0; i < totalNum; i++) {
                info("Verify that the template is shown in the list");
                evt.waitForElementNotPresent(ELEMENT_WIKI_SETTINGS_RESULTS.replace("${template}", template));
                info("The template is shown successfully");
            }
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
     * Verify that the page is shown in searched results list
     *
     * @param page String
     */
    public void verifySearchResults(String page) {
        info("Verify that the page is shown in results searched");
        evt.waitForAndGetElement(ELEMENT_WIKI_SEARCH_RESULT_PAGE_LINK.replace("$page", page));
        info("The page is shown successfully");
    }

    /**
     * Verify that the page is not shown in searched results list
     *
     * @param page String
     */
    public void verifyNotSearchResults(String page) {
        info("Verify that the page isnot shown in results searched");
        evt.waitForElementNotPresent(ELEMENT_WIKI_SEARCH_RESULT_PAGE_LINK.replace("$page", page));
        info("The page isnot shown successfully");
    }

    /**
     * Verify that searched results is empty
     */
    public void verifyEmptySearchResults() {
        info("Verify that searched results is empty");
        evt.waitForAndGetElement(ELEMENT_WIKI_SEARCH_EMPTY_RESULTS);
        info("No results is found");
    }

    /**
     * Verify that user can use scroll down to see more spaces
     */
    public void verifyScrollDownOfSpaceSwitcher() {
        info("Scroll down");
        WebElement spaceList = evt.waitForAndGetElement(By.className("spaceList"));
        String str1 = String.valueOf(((JavascriptExecutor) testBase.getExoWebDriver()
                .getWebDriver()).executeScript("return arguments[0].clientHeight;",
                spaceList));
        String str = String.valueOf(((JavascriptExecutor) testBase.getExoWebDriver()
                .getWebDriver()).executeScript("return arguments[0].scrollHeight;",
                spaceList));
        int clientHeight = Integer.parseInt(str1);
        int scrollHeight = Integer.parseInt(str);
        assert clientHeight < scrollHeight;
    }

    /**
     * Verify that spaces in space switcher lis are shown
     *
     * @param spaces   ArrayList
     * @param numIndex int
     */
    public void verifyPresentSpaceSwitcher(ArrayList<String> spaces, int... numIndex) {
        if (numIndex.length > 0) {
            for (int i = 0; i < spaces.size() - numIndex[0]; i++) {
                info("Verify that all spaces is shown");
                evt.waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", spaces.get(i).toLowerCase()).replace(" ", "_"));
            }
        } else {
            for (int i = 0; i < spaces.size(); i++) {
                info("Verify that all spaces is shown");
                evt.waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", spaces.get(i).toLowerCase()).replace(" ", "_"));
            }
        }
    }

    /**
     * Verify that spaces in space switcher are not shown
     *
     * @param spaces   ArrayList
     * @param numIndex int
     */
    public void verifyNotPresentSpaceSwitcher(ArrayList<String> spaces, int... numIndex) {
        if (numIndex.length > 0) {
            for (int i = 0; i < spaces.size() - numIndex[0]; i++) {
                info("Verify that all spaces is shown");
                evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", spaces.get(i)));
            }
        } else {
            for (int i = 0; i < spaces.size(); i++) {
                info("Verify that all spaces is shown");
                evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", spaces.get(i)));
            }
        }
    }

    /**
     * Verify that a space is shown in space switcher
     *
     * @param space String
     */
    public void verifyPresentSpaceSwitcher(String space) {
      sleep(Configuration.timeout);
        if (!space.isEmpty()) {
            info("Verify that the space is shown");
            $(byXpath(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", space))).waitUntil(Condition.visible, Configuration.timeout);
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
     * Verify tables in Page information as summary, related, hierachy and recent
     * changes table
     */
    public void verifyTablesPageInformation() {
        info("Verify sumary table");
        evt.waitForAndGetElement(ELEMENT_PAGE_INFO_SUMMARY_TABLE);
        info("Verify relate table");
        evt.waitForAndGetElement(ELEMENT_PAGE_INFO_RELATED_TABLE);
        info("Verify hierachy table");
        evt.waitForAndGetElement(ELEMENT_PAGE_INFO_HIERARCHY_TABLE);
        info("Verify recent changes table");
        evt.waitForAndGetElement(ELEMENT_PAGE_INFO_RECENT_CHANGES_TABLE);
    }

    /**
     * Verify that related page is added in Related table
     *
     * @param locator     String
     * @param relatedPage String
     */
    public void verifyRelatedPage(String locator, String relatedPage) {
        info("Verify that related page is added to the related table");
        evt.waitForAndGetElement(ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT.replace("${col1}", locator).replace("${col2}", relatedPage),
                2000,
                1);
    }

    /**
     * Verify that a page is not shown in related page list of the related page
     * popup
     *
     * @param page String
     */
    public void verifyNotPageInRelatedPageList(String page) {
        info("Verify that a page is not listed in related page list to select it");
        evt.waitForElementNotPresent(ELEMENT_ADD_RELATED_POPUP_CONTENT.replace("${page}", page));
    }

    /**
     * Verify that a page is not shown in left related page list
     *
     * @param page String
     */
    public void verifyNotPageInLeftRelatePageList(String page) {
        info("Verify that a page is not shown in left related page list");
        evt.waitForElementNotPresent(ELEMENT_PAGE_INFO_RELATED_PAGE_LINK.replace("$page", page));
    }

    /**
     * Verify that a page is shown in left related page list
     *
     * @param page String
     */
    public void verifyPageInLeftRelatePageList(String page) {
        info("Verify that a page is shown in left related page list");
        evt.waitForAndGetElement(ELEMENT_PAGE_INFO_RELATED_PAGE_LINK.replace("$page", page));
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
     * Verify macro JIRA
     */
    public void verifyMacroTableHeaderJIRA() {
        info("Information from a JIRA server and displays them as a table with Type, Key, Summary, Status, Created Date");
        evt.waitForAndGetElement(ELEMENT_MARCO_HEADER_TABLE_JIRA.replace("$header", "Type"));
        evt.waitForAndGetElement(ELEMENT_MARCO_HEADER_TABLE_JIRA.replace("$header", "Key"));
        evt.waitForAndGetElement(ELEMENT_MARCO_HEADER_TABLE_JIRA.replace("$header", "Summary"));
        evt.waitForAndGetElement(ELEMENT_MARCO_HEADER_TABLE_JIRA.replace("$header", "Status"));
        evt.waitForAndGetElement(ELEMENT_MARCO_HEADER_TABLE_JIRA.replace("$header", "Created Date"));
    }

    /**
     * Verify footNode intoFrame of Rich Text Mode
     *
     * @param footNode1       String
     * @param footNode2       String
     * @param contentMacroBox String
     */
    public void verifyMacroFootNodeIntoFrame(String footNode1, String footNode2, String contentMacroBox) {
        testBase.getExoWebDriver().getWebDriver().switchTo().frame(evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
        evt.waitForAndGetElement(By.linkText("1"));
        evt.waitForAndGetElement(By.linkText("2"));
        evt.waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", footNode1));
        evt.waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", footNode2));
        evt.waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", contentMacroBox));
        evt.switchToParentWindow();
    }

    /**
     * Verify footNode into the content of the page
     *
     * @param footNode1       String
     * @param footNode2       String
     * @param contentMacroBox String
     */
    public void verifyMacroFootNodeIntoContentPage(String footNode1, String footNode2, String contentMacroBox) {
        evt.waitForAndGetElement(By.linkText("1"));
        evt.waitForAndGetElement(By.linkText("2"));
        evt.waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", footNode1));
        evt.waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", footNode2));
        evt.waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", contentMacroBox));
    }

    /**
     * Define effect types in Source Editor
     */
    public enum effectTypes {
        Bold, Bullest_List, Number_List, Heading1, Heading3, Heading2, Heading5, Italic, Link, Strike, Underline;
    }
}