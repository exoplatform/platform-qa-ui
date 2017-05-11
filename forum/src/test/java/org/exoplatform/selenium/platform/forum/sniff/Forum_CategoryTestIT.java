package org.exoplatform.selenium.platform.forum.sniff;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("forum")
public class Forum_CategoryTestIT extends Base{
	HomePagePlatform homePagePlatform;
	ForumCategoryManagement forumCategoryManagement;
	ForumHomePage forumHomePage;
	ForumForumManagement forumForumManagement;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");

		homePagePlatform = new HomePagePlatform(this);
		forumHomePage = new ForumHomePage(this);
		forumCategoryManagement = new ForumCategoryManagement(this);
		forumForumManagement = new ForumForumManagement(this);
	}

	/**
	 *<li> Case ID:116742.</li>
	 *<li> Test Case Name: Add a category.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:116743.</li>
	 *<li> Test Case Name: Edit a category.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:116744.</li>
	 *<li> Test Case Name: Delete a category.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_03_04_AddEditDeleteACategory() {
		info("Test 2: Add a category");

		String nameCat = "nameCat"+getRandomNumber();
		String nameCat2 = "nameCat2"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Add a category
		 *Step Description:
			- Go to Forum page
			- Click on Add Category
			- Put values
			- Save
		 *Input Data:

		 *Expected Outcome:
			- Category is added successfully and shown in Forum home*/
		info("go to Forum home page");
		homePagePlatform.goToForum();
		info("Add a category");
		forumCategoryManagement.addCategorySimple(nameCat,"",nameCat);

		info("Test 03: Edit a category");
		info("edit category");
		forumCategoryManagement.editCategory(nameCat2);


		info("Test 04: Delete a category");
		info("delete category");
		forumHomePage.goToHomeCategory();
		forumCategoryManagement.deleteCategory(nameCat2);
	}


	}