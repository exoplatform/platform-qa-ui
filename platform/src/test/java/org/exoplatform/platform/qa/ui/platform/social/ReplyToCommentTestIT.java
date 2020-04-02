package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_VIEW_ALL_REPLIES_LINK;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_VIEW_ALL_REPLIES_LINK_TASK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by dmardassi on 16/10/2017.
 */
@Tag("social")
@Tag("sniff")
public class ReplyToCommentTestIT extends Base {

    ForumTopicManagement forumTopicManagement;

    TasksManagement tasksManagement;

    ForumForumManagement forumForumManagement;

    ForumCategoryManagement forumCategoryManagement;

    NavigationToolbar navigationToolbar;

    AddUsers addUsers;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    ConnectionsManagement connectionsManagement;

    ActivityStream activityStream;

    ForumHomePage forumHomePage;

    ProjectsManagement projectsManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        forumHomePage = new ForumHomePage(this);
        forumForumManagement = new ForumForumManagement(this);
        forumCategoryManagement = new ForumCategoryManagement(this);
        navigationToolbar = new NavigationToolbar(this);
        forumTopicManagement = new ForumTopicManagement(this);
        homePagePlatform = new HomePagePlatform(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        connectionsManagement = new ConnectionsManagement(this);
        activityStream = new ActivityStream(this);
        projectsManagement = new ProjectsManagement(this);
        tasksManagement = new TasksManagement(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
            manageLogInOut.signOut();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    @Test
    public void test01_ReplyToCommentInAS() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        String reply1 = "Reply" + getRandomNumber();
        String reply2 = "Reply" + getRandomNumber();
        String reply3 = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        info("Reply To Comment Shorten Section");
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply1, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply2, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply3, DATA_NAME_USER1);
        info("Check Two Replies Not Collapsed");
        $(byText(reply1)).waitUntil(Condition.visible, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.visible, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.visible, Configuration.timeout);
        refresh();
        info("Check Reply Number In AS");
        //Check that the replies number in show reply link
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
        $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK.replace("{id}", idBlocComment))).waitUntil(Condition.appears, Configuration.timeout).shouldHave(Condition.text("3"));
        refresh();
        //Check that the replies number in show reply link
        activityStream.showAllReplies(comment);
        //Check that replies are well added
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        //Check that replies are displayed under the parent comment
        String comment_parent_id = $(byText(comment)).parent().parent().parent().parent().getAttribute("data-comment-id");
        assertEquals($(byText(reply1)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        assertEquals($(byText(reply2)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        assertEquals($(byText(reply3)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        info("Reply To Reply");
        activityStream.showAllReplies(comment);
        activityStream.replyToReply(activity, reply, replytoreply);
        manageLogInOut.signOut();
        info("Delete Reply To Comment In AS");
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.showAllReplies(comment);
        activityStream.deleteReplyInAS(reply1);
        info("Check Reply After Delete Parent Comment");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.deletecomment(activity, comment);
        $(byText(reply)).shouldNot(Condition.exist);
        $(byText(replytoreply)).shouldNot(Condition.exist);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test02_CheckQuoteInForumTurnsToReplyToCommentInAS() {
        String title = "Title" + getRandomNumber();
        String content = "Content" + getRandomNumber();
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String quote="quote"+getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        info("Open forum portlet");
        homePagePlatform.goToForum();
        info("Add a new category");
        forumCategoryManagement.addCategorySimple(nameCat, "", description);
        info("Add a new forum");
        forumForumManagement.addForumSimple(nameForum, "", description);
        info("Add a new topic");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(nameTopic, description, "", "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToForum();
        forumHomePage.goToTopic(nameTopic);
        executeJavaScript("window.scrollBy(0,-5500)", "");
        forumTopicManagement.postReply(title, content);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToForum();
        forumHomePage.goToTopic(nameTopic);
        forumTopicManagement.quotePost(content, quote);
        homePagePlatform.goToHomePage();
        executeJavaScript("window.scrollBy(0,150)", "");
        homePagePlatform.refreshUntil($(byText(quote)),Condition.visible,1000);
        String comment_parent_id = $(byText(quote)).parent().parent().parent().parent().getAttribute("data-comment-id");
        assertEquals($(byText(quote)).parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("data-parent-comment"), comment_parent_id);
        homePagePlatform.goToHomePage();
        homePagePlatform.goToForum();
        info("Go to Forum home page");
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).waitUntil(Condition.visible,Configuration.timeout).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);

    }

    //Check that quote a post in forum turns to reply to a comment in activity stream
    @Test
    @Tag("sabis")
    public void test03_CheckAllRepliesBelongsToSameParentCommentInTpoicActivity() {
        String title = "Title" + getRandomNumber();
        String content = "Content" + getRandomNumber();
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String comment2 = "Comment2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        info("Open forum portlet");
        homePagePlatform.goToForum();
        info("Add a new category");
        forumCategoryManagement.addCategorySimple(nameCat, "", description);
        info("Add a new forum");
        forumForumManagement.addForumSimple(nameForum, "", description);
        info("Add a new topic");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(nameTopic, description, "", "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentTopicActivity(description, comment);
        activityStream.commentTopicActivity(description, comment2);
        homePagePlatform.goToForum();
        forumHomePage.goToTopic(nameTopic);
        executeJavaScript("window.scrollBy(0,-5500)", "");
        forumTopicManagement.postReply(title, content);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        sleep(2000);
        activityStream.replyToComment(comment2, reply2, DATA_NAME_USER1);
        info("Check Reply To Comment In Forum Belongs To Parent Comment");
        sleep(1000);
        executeJavaScript("window.scrollBy(0,-500)", "");
        $(byText(nameTopic)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        info("Check Reply To Comment Turns To Quote In Forum");
        $(byClassName("contentQuote")).shouldHave(Condition.text(comment));
        $(byClassName("contentQuote")).shouldHave(Condition.text(DATA_USER2));
        info("Check All Replies Belong To Same Parent Comment In Topic Activity");
        $(byText(reply)).parent().shouldHave(Condition.text(comment));
        $(byText(reply2)).parent().shouldHave(Condition.text(comment2));
        info("Check Two Replies Belong To Same Parent Comment In Forum");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replyToComment(comment, reply3, DATA_NAME_USER2);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        $(byText(nameTopic)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        $(byText(reply3)).parent().shouldHave(Condition.text(comment));
        info("Go to Forum home page");
        homePagePlatform.goToForum();
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);

    }

    @Test
    public void test04_CheckReplyToCommentThenReplyToReplyThenReplyToCommentShortenSectionThenReplyNumberInPreviewMode() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        String reply02 = "Reply02" + getRandomNumber();
        String reply03 = "Reply03" + getRandomNumber();
        String reply04 = "Reply04" + getRandomNumber();
        ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity, "");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        String id = $(byText(activity)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        refresh();
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply2, DATA_NAME_USER2);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        //john replys
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply3, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        $(byId("commentArea")).find(byClassName("subCommentShowAllLink")).shouldHave(Condition.text("3"));

        //Check that the replies number in show reply link
        activityStream.showAllReplies(comment);
        //Check that replies are well added
        $(byText(reply)).waitUntil(Condition.appears, openBrowserTimeoutMs);
        $(byText(reply2)).waitUntil(Condition.appears, openBrowserTimeoutMs);
        $(byText(reply3)).waitUntil(Condition.appears, openBrowserTimeoutMs);
        //Check that replies are displayed under the parent comment
        String data_comment = $(byText(comment)).parent().parent().parent().getAttribute("data-comment");
        assertEquals($(byText(reply)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        assertEquals($(byText(reply2)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        assertEquals($(byText(reply3)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply02, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply03, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply04, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER2, PLFData.DATA_PASS);
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToReplyInPreviewMode(reply,replytoreply);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test05_ReplyToReplyThenToCommentShortenSectionThenCheckReplyNumberInTasks() {
        String task = "Task" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        tasksManagement.addTask(task);
        $(byText(task)).should(Condition.exist);
        tasksManagement.addCoworker(task);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.commentTask(task, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        info("Check Reply After Delete Parent Comment In Tasks");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToReply(task, reply, replytoreply, DATA_NAME_USER2);
        tasksManagement.deleteComment(task, comment);
        $(byText(reply)).shouldNot(Condition.exist);
        $(byText(replytoreply)).shouldNot(Condition.exist);
        homePagePlatform.goToTaskPage();
        tasksManagement.commentTask(task, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        info("test_Reply To Comment Shorten Section In Task");
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToReply(task, reply, replytoreply, DATA_NAME_USER2);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToCommentTask(task, reply2, DATA_NAME_USER1);
        refresh();
        tasksManagement.replyToCommentTask(task, reply3, DATA_NAME_USER1);
        refresh();
        //Check that the replies number in show reply link
        tasksManagement.showAllReplies(task, comment);
        //Check that replies are well added
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        //Check that replies are displayed under the parent comment
        info("Check Reply Number In Task");
        String idDataComment = $(byText(comment)).parent().parent().parent().getAttribute("data-commentid");
        Assert.assertEquals($(byId(ELEMENT_VIEW_ALL_REPLIES_LINK_TASK.replace("{id}", idDataComment))).parent().waitUntil(Condition.appears, Configuration.timeout).getText(),
                 DATA_NAME_USER2 + " less than a minute ago\n" +
                        comment + "\n" +
                        "Reply\n" +
                         DATA_NAME_USER1 + " less than a minute ago\n" +
                         reply + "\n" +
                        "Reply\n" +
                         DATA_NAME_USER2 + " less than a minute ago\n" +
                         replytoreply + "\n" +
                        "Reply\n" +
                         DATA_NAME_USER1 + " less than a minute ago\n" +
                         reply2 + "\n" +
                        "Reply\n" +
                         DATA_NAME_USER1 + " less than a minute ago\n" +
                         reply3 + "\n" +
                        "Reply");
        assertEquals($(byText(reply)).parent().parent().parent().getAttribute("data-parent-comment"), idDataComment);
        assertEquals($(byText(reply2)).parent().parent().parent().getAttribute("data-parent-comment"), idDataComment);
        assertEquals($(byText(reply3)).parent().parent().parent().getAttribute("data-parent-comment"), idDataComment);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.deleteTask(task);
    }

    @Test
    @Disabled
    public void test_CheckReplyWithImage() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply1, DATA_NAME_USER1);
        String id = $(byText(activity)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        OPEN_IN_DOCUMENT_ICON.click();
        GO_BACK_ICON.click();
        ELEMENT_ADDRESS_BAR_ICON_VIEW.click();
        $(byText("eXo-Platform.png")).contextClick();
        COPY_URL_TO_CLIPBOARD_BUTTON.click();
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToHomePage();
        activityStream.replyToCommentUsingImage(comment, reply2, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply3, DATA_NAME_USER1);
        refresh();
        activityStream.showAllReplies(comment);
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        activityStream.deleteActivity(activity);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test06_checkNotificationWhenManyUserLikeReplyThenWhenManyUsersLikeCommentInDocumentPreview() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String activity2 = "Activity2" + getRandomNumber();
        String comment2 = "Comment2" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String notifContent = " and 1 others like your comment.";
        String notifContentOneUser = " likes your comment.";

        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
        ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity2, "");
        String id = $(byText(activity2)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment2 + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment2)).should(Condition.exist);
        activityStream.replyToCommentInPreview(comment2, reply2, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity, "");
        activityStream.commentActivity(activity, comment);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.appears, Configuration.timeout).click();
        $(byId("commentArea")).find(byText(reply2)).parent().find(byClassName("likeCommentLink")).waitUntil(Condition.appears, Configuration.timeout).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.appears, Configuration.timeout).click();
        info("Check Notification When One User Likes Reply");
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER2 + notifContentOneUser));
        info("Check Notification When One User Likes Comment In Document Preview");
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
              .find(byText(reply2))
              .parent()
              .parent()
              .shouldHave(Condition.text(DATA_NAME_USER2 + notifContentOneUser));
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
              .find(byText(DATA_NAME_USER2))
              .parent()
              .parent()
              .find(byText(reply2))
              .should(Condition.exist);
        manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.appears, Configuration.timeout).click();
        $(byId("commentArea")).find(byText(reply2)).parent().find(byClassName("likeCommentLink")).waitUntil(Condition.appears, Configuration.timeout).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.appears, Configuration.timeout).click();
        manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.appears, Configuration.timeout).click();
        $(byId("commentArea")).find(byText(reply2)).parent().find(byClassName("likeCommentLink")).waitUntil(Condition.appears, Configuration.timeout).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.appears, Configuration.timeout).click();
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        info("Check Notification When Many Users Like Reply");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER4 + ", " + DATA_NAME_USER3 + notifContent));
        info("Check Notification When Many Users Like Comment In Document Preview");
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
              .find(byText(reply2))
              .parent()
              .parent()
              .shouldHave(Condition.text(DATA_NAME_USER4 + ", " + DATA_NAME_USER3 + notifContent));
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
              .find(byText(DATA_NAME_USER4))
              .parent()
              .parent()
              .find(byText(reply2))
              .should(Condition.exist);
        assertEquals("rgba(227, 236, 246, 1)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        assertEquals("rgba(227, 236, 246, 1)", ELEMENT_NOTIFICATION_POPUP.find(byText(reply2)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply2)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)", ELEMENT_NOTIFICATION_POPUP.find(byText(reply2)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        homePagePlatform.goToHomePage();
        activityStream.deleteactivity(activity);
    }

}