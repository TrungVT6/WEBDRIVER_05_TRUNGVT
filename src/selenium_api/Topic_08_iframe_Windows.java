package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_iframe_Windows {
  WebDriver driver;
  WebDriverWait wait;
  String baseURL1 = "https://www.hdfcbank.com/";
  String baseURL2 = "http://daominhdam.890m.com/";

  @BeforeClass
  public void beforeClass() {
    // driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TC_01_iFrame() {
    // Step 1
    driver.get(baseURL1);

    // Step 2
    List<WebElement> iFrames =
        driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
    if (iFrames.size() > 0) {
      driver.switchTo().frame(iFrames.get(0));
      driver.findElement(By.xpath("//div[@id='div-close']")).click();
      System.out.println("Close Popup");
      driver.switchTo().defaultContent();
    }

    // Step 3
    WebElement WhatAreYouLookingForFrame =
        driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
    driver.switchTo().frame(WhatAreYouLookingForFrame);
    String Text = driver.findElement(By.xpath("//span[@id ='messageText']")).getText();
    Assert.assertEquals(Text, "What are you looking for?");
    System.out.println(Text);
    driver.switchTo().defaultContent();

    // Step 4
    WebElement bannerImageIframe =
        driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
    driver.switchTo().frame(bannerImageIframe);
    By bannerImageXpath = By.xpath("//div[@id='productcontainer']/div");
    List<WebElement> bannerImage = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bannerImageXpath));
    int bannerImageNumber = bannerImage.size();
    Assert.assertEquals(bannerImageNumber, 6);
    driver.switchTo().defaultContent();

    // Step 5
    WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBannerWrap']"));
    Assert.assertTrue(flipBanner.isDisplayed());
    List<WebElement> Banners =
        driver.findElements(By.xpath("//div[@class='flipBannerWrap']//img[@class='front icon']"));
    int numberBanners = Banners.size();
    Assert.assertEquals(numberBanners, 8);
    int i = 0;
    for (WebElement banner : Banners) {
      Assert.assertTrue(banner.isDisplayed());
      i++;
      System.out.println("Image [" + i + "] is displayed");
    }
  }

  @Test
  public void TC_02_2Windows() {
    // Step 1
    driver.get(baseURL2);

    // Step 2
    String parentWindow = driver.getWindowHandle();
    WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click Here']"));
    clickHere.click();
    switchToChildWindow(parentWindow);

    // Step 3
    String currentTitle = driver.getTitle();
    Assert.assertEquals(currentTitle, "Google");
    System.out.println("Title of new window is " + currentTitle);

    // Step 4
    closeAllWithoutParent(parentWindow);

    // Step 5
    driver.switchTo().window(parentWindow);
  }

  @Test
  public void TC_03_MultipleWindows() {
    // Step 1
    driver.get(baseURL1);

    // Step 2
    List<WebElement> iFrames =
        driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
    if (iFrames.size() > 0) {
      driver.switchTo().frame(iFrames.get(0));
      driver.findElement(By.xpath("//div[@id='div-close']")).click();
      System.out.println("Close Popup");
      driver.switchTo().defaultContent();
    }

    // Step 3
    String parentWindow = driver.getWindowHandle();
    WebElement agriLink = driver.findElement(By.xpath("//a[text()= 'Agri']"));
    agriLink.click();
    switchToChildWindow(parentWindow);

    // Step 4
    WebElement accDetails = driver.findElement(By.xpath("//p[text()='Account Details']"));
    accDetails.click();
    switchToWindowByTitle("Welcome to HDFC Bank NetBanking");

    // Step 5
    WebElement footer = driver.findElement(By.xpath("//frame[@name='footer']"));
    driver.switchTo().frame(footer);
    //WebElement privacyPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
    WebElement privacyPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
    wait.until(ExpectedConditions.visibilityOf(privacyPolicy));
    privacyPolicy.click();
    switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");

    // Step 6
    WebElement CSR = driver.findElement(By.xpath("//a[text()='CSR']"));
    CSR.click();

    // Step 7
    driver.switchTo().window(parentWindow);

    // Step 8
    closeAllWithoutParent(parentWindow);

  }

  public void switchToChildWindow(String parent) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindow : allWindows) {
      if (!runWindow.equals(parent)) {
        driver.switchTo().window(runWindow);
        break;
      }
    }
  }

  public void closeAllWithoutParent(String parent) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindows : allWindows) {
      if (!runWindows.equals(parent)) {
        driver.switchTo().window(runWindows);
        driver.close();
      }
    }
  }

  public void switchToWindowByTitle(String title) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindow : allWindows) {
      driver.switchTo().window(runWindow);
      String currentWindow = driver.getTitle();
      if (currentWindow.equals(title)) {
        break;
      }
    }
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }

}
