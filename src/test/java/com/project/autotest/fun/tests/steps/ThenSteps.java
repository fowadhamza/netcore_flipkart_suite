package com.project.autotest.fun.tests.steps;

import com.project.autotest.fun.pages.Pages;
import cucumber.api.java.en.Then;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class ThenSteps {

    private static final Pages page = CommonSteps.getPage();

    //----------------------------Generic Cucumber Steps -----------------------------------------------

    //---THEN STEPS-----------------------------------------------------------------------
    @Then("^the (.*) (page|popup|header|dashboard|section|status) should appear$")
    public void verifyThePage(String xpathKey, String section) {
        page.commonSeleniumActions().verifyElementIsDisplayed(xpathKey + "_" + section, 15);
    }

    @Then("^the (.*) textbox should (be empty|contain .*)$")
    public void verifyTestIsDisplayed(String xpathKey, String expectedValue) {
        if (containsIgnoreCase(expectedValue, "empty")) {
            expectedValue = "";
        } else {
            expectedValue = expectedValue.replaceFirst("^contain ", "");
        }
        page.commonSeleniumActions().verifyContentInTextBoxMatchesWithExpected(xpathKey, expectedValue);
    }

    @Then("^the (.*) (message|notification|value) should (be displayed|contain) (.*)$")
    public void verifyTheMessageDisplayed(String xpathKey, String contentType, String validationType, String expectedMessage) {
        xpathKey = xpathKey + " " + contentType;
        if (containsIgnoreCase(validationType, "contain")) {
            page.commonSeleniumActions().verifyStringContainsTheExpectedValue(xpathKey, expectedMessage);
        } else {
            page.commonSeleniumActions().verifyContentMatchesWithExpected(xpathKey, expectedMessage);
        }
    }

    @Then("^the (.*) should be (enabled|disabled)$")
    public void verifyTheMessageDisplayed(String xpathKey, String elementState) {
        page.commonSeleniumActions().verifyTheElementState(xpathKey, elementState);
    }



//    @Then("^the number of items in cart icon should be (increased|decreased)$")
//    public void verifyTheCartIconNumberChanging(String change) {
//        page.homePage().cartIconValueChanged(change);
//    }
//
//    @Then("^the edited height value should be (discarded)$")
//    public void verifyTheHeightValueInPdp(String change) {
//        page.homePage().heightValueDiscarded(change);
//    }
//
//    @Then("^the recent order id value should be (available)$")
//    public void verifyTheRecentOrderIdInMyOrders(String change) {
//        page.homePage().recentOrderIsInMyOrders(change);
//    }
//
//    @Then("^the added items should displayed in the cart page$")
//    public void addedProductsAreDisplayingInCartPage() {
//        page.homePage().verifyAddedProductsAreDisplayingInCartPage();
//    }
//
//    @Then("^the listed (room scenes|products|orders) should match with (.*)$")
//    public void validateSearchResult(String type, String searchTerm) {
//        page.homePage().validateSearchResultAreMatchingWithSearch(type, searchTerm);
//    }

    @Then("^the listed room scenes downloaded in the new tab$")
    public void theListedRoomScenesDownloadedInTheNewTab() {
        page.loginPage().verifyDownlodTabIsDisplayed();
    }

    @Then("^the selected categories should be displayed in the (.*) page$")
    public void checkSelectedCategoriesAreDisplaying(String pageType) {
        page.loginPage().verifySelectedCategoriesAreDisplayed(pageType);
    }


}