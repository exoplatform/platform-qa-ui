package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;

public class WikiSettingManagement {

  private final TestBase       testBase;

  private HomePagePlatform homePagePlatform;

  public ManageAlert           alert;

  private ElementEventTestBase evt;


  public WikiSettingManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    alert = new ManageAlert(testBase);
    this.homePagePlatform=new HomePagePlatform(testBase);

  }

public void addPreviewTemplate(String title,String description,String content){

    $(ELEMENT_TITLE_TEMPLATE).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).setValue(title);
    $(ELEMENT_DESCRIPTION_TEMPLATE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(description);
    $(ELEMENT_CONTENT_TEMPLATE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(content);
    ELEMENT_WIKI_PREVIEW_TEMPLATE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byText(title)).should(exist);
    $(byText(content)).should(exist);
    $(ELEMENT_CLOSE_PREVIEW_WINDOW).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SAVE_TEMPLATE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_WIKI_OK_SAVE_TEMPLATE.waitUntil(Condition.appears,Configuration.openBrowserTimeoutMs).click();
}
  /**
   * Edit a wiki template
   *
   * @param template String
   * @param text String
   * @param title String
   * @param subTitle String
   */
  public void editTemplate(String template, String title, String subTitle, String text) {
    if (title != "") {
      $(ELEMENT_TITLE_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).clear();
      $(ELEMENT_TITLE_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).setValue(title);
    }

    $(ELEMENT_SAVE_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).click();
  }

  public void deleteTemplate(String template) {
    info("Delete template " + template);
    $(byText(template)).parent().parent().find(ELEMENT_WIKI_ICON_DELETE_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).click();
    alert.acceptAlert();
    ELEMENT_WIKI_LISTE_TEMPLATE.find(byText(template)).shouldNot(Condition.exist);  }
}
