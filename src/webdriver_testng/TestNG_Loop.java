package webdriver_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class TestNG_Loop {
 WebDriver driver;
  
  @Parameters("browser")
  @BeforeMethod
  public void beforeMethod(String browserName) {
    if(browserName.equals("chrome")) {
      System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
      driver = new ChromeDriver();
    } else if(browserName.equals("firefox")) {
      driver = new FirefoxDriver();
    }
    driver.get("http://live.guru99.com/index.php/customer/account/login/");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test(invocationCount = 5)
  public void TC_Multibrowser() {
      driver.findElement(By.xpath("//*[@id='email']")).sendKeys("automationvalid_01@gmail.com");
      driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
      driver.findElement(By.xpath("//*[@id='send2']")).click();
      Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
  }

  @AfterMethod
  public void afterMethod() {
    driver.quit();
}
}