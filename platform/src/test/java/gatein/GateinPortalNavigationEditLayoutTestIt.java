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
import static com.codeborne.selenide.Selenide.page;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
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

    PortalManagePages PortalManagePages;

    HomePagePlatform homePagePlatform;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        portalmanagesites = new PortalManageSites(this);
        pagecreationwizard = new PageCreationWizard(this);
        navigationToolbar = new NavigationToolbar(this);
        navigationmanagement = new NavigationManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        PortalManagePages  = new PortalManagePages(this);
        homePagePlatform=new HomePagePlatform(this);
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
			- The layout of page is displayed in the view mode with all changes
     **/



    @Test
    public void test01_AddApplicationIntoContainerWhenEditLayoutForGroupPage() {

        info("Test 01:  Add application into container when edit layout for group's page");
        String num= "num " + getRandomNumber();
        String pageName = "pageName"+ getRandomNumber();
        String title = "title" + getRandomNumber();

        info("Get value of group type");
        String type = "type"+ getRandomNumber();

        info("Get information of an application");
        String idName = "Application Registry";
        String name = "Application Registry";

        String container = "One Row";


        navigationToolbar.goToPotalPages();
        info("Add a new page with group type");
        PortalManagePages.addPage(pageName, title,type);

        info("Add a container");
        navigationToolbar.goToPotalPages();
        PortalManagePages.editPage(title,type);
        pagecreationwizard.addContainer(container);

        info("Add an application to the container");
        navigationToolbar.goToPotalPages();
        PortalManagePages.editPage(title ,type);
        pagecreationwizard.addApp("",name,$(byXpath(ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName))),$(byXpath(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}","Container"))));

        info("Verify that the application is added successfully in the container");
        navigationToolbar.goToPotalPages();
        PortalManagePages.editPage(title,type);
        $(byXpath(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name))).waitUntil(Condition.visible, Configuration.timeout);
        pagecreationwizard.switchViewMode(true);
        pagecreationwizard.saveChangesPageEditor();

        PortalManagePages.deletePage(title,type);
    }

    /*
    Step Number: 1
            *Step Name: Step 1: Move application when edit layout for portal's page
            *Step Description:
                - Select a page and click Edit layout
                - Select application, drag & drop it to new place on page
                - Switch view mode

            *Input Data:

            *Expected Outcome:
                - The application  is move to new place
                - The Layout of page is displayed in the view mode with all changes

                */





    /* no   */
    @Test
    public  void test05_MoveApplicationWhenEditLayoutForPortalPage() {
        info("Test 05: Move application when edit layout for portal's page");

        info("Get information of an application");

        String idName = "Administration/portlet_ApplicationRegistryPortlet";
        String name = "Application Registry";
        String portalName = "intranet";

        info("Add an application to the layout");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.addApp("",name,$(byXpath(ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName))),$(ELEMENT_PAGEEDITOR_VIEWPAGE));
        info("Move an application");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.moveApplication(name);

        info("Delete an application");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.deleteApplication(name);

        /*
        info("Move an application to new place");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.moveApplication(name);
        navigationToolbar.goToEditLayout();
        pagecreationwizard.checkPositions($(byXpath(ELEMENT_APPLICATION_PRECEDING_PORTLET.replace("${app1}","Announcement").replace("${app2}",name))),$(byXpath(ELEMENT_APPLICATION_FOLLOWING_PORTLET.replace("${app1}","Announcement").replace("${app2}",name))));
*/

    }

/*  ok */

    @Test
    public  void test04_EditPortalConfig() {

        	/*Step Number: 1
		*Step Name:Step 1: Edit portal's config
		*Step Description:
			- Go to Administration/Portal/Sites
			- Choose a portal from list and click Edit portal config
			- Make change something and save
		*Input Data:

		*Expected Outcome:
			Portlet Setting, Properties, Permission setting tab is updated successfully with new changes*/
        info("Test 04: Edit portal's config");
        String num = getRandomNumber();
        String portalName = "intranet";
        String label = "label" + getRandomNumber();
        String des   = "des"+ getRandomNumber();

        navigationToolbar.goToPotalSites();
        portalmanagesites.goToEditSiteConfig(portalName);
        portalmanagesites.editSimplePortal("",label,des,"","");
        info("Verify that the changes are updated");
        $(byXpath(ELEMENT_MANAGESITES_PORTAL_LABEL.replace("${portal}",portalName).replace("${label}",label))).waitUntil(Condition.visible,Configuration.timeout);
        $(byXpath(ELEMENT_MANAGESITES_PORTAL_DESC.replace("${portal}",portalName).replace("${desc}",des))).waitUntil(Condition.visible, Configuration.timeout);
    }


    /*Step Number: 1
		*Step Name: Step 1: Add application when edit layout for portal's page
		*Step Description:
			- Select a page and click Edit layout
			- Select Application tab from edit inline composer
			- Add application by drag & drop
			- Switch view mode
		*Input Data:

		*Expected Outcome:
			- Add application successfully
			- The Layout of page is displayed in the view mode with all changes*/

    /* no */
    @Test
    public  void test06_AddApplicationWhenEditLayoutForFortalPage() {
        info("Test 06: Add application when edit layout for portal's page");

        info("Get information of an application");
        String idName = "Administration/portlet_ApplicationRegistryPortlet";
        String name = "Application Registry";

        String portalName = "intranet";

        info("Add an application to the layout");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.addApp("",name,$(byXpath(ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName))),$(ELEMENT_PAGEEDITOR_VIEWPAGE));

        info("Verify that the application is added successfully in the container");
        navigationToolbar.goToEditLayout();

        pagecreationwizard.switchViewMode(true);
        pagecreationwizard.saveChangesPageEditor();

        info("Delete an application");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.deleteApplication(name);

    }
    /*Step Number: 1
		*Step Name: Step 1: Change application when edit layout for portal's page
		*Step Description:
			- Select a page and click Edit layout
			- Select application on page and click Edit portlet icon
			- Change something and click [Save and Close]
			- Click Finish button
			- Switch view mode
		*Input Data:

		*Expected Outcome:
			- The application is updated with the change value
			- The Layout of page is displayed in the view mode with all changes
				/*Step Number: 1
		*Step Name: Step 1: Remove application when edit layout for portal's page
		*Step Description:
			- Select a page and click Edit layout
			- Select application on page and click [Delete portlet] icon
			- Switch view mode
		*Input Data:

		*Expected Outcome:
			- The application  is removed successfully
			- The Layout of page is displayed in the view mode with all changes
			*/

    /* no */
    @Test
    public  void test07_08_Edit_Remove_ApplicationWhenEditLayoutForPortalPage() {
        info("Test 07: Edit application when edit layout for portal's page");
        info("Get information of an application");
        String idName = "idName" + getRandomNumber();
        String name = "name"+ getRandomNumber();
        String newTitle = "newTitle" +getRandomNumber();
        String portalName = "intranet";


        info("Add an application to the layout");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.addApp("",name,$(byXpath(ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName))),$(ELEMENT_PAGEEDITOR_VIEWPAGE));

        info("Edit an application");
        navigationToolbar.goToEditLayout();
        pagecreationwizard.editApplication(name,newTitle,"","");
        pagecreationwizard.saveChangesApplication();
        pagecreationwizard.saveChangesPageEditor();

        info("Verify that the application is added successfully in the container");
        navigationToolbar.goToEditLayout();
        $(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",newTitle)).waitUntil(Condition.visible, Configuration.timeout);
        pagecreationwizard.switchViewMode(true);
        pagecreationwizard.saveChangesPageEditor();

        info("Test 08:Remove application when edit layout for portal's page");


        navigationToolbar.goToEditLayout();
        pagecreationwizard.deleteApplication(newTitle);
    }


}
