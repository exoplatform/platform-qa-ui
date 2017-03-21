package org.exoplatform.platform.qa.ui.selenium.platform.administration;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ContentAdministration {

	ECMS_Permission ecmsPerm;
	PlatformPermission plfPerm;
	
	//Permission
	public By ELEMENT_ECM_WEB_VIEW_PERMISSION = By.xpath("//a[@href='#tab-UIViewPermissionContainer']");
	public By ELEMENT_ECM_DRIVE_PERMISSION = By.xpath("//*[@id='permissions']");
		
	ManageAlert alert ;
	public ContentAdministration(WebDriver dr){
		driver = dr;
		alert= new ManageAlert(dr);
		ecmsPerm = new ECMS_Permission(driver);
		plfPerm = new PlatformPermission(driver);
	}

	/**
	 * Select a function as: Explorer, Advanced, Template and Repositoty
	 */
	public enum mainEcmFunctions{
		EXPLORER,ADVANCED,TEMPLATES,REPOSITORY;
	}

	/**
	 * Select a subfunction as Documents, List, Metadata, Drives,
	 * Tags, NameSpaces,Nodestypes,Locks, Categories, Queries, Scripts
	 * Actions
	 *
	 */
	public enum specificEcmFunctions{
		DOCUMENTS,LIST,METADATA,VIEW,DRIVES,TAGS,NAMESPACES,NODESTYPES,LOCKS,CATEGORIES,QUERIES,SCRIPTS,ACTIONS;
	}

	/**
	 * 
	 * view option
	 *
	 */
	public enum specificView{
		ADMIN, CATEGORY, LIST, ICON, WEB,ITEM
	}

	/**
	 * select action type for a web view
	 * By quynhpt
	 */
	public enum specificEcmActionstypes {
		ADD_CATEGORY, ADD_TRANSLATION, CONTENT_NAVIGATION, IMPORT_NOTE, MANAGE_CATEGORIES, MANAGE_RELATION, APPROVE_CONTENT, SHOW_JCR_STRUCTURE, VIEW_METADATA, 
		VIEW_PROPERTIES, ADD_DOCUMENT, ADD_SYMLINK, EDIT_DOCUMENT, MANAGE_ACTIONS, HIDE_SHOW_CONTENT, MANAGE_VERSION, PUBLISH, TAG_DOCUMENT, VIEW_NODE_TYPE, VOTE, 
		ADD_FOLDER, COMMENT, EXPORT_NODE, MANAGE_AUDITING, MANAGE_PUBLISHTATION, OVERLOAD_THUMBNAILS, REQUEST_APPROVAL, UPLOAD, VIEW_PERMISSIONS, WATCH_DOCUMENTS;
	}

	/**
	 * Go to Explorer function
	 * @param main
	 */
	public void goToSpecificMainFunctions(mainEcmFunctions main){
		info("Go to select a main function");
		switch(main){
		case EXPLORER :
			info("Select Explorer tab");
			if(waitForAndGetElement(ELEMENT_EXPLORER_CATEGORIES_ECM_FUNCTIONS, 5000,0)!=null)
				click(ELEMENT_EXPLORER_CATEGORIES_ECM_FUNCTIONS);
			break;
		case ADVANCED:
			info("Select Advanced tab");
			if(waitForAndGetElement(ELEMENT_ADVANCED_CATEGORIES_ECM_FUNCTIONS, 5000,0)!=null)
				click (ELEMENT_ADVANCED_CATEGORIES_ECM_FUNCTIONS);
			break;
		case TEMPLATES:
			info("Select Templates tab");
			if(waitForAndGetElement(ELEMENT_TEMPLATE_CATEGORIES_ECM_FUNCTIONS, 5000,0)!=null)
				click (ELEMENT_TEMPLATE_CATEGORIES_ECM_FUNCTIONS);
			break;
		case REPOSITORY:
			info("Select Repository tab");
			if(waitForAndGetElement(ELEMENT_RESPONSITORY_CATEGORIES_ECM_FUNCTIONS, 5000,0)!=null)
				click (ELEMENT_RESPONSITORY_CATEGORIES_ECM_FUNCTIONS);
			break;
		}
		info("The main function is selected successfully");
	}

	/**
	 * Select a sub function in Advance function
	 * @param spec
	 */
	public void goToSpecificFunctions(specificEcmFunctions spec){
		info("Go to select a function in Advance tab");
		switch(spec){
		case ACTIONS :
			info("Select Actions function");
			click(ELEMENT_ECMS_FUNCTIONS_ACTIONS);
			break;
		case SCRIPTS :
			info("Select Scripts function");
			click(ELEMENT_ECMS_FUNCTIONS_SCRIPTS);
			break;
		case QUERIES :
			info("Select Queries function");
			click(ELEMENT_ECMS_FUNCTIONS_QUERIES);
			break;
		case CATEGORIES :
			info("Select Categories function");
			click(ELEMENT_ECMS_FUNCTIONS_CATEGORIES);
			break;
		case DRIVES :
			info("Select Drives function");
			click(ELEMENT_ECMS_FUNCTIONS_DRIVES);
			break;
		case VIEW :
			info("Select Views function");
			click(ELEMENT_ECMS_FUNCTIONS_VIEWS);
			break;
		case TAGS :
			info("Select Tags function");
			click(ELEMENT_ECMS_FUNCTIONS_TAGS);
			break;
		case NODESTYPES :
			info("Select NodeStypes function");
			click(ELEMENT_ECMS_FUNCTIONS_NODES);
			break;
		case NAMESPACES :
			info("Select NameSpace function");
			click(ELEMENT_ECMS_FUNCTIONS_NAMESPACES);
			break;
		case LOCKS :
			info("Select Locks function");
			click(ELEMENT_ECMS_FUNCTIONS_LOCKS);
			break;	
		case DOCUMENTS :
			info("Select Documents function");
			click(ELEMENT_ECMS_FUNCTIONS_DOCUMENTS);
			break;
		case LIST :
			info("Select List function");
			click(ELEMENT_ECMS_FUNCTIONS_LIST);
			break;
		case METADATA :
			info("Select Metadata function");
			click(ELEMENT_ECMS_FUNCTIONS_METADATA);
			break;
		}
		info("End of selecting a function in Advance tab");
	}

	/**
	 * Add a new View
	 * @param name
	 * @param tabName
	 * @param tab
	 * @param userNamePermission
	 */
	public void addView(String name,String tabName,String[] tab, String... perm){
		click(ELEMENT_ECM_EXPLORER_VIEWS_ADD_VIEWS);
		type(ELEMENT_ECM_EXPLORER_NAME_VIEW_FORM,name,true);
		click(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM);
		click(ELEMENT_ECM_EXPLORER_ADD_ACTION_VIEW_FORM);
		type(ELEMENT_ECM_EXPLORE_TAB_NAME_VIEW_FORM,tabName,true);
		for(String arrayElement : tab ){
			check(By.xpath(ELEMENT_ECM_EXPLORER_CHOOSE_TAB_CATEGORY_VIEW_FORM.replace("{$tab}",arrayElement)),2);
		}
		click(ELEMENT_ECM_EXPLORE_SAVE_TAB_VIEW_FORM);
		click(ELEMENT_ECM_EXPLORER_GO_TO_PERMISSION_FORM);
		if(perm.length<2){
			click(ELEMENT_ECM_EXPLORER_USER_PERMISSION_ADD);
			click(By.xpath(ELEMENT_ECM_EXPLORER_SELECT_USER_LIST_PERMISSION.replace("{$user}",perm[0])));
		}else{
			ecmsPerm.selectGroupMembershipOfTag(perm[0],perm[1]);
		}
		click(ELEMENT_ECM_EXPLORER_ADD_PERMISSION_FORM);
		click(ELEMENT_ECM_EXPLORER_SAVE_FORM_ADD_VIEW);
	}

	/**
	 * Edit a view with Permission User
	 * @param viewName
	 * @param oldName
	 * @param newName
	 */
	public void editViewPermissionUser(String viewName, String oldName, String newName){
		click(By.xpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}",viewName)));
		click(ELEMENT_ECM_EXPLORER_GO_TO_PERMISSION_FORM);
		click(ELEMENT_ECM_EXPLORER_USER_PERMISSION_ADD);
		click(By.xpath(ELEMENT_ECM_EXPLORER_SELECT_USER_LIST_PERMISSION.replace("{$user}",newName)));
		click(ELEMENT_ECM_EXPLORER_ADD_PERMISSION_FORM);
		click(By.xpath(ELEMENT_ECM_EXPLORER_DELETE_PERMISSION_USER.replace("{$name}",oldName)));
		alert.acceptAlert();
		click(ELEMENT_ECM_EXPLORER_SAVE_FORM_ADD_VIEW);
	}
	/**
	 * Delete a View
	 * @param viewName
	 */
	public void deleteView(String viewName){
		click(By.xpath(ELEMENT_ECM_EXPLORER_VIEW_DELETE_LIST.replace("{$name}",viewName)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}",viewName)));
	}
	/**
	 * Add a new drive
	 * @param name
	 * @param permission
	 * @param applyViews
	 */
	public void addDrives(String name, String permission,specificView[] applyViews){
		info("Click on Add button of the drive in the list");
		click(ELEMENT_ECM_EXPLORER_DRIVES_ADD_DRIVES);
		info("Type a name for the drive");
		type(ELEMENT_ECM_EXPLORER_NAME_DRIVES_FORM,name,true);
		info("Click on Add Permission button for the drive");
		click(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON);
		if(permission=="any"){
			info("Set 'any' permission for the drive");
			click(ELEMENT_ECM_COMMON_ANY_PERMISSION);
		}
		info("Click on Aplly Views tab");
		click(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_FORM);
		for(specificView arrayElement : applyViews ){
			info("Select a view type for the drive");
			switch(arrayElement){
			case ADMIN :
				info("Add Admin view type");
				//check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN,2);
				clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN, 2);
				break;
			case CATEGORY:
				info("Add Category view type");
				//check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES,2);
				clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES, 2);
				break;
			case ICON:
				info("Add Icon view type");
				//check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS,2);
				clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS, 2);
				break;
			case LIST :
				info("Add List view type");
				//check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST,2);
				clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST, 2);
				break;
			case WEB :
				info("Add WEB view type");
				//check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB,2);
				clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB, 2);
				break;
			default:
				info("Add WEB view type");
				//check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB,2);
				clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB, 2);
				break;
			}
		}
		info("Save all changes");
		click(ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM);
		info("Finished adding a drive");
	}
	/**
	 * 
	 * @param name
	 * @param applyViews (All the view want to be check have to be specified, the other will be uncheck) 
	 */
	public void editDrives(String name,specificView[] applyViews,String...views){
		click(By.xpath(ELEMENT_ECM_EXPLORER_DRIVES_EDIT_LIST.replace("{$name}", name)));
		click(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_FORM);
		uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN,2);
		uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES,2);
		uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS,2);
		uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST,2);
		uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB,2);
		for(specificView arrayElement : applyViews ){
			switch(arrayElement){
			case ADMIN :
				check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN,2);
				break;
			case CATEGORY:
				check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES,2);
				break;
			case ICON:
				check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS,2);
				break;
			case LIST :
				check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST,2);
				break;
			case WEB :
				check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB,2);
				break;
			case ITEM :
				check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ITEM.replace("$item", views[0]),2);
				break;
			default:
				check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB,2);
				break;
			}
		}
		click(ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM);
	}


	/**
	 * Delete a drive
	 * @param name
	 */
	public void deleteDrives(String name){
		click(By.xpath(ELEMENT_ECM_EXPLORER_DRIVES_DELETE_LIST.replace("{$name}",name)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_EXPLORER_DRIVES_DELETE_LIST.replace("{$name}",name)));
	}
	/**
	 * Add a Tags
	 * @param tagName
	 * @param occurenceNumber
	 * @param htmlStyle
	 */
	public void addTags(String tagName, String occurenceNumber,String htmlStyle){
		click(ELEMENT_ECM_EXPLORER_TAGS_ADD_STYLE_BUTTON);
		type(ELEMENT_ECM_EXPLORER_TAGS_ADD_NAME_FORM,tagName,true);
		type(ELEMENT_ECM_EXPLORER_TAGS_NUMBER_OCCURENCE_FORM, occurenceNumber, true);
		type(ELEMENT_ECM_EXPLORER_TAGS_HTML_STYLE_FORM, htmlStyle, true);
		click(ELEMENT_ECM_EXPLORER_TAGS_UPDATE_FORM);
	}
	/**
	 * Update a Tag
	 * @param tagName
	 * @param occurences
	 * @param html
	 */
	public void updateTags(String tagName, String occurences,String html){
		click(By.xpath(ELEMENT_ECM_EXPLORER_TAGS_EDIT_LIST.replace("{$name}",tagName)));
		if(occurences!=null)
			type(ELEMENT_ECM_EXPLORER_TAGS_NUMBER_OCCURENCE_FORM, occurences, true);
		if(html!=null)
			type(ELEMENT_ECM_EXPLORER_TAGS_HTML_STYLE_FORM, html, true);
		click(ELEMENT_ECM_EXPLORER_TAGS_UPDATE_FORM);
	}
	/**
	 * Delete a tag
	 * @param tagName
	 */
	public void deleteTags(String tagName){
		click(By.xpath(ELEMENT_ECM_EXPLORER_TAGS_DELETE_LIST.replace("{$name}",tagName)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_EXPLORER_TAGS_DELETE_LIST.replace("{$name}",tagName)));

	}
	/**
	 * Delete a category by name
	 * @param name
	 */
	public void deleteCategories(String name){
		info("Delete the category");
		info("Click on Delete button of the category");
		click(By.xpath(ELEMENT_ECM_ADVANCED_CATEGORIES_DELETE.replace("{$name}", name)));
		info("Click on Ok button of the alert popup");
		alert.acceptAlert();
		info("Verify that the category is deleted");
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_ADVANCED_CATEGORIES_DELETE.replace("{$name}", name)));
		info("The category is deleted succcessfully");
	}
	/**
	 * Add a new category
	 * @param name
	 * @param nameAction
	 * @param lifeCycle
	 * @param targetPath
	 */
	public void addCategories(String name,String nameAction,String lifeCycle, String targetPath,Object...params){
		info("Add a category");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_CATEGORIES);
		info("Type a name for the category");
		type(ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_FORM,name,true);
		if(params.length>0){
			String workspace = (String)params[0];
			String pathWorkspace = (String)params[1];
			info("Select the workspace:"+workspace+" for the category");
			select(ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_SELECT_FORM,workspace);
			info("Click on Add button of home path form");
			click(ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_HOME_PATH_FORM);
			if(pathWorkspace=="root"){
				info("if workspace is root, click on ROOT node");
				click(ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM);
			}
		}
		info("Click on Next button of the step 1");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_1STPAGE_FORM);
		Utils.pause(500);
		info("select permission");
		if(params.length>2){
			Boolean read = (Boolean)params[2];
			Boolean modify = (Boolean)params[3];
			Boolean remove = (Boolean)params[4];
			String group = (String)params[5];
			String mem = (String)params[6];
			click(ecmsPerm.ELEMENT_PERMISSION_SELECTMEMBERSHIP);
			ecmsPerm.selectGroupMembershipOfCat(group,mem);
			ecmsPerm.selectCheckBoxRight(read, modify, remove);
			info("Click on Save button");
			click(ecmsPerm.ELEMENT_PERMISSION_SAVE);
			ecmsPerm.deletePermissionNode("any");
			ecmsPerm.deletePermissionNode("*:/platform/users");
		}
		info("Click on Next button of the step 2");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_2NDPAGE_FORM);
		info("Type a name for the action of the category");
		type(ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_ACTION_FORM,nameAction,true);
		info("Select a lifeCycle");
		select(ELEMENT_ECM_ADVANCED_CATEGORIES_LIFECYCLE_FORM,lifeCycle);
		info("Click on target path on action form");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_OPEN_TARGETPATH_ACTION_FORM);
		if(targetPath=="root"){
			info("if targetPath is root, select ROOT node");
			click(ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM);
		}
		info("Save all changes");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_SAVE_FORM);
		info("Close the form");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_CLOSE_FORM);
		info("Finish adding the category");
	}
	
	/**
	 * Edit a category
	 * @param name
	 * @param nameAction
	 * @param lifeCycle
	 * @param targetPath
	 * @param workspace
	 * @param pathWorkspace
	 */
	public void editCategories(String name,String nameAction,String lifeCycle, String targetPath,String workspace,String pathWorkspace){
		info("Edit the Category");
		info("Click on Edit button of the category in the list");
		click(By.xpath(ELEMENT_ECM_ADVANCED_CATEGORIES_EDIT_FORM.replace("{$name}",name)));
		info("Click on Previous button of step4");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_4THPAGE);
		info("Click on Previous button of step3");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_3RDPAGE);
		info("Click on Previous button of step2");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE);
		if(!workspace.isEmpty()){
			info("Select the workspace:"+workspace+" for the category");
			select(ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_SELECT_FORM,workspace);
			info("Click on Add button of home path form");
			click(ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_HOME_PATH_FORM);
			if(pathWorkspace=="root"){
				info("if workspace is root, click on ROOT node");
				click(ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM);
			}
		}
		info("Click on Next butotn of step 1");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_1STPAGE_FORM);
		info("Click on Next button of step 2");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_2NDPAGE_FORM);
		info("Click on Save button");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_SAVE_FORM);
		info("Close the form");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_CLOSE_FORM);
		info("Finished editing the category");
	}
	/**
	 * Select a group in permission selector popup by string
	 * @param groupsPath is path of groups as:Platform/Content Manangement
	 */
	public void selectGroup(String groupsPath){
		info("Select a group with the path:"+groupsPath);
		String[] groups = groupsPath.split("/");
		for(int i =0; i<groups.length-1; i++){
			info("Select group:"+groups[i]);
			click(ELEMENT_SELECT_CATEGORY.replace("${name}", groups[i]));
		}
		Utils.pause(2000);
	}
	/**
	 * Add sub category
	 * @param name
	 */
	public void addSubCategory(String name){
		info("Add sub category "+name);
		click(ELEMENT_ADD_SUB_CAT_BUTTON);
		type(ELEMENT_NAME_CAT_TEXTBOX,name,true);
		click(ELEMENT_ADD_CAT_SAVE_BUTTON);
	}
	/**
	 * edit with sub category
	 * @param name
	 * 				name of category
	 * @param sub
	 * 				name of sub category
	 */
	public void addSubCat(String name,String sub){
		info("add sub category");
		click(By.xpath(ELEMENT_ECM_ADVANCED_CATEGORIES_EDIT_FORM.replace("{$name}",name)));
		click(ELEMENT_ADD_SUB_CAT_BUTTON);
		type(ELEMENT_NAME_CAT_TEXTBOX,sub,true);
		click(ELEMENT_ADD_CAT_SAVE_BUTTON);
		Utils.pause(1000);
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_CLOSE_FORM);
	}
	/**
	 * Add a new action type
	 * @param name
	 * @param script
	 * @param variables
	 */
	public void addActionType(String name, String script,String variables){
		info("Add a action type");
		click(ELEMENT_ADD_ACTION_TYPE);
		info("Type a name");
		type(ELEMENT_ECM_ACTION_NAME_FORM,name,true);
		if(!script.isEmpty()){
			info("Select a script in form");
			select(ELEMENT_ECM_ACTION_SCRIPT_FORM,script);
		}
		if(!variables.isEmpty()){
			info("Type a variable in form");
			type(ELEMENT_ECM_ACTION_VARIABLES_FORM,variables, true);
		}
		info("Save all changes");
		waitForAndGetElement(ELEMENT_ECM_ACTION_SAVE_FORM);
		click(ELEMENT_ECM_ACTION_SAVE_FORM);
	}
	/**
	 * Edit a action type
	 * @param name
	 * @param newName
	 * @param script
	 * @param variables
	 */
	public void editActionType(String name,String newName, String script,String variables){
		info("Edit a action type");
		info("Select a action in the list");
		click(By.xpath(ELEMENT_ECM_ACTION_EDIT_LIST.replace("{$name}", name)));
		info("Add a action type");
		addActionType( newName,  script,variables);
		info("Verify that the new action is added");
		waitForAndGetElement(ELEMENT_ECM_ACTION_LIST.replace("{$name}",newName));
		info("Verify that the old action is replaced");
		waitForElementNotPresent(ELEMENT_ECM_ACTION_LIST.replace("{$name}",name));
		info("End of Editing action type");
	}
	/**
	 * Delete a action type
	 * @param name
	 */
	public void deleteAction(String name){
		info("Delete a action");
		info("Click on Delete button of the action");
		click(By.xpath(ELEMENT_ECM_ACTION_DELETE_LIST.replace("{$name}", name)));
		info("Click on OK button on Alert popup");
		alert.acceptAlert();
		info("Verify that the action is deleted");
		waitForElementNotPresent(ELEMENT_ECM_ACTION_LIST.replace("{$name}",name));
		info("The action is deleted successfully");
	}
	/**
	 * Add a new script
	 * @param name
	 * @param content
	 * @param script
	 */
	public void addScripts(String name, String content,String script){
		info("Add a script");
		click(ELEMENT_ECM_ADVANCED_SCRIPT_ADD_SCRIPT);
		info("Type a name for the script");
		type(ELEMENT_ECM_ADVANCED_SCRIPT_NAME_FORM,name,true);
		if(!script.isEmpty()){
			info("Type a script on the form");
			type(ELEMENT_ECM_ADVANCED_SCRIPT_SCRIPT_FORM,script,true);
		}
		if(!content.isEmpty()){
			info("Type a content for the script");
			type(ELEMENT_ECM_ADVANCED_SCRIPT_CONTENT_FORM,content, true);
		}
		info("Save all changes");
		click(ELEMENT_ECM_ADVANCED_SCRIPT_SAVE_FORM);
		info("Finish adding the script");
		waitForAndGetElement(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}",name));
	}
	/**
	 * Edit a Script
	 * @param oldname
	 * @param name
	 * @param content
	 * @param script
	 */
	public void EditScripts(String oldname,String name, String content,String script){
		info("Edit the script");
		info("Click on Edit button of the script on the list");
		click(By.xpath(ELEMENT_ECM_ADVANCED_SCRIPT_EDIT_LIST.replace("{$name}", oldname)));
		info("Type the mame for the script");
		type(ELEMENT_ECM_ADVANCED_SCRIPT_NAME_FORM,name,true);
		if(!script.isEmpty()){
			info("Type a script: "+script+" on the script form");
			type(ELEMENT_ECM_ADVANCED_SCRIPT_SCRIPT_FORM,script,true);
		}
		if(!content.isEmpty()){
			info("Type a content: "+content+" for the script");
			type(ELEMENT_ECM_ADVANCED_SCRIPT_CONTENT_FORM,content, true);
		}
		info("Save all changes");
		click(ELEMENT_ECM_ADVANCED_SCRIPT_SAVE_FORM);
		info("Verify that the script is saved with new changes");
		waitForAndGetElement(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}",name));
		info("Verify that old script is changed");
		waitForElementNotPresent(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}",oldname));
		info("Editing the script is changed successfully");
	}
	/**
	 * Delete a script by name
	 * @param name
	 */
	public void deleteScripts(String name){
		info("Click on Delete button of the script");
		click(By.xpath(ELEMENT_ECM_ADVANCED_SCRIPT_DELETE_LIST.replace("{$name}", name)));
		info("Click on OK button of the alert popup");
		alert.acceptAlert();
		info("Verify that the script is deleted");
		waitForElementNotPresent(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}",name));
		info("The script is deleted successfullyy");
	}
	/**
	 * Add a new query
	 * @param name
	 * @param queryType
	 * @param statement
	 * @param permission
	 */
	public void addQueries(String name,String queryType, String statement,String...permissions){
		info("Add a query");
		info("Click on Add button");
		click(ELEMENT_ECM_ADVANCED_QUERIES_ADD_QUERIES);
		info("Type a name for the query");
		type(ELEMENT_ECM_ADVANCED_QUERIES_NAME_FORM,name,true);
		if(!queryType.isEmpty()){
			info("Select the type:"+queryType+"for the query");
			select(ELEMENT_ECM_ADVANCED_QUERIES_QUERY_TYPE_FORM,queryType);
		}
		if(!statement.isEmpty()){
			info("Type a text: "+statement+"for the statement of the query");
			type(ELEMENT_ECM_ADVANCED_QUERIES_STATEMENT_FORM,statement, true);
		}
		info("Select permission form of the query");
		click(ELEMENT_ECM_ADVANCED_QUERIES_PERMISSION_FORM);
		if(permissions.length<2){
			info("Set permission of the query is:"+permissions[0]);
			click(ELEMENT_PERMISSION_ANY);
		}else{
			info("select permission");
			ecmsPerm.selectGroupMembershipOfQuery(permissions[0],permissions[1]);
		}
		info("Save all changes");
		click(ELEMENT_ECM_ADVANCED_QUERIES_SAVE_FORM);
		info("Adding the query is success");
	}
	/**
	 * Edit a query
	 * @param name
	 * @param queryType
	 * @param statement
	 * @param permission
	 */
	public void editQueries(String name,String queryType, String statement,String permission){
		info("Click on Edit button of the query");
		click(ELEMENT_ECM_ADVANCED_QUERIES_EDIT_BUTTON.replace("{$name}",name));
		if(!queryType.isEmpty()){
			info("Select a type for the query");
			select(ELEMENT_ECM_ADVANCED_QUERIES_QUERY_TYPE_FORM,queryType);
		}
		if(!statement.isEmpty()){
			info("Type the statement: "+statement+" for the query");
			type(ELEMENT_ECM_ADVANCED_QUERIES_STATEMENT_FORM,statement, true);
		}
		if(!permission.isEmpty()){
			info("Select permission for the query");
			click(ELEMENT_ECM_ADVANCED_QUERIES_PERMISSION_FORM);
		}
		info("Save all changes");
		click(ELEMENT_ECM_ADVANCED_QUERIES_SAVE_FORM);
		info("Editing is finished");
	}
	/**
	 * Delete a queri
	 * @param name
	 */
	public void deleteQueries(String name){
		info("Delete a query");
		info("Click on Delete button of the query");
		click(By.xpath(ELEMENT_ECM_ADVANCED_QUERIES_DELETE_BUTTON.replace("{$name}", name)));
		info("Click on OK button of the alert popup");
		alert.acceptAlert();
		info("Verify that the query is deleted");
		waitForElementNotPresent(ELEMENT_ECM_ADVANCED_QUERIES_LIST.replace("{$name}",name));
		info("The query is deleted successfully");
	}

	/**
	 * Add a node type
	 * @param name
	 * @param superTypes
	 * @param opParams
	 */
	public void addNodeType(String name,String superTypes,Object... opParams){
		String mixinType = (String) (opParams.length>0 ? opParams[0] : null);
		click(ELEMENT_ECM_REPOSITORY_NODES_ADD);
		type(ELEMENT_ECM_REPOSITORY_NODES_NAME_FORM,name,true);
		type(ELEMENT_ECM_REPOSITORY_NODES_SUPER_TYPES_FORM,superTypes,true);
		if(mixinType!=null)
			select(ELEMENT_ECM_REPOSITORY_NODES_MIXIN_TYPES, mixinType);
		click(ELEMENT_ECM_REPOSITORY_NODES_SAVE_FORM);
		click(ELEMENT_ECM_REPOSITORY_NODES_OK_FORM);
	}

	/**
	 * Search a Node and check it
	 * @param name
	 * @param types
	 */
	public void searchNodeAndCheckIt(String name,String types){
		type(ELEMENT_ECM_REPOSITORY_NODES_SEARCH_NODE,name,true);
		waitForAndGetElement(ELEMENT_ECM_REPOSITORY_NODES_SEARCH_NODE).sendKeys(Keys.ENTER);
		click(By.xpath(ELEMENT_ECM_REPOSITORY_NODES_SHOW_SPECIFIC_NODE.replace("{$node}", name)));
		waitForAndGetElement(By.xpath(ELEMENT_ECM_REPOSITORY_NODES_CHECK_SUPER_TYPES.replace("{$types}", types)));
		click(ELEMENT_ECM_REPOSITORY_NODES_CLOSE_FORM);
	}
	/**
	 * register Name space
	 * @param nameSpacePrefix
	 * @param url
	 */
	public void registerNamespace(String nameSpacePrefix, String url){
		click(ELEMENT_ECM_REPOSITORY_NAMESPACES_ADD);
		type(ELEMENT_ECM_REPOSITORY_NAMESPACES_FORM_NAME, nameSpacePrefix,true);
		type(ELEMENT_ECM_REPOSITORY_NAMESPACES_URI_FORM, url,true);
		click(ELEMENT_ECM_REPOSITORY_NAMESPACES_SAVE_FORM);
	}
	/**
	 * Add a document in Template
	 * @param label
	 * @param permission
	 */
	public void addDocumentInTemplates(String label, String...permission){
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_ADD_DOCUMENT);
		type(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LABEL_FORM, label, true);
		click(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON);
		if(permission[0]=="any")
			click(ELEMENT_PERMISSION_ANY);
		else{
			ecmsPerm.selectGroupMembershipOfLock(permission[0],permission[1]);
			
		}
		Utils.pause(1000);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_FORM);
	}
	/**
	 * Edita Document in Templates
	 * @param oldName
	 * @param newName
	 */
	public void editDocumentInTemplates(String oldName, String newName){
		click(By.xpath(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_EDIT.replace("{$name}",oldName)));
		type(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LABEL_FORM, newName, true);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_EDIT_FORM);
		waitForAndGetElement(By.xpath(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST.replace("{$name}",newName)));
	}
	/**
	 * Edit permission of template dialog
	 * @param label
	 * @param group
	 * @param member
	 */
	public void editPermOfTemplateDialog(String label,String group,String member){
		info("edit permission");
		goToSpecificMainFunctions(mainEcmFunctions.TEMPLATES);
		goToSpecificFunctions(specificEcmFunctions.DOCUMENTS);
		click(By.xpath(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_EDIT.replace("{$name}",label)));
		Utils.pause(500);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_TAB,0,true);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_EDIT,0,true);
		Utils.pause(500);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_REMOVE_PERM,0,true);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_ADD_PERM,0,true);
		ecmsPerm.selectGroupMembershipOfLock(group,member);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_SAVE,0,true);
		Utils.pause(500);
	}
	/**
	 * Edit permission of template view
	 * @param label
	 * @param group
	 * @param member
	 */
	public void editPermOfTemplateView(String label,String group,String member){
		info("edit permission");
		goToSpecificMainFunctions(mainEcmFunctions.TEMPLATES);
		goToSpecificFunctions(specificEcmFunctions.DOCUMENTS);
		click(By.xpath(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_EDIT.replace("{$name}",label)));
		Utils.pause(500);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_TAB,0,true);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_EDIT,0,true);
		Utils.pause(500);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_REMOVE_PERM,0,true);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_ADD_PERM,0,true);
		ecmsPerm.selectGroupMembershipOfLock(group,member);
		click(ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_SAVE,0,true);
		Utils.pause(500);
	}
	/**
	 * Delete a Document in Template
	 * @param title
	 */
	public void deleteDocumentTemplate(String title){
		click(By.xpath(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_DELETE.replace("{$name}", title)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST.replace("{$name}",title)));
	}
	/**
	 * Add a template into list
	 * @param name
	 * @param nameTemplate
	 * @param content
	 */
	public void addTemplateInList(String name,String nameTemplate, String content){
		click(ELEMENT_ECM_TEMPLATES_LIST_ADD_LIST);
		type(ELEMENT_ECM_TEMPLATES_LIST_TEMPLATE_NAME_FORM,nameTemplate,true);
		type(ELEMENT_ECM_TEMPLATES_LIST_NAME_FORM,name,true);
		type(ELEMENT_ECM_TEMPLATES_LIST_CONTENT_FORM,content,true);
		click(ELEMENT_ECM_TEMPLATES_LIST_SAVE_FORM);
		waitForAndGetElement(By.xpath(ELEMENT_ECM_TEMPLATES_LIST_CHECK_LIST.replace("{$name}",name).replace("{$template}",nameTemplate)));
	}
	/**
	 * Edit a Template in List
	 * @param oldTem
	 * @param newName
	 */
	public void editTemplateNameInList(String oldTem, String newName){
		click(By.xpath(ELEMENT_ECM_TEMPLATES_LIST_EDIT_LIST.replace("{$name}",oldTem)));
		type(ELEMENT_ECM_TEMPLATES_LIST_NAME_FORM,newName,true);
		click(ELEMENT_ECM_TEMPLATES_LIST_SAVE_FORM);
		waitForAndGetElement(By.xpath(ELEMENT_ECM_TEMPLATES_LIST_CHECK_BY_NAME.replace("{$name}",newName)));
	}
	/**
	 * Delete a Template in List
	 * @param name
	 */
	public void deleteTemplateList(String name){
		click(By.xpath(ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST.replace("{$name}",name)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST.replace("{$name}",name)));
	}
	/**
	 * Edit a Metadata name and Permission
	 * @param oldName
	 * @param newName
	 * @param permission
	 */
	public void editeMetadataNameAndPermission(String oldName, String newName, String permission){
		click(By.xpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_EDIT.replace("{$name}",oldName)));
		type(ELEMENT_ECM_TEMPLATES_METEDATA_FORM_EDIT_LABEL,newName,true);
		click(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON);
		if(permission=="any")
			click(ELEMENT_PERMISSION_ANY);
		click(ELEMENT_ECM_TEMPLATES_METADATA_FORM_APPLY);
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST.replace("{$name}",oldName)));
	}
	/**
	 * Delete a Metadata
	 * @param name
	 */
	public void deleteMetadata(String name){
		click(By.xpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_DELETE.replace("{$name}", name)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_DELETE.replace("{$name}", name)));
		click(ELEMENT_ECM_TEMPLATES_METADATA_FORM_OK_FORM);
	}

	/**
	 * Add more actions for a View type
	 * By QuynhPT
	 * date 09/01/2015
	 * @param nameView the name of View type as: Web, Admin, Icon, List, Categories
	 * @param applyActions  the name of actions that list in the popup
	 */
	public void addActionsForAView (String nameView,specificEcmActionstypes action){
		info("Go to Explorer tab");
		goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
		info("Go to Views link");
		goToSpecificFunctions(specificEcmFunctions.VIEW);
		info("Click on Edit button of the View type");
		click(By.xpath(ELEMENT_ECM_EXPLORER_EDIT.replace("${nameView}", nameView)));
		Utils.pause(2000);
		info("Open Action tab");
		click(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM);
		Utils.pause(1000);
		info("Click on Add action button");
		click(ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM);
		Utils.pause(2000);
		info("Select the action");
		goTospecificEcmActionstypes(action);
		Utils.pause(2000);
		info("Click on Save button of Action popup");
		click(ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON);
		Utils.pause(2000);
		info("Save all changes");
		click(ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON);
		Utils.pause(1000);
		info("Finished Adding action for a view");
	}

	/**
	 * select an actions to add for a view type
	 * @param action
	 */
	public void goTospecificEcmActionstypes (specificEcmActionstypes action){
		switch(action){
		case ADD_CATEGORY:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_CATEGORY,2);
			break;
		case ADD_TRANSLATION:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_TRANSLATION,2);
			break;
		case CONTENT_NAVIGATION:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_CONTENT_NAVIGATION,2);
			break;
		case IMPORT_NOTE:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_IMPORT_NOTE,2);
			break;
		case MANAGE_CATEGORIES :
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_CATEGORIES,2);
			break;
		case MANAGE_RELATION:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_RELATIONS,2);
			break;
		case APPROVE_CONTENT:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_APPROVE_CONTENT,2);
			break;
		case SHOW_JCR_STRUCTURE:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_SHOW_JCR_STRUCTURE,2);
			break;
		case VIEW_METADATA:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_METADATA,2);
			break;
		case VIEW_PROPERTIES:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_PROPERTIES,2);
			break;
		case ADD_DOCUMENT:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_DOCUMENT,2);
			break;
		case ADD_SYMLINK:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_SYMLINK,2);
			break;
		case EDIT_DOCUMENT:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_EDIT_DOCUMENT,2);
			break;
		case MANAGE_ACTIONS:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_ACTIONS,2);
			break;
		case HIDE_SHOW_CONTENT:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_HIDE_SHOW_CONTENT,2);
			break;
		case MANAGE_VERSION :
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_VERSIONS,2);
			break;
		case PUBLISH:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_PUBLISH,2);
			break;
		case TAG_DOCUMENT:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_TAG_DOCUMENT,2);
			break;
		case VIEW_NODE_TYPE:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_NODE_TYPE,2);
			break;
		case VOTE:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VOTE,2);
			break;
		case ADD_FOLDER:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_FOLDER,2);
			break;
		case COMMENT:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_COMMENT,2);
			break;
		case EXPORT_NODE:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_EXPORT_NODE,2);
			break;
		case MANAGE_AUDITING:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_AUDITING,2);
			break;
		case MANAGE_PUBLISHTATION:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_PUBLICATION,2);
			break;
		case OVERLOAD_THUMBNAILS :
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_OVERLOAD_THUMBNAIL,2);
			break;
		case REQUEST_APPROVAL:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_REQUEST_APPROVAL,2);
			break;
		case UPLOAD:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_UPLOAD,2);
			break;
		case VIEW_PERMISSIONS:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_PERMISSIONS,2);
			break;
		case WATCH_DOCUMENTS:
			check(ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_WATCH_DOCUMENT,2);
			break;
		}
	}
	/**
	 * Add all actions
	 */
	public void addAllActions(){
		info("Go to Explorer tab");
		goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
		info("Go to Views link");
		goToSpecificFunctions(specificEcmFunctions.VIEW);
		info("Click on Edit button of the View type");
		click(ELEMENT_ECM_EXPLORER_EDIT.replace("${nameView}","Web"));
		Utils.pause(2000);
		info("Open Action tab");
		click(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM);
		Utils.pause(1000);
		info("Click on Add action button");
		click(ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM);
		Utils.pause(2000);
		info("Select the actions");
		for(specificEcmActionstypes type: specificEcmActionstypes.values())
			goTospecificEcmActionstypes(type);
		Utils.pause(2000);
		info("Click on Save button of Action popup");
		//click(ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON);
		clickByJavascript(ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON, 2);
		Utils.pause(2000);
		info("Save all changes");
		//click(ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON);
		clickByJavascript(ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON, 2);
		Utils.pause(1000);
		info("Finished Adding action for a view");
	}
	/**
	 * Add group permission to drive
	 * @param drive
	 * @param group
	 * @param membership
	 */
	public void addGroupPermToDrive(String drive,String group,String membership){
		info("add group permission to drive:"+drive);
		goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
		goToSpecificFunctions(specificEcmFunctions.DRIVES);
		click(By.xpath(ELEMENT_ECM_EXPLORER_DRIVES_EDIT_LIST.replace("{$name}", drive)),0,true);
		Utils.pause(500);
		click(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON,0,true);
		ecmsPerm.selectGroupMembershipOfDrive(group, membership);
		click(ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM,0,true);
		Utils.pause(1000);
	}
	/**
	 * Add group permission to lock
	 * @param drive
	 * @param group
	 * @param membership
	 */
	public void addGroupPermToLock(String group,String membership){
		info("add group permission to lock manager");
		goToSpecificMainFunctions(mainEcmFunctions.REPOSITORY);
		goToSpecificFunctions(specificEcmFunctions.LOCKS);
		click(ELEMENT_ECM_REPOSITORY_MANAGE_LOCK);
		ecmsPerm.selectGroupMembershipOfLock(group,membership);
		Utils.pause(1000);
	}
	/**
	 * Add group permission to tag
	 * @param drive
	 * @param group
	 * @param membership
	 */
	public void addGroupPermToTag(String group,String membership){
		info("add group permission to tag");
		goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
		goToSpecificFunctions(specificEcmFunctions.TAGS);
		click(ELEMENT_ECM_EXPLORER_TAGS_TAG_PERM_TAB);
		ecmsPerm.selectGroupMembershipOfTag(group,membership);
		Utils.pause(1000);
		click(ecmsPerm.ELEMENT_TAG_SELECT_MEMBERSHIP_ADD_BTN,0,true);
	}
	/**
	 * Add group permission to node
	 * @param drive
	 * @param group
	 * @param membership
	 */
	public void addGroupPermToNode(String group,String membership,boolean read,boolean modify,boolean remove){
		info("add group permission to node document");
		ecmsPerm.selectGroupMembershipOfTag(group,membership);
		ecmsPerm.selectCheckBoxRight(read, modify, remove);
		click(ecmsPerm.ELEMENT_PERMISSION_SAVE,0,true);
		Utils.pause(1000);
		click(ecmsPerm.ELEMENT_PERMISSION_CLOSE,0,true);
	}
  /**
   * Check select user in Advance/Category
   * @param cat: name of new category
   * @param user
   * @param isPresent
   * 				true if user is displayed
   * 				false if user is NOT displayed
   */
	public void checkUserSelectorOfCategory(String cat,String user,boolean isPresent){
		goToSpecificMainFunctions(mainEcmFunctions.ADVANCED);
		goToSpecificFunctions(specificEcmFunctions.CATEGORIES);
		info("Add a category");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_CATEGORIES);
		info("Type a name for the category");
		type(ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_FORM,cat,true);
		info("Click on Next button of the step 1");
		click(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_1STPAGE_FORM,0,true);
		info("check user selector");
		click(plfPerm.ELEMENT_SELECT_USER_ICON1,0,true);
		plfPerm.checkUserSelector(user, isPresent);
	}
		/**
	   * Check select user in Explorer/Tag
	   * @param user
	   * @param isPresent
	   * 				true if user is displayed
	   * 				false if user is NOT displayed
	   */
		public void checkUserSelectorOfTags(String user,boolean isPresent){
			goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
			goToSpecificFunctions(specificEcmFunctions.TAGS);
			click(ELEMENT_ECM_EXPLORER_TAGS_PERM_TAB,0,true);
			info("check user selector");
			click(plfPerm.ELEMENT_SELECT_USER_ICON1,0,true);
			plfPerm.checkUserSelector(user, isPresent);
		}
		/**
	   * Check select user in Explorer/View
	   * @param cat: name of view
	   * @param user
	   * @param isPresent
	   * 				true if user is displayed
	   * 				false if user is NOT displayed
	   */
		public void checkUserSelectorOfView(String view,String user,boolean isPresent){
			goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
			goToSpecificFunctions(specificEcmFunctions.VIEW);
			click(By.xpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}",view)));
			click(ELEMENT_ECM_EXPLORER_GO_TO_PERMISSION_FORM);
			info("check user selector");
			click(plfPerm.ELEMENT_SELECT_USER_ICON1,0,true);
			plfPerm.checkUserSelector(user, isPresent);
		}
	
	/**
	 * Add WebView type to drive
	 * @Author: QuyenNT
	 * Date: Oct 20, 2015
	 */
	public void addViewTypeToDrive(String driveName, specificView[] applyViews){
		NavigationToolbar navTool = new NavigationToolbar(driver);	
				
		Utils.pause(3000);		
		
		info("Go to Explorer tab");
		navTool.goToContentAdministration();
		
		info("Go to Explorer tab");
		goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
		
		info("Go to Drives link");
		goToSpecificFunctions(specificEcmFunctions.DRIVES);
		
		editDrives(driveName,applyViews);
		Utils.pause(3000);
	}	
	
	/**
	 * Add permission to Drive
	 * @Author: QuyenNT
	 * Date: Oct 23, 2015
	 */
	public void addPermissionToDrive(String driveName, String permissionToAdd){
		NavigationToolbar navTool = new NavigationToolbar(driver);	
				
		Utils.pause(3000);		
		
		info("Go to Explorer tab");
		navTool.goToContentAdministration();
		
		info("Go to Explorer tab");
		goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
		
		info("Go to Drives link");
		goToSpecificFunctions(specificEcmFunctions.DRIVES);
		
		info("Open Drive");
		click(By.xpath(ELEMENT_ECM_EXPLORER_DRIVES_EDIT_LIST.replace("{$name}", driveName)));
		info("Go to Drive tab");
		click(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON);
		
		WebElement permissionElement = getElement(ELEMENT_ECM_DRIVE_PERMISSION);
		String permissionValue = permissionElement.getAttribute("value");
		permissionValue += "," + permissionToAdd;
		
		type(ELEMENT_ECM_DRIVE_PERMISSION, permissionValue, true);
		
		Utils.pause(3000);
		click(ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM);
	}		
}
