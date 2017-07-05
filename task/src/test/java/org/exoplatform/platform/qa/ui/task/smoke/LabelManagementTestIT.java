package org.exoplatform.platform.qa.ui.task.smoke;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.task.pageobject.ManagementLabels;
import org.exoplatform.platform.qa.ui.task.pageobject.ManagementTasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TASKS_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 16/06/17.
 */
@Tag("smoke")
@Tag("task")
public class LabelManagementTestIT extends Base {

    ManagementLabels managementLabels;

    HomePagePlatform homePagePlatform;

    ManagementTasks managementTasks;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        managementLabels = new ManagementLabels(this);
        managementTasks = new ManagementTasks(this);
    }

    @Test
    public void test01_02_03Add_Edit_DeleteLabel() {
        String label = "label" + getRandomNumber();
        String Newlabel = "Newlabel" + getRandomNumber();
        info("add task");
        homePagePlatform.goToTaskPage();
        info("Add label");
        managementLabels.addLabel(label);
        info("edit label");
        managementLabels.editLabel(label, Newlabel);
        info("delete label");
        managementLabels.deleteLabel(Newlabel);
    }

    @Test
    public void test04_addTaskInLabel() {
        String task = "task" + getRandomNumber();
        String label = "label" + getRandomNumber();
        info("add task");
        homePagePlatform.goToTaskPage();
        managementLabels.addLabel(label);
        $(byText(label)).click();
        managementTasks.addTask(task);
        ELEMENT_TASKS_LIST.find(byText(task)).parent().find(byText(label)).should(Condition.exist);
        homePagePlatform.goToTaskPage();
        managementTasks.deleteTask(task);
        managementLabels.deleteLabel(label);

    }
}
