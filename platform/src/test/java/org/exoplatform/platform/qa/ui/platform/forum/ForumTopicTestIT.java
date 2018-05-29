package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_ACTIONBAR_TOPIC_TAGDELETE;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_FORUM_POLL_GRID;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

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

@Tag("forum")
@Tag("sniff")
public class ForumTopicTestIT extends Base {
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

  /**
   * <li>Case ID:116689.</li>
   * <li>Test Case Name: Watch&Unwatch topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Prepare data: create a
   * caterory, forum, topic Step Description: - Create a category - Create a forum
   * - Create a topic Input Data: Expected Outcome: Category, forum, topic are
   * created successfully Step number: 2 Step Name: Watch a topic Step
   * Description: - Access a topic - Click on Watch Input Data: Expected Outcome:
   * Topic is watchedsuccessfully Step number: 3 Step Name: Unwatch a topic Step
   * Description: - Access a watched topic - Click on Unwatch Input Data: Expected
   * Outcome: Topic is unwatchedsuccessfully
   */
  @Test
  public void test01_Watch_UnwatchTopic() {
    info("Test 1: Watch&Unwatch topic");

    String name = "name" + getRandomNumber();
    String desc = "desc" + getRandomNumber();
    String topic = "topic" + getRandomNumber();

    info("Go to Forum portlet");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name, "", desc);
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");

    info("Go to a topic");
    forumHomePage.goToTopic(topic);
    info("Watch the topic");
    forumHomePage.watchItem(true);

    info("UnWatch the topic");
    forumHomePage.watchItem(false);
    info("Delete Category");
    forumHomePage.goToHomeCategory();
    $(byText(name)).click();
    forumCategoryManagement.deleteCategory(name);
  }

  @Test
  public void test02_RateTopic() {
    info("Test 2: Rate topic");
    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

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
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
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
    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

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
    info("Delete the tag");
    $(ELEMENT_ACTIONBAR_TOPIC_TAGDELETE).click();
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
  }

  /**
   * <li>Case ID:116759.</li>
   * <li>Test Case Name: Move a topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test04_MoveATopic() {
    info("Test 4: Move a topic");

    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String name3 = "Forum" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Prepare data: create a caterory, forum, topic Step
     * Description: - Create a category - Create a forum - Create a topic Input
     * Data: Expected Outcome: Category, forum, topic are created successfully
     */
    info("Open forum portlet");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);

    forumHomePage.goToHomeCategory();
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name3, "", desc);
    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    /*
     * Step number: 2 Step Name: Move a topic Step Description: - Open topic above -
     * Click More Action >’ Move - Choose the destination forum Input Data: Expected
     * Outcome: - Topic is moved to destination Forum successfully
     */

    info("Move Topic:" + topic);
    forumHomePage.goToTopic(topic);
    forumTopicManagement.selectItemMoreActionMenuTopic(ForumTopicManagement.specifMoreActionMenuTopic.MOVE);
    info("Move the topic to a forum");
    forumTopicManagement.moveTopicToForum(name, name2);
    info("Verify that the forum is moved to new category");
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
  }

  /**
   * <li>Case ID:116776.</li>
   * <li>Test Case Name: Add a new poll.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <p>
   * *
   * <li>Case ID:116760.</li>
   * <li>Test Case Name: Edit a poll.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <p>
   * <li>Case ID:116761.</li>
   * <li>Test Case Name: Close / Reopen a poll.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <p>
   * *
   * <li>Case ID:116777.</li>
   * <li>Test Case Name: Delete a poll.</li>
   * <li>Pre-Condition: a poll's activity is shared in the activity stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test05_AddANewPoll() {
    info("Test 5: Add a new poll");
    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String name3 = "Poll" + getRandomNumber();
    String name4 = "Poll" + getRandomNumber();
    String name5 = "Poll" + getRandomNumber();
    String name6 = "Poll" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

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
    info("delete poll");
    forumTopicManagement.deletePoll();
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
  }

  @Test
  public void test06_EditANewPoll() {
    info("Test 6: Edit a poll");
    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String name3 = "Poll" + getRandomNumber();
    String name4 = "Poll" + getRandomNumber();
    String name5 = "Poll" + getRandomNumber();
    String name6 = "Poll" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

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
    info("Edit a poll");
    forumTopicManagement.editPoll(name5, name5, name6);
    info("delete poll");
    forumTopicManagement.deletePoll();
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
  }

  /**
   * <li>Case ID:116762.</li>
   * <li>Test Case Name: Lock/ Unlock a topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test07_LockUnlockATopic() {
    info("Test 7: Lock/ Unlock a topic");

    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Prepare data: create a caterory, forum, topic Step
     * Description: - Create a category - Create a forum - Create a topic Input
     * Data: Expected Outcome: Category, forum, topic are created successfully
     */

    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);

    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    /*
     * Step number: 2 Step Name: Lock a topic Step Description: - Click on More
     * action> Lock Input Data: Expected Outcome: Topic is locked successfully.
     * Users cannot post to this.
     */
    info("Lock the topic");
    forumTopicManagement.lockUnlockTopic(true);

    /*
     * Step number: 3 Step Name: Unlock a topic Step Description: - Open a locked
     * topic - Click on More action> Unlock Input Data: Expected Outcome: Topic is
     * unlocked successfully.
     */
    info("Unlock the topic");
    forumTopicManagement.lockUnlockTopic(false);
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
  }

  /**
   * <li>Case ID:116763.</li>
   * <li>Test Case Name: Open/ Close a topic.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test08_OpenCloseATopic() {
    info("Test 8: Open/ Close a topic");

    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Prepare data: create a caterory, forum, topic Step
     * Description: - Create a category - Create a forum - Create a topic Input
     * Data: Expected Outcome: Category, forum, topic are created successfully
     */
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name2, "", desc);

    info("Add and go to a topic in the forums");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");

    /*
     * Step number: 2 Step Name: Close a topic Step Description: Click on More
     * action > Close Input Data: Expected Outcome: Normal user can not view closed
     * topic
     */

    info("Close the topic");
    forumHomePage.goToTopic(topic);
    forumTopicManagement.closeOpenTopic(true);
    info("sign out and log in with user2");
    // manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    $(byText(name2)).click();
    $(byText(topic)).should(Condition.exist);

    /*
     * Step number: 3 Step Name: Open a topic Step Description: Access a closed
     * topic, Click on More action > Open Input Data: Expected Outcome: Open topic
     * successfully.
     */

    info("sign out and log in with user1");
    // manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Go to the forum");
    $(byText(name2)).click();
    info("Go to the topic");
    forumHomePage.goToTopic(topic);
    info("open the topic");
    forumTopicManagement.closeOpenTopic(false);

    info("sign out and log in with user2");
    // manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Verify that the topic is shown again");
    $(byText(topic)).should(Condition.exist);

    info("log in back USER1");
    // manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Delete data");
    $(byText(name)).click();
    forumCategoryManagement.deleteCategory(name);
  }

  /**
   * <li>Case ID:116766.</li>
   * <li>Test Case Name: Update topic content.</li>
   * <li>Pre-Condition: - A topic exists</li>
   * <li>Post-Condition:</li>
   * <p>
   * *
   * <li>Case ID:116765.</li>
   * <li>Test Case Name: Update topic title.</li>
   * <li>Pre-Condition: - A topic exists</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test10_UpdateTopicContent() {
    info("Test 11 Update topic content");

    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String name3 = "Topic" + getRandomNumber();
    String content = "NewContent" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: - Edit Topic content Step Description: - Login and
     * goto Forum - Goto Category -> Forum ->Topic - Click [More Action] -> Edit -
     * Update content of topic with new value - click [Submit] Input Data: Expected
     * Outcome: - Topic content is updated
     */
    homePagePlatform.goToForum();
    info("Add a topic");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    forumForumManagement.addForumSimple(name2, "", desc);
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    forumTopicManagement.editTopic(name3, content);
    info("Open forum portlet");
    homePagePlatform.goToForum();
    info("Go to Forum home page");
    forumHomePage.goToHomeCategory();
    info("Delete category");
    forumCategoryManagement.deleteCategory(name);
  }

  @Test
  public void test11_UpdateTopicTitle() {
    info("Test 11 Update topic content");

    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String name3 = "Topic" + getRandomNumber();
    String content = "NewContent" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: - Edit Topic content Step Description: - Login and
     * goto Forum - Goto Category -> Forum ->Topic - Click [More Action] -> Edit -
     * Update content of topic with new value - click [Submit] Input Data: Expected
     * Outcome: - Topic content is updated
     */
    homePagePlatform.goToForum();
    info("Add a topic");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    forumForumManagement.addForumSimple(name2, "", desc);
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumHomePage.goToTopic(topic);
    forumTopicManagement.editTopic(name3, content);
    info("Open forum portlet");
    homePagePlatform.goToForum();
    info("Go to Forum home page");
    forumHomePage.goToHomeCategory();
    info("Delete category");
    forumCategoryManagement.deleteCategory(name);
  }

  @Test
  public void test13_CloseReopenANewPoll() {
    info("Test 13: Close and open a poll");
    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String name3 = "Poll" + getRandomNumber();
    String name4 = "Poll" + getRandomNumber();
    String name5 = "Poll" + getRandomNumber();
    String name6 = "Poll" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

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
    info("Close the poll");
    forumTopicManagement.closeOpenPoll(true);
    info("Open the poll");
    forumTopicManagement.closeOpenPoll(false);
    info("delete poll");
    forumTopicManagement.deletePoll();
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);

  }

  @Test
  public void test14_DeleteaPoll() {
    info("Test 14: Delete a poll");
    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String name3 = "Poll" + getRandomNumber();
    String name4 = "Poll" + getRandomNumber();
    String name5 = "Poll" + getRandomNumber();
    String name6 = "Poll" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

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
    info("delete poll");
    forumTopicManagement.deletePoll();
    info("Delete data");
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
  }
}
