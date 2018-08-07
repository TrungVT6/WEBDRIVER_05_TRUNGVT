package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Button_Radio_Checkbox_Alert {
  WebDriver driver;
  String baseURL1 = "http://live.guru99.com/";
  String baseURL2 = "http://demos.telerik.com/kendo-ui/styling/checkboxes";
  String baseURL3 = "http://demos.telerik.com/kendo-ui/styling/radios";
  String baseURL4 = "http://daominhdam.890m.com/";

  public void clickElementByJavascript(String locator) {
    WebElement element = driver.findElement(By.xpath(locator));
    JavascriptExecutor je = (JavascriptExecutor) driver;
    je.executeScript("arguments[0].click();", element);
  }

  public boolean isElementSelected(String locator) {
    WebElement element = driver.findElement(By.xpath(locator));
    return element.isSelected();
  }

  public void unCheckTheCheckbox(String locator) {
    if (isElementSelected(locator)) {
      clickElementByJavascript(locator);
    }
  }

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    // System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    // driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TC_01_Button() {
    driver.get(baseURL1);
    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"))
        .isDisplayed());
    Assert.assertEquals(driver.getCurrentUrl(),
        "http://live.guru99.com/index.php/customer/account/login/");

    clickElementByJavascript("//a[@title='Create an Account']");
    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-create']")).isDisplayed());
    Assert.assertEquals(driver.getCurrentUrl(),
        "http://live.guru99.com/index.php/customer/account/create/");
  }

  public void TC_02_CustomCheckbox() {
    driver.get(baseURL2);
    String dualZoneAirConditioning = "//label[@text()='Dual-zone air conditioning']/preceding-sibling::input";
    clickElementByJavascript(dualZoneAirConditioning);
    Assert.assertTrue(isElementSelected(dualZoneAirConditioning));
    
    unCheckTheCheckbox(dualZoneAirConditioning);
    Assert.assertFalse(isElementSelected(dualZoneAirConditioning));
  }

  //public void TC_03_CustomRadioButton() {
  
  @AfterClass
  public void afterClass() {
    driver.quit();
  }

}
