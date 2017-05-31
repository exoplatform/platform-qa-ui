package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_DELETE_DRAFT;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_DRAFT_OF_NEW_PAGE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiDraftPage {
  private final TestBase       testBase;

  public ManageAlert           magAl;

  /**
   * constructor
   *
   * @param dr
   */
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
    evt.click(ELEMENT_DELETE_DRAFT.replace("${title}", title));
    alert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_DELETE_DRAFT.replace("${title}", title));
  }

  /**
   * resume a draft
   *
   * @param title
   */
  public void resumeADraft(String title) {
    info("Click on the title of the draf in the list");
    evt.waitForAndGetElement(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title), 3000, 0).click();

  }
}
