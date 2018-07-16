package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import com.codeborne.selenide.Configuration;
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
 * @author cmugnier
 * @date 20/01/2015
 */
@Tag("forum")
@Tag("sniff")

public class ForumSearchTestIT extends Base {

  /**
   * <li>Case ID:116696.</li>
   * <li>Test Case Name: Quick search.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */

  HomePagePlatform        homePagePlatform;

  ForumForumManagement    forumForumManagement;

  ForumTopicManagement    forumTopicManagement;

  ForumCategoryManagement forumCategoryManagement;

  ForumHomePage           forumHomePage;

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


  @Test
  /*
   * Step Number: 1 Step Name: Prepare data: create a caterory, forum, topic, post
   * Step Description: - Log in as admin (root or john) - Click Forums on the left
   * panel - Create categories - Create forums - Create topics - Create posts
   * Input Data: Expected Outcome: Category, forum, topic, post are created
   * successfully Step number: 2 Step Name: Quick Search Step Description: - Put
   * keyword in the Search box - Press Enter or click on Search icon Input Data:
   * Expected Outcome: Return search result with category/forum/topic/post matched
   * key word search
   */
  public void test01_QuickSearch() {
    info("Test 1: Quick search");

    String name = "name" + getRandomNumber();
    String name2 = "name2" + getRandomNumber();
    String name3 = "name3" + getRandomNumber();
    String name4 = "name4" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String description = "Description" + getRandomNumber();

    homePagePlatform.goToForum();
    forumCategoryManagement.addCategorySimple(name, "", "");
    forumForumManagement.addForumSimple(name2, "", "");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(name3, description, "", "");
    forumHomePage.goToTopic(name3);
    forumTopicManagement.addPostSimple(name4, content);

    $(ELEMENT_SEARCH_TEXTBOX).setValue(name4).pressEnter();
    ELEMENT_SEARCH_RESULT.find(byText(name4)).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byText(name4)).should(Condition.exist);
    $(byText(content)).should(Condition.exist);
    ELEMENT_CLOSE_POPUP.click();
    ELEMENT_HOME_FORUM.click();
    $(byText(name)).click();
    forumCategoryManagement.deleteCategory(name);
  }

  /*
   * Step Number: 1 Step Name: Step 1: Prepare data: createcaterories, forums,
   * topics, posts Step Description: - Log in as admin (root or john) - Click
   * Forums on the left panel - Create categories - Create forums - Create topics
   * - Create posts Input Data: Expected Outcome: Categories, forums, topics,
   * posts are created successfully Step number: 2 Step Name: Step 2: Access the
   * Advanced search form Step Description: - Click a category - Click [Search
   * this category] on the user bar - Input Data: Expected Outcome: - Display the
   * Advanced Search form Step number: 3 Step Name: Step 3: Search in Forum Step
   * Description: - Select Forum in the [Search in] field - Input search criteria
   * - Press Search Input Data: Expected Outcome: Return search results with forum
   * matched key word and search criteria Step number: 4 Step Name: Step 4: Search
   * in Topic Step Description: - Click Advanced Search icon - Select Topic in the
   * [Search in] field - Input search criteria - Press Search Input Data: Expected
   * Outcome: Return search results with post matched key word and search criteria
   * Step number: 5 Step Name: Step 5: Search in Post Step Description: - Click
   * Advanced Search icon - Select Post in the [Search in] field - Input search
   * criteria - Press Search Input Data: Expected Outcome: Return search results
   * with post matched key word and search criteria Step number: 6 Step Name: Step
   * 6: Search in Category Step Description: - Click Advanced Search icon - Select
   * Category in the [Search in] field - Input search criteria - Press Search
   * Input Data: Expected Outcome: Return search results with category matched key
   * word and search criteria
   */

  @Test
  public void test02_AdvancedSearch() {
    info("Test 2: Advanced search");

    String name = "name" + getRandomNumber();
    String name2 = "name2" + getRandomNumber();
    String name3 = "name3" + getRandomNumber();
    String name4 = "name4" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String description = "Description" + getRandomNumber();

    homePagePlatform.goToForum();
    forumCategoryManagement.addCategorySimple(name, "", "");
    forumForumManagement.addForumSimple(name2, "", "");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(name3, description, "", "");
    forumHomePage.goToTopic(name3);
    forumTopicManagement.addPostSimple(name4, content);

    $(ELEMENT_SEARCH_TEXTBOX).setValue(name).pressEnter();
    ;
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH).click();

    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION).selectOption("Forum");
    $(ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX).setValue(name2);
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH).click();
    ELEMENT_SEARCH_RESULT.find(byText(name2)).should(Condition.exist);

    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH).click();
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION).selectOption("Topic");
    $(ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX).setValue(name3);
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH).click();
    ELEMENT_SEARCH_RESULT.find(byText(name3)).should(Condition.exist);

    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH).click();
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION).selectOption("Post");
    $(ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX).setValue(name4);
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH).click();
    ELEMENT_SEARCH_RESULT.find(byText(name4)).should(Condition.exist);

    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH).click();
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION).selectOption("Category");
    $(ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX).setValue(name);
    $(ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH).click();
    ELEMENT_SEARCH_RESULT.find(byText(name)).should(Condition.exist);
    $(byText(name)).click();
    forumCategoryManagement.deleteCategory(name);
  }

}
