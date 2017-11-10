package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
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
 * @author dmardassi Date 24/07/2017
 */
@Tag("forum")
@Tag("sniff")
public class ForumForumMoreActionTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumForumManagement    forumForumManagement;

  ForumTopicManagement    forumTopicManagement;

  ForumHomePage           forumHomePage;

  ForumCategoryManagement forumCategoryManagement;

  ManageLogInOut          manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
      manageLogInOut.signInCas(username, password);
  }



  /**
   * CaseID: 116747 Case_name: Lock / Unlock a forum Steps: 1. Prepare data:
   * create a caterory, forum: - Create a category - Create a forum 2. Lock a
   * forum - Open this forum - Click on More Action, then click on Lock ==> Forum
   * is locked successfully. Normal user can not add topic into locked forum 3.
   * Unlock a forum - Open this forum - Click on More Action, then click on Unlock
   * ==> Forum is unlocked successfully
   */
  @Test
  public void test02_LockUnlockForum() {
    info("Lock / Unlock a forum");
    String cate = "Category" + getRandomNumber();
    String forum = "Forum" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();

    info("Add a category");
    forumCategoryManagement.addCategorySimple(cate, "", cate);

    info("Add a forum in the category");
    forumForumManagement.addForumSimple(forum, "", forum);

    info("lock the forum");
    forumForumManagement.lockAndUnlock(true);

    info("sign out and log in with user2");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Go to the forum");
    $(byText(forum)).click();
    $(ELEMENT_FORUM_START_TOPIC_DISABLE).should(Condition.exist);
    info("sign out and log in with user1");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Go to the forum");
    $(byText(forum)).click();
    info("unlock the forum");
    forumForumManagement.lockAndUnlock(false);

    info("sign out and log in with user2");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Go to the forum");
    $(byText(forum)).click();
    info("Verify that the forum is enabled");
    $(ELEMENT_FORUM_START_TOPIC_BUTTON).should(Condition.exist);

    info("log in back USER1");
    // manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Delete category");
    ELEMENT_CAT_CONTAINER.find(byText(cate)).click();
    forumCategoryManagement.deleteCategory(cate);

  }

  /**
   * CaseID: 116748 Case_name: Open / Close a forum Steps: 1. Prepare data: create
   * a caterory, forum: - Create a category - Create a forum 2. Close a forum -
   * Access 1 forum - Click on More Action, click on Close ==> Normal user can not
   * view closed forum 3. Open a forum - Access a closed forum - Click on More
   * Action, click on Open ==> This forum is opened, normal user can view this
   */
  @Test
  public void test03_OpenCloseForum() {
    info("Open / Close a forum");
    String cate = "Category" + getRandomNumber();
    String forum = "Forum" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();

    info("Add a category");
    forumCategoryManagement.addCategorySimple(cate, "", cate);

    info("Add a forum in the category");
    forumForumManagement.addForumSimple(forum, "", forum);

    info("Close the forum");
    forumForumManagement.closeAndOpen(true);

    info("sign out and log in with user2");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Verify that the forum is hided");
    $(byText(cate)).click();
    $(byText(forum)).shouldNot(Condition.exist);

    info("sign out and log in with user1");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Go to the forum");
    // forumHomePage.goToForum(forum);
    info("open the forum");
    $(byText(forum)).click();
    forumForumManagement.closeAndOpen(false);

    info("sign out and log in with user2");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Go to the forum");
    $(byText(forum)).click();
    info("log in back USER1");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    info("go to Forum home page");
    homePagePlatform.goToForum();

    info("Delete category");
    ELEMENT_CAT_CONTAINER.find(byText(cate)).click();
    forumCategoryManagement.deleteCategory(cate);

  }
}
