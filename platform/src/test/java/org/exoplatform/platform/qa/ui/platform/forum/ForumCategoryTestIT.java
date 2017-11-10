package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("forum")
@Tag("sniff")
public class ForumCategoryTestIT extends Base {
  HomePagePlatform        homePagePlatform;

  ForumCategoryManagement forumCategoryManagement;

  ForumHomePage           forumHomePage;

  ForumForumManagement    forumForumManagement;

  ManageLogInOut          manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }



  /**
   * <li>Case ID:116745.</li>
   * <li>Test Case Name: Export a category.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Prepare data: Create a
   * category Step Description: - Create a category Input Data: Expected Outcome:
   * Category is created successfully. Step number: 2 Step Name: Export a category
   * Step Description: - Go to Forum page - Click on Administration, then click on
   * Export - Select category(s) - Click Save Input Data: Expected Outcome:
   * Category is exported successfully
   */

  @Test
  public void test01_ExportACategory() {
    info("Test 1: Export a category");
    String fileName = "fileName" + getRandomNumber();
    String nameCat = "nameCat" + getRandomNumber();
    String description = "description" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", description);

    info("Export category");
    forumHomePage.exportCategory(nameCat, fileName);
    info("Delete category");
    forumCategoryManagement.deleteCategory(nameCat);
  }
  /*
   * Step number: 1 Step Name: Import a category Step Description: - Click on
   * Administration, click on’ Import - Browse a file - Click Save Input Data:
   * Expected Outcome: Category is imported successfully
   */

  @Test
  public void test02_ImportACategory() {
    info("Test 1: Import a category");

    info("go to Forum home page");
    homePagePlatform.goToForum();

    info("Import category");
    forumHomePage.selectItemAdministrationMenu(ForumHomePage.specifAdministrationMenu.IMPORT);
    ELEMENT_UPLOAD_CATEGORIE_FORUM.uploadFromClasspath("fileNameCat.zip");
    ELEMENT_PROGRESS_BAR.waitUntil(Condition.disappears, Configuration.timeout);
    $(ELEMENT_SAVE_IMPORT).click();
    ELEMENT_BTN_OK.click();

    info("delete category");
    ELEMENT_CAT_CONTAINER.find(byText("categorieTest")).click();
    forumCategoryManagement.deleteCategory("categorieTest");
  }

  /**
   * <li>Case ID:116746.</li>
   * <li>Test Case Name: Export / Import a forum.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Prepare data: create a
   * caterory, forum Step Description: - Create a category - Create some forums in
   * the category Input Data: Expected Outcome: Category, forums are created
   * successfully
   */

  @Test
  public void test03_ExportAForum() {
    info("Test 3: Export a forum");
    String fileName = "fileName" + getRandomNumber();
    String nameCat = "nameCat" + getRandomNumber();
    String nameForum = "nameForum" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);

    info("Add a forum in the category");
    forumForumManagement.addForumSimple(nameForum, "", nameForum);

    /*
     * Step number: 2 Step Name: Export forums Step Description: - Open this
     * category - Click on Manage Categoy, click on Export Forum(s) - Select
     * forum(s) to export - Click Save Input Data: Expected Outcome: Forums are
     * exported successfully
     */
    info("Export the forum");
    forumHomePage.goToCategory(nameCat);
    forumCategoryManagement.exportForum(nameForum, fileName);

    info("delete category");
    forumCategoryManagement.deleteCategory(nameCat);
  }

  @Test
  public void test04_ImportAForum() {
    info("Test 4: Import a forum");
    String nameCat = "nameCat" + getRandomNumber();

    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);

    info("Import forum");
    forumCategoryManagement.selectItemManageCategoryMenu(ForumCategoryManagement.specifManageCategoryMenu.IMPORT_FORUM);
    ELEMENT_UPLOAD_CATEGORIE_FORUM.uploadFromClasspath("ks-export-forum.xml");
    ELEMENT_PROGRESS_BAR.waitUntil(Condition.disappears, Configuration.timeout);
    $(ELEMENT_SAVE_IMPORT).click();
    ELEMENT_BTN_OK.click();

    info("delete category");
    ELEMENT_CAT_CONTAINER.find(byText(nameCat)).click();
    forumCategoryManagement.deleteCategory(nameCat);
  }

}
