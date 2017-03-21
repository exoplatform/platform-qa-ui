package org.exoplatform.platform.qa.ui.selenium.platform.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformPermission;

public class ForumTopicManagement extends ForumLocator {
	
	PlatformPermission per;
	ManageAlert alert;
	Button button;
	ForumPermission forumPerm;
	/**
	 * constructor
	 * @param dr
	 */
	public ForumTopicManagement(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(driver);
		button = new Button(driver);
		forumPerm = new ForumPermission(driver);
	}
	/**
	 * Move a topic to a forum
	 * @param category
	 * @param forum
	 */
	public void moveTopicToForum(String category,String forum){
		info("if not found forum");
		if(waitForAndGetElement(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum), 3000,0)==null){
			info("Click on Category to expand tree");
			click(ELEMENT_MOVE_POPUP_COLLASPE_NODE.replace("${category}",category));
			info("Select the forum");
		    click(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum));
		}else{
			info("Select the forum");
			click(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum));
		}
		info("Finish moving");
	}
	
	/**
	 * Open More Action menu of topic
	 * By QuynhPT
	 */
	public void openMoreActionMenu(){
		info("Wait More link is shown");
		waitForAndGetElement(ELEMENT_MORE_ACTION);
		info("Click on More link");
		click(ELEMENT_MORE_ACTION);
	}
	/**
	 * list sublinks in More Action menu of Topic
	 * @author quynhpt
	 *
	 */
	public enum specifMoreActionMenuTopic{
		EDIT,ADD_POLL,LOCK,UNLOCK,CLOSE,OPEN,STICK,SPLIT,MOVE,DELETE,WATCHES;
	}
	/**
	 * select a item in More Action menu
	 * By QuynhPT
	 * @param item
	 */
    public void selectItemMoreActionMenuTopic(specifMoreActionMenuTopic item){
    	openMoreActionMenu();
    	info("Select a link in More menu of the topic");
    	switch(item) {
    	case ADD_POLL:
    		info("Click on Add poll button");
    		click(ELEMENT_ADD_POLL);
    		break;
		case EDIT:
			info("Click on Edit topic button");
			click(ELEMENT_EDIT_TOPIC);
			break;
		case DELETE:
			info("Click on Delete topic button");
			click(ELEMENT_DELETE_TOPIC);
			Utils.pause(1000);
			info("Verify that confirm popup is shown");
			alert.waitForMessage("Are you sure you want to delete this topic ?");
			info("Click on OK on Confirm popup");
			click(ELEMENT_OK_DELETE);
			break;
		case WATCHES:
			break;
		case LOCK:
			info("Click on lock topic button");
			click(ELEMENT_LOCK_TOPIC);
			break;
		case UNLOCK:
			info("Click on unlock topic button");
			click(ELEMENT_UNLOCK_TOPIC);
			break;
		case MOVE:
			info("Wait Move topic link is shown");
			waitForAndGetElement(ELEMENT_MOVE_TOPIC);
			info("Click on move topic link");
			click(ELEMENT_MOVE_TOPIC);
			break;
		case CLOSE:
			info("Click on Close");
			click(ELEMENT_CLOSE_TOPIC);
			break;
		case OPEN:
			info("Click on Close");
			click(ELEMENT_OPEN_TOPIC);
			break;
		case STICK:
			break;
		case SPLIT:
			break;
		default:
			break;

		}
	}
    /**
     * Open More Action menu of poll portlet
     */
    public void openMoreActionMenuPoll(){
    	info("Wait More link is shown");
		waitForAndGetElement(ELEMENT_MORE_ACTIONS_POLL);
		info("Click on More link");
		click(ELEMENT_MORE_ACTIONS_POLL);
    }
	/**
	 * list sublinks of MoreAction of Poll portlet
	 * @author quynhpt
	 *
	 */
    public enum specifMoreActionMenuPoll{
		EDIT,CLOSE,OPEN,REMOVE;
    }
    /**
     * Select a action in More Action menu of Poll portlet
     * @param item
     */
    public void selectItemMoreActionMenuPoll(specifMoreActionMenuPoll item){
    	openMoreActionMenuPoll();
    	info("Select a link in More menu of the poll");
    	switch(item) {
		case EDIT:
			info("Click on Edit link");
			click(ELEMENT_EDIT_POLL);
			break;
		case CLOSE:
			info("Click on Close link");
			click(ELEMENT_CLOSE_POLL);
			break;
		case OPEN:
			info("Click on Open link");
			click(ELEMENT_OPEN_POLL);
			break;
		case REMOVE:
			info("Click on Remove link");
			click(ELEMENT_REMOVE_POLL);
			break;
		default:
			break;

		}
	}
    /**
     * Close or open a poll 
     * @param isClose = true if the poll is closed
     *                = false if the poll is opened
     */
    public void closeOpenPoll(boolean isClose){
    	if(isClose){
    		info("Close the poll");
    		selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.CLOSE);
    		waitForAndGetElement(ELEMENT_FORUM_POLL_GRIDCLOSE);
    	}else {
    		info("Open the poll");
    		selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.OPEN);
    		waitForAndGetElement(ELEMENT_FORUM_POLL_GRID);
    	}
    	
    }
    
    /**
     * Post a reply
     * @param title
     * @param content
     */
    public void postReply(String title, String content){
    	click(ELEMENT_POST_REPLY);
    	replyTopic(title,content);
    }
    /**
     * Input information for Reply form
     * @param title
     * @param content
     */
    public void replyTopic(String title, String content){
    	if (!title.isEmpty())
			type(ELEMENT_TITLE_POST,title,true);
		inputFrame(ELEMENT_POST_CONTENT, content);
		click(ELEMENT_POST_FORM_SUBMIT);
		info("Verify that the post is created");
		waitForAndGetElement(ELEMENT_POST_IN_TOPIC.replace("{$title}",title).replace("{$content}",content));
    }
    
    /**
     * Edit a post
     * @param title
     * @param newTitle
     * @param newContent
     */
    public void editPost(String title, String newTitle,String newContent){
    	click(ELEMENT_EDIT_POST.replace("{$title}", title));
    	if (!newTitle.isEmpty())
			type(ELEMENT_TITLE_POST,newTitle,true);
    	if(!newContent.isEmpty())
    		inputFrame(ELEMENT_POST_CONTENT, newContent);
    	click(ELEMENT_POST_FORM_SUBMIT);
    }
    
    /**
     * Quote a post 
     * @param title
     * @param newContent
     */
    public void quotePost(String title,String newContent){
    	click(By.xpath(ELEMENT_QUOTE_POST.replace("{$title}",title)));
    	
    	if(newContent !="")
    		inputFrame(ELEMENT_POST_CONTENT, newContent);
    	
    	click(ELEMENT_POST_FORM_SUBMIT);
    }
    
    /**
     * Create a private post
     * @param titlePost
     * @param newTitle
     * @param content
     */
    public void privatePost(String titlePost, String newTitle, String content){
    	click(By.xpath(ELEMENT_PRIVATE_POST.replace("{$title}",titlePost)));
    	
    	if(newTitle !="")
			type(ELEMENT_TITLE_POST,newTitle,true);
    	
    	if(content !="")
    		inputFrame(ELEMENT_POST_CONTENT, content);
    	
    	click(ELEMENT_POST_FORM_SUBMIT);
    }
    
	/**
	 * Add poll
	 * @param question
	 * @param option1
	 * @param option2
	 */
    public void addPoll(String question, String option1, String option2){
    	selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.ADD_POLL);
    	info("Input a question to poll");
    	type(ELEMENT_POLL_QUESTION,question,true);
    	info("Input an option 1 to poll");
    	type(ELEMENT_POLL_OPTIONS0,option1,true);
    	info("Input an option 2 to poll");
    	type(ELEMENT_POLL_OPTIONS1,option2,true);
    	info("Click on Submit button");
    	click(ELEMENT_POLL_SUBMIT);
    	waitForElementNotPresent(ELEMENT_POLL_SUBMIT);
    	info("Finished adding poll");
    	Utils.pause(2000);
    }
    /**
	 * Lock or Unlock a topic
	 * By QuynhPT
	 * @param islock =true if a topic is locked
	 *               =false if a topic is unlocked
	 */
	public void lockUnlockTopic(boolean islock){
		if (islock) {
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.LOCK);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
		}else {
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.UNLOCK);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		}
	}
	/**
	 * Close or Open a topic
	 * @param isClose  =true if a topic is closed
	 *                 = false if a topic is opened
	 */
	public void closeOpenTopic(boolean isClose){
		if(isClose){
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.CLOSE);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
		}else{
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.OPEN);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		}
		
	}
    /**
     * Edit Poll
     * @param question
     * @param option1
     * @param option2
     */
    public void editPoll(String question,String option1, String option2){
    	selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.EDIT);
    	info("Refresh the page");
    	driver.navigate().refresh();
    	waitForAndGetElement(ELEMENT_POLL_POPUP_TITLE);
    	info("Input a question to poll");
    	waitForAndGetElement(ELEMENT_POLL_QUESTION).clear();
    	type(ELEMENT_POLL_QUESTION,question,true);
    	info("Input an option 1 to poll");
    	waitForAndGetElement(ELEMENT_POLL_OPTIONS0).clear();
    	type(ELEMENT_POLL_OPTIONS0,option1,true);
    	info("Input an option 2 to poll");
    	waitForAndGetElement(ELEMENT_POLL_OPTIONS1).clear();
    	type(ELEMENT_POLL_OPTIONS1, option2,true);
    	info("Click on Submit button");
    	click(ELEMENT_POLL_SUBMIT);
    	info("Finished adding poll");
    }
	
    /**
     * Delete the poll of the topic
     */
    public void deletePoll(){
    	selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.REMOVE);
    	info("Click on Ok button");
    	click(ELEMENT_OK_DELETE);
    	info("Finish deleting poll");
    }
    /**
     * Delete a topic
     */
    public void deleteTopic(){
    	info("Delete the topic");
    	selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.DELETE);
    }
    
    /**
     * Add a tag gor topic
     * @param name
     */
	public void addATag(String name) {
		click(ELEMENT_ACTIONBAR_TOPIC_TAG);
		type(ELEMENT_ACTIONBAR_TOPIC_TAGNAME, name, true);
		click(ELEMENT_FORUM_TOPIC_ADD_TAG);
		waitForAndGetElement(ELEMENT_ACTIONBAR_TOPIC_TAGPRESENT.replace("${tag}", name));
	}

	/**
	 * addPostSimple
	 * @param name
	 * @param message
	 * 
	 */
	public void addPostSimple(String name, String message) {
		info("Add post simple");
		Utils.pause(2000);
		click(ELEMENT_FORUM_ADDPOST);
		waitForAndGetElement(ELEMENT_FORUM_POST_TITLE, DEFAULT_TIMEOUT, 1);
		type(ELEMENT_FORUM_POST_TITLE, name, true);
		inputFrame(ELEMENT_FORUM_MESSAGE , message);
		//switchToParentWindow();
		waitForAndGetElement(ELEMENT_FORUM_SETTINGS_SUBMIT, DEFAULT_TIMEOUT, 1);
		clickByJavascript(ELEMENT_FORUM_SETTINGS_SUBMIT, 2);
		//click(ELEMENT_FORUM_SETTINGS_SUBMIT);
	}
	
	/**
	 * addTopicSimple
	 * @param name
	 * @param message
	 * 
	 */
	public void addTopicSimple(String name, String message) {
		click(ELEMENT_FORUM_ADDTOPIC);
		type(ELEMENT_FORUM_TOPIC_TITLE, name, true);
		inputFrame(ELEMENT_FORUM_MESSAGE , message);
		//switchToParentWindow();
		//click(ELEMENT_FORUM_SETTINGS_SUBMIT);
		do {
			waitForAndGetElement(ELEMENT_FORUM_SETTINGS_SUBMIT, DEFAULT_TIMEOUT, 1);
			clickByJavascript(ELEMENT_FORUM_SETTINGS_SUBMIT, 2);
		}
		while (waitForAndGetElement(ELEMENT_FORUM_TOPIC_LINK.replace("${name}",name),DEFAULT_TIMEOUT,1) == null);
		info("The topic is created successfully");
	}
	
	/**
	 * Reply the topic
	 * @param newTitle
	 * @param newMessg
	 * @param pathFile
	 * @param fileName
	 */
	public void replyTopic(String newTitle,String newMessg,String pathFile,String fileName){
		info("Click on Post Reply button"); 
		//click(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		clickByJavascript(ELEMENT_TOPIC_POST_REPLY_BOTTOM, 2);
		info("Verify that the pop up is shown");
		waitForAndGetElement(ELEMENT_TOPIC_NEW_POST_TITLE);
		info("Refresh the page");
		this.driver.navigate().refresh();
		if(!newTitle.isEmpty()){
			info("Input the title:"+newTitle);
			waitForAndGetElement(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD).clear();
			type(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD,newTitle, true);
		}
		
		if (!newMessg.isEmpty()){
			info("Input the message:"+newMessg);
			inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR,newMessg);
		}
		
		if (!pathFile.isEmpty()|| !fileName.isEmpty()) {
			info("click on Attached file button");
			click(ELEMENT_START_TOPIC_ATTACH_FILE);
			info("Verify that upload button is shown");
			waitForAndGetElement(ELEMENT_UPLOAD_POPUP_FILE);
			info("Attached file");
			attachFile(pathFile,fileName);
			info("Verify that upload popup is closed");
			waitForElementNotPresent(ELEMENT_UPLOAD_POPUP_FILE);
		}
		info("click on Submit button");
		//click(ELEMENT_SUBMIT_BUTTON);
		clickByJavascript(ELEMENT_SUBMIT_BUTTON, 2);
		info("Verify that the replying is created");
		waitForAndGetElement(ELEMENT_TOPIC_REPPLY_CONTENT.replace("${content}", newMessg));
		info("Reply topic successfully");
	}
	
	/**
	 * Start a Topic
	 * By QuynhPT
	 * @param title
	 * @param message
	 */
	public void startTopic(String title, String message,String pathFile,String fileName) {
		info("Verify that the pop up is shown");
		waitForAndGetElement(ELEMENT_START_TOPIC_POPUP_TITLE_FILED);
		//info("Refresh the page");
		//this.driver.navigate().refresh();
		if(!title.isEmpty()){
			info("Input the title:"+title);
			waitForAndGetElement(ELEMENT_START_TOPIC_POPUP_TITLE_FILED, DEFAULT_TIMEOUT, 1);
			type(ELEMENT_START_TOPIC_POPUP_TITLE_FILED, title, true);
		}
		
		if (!message.isEmpty()){
			info("Input the message:"+message);
			inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR,message);
		}
		
		if (!pathFile.isEmpty()|| !fileName.isEmpty()) {
			info("click on Attached file button");
			click(ELEMENT_START_TOPIC_ATTACH_FILE);
			info("Verify that upload button is shown");
			waitForAndGetElement(ELEMENT_UPLOAD_POPUP_FILE);
			info("Attached file");
			attachFile(pathFile,fileName);
			info("Verify that upload popup is closed");
			waitForElementNotPresent(ELEMENT_UPLOAD_POPUP_FILE);
		}
		info("click on Submit button");
		waitForAndGetElement(ELEMENT_SUBMIT_BUTTON, DEFAULT_TIMEOUT, 1);
		clickByJavascript(ELEMENT_SUBMIT_BUTTON,2);
		//click(ELEMENT_SUBMIT_BUTTON);
		info("Verify that the topic is created");
		waitForAndGetElement(By.linkText(title));
		info("Start topic successfully");
	}
	/**
	 * Rate a topic
	 * @param name
	 */
	public void rateTopic(String name,String starType){
		click(ELEMENT_ACTIONBAR_TOPIC_RATE);
		click(ELEMENT_FORUM_VOTE_MARK.replace("${star}",starType));
		Utils.pause(1000);
	}
	/**
	 * Edit a topic 
	 * @param newTitle
	 * @param newContent
	 */
	public void editTopic(String newTitle,String newContent){
		selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.EDIT);
		if(!newTitle.isEmpty())
		  type(ELEMENT_START_TOPIC_POPUP_TITLE_FILED,newTitle, true);
		if(!newContent.isEmpty())
		  inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR,newContent);
		info("Click on Submit button");
		waitForAndGetElement(ELEMENT_SUBMIT_BUTTON, DEFAULT_TIMEOUT, 1);
		clickByJavascript(ELEMENT_SUBMIT_BUTTON, 2);
		//click(ELEMENT_SUBMIT_BUTTON);
		info("All changes are saved");
	}
	/**
	 * Check display of manage topic
	 * @param forum
	 * @param topic
	 * @param isDisplay
	 */
	public void checkDisplayOfTopicManage(String forum,String topic,boolean isDisplay){
		info("check display of manage topic");
		click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}",topic),0,true);
		if(isDisplay){
			waitForAndGetElement(ELEMENT_MORE_ACTION);
			waitForAndGetElement(ELEMENT_MODERATOR);
		}else{
			waitForElementNotPresent(ELEMENT_MORE_ACTION);
			waitForElementNotPresent(ELEMENT_MODERATOR);
		}
	}
	/**
	 * Check enable of post reply 
	 * @param topic
	 * @param isEnable
	 */
	public void checkEnableOfPostReply(String topic,boolean isEnable){
		info("check enable of post reply");
		click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}",topic),0,true);
		if(isEnable){
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		}else{
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
		}
	}
	/**
	 * Check enable of view post
	 * @param forum
	 * @param topic
	 * @param isEnable
	 */
	public void checkEnableOfViewPost(String forum,String topic,boolean isEnable){
		info("check enable of view post");
		click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}",forum),0,true);
		if(isEnable){
			waitForAndGetElement(ELEMENT_START_TOPIC_BTN);
			click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}",topic),0,true);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		}else{
			waitForElementNotPresent(ELEMENT_FORUM_TOPIC_LINK.replace("${name}",topic));
		}
	}
	/**
	 * Edit permission of topic
	 * @param topic
	 * @param groupPath
	 * @param member
	 * @param isView
	 * @param isPost
	 */
	public void editPermOfTopic(String topic,String groupPath,String member,boolean isView,boolean isPost){
		info("edit permission of topic:"+topic);
		click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}",topic),0,true);
		selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.EDIT);
		forumPerm.selectPermGroupMemberInTopic(groupPath, member, isView,isPost);
		click(ELEMENT_SUBMIT_BUTTON);
	}	
	/**
	 * Open permissions tab in Add/Edit Topic
	 */
	public void goToPermissions(){
		info("Permissions page");
		   click(ELEMENT_TOPIC_PERMISSION_TAB);
	}
	
	/**
	 * Select User in Permission tab
	 * 
	 */
	public void gotoUserSelectorInPermissionTab(){
		info("-- Go to wiki home page --");
		click(ELEMENT_TOPIC_PERMISSION_TAB_USER_SELECTOR);
	}
	
	/**
	 * function: Search user in User Selection Form in Topic Permission
	 * 
	 */
	
	public void searchUser(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_TOPIC_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_TOPIC_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_TOPIC_PERMISSION_SEARCH_ICON);
		waitForTextPresent(user);
	}
	
	public void searchUserNotFound(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_TOPIC_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_TOPIC_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_TOPIC_PERMISSION_SEARCH_ICON);
		waitForTextNotPresent(user);
	}
	
	/**
	 * Close User Selector page
	 */
	public void closeUserSelector(){
		info("-- Go to User Selector page --");
		click(ELEMENT_TOPIC_PERMISSION_CLOSE_USER_SELETOR);
		Utils.pause(2000);
	}
	
	/**
	 * Cancel Add/Edit Forum form
	 */
	public void cancelAddEditTopic(){
		info("Cancel Add or Edit Forum");
		click(ELEMENT_TOPIC_CANCEL);
	}
}
