// src/test/java/com/testdrip/steps/SpaceXCapsulesSteps.java
package com.testdrip.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.assertj.core.api.Assertions.assertThat;

public class SpaceXCapsulesSteps {

  private static final Logger logger = LogManager.getLogger(SpaceXCapsulesSteps.class);
  private String baseUri;
  private Response response;

  @Given("the SpaceX API base URI {string}")
  public void theSpaceXAPIBaseURI(String uri) {
    this.baseUri = uri;
    RestAssured.baseURI = baseUri;
    logger.info("Base URI set to: {}", baseUri);
  }

  @When("I request capsule details for serial {string}")
  public void iRequestCapsuleDetailsForSerial(String capsuleSerial) {
    logger.debug("Requesting details for capsule serial: {}", capsuleSerial);
    response = RestAssured.given()
        .pathParam("capsule_serial", capsuleSerial)
        .get("/capsules/{capsule_serial}");
    response.prettyPrint();
  }

  @Then("the response status code is {int}")
  public void theResponseStatusCodeIs(int expectedStatusCode) {
    assertThat(response.statusCode())
        .as("Status code should be " + expectedStatusCode)
        .isEqualTo(expectedStatusCode);
    logger.info("Status code is {}", expectedStatusCode);
  }

  @Then("the capsule serial in the response is {string}")
  public void theCapsuleSerialInTheResponseIs(String expectedSerial) {
    String actualSerial = response.jsonPath().getString("capsule_serial");
    assertThat(actualSerial)
        .as("Capsule serial should be " + expectedSerial)
        .isEqualTo(expectedSerial);
    logger.info("Capsule serial is {}", actualSerial);
  }

  @Then("the capsule status is {string}")
  public void theCapsuleStatusIs(String expectedStatus) {
    String actualStatus = response.jsonPath().getString("status");
    assertThat(actualStatus)
        .as("Capsule status should be " + expectedStatus)
        .isEqualTo(expectedStatus);
    logger.info("Capsule status is {}", actualStatus);
  }

  @Then("the capsule type is {string}")
  public void theCapsuleTypeIs(String expectedType) {
    String actualType = response.jsonPath().getString("type");
    assertThat(actualType)
        .as("Capsule type should be " + expectedType)
        .isEqualTo(expectedType);
    logger.info("Capsule type is {}", actualType);
  }
}