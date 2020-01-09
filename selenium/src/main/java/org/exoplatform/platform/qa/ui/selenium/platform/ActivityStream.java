package org.exoplatform.platform.qa.ui.selenium.platform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.awt.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_STREAM_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

public class ActivityStream {
    private final TestBase testBase;

    public Button button;

    public HomePagePlatform homePagePlatform;

    private ElementEventTestBase evt;

    /**
     * constructor
     *
     * @param testBase testbase
     */

    public ActivityStream(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.button = new Button(testBase);
        this.homePagePlatform = new HomePagePlatform(testBase);

    }

    /**
     * Check if there is an activity in the stream
     *
     * @param name String
     */
    public void checkActivity(String name) {
        info("Verify that the activity of the name:" + name + " is shown");
        sleep(Configuration.timeout);
        $(byText(name)).waitUntil(Condition.visible, Configuration.timeout);
        info("The activity of the name:" + name + " is shown successfully");
    }

    /**
     * Check if there is not an activity in the stream
     *
     * @param name String
     */
    public void checkNotShownActivity(String name) {
        info("Verify that the activity of the name:" + name + " is not shown");
        $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}", name))).shouldNotBe(Condition.visible);
        info("The activity of the name:" + name + " is not shown successfully");
    }

    /**
     * Check comment of an activity
     *
     * @param activity String
     * @param comment  String
     */
    public void checkCommentOfActivity(String activity, String comment) {
        info("Verify that the comment is added");
        sleep(Configuration.collectionsTimeout);
        $(byXpath("//div[@class='titleWiki']/a[text()='${activity}']".replace("${activity}",activity))).should(Condition.exist);
        sleep(2000);
        ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(comment)).should(Condition.exist);
        info("The comment is added successfully");
    }

    public void checkComment(String title, String comment, String value, changeTypes type) {
        switch (type) {
            case Has_One_Value:
                info("Verify that the comment is added");
                $(byXpath(ELEMENT_ACTIVITY_COMMENT.replace("${title}", title)
                        .replace("${comment}", comment)
                        .replace("$value", value))).waitUntil(Condition.visible, Configuration.timeout);
                break;
            case No_Value:
                $(byXpath(ELEMENT_ACTIVITY_COMMENT.replace("${title}", title)
                        .replace("${comment}", comment))).waitUntil(Condition.visible, Configuration.timeout);
                break;
        }
    }

    /**
     * Check activity of adding wiki page with 4 lines in the content
     *
     * @param title   String
     * @param content String
     * @param version String
     */
    public void checkActivityAddWikiPage(String title, String content, String version) {
        if (version == null)
            version = "Version: 1";
        String[] arrayline;
        arrayline = content.split("</br>");
        info("Check wiki page's title");
        $(byText(title)).should(Condition.exist);
        info("Check wiki page's version");
        $(byText(version)).should(Condition.exist);
        info("Check first 4 lines of the wiki page");
        $(byText(arrayline[0])).isDisplayed();
        $(byText(arrayline[1])).isDisplayed();
        $(byText(arrayline[2])).isDisplayed();
        $(byText(arrayline[3])).isDisplayed();
        info("Check line 5 of the wiki page is not shown");
        $(byText(arrayline[4])).shouldNot(Condition.exist);
    }

    /**
     * Check Activity of Wiki page
     *
     * @param title   String
     * @param content String
     * @param version String
     * @param isEdit  = true for checking View Change link is shown = false if not
     *                View change link
     */
    public void checkActivityWikiPage(String title, String content, String version, boolean isEdit) {
        if (!title.isEmpty()) {
            info("Check wiki page's title");

            $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}", title))).waitUntil(Condition.visible,
                    Configuration.timeout);
        }
        if (!content.isEmpty()) {
            info("Check the content");
            String[] arrayline;
            arrayline = content.split("</br>");
            if (arrayline.length > 1) {
                info("Check first 4 lines of the wiki page");
                $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}", title))).waitUntil(Condition.visible,
                        Configuration.timeout);
                $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}", title))).waitUntil(Condition.visible,
                        Configuration.timeout);
                $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}", title))).waitUntil(Condition.visible,
                        Configuration.timeout);
                $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}", title))).waitUntil(Condition.visible,
                        Configuration.timeout);
                info("Check line 5 of the wiki page is not shown");
                $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}", title))).shouldNotBe();
            } else {
                info("Check first line of the wiki page");
                $(byXpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}", title))).waitUntil(Condition.visible,
                        Configuration.timeout);
            }
        }
        if (isEdit) {
            info("View change link is shown");
            $(byXpath(ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK.replace("$title", title))).waitUntil(Condition.visible,
                    Configuration.timeout);
        }
    }

    /**
     * Check activity after add a web content
     *
     * @param title   String
     * @param version String
     * @param status  String
     */
    public void checkActivityAddWebContent(String title, String version, String status) {
        if (version == null)
            version = "0";
        if (status == null)
            status = "Draft";
        // check icon and title
        sleep(3000);
        $(byXpath(ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("${title}", title))).exists();
        sleep(Configuration.timeout);
        $(byXpath(ELEMENT_ACTIVITY_WEBCONTENT_CHECK_VERSION.replace("${title}", title)
                .replace("{$version}", version))).waitUntil(Condition.visible,
                Configuration.timeout).exists();
        sleep(Configuration.timeout);
        $(byXpath(ELEMENT_ACTIVITY_WEBCONTENT_CHECK_STATUS.replace("${title}", title)
                .replace("{$status}", status))).waitUntil(Condition.visible,
                Configuration.timeout).exists();
    }

    /**
     * Show all comment of activities
     *
     * @param name String
     */
    public void showComment(String name) {
        info("Show all comment");
        $(byXpath(ELEMENT_PUBLICATION_SEEALLCOMMENTBTN.replace("${activity}", name))).click();
        $(byXpath(ELEMENT_PUBLICATION_HIDEALLCOMMENTBTN.replace("${activity}", name))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Hide all comment of activities
     *
     * @param name String
     */
    public void hideComment(String name) {
        info("Show all comment");
        $(byXpath(ELEMENT_PUBLICATION_HIDEALLCOMMENTBTN.replace("${activity}", name))).click();
        $(byXpath(ELEMENT_PUBLICATION_SEEALLCOMMENTBTN.replace("${activity}", name))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * Add text into activity text box
     *
     * @param text String
     */
    public void addText(String text) {
        info("----Add text into activity text box-----");
        SelenideElement frame = $(byClassName("cke_wysiwyg_frame")).waitUntil(Condition.visible, Configuration.collectionsTimeout);
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        ELEMENT_INPUT_ACTIVITY.sendKeys(text);
        switchTo().defaultContent();
    }

    /**
     * Add link activity
     *
     * @param link String
     */
    public void addLink(String link) {
        info("----Click on Link----");
        ELEMENT_TAB_ADD_LINK.click();

        info("----Input link into link box-----");
        ELEMENT_INPUT_LINK.setValue(link);

        info("----Click attach button-----");
        $(ELEMENT_COMPOSER_ATTACH_LINK_BUTTON).click();
        evt.waitForAndGetElement(By.id("LinkTitle"));
    }

    /**
     * Share activity
     */
    public void shareActivity() {
        info("----Click share button----");
        if (testBase.getBrowser().contains("iexplorer")) {
            info("mouse over and click");

            WebElement el = evt.waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON, 2000, 1, 2);
            el.sendKeys("\n");
        } else
            $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    }

    /**
     * Add new activity for space
     *
     * @param text String
     * @param link String
     */
    public void addActivity(String text, String link) {
        info("-- Adding an activity--");

        if (text != "" && text != null) {
            info("----Add text into activity text box-----");
            addText(text);
        }
        if (link != "" && link != null) {
            info("----Add link into activity text box-----");
            addLink(link);
        }
        shareActivity();
        info("-- Verify that an activity has been added --");
        $(byText(text)).should(Condition.exist);
        $(ELEMENT_COMPOSER_SHARE_BUTTON).shouldBe(Condition.disabled);
        info("The activity is shared success");
    }

    @Test
    public void Upload_File_With_Text(String text) {
        ELEMENT_TAB_LINK.click();
        refresh();
        addText(text);
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        $(ELEMENT_COMPOSER_SHARE_BUTTON).should(Condition.be(Condition.enabled));
        $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    }

    public void addFormattingText(String text, activitiesFormat type) {
        info("-- Adding formating Text--");
        SelenideElement frame = $(byClassName("cke_wysiwyg_frame")).waitUntil(Condition.visible, Configuration.timeout);
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.click();
        switchTo().defaultContent();
        switch (type) {
            case Add_FormattingBOLD:
                info("check bold on toolbar");
                $(ELEMENT_BUTTON_BOLD_ICON).click();
                break;
            case Add_FormattingITALIC:
                info("check italic on toolbar");
                $(ELEMENT_BUTTON_ITALIC_ICON).click();
                break;
            case Add_FormattingQuote:
                info("check quote on toolbar");
                $(ELEMENT_BLOCKQUOTE_ICON).click();
                break;
            case Add_Formtting_numbred_List:
                info("check numbred list on toolbar");
                $(ELEMENT_NUMBERED_LIST_ICON).click();
                break;
            case Add_FormttingRemoveFormat:
                info("check remove format on toolbar");
                $(ELEMENT_BUTTON_REMOVE_FORMAT_ICON).click();
                break;
            case Add_Formtting_Bulled_List:
                info("check BulletedList on toolbar");
                $(ELEMENT_BULLETEDLIST_ICON).click();
                break;
            case Add_Formtting_Image:
                info("check BulletedList on toolbar");
                switchTo().frame(frame);
                ELEMENT_INPUT_ACTIVITY.sendKeys(text);
                switchTo().defaultContent();
                $(ELEMENT_SELECTIMAGE_ICON).click();
                $(byClassName("selectImageBox")).find(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
                $(byClassName("cke_dialog_ui_button")).parent()
                        .waitUntil(Condition.have(Condition.not(Condition.attribute("disabled"))),
                                Configuration.timeout);
                $(byClassName("cke_dialog_ui_button")).click();
                break;
            case Add_Formtting_Link:
                info("check link on toolbar");
                $(ELEMENT_SIMPLELINK_ICON).click();
                $(byClassName("cke_dialog_ui_input_textarea")).find(byAttribute("rows", "5")).setValue(text);
                $(byClassName("cke_dialog_ui_input_text")).find(byAttribute("type", "text")).setValue("https://www.google.fr/");
                $(byClassName("cke_dialog_ui_button")).click();
                break;
        }
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.sendKeys(text);
        switchTo().defaultContent();
        $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
        $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.disabled, Configuration.timeout);
    }

    public void editActivity(String text, String newtext) {
        info("-- Editing an activity--");
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).click();
        SelenideElement frame = $(byAttribute("title",
                "Rich Text Editor, composerEditInput" + idActivity)).waitUntil(Condition.visible,
                Configuration.timeout);
        $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible,Configuration.timeout).click();
        ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible,Configuration.timeout).sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.enabled, Configuration.timeout)
                .click();
        $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void editActivityImage(String text, String newtext) {
        info("-- Editing an activity--");
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).click();
        SelenideElement frame = $(byAttribute("title",
                "Rich Text Editor, composerEditInput" + idActivity)).waitUntil(Condition.visible,
                Configuration.timeout);
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.enabled, Configuration.timeout)
                .click();
        $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void editFormatedActivity(String text, String newtext) {
        info("-- Editing an activity--");
        String idActivity = $(byText(text)).parent().parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).click();
        SelenideElement frame = $(byAttribute("title",
                "Rich Text Editor, composerEditInput" + idActivity)).waitUntil(Condition.visible,
                Configuration.timeout);
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.click();
        ELEMENT_INPUT_ACTIVITY.sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.enabled, Configuration.timeout)
                .click();
        $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void cancelActivity(String text, String newtext) {
        info("-- Editing an activity--");
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).click();
        SelenideElement frame = $(byAttribute("title",
                "Rich Text Editor, composerEditInput" + idActivity)).waitUntil(Condition.visible,
                Configuration.timeout);
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.click();
        ELEMENT_INPUT_ACTIVITY.sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_CANCEL_EDITACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.enabled, Configuration.timeout)
                .click();
        $(byId(ELEMENT_BUTTON_CANCEL_EDITACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void cancelComment(String text, String newtext) {
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
        $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).click();
        SelenideElement frame = $(byAttribute("title", "Rich Text Editor, composerEditCommentcomment" + idComment));
        switchTo().frame(frame);
        $(byXpath("/html/body")).sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_CANCEL_EDITCOMMENT.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout)
                .click();
        $(byId(ELEMENT_BUTTON_CANCEL_EDITCOMMENT.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void editComment(String text, String newtext) {
        info("-- Editing a Comment--");
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout).click();
        SelenideElement frame = $(byAttribute("title", "Rich Text Editor, composerEditCommentcomment" + idComment));
        switchTo().frame(frame);
        $(byXpath("/html/body")).click();
        $(byXpath("/html/body")).sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void editCommentImage(String text, String newtext) {
        info("-- Editing a Comment--");
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
        $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).click();
        SelenideElement frame = $(byAttribute("title", "Rich Text Editor, composerEditCommentcomment" + idComment));
        switchTo().frame(frame);
        $(byXpath("/html/body")).sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void editFormattingComment(String text, String newtext) {
        info("-- Editing a Comment--");
        String idComment = $(byText(text)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
        $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).click();
        SelenideElement frame = $(byAttribute("title", "Rich Text Editor, composerEditCommentcomment" + idComment));
        switchTo().frame(frame);
        $(byXpath("/html/body")).sendKeys(newtext);
        switchTo().defaultContent();
        $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void CheckEditActivity(String text, Boolean edit) {
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        if (edit) {
            $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
            $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, Configuration.timeout);
        } else {
            $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    public void CheckEditActivityBydefault(String text, Boolean edit) {
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        if (edit) {
            $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
            $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.enabled, Configuration.timeout);
        } else {
            $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    public void CheckEditActivityInSpaceByManager(String text, Boolean edit) {
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        if (edit) {
            $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
            $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, Configuration.timeout);
        } else {
            $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible, Configuration.timeout).click();
            $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    public void CheckEditComment(String text, Boolean edit) {
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        if (edit) {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
            $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).waitUntil(Condition.visible, Configuration.timeout);
        } else {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    public void CheckEditCommentbyDefault(String text, Boolean edit) {
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        if (edit) {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
            $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout);
        } else {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    public void CheckEditCommentbySpaceManager(String text, Boolean edit) {
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        if (edit) {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
            $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).waitUntil(Condition.visible, Configuration.timeout);
        } else {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).waitUntil(Condition.visible, Configuration.timeout).click();
            $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }

    }

    public void gotoActivityViewer(String text) {
        String idActivityTime = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_TIME.replace("{id}", idActivityTime))).click();
        $(byId("ProfileActivity")).waitUntil(Condition.visible, Configuration.timeout);
    }

    public void gotoCommentViewer(String text) {
        String idCommentTime = $(byText(text)).parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        $(byId(ELEMENT_COMMENT_TIME.replace("{id}", idCommentTime))).click();
        $(byId("ProfileActivity")).waitUntil(Condition.visible, Configuration.timeout);
    }

    public void CheckEditCommentInSpaceByManager(String text, Boolean edit) {
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        if (edit) {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
            $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).waitUntil(Condition.visible, Configuration.timeout);
        } else {
            $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).waitUntil(Condition.visible, Configuration.timeout);
            $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    public void checkNotEditInTopicActivity(String topic) {
        String idActivity = $(byText(topic)).parent().parent().parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void checkNotEditInWikiActivity(String title) {
        String idActivity = $(byText(title)).parent().parent().parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    public void removeCharactersActivity(String text, int nbreCh, Boolean alltext) {
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_EDIT_ACTIVITY_LINK.replace("{id}", idActivity))).click();
        SelenideElement frame = $(byAttribute("title",
                "Rich Text Editor, composerEditInput" + idActivity)).waitUntil(Condition.visible,
                Configuration.timeout);
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.click();
        for (int i = 0; i < nbreCh; i++) {
            ELEMENT_INPUT_ACTIVITY.sendKeys(Keys.BACK_SPACE);
        }
        switchTo().defaultContent();
        if (alltext) {
            $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.disabled, Configuration.timeout);
        } else {
            $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.enabled, Configuration.timeout)
                    .click();
            $(byId(ELEMENT_BUTTON_UPDATE_ACTIVITY.replace("{id}", idActivity))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    public void removeCharactersComment(String text, int nbreCh, Boolean alltext) {
        String idComment = $(byText(text)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idComment))).click();
        $(byId(ELEMENT_EDIT_COMMENT_LINK.replace("{id}", idComment))).click();
        SelenideElement frame = $(byAttribute("title", "Rich Text Editor, composerEditCommentcomment" + idComment));
        switchTo().frame(frame);
        $(byXpath("/html/body")).click();
        for (int i = 0; i < nbreCh; i++) {
            ELEMENT_INPUT_COMMENT.sendKeys(Keys.BACK_SPACE);
        }
        switchTo().defaultContent();
        if (alltext) {
            $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.disabled, Configuration.timeout);
        } else {
            $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.enabled, Configuration.timeout)
                    .click();
            $(byId(ELEMENT_BUTTON_UPDATE_COMMENT.replace("{id}", idComment))).waitUntil(Condition.not(Condition.visible),
                    Configuration.timeout);
        }
    }

    /**
     * Post a activity with mention a user and description text
     *
     * @param username String
     * @param text     String
     * @throws AWTException exception
     */
    public void mentionUserActivity(String username, String text) {
        info("mention user in activity");
        ELEMENT_ACTIVITY_INPUT_TEXT.waitUntil(Condition.appears, Configuration.timeout).click();
        switchTo().frame(0);
        $(byXpath("/html/body")).setValue("@" + username);
        switchTo().defaultContent();
        $(byXpath("//*[@id=\"at-view-64\"]")).waitUntil(Condition.visible, Configuration.timeout);
        switchTo().frame(0);
        $(byXpath("/html/body")).pressEnter();
        switchTo().defaultContent();
        if (!text.isEmpty())
            switchTo().frame(0);
        $(byXpath("/html/body")).sendKeys(text);
        switchTo().defaultContent();
        info("Click share button");
        $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
        $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.disabled, Configuration.timeout);
    }

    /**
     * Post a comment with mention a user and description text
     *
     * @param username    String
     * @param textContent String
     * @param activity    String
     * @throws AWTException AWTException
     */
    public void addCommentWithMentionUser(String activity, String username, String textContent) {
        // get the id of activity created
        String id = $(byText(activity)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        SelenideElement frame = $(byText(activity)).parent()
                .parent()
                .parent()
                .find(byAttribute("class", "cke_wysiwyg_frame cke_reset"));
        switchTo().frame(frame);
        $(byXpath("/html/body")).setValue("@" + username);
        switchTo().defaultContent();
        $(byAttribute("data-value", username)).waitUntil(Condition.visible, Configuration.timeout);
        switchTo().frame(frame);
        $(byXpath("/html/body")).pressEnter();
        $(byXpath("/html/body")).sendKeys(textContent);
        switchTo().defaultContent();
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(textContent)).should(Condition.exist);
        info("The comment is added successfully");
    }

    /**
     * Like/Unlike an activity
     *
     * @param activityText input a text (String)
     */
    public void likeActivity(String activityText) {
        info("-- Action: Like or Unlike an activity --");
        info("-- Like activity --");
        int numberofLike = Integer.parseInt($(byText(activityText)).parent()
                .parent()
                .parent()
                .parent()
                .find(ELEMENT_ICON_LIKE_ACTIVITY)
                .parent()
                .parent()
                .getText()
                .split(" ")[1]);
        String numberAfterlike = String.valueOf(numberofLike + 1);
        homePagePlatform.refreshUntil($(byText(activityText)), Condition.visible, 1000);
        $(byText(activityText)).parent().parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).click();
        $(byId("UIActivitiesLoader")).find(byText(activityText)).parent()
                .parent()
                .parent()
                .parent()
                .find(ELEMENT_ICON_LIKE_ACTIVITY)
                .parent()
                .parent()
                .waitUntil(Condition.text(numberAfterlike), Configuration.timeout);
    }

    /**
     * Like/Unlike an activity
     *
     * @param activityText input a text (String)
     */
    public void unlikeActivity(String activityText) {
        sleep(Configuration.timeout);
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
        sleep(2000);
        int numberLike = Integer.parseInt($(byText(activityText)).parent()
                .parent()
                .parent()
                .parent()
                .find(ELEMENT_ICON_LIKE_ACTIVITY)
                .parent()
                .parent()
                .getText()
                .split(" ")[1]);
        sleep(Configuration.timeout);
        $(byText(activityText)).parent().parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).waitUntil(Condition.visible,Configuration.timeout).click();
        sleep(Configuration.timeout);
        refresh();
        sleep(Configuration.timeout);
        String numberAfterUnlike = String.valueOf(numberLike - 1);
        $(byText(activityText)).parent()
                .parent()
                .parent()
                .parent()
                .find(ELEMENT_ICON_LIKE_ACTIVITY)
                .parent()
                .parent()
                .shouldHave(Condition.text(numberAfterUnlike));
    }

    /**
     * Remove an activity
     *
     * @param name String
     */
    public void deleteActivity(String name) {
        info("remove activity");
        int repeat = 0;
        while (evt.waitForAndGetElement(ELEMENT_ACTIVITY_BOX.replace("${name}", name), 3000, 0) != null) {
            if (repeat > 5)
                break;
            evt.mouseOver(ELEMENT_ACTIVITY_BOX.replace("${name}", name), true);
            evt.click(ELEMENT_ACTIVITY_BOX_DELETE_BUTTON.replace("${name}", name), 2);

            evt.click(button.ELEMENT_OK_BUTTON);
            if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_BOX.replace("${name}", name), 3000, 0) == null)
                break;
            repeat++;
        }
        evt.waitForElementNotPresent(ELEMENT_ACTIVITY_BOX.replace("${name}", name));
        info("the activity is removed successfully");
    }

    /**
     * Click on View Change link on action bar
     *
     * @param title String
     */
    public void clickOnViewChange(String title) {
        info("Click on View change link");
        $(byXpath(ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK.replace("$title", title))).click();
        $(byXpath(ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK.replace("$title", title))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);
    }

    /**
     * Verify that has not any comment of an activity
     *
     * @param title String
     */
    public void checkNotComment(String title) {
        info("Verify that the activity hasn't any comment");
        $(By.xpath(ELEMENT_ACTIVITY_NOT_ANY_COMMENT.replace("$title", title))).waitUntil(Condition.visible, Configuration.timeout);
    }

    public void addcomment_to_activity(String id, String comment) {
        info("Add Comment");
        executeJavaScript("window.scrollBy(0,150)");
        $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        info("Test 15: Delete comment");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    }

    public void commentActivity(String activity, String comment) {
        // get the id of activity created
        String id = $(byText(activity)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.visible,Configuration.timeout).pressEnter()
                .waitUntil(Condition.not(Condition.visible), Configuration.collectionsTimeout);
        $(byText(comment)).should(Condition.visible);
    }

    public void addFormattedTextInComment(String activity, String comment, activitiesFormat type) {
        String id = $(byText(activity)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("UIActivityLoader")[1];
        SelenideElement frame = $(byAttribute("title", "Rich Text Editor, CommentTextarea" + id));
        $(byText(activity)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).click();
        homePagePlatform.refreshUntil($(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))), Condition.visible, 1000);
        switch (type) {
            case Add_FormattingBOLD:
                info("check bold on toolbar");
                $(byText(activity)).parent().parent().find(ELEMENT_BUTTON_BOLD_ICON).click();
                break;
            case Add_FormattingITALIC:
                info("check italic on toolbar");
                $(byText(activity)).parent().parent().$(ELEMENT_BUTTON_ITALIC_ICON).click();
                break;
            case Add_FormattingQuote:
                info("check quote on toolbar");
                $(byText(activity)).parent()
                        .parent()
                        .$(ELEMENT_BLOCKQUOTE_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_Formtting_numbred_List:
                info("check quote on toolbar");
                $(byText(activity)).parent()
                        .parent()
                        .$(ELEMENT_NUMBERED_LIST_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_Formtting_Bulled_List:
                info("check quote on toolbar");
                $(byText(activity)).parent()
                        .parent()
                        .$(ELEMENT_BULLETEDLIST_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_Formtting_Image:
                info("check BulletedList on toolbar");
                // click on the button comment
                switchTo().frame(frame);
                ELEMENT_INPUT_ACTIVITY.sendKeys(comment);
                switchTo().defaultContent();
                $(byText(activity)).parent().parent().$(ELEMENT_SELECTIMAGE_ICON).click();
                $(byClassName("selectImageBox")).find(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
                $(byClassName("cke_dialog_ui_button")).parent()
                        .waitUntil(Condition.have(Condition.not(Condition.attribute("disabled"))),
                                Configuration.timeout);
                $(byClassName("cke_dialog_ui_button")).click();
                $(byClassName("cke_dialog_ui_button")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

                break;
            case Add_Formtting_Link:
                info("check link on toolbar");
                $(byText(activity)).parent().parent().$(ELEMENT_SIMPLELINK_ICON).click();
                $(byClassName("cke_dialog_ui_input_textarea")).find(byAttribute("rows", "5")).setValue(comment);
                $(byClassName("cke_dialog_ui_input_text")).find(byAttribute("type", "text")).setValue("https://www.google.fr/");
                $(byClassName("cke_dialog_ui_button")).click();
                break;
        }

        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.sendKeys(comment);
        switchTo().defaultContent();
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
    }

    public void addFormattedTextInReply(String comment, String reply, String user, activitiesFormat type) {
        String id = $(byText(comment)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("CommentBlockBound")[1];
        String idBlocComment = $(byText(comment)).parent()
                .parent()
                .parent()
                .find(byText(comment))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        SelenideElement frame = $(byAttribute("title", "Rich Text Editor, CommentTextarea" + id));
        // Get id Comment button
        executeJavaScript("window.scrollBy(0,-250)");
        // Click on reply link
        $(byId(ELEMENT_lABEL_REPLY_COMMENT.replace("{id}", idBlocComment))).waitUntil(Condition.visible, Configuration.timeout)
                .click();
        // Insert the reply
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.visible, Configuration.collectionsTimeout);
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).click();
        homePagePlatform.refreshUntil($(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))), Condition.visible, 1000);
        switch (type) {
            case Add_FormattingBOLD:
                info("check bold on toolbar");
                $(byText(comment)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(ELEMENT_BUTTON_BOLD_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_FormattingITALIC:
                info("check italic on toolbar");
                $(byText(comment)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(ELEMENT_BUTTON_ITALIC_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_FormattingQuote:
                info("check quote on toolbar");
                $(byText(comment)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(ELEMENT_BLOCKQUOTE_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_Formtting_numbred_List:
                info("check quote on toolbar");
                $(byText(comment)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(ELEMENT_NUMBERED_LIST_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_Formtting_Bulled_List:
                info("check quote on toolbar");
                $(byText(comment)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(ELEMENT_BULLETEDLIST_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                break;
            case Add_Formtting_Image:
                info("check BulletedList on toolbar");
                info("check BulletedList on toolbar");
                // click on the button comment
                switchTo().frame(frame);
                ELEMENT_INPUT_ACTIVITY.sendKeys(reply);
                switchTo().defaultContent();
                $(byText(comment)).parent().parent().parent().parent().parent().find(ELEMENT_SELECTIMAGE_ICON).click();
                $(byClassName("selectImageBox")).find(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
                $(byClassName("cke_dialog_ui_button")).parent()
                        .waitUntil(Condition.have(Condition.not(Condition.attribute("disabled"))),
                                Configuration.timeout);
                $(byClassName("cke_dialog_ui_button")).click();
                $(byClassName("cke_dialog_ui_button")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

                break;
            case Add_Formtting_Link:
                info("check link on toolbar");
                $(byText(comment)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(ELEMENT_SIMPLELINK_ICON)
                        .waitUntil(Condition.visible, Configuration.timeout)
                        .click();
                $(byClassName("cke_dialog_ui_input_textarea")).find(byAttribute("rows", "5")).setValue(reply);
                $(byClassName("cke_dialog_ui_input_text")).find(byAttribute("type", "text")).setValue("https://www.google.fr/");
                $(byClassName("cke_dialog_ui_button")).click();
                break;
        }
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.sendKeys(reply);
        switchTo().defaultContent();
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(reply)).should(Condition.exist);
        $(byText(reply)).parent().parent().parent().find(byText(user)).should(Condition.exist);
        info("Verify that the reply is added");
    }

    public void commentWikiActivity(String wikiactivity, String comment) {
        // get the id of wikiactivity
        String id = $(byText(wikiactivity)).parent().parent().parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        // click on comment link
        $(byText(wikiactivity)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
    }

    public void commentTopicActivity(String description, String comment) {
        // get the id of activity created
        String id = $(byText(description)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("UIActivityLoader")[1];
        // click on comment link
        $(byText(description)).parent().parent().parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
    }

    public void deleteactivity(String text) {
        // get the id of activity created
        info("-- Editing an activity--");
        homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible,Configuration.timeout).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible,Configuration.timeout).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(byText(text)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }

    public void deleteGeneratedActivity(String text) {
        // get the id of activity created
        info("-- Editing an activity--");
        homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
        String idActivity = $(byText(text)).parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(byText(text)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }

    public void deleteFormatedActivity(String text) {
        // get the id of activity created
        info("-- Editing an activity--");
        String idActivity = $(byText(text)).parent().parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).click();
        $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), Configuration.timeout);

    }

    public void deleteActivityWiki(String activity) {
        // get the id of activity created
        String id = $(byText(activity)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("UIActivityLoader")[1];
        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        sleep(Configuration.timeout);
        $(byXpath("//i[@class='uiIconActivityAction uiIconLightGray']")).waitUntil(Condition.visible,Configuration.timeout).click();
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).waitUntil(Condition.visible,Configuration.timeout).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible,Configuration.timeout).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }

    public void likeUnlikeComment(String activity, String comment) {
        // get the id of the comment which is the same id of like comment
        String idBlocComment = $(byText(activity)).parent()
                .parent()
                .parent()
                .find(byText(comment))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).click();
    }

    public void replyToComment(String comment, String reply, String user) {
        String id = $(byText(comment)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("CommentBlockBound")[1];
        String idBlocComment = $(byText(comment)).parent()
                .parent()
                .parent()
                .find(byText(comment))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        // Get id Comment button
        executeJavaScript("window.scrollBy(0,-250)");
        // Click on reply link
        $(byId(ELEMENT_lABEL_REPLY_COMMENT.replace("{id}", idBlocComment))).click();
        // Insert the reply
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + reply + "\")", "");
        info("Verify that the reply is added");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(byText(reply)).should(Condition.exist);
        $(byText(reply)).parent().parent().find(byText(user)).should(Condition.exist);
    }

    public void replyToCommentUsingImage(String comment, String reply, String user) {
        String id = $(byText(comment)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("CommentBlockBound")[1];
        String idBlocComment = $(byText(comment)).parent()
                .parent()
                .parent()
                .find(byText(comment))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        executeJavaScript("window.scrollBy(0,-250)");
        // Click on reply link
        $(byId(ELEMENT_lABEL_REPLY_COMMENT.replace("{id}", idBlocComment))).click();
        // Insert the reply
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + reply + "\")", "");
        info("Verify that the reply is added");
        // Click on the Insert image icon
        $(byText(comment)).parent().parent().parent().parent().parent().find(byClassName("cke_button__simpleimage ")).click();
        // insert URL
        // $(byClassName("cke_dialog_ui_input_text")).findElementByClassName("cke_dialog_ui_input_text").sendKeys("http://qa-ui03.acceptance7.exoplatform.org/rest/private/jcr/repository/collaboration/Users/j___/jo___/joh___/john/Public/Activity
        // Stream Documents/eXo-Platform.png");
        $$(byClassName("cke_dialog_ui_input_text")).get(1).sendKeys(Keys.CONTROL, "v");
        $$(byClassName("cke_dialog_ui_input_text")).get(1).pressEnter();
        // Validate
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(reply)).should(Condition.exist);
        $(byText(reply)).parent().parent().find(byText(user)).should(Condition.exist);
    }

    public void deleteReplyInAS(String reply) {
        String idCommentContainer = $(byText(reply)).parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idCommentContainer))).hover().click();
        $(byId(ELEMENT_INCON_DELETE_COMMENT.replace("{id}", idCommentContainer))).click();
        // Confirm delete
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(byText(reply)).shouldNot(Condition.exist);
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }

    public void replyToCommentInPreview(String comment, String reply, String user) {
        // Click on reply link
        $(byId("commentArea")).find(byText(comment)).parent().parent().parent().find(byClassName("replyCommentLink")).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.visible, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + reply + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(reply)).parent().parent().parent().find(byText(user)).should(Condition.exist);
    }

    public void showAllReplies(String comment) {
        String idBlocComment = $(byText(comment)).parent()
                .parent()
                .parent()
                .find(byText(comment))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        // Get id Comment button
        executeJavaScript("window.scrollBy(0,-250)");
        $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK.replace("{id}", idBlocComment))).waitUntil(Condition.appears, Configuration.timeout)
                .findElementByClassName("subCommentShowAllLink")
                .click();
    }

    public void replyToReply(String activity, String reply, String replytoreply) {
        String idBlocReply = $(byText(reply)).parent()
                .parent()
                .parent()
                .find(byText(reply))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        // Get id Reply button
        executeJavaScript("window.scrollBy(0,-250)");
        String id = $(byText(activity)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("UIActivityLoader")[1];
        // Click on reply link
        $(byId(ELEMENT_lABEL_REPLY_COMMENT.replace("{id}", idBlocReply))).click();
        // Insert the reply
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + replytoreply + "\")", "");
        info("Verify that the reply is added");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(replytoreply)).should(Condition.visible);
    }

    public void replyToReplyInPreviewMode(String reply, String replytoreply) {
        $(byId("commentArea")).find(byText(reply)).parent().parent().parent().find(byClassName("replyCommentLink")).click();

        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + replytoreply + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    }

    public void deletecomment(String activity, String comment) {
        String idBlocComment = $(byText(activity)).parent()
                .parent()
                .parent()
                .find(byText(comment))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];

        $(byId(ELEMENT_COMMENT_DROPDOWN.replace("{id}", idBlocComment))).hover().click();
        $(byId(ELEMENT_INCON_DELETE_COMMENT.replace("{id}", idBlocComment))).click();
        // Confirm delete
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(byText(comment)).shouldNot(Condition.exist);
    }

    public void likeReply(String reply) {
        String idReplyContainer = $(byText(reply)).parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idReplyContainer))).click();
    }

    public enum changeTypes {
        No_Value, Has_One_Value;
    }

    public enum activitiesFormat {
        Add_FormattingBOLD, Add_FormattingITALIC, Add_FormattingQuote, Add_Formtting_numbred_List, Add_Formtting_Bulled_List, Add_FormttingRemoveFormat, Add_Formtting_Image, Add_Formtting_Link;
    }
}
