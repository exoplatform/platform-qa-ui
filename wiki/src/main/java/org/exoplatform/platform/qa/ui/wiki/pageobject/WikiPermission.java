package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiPermission {

    private final TestBase       testBase;

    public Dialog                dialog;

    public Button                button;

    public PlatformBase          plf;

    private ElementEventTestBase evt;

    /**
     * constructor
     *
     * @param testBase TestBase
     */
    public WikiPermission(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.button = new Button(testBase);
        this.dialog = new Dialog(testBase);
        this.plf = new PlatformBase(testBase);
    }

    /**
     * Delete a group, users in permission popup
     *
     * @param groupUsers String
     */
    public void deletePermission(String groupUsers) {
        By bDelete = By.xpath(ELEMENT_DELETE_PERMISSION.replace("$user", groupUsers));
        if (evt.waitForAndGetElement(bDelete, 20000, 0) != null) {
            info("--Delete permission--");
            evt.click(bDelete);
            evt.waitForElementNotPresent(bDelete);
        }

    }

    /**
     * Select permission for a username/group/membership
     *
     * @param userGroup String
     * @param type permissionType
     */
    public void selectPermission(String userGroup, permissionType type) {
        switch (type) {
            case View_Pages:
                info("Select View pages permission");
                $(byId("VIEWPAGE"+userGroup)).parent().click();
                break;
            case Edit_Pages:
                info("Select Edit pages permission");

                $(byId("EDITPAGE"+userGroup)).parent().click();
                break;
            case Admin_Pages:
                info("Select View pages permission");
                $(byId("ADMINPAGE"+userGroup)).parent().click();
                break;
            case Admin_Wiki:
                info("Select View pages permission");
                $(byId("ADMINSPACE"+userGroup)).parent().click();
                break;
        }
    }

    /**
     * Unselect permission for a username/group/membership
     *
     * @param userGroup String
     * @param type permissionType
     */
    public void unSelectPermission(String userGroup, permissionType type) {
        switch (type) {
            case View_Pages:
                info("un Select View pages permission");
                evt.uncheck(ELEMENT_PERMISSION_VIEW_CHECKBOX.replace("$userGroup", userGroup), 2);
                break;
            case Edit_Pages:
                info("un Select View pages permission");
                if($(byXpath(ELEMENT_PERMISSION_EDIT_CHECKBOX.replace("$userGroup", userGroup))).is(Condition.checked))
                    $(byXpath(ELEMENT_PERMISSION_EDIT_CHECKBOX.replace("$userGroup", userGroup))).click();
                break;
            case Admin_Pages:
                info("Select View pages permission");
                evt.uncheck(ELEMENT_PERMISSION_ADMPAGE_CHECKBOX.replace("$userGroup", userGroup), 2);
                break;
            case Admin_Wiki:
                info("Select View pages permission");
                evt.uncheck(ELEMENT_PERMISSION_ADMWIKI_CHECKBOX.replace("$userGroup", userGroup), 2);
                break;
        }
    }


    /**
     * Add a group/user/membership to permission table by type
     *
     * @param groupUsers String
     */
    public void addPermisisonByType(String groupUsers) {
        if (!groupUsers.isEmpty()) {
            info("Type a:" + groupUsers + " to the textbox");
            $(ELEMENT_PERMISSION_TYPE_INPUT).setValue(groupUsers);
            info("Click on Add button");
            $(ELEMENT_PERMISSION_ADD_BUTTON).click();
            info("The group/user/membership is added successfully");
        }
    }

    /**
     * Click on Save button in More/Permission
     * @param booleans Boolean
     */
    public void savePermisison(Boolean... booleans) {
        boolean savePresent = (booleans.length > 0 ? booleans[0] : true);
        info("Click on Save button");
        $(ELEMENT_PERMISSION_BUTTON_SAVE).click();
        if (!savePresent)
            $(ELEMENT_PERMISSION_BUTTON_SAVE).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

    }

    /**
     * Define permission types as View page or Edit page
     */
    public enum permissionType {
        View_Pages, Edit_Pages, Admin_Pages, Admin_Wiki;
    }
}