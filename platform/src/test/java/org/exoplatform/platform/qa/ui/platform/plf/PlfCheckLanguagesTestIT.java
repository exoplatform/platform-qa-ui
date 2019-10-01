package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_DELETE_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_DELETE_POPUP_OK;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_LINK_SETUP;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ChangeLanguages;
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

  ChangeLanguages changeLanguages;

  ActivityStream activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    changeLanguages= new ChangeLanguages (this);
    activityStream=new ActivityStream (this);
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

  @Test
  public void test02_addActivity_on_spanichlanguage() {
    info("Test 1: add activity on spanich language");
    // change language
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("Spanish - Spain","Apply");
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.deleteactivity(activity1);
    info("the activity is removed successfully");
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("Inglés","Aplicar");
  }

  @Test
  public void test03_addActivity_on_Portugueselanguage() {
    info("Test 1: add activity on Portuguese - Brazil language");
    // change language
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("Portuguese - Brazil","Apply");
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.deleteactivity(activity1);
    info("the activity is removed successfully");
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("Inglês","Aplicar");
  }

  @Test
  public void test04_checkBadUIContentManagementStatusColumnWidthLimitAfterLanguageChange() {
    //8336
    String columnName ="Etat de publication";
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("French","Apply");    // change language
    $(ELEMENT_LINK_SETUP).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(Configuration.timeout);
    do {
      $(byXpath("//li[@class='dropdown-submenu']/a[text()='Contenu']")).hover();
    } while (!$(byXpath("(//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Administration de Contenu')]/preceding::a[1])[1]")).exists());
    $(byXpath("(//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Administration de Contenu')]/preceding::a[1])[1]")).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Site Explorer is shown successfully");
    navigationToolbar.verifyColumnName(columnName);
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("English","Appliquer");
  }
}
