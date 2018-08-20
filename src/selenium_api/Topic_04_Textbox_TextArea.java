package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Textbox_TextArea {
  WebDriver driver;
  String baseURL = "http://demo.guru99.com/v4";
  By newCustomer = By.xpath("//ul[@class='menusubnav']//a[contains(text(), 'New Customer')]");
  By editCustomer = By.xpath("//a[contains(text(), 'Edit Customer')]");
  By customerName = By.xpath("//input[@name='name']");
  By genderMale = By.xpath("//input[@name='rad1' and @value='m']");
  By dateOfBirth = By.xpath("//input[@name='dob']");
  By address = By.xpath("//textarea[@name='addr']");
  By city = By.xpath("//input[@name='city']");
  By state = By.xpath("//input[@name='state']");
  By pin = By.xpath("//input[@name='pinno']");
  By mobileNumber = By.xpath("//input[@name='telephoneno']");
  By email = By.xpath("//input[@name='emailid']");
  By password = By.xpath("//input[@name='password']");
  By submitbutton = By.xpath("//input[@name='sub']");
  By customerID = By.xpath("//td[contains(text(), 'Customer ID')]/following-sibling::td");
  By displayedCity = By.xpath("//td[contains(text(), 'City')]/following-sibling::td");
  By displayedAddr = By.xpath("//td[contains(text(), 'Address')]/following-sibling::td");
  By cusIDTextbox = By.xpath("//input[@name = 'cusid']");
  By submitEdit = By.xpath("//input[@name='AccSubmit']");

  public int randomEmail() {
    Random random = new Random();
    int number = random.nextInt(999999) + 1;
    return number;
  }


  @BeforeClass
  public void beforeClass() {
    // driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get(baseURL);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void Textbox_TextArea() {
    driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr146136");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("yhahYha");
    driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

    Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");

    driver.findElement(newCustomer).click();
    driver.findElement(customerName).sendKeys("Trung Vu");
    driver.findElement(genderMale).click();
    driver.findElement(dateOfBirth).sendKeys("08021990");
    driver.findElement(address).sendKeys("No36 An Duong street");
    driver.findElement(city).sendKeys("Vietnam");
    driver.findElement(state).sendKeys("Hanoi");
    driver.findElement(pin).sendKeys("100000");
    driver.findElement(mobileNumber).sendKeys("01523654895");
    driver.findElement(email).sendKeys("Automation" + randomEmail() + "@gmail.com");
    driver.findElement(password).sendKeys("12345678");
    driver.findElement(submitbutton).click();

    // This step is to make webpage more stable after loading new page
    driver.getTitle();

    String ID = driver.findElement(customerID).getText();
    driver.findElement(editCustomer).click();
    driver.findElement(cusIDTextbox).sendKeys(ID);
    driver.findElement(submitEdit).click();

    String displayedCusName = driver.findElement(customerName).getAttribute("value");
    Assert.assertEquals(displayedCusName, "Trung Vu");

    String displayedAddress = driver.findElement(address).getText();
    Assert.assertEquals(displayedAddress, "No36 An Duong street");

    driver.findElement(address).clear();
    driver.findElement(address).sendKeys("36 An Duong street Tay Ho district");
    driver.findElement(city).clear();
    driver.findElement(city).sendKeys("Laos");
    driver.findElement(submitbutton).click();

    String currentCity = driver.findElement(displayedCity).getText();
    Assert.assertEquals(currentCity, "Laos");
    String currentAddress = driver.findElement(displayedAddr).getText();
    Assert.assertEquals(currentAddress, "36 An Duong street Tay Ho district");

  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }

}
