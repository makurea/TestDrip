// src/test/java/com/testdrip/steps/SpaceXCapsulesSteps.java
package com.testdrip.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.assertj.core.api.Assertions.assertThat; // Используем AssertJ для удобных проверок

public class SpaceXCapsulesSteps {

  private String baseUri;
  private Response response;

  @Given("the SpaceX API base URI {string}")
  public void theSpaceXAPIBaseURI(String uri) {
    this.baseUri = uri;
    RestAssured.baseURI = baseUri;
    System.out.println("Base URI set to: " + baseUri);
  }

  @When("I request capsule details for serial {string}")
  public void iRequestCapsuleDetailsForSerial(String capsuleSerial) {
    System.out.println("Requesting details for capsule serial: " + capsuleSerial);
    response = RestAssured.given()
        .pathParam("capsule_serial", capsuleSerial) // Указываем параметр пути
        .get("/capsules/{capsule_serial}"); // Используем шаблон пути
    response.prettyPrint(); // Выводим полный ответ для отладки
  }

  @Then("the response status code is {int}")
  public void theResponseStatusCodeIs(int expectedStatusCode) {
    System.out.println("Verifying status code...");
    assertThat(response.statusCode())
        .as("Status code should be " + expectedStatusCode)
        .isEqualTo(expectedStatusCode);
    System.out.println("Status code is " + expectedStatusCode);
  }

  @Then("the capsule serial in the response is {string}")
  public void theCapsuleSerialInTheResponseIs(String expectedSerial) {
    System.out.println("Verifying capsule serial...");
    String actualSerial = response.jsonPath().getString("capsule_serial");
    assertThat(actualSerial)
        .as("Capsule serial should be " + expectedSerial)
        .isEqualTo(expectedSerial);
    System.out.println("Capsule serial is " + actualSerial);
  }

  @Then("the capsule status is {string}")
  public void theCapsuleStatusIs(String expectedStatus) {
    System.out.println("Verifying capsule status...");
    String actualStatus = response.jsonPath().getString("status");
    assertThat(actualStatus)
        .as("Capsule status should be " + expectedStatus)
        .isEqualTo(expectedStatus);
    System.out.println("Capsule status is " + actualStatus);
  }

  @Then("the capsule type is {string}")
  public void theCapsuleTypeIs(String expectedType) {
    System.out.println("Verifying capsule type...");
    String actualType = response.jsonPath().getString("type");
    assertThat(actualType)
        .as("Capsule type should be " + expectedType)
        .isEqualTo(expectedType);
    System.out.println("Capsule type is " + actualType);
  }
}