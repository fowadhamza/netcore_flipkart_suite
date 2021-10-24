package com.project.autotest.fun.pages.drivers;

import com.project.autotest.fun.pages.SeleniumUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.project.autotest.fun.utility.XpathConstants.getXpathForKey;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonSeleniumActions extends SeleniumUtil {

    public static String       numberOfItemsInCart;
    public static String       heightDimensionInPdp;
    public static String       orderIdInMyOrders;
    public static List<String> addedProductList   = null;
    public static List<String> addedRoomsList     = null;
    public static List<String> selectedCategories = null;

    public CommonSeleniumActions(WebDriver driver) {
        super(driver);
        addedProductList = null;
        numberOfItemsInCart = null;
        addedRoomsList = null;
    }

    public void verifyContentInTextBoxMatchesWithExpected(String xpathKey, String expectedValue) {
        String value = findElementByKey(xpathKey).getAttribute("value");
        assertTrue("Content displayed(" + value + ") and expected(" + expectedValue + ") are not matching.",
            equalsIgnoreCase(value.trim(), expectedValue));
    }

    public void verifyContentMatchesWithExpected(String xpathKey, String expectedValue) {
        String value = findElementByKey(xpathKey).getText().trim();
        assertTrue("Content displayed(" + value + ") and expected(" + expectedValue + ") are not matching.",
            equalsIgnoreCase(value.trim(), expectedValue));
    }

    public void verifyStringContainsTheExpectedValue(String xpathKey, String expectedValue) {
        String value = findElementByKey(xpathKey).getText().trim();
        assertTrue("Content displayed(" + value + ") and expected(" + expectedValue + ") are not matching.",
            containsIgnoreCase(value.trim(), expectedValue));
    }

    public String generateRandomIds(String format, String type) {
        StringBuffer sb = new StringBuffer();
        String randomString = null;

        if (containsIgnoreCase(type, "string")) {
            randomString = generateRandomString();
        } else {
            randomString = generateRandomNumbers();
        }

        if (containsIgnoreCase(format, "email")) {
            sb.append("automationuser");
            sb.append(randomString);
            sb.append("@mailinator.com");
        } else {
            sb.append(randomString);
        }
        return sb.toString();
    }

    public String generateRandomNumbers() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = dateFormat.format(new Date());
        return dateString;
    }

    public String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public void verifyTheElementState(String xpathKey, String state) {
        if (containsIgnoreCase(state, "disabled")) {
            assertFalse(xpathKey + " - element is not in disabled state", findElementByKey(xpathKey).isEnabled());
        } else {
            assertTrue(xpathKey + " - element is not in enabled state", findElementByKey(xpathKey).isEnabled());
        }
    }

    public void waitAndClick(String xpathKey) throws InterruptedException {
        Thread.sleep(5000);
        clickOnElement(xpathKey, 5);
    }




}