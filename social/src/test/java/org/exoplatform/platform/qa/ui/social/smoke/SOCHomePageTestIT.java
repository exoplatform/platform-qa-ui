package org.exoplatform.platform.qa.ui.social.smoke;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("smoke")
@Tag("social")
public class SOCHomePageTestIT extends Base {
    NavigationToolbar navigationToolbar;

    HomePagePlatform  homePagePlatform;

    ActivityStream    activityStream;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        homePagePlatform = new HomePagePlatform(this);
        activityStream = new ActivityStream(this);
    }

    /**
     * <li>Case ID:121888.</li>
     * <li>Test Case Name: Like Activity.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    @Test
    public void test01_LikeActivity() {
        info("Test 1: Like Activity");
        String activity1 = "activity1" + getRandomNumber();
        activityStream.addActivity(activity1, "");
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

        ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.timeout);
        // hover on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).hover();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        // verify that the activity doesn't exist
        $(byText(activity1)).shouldNot(Condition.exist);
        info("the activity is removed successfully");
    }

    /**
     * <li>Case ID:121909.</li>
     * <li>Test Case Name: Add comment.</li>
     */
    @Test
    public void test02AddComment() {
        info("Test 2: Add comment");
    /*
     * Step Number: 1 Step Name: Add comment for activity Step Description: - Go to
     * Intranet home - Select the activity - Click comment icon to show input text
     * field - input the comment and click comment Input Data: Expected Outcome: -
     * Comment will be shown in comment section of activity
     */

        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        activityStream.addActivity(activity1, "");
        // get the id of activity created
        String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        $(byText(comment)).should(Condition.exist);
        // hover on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).hover();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        // verify that the activity doesn't exist
        $(byText(activity1)).shouldNot(Condition.exist);
        info("the activity is removed successfully");
    }

    /**
     * <li>Case ID:121933.</li>
     * <li>Test Case Name: Delete comment.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    @Test
    public void test_15DeleteComment() {
        info("Test 2: Add comment");
    /*
     * Step Number: 1 Step Name: Add comment for activity Step Description: - Go to
     * Intranet home - Select the activity - Click comment icon to show input text
     * field - input the comment and click comment Input Data: Expected Outcome: -
     * Comment will be shown in comment section of activity
     */

        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        activityStream.addActivity(activity1, "");
        // get the id of activity created
        String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
        // click on comment link
        $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
        info("Test 15: Delete comment");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        // scroll up
        executeJavaScript("window.scrollBy(0,-550)");
        // the id of the comment is id of the activity+1
        Integer idComment = Integer.parseInt(id) + 1;
        // hover on the comment to appear the delete button
        $(byId(ELEMENT_COMMENT_BLOC.replace("{id}", id))).hover().click();
        $(byId(ELEMENT_COMMENT_DELETE.replace("{id}", idComment.toString()))).click();
        // Confirm
        $(ELEMENT_DELETE_POPUP_OK).click();
        // verify that the comment is deleted
        $(byText(comment)).shouldNot(Condition.exist);
        // hover on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).hover();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        ELEMENT_DELETE_POPUP_OK.click();
        // verify that the activity doesn't exist
        $(byText(activity1)).shouldNot(Condition.exist);
        info("the activity is removed successfully");
    }

    /**
     * <li>Case ID:121910.</li>
     * <li>Test Case Name: Delete your activity.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    @Test
    public void test03_DeleteYourActivity() {
        info("Test 3: Delete your activity");
    /*
     * Step Number: 1 Step Name: - Delete a comment Step Description: - Go to
     * Intranet home - Select the activity - mouse over activity you want to delete
     * - Click the (x) icon to delete Input Data: Expected Outcome: - Comment will
     * be shown in comment section of activity - the (x) icon display on the top
     * -right of activity - activity is deteled successfully
     */
        // get the id of the webContent created
        String activity1 = "activity1" + getRandomNumber();
        activityStream.addActivity(activity1, "");
        String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
        // hover on the activity to appear the delete button
        $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).hover();
        // click on delete button
        $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
        $(ELEMENT_DELETE_POPUP_OK).click();
        // verify that the activity doesn't exist
        $(byText(activity1)).shouldNot(Condition.exist);
        info("the activity is removed successfully");
    }
}