package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css_Locator {
	WebDriver driver;
	
public int randomEmail() {
	Random random = new Random();
	int number = random.nextInt(999999);
	return number;
}

	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
	  }
	
  @Test
  public void TC_01_LoginWithUserPassEmpty() {
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.id("send2")).click();
	  
	  String usenameEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(usenameEmptyMessage, "This is a required field.");
	  
	  String passwordEmptyMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(passwordEmptyMessage, "This is a required field.");  
  }
  
  @Test
  public void TC_02_LoginWithEmailInvalid() {
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.id("send2")).click();
	  
	  String usernameInvalidMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(usernameInvalidMessage, "Please enter a valid email address. For example johndoe@domain.com.");
  }
  
  @Test
  public void TC_03_LoginWithPassworkLessThan6Characters() {
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  driver.findElement(By.id("send2")).click();
	  
	  String passwordIncorectMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(passwordIncorectMessage, "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  @Test
  public void TC_04_CreateAnAccount() throws InterruptedException {
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.xpath("//*[@id='login-form']//a[@title='Create an Account']")).click();
	  driver.findElement(By.id("firstname")).sendKeys("Nguyen");
	  driver.findElement(By.id("middlename")).sendKeys("Van");
	  driver.findElement(By.id("lastname")).sendKeys("A");
	  driver.findElement(By.id("email_address")).sendKeys("Automation"+ randomEmail() +"@gmail.com" );
	  driver.findElement(By.id("password")).sendKeys("12345678");
	  driver.findElement(By.id("confirmation")).sendKeys("12345678");
	  driver.findElement(By.xpath("//*[@id='form-validate']//button[@class='button']")).click();
	  
	  String registerSuccessMessage = driver.findElement(By.xpath(".//*[@id='top']//span[contains(text(), 'Thank you')]")).getText();
	  Assert.assertEquals(registerSuccessMessage, "Thank you for registering with Main Website Store.");
	  
	  driver.findElement(By.xpath("//*[@id='header']//a[@class='skip-link skip-account']")).click();
	  driver.findElement(By.xpath(".//*[@id='header-account']//a[@title='Log Out']")).click();
	  
	  Thread.sleep(6000);
	  String checkHomepage = driver.getCurrentUrl();
	  Assert.assertEquals(checkHomepage, "http://live.guru99.com/index.php/");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
