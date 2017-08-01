package org.exoplatform.selenium.platform.forum.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_CONTENT_SEARCH_RESULT;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_SEARCH_TEXTBOX;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
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
  public void test02_EditForum() {
    info("test02: Edit Forum");
    String nameCat = "category-" + getRandomNumber();
    String nameForum = "forum-" + getRandomNumber();
    String newNameForum = "newforum-" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(nameForum, "", nameForum);
    info("Edit the forum");
    forumForumManagement.editForum(newNameForum, "", newNameForum);
    info("Verify that the forum is edit successfully");
    $(new WithText(newNameForum)).should(Condition.exist);

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
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(nameCat);
  }

  /**
   * CaseID: 116738 Case_name: Move a forum Steps: 1. Prepare data: create a
   * caterory 2. Add a forum: - Select 1 forum - Click on More Action, select Move
   * - Choose destination category Expected: This forum is moved to a destination
   * category
   */
  @Test
  public void test04_MoveForum() {
    info("Move a forum");
    String category1 = "category1-" + getRandomNumber();
    String category2 = "category2-" + getRandomNumber();
    String forum = "forum1-" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category 1");
    forumCategoryManagement.addCategorySimple(category1, "", category1);
    info("go to Home page of category");
    forumHomePage.goToHomeCategory();
    info("Add a category 2");
    forumCategoryManagement.addCategorySimple(category2, "", category2);

    forumHomePage.goToHomeCategory();
    forumHomePage.goToCategory(category1);
    info(" Add a forum in the category1");
    forumForumManagement.addForumSimple(forum, "", forum);

    info("Move forum");
    forumForumManagement.moveForum(forum, category2);

    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(category2);
    forumCategoryManagement.deleteCategory(category1);
  }

  @Test
  @Tag("search")
  public void test05_SearchForum() {

    String nameCat = "categorya" + getRandomString();
    String nameForum = "foruma" + getRandomString();
    String nameForum1 = "forumb" + getRandomString();
    String nameForum2 = "forumc" + getRandomString();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(nameForum, "", nameForum);
    forumForumManagement.addForumSimple(nameForum1, "", nameForum1);
    forumForumManagement.addForumSimple(nameForum2, "", nameForum2);
    info("search forum");
    homePagePlatform.goToForum();
    $(ELEMENT_SEARCH_TEXTBOX).setValue(nameForum);
    ForumLocator.ELEMENT_ICON_SEARCH.click();
    ELEMENT_CONTENT_SEARCH_RESULT.find(byText(nameForum)).should(Condition.exist);
    ELEMENT_CONTENT_SEARCH_RESULT.find(byText(nameForum1)).shouldNot(Condition.exist);
    ELEMENT_CONTENT_SEARCH_RESULT.find(byText(nameForum2)).shouldNot(Condition.exist);
    info("delete data");
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(nameCat);
  }
}
