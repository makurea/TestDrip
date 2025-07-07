package com.testdrip.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiClient {

  private static final String BASE_URI = "https://petstore.swagger.io/v2";

  public static Response get(String path) {
    return RestAssured
        .given()
        .baseUri(BASE_URI)
        .header("Accept", "application/json")
        .log().all()
        .when()
        .get(path)
        .then()
        .log().all()
        .extract()
        .response();
  }
  public static Response post(String path, String body) {
    return RestAssured
        .given()
        .baseUri(BASE_URI)
        .contentType(ContentType.JSON)
        .body(body)
        .log().all()
        .when()
        .post(path)
        .then()
        .log().all()
        .extract()
        .response();
  }

  public static Response delete(String path) {
    return RestAssured
        .given()
        .baseUri(BASE_URI)
        .log().all()
        .when()
        .delete(path)
        .then()
        .log().all()
        .extract()
        .response();
  }
}
