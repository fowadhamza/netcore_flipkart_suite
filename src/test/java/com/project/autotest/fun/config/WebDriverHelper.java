package com.project.autotest.fun.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class WebDriverHelper {

    private static String getBrowser;
    protected WebDriver driver;

    public WebDriver getDriver() {

        getBrowser = System.getProperty("browser", "chrome");
        if (getBrowser.isEmpty()) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(getOption());

        } else if (getBrowser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(getOption());

        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(getOption());
        }
        return driver;
    }

    private FirefoxProfile getProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.http.phishy-userpass-length", 255);
        return profile;
    }

    private ChromeOptions getOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        return options;
    }
}