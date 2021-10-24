package com.project.autotest.fun.tests.steps;

import com.project.autotest.fun.pages.Pages;

public class CommonSteps {

  private static Pages page;

  public CommonSteps() {
  }

  public static Pages getPage() {
    if (page == null) {
      page = new Pages();
    }

    return page;
  }


}