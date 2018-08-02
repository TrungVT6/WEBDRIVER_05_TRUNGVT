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
	String baseURL2 = "https://material.angular.io/components/select/examples ";

    public void selectCustomDropdownList(String dropdown, String listitems,String valueitem) throws Exception {
	    WebElement dropdownElement = driver.findElement(By.xpath(dropdown));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
	    Thread.sleep(3000);
	    dropdownElement.click();
	    List<WebElement> allitems = driver.findElements(By.xpath(listitems));
	    wait.until(ExpectedConditions.visibilityOfAllElements(allitems));
	    for(WebElement item : allitems) {
	      //System.out.println(item.getText());
	      if(item.getText().equals(valueitem)) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
	        item.click();
	        break;
	      }
	    }
	  }	
  @BeforeClass
  public void beforeClass() {
  //driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    driver = new ChromeDriver();
    wait   = new WebDriverWait(driver, 30);
	driver.manage().window().maximize();
	  }
		  
  @Test
  public void TC_01_selectCustomDropdownList() throws Exception {
    driver.get(baseURL1);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    selectCustomDropdownList("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
    
    driver.get(baseURL2);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	selectCustomDropdownList("//mat-select[@id='mat-select-0']","//mat-option/span", "Pizza");
    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Pizza']")).isDisplayed());
    
    selectCustomDropdownList("//mat-select[@id='mat-select-5']","//mat-option/span", "Idaho");
    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Idaho']")).isDisplayed());
}
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
