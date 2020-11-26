package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
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
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
public class SOCManageSpacesDWTestIT extends BaseDW {
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
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_CheckTheExistanceOfTheSpacesInTheManageSpacesTab() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    String spaceNamec = "spaceNamec" + getRandomNumber();
    String spaceDesc = "descriptionc" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceDesb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(spaceNamec, spaceDesc, "Open", "No", null);

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
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceDesb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamec, spaceDesc, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamed, spaceDesd, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamee, spaceDese, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamef, spaceDesf, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameg, spaceDesg, "Open", "No", null);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();
    homePagePlatform.goToSideBarMenuTribe();
    info("Check That the created spaces are displayed in order on SideBar menu");
    info("Last space created is the first displayed on SideBar menu");
    sleep(2000);
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
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
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
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
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
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    info("Top Bar Tabs after Space Creation are displayed in order");
    tribeSpaceManagement.checkThatSpaceTabsAreDisplayedInOrder(spaceNamea);

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


    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test07_CheckThePullDownFilterInSpacesPageAfterASpaceCreation() {

    String spaceNamea = "spacea" + getRandomNumber();
    String spaceNameb = "spaceb" + getRandomNumber();
    String spaceNamec = "spacec" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceNamea, "Open", "No", inviteUsers);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceNameb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamec, spaceNamec, "Validation", "No", null);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    homePagePlatform.goToMySpacesTribeViaUrl();
    info("Check that all spaces are displayed");
    tribeSpaceManagement.searchSpace(spaceNamea);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_BEFORE_ACCEPTING_INVITATION_DW.replace("${space}", spaceNamea))).getText(), "Accept to join");
    navigationToolbar.goToIntranetNotificationDW_WithoutRefresh();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNamea);
    navigationToolbar.verifyAcceptJoinSpaceViaNotificationDW(spaceNamea);
    navigationToolbar.closeNotificationsDW();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceNamea);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamea))).getText(), "Leave");
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceNameb);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNameb))).getText(), "Join");
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceNamec);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamec))).getText(), "Request access");

    info("Only Space1 is displayed when clicking on My Spaces");
    homePagePlatform.goToMySpacesTribeViaUrl();
    ELEMENT_SPACES_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("My spaces");

    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceNamea))).getText(), "Leave");

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    tribeSpaceManagement.deleteTribeSpace(spaceNameb);
    tribeSpaceManagement.deleteTribeSpace(spaceNamec);

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
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceNamea, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceNameb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamec, spaceNamec, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamed, spaceNamed, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamee, spaceNamee, "Open", "No", null);

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
    String spaceNamef = "spacef" + getRandomNumber();
    String spaceNameg = "spaceg" + getRandomNumber();
    String spaceNameh = "spaceh" + getRandomNumber();
    String spaceNamei = "spacei" + getRandomNumber();
    String spaceNamek = "spacek" + getRandomNumber();
    String spaceNamel = "spacel" + getRandomNumber();
    String spaceNamem = "spacem" + getRandomNumber();
    String spaceNamen = "spacen" + getRandomNumber();
    String spaceNameo = "spaceo" + getRandomNumber();
    String spaceNamep = "spacep" + getRandomNumber();
    String spaceNames = "spaces" + getRandomNumber();
    String spaceNamer = "spacer" + getRandomNumber();
    String spaceNamet = "spacet" + getRandomNumber();
    String spaceNameu = "spaceu" + getRandomNumber();
    String spaceNamev = "spacev" + getRandomNumber();
    String spaceNamew = "spacew" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceNamea, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceNameb, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamec, spaceNamec, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamed, spaceNamed, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamee, spaceNamee, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamef, spaceNamef, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameg, spaceNameg, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameh, spaceNameh, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamek, spaceNamek, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamel, spaceNamel, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamem, spaceNamem, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamen, spaceNamen, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameo, spaceNameo, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamep, spaceNamep, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamei, spaceNamei, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNames, spaceNames, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamer, spaceNamer, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamet, spaceNamet, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameu, spaceNameu, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamev, spaceNamev, "Open", "No", null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamew, spaceNamew, "Open", "No", null);

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

    info("Check the Full Number of All Spaces After loading more spaces");

    ELEMENT_LOAD_MORE_SPACES_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    for (int i = 1; i <= 21; i++) {
      $(By.xpath(ELEMENT_FULL_SPACES_NUMBER_DW.replace("{i}", String.valueOf(i)))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    }

    info("Check the Full Number of My Spaces displayed After loading more spaces");
    ELEMENT_SPACES_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Mes espaces");

    for (int i = 1; i <= 21; i++) {
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
    tribeSpaceManagement.deleteTribeSpace(spaceNamef);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNameg);
    tribeSpaceManagement.deleteTribeSpace(spaceNameh);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamei);
    tribeSpaceManagement.deleteTribeSpace(spaceNamek);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamel);
    tribeSpaceManagement.deleteTribeSpace(spaceNamem);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamen);
    tribeSpaceManagement.deleteTribeSpace(spaceNameo);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamep);
    tribeSpaceManagement.deleteTribeSpace(spaceNames);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamer);
    tribeSpaceManagement.deleteTribeSpace(spaceNamet);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNameu);
    tribeSpaceManagement.deleteTribeSpace(spaceNamev);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamew);

  }

  @Test
  public void test10_CheckUserSpacesInvitations() {

    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space1,space1,"Open","No",inviteUsers);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space2,space2,"Open","No",inviteUsers);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1,password);
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

    info("Check that user " + username1 + " had joined " + space1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(space1);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space1))).getText(), "Leave");

    info("Check that user " + username1 + " hadn't joined " + space2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(space2);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).getText(), "Join");

    info("Delete space");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space1);
    tribeSpaceManagement.deleteTribeSpace(space2);

  }

  @Test
  public void test11_CheckTheSuggestionsWidgetInSpacesPage() {

    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space1,space1,"Open","No",null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space2,space2,"Open","No",null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space3,space3,"Open","No",null);

    info("Go to Space Page");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1,password);
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

    info("Check that user " + username1 + " had joined " + firstSpaceSuggestion);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(firstSpaceSuggestion);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", firstSpaceSuggestion))).getText(), "Leave");

    info("Check that user " + username1 + " hadn't joined " + secondSpaceSuggestion);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(secondSpaceSuggestion);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", secondSpaceSuggestion))).getText(), "Join");

    info("Delete space");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space1);
    tribeSpaceManagement.deleteTribeSpace(space2);
    tribeSpaceManagement.deleteTribeSpace(space3);

  }

  @Test
  public void test12_CheckPopularSpacesWidgetBehavior() {

    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();

    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity2" + getRandomNumber();
    String activity3 = "activity3" + getRandomNumber();
    String activity4 = "activity4" + getRandomNumber();


    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space1,space1,"Validation","No",null);
    info("Post Activities");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity2, "");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity3, "");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity4, "");

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space2,space2,"Open","No",null);
    info("Post Activities");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity2, "");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity3, "");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity4, "");

    info("Go to Space Page");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1,password);
    homePagePlatform.goToMySpacesTribeViaUrl();

    info( "Join button is displayed in popular Spaces Widget for " + space2);
    Assert.assertEquals($(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space2))).getText(),"Join");

    info("Check that user " + username1 + " hadn't joined " + space2);
    tribeSpaceManagement.searchSpace(space2);
    sleep(3000);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).getText(), "Join");

    info("Request to join button for " + space1 + " is displayed");
    Assert.assertEquals($(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space1))).getText(),"Request to join");
    sleep(3000);
    $(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    info("Cancel request for " + space1 + " is displayed");
    sleep(3000);
    Assert.assertEquals($(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space1))).getText(),"Cancel request");

    info("Cancel request for " +space1);
    $(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(2000);
    Assert.assertEquals($(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space1))).getText(),"Request to join");

    info("Request to join " + space1 );
    $(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(2000);
    Assert.assertEquals($(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space1))).getText(),"Cancel request");

    info("Request to join " + space2 );
    $(byXpath(ELEMENT_POPULAR_SPACE_JOIN_SUGGESTION_DW.replace("${space}",space2))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    info("Check that user " + username1 + " had joined " + space2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(space2);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).getText(), "Leave");

    info("Check that an invitation is sent to join "+ space1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(space1);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space1))).getText(), "Cancel request");

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(space1);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(space1);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space1))).getText(), "Leave");

    info("Delete space");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space1);
    tribeSpaceManagement.deleteTribeSpace(space2);

  }

  @Test
  public void test13_CheckUserSpacesRequests() {

    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space1,space1,"Validation","No",null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space2,space2,"Validation","No",null);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space3,space3,"Validation","No",null);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1,password);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(space2);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).getText(), "Request access");
    sleep(2000);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space2))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(space3);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space3))).getText(), "Request access");
    sleep(2000);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", space3))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    homePagePlatform.goToMySpacesTribeViaUrl();
    info("Check that Spaces Sent Requests Number is " + ELEMENT_SPACE_SENT_REQUESTS_NUMBER_BTN_DW.getText());
    Assert.assertEquals(ELEMENT_SPACE_SENT_REQUESTS_NUMBER_BTN_DW.getText(),"2");

    info("Check that " + space2 + " and " + space3 + " are displayed in Sent Requests Drawer");
    ELEMENT_SPACE_SENT_REQUESTS_NUMBER_BTN_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(By.xpath(ELEMENT_REMOVE_SPACE_SENT_REQUEST_DW.replace("${space}",space2))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    $(By.xpath(ELEMENT_REMOVE_SPACE_SENT_REQUEST_DW.replace("${space}",space3))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

    info("Remove Sent Request to " + space2);
    $(By.xpath(ELEMENT_REMOVE_SPACE_SENT_REQUEST_DW.replace("${space}",space2))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    info("Check that " + space2 + " is not displayed in Sent Requests Drawer");
    $(By.xpath(ELEMENT_REMOVE_SPACE_SENT_REQUEST_DW.replace("${space}",space2))).waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);

    info("Close Spaces Sent Requests");
    ELEMENT_CLOSE_SPACES_INVITATIONS_DRAWER_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    sleep(2000);
    info("Check that the new Spaces Sent Requests Number is " + ELEMENT_SPACE_SENT_REQUESTS_NUMBER_BTN_DW.getText());
    Assert.assertEquals(ELEMENT_SPACE_SENT_REQUESTS_NUMBER_BTN_DW.getText(),"1");

    info("Delete space");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space1);
    tribeSpaceManagement.deleteTribeSpace(space2);
    tribeSpaceManagement.deleteTribeSpace(space3);

  }

  @Test
  public void test14_CheckSpaceManagingRequests() {

    String space1 = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space1,space1,"Validation","No",null);

    homePagePlatform.goToMySpacesTribeViaUrl();

    info("Check that Managing is displayed in Space Widget");
    Assert.assertEquals(ELEMENT_MANAGING_SPACE_TITLE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).getText(),"Managing");

    info("Check that Managed Spaces Number is displayed");
    ELEMENT_MANAGING_SPACE_NUMBER.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).isDisplayed();
    info("Check that Spaces Number is " + ELEMENT_MANAGING_SPACE_NUMBER.getText());

    info("Click on Managing Number");
    ELEMENT_MANAGING_SPACE_NUMBER.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    info("Check that displayed space with Edit Icon is displayed");
    $(byXpath(ELEMENT_EDIT_MANAGING_SPACE.replace("${space}",space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check on Edit Icon");
    $(byXpath(ELEMENT_EDIT_MANAGING_SPACE.replace("${space}",space1))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    info("Check that Edit Space Drawer is opened");
    ELEMENT_DRAWER_EDIT_SPACE_TITLE_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_DRAWER_EDIT_SPACE_TITLE_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).getText(), "Edit Space " + space1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space1);


  }

}