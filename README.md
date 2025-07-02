# TestDrip

Автоматизированные API тесты для Swagger Petstore с использованием Java, JUnit5, Rest Assured и Cucumber.

---

## Структура проекта

- `src/test/resources/features` — файлы фич Cucumber
- `src/test/java/com/testdrip/stepdefinitions` — реализации шагов тестов
- `src/test/java/com/testdrip/runners` — запуск тестов (TestRunner)
- `src/test/java/com/testdrip/utils` — вспомогательные классы (ApiClient и т.п.)

---

## Запуск тестов локально

```bash
./gradlew clean test
```
---

## Allure отчет

Отчет генерируется автоматически после запуска тестов.

**Публичная ссылка:**  
[https://makurea.github.io/TestDrip/](https://makurea.github.io/TestDrip/)

---

## CI/CD

GitHub Actions запускает тесты и обновляет отчет на GitHub Pages при каждом пуше в ветку main.

---

## Контакты
Автор: makurea
GitHub: https://github.com/makurea