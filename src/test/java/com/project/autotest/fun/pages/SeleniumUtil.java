package com.project.autotest.fun.pages;

import static com.project.autotest.fun.utility.XpathConstants.getXpathForKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {


  protected final WebDriver driver;

  public SeleniumUtil(WebDriver driver) {
    this.driver = driver;
  }

  //SendKeys - For adding values in the text box
  //--------------------------------------------------------------------------------------------------------------------------------------------
  public void sendKeys(final String xpathKey, final String text) {
    final WebElement TextBox = findElementByKey(xpathKey);
    TextBox.click();
    TextBox.clear();
    TextBox.sendKeys(text);
  }

  public void sendKeys(final String xpathKey, final String text, int waitTimeout) {
    waitForElementToBeClickable(xpathKey, waitTimeout);
    final WebElement TextBox = findElementByKey(xpathKey);
    TextBox.clear();
    TextBox.sendKeys(text);
  }

  public void sendKeysAndSubmitForm(final String xpathKey, final String text, int waitTimeout) {
    waitForElementToBeClickable(xpathKey, waitTimeout);
    final WebElement TextBox = findElementByKey(xpathKey);
    TextBox.clear();
    TextBox.sendKeys(text);
    TextBox.submit();
  }
  //--------------------------------------------------------------------------------------------------------------------------------------------

  //Find Elements :- Methods are used for finding elements in the site
  //--------------------------------------------------------------------------------------------------------------------------------------------
  public WebElement findElementByXpath(final String xpath, final String xpathKey) {
    WebElement element = null;
    try {
      element = driver.findElement(By.xpath(xpath));
    } catch (StaleElementReferenceException ex) {
      assertTrue("STALE ELEMENT EXCEPTION: " + xpathKey, false);
    } catch (NoSuchElementException ex) {
      assertTrue("NO SUCH ELEMENT EXCEPTION: " + xpathKey, false);
    } catch (Exception ex) {
      assertTrue(ex.getMessage(), false);
    }
    return element;
  }

  public WebElement findElementByKey(final String xpathKey) {
    final String xpath = getXpathForKey(xpathKey);
    return findElementByXpath(xpath, xpathKey);
  }

  public List<WebElement> findElementsByKey(final String xpathKey) {
    final String xpath = getXpathForKey(xpathKey);
    List<WebElement> webElements = null;
    try {
      webElements = driver.findElements(By.xpath(xpath));
    } catch (StaleElementReferenceException ex) {

      try {

        webElements = driver.findElements(By.xpath(xpath));
      } catch (Exception ey) {
        assertTrue(ex.getMessage(), false);

      }


    } catch (Exception ex) {
      assertTrue(ex.getMessage(), false);
    }
    return webElements;
  }

  public List<WebElement> findElementsByKey(final String xpathKey, int waitTime) {
    final String xpath = getXpathForKey(xpathKey);
    driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    List<WebElement> webElements = null;
    try {
      webElements = driver.findElements(By.xpath(xpath));
    } catch (StaleElementReferenceException ex) {
      assertTrue("STALE ELEMENT EXCEPTION: " + xpathKey, false);
    } catch (Exception ex) {
      assertTrue(ex.getMessage(), false);
    }
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    return webElements;
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------

  //Method for clicking on a element
  //--------------------------------------------------------------------------------------------------------------------------------------------
  public void clickOnElement(final String xpathKey) {
    findElementByKey(xpathKey).click();
  }

  public void clickOnElement(final String xpathKey, int waitTimeout) {
    waitForElementToBeClickable(xpathKey, waitTimeout);
    findElementByKey(xpathKey).click();
  }

  public void clickOnNthElement(final String xpathKey, int index, int waitTimeout) {
    waitForElementToBeClickable(xpathKey, waitTimeout);
    --index;
    findElementsByKey(xpathKey).get(index).click();
  }

  public void clickOnElementAndWaitUntilInvisible(final String xpathKey, int waitTimeout) {
    waitForElementToBeClickable(xpathKey, waitTimeout);
    WebElement element = findElementByKey(xpathKey);
    element.click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.invisibilityOf(element));
  }

  public void clickonStaleElement(final String xpathKey, int waitTimeout) {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathKey)));
    waitForElementToBeClickable(xpathKey, waitTimeout);
    try {
      findElementByKey(xpathKey).click();
    } catch (StaleElementReferenceException e) {
      findElementByKey(xpathKey).click();
    }
  }
  //--------------------------------------------------------------------------------------------------------------------------------------------

  //Wait conditions
  //--------------------------------------------------------------------------------------------------------------------------------------------
  public void waitForElementToBeClickable(final String xpathKey, int waitTimeout) {
    final String xpath = getXpathForKey(xpathKey);
    try {
      WebDriverWait wait = new WebDriverWait(driver, waitTimeout);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    } catch (TimeoutException tx) {
      assertTrue("TIMEOUT EXCEPTION : Timeout waiting for the element=" + xpathKey, false);
    } catch (NoSuchElementException ex) {
      assertTrue("NO SUCH ELEMENT EXCEPTION: " + xpathKey + " is not found using the xpath", false);
    } catch (Exception ex) {
      assertTrue(ex.getMessage(), false);
    }
  }

  public void waitForPageLoad(int timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    wait.until(new Function<WebDriver, Object>() {
      public Boolean apply(WebDriver driver) {
        return String
            .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
            .equals("complete");
      }
    });
  }
  //--------------------------------------------------------------------------------------------------------------------------------------------

  //Validation conditions
  //--------------------------------------------------------------------------------------------------------------------------------------------
  public void verifyElementIsDisplayed(final String page, int waitTimeout) {
    String elementForValidation = getXpathForKey("VALIDATE_" + page);
    final String xpath = getXpathForKey(elementForValidation);
    String errorMessage = "Fails to verify " + page + " displayed ::";
    try {
      WebDriverWait wait = new WebDriverWait(driver, waitTimeout);
      System.out.println("xpath--->" + xpath);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
      assertTrue(errorMessage + " displayed :: " + elementForValidation + " - is not displayed",
          findElementByXpath(xpath, elementForValidation).isDisplayed());
    } catch (TimeoutException tx) {
      assertTrue(errorMessage + " :: TIMEOUT EXCEPTION : Timeout waiting for the element="
          + elementForValidation, false);
    } catch (NoSuchElementException ex) {
      assertTrue(errorMessage + " :: NO SUCH ELEMENT EXCEPTION: " + elementForValidation
          + " is not found using the xpath", false);
    } catch (Exception ex) {
      assertTrue(ex.getMessage(), false);
    }
  }


}