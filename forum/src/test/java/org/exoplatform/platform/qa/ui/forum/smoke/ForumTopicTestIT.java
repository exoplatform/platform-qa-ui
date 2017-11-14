package org.exoplatform.platform.qa.ui.forum.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_ACTIONBAR_TOPIC_TAGDELETE;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_FORUM_POLL_GRID;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("sniff")
@Tag("forum")
public class ForumTopicTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumCategoryManagement forumCategoryManagement;

  ForumHomePage           forumHomePage;

  ForumForumManagement    forumForumManagement;

  ForumTopicManagement    forumTopicManagement;

  ManageLogInOut          manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }

    manageLogInOut.signInCas(username, password);
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  @Test
  public void test02_RateTopic() {
    info("Test 2: Rate topic");
    String name = "Category RateTopic" ;
    String name2 = "Forum RateTopic" ;
    String desc = "Description RateTopic" ;
    String topic = "Topic RateTopic";

    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Rate Topic Input Data:
     * - Open 1 topic - Click on Rate - Move mouse over stars Expected Outcome:
     * Topic is rated successfully. The number of stars are selected set yellow.
     */
    info("Open Forum portlet");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    info("Rate the topic");
    forumHomePage.goToTopic(topic);
    forumTopicManagement.rateTopic(name2);

  }

  /**
   * <li>Case ID:116695.</li>
   * <li>Test Case Name: Rate topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /**
   * <li>Case ID:116702.</li>
   * <li>Test Case Name: Tag for topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test03_TagForTopic() {
    info("Test 3: Tag for topic");
    String name = "Category TagForTopic";
    String name2 = "Forum TagForTopic" ;
    String desc = "Description TagForTopic" ;
    String topic = "Topic TagForTopic" ;

    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Manage tag on Topic
     * Input Data: - Open 1 topicAdd tag - Click on Tag - Put tag's name Expected
     * Outcome: - Topic is added tag successful - Topic is displayed when view tag -
     * Topic is untagged successful - Topic is auto -suggest tag when type first
     * character of an existing tag name
     */
    info("Open Forum portlet");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    info("Add and verify a tag");
    forumTopicManagement.addATag(name);

  }


  /**
   * <li>Case ID:116776.</li>
   * <li>Test Case Name: Add a new poll.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test05_AddANewPoll() {
    info("Test 5: Add a new poll");
    String name = "Category AddANewPoll" ;
    String name2 = "Forum AddANewPoll" ;
    String name3 = "Poll AddANewPoll" ;
    String name4 = "Poll AddANewPoll" ;
    String name5 = "Poll AddANewPoll" ;
    String name6 = "Poll AddANewPoll" ;
    String desc = "Description AddANewPoll";
    String topic = "Topic AddANewPoll" ;

    info("Open forum portlet");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    info("Add a new poll to the topic");
    forumHomePage.goToTopic(topic);
    forumTopicManagement.addPoll(name3, name3, name4);
    $(ELEMENT_FORUM_POLL_GRID).should(Condition.exist);

  }

  /**
   * <li>Case ID:116764.</li>
   * <li>Test Case Name: Create new Topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> *
   * <li>Case ID:116767.</li>
   * <li>Test Case Name: Delete topic.</li>
   * <li>Pre-Condition: A topic is existed</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("smoke")
  public void test09_CreateNewTopic() {
    info("Test 9: Create new Topic");
    String name = "name CreateNewTopic" ;
    String name2 = "name2 CreateNewTopic";
    String desc = "desc CreateNewTopic" ;
    String topic = "topic CreateNewTopic" ;
    /*
     * Step Number: 1 Step Name: - Create new category Step Description: - Login and
     * goto Forum application - Click [Add Category] - Fill the information and
     * click [Save] Input Data: Expected Outcome: - New category is created - No
     * activity is added in activity stream
     */
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    /*
     * Step number: 2 Step Name: - Create new Forum Step Description: - Click [Add
     * Forum] - Fill the information and click [Save] Input Data: Expected Outcome:
     * - New forum is created - No activity is added in activity stream
     */
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);
    /*
     * Step number: 3 Step Name: - Create new Topic Step Description: - Click [start
     * Topic] - input the information and click [Save] Input Data: Expected Outcome:
     * - New Topic is created
     */
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    $(byText(name2)).waitUntil(Condition.appears, Configuration.timeout);

  }
}
