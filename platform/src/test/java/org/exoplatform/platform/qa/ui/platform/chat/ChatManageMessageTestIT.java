package org.exoplatform.platform.qa.ui.platform.chat;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

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

/**
 * Created by ilyes on 16/02/18.
 */
@Tag("chat")
@Tag("sniff")
public class ChatManageMessageTestIT extends Base {
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
  @Tag("CHAT-808")
  public void test01_uploadFileInSpaceChatThenUserChat() {
    String space = "space" + getRandomNumber();
    info("create space and invite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    ELEMENT_INPUT_INVITE_USER.sendKeys(PLFData.DATA_USER2);
    $(ELEMENT_SPACE_BTN_INVITE).click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(PLFData.DATA_USER2);
    manageLogInOut.signIn(PLFData.DATA_USER2, "gtn");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    homePagePlatform.goToAllSpace();
    ELEMENT_SPACES_LIST.find(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).click();
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToChat();
    switchTo().window(1);
    info("upload file in space chat");
    $(byText(space)).click();
    chatManagement.uploadFile("eXo-Platform.png");
    info("upload file in user chat");
    if ($(byText(PLFData.DATA_NAME_USER2)).is(Condition.not(Condition.visible)))
      ELEMENT_CHAT_BUTTON_HIDE_OFF_LINE.click();
    $(byText(PLFData.DATA_NAME_USER2)).click();
    chatManagement.uploadFile("testavatar.png");
    switchTo().window(0);
    info("verify file uploaded in user chat exist in personal document");
    homePagePlatform.goToDocuments();
    ELEMENT_FOLDER_DOCUMENT.click();
    ELEMENT_LIST_DOCUMENTS.find(byText("eXo-Platform")).shouldNot(Condition.exist);
    ELEMENT_LIST_DOCUMENTS.find(byText("testavatar")).should(Condition.exist);
    info("verify file uploaded in space chat exist in space's documents");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceManagement.goToDocumentTab();
    ELEMENT_LIST_DOCUMENTS_IN_SPACE.find(byText("eXo-Platform.png")).should(Condition.visible);
    ELEMENT_LIST_DOCUMENTS_IN_SPACE.find(byText("testavatar.png")).shouldNot(Condition.visible);
    info("verify document uploaded in space chat exist in activity stream");
    homePagePlatform.goToHomePage();
    ELEMENT_CONTAINER_ACTIVITY.find(byAttribute("data-original-title", "eXo-Platform.png")).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  @Tag("CHAT-808")
  public void test02_uploadFileInUserChatThenSpaceChat() {
    String space = "space" + getRandomNumber();
    info("create space and invite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    ELEMENT_INPUT_INVITE_USER.sendKeys(PLFData.DATA_USER2);
    $(ELEMENT_SPACE_BTN_INVITE).click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(PLFData.DATA_USER2);
    manageLogInOut.signIn(PLFData.DATA_USER2, "gtn");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    homePagePlatform.goToAllSpace();
    ELEMENT_SPACES_LIST.find(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).click();
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToChat();
    switchTo().window(1);
    info("upload file in user chat");
    if ($(byText(PLFData.DATA_NAME_USER2)).is(Condition.not(Condition.visible)))
      ELEMENT_CHAT_BUTTON_HIDE_OFF_LINE.click();
    $(byText(PLFData.DATA_NAME_USER2)).click();
    chatManagement.uploadFile("testavatar.png");
    refresh();
    info("upload file in space chat");
    $(byText(space)).click();
    chatManagement.uploadFile("eXo-Platform.png");
    switchTo().window(0);
    info("verify file uploaded in user chat exist in personal document");
    homePagePlatform.goToDocuments();
    ELEMENT_FOLDER_DOCUMENT.click();
    ELEMENT_LIST_DOCUMENTS.find(byText("eXo-Platform")).shouldNot(Condition.exist);
    ELEMENT_LIST_DOCUMENTS.find(byText("testavatar")).should(Condition.exist);
    info("verify file uploaded in space chat exist in space's documents");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceManagement.goToDocumentTab();
    ELEMENT_LIST_DOCUMENTS_IN_SPACE.find(byText("eXo-Platform.png")).should(Condition.visible);
    ELEMENT_LIST_DOCUMENTS_IN_SPACE.find(byText("testavatar.png")).shouldNot(Condition.visible);
    info("verify document uploaded in space chat exist in activity stream");
    homePagePlatform.goToHomePage();
    ELEMENT_CONTAINER_ACTIVITY.find(byAttribute("data-original-title", "eXo-Platform.png")).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  @Tag("CHAT-829")
  public void test03_checkUserStatusWhenSendMessageInRoom() throws InterruptedException {
    String room = "room" + getRandomNumber();
    String message = "room" + getRandomNumber();
    manageLogInOut.signIn(PLFData.DATA_USER2, PLFData.password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    chatManagement.changeStatus("Available");
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room, PLFData.DATA_USER2);
    switchTo().window(0);
    manageLogInOut.signIn(PLFData.DATA_USER2, PLFData.password);
    chatManagement.changeStatus("Away");
    homePagePlatform.goToChat();
    switchTo().window(1);
    chatManagement.sendMessageInRoomOrSpace(room, message);
    switchTo().window(0);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToChat();
    switchTo().window(1);
    $(byText(room)).click();
    info("verify message");
    ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.visible);
    $(byClassName("buttonChangeStatus")).find(byClassName("chat-status-available")).should(Condition.visible);
    roomManagement.deleteRomm(room);

  }

  @Test
  @Tag("CHAT-821")
  public void test04_saveMeetingNote() {
    String room = "room" + getRandomNumber();
    String message = "room" + getRandomNumber();
    String message1 = "room" + getRandomNumber();
    String message2 = "room" + getRandomNumber();
    manageLogInOut.signIn(PLFData.DATA_USER2, PLFData.password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToChat();
    switchTo().window(1);
    refresh();
    roomManagement.addRoom(room, PLFData.DATA_USER2);
    roomManagement.startStopmeeting(room);
    chatManagement.sendMessageInRoomOrSpace(room, message);
    chatManagement.sendMessageInRoomOrSpace(room, message1);
    chatManagement.sendMessageInRoomOrSpace(room, message2);
    roomManagement.startStopmeeting(room);
    ELEMENT_CHAT_LIST_MSG.find(byClassName("uiIconChatWiki")).parent().find(byClassName("save-meeting-notes")).click();
    ELEMENT_CHAT_LINK_TEXT_OPEN_WIKI_APP.click();
    $(byText("Meeting Notes")).click();
    String nameWikipage = room + " Meeting " + getDate(0, "dd-MM-yyyy HH-mm");
    $(byText(nameWikipage)).shouldBe(Condition.visible);
    wikiHomePage.deleteWiki(nameWikipage);

  }
}
