package org.exoplatform.platform.qa.ui.platform.wiki.functional;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiSettingPage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class WikiTemplateTestIT extends Base{
HomePagePlatform homePagePlatform;
WikiHomePage wikiHomePage;
    WikiSettingPage wikiSettingPage;
WikiValidattions wikiValidattions;
ManageLogInOut manageLogInOut;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
    homePagePlatform=new HomePagePlatform(this);
    wikiHomePage=new WikiHomePage(this);
    wikiSettingPage=new WikiSettingPage(this);
    wikiValidattions=new WikiValidattions(this);
    manageLogInOut=new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");
    }
/**
*<li> Case ID:139395.</li>
*<li> Test Case Name: Create new template.</li>
*<li> Pre-Condition: </li>
*<li> Post-Condition: </li>
*/
@Test
public  void test01_CreateNewTemplate() {
    info("Add a new template");
    String title = "title"+getRandomNumber();
    String des = "des"+getRandomNumber();
    String content = "content"+getRandomNumber();
    info("Test 1: Create new template");
    /*Step Number: 1
    *Step Name: Step 1: Open form to create new template
    *Step Description:
        - Go to Browser
        -> Wiki Settings
        - Select Template tab
        - Click on Add More Templates link
    *Input Data:

    *Expected Outcome:
        Form to create new template appears*/
    info("Go to Wiki Settings");
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();

    /*Step number: 2
    *Step Name: Step 2: Add new template
    *Step Description:
        - Put title, description, content
        - Click on Save Template
    *Input Data:

    *Expected Outcome:
        New template is created. It'll be shown in Template form*/

    wikiSettingPage.addTemplate(title, des, content);
    wikiSettingPage.saveTemplate();
    wikiHomePage.confirmWaringMessage(true);

    info("Verify that new tempate is created. It'll be shown in template form");
     wikiValidattions.verifyTemplateInList(title);
     wikiSettingPage.deleteTemplate(title);
 }

}