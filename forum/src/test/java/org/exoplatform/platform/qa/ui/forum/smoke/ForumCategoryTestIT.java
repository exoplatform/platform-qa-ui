package org.exoplatform.platform.qa.ui.forum.smoke;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

@Tag("smoke")
@Tag("forum")
public class ForumCategoryTestIT extends Base {
  HomePagePlatform        homePagePlatform;

  ForumCategoryManagement forumCategoryManagement;

  ForumHomePage           forumHomePage;

  ForumForumManagement    forumForumManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
  }

  /**
   * <li>Case ID:116742.</li>
   * <li>Test Case Name: Add a category.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test02_AddACategory() {
    info("Test 2: Add a category");

    String nameCat = "nameCat" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Add a category Step Description: - Go to Forum page
     * - Click on Add Category - Put values - Save Input Data: Expected Outcome: -
     * Category is added successfully and shown in Forum home
     */
    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);

  }

}
