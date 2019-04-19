package org.exoplatform.platform.qa.ui.platform.social.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMMENT_TEXT;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMMENT_TEXT_QUOTES;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("functional")
@Tag("social")
public class SOC_People_Activity_UserProfileActivitiesTestIT extends Base {
	NavigationToolbar navigationToolbar;
	NotificationsAdminSeting notificationsAdminSeting;
	ManageLogInOut manageLogInOut;
	UserAndGroupManagement userAndGroupManagement;
	UserAddManagement userAddManagement;
	UserProfilePage userProfilePage;
	ActivityStream activityStream;
	AddUsers addUsers;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		navigationToolbar = new NavigationToolbar(this);
		userAddManagement = new UserAddManagement(this);
		manageLogInOut = new ManageLogInOut(this);
		notificationsAdminSeting = new NotificationsAdminSeting(this);
		userAndGroupManagement = new UserAndGroupManagement(this);
		userProfilePage = new UserProfilePage(this);
		activityStream = new ActivityStream(this);
		addUsers = new AddUsers(this);
		manageLogInOut.signInCas(DATA_USER1,DATA_PASS2);

	}


	/**
	 *<li> Case ID:125306.</li>
	 *<li> Test Case Name: Update user profile activity after changing avatar.</li>
	 *<li> Case ID:126694.</li>
	 *<li> Test Case Name: Dislike User profile activity from like icon.</li>
	 *<li> Case ID:126693.</li>
	 *<li> Test Case Name: Delete User profile activity from activity stream by owner.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_04_UpdateUserProfileActivityAfterChangingAvatar() {
		info("Test 4: Update user profile activity after changing avatar");

		/*Create data test*/
		String username1 = "usernameaa" + getRandomString();
		String email1 = username1+"@gmail.com";
		String password="1234567";
		info("Add new user");
		navigationToolbar.goToAddUser();
		userAddManagement.addUser(username1, password, email1, username1, username1);
		manageLogInOut.signIn(username1,password);

		
		String activity1 = username1 +" "+username1;
		String comment1= "Avatar has been updated.";

		/*Step Number: 1
		 *Step Name: Step 1: Go to my profile page
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			Show content of my profile page*/
		navigationToolbar.goToMyProfile();
		/*Step number: 2
		 *Step Name: Step 2: Edit Profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
		 *Input Data: 

		 *Expected Outcome: 
			Edit Profile page is displayed*/
		info("edit avatar");
		String oldUrl=$(byXpath(ELEMENT_USER_AVATAR.replace("${userName}",username1))).getAttribute("src");
		info("edit profile");
		$(ELEMENT_EDIT_MY_PROFILE_LINK).click();
		/*Step number: 3
		 *Step Name: Step 3: Change avatar
		 *Step Description: 
			- Click on [Change Avatar] button 
			- Browser to image file ( .png, .gif, .jpg, .jpeg) on local with size is smaller 2MB
			- Click on Confirm button and then [OK]
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added in user activity stream and Recent Activities: Avatar has been updated.
			- Activity content is updated with new avatar*/
		userProfilePage.changeAvatar();
		userProfilePage.saveCancelUpdateInfo(false);
		String newUrl=$(byXpath(ELEMENT_USER_AVATAR.replace("${userName}",username1))).getAttribute("src");
		assert !oldUrl.equals(newUrl);
		$(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1))).waitUntil(Condition.visible, Configuration.timeout);
		navigationToolbar.goToMyActivities();
		$(byId("UIUserActivityStreamPortlet")).find(byText(activity1)).parent().parent().parent().parent().find(byText(comment1)).waitUntil(Condition.visible,Configuration.timeout);

		info("Test 2: Dislike User profile activity from like icon");
		/*Step Number: 1
		 *Step Name: Step 1: Like user profile activity
		 *Step Description: 
			- Connect to Intranet 
			- Click Like icon
		 *Input Data: 

		 *Expected Outcome: 
			- The User profile activity is displayed in the activity stream
			-Number of liker is increased 1*/
		navigationToolbar.goToMyActivities();
		activityStream.likeActivity(activity1);

		/*Step number: 2
		 *Step Name: Step 2: Dislike user profile activity
		 *Step Description: 
			- Click on like icon again
		 *Input Data: 

		 *Expected Outcome: 
			- The User profile activity is disliked by the user, the number of like is updated to 
			-1*/
		activityStream.unlikeActivity(activity1);

		info("Test 1: Delete User profile activity from activity stream by owner");
		/*Step Number: 1
		 *Step Name: Step 1: Show Remove icon
		 *Step Description: 
			- Connect to intranet
			- Move the mouse over the User profile activity on activity stream
		 *Input Data: 

		 *Expected Outcome: 
			A (X) icon is displayed in the top right panel of the activity*/
		/*Create data test*/

		/*Step number: 2
		 *Step Name: Step 2: Click Remove icon
		 *Step Description: 
			- Click on the (X) icon
		 *Input Data: 

		 *Expected Outcome: 
			The User profile activity for spaceis removed from the activity stream*/
		manageLogInOut.signIn(DATA_USER1, "gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);


	}


	/**
	 *<li> Case ID:125307.</li>
	 *<li> Test Case Name: Update user profile activity after editing About me.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_UpdateUserProfileActivityAfterEditingAboutMe() {
		info("Test 5: Update user profile activity after editing About me");
		String aboutMe = "usernamebb"+getRandomNumber();

		/*Create data test*/
		String username1 = "usernameabc" + getRandomString();
		String email1 = username1+"@gmail.com";
		String password="1234567";
		info("Add new user");
		navigationToolbar.goToAddUser();
		userAddManagement.addUser(username1, password, email1, username1, username1);
		manageLogInOut.signIn(username1,password);

		
		String comment1 = "\"About me\" has been updated.";
		String comment2 = "\"About me\" has been updated.";
		String activity1 = username1 + " "+username1;

		/*Step Number: 1
		 *Step Name: Step 1: Go to my profile page
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			Show content of my profile page*/
		navigationToolbar.goToMyProfile();

		/*Step number: 2
		 *Step Name: Step 2: Edit Profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
		 *Input Data: 

		 *Expected Outcome: 
			Edit Profile page is displayed*/

		info("edit about me");
		$(ELEMENT_EDIT_MY_PROFILE_LINK).click();

		/*Step number: 3
		 *Step Name: Step 3: Edit About me
		 *Step Description: 
			- Edit About me field with some values
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable. 
			- A user profile activity is updated in activity stream
			- A comment is added in activity stream and Recent Activities: About me has been updated.*/ 
		userProfilePage.updateAboutMe(aboutMe);
		userProfilePage.saveCancelUpdateInfo(true);
		$(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1))).waitUntil(Condition.visible, Configuration.timeout);
		navigationToolbar.goToMyActivities();
		$(byId("UIUserActivityStreamPortlet")).find(byText(activity1)).parent().parent().parent().parent().find(byText(comment2)).waitUntil(Condition.visible,Configuration.timeout);
		manageLogInOut.signIn(DATA_USER1, "gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);

}

	/**
	 *<li> Case ID:125308.</li>
	 *<li> Test Case Name: Update user profile activity after editing contact information.</li>
	 *<li> Case ID:126691.</li>
	 *<li> Test Case Name: Display the content of the user profile activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_06_UpdateUserProfileActivityAfterEditingContactInformation() {
		info("Test 6: Update user profile activity after editing contact information");
		String jobTitle = "job"+getRandomNumber();

		String url1 = getRandomString()+".com";
		String url2 = getRandomString()+".net";

		String gender = "male";

		String im1 = "skype";
		String im2 = "gtalk";
		String im3 = "other";
		String im4 = "yahoo";
		String im5 = "msn";

		String phone1 = getRandomNumber()+getRandomNumber();
		String phone2 = getRandomNumber()+getRandomNumber();
		String phone3 = getRandomNumber()+getRandomNumber();

		String email = "testemail" + getRandomString()+"@gmail.com";

		/*Create data test*/
		String username1 = "usernametest" + getRandomString();
		String email1 = username1+"@gmail.com";
		String password="1234567";
		info("Add new user");
		navigationToolbar.goToAddUser();
		userAddManagement.addUser(username1, password, email1, username1, username1);
		manageLogInOut.signIn(username1,password);

		
		String comment1= "Contact information has been updated.";
		String comment2 = "Contact information has been updated.";
		String activity1 = username1 +" "+username1;

		/*Step Number: 1
		 *Step Name: Step 1: Go to my profile page
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			Show content of my profile page*/
		navigationToolbar.goToMyProfile();
		info("goto edit profile page");

		/*Step number: 2
		 *Step Name: Step 2: Edit Profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
		 *Input Data: 

		 *Expected Outcome: 
			Edit Profile page is displayed*/
		$(ELEMENT_EDIT_MY_PROFILE_LINK).click();

		/*Step number: 3
		 *Step Name: Step 3: Edit Contact Information
		 *Step Description: 
			- Edit contact information (First Name/Last Name/Email....)
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable. 
			- A comment is added in activity stream and Recent Activities: Contact information has been updated.
			- Activity content is updated with new value
			- A user profile activity is created in the activity stream with the following information:* User's job title* User's email* User's phone* User's gender* User's avatar*/ 

		info("edit contact info");
		info("edit info");
		userProfilePage.updateBasicInformation(null, null, email);
		userProfilePage.updateGenderJob(jobTitle,gender);

		userProfilePage.updateIms("skype",im1,0);
		userProfilePage.updateIms("gtalk", im2,1);
		userProfilePage.updateIms("other", im3,2);
		userProfilePage.updateIms("yahoo", im4,3);
		userProfilePage.updateIms("msn", im5,4);

		userProfilePage.updatePhone("work", phone1,0);
		userProfilePage.updatePhone("home",phone2,1);
		userProfilePage.updatePhone("other", phone3,2);

		userProfilePage.updateUrl(url1,"1");
		userProfilePage.updateUrl(url2,"2");

		userProfilePage.saveCancelUpdateInfo(true);

		$(byXpath(ELEMENT_EMAIL_INFO.replace("${email}",email))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_GENDER_INFO.replace("${gender}","Male"))).waitUntil(Condition.visible, Configuration.timeout);

		$(byXpath(ELEMENT_PHONE_INFO.replace("${type}","Work").replace("${phone}", phone1))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_PHONE_INFO.replace("${type}","Home").replace("${phone}", phone2))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_PHONE_INFO.replace("${type}","Other").replace("${phone}", phone3))).waitUntil(Condition.visible, Configuration.timeout);

		$(byXpath(ELEMENT_IM_INFO.replace("${type}","Skype").replace("${im}", im1))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_IM_INFO.replace("${type}","Gtalk").replace("${im}", im2))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_IM_INFO.replace("${type}","Other").replace("${im}", im3))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_IM_INFO.replace("${type}","Yahoo").replace("${im}", im4))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_IM_INFO.replace("${type}","Msn").replace("${im}", im5))).waitUntil(Condition.visible, Configuration.timeout);

		$(byXpath(ELEMENT_URL_INFO.replace("${url}",url1))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_URL_INFO.replace("${url}",url2))).waitUntil(Condition.visible, Configuration.timeout);

		info("Test 3: Display the content of the user profile activity");

		$(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1))).waitUntil(Condition.visible, Configuration.timeout);
		navigationToolbar.goToMyActivities();
$(byId("UIUserActivityStreamPortlet")).find(byText(activity1)).parent().parent().parent().parent().find(byText(comment2)).waitUntil(Condition.visible,Configuration.timeout);
		manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);

	}


	/**
	 *<li> Case ID:125309.</li>
	 *<li> Test Case Name: Update user profile activity after editing experiences.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_UpdateUserProfileActivityAfterEditingExperiences() {
		info("Test 7: Update user profile activity after editing experiences");
		String organization = "organization"  + getRandomString();
		String jobTitle = "jobTitle" + getRandomString();
		String jobDetail = "jobDetail" + getRandomString();
		String skill = "skill" + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		String dEnd = getDate(-1, "MM/dd/yyyy");
		String actdStart = getDate(-7, "MMMM dd, yyyy");
		String actdEnd = getDate(-1, "MMMM dd, yyyy");

		/*Create data test*/
		String username1 = "usernameaqw" + getRandomString();
		String email1 = username1 +"@exoplatform.com";
		String activity1= username1 + " "+username1;
		String comment1 = "Experience has been updated.";
		String comment2 = "Experience has been updated.";
		String password="1234567";
		info("Add new user");
		navigationToolbar.goToAddUser();
		userAddManagement.addUser(username1, password, email1, username1, username1);
		manageLogInOut.signIn(username1, password);

		/*Step Number: 1
		 *Step Name: Step 1: Go to my profile page
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			Show content of my profile page*/

		/*Step number: 2
		 *Step Name: Step 2: Edit Profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
		 *Input Data: 

		 *Expected Outcome: 
			Edit Profile page is displayed*/
		navigationToolbar.goToMyProfile();
		$(ELEMENT_EDIT_MY_PROFILE_LINK).click();

		/*Step number: 3
		 *Step Name: Step 3 : Add experience
		 *Step Description: 
			- Click the + icon next to [Add an experience]
			- Add one experience (Organization, Job Title) 
			- click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable.
			- A comment is added in activity stream and Recent Activities: Experiences have been updated.
			- Activity content is updated with new experience*/ 
		userProfilePage.updateExperience(organization,jobTitle,jobDetail,skill,dStart,dEnd,false);
		userProfilePage.saveCancelUpdateInfo(true);
		$(byXpath(ELEMENT_COMPANY_INFO.replace("${company}",organization))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_POSITION_INFO.replace("${position}",jobTitle))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_SKILL_INFO.replace("${skill}",skill))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_STARTDATE_INFO.replace("${date}",actdStart))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1))).waitUntil(Condition.visible, Configuration.timeout);
		navigationToolbar.goToMyActivities();
		$(byId("UIUserActivityStreamPortlet")).find(byText(activity1)).parent().parent().parent().parent().find(byText(comment2)).waitUntil(Condition.visible,Configuration.timeout);
		manageLogInOut.signIn(DATA_USER1, "gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);

	}
}