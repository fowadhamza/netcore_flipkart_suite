package com.project.autotest.fun.pages;

import com.project.autotest.fun.config.WebDriverHelper;
import com.project.autotest.fun.pages.drivers.*;

import org.openqa.selenium.WebDriver;

public class Pages {

    protected static WebDriver driver;
    private static HomePage homePage;
    private static LoginPage loginPage;
    private static CommonSeleniumActions commonSeleniumActions;
    //private static GmailApiActions gmailApiActions;

    public Pages() {
        homePage = null;
        loginPage = null;
        commonSeleniumActions = null;
        driver = new WebDriverHelper().getDriver();
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public CommonSeleniumActions commonSeleniumActions() {
        if (commonSeleniumActions == null) {
            commonSeleniumActions = new CommonSeleniumActions(driver);
        }
        return commonSeleniumActions;
    }


}
