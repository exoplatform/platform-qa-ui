package org.exoplatform.platform.qa.ui.selenium.platform.ecms;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;

public class SEOManagement  {
	
	public ManageAlert magAlert;
	private final TestBase testBase;
	private ElementEventTestBase evt;
		
	public SEOManagement(TestBase testBase) {
		this.testBase = testBase;
		this.evt = testBase.getElementEventTestBase();
		this.magAlert = new ManageAlert(testBase);
	}
	/**
	 * Delete a added language
	 * @param language
	 */
	public void deleteLanguage(String language){
		evt.click(ELEMENT_SEO_SELECTED_LANGUAGE.replace("${language}",language));
		evt.click(ELEMENT_SEO_DELETE);
		magAlert.acceptAlert();
		evt.click(ELEMENT_SEO_CLOSE);
	}
	
		
}
