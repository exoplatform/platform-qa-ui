package org.exoplatform.platform.qa.ui.selenium.platform.taskmanagement;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * This class will define actions about management tasks
 */

public class ManagementLabels {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ManagementLabels(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }
}
