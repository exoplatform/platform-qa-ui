package org.exoplatform.platform.qa.ui.selenium.platform.administration;

import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ChangeLanguages {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ChangeLanguages(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a language and changed it
   * 
   * @param language
   */
  public void changeLanguage(String language, String applyText) {
    info("Select language and change it");
    evt.waitForAndGetElement(ELEMENT_CHANGE_LANGUAGE_POPUP_TITLE);
    evt.click(ELEMENT_CHANGELANGUAGE_LANGUAGE.replace("${language}", language));
    evt.click(ELEMENT_AVATAR_CHANGELANGUAGE_APPLY.replace("${text}", applyText));
  }
}
