package org.exoplatform.platform.qa.ui.platform.social.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("functional")
@Tag("social")
public class SOCPeopleActivityUserProfileActivitiesTestIT extends Base {
    NavigationToolbar navigationToolbar;

    NotificationsAdminSeting notificationsAdminSeting;

    ManageLogInOut manageLogInOut;

    UserAndGroupManagement userAndGroupManagement;
    UserProfilePage userProfilePage;

    AddUsers addUsers;
    ActivityStream activityStream;
    HomePagePlatform homePagePlatform;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        notificationsAdminSeting = new NotificationsAdminSeting(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        userProfilePage = new UserProfilePage(this);
        activityStream = new ActivityStream(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
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
    public  void test01_UpdateUserProfileActivityAfterChangingAvatar() {
        info("Test 4: Update user profile activity after changing avatar");

        /*Create data test*/

        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, "123456", email1, username1, username1);
        manageLogInOut.signIn(username1, password);

        String activity1 = username1 + " "+username1;
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
        assert oldUrl.equals(newUrl);
        $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1))).waitUntil(Condition.visible, Configuration.timeout);
        navigationToolbar.goToMyActivities();
        $(byId("boxContainer")).find(byText(activity1)).parent().parent().parent().parent().find(byText(comment1)).waitUntil(Condition.visible,Configuration.timeout);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    /**
     * <li> Case ID:125307.</li>
     * <li> Test Case Name: Update user profile activity after editing About me.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test05_UpdateUserProfileActivityAfterEditingAboutMe() {
        info("Test 5: Update user profile activity after editing About me");
        String aboutMe = "aboutMe" + getRandomNumber();

        /*Create data test*/
        String username1 = "usernamea" + getRandomString();
        String password1 = "123456";
        String email1 = username1 + "@gmail.com";
        /* Create data test */
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password1, email1, username1, username1);
        info("goto My notification");
        manageLogInOut.signIn(username1, password1);
        String comment2 = "\"About me\" has been updated.";

        String activity1 = username1 + " " + username1;

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
        navigationToolbar.goToMyActivities();
        $(byId("boxContainer")).find(byText(activity1)).parent().parent().parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    /**
     * <li> Case ID:125309.</li>
     * <li> Test Case Name: Update user profile activity after editing experiences.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test07_UpdateUserProfileActivityAfterEditingExperiences() {
        info("Test 7: Update user profile activity after editing experiences");
        String organization = "organization" + getRandomString();
        String jobTitle = "jobTitle" + getRandomString();
        String jobDetail = "jobDetail" + getRandomString();
        String skill = "skill" + getRandomString();
        String dStart = getDate(-7, "MM/dd/yyyy");
        String dEnd = getDate(-1, "MM/dd/yyyy");
        String actdStart = getDate(-7, "MMMM dd, yyyy");
        String actdEnd = getDate(-1, "MMMM dd, yyyy");

        /*Create data test*/
        String username1 = "usernamea" + getRandomString();
        String comment1 = "Experience has been updated.";
        String password1 = "123456";
        String email1 = username1 + "@gmail.com";
        /* Create data test */
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password1, email1, username1, username1);
        info("goto My notification");
        manageLogInOut.signIn(username1, password1);

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
        userProfilePage.updateExperience(organization, jobTitle, jobDetail, skill, dStart, dEnd, false);
        userProfilePage.saveCancelUpdateInfo(true);
        $(byXpath(ELEMENT_COMPANY_INFO.replace("${company}", organization))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_POSITION_INFO.replace("${position}", jobTitle))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_JOB_DETAIL_INFO.replace("${description}", jobDetail))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_SKILL_INFO.replace("${skill}", skill))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_STARTDATE_INFO.replace("${date}", actdStart))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_ENDDATE_INFO.replace("${date}", actdEnd))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1))).waitUntil(Condition.visible, Configuration.timeout);

        navigationToolbar.goToMyActivities();
        $(byId("boxContainer")).find(byText(username1 + " " + username1)).parent().parent().parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    /**
     * <li> Case ID:125308.</li>
     * <li> Test Case Name: Update user profile activity after editing contact information.</li>
     * <li> Case ID:126691.</li>
     * <li> Test Case Name: Display the content of the user profile activity.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test06_UpdateUserProfileActivityAfterEditingContactInformation() {
        info("Test 6: Update user profile activity after editing contact information");
        String jobTitle = "Content1" + getRandomNumber();

        String url1 = getRandomString() + ".com";
        String url2 = getRandomString() + ".net";

        String gender = "male";

        String im1 = getRandomNumber() + getRandomNumber();
        String im2 = getRandomNumber() + getRandomNumber();
        String im3 = getRandomNumber() + getRandomNumber();
        String im4 = getRandomNumber() + getRandomNumber();
        String im5 = getRandomNumber() + getRandomNumber();

        String phone1 = getRandomNumber() + getRandomNumber();
        String phone2 = getRandomNumber() + getRandomNumber();
        String phone3 = getRandomNumber() + getRandomNumber();

        /*Create data test*/
        String username1 = "usernamea" + getRandomString();
        String password1 = "123456";
        String email1 = username1 + "@gmail.com";
        /* Create data test */
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password1, email1, username1, username1);
        manageLogInOut.signIn(username1, password1);

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
        userProfilePage.updateBasicInformation(null, null, email1);
        userProfilePage.updateGenderJob(gender, jobTitle);

        userProfilePage.updateIms("gtalk", im1, 0);
        userProfilePage.updateIms("msn", im2, 1);
        userProfilePage.updateIms("skype", im3, 2);
        userProfilePage.updateIms("yahoo", im4, 3);
        userProfilePage.updateIms("other", im5, 4);

        userProfilePage.updatePhone("work", phone1, 0);
        userProfilePage.updatePhone("home", phone2, 1);
        userProfilePage.updatePhone("other", phone3, 2);
        homePagePlatform.refreshUntil($(ELEMENT_CONTACT_SAVE_BUTTON), Condition.visible, 1000);
        userProfilePage.updateUrl(url1, "1");
        homePagePlatform.refreshUntil($(ELEMENT_CONTACT_SAVE_BUTTON), Condition.visible, 1000);
        userProfilePage.updateUrl(url2, "2");
        homePagePlatform.refreshUntil($(ELEMENT_CONTACT_SAVE_BUTTON), Condition.visible, 1000);
        userProfilePage.saveCancelUpdateInfo(true);

        $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_GENDER_INFO.replace("${gender}", "Male"))).waitUntil(Condition.visible, Configuration.timeout);

        $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Work").replace("${phone}", phone1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Home").replace("${phone}", phone2))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Other").replace("${phone}", phone3))).waitUntil(Condition.visible, Configuration.timeout);

        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Gtalk").replace("${im}", im1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Msn").replace("${im}", im2))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Skype").replace("${im}", im3))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Yahoo").replace("${im}", im4))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Other").replace("${im}", im5))).waitUntil(Condition.visible, Configuration.timeout);

        $(byXpath(ELEMENT_URL_INFO.replace("${url}", url1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_URL_INFO.replace("${url}", url2))).waitUntil(Condition.visible, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }


    /**
     * <li> Case ID:125308.</li>
     * <li> Test Case Name: Update user profile activity after editing contact information.</li>
     * <li> Case ID:126691.</li>
     * <li> Test Case Name: Display the content of the user profile activity.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test03_UpdateUserProfileActivityAfterEditingContactInformation() {
        info("Test 6: Update user profile activity after editing contact information");
        String jobTitle = "Content1" + getRandomNumber();

        String url1 = getRandomString() + ".com";
        String url2 = getRandomString() + ".net";

        String gender = "male";

        String im1 = getRandomNumber() + getRandomNumber();
        String im2 = getRandomNumber() + getRandomNumber();
        String im3 = getRandomNumber() + getRandomNumber();
        String im4 = getRandomNumber() + getRandomNumber();
        String im5 = getRandomNumber() + getRandomNumber();

        String phone1 = getRandomNumber() + getRandomNumber();
        String phone2 = getRandomNumber() + getRandomNumber();
        String phone3 = getRandomNumber() + getRandomNumber();

        /*Create data test*/
        String username1 = "usernamea" + getRandomString();
        String password1 = "123456";
        String email1 = username1 + "@gmail.com";
        /* Create data test */
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password1, email1, username1, username1);
        manageLogInOut.signIn(username1, password1);


        String comment1 = "Contact information has been updated.";

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
        userProfilePage.updateBasicInformation(null, null, email1);
        userProfilePage.updateGenderJob(gender, jobTitle);

        userProfilePage.updateIms("gtalk", im1, 0);
        userProfilePage.updateIms("msn", im2, 1);
        userProfilePage.updateIms("skype", im3, 2);
        userProfilePage.updateIms("yahoo", im4, 3);
        userProfilePage.updateIms("other", im5, 4);

        userProfilePage.updatePhone("work", phone1, 0);
        userProfilePage.updatePhone("home", phone2, 1);
        userProfilePage.updatePhone("other", phone3, 2);
        homePagePlatform.refreshUntil($(ELEMENT_CONTACT_SAVE_BUTTON), Condition.visible, 1000);
        userProfilePage.updateUrl(url1, "1");
        homePagePlatform.refreshUntil($(ELEMENT_CONTACT_SAVE_BUTTON), Condition.visible, 1000);
        userProfilePage.updateUrl(url2, "2");
        homePagePlatform.refreshUntil($(ELEMENT_CONTACT_SAVE_BUTTON), Condition.visible, 1000);
        userProfilePage.saveCancelUpdateInfo(true);

        $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_GENDER_INFO.replace("${gender}", "Male"))).waitUntil(Condition.visible, Configuration.timeout);

        $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Work").replace("${phone}", phone1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Home").replace("${phone}", phone2))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Other").replace("${phone}", phone3))).waitUntil(Condition.visible, Configuration.timeout);

        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Gtalk").replace("${im}", im1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Msn").replace("${im}", im2))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Skype").replace("${im}", im3))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Yahoo").replace("${im}", im4))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Other").replace("${im}", im5))).waitUntil(Condition.visible, Configuration.timeout);

        $(byXpath(ELEMENT_URL_INFO.replace("${url}", url1))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_URL_INFO.replace("${url}", url2))).waitUntil(Condition.visible, Configuration.timeout);

        info("Test 3: Display the content of the user profile activity");

        $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1))).waitUntil(Condition.visible, Configuration.timeout);
        navigationToolbar.goToMyActivities();
        $(byId("boxContainer")).find(byText(username1 + " " + username1)).parent().parent().parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

}