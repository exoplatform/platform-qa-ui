package org.exoplatform.platform.qa.ui.platform.wiki.functional;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_ADD_RELATED_POPUP_DROPDOWN_VALUE;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 6/27/18.
 */
public class WikiPageInformationRelatedPageTestIT extends Base {
    HomePagePlatform homePagePlatform;
    WikiHomePage wikiHomePage;
    WikiManagement wikiManagement;
    SourceTextEditor sourceTextEditor;
    ManageLogInOut manageLogInOut;
    WikiValidattions wikiValidattions;
    RichTextEditor richTextEditor;
WikiPageInformation wikiPageInformation;
UserAddManagement userAddManagement ;
NavigationToolbar navigationToolbar ;
WikiPermission wikiPermission;
SpaceManagement spaceManagement;
SpaceHomePage spaceHomePage;
SpaceSettingManagement spaceSettingManagement;
AddUsers addUsers;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiValidattions = new WikiValidattions(this);
    richTextEditor = new RichTextEditor(this);
        userAddManagement = new UserAddManagement(this);
        wikiPageInformation = new WikiPageInformation(this);
        navigationToolbar = new NavigationToolbar(this);
        wikiPermission = new WikiPermission(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        addUsers = new AddUsers(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
        $(ELEMENT_SKIP_BUTTON).click();
    }
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
}


    /**
     *<li> Case ID:139294.</li>
     *<li> Test Case Name: Add related page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test01_AddRelatedPage() {
        info("Test 1: Add related page");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages are created successfully*/
        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);


        info("Create new page 2");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

		/*Step number: 2
		*Step Name: Step 2: Open form to see page's information
		*Step Description:
			- Select 1 page
			- Select More
			-> Page Info
		*Input Data:

		*Expected Outcome:
			All page's information are listed: Summary, Related page, Hierarchy, Recent Changes*/
        info("Open form to see page's information");
        wikiHomePage.goToPageInformation(page1);
        wikiValidattions.verifyTablesPageInformation();

		/*Step number: 3
		*Step Name: Step 3: Add related page
		*Step Description:
			- Click on Add more relation button
			- Select 1 page
			- Click on Select button
		*Input Data:

		*Expected Outcome:
			Related page is added and listed*/
        info("Add related page");
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page);
        wikiValidattions.verifyRelatedPage("portal",page);
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(page);


    }

    /**
     *<li> Case ID:139295.</li>
     *<li> Test Case Name: Add related page when user does not have permission to edit this page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test02_AddRelatedPageWhenUserDoesNotHavePermissionToEditThisPage() {
        info("Test 2: Add related page when user does not have permission to edit this page");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages are created successful. E.g: Test1 and Test2*/
        info("Create 2 new users");


        String password="123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1,password);

        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);


		/*Step number: 2
		*Step Name: Step 2: Set permission for page
		*Step Description:
			- Select page: Test1
			- Set permission for this page so that some user cannot edit this page
		*Input Data:

		*Expected Outcome:
			Page is added permission*/


		/*Step number: 3
		*Step Name: Step 3: Open form to see page's information
		*Step Description:
			- Login by user can not edit page above (Test1)
			- Select page Test1
			- Select More
			-
			-> Page Info
		*Input Data:

		*Expected Outcome:
			All page's information are listed: Summary, Related page, Hierarchy, Recent Changes*/

        info("Login with user 1");
        manageLogInOut.signIn(arrayUsers.get(0), password);


        info("Open form to see page's information");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPageInformation(page);
        wikiValidattions.verifyTablesPageInformation();

		/*Step number: 4
		*Step Name: Step 4: Add related page
		*Step Description:
			Click on Add more relation button
		*Input Data:

		*Expected Outcome:
			User can't see Add more relation button*/
        info("Add related page");
        $(ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS).waitUntil(Condition.not(Condition.visible),Configuration.timeout);

    }

    /**
     *<li> Case ID:139296.</li>
     *<li> Test Case Name: Add related page when user does not have permission to view selected page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test03_AddRelatedPageWhenUserDoesNotHavePermissionToViewSelectedPage() {
        info("Test 3: Add related page when user does not have permission to view selected page");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages are created successfully. E.g: Test1 and Test2*/
        info("Create 2 new users");

        String password="123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1,password);


        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);

        info("Set permission for page");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();


        info("Create new page 2");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);


		/*Step number: 2
		*Step Name: Step 2: Set permission for page
		*Step Description:
			- Select page: Test2
			- Set permission for this page so that some user cannot view this page
		*Input Data:

		*Expected Outcome:
			Page is added permission*/
        info("Set permission for page");
        wikiHomePage.goToPermissions();
        wikiPermission.deletePermission("any");
        wikiPermission.savePermisison();

		/*Step number: 3
		*Step Name: Step 3: Open form to see page's information
		*Step Description:
			- Login by user can not view page above (Test2)
			- Select page Test1
			- Select More
			-
			-> Page Info
		*Input Data:

		*Expected Outcome:
			All page's information are listed: Summary, Related page, Hierarchy, Recent Changes*/
        info("Login with user 1");
        manageLogInOut.signIn(arrayUsers.get(0), password);

        info("Open form to see page's information");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPageInformation(page);
        wikiValidattions.verifyTablesPageInformation();

		/*Step number: 4
		*Step Name: Step 4: Add related page
		*Step Description:
			- Click on Add more relation button
		*Input Data:

		*Expected Outcome:
			Can not see page Test2 on the list to select*/
        wikiPageInformation.goToAddRelations();
        wikiValidattions.verifyNotPageInRelatedPageList(page1);


    }
    /**
     *<li> Case ID:139301.</li>
     *<li> Test Case Name: Delete page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test04_DeletePage() {
        info("Test 4: Delete page");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages are created successfully*/
        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);


        info("Create new page 2");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);


		/*Step number: 2
		*Step Name: Step 2: Add related page
		*Step Description:
			- Select 1 page
			- Add more related page
		*Input Data:

		*Expected Outcome:
			Page is added relations*/
        info("Open form to see page's information");
        wikiHomePage.goToPageInformation(page1);
        wikiValidattions.verifyTablesPageInformation();

		/*Step number: 3
		*Step Name: Step 3: Delete related page
		*Step Description:
			- Click Remove corresponding with the related page want to delete
			- Click OK on confirm message
		*Input Data:

		*Expected Outcome:
			Related page is deleted*/
        info("Add related page");
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page);
        wikiValidattions.verifyRelatedPage("portal",page);
        info("Delete related page");
        wikiPageInformation.deleteRelation(page);
        homePagePlatform.goToWiki();


    }
    /**
     *<li> Case ID:139302.</li>
     *<li> Test Case Name: Delete related page when cancel confirm message.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test05_DeleteRelatedPageWhenCancelConfirmMessage() {
        info("Test 5: Delete related page when cancel confirm message");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages are created successfully*/
        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);



        info("Create new page 2");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);


		/*Step number: 2
		*Step Name: Step 2: Add related page
		*Step Description:
			- Select 1 page
			- Add more related page
		*Input Data:

		*Expected Outcome:
			Page is added relations*/
        info("Open form to see page's information");
        wikiHomePage.goToPageInformation(page1);
        wikiValidattions.verifyTablesPageInformation();


		/*Step number: 3
		*Step Name: Step 3: Delete related page
		*Step Description:
			- Click Remove corresponding with the related page want to delete
			- Click Cancel on confirm message
		*Input Data:

		*Expected Outcome:
			Related page is not deleted*/
        info("Add related page");
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page);
        wikiValidattions.verifyRelatedPage("portal",page);
        info("Delete related page");
        wikiPageInformation.deleteRelationWithCancelDeleting(page);

    }

    /**
     *<li> Case ID:139303.</li>
     *<li> Test Case Name: Delete related page when user does not have permission to edit page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test06_DeleteRelatedPageWhenUserDoesNotHavePermissionToEditPage() {
        info("Test 6: Delete related page when user does not have permission to edit page");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages are created successful*/
        info("Create 2 new users");
        String password="123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1,password);

        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);


        info("Create new page 2");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);


		/*Step number: 2
		*Step Name: Step 2: Add related page
		*Step Description:
			- Select 1 page
			- Add more related page
		*Input Data:

		*Expected Outcome:
			Page is added relations*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page1);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page);
        wikiValidattions.verifyRelatedPage("portal",page);

		/*Step number: 3
		*Step Name: Step 3: Delete related page
		*Step Description:
			- Login by user does not have permission to edit page at step 1
			- Click Remove corresponding with the related page want to delete
		*Input Data:

		*Expected Outcome:
			There is no remove link to click*/
        info("Login with user 1");
        manageLogInOut.signIn(arrayUsers.get(0), password);

        info("There is no remove link to click");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page1);
        wikiHomePage.goToPageInformation(page);
        $(byXpath(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}",page))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    }

    /**
     *<li> Case ID:139311.</li>
     *<li> Test Case Name: View related page.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test07_ViewRelatedPage() {
        info("Test 7: View related page");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode*/
        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);


        info("Create new page 2");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);


		/*Step number: 2
		*Step Name: Step 2: Add related page
		*Step Description:
			- Select 1 page
			- Add more related page
		*Input Data:

		*Expected Outcome:
			Page is added relations*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page);
        wikiValidattions.verifyRelatedPage("portal",page);

		/*Step number: 3
		*Step Name: Step 3: View content of related page
		*Step Description:
			- Click on one of related page
		*Input Data:

		*Expected Outcome:
			Content of related page is shown*/
        info("View content of related page");
        wikiPageInformation.viewRelatedPageContent(page);
    }
    /**
     *<li> Case ID:139312.</li>
     *<li> Test Case Name: View related page when user does not have permission to view.</li>
     *<li> Pre-Condition: </li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test08_ViewRelatedPageWhenUserDoesNotHavePermissionToView() {
        info("Test 8: View related page when user does not have permission to view");
		/*Step Number: 1
		*Step Name: Step 1: Create new pages
		*Step Description:
			- Click [Add Page]
			-
			-> [Blank Page]/[From Template...]
			- Add values in required fields
			- Click Save
		*Input Data:

		*Expected Outcome:
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages are created successful*/
        info("Create 2 new users");
        String password="123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1,password);

        info("Create new page 1");
        String page = "page"+getRandomNumber();
        String pageContent= "pageContent"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page,pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);


        info("Create new page 2");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Set permission for page");
        wikiHomePage.goToPermissions();
        wikiPermission.deletePermission("any");
        wikiPermission.savePermisison();

		/*Step number: 2
		*Step Name: Step 2: Add related page
		*Step Description:
			- Select 1 page
			- Add more related page
		*Input Data:

		*Expected Outcome:
			Page is added relations*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page1);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page);
        wikiValidattions.verifyRelatedPage("portal",page);

		/*Step number: 3
		*Step Name: Step 3: View content of related page
		*Step Description:
			- Click on one of related page that user does not have permission to view
		*Input Data:

		*Expected Outcome:
			User can't see this related page*/
        info("Login with user 1");
        manageLogInOut.signIn(arrayUsers.get(0), password);

        info("There is no remove link to click");
        homePagePlatform.goToWiki();
        wikiValidattions.verifyNotTitleWikiPage(page1);
        wikiValidattions.verifyNotPageInLeftRelatePageList(page1);
    }

    /**
     *<li> Case ID:139313.</li>
     *<li> Test Case Name: Renaming a page from another space should correctly be updated in Page Info Layout of a related page.</li>
     *<li> Pre-Condition: User is member of Space 1, Space 2, Space 3
     *Wiki of "Space 1" has following pages:
     - Page 1
     - Page 2
     Wiki of "Space 2" has following pages:
     - Page A
     - Page B
     Wiki of "Space 3" has following pages:
     - Page a
     - Page b
     "Page 1 " of "Space 1" wiki has relations with:
     -- "Page A" from "Space 2"
     -- "Page a" from "Space 3"</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test09_RenamingAPageFromAnotherSpaceShouldCorrectlyBeUpdatedInPageInfoLayoutOfARelatedPage() {
        info("Test 9: Renaming a page from another space should correctly be updated in Page Info Layout of a related page");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Page A" of "Space 2" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki is displaying "Page A"*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create a space 2");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2,space2);


        info("Create new page A");
        String pageA = "pageA"+getRandomNumber();
        String pageContentA= "pageContentA"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA,pageContentA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);

        info("Add related page");
        wikiHomePage.goToPageInformation(pageA);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations(space1,page1);
        wikiValidattions.verifyRelatedPage(space1,page1);


        info("Create a space 3");
        String space3 = "space3"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space3,space3);


        info("Create new page a");
        String page_a = "page_a"+getRandomNumber();
        String pageContent_a= "pageContent_a"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page_a,pageContent_a);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page_a);

        info("Add related page");
        wikiHomePage.goToPageInformation(page_a);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations(space1,page1);
        wikiValidattions.verifyRelatedPage(space1,page1);

        info("Open Space 2 and Page A");
        homePagePlatform.goToMySpaces();
        ELEMENT_SPACES_LIST.find(byText(space1)).click();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAPage(page1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Rename "Page A" to "Page A renamed"
		*Input Data:

		*Expected Outcome:
			- Title of the wiki page is now "Page A renamed"*/
        info("Rename page A");
        String page1Renamed = "page1Renamed"+getRandomNumber();
        wikiManagement.renamePageByDoubleClick(page1, page1Renamed);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Go to "Page 1 " of "Space 1" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki is displaying "Page 1"*/
        info("Open Space 2 and Page A");
        homePagePlatform.goToMySpaces();
        ELEMENT_SPACES_LIST.find(byText(space2)).click();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAPage(pageA);

		/*Step number: 4
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page infos of Page 1 are diplayed*/
        info("Open Page Information");
        wikiHomePage.goToPageInformation(pageA);

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Check updates in Related pages
		*Input Data:

		*Expected Outcome:
			- Title of "Page A" has been correctly renamed "Page A renamed"*/
        info("Space1:"+space1);
        info("page1:"+page1);
        info("page1Renamed:"+page1Renamed);
        wikiValidattions.verifyRelatedPage(space1,page1Renamed);
        wikiValidattions.verifyNotPageInRelatedPageList(page1);
        wikiValidattions.verifyPageInLeftRelatePageList(page1Renamed);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);
        spaceManagement.deleteSpace(space3, false);

    }
    /**
     *<li> Case ID:139314.</li>
     *<li> Test Case Name: Renaming a Space should be correctly updated in Page Info Layout of a related page.</li>
     *<li> Pre-Condition: User is member of Space 1, Space 2, Space 3
     *Wiki of "Space 1" has following pages:
     - Page 1
     - Page 2
     Wiki of "Space 2" has following pages:
     - Page A
     - Page B
     Wiki of "Space 3" has following pages:
     - Page a
     - Page b
     "Page 1 " of "Space 1" wiki has relations with:
     -- "Page A" from "Space 2"
     -- "Page a" from "Space 3"</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test10_RenamingASpaceShouldBeCorrectlyUpdatedInPageInfoLayoutOfARelatedPage() {
        info("Test 10 Renaming a Space should be correctly updated in Page Info Layout of a related page");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space 3"
		*Input Data:

		*Expected Outcome:
			- Activity stream of "Space 3" is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);


        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create a space 2");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2,space2);


        info("Create new page A");
        String pageA = "pageA"+getRandomNumber();
        String pageContentA= "pageContentA"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA,pageContentA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);

        info("Add related page");
        wikiHomePage.goToPageInformation(pageA);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations(space1,page1);
        wikiValidattions.verifyRelatedPage(space1,page1);


        info("Create a space 3");
        String space3 = "space3"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space3,space3);


        info("Create new page a");
        String page_a = "page_a"+getRandomNumber();
        String pageContent_a= "pageContent_a"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page_a,pageContent_a);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page_a);

        info("Add related page");
        wikiHomePage.goToPageInformation(page_a);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations(space1,page1);
        wikiValidattions.verifyRelatedPage(space1,page1);

        info("Open Space 2 and Page A");
        homePagePlatform.goToSpecificSpace(space1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Go to "Space Management"
			- Renamed "Space 3" to "Space 3 renamed"
		*Input Data:

		*Expected Outcome:
			- Space has been renamed to "Space 3 renamed"*/
        info("Rename space 1");
        String space1Rename = "space1Rename"+getRandomNumber();
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.updateInfoSpace(space1Rename,space1Rename);
        spaceSettingManagement.saveInfoSpace();
        wikiHomePage.confirmWaringMessage(true);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Go to "Page 1 " of "Space 1" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki is displaying "Page 1"*/
        info("Open page a  of space3");
        homePagePlatform.goToSpecificSpace(space3);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAPage(page_a);

		/*Step number: 4
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page infos of Page 1 are diplayed*/
        info("Open Page Information");
        wikiHomePage.goToPageInformation(page_a);

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Check updates in Related pages
		*Input Data:

		*Expected Outcome:
			- Wiki of "Page a" is "Space 3 renamed"*/
        wikiValidattions.verifyRelatedPage(space1Rename,page1);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);
        spaceManagement.deleteSpace(space3, false);
    }

    /**
     *<li> Case ID:139315.</li>
     *<li> Test Case Name: Add 2 relations from 2 different spaces.</li>
     *<li> Pre-Condition: User is member of Space 1, Space 2, Space 3
     *Wiki of "Space 1" has following pages:
     - Page 1
     - Page 2
     Wiki of "Space 2" has following pages:
     - Page A
     - Page B
     Wiki of "Space 3" has following pages:
     - Page a
     - Page b</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test11_Add2RelationsFrom2DifferentSpaces() {
        info("Test 11 Add 2 relations from 2 different spaces");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space 1" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki of "Space 1" is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);


        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create a space 2");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2,space2);


        info("Create new page A");
        String pageA = "pageA"+getRandomNumber();
        String pageContentA= "pageContentA"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA,pageContentA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);


        info("Create new page B");
        String pageB = "pageB"+getRandomNumber();
        String pageContentB= "pageContentB"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB,pageContentB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);

        info("Create a space 3");
        String space3 = "space3"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space3,space3);

        info("Create new page a");
        String page_a = "page_a"+getRandomNumber();
        String pageContent_a= "pageContent_a"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page_a,pageContent_a);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page_a);


        info("Create new page b");
        String page_b = "page_b"+getRandomNumber();
        String pageContent_b= "pageContent_b"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page_b,pageContent_b);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page_b);

        info("Open space 1");
        homePagePlatform.goToSpecificSpace(space1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "Page 1"
		*Input Data:

		*Expected Outcome:
			- Page 1 is displayed in the wiki*/
        info("Open page 1");
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAPage(page_b);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page infos of Page 1 are diplayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- On "Related Page Layout" click on button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Open Space switcher component
		*Input Data:

		*Expected Outcome:
			- The list of space swticher options is displayed*/

		/*Step number: 6
		*Step Name:
		*Step Description:
			- Select "Space 2"
		*Input Data:

		*Expected Outcome:
			- Wiki tree of "Space 2" is displayed on the container below the space switcher*/

		/*Step number: 7
		*Step Name:
		*Step Description:
			- Select "Page A"
			- Click on "Select" button
		*Input Data:

		*Expected Outcome:
			- Popup is closed
			- "Page A" is added as a related pages on page info layout*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page_b);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations(space2,pageA);
        wikiValidattions.verifyRelatedPage(space2,pageA);

		/*Step number: 8
		*Step Name:
		*Step Description:
			- On "Related Page Layout" click on button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 9
		*Step Name:
		*Step Description:
			- Open Space switcher component
		*Input Data:

		*Expected Outcome:
			- The list of space swticher options is displayed*/

		/*Step number: 10
		*Step Name:
		*Step Description:
			- Select "Space 3"
		*Input Data:

		*Expected Outcome:
			- Wiki tree of "Space 3" is displayed on the container below the space switcher*/

		/*Step number: 11
		*Step Name:
		*Step Description:
			- Select "Page a"
			- Click on "Select" button
		*Input Data:

		*Expected Outcome:
			- Popup is closed
			- "Page a" is added as a related pages on page info layout
			- "Page A" is still displayed as a related pages on page info layout*/
        info("Add related page");
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations(space3,page_a);
        wikiValidattions.verifyRelatedPage(space3,page_a);
        wikiValidattions.verifyRelatedPage(space2,pageA);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }
    /**
     *<li> Case ID:139316.</li>
     *<li> Test Case Name: Add related popup must display the currently browsed space by default.</li>
     *<li> Pre-Condition: User is member of Space 2
     *Space 2 wiki has following pages:
     - Page 1
     - Page 2
     --- Sub-Page 1</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test12_AddRelatedPopupMustDisplayTheCurrentlyBrowsedSpaceByDefault() {
        info("Test 12 Add related popup must display the currently browsed space by default");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go into "Space 2" wiki
		*Input Data:

		*Expected Outcome:
			- "Space 2 " wiki is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);


        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create new sub page 2");
        String subpage1 = "subpage1"+getRandomNumber();
        String subpageContent1= "subpageContent1"+getRandomNumber();
        wikiHomePage.goToAPage(page2);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subpage1,subpageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subpage1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page Info of "Wiki Home" page are displayed*/

		/*Step number: 3
		*Step Name:
		*Step Description:
			- On the part dedicated to related page, click on the button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- Popup to select a page is displayed
			- Label "Select the space:" with the space switcher are displayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- Check space switcher component
		*Input Data:

		*Expected Outcome:
			- Space switcher component is displaying "Space 2"*/
        info("Add related page");
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToPageInformation(subpage1);
        wikiPageInformation.goToAddRelations();
       // waitForAndGetElement(wHome.ELEMENT_ADD_RELATED_POPUP_DROPDOWN_VALUE.replace("$space",space1));

    }

    /**
     *<li> Case ID:139317.</li>
     *<li> Test Case Name: Add relation to a page from another space.</li>
     *<li> Pre-Condition: User is member of Space 1 and Space 2Wiki of "Space 1" has following pages:
     - Page 1
     - Page 2
     Wiki of "Space 2" has following pages:
     - Page A
     - Page B</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test13_AddRelationToAPageFromAnotherSpace() {
        info("Test 13 Add relation to a page from another space");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space 1" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki of "Space 1" is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create a space 2");
        String space2 = "space2"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space2,space2);


        info("Create new page A");
        String pageA = "pageA"+getRandomNumber();
        String pageContentA= "pageContentA"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageA,pageContentA);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageA);


        info("Create new page B");
        String pageB = "pageB"+getRandomNumber();
        String pageContentB= "pageContentB"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(pageB,pageContentB);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(pageB);



		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "Page 1"
		*Input Data:

		*Expected Outcome:
			- Page 1 is displayed in the wiki*/
        info("Open page 1 of space 1");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAPage(page1);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page infos of Page 1 are diplayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- On "Related Page Layout" click on button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Open Space switcher component
		*Input Data:

		*Expected Outcome:
			- The list of space swticher options is displayed*/

		/*Step number: 6
		*Step Name:
		*Step Description:
			- Select "Space 2"
		*Input Data:

		*Expected Outcome:
			- Wiki tree of "Space 2" is displayed on the container below the space switcher*/

		/*Step number: 7
		*Step Name:
		*Step Description:
			- Select "Page A"
			- Click on "Select" button
		*Input Data:

		*Expected Outcome:
			- Popup is closed
			- "Page A" is added as a related pages on page info layout*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page1);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations(space2,pageA);
        wikiValidattions.verifyRelatedPage(space2,pageA);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
        spaceManagement.deleteSpace(space2, false);

    }

    /**
     *<li> Case ID:139318.</li>
     *<li> Test Case Name: Add relation to a page from the same space.</li>
     *<li> Pre-Condition: User is member of Space 1
     *Wiki of "Space 1" has following pages:
     - Page 1
     - Page 2</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test14_AddRelationToAPageFromTheSameSpace() {
        info("Test 14 Add relation to a page from the same space");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space 1" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki of "Space 1" is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "Page 1"
		*Input Data:

		*Expected Outcome:
			- Page 1 is displayed in the wiki*/
        info("Open page 1");
        wikiHomePage.goToAPage(page1);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page infos of Page 1 are diplayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- On "Related Page Layout" click on button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Select "Page 2"
			- Click on "Select" button
		*Input Data:

		*Expected Outcome:
			- Popup is closed
			- "Page 2" is added as a related pages on page info layout*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page1);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page2);
        wikiValidattions.verifyRelatedPage(space1,page2);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);

    }

    /**
     *<li> Case ID:139319.</li>
     *<li> Test Case Name: User should be able to select the currently browsed space.</li>
     *<li> Pre-Condition: User is member of Space 2Wiki of "Space 2" has following pages:
     - Page 1
     - Page 2
     --- Sub-Page 1</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test15_UserShouldBeAbleToSelectTheCurrentlyBrowsedSpace() {
        info("Test 15 User should be able to select the currently browsed space");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Space 2" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki Space 2 is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);

        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();;
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create new sub page 2");
        String subpage1 = "subpage1"+getRandomNumber();
        String subpageContent1= "subpageContent1"+getRandomNumber();
        wikiHomePage.goToAPage(page2);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subpage1,subpageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subpage1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Select "Page 2" the wiki tree
		*Input Data:

		*Expected Outcome:
			- "Page 2" is displayed*/
        info("Open page 2 and space 1");
        homePagePlatform.goToSpecificSpace(space1);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAPage(page2);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page info of "Page 2" are displayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- On "Related pages" part, click on the button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Open Space switcher component
		*Input Data:

		*Expected Outcome:
			- Space switcher component is displaying its list of options with "Space 2"*/

		/*Step number: 6
		*Step Name:
		*Step Description:
			- Select "Space 2"
		*Input Data:

		*Expected Outcome:
			- the page selection part is displaying the wiki tree of "Space 2"*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page2);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page1);
        wikiValidattions.verifyRelatedPage(space1,page1);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);

    }

    /**
     *<li> Case ID:139320.</li>
     *<li> Test Case Name: Check behaviors when long space name in Page Layout.</li>
     *<li> Pre-Condition: User is member of space : "Check long title for a space 1"
     *Wiki of "Check long title for a space 1" has following pages:
     - Page 1
     - Page 2</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test16_CheckBehaviorsWhenLongSpaceNameInPageLayout() {
        info("Test 16 Check behaviors when long space name in Page Layout");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Check long title for a space 1" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki of "Check long title for a space 1" is displayed*/
        info("Create a space 1");
        String space1 = "Check long title for"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);


        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create new sub page 2");
        String subpage1 = "subpage1"+getRandomNumber();
        String subpageContent1= "subpageContent1"+getRandomNumber();
        wikiHomePage.goToAPage(page2);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subpage1,subpageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subpage1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "Page 1"
		*Input Data:

		*Expected Outcome:
			- Page 1 is displayed in the wiki*/
        info("Open page 1");
        wikiHomePage.goToAPage(page1);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page infos of Page 1 are diplayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- On "Related Page Layout" click on button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Select "Page 2"
			- Click on "Select" button
		*Input Data:

		*Expected Outcome:
			- the popup is closed
			- "Page 2" is added as a related page on page info layout*/

		/*Step number: 6
		*Step Name:
		*Step Description:
			- Check Page Info Layout, on related page part
		*Input Data:

		*Expected Outcome:
			- The space named is displayed correctly*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page1);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page2);
        wikiValidattions.verifyRelatedPage(space1,page2);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);


    }

    /**
     *<li> Case ID:139321.</li>
     *<li> Test Case Name: Check behaviors when long space name in Select Page popup.</li>
     *<li> Pre-Condition: User is member of space : "Check long title for a space 1"
     *Wiki of "Check long title for a space 1" has following pages:
     - Page 1
     - Page 2</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test17_CheckBehaviorsWhenLongSpaceNameInSelectPagePopup() {
        info("Test 17 Check behaviors when long space name in Select Page popup");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go to "Check long title for a space 1" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki of "Check long title for a space 1" is displayed*/
        info("Create a space 1");
        String space1 = "Check long title for"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);


        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create new sub page 2");
        String subpage1 = "subpage1"+getRandomNumber();
        String subpageContent1= "subpageContent1"+getRandomNumber();
        wikiHomePage.goToAPage(page2);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subpage1,subpageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subpage1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "Page 1"
		*Input Data:

		*Expected Outcome:
			- Page 1 is displayed in the wiki*/
        info("Open page 1");
        wikiHomePage.goToAPage(page1);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page infos of Page 1 are diplayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- On "Related Page Layout" click on button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Check Space Switcher
		*Input Data:

		*Expected Outcome:
			- "Check long title for a space 1" is displayed correctly*/
        info("Add related page");
        wikiHomePage.goToPageInformation(page1);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page2);
        wikiValidattions.verifyRelatedPage(space1,page2);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);

    }

    /**
     *<li> Case ID:139322.</li>
     *<li> Test Case Name: Check changes in Add related popup layout.</li>
     *<li> Pre-Condition: User is member of Space 2Space 2 wiki has following pages:
     - Page 1
     - Page 2
     --- Sub-Page 1</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test18_CheckChangesInAddRelatedPopupLayout() {
        info("Test 18 Check changes in Add related popup layout");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Go into "Space 2" wiki
		*Input Data:

		*Expected Outcome:
			- "Space 2 " wiki is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);


        info("Create new page 1");
        String page1 = "spaMg"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create new sub page 2");
        String subpage1 = "subpage1"+getRandomNumber();
        String subpageContent1= "subpageContent1"+getRandomNumber();
        wikiHomePage.goToAPage(page2);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subpage1,subpageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subpage1);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			- Page Info of "Wiki Home" page are displayed*/
        info("Open Wiki Home");
        wikiHomePage.goToHomeWikiPage();

		/*Step number: 3
		*Step Name:
		*Step Description:
			- On the part dedicated to related page, click on the button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- Popup to select a page is displayed
			- Label "Select the space:" with the space switcher are displayed*/
        wikiHomePage.goToPageInformation(page2);
        wikiPageInformation.goToAddRelations();
        $(byXpath(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_VALUE.replace("$space",space1))).waitUntil(Condition.visible,Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }

    /**
     *<li> Case ID:139323.</li>
     *<li> Test Case Name: Check changes in Page info layout.</li>
     *<li> Pre-Condition: User is member of Space 2
     *Space 2 wiki has following pages:
     - Page 1
     - Page 2
     --- Sub-Page 1</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test19_CheckChangesInPageInfoLayout() {
        info("Test 19 Check changes in Page info layout");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			Go to "Space 2" wiki
		*Input Data:

		*Expected Outcome:
			- Wiki Space 2 is displayed*/
        info("Create a space 1");
        String space1 = "space1"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1,space1);


        info("Create new page 1");
        String page1 = "page1"+getRandomNumber();
        String pageContent1= "pageContent1"+getRandomNumber();
        spaceManagement.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page1,pageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page1);

        info("Create new page 2");
        String page2 = "page2"+getRandomNumber();
        String pageContent2= "pageContent2"+getRandomNumber();
        wikiHomePage.goToHomeWikiPage();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page2,pageContent2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page2);

        info("Create new sub page 2");
        String subpage1 = "subpage1"+getRandomNumber();
        String subpageContent1= "subpageContent1"+getRandomNumber();
        wikiHomePage.goToAPage(page2);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(subpage1,subpageContent1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(subpage1);


		/*Step number: 2
		*Step Name:
		*Step Description:
			- Select "Page 2" the wiki tree
		*Input Data:

		*Expected Outcome:
			- "Page 2" is displayed*/
        info("Open Page 2");
        wikiHomePage.goToAPage(page2);

		/*Step number: 3
		*Step Name:
		*Step Description:
			- Open "More" Menu
			- Select "Page Info"
		*Input Data:

		*Expected Outcome:
			Page info of "Page 2" are displayed*/

		/*Step number: 4
		*Step Name:
		*Step Description:
			- On "Related pages" part, click on the button "Add More Relations"
		*Input Data:

		*Expected Outcome:
			- The popup to select a related page is displayed*/

		/*Step number: 5
		*Step Name:
		*Step Description:
			- Select "Page 1"
			- Click on "Select" button
		*Input Data:

		*Expected Outcome:
			- The popup is dismissed
			- Page 1 is added in related page part*/

		/*Step number: 6
		*Step Name:
		*Step Description:
			- Check Related page table layout
		*Input Data:

		*Expected Outcome:
			- First column is Wiki and displaying "Space 2"
			- Second column is Related Pages and displaying "Wiki Home > Page 1"
			- Third column is Actions and displaying a bin icon*/
        info("Add related page");
        wikiHomePage.goToPageInformation(subpage1);
        wikiPageInformation.goToAddRelations();
        wikiPageInformation.addRelations("",page1);
        wikiValidattions.verifyRelatedPage(space1,page1);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);

    }
}
