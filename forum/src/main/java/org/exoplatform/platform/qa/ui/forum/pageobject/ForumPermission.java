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
