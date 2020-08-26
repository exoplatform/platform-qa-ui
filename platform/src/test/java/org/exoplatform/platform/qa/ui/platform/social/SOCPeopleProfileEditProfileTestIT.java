package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_USER_PROFILE_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase.scrollToBottomPage;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("sniff")
@Tag("social")
public class SOCPeopleProfileEditProfileTestIT extends Base {
  NavigationToolbar navigationToolbar;

  ManageLogInOut    manageLogInOut;

  AddUsers          addUsers;

  UserProfilePage   userProfilePage;

  UserAndGroupManagement userAndGroupManagement;

  HomePagePlatform homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    userAndGroupManagement= new UserAndGroupManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    addUsers = new AddUsers(this);
    userProfilePage = new UserProfilePage(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  @Test
  @Tag("SOC-6086")
  public void test07_CheckEmptyGenderIsSynchronizedInMangeUsers(){
    String username = "username" + getRandomString();
    String email = username + "@test.com";
    String password = "Aa123456";
    String organization = "organization" + getRandomString();
    String jobTitle = "jobTitle" + getRandomString();
    String jobDetail = "jobDetail" + getRandomString();
    String skill = "skill" + getRandomString();
    String dStart = getDate(-7, "MM/dd/yyyy");
    String dEnd = getDate(-1, "MM/dd/yyyy");
    String actdStart = getDate(-7, "MMMM dd, yyyy");
    String actdEnd = getDate(-1, "MMMM dd, yyyy");
    String actExperience = "Experience has been updated.";
    String aboutMe = "aboutMe" + getRandomNumber();
    String aboutMe1 = "aboutMe1" + getRandomNumber();
    String actAboutMe = "\"About me\" has been updated.";
    String actAvatar = "Avatar has been updated.";
    String im1 = "im1" + getRandomNumber();
    String im2 = "im2" + getRandomNumber();
    String im3 = "im3" + getRandomNumber();
    String im4 = "im4" + getRandomNumber();
    String im5 = "im5" + getRandomNumber();

    String phone1 = getRandomNumber() + getRandomNumber();
    String phone2 = getRandomNumber() + getRandomNumber();
    String phone3 = getRandomNumber() + getRandomNumber();
    String actContactInfo = "Contact information has been updated.";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    navigationToolbar.goToAddUser();
    $(ELEMENT_USER_PROFILE_TAB).click();
    $(ELEMENT_GENDER).shouldHave(Condition.exactValue(""));

    manageLogInOut.signIn(username,password);
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    scrollToBottomPage(this.getExoWebDriver().getWebDriver());
    $(ELEMENT_GENDER_EDIT_PROFILE).shouldHave(Condition.exactValue(""));

    info("edit profile");
    info("update about me");
    userProfilePage.updateAboutMe(aboutMe);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe))).should(Condition.exist);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actAboutMe))).should(Condition.exist);

    $(ELEMENT_EDIT_MY_PROFILE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("update about me");
    userProfilePage.updateAboutMe(aboutMe1);
    userProfilePage.saveCancelUpdateInfo(false);
    $(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe1))).shouldNot(Condition.exist);

    info("Edit Experience");
    $(ELEMENT_EDIT_MY_PROFILE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    userProfilePage.updateExperience(organization, jobTitle, jobDetail, skill, dStart, dEnd, false);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_COMPANY_INFO.replace("${company}", organization))).should(Condition.exist);
    $(byXpath(ELEMENT_POSITION_INFO.replace("${position}", jobTitle))).should(Condition.exist);
    $(byXpath(ELEMENT_JOB_DETAIL_INFO.replace("${description}", jobDetail))).should(Condition.exist);
    $(byText(skill)).should(Condition.exist);
    $(byXpath(ELEMENT_STARTDATE_INFO.replace("${date}", actdStart))).should(Condition.exist);
    $(byXpath(ELEMENT_ENDDATE_INFO.replace("${date}", actdEnd))).should(Condition.exist);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_STATUS.replace("${index}", "1"))).shouldHave(Condition.text(actExperience));

    info("edit avatar");
    $(ELEMENT_EDIT_MY_PROFILE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_CHANGE_AVATAR.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_UPLOAD_AVATAR.uploadFromClasspath("testavatar.png");
    ELEMENT_BUTTON_CONFIRM_UPLOAD.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    userProfilePage.saveCancelUpdateInfo(false);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_STATUS.replace("${index}", "1"))).shouldHave(Condition.text(actAvatar));

    info("update information");
    info("edit contact info");
    info("edit info");
    $(ELEMENT_EDIT_MY_PROFILE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    userProfilePage.updateBasicInformation(null, null, email);
    userProfilePage.updateIms("skype", im1, 0);
    userProfilePage.updateIms("gtalk", im2, 1);
    userProfilePage.updateIms("other", im3, 2);
    userProfilePage.updateIms("yahoo", im4, 3);
    userProfilePage.updateIms("msn", im5, 4);

    userProfilePage.updatePhone("work", phone1, 0);
    userProfilePage.updatePhone("home", phone2, 1);
    userProfilePage.updatePhone("other", phone3, 2);
    userProfilePage.saveCancelUpdateInfo(true);

    $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email))).should(Condition.exist);

    $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Work").replace("${phone}", phone1))).should(Condition.exist);
    $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Home").replace("${phone}", phone2))).should(Condition.exist);
    $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Other").replace("${phone}", phone3))).should(Condition.exist);

    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Skype").replace("${im}", im1))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Gtalk").replace("${im}", im2))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Other").replace("${im}", im3))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Yahoo").replace("${im}", im4))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Msn").replace("${im}", im5))).should(Condition.exist);

    $(byXpath(ELEMENT_RECENT_ACTIVITY_STATUS.replace("${index}", "1"))).shouldHave(Condition.text(actContactInfo));


    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToEditUserInfo(username);
    userAndGroupManagement.editUserInfo_UserProfileTab("", "", "", "", "female", "", "", "","");
    manageLogInOut.signIn(username,password);
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    scrollToBottomPage(this.getExoWebDriver().getWebDriver());
    $(ELEMENT_GENDER_EDIT_PROFILE).shouldHave(Condition.exactValue(""));
    $(ELEMENT_GENDER_EDIT_PROFILE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).selectOptionByValue("");
    $(ELEMENT_CONTACT_SAVE_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    homePagePlatform.goToHomePage();
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    scrollToBottomPage(this.getExoWebDriver().getWebDriver());
    $(ELEMENT_GENDER_EDIT_PROFILE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).shouldHave(Condition.exactValue(""));
    info("verify that empty gender is synchronized in manage users");
    manageLogInOut.signIn(DATA_USER1,DATA_PASS2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToEditUserInfo(username);
    $(ELEMENT_USER_PROFILE_TAB).click();
    $(ELEMENT_GENDER).shouldHave(Condition.exactValue(""));
    info("delete user");
    ELEMENT_BTN_SAVE_EDIT_USER.click();
    ELEMENT_BTN_SAVE_EDIT_USER.waitUntil(Condition.disappears, Configuration.timeout);
    $(byText("The user profile has been updated.")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText("OK")).click();
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username);
  }

}
