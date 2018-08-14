package org.exoplatform.platform.qa.ui.platform.social.functional;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ChangeLanguages;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.USER_ROOT;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SOCNotificationAdministrationTestIT extends Base {
NavigationToolbar navigationToolbar;
NotificationsAdminSeting notificationsAdminSeting;
ManageLogInOut manageLogInOut;
UserAndGroupManagement userAndGroupManagement;
AddUsers addUsers;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        notificationsAdminSeting =new NotificationsAdminSeting(this);
        userAndGroupManagement=new UserAndGroupManagement(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }
    /**
     * <li> Case ID:117480.</li>
     * <li> Test Case Name: Check global settings of the second section "Notification Types".</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
    Step Number: 1
    *Step Name: Step 1: Access notification Administration
    *Step Description:
        - Login as admin
        - Move mouse over:Administration > Portal > Notifications
    *Input Data:

    *Expected Outcome:
        - Notifications Administration page is displayed*/
      /*Step number: 2
    *Step Name: Step 2: Change settings
    *Step Description:
        - Disable and enable some notification types.For example:
        disable "Someone joins the social intranet" and enable "Someone @mention me"
    *Input Data:

    *Expected Outcome:
        - The change is saved.
       Step number: 3
    *Step Name: Step 3: Check settings by other user
    *Step Description:
        - Login as other user
        - Select My Connections from the user menu to go to Notification Settings page.
        - Check settings in Notification Admin
    *Input Data:

    *Expected Outcome:
        - The change in Notification admin at step 2 is applied for other user
        - Notification type "Someone joins the social intranet" is not displayed,
        "Someone @mention me" is displayed*/
    @Test
    public void test01_CheckGlobalSettingsOfTheSecondSectionNotificationTypes() {
        info("Test 1: Check global settings of the second section Notification Types");
        String username = "username" + getRandomString();
        String email = username + "@test.com";
        String password="123456";
        String username1 = "username" + getRandomString();
        String email1 = username1 + "@test.com";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.addUserAdmin(username1,"*");
        manageLogInOut.signIn(username1,password);
        navigationToolbar.goToAddUser();
        addUsers.addUser(username, password, email, username, username);
        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible,Configuration.timeout);
        info("Change setting.Disable New User intranet");
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.NewUser_intranet);
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.NewUser_email);
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.NewUser_mobile);
        manageLogInOut.signIn(username, password);
        info("Go to My notification");
        navigationToolbar.goToMyNotifications();
        info("Verify that new user and mention plugin is not displayed");
        $(ELEMENT_MY_NOTIFICATION_NEW_USER_PLUGIN).shouldNotBe(Condition.visible);
        manageLogInOut.signIn(username1, password);
        info("Go to Admin notification");
        navigationToolbar.goToAdminNotifications();
        info("Change setting.Enable New User intranet");
        notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.NewUser_intranet);
        notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.NewUser_email);
        manageLogInOut.signIn(username, password);
        info("Go to My notification");
        navigationToolbar.goToMyNotifications();
        info("Verify that New user and Mention plugin is shown");
        $(ELEMENT_MY_NOTIFICATION_NEW_USER_PLUGIN).waitUntil(Condition.visible,Configuration.timeout);
        $(ELEMENT_MY_NOTIFICATION_MENTION_PLUGIN).waitUntil(Condition.visible,Configuration.timeout);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username);
        addUsers.deleteUser(username1);
    }

    /**
     *<li> Case ID:117512.</li>
     *<li> Test Case Name: Check Notification types table.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
    Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description:
			- Login as admin.
			- Move mouse over:Administration > Portal > Notifications.
		*Input Data:

		*Expected Outcome:
			- Notifications Administration page is displayed
    *Step number: 2
		*Step Name: Step 2: Check Notification Types table
		*Step Description:
			- Check Notification Types table
		*Input Data:

		*Expected Outcome:
			-A table list all currently loaded notification plugins. Columns are :
			+ Notifications: the name of the notificaiton plugin
			+ Title: the title of the notificaiton that will be shown in the notification's settings
			and in the email,
			+ Enable: the actions possible on this plugin*/
    @Test
    public  void test02_CheckNotificationTypesTable() {
        info("Test 2: Check Notification types table");
        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible,Configuration.timeout);
        info("Verify that Notificaiton column is shown");
        $(ELEMENT_NOTIFICATION_GRID_TITLE).waitUntil(Condition.visible,Configuration.timeout);
        info("Verify that Title column is shown");
        $(ELEMENT_TITLE_NOTIFICATION_GRID).waitUntil(Condition.visible,Configuration.timeout);
        info("Verify that Enable column is shown");
        $(ELEMENT_ENABLE_NOTIFICATION_GRID).waitUntil(Condition.visible,Configuration.timeout);

    }
    /**
     *<li> Case ID:117552.</li>
     *<li> Test Case Name: Check [Address] field when input invalid value.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description:
			- Login as admin.
			- Move mouse over:Administration > Portal >Notifications.
		*Input Data:

		*Expected Outcome:
			- The Notifications Administration page is displayed.
    *Step number: 2
		*Step Name: Step 2: Check [Address] field
		*Step Description:
			- Check [Address] field when input invalid value
		*Input Data:

		*Expected Outcome:
			- Show warning message about invalid email:
			 "Cannot update the sender information: empty value or the format is not correct."*/
    @Test
    public  void test03_CheckAddressFieldWhenInputInvalidValue() {
        info("Test 3: Check [Address] field when input invalid value");
        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);
        String invalid_emal=getRandomString();
        notificationsAdminSeting.changeNotificationSender("",invalid_emal);
        notificationsAdminSeting.verifyFailChangeNotificationSender();
    }

    /**
     *<li> Case ID:117563.</li>
     *<li> Test Case Name: Uncheck one notification type.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
    Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description:
			- Login as admin.
			- Move mouse over:Administration > Portal >Notifications.
		*Input Data:

		*Expected Outcome:
			- The Notifications Administration page is displayed
    Step number: 2
		*Step Name: Step 2: Uncheck one notification type.
		*Step Description:
			- In Disable column, s
			elect Edit icon on any item in Notification Types table,
			then untick two checkboxes: Email Notifications and Intranet Notifications.
			- Click Save button.
		*Input Data:

		*Expected Outcome:
			- One text "No notifications" is shown.
			- No new notification of this type is sent anymore.
    *Step number: 3
		*Step Name: Step 3: Check the Notification Settings
		*Step Description:
			- Log in as a user.
			- Select My Notifications from the User menu to open the Notification Settings page.
		*Input Data:

		*Expected Outcome:
			- The correspondingrow disappears from all users'Notification Settings screen.*/
    @Test
    public  void test04_UncheckOneNotificationType() {
        info("Test 4: Uncheck one notification type");
        String username = "username" + getRandomString();
        String email = username+"@gmail.com";
        String password="123456";
        String username1 = "username" + getRandomString();
        String email1 = username1 + "@test.com";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.addUserAdmin(username1,"*");
        manageLogInOut.signIn(username1,password);
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username, password, email, username, username);
        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);
        info("Change setting.Disable Activity comment intranet");
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.AS_Comment_email);
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.AS_Comment_intranet);
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.AS_Comment_mobile);
        info("Change setting.Disable Space Join request intranet");
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.Space_Join_email);
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.Space_Join_intranet);
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.Space_Join_mobile);
        notificationsAdminSeting.veriftyNotificationTypeDisable(NotificationsAdminSeting.notiMode.AS_Comment);
        notificationsAdminSeting.veriftyNotificationTypeDisable(NotificationsAdminSeting.notiMode.Space_Join);
        manageLogInOut.signIn(username,password);
        info("Go to My notification");
        navigationToolbar.goToMyNotifications();
        info("Verify that new user and mention plugin is not displayed");
        waitForElementNotPresent(ELEMENT_MY_NOTIFICATION_SPACE_REQJOIN_PLUGIN);
        waitForElementNotPresent(ELEMENT_MY_NOTIFICATION_ACTIVITY_COMMENT_PLUGIN);
        manageLogInOut.signIn(username1,password);
        info("Go to Admin notification");
        navigationToolbar.goToAdminNotifications();
        info("Change setting.Enable Space join intranet");
        notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.AS_Comment_email);
        notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.AS_Comment_intranet);
        info("Change setting.Enable Activity comment intranet");
        notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.Space_Join_email);
        notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.Space_Join_intranet);
        manageLogInOut.signIn(username,password);
        info("Go to My notification");
        navigationToolbar.goToMyNotifications();
        info("Verify that Space join and Activity comment plugin is shown");
        waitForAndGetElement(ELEMENT_MY_NOTIFICATION_SPACE_REQJOIN_PLUGIN);
        waitForAndGetElement(ELEMENT_MY_NOTIFICATION_ACTIVITY_COMMENT_PLUGIN);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username);
        addUsers.deleteUser(username1);

    }
    /**
     *<li> Case ID:117577.</li>
     *<li> Test Case Name: Check [Save] button of Notification Sender section.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
    *Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description:
			- Login as admin
			- Move mouse over:Administration > Portal > Notifications.
		*Input Data:

		*Expected Outcome:
			- The Notifications Administration page is displayed.
    *Step number: 2
		*Step Name: Step 2: Check [Save] button
		*Step Description:
			- Input valid value into [Name] and [Address] field: Name is "Test" and Address is: "test@exoplatform.com" for ex
			- Click [Save] button.
		*Input Data:

		*Expected Outcome:
			- A feedback message is displayed:Notifications will now be sent to your users from "$SENDER_NAME"<$SENDER_ADDRESS>where $SENDER_NAME = Test and <$SENDER_ADDRESS> = test@exoplatform.com*/

    @Test
    public  void test05_CheckSaveButtonOfNotificationSenderSection() {
        info("Test 5: Check [Save] button of Notification Sender section");

        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);
        String name ="Test";
        String address="test@exoplatform.com";
        notificationsAdminSeting.changeNotificationSender(name,address);
        notificationsAdminSeting.verifySuccessChangeNotificationSender(name,address);
    }
}