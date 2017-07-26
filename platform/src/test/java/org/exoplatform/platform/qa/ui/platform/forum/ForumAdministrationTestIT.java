package org.exoplatform.platform.qa.ui.platform.forum;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * @author cmugnier
 * @date 20/01/2015
 */
@Tag("forum")
@Tag("smoke")
public class ForumAdministrationTestIT extends Base {

    HomePagePlatform homePagePlatform;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    ForumForumManagement forumForumManagement;

    ForumTopicManagement forumTopicManagement;

    ForumHomePage forumHomePage;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceManagement = new SpaceManagement(this);
        forumForumManagement = new ForumForumManagement(this);
        forumTopicManagement = new ForumTopicManagement(this);
        forumHomePage = new ForumHomePage(this);
    }

    /**
     * <li>Case ID:116690.</li>
     * <li>Test Case Name: Ban IP.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    // @Test(groups="pending")
    // public void test01_BanIP() {
    // info("Test 1: Ban IP");
    // /*Step Number: 1
    // *Step Name: -
    // *Step Description:
    // *Step 1: Add Ban IP
    // *Input Data:
    // *- click on Administration menu and select â€œBan IP
    // *- Add IP into list and click on â€œAddâ€
    // *Expected Outcome:
    // *Ban IP is added successfully to listBan IP user can not add post/create
    // topic*/
    // homePagePlatform.goToForum();
    // $(ELEMENT_ACTIONBAR_ADMINISTRATION).click();
    // $(ELEMENT_ACTIONBAR_ADMIN_BANIP).click();
    // }

    /**
     * <li>Case ID:116709.</li>
     * <li>Test Case Name: Add BB code.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     * <li>Case ID:116710.</li>
     * <li>Test Case Name: Edit BB code.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     * <li>Case ID:116711.</li>
     * <li>Test Case Name: Delete BB code.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    @Test
    public void test02_03_04_AddEditDeleteBBCode() {
        info("Test 2: Add BB code");
        info("Create data test");
        String tag = "tag" + getRandomNumber();
        String replacement = "<b>{param}</b>.";
        String description = "<b>" + "tag" + getRandomNumber() + "</b>.";
        String example = "<b>" + "tag" + getRandomNumber() + "</b>.";
        info("Finished creating data test");
    /*
     * Step Number: 1 Step Name: Go To BB code manage Step Description: - Click on
     * Administration menu and select"BB Code" Input Data: Expected Outcome: BB code
     * management screen is shown.
     */
    /*
     * Step number: 2 Step Name: Add BB Code Step Description: - Click on Add BBCode
     * - Enter data into fields - Save Input Data: Expected Outcome: BB code is
     * added successfully
     */
        info("Go to Forum portlet");
        homePagePlatform.goToForum();
        info("Add BBcode");
        forumHomePage.addBBCode(tag, replacement, description, example, false);
        info("BBcode is created successfully");
        info("Test 03: Edit BB code");
        info("Edit a BBCode");
        forumHomePage.editBBCode(tag, replacement, description, example, false);
        info("BBcode is edited with changes successfully");
        info("Test 04: Delete BB code");
        info("Delete BBcode");
        forumHomePage.deleteBBcode(tag);
    }
}
