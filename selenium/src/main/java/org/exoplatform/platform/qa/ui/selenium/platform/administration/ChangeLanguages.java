package org.exoplatform.platform.qa.ui.selenium.platform.administration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
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
    $(ELEMENT_CHANGE_LANGUAGE_POPUP_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_CHANGELANGUAGE_LANGUAGE.replace("${language}", language))).click();
    $(byXpath(ELEMENT_AVATAR_CHANGELANGUAGE_APPLY.replace("${text}", applyText))).click();
  }
}
