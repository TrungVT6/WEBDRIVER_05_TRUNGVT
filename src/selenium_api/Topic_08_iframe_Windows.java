package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_iframe_Windows {
	WebDriver driver;
	WebDriverWait wait;
	String baseURL1 = "http://www.hdfcbank.com/";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_iFrame() {
	//Step 1
	 driver.get(baseURL1);
	 
	//Step 2
	 List<WebElement> iFrames = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	 if(iFrames.size()>0) {
		driver.switchTo().frame(iFrames.get(0));
		driver.findElement(By.xpath("//div[@id='div-close']")).click();
		System.out.println("Close Popup");
		driver.switchTo().defaultContent();
	 }
	 
	 //Step 3
	 WebElement WhatAreYouLookingForFrame = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	 driver.switchTo().frame(WhatAreYouLookingForFrame);
	 String Text = driver.findElement(By.xpath("//span[@id ='messageText']")).getText();
	 Assert.assertEquals(Text, "What are you looking for?");
	 System.out.println(Text);
	 driver.switchTo().defaultContent();
	 
	 //Step 4
	 WebElement bannerImageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	 driver.switchTo().frame(bannerImageIframe);
	 By bannerImageXpath = By.xpath("//div[@id='productcontainer']//img");
	 List<WebElement> bannerImage = driver.findElements(bannerImageXpath);
	 int bannerImageNumber = bannerImage.size();
	 Assert.assertEquals(bannerImageNumber, 6);
     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImageXpath));
	 driver.switchTo().defaultContent();
	 
	 //Step 5
	 WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBannerWrap']"));
	 Assert.assertTrue(flipBanner.isDisplayed());
	 List<WebElement> Banners = driver.findElements(By.xpath("//div[@class='flipBannerWrap']//img[@class='front icon']"));
	 int numberBanners = Banners.size();
	 Assert.assertEquals(numberBanners, 8);
	 int i = 0;
	 for(WebElement banner : Banners) {
		 Assert.assertTrue(banner.isDisplayed());
		 i++;
		 System.out.println("Image ["+ i +"] is displayed");
	 }
	 
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
