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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_VIEW_ALL_REPLIES_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
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

    @AfterEach
    public void signout() {
        manageLogInOut.signOut();
    }


    @Test
    public void test01_ReplyToCommentInAS() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);

        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test03_ReplyToCommentShortenSection() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply1 = "Reply" + getRandomNumber();
        String reply2 = "Reply" + getRandomNumber();
        String reply3 = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply1);
        activityStream.replytocomment(comment, reply2);
        activityStream.replytocomment(comment, reply3);
        refresh();
        //Check that the replies number in show reply link
        activityStream.showallreplies(comment);
        //Check that replies are well added
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        //Check that replies are displayed under the parent comment
        String comment_parent_id = $(byText(comment)).parent().parent().parent().parent().getAttribute("data-comment-id");
        assertEquals($(byText(reply1)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        assertEquals($(byText(reply2)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        assertEquals($(byText(reply3)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test04_CheckReplyNumberInAS() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);
        activityStream.replytocomment(comment, reply);
        activityStream.replytocomment(comment, reply);
        activityStream.replytocomment(comment, reply);
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
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test05_Checktworepliesnotcollapsed() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply1 = "Reply" + getRandomNumber();
        String reply2 = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply1);
        activityStream.replytocomment(comment, reply2);
        refresh();
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test06_ReplyToReply() {

        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replytoreply(activity1, reply, replytoreply);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test07_CheckReplyAfterDeleteParentComment() {

        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replytoreply(activity1, reply, replytoreply);
        activityStream.deletecomment(activity1, comment);
        $(byText(reply)).shouldNot(Condition.exist);
        $(byText(replytoreply)).shouldNot(Condition.exist);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity1);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }
}
