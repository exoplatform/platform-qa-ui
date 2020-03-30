package org.exoplatform.platform.qa.ui.getRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.*;

public class getData {

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void testBody() {
    Response resp = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");

    String data = resp.asString();
    System.out.println("Data is" + data);
    System.out.println("Response time " + resp.getTime());

  }

  @Test
  public void test1() {

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    json.put("id", "26");
    json.put("title", "Selenium");
    json.put("author", "Automation155");

    request.body(json.toJSONString());
    Response response = request.put("http://localhost:3000/posts/26");
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);

  }


}
