package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Button_Radio_Checkbox_Alert {
	WebDriver driver;
	String baseURL1 = "http://live.guru99.com/";
	String baseURL2 = "http://demos.telerik.com/kendo-ui/styling/checkboxes";
	String baseURL3 = "http://demos.telerik.com/kendo-ui/styling/radios";
	String baseURL4 = "http://daominhdam.890m.com/";

	public void clickElementByJavascript(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}

	public boolean isElementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public void unCheckTheCheckbox(String locator) {
		if (isElementSelected(locator)) {
			clickElementByJavascript(locator);
		}
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Button() {
		driver.get(baseURL1);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		clickElementByJavascript("//a[@title='Create an Account']");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-create']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_CustomCheckbox() {
		driver.get(baseURL2);
		String dualZoneAirConditioning = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		clickElementByJavascript(dualZoneAirConditioning);
		Assert.assertTrue(isElementSelected(dualZoneAirConditioning));

		unCheckTheCheckbox(dualZoneAirConditioning);
		Assert.assertFalse(isElementSelected(dualZoneAirConditioning));
	}

	@Test
	public void TC_03_CustomRadioButton() {
		driver.get(baseURL3);
		String Petrol147kW = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		clickElementByJavascript(Petrol147kW);
		Assert.assertTrue(isElementSelected(Petrol147kW));

		if(!isElementSelected(Petrol147kW)) {
			clickElementByJavascript(Petrol147kW);
		}
	}

	@Test
	public void TC_04_Alert() {
		driver.get(baseURL4);
		clickElementByJavascript("//button[@onclick='jsAlert()']");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You clicked an alert successfully");
	}

	@Test
	public void TC_05_AlertConfirm() {
		driver.get(baseURL4);
		clickElementByJavascript("//button[@onclick='jsConfirm()']");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_06_AlertPrompt() throws Exception {
		driver.get(baseURL4);
		String text = "Automation";

		clickElementByJavascript("//button[@onclick='jsPrompt()']");
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		alert.sendKeys(text);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + text);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
