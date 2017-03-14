package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.locator.HomePageLocator;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.FaqHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.forum.ForumHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.openqa.selenium.WebDriver;

public class HomePagePlatform extends HomePageLocator{
    WikiHomePage wHome;
	CalendarHomePage cHome;
	SpaceManagement sMang;
	AnswerHomePage aHome;
	ForumHomePage fHome;
	FaqHomePage fqHome;
	ConnectionsManagement connMg;
	/**
	 * constructor
	 * @param dr
	 */
	public HomePagePlatform(WebDriver dr){
		this.driver=dr;
		wHome = new WikiHomePage(dr);
		cHome = new CalendarHomePage(dr);
		sMang = new SpaceManagement(dr);
		aHome = new AnswerHomePage(dr);
		fHome = new ForumHomePage(dr);
		fqHome = new FaqHomePage(dr);
		connMg = new ConnectionsManagement(dr);
	}

	/**
	 * Go to Wiki portlet
	 */
	public void goToWiki(){
		info("--Go to Wiki--");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_WIKI_LINK_PLF, DEFAULT_TIMEOUT, 1);
		click(ELEMENT_WIKI_LINK_PLF);
		Utils.pause(2000);
	}
	/**
	 * Go to Documents
	 */
	public void goToDocuments(){
		info("--Go to Documents--");
		click(ELEMENT_DOCUMENTS_LINK_PLF);
		Utils.pause(2000);
	}
	/**
	 * Go to Home page
	 */
	public void goToHomePage(){
		info("--Go to Home page--");
		Utils.pause(3000);
		int repeat=0;
		while(waitForAndGetElement(ELEMENT_PLF_OFFICE_RIGHT_COLUMN,2000,0)==null){
			if(repeat>5)break;
			info("Click on Home link of intranet page");
			click(ELEMENT_HOME_LINK_PLF);
			if(waitForAndGetElement(ELEMENT_PLF_OFFICE_RIGHT_COLUMN,2000,0)!=null)
				break;
			repeat++;
		}
		waitForAndGetElement(ELEMENT_PLF_OFFICE_RIGHT_COLUMN);
		info("the intranet is shown sucessfully"); 
		Utils.pause(2000);
	}


	/**
	 * Go to Home Calendar Page
	 */
	public void goToCalendarPage(){
			info("-- Go to calendar home page --");
			Utils.pause(3000);
			waitForAndGetElement(ELEMENT_CALENDAR_LINK_PLF, DEFAULT_TIMEOUT, 1);
			info("click on Calendar link");
			click(ELEMENT_CALENDAR_LINK_PLF);
			info("Verify that Calendar page is shown");
			waitForAndGetElement(cHome.ELEMENT_CALENDAR_WORKING_PANEL);
			info("The calendar page is shown successfully");
	}

	/**
	 * Go to my spaces
	 */
	public void goToMySpaces(){
		info("-- Go to my spaces --");
		int repeat=0;
		while(waitForAndGetElement(ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0) == null){
			if(repeat>5)break;
			click(ELEMENT_MY_SPACE_LINK_PLF);
			Utils.pause(2000);
			repeat++;
		}
		waitForAndGetElement(ELEMENT_ADDNEWSPACE_BUTTON, 3000,1);
	}
	/**
	 * Go to All space list
	 */
	public void goToAllSpace(){
		info("Click on Join a space link");
		click(ELEMENT_ALL_SPACE_JOIN_LINK,0,true);
		Utils.pause(2000);
	}

	/**
	 * Go to answer page
	 */
	public void goToAnswer(){
		info("-- Go to answer page --");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_ANSWER_LINK_PLF);
		click(ELEMENT_ANSWER_LINK_PLF,0,true);
		if(waitForAndGetElement(aHome.ELEMENT_ANSWER_PORTLET,5000,0)==null)
			driver.navigate().refresh();
		waitForAndGetElement(aHome.ELEMENT_ANSWER_PORTLET);
		Utils.pause(2000);
	}

	/**
	 * Go to forum page
	 */
	public void goToForum(){
		do {
			info("-- Go to forum page --");
			info("Click on Forum link");
			click(ELEMENT_FORUM_LINK_PLF);
			info("Verify that the forum portlet is shown");
		} while (waitForAndGetElement(fHome.ELEMENT_FORUM_PORTLET, DEFAULT_TIMEOUT, 1) == null);
		info("The forum portlet is shown successfully");
	}

	/**
	 * Go to faq page
	 */
	public void goToFaq(){
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		info("Base url is " + baseUrl);
		String url = baseUrl + "/intranet/home/FAQ";
		info("-- Go to FAQ page --");
		driver.get(url);
		waitForAndGetElement(fqHome.ELEMENT_FAQ_QUESTION_LIST);
	}
	/**
	 * Open Connections page
	 */
	public void goToConnections(){
		info("--Go to Connections page---");
		Utils.pause(2000);
		info("Click on Connection link");
		click(ELEMENT_CONNECTIONS_LINK_PLF, 2);
		info("Verify that the connections portlet is shown");
		waitForAndGetElement(connMg.ELEMENT_CONNECTION_EVERYONE_TITLE,2000,0);
		info("The connections portlet is shown successfully");
	}
	
   
    /**
     * Go to a space
     * @param space
     */
	public void goToSpecificSpace(String space){
		info("Go to space "+space);
		click(ELEMENT_SPECIFIC_PANEL.replace("{$space}",space),2);
		Utils.pause(3000);
	}
	/**
	 * Open friend profile page
	 * @param username
	 */
	public void goToFriendProfilePage(String username){
		info("Go to Friend profile page");
		this.driver.get(baseUrl+"/intranet/profile/"+username);
		Utils.pause(2000);
	}
	/**
	 * Define display mode's type of the AS
	 * as My Activities,All Activities,...
	 */
	public enum displayModeType{
		My_Activities,All_Activities,My_Spaces,Connections;
	}
    /**
     * Select display mode for AS
     * @param type
     *             as My Activities, All Activities....
     */
	public void selectDisplayMode(displayModeType type){
		info("Open drop menu");
		click(ELEMENT_HOMEPAGE_DROP_MENU_ARROW);
		switch(type){
		case My_Activities:
			info("Select My Activities");
			click(ELEMENT_HOMEPAGE_DROP_MENU_MY_ACTIVITIES);
			break;
		case All_Activities:
			info("Select All Activities");
			click(ELEMENT_HOMEPAGE_DROP_MENU_ALL_ACTIVITIES);
			break;
		case My_Spaces:
			info("Select My Spaces");
			click(ELEMENT_HOMEPAGE_DROP_MENU_MY_SPACES);
			break;
		case Connections:
			info("Select Connections");
			click(ELEMENT_HOMEPAGE_DROP_MENU_CONNECTIONS);
			break;
			}
		}
		
	 /** Check display of user in Invitation gadget
	 * @param user
	 * @param isPresent
	 */
	public void checkDisplayInInvitationGadget(String user,boolean isPresent){
		info("check display of user in Invitation");
		goToHomePage();
		if(isPresent)
			waitForAndGetElement(ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",user));
		else
			waitForElementNotPresent(ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",user));
	}
	/**
     * Load More Activities
     */
	public void loadMoreActivities(){
		Utils.pause(2000);
		info("Load more activities");
		waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON, DEFAULT_TIMEOUT, 1);
		click(ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON);
	}
	/**
	 * Check display of user in Suggestion gadget
	 * @param user
	 * @param isPresent
	 */
	public void checkDisplayInSuggestionGadget(String user,boolean isPresent){
		info("check display of user in Suggestion");
		goToHomePage();
		if(isPresent)
			waitForAndGetElement(ELEMENT_SUGGESTION_NAME.replace("${name}",user));
		else
			waitForElementNotPresent(ELEMENT_SUGGESTION_NAME.replace("${name}",user));
	}
	
}

