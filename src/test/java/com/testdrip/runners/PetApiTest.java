package com.testdrip.runners;

import com.testdrip.utils.ApiClient; // Импортируем наш API-клиент
import io.qameta.allure.Link; // Добавим для примера, если есть URL к документации
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag; // Если используете теги
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance; // Для @BeforeAll без static
import org.junit.jupiter.api.BeforeAll;

import static org.assertj.core.api.Assertions.assertThat; // Используем assertj для более читаемых ассертов

// Определите константы для тегов, если они нужны
// Например, создайте класс common.Tags если его нет, или используйте прямо здесь
// import static common.Tags.PET_SERVICE; // Пример тега

@Tag("PetService") // Пример тега для сервиса
@Link(name = "Petstore API Docs", url = "https://petstore.swagger.io/v2/swagger.json") // Пример ссылки на документацию
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Позволяет использовать нестатический @BeforeAll
public class PetApiTest {

  // Объявляем наш API клиент так же, как в вашем примере
  private ApiClient petApiClient;
  private static long createdPetId; // Передаем ID между тестами, если бы их было несколько

  // Настройка перед всеми тестами, похожая на ваш setUp()
  @BeforeAll
  public void setUp() {
    // Инициализация клиента, если она нужна (у нас пока методы статические)
    // Если ApiClient.java будет нестатическим, то здесь будет:
    // petApiClient = new ApiClient("https://petstore.swagger.io/v2");
    System.out.println("--- Выполнение Pre-conditions для PetApiTest ---");
  }

  @DisplayName("Verify creation of a new pet in Petstore")
  @Tag("Positive") // Пример позитивного тега
  @Test
  public void createNewPetTest() {
    System.out.println("--- Запуск теста: Создание нового питомца ---");

    String requestBody = "{\n"
        + "  \"id\": 0,\n" // ID будет сгенерирован сервером, но можно отправить 0
        + "  \"category\": {\n"
        + "    \"id\": 1,\n"
        + "    \"name\": \"dogs\"\n"
        + "  },\n"
        + "  \"name\": \"doggie_" + System.currentTimeMillis() + "\",\n" // Делаем имя уникальным
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

    // Вызываем POST-метод для создания питомца
    Response response = ApiClient.post("/pet", requestBody);

    // Одна основная проверка - статус 200 OK
    response.then().statusCode(200);

    // Опционально: сохраняем ID для других тестов, если бы они были
    createdPetId = response.jsonPath().getLong("id");
    System.out.println("Создан питомец с ID: " + createdPetId);

    // Еще одна проверка, чтобы убедиться, что ID действительный
    assertThat(createdPetId).isPositive();

    System.out.println("--- Тест 'Создание нового питомца' завершен ---");
  }

  //
    /*
    @DisplayName("Verify retrieval of a created pet by ID")
    @Tag("Positive")
    @Test
    public void getCreatedPetById() {
        System.out.println("--- Запуск теста: Получение созданного питомца по ID ---");
        assertThat(createdPetId).isPositive(); // Убеждаемся, что ID установлен

        Response response = ApiClient.get("/pet/" + createdPetId);

        response.then().statusCode(200);
        assertThat(response.jsonPath().getLong("id")).isEqualTo(createdPetId);
        System.out.println("--- Тест 'Получение созданного питомца по ID' завершен ---");
    }
    */
}