package com.testdrip.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
  private static final Properties properties = new Properties();

  static {
    try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
      if (input == null) {
        throw new RuntimeException("config.properties not found in resources");
      }
      properties.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load config.properties", e);
    }
  }

  public static String get(String key) {
    return properties.getProperty(key);
  }
}
