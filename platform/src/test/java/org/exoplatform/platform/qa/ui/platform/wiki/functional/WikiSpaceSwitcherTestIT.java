package org.exoplatform.platform.qa.ui.platform.wiki.functional;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_WIKI_TAB;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class WikiSpaceSwitcherTestIT extends Base{
NavigationToolbar navigationToolbar;
ManageLogInOut manageLogInOut;
SpaceManagement spaceManagement;
SpaceHomePage spaceHomePage;
WikiHomePage wikiHomePage;
WikiManagement wikiManagement;
RichTextEditor richTextEditor;
HomePagePlatform homePagePlatform;
WikiValidattions wikiValidattions;
UserAddManagement userAddManagement;
UserAndGroupManagement userAndGroupManagement;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar=new NavigationToolbar(this);
        spaceManagement=new SpaceManagement(this);
        spaceHomePage=new SpaceHomePage(this);
        richTextEditor=new RichTextEditor(this);
        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiValidattions = new WikiValidattions(this);
        wikiManagement = new WikiManagement(this);
        userAndGroupManagement=new UserAndGroupManagement(this);
        userAddManagement=new UserAddManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }
/**
*<li> Case ID:139372.</li>
*<li> Test Case Name: Changing from one space wiki to another should change the currently browsed space.</li>
*<li> Pre-Condition: User is member of Space 1 and Space 2
*Wiki of Space 2 has :
- Page 1
- Page 2
--- Sub-Page 3</li>
*<li> Post-Condition: </li>
*Step Number: 1
    *Step Name:
    *Step Description:
        - Using the left sidebar navigation, select "Space 2"
    *Input Data:

    *Expected Outcome:
        - Space 2 is displayed with space navigation, on its activity stream
  *Step number: 2
    *Step Name:
    *Step Description:
        - Go to wiki application
    *Input Data:

    *Expected Outcome:
        - Space 2 is displaying wiki application.
        - The space navigation is still displayed*/

    /*Step number: 3
    *Step Name:
    *Step Description:
        - Open the space switcher
        - Select "Space 1"
    *Input Data:

    *Expected Outcome:
        - The wiki of "Space 1" is displayed
        - The Space navigation of "Space 2" is not displayed anymore
        - Wiki is selected on the left sidebar navigation*/
@Test
public  void test01_ChangingFromOneSpaceWikiToAnotherShouldChangeTheCurrentlyBrowsedSpace() {
    info("Test 1: Changing from one space wiki to another should change the currently browsed space");

    info("Create 2 new users");
    ArrayList<String> arrayUsers;
    String password="123456";
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1,password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);

    info("Create a space");
    String space1 = "space1"+getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1,space1,6000);
    info("Create a space");
    String space2 = "space2"+getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2,space2,6000);

    info("Create pages");
    String page1 =  "page1"+getRandomNumber();
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space2)).click();
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page1,page1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page1);

    info("Create pages");
    String page2 =  "page2"+getRandomNumber();
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page2,page2);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page2);

    info("Create subpage");
    String subpage =  "subpage"+getRandomNumber();
    wikiHomePage.goToAPage(page2);
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(subpage,subpage);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(subpage);


    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space2)).click();
    spaceManagement.goToWikiTab();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
    wikiValidattions.verifyBreadCrumbePath(space1,"Wiki Home");
    $(ELEMENT_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
 }
}