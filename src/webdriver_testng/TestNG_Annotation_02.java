package webdriver_testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_Annotation_02 {
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
    
  }

  @BeforeMethod
  public void beforeMethod() {
    driver = new FirefoxDriver();
    driver.get("http://live.guru99.com/index.php/customer/account/login/");
  }
  
  @Test
  public void TC_01_CheckUrl() {
   String loginURL = driver.getCurrentUrl();
   Assert.assertEquals(loginURL, "http://live.guru99.com/index.php/customer/account/login/");
  }
 
  @Test
  public void TC_02_CheckTitle() {
    String loginTitle = driver.getTitle();
    Assert.assertEquals(loginTitle, "Customer Login");
  }
  
  @AfterMethod
  public void afterMethod() {
    driver.quit();
  }

  @AfterClass
  public void afterClass() {
    
  }

}
