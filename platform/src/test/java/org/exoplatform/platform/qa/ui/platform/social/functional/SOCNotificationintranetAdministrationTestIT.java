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
        info("Check second section: Email Notifications Sender");
        info("Go to Notification Administration");
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible, Configuration.timeout);
        info("Verify that Notification sender is used to define the From field of notification email: Name and Address");
        $(ELEMENT_ADMIN_NOTIFICATION_SENDER_NAME).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_ADMIN_NOTIFICATION_SENDER_ADDRESS).waitUntil(Condition.visible, Configuration.timeout);
        info("Verify that Notification Types allows to enable/disable globally all the notification types. ");
        $(ELEMENT_ENABLE_NOTIFICATION_GRID).waitUntil(Condition.visible, Configuration.timeout);
        info("Check deactivate notification from Administration");
        notificationsAdminSeting.disableNotification(NotificationsAdminSeting.notificationType.NewUser_intranet);
        navigationToolbar.goToMyNotifications();
        info("The label See on My intranet is not displayed in the row");
        $(ELEMENT_NEWUSER_ICON_INTRANET_NOTIFICATION).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_EDIT_NEWUSER_ICON).click();
        $(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        navigationToolbar.goToAdminNotifications();
        info("Notifications Administration page is displayed");
        $(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE).waitUntil(Condition.visible, Configuration.timeout);
        notificationsAdminSeting.enableNotification(NotificationsAdminSeting.notificationType.NewUser_intranet);

    }


}