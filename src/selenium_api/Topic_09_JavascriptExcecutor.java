package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_JavascriptExcecutor {
  WebDriver driver;
  String baseURL1 = "http://live.guru99.com/";
  String baseURL2 = "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled";

  public void highlightElement(WebElement element) {
  JavascriptExecutor js = (JavascriptExecutor)driver;
  js.executeScript("arguments[0].style.border='6px groove red'", element);
  try {
    Thread.sleep(2000);
  } catch (InterruptedException e) {
  }
  }
  
  public Object executeForBrowser(String javascript) {
    JavascriptExecutor js = (JavascriptExecutor)driver;
    return js.executeScript(javascript);
  }
  
  public Object clickElement (WebElement element) {
  JavascriptExecutor js = (JavascriptExecutor)driver;
  return js.executeScript("arguments[0].click();", element);
}

  public Object navigateToAnyUrl(String Url) {
    JavascriptExecutor js = (JavascriptExecutor)driver;
    return js.executeScript("window.location ='" + Url + "'");
  }
  
  public Object scrollToBottom() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
  }
  
  public Object removeAttributeInDOM(WebElement element, String attribute) {
     JavascriptExecutor js = (JavascriptExecutor) driver;
     return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
  }
     
  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    //System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    //driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TC_01_JavascriptExcecutor() {
    //Step 1
    driver.get(baseURL1);
    
    //Step 2
    String domain = (String)executeForBrowser("return document.domain");
    Assert.assertEquals(domain, "live.guru99.com");
    
    //Step 3
    String UrL = (String)executeForBrowser("return document.URL");
    Assert.assertEquals(UrL, "http://live.guru99.com/");
    
    //Step 4
    WebElement mobilePage = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
    highlightElement(mobilePage);
    clickElement(mobilePage);
    
    //Step 5
    WebElement AddSamsungGalaxyToCart = driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div//button"));
    highlightElement(AddSamsungGalaxyToCart);
    clickElement(AddSamsungGalaxyToCart);
    
    //Step 6
    String samsungAddedMsg = (String)executeForBrowser("return document.documentElement.innerText;");
    Assert.assertTrue(samsungAddedMsg.contains("Samsung Galaxy was added to your shopping cart."));
    
    //Step 7
    WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
    highlightElement(privacyLink);
    clickElement(privacyLink);
    String privacyTitle = (String)executeForBrowser("return document.title");
    Assert.assertEquals(privacyTitle, "Privacy Policy");
    
    //Step 8
    scrollToBottom();
    
    //Step 9
    WebElement wishlistTableContent = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']//following-sibling::td[text()='The number of items in your Wishlist.']"));
    highlightElement(wishlistTableContent);
    Assert.assertTrue(wishlistTableContent.isDisplayed());
    
    //Step 10
    navigateToAnyUrl("http://demo.guru99.com/v4/");
    String newDomain = (String)executeForBrowser("return document.domain");
    Assert.assertEquals(newDomain, "demo.guru99.com");
  }

  @Test
  public void TC_02_removeAttribute() {
    //Step 1
    driver.get(baseURL2);
    String firstName = "Automation";
    String lastName  = "Testing";
    
    //Step 2
    WebElement resultFrame = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
    driver.switchTo().frame(resultFrame);
    WebElement FirstName = driver.findElement(By.xpath("//input[@name='fname']"));
    WebElement LastName = driver.findElement(By.xpath("//input[@name='lname']"));
    removeAttributeInDOM(LastName, "disabled");
    
    //Step 3
    FirstName.sendKeys(firstName);
    LastName.sendKeys(lastName);
    
    //Step 4
    WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
    clickElement(submitBtn);
    
    //Step 5
   String input = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
   Assert.assertTrue(input.contains(firstName) && input.contains(lastName));
  }
  
  @AfterClass
  public void afterClass() {
    driver.quit();
  }

}
