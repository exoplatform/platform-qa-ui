package org.exoplatform.platform.qa.ui.platform.forum;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_REMOVE_ICON;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_START_TOPIC_POPUP_TITLE_FILED;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_SUBMIT_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 28/03/2018.
 */

@Tag("forum")
@Tag("sniff")
public class RemoveAttachFileInTopic extends Base{

    HomePagePlatform homePagePlatform;
    ForumCategoryManagement forumCategoryManagement;
    ForumHomePage forumHomePage;
    ForumForumManagement forumForumManagement;
    ForumTopicManagement forumTopicManagement;
    ManageLogInOut manageLogInOut;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        forumTopicManagement = new ForumTopicManagement(this);
        forumHomePage = new ForumHomePage(this);
        forumCategoryManagement = new ForumCategoryManagement(this);
        forumForumManagement= new ForumForumManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(username, password);
    }

    /*
    bug FORUM-1382
    */

    @Test
    public void test_VerifyRemoveAttachFile(){
        String category= "category" + getRandomNumber();
        String desc= "description" + getRandomNumber();
        String forum = "forum" + getRandomNumber();
        String topic = "topic" + getRandomNumber();
        info("Go to Forum portlet");
        homePagePlatform.goToForum();
        info("Add a category");
        forumCategoryManagement.addCategorySimple(category, "", desc);
        info("Add a forum in the category");
        forumForumManagement.addForumSimple(forum, "", desc);
        info("Add and go to a topic in the forums");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(topic, topic, "", "");
        info("Go to a topic");
        forumHomePage.goToTopic(topic);
        forumTopicManagement.selectItemMoreActionMenuTopic(ForumTopicManagement.specifMoreActionMenuTopic.EDIT);
        $(byId("UIEditTopicContainer")).find(byClassName("uiIconDelete")).click();
        $(ELEMENT_SUBMIT_BUTTON).click();
        info("verify that the attach file is removed");
        forumHomePage.goToTopic(topic);
        forumTopicManagement.selectItemMoreActionMenuTopic(ForumTopicManagement.specifMoreActionMenuTopic.EDIT);
        $(byId("UIEditTopicContainer")).find(byClassName("uiIconDelete")).shouldNotBe(Condition.visible);
        $(ELEMENT_SUBMIT_BUTTON).click();
        info("Delete category");
        forumHomePage.goToHomeCategory();
        forumCategoryManagement.deleteCategory(category);

    }


}



