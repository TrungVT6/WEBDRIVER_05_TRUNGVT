package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_UserInteractions {
  WebDriver driver;
  String baseURL1 = "http://daominhdam.890m.com/";
  String baseURL2 = "http://www.myntra.com/";
  String baseURL3 = "http://jqueryui.com/resources/demos/selectable/display-grid.html";
  String baseURL4 = " http://www.seleniumlearn.com/double-click";
  String baseURL5 = "http://swisnl.github.io/jQuery-contextMenu/demo.html";
  String baseURL6 = "http://demos.telerik.com/kendo-ui/dragdrop/angular";

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TC_01_HoverMouse() {
    driver.get(baseURL1);
    WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
    Actions action = new Actions(driver);
    action.moveToElement(hoverText).perform();
    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).getText(),
        "Hooray!");

    driver.get(baseURL2);
    WebElement menuLogin = driver
        .findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-user']"));
    action.moveToElement(menuLogin).perform();
    action
        .click(driver.findElement(By.xpath("//a[@class='desktop-linkButton' and text()='login']")))
        .perform();
    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
  }

  @Test
  public void TC_02_ClickAndHold() {
    driver.get(baseURL3);
    List<WebElement> selectable =
        driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
    Actions action = new Actions(driver);
    action.clickAndHold(selectable.get(0)).moveToElement(selectable.get(3)).release().perform();
    Assert.assertEquals(driver
        .findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(),
        4);
  }

  @Test
  public void TC_03_DoubleClick() {
    driver.get(baseURL4);
    WebElement doubleClickText =
        driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
    Actions action = new Actions(driver);
    action.doubleClick(doubleClickText).perform();

    Alert alert = driver.switchTo().alert();
    Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
    alert.accept();

  }

  @Test
  public void TC_04_RighClick() {
    driver.get(baseURL5);
    WebElement rightClickMe = driver.findElement(By.xpath("//span[text()='right click me']"));
    Actions action = new Actions(driver);
    action.contextClick(rightClickMe).perform();
    WebElement QuitBefore =
        driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
    action.moveToElement(QuitBefore).perform();
    Assert.assertTrue(driver.findElement(By.xpath(
        "//li[contains(@class,'context-menu-icon-quit') and contains(@class, 'context-menu-visible') and contains(@class, 'context-menu-hover')]"))
        .isDisplayed());
    action.click(QuitBefore).perform();
    Alert alert = driver.switchTo().alert();
    Assert.assertEquals(alert.getText(), "clicked: quit");
    alert.accept();
  }

  @Test
  public void TC_05_DragAndDrop() {
    driver.get(baseURL6);
    WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
    WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
    Assert.assertEquals(targetElement.getText(), "Drag the small circle here.");
    Actions action = new Actions(driver);
    action.dragAndDrop(sourceElement, targetElement).perform();
    Assert.assertEquals(targetElement.getText(), "You did great!");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }

}
