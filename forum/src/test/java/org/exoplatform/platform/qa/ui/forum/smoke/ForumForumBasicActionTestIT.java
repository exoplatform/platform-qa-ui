package org.exoplatform.platform.qa.ui.forum.smoke;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors.WithText;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

/**
 * By quynhpt Date 20/01/2015
 */
@Tag("smoke")
@Tag("forum")
public class ForumForumBasicActionTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumHomePage           forumHomePage;

  ForumCategoryManagement forumCategoryManagement;

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
   * CaseID: 116735 Case_name: Add a forum Steps: 1. Prepare data: create a
   * caterory. a forum 2. Edit a forum: - Select 1 forum - Click on More Action,
   * click Edit - Put values - Click Save Expected: Forum is updated with new
   * changes
   */
  @Test

  public void test01_AddForum() {
    info("test01: Add Forum");
    String nameCat = "category-" + getRandomNumber();
    String nameForum = "forum-" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(nameForum, "", nameForum);
    info("Verify that the forum is shown successfully");
    $(new WithText(nameForum)).should(Condition.exist);

    info("Delete category");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(nameCat);
  }


  /**
   * CaseID: 116736 Case_name: Edid a forum Steps: 1. Prepare data: create a
   * caterory 2. Add a forum: - Click on Add forum icon - Put values - Click Save
   * Expected: Forum is added successfully.
   */
  @Test
  public void test03_DeleteForum() {
    info("test03: Delete Forum");
    String nameCat = "category-" + getRandomNumber();
    String nameForum = "forum-" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(nameForum, "", nameForum);
    info("Delete forum");
    forumForumManagement.deleteForum(nameForum);
    info("Delete category");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToForum();
    forumCategoryManagement.deleteCategory(nameCat);
  }
}
