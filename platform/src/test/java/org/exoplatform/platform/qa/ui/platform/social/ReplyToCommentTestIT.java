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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        refresh();
        executeJavaScript("window.scrollBy(0,-2000)", "");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test02_DeleteReplyToCommentInAS() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
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
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        activityStream.deleteReplyInAS(reply);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test03_ReplyToCommentShortenSection() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
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
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply1, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply2, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply3, DATA_NAME_USER1);
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
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test04_CheckReplyNumberInAS() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
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
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);

        refresh();
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
        $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK.replace("{id}", idBlocComment))).waitUntil(Condition.appears, Configuration.timeout).shouldHave(Condition.text("4"));
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test05_Checktworepliesnotcollapsed() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply1 = "Reply" + getRandomNumber();
        String reply2 = "Reply" + getRandomNumber();
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
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply1, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply2, DATA_NAME_USER1);
        refresh();
        $(byText(reply1)).waitUntil(Condition.visible, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.visible, Configuration.timeout);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test06_ReplyToReply() {

        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
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
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replyToReply(activity, reply, replytoreply);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test07_CheckReplyAfterDeleteParentComment() {

        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
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
        homePagePlatform.goToHomePage();
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replyToReply(activity, reply, replytoreply);
        activityStream.deletecomment(activity, comment);
        $(byText(reply)).shouldNot(Condition.exist);
        $(byText(replytoreply)).shouldNot(Condition.exist);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity);

    }

    //Check that quote a post in forum turns to reply to a comment in activity stream
    @Test
    @Tag("sabis")
    public void test08_CheckQuoteInForumTurnsToReplyToCommentInAS() {
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
        sleep(Configuration.timeout);
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

    @Test
    public void test09_CheckReplyToCommentInForumBelongsToParentComment() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
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
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byText(nameTopic)).click();
        $(byText(reply)).parent().shouldHave(Condition.text(comment));
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);

    }

    //Check reply turns to quote
    @Test
    public void test10_CheckReplyToCommentTurnsToQuoteInForum() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
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
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byText(nameTopic)).click();
        $(byClassName("contentQuote")).shouldHave(Condition.text(comment));
        $(byClassName("contentQuote")).shouldHave(Condition.text(DATA_USER2));
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToForum();
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);

    }

    //Check that each reply is attached to its parent comment
    @Test
    public void test11_CheckAllRepliesBelongsToSameParentCommentInTpoicActivity() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String comment1 = "Comment1" + getRandomNumber();
        String comment2 = "Comment2" + getRandomNumber();
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
        forumHomePage.goToTopic(nameTopic);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentTopicActivity(description, comment1);
        activityStream.commentTopicActivity(description, comment2);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
        activityStream.replyToComment(comment2, reply2, DATA_NAME_USER1);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byText(nameTopic)).click();
        $(byText(reply1)).parent().shouldHave(Condition.text(comment1));
        $(byText(reply2)).parent().shouldHave(Condition.text(comment2));
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);

    }

    //Check when replying to same comment for two times
    @Test
    public void test12_CheckTwoRepiesyBelongsToSameParentCommentInForum() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        info("Finished creating data test");
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
        forumHomePage.goToTopic(nameTopic);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentTopicActivity(description, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replyToComment(comment, reply1, DATA_NAME_USER1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replyToComment(comment, reply2, DATA_NAME_USER2);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        $(byText(nameTopic)).click();
        $(byText(reply1)).parent().shouldHave(Condition.text(comment));
        $(byText(reply2)).parent().shouldHave(Condition.text(comment));
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);

    }

    @Test
    public void test13_CheckReplyToCommentInPreviewMode() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        ELEMENT_TAB_LINK.click();
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
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test14_CheckReplyToReplyInPreviewMode() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
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
    public void test15_ReplyToCommentShortenSectionInPreview() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
        activityStream.addActivity(activity, "");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        //mary comments
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
        //john replys
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply1, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        //mary replys
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
        //Check that the replies number in show reply link
        activityStream.showAllReplies(comment);
        //Check that replies are well added
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        //Check that replies are displayed under the parent comment
        String data_comment = $(byText(comment)).parent().parent().parent().getAttribute("data-comment");
        assertEquals($(byText(reply1)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        assertEquals($(byText(reply2)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        assertEquals($(byText(reply3)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test16_CheckReplyNumberInPreview() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        String reply4 = "Reply4" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
        activityStream.addActivity(activity, "");
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
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply1, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply2, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply3, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        activityStream.replyToCommentInPreview(comment, reply4, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        refresh();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).hover().click();
        $(byId("commentArea")).find(byClassName("subCommentShowAllLink")).shouldHave(Condition.text("4"));
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test17_ReplyToCommentInTasks() {
        String task = "task" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
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

        tasksManagement.deleteTask(task);
    }

    @Test
    public void test18_DeleteReplyInTaks() {
        String task = "Task" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        tasksManagement.addTask(task);
        tasksManagement.addCoworker(task);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.commentTask(task, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        tasksManagement.deleteReply(task, reply);
        tasksManagement.deleteTask(task);
    }

    @Test
    public void test19_ReplyToReplyInTasks() {
        String task = "Task" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
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
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToReply(task, reply, replytoreply, DATA_NAME_USER2);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.deleteTask(task);
    }

    @Test
    public void test20_ReplyToCommentShortenSectionInTask() {
        String task = "Task" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        tasksManagement.addTask(task);
        tasksManagement.addCoworker(task);
        //login mary
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.commentTask(task, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToCommentTask(task, reply1, DATA_NAME_USER1);
        refresh();
        tasksManagement.replyToCommentTask(task, reply2, DATA_NAME_USER1);
        refresh();
        tasksManagement.replyToCommentTask(task, reply3, DATA_NAME_USER1);
        refresh();
        //Check that the replies number in show reply link
        tasksManagement.showAllReplies(task, comment);
        //Check that replies are well added
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        //Check that replies are displayed under the parent comment
        String idDataComment = $(byText(comment)).parent().parent().getAttribute("data-commentid");
        assertEquals($(byText(reply1)).parent().parent().getAttribute("data-parent-comment"), idDataComment);
        assertEquals($(byText(reply2)).parent().parent().getAttribute("data-parent-comment"), idDataComment);
        assertEquals($(byText(reply3)).parent().parent().getAttribute("data-parent-comment"), idDataComment);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        tasksManagement.deleteTask(task);
    }

    @Test
    public void test21_CheckReplyNumberInTask() {
        String task = "Task" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        tasksManagement.addTask(task);
        tasksManagement.addCoworker(task);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.commentTask(task, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        refresh();
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        refresh();
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        refresh();
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        refresh();
        $(byText(task)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        //Check that the replies number in show reply link
        String idDataComment = $(byText(comment)).parent().parent().parent().getAttribute("data-commentid");
        $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK_TASK.replace("{id}", idDataComment))).waitUntil(Condition.appears, Configuration.timeout).shouldHave(Condition.text("4"));
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.deleteTask(task);
    }

    @Test
    public void test22_ChecktworepliesnotcollapsedInTasks() {
        String task = "Task" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        tasksManagement.addTask(task);
        tasksManagement.addCoworker(task);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.commentTask(task, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToCommentTask(task, reply1, DATA_NAME_USER1);
        refresh();
        tasksManagement.replyToCommentTask(task, reply2, DATA_NAME_USER1);
        refresh();
        $(byText(task)).click();
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        tasksManagement.deleteTask(task);
    }

    @Test
    public void test23_CheckReplyAfterDeleteParentCommentInTasks() {

        String task = "Task" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        tasksManagement.addTask(task);
        tasksManagement.addCoworker(task);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.commentTask(task, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToCommentTask(task, reply, DATA_NAME_USER1);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToTaskPage();
        tasksManagement.replyToReply(task, reply, replytoreply, DATA_NAME_USER2);
        tasksManagement.deleteComment(task, comment);
        $(byText(reply)).shouldNot(Condition.exist);
        $(byText(replytoreply)).shouldNot(Condition.exist);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        homePagePlatform.goToTaskPage();
        tasksManagement.deleteTask(task);
    }

    @Test
    @Disabled
    public void test24_CheckReplyWithImage() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        ELEMENT_TAB_LINK.click();
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
    public void test25_checkNotificationWhenOneUserLikeReply() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String notifContent = " likes your comment.";
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity, "");
        activityStream.commentActivity(activity, comment);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER2 + notifContent));
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(DATA_NAME_USER2))
                .parent()
                .parent()
                .find(byText(reply))
                .should(Condition.exist);
        assertEquals("rgba(227, 236, 246, 1)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        homePagePlatform.goToHomePage();
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test26_checkNotificationWhenTwoUserLikeReply() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String notifContent = " like your comment.";
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity, "");
        activityStream.commentActivity(activity, comment);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER3 + " and " + DATA_NAME_USER2 + notifContent));
        assertEquals("rgba(227, 236, 246, 1)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        homePagePlatform.goToHomePage();
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test27_checkNotificationWhenManyUserLikeReply() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String notifContent = " and 1 others like your comment.";
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity, "");
        activityStream.commentActivity(activity, comment);
        activityStream.replyToComment(comment, reply, DATA_NAME_USER1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.likeReply(reply);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER4 + ", " + DATA_NAME_USER3 + notifContent));
        assertEquals("rgba(227, 236, 246, 1)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        homePagePlatform.goToHomePage();
        activityStream.deleteactivity(activity);
    }

    @Test
    public void test28_checkNotificationWhenOneUserLikeCommentInDocumentPreview() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String notifContent = " likes your comment.";
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        ELEMENT_TAB_LINK.click();
        ELEMENT_TAB_LINK.click();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity, "");
        String id = $(byText(activity)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        activityStream.replyToCommentInPreview(comment, reply, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        $(byId("commentArea")).find(byText(reply)).parent().find(byClassName("likeCommentLink")).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER2 + notifContent));
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(DATA_NAME_USER2))
                .parent()
                .parent()
                .find(byText(reply))
                .should(Condition.exist);
        assertEquals("rgba(227, 236, 246, 1)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        homePagePlatform.goToHomePage();
        activityStream.deleteactivity(activity);
    }

    @Test
    public void test29_checkNotificationWhenTwoUsersLikeCommentInDocumentPreview() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String notifContent = " like your comment.";
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        homePagePlatform.goToHomePage();
        ELEMENT_TAB_LINK.click();
        ELEMENT_TAB_LINK.click();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        activityStream.addActivity(activity, "");
        String id = $(byText(activity)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        activityStream.replyToCommentInPreview(comment, reply, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        $(byId("commentArea")).find(byText(reply)).parent().find(byClassName("likeCommentLink")).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        $(byId("commentArea")).find(byText(reply)).parent().find(byClassName("likeCommentLink")).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER3 + " and " + DATA_NAME_USER2 + notifContent));
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(DATA_NAME_USER2))
                .parent()
                .parent()
                .find(byText(reply))
                .should(Condition.exist);
        assertEquals("rgba(227, 236, 246, 1)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        homePagePlatform.goToHomePage();
        activityStream.deleteactivity(activity);

    }

    @Test
    public void test30_checkNotificationWhenManyUsersLikeCommentInDocumentPreview() {
        String activity = "Activity" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String notifContent = " and 1 others like your comment.";
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        ELEMENT_TAB_LINK.click();
        ELEMENT_TAB_LINK.click();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity, "");
        String id = $(byText(activity)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        activityStream.replyToCommentInPreview(comment, reply, DATA_NAME_USER1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        $(byId("commentArea")).find(byText(reply)).parent().find(byClassName("likeCommentLink")).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        $(byId("commentArea")).find(byText(reply)).parent().find(byClassName("likeCommentLink")).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).click();
        $(byId("commentArea")).find(byText(reply)).parent().find(byClassName("likeCommentLink")).click();
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_ICON_NOTIFICATION.click();
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(reply))
                .parent()
                .parent()
                .shouldHave(Condition.text(DATA_NAME_USER4 + ", " + DATA_NAME_USER3 + notifContent));
        ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                .find(byText(DATA_NAME_USER4))
                .parent()
                .parent()
                .find(byText(reply))
                .should(Condition.exist);
        assertEquals("rgba(227, 236, 246, 1)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().click();
        refresh();
        ELEMENT_ICON_NOTIFICATION.click();
        assertEquals("rgba(0, 0, 0, 0)",ELEMENT_NOTIFICATION_POPUP.find(byText(reply)).parent().parent().parent().parent().parent().getCssValue("background-color"));
        homePagePlatform.goToHomePage();
        activityStream.deleteactivity(activity);

    }
}