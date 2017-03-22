package org.exoplatform.platform.qa.ui.selenium.platform.administration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentSearchAdministration {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ContentSearchAdministration(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

}
