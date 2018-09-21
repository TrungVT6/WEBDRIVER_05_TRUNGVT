package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_Wait {
  WebDriver driver;
  WebDriverWait wait;
  String baseURL1 = "http://the-internet.herokuapp.com/dynamic_loading/2";
  String baseURL2 = "http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx";
  String baseURL3 = "https://daominhdam.github.io/fluent-wait/";

  @BeforeClass
  public void beforeClass() {
    // driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().window().maximize();
  
  }

  
  public void TC_01_ImplicitWait() {
  //Step 1  
  driver.get(baseURL1);
  
  //Step 2
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  
  //Step 3
  WebElement StartButton = driver.findElement(By.xpath("//div[@id='start']/button"));
  StartButton.click();
  
  //Step 4
  WebElement resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
  
  //Step 5
  Assert.assertEquals(resultText.getText(), "Hello World!");
  }
  
  @Test
  public void TC_02_ExplicitWait() {
    //Step 1  
    driver.get(baseURL2);
    
    //Step 2
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
    
    //Step 3
   WebElement displayedDate = driver.findElement(By.xpath("//span[@class='label']"));
   String DateBefore = displayedDate.getText();
   Assert.assertEquals(DateBefore, "No Selected Dates to display.");
    
    //Step 4
   driver.findElement(By.xpath("//td[contains(@title, '04, 2018')]")).click();
    
    //Step 5
   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
   
   //Step 6
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@class,'rcSelected')]/a[text()='4']")));

   //Step 7
   WebElement displayedDate1 = driver.findElement(By.xpath("//span[@class='label']"));
   String DateAfter = displayedDate1.getText();
   Assert.assertEquals(DateAfter, "Tuesday, September 04, 2018");
   
  }
  
  @AfterClass
  public void afterClass() {
    //driver.quit();
  }
}