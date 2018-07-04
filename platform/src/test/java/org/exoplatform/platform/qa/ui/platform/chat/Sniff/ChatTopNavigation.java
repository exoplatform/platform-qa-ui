package org.exoplatform.platform.qa.ui.platform.chat.Sniff;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;

public class ChatTopNavigation extends Base {
    HomePagePlatform       homePagePlatform;

    ChatManagement         chatManagement;

    ManageLogInOut         manageLogInOut;

    ConnectionsManagement  connectionsManagement;

    SpaceManagement        spaceManagement;

    SpaceHomePage          spaceHomePage;

    SpaceSettingManagement spaceSettingManagement;

    RoomManagement         roomManagement;

    WikiHomePage           wikiHomePage;

@BeforeEach
   public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        connectionsManagement = new ConnectionsManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        chatManagement = new ChatManagement(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        roomManagement = new RoomManagement(this);
        wikiHomePage = new WikiHomePage(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
}

@Test
@BugInPLF("8067")
public void test01_check_chat_change_status() {
    chatManagement.changeStatus("Available");
   assertEquals("rgba(70, 165, 70, 1)",$(byAttribute("class","uiIconStatus uiNotifChatIcon")).getCssValue("color"));
    chatManagement.changeStatus("Do not disturb");
    assertEquals("rgba(199, 34, 34, 1)",$(byAttribute("class","uiIconStatus uiNotifChatIcon")).getCssValue("color"));
    chatManagement.changeStatus("Away");
    assertEquals("rgba(253, 140, 64, 1)",$(byAttribute("class","uiIconStatus uiNotifChatIcon")).getCssValue("color"));
    chatManagement.changeStatus("Invisible");
    assertEquals("rgba(153, 153, 153, 1)",$(byAttribute("class","uiIconStatus uiNotifChatIcon")).getCssValue("color"));
}}