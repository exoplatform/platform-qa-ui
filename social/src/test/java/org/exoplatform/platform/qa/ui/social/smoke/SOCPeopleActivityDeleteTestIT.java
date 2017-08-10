package org.exoplatform.platform.qa.ui.social.smoke;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_DELETE_POPUP_OK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("smoke")
@Tag("social")
public class SOCPeopleActivityDeleteTestIT extends Base {
  NavigationToolbar navigationToolbar;

  AddUsers          addUsers;

  ManageLogInOut    manageLogInOut;

  ActivityStream    activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    activityStream = new ActivityStream(this);
  }

  /**
   * <li>Case ID:122800.</li>
   * <li>Test Case Name: Delete your activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_DeleteYourActivityByUser() {
    info("Test 1: Delete your activity");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add new activities
     * Input Data: - Sign in system - Select Activities page on User Toolbar portlet
     * in the upper right corner of the screen - Select activity in the left pane -
     * Enter some text into text box - Click on [Share] button Expected Outcome: Add
     * an activity successfully: - This activity is added into users activities
     * list.User who is in your contact, can view your active on his/her activity
     * list
     */
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

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Delete activity Input
     * Data: - Select an activity - Click on Delete - Click OK to confirm deleting
     * Expected Outcome: The activity is deleted. All comments of activity are
     * deleted too.
     */
    // get the id of the webContent created
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // hover on the activity to appear the delete button
    $(byId("ActivityContextBox" + id)).find(byClassName("dateTime")).hover();
    // click on delete button
    $(byId("DeleteActivityButton" + id)).click();
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");

    // delete user
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }
}
