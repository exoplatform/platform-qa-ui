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
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_TOPIC_LOCK;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_TOPIC_MORE_ACTION;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_TOPIC_QUOTE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 16/03/2018.
 */


@Tag("sniff")
@Tag("forum")
public class ForumTopicLockTestIT extends Base {

    HomePagePlatform        homePagePlatform;

    ForumForumManagement    forumForumManagement;

    ForumTopicManagement    forumTopicManagement;

    ForumCategoryManagement forumCategoryManagement;

    ForumHomePage           forumHomePage;

    ManageLogInOut          manageLogInOut;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        forumForumManagement = new ForumForumManagement(this);
        forumTopicManagement = new ForumTopicManagement(this);
        forumHomePage = new ForumHomePage(this);
        forumCategoryManagement = new ForumCategoryManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(username, password);
    }


    /*
       bug FORUM-1317
    */

    @Test
    public  void test01_Lock_Topic(){

        String name = "name" + getRandomNumber();
        String desc = "desc" + getRandomNumber();
        String topic = "topic" + getRandomNumber();

        homePagePlatform.goToForum();
       info("Add a category");
        forumCategoryManagement.addCategorySimple(name, "", desc);
        info("Add a forum in the category");
        forumForumManagement.addForumSimple(name, "", desc);
        info("Add and go to a topic in the forums");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(topic, topic, "", "");
        info("Go to a topic");
        forumHomePage.goToTopic(topic);
        $(ELEMENT_TOPIC_MORE_ACTION).click();
        $(ELEMENT_TOPIC_LOCK).click();
        $(ELEMENT_TOPIC_QUOTE).shouldNot(Condition.visible);

    }

}



