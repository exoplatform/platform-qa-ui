package org.exoplatform.platform.qa.ui.platform.social.functional;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("functional")
@Tag("social")
public class SpaceTemplateTestIT extends Base {

    NavigationToolbar navigationToolbar;

    AddUsers addUsers;

    ManageLogInOut manageLogInOut;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    HomePagePlatform homePagePlatform;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        homePagePlatform = new HomePagePlatform(this);
        addUsers = new AddUsers(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    @Test
    public void test01_ChecktheDisplayOfTheComboListInCreateSpaceDrawer() {

        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        manageLogInOut.signIn(username1, password);

        info("create a new space");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);

    }

    @Test
    public void test02_CheckCommunicationTemplate() {
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";
        String information = "Interpersonal communication where a space groups are involved in exchange of ideas, skills and interests.";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        manageLogInOut.signIn(username1, password);

        info("create a new space");
        String spaceNamea = "spaceNameb" + getRandomNumber();
        String spaceDesa = "descriptionb" + getRandomNumber();
        String spaceTemp = "Communication";
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpacewithspecificTemplate(spaceNamea, information, spaceDesa, spaceTemp);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    @Test
    public void test03_CheckProjectTemplate() {
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";
        String information = "A project space where members coordinate on tasks toward a predefined outcome.";

        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        manageLogInOut.signIn(username1, password);

        info("create a new space");
        String spaceNamea = "spaceNamec" + getRandomNumber();
        String spaceDesa = "descriptionc" + getRandomNumber();
        String spaceTemp = "Project";
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpacewithspecificTemplate(spaceNamea, information, spaceDesa, spaceTemp);

        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    @Test
    public void test04_CheckTeamTemplate() {
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";
        String information = "A central destination for members of a team.";

        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        manageLogInOut.signIn(username1, password);

        info("create a new space");
        String spaceNamea = "spaceNamed" + getRandomNumber();
        String spaceDesa = "descriptiond" + getRandomNumber();
        String spaceTemp = "Team";
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpacewithspecificTemplate(spaceNamea, information, spaceDesa, spaceTemp);

        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);


    }
}