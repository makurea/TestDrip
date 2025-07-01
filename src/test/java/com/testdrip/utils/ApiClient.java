package com.testdrip.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {

  private static final String BASE_URI = "https://petstore.swagger.io/v2";

  public static Response get(String path) {
    return RestAssured
        .given()
        .baseUri(BASE_URI)
        .header("Accept", "application/json")
        .when()
        .get(path)
        .then()
        .extract()
        .response();
  }
}
