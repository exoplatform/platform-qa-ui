package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

/**
 * Created by exo on 02/06/17.
 */
@Tag("forum")
@Tag("sniff")

public class ForumManagementInSpaceUsingNormalUserTestIT extends Base {

  UserAddManagement      userAddManagement;

  ArrayList<String>      arrayPage;

  NavigationToolbar      navigationToolbar;

  ManageLogInOut         manageLogInOut;

  UserAndGroupManagement userAndGroupManagement;

  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  ForumForumManagement   forumForumManagement;

  ForumTopicManagement   forumTopicManagement;

  ForumHomePage          forumHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);

    arrayPage = new ArrayList<String>();
    userAddManagement = new UserAddManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas("john", "gtngtn");
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  @Test
  public void startNewTopicInSpace() {

    String space = "space startNewTopicInSpaceNU" ;
    String topic = "topic startNewTopicInSpaceNU";
    info("Create a space");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    info("start topic");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToForumsTab();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    info("verify topic");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToForumsTab();
    forumHomePage.goToTopic(topic);
    $(byText(topic)).waitUntil(Condition.appears, Configuration.timeout);


  }

}
