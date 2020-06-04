package org.exoplatform.platform.qa.ui.task.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.task.pageobject.LabelsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TASKS_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TITLE_OF_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 16/06/17.
 */
@Tag("smoke")
@Tag("task")
public class LabelManagementTestIT extends Base {

  LabelsManagement labelsManagement;

  HomePagePlatform homePagePlatform;

  TasksManagement tasksManagement;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    labelsManagement = new LabelsManagement(this);
    tasksManagement = new TasksManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  @Test
  public void test01_AddEditDelete_Label() {
    String label = "label" + getRandomNumber();
    String Newlabel = "Newlabel" + getRandomNumber();
    homePagePlatform.goToTaskPage();
    info("Add label");
    labelsManagement.addLabel(label);
    info("edit label");
    labelsManagement.editLabel(label, Newlabel);
    $(byText(Newlabel)).should(Condition.exist);
    info("delete label");
    labelsManagement.deleteLabel(Newlabel);
  }

  @Test
  public void test02_addTaskInLabel() {
    String task = "task" + getRandomNumber();
    String label = "label" + getRandomNumber();
    info("add task in label");
    homePagePlatform.goToTaskPage();
    labelsManagement.addLabel(label);
    $(byText(label)).click();
    ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.hasText(label), Configuration.timeout);
    tasksManagement.addTask(task);
    ELEMENT_TASKS_LIST.find(byText(task)).parent().find(byText(label)).should(Condition.exist);
    homePagePlatform.goToTaskPage();
    tasksManagement.deleteTask(task);
    labelsManagement.deleteLabel(label);

  }

}
