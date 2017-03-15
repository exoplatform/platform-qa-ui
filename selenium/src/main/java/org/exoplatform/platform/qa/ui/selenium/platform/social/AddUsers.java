package org.exoplatform.platform.qa.ui.selenium.platform.social;


import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_GMAIL_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;


public class AddUsers {
	private final TestBase testBase;
	private ElementEventTestBase evt;

	public AddUsers(TestBase testBase){
		this.testBase = testBase;
		this.evt = testBase.getElementEventTestBase();
	}
	
	/**
	 * Add en user on the plf
	 * @param userName
	 * @param Password
	 * @param email
	 * @param Firstname
	 * @param lastName
	 */
	public void addUser(String userName, String Password, String email, String Firstname, String lastName){
		info("Add an user");
		evt.type(ELEMENT_USERNAME,userName,true);
		evt.type(ELEMENT_PASSWORD,Password,true);
		evt.type(ELEMENT_CONFIRM_PASSWORD,Password,true);
		evt.type(ELEMENT_EMAIL,email,true);
		evt.type(ELEMENT_FIRSTNAME,Firstname,true);
		evt.type(ELEMENT_LASTNAME,lastName,true);
		evt.click(ELEMENT_SAVE);
		Utils.pause(2500);
		evt.click(ELEMENT_CONFIRM_INFORMATION);
	}
	
	/**
	 * function: check content of mail then delete mail
	 * @param title title of the page
	 * @param opParams if true check it's present, false check if it's not present
	 */
	public void checkEmailNotification(String title,Object... opParams){
		info("Check and delete mail");
		Boolean checkOrNo = (Boolean)(opParams.length > 0 ? opParams[0]: true);
		String parentWindow = testBase.getSeleniumDriver().getWindowHandle();
		info("parentWindow:"+parentWindow);
		  for(String windowHandle  : testBase.getSeleniumDriver().getWindowHandles()){
			  testBase.getSeleniumDriver().switchTo().window(windowHandle);
			     info("driver.title:"+testBase.getSeleniumDriver().getTitle());
		}
		if(checkOrNo==true){
			evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,0);
		}else{
			evt.waitForElementNotPresent(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,0);
		}
		
	}
}
