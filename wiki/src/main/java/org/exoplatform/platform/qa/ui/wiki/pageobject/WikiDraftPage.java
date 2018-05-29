package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
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
   * @param title The title of a wiki's page to be deleted.
   */
  public void deleteDraft(String title) {
    $(byText(title + "(New Page)")).parent().parent().parent().find(byClassName("uiIconDeleteDraft")).click();
    alert.acceptAlert();
  }

  /**
   * resume a draft
   *
   * @param title String
   */
  public void resumeADraft(String title) {
    info("Click on the title of the draf in the list");
    // $(byXpath("UIWikiDraftGrid")).find(byText(title + "(New Page)")).click();

    $(byText(title + "(New Page)")).click();

  }
}
