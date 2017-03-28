package org.exoplatform.selenium.platform.forum.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class Forum_TopicTestIT extends Base {
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
		forumTopicManagement=new ForumTopicManagement(this);
	}
	/**
	 *<li> Case ID:116764.</li>
	 *<li> Test Case Name: Create new Topic.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 * *<li> Case ID:116767.</li>
	 *<li> Test Case Name: Delete topic.</li>
	 *<li> Pre-Condition: A topic is existed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test09_CreateDeleteNewTopic() {
		info("Test 9: Create new Topic");

		String name = "name"+getRandomNumber();
		String name2 = "name2"+getRandomNumber();
		String desc = "desc"+getRandomNumber();
		String topic = "topic"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: - Create new category
		 *Step Description:
			- Login and goto Forum application
			- Click [Add Category]
			- Fill the information and click [Save]
		 *Input Data:

		 *Expected Outcome:
			- New category is created
			- No activity is added in activity stream*/
		homePagePlatform.goToForum();
		info("Add a category");
		forumCategoryManagement.addCategorySimple(name,"",desc);

		/*Step number: 2
		 *Step Name: - Create new Forum
		 *Step Description:
			- Click [Add Forum]
			- Fill the information and click [Save]
		 *Input Data:

		 *Expected Outcome:
			- New forum is created
			- No activity is added in activity stream*/
		info("Add a forum in the category");
		forumForumManagement.addForumSimple(name2,"",desc);
		/*Step number: 3
		 *Step Name: - Create new Topic
		 *Step Description:
			- Click [start Topic]
			- input the information and click [Save]
		 *Input Data:

		 *Expected Outcome:
			- New Topic is created*/
		info("Add and go to a topic in the forums");
		forumForumManagement.goToStartTopic();
		forumTopicManagement.startTopic(topic, topic,"","");
		forumHomePage.goToTopic(topic);
		$(byText(name2)).waitUntil(Condition.appears, Configuration.timeout);
		info("Delete data");
		forumHomePage.goToHomeCategory();
		forumCategoryManagement.deleteCategory(name);
	}




}
