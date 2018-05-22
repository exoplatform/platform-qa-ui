package gatein;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("gatein")
@Tag("sniff")

public class GateinPortalNavigationEditLayoutTestIt extends Base {

    NavigationToolbar navigationToolbar;

    PortalManageSites portalmanagesites;

    NavigationManagement navigationmanagement;

    PageCreationWizard pagecreationwizard;

    ManageLogInOut manageLogInOut;

    PortalManagePages portalManagePages;

    HomePagePlatform homePagePlatform;
    UserAddManagement userAddManagement;
    UserAndGroupManagement userAndGroupManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        portalmanagesites = new PortalManageSites(this);
        pagecreationwizard = new PageCreationWizard(this);
        navigationToolbar = new NavigationToolbar(this);
        navigationmanagement = new NavigationManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        portalManagePages  = new PortalManagePages(this);
        homePagePlatform=new HomePagePlatform(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        userAddManagement = new UserAddManagement(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");

    }


    /*Step Number: 1
		*Step Name: Step 1: Change site's config of portal
		*Step Description:
			- Go to Administration/Portal/Sites
			- Choose a portal from list and click Edit layout
			- Click Site's config from edit inline composer
			- Make change something and save
		*Input Data:

		*Expected Outcome:
			Portal Setting, Properties, Permission setting tab are updated successfully with new changes
	Step Number: 1
		*Step Name: Step 1: Change site's config of portal
		*Step Description:
			- Go to Administration/Portal/Sites
			- Choose a portal from list and click Edit layout
			- Click Site's config from edit inline composer
			- Make change something and save
		*Input Data:

		*Expected Outcome:
			Portal Setting, Properties, Permission setting tab are updated successfully with new changes*/

    @Test
    public  void test02_ChangeSiteConfigOfPortal() {
        info("Test 02:Change site's config of portal");
        String num = "num" + getRandomNumber();
        String portalName = "intranet";
        String label = "Label" + getRandomNumber();
        String des   = "des" + getRandomNumber();

        navigationToolbar.goToPotalSites();
        portalmanagesites.changeConfig(portalName);
        portalmanagesites.editSimplePortal("",label,des,"","");
        pagecreationwizard.saveChangesPageEditor();
        info("Verify that the changes are updated");
      $(byXpath(ELEMENT_MANAGESITES_PORTAL_LABEL.replace("${portal}",portalName).replace("${label}",label))).waitUntil(Condition.visible,Configuration.timeout);
      $(byXpath(ELEMENT_MANAGESITES_PORTAL_DESC.replace("${portal}",portalName).replace("${desc}",des))).waitUntil(Condition.visible,Configuration.timeout);
    }

    /**
     *<li> Case ID:99421.</li>
     *<li> Test Case Name:Add application into container when edit layout for group's page.</li>

     Step Number: 1
		*Step Name: Step 1: Add application into container when edit layout for group's page
		*Step Description:
			- Select a page and click Edit layout
			- Add container by drag & drop
			- Drag & drop application into the container added above
			- Switch view mode
		*Input Data:

		*Expected Outcome:
			- Add application into container successfully
			- The layout of page is displayed in the view mode with all changes*/


    @Test
    public void test01_AddApplicationIntoContainerWhenEditLayoutForGroupPage() {
        info("Test 01:  Add application into container when edit layout for group's page");
        String username="username"+getRandomString();
        String password="123456";
        String email=username+"@test.com";
        String num= "num" + getRandomNumber();
        String pageName ="pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        int index = 1;
        String type = "type"+ getRandomNumber();

        info("Get information of an application");
        int index2 = 2;
        String idName = "idName" + getRandomNumber();
        String name = "name" + getRandomNumber();
        String container = "Une Ligne";
        String  siteName = "siteName" + getRandomNumber();
        manageLogInOut.signIn(PLFData.username,PLFData.password);
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, username, username);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.addUserAdmin(username,"*");
        manageLogInOut.signIn(username,password);
        navigationToolbar.goToPotalPages();
        info("Add a new page with group type");
        portalManagePages.addPage(pageName, title,"group");
        info("Add a container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title,"group");
      //  PageCreationWizard.addContainer (container);

        info("Add an application to the container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title ,type);
        pagecreationwizard.addApp("",name,$(byXpath(ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName))),$(byXpath(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}","Container"))));

        info("Verify that the application is added successfully in the container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title,type);
        $(byXpath(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name))).waitUntil(Condition.visible, Configuration.timeout);
        pagecreationwizard.switchViewMode(true);
        pagecreationwizard.saveChangesPageEditor();

        portalManagePages.deletePage(title,type);
    }
}
