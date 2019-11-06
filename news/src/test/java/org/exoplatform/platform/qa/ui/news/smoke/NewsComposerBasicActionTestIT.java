package org.exoplatform.platform.qa.ui.news.smoke;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.news.pageobject.NewsComposerManagement;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.switchTo;
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

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  public void test02_EditANewsInASpace() {
    info("Root creates a space");

    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();

    String newsTitle = "newsTitle" + getRandomNumber();
    String newsTitleUpdated = newsTitle + " updated";
    String newsContent = "newsContent" + getRandomNumber();

    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);
    homePagePlatform.goToSpecificSpace(spaceName);

    newsComposerManagement.createNews(newsTitle, newsContent);

    newsComposerManagement.openNewsEditor();

    newsComposerManagement.verifyUpdateButton(false);

    newsComposerManagement.verifyNewsEditorTitle(newsTitle);

    newsComposerManagement.fillTitleField(newsTitleUpdated);

    newsComposerManagement.verifyUpdateButton(true);

    newsComposerManagement.updateNews();

    newsComposerManagement.verifyNewsDetailsTitle(newsTitleUpdated);

    // cleanup
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(spaceName, false);
  }
}
