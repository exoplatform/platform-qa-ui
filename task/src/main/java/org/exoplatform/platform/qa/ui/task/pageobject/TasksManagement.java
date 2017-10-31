package org.exoplatform.platform.qa.ui.task.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * This class will define actions about management project
 */
public class TasksManagement {

  private final TestBase       testBase;

  public TaskManagementHome    tasHome;

  private ElementEventTestBase evt;

  public TasksManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.tasHome = new TaskManagementHome(testBase);
  }

  /**
   * Select an option in Task List
   * 
   * @param op is an option in the list as: Incoming,All tasks,...
   */
  public void selectOptionTask(optionTask op) {
    info("--Open Task Management--");
    tasHome.goToTasks();
    switch (op) {
    case Incoming:
      info("Select Incomming option");
      break;
    case All_Tasks:
      info("Select All tasks option");
      break;
    case Overdue:
      info("Select Overdue option");
      break;
    case Today:
      info("Select Today option");
      break;
    case Tommorrow:
      info("Select Tommorrow option");
      break;
    case Upcoming:
      info("Select Upcoming option");
      break;
    default:
      info("No option in the list.Please select correct option.");
      break;
    }
  }

  /**
   * Open Sorting list of Incoming
   */
  public void goToSortIncomingList() {
    info("Click on Sort list");
  }

  /**
   * Open Group list in Incoming
   */
  public void goToGroupIncoming() {
    info("Click on Group list");
  }

  public void addTask(String task) {

    ELEMENT_BUTTON_ADD_TASK.click();
    ELEMENT_INPUT_TASK_TITLE.setValue(task).pressEnter();
    ELEMENT_TASK_FORM.waitUntil(Condition.appears, Configuration.timeout);
  }

  public void editTask(String task, String newTask, String priority) {
    ELEMENT_TASKS_LIST.find(byText(task)).click();
    ELEMENT_TASK_FORM.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_TASK_FORM.find(byText(task)).click();
    ELEMENT_TASK_FORM_INPUT_TITLE.setValue(newTask);
    ELEMENT_TASK_FORM_PRIORITY.click();
    ELEMENT_TASK_SELECT_PRIORITY.waitUntil(Condition.appears, Configuration.timeout).selectOption(priority);
  }

  public void deleteTask(String task) {
    ELEMENT_TASKS_LIST.find(byText(task)).click();
    ELEMENT_TASK_FORM.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_TASK_FORM_ICOND_DROP_DOWN_MENU.click();
    ELEMENT_TASK_BUTTON_DELETE.click();
    ELEMENT_TASK_BUTTON_DELETE_OK.click();
  }

  public void addCoworker(String task) {
    $(byText(task)).click();
    ELEMENT_EDIT_ASSIGNEE.click();
    COWORKER_INPUT_FIELD.setValue("Mary");
    COWORKER_FILED.waitUntil(Condition.visible,Configuration.timeout);
    COWORKER_INPUT_FIELD.pressEnter();
  }

  public void commentTask(String task, String comment) {
    $(byText(task)).click();
    SelenideElement frame= ELEMENT_INPUT_COMMENT_TASK;
    switchTo().frame(frame);
    COMMENT_INPUT_AREA.sendKeys(comment);
    switchTo().defaultContent();
    COMMENT_BUTTON.click();
  }

  public void deleteComment (String task, String comment) {
    $(byText(task)).click();
    $(byText(comment)).hover();
    $(byText(comment)).parent().parent().parent().find(byClassName("uiIconTrashMini")).click();
    refresh();
    $(byText(comment)).shouldNot(Condition.exist);
  }

  public void deleteReply (String task, String reply){
    $(byText(task)).click();
    $(byText(reply)).hover();
    $(byText(reply)).parent().parent().find(byClassName("uiIconTrashMini")).click();
    $(byText(reply)).shouldNot(Condition.exist);
  }
  public void replyToCommentTask(String task, String reply, String user) {
    $(byText(task)).click();
    ELEMENT_LABEL_REPLY_TASK.click();
    SelenideElement frame= ELEMENT_INPUT_COMMENT_TASK;
    switchTo().frame(frame);
    COMMENT_INPUT_AREA.sendKeys(reply);
    switchTo().defaultContent();
    COMMENT_BUTTON.click();
    $(byText(reply)).parent().parent().parent().parent().find(byText(user)).should(Condition.exist);
  }

  public void replyToReply(String task,String reply,String replytoreply, String user) {
    $(byText(task)).click();
    $(byText(reply)).parent().parent().find(byClassName("replyCommentLink")).click();
    SelenideElement frame= ELEMENT_INPUT_COMMENT_TASK;
    switchTo().frame(frame);
    COMMENT_INPUT_AREA.sendKeys(replytoreply);
    switchTo().defaultContent();
    COMMENT_BUTTON.click();
    $(byText(replytoreply)).parent().parent().parent().parent().find(byText(user)).should(Condition.exist);
  }

  public void showAllReplies(String task, String comment) {
    $(byText(task)).click();
    String idDataComment = $(byText(comment)).parent().parent().getAttribute("data-commentid");
    // Get id Comment button
    $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK_TASK.replace("{id}", idDataComment))).waitUntil(Condition.appears, Configuration.timeout).findElementByClassName("subCommentShowAllLink").click();
  }

  /**
   * Define options in Task list
   */
  public enum optionTask {
    Incoming, All_Tasks, Overdue, Today, Tommorrow, Upcoming;
  }
}
