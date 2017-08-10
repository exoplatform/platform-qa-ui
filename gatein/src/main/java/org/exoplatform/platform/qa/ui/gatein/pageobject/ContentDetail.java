package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_CONTENT_DETAIL_ADDPATH_BTN;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentDetail {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ContentDetail(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a folder or a content or both in Multiple Content Selector Pane popup
   *
   * @param arrayPath
   * @param content
   */
  public void selectFolderContent(String path, String content) {
    evt.waitForAndGetElement(ELEMENT_CONTENT_DETAIL_ADDPATH_BTN);
    $(byClassName("uiIconAddPath")).waitUntil(Condition.appears, Configuration.timeout).click();
    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      $(byText("Name")).click();
      $(byText(arrayElement)).click();

    }
    if (content != "" || content != null) {

      $(byClassName("Item")).click();
    }
  }

}
