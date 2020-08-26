package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("functional")
@Tag("social")
public class SOCPeopleActivityCommentTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  ActivityStream        activityStream;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  AddUsers              addUsers;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    activityStream = new ActivityStream(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  @Test
  public void test01_CommentOnYourFriendsActivity() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "Aa123456";
    String activity2 = "activity2" + getRandomNumber();
    String activity3 = "activity3" + getRandomNumber();
    String textDes = "textDes" + getRandomNumber();
    String link = "https://www.google.fr/";
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String comment4 = "comment4" + getRandomNumber();

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Edit description of share link with unlimited chars");
    activityStream.addActivity(activity3, "");
    activityStream.addActivity(textDes, link);
    $(byXpath(ELEMENT_ACTIVITY_TITLE.replace("${text}", textDes).replace("${file}", link))).waitUntil(Condition.visible,
            Configuration.timeout);

    info("Show/hide all comment");
    activityStream.addActivity(activity2, "");
    activityStream.commentActivity(activity2, comment1);
    $(byText(activity2)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.commentActivity(activity2, comment2);
    $(byText(activity2)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.commentActivity(activity2, comment3);
    $(byText(activity2)).parent().parent().find(byText(comment3)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.commentActivity(activity2, comment4);
    $(byText(activity2)).parent().parent().find(byText(comment4)).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToConnections();
    homePagePlatform.goToHomePage();
    $(byText(activity2)).parent()
            .parent()
            .find(byText(comment1))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity2)).parent()
            .parent()
            .find(byText(comment2))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity2)).parent().parent().find(byText(comment3)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity2)).parent().parent().find(byText(comment4)).waitUntil(Condition.visible, Configuration.timeout);
    info("Verify that view all comment links is shown and clickable on it");
    activityStream.showComment(activity2);
    $(byText(activity2)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity2)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity2)).parent().parent().find(byText(comment3)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity2)).parent().parent().find(byText(comment4)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.hideComment(activity2);
    $(byText(activity2)).parent()
            .parent()
            .find(byText(comment1))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity2)).parent()
            .parent()
            .find(byText(comment2))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity2)).parent()
            .parent()
            .find(byText(comment3))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity2)).parent()
            .parent()
            .find(byText(comment4))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    navigationToolbar.goToMyActivities();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    info("Comment on your activity");
    String comment0 = "comment" + getRandomNumber();
    activityStream.commentActivity(activity1, comment0);
    $(byText(activity1)).parent().parent().find(byText(comment0)).waitUntil(Condition.visible, Configuration.timeout);
    info("Comment on your friends activity");
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Access people list, invite an user");
    connectionsManagement.connectToAUser(username2);
    info("Invited user accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("Verify after accept");
    connectionsManagement.verifyConnection(username1, true);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.ALL);
    connectionsManagement.goToUserByFullName(username1 + " " + username1);
    homePagePlatform.goToHomePage();
    String comment = "comment" + getRandomNumber();
    activityStream.commentActivity(activity1, comment);
    $(byText(activity1)).parent().parent().find(byText(comment)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(username1, password);
    activityStream.deleteactivity(activity1);
    $(byText(activity1)).parent()
            .parent()
            .find(byText(comment))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

}
