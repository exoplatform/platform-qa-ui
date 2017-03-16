package org.exoplatform.platform.qa.ui.selenium.platform.wiki;


import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class WikiDraftPage {
	public ManageAlert magAl;
	private final TestBase testBase;

	private ElementEventTestBase evt;
	/**
	 * constructor
	 * @param dr
	 */
	ManageAlert alert;
	public WikiDraftPage(TestBase testBase){
		this.alert = new ManageAlert(testBase);
		this.testBase = testBase;
		this.evt = testBase.getElementEventTestBase();
	}
	
	/**
	 * Deletes a draft.
	 * 
	 * 
	 * @param title The title of a wiki's page to be deleted.
	 */
	public void deleteDraft(String title){
		evt.click(ELEMENT_DELETE_DRAFT.replace("${title}", title));
		alert.acceptAlert();
		evt.waitForElementNotPresent(ELEMENT_DELETE_DRAFT.replace("${title}", title));
	}
	/**
	 * resume a draft
	 * @param title
	 */
	public void resumeADraft(String title){
		info("Click on the title of the draf in the list");
		evt.waitForAndGetElement(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title),3000,0).click();
	    Utils.pause(3000);
	}
}
