package gatein;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManagePages;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
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
    PortalManagePages portalManagePages;
    HomePagePlatform homePagePlatform;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        portalmanagesites = new PortalManageSites(this);
        pagecreationwizard = new PageCreationWizard(this);
        navigationToolbar = new NavigationToolbar(this);
        navigationmanagement = new NavigationManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        portalManagePages = new PortalManagePages(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut.signInCas("root", "gtn");
    }

    /**
     * Step Number: 1
     * Step Name: Step 1: Change site's config of portal
     * Step Description:
     * - Go to Administration/Portal/Sites
     * - Choose a portal from list and click Edit layout
     * - Click Site's config from edit inline composer
     * - Make change something and save
     * Input Data:
     * <p>
     * Expected Outcome:
     * Portal Setting, Properties, Permission setting tab are updated successfully with new changes
     * Step Number: 1
     * Step Name: Step 1: Change site's config of portal
     * Step Description:
     * - Go to Administration/Portal/Sites
     * - Choose a portal from list and click Edit layout
     * - Click Site's config from edit inline composer
     * - Make change something and save
     * Input Data:
     * <p>
     * Expected Outcome:
     * Portal Setting, Properties, Permission setting tab are updated successfully with new changes
     **/
    @Test
    public void test02_ChangeSiteConfigOfPortal() {
        info("Test 02:Change site's config of portal");
        String portalName = "intranet";
        String label = "Label" + getRandomNumber();
        String des = "des" + getRandomNumber();
        navigationToolbar.goToPotalSites();
        portalmanagesites.changeConfig(portalName);
        portalmanagesites.editSimplePortal("", label, des, "", "");
        pagecreationwizard.saveChangesPageEditor();
        info("Verify that the changes are updated");
        $(byXpath(ELEMENT_MANAGESITES_PORTAL_LABEL.replace("${portal}", portalName).replace("${label}", label))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_MANAGESITES_PORTAL_DESC.replace("${portal}", portalName).replace("${desc}", des))).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * <li> Case ID:99421.</li>
     * <li> Test Case Name:Add application into container when edit layout for group's page.</li>
     * <p>
     * Step Number: 1
     * Step Name: Step 1: Add application into container when edit layout for group's page
     * Step Description:
     * - Select a page and click Edit layout
     * - Add container by drag & drop
     * - Drag & drop application into the container added above
     * - Switch view mode
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Add application into container successfully
     * - The layout of page is displayed in the view mode with all changes
     */
    @Test
    public void test01_AddApplicationWhenEditLayoutForGroupPage() {
        info("Test 01:  Add application into container when edit layout for group's page");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "type" + getRandomNumber();
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, "group");
        info("Add a container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addContainer("", "Two Rows", $(byId("twoRows")), $(byClassName("VIEW-PAGE")));
        info("Add an application to the container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addApp("",
                "Application Registry",
                $(byId("Administration/portlet_ApplicationRegistryPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Verify that the application is added successfully in the container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.switchViewMode(true);
        pagecreationwizard.saveChangesPageEditor();

        info("Delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    @Test
    public void test05_MoveApplicationWhenEditLayoutForPortalPage() {
        info("Test 01:  Add application into container when edit layout for group's page");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        String name = "Group Navigations";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Add application1");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addApp("",
                "Application Registry",
                $(byId("Administration/portlet_ApplicationRegistryPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Add application2");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Move an application to new place");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.moveApplication(byText("Application Registry"), $(byText("Group Navigation Portlet")));
        info("check position for application moved");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.checkPositions(byText("Application Registry") , byText("Group Navigation Portlet"));
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    /*Step Number: 1
*Step Name:Step 1: Edit portal's config
*Step Description:
    - Go to Administration/Portal/Sites
    - Choose a portal from list and click Edit portal config
    - Make change something and save
*Input Data:

*Expected Outcome:
    Portlet Setting, Properties, Permission setting tab is updated successfully with new changes*/
    @Test
    public void test04_EditPortalConfig() {
        info("Test 04: Edit portal's config");
        String portalName = "intranet";
        String label = "label1" + getRandomNumber();
        String des = "des1" + getRandomNumber();
        navigationToolbar.goToPotalSites();
        portalmanagesites.goToEditSiteConfig(portalName);
        portalmanagesites.editSimplePortal("", label, des, "", "");
        info("Verify that the changes are updated");
        navigationToolbar.goToPotalSites();
        portalmanagesites.goToEditSiteConfig(portalName);
        $(byXpath(ELEMENT_MANAGESITES_PORTAL_LABEL.replace("${portal}", portalName).replace("${label}", label))).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath(ELEMENT_MANAGESITES_PORTAL_DESC.replace("${portal}", portalName).replace("${desc}", des))).waitUntil(Condition.visible, Configuration.timeout);
        portalmanagesites.saveNewPortal();
    }

    /*
    Step Number: 1
    *Step Name: Step 1: Add application when edit layout for portal's page
    *Step Description:
        - Select a page and click Edit layout
        - Select Application tab from edit inline composer
        - Add application by drag & drop
        - Switch view mode
    *Input Data:

    *Expected Outcome:
        - Add application successfully
        - The Layout of page is displayed in the view mode with all changes
      */
    @Test
    public void test06_AddApplicationWhenEditLayoutForFortalPage() {
        info("Test 06: Add application when edit layout for portal's page");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();

        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Add application1");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Verify that the application is added successfully in the container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.switchViewMode(true);
        pagecreationwizard.saveChangesPageEditor();
        info("Delete an application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.deleteApplication("Group Navigation Portlet");
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    /**
     * <li> Case ID:99601.</li>
     * <li> Test Case Name: Edit application when edit layout for portal's page.</li>
     * *<li> Case ID:99602.</li>
     * <li> Test Case Name: Remove application when edit layout for portal's page.</li>
     * <p>
     * Step Number: 1
     * Step Name: Step 1: Change application when edit layout for portal's page
     * Step Description:
     * - Select a page and click Edit layout
     * - Select application on page and click Edit portlet icon
     * - Change something and click [Save and Close]
     * - Click Finish button
     * - Switch view mode
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The application is updated with the change value
     * - The Layout of page is displayed in the view mode with all changes
     * <p>
     * Step Number: 1
     * Step Name: Step 1: Remove application when edit layout for portal's page
     * Step Description:
     * - Select a page and click Edit layout
     * - Select application on page and click [Delete portlet] icon
     * - Switch view mode
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The application  is removed successfully
     * - The Layout of page is displayed in the view mode with all changes
    **/
    @Test
    public void test07_EditApplicationWhenEditLayoutForPortalPage() {
        info("Test 07: Edit application when edit layout for portal's page");
        info("Get information of an application");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Add application1");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Application Registry",
                $(byId("Administration/portlet_ApplicationRegistryPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Edit an application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.editApplication((byText("Application Registry")), "newtitle123456", "30", "40");
        pagecreationwizard.saveChangesApplication();
        pagecreationwizard.saveChangesPageEditor();
        info("Verify that the application is added successfully in the container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.switchViewMode(true);
        pagecreationwizard.saveChangesPageEditor();

        info("Test 08:Remove application when edit layout for portal's page");
        info("Delete an application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.deleteApplication("newtitle123456");
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

/*
    Step Number: 1
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
    @Test
    public void test08_Edit_Remove_ApplicationWhenEditLayoutForPortalPage() {
        info("Test 07: Edit application when edit layout for portal's page");
        info("Get information of an application");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Edit an application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.editApplication((byText("Group Navigation Portlet")), "newtitle123456", "30", "40");
        pagecreationwizard.saveChangesApplication();
        pagecreationwizard.saveChangesPageEditor();
        info("Test 08:Remove application when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.deleteApplication("newtitle123456");
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    /**
     * <li> Case ID:99605.</li>
     * <li> Test Case Name: Add container when edit layout for portal's page.</li>
     * <li> Case ID:99606.</li>
     * <li> Test Case Name: Edit container when edit layout for portal's page.</li>
     * <li> Case ID:99604.</li>
     * <li> Test Case Name: Move container when edit layout for portal's page.</li>
     * <li> Case ID:99607.</li>
     * <li> Test Case Name: Delete container when edit layout for portal's page.</li>
     * <p>
     * <p>
     * <p>
     * Step Number: 1
     * Step Name: Step 1: Add container when edit layout for portal's page
     * Step Description:
     * - Select a page and click Edit layout
     * - Select Container tab from edit inline composer
     * - Add container by drag & drop
     * - Switch view mode
     * <p>
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Add container successfully
     * - The Layout of page is displayed in the view mode with all changes
     **/
    @Test
    public void test09_AddContainerWhenEditLayoutForPortalPage() {
        info("Get information of an application");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Add application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Test 09: Add container when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addContainer("One Row", "One Row", $(byId("oneRow")), $(byClassName("VIEW-PAGE")));
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    /*
    Step Number: 1
		*Step Name: Step 1: Edit container when edit layout for portal's page
		*Step Description:
			- Select a page and click Edit layout
			- Select a container in page and click Edit container
			- Change something
			- Click Save and Finish button
			- Switch view mode
		*Input Data:

		*Expected Outcome:
			- The container is updated with the change value
			- The Layout of page is displayed in the view mode with all changes
	*/
    @Test
    public void test10_EditContainerWhenEditLayoutForPortalPage() {
        info("Test 10: Edit container when edit layout for portal's page");
        info("Get information of an application");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Add application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Test 09: Add container when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addContainer("One Row", "One Row", $(byId("oneRow")), $(byClassName("VIEW-PAGE")));
        info("Edit a container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.editContainer("Container", title, "", "");
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    /*
         Step Number: 1
         *Step Name: Step 1: Move container when edit layout for portal's page
         *Step Description:
             - Select a page and click Edit layout
             - Select a container in page and drag & drop it to new page
             - Switch view mode
         *Input Data:

         *Expected Outcome:
             - The container is move to new place
             - The Layout of page is displayed in the view mode with all changes
     */
    @Test
    public void test11_Add_Edit_Move_ContainerWhenEditLayoutForPortalPage() {
        info("Move a container to new place");
        info("Get information of an application");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Add application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Test 09: Add container when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addContainer("One Row", "One Row", $(byId("oneRow")), $(byClassName("VIEW-PAGE")));
        info("Edit a container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.editContainer("Container", title, "", "");
        info("Test 11: Move container when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.moveContainer(title, ELEMENT_CONTAINER_HOLDER_MOVE, ELEMENT_PORTLET, 87);
        info("Verify that the container is changed the position");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.checkPositions(byText("Group Navigation Portlet"), byClassName("UIRowContainer "));
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    /*
      Step Number: 1
       *Step Name: Step 1: Add container when edit layout for portal's page
       *Step Description:
           - Select a page and click Edit layout
           - Select Container tab from edit inline composer
           - Add container by drag & drop
           - Switch view mode
       *Input Data:
       *Expected Outcome:
           - Add container successfully
           - The Layout of page is displayed in the view mode with all changes
           /*Step Number: 1
       *Step Name: Step 1: Edit container when edit layout for portal's page
       *Step Description:
           - Select a page and click Edit layout
           - Select a container in page and click Edit container
           - Change something
           - Click Save and Finish button
           - Switch view mode
       *Input Data:

       *Expected Outcome:
           - The container is updated with the change value
           - The Layout of page is displayed in the view mode with all changes

           /*Step Number: 1
       *Step Name: Step 1: Edit container when edit layout for portal's page
       *Step Description:
           - Select a page and click Edit layout
           - Select a container in page and click Edit container
           - Change something
           - Click Save and Finish button
           - Switch view mode
       *Input Data:

       *Expected Outcome:
           - The container is updated with the change value
           - The Layout of page is displayed in the view mode with all changes

           /*Step Number: 1
       *Step Name: Step 1: Move container when edit layout for portal's page
       *Step Description:
           - Select a page and click Edit layout
           - Select a container in page and drag & drop it to new page
           - Switch view mode
       *Input Data:

       *Expected Outcome:
           - The container is move to new place
           - The Layout of page is displayed in the view mode with all changes
               /*Step Number: 1
       *Step Name: Step 1: Delete container when edit layout for portal's page
       *Step Description:
           - Select a page and click Edit layout
           - Select a container on page and click Delete container
           - Switch view mode

       *Input Data:

       *Expected Outcome:
           - The container is removed successfully
           - The Layout of page is displayed in the view mode with all changes

       info("Verify that the container is changed the position");
       navigationToolbar.goToPotalPages();
       portalManagePages.editPage(title,"group");
       pagecreationwizard.checkPositions ($(ELEMENT_CONTAINER_PRECEDING_PORTLET) ,$(ELEMENT_CONTAINER_FOLLOWING_PORTLET));
    */
    @Test
    public void test12_Add_Edit_Move_Delete_ContainerWhenEditLayoutForPortalPage() {
        info("Test 12: Delete container when edit layout for portal's page");
        info("Get information of an application");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Add application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("VIEW-PAGE")));
        info("Test 09: Add container when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addContainer("One Row", "One Row", $(byId("oneRow")), $(byClassName("VIEW-PAGE")));
        info("Edit a container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.editContainer("Container", title, "", "");
        info("Test 11: Move container when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.moveContainer(title, ELEMENT_CONTAINER_HOLDER_MOVE, ELEMENT_PORTLET, 87);
        info("Verify that the container is changed the position");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.checkPositions(byText("Group Navigation Portlet"),byText(""));
        info("Delete a container");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.deleteContainer(title);
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");
    }

    /**
     * <li> Case ID:99434.</li>
     * <li> Test Case Name: Add application into container in layout of portal.</li>
     * <p>
     * /*Step Number: 1
     * Step Name: Step 1: Add application into container in layout of portal
     * Step Description:
     * - Go to Site
     * - Choose a portal from list and click Edit layout
     * - Add container by drag & drop
     * - Drag & drop application into the container added above
     * - Switch view mode
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Add application into container successfully
     * - The portal is displayed in the view mode with all changes
     **/
    @Test
    public void test03_AddApplicationIntoContainerInLayoutOfPortal() {
        info("Test 03: Add application into container in layout of portal");
        info("Get information of an application");
        String pageName = "pageName" + getRandomNumber();
        String title = "title" + getRandomNumber();
        String idName = "idName" + getRandomNumber();
        info("Get value of group type");
        String type = "group";
        info("Add a new page with group type");
        navigationToolbar.goToPotalPages();
        portalManagePages.addPage(pageName, title, type);
        info("Test 09: Add container when edit layout for portal's page");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        pagecreationwizard.addContainer("One Row", "One Row", $(byId("oneRow")), $(byClassName("VIEW-PAGE")));
        info("Add application");
        navigationToolbar.goToPotalPages();
        portalManagePages.editPage(title, "group");
        info("Add an application to the layout");
        pagecreationwizard.addApp("",
                "Group Navigation Portlet",
                $(byId("Administration/portlet_GroupNavigationPortlet")),
                $(byClassName("UIRowContainer")));
        info("Verify that the application is added successfully in the container");
        info("delete page");
        navigationToolbar.goToPotalPages();
        portalManagePages.deletePage(title, "group");

    }
}