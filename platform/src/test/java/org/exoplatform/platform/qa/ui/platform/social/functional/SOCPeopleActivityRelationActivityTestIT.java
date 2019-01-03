package org.exoplatform.platform.qa.ui.platform.social.functional;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.*;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("functional")
@Tag("social")
public class SOCPeopleActivityRelationActivityTestIT extends Base {
    NavigationToolbar navigationToolbar;

    NotificationsAdminSeting notificationsAdminSeting;

    ManageLogInOut manageLogInOut;

    UserAndGroupManagement userAndGroupManagement;
    ConnectionsManagement connectionsManagement;

    AddUsers addUsers;
    HomePagePlatform homePagePlatform;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        notificationsAdminSeting = new NotificationsAdminSeting(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        manageLogInOut.signInCas(DATA_USER1,DATA_PASS2);
    }
    /**
     *<li> Case ID:122758.</li>
     *<li> Test Case Name: Delete a Relation activity from activity stream by its user.</li>
     *<li> Pre-Condition: - A relation activity of user A is already shared</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test02_DeleteARelationActivityFromActivityStreamByItsUser() {

        info("Add new user");
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);
        String activity1 = "I'm now connected with";
        String comment1 = "I'm now connected with "+ username1+" "+username1;
        info("Click on Connections on the left panel");
        homePagePlatform.goToConnections();
        info("Access people list, invite an user");
        connectionsManagement.connectToAUser(username2);
        info("Invited user accept invitation");
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        info("Verify after accept");
        connectionsManagement.verifyConnection(username1, true);
        navigationToolbar.goToMyActivities();

        info("Test 2: Delete a Relation activity from activity stream by its user");
		/*Step Number: 1
		 *Step Name: Step 1: Connect to Intranet
		 *Step Description:
			- Connect to Intranet as user A
		 *Input Data:

		 *Expected Outcome:
			The Relation activity is displayed in the activity stream*/
        $(byXpath(ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));

		/*Step number: 2
		 *Step Name: Step 2: Show [remove] icon
		 *Step Description:
			- Move the mouse over the Relation activity
		 *Input Data:

		 *Expected Outcome:
			A (X) icon is displayed in the top right panel of the activity*/

		/*Step number: 3
		 *Step Name: Step 3: Click [remove] icon
		 *Step Description:
			- Click on the (X) icon
			- Click on OK button of confirmation message
		 *Input Data:

		 *Expected Outcome:
			The Relation activity is removed from the activity stream*/
        hpAct.deleteActivity(activity1);

    }
}