package org.exoplatform.platform.qa.ui.task.smoke;

import com.codeborne.selenide.Condition;
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
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_ICON_MARK_AS_COMPLETED;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TASKS_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 08/06/17.
 */
@Tag("smoke")
@Tag("task")
public class TaskManagementTestIT extends Base {
  HomePagePlatform homePagePlatform;

  TasksManagement tasksManagement;

  LabelsManagement labelsManagement;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    tasksManagement = new TasksManagement(this);
    labelsManagement = new LabelsManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  @Test
  public void test01_AddEditDelete_Task() {
    String task = "task" + getRandomNumber();
    String newTask = "newTask" + getRandomNumber();
    info("add task");
    homePagePlatform.goToTaskPage();
    tasksManagement.addTask(task);
    info("edit task");
    tasksManagement.editTask(task, newTask, "High");
    $(byText(newTask)).should(Condition.exist);
    info("delete task");
    tasksManagement.deleteTask(newTask);

  }

  @Test
  public void test02_MarkTaskAsCompleted() {
    String task = "task" + getRandomNumber();
    info("add task");
    homePagePlatform.goToTaskPage();
    tasksManagement.addTask(task);
    info("mark task as completed");
    ELEMENT_TASKS_LIST.find(byText(task)).parent().find(ELEMENT_ICON_MARK_AS_COMPLETED).click();
    $(byText(task)).shouldNot(Condition.exist);

  }

}
