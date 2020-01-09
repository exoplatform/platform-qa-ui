package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_POST_IN_TOPIC;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;

/**
 * @author eXo
 */
@Tag("forum")
@Tag("sniff")
public class ForumPostTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumForumManagement    forumForumManagement;

  ForumTopicManagement    forumTopicManagement;

  ForumHomePage           forumHomePage;

  ForumCategoryManagement forumCategoryManagement;

  ManageLogInOut          manageLogInOut;

  NavigationToolbar       navigationToolbar;

  UserPageBase            userPageBase;

  String                  nameCat;

  String                  nameForum;

  String                  nameTopic;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    userPageBase = new UserPageBase(this);
    navigationToolbar = new NavigationToolbar(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  /**
   * Create a category, forum and topic
   */
  public void prepareData() {
    info("Create data test");
    nameCat = "Category" + getRandomNumber();
    nameForum = "Forum" + getRandomNumber();
    nameTopic = "Topic" + getRandomNumber();
    String description = "Description" + getRandomNumber();
    info("Finished creating data test");
    info("Open forum portlet");
    homePagePlatform.goToForum();
    info("Add a new category");
    forumCategoryManagement.addCategorySimple(nameCat, "", description);
    info("Add a new forum");
    forumForumManagement.addForumSimple(nameForum, "", description);
    info("Add a new topic");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(nameTopic, description, "", "");
    forumHomePage.goToTopic(nameTopic);
  }

  /**
   * Delete data test
   */
  public void deletaData() {
    info("Open forum portlet");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    executeJavaScript("window.scrollBy(0,-550)");
    homePagePlatform.goToForum();
    info("Go to Forum home page");
    forumHomePage.goToHomeCategory();
    $(byText(nameCat)).click();
    info("Delete category");
    forumCategoryManagement.deleteCategory(nameCat);
  }

  /**
   * <li>Case ID:116750.</li>
   * <li>Test Case Name: Add a post.</li>
   * <li>Case ID:116751.</li>
   * <li>Test Case Name: Edit a post.</li>
   * <li>Case ID:116753.</li>
   * <li>Test Case Name: Quote a post.</li>
   * <li>Case ID:116752.</li>
   * <li>Test Case Name: Delete a post.</li>
   * <li>Case ID:116754.</li>
   * <li>Test Case Name: Add a private post.</li>
   */
  @Test
  public void test01_Add__Post() {
    info("Test 1: Add a post");
    String title = "Title" + getRandomNumber();
    String content = "Content" + getRandomNumber();
    prepareData();
    info("Reply a topic");
    forumTopicManagement.postReply(title, content);
    info("Test 4: Delete a post");
    info("Click on delete button of the post that is replied");
    $(byText(content)).parent().parent().parent().parent().find(ELEMENT_BUTTON_DELETE_POST).click();
    info("Click on OK button of the confirm popup");
    $(ELEMENT_DELETE_BOX_CONFIRMATION).waitUntil(visible,Configuration.timeout).click();
    info("Verify that the replied post is deleted");
    $(ELEMENT_POST_IN_TOPIC).find(byText(title)).shouldNot(Condition.exist);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(visible,Configuration.timeout).click();
    info("Delete data");
    deletaData();
  }

  @Test
  public void test02_Edit__Post() {
    info("Test 2: Edit a post");
    String title = "Title" + getRandomNumber();
    String content = "Content" + getRandomNumber();
    prepareData();
    info("Reply a topic");
    forumTopicManagement.postReply(title, content);

    info("Test 2: Edit a post");
    forumTopicManagement.editPost(title, content);
    info("Verify that the post is edited");
    $(byText(content + content)).should(Condition.exist);
    info("Test 4: Delete a post");
    info("Click on delete button of the post that is replied");
    $(byText(content + content)).parent().parent().parent().parent().find(ELEMENT_BUTTON_DELETE_POST).click();
    info("Click on OK button of the confirm popup");
    $(ELEMENT_DELETE_BOX_CONFIRMATION).click();
    info("Verify that the replied post is deleted");
    $(ELEMENT_POST_IN_TOPIC).find(byText(title)).shouldNot(Condition.exist);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    info("Delete data");
    deletaData();

  }

  @Test
  public void test03_Quote__Post() {
    info("Test 3: Quote a post");
    String title = "Title" + getRandomNumber();
    String content = "Content" + getRandomNumber();
    String newTitle = "NewTitle" + getRandomNumber();

    prepareData();
    info("Reply a topic");
    forumTopicManagement.postReply(title, content);
    info("Test 3: Quote a post");
    info("Quote a post");
    forumTopicManagement.quotePost(content, newTitle);
    info("Verify that quote a post successfully");
    homePagePlatform.refreshUntil($(byText(newTitle)),Condition.visible,1000);
    info("Test 4: Delete a post");
    info("Click on delete button of the post that is replied");
    $(byText(newTitle)).parent().parent().parent().parent().find(ELEMENT_BUTTON_DELETE_POST).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Click on OK button of the confirm popup");
    $(ELEMENT_DELETE_BOX_CONFIRMATION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Verify that the replied post is deleted");
    $(ELEMENT_POST_IN_TOPIC).find(byText(title)).shouldNot(Condition.exist);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Delete data");
    deletaData();

  }

  @Test
  public void test04_Private__Post_For_Topic() {
    info("Test 4: Add a Private post");
    String title = "Title" + getRandomNumber();
    String content = "Content" + getRandomNumber();
    String newTitle = "NewTitle" + getRandomNumber();
    prepareData();
    info("Reply a topic");

    forumTopicManagement.privatePostfortopic(newTitle, content);
    info("Test 4: Add a Private post");
    info("Click on delete button of the post that is replied");
    $(byText(content)).parent().parent().parent().parent().waitUntil(Condition.visible,Configuration.timeout).find(ELEMENT_BUTTON_DELETE_POST).click();
    info("Click on OK button of the confirm popup");
    $(ELEMENT_DELETE_BOX_CONFIRMATION).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Verify that the replied post is deleted");
    $(ELEMENT_POST_IN_TOPIC).find(byText(title)).shouldNot(Condition.exist);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Delete data");
    deletaData();

  }

  @Test
  public void test05_Private__Post_from_Post() {
    info("Test 1: Add a Private post from post");
    String title = "Title" + getRandomNumber();
    String content = "Content" + getRandomNumber();
    String newTitle = "NewTitle" + getRandomNumber();

    prepareData();
    info("Reply a topic");
    forumTopicManagement.postReply(title, content);

    info("Test 5: Add a Private post from post");
    forumTopicManagement.privatePostFromPost(newTitle, content);
    info("Click on delete button of the post that is replied");
    $(byText(content)).parent().parent().parent().parent().find(ELEMENT_BUTTON_DELETE_POST).click();
    info("Click on OK button of the confirm popup");
    $(ELEMENT_DELETE_BOX_CONFIRMATION).click();
    info("Verify that the replied post is deleted");
    $(ELEMENT_POST_IN_TOPIC).find(byText(title)).shouldNot(Condition.exist);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    info("Delete data");
    deletaData();

  }

}
