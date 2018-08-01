package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_CustomDropdownList {
	WebDriver driver;
	WebDriverWait wait;
	String baseURL1 = "http://jqueryui.com/resources/demos/selectmenu/default.html";

	  @BeforeClass
	  public void beforeClass() {
//		  driver = new FirefoxDriver();
		  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  wait   = new WebDriverWait(driver, 30);
		  driver.manage().window().maximize();
		  driver.get(baseURL1);
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 
	  }
	
	  public void selectCustomDropdownList(String dropdown, String listitems,String valueitem) {
	    driver.findElement(By.xpath(dropdown)).click();
	    List<WebElement> allitems = driver.findElements(By.xpath(listitems));
	    wait.until(ExpectedConditions.visibilityOfAllElements(allitems));
	    for(WebElement item : allitems) {
	      System.out.println(item.getText());
	      if(item.getText().equals(valueitem)) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
	        item.click();
	        break;
	      }
	    }
	  }
	    	  
  @Test
  public void TC_01_selectCustomDropdownList_jquery() {
   
    selectCustomDropdownList("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
}
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
