package org.exoplatform.platform.qa.ui.platform.wiki.functional;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_WIKI_SEARCH_FIELD;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import java.util.ArrayList;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

/**
 *<li> Case ID:139356.</li>
 *<li> Test Case Name: Search when the keyword is matched.</li>
 *<li> Pre-Condition: </li>
 *<li> Post-Condition: </li>
 **/
@Tag("functional")
@Tag("wiki")
public class WikiSearchTestIT  extends Base {

    NavigationToolbar      navigationToolbar;

    ManageLogInOut         manageLogInOut;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    WikiHomePage           wikiHomePage;

    WikiManagement         wikiManagement;

    RichTextEditor         richTextEditor;

    HomePagePlatform       homePagePlatform;

    WikiValidattions       wikiValidattions;

    WikiPermission         wikiPermission;

    ArrayList<String>      arrayPage;

    UserAddManagement userAddManagement;

    WikiSearch        wikiSearch;

    UserAndGroupManagement userAndGroupManagement;

    SpaceSettingManagement spaceSettingManagement;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        richTextEditor = new RichTextEditor(this);
        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiValidattions = new WikiValidattions(this);
        wikiManagement = new WikiManagement(this);
        wikiSearch = new WikiSearch(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        wikiPermission = new WikiPermission(this);
        userAddManagement = new UserAddManagement(this);
        arrayPage = new ArrayList<String>();
        spaceSettingManagement = new SpaceSettingManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas("root", "gtn");
    }


/**
    Step Number: 1
            *Step Name: -
            *Step Description: Step 1: Create pages
 *Input Data:
            - Go to Add Page â†’Blank Page (or From Template)
 - Add values in required fields
 - Click Save
 *Expected Outcome:
            - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
 - New pages is created successful : Step number: 2
 *Step Name: -
 *Step Description:
 Step 2: Do search
 *Input Data:
 - Put keyword in the search box
 - Press Enter
 *Expected Outcome:
 The results are matched with the query are listed: Step number: 3
 *Step Name: -
 *Step Description:
 Step 3: View content of result
 *Input Data:
 - Click on link of one result
 *Expected Outcome:
 The content of page is displayed

 */

    @Test
    public  void test01_SearchWhenTheKeywordIsMatched() {
        info("Test 1: Search when the keyword is matched");
        info("Create a wiki page");
        String title = "title" +getRandomNumber();
        String content = "content" +getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Do Search");
        wikiSearch.quickSeach(title);
        info("Verify that the page is shown in searched results");
        wikiValidattions.verifySearchResults(title);
        info("View content of the page result");
        wikiSearch.viewContentOfSearchedPage(title, content);
        arrayPage.add(title);
        info ("Delete a wiki page");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);
    }

    /**
     *<li> Case ID:139357.</li>
     *<li> Test Case Name: Search when the keyword is matched.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>

     *Step Number: 1
     *Step Name: -
     *Step Description:
     Step 1: Create pages
     *Input Data:
     - Go to Add Page â†’Blank Page (or From Template)
     - Add values in required fields
     - Click Save
     *Expected Outcome:
     - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     - New pages is created successful
     Step number: 2
     *Step Name: -
     *Step Description:
     Step 2: Open Advanced search form
     *Input Data:
     - Put keyword in the search box
     - Press Enter
     *Expected Outcome:
     The search result screen appears

     Step number: 3
     *Step Name: -
     *Step Description:
     Step 3: Do advanced search
     *Input Data:
     - Enter keyword in the Search field
     - Select space in the Space field
     - Press Enter
     *Expected Outcome:
     The results are matched with the query are listed

     Step number: 4
     *Step Name: -
     *Step Description:
     Step 4: View content of result
     *Input Data:
     - Click on link of page
     *Expected Outcome:
     Content of page are shown*/

    @Test
    public  void test02_SearchWhenTheKeywordIsMatched() {
        info("Test 2: Search when the keyword is matched");
        info("Create a wiki page");
        String title = "title" +getRandomNumber();
        String content = "content" +getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Create a space 1: moving space");
        String space1 = "space1" +getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space1,space1,6000);
        info("Create page A");
        String pageC = "pageC" +getRandomNumber();
        String pageContent = "pageContent" +getRandomNumber();
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageC,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageC);
        info("Do Search");
        homePagePlatform.goToWiki();
        wikiSearch.quickSeach(title);
        info("Verify that the page is shown in searched results");
        wikiValidattions.verifySearchResults(title);
        info("Go to Advanced search");
        wikiSearch.advancedSearch(pageC,space1);
        info("Verify that the page is shown in searched results");
        wikiValidattions.verifySearchResults(title);
        info("View content of the page result");
        wikiSearch.viewContentOfSearchedPage(pageC,pageContent);
        info ("Delete a wiki page");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     *<li> Case ID:139358.</li>
     *<li> Test Case Name: Search when the keyword is not matched.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li> Step Number: 1
     *Step Name: -
     *Step Description:
     Step 1: Create pages
     *Input Data:
     - Go to Add Page â†’Blank Page (or From Template)
     - Add values in required fields
     - Click Save
     *Expected Outcome:
     - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     - New pages is created successful
     Step number: 2
     *Step Name: -
     *Step Description:
     Step 2: Do search
     *Input Data:
     - Put keyword in the search box
     - Press Enter
     *Expected Outcome:
     Show message alert that no result found*/
    @Test
    public  void test03_SearchWhenTheKeywordIsNotMatched() {
        info("Test 3: Search when the keyword is not matched");

        info("Create a wiki page");
        String title = "title" +getRandomNumber();
        String content = "content" +getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        arrayPage.add(title);
        info("Do Search");
        String title1 = "title1" +getRandomNumber();
        wikiSearch.quickSeach(title1);
        info("Verify that the searched results is empty");
        wikiValidattions.verifyEmptySearchResults();

        info ("Delete page wiki");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);
    }

    /**
     *<li> Case ID:139359.</li>
     *<li> Test Case Name: Search when the keyword is not matched.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li> Step Number: 1
     *Step Name: -
     *Step Description:
     Step 1: Create pages
     *Input Data:
     - Go to Add Page â†’Blank Page (or From Template)
     - Add values in required fields
     - Click Save
     *Expected Outcome:
     - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     - New pages is created successful
     Step number: 2
     *Step Name: -
     *Step Description:
     Step 2: Open Advanced search form
     *Input Data:
     - Put keyword in the search box
     - Press Enter
     *Expected Outcome:
     The search result screen appears
     Step number: 3
     *Step Name: -
     *Step Description:
     Step 3: Do advanced search
     *Input Data:
     - Enter keyword in the Search field
     - Select space in the Space field
     - Press Enter
     *Expected Outcome:
     Show message alert that no result found*/

    @Test
    public  void test04_SearchWhenTheKeywordIsNotMatched() {
        info("Test 4: Search when the keyword is not matched");
        info("Create a wiki page");
        String title = "title" +getRandomNumber();
        String content = "content" +getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        arrayPage.add(title);
        info("Do Search");
        wikiSearch.quickSeach(title);
        info("Verify that the page is shown in searched results");
        wikiValidattions.verifySearchResults(title);
        info("Go to Advanced search");
        wikiSearch.advancedSearch(title,"My Wiki");
        info("Verify that the searched results is empty");
        wikiValidattions.verifyEmptySearchResults();
        info ("Delete page wiki");
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);
    }

    /**
     *<li> Case ID:139360.</li>
     *<li> Test Case Name: View content of search result when user does not have permission to view page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     /*Step Number: 1 Step Name: -
     *Step Description:
     Step 1: Create pages
     *Input Data:
     - Go to Add Page â†’Blank Page (or From Template)
     - Add values in required fields
     - Click Save
     *Expected Outcome:
     - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     - New pages is created successful
     Step number: 2
     *Step Name: -
     *Step Description:
     Step 2: Set permission for page
     *Input Data:
     - Add permission for page that some users/groups can not view and edit this page
     *Expected Outcome:
     Page is added permission
     Step number: 3
     *Step Name: -
     *Step Description:
     Step 3: Do search
     *Input Data:
     - Login by user does not have permission to view and edit page
     - Put keyword in the search box
     - Press Enter
     *Expected Outcome:
     Page which user does not have permission is not listed in search result*/
    @Test
    public  void test05_ViewContentOfSearchResultWhenUserDoesNotHavePermissionToViewPage() {
        info("Test 5: View content of search result when user does not have permission to view page");
        info("Create new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create a wiki page");
        String title = "title" +getRandomNumber();
        String content = "content" +getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        arrayPage.add(title);
        info("Set permisison for PageD");
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToPermissions();
        wikiPermission.deletePermission("any");
        wikiPermission.savePermisison();
        info("Login with user 1");
        manageLogInOut.signOut();
        manageLogInOut.signInCas(arrayUsers.get(0), password);
        info("Do Search");
        homePagePlatform.goToWiki();
        wikiSearch.quickSeach(title);
        info("Verify that the searched results is empty");
        wikiValidattions.verifyEmptySearchResults();
    }

    /**
     *<li> Case ID:139361.</li>
     *<li> Test Case Name: View content of search result when user does not have permission to view page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li> Step Number: 1
     *Step Name: -
     *Step Description:
     Step 1: Create pages
     *Input Data:
     - Go to Add Page â†’Blank Page (or From Template)
     - Add values in required fields
     - Add values in required fields
     - Click Save
     *Expected Outcome:
     - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     - New pages is created successful
     /*Step number: 2
     *Step Name: -
     *Step Description:
     Step 2: Set permission for page
     *Input Data:
     - Add permission for page that some users/groups can not view and edit this page
     *Expected Outcome:
     Page is added permission
          */
    @Test
    public  void test06_ViewContentOfSearchResultWhenUserDoesNotHavePermissionToViewPage() {
        info("Test 6: View content of search result when user does not have permission to view page");
        info("Create new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create a wiki page");
        String title = "title" +getRandomNumber();
        String content = "content" +getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        arrayPage.add(title);
        info("Set permisison for PageD");
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToPermissions();
        wikiPermission.deletePermission("any");
        wikiPermission.savePermisison();
        info("Login with user 1");
        manageLogInOut.signOut();
        manageLogInOut.signInCas(arrayUsers.get(0), password);
        info("Do Search");
        homePagePlatform.goToWiki();
        wikiSearch.quickSeach(title);
        info("Verify that the searched results is empty");
        wikiValidattions.verifyEmptySearchResults();
        info("Go to Advanced search");
        wikiSearch.advancedSearch(title,"My Wiki");
        info("Verify that the searched results is empty");
       manageLogInOut.signOut();
        manageLogInOut.signInCas("root", "gtn");
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteAllUsers(arrayUsers);

    }

    /**
     *<li> Case ID:139363.</li>
     *<li> Test Case Name: Search - Space switcher is scrollable when there are more than 10 results.</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li>
     *<li> Case ID:139364.</li>
     *<li> Test Case Name: Search doesn't take into account order of words.</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li>
     *<li> Case ID:139365.</li>
     *<li> Test Case Name: Search is not case sensitive.</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li>
     *<li> Case ID:139366.</li>
     *<li> Test Case Name: Search is working as a filter in the list of spaces.</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li>
     *<li> Case ID:139367.</li>
     *<li> Test Case Name: Search should not take into account spaces at the end of a text inputted.</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4,
     * Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li>
     *<li> Case ID:139368.</li>
     *<li> Test Case Name: Search should start on first characters inputed.</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li>
     *<li> Case ID:139369.</li>
     *<li> Test Case Name: Search should take into account spaces in the middle of text inputted.</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li>
     *<li> Case ID:139370.</li>
     *<li> Test Case Name: When no results search result, space switcher should display "No Spaces".</li>
     *<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
     *<li> Post-Condition: </li> Step Number: 1
     *Step Name:
     *Step Description:
     - Using left sidebar navigation, go into the wiki
     *Input Data:

     *Expected Outcome:
     - Wiki is displayed
     - Breadcrumb is displayed with space switcher component
     /*Step number: 2
     *Step Name:
     *Step Description:
     - Open the Space switcher component
     *Input Data:

     *Expected Outcome:
     - Input text is displayed
    *Step number: 3
		*Step Name:
		*Step Description:
			- Input "Spac" in the input text
		*Input Data:

		*Expected Outcome:
			- List of spaces is updated and displaying :
			* Space 1* Space 2* Space 3* Space 4* Space 5* Space 6* Space 7* Space 8* Space 9* Space 10
			* User can scroll down to see* Space 11* Long title for a space name 30
     *
     * Step number: 2
     * 		*Step Name:
     * 		*Step Description:
     * 			- Open the Space switcher component
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- Input text is displayed
     *  Step number: 3
     * 		*Step Name:
     * 		*Step Description:
     * 			- Input "mob" in the input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of spaces is updated and displaying :* Mobile
     * 		/*Step number: 2
     * 		*Step Name:
     * 		*Step Description:
     * 			- Open the Space switcher component
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- Input text is displayed
     *Step number: 3
     * 		*Step Name:
     * 		*Step Description:
     * 			- Input "Mo" in the input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of spaces is updated and displaying :* Mobile
     *Step number: 4
     * 		*Step Name:
     * 		*Step Description:
     * 			- Delete "Mo" from input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of last browsed space is displayed again
     *Step number: 5
     * 		*Step Name:
     * 		*Step Description:
     * 			- Input "bile"
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of sapces is updated and displaying : * Mobile
     * 		/*Step number: 2
     * 		*Step Name:
     * 		*Step Description:
     * 			- Open the Space switcher component
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- Input text is displayed
     *Step number: 3
     * 		*Step Name:
     * 		*Step Description:
     * 			- Input "Mo" (with 2 spaces at the end) in the input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of spaces is updated and displaying :* Mobile
     * 			/*Step number: 2
     * 		*Step Name:
     * 		*Step Description:
     * 			- Open the Space switcher component
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- Input text is displayed
     *Step number: 3
     * 		*Step Name:
     * 		*Step Description:
     * 			- Input "x" in the input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of spaces is updated
     * Step number: 4
     * 		*Step Name:
     * 		*Step Description:
     * 			- Remove "Long title" from the input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of last browsed spaces is displayed again
     * Step number: 5
     * 		*Step Name:
     * 		*Step Description:
     * 			- Input "Longtitle" (with 2 spaces between "Long" and "title") in the input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			Space switcher is displaying :
     * 			- "No Spaces"*
     * 				/*Step number: 2
     * 		*Step Name:
     * 		*Step Description:
     * 			- Open the Space switcher component
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- Input text is displayed
     *Step number: 3
     * 		*Step Name:
     * 		*Step Description:
     * 			- Input "x" in the input text
     * 		*Input Data:
     *
     * 		*Expected Outcome:
     * 			- List of spaces is updated and displaying "No Spaces"*/
    @Test
    public  void test07_SearchSpaceSwitcher() {
        info("Test 7: Search - Space switcher is scrollable when there are more than 10 results");
        info("Create new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Login with user 1");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        info("Login with user 1");
        ArrayList<String>array= new ArrayList<String>();
        for(int i=0;i<4;i++){
            info("Create a space");
            String space = "Space "+getRandomNumber();
            homePagePlatform.goToMySpaces();
            refresh();
            spaceManagement.addNewSpaceSimple(space, space,6000);
            array.add(space);
        }

        info("Create a other space");
        String space1 = "Long"+" title"+" Space"+ getRandomNumber();

        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1,6000);
        info("Create a other space");
        String space2 = "Mobile"+ "spc2" +getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2,space2,6000);
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("Spac");
        wikiValidattions.verifyPresentSpaceSwitcher(array);
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("Spac");
        wikiValidattions.verifyPresentSpaceSwitcher(space1);
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("Spac");
        wikiValidattions.verifyNotPresentSpaceSwitcher(space2);

        info("Test 8: Search doesn't take into account order of words");
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("title");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyPresentSpaceSwitcher(space1);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space2);
        info("Test 9: Search is not case sensitive");
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("mob");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyPresentSpaceSwitcher(space2);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
        info("Test 10 Search is working as a filter in the list of spaces");
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("Mo");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyPresentSpaceSwitcher(space2);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
        wikiHomePage.inputSpaceSwitcher(" ");
        wikiValidattions.verifyPresentSpaceSwitcher(array);
        wikiValidattions.verifyPresentSpaceSwitcher(space1);
        wikiValidattions.verifyPresentSpaceSwitcher(space2);
        wikiHomePage.inputSpaceSwitcher("bile");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyPresentSpaceSwitcher(space2);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
        info("Test 11 Search should not take into account spaces at the end of a text inputted");
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("Mo");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyPresentSpaceSwitcher(space2);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
        info("Test 12 Search should start on first characters inputed");
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("x");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space2);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
        info("Test 13 Search should take into account spaces in the middle of text inputted");
        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("Long title");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space2);
        wikiValidattions.verifyPresentSpaceSwitcher(space1);
        wikiHomePage.inputSpaceSwitcher(" ");
        wikiValidattions.verifyPresentSpaceSwitcher(array);
        wikiValidattions.verifyPresentSpaceSwitcher(space2);
        wikiValidattions.verifyPresentSpaceSwitcher(space1);
        wikiHomePage.inputSpaceSwitcher("Longtitle");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space2);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
        info("Test 14 When no results search result, space switcher should display No Spaces");

        homePagePlatform.goToWiki();
        wikiHomePage.goToSpaceSwitcher();
        wikiHomePage.inputSpaceSwitcher("x");
        wikiValidattions.verifyNotPresentSpaceSwitcher(array);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space2);
        wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
        info ("Delete all users");
        manageLogInOut.signOut();
        manageLogInOut.signInCas("root", "gtn");
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteAllUsers(arrayUsers);
    }
    /**
     *<li> Case ID:139371.</li>
     *<li> Test Case Name: When user is member of at least one space, input text should be displayed.</li>
     *<li> Pre-Condition: User is member of space "Mobile"</li>
     *<li> Post-Condition: </li>
     Step Number: 1
     *Step Name:
     *Step Description:
     - Go to Company wiki
     *Input Data:

     *Expected Outcome:
     - Company wiki is displayed
     - Breadcrumb is displaying Space Switcher
     Step number: 2
     *Step Name:
     *Step Description:
     - Open the Space Switcher
     *Input Data:

     *Expected Outcome:
     - The first item of the list is an input text field*/
    @Test
    public  void test15_WhenUserIsMemberOfAtLeastOneSpaceInputTextShouldBeDisplayed() {
        info("Test 15 When user is member of at least one space, input text should be displayed");
        info("Go to space switcher");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb("My Wiki", "John");
        info("Verify that The first item of the list is an input text field");
        $(ELEMENT_WIKI_SEARCH_FIELD).waitUntil(Condition.visible,Configuration.timeout);

    }}