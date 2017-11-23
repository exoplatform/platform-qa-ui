package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentList {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ContentList(TestBase testBase) {

    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a folder or a content or both in Multiple Content Selector Pane popup
   *
   * @param content String
   * @param path String
   */
  public void selectFolderContent(String path, String content) {

    String[] arrayPath = path.split("/");
    if ($(byText(arrayPath[1])).waitUntil(Condition.appears, 10000).is(Condition.appears))
      $(byId("UIContentBrowsePanelOne")).findAll(byClassName("OddItem")).get(1).find(byClassName("Item")).click();
    else {
      for (String arrayElement : arrayPath) {
        $(byText(arrayElement)).click();
      }
    }

  }

}
