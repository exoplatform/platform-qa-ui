package org.exoplatform.platform.qa.ui.news.smoke;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.news.pageobject.NewsComposerManagement;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;

import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;


@Tag("news")
@Tag("smoke")
public class NewsComposerBasicActionTestIT extends Base {

  HomePagePlatform homePagePlatform;
  ManageLogInOut manageLogInOut;
  NavigationToolbar navigationToolbar;
  SpaceHomePage spaceHomePage;
  SpaceManagement spaceManagement;
  NewsComposerManagement newsComposerManagement;
  AddUsers addUsers;
  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    Logger.info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    newsComposerManagement = new NewsComposerManagement(this);
    addUsers = new AddUsers(this);
    spaceSettingManagement = new SpaceSettingManagement(this);

  }

  @Test
  public void test01_CreateANewsInASpace() {
    info("Root creates a space");

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();

    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    homePagePlatform.goToSpecificSpace(spaceName);

    newsComposerManagement.openNewsComposer();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillTitleField(newsTitle);

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillBodyField(newsContent);

    newsComposerManagement.verifyPostButton(true);

    newsComposerManagement.postNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  public void test02_CreateANewsInASpaceeWithRedactorRole() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String redactor = "redactor";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("Create a space and invite a user");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("give  the role redactor for user ");
    newsComposerManagement.addUserRedactor(redactor, spaceName, username1);
    info("user accept invitation to the space ");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceName);
    info("user redactor post an article ");

    homePagePlatform.goToSpecificSpace(spaceName);
    newsComposerManagement.openNewsComposer();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillTitleField(newsTitle);

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillBodyField(newsContent);

    newsComposerManagement.verifyPostButton(true);

    newsComposerManagement.postNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  public void test03_CreateANewsInASpaceWithPublisherRole() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String publisher = "publisher";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("Create a space and invite a user");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("give  the role publisher for user ");
    newsComposerManagement.addUserPublisher(publisher, username1);

    info("user accept invitation to the space ");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceName);
    info("user publisher post an article ");

    homePagePlatform.goToSpecificSpace(spaceName);
    newsComposerManagement.openNewsComposer();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillTitleField(newsTitle);

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillBodyField(newsContent);

    newsComposerManagement.verifyPostButton(true);

    newsComposerManagement.postNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);

  }


  @Test
  public void test04_CreateANewsInASpaceWithSummary() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();
    String newsSummary = "newsSummary" + getRandomNumber();


    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("user create a space");
    manageLogInOut.signIn(username1, "123456");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("user (space manager) post an article with summary");
    newsComposerManagement.openNewsComposer();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillTitleField(newsTitle);

    newsComposerManagement.verifyPostButton(false);
    newsComposerManagement.fillSummaryField(newsSummary);
    newsComposerManagement.verifyPostButton(false);
    newsComposerManagement.fillBodyField(newsContent);

    newsComposerManagement.verifyPostButton(true);

    newsComposerManagement.postNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

  @Test
  public void test05_CreateANewsInASpaceWithIllustrationAndSummary() {


    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsSummary = "newsSummary" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String redactor = "redactor";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("Create a space and invite a user");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("give  the role redactor for user ");
    newsComposerManagement.addUserRedactor(redactor, spaceName, username1);
    info("user accept invitation to the space ");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceName);

    info("user redactor post an article with summary and illustration ");
    homePagePlatform.goToSpecificSpace(spaceName);
    newsComposerManagement.openNewsComposer();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillTitleField(newsTitle);
    newsComposerManagement.uploadIllustration();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillSummaryField(newsSummary);

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillBodyField(newsContent);

    newsComposerManagement.verifyPostButton(true);

    newsComposerManagement.postNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }


  @Test
  public void test06_EditANewsInASpace_SpaceManager() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsTitleUpdated = newsTitle + " updated";
    String newsContent = "newsContent" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("user create a space");
    manageLogInOut.signIn(username1, "123456");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("space manager edit a news");
    homePagePlatform.goToSpecificSpace(spaceName);
    newsComposerManagement.createNews(newsTitle, newsContent);

    newsComposerManagement.openNewsEditor();

    newsComposerManagement.verifyUpdateButton(false);

    newsComposerManagement.verifyNewsEditorTitle(newsTitle);

    newsComposerManagement.fillTitleField(newsTitleUpdated);

    newsComposerManagement.verifyUpdateButton(true);

    newsComposerManagement.updateNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitleUpdated);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test07_EditANewsInASpace_Redactor() {
    info("Root creates a space");

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsTitleUpdated = newsTitle + " updated";
    String newsContent = "newsContent" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String redactor = "redactor";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("Create a space and invite a user");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("give  the role redactor for user ");
    newsComposerManagement.addUserRedactor(redactor, spaceName, username1);
    info("user accept invitation to the space ");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceName);

    info("user redactor edit a news ");
    newsComposerManagement.createNews(newsTitle, newsContent);

    newsComposerManagement.openNewsEditor();

    newsComposerManagement.verifyUpdateButton(false);

    newsComposerManagement.verifyNewsEditorTitle(newsTitle);

    newsComposerManagement.fillTitleField(newsTitleUpdated);

    newsComposerManagement.verifyUpdateButton(true);

    newsComposerManagement.updateNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitleUpdated);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  public void test08_EditAndPostNewsInASpace_Redactor() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsTitleUpdated = newsTitle + " updated";
    String newsContent = "newsContent" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String redactor = "redactor";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("Create a space and invite a user");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("give  the role redactor for user ");
    newsComposerManagement.addUserRedactor(redactor, spaceName, username1);
    info("user accept invitation to the space ");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceName);

    info("Redactor edit and post a news ");

    homePagePlatform.goToSpecificSpace(spaceName);
    newsComposerManagement.createNews(newsTitle, newsContent);

    newsComposerManagement.openNewsEditor();

    newsComposerManagement.verifyUpdateButton(false);

    newsComposerManagement.verifyNewsEditorTitle(newsTitle);

    newsComposerManagement.fillTitleField(newsTitleUpdated);

    newsComposerManagement.verifyUpdateButton(true);

    newsComposerManagement.updateAndPostNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitleUpdated);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    // cleanup
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  public void test09_SpaceMember_CheckLinkWriteAnArticleIsNotDisplayed() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("Create a space and invite a user");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("user accept invitation to the space ");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceName);

    info("check link write an article is not displayed ");
    newsComposerManagement.verifyNewsComposerLinkVisible(false);

    // cleanup
    manageLogInOut.signOut();
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }

}
