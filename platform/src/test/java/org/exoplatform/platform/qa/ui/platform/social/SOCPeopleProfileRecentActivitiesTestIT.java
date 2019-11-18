package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMPOSER_SHARE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import java.awt.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
@Tag("social")
@Tag("sniff")
public class SOCPeopleProfileRecentActivitiesTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ActivityStream        activityStream;

  ManageLogInOut        manageLogInOut;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform      homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    activityStream = new ActivityStream(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122953.</li>
   * <li>Test Case Name: Check my Recent Activities section.</li>
   * <li>Pre-Condition: - User A has more 5 activities in his stream
   * (wiki,forum,poll,document...with title, is mentioned,post
   * activity,like,comment)</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:122954.</li>
   * <li>Test Case Name: Check the Recent Activities section of another user.</li>
   * <li>Pre-Condition: - User A and User B are created - User B has over 5
   * activities in his stream (wiki,forum,poll,document...with title,post
   * activity)</li>
   * <li>Post-Condition:</li>
   * 
   * @throws AWTException Step Number: 1 Step Name: Step 1 : go to my profile Step
   *           Description: - Login - Go to User Menu > [My Profile] Input Data:
   *           Expected Outcome: - The section "Recent activities" is displayed at
   *           the bottom of the mid -column - Button "View All" at the bottom to
   *           redirect to My activities* Step number: 2 Step Name: Step 2: Check
   *           Activity Step Description: - Check classic activity of Recent
   *           Activities section Input Data: Expected Outcome: summary of the
   *           activity is displayed : - Avatar - Type - Activity message / title
   *           - Source link if provided
   */
  @Test
  public void test01_CheckMyRecentActivitiesSection() throws AWTException {
    info("Test 1: Check my Recent Activities section");
    String textDes2 = "textDes2" + getRandomNumber();
    String mention = "mention" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    // Create data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username2);
    homePagePlatform.goToHomePage();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    info("add mention");
    activityStream.mentionUserActivity(username2, mention);

    info("share a document and comment");
    ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.disabled, Configuration.timeout);

    info("add activity");
    activityStream.addActivity(textDes2, "");
    activityStream.commentActivity(textDes2, comment);
    info("goto my profile");
    navigationToolbar.goToMyProfile();
    $(ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN).click();
    $(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES).should(visible);
    $(byText(textDes2)).should(visible);
    $(byText(mention)).parent().shouldHave(Condition.text(username2 + " " + username2 + " " + mention)).should(visible);
    $(byAttribute("data-original-title", "eXo-Platform.png")).should(Condition.exist);
    $(byText(textDes2)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText(comment))
                       .scrollTo()
                       .should(visible);
    navigationToolbar.goToMyProfile();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test02_CheckOtherRecentActivitiesSection() throws Exception {
    info("Test 1: Check my Recent Activities section");
    String textDes2 = "textDes2" + getRandomNumber();
    String mention = "mention" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    // Create data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username2);
    homePagePlatform.goToHomePage();
    $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(visible,Configuration.timeout).click();
    info("add mention");
    activityStream.mentionUserActivity(username2, mention);

    info("share a document and comment");
    ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.disabled, Configuration.timeout);

    info("add activity");
    activityStream.addActivity(textDes2, "");
    activityStream.commentActivity(textDes2, comment);
    info("goto my profile");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(username1, null, null, null);
    homePagePlatform.refreshUntil($(byText(username1 + " " + username1)), visible, 2000);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).click();
    $(ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN).click();
    $(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES).should(visible);
    $(byText(textDes2)).should(visible);
    $(byText(mention)).parent().shouldHave(Condition.text(username2 + " " + username2 + " " + mention)).should(visible);
    $(byAttribute("data-original-title", "eXo-Platform.png")).should(Condition.exist);
    $(byText(textDes2)).parent()
                       .parent()
                       .parent()
                       .parent()
                       .parent()
                       .parent()
                       .parent()
                       .find(byText(comment))
                       .scrollTo()
                       .should(visible);
    navigationToolbar.goToMyProfile();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
