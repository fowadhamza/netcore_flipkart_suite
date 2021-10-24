package com.project.autotest.fun.pages.drivers;

import com.project.autotest.fun.pages.SeleniumUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.junit.Assert.assertTrue;

public class LoginPage extends SeleniumUtil {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDownlodTabIsDisplayed() {
        Set<String> browserTabs = driver.getWindowHandles();
        assertTrue("New tab is not opened", browserTabs.size() > 1);
        driver.switchTo().window(browserTabs.toArray()[(browserTabs.size() - 1)].toString());
        String url = driver.getCurrentUrl();
        assertTrue("Image is not loaded.", (containsIgnoreCase(url, ".jpg") || containsIgnoreCase(url, ".png")));
    }

    public void verifySelectedCategoriesAreDisplayed(String pageType) {
        String xpathKey = null;
        if (containsIgnoreCase(pageType, "my account")) {
            xpathKey = "MY_ACCOUNT_CATEGORY_LABEL";
        } else if (containsIgnoreCase(pageType, "product overview")) {
            xpathKey = "PRODUCT_OVERVIEW_ALL_CATEGORY";
        } else if (containsIgnoreCase(pageType, "create new product")) {
            xpathKey = "CREATE_PRODUCT_ALL_CATEGORY";
        }

        List<WebElement> elements = findElementsByKey(xpathKey);
        List<String> displayedItems = new ArrayList<>();

        for (WebElement element : elements) {
            displayedItems.add(element.getText().trim());
        }

        int count = 0;
        int totalItems = CommonSeleniumActions.selectedCategories.size();
        for (String selectedCategory : CommonSeleniumActions.selectedCategories) {
            for (String displayedItem : displayedItems) {
                if (containsIgnoreCase(selectedCategory, displayedItem)) {
                    count++;
                    break;
                }
            }
        }

        assertEquals("Selected categories are not displayed in the page :-: " + pageType, totalItems, count);
    }


}