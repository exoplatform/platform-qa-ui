package org.exoplatform.platform.qa.ui.news.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.news.NewsLocator.ELEMENT_EDIT_BUTTON_UPDATE;
import static org.exoplatform.platform.qa.ui.selenium.locator.news.NewsLocator.ELEMENT_NEWS_SUMMARY;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import com.codeborne.selenide.Condition;

public class EditNews {

  private final TestBase       testBase;

  public PlatformPermission    per;

  public ManageAlert           alert;

  public PlatformBase          plf;

  private ElementEventTestBase evt;

  public EditNews(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.plf = new PlatformBase(testBase);
  }

  /*
   * public void pinNewsFromEditForm() {
   * $(ELEMENT_EDIT_PIN_CHECKBOX).shouldBe(Condition.visible).click(); }
   */

  public void addSummaryToNews(String newsSummary) {
    info("--Set news summary");
    ELEMENT_NEWS_SUMMARY.setValue(newsSummary);
    info("--Update news");
    ELEMENT_EDIT_BUTTON_UPDATE.shouldBe(Condition.enabled).click();
    info("--News updated");
  }

}
