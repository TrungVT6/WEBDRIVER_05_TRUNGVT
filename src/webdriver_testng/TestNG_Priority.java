package webdriver_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class TestNG_Priority {
WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    driver.get("http://live.guru99.com/index.php/customer/account/login/");
  }

  
  @Test(priority = 0)
  public void TC_01() {
  System.out.println("Testcase 01");
  }
 
  @Test(priority = 3)
  public void TC_02() {
    System.out.println("Testcase 02");
  }
  
  @Test(priority = 2)
  public void TC_03() {
    System.out.println("Testcase 03");
  }
  
  @Test(priority = 1)
  public void TC_04() {
    System.out.println("Testcase 04");
  }
  
  @Test(priority = 4)
  public void TC_05() {
    System.out.println("Testcase 05");
  }
  
  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
