package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_SPACENAME_TRIBE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 11/09/17.
 */
@Tag("tribe")
@Tag("social")
@Tag("sniff")
public class SOCManageSpacesTestIT extends BaseTribe {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  ConnectionsManagement  connectionsManagement;

  ActivityStream         activityStream;

  TribeActivityStream         tribeActivityStream;

  SpaceHomePage          spaceHomePage;

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
    manageLogInOut.signInTribeWithGoogle(tribe_mail, atlassian_username, atlassian_password);

  }

  @Test
  @Tag("sabis")
  public void test09_CheckTheExistanceOfTheSpacesInTheManageSpacesTab() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    String spaceNamec = "spaceNamec" + getRandomNumber();
    String spaceDesc = "descriptionc" + getRandomNumber();

    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    ELEMENT_SPACENAME_TRIBE.parent().find(byText(spaceNamea)).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
    ELEMENT_SPACENAME_TRIBE.parent().find(byText(spaceNameb)).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpaceSimple(spaceNamec, spaceDesc);
    ELEMENT_SPACENAME_TRIBE.parent().find(byText(spaceNamec)).waitUntil(Condition.visible, Configuration.timeout);

    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteSpace(spaceNamea, false);
    tribeSpaceManagement.deleteSpace(spaceNameb, false);
    tribeSpaceManagement.deleteSpace(spaceDesc, false);
  }


}
