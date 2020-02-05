package org.exoplatform.platform.qa.ui.selenium.platform.administration;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ManageLayout {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public ManageLayout(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * Close the page editing form
   */
  public void abortPageUpdate() {
    $(ELEMENT_EDIT_PORTLET_ABORT).isDisplayed();
    $(ELEMENT_EDIT_PORTLET_ABORT).waitUntil(Condition.visible, Configuration.timeout).click();
  }
}
