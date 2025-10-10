## 🚀 SpaceX Capsules API Tests

### 📘 Описание проекта

**SpaceX Capsules API Tests** — это **учебный проект по автоматизации тестирования REST API** с использованием публичного API компании [SpaceX](https://api.spacexdata.com/v3).
Проект демонстрирует подходы к BDD (Behavior Driven Development) с использованием Cucumber, REST Assured, JUnit 5 и Allure.

API, используемое в проекте:

> 🔗 [https://api.spacexdata.com/v3](https://api.spacexdata.com/v3)

Пример тестируемого эндпоинта:

```
GET https://api.spacexdata.com/v3/capsules/C112
```

---

### 🧰 Используемые технологии

| Компонент                       | Назначение                                             |
| ------------------------------- | ------------------------------------------------------ |
| **Java 17**                     | Язык разработки                                        |
| **Gradle**                      | Система сборки проекта                                 |
| **JUnit 5**                     | Фреймворк тестирования                                 |
| **Cucumber (BDD)**              | Формат тестов в стиле Gherkin                          |
| **REST Assured**                | Тестирование REST API                                  |
| **AssertJ**                     | Удобные fluent-ассерты                                 |
| **Allure**                      | Отчёты о тестировании                                  |
| **Log4j2**                      | Логирование шагов и результатов                        |
| **SLF4J Bridge**                | Интеграция с Log4j                                     |
| **Allure Cucumber 7 JVM**       | Поддержка Allure для Cucumber                          |
| **JUnit Platform Suite Engine** | Запуск тестов под JUnit 5                              |
| **GitHub Actions**              | CI/CD пайплайн (автозапуск тестов и публикация отчёта) |

---

### ⚙️ CI/CD: GitHub Actions

Проект использует автоматический пайплайн **GitHub Actions**, который:

* запускается при каждом пуше в ветку `main`;
* выполняет тесты на **Ubuntu с JDK 17**;
* генерирует отчёт **Allure**;
* публикует Allure Report на **GitHub Pages** 🚀.

#### Конфигурация пайплайна

Файл `.github/workflows/test.yml`:

```yaml
name: test-and-publish

on:
  push:
    branches: [ main ]
  workflow_dispatch:

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
          cache: 'gradle'

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      - name: Run tests and generate Allure results
        run: ./gradlew clean test

      - name: Get Allure history
        uses: actions/checkout@v4
        if: always()
        continue-on-error: true
        with:
          ref: gh-pages
          path: gh-pages

      - name: Generate Allure Report # Генерация отчёта с историей
        uses: simple-elf/allure-report-action@v1.9
        id: allure_report
        if: always()
        with:
          allure_results: build/allure-results
          allure_history: gh-pages/allure-history
          github_token: ${{ secrets.GITHUB_TOKEN }}

      - name: Deploy Allure report to GitHub Pages 🚀
        uses: peaceiris/actions-gh-pages@v4
        if: always()
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: allure-report
          publish_branch: gh-pages
```

📊 После выполнения пайплайна отчёт доступен по адресу:
`https://makurea.github.io/TestDrip`

---

### 🗂️ Структура проекта

```
TestDrip/
 ├── build.gradle                     # Настройки сборки Gradle
 ├── src/
 │   ├── main/
 │   │   └── resources/
 │   └── test/
 │       ├── java/com/testdrip/steps/   # Step Definitions (Cucumber)
 │       ├── resources/features/        # Gherkin-файлы с тестами
 │       └── log4j2.xml                 # Конфигурация логирования
 ├── .github/workflows/test-and-publish.yml  # CI/CD пайплайн
 ├── build/                             # Каталог сборки Gradle
 └── README.md
```

---

### 💻 Команды Gradle

| Команда               | Назначение                       |
| --------------------- | -------------------------------- |
| `gradle build`        | Сборка проекта                   |
| `gradle test`         | Запуск всех тестов               |
| `gradle allureReport` | Генерация Allure отчёта          |
| `gradle allureServe`  | Запуск локального Allure сервера |
| `gradle clean`        | Очистка сборки                   |

---

### 🧾 Примечания

* При повторной генерации отчёта Allure может появиться сообщение:
  *“Target directory … for the report is already in use”*
  → Используйте команду:

  ```bash
  gradle allureReport --clean
  ```

---



