package org.exoplatform.platform.qa.ui.getRequest;

import io.restassured.RestAssured;
import org.junit.Test;

public class RestAssuredDemo
{
  @Test
  public void test12() {

    int code = RestAssured.given().auth()
            .preemptive().basic("ToolsQA", "TestPassword")
            .get("https://restapi.demoqa.com/authentication/CheckForAuthentication/")
            .getStatusCode();

    System.out.println("Response code from server is " + code);

  }
}
