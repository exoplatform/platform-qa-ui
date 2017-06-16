package org.exoplatform.platform.qa.ui.task.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.task.pageobject.ManagementLabels;
import org.exoplatform.platform.qa.ui.task.pageobject.ManagementTasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_ICON_MARK_AS_COMPLETED;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TASKS_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TEXT_INCOMING;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 08/06/17.
 */
@Tag("smoke")
@Tag("task")
public class TaskManagementTestIT extends Base {
    HomePagePlatform homePagePlatform;
    ManagementTasks managementTasks;
    ManagementLabels managementLabels;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        managementTasks =new ManagementTasks(this);
        managementLabels=new ManagementLabels(this);
    }
    @Test
    public void test01_02_03_AddEditDeleteTask(){
        String task="task"+getRandomNumber();
        String newTask="newTask"+getRandomNumber();
        info("add task");
        homePagePlatform.goToTaskPage();
        managementTasks.addTask(task);
        info("edit task");
        managementTasks.editTask(task,newTask,"High");
        info("delete task");
        managementTasks.deleteTask(newTask);



    }
    @Test
    public void test04_MarkTaskAsCompleted(){
        String task="task"+getRandomNumber();
        info("add task");
        homePagePlatform.goToTaskPage();
        managementTasks.addTask(task);
        info("mark task as completed");
        ELEMENT_TASKS_LIST.find(byText(task)).parent().find(ELEMENT_ICON_MARK_AS_COMPLETED).click();
        $(byText(task)).shouldNot(Condition.exist);

    }

}
