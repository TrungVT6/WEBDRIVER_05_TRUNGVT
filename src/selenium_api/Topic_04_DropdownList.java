package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_DropdownList {
	WebDriver driver;
	String baseURL = "http://daominhdam.890m.com/";
	String value1  = "Automation Tester";
	String value2  = "Manual Tester";
	String value3  = "Mobile Tester";
  
	  @BeforeClass
	  public void beforeClass() {
//		  driver = new FirefoxDriver();
		  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get(baseURL);
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
  @Test
  public void TC_01_DropdownList() throws Exception {
  Select jobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']"))); 
  
  jobRole.isMultiple();
  if(jobRole.isMultiple()) {
	  System.out.println("It supports multiple choice");
  }else {
	  System.out.println("It does not support multiple choice");
  }
  
  jobRole.selectByVisibleText(value1);
  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), value1);
  Thread.sleep(3000);
  
  jobRole.selectByValue("manual");
  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), value2);
  Thread.sleep(3000);
  
  jobRole.selectByIndex(3);
  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), value3);
  Thread.sleep(3000);
  
  int NumberItems = jobRole.getOptions().size();
  Assert.assertEquals(NumberItems, 5);
  System.out.println("DropdownList has "+ NumberItems + " items" );
}
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
