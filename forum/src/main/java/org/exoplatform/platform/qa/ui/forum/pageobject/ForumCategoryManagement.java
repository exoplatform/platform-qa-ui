package org.exoplatform.platform.qa.ui.forum.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;
import org.junit.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
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
        info("Waiting manage menu is shown");
        $(ELEMENT_MENU_MANAGE_CATEGORY).waitUntil(appears, Configuration.timeout);
        info("Click on Manage menu");
        $(ELEMENT_MENU_MANAGE_CATEGORY).click();
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
            ELEMENT_CAT_CONTAINER.find(byText(nameCat)).waitUntil(appears, Configuration.timeout).click();
        }
        info("Select Delete link");
        selectItemManageCategoryMenu(specifManageCategoryMenu.DELETE);
        info("Verify that the category is deleted");
        $(withText(nameCat)).shouldNot(exist);
        info("The category is deleted successfully");

    }

    /**
     * Cancel all changes of Add Category By QuynhPT
     */
    public void cancelChangeCategory() {
        evt.waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON);
        evt.click(ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON);

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
        info("Select check box of the forum");
        ELEMENT_CHECKBOX_SELECT_ONE_FORUM_CATEGORIE.click();
        info("input name");
        $(ELEMENT_FILENAME_INPUT).setValue(fileName);
        info("Save all changes");
        $(ELEMENT_SAVE_BTN).waitUntil(visible,Configuration.timeout).click();
    }

    /**
     * Import a forum
     *
     * @param folderDowloadFile String
     * @param nameFile          String
     */
    public void importForum(String folderDowloadFile, String nameFile) {
        selectItemManageCategoryMenu(specifManageCategoryMenu.IMPORT_FORUM);
        mftb.importFile(folderDowloadFile, nameFile);
        button.ok();
    }

    /**
     * Edit permission of category
     *
     * @param cat         String
     * @param groupPath   String
     * @param member      String
     * @param isMod       boolean
     * @param isPostReply boolean
     * @param isStartTop  boolean
     * @param isViewPost  boolean
     */
    public void editPermOfCategory(String cat,
                                   String groupPath,
                                   String member,
                                   boolean isMod,
                                   boolean isStartTop,
                                   boolean isPostReply,
                                   boolean isViewPost) {
        info("edit permission of category:" + cat);
        evt.click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
        selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
        evt.click(ELEMENT_PERM_TAB, 0, true);
        forumPerm.selectPermGroupMember(groupPath, member, isMod, isStartTop, isPostReply, isViewPost);
        evt.click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
    }

    /**
     * Edit permission of category
     *
     * @param cat       String
     * @param groupPath String
     * @param member    String
     */
    public void editRestrictedAudience(String cat, String groupPath, String member) {
        info("edit permission of category:" + cat);
        evt.click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
        selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
        forumPerm.selectPermGroupMemberRestricted(groupPath, member);
        evt.click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
    }

    /**
     * Check display of category
     *
     * @param cat       String
     * @param isDisplay boolean
     */
    public void checkDisplayOfCat(String cat, boolean isDisplay) {
        info("check display of category:" + cat);
        if (isDisplay) {
            evt.waitForAndGetElement(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
            evt.click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat), 0, true);
        } else {
            evt.waitForElementNotPresent(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
        }
    }

    public void gotoUserSelectorForRestrictedAudience() {
        info("-- Go to wiki home page --");
        evt.click(ELEMENT_ADDCATEGORY_RESTRICTED_AUDIENCE);
    }

    /**
     * function: Search user in User Seletion Form in Restricted Audience
     *
     * @param searchOption String
     * @param user         String
     */

    public void searchUser(String user, String searchOption) {
        info("--Search user " + user + "--");
        evt.type(ELEMENT_RESTRICTED_AUDIENCE_INPUT_SEARCH_USER_NAME, user, true);
        evt.select(ELEMENT_RESTRICTED_AUDIENCE_SELECT_SEARCH_OPTION, searchOption);
        evt.click(ELEMENT_RESTRICTED_AUDIENCE_SEARCH_ICON);
        evt.waitForTextPresent(user);
    }

    public void searchUserNotFound(String user, String searchOption) {
        info("--Search user " + user + "--");
        evt.type(ELEMENT_RESTRICTED_AUDIENCE_INPUT_SEARCH_USER_NAME, user, true);
        evt.select(ELEMENT_RESTRICTED_AUDIENCE_SELECT_SEARCH_OPTION, searchOption);
        evt.click(ELEMENT_RESTRICTED_AUDIENCE_SEARCH_ICON);
        evt.waitForTextNotPresent(user);
    }

    /**
     * Close User Selector page
     */
    public void closeUserSelector() {
        info("-- Go to User Selector page --");
        evt.click(ELEMENT_RESTRICTED_AUDIENCE_CLOSE_USER_SELETOR);

    }

    /**
     * Open permissions tab in Add/Edit Category form
     */
    public void goToPermissions() {
        info("Permissions page");
        evt.click(ELEMENT_CATEGORY_PERMISSION_TAB);
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
     * Select User in Permission tab
     */
    public void gotoUserSelectorInPermissionTab() {
        info("-- Go to wiki home page --");
        evt.click(ELEMENT_CATEGORY_PERMISSION_TAB_USER_SELECTOR);
    }

    /**
     * Cancel Add/Edit Category form
     */
    public void cancelAddEditCategory() {
        info("Cancel Add or Edit Category");
        evt.click(ELEMENT_CATEGORY_CANCEL);
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
