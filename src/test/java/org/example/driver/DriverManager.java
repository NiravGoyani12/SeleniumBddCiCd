package org.example.driver;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverManager {

  public static WebDriver driver;
  String browser = "chrome";
  String basUrl = "https://demo.nopcommerce.com/";


  public void openBrowser() {
    switch (browser) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        /*Run on Remote CI - CD */
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);

        /*Run Locally  */
//        driver = new ChromeDriver();
        break;
      case "edge":
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        break;
      default:
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
  }

  public void maxBrowser() {
    driver.manage().window().maximize();
  }

  public void applyImlicitWait() {
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  public void closeBrowser() {
    driver.quit();
  }

  public void sleepBrowser(int ms) throws InterruptedException {
    Thread.sleep(ms);
  }

  public void goToUrl() {
    driver.get(basUrl);
  }

  public String getUrl() {
    return driver.getCurrentUrl();
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public WebElement waitUntilElementIsClickable(WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitForElementVisibility(WebElement element, int timeout, String failureMessage) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(failureMessage);
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public void scrollTo(WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public void takeScreenshot(Scenario scenario) {

    byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    scenario.embed(screenShot, "image/png");
//take a screen shot
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    try {
      FileUtils.copyFile(scrFile, new File("/Users/khuntn01/Desktop/screanshotTests/Error.jpg"));
    } catch (IOException e) {
// TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
