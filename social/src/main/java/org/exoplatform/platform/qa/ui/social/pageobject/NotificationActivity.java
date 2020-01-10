package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class NotificationActivity {
  private final TestBase       testBase;

  public PlatformBase          plf;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase TestBase
   */
  public NotificationActivity(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.plf = new PlatformBase(testBase);
  }

  /**
   * function: check like in activity viewer
   * 
   * @param number number of like
   */
  public void checkLikeInActivityViewer(String number) {
    info("Check like in Activity viewer");
    $(ELEMENT_ACTIVITY_ICON_LIKE).parent().shouldHave(Condition.text(number));
  }

}
