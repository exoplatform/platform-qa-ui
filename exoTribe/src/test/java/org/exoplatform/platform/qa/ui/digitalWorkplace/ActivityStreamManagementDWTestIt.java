package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_DW_POST_ACTIVITY_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("dw")
public class ActivityStreamManagementDWTestIt extends BaseDW {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

  TribeSpaceManagement tribeSpaceManagement;

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
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_PostDelete_Activity() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    info("Post Activity");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test02_LikeDislike_Activity() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    info("Like Activity");
    tribeActivityStream.likeActivityDW(activityId);
    info("Dislike Activity");
    tribeActivityStream.dislikeActivityDW(activityId);

    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test03_AddDelete_Comment() {

    info("Add comment");

    String comment = "comment" + getRandomNumber();
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    tribeActivityStream.addActivityComment(activityId, comment);

    info("Delete comment");
    activityStream.deletecommentDW(activity1, comment);
    // verify that the comment is deleted
    $(byText(comment)).waitUntil(Condition.not(Condition.exist), Configuration.openBrowserTimeoutMs);

    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test04_Reply_ToAComment() {

    info("Add comment");

    String comment = "comment" + getRandomNumber();
    String reply = "reply" + getRandomNumber();
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String user = "Khalil Riahi";

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    tribeActivityStream.addActivityComment(activityId, comment);

    info("Reply to a comment");
    activityStream.replyToCommentDW(comment, reply, user);
    activityStream.deleteReplyDW(comment, reply);
    // verify that the reply is deleted
    $(byText(reply)).waitUntil(Condition.not(Condition.exist), openBrowserTimeoutMs);

    info("Delete comment");
    activityStream.deletecommentDW(activity1, comment);
    // verify that the comment is deleted
    $(byText(comment)).waitUntil(Condition.not(Condition.exist), Configuration.openBrowserTimeoutMs);

    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test05_LikeDislike_AComment() {

    info("Add comment");

    String comment = "comment" + getRandomNumber();
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    tribeActivityStream.addActivityComment(activityId, comment);

    info("Like Comment");
    activityStream.likeCommentDW(activity1, comment);

    info("Dislike Comment");
    activityStream.dislikeCommentDW(activity1, comment);

    info("Delete comment");
    activityStream.deletecommentDW(activity1, comment);
    // verify that the comment is deleted
    $(byText(comment)).waitUntil(Condition.not(Condition.exist), Configuration.openBrowserTimeoutMs);

    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test06_LikeDislike_ActivityByOtherUser() {

    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationDW(spaceNamea);
    navigationToolbar.closeNotificationsDW();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Like Activity");
    tribeActivityStream.likeActivityDW(activityId);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWholikesActivityIsDisplayedDW(username1, activity1);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Dislike Activity");
    tribeActivityStream.dislikeActivityDW(activityId);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWhoDislikesActivityIsNotDisplayedDW(username1, activity1);

    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test07_SendActivityKudosByOtherUser() {

    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String kudosMessage = "Thanks for your help";

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToIntranetNotificationDW_WithoutRefresh();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationDW(spaceNamea);
    navigationToolbar.closeNotificationsDW();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Send A Kudos");
    tribeActivityStream.sendActivityKudosDW(activity1, kudosMessage);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Check That User Who Sends A Kudos Is Displayed");
    tribeActivityStream.checkThatUserWhoSendsAKudosIsDisplayedDigitalWorkplace(username1, activity1, kudosMessage, DATA_NAME_USER1);

    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test08_CommentAndReplyToAComment_ActivityByOtherUser() {

    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String reply = "reply" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    addUsers.addUserTribe(username2, password, email2, username2, username2, "");


    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);
    inviteUsers.add(username2);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToIntranetNotificationDW_WithoutRefresh();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationDW(spaceNamea);
    navigationToolbar.closeNotificationsDW();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Comment Activity");
    tribeActivityStream.addActivityComment(activityId, comment);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWhoCommentsActivityIsDisplayedDW(activity1, username1, comment);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    navigationToolbar.acceptJoinSpaceViaSpacesDW();
    tribeSpaceManagement.accessToSearchedSpace();

    info("Reply To A Comment");
    activityStream.replyToCommentByOtherUserDigitalWorkplace(comment, reply, username2);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWhoReplyToActivityCommentIsDisplayedDW(activity1, username2, comment, reply);

    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

}
