package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

/**
 * Created by dmardassi on 12/07/2017.
 */
@Tag("forum")
@Tag("sniff")
public class ForumBasicTestWithUserTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumForumManagement    forumForumManagement;

  ForumHomePage           forumHomePage;

  UserAddManagement       userAddManagement;

  ArrayList<String>       arrayPage;

  NavigationToolbar       navigationToolbar;

  ManageLogInOut          manageLogInOut;

  UserAndGroupManagement  userAndGroupManagement;

  SpaceManagement         spaceManagement;

  SpaceHomePage           spaceHomePage;

  ForumCategoryManagement forumCategoryManagement;

  ForumTopicManagement    forumTopicManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    arrayPage = new ArrayList<String>();
    userAddManagement = new UserAddManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas("john", "gtngtn");
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
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
    $(new Selectors.WithText(nameForum)).should(Condition.exist);

    info("Delete category");
    forumHomePage.goToHomeCategory();
    $(byText(nameCat)).click();
    forumCategoryManagement.deleteCategory(nameCat);
  }

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
    $(new Selectors.WithText(newNameForum)).should(Condition.exist);

    info("Delete category");
    forumHomePage.goToHomeCategory();
    $(byText(nameCat)).click();
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
    $(byText(nameCat)).click();
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
    $(byText(category2)).click();
    forumCategoryManagement.deleteCategory(category2);
    $(byText(category1)).click();
    forumCategoryManagement.deleteCategory(category1);
  }
  /*
   * Step Number: 1 Step Name: Add a category Step Description: - Go to Forum page
   * - Click on Add Category - Put values - Save Input Data: Expected Outcome: -
   * Category is added successfully and shown in Forum home
   */

  @Test
  public void test02_AddACategory() {
    info("Test 2: Add a category");

    String nameCat = "nameCat" + getRandomNumber();
    String nameCat2 = "nameCat2" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);

    info("Test 03: Edit a category");
    info("edit category");
    forumCategoryManagement.editCategory(nameCat2);

    info("Test 04: Delete a category");
    info("delete category");
    forumHomePage.goToHomeCategory();
    $(byText(nameCat2)).click();
    forumCategoryManagement.deleteCategory(nameCat2);
  }
  /*
   * Step Number: 1 Step Name: Add a category Step Description: - Go to Forum page
   * - Click on Add Category - Put values - Save Input Data: Expected Outcome: -
   * Category is added successfully and shown in Forum home
   */

  @Test
  public void test_03EditACategory() {
    info("Test 2: Add a category");

    String nameCat = "nameCat" + getRandomNumber();
    String nameCat2 = "nameCat2" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);

    info("Test 03: Edit a category");
    info("edit category");
    forumCategoryManagement.editCategory(nameCat2);

    info("Test 04: Delete a category");
    info("delete category");
    forumHomePage.goToHomeCategory();
    $(byText(nameCat2)).click();
    forumCategoryManagement.deleteCategory(nameCat2);
  }
  /*
   * Step Number: 1 Step Name: Add a category Step Description: - Go to Forum page
   * - Click on Add Category - Put values - Save Input Data: Expected Outcome: -
   * Category is added successfully and shown in Forum home
   */

  @Test
  public void test04_DeleteACategory() {
    info("Test 2: Add a category");

    String nameCat = "nameCat" + getRandomNumber();
    String nameCat2 = "nameCat2" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);

    info("Test 03: Edit a category");
    info("edit category");
    forumCategoryManagement.editCategory(nameCat2);

    info("Test 04: Delete a category");
    info("delete category");
    forumHomePage.goToHomeCategory();
    $(byText(nameCat2)).click();
    forumCategoryManagement.deleteCategory(nameCat2);
  }
  /*
   * Step Number: 1 Step Name: - Create new category Step Description: - Login and
   * goto Forum application - Click [Add Category] - Fill the information and
   * click [Save] Input Data: Expected Outcome: - New category is created - No
   * activity is added in activity stream Step number: 2 Step Name: - Create new
   * Forum Step Description: - Click [Add Forum] - Fill the information and click
   * [Save] Input Data: Expected Outcome: - New forum is created - No activity is
   * added in activity stream Step number: 3 Step Name: - Create new Topic Step
   * Description: - Click [start Topic] - input the information and click [Save]
   * Input Data: Expected Outcome: - New Topic is created
   */

  @Test
  public void test09_CreateDeleteNewTopic() {
    info("Test 9: Create new Topic");

    String name = "name" + getRandomNumber();
    String name2 = "name2" + getRandomNumber();
    String desc = "desc" + getRandomNumber();
    String topic = "topic" + getRandomNumber();

    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);

    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    $(byText(name2)).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete data");
    forumHomePage.goToHomeCategory();
    $(byText(name)).click();
    forumCategoryManagement.deleteCategory(name);
  }

  /*
   * Step Number: 1 Step Name: - Create new category Step Description: - Login and
   * goto Forum application - Click [Add Category] - Fill the information and
   * click [Save] Input Data: Expected Outcome: - New category is created - No
   * activity is added in activity stream Step number: 2 Step Name: - Create new
   * Forum Step Description: - Click [Add Forum] - Fill the information and click
   * [Save] Input Data: Expected Outcome: - New forum is created - No activity is
   * added in activity stream Step number: 3 Step Name: - Create new Topic Step
   * Description: - Click [start Topic] - input the information and click [Save]
   * Input Data: Expected Outcome: - New Topic is created
   */

  @Test
  public void test09_DeleteNewTopic() {
    info("Test 9: Create new Topic");

    String name = "name" + getRandomNumber();
    String name2 = "name2" + getRandomNumber();
    String desc = "desc" + getRandomNumber();
    String topic = "topic" + getRandomNumber();
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);

    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    $(byText(name2)).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete data");
    forumHomePage.goToHomeCategory();
    $(byText(name)).click();
    forumCategoryManagement.deleteCategory(name);
  }
}
