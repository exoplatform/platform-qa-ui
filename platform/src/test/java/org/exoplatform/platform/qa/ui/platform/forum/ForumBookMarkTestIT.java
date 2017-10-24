package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_CATEGORY_BREADCUMB_HOME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * @author dmardassi
 * @date 13/07/2017
 */
@Tag("forum")
@Tag("sniff")

public class ForumBookMarkTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumForumManagement    forumForumManagement;

  ForumTopicManagement    forumTopicManagement;

  ForumHomePage           forumHomePage;

  ForumCategoryManagement forumCategoryManagement;

  ManageLogInOut          manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }


  /**
   * <li>Case ID:116739.</li>
   * <li>Test Case Name: Bookmark a category.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Prepare data: Create a
   * category Step Description: - Create a category Input Data: Expected Outcome:
   * Category is created successfully. Step number: 2 Step Name: Bookmark a
   * category Step Description: - Right click on a category, select Bookmark Input
   * Data: Expected Outcome: - Bookmark Category is successfully -
   * BookmarkedCategory is displayed in Bookmark list
   */

  @Test
  public void test01_BookmarkACategory() {
    info("Test 1: Bookmark a category");
    String nameCat = "Category" + getRandomNumber();
    String description = "Description" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", description);
    info("Bookmark the topic");
    forumHomePage.bookmark(nameCat);
    info("Delete the category");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToForum();
    forumCategoryManagement.deleteCategory(nameCat);
  }

  /**
   * <li>Case ID:116740.</li>
   * <li>Test Case Name: Bookmark a forum.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Prepare data: create a
   * caterory, forum Step Description: - Create a category - Create a forum Input
   * Data: Expected Outcome: Category, forum are created successfully Step number:
   * 2 Step Name: Bookmark a forum Step Description: - Right click on a forum,
   * select Bookmark Input Data: Expected Outcome: - Bookmark forum is
   * successfully - Bookmarkedforum is displayed in Bookmark list
   */

  @Test
  public void test02_BookmarkAForum() {
    info("Test 2: Bookmark a forum");
    String nameCat = "Category" + getRandomNumber();
    String nameForum = "Forum" + getRandomNumber();
    String description = "Description" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", description);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(nameForum, "", description);
    info("Verify that the forum is shown successfully");

    info("Bookmark the topic");
    forumHomePage.bookmark(nameForum);
    info("Refresh page");
    refresh();
    info("Delete the category");
    forumHomePage.goToHomeCategory();
    $(byText(nameCat)).click();
    forumCategoryManagement.deleteCategory(nameCat);

  }

  /**
   * <li>Case ID:116741.</li>
   * <li>Test Case Name: Bookmark a topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Prepare data: create a
   * caterory, forum, topic Step Description: - Create a category - Create a forum
   * in this category - Create a topic in this forum Input Data: Expected Outcome:
   * Category, forum, topic are created successfully Step number: 2 Step Name:
   * Bookmark a topic Step Description: - Right click on a topic, select Bookmark
   * Input Data: Expected Outcome: - Bookmark topic is successfully -
   * Bookmarkedtopic is displayed in Bookmark list
   */

  @Test
  public void test03_BookmarkATopic() {
    info("Test 3: Bookmark a topic");
    String nameCat = "Category" + getRandomNumber();
    String nameForum = "Forum" + getRandomNumber();
    String topic1 = "Topic" + getRandomNumber();
    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(nameForum, "", nameForum);
    info("Verify that the forum is shown successfully");
    $(byText(nameForum)).should(Condition.exist);
    info("Create a topic without attached file");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    info("Bookmark the topic");
    forumHomePage.goToTopic(topic1);
    forumHomePage.topicbookmark(topic1);
    info("Delete the category");
    info("Refresh page");
    refresh();
    $(ELEMENT_CATEGORY_BREADCUMB_HOME).click();
    $(byText(nameCat)).click();
    forumCategoryManagement.deleteCategory(nameCat);
  }
}
