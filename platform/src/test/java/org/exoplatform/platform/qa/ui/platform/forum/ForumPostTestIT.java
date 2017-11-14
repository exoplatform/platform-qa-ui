package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

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
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }

    manageLogInOut.signInCas(username, password);
  }

  /**
   * Create a category, forum and topic
   */
  public void prepareData() {
    info("Create data test");
    nameCat = "Category prepareData" ;
    nameForum = "Forum prepareData" ;
    nameTopic = "Topic prepareData" ;
    String description = "Description prepareData" ;
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
    String title = "Title Add Post" ;
    String content = "Content Add Post";
    prepareData();
    info("Reply a topic");
    forumTopicManagement.postReply(title, content);

  }

}
