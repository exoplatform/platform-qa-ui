package org.exoplatform.platform.qa.ui.forum.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors.WithText;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * By quynhpt Date 20/01/2015
 */
@Tag("smoke")
@Tag("forum")
public class ForumForumBasicActionTestIT extends Base {

  HomePagePlatform homePagePlatform;

  ForumHomePage forumHomePage;

  ForumCategoryManagement forumCategoryManagement;

  ForumForumManagement forumForumManagement;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
}

  /**
   * CaseID: 116736 Case_name: Edid a forum Steps: 1. Prepare data: create a
   * caterory 2. Add a forum: - Click on Add forum icon - Put values - Click Save
   * Expected: Forum is added successfully.
   */
  @Test
  public void test01_AddEditDelete_AForum() {
    info("Edit Forum");
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
   * CaseID: 116738 Case_name: Move a forum Steps: 1. Prepare data: create a
   * caterory 2. Add a forum: - Select 1 forum - Click on More Action, select Move
   * - Choose destination category Expected: This forum is moved to a destination
   * category
   */
  @Test
  public void test02_MoveForum() {
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
    ELEMENT_CAT_CONTAINER.find(byText(category2)).waitUntil(appears, Configuration.timeout).click();
    forumCategoryManagement.deleteCategory(category2);
    ELEMENT_CAT_CONTAINER.find(byText(category1)).waitUntil(appears, Configuration.timeout).click();
    forumCategoryManagement.deleteCategory(category1);
  }

  @Test
  @Tag("search")
  public void test03_SearchForum() {

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
