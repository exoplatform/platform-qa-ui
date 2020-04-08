package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.SocialActivitiesManagement;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;

public class SocialActivitiesTestAPI extends  Base{

  SocialActivitiesManagement socialActivitiesManagement = new SocialActivitiesManagement(this);

  @Test
  public void testResponseCode()

  {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/activities/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test_getActivitiesAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/activities/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Activities Data are" + data);
  }

  @Test
  public void test_getFirstActivityDataAPI() {

    socialActivitiesManagement.getLastActivityAPI();

  }


  @Test
  public void  test_putActivityAPI() {

    String activity = "activity" + getRandomNumber();

    socialActivitiesManagement.putActivityAPI(socialActivitiesManagement.getLastActivityAPI(),activity);

  }

  @Test
  public void  test_deleteActivityAPI() {

    socialActivitiesManagement.deleteActivityAPI(socialActivitiesManagement.getLastActivityAPI());

  }

}
