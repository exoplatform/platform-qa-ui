package org.exoplatform.platform.qa.ui.task.pageobject;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * This class will define all actions on Home page of the feature
 */
public class TaskManagementHome {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public TaskManagementHome(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

}
