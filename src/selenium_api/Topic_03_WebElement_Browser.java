package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_WebElement_Browser {
	WebDriver driver;
	String baseURL = "http://daominhdam.890m.com/";

	
public boolean isControlDisplayed(WebElement element) {
	return element.isDisplayed();
}	

public void isControlEnabled(WebElement element) {
	String elementName = element.toString();
	if(element.isEnabled()) {
		System.out.println(elementName+" is enabled");
	}
	else {
		System.out.println(elementName+" is disabled");
	}
}

	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
//		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get(baseURL);
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
  @Test
  public void TC_01_IsDisplayed() {
  WebElement emailTextbox = driver.findElement(By.id("mail"));
  WebElement ageUnder18 = driver.findElement(By.id("under_18"));
  WebElement educationTextArea = driver.findElement(By.id("edu"));
  
  Assert.assertTrue(isControlDisplayed(emailTextbox));
  Assert.assertTrue(isControlDisplayed(ageUnder18));
  Assert.assertTrue(isControlDisplayed(educationTextArea));
  
  if(isControlDisplayed(emailTextbox) && isControlDisplayed(educationTextArea)) {
	  emailTextbox.sendKeys("Automation Testing");
	  educationTextArea.sendKeys("Automation Testing");
  }
	  
  if(isControlDisplayed(ageUnder18)) {
	  ageUnder18.click();
  }
  
}
  
  @Test
  public void TC_02_IsEnabled() { 
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		WebElement ageUnder18 = driver.findElement(By.id("under_18"));
		WebElement educationTextArea = driver.findElement(By.id("edu"));
		WebElement passwordTextbox = driver.findElement(By.id("password"));
		WebElement ageLastRadioButton = driver.findElement(By.id("radio-disabled"));
		WebElement biographyTextArea = driver.findElement(By.id("bio"));
		WebElement jobRole01 = driver.findElement(By.id("job1"));
		WebElement jobRole02 = driver.findElement(By.id("job2"));
		WebElement developmentCheckbox = driver.findElement(By.id("development"));
		WebElement checkboxIsDisabled = driver.findElement(By.id("check-disbaled"));
		WebElement buttonIsEnabled = driver.findElement(By.id("button-enabled"));
		WebElement buttonIsDisnabled = driver.findElement(By.id("button-disabled"));
		WebElement slide01 = driver.findElement(By.id("slider-1"));
		WebElement slide02 = driver.findElement(By.id("slider-2"));
	  
	  isControlEnabled(emailTextbox);
	  isControlEnabled(ageUnder18);
	  isControlEnabled(educationTextArea);
	  isControlEnabled(passwordTextbox);
	  isControlEnabled(ageLastRadioButton);
	  isControlEnabled(biographyTextArea);
	  isControlEnabled(jobRole01);
	  isControlEnabled(jobRole02);
	  isControlEnabled(developmentCheckbox);
	  isControlEnabled(checkboxIsDisabled);
	  isControlEnabled(buttonIsEnabled);
	  isControlEnabled(buttonIsDisnabled);
	  isControlEnabled(slide01);
	  isControlEnabled(slide02);
  }

  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
