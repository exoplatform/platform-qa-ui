package org.exoplatform.platform.qa.ui.social.smoke;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.platform.qa.ui.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.*;

import static com.codeborne.selenide.Configuration.browser;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class SOC_HomePage extends Base {
	/**
	 *<li> Case ID:121888.</li>
	 *<li> Test Case Name: Like Activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	NavigationToolbar navigationToolbar;

	HomePagePlatform homePagePlatform;
	ActivityStream activityStream;
	CreateNewDocument createNewDocument;
	SiteExplorerHome siteExplorerHome;
	@Test
	public  void test01_LikeActivity() {
		info("Test 1: Like Activity");

		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();

		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(name, content);
		createNewDocument.saveAndClose();
		Utils.pause(5000);
		homePagePlatform.goToHomePage();

		/*Step Number: 1
		 *Step Name: Step 1: Like/Unlike Activity
		 *Step Description: 
			- Go to Intranet home
			- Click on Like activity in action bar part of an activity
		 *Input Data: 

		 *Expected Outcome: 
			- Like button is highlighted and the number of likers is updated*/

		clickByJavascript((ELEMENT_PUBLICATION_LIKE).replace("${title}", name));
		waitForAndGetElement((ELEMENT_PUBLICATION_LIKED).replace("${title}", name));

		/*Step number: 2
		 *Step Name: Check Likes part
		 *Step Description: 
			- Check avatar
			- Mouse over the avatar
		 *Input Data: 

		 *Expected Outcome: 
			- Avatar of liker is added into likes part, the oldest liker is displayed at the right and the newest at the left.
			- Profile pictures of users popup*/ 
		//mouseOver(hp.ELEMENT_PUBLICATION_WHOLIKED, true);
		mouseHoverByJavaScript(ELEMENT_PUBLICATION_WHOLIKED, 2);
		waitForAndGetElement(ELEMENT_PUBLICATION_WHOLIKEDPOPUP);
	}

	/**
	 *<li> Case ID:121909.</li>
	 *<li> Test Case Name: Add comment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:121933.</li>
	 *<li> Test Case Name: Delete comment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_15_AddDeleteComment() {
		info("Test 2: Add comment");

		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String content2 = "content2"+getRandomNumber();

		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(name, content);
		createNewDocument.saveAndClose();
		Utils.pause(5000);
		homePagePlatform.goToHomePage();

		/*Step Number: 1
		 *Step Name: Add comment for activity
		 *Step Description: 
			- Go to Intranet home
			- Select the activity
			- Click comment icon to show input text field
			- input the comment and click comment
		 *Input Data: 

		 *Expected Outcome: 
			- Comment will be shown in comment section of activity*/
		activityStream.addComment(name,content2);
		waitForAndGetElement(ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}", content2));

		info("Test 15: Delete comment");
		homePagePlatform.goToHomePage();
		activityStream.deleteComment(name, content2);
		waitForElementNotPresent(ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}", content2));
	}

	/**
	 *<li> Case ID:121910.</li>
	 *<li> Test Case Name: Delete your activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_DeleteYourActivity() {
		info("Test 3: Delete your activity");
		//Actions actions = new Actions(driver);
		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();

		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(name, content);
		createNewDocument.saveAndClose();
		Utils.pause(5000);
		homePagePlatform.goToHomePage();

		/*Step Number: 1
		 *Step Name: - Delete a comment
		 *Step Description: 
			- Go to Intranet home
			- Select the activity 
			- mouse over activity you want to delete
			- Click the (x) icon to delete
		 *Input Data: 

		 *Expected Outcome: 
			- Comment will be shown in comment section of activity
			- the (x) icon display on the top
			-right of activity
			- activity is deteled successfully*/ 
		if(browser.contains("iexplorer")){
			info("Click X icon");
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_PUBLICATION_AUTHOR.replace("${title}", name), DEFAULT_TIMEOUT, 1);
			mouseOver((ELEMENT_PUBLICATION_AUTHOR).replace("${title}", name), true);
			waitForAndGetElement(ELEMENT_PUBLICATION_DELETE.replace("${title}", name), 5000, 1);
			Utils.pause(2000);
			clickByJavascript((ELEMENT_PUBLICATION_DELETE).replace("${title}", name), 2);
			
			info("clik OK button");
			waitForAndGetElement(ELEMENT_DELETE_POPUP_OK, DEFAULT_TIMEOUT, 1);
			Utils.pause(2000);
			
			click(ELEMENT_DELETE_POPUP_OK, 2);
			waitForElementNotPresent(ELEMENT_PUBLICATION_AUTHOR.replace("${title}", name));
		}
		else{
			Utils.pause(2000);
			mouseOver((ELEMENT_PUBLICATION_AUTHOR).replace("${title}", name), true);
			waitForAndGetElement(ELEMENT_PUBLICATION_DELETE.replace("${title}", name), 5000, 1);
			click((ELEMENT_PUBLICATION_DELETE).replace("${title}", name));
			click(ELEMENT_DELETE_POPUP_OK);
			waitForElementNotPresent(ELEMENT_PUBLICATION_TITLE.replace("${title}", name));
		}
	}


}