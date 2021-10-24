package com.project.autotest.fun.pages.drivers;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.assertTrue;

import com.opencsv.CSVWriter;
import com.project.autotest.fun.pages.*;
import com.project.autotest.fun.utility.*;
import cucumber.api.Scenario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class HomePage extends SeleniumUtil {

  private static final String testEnvironment = System.getenv("env");
  private static String reset_password;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public static String getUrl() {
    String url = null;

    if (isBlank(testEnvironment)) {
      url = FunTestUtil.getProperty("default.environment.url");
    } else if (containsIgnoreCase(testEnvironment, "default")) {
      url = FunTestUtil.getProperty("default.environment.url");
    } else if (containsIgnoreCase(testEnvironment, "test")) {
      url = FunTestUtil.getProperty("default.environment.url");
    } else if (containsIgnoreCase(testEnvironment, "dev")) {
      url = FunTestUtil.getProperty("default.environment.url");
    }

    assertTrue("Invalid test environment is selected, Please select a valid environment",
        isNotBlank(url));
    return url;
  }

  public void testTearDown(Scenario scenario) {
    if (scenario.isFailed()) {
      try {
        Screenshot takeScreenshot = new AShot()
            .shootingStrategy(ShootingStrategies.viewportPasting(100))
            .takeScreenshot(driver);
        ByteArrayOutputStream screenshotAsByteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(takeScreenshot.getImage(), "png", screenshotAsByteArrayOutputStream);
        scenario.embed(screenshotAsByteArrayOutputStream.toByteArray(), "image/png");
        String errorPage = "";
        errorPage = errorPage
            .concat("<a href=" + driver.getCurrentUrl() + ">This is a link to the failed page</a>");
        scenario.embed(errorPage.getBytes(), "text/html");
      } catch (Exception wde) {
        System.out.println(wde);
      }
    }
    try {
      driver.manage().deleteAllCookies();
      driver.quit();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void goToSite(String site) {
    Dimension d = new Dimension(1280, 800);
    driver.manage().window().maximize();
    driver.get(site);
  }

  public void enterUser(String user) throws InterruptedException {
    findElementByKey("LOGIN_TEXT_SEND").sendKeys(user);
  }

  public void enterPass(String password) {
    findElementByKey("PASS_TEXT_SEND").sendKeys(password);
  }

  public void search(String product) {
    findElementByKey("SEARCH").sendKeys(product);
  }

  public void saveCSV(String attributes,Integer price) throws IOException, InterruptedException {
    Thread.sleep(2000);
    File file = new File("report.csv");

    FileWriter outputfile = new FileWriter(file);

    CSVWriter writer = new CSVWriter(outputfile);

    //split attribites into string
    String[] attri_split = attributes.split(",");
    writer.writeNext(attri_split);

    int productcount = findElementsByKey(attri_split[0]).size();
    List<String> list = new ArrayList<>();

    for (int i = 0; i < productcount; i++) {
      list.clear();
      for (String attrbute : attri_split) {
              if(Integer.parseInt(findElementsByKey("PRODUCT PRICE LINK").get(i).getText().trim().replaceAll("[^\\d.]", "")) <= price)
              list.add(findElementsByKey(attrbute).get(i).getText());
          }
      String[] stringArray = list.toArray(new String[0]);
      writer.writeNext(stringArray);
    }
    writer.close();
  }
}