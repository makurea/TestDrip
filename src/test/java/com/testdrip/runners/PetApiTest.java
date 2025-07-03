package com.testdrip.runners;

import com.testdrip.utils.ApiClient; // Импортируем наш API-клиент
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class) // Указываем порядок выполнения тестов по аннотации @Order
public class PetApiTest {

  private static long petId; // Для хранения ID питомца между тестами

  @Test
  @Order(1) // Этот тест выполнится первым
  @DisplayName("Создание нового питомца")
  void createNewPetTest() {
    System.out.println("--- Запуск теста: Создание нового питомца ---");

    String requestBody = "{\n"
        + "  \"id\": 0,\n" // ID будет сгенерирован сервером, но можно отправить 0
        + "  \"category\": {\n"
        + "    \"id\": 1,\n"
        + "    \"name\": \"dogs\"\n"
        + "  },\n"
        + "  \"name\": \"doggie\",\n"
        + "  \"photoUrls\": [\n"
        + "    \"string\"\n"
        + "  ],\n"
        + "  \"tags\": [\n"
        + "    {\n"
        + "      \"id\": 0,\n"
        + "      \"name\": \"string\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"status\": \"available\"\n"
        + "}";

    Response response = ApiClient.post("/pet", requestBody); // Используем POST для создания

    response.then().statusCode(200); // Проверяем, что статус 200 OK

    // Извлекаем ID созданного питомца для последующих тестов
    petId = response.jsonPath().getLong("id");
    System.out.println("Создан питомец с ID: " + petId);

    assertThat(petId).isPositive(); // Проверяем, что ID сгенерирован (больше 0)
    assertThat(response.jsonPath().getString("name")).isEqualTo("doggie"); // Проверяем имя
    assertThat(response.jsonPath().getString("status")).isEqualTo("available"); // Проверяем статус

    System.out.println("--- Тест 'Создание нового питомца' завершен ---");
  }

  @Test
  @Order(2) // Этот тест выполнится вторым
  @DisplayName("Получение питомца по ID")
  void getPetByIdTest() {
    System.out.println("--- Запуск теста: Получение питомца по ID ---");

    assertThat(petId).isPositive(); // Убеждаемся, что petId установлен из предыдущего теста

    Response response = ApiClient.get("/pet/" + petId);

    response.then().statusCode(200); // Проверяем, что статус 200 OK

    assertThat(response.jsonPath().getLong("id")).isEqualTo(petId); // Проверяем, что вернулся правильный ID
    assertThat(response.jsonPath().getString("name")).isEqualTo("doggie"); // Проверяем имя
    assertThat(response.jsonPath().getString("status")).isEqualTo("available"); // Проверяем статус

    System.out.println("--- Тест 'Получение питомца по ID' завершен ---");
  }

  @Test
  @Order(3) // Этот тест выполнится третьим
  @DisplayName("Удаление питомца по ID")
  void deletePetTest() {
    System.out.println("--- Запуск теста: Удаление питомца по ID ---");

    assertThat(petId).isPositive(); // Убеждаемся, что petId установлен

    Response response = ApiClient.delete("/pet/" + petId); // Используем DELETE для удаления

    response.then().statusCode(200); // Проверяем, что статус 200 OK
    assertThat(response.jsonPath().getLong("message")).isEqualTo(petId); // Проверяем, что в сообщении есть ID

    System.out.println("--- Тест 'Удаление питомца по ID' завершен ---");
  }


  @Test
  @Order(4) // Этот тест выполнится четвертым
  @DisplayName("Попытка получить удаленного питомца (должен быть 404 Not Found)")
  void getDeletedPetNotFoundTest() {
    System.out.println("--- Запуск теста: Попытка получить удаленного питомца ---");

    assertThat(petId).isPositive(); // Убеждаемся, что petId установлен

    Response response = ApiClient.get("/pet/" + petId);

    // Ожидаем 404 Not Found, что является корректным поведением для удаленного ресурса
    response.then().statusCode(404); // <--- ВОТ ЭТО КОРРЕКТНОЕ ИЗМЕНЕНИЕ!

    // Проверяем, что в теле ответа есть сообщение "Pet not found"
    // (хотя при 404 оно может быть и необязательным, но Petstore его возвращает)
    assertThat(response.jsonPath().getString("message")).isEqualTo("Pet not found");
    assertThat(response.jsonPath().getInt("code")).isEqualTo(1); // Проверяем код ошибки

    System.out.println("--- Тест 'Попытка получить удаленного питомца' завершен ---");
  }
}