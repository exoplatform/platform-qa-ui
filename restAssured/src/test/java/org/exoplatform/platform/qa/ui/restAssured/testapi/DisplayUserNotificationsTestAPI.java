package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;


public class DisplayUserNotificationsTestAPI extends Base {


  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.DATA_USER1, PLFData.DATA_PASS2).when().get(getExoWebDriver().getBaseUrl() + "rest/private/notifications/webNotifications/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test02_getUserNotificationsAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/notifications/webNotifications/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println(PLFData.DATA_USER1 + "Notifications are" + data);
  }

}
