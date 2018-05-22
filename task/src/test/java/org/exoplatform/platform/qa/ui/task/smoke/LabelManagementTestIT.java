package org.exoplatform.platform.qa.ui.task.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.task.pageobject.LabelsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TASKS_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TITLE_OF_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 16/06/17.
 */
@Tag("smoke")
@Tag("task")
public class LabelManagementTestIT extends Base {

    LabelsManagement labelsManagement;

    HomePagePlatform homePagePlatform;

    TasksManagement tasksManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        labelsManagement = new LabelsManagement(this);
        tasksManagement = new TasksManagement(this);
    }

    @Test
    public void test01_Add_Label() {
        String label = "label" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        info("Add label");
        labelsManagement.addLabel(label);
        $(byText(label)).should(Condition.exist);
        info("delete label");
        labelsManagement.deleteLabel(label);
    }
    @Test
    public void test02_Edit_Label() {
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
    public void test03DeleteLabel() {
        String label = "label" + getRandomNumber();
        homePagePlatform.goToTaskPage();
        info("Add label");
        labelsManagement.addLabel(label);
        info("delete label");
        labelsManagement.deleteLabel(label);
        $(byText(label)).waitUntil(Condition.disappears, Configuration.timeout);
    }

    @Test
    public void test04_addTaskInLabel() {
        String task = "task" + getRandomNumber();
        String label = "label" + getRandomNumber();
        info("add task in label");
        homePagePlatform.goToTaskPage();
        labelsManagement.addLabel(label);
        $(byText(label)).click();
        ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.hasText(label),Configuration.timeout);
        tasksManagement.addTask(task);
        ELEMENT_TASKS_LIST.find(byText(task)).parent().find(byText(label)).should(Condition.exist);
        homePagePlatform.goToTaskPage();
        tasksManagement.deleteTask(task);
        labelsManagement.deleteLabel(label);

    }
}
