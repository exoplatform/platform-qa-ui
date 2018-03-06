package org.exoplatform.platform.qa.ui.platform.task;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;

/**
 * Created by exo on 05/03/18.
 */
@Tag("task")
@Tag("sniff")
public class TaskCommentTestIT extends Base {
  HomePagePlatform homePagePlatform;

  TasksManagement  tasksManagement;

  ManageLogInOut   manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    tasksManagement = new TasksManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @Tag("TA-611")
  @Test
  @BugInPLF("TA-618")
  public void test01_commentTask_WithImage() {
    String taskName = "task" + getRandomNumber();
    homePagePlatform.goToTaskPage();
    tasksManagement.addTask(taskName);
    ELEMENT_INPUT_COMMENT_TASK.click();
    ELEMENT_ICON_ADD_IMAGE_IN_COMMENT.click();
    $(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BUTTON_OK_UPLOAD.waitUntil(Condition.not(Condition.attribute("disabled")), Configuration.timeout).click();
    COMMENT_BUTTON.waitUntil(Condition.enabled, Configuration.timeout).pressEnter();
    tasksManagement.deleteTask(taskName);
  }
}
