package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeSpaceManagement")
public class SOCManageSpacesTestIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  TribeSpaceManagement tribeSpaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    spaceHomePage = new SpaceHomePage(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  @Tag("spacetest")
  public void test01_CheckTheExistanceOfTheSpacesInTheManageSpacesTab() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    String spaceNamec = "spaceNamec" + getRandomNumber();
    String spaceDesc = "descriptionc" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpaceTribe(spaceNameb, spaceDesb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamec, spaceDesc, "Open", "No", null);

    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    tribeSpaceManagement.deleteTribeSpace(spaceNameb);
    tribeSpaceManagement.deleteTribeSpace(spaceDesc);

  }

  @Test
  public void test02_CheckCreatedSpacesOrderOnTheSideBarMenu() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    String spaceNamec = "spaceNamec" + getRandomNumber();
    String spaceDesc = "descriptionc" + getRandomNumber();
    String spaceNamed = "spaceNamed" + getRandomNumber();
    String spaceDesd = "descriptiond" + getRandomNumber();
    String spaceNamee = "spaceNamee" + getRandomNumber();
    String spaceDese = "descriptione" + getRandomNumber();
    String spaceNamef = "spaceNamef" + getRandomNumber();
    String spaceDesf = "descriptionf" + getRandomNumber();
    String spaceNameg = "spaceNameg" + getRandomNumber();
    String spaceDesg = "descriptiong" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNameb, spaceDesb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamec, spaceDesc, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamed, spaceDesd, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamee, spaceDese, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamef, spaceDesf, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNameg, spaceDesg, "Open", "No", null);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToSideBarMenuTribe();
    info("Check That the created spaces are displayed in order on SideBar menu");
    info("Last space created is the first displayed on SideBar menu");
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_SIDEBAR_ORDER.replace("{id}", "1"))).getText(), spaceNameg);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_SIDEBAR_ORDER.replace("{id}", "2"))).getText(), spaceNamef);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_SIDEBAR_ORDER.replace("{id}", "3"))).getText(), spaceNamee);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_SIDEBAR_ORDER.replace("{id}", "4"))).getText(), spaceNamed);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_SIDEBAR_ORDER.replace("{id}", "5"))).getText(), spaceNamec);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_SIDEBAR_ORDER.replace("{id}", "6"))).getText(), spaceNameb);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_SIDEBAR_ORDER.replace("{id}", "7"))).getText(), spaceNamea);

    ELEMENT_TRIBE_LAST_VISITED_SPACES_CHECK_ORDER.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
    info("Check That the created spaces are displayed in order on Last Visited Spaces Menu");
    info("Last space created is the first displayed on Last Visited Spaces Menu");
    Assert.assertEquals($(By.xpath(ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER.replace("{id}", "1"))).getText(), spaceNameg);
    Assert.assertEquals($(By.xpath(ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER.replace("{id}", "2"))).getText(), spaceNamef);
    Assert.assertEquals($(By.xpath(ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER.replace("{id}", "3"))).getText(), spaceNamee);
    Assert.assertEquals($(By.xpath(ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER.replace("{id}", "4"))).getText(), spaceNamed);
    Assert.assertEquals($(By.xpath(ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER.replace("{id}", "5"))).getText(), spaceNamec);
    Assert.assertEquals($(By.xpath(ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER.replace("{id}", "6"))).getText(), spaceNameb);
    Assert.assertEquals($(By.xpath(ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER.replace("{id}", "7"))).getText(), spaceNamea);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    tribeSpaceManagement.deleteTribeSpace(spaceNameb);
    tribeSpaceManagement.deleteTribeSpace(spaceNamec);
    tribeSpaceManagement.deleteTribeSpace(spaceNamed);
    tribeSpaceManagement.deleteTribeSpace(spaceNamee);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamef);
    tribeSpaceManagement.deleteTribeSpace(spaceNameg);

  }

  @Test
  public void test03_SelectSpaceFromLastVisitedSpaces() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToSideBarMenuTribe();
    info("Select Space From Last Visited Spaces List");
    tribeSpaceManagement.selectSpaceFromLastVisitedSpaces(spaceNamea);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test04_SearchASpaceViaRecentSpaces() {


    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToSideBarMenuTribe();
    info("Search A Space Via Recent Spaces");
    tribeSpaceManagement.searchSpaceViaRecentSpaces(spaceNamea);
    tribeSpaceManagement.accessToTheSearchedSpace(spaceNamea);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test05_CheckThatTopBarItemsAreDisplayedAfterASpaceCreation() {

    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

    info("Top Bar Tabs after Space Creation are displayed in order");
    tribeSpaceManagement.checkThatSpaceTabsAreDisplayedInOrderDW(spaceNamea);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test06_CheckthatTopBarSpacesSectionAreDisplayed() {


    homePagePlatform.goToMySpacesTribeViaUrl();

    info("Check that Add new space button is displayed");
    ELEMENT_ADDNEWSPACE_SECOND_TRIBE_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Filter spaces from existing spaces is displayed");
    ELEMENT_SPACES_TRIBE_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Filter pulldown (All spaces/My spaces) is displayed");
    ELEMENT_SPACES_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check the Full Number of All Spaces displayed");

    for (int i = 1; i <= 20; i++) {
      $(By.xpath(ELEMENT_FULL_SPACES_NUMBER_DW.replace("{i}", String.valueOf(i)))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    }

    info("Check the Full Number of My Spaces displayed");
    ELEMENT_SPACES_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Mes espaces");

    for (int i = 1; i <= 20; i++) {
      $(By.xpath(ELEMENT_FULL_SPACES_NUMBER_DW.replace("{i}", String.valueOf(i)))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    }

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test07_CheckThePullDownFilterInSpacesPageAfterASpaceCreation() {

    String space1 = "000 Sandbox";
    String space2 = "Access to all";
    String space3 = "00 Future of Work";
    String space4 = "51grABICT";

    homePagePlatform.goToMySpacesTribeViaUrl();

    info("Check that all spaces are displayed");
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space1))).getText(), "Quitter");
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).getText(), "Quitter");
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space3))).getText(), "Rejoindre");
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space4))).getText(), "Demander l’accès");

    info("Only Space1 and Space 2 are displayed when clicking on My Spaces");
    ELEMENT_SPACES_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Mes espaces");

    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space1))).getText(), "Quitter");
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).getText(), "Quitter");
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space3))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space4))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test08_CheckTheFilterSpacesFieldInSpacesPageAfterASpaceCreation() {

    String spaceNamea = "spaceName7770";
    String spaceNameb = "spaceName7772";
    String spaceNamec = "spaceName7773";
    String spaceNamed = "spaceName7774";
    String spaceNamee = "spaceName7775";

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceNamea, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNameb, spaceNameb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamec, spaceNamec, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamed, spaceNamed, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamee, spaceNamee, "Open", "No", null);

    info("Check that only " + spaceNamee + "is displayed");
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceNamee.split("Name")[1]);

    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamee))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamea))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNameb))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamec))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamed))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    info("Check that all new created spaces are displayed when searching by " + spaceNamee.split("7")[0]);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceNamee.split("7")[0]);

    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamee))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamea))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNameb))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamec))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamed))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    tribeSpaceManagement.deleteTribeSpace(spaceNameb);
    tribeSpaceManagement.deleteTribeSpace(spaceNamec);
    tribeSpaceManagement.deleteTribeSpace(spaceNamed);
    tribeSpaceManagement.deleteTribeSpace(spaceNamee);

  }

  @Test
  public void test09_ChecktheFullSpacesNumberAfterLoadingMoreSpaces() {


    String spaceNamea = "spacea" + getRandomNumber();
    String spaceNameb = "spaceb" + getRandomNumber();
    String spaceNamec = "spacec" + getRandomNumber();
    String spaceNamed = "spaced" + getRandomNumber();
    String spaceNamee = "spacee" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceNamea, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNameb, spaceNameb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamec, spaceNamec, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamed, spaceNamed, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamee, spaceNamee, "Open", "No", null);

    homePagePlatform.goToMySpacesTribeViaUrl();

    info("Check that Add new space button is displayed");
    ELEMENT_ADDNEWSPACE_SECOND_TRIBE_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Filter spaces from existing spaces is displayed");
    ELEMENT_SPACES_TRIBE_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Filter pulldown (All spaces/My spaces) is displayed");
    ELEMENT_SPACES_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();


    info("Check the Full Number of All Spaces After loading more spaces");

    ELEMENT_LOAD_MORE_SPACES_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    for (int i = 1; i <= 40; i++) {
      $(By.xpath(ELEMENT_FULL_SPACES_NUMBER_DW.replace("{i}", String.valueOf(i)))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    }

    info("Check the Full Number of My Spaces displayed After loading more spaces");
    ELEMENT_SPACES_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Mes espaces");

    for (int i = 1; i <= 40; i++) {
      $(By.xpath(ELEMENT_FULL_SPACES_NUMBER_DW.replace("{i}", String.valueOf(i)))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    }

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    tribeSpaceManagement.deleteTribeSpace(spaceNameb);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamec);
    tribeSpaceManagement.deleteTribeSpace(spaceNamed);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamee);

  }

  @Test
  public void test10_CheckUserSpacesInvitations() {

  String space1 = "space" + getRandomNumber();
  String space2 = "space" + getRandomNumber();
  String user1 = tribe_user3;

  ArrayList<String> inviteUsers = new ArrayList<>();
  inviteUsers.add(user1);

  homePagePlatform.goToMySpacesTribeViaUrl();
  tribeSpaceManagement.addNewSpaceTribe(space1,space1,"Open","No",inviteUsers);
  homePagePlatform.goToMySpacesTribeViaUrl();
  tribeSpaceManagement.addNewSpaceTribe(space2,space2,"Open","No",inviteUsers);

  manageLogInOut.signOutTribe();
  manageLogInOut.signInTribe(tribe_username3,tribe_password3);
  homePagePlatform.goToMySpacesTribeViaUrl();

  info("Check that Invitations Number is " + ELEMENT_SPACE_INVITATION_NUMBER_BTN_DW.getText());
  Assert.assertEquals(ELEMENT_SPACE_INVITATION_NUMBER_BTN_DW.getText(),"2");

  info("Check that " + space1 + " and " + space2);
  ELEMENT_SPACE_INVITATION_NUMBER_BTN_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  $(By.xpath(ELEMENT_ACCEPT_JOIN_SPACE_DW.replace("${space}",space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
  $(By.xpath(ELEMENT_ACCEPT_JOIN_SPACE_DW.replace("${space}",space2))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  info("Accept joining Space " + space1);
  $(By.xpath(ELEMENT_ACCEPT_JOIN_SPACE_DW.replace("${space}",space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  $(By.xpath(ELEMENT_ACCEPT_JOIN_SPACE_DW.replace("${space}",space1))).waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);
  $(By.xpath(ELEMENT_ACCEPT_JOIN_SPACE_DW.replace("${space}",space2))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  info("Check Invitations number after joining Space " + space1);
  ELEMENT_CLOSE_SPACES_INVITATIONS_DRAWER_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  Assert.assertEquals(ELEMENT_SPACE_INVITATION_NUMBER_BTN_DW.getText(),"1");
  ELEMENT_SPACE_INVITATION_NUMBER_BTN_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  info("Refuse joining Space " + space2);
  $(By.xpath(ELEMENT_REFUSE_JOIN_SPACE_DW.replace("${space}",space2))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  $(By.xpath(ELEMENT_ACCEPT_JOIN_SPACE_DW.replace("${space}",space1))).waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);
  $(By.xpath(ELEMENT_ACCEPT_JOIN_SPACE_DW.replace("${space}",space2))).waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);

  info("Check Invitations number after refusing to join Space " + space2);
  ELEMENT_CLOSE_SPACES_INVITATIONS_DRAWER_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  Assert.assertEquals(ELEMENT_SPACE_INVITATION_NUMBER_BTN_DW.getText(),"0");

  info("Check that user " + tribe_user3 + " had joined " + space1);
  tribeSpaceManagement.searchSpace(space1);
  Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space1))).getText(), "Leave");

  info("Check that user " + tribe_user3 + " hadn't joined " + space2);
  homePagePlatform.goToMySpacesTribeViaUrl();
  tribeSpaceManagement.searchSpace(space2);
  Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).getText(), "Join");

  info("Delete space");
  manageLogInOut.signOutTribe();
  manageLogInOut.signInTribe(tribe_username,tribe_password);
  homePagePlatform.goToMySpacesTribeViaUrl();
  tribeSpaceManagement.deleteTribeSpace(space1);
  tribeSpaceManagement.deleteTribeSpace(space2);

}

  @Test
  public void test11_CheckTheSuggestionsWidgetInSpacesPage() {

    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space1,space1,"Open","No",null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space2,space2,"Open","No",null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space3,space3,"Open","No",null);

    info("Go to Space Page");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3,tribe_password3);
    homePagePlatform.goToMySpacesTribeViaUrl();

    info("The suggestion widget is existing and displayed only 2 spaces with Add button and Delete buttons");
    ELEMENT_FIRST_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_SECOND_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_ADD_FIRST_SPACE_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_ADD_SECOND_SPACE_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_DELETE_FIRST_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_DELETE_SECOND_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    String firstSpaceSuggestion = ELEMENT_FIRST_SUGGESTION_DW.getText();
    String secondSpaceSuggestion = ELEMENT_SECOND_SUGGESTION_DW.getText();

    info("Add the first space suggestion");
    ELEMENT_ADD_FIRST_SPACE_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The first space suggestion is not displayed");
    sleep(2000);
    Assert.assertNotEquals(ELEMENT_FIRST_SUGGESTION_DW.getText(), firstSpaceSuggestion);
    Assert.assertEquals(ELEMENT_FIRST_SUGGESTION_DW.getText(), secondSpaceSuggestion);

    info("Delete the space suggestion");
    ELEMENT_DELETE_FIRST_SUGGESTION_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The space suggestion is not displayed");
    sleep(2000);
    Assert.assertNotEquals(ELEMENT_FIRST_SUGGESTION_DW.getText(), secondSpaceSuggestion);

    info("Check that user " + tribe_user3 + " had joined " + firstSpaceSuggestion);
    tribeSpaceManagement.searchSpace(firstSpaceSuggestion);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", firstSpaceSuggestion))).getText(), "Leave");

    info("Check that user " + tribe_user3 + " hadn't joined " + secondSpaceSuggestion);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(secondSpaceSuggestion);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", secondSpaceSuggestion))).getText(), "Join");

    info("Delete space");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username,tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space1);
    tribeSpaceManagement.deleteTribeSpace(space2);
    tribeSpaceManagement.deleteTribeSpace(space3);

  }

}
