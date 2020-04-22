package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.BrandingManagement;
import org.junit.Assert;
import org.junit.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;

public class BrandingTestAPI extends Base {

  BrandingManagement brandingManagement = new BrandingManagement(this);

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/v1/platform/branding/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test03_getComapnyDataAPI() {

    brandingManagement.getCompanyDataAPI();

  }


  @Test
  public void test04_putBrandingAPI() {

    String company = "company" + getRandomNumber();
    String darkTheme = "Dark";
    String lightTheme = "Light";

    brandingManagement.putBrandingAPI(company, darkTheme);
    brandingManagement.getCompanyDataAPI();

  }


}
