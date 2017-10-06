package org.exoplatform.platform.qa.ui.task.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_ICON_MARK_AS_COMPLETED;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TASKS_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.task.pageobject.LabelsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;

/**
 * Created by exo on 08/06/17.
 */
@Tag("smoke")
@Tag("task")
public class TaskManagementTestIT extends Base {
  HomePagePlatform homePagePlatform;

  TasksManagement tasksManagement;

  LabelsManagement labelsManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    tasksManagement = new TasksManagement(this);
    labelsManagement = new LabelsManagement(this);
  }

  @Test
  public void test01_AddTask() {
    String task = "task" + getRandomNumber();
    info("add task");
    homePagePlatform.goToTaskPage();
    tasksManagement.addTask(task);
    $(byText(task)).should(Condition.exist);

  }

}
