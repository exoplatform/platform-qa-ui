package org.exoplatform.platform.qa.ui.social.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.platform.qa.ui.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.awt.*;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("social")
public class SOC_HomePageTestIT extends Base {
	NavigationToolbar navigationToolbar;

	HomePagePlatform homePagePlatform;
	ActivityStream activityStream;
	CreateNewDocument createNewDocument;
	SiteExplorerHome siteExplorerHome;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
	navigationToolbar=new NavigationToolbar(this);
	homePagePlatform =new HomePagePlatform(this);
	activityStream=new ActivityStream(this);
	createNewDocument=new CreateNewDocument(this);
	siteExplorerHome=new SiteExplorerHome(this);
	}
	/**
	 *<li> Case ID:121888.</li>
	 *<li> Test Case Name: Like Activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */

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
		homePagePlatform.goToHomePage();

		/*Step Number: 1
		 *Step Name: Step 1: Like/Unlike Activity
		 *Step Description: 
			- Go to Intranet home
			- Click on Like activity in action bar part of an activity
		 *Input Data: 

		 *Expected Outcome: 
			- Like button is highlighted and the number of likers is updated*/

//get the id of the webContent created
		String id = $(byClassName("contentShare")).parent().getAttribute("id").split("ActivityContextBox")[1];

//click on the like button of the webContent
		$(byXpath("//*[@id=\"LikeLink"+id+"\"]")).click();

		$(byXpath("//*[@id=\"UnLikeLink"+id+"\"]")).waitUntil(Condition.appears, Configuration.timeout);
		/*Step number: 2
		 *Step Name: Check Likes part
		 *Step Description: 
			- Check avatar
			- Mouse over the avatar
		 *Input Data: 

		 *Expected Outcome: 
			- Avatar of liker is added into likes part, the oldest liker is displayed at the right and the newest at the left.
			- Profile pictures of users popup*/ 
		/*mouseOver(ELEMENT_PUBLICATION_WHOLIKED, true);
		mouseHoverByJavaScript(ELEMENT_PUBLICATION_WHOLIKED, 2);
		waitForAndGetElement(ELEMENT_PUBLICATION_WHOLIKEDPOPUP);*/
		ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears,Configuration.timeout);
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.deleteData(name);

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

		//get the id of webContent created
		String id = $(byClassName("contentShare")).parent().getAttribute("id").split("ActivityContextBox")[1];
		//click on comment icon
		$(byXpath("//*[@id=\"CommentLink"+id+"\"]")).click();
		//insert comment
		$(byId("cke_CommentTextarea"+id)).waitUntil(Condition.appears,Configuration.timeout).click();
		executeJavaScript("CKEDITOR.instances.CommentTextarea"+id+".insertText(\"test\")","");
		//click on the button comment
		$(byText("Comment")).waitUntil(Condition.enabled,Configuration.timeout).click();


		info("Test 15: Delete comment");
		//hover on the comment to appear the delete button
		$(byClassName("commentRight")).waitUntil(Condition.appears,Configuration.timeout).hover();
		//the id of the comment is id of the activity+1
		Integer idComment=Integer.parseInt(id)+1;
		$(byId("DeleteCommentButtoncomment"+idComment.toString())).click();
		//Confirm
		$(byText("OK")).click();
		//verify that the comment is deleted
		$(byText("test")).shouldNot(Condition.exist);
		//delete webContent
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.deleteData(name);



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

		//get the id of the webContent created
		String id = $(byClassName("contentShare")).parent().getAttribute("id").split("ActivityContextBox")[1];
		//hover on the activity to appear the delete button
		$(byId("ActivityContextBox"+id)).find(byClassName("dateTime")).hover();
		//click on delete button
		$(byId("DeleteActivityButton"+id)).click();
		$(byText("OK")).click();
		$(byText(name)).shouldNot(Condition.exist);
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.deleteData(name);


	}


}