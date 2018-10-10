package org.exoplatform.platform.qa.ui.platform.chat.Functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TABLE_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("chat")
@Tag("sniff")
public class ChatAssignTaskTestIT extends Base {

    HomePagePlatform       homePagePlatform;
    ManageLogInOut         manageLogInOut;
    UserAndGroupManagement userandgroupmanagement;
    UserAddManagement      userAddManagement;
    RoomManagement         roomManagement;
    ProjectsManagement     projectsManagement;
    NavigationToolbar      navigationToolbar;
    ConnectionsManagement  connectionsManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        userAddManagement = new UserAddManagement(this);
        userandgroupmanagement = new UserAndGroupManagement(this);
        roomManagement= new RoomManagement(this);
        projectsManagement=new ProjectsManagement(this);
        navigationToolbar =new NavigationToolbar(this);
        connectionsManagement= new ConnectionsManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2 );
    }

     @Test
     public void test01_CheckTaskWithNoAssignee() throws InterruptedException {
        String room =  "room" +getRandomNumber();
        String task =  "task" +getRandomNumber();
        String usernamea = "usernamea" + getRandomNumber();
        String password = "123456" + getRandomNumber();
        String emaila = "emaila" + getRandomNumber() + "@gmail.com";
        String FirstNameA ="FirstNameA";
        String LastNameA = "LastNameA";
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(usernamea, password, emaila, FirstNameA, LastNameA);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room,usernamea);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(usernamea,password);
         homePagePlatform.goToChat();
         switchTo().window(1);
         ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
         switchToParentWindow();
         manageLogInOut.signOut();
         manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2 );
         homePagePlatform.goToChat();
         switchTo().window(1);
         ELEMENT_CONTACT_LIST.find(byText(room)).click();
         ELEMENT_COLLABORATION_ACTIONS.click();
         ELEMENT_CHAT_CREATE_TASK.click();
         ELEMENT_CHAT_TASK_NAME.setValue(task);
         ELEMENT_CHAT_POST_TASK_BUTTON.click();
         switchToParentWindow();
        homePagePlatform.goToTaskPage();
        $(byText(room)).should(Condition.exist);
        $(byText(room)).click();
        ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).should(Condition.exist);
        projectsManagement.deleteProject(room);
        switchTo().window(1);
        roomManagement.deleteRomm(room);
     }
     @BugInPLF("CHAT-982")
     @Test
    public void test02_CheckTaskWithAssignee(){

         String room =  "room" +getRandomNumber();
         String task =  "task" +getRandomNumber();
         String usernamea = "usernamea" + getRandomString();
         String password = "123456";
         String email = "email" + getRandomNumber() + "@test.com";
         String FirstName = "FirstName"+getRandomString();
         String LastName = "LastName"+getRandomString();
         navigationToolbar.goToAddUser();
         userAddManagement.addUser(usernamea, password, email, FirstName, LastName);
         homePagePlatform.goToChat();
         switchTo().window(1);
         roomManagement.addRoom(room,usernamea);
         switchToParentWindow();
         manageLogInOut.signOut();
         manageLogInOut.signInCas(usernamea,password);
         homePagePlatform.goToChat();
         switchTo().window(1);
         ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible,Configuration.timeout);
         switchToParentWindow();
         manageLogInOut.signOut();
         manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2 );
         homePagePlatform.goToChat();
         switchTo().window(1);
         ELEMENT_CONTACT_LIST.find(byText(room)).click();
         ELEMENT_COLLABORATION_ACTIONS.click();
         ELEMENT_CHAT_CREATE_TASK.click();
         ELEMENT_CHAT_TASK_NAME.setValue(task);
         ELEMENT_CHAT_ASSIGNEE_TASK.setValue(FirstName);
         ELEMENT_CHAT_RESULT_SEARCH_ASSIGNEE.waitUntil(Condition.visible,Configuration.timeout);
         ELEMENT_CHAT_ASSIGNEE_TASK.pressEnter();
         ELEMENT_CHAT_DUE_DATE_TASK.click();
         ELEMENT_CHAT_CURRENT_DATE_TASK.click();
         ELEMENT_CHAT_POST_TASK_BUTTON.click();
     }
}