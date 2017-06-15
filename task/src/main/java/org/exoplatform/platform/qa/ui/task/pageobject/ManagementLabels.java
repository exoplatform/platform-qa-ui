package org.exoplatform.platform.qa.ui.task.pageobject;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;

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
public void addLabel(String label){
  ELEMENT_LABEL_ICON_ADD_LABEL.click();
  ELEMENT_ADD_LABEL.click();
  ELEMENT_INPUT_LABEL.setValue(label).pressEnter();

  }
}

