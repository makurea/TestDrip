// src/test/java/com/testdrip/runners/CucumberRunner.java
package com.testdrip.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Ищет .feature файлы в src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.testdrip.steps") // Указывает на пакет с шагами
public class CucumberRunner {
  // Этот класс остается пустым, он просто запускает тесты Cucumber.
  // Все настройки через аннотации.
}