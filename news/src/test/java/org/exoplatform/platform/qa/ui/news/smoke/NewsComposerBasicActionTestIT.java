package org.exoplatform.platform.qa.ui.news.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.news.pageobject.NewsComposerManagement;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("news")
@Tag("smoke")
public class NewsComposerBasicActionTestIT extends Base {
  HomePagePlatform homePagePlatform;
  ManageLogInOut manageLogInOut;
  NavigationToolbar navigationToolbar;
  SpaceHomePage spaceHomePage;
  SpaceManagement spaceManagement;
  NewsComposerManagement newsComposerManagement;


  @BeforeEach
  public void setupBeforeMethod() {
    Logger.info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    newsComposerManagement = new NewsComposerManagement(this);
  }

  @Test
  public void test01_CreateANewsInASpace() {
    info("Root creates a space");
    homePagePlatform.goToAllSpace();
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsContent = "newsContent" + getRandomNumber();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    homePagePlatform.goToSpecificSpace(spaceName);

    newsComposerManagement.postSimpleNews(newsTitle, newsContent);

    homePagePlatform.goToAllSpace();

    spaceManagement.deleteSpace(spaceName, false);
  }
}
