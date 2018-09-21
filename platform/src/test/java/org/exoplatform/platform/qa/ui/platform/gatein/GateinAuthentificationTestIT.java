package org.exoplatform.platform.qa.ui.platform.gatein;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.commons.objectdatabase.TextBoxDatabase;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_PLF_HOMEPAGE_DISPLAY;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
* @author eXo
*
*/
public class GateinAuthentificationTestIT extends Base{

    ManageLogInOut manageLogInOut;
    HomePagePlatform homePagePlatform;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    TextBoxDatabase textBoxDatabase;

/**
*<li> Case ID:123066.</li>
*<li> Test Case Name: Sign In /Sign Out.</li>
*/
@BeforeEach
public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar= new NavigationToolbar(this);
    userAddManagement= new UserAddManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
        $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
}

@Test
public  void test01_SignInSignOut() {
    info("Test 1: Sign In /Sign Out");
    /*Step Number: 1
    *Step Name: Step 1: Sign In portal
    *Step Description:
        - Click Sign In link
        - Input user and password
        - Click Sign In button
    *Input Data:

    *Expected Outcome:
        - Sign In successfully*/
    ELEMENT_PLF_HOMEPAGE_DISPLAY.waitUntil(Condition.visible, Configuration.timeout);
    /*Step number: 2
    *Step Name: Sign Out portal
    *Step Description:
        - Click Sign out button
    *Input Data:

    *Expected Outcome:
        - Sign Out successfully*/
    manageLogInOut.signOut();
    ELEMENT_PLF_HOMEPAGE_DISPLAY.waitUntil(Condition.not(Condition.visible),Configuration.timeout);
 }

/**
*<li> Case ID:123067.</li>
*<li> Test Case Name: Register new account in public mode.</li>
*Pending : To register need to verify a captcha, impossible to check it with selenium
*/
@Test
public  void test02_RegisterNewAccountInPublicMode() {
    info("Test 2: Register new account in public mode");
    /*Step Number: 1
    *Step Name: Step 1: Register new account
    *Step Description:
        - Click Register
        - Input all data require fields with valid value
        - Click Save
    *Input Data:

    *Expected Outcome:
        - New account is created successfully with valid input values*/

    /*Step number: 2
    *Step Name: Step 2: Check after add new user
    *Step Description:
        - Login with new user
    *Input Data:

    *Expected Outcome:
        - Login successfully and new user display with username or display name*/

}

/**
*<li> Case ID:123068.</li>
*<li> Test Case Name: Remember my login.</li>
*/
@Test
public  void test03_RememberMyLogin() {
    info("Test 3: Remember my login");
    /*Step Number: 1
    *Step Name: Step 1: Remember my login
    *Step Description:
        - Click Sign In link
        - Input user and password
        - Check Remember my login
        - Click Sign In button
    *Input Data:

    *Expected Outcome:
        When user opens the GateIn again, User automatically go to private mode and do not have to sign in*/
   manageLogInOut.signIn(DATA_USER2, DATA_PASS);
   ELEMENT_PLF_HOMEPAGE_DISPLAY.waitUntil((Condition.visible),Configuration.timeout);

 }

/**
*<li> Case ID:123069.</li>
*<li> Test Case Name: Create new account in private mode.</li>
*/
@Test
public  void test04_CreateNewAccountInPrivateMode() {
    info("Test 4: Create new account in private mode");
    /*Step Number: 1
    *Step Name: Step 1: Create new account
    *Step Description:
        - Go to Administration/users/Add users
        - Input all data require fields
        - Click Save
    *Input Data:

    *Expected Outcome:
        - New account is created successfully with valid input values*/

    /*Step number: 2
    *Step Name: Step 2: Check after add new user
    *Step Description:
        - Login with new user
    *Input Data:

    *Expected Outcome:
        - Login successfully and new user display with username or display name*/
  String username= "username"+ getRandomNumber();
  String pass= "123456";
  String email = "email"+ getRandomNumber()+"@gmail.com";
  String firstname= "firstname";
  String lastname= "lastname";
  navigationToolbar.goToAddUser();
  userAddManagement.addUser(username, pass,email, firstname, lastname);
  manageLogInOut.signOut();
  manageLogInOut.signInCas(username, pass);
  ELEMENT_PLF_HOMEPAGE_DISPLAY.waitUntil(Condition.visible, Configuration.timeout);
 }
}