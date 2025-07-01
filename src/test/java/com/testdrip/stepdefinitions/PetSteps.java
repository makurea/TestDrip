package com.testdrip.stepdefinitions;

import com.testdrip.utils.ApiClient;
import io.cucumber.java.en.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PetSteps {

  private Response response;

  @Step("Проверяем, что API Petstore доступен")
  @Given("the Petstore API is available")
  public void thePetstoreApiIsAvailable() {
    Response healthCheck = ApiClient.get("/pet/findByStatus?status=available");
    assertThat(healthCheck.statusCode()).isEqualTo(200);
  }

  @Step("Отправляем GET-запрос для питомцев со статусом {status}")
  @When("I send GET request for pets with status {string}")
  public void iSendGetRequestForPetsWithStatus(String status) {
    response = ApiClient.get("/pet/findByStatus?status=" + status);
  }

  @Step("Проверяем, что получен непустой список питомцев")
  @Then("I should receive a non-empty list of pets")
  public void iShouldReceiveANonEmptyListOfPets() {
    assertThat(response.statusCode()).isEqualTo(200);
    assertThat(response.jsonPath().getList("$")).isNotEmpty();
  }

  @Step("Проверяем, что у каждого питомца есть id и имя")
  @Then("each pet should have an id and a name")
  public void eachPetShouldHaveIdAndName() {
    var pets = response.jsonPath().getList("$");

    for (Object pet : pets) {
      @SuppressWarnings("unchecked")
      Map<String, Object> map = (Map<String, Object>) pet;
      assertThat(map).containsKeys("id", "name");
    }
  }
}
