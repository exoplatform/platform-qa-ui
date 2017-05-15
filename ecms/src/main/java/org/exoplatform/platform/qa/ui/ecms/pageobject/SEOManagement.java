package org.exoplatform.platform.qa.ui.ecms.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class SEOManagement {

  private final TestBase       testBase;

  public ManageAlert           magAlert;

  private ElementEventTestBase evt;

  public SEOManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.magAlert = new ManageAlert(testBase);
  }

  /**
   * Delete a added language
   * 
   * @param language
   */
  public void deleteLanguage(String language) {
    evt.click(ELEMENT_SEO_SELECTED_LANGUAGE.replace("${language}", language));
    evt.click(ELEMENT_SEO_DELETE);
    magAlert.acceptAlert();
    evt.click(ELEMENT_SEO_CLOSE);
  }

}
