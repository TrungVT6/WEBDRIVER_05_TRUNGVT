package webdriver_testng;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class TestNG_DataProvider {
WebDriver driver;
  
  @BeforeMethod
  public void beforeMethod() {
    driver = new FirefoxDriver();
    driver.get("http://live.guru99.com/index.php/customer/account/login/");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test(dataProvider = "account")
  public void TC_LoginToSystem (String username, String password) {
    driver.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
    driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
    driver.findElement(By.xpath("//*[@id='send2']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
  }

  @DataProvider
  public Object[][] account() {
    return new Object[][] {
      new Object[] { "automationvalid_01@gmail.com", "111111" },
      new Object[] { "automationvalid_02@gmail.com", "111111" },
    };
  }

  @AfterMethod
  public void afterMethod() {
    driver.quit();
  }

}
