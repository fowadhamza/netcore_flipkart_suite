package com.project.autotest.fun.tests.steps;

import com.project.autotest.fun.pages.Pages;
import cucumber.api.java.en.When;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class WhenSteps {

    private static final Pages page = CommonSteps.getPage();

    //----------------------------Generic Cucumber Steps -----------------------------------------------

    //---WHEN STEPS-----------------------------------------------------------------------

    @When("^I enter the (.*) - (.*)$")
    public void enterTheStringInTextbox(String xpathKey, String value) {
        if (containsIgnoreCase(value, "random")) {
            value = page.commonSeleniumActions().generateRandomIds(xpathKey, value);
        }
        page.commonSeleniumActions().sendKeys(xpathKey, value);
    }

    @When("^I click on the (.*) (button|link|checkbox)$")
    public void clickOnTheElement(String xpathKey, String element) {
        xpathKey = xpathKey + " " + element;
        page.commonSeleniumActions().clickOnElement(xpathKey, 5);
    }



    @When("^I wait and click on the (.*) (button|link)$")
    public void waitAndClickOn(String xpathKey, String element) throws InterruptedException {
        xpathKey = xpathKey + " " + element;
        page.commonSeleniumActions().waitAndClick(xpathKey);
    }




//    @When("^I click on the (.*) (button|link) and wait until invisible$")
//    public void clickOnTheElementWaitUntilInvisible(String xpathKey, final String element) {
//        xpathKey = xpathKey + " " + element;
//        if (containsIgnoreCase(xpathKey, "placeable")) {
//            page.commonSeleniumActions().extractProductName(xpathKey);
//        } else if (containsIgnoreCase(xpathKey, "line item delete")) {
//            page.homePage().removedProductFromSavedList(xpathKey);
//        }
//        page.commonSeleniumActions().clickOnElementAndWaitUntilInvisible(xpathKey, 5);
//    }

//    @When("^I open the activation link from mail$")
//    public void openTheActivationLink() {
//        page.loginPage().openActivationLink();
//    }

    @When("^I validate (.*)$")
    public void validate(String key) throws InterruptedException {
        page.commonSeleniumActions().verifyElementIsDisplayed(key,20);
        Thread.sleep(1000000);
    }

    @When("^I should see the element (.*) contain the text (.*)$")
    public void validateText(String xpathKey,String text) throws InterruptedException {
        page.commonSeleniumActions().verifyStringContainsTheExpectedValue(xpathKey,text);
    }

    @When("^I should store the ((?:[a-zA-Z0-9 ]+,)*[a-zA-Z0-9 ]+) for max price of INR(\\d+)$")
    public void storeSearch( String attributes, Integer price)
        throws InterruptedException, IOException {
        page.homePage().saveCSV( attributes, price);
    }
}