package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_BUTTON_JOIN_SPACE;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_INPUT_INVITE_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_BTN_INVITE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 11/09/17.
 */
public class LikeCommentTestIT extends Base {
    NavigationToolbar navigationToolbar;

    AddUsers addUsers;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    ConnectionsManagement connectionsManagement;

    ActivityStream activityStream;

    SpaceHomePage spaceHomePage;
    SpaceManagement spaceManagement;
    SpaceSettingManagement spaceSettingManagement;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        homePagePlatform = new HomePagePlatform(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        connectionsManagement = new ConnectionsManagement(this);
        activityStream = new ActivityStream(this);
        spaceHomePage=new SpaceHomePage(this);
        spaceManagement=new SpaceManagement(this);
        spaceSettingManagement=new SpaceSettingManagement(this);
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
    public void test02_CheckNumberWhenUserLikeComment(){
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, ""); // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];

        manageLogInOut.signIn(DATA_USER2,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER3,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER4,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().shouldHave(Condition.text("(3)"));

        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappears,Configuration.timeout);
        // scroll up
        executeJavaScript("window.scrollBy(0,-950)","");
        homePagePlatform.goToAllSpace();
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
        connectionsManagement.removeConnection(DATA_USER3);
        connectionsManagement.removeConnection(DATA_USER4);

    }

    @Test
    public void test03_CheckNumberWhenUserUnLikeComment(){
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, ""); // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];

        manageLogInOut.signIn(DATA_USER2,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER3,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER4,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().shouldHave(Condition.text("(3)"));
        //connect with user 4 and unlike comment
        manageLogInOut.signIn(PLFData.DATA_USER4,PLFData.DATA_PASS);
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().shouldHave(Condition.text("(2)"));
        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappears,Configuration.timeout);
        // scroll up
        executeJavaScript("window.scrollBy(0,-950)","");
        homePagePlatform.goToAllSpace();
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
        connectionsManagement.removeConnection(DATA_USER3);
        connectionsManagement.removeConnection(DATA_USER4);

    }

    @Test
    public void test04_CheckListOfUsersWhoLikeComment(){
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, ""); // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];

        manageLogInOut.signIn(DATA_USER2,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER3,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER4,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().find(byText("(3)")).click();
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller")).should(Condition.exist);
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David")).should(Condition.exist);
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams")).should(Condition.exist);
        ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
        executeJavaScript("window.scrollBy(0,-950)","");
        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappears,Configuration.timeout);
        // scroll up
        executeJavaScript("window.scrollBy(0,-950)","");
        homePagePlatform.goToAllSpace();
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
        connectionsManagement.removeConnection(DATA_USER3);
        connectionsManagement.removeConnection(DATA_USER4);

    }

    @Test
    public void test05_DisplayListOfUsersOrderedByTheMostRecentLike(){
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, ""); // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];

        manageLogInOut.signIn(DATA_USER2,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER3,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER4,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().find(byText("(3)")).click();
        ELEMENT_FIRST_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Jack Miller"));
        ELEMENT_SECOND_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("James David"));
        ELEMENT_THIRD_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Mary Williams"));
        ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
        executeJavaScript("window.scrollBy(0,-950)","");
        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappears,Configuration.timeout);
        // scroll up
        executeJavaScript("window.scrollBy(0,-950)","");
        homePagePlatform.goToAllSpace();
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
        connectionsManagement.removeConnection(DATA_USER3);
        connectionsManagement.removeConnection(DATA_USER4);

    }

    @Test
    public void test06_CheckConnexionStatusButtonOfUsersLikeComment(){
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        connectionsManagement.connectToAUser(DATA_USER3);
        connectionsManagement.connectToAUser(DATA_USER4);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, ""); // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];

        manageLogInOut.signIn(DATA_USER2,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER3,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER4,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().find(byText("(3)")).click();
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller")).parent().parent().find(byClassName("uiActionLike")).shouldHave(Condition.text("Remove Connection"));
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David")).parent().parent().find(byClassName("uiActionLike")).shouldHave(Condition.text("Remove Connection"));
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams")).parent().parent().find(byClassName("uiActionLike")).shouldHave(Condition.text("Remove Connection"));
        ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
        executeJavaScript("window.scrollBy(0,-950)","");
        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappears,Configuration.timeout);
        // scroll up
        executeJavaScript("window.scrollBy(0,-950)","");
        homePagePlatform.goToAllSpace();
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
        connectionsManagement.removeConnection(DATA_USER3);
        connectionsManagement.removeConnection(DATA_USER4);

    }
    @Test
    public void test07_CheckConnexionStatusButtonOfUsersLikeCommentInSpace(){


        String space = "space" + getRandomNumber();
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        info("Create a space and invite users");
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceSettingManagement.goToMemberTab();
        ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER2);
        $(ELEMENT_SPACE_BTN_INVITE).click();
        refresh();
        ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER3);
        $(ELEMENT_SPACE_BTN_INVITE).click();
        refresh();
        ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER4);
        $(ELEMENT_SPACE_BTN_INVITE).click();
        spaceManagement.goToActivityStreamTab();
        activityStream.addActivity(activity1, "");
        // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        executeJavaScript("window.scrollBy(0,-550)","");
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];

        manageLogInOut.signIn(DATA_USER2,DATA_PASS);
        homePagePlatform.goToAllSpace();
        spaceManagement.acceptAInvitation(space);
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();
        manageLogInOut.signIn(DATA_USER3,DATA_PASS);
        homePagePlatform.goToAllSpace();
        spaceManagement.acceptAInvitation(space);
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();
        manageLogInOut.signIn(DATA_USER4,DATA_PASS);
        homePagePlatform.goToAllSpace();
        spaceManagement.acceptAInvitation(space);
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();
        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        homePagePlatform.goToSpecificSpace(space);
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().find(byText("(3)")).click();
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller")).parent().parent().find(byClassName("uiActionLike")).shouldHave(Condition.text("Connect"));
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David")).parent().parent().find(byClassName("uiActionLike")).shouldHave(Condition.text("Connect"));
        ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams")).parent().parent().find(byClassName("uiActionLike")).shouldHave(Condition.text("Connect"));
        ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
        homePagePlatform.goToAllSpace();
        spaceManagement.deleteSpace(space,false);

    }

    @Test
    public void test08_checkListOfUsersInToolTipWhen11UsersLikeComment(){
        String space = "space" + getRandomNumber();String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String username1 = "a" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "b" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String username3 = "c" + getRandomString();
        String email3 = username3 + "@gmail.com";
        String username4 = "d" + getRandomString();
        String email4 = username4 + "@gmail.com";
        String username5 = "e" + getRandomString();
        String email5 = username5 + "@gmail.com";
        String username6 = "f" + getRandomString();
        String email6 = username6 + "@gmail.com";
        String username7 = "g" + getRandomString();
        String email7 = username7 + "@gmail.com";
        String username8 = "h" + getRandomString();
        String email8 = username8 + "@gmail.com";
        String username9 = "i" + getRandomString();
        String email9 = username9 + "@gmail.com";
        String username10 = "j" + getRandomString();
        String email10 = username10 + "@gmail.com";
        String username11 = "k" + getRandomString();
        String email11 = username11 + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        addUsers.addUser(username4, password, email4, username4, username4);
        addUsers.addUser(username5, password, email5, username5, username5);
        addUsers.addUser(username6, password, email6, username6, username6);
        addUsers.addUser(username7, password, email7, username7, username7);
        addUsers.addUser(username8, password, email8, username8, username8);
        addUsers.addUser(username9, password, email9, username9, username9);
        addUsers.addUser(username10, password, email10, username10, username10);
        addUsers.addUser(username11, password, email11, username11, username11);
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceHomePage.goToSpaceSettingTab();
        $(byXpath("//*[@id=\"UITabPane\"]/ul/li[2]/a")).click();
        $(byId("UIRegistration")).selectRadio("open");
        $(byXpath("//*[@id=\"UISpacePermission\"]/div[3]/button")).click();
        $(byClassName("infoIcon")).parent().parent().parent().find(byClassName("btn")).click();
        $(byClassName("infoIcon")).parent().parent().parent().find(byClassName("btn")).waitUntil(Condition.disappears,Configuration.timeout);
        spaceManagement.goToActivityStreamTab();
        activityStream.addActivity(activity1, "");
        // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];

        manageLogInOut.signIn(username1,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username2,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username3,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username4,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username5,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username6,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username7,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username8,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username9,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username10,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        homePagePlatform.goToMySpaces();
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(username11,password);
        homePagePlatform.goToAllSpace();
        $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
        $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears,Configuration.timeout);
        $(byText(space)).click();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();

        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        homePagePlatform.goToSpecificSpace(space);
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().find(byText("(11)")).scrollTo().hover();
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username11+" "+username11)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username10+" "+username10)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username9+" "+username9)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username8+" "+username8)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username7+" "+username7)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username6+" "+username6)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username5+" "+username5)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username4+" "+username4)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username3+" "+username3)).should(Condition.exist);
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText("2 more...")).should(Condition.exist);
        executeJavaScript("window.scrollBy(0,-550)","");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space,false);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
        addUsers.deleteUser(username4);
        addUsers.deleteUser(username5);
        addUsers.deleteUser(username6);
        addUsers.deleteUser(username7);
        addUsers.deleteUser(username8);
        addUsers.deleteUser(username9);
        addUsers.deleteUser(username10);
        addUsers.deleteUser(username11);
    }
    @Test
    public void test09_checkFullNameOfUserIsDisplayed(){
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, ""); // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        manageLogInOut.signIn(DATA_USER2,PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();
        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().find(byText("(1)")).hover();
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText("Mary Williams")).should(Condition.exist);
        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappears,Configuration.timeout);
        // scroll up
        executeJavaScript("window.scrollBy(0,-950)","");
        homePagePlatform.goToAllSpace();
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);

    }
    
    public void test10_checkFullNameWhenNameIsLong(){
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String username1 ="aaaaaaaaaaaaaaaaaaaaaaaaaaaa"+getRandomString();
        String username=username1.substring(0,29);
        String email=username1.substring(25,29)+"@test.com";
        String password="123456";
        navigationToolbar.goToAddUser();
        addUsers.addUser(username, password, email, username, username1);
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, ""); // get the id of activity created
        String id = $(byText(activity1)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byText(activity1)).parent().find(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        String idBlocComment=$(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
        manageLogInOut.signIn(username,password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).find(byClassName("uiIconThumbUp")).scrollTo().click();
        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}",idBlocComment))).parent().find(byText("(1)")).scrollTo().hover();
        ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("aaaaaaaaaaaaaaaaaaaaaaaaaaaa..."));
        // click on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappears,Configuration.timeout);
        // scroll up
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username);

    }
}
