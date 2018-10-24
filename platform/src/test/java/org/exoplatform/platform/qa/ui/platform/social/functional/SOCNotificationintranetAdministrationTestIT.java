package org.exoplatform.platform.qa.ui.platform.social.functional;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("functional")
@Tag("social")
public class SOCNotificationintranetAdministrationTestIT extends Base {
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
        notificationsAdminSeting = new NotificationsAdminSeting(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    /**
     * <li> Case ID:125059.</li>
     * <li> Test Case Name: Check second section: Email Notifications Sender.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Access notification Administration
     * Step Description:
     * - Login as admin
     * - Move mouse over:Administration > Portal > Notifications
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Notification Administration page is displayed
     * Step number: 2
     * Step Name: Step 2: Check the 2nd section
     * Step Description:
     * - Check the 2nd section at the bottom of the page: Email Notifications Sender
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Notification sender is used to define the From field of notification email: Name and Address
     */
    @Test
    public void test01_CheckSecondSectionEmailNotificationsSender() {
        info("Test 1: Check second section: Email Notifications Sender");

        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible, Configuration.timeout);
        info("Verify that Notification sender is used to define the From field of notification email: Name and Address");
        $(ELEMENT_ADMIN_NOTIFICATION_SENDER_NAME).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_ADMIN_NOTIFICATION_SENDER_ADDRESS).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * <li> Case ID:125060.</li>
     * <li> Test Case Name: Check the first section: Notification types.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Access notification Administration
     * Step Description:
     * - Login as admin
     * - Move mouse over:Administration > Portal > Notifications
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Notification Administration page is displayed
     * Step number: 2
     * Step Name: Step 2: Check the first section
     * Step Description:
     * - Check the first section in page
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The second section is:
     * Notification Types allows to enable/disable globally all the notification types.
     */
    @Test
    public void test02_CheckTheFirstSectionNotificationTypes() {
        info("Test 2: Check the first section: Notification types");
        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible, Configuration.timeout);
        info("Verify that Notification Types allows to enable/disable globally all the notification types. ");
        $(ELEMENT_ENABLE_NOTIFICATION_GRID).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * <li> Case ID:125062.</li>
     * <li> Test Case Name: Access notification Administration.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Access notification Administration
     * Step Description:
     * - Login as admin (belongs to the group: *:/platform/administrators)
     * - Move mouse over:Administration > Portal > Notifications
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Notification Administration page is displayed
     */
    @Test
    public void test03_AccessNotificationAdministration() {
        info("Test 3: Access notification Administration");

        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * <li> Case ID:125063.</li>
     * <li> Test Case Name: Check deactivate notification from Administration.</li>
     * <li> Pre-Condition: - The user is admin
     * - New User notifications is selected in the User Settings</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1 : Deactivate notification type
     * Step Description:
     * - Login with an admin account
     * - Go to Administration > Portal > Notifications
     * - Go to New User row
     * - Click edit and untick Intranet Notification
     * - Click save
     * Input Data:
     * <p>
     * Expected Outcome:
     * - New user Intranet notifications is disabled
     * Step number: 2
     * Step Name: Step 2 : Go to User settings
     * Step Description:
     * - Go to User menu and [My Notifications]
     * - Check the notification row : New User
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The label "See on My intranet" is not displayed in the row
     * Step number: 3
     * Step Name: Step 3 : Edit and check
     * Step Description:
     * - Click edit
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The label "See on My intranet" is not available in the edit section
     */
    @Test
    public void test04_CheckDeactivateNotificationFromAdministration() {
        info("Test 4: Check deactivate notification from Administration");
        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible, Configuration.timeout);
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.NewUser_intranet);
        navigationToolbar.goToMyNotifications();
        info("The label See on My intranet is not displayed in the row");
        $(ELEMENT_NEWUSER_ICON_INTRANET_NOTIFICATION).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_EDIT_NEWUSER_ICON).click();
        $(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

    }
}