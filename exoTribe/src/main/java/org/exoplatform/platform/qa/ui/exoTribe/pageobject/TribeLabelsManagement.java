package org.exoplatform.platform.qa.ui.exoTribe.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;

/**
 * This class will define actions about management tasks
 */

public class TribeLabelsManagement {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public TribeLabelsManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  public void addLabel(String label) {
    ELEMENT_LABEL_ICON_ADD_LABEL.click();
    ELEMENT_ADD_LABEL.click();
    ELEMENT_INPUT_LABEL.setValue(label).pressEnter();
    $(byText(label)).should(Condition.visible);

  }

  public void editLabel(String label, String newLabel) {
    $(byText(label)).click();
    $(byText(label)).parent().parent().find(ELEMENT_ICON_OPEN_MENU_LABEL).click();
    $(byText(label)).parent().parent().find(ELEMENT_OPEN_MENU_EDIT_LABEL).click();
    $(byId("lblName")).setValue(newLabel);
    ELEMENT_SAVE_EDIT_LABEL.click();
    $(byText(newLabel)).should(Condition.exist);
  }

  public void deleteLabel(String label) {
    $(byText(label)).click();
    $(byText(label)).parent().parent().find(ELEMENT_ICON_OPEN_MENU_LABEL).click();
    $(byText(label)).parent().parent().find(ELEMENT_OPEN_MENU_DELETE_LABEL).click();
    executeJavaScript("window.scrollBy(0,250)");
    ELEMENT_LABEL_BUTTON_CONFIRM_DELETE.click();
    $(byText(label)).waitUntil(Condition.disappears, Configuration.timeout);

  }

}
