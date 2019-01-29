package org.exoplatform.platform.qa.ui.platform.social.functional;

import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("functional")
@Tag("social")

public class SOCPeopleActivityDeleteTestIT extends Base {
  NavigationToolbar navigationToolbar;

  ManageLogInOut    manageLogInOut;

  AddUsers          addUsers;

  ActivityStream    activityStream;

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
   * <li>Case ID:122800.</li>
   * <li>Test Case Name: Delete your activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Add new activities Input Data: - Sign in system - Select Activities page
   * on User Toolbar portlet in the upper right corner of the screen - Select
   * activity in the left pane - Enter some text into text box - Click on [Share]
   * button Expected Outcome: Add an activity successfully: - This activity is
   * added into users activities list.User who is in your contact, can view your
   * active on his/her activity list Step number: 2 Step Name: - Step Description:
   * Step 2: Delete activity Input Data: - Select an activity - Click on Delete -
   * Click OK to confirm deleting Expected Outcome: The activity is deleted. All
   * comments of activity are deleted too.
   */
  @Test
  public void test01_DeleteYourActivity() {
    info("Test 1: Delete your activity");
    info("Test 1: Check Like Notification (1 like)");
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
    activityStream.deleteactivity(activity1);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }
}
