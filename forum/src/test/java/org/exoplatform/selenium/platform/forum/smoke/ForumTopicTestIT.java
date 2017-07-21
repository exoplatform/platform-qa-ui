package org.exoplatform.selenium.platform.forum.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_ACTIONBAR_TOPIC_TAGDELETE;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_FORUM_POLL_GRID;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("forum")
public class ForumTopicTestIT extends Base {
  
    HomePagePlatform homePagePlatform;

    ForumCategoryManagement forumCategoryManagement;

    ForumHomePage forumHomePage;

    ForumForumManagement forumForumManagement;

    ForumTopicManagement forumTopicManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        forumHomePage = new ForumHomePage(this);
        forumCategoryManagement = new ForumCategoryManagement(this);
        forumForumManagement = new ForumForumManagement(this);
        forumTopicManagement = new ForumTopicManagement(this);
    }
    @Test
    public void test02_RateTopic() {
        info("Test 2: Rate topic");
        String name = "Category" + getRandomNumber();
        String name2 = "Forum" + getRandomNumber();
        String desc = "Description" + getRandomNumber();
        String topic = "Topic" + getRandomNumber();

		/*Step Number: 1
         *Step Name: -
		 *Step Description:
			Step 1: Rate Topic
		 *Input Data:
			- Open 1 topic
			- Click on Rate
			- Move mouse over stars
		 *Expected Outcome:
			Topic is rated successfully. The number of stars are selected set yellow.*/
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
     * <li> Case ID:116695.</li>
     * <li> Test Case Name: Rate topic.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    /**
     * <li> Case ID:116702.</li>
     * <li> Test Case Name: Tag for topic.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test03_TagForTopic() {
        info("Test 3: Tag for topic");
        String name = "Category" + getRandomNumber();
        String name2 = "Forum" + getRandomNumber();
        String desc = "Description" + getRandomNumber();
        String topic = "Topic" + getRandomNumber();

		/*Step Number: 1
         *Step Name: -
		 *Step Description:
			Step 1: Manage tag on Topic
		 *Input Data:
			- Open 1 topicAdd tag
			- Click on Tag
			- Put tag's name
		 *Expected Outcome:
			- Topic is added tag successful
			- Topic is displayed when view tag
			- Topic is untagged successful
			- Topic is auto
			-suggest tag when type first character of an existing tag name*/
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
     * <li> Case ID:116776.</li>
     * <li> Test Case Name: Add a new poll.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * <p>
     * *<li> Case ID:116760.</li>
     * <li> Test Case Name: Edit a poll.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * <p>
     * <li> Case ID:116761.</li>
     * <li> Test Case Name: Close / Reopen a poll.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * <p>
     * *<li> Case ID:116777.</li>
     * <li> Test Case Name: Delete a poll.</li>
     * <li> Pre-Condition: a poll's activity is shared in the activity stream</li>
     * <li> Post-Condition: </li>
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
    public void test09_CreateDeleteNewTopic() {
        info("Test 9: Create new Topic");
        String name = "name" + getRandomNumber();
        String name2 = "name2" + getRandomNumber();
        String desc = "desc" + getRandomNumber();
        String topic = "topic" + getRandomNumber();
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
        info("Delete data");
        forumHomePage.goToHomeCategory();
        forumCategoryManagement.deleteCategory(name);
    }
}
