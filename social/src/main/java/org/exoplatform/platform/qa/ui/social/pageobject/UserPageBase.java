package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class UserPageBase {

  private final TestBase       testBase;

  public HomePagePlatform homePagePlatform;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase TestBase
   */
  public UserPageBase(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.homePagePlatform=new HomePagePlatform(testBase);
  }

  /**
   * Go to my wiki
   */
  public void goToWikiTab() {
    info("Go to my wiki");
    evt.click(ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);
  }

  /**
   * Go to my activity tab
   */
  public void goToActivityTab() {
    info("Go to activity tab");
    homePagePlatform.refreshUntil($(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES),Condition.visible,1000);
    $(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES).click();
  }
}
