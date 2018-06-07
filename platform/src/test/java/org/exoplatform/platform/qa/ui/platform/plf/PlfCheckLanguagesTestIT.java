package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byText;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_BUTTON_CANCEL_CHANGE_LANGUAGES;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_POPUP_LIST_OF_LANGUAGES;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

/**
 * Created by exo on 06/03/18.
 */
@Tag("plf")
@Tag("sniff")
public class PlfCheckLanguagesTestIT extends Base {
  NavigationToolbar navigationToolbar;

  ManageLogInOut    manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @Test
  public void test01_checkListOfLanguages() {
    navigationToolbar.goToChangeLanguage();
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Arabic")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Catalan")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Dutch")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("English")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Filipino")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("French")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("German")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Greek")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Indonesian")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Italian")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Japanese")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Persian")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Polish")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Portuguese - Brazil")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Portuguese - Portugal")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Romanian")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Russian")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Simplified Chinese")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Slovenian")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Spanish - Spain")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Turkish")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Ukrainian")).shouldBe(Condition.visible);
    ELEMENT_POPUP_LIST_OF_LANGUAGES.find(byText("Simplified Chinese")).shouldBe(Condition.visible);
    ELEMENT_BUTTON_CANCEL_CHANGE_LANGUAGES.click();
  }
}
