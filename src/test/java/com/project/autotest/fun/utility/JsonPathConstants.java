package com.project.autotest.fun.utility;

public enum JsonPathConstants {
  RESET_PASSWORD_VALUE("result.body.value"),
  RESET_PASSWORD_VALUES("result.body.value");


  private final String jsonPath;

  JsonPathConstants(String jsonPath) {
    this.jsonPath = jsonPath;
  }

  public static String getJsonPathForKey(String key) {
    key = key.toUpperCase().replaceAll("\\s", "_");
    try {
      return JsonPathConstants.valueOf(key).getJsonPath();
    } catch (EnumConstantNotPresentException e) {
      return null;
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  public String getJsonPath() {
    return jsonPath;
  }
}
