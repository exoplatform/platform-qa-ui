package org.exoplatform.platform.qa.ui.task.pageobject;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

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

  public void addTask(String taskContent) {

    ELEMENT_BUTTON_ADD_TASK.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_TASK_TITLE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(taskContent).pressEnter();
    ELEMENT_TASK_FORM.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    $(byText(taskContent)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
  }

  public void editTask(String taskContent, String newTask, String priority) {
    ELEMENT_TASKS_LIST.find(byText(taskContent)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_FORM.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).find(byText(taskContent)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    ELEMENT_TASK_FORM_INPUT_TITLE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(newTask);
    sleep(2000);
    ELEMENT_TASK_FORM_INPUT_TITLE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).pressEnter();
    ELEMENT_TASK_FORM_PRIORITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_SELECT_PRIORITY.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).sendKeys(priority);
    sleep(2000);
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
  }

  public void deleteTask(String taskContent) {
    ELEMENT_TASKS_LIST.find(byText(taskContent)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_FORM.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_TASK_FORM_ICOND_DROP_DOWN_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_BUTTON_DELETE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_BUTTON_DELETE_OK.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byText(taskContent)).should(Condition.exist);
  }

  public void deleteTaskDW(String taskContent) {
    $(byXpath("//*[@class='table-project']//*[@class='column-item column-title taskName' and contains(text(),'${taskContent}')]".replace("${taskContent}", taskContent))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_FORM.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_TASK_FORM_ICOND_DROP_DOWN_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_BUTTON_DELETE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TASK_BUTTON_DELETE_OK.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byText(taskContent)).should(Condition.not(Condition.exist));
  }

  public void addCoworker(String task) {
    $(byText(task)).click();
    ELEMENT_EDIT_ASSIGNEE.click();
    COWORKER_INPUT_FIELD.setValue("Mary");
    COWORKER_FILED.waitUntil(Condition.visible,Configuration.timeout);
    COWORKER_INPUT_FIELD.pressEnter();
  }

  public void commentTask(String taskContent, String comment) {
    $(byText(taskContent)).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    SelenideElement frame= ELEMENT_INPUT_COMMENT_TASK;
    switchTo().frame(frame);
    COMMENT_INPUT_AREA.sendKeys(comment);
    switchTo().defaultContent();
    COMMENT_BUTTON.click();
  }

  public void deleteComment (String task, String comment) {
    $(byText(task)).click();
    $(byText(comment)).waitUntil(Condition.visible,Configuration.timeout).hover();
    $(byText(comment)).parent().parent().parent().find(byClassName("uiIconTrashMini")).click();
    refresh();
    $(byText(comment)).shouldNot(Condition.exist);
  }

  public void deleteReply (String task, String reply){
    $(byText(task)).click();
    $(byText(reply)).hover();
    $(byText(reply)).parent().parent().parent().find(byClassName("uiIconTrashMini")).click();
    $(byText(reply)).shouldNot(Condition.exist);
  }

  public void replyToCommentTask(String taskContent, String reply, String user) {
    $(byText(taskContent)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_LABEL_REPLY_TASK.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    SelenideElement frame= ELEMENT_INPUT_COMMENT_TASK;
    switchTo().frame(frame);
    COMMENT_INPUT_AREA.sendKeys(reply);
    switchTo().defaultContent();
    COMMENT_BUTTON.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byText(reply)).parent().parent().parent().parent().find(byText(user)).should(Condition.exist);
  }

  public void replyToReply(String task,String reply,String replytoreply, String user) {
    $(byText(task)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byText(reply)).parent().parent().find(byClassName("replyCommentLink")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    SelenideElement frame= ELEMENT_INPUT_COMMENT_TASK;
    switchTo().frame(frame);
    COMMENT_INPUT_AREA.sendKeys(replytoreply);
    switchTo().defaultContent();
    COMMENT_BUTTON.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byText(replytoreply)).parent().parent().parent().parent().find(byText(user)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
  }

  public void showAllReplies(String task, String comment) {
    $(byText(task)).click();

    String idDataComment = $(byText(comment)).parent().parent().parent().getAttribute("data-commentid");
    // Get id Comment button
    $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK_TASK.replace("{id}", idDataComment))).waitUntil(Condition.visible, Configuration.timeout).find(".subCommentShowAllLink").click();
  }
}
