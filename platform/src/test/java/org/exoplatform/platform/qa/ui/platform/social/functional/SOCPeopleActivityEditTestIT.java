package org.exoplatform.platform.qa.ui.platform.social.functional;

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
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("functional")
@Tag("social")
public class SOCPeopleActivityEditTestIT extends Base {
  NavigationToolbar navigationToolbar;

  ManageLogInOut    manageLogInOut;

  ActivityStream    activityStream;

  AddUsers          addUsers;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122776.</li>
   * <li>Test Case Name: Edit description of share link with unlimited chars.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Go to my profile page Input Data: - Sign in system - Select Activities
   * page on User Toolbar portlet in the upper right corner of the screen Expected
   * Outcome: - User activities page is displayed. It focus on activity list Step
   * number: 2 Step Name: - Step Description: Step 2: Share a link Input Data: -
   * Select activity - Click on Attach icon - Enter a not exited URL - Hit Enter
   * from keyboard or click on [Attach] button on Share link form - Double click
   * on description of shared link. Enter unlimited text into this field and hit
   * Enter - Enter chars into text box or not - Click on [Share] button Expected
   * Outcome: A link is shared with new title.
   */
  @Test
  public void test01_EditDescriptionOfShareLinkWithUnlimitedChars() {
    info("Test 1: Edit description of share link with unlimited chars");
    String textDes = "textDes" + getRandomNumber();
    String link = "https://www.google.fr/";
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.addActivity(textDes, link);
    $(byXpath(ELEMENT_ACTIVITY_TITLE.replace("${text}", textDes).replace("${file}", link))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }
}
