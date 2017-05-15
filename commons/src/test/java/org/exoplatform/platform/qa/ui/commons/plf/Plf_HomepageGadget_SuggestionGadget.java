package org.exoplatform.platform.qa.ui.commons.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.AddUsers;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class Plf_HomepageGadget_SuggestionGadget extends Base{

   ManageLogInOut manageLogInOut;
   SpaceManagement spaceManagement;
   HomePagePlatform homePagePlatform;
   AddUsers addUsers;
   NavigationToolbar navigationToolbar;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        spaceManagement = new SpaceManagement(this);
        manageLogInOut =new ManageLogInOut(this);
        addUsers=new AddUsers(this);
        navigationToolbar=new NavigationToolbar(this);
    }
/**
*<li> Case ID:120859.</li>
*<li> Test Case Name: Accept a space suggestion.</li>
*<li> Pre-Condition: There are some spaces on system created by userA for ex</li>
*/
@Test
@Tag("smoke")
public  void test03_AcceptASpaceSuggestion(){
    info("Test 03: Accept a space suggestion");
    /*Step Number: 1
    *Step Name: Show Space suggestion
    *Step Description:
        - Login
        - Moves the mouse over a "Space suggestion" of a space
    *Input Data:

    *Expected Outcome:
        - Space suggestion is shown on Suggestion calendar
        - The request and cancel button are displayed*/

    info("Create 1 space");
    String space1="space1"+getRandomNumber();
    String username1="usernamea"+getRandomString();
    String email1=username1+"@gmail.com";
    String password="123456";
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1,password,email1,username1,username1);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1,space1);

    info("Sign in");
    manageLogInOut.signIn(username1, password);
    info("click on join a space");
    homePagePlatform.goToAllSpace();
    info("click on request to join");
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space1,"");
    $(byText("Request to Join")).click();
    $(byText("Cancel")).waitUntil(Condition.appears, Configuration.timeout);
    info("accept suggestion");
    manageLogInOut.signIn("root","gtn");
    info("go to notification icon and accept suggestion");
    $(byId("UINotificationPopoverToolbarPortlet")).click();
    $(byId("NotificationPopup")).waitUntil(Condition.appears,Configuration.timeout);
    $(byId("NotificationPopup")).find(byText("Accept")).click();
    info("verify that user id added in space");
    ELEMENT_SEARCH_SPACE.setValue(space1);
    $(byText(space1)).click();
    spaceManagement.goToMemberTab();
    $(byId("spaceMemberListBox")).scrollTo().find(byText(username1 +" "+username1)).waitUntil(Condition.appears,Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space1,"");
    spaceManagement.deleteSpace(space1, false);
    navigationToolbar.goToCommunityManagement();
    addUsers.deleteUser(username1);



}

/**
 * <li>Case ID:120863.</li> <li>Test Case Name: Cancel a space connection.</li>
 * <li>Pre-Condition: There are some spaces created on application</li>
 */
/*
@Test
public void test04_CancelASpaceConnection(){
    info("Test 04: Cancel a space connection");
    /*Step Number: 1
    *Step Name: Show space connection
    *Step Description:
        - Login
        - Moves the mouse over a "Space suggestion" of a space
    *Input Data:

    *Expected Outcome:
        - Space suggestion is shown on Suggestion calendar
        - The request and cancel button are displayed*/
  /*  info("Sign out");
    manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("Create 1 space");
    space1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1,space1);

    info("--Log in with james account--");
    info("Sign out");
    manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    info("Create 1 space");
    space2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2,space2);

    info("Sign out");
    manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS);

    info("Click on Accept button of Space1");
    mouseOver(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space1),true);
    Utils.pause(2000);
    waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_CANCEL_BTN.replace("${space}",space1),5000,1,2);
    waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_REQUEST_BTN.replace("${space}",space1),5000,1,2);
    /*Step number: 2
    *Step Name: Cancel a space suggestion
    *Step Description:
        - Click on Cancel button
    *Input Data:

    *Expected Outcome:
        - The suggestion of space fades out and is never suggested again.
        - The suggestion of other space is moved up*/
   /* info("Cancel is sent to space. The suggestionfades out and is never suggested again.");
    WebElement elementToClick2=waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_CANCEL_BTN.replace("${space}",space1),5000,1,2);
    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick2);
    waitForElementNotPresent(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space1),5000,1,2);
    info("The suggestion of other space is moved up");
    waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_REQUEST_BTN.replace("${space}",space2),5000,1,2);

    info("Delete space 1");
    manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);

    info("Delete space 2");
    manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space2, false);
}
*/
}