package org.exoplatform.platform.qa.ui.platform.forum;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("forum")
public class ForumTopicTestIT extends Base {
	HomePagePlatform homePagePlatform;

	ForumForumManagement forumForumManagement;

	ForumTopicManagement forumTopicManagement;

	ForumCategoryManagement forumCategoryManagement;

	ForumHomePage forumHomePage;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");

		homePagePlatform = new HomePagePlatform(this);
		forumForumManagement = new ForumForumManagement(this);
		forumTopicManagement = new ForumTopicManagement(this);
		forumHomePage = new ForumHomePage(this);
		forumCategoryManagement = new ForumCategoryManagement(this);
	}

	/**
	 *<li> Case ID:116689.</li>
	 *<li> Test Case Name: Watch&Unwatch topic.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_Watch_UnwatchTopic() {
		info("Test 1: Watch&Unwatch topic");

		String name = "name"+getRandomNumber();
		String desc = "desc"+getRandomNumber();
		String topic = "topic"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Prepare data: create a caterory, forum, topic
		 *Step Description:
			- Create a category
			- Create a forum
			- Create a topic
		 *Input Data:

		 *Expected Outcome:
			Category, forum, topic are created successfully*/
		info("Go to Forum portlet");
		homePagePlatform.goToForum();
		info("Add a category");
		forumCategoryManagement.addCategorySimple(name,"",desc);
		info("Add a forum in the category");
		forumForumManagement.addForumSimple(name,"",desc);
		info("Add and go to a topic in the forums");
		forumForumManagement.goToStartTopic();
		forumTopicManagement.startTopic(topic, topic,"","");

		/*Step number: 2
		 *Step Name: Watch a topic
		 *Step Description:
			- Access a topic
			- Click on Watch
		 *Input Data:

		 *Expected Outcome:
			Topic is watchedsuccessfully*/
		info("Go to a topic");
		forumHomePage.goToTopic(topic);
		info("Watch the topic");
		forumHomePage.watchItem(true);
		/*Step number: 3
		 *Step Name: Unwatch a topic
		 *Step Description:
			- Access a watched topic
			- Click on Unwatch
		 *Input Data:

		 *Expected Outcome:
			Topic is unwatchedsuccessfully*/
		info("UnWatch the topic");
		forumHomePage.watchItem(false);
		info("Delete Category");
		forumHomePage.goToHomeCategory();
		forumCategoryManagement.deleteCategory(name);
	}
	}