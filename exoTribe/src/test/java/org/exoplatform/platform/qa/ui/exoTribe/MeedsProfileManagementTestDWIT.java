package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeUserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeMeedsProfile")
public class MeedsProfileManagementTestDWIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform homePagePlatform;

  TribeUserProfilePage tribeUserProfilePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    tribeUserProfilePage = new TribeUserProfilePage(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);

  }

  @Test
  public void test01_CheckTheUserAvatarAndCoverInProfilePage() {

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Check That User Cover is displayed in Profile Page");
    ELEMENT_PROFILE_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check That User Avatar is displayed in Profile Page");
    ELEMENT_PROFILE_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check That User Fullname is displayed in Profile Page");
    Assert.assertEquals(ELEMENT_PROFILE_FULLNAME_DW.getText(), tribe_user3);

    info("Check That User Job is displayed in Profile Page");
    Assert.assertEquals(ELEMENT_PROFILE_JOB_DW.getText(), "IT Engineer");

  }

  @Test
  public void test02_CheckContactInformationsInProfilePage() {

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Check That Profile Contact Fullname is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_TITLE_DW.getText(), "Contact information");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FULLNAME_DW.getText(), tribe_user3);

    ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    String contactEmail = ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.getText();
    info("Check That Profile Contact Email is displayed" + contactEmail);

    info("Check That Profile Contact Job is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), "IT Engineer");

    manageLogInOut.signInTribe(tribe_username4, tribe_password4);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Check That User Job is not displayed in Profile Page");
    Assert.assertEquals(ELEMENT_PROFILE_JOB_DW.getText(), " ");

    info("Check That Profile Contact Job is not displayed");
    Assert.assertNotEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), "IT Engineer");

  }

  @Test
  public void test03_UpdateUserBasicInformations() {

    String newFirstName = "Bilel";
    String newLastName = "Dridi";
    String job = "IT Engineer";
    String newJob = "Solutions Consultant";
    String newMail = "Bilel.Dridi@exo.com";

    manageLogInOut.signInTribe(tribe_username, tribe_password);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Update Contact Basic Informations");
    tribeUserProfilePage.updateBasicInformationTribe(newFirstName, newLastName, newMail, newJob);

    info("Check That Profile Contact New Fullname is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FULLNAME_DW.getText(), newFirstName + " " + newLastName);

    ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    String contactEmail = ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.getText();
    info("Check That Profile Contact New Email is displayed" + newMail);

    info("Check That Profile Contact New Job is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), newJob);

    info("Reset Contact Basic Informations");
    tribeUserProfilePage.updateBasicInformationTribe(tribe_user.split(" ")[0], tribe_user.split(" ")[1], tribe_mail, job);

    info("Check That Profile Contact Fullname is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FULLNAME_DW.getText(), tribe_user);

    ELEMENT_PROFILE_CONTACT_INFORMATION_EMAIL_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    info("Check That Profile Contact Email is displayed" + tribe_mail);

    info("Check That Profile Contact Job is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_JOBTITLE_DW.getText(), "IT Engineer");

  }

  @Test
  public void test04_UpdateAndAddNewUserOtherInformations() {

    String company = "eXo";
    String phone = "50870088";
    String secondPhone = "71703865";
    String skype = "khalil.riahi12";
    String github = "kriahi12";
    String profileUrl = "https://community-preprod.exoplatform.com/portal/dw/profile/" + tribe_username;
    String secondUrl = "https://www.linkedin.com/";

    manageLogInOut.signInTribe(tribe_username, tribe_password);

    info("Go to Profile Page");
    homePagePlatform.goToProfilesPageTribeViaUrl();

    info("Update Contact Other Informations");
    tribeUserProfilePage.updateContactOtherInformationsTribe(company, TribeUserProfilePage.PhoneType.WORK, phone, TribeUserProfilePage.InstantMessagingType.SKYPE, skype, profileUrl);

    info("Check That Profile Contact Company is displayed");
    sleep(2000);
    ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), company);

    info("Check That Profile Contact Phone is displayed" + phone);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_PHONE_DW.getText(), "Professionnel: " + phone);

    info("Check That Profile Contact Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_INSTANT_MESSAGING_DW.getText(), "Skype: " + skype);

    info("Check That Profile Contact Url is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_URL_DW.getText(), profileUrl);

    tribeUserProfilePage.goToUpdateContactInformationsTribe();

    info("Add Second Phone Number");
    tribeUserProfilePage.addNewPhoneNumber();
    tribeUserProfilePage.enterNewPhoneNumber(TribeUserProfilePage.PhoneType.HOME, secondPhone);

    info("Add Second Instant Messaging");
    tribeUserProfilePage.addNewMessagingInstant();
    tribeUserProfilePage.enterNewMessagingInstant(TribeUserProfilePage.InstantMessagingType.GITHUB, github);

    info("Add Second Url");
    tribeUserProfilePage.addNewUrl();
    tribeUserProfilePage.enterNewUrl(secondUrl);

    info("Save Updated Informations");
    tribeUserProfilePage.saveUpdatedInformations();

    info("Check That Profile Contact Company is displayed");
    sleep(2000);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), company);

    info("Check That Profile Contact First Phone is displayed" + phone);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION__FIRST_PHONE_DW.getText(), "Professionnel: " + phone);

    info("Check That Profile Contact Second Phone is displayed" + secondPhone);
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_PHONE_DW.getText(), "Accueil: " + secondPhone);

    info("Check That Profile Contact First Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_FIRST_INSTANT_MESSAGING_DW.getText(), "Skype: " + skype);

    info("Check That Profile Contact Second Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_INSTANT_MESSAGING_DW.getText(), "Github: " + github);

    info("Check That Profile Contact First Url is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION__FIRST_URL_DW.getText(), profileUrl);

    info("Check That Profile Contact Second Url is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_SECOND_URL_DW.getText(), secondUrl);

    info("Reset Contact Informations");

    tribeUserProfilePage.goToUpdateContactInformationsTribe();

    tribeUserProfilePage.enterNewPhoneNumber(TribeUserProfilePage.PhoneType.OTHER, " ");

    tribeUserProfilePage.enterNewMessagingInstant(TribeUserProfilePage.InstantMessagingType.OTHER, " ");

    tribeUserProfilePage.enterNewUrl(" ");

    tribeUserProfilePage.saveUpdatedInformations();

    tribeUserProfilePage.updateContactOtherInformationsTribe("", TribeUserProfilePage.PhoneType.OTHER, " ", TribeUserProfilePage.InstantMessagingType.GITHUB, github, " ");

    info("Check That Profile Contact Company is not displayed");
    sleep(2000);
    Assert.assertNotEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), company);

    info("Check That Profile Contact Instant Messaging is displayed");
    Assert.assertEquals(ELEMENT_PROFILE_CONTACT_INFORMATION_COMPANY_DW.getText(), "Github: " + github);

  }

}
