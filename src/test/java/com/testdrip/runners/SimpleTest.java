package com.testdrip.runners;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest {

  @Test
  @DisplayName("Простой успешный тест")
  void successfulTest() {
    System.out.println("--- Запуск успешного теста ---");
    assertThat(true).isTrue(); // Простая проверка, которая всегда будет успешной
    System.out.println("--- Успешный тест завершен ---");
  }

  @Test
  @DisplayName("Простой провальный тест")
  void failedTest() {
    System.out.println("--- Запуск провального теста ---");
    // Этот тест намеренно провалится
    assertThat(false).isTrue();
    System.out.println("--- Провальный тест завершен ---");
  }

  @Test
  @DisplayName("Простой пропущенный тест")
  void skippedTest() {
    System.out.println("--- Запуск пропущенного теста ---");
    // Этот тест будет пропущен. Полезно для демонстрации в отчете.
    org.junit.jupiter.api.Assumptions.assumeFalse(true, "Тест пропущен намеренно для демонстрации.");
    System.out.println("--- Пропущенный тест завершен ---"); // Эта строка не выполнится
  }
}