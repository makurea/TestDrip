// src/test/java/com/testdrip/runners/CucumberRunner.java
package com.testdrip.runners;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.testdrip.steps")
public class CucumberRunner {
  // Этот класс остается пустым, он просто запускает тесты Cucumber.
}