package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
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

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_DW_POST_ACTIVITY_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("tribe")
@Tag("social")
@Tag("sniff")
public class ActivityStreamManagementTestIt extends BaseTribe {
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
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_PostDelete_Activity() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

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
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

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
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

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
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    tribeActivityStream.addActivityComment(activityId, comment);

    info("Reply to a comment");
    activityStream.replyToCommentDW(comment, reply, user);
    $(byText(reply)).parent().parent().find(byText(user)).waitUntil(exist, openBrowserTimeoutMs);
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
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

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
    String user1 = tribe_user3;

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(user1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationTribe(spaceNamea);
    navigationToolbar.closeNotificationsTribe();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Like Activity");
    tribeActivityStream.likeActivityDW(activityId);

    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWholikesActivityIsDisplayedTribe(tribe_user3, activity1);

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Dislike Activity");
    tribeActivityStream.dislikeActivityDW(activityId);

    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWhoDislikesActivityIsNotDisplayedTribe(tribe_user3, activity1);

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
    String user1 = tribe_user4;

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(user1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    manageLogInOut.signInTribe(tribe_username4, tribe_password4);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationTribe(spaceNamea);
    navigationToolbar.closeNotificationsTribe();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Send A Kudos");
    tribeActivityStream.sendActivityKudosDW(activity1, kudosMessage);

    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Check That User Who Sends A Kudos Is Displayed");
    tribeActivityStream.checkThatUserWhoSendsAKudosIsDisplayedTribe(tribe_user4, activity1, kudosMessage, tribe_user);

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
    String user1 = tribe_user3;
    String user2 = tribe_user4;

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(user1);
    inviteUsers.add(user2);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationTribe(spaceNamea);
    navigationToolbar.closeNotificationsTribe();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Comment Activity");
    tribeActivityStream.addActivityComment(activityId, comment);

    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWhoCommentsActivityIsDisplayedDW(activity1, tribe_user3, comment);

    manageLogInOut.signInTribe(tribe_username4, tribe_password4);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);

    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationTribe(spaceNamea);
    navigationToolbar.closeNotificationsTribe();

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Reply To A Comment");
    activityStream.replyToCommentByOtherUserDW(comment, reply, tribe_user4);

    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeActivityStream.checkThatUserWhoReplyToActivityCommentIsDisplayedDW(activity1, tribe_user4, comment, reply);

    tribeActivityStream.deleteactivityDW(activity1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

}
