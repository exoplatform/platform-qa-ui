import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("chat")
@Tag("smoke")
public class RoomBaseAction extends Base {
    HomePagePlatform homePagePlatform;
    RoomManagement roomManagement;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    ManageLogInOut manageLogInOut;
    UserAndGroupManagement userandgroupmanagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
    homePagePlatform=new HomePagePlatform(this);
    roomManagement=new RoomManagement(this);
    navigationToolbar=new NavigationToolbar(this);
        userAddManagement=new UserAddManagement(this);
        manageLogInOut=new ManageLogInOut(this);
        userandgroupmanagement=new UserAndGroupManagement(this);
    }
    @Test
    public void Test01_AddRoomWithSeveralUsers(){
        String usernamea = "usernamea" + getRandomString();
        String usernameb = "usernameb" + getRandomString();
        String usernamec = "usernamec" + getRandomString();
        String password = "123456" ;
        String room="room"+getRandomNumber();

        String emaila = usernamea + getRandomNumber() + "@gmail.com";
        String emailb = usernameb + getRandomNumber() + "@gmail.com";
        String emailc = usernamec + getRandomNumber() + "@gmail.com";
        navigationToolbar.goToAddUser();
        info("Create new user");
        userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
        userAddManagement.addUser(usernameb, password, emailb, usernameb, usernamec);
        userAddManagement.addUser(usernamec, password, emailc, usernamec, usernamec);
        manageLogInOut.signIn(usernamea,password);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(usernameb,password);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(usernamec,password);
        homePagePlatform.goToChat();
        manageLogInOut.signIn("root","gtn");
        homePagePlatform.goToChat();

        switchTo().window("Chat");
        roomManagement.addRoom(room,3,usernamea,usernameb,usernamec);

        switchTo().window("Home Page");
        manageLogInOut.signIn(usernamea,password);
        homePagePlatform.goToChat();
        switchTo().window("Chat");
        $(byText(room)).should(Condition.exist);

        switchTo().window("Home Page");
        manageLogInOut.signIn(usernameb,password);
        homePagePlatform.goToChat();
        switchTo().window("Chat");
        $(byText(room)).should(Condition.exist);

        switchTo().window("Home Page");
        manageLogInOut.signIn(usernamec,password);
        homePagePlatform.goToChat();
        switchTo().window("Chat");
        $(byText(room)).should(Condition.exist);

        switchTo().window("Home Page");
        manageLogInOut.signIn("root","gtn");
        homePagePlatform.goToChat();
        switchTo().window("Chat");
        roomManagement.deleteRomm(room);


        switchTo().window("Home Page");
        navigationToolbar.goToManageCommunity();
        userandgroupmanagement.deleteUser(usernamea);
        userandgroupmanagement.deleteUser(usernameb);
        userandgroupmanagement.deleteUser(usernamec);

    }
}
