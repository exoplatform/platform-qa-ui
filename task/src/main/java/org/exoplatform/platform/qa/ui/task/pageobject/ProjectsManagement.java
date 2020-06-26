package org.exoplatform.platform.qa.ui.task.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;

/**
 * This class will define actions about management tasks
 */

public class ProjectsManagement {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ProjectsManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Add a new project
   *
   * @param title is project's name
   * @param des is project's description
   * @param enableCalendar = true if want to create a task calendar for the
   *          project in Calendar application = false if don't want.
   */
  public void addProject(String title, String des, boolean enableCalendar) {
    ELEMENT_PROJECT_ICON_ADD_PROJECT.waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_ADD_PROJECT.waitUntil(Condition.visible, Configuration.timeout).click();
    info("Create a new project");
    if (title != null || title != "") {
      info("Input title");
      $(ELEMENT_ADD_PROJECT_TITLE).waitUntil(Condition.visible, Configuration.timeout).setValue(title);
    }
    if (des != null || des != "") {
      info("Input description");
    }
    if (enableCalendar) {
      info("Enable Calendar intergration");
      evt.check(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    } else {
      info("Disable Calendar intergration");
      evt.uncheck(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    }
    ELEMENT_SAVE_PROJECT.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byText(title)).should(Condition.visible);
  }

  /**
   * Edit a project
   *
   * @param projectPath is project's names as Project1/sub_Project1
   * @param title is a new project's title to edit
   * @param des is a new description of the project to edit
   * @param opt 0 and opt[0]==true if want to input new title/description with
   *          keeping all old title/description 0 or inf0 if want to input new
   *          title/description after cleared all old title/description sup1 and
   *          opt[1]eg true if want to enable calendar integration sup0 and inf1 if
   *          want to disable or uncheck calendar integration
   */
  public void editProject(String projectPath, String title, String newTitle, String des, boolean... opt) {
    ELEMENT_LIST_PROJECT.find(byText(projectPath)).click();
    ELEMENT_LIST_PROJECT.find(byText(projectPath)).parent().parent().find(ELEMENT_ICON_PROJECT).click();
    ELEMENT_LIST_PROJECT.find(byText(projectPath)).parent().parent().find(ELEMENT_EDIT_PROJECT_OPTION).click();
    if (title != null && title != "") {
      info("Input title");
      ELEMENT_POPUB_EDIT_PROJECT.find(byText(title)).click();
// Input a new title with clearing an old title
      ELEMENT_EDIT_PROJECT.setValue(newTitle).pressEnter();
    }
    if (des != null && des != "") {
      info("Input description");
      $(byClassName("prjDescription")).click();
    }

    if (opt.length >= 1 && opt[0] == true) {
      info("Enable Calendar intergration");
      ELEMETN_EDIT_PROJECT_ENABLE_CALENDAR_CHECKBOX.click();
    } else {
      info("Disable Calendar intergration");
      evt.uncheck(ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX, 2);
    }
    saveEditProject();
  }

  public void deleteProject(String title) {
    $(byText(title)).click();
    $(byText(title)).parent().parent().find(ELEMENT_ICON_PROJECT).click();
    $(byText(title)).parent().parent().find(ELEMENT_DELETE_PROJECT_OPTION).click();
    ELEMENT_CONFIRM_DELETE.click();
    $(byText(title)).should(Condition.not(Condition.visible));
  }

  public void deleteProjectTribe(String title) {
    $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}",title))).click();
    String id = $(byXpath("//*[@class='project-name' and contains(text(),'${title}')]".replace("${title}",title))).getAttribute("data-id");
    $(byXpath("//*[@class='dropdown project-item active' and @data-projectid='${id}']//i[@class='uiIconRightMenu uiIconLightGray']".replace("${id}",id))).click();
    $(byXpath("//*[@class='uiDropdownMenu project-menu' and @data-projectid='${id}']//*[@class='uiIconTrash uiIconLightGray']".replace("${id}",id))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CONFIRM_DELETE.click();
    $(byText(title)).waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);
  }

  /**
   * Save all changes when editing a project
   */
  public void saveEditProject() {
    info("Click on Save button");
    ELEMENT_BUTTON_SAVE.click();
  }
}