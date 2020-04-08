package org.exoplatform.platform.qa.ui.forum.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_SAVE_BTN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class ForumCategoryManagement {

    private final TestBase testBase;

    public ManageAlert alert;

    public Button button;

    public ForumPermission forumPerm;

    public ManageFileTestBase mftb;

    private ElementEventTestBase evt;

    /**
     * constructor
     *
     * @param testBase
     */
    public ForumCategoryManagement(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.alert = new ManageAlert(testBase);
        this.button = new Button(testBase);
        this.forumPerm = new ForumPermission(testBase);
        this.mftb = new ManageFileTestBase(testBase);
    }

    /**
     * Add a new category By QuynhPT
     *
     * @param nameCat     the title of category
     * @param order       the order's number as:0,1,2,3...(0 is default value)
     * @param description the content of description for category
     */
    public void addCategorySimple(String nameCat, String order, String description) {

        info("click on Add Category button");
        $(ELEMENT_ACTIONBAR_ADDCATEGORY).click();
        info("input the title for the category");
        $(ELEMENT_ADDCATEGORY_POPUP_TITLE).val(nameCat);
        info("check and input description");
        $(ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION).val(description);
        info("Click on Save button");
        $(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON).click();
    }

    /**
     * select a item in Manage Category Menu By QuynhPT
     *
     * @param item enum
     */
    public void selectItemManageCategoryMenu(specifManageCategoryMenu item) {
        sleep(Configuration.timeout);
        info("Waiting manage menu is shown");
        $(ELEMENT_MENU_MANAGE_CATEGORY).waitUntil(appears, Configuration.timeout);
        info("Click on Manage menu");
        $(ELEMENT_MENU_MANAGE_CATEGORY).waitUntil(visible,Configuration.timeout).click();
        switch (item) {
            case EDIT_CATEGORY:
                info("click on Edit link");
                $(ELEMENT_EDIT_CATEGORY).click();
                break;
            case EXPORT_FORUM:
                info("Click on Export link");
                $(ELEMENT_EXPORT_FORUM).click();
                break;
            case IMPORT_FORUM:
                info("Click on Import link");
                $(ELEMENT_IMPORT_FORUM).click();
                break;
            case DELETE:
                info("Click on Delete link");
                $(ELEMENT_DELETE_CATEGORY).click();
                $(ELEMENT_OK_DELETE).click();
                break;
            case WATCHES:
                break;
            case ADD_FORUM:
                break;
            case EDIT_FORUM:
                break;
            case LOCK:
                break;
            case UNLOCK:
                break;
            case CLOSE:
                break;
            case OPEN:
                break;
            case MOVE:
                break;
            case REMOVE:
                break;
        }
    }

    /**
     * Edit a category
     *
     * @param newName String
     */
    public void editCategory(String newName) {
        selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
        info("Imput a new name");
        $(ELEMENT_ADDCATEGORY_POPUP_TITLE).val(newName);
        info("Save all changes");
        $(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON).click();

    }

    /**
     * Delete Category By QuynhPT
     *
     * @param nameCat String
     */
    public void deleteCategory(String nameCat) {
        // TODO Auto-generated method stub
        info("Wait the category is shown");
        if ($(ELEMENT_MENU_MANAGE_CATEGORY).is(not(exist))) {
            ELEMENT_CAT_CONTAINER.find(byText(nameCat)).waitUntil(appears, Configuration.openBrowserTimeoutMs + Configuration.timeout).click();
        }
        info("Select Delete link");
        selectItemManageCategoryMenu(specifManageCategoryMenu.DELETE);
        info("Verify that the category is deleted");
        $(withText(nameCat)).waitUntil(Condition.not(exist),Configuration.openBrowserTimeoutMs);
        info("The category is deleted successfully");

    }

    /**
     * Export a forum
     *
     * @param forumName String
     * @param fileName  String
     */
    public void exportForum(String forumName, String fileName) {
        selectItemManageCategoryMenu(specifManageCategoryMenu.EXPORT_FORUM);
        info("Uncheck All check boxes");
        ELEMENT_CHECKBOX_ALL_FORUM_CATEGORIE.click();
        sleep(2000);
        info("Select check box of the forum");
        ELEMENT_CHECKBOX_SELECT_ONE_FORUM_CATEGORIE.click();
        sleep(2000);
        info("input name");
        $(ELEMENT_FILENAME_INPUT).setValue(fileName);
        sleep(2000);
        info("Save all changes");
        $(byXpath(ELEMENT_SAVE_BTN)).click();
        sleep(Configuration.timeout);
    }

    /**
     * Add Category with Permissions
     */
    public void addCategoryWithPermissions(String nameCat, String description, String user) {
        info("click on Add Category button");
        $(ELEMENT_ACTIONBAR_ADDCATEGORY).click();
        info("input the title for the category");
        $(ELEMENT_ADDCATEGORY_POPUP_TITLE).val(nameCat);
        info("check and input description");
        $(ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION).val(description);
        forumPerm.addUserPermission(user, "category", "", "");
        info("Click on Save button");
        $(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON).click();
    }

    public void addForumWithPermissions(String nameForum, String order, String description, String user) {
        $(ELEMENT_ACTIONBAR_ADDFORUM).waitUntil(Condition.appears, Configuration.timeout);
        info("click on Add forum button");
        $(ELEMENT_ACTIONBAR_ADDFORUM).click();
        info("input the title for the forum");
        $(ELEMENT_ADDFORUM_POPUP_TITLE).val(nameForum);
        info("check and input Oder field");
        $(ELEMENT_ADDFORUM_POPUP_ORDER).val(order);
        info("check and input description");
        $(ELEMENT_ADDFORUM_POPUP_DESCRIPTION).val(description);
        forumPerm.addUserPermission(user, "", "forum", "");
        info("Click on Save button");
        $(ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON).click();
        info("Finish adding new forum");
    }

    /**
     * list sublinks in Manage Cagegory menu
     *
     * @author quynhpt
     */
    public enum specifManageCategoryMenu {
        EDIT_CATEGORY, EXPORT_FORUM, IMPORT_FORUM, DELETE, WATCHES, ADD_FORUM, EDIT_FORUM, LOCK, UNLOCK, OPEN, CLOSE, MOVE, REMOVE;
    }
}
