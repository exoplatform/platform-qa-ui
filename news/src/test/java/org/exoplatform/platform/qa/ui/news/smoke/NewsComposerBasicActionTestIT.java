package org.exoplatform.platform.qa.ui.news.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
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

import static org.exoplatform.platform.qa.ui.news.NewsLocator.*;
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

  @Test
  public void test10_CheckNewsDetailsPage_AfterPostAnArticle () {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();
    // String author = "author" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String redactor="redactor";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    info("Create a space and invite a user");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("give  the role redactor for user ");
    newsComposerManagement.addUserRedactor(redactor, spaceName,username1);
    info("user accept invitation to the space ");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceName);

    info("Redactor posts an article and checks news details page ");

    newsComposerManagement.createNews(newsTitle, newsContent);

    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);
    newsComposerManagement.verifyNewsButtonEditExists();
    newsComposerManagement.verifyNewsButtonShareExists();
    newsComposerManagement.verifyNewsAuthor(username1);
    newsComposerManagement.verifySpaceNameIsVisible();
    newsComposerManagement.verifyNewsDetailsPublicationDateVisible();


    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root","gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  public void test11_PinArticle_From_CreationForm() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String publisher="publisher";

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

    info("user publisher post and pin  an article ");
    newsComposerManagement.openNewsComposer();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillTitleField(newsTitle);

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillBodyField(newsContent);

    newsComposerManagement.verifyPostButton(true);

    newsComposerManagement.pinNewsFromCreationForm();
    newsComposerManagement.postNews();
    newsComposerManagement.ConfirmPinUnPinArticle();
    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    info("check pinned article in Bloc News ");
    homePagePlatform.goToHomePage();
    BLOC_LATEST_NEWS.shouldBe(Condition.visible);
    NEWS_PINNED_ARTICLE.shouldBe(Condition.visible);

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root","gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();


  }

  @Test
  public void test12_PinArticleFromNewsDetails() {

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

    newsComposerManagement.pin_unpin_news_details ();

    newsComposerManagement.ConfirmPinUnPinArticle();

    ALERT_SUCCESS.waitUntil(Condition.visible, Configuration.timeout);

    info("check pinned article in News CLV ");
    homePagePlatform.goToHomePage();
    BLOC_LATEST_NEWS.shouldBe(Condition.visible);
    NEWS_PINNED_ARTICLE.shouldBe(Condition.visible);

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
  public void test13_UnpinArticleFromNewsDetails () {


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

    info("user 'publisher' pin an article ");
    NEWS_DETAILS_BUTTON_PIN_UNPIN.click();
    newsComposerManagement.ConfirmPinUnPinArticle();
    ALERT_SUCCESS.waitUntil(Condition.visible,Configuration.timeout);

    info("user 'publisher' unpin Article ");
    ALERT_SUCCESS.waitUntil(Condition.not(Condition.visible),Configuration.collectionsTimeout);
    NEWS_DETAILS_BUTTON_PIN_UNPIN.waitUntil(Condition.visible,Configuration.timeout).click();
    newsComposerManagement.ConfirmPinUnPinArticle();
    ALERT_SUCCESS.waitUntil(Condition.visible,Configuration.timeout);

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
  public void test14_UNPinArticle_From_CreationForm() {

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    String publisher="publisher";

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
    info("user publisher post and pin  an article ");

    newsComposerManagement.openNewsComposer();

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillTitleField(newsTitle);

    newsComposerManagement.verifyPostButton(false);

    newsComposerManagement.fillBodyField(newsContent);

    newsComposerManagement.verifyPostButton(true);

    newsComposerManagement.pinNewsFromCreationForm();
    newsComposerManagement.postNews();
    newsComposerManagement.ConfirmPinUnPinArticle();
    newsComposerManagement.verifyNewsDetailsTitle(newsTitle);
    newsComposerManagement.verifyNewsDetailsBody(newsContent);

    info("user 'publisher' Unpin article ");
    newsComposerManagement.openNewsEditor();
    newsComposerManagement.unpinNewsFromCreationForm();
    newsComposerManagement.verifyUpdateButton(true);
    newsComposerManagement.updateNews();
    newsComposerManagement.ConfirmPinUnPinArticle();

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    manageLogInOut.signOut();
    manageLogInOut.signIn("root","gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    homePagePlatform.goToAllSpace();
  }

}
