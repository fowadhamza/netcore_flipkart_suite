package com.project.autotest.fun.tests.steps;

import com.project.autotest.fun.pages.Pages;
import cucumber.api.java.en.Given;
import java.io.IOException;

public class GivenSteps {

  private static final Pages page = CommonSteps.getPage();

  //----------------------------Generic Cucumber Steps -----------------------------------------------
  //---GIVEN STEPS-----------------------------------------------------------------------


  @Given("^I go to (.*)$")
  public void open1(String site) throws InterruptedException, IOException {
    page.homePage().goToSite(site);
  }
}