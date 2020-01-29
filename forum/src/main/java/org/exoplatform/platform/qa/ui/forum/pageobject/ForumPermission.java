package org.exoplatform.platform.qa.ui.forum.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_GROUP_SEARCH_USER_SEARCH;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class ForumPermission {

    private final TestBase testBase;

    public ManageAlert alert;

    private ElementEventTestBase evt;

    /**
     * constructor
     *
     * @param testBase TestBase
     */
    public ForumPermission(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.alert = new ManageAlert(testBase);
    }

    /**
     * select permission in category
     *
     * @param isMod       boolean
     * @param isStartTop  boolean
     * @param isPostReply boolean
     * @param isViewPost  boolean
     */
    public void selectPermInCategory(boolean isMod, boolean isStartTop, boolean isPostReply, boolean isViewPost) {
        info("select permission");
        if (isMod) {
            evt.check(ELEMENT_PERM_MOD_CHECKBOX, 2);
        } else {
            evt.uncheck(ELEMENT_PERM_MOD_CHECKBOX, 2);
        }
        if (isStartTop) {
            evt.check(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
        } else {
            evt.uncheck(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
        }
        if (isPostReply) {
            evt.check(ELEMENT_PERM_POSTREPLY_CHECKBOX, 2);
        } else {
            evt.uncheck(ELEMENT_PERM_POSTREPLY_CHECKBOX, 2);
        }
        if (isViewPost) {
            evt.check(ELEMENT_PERM_VIEWPOST_CHECKBOX, 2);
        } else {
            evt.uncheck(ELEMENT_PERM_VIEWPOST_CHECKBOX, 2);
        }
    }

    /**
     * select permission in topic
     *
     * @param isView boolean
     * @param isPost boolean
     */
    public void selectPermInTopic(boolean isPost, boolean isView) {
        info("select permission");
        if (isPost) {
            evt.check(ELEMENT_PERM_MOD_CHECKBOX, 2);
        } else {
            evt.uncheck(ELEMENT_PERM_MOD_CHECKBOX, 2);
        }
        if (isView) {
            evt.check(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
        } else {
            evt.uncheck(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
        }
    }

    /**
     * Select permission group membership
     *
     * @param groupPath String
     * @param member    String
     */
    public void selectPermGroupMemberMes(String groupPath, String member) {
        String[] temp;
        info("select group membership");
        evt.click(ELEMENT_MESSAGE_ROLE_ICON, 0, true);
        evt.waitForAndGetElement(ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP);
        temp = groupPath.split("/");
        for (int i = 0; i < temp.length; i++) {
            evt.click(ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]), 0, true);
        }
        evt.click(ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP.replace("$group", member), 0, true);
        evt.waitForElementNotPresent(ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP);
    }

    public void addUserPermission(String user, String category, String forum, String topic) {
        if (!topic.isEmpty()) {
            String topicTitle = "topicTitle" + getRandomNumber();
            String topicMessage = "topicMessage" + getRandomNumber();
            $(ELEMENT_FORUM_TOPIC_TITLE).setValue(topicTitle);
            switchTo().frame($(ELEMENT_FORUM_MESSAGE));
            $(byXpath("/html/body")).waitUntil(Condition.visible, Configuration.timeout).sendKeys(topicMessage);
            switchTo().defaultContent();
        }
        $(byXpath("//*[contains(text(),'Permissions')]")).click();
        if (!forum.isEmpty()) {
            $(By.xpath("//a[@data-original-title='Select User']")).click();
        }
        if (!category.isEmpty()) {
            $(By.xpath("(//a[@data-original-title='Select User'])[2]")).click();
        }
        $(ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT).setValue(user);
        $(ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT).click();
        $(ELEMENT_GROUP_SEARCH_USER_SEARCH).waitUntil(visible, Configuration.timeout).click();
        if (!topic.isEmpty()) {
            $(byXpath("(//th[@class='center']/span[@class='uiCheckbox'])[2]")).click();
        } else {
            $(byXpath("//th[@class='center']/span[@class='uiCheckbox']")).click();
        }
        $(byXpath("//a[text()=\"Add\"]")).click();
        $(byXpath("//button[text()=\"Add\"]")).click();
        info("Check User Permission Added");
        Assert.assertEquals($(byXpath("//div[text()='${userName}']".replace("${userName}", user))).getText(), user);
        if (!topic.isEmpty()) {
            $(ELEMENT_FORUM_SETTINGS_SUBMIT).waitUntil(visible, Configuration.timeout).click();
        }
    }
}
