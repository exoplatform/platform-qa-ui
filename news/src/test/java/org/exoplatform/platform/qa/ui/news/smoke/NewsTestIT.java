package org.exoplatform.platform.qa.ui.news.smoke;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.news.NewsLocator.ELEMENT_DETAILS_SPAN_SUMMARY;
import static org.exoplatform.platform.qa.ui.selenium.locator.news.NewsLocator.ELEMENT_SPACE_NEWS_ACTIVITY_INPUT_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.Assert.assertEquals;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.news.pageobject.EditNews;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

/**
 * @author mehboussada
 */

@Tag("news")
@Tag("smoke")
public class NewsTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar     navigationToolbar;

  SpaceManagement       spaceManagement;

  HomePagePlatform      homePagePlatform;

  SpaceHomePage         spaceHomePage;

  ManageLogInOut        manageLogInOut;

  EditNews              editNews;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);
    spaceManagement = new SpaceManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    editNews = new EditNews(this);
  }

  /**
   * <li>Test Case Name: Pin News.</li>
   */
  @Test
  public void test01_PinNews() {

    String space = "space" + getRandomNumber();
    String newsTitle = "news title" + getRandomNumber();
    String newsDescription = "news description" + getRandomNumber();
    String newsSummary = "news summary" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Go to the created space");
    // homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(space);
    info("Go to the news tab");
    spaceHomePage.goToNewsTab();
    info("Create news");
    spaceHomePage.addNews(newsTitle, newsDescription);
    $(byXpath(ELEMENT_SPACE_NEWS_ACTIVITY_INPUT_TITLE.replace("${newsName}", newsTitle))).should(Condition.appears);
    info("Go to news details form");
    spaceHomePage.goToNewsDetails(newsTitle);
    info("Go to news edit form");
    spaceHomePage.goToNewsEditForm(newsTitle);
    info("Add summary to news and update news");
    editNews.addSummaryToNews(newsSummary);

    $(ELEMENT_DETAILS_SPAN_SUMMARY).should(Condition.exist);
    $(ELEMENT_DETAILS_SPAN_SUMMARY).should(Condition.visible);
    assertEquals(newsSummary, $(ELEMENT_DETAILS_SPAN_SUMMARY).getText());

    spaceManagement.deleteSpace(space, false);
  }

}
