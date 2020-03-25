package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiDraftPage {
  private final TestBase       testBase;

  public ManageAlert           magAl;

  ManageAlert                  alert;

  private ElementEventTestBase evt;

  public WikiDraftPage(TestBase testBase) {
    this.alert = new ManageAlert(testBase);
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Deletes a draft.
   *
   */
  public void deleteDraft() {
    $(byXpath("(//i[@class=\"uiIconDeleteDraft uiIconLightGray\"])[1]")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    alert.acceptAlert();
  }

  /**
   * resume a draft
   *
   * @param title String
   */
  public void resumeADraft(String title) {

   info("Click on the title of the draf in the list");
    $(byId("UIWikiDraftGrid")).find(byText(title + "(New Page)")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();


  }
}
