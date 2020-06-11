package org.exoplatform.platform.qa.ui.forum.smoke;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("smoke")
@Tag("forum")
public class ForumCategoryTestIT extends Base {
  HomePagePlatform homePagePlatform;

  ForumCategoryManagement forumCategoryManagement;

  ForumHomePage forumHomePage;

  ForumForumManagement forumForumManagement;

  ManageLogInOut manageLogInOut;

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
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
}

  /**
   * <li>Case ID:116743.</li>
   * <li>Test Case Name: Edit a category.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_AddEditDelete_ACategory() {
    info("Add a category");

    String nameCat = "nameCat" + getRandomNumber();
    String nameCat2 = "nameCat2" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Add a category Step Description: - Go to Forum page
     * - Click on Add Category - Put values - Save Input Data: Expected Outcome: -
     * Category is added successfully and shown in Forum home
     */
    info("go to Forum home page");
    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);

    info("Edit a category");
    info("edit category");
    forumCategoryManagement.editCategory(nameCat2);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToForum();
    info("Delete a category");
    info("delete category");
    forumCategoryManagement.deleteCategory(nameCat2);
  }


}
