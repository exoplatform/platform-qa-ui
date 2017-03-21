package org.exoplatform.platform.qa.ui.selenium.platform.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;

public class ForumForumManagement extends ForumLocator {
	
	ManageAlert alert;
	ForumHomePage forumHP;
	ForumPermission forumPerm;
	/**
	 * constructor
	 * @param dr
	 */
	public ForumForumManagement(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
		forumHP = new ForumHomePage(dr);
		forumPerm = new ForumPermission(driver);
	}

	/**
	 * Add a new forum
	 * By QuynhPT
	 * @param nameForum
	 * @param order
	 * @param description
	 */
	public void addForumSimple(String nameForum, String order, String description) {
		// TODO Auto-generated method stub
		info("Add forum simple");
		waitForAndGetElement(ELEMENT_ACTIONBAR_ADDFORUM,DEFAULT_TIMEOUT,1);
		info("click on Add forum button");
		click(ELEMENT_ACTIONBAR_ADDFORUM);
	    info("input the title for the forum");
	    waitForAndGetElement(ELEMENT_ADDFORUM_POPUP_TITLE, DEFAULT_TIMEOUT, 1);
	    type(ELEMENT_ADDFORUM_POPUP_TITLE,nameForum,true);
	    
	    info("check and input Oder field");
	    if(order!=null && order!=""){
	    	info("Clear all old order data");
	    	waitForAndGetElement(ELEMENT_ADDFORUM_POPUP_ORDER).clear();
	    	info("Input new order data");
	    	type(ELEMENT_ADDFORUM_POPUP_ORDER,order,true);
	    }
        info("check and input description");
	    if (description!=null && description!=""){
	    	info("Clear all old description data");
	    	waitForAndGetElement(ELEMENT_ADDFORUM_POPUP_DESCRIPTION).clear();
	    	info("Input new description data");
	    	type(ELEMENT_ADDFORUM_POPUP_DESCRIPTION,description,true);
	    }
		info("Click on Save button");
		click(ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON);
		Utils.pause(2000);
		info("Finish adding new forum");
	}
	
	/**
	 * Cancel all changes of Add FORUM
	 * By QuynhPT
	 */
	public void cancelChangeAddForum(){
		waitForAndGetElement(ELEMENT_ADDFORUM_POPUP_CANCEL_BUTTON);
		click(ELEMENT_ADDFORUM_POPUP_CANCEL_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * list sublinks in More Action menu of Forum
	 * @author quynhpt
	 *
	 */
	public enum specifMoreActionMenu{
		START_TOPIC,EDIT,LOCK,UNLOCK,CLOSE,OPEN,MOVE,DELETE,EXPORT_FORUM,WATCHES,BANNED_IPS;
	}
	/**
	 * select a item in More Action menu for a forum
	 * By QuynhPT
	 * @param item
	 */
    public void selectItemMoreActionMenu(specifMoreActionMenu item){
    	info("Wait More link is shown");
		waitForAndGetElement(ELEMENT_MORE_ACTION);
		info("Click on More link");
		click(ELEMENT_MORE_ACTION);
    	info("Select a link on More menu");
    	switch(item) {
		case START_TOPIC:
			info("wait Start Topic button is shown");
			waitForAndGetElement(ELEMENT_START_TOPIC_BUTTON,2000,0);
			info("click on Start Topic button");
			click(ELEMENT_START_TOPIC_BUTTON);
			info("Verify that the popup is shown");
			waitForAndGetElement(ELEMENT_START_TOPIC_POPUP_TITLE);
			info("The popup is shown successfully");
			break;
		case EDIT:
			info("click on Edit link");
			waitForAndGetElement(ELEMENT_EDIT_FORUM,2000,0);
			click(ELEMENT_EDIT_FORUM);
			info("Verify that Edit popup is shown");
			waitForAndGetElement(ELEMENT_EDITFORUM_POPUP_TITLE);
			info("The popup is shown successfully");
			break;
		case DELETE:
			info("click on Delete link");
			waitForAndGetElement(ELEMENT_DELETE_FORUM,2000,0);
			click(ELEMENT_DELETE_FORUM);
			Utils.pause(1000);
			info("Verify that Confirm popup is shown");
			alert.waitForMessage("Are you sure you want to delete this forum ?");
			info("Click on OK button of Confirm popup");
			click(ELEMENT_OK_DELETE);
			info("Finish deleting the forum");
			break;
		case WATCHES:
			break;
		case LOCK:
			waitForAndGetElement(ELEMENT_LOCK_FORUM,2000,0);
			click(ELEMENT_LOCK_FORUM);
			break;
		case UNLOCK:
			waitForAndGetElement(ELEMENT_UNLOCK_FORUM,2000,0);
			click(ELEMENT_UNLOCK_FORUM);
			break;
		case CLOSE:
			waitForAndGetElement(ELEMENT_CLOSE_FORUM,2000,0);
			click(ELEMENT_CLOSE_FORUM);
			break;
		case OPEN:
			waitForAndGetElement(ELEMENT_OPEN_FORUM,2000,0);
			click(ELEMENT_OPEN_FORUM);
			break;
		case EXPORT_FORUM:
			break;
		case MOVE:
			info("Wait Move link is shown");
			waitForAndGetElement(ELEMENT_MOVE_FORUM,2000,0);
			info("Click on Move link");
			click(ELEMENT_MOVE_FORUM);
			info("Verify that Move popup is shown");
			waitForAndGetElement(ELEMENT_POPUP_MOVE_FORUM);
			info("The popup is shown successfully");
			break;
		case BANNED_IPS:
			break;
		default:
			break;
		}
	}
	
	/**
	 *  Edit a forum
	 * @param newName
	 * @param order
	 * @param newDescription
	 */
	public void editForum(String newName, String order,String newDescription) {
		// TODO Auto-generated method stub
		selectItemMoreActionMenu(specifMoreActionMenu.EDIT);
        info("Input a new title");
		type(ELEMENT_ADDFORUM_POPUP_TITLE,newName,true);
		info("check and input Oder field");
		if (!order.isEmpty())
			type(ELEMENT_ADDFORUM_POPUP_ORDER,order,true);
		info("check and input description");
		if (!newDescription.isEmpty())
			type(ELEMENT_ADDFORUM_POPUP_DESCRIPTION,newDescription,true);
		info("Click on Save button");
		click(ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON);
		Utils.pause(2000);
		info("Finish edting new forum");

	}
	
	/**
	 * Open or Close a forum
	 * By QuynhPT
	 * @param isClose =true if a forum is closed
	 *               =false if a forum is opened
	 */
	public void closeAndOpen(boolean isClose){
		if (isClose) {
			selectItemMoreActionMenu(specifMoreActionMenu.CLOSE);
			waitForAndGetElement(ELEMENT_FORUM_START_TOPIC_DISABLE);
		}else {
			selectItemMoreActionMenu(specifMoreActionMenu.OPEN);
			waitForAndGetElement(ELEMENT_FORUM_START_TOPIC_BUTTON);
		}
	}
	
	/**
	 * Delete a forum in the list
	 * By QuynhPT
	 * @param name
	 */
	public void deleteForum(String name){
		selectItemMoreActionMenu(specifMoreActionMenu.DELETE);
		info("Verify that the forum is deleted");
		waitForElementNotPresent(ELEMENT_FORUM_FORUM_NAME_LINK.replace("${name}",name));
	}
	
	/**
	 * function: move a forum from a category to another category
	 * @param forum
	 * @param destination
	 */
	public void moveForum(String forum, String destination){
		info("move forum to category " + destination);
		selectItemMoreActionMenu(specifMoreActionMenu.MOVE);
		click(By.linkText(destination));
		waitForElementNotPresent(ELEMENT_POPUP_MOVE_FORUM);
		forumHP.goToCategory(destination);
		waitForAndGetElement(ELEMENT_FORUM_FORUM_NAME_LINK.replace("${name}", forum));
		info("Move forum successfully");
	}
	/**
	 * Open Start Topic popup
	 * By QuynhPT
	 */
	public void goToStartTopic(){
		info("Go to start topic from more action");
		selectItemMoreActionMenu(specifMoreActionMenu.START_TOPIC);
		Utils.pause(2000);
	}
	
	/**
	 * Lock or Unlock a forum
	 * By QuynhPT
	 * @param islock =true if a forum is locked
	 *               =false if a forum is unlocked
	 */
	public void lockAndUnlock(boolean islock){
		if (islock) {
			selectItemMoreActionMenu(specifMoreActionMenu.LOCK);
			waitForAndGetElement(ELEMENT_FORUM_START_TOPIC_DISABLE);
		}else {
			selectItemMoreActionMenu(specifMoreActionMenu.UNLOCK);
			waitForAndGetElement(ELEMENT_FORUM_START_TOPIC_BUTTON);
		}
	}
	/**
	 * Check display of manage forum
	 * @param forum
	 * @param isDisplay
	 */
	public void checkDisplayOfForumManage(String forum,boolean isDisplay){
		info("check display of manage forum");
		click(ELEMENT_FORUM_FORUM_NAME_LINK.replace("${name}",forum));
		if(isDisplay){
			waitForAndGetElement(ELEMENT_MORE_ACTION);
			waitForAndGetElement(ELEMENT_MODERATOR);
		}else{
			waitForElementNotPresent(ELEMENT_MORE_ACTION);
			waitForElementNotPresent(ELEMENT_MODERATOR);
		}
	}
	/**
	 * Edit permission of forum
	 * @param cat
	 * @param groupPath
	 * @param member
	 * @param isMod
	 * @param isStartTop
	 * @param isPostReply
	 * @param isViewPost
	 */
	public void editPermOfForum(String forum,String groupPath,String member,boolean isMod,boolean isStartTop,boolean isPostReply,boolean isViewPost){
		info("edit permission of forum:"+forum);
		click(ELEMENT_FORUM_FORUM_NAME_LINK.replace("${name}",forum),0,true);
		selectItemMoreActionMenu(specifMoreActionMenu.EDIT);
		forumPerm.selectPermGroupMember(groupPath, member, isMod, isStartTop, isPostReply, isViewPost);
		click(ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON);
	}
	/**
	 * Check enable of start topic
	 * @param forum
	 * @param isEnable
	 */
	public void checkEnableOfStartTopic(String forum,boolean isEnable){
		info("check enable of start topic");
		click(ELEMENT_FORUM_FORUM_NAME_LINK.replace("${name}",forum),0,true);
		if(isEnable){
			waitForAndGetElement(ELEMENT_START_TOPIC_BTN);
		}else{
			waitForElementNotPresent(ELEMENT_START_TOPIC_BTN);
		}
	}	
	/**
	 * Open permissions tab in Add/Edit Forum form
	 */
	public void goToPermissions(){
		info("Permissions page");
		   click(ELEMENT_FORUM_PERMISSION_TAB);
	}
	
	/**
	 * Select User in Permission tab
	 * 
	 */
	public void gotoUserSelectorInPermissionTab(){
		info("-- Go to wiki home page --");
		click(ELEMENT_FORUM_PERMISSION_TAB_USER_SELECTOR);
	}
	
	/**
	 * Cancel Add/Edit Forum form
	 */
	public void cancelAddEditForum(){
		info("Cancel Add or Edit Forum");
		click(ELEMENT_FORUM_CANCEL);
	}
	
	/**
	 * Close User Selector page
	 */
	public void closeUserSelector(){
		info("-- Go to User Selector page --");
		click(ELEMENT_FORUM_PERMISSION_CLOSE_USER_SELETOR);
		Utils.pause(2000);
	}
	
	/**
	 * function: Search user in User Selection Form in Forum Permission
	 * @param user
	 * @param searchOption
	 */
	
	public void searchUser(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_FORUM_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_FORUM_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_FORUM_PERMISSION_SEARCH_ICON);
		waitForTextPresent(user);
	}
	/**
	 * Search user not found
	 * @param user
	 * @param searchOption
	 */
	public void searchUserNotFound(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_FORUM_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_FORUM_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_FORUM_PERMISSION_SEARCH_ICON);
		waitForTextNotPresent(user);
	}
}
