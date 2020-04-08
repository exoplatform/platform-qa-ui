package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 11/09/17.
 */
@Tag("social")
@Tag("sniff")
public class SOCHomePageTestIT extends BaseTribe {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  ConnectionsManagement  connectionsManagement;

  ActivityStream         activityStream;

  TribeActivityStream         tribeActivityStream;

  SpaceHomePage          spaceHomePage;

  SpaceManagement        spaceManagement;

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
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut.signInTribeWithGoogle(tribe_mail, atlassian_username, atlassian_password);

  }


  @Test
  public void test01_LikeActivity() {
    String activity1 = "activity1" + getRandomNumber();
    info("Test 1: Like Activity");
    tribeActivityStream.addActivity(activity1, "");
    /*
     * Step Number: 1 Step Name: Step 1: Like/Unlike Activity Step Description: - Go
     * to Intranet home - Click on Like activity in action bar part of an activity
     * Input Data: Expected Outcome: - Like button is highlighted and the number of
     * likers is updated
     */
    // get the id of the activity created
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on the like button of the activity
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
    /*
     * Step number: 2 Step Name: Check Likes part Step Description: - Check avatar -
     * Mouse over the avatar Input Data: Expected Outcome: - Avatar of liker is
     * added into likes part, the oldest liker is displayed at the right and the
     * newest at the left. - Profile pictures of users popup
     */

    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivity(activity1);
  }

  /**
   * <li>Case ID:121909.</li>
   * <li>Test Case Name: Add comment.</li>
   */
  @Test
  public void test02_AddComment() {
    info("Test 2: Add comment");
    /*
     * Step Number: 1 Step Name: Add comment for activity Step Description: - Go to
     * Intranet home - Select the activity - Click comment icon to show input text
     * field - input the comment and click comment Input Data: Expected Outcome: -
     * Comment will be shown in comment section of activity
     */

    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    tribeActivityStream.addActivity(activity1, "");
    // get the id of activity created
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on comment link
    $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    // insert comment
    $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).pressEnter().waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    $(byText(comment)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    sleep(1000);
    executeJavaScript("window.scrollBy(0,-2000)");

    info("Delete comment");
    activityStream.deletecomment(activity1,comment);
    // verify that the comment is deleted
    $(byText(comment)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);

    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivity(activity1);
  }

}
