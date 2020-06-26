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

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_LAST_VISITED_SPACES_SIDEBAR_ORDER;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
@Tag("social")
@Tag("sniff")
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

}