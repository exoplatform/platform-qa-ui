package org.exoplatform.platform.qa.ui.platform.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.Keys;
import org.testng.annotations.*;

/**
* @author cmugnier
* @date 20/01/2015
*/

public class ForumSearchTestIT extends Forum_TestConfig {
	
	/**
	 *<li> Case ID:116696.</li>
	 *<li> Test Case Name: Quick search.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public void test01_QuickSearch() {
		info("Test 1: Quick search");
		
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		hp.goToForum();
		forumCatMag.addCategorySimple(name,"","");
		forumMag.addForumSimple(name2,"","");
		foTopic.addTopicSimple(name3, content);
		forumHP.goToTopic(name3);
		foTopic.addPostSimple(name4, content);
		/*Step Number: 1
		 *Step Name: Prepare data: create a caterory, forum, topic, post
		 *Step Description:
		- Log in as admin (root or john)
		- Click Forums on the left panel
		- Create categories
		- Create forums
		- Create topics
		- Create posts
		 *Input Data:
		 *Expected Outcome:
		Category, forum, topic, post are created successfully*/
		/*Step number: 2
		 *Step Name: Quick Search
		 *Step Description:
		- Put keyword in the Search box
		- Press Enter or click on Search icon
		 *Input Data:
		 *Expected Outcome:
		Return search result with category/forum/topic/post matched key word search*/
		type(forumHP.ELEMENT_SEARCH_TEXTBOX, name4, true);
		driver.findElement(forumHP.ELEMENT_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		waitForAndGetElement((forumHP.ELEMENT_SEARCH_FORUM_POST).replace("${name}", name4));
	}
	
	
	/**
	 *<li> Case ID:116697.</li>
	 *<li> Test Case Name: Advanced search.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public void test02_AdvancedSearch() {
		info("Test 2: Advanced search");
		
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Prepare data: createcaterories, forums, topics, posts
		 *Step Description:
		- Log in as admin (root or john)
		- Click Forums on the left panel
		- Create categories
		- Create forums
		- Create topics
		- Create posts
		 *Input Data:
		 *Expected Outcome:
		Categories, forums, topics, posts are created successfully*/

		hp.goToForum();
		forumCatMag.addCategorySimple(name,"","");
		forumMag.addForumSimple(name2,"","");
		foTopic.addTopicSimple(name3, content);
		forumHP.goToTopic(name3);
		foTopic.addPostSimple(name4, content);
		
		/*Step number: 2
		 *Step Name: Step 2: Access the Advanced search form
		 *Step Description:
		- Click a category
		- Click [Search this category] on the user bar
		-
		 *Input Data:
		 *Expected Outcome:
		- Display the Advanced Search form*/
	
		type(forumHP.ELEMENT_SEARCH_TEXTBOX, name, true);
		driver.findElement(forumHP.ELEMENT_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH);
		
		/*Step number: 3
		 *Step Name: Step 3: Search in Forum
		 *Step Description:
		- Select Forum in the [Search in] field
		- Input search criteria
		- Press Search
		 *Input Data:
		 *Expected Outcome:
		Return search results with forum matched key word and search criteria*/
	
		select(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION, "Forum", 2);
		type(forumHP.ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX, name2, true);
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH);
		waitForAndGetElement((forumHP.ELEMENT_SEARCH_FORUM_FORUM).replace("${name}", name2));
		
		/*Step number: 4
		 *Step Name: Step 4: Search in Topic
		 *Step Description:
		- Click Advanced Search icon
		- Select Topic in the [Search in] field
		- Input search criteria
		- Press Search
		 *Input Data:
		 *Expected Outcome:
		Return search results with post matched key word and search criteria*/
		
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH);
		select(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION, "Topic", 2);
		type(forumHP.ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX, name3, true);
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH);
		waitForAndGetElement((forumHP.ELEMENT_SEARCH_FORUM_TOPIC).replace("${name}", name3));
		
		/*Step number: 5
		 *Step Name: Step 5: Search in Post
		 *Step Description:
		- Click Advanced Search icon
		- Select Post in the [Search in] field
		- Input search criteria
		- Press Search
		 *Input Data:
		 *Expected Outcome:
		Return search results with post matched key word and search criteria*/
		
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH);
		select(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION, "Post", 2);
		type(forumHP.ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX, name4, true);
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH);
		waitForAndGetElement((forumHP.ELEMENT_SEARCH_FORUM_POST).replace("${name}", name4));
		
		/*Step number: 6
		 *Step Name: Step 6: Search in Category
		 *Step Description:
		- Click Advanced Search icon
		- Select Category in the [Search in] field
		- Input search criteria
		- Press Search
		 *Input Data:
		 *Expected Outcome:
		Return search results with category matched key word and search criteria*/
		
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH);
		select(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION, "Category", 2);
		type(forumHP.ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX, name, true);
		click(forumHP.ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH);
		waitForAndGetElement((forumHP.ELEMENT_SEARCH_FORUM_CATEGORY).replace("${name}", name));
	}
	
}