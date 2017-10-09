package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ACTIVITY_STREAM_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.*;

@Tag("sniff")
@Tag("social")
public class SOCHomePageTestIT extends Base {
    NavigationToolbar navigationToolbar;

    HomePagePlatform  homePagePlatform;

    ActivityStream    activityStream;

    ManageLogInOut manageLogInOut;

    SiteExplorerHome siteExplorerHome;
    CreateNewDocument createNewDocument;
    SpaceManagement spaceManagement;
    ConnectionsManagement connectionsManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        spaceManagement=new SpaceManagement(this);
        navigationToolbar = new NavigationToolbar(this);
        homePagePlatform = new HomePagePlatform(this);
        activityStream = new ActivityStream(this);
        manageLogInOut=new ManageLogInOut(this);
        siteExplorerHome=new SiteExplorerHome(this);
        createNewDocument=new CreateNewDocument(this);
        connectionsManagement=new ConnectionsManagement(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
            manageLogInOut.signOut();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    @AfterEach
    public void signout() {
        manageLogInOut.signOut();
    }
    /**
     *<li> Case ID:121915.</li>
     *<li> Test Case Name: Mention a user in activity composer.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     *Step Number: 1
     *Step Name: Step 1: Mentions on User Activity Stream
     *Step Description:
     - Login Intranet home
     - Hover the mouse over the name of user and select My Activity streams
     - Write something
     -Entering @ followed by the user name
     - Select name is wrapped in the input
     - Click Share button
     *Input Data:
     *Expected Outcome:
     - The suggestion list is hidden
     - In the activity stream, mentions are displayed as a link on "Firstname Lastname" to the user's activities page*/
    @Test
    public  void test04_MentionAUserInActivityComposer()  {
        info("Test 4: Mention a user in activity composer");
        String text = "text"+getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.mentionUserActivity(DATA_USER2,text);
        $(byText(text)).parent().parent().find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        $(byText(text)).parent().parent().find(ELEMENT_ICON_DELETE_ACTIVITY).click();
        $(ELEMENT_DELETE_POPUP_OK).click();
        // verify that the activity doesn't exist
        $(byText(text)).shouldNot(Condition.exist);
    }
    /**
     *<li> Case ID:121923.</li>
     *<li> Test Case Name: Share your status.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>

    *Step Number: 1
		 *Step Name: Share your status
		 *Step Description:
			- Log in
			- Go to Home page
			- Input into activity input field and click share
		 *Input Data:

		 *Expected Outcome:
			- The message will show in Activity Stream*/
    @Test
    public  void test05_ShareYourStatus() {
        info("Test 5: Share your status");

        String name = "name"+getRandomNumber();


        homePagePlatform.goToHomePage();
        activityStream.addActivity(name,"");
        $(byText(name)).should(Condition.exist);
        $(byText(name)).parent().parent().find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
        $(byText(name)).parent().parent().find(ELEMENT_ICON_DELETE_ACTIVITY).click();
        $(ELEMENT_DELETE_POPUP_OK).click();
        // verify that the activity doesn't exist
        $(byText(name)).shouldNot(Condition.exist);
    }
    /**
     *<li> Case ID:121924.</li>
     *<li> Test Case Name: Add a document.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
    Step Number: 1
		 *Step Name: - Go to Select File Dialog
		 *Step Description:
			- Log in
			- Goto Homepage
			- In Activity Composer click File (icon)
		 *Input Data:

		 *Expected Outcome:
			- Select File Dialog shows up*/

		/*Step number: 2
		 *Step Name: - Select document
		 *Step Description:
			- Browse the Select File Dialog to choose a document
			- Click Select
		 *Input Data:

		 *Expected Outcome:
			- A breadcrumb is displaying the current position of the user in the browsed drive.
			- the name of the file should be displayed under the activity input field.*/

		/*Step number: 3
		 *Step Name: - Share the document
		 *Step Description:
			- Click Share button
		 *Input Data:

		 *Expected Outcome:
			- Activity is added into activity stream*/
    @Test
    public  void test06_AddADocument() {
        info("Test 6: Add a document");

        String name = "name"+getRandomNumber();
        String content = "content"+getRandomNumber();
        info("add file");
        navigationToolbar.goToSiteExplorer();
        siteExplorerHome.goToAddNewContent();
        info("Create new file document");
        createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
        createNewDocument.addNewFile(name, content);
        createNewDocument.saveAndClose();
        homePagePlatform.goToHomePage();
        info("share file from activity stream");
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_LINK_TEXT_SELECT_FROM_EXISTING_UPLOAD.click();
        ELEMENT_TEXT_DOCUMENT_IN_DOC_ACTIVITY_POPUP.click();
        ELEMENT_SITE_MANAGEMENT_IN_DOC_ACTIVITY_POPUP.click();
        ELEMENT_POPUP_DOC_ACTIVITY.find(byText(name)).click();
        ELEMENT_BUTTON_ATTASH_FILE_IN_DOC_ACTIVITY_POPUP.click();
        $(byAttribute("data-original-title",name)).should(Condition.exist);
        $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
        info("delete data");
        navigationToolbar.goToSiteExplorer();
        siteExplorerHome.deleteData(name);

    }

    /**
     *<li> Case ID:121926.</li>
     *<li> Test Case Name: Add a link.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>

    Step Number: 1
		 *Step Name: - Add a link
		 *Step Description:
			- Log in
			- Goto Homepage
			- In Activity Composer click Add Link
			- Input URL and click Attach
		 *Input Data:

		 *Expected Outcome:
			- A input text field shows up to users to input the URL
			- The infomation of URL is shown in featured content parts

		click(hp.ELEMENT_PUBLICATION_ADDLINK);
		type(hp.ELEMENT_PUBLICATION_ADDLINK_INPUT, "http://www.ted.com", false);
		click(hp.ELEMENT_PUBLICATION_ADDLINK_ATTCHBTN);

		Step number: 2
		 *Step Name: - Share the document
		 *Step Description:
			- Click Share button
		 *Input Data:

		 *Expected Outcome:
			- Activity is added into activity stream*/
    @Test
    public  void test08_AddALink() {
        info("Test 8: Add a link");
        String textDes = "textDes"+getRandomNumber();
        String link = "http://www.google.fr";


        homePagePlatform.goToHomePage();
        activityStream.addActivity(textDes, link);
        $(byText(link)).should(Condition.exist);
        $(byText(textDes)).should(Condition.exist);
        $(byText(link)).parent()
                .parent()
                .parent()
                .parent()
                .parent()
                .find(byClassName(ELEMENT_DATE_ACTIVITY))
                .waitUntil(Condition.visible, Configuration.timeout)
                .hover();
        $(byText(link)).parent().parent().parent().parent().parent().find(ELEMENT_ICON_DELETE_ACTIVITY).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
        $(byText(link)).parent().parent().parent().parent().parent().waitUntil(Condition.disappear, Configuration.timeout);

    }

    /**
     *<li> Case ID:121927.</li>
     *<li> Test Case Name: Load previous activity page automatically.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
    Step Number: 1
		 *Step Name: - Create more than 1 page of activities (Default 20)
		 *Step Description:
			- Log in
			- Go to homepage and create more than 20 activities
		 *Input Data:

		 *Expected Outcome:
			- more than 20 activities are created successfully

		Step number: 2
		 *Step Name: - check the loading automaticallyprevious activity page
		 *Step Description:
			- log out andlog in again
			- go to home page
			- scroll to the bottom of the activity stream
		 *Input Data:

		 *Expected Outcome:
			-the first page of last activities is displayed
			- previous activities' pages are load automatically*/
    @Test
    public  void test09_LoadPreviousActivityPageAutomatically() {
        info("Test 9: Load previous activity page automatically");
       String textDesfirst="textDesfirst"+getRandomNumber();
        activityStream.addActivity(textDesfirst, "");
        refresh();
        for(int i=0;i<=15;i++){
            String act="act"+getRandomNumber();
            activityStream.addActivity(act, "");
            refresh();
        }
        executeJavaScript("window.scrollBy(0,5500)", "");
        homePagePlatform.loadMoreActivities();
        $(byText(textDesfirst)).should(Condition.exist);
        executeJavaScript("window.scrollBy(0,-5500)", "");
        for(int i=0;i<=15;i++){
            if($(byClassName(ELEMENT_DATE_ACTIVITY)).is(Condition.exist)){
                $(byClassName("author")).click();
                $(byClassName("author")).parent().find(ELEMENT_ICON_DELETE_ACTIVITY).click();
                ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
                ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.disappear,Configuration.timeout);
            }
        }
    }

    /**
     *<li> Case ID:121928.</li>
     *<li> Test Case Name: Check [All activities] filter.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    /*Step Number: 1
		 *Step Name: - Goto social homepage
		 *Step Description:
			- Log in
			- Goto homepage
		 *Input Data:

		 *Expected Outcome:
			- Home page is displayed
    Step number: 2
		 *Step Name: - Check [All activity] filter
		 *Step Description:
			- In the drop
			-down select box, select [All Activities]
		 *Input Data:

		 *Expected Outcome:
			- All activities are displayed in activity stream*/
    @Test
    public  void test10_CheckAllActivitiesFilter() {
        info("Test 10 Check [All activities] filter");

        String text="text"+getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(text,"");
        assertEquals("All Activities",$(byId("DisplayModesDropDown")).getText());
        activityStream.deleteactivity(text);

    }

    /**
     *<li> Case ID:121929.</li>
     *<li> Test Case Name: Check [My Spaces] filter.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    /*Step Number: 1
		 *Step Name: - Goto social homepage
		 *Step Description:
			- Log in
			- Goto homepage
		 *Input Data:

		 *Expected Outcome:
			- Home page is displayed
    Step number: 2
		 *Step Name: - Check [My Spaces] filter
		 *Step Description:
			- In the drop
			-down select box, select [My Space]
		 *Input Data:

		 *Expected Outcome:
			- It shows only activities created in space where the user is a member*/
    @Test
    public  void test11_CheckMySpacesFilter() {
        info("Test 11 Check [My Spaces] filter");

        String space = "space" + getRandomNumber();

        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        homePagePlatform.goToHomePage();
        $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).click();
        $(ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE_OPTION).click();
        $(byText(space)).should(Condition.exist);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space,false);

    }
    /**
     *<li> Case ID:121930.</li>
     *<li> Test Case Name: Check [Connections] filter.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    /*Step Number: 1
		 *Step Name: - Goto intranet homepage
		 *Step Description:
			- Log in
			- Goto intranet homepage
		 *Input Data:

		 *Expected Outcome:
			- Home page is displayed
    Step number: 2
		 *Step Name: - Check [Connections] filter
		 *Step Description:
			- In the drop
			-down select box, select [Connnection]
		 *Input Data:

		 *Expected Outcome:
			shows only activities created by the user's connections and by the user himself, outside a space*/
    @Test
    public  void test12_CheckConnectionsFilter() {
        info("Test 12 Check [Connections] filter");
        String text="text"+getRandomNumber();
        activityStream.addActivity(text,"");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        manageLogInOut.signIn(DATA_USER2,DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).click();
        $(ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION_OPTION).click();
        executeJavaScript("window.scrollBy(0,-1500)", "");
        $(byText(text)).should(Condition.exist);
        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        activityStream.deleteactivity(text);

    }

    /**
     *<li> Case ID:121931.</li>
     *<li> Test Case Name: Check [My Activities] filter.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    /*Step Number: 1
		 *Step Name: - Go to intranethomepage
		 *Step Description:
			- Log in
			- Go to intranet homepage
		 *Input Data:

		 *Expected Outcome:
			- Home page is displayed*
    *Step number: 2
		 *Step Name: - check [My Activities] filter
		 *Step Description:
			- In the drop
			-down select box, select [My Activities]
		 *Input Data:

		 *Expected Outcome:
			shows only activities where the user has been @mentionned, the user has commented or liked, and the user's activities (inside and outside a space)*/

    @Test
    public  void test13_CheckMyActivitiesFilter() {
        info("Test 13 Check [My Activities] filter");
        String space = "space" + getRandomNumber();
        String text="text"+getRandomNumber();
        String text1="text"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        manageLogInOut.signIn(DATA_USER2,DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(text,"");
        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        activityStream.addActivity(text1,"");
        homePagePlatform.goToMySpaces();
        homePagePlatform.goToHomePage();
        $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).click();
        $(ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION).click();
        ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(space)).shouldNot(Condition.exist);
        $(byText(text)).shouldNot(Condition.exist);
        $(byText(text1)).should(Condition.exist);
        activityStream.deleteactivity(text1);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space,false);
        manageLogInOut.signIn(DATA_USER2,DATA_PASS);
        activityStream.deleteactivity(text);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER1);

    }
    /**
     *<li> Case ID:121932.</li>
     *<li> Test Case Name: Check activities order.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     * Step Number: 1
     *Step Name: - Check order of activities
     *Step Description:
     - Log in and go to homepage
     - Check order of activity
     *Input Data:

     *Expected Outcome:
     - Homepage is displayed
     - the order of activities is based on activity's last action date.
     The last action date is the latest of:
     1. The publication date
     2. The date of the last comment posted*/

    @Test
    public  void test14_CheckActivitiesOrder() {
        info("Test 14 Check activities order");
        String text="text"+getRandomNumber();
        String text1="text1"+getRandomNumber();
        String comment="comment"+getRandomNumber();
        activityStream.addActivity(text,"");
        refresh();
        activityStream.addActivity(text1,"");
        ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(text1)).should(Condition.exist);
        ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(text)).should(Condition.exist);
        activityStream.commentActivity(text,comment);
        refresh();
        ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(text)).should(Condition.exist);
        activityStream.deleteactivity(text);
        activityStream.deleteactivity(text1);
    }

    /**
     *<li> Case ID:121934.</li>
     *<li> Test Case Name: Check Layout of Activities.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
    Step Number: 1
		 *Step Name: Check layout of activity
		 *Step Description:
			- log in and goto intranet hompage
			- Select an activity to check its layout
		 *Input Data:

		 *Expected Outcome:
			An activity is made out of different parts:(see attached)
			1.the author
			2.the author's avatar
			3.the space (optional)
			4.the type (optional)
			5.the activity message
			6.the featured content (optional)
			7.the Action bar (Comment and Like links + custom actions)
			8.the like section (optional)
			9.the comment section (optional)*/
    @Test
    public  void test16_CheckLayoutOfActivities() {
        info("Test 16 Check Layout of Activities");

        String name =""+getRandomNumber();


        homePagePlatform.goToHomePage();
        activityStream.addActivity(name, "");
        $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
        $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(ELEMENT_PUBLICATION_FIRSTPOST_AUTHORAVATAR).should(Condition.exist);
        $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(ELEMENT_PUBLICATION_FIRSTPOST_ACTIVITYTEXT).should(Condition.exist);
        $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(ELEMENT_ICONCOMMENT).should(Condition.exist);
        $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(ELEMENT_ICONLIKE).should(Condition.exist);
        activityStream.deleteactivity(name);
    }

    /**
     *<li> Case ID:121935.</li>
     *<li> Test Case Name: View Comments.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
   Step Number: 1
		 *Step Name: - Check view of comment
		 *Step Description:
			- goto home page
			- select an activity with more than 2 comments on it (fx: 10 comments)
			- click on the message "View all 10 comments"
		 *Input Data:

		 *Expected Outcome:
			- onlythe latest (most recently posted) two comments are displayed below the activity.
			- "View all 10 comments" message is shown
			- all comments is displayed, in the time order (oldest at the top)*/
    @Test
    public  void test17_ViewComments() {
        info("Test 17 View Comments");

        String name = "name"+getRandomNumber();

        activityStream.addActivity(name, "");

        for(int i=0;i<=9;i++){
            String comment="comment"+getRandomNumber();
            activityStream.commentActivity(name,comment);
            refresh();
            executeJavaScript("window.scrollBy(0,-1500)", "");
        }
        String text="text"+getRandomNumber();
        String text1="text1"+getRandomNumber();
        activityStream.commentActivity(name,text);
        refresh();
        executeJavaScript("window.scrollBy(0,-1500)", "");
        activityStream.commentActivity(name,text1);
        refresh();
        info("Verify that only second last and last comment are shown");
        $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(byText(text)).should(Condition.exist);
        $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(byText(text1)).should(Condition.exist);
        info("Verify that view all comment links is shown and clickable on it");
        String id = $(byText(name)).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("UIActivityLoader")[1];
        $(byXpath(ELEMENT_LINK_TEXT_VIEW_ALL_COMMENTS.replace("{id}",id))).click();
        info("Verify that all comments is shown");
        assertEquals(13,$(byId(ELEMENT_LIST_ALL_COMMENNTS.replace("{id}",id))).findAll(byClassName("commentItem")).size());
        executeJavaScript("window.scrollBy(0,-1500)", "");
        activityStream.deleteactivity(name);

    }

    /**
     *<li> Case ID:121936.</li>
     *<li> Test Case Name: Mention a user in comment.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     * @throws AWTException
     Step Number: 1
		 *Step Name: Add comment with mention
		 *Step Description:
			- Select the activity that has some comments
			- input the comment with mention and click comment
		 *Input Data:

		 *Expected Outcome:
			- Input text field is displayed in activity, click comment button to show comment textbox
			- Comment will be added into comment section of activity*/
    @Test
    public  void test18_MentionAUserInComment() throws AWTException {
        info("Test 18 Mention a user in comment");
        String name = "name"+getRandomNumber();
        String text = "text"+getRandomNumber();

        homePagePlatform.goToHomePage();
        activityStream.addActivity(name,null);
        activityStream.addCommentWithMentionUser(name,DATA_USER2,text);
        activityStream.deleteactivity(name);
    }
}