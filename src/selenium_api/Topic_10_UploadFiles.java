package selenium_api;

import org.testng.annotations.Test;
import com.sun.glass.events.KeyEvent;
import org.testng.annotations.BeforeClass;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_UploadFiles {
  WebDriver driver;
  WebDriverWait wait;
  String projectDirectory = System.getProperty("user.dir");
  String fileName1         = "upload1.jpg";
  String uploadFilePath1   = projectDirectory + "\\image\\" + fileName1;
  
  String fileName2         = "upload2.jpg";
  String uploadFilePath2   = projectDirectory + "\\image\\" + fileName2;
  
  
  String chromeUpload     = projectDirectory + "\\upload\\chrome.exe";
  String firefoxUpload    = projectDirectory + "\\upload\\firefox.exe";
  
  String baseURL1         = "http://blueimp.github.com/jQuery-File-Upload/";
  String baseURL2         = "https://encodable.com/uploaddemo/";
  
  String uploadOption     = "/uploaddemo/files/";
  String newSubfolderName = "Trung" + randomNumber();
  String email            = "Trung" + randomNumber() + "@gmail.com";
  String Name             = "Trung";

  public int randomNumber() {
    Random random = new Random();
    int number = random.nextInt(999999);
    return number;
  }  
  
  @BeforeClass
  public void beforeClass() {
    //driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TC_01_UploadFileBySendkeys() throws Exception {
    //Step 1
    driver.get(baseURL1);
    
    //Step 2
    String files[] = {uploadFilePath1, uploadFilePath2};
    for(int i=0; i<files.length; i++) {
      WebElement Addfiles = driver.findElement(By.xpath("//input[@type = 'file']"));
      Addfiles.sendKeys(files[i]);
    }
    
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName1 + "']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName2 + "']")).isDisplayed());
    
    List<WebElement> startbtn = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
    for(WebElement btn : startbtn) {
    wait.until(ExpectedConditions.elementToBeClickable(btn));
    btn.click();
    }
    
    //Step 3
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName1 + "']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName2 + "']")).isDisplayed());
  }

  @Test
  public void TC_02_UploadFileByAutoIT() throws Exception {
    //Step 1
    driver.get(baseURL1);
    
    //Step 2
    WebElement Addfiles = driver.findElement(By.cssSelector(".fileinput-button"));
    Addfiles.click();
    
    //Runtime.getRuntime().exec(new String[] {firefoxUpload , uploadFilePath });
    Runtime.getRuntime().exec(new String[] {chromeUpload , uploadFilePath1 });
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName1 + "']")).isDisplayed());
    
    WebElement startbtn = driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"));
    wait.until(ExpectedConditions.elementToBeClickable(startbtn));
    startbtn.click();
    
    //Step 3
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName1 + "']")).isDisplayed());
  }
  
  @Test
  public void TC_03_UploadFileByRobot() throws Exception {
    //Step 1
    driver.get(baseURL1);
    
    //Step 2
    StringSelection select = new StringSelection(uploadFilePath1);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
    
    WebElement Addfiles = driver.findElement(By.cssSelector(".fileinput-button"));
    Addfiles.click();
    
    Robot robot = new Robot();
    Thread.sleep(1000);
    
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_V);
    Thread.sleep(1000);
    
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName1 + "']")).isDisplayed());
    
    WebElement startbtn = driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']"));
    wait.until(ExpectedConditions.elementToBeClickable(startbtn));
    startbtn.click();
    
    //Step 3
    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName1 + "']")).isDisplayed());
  }
  
  @Test
  public void TC_04_UploadFiles() {
    //Step 1
    driver.get(baseURL2);
    
    //Step 2
    WebElement chooseFilebtn = driver.findElement(By.xpath("//input[@type = 'file']"));
    chooseFilebtn.sendKeys(uploadFilePath1);
    
    //Step 3
    WebElement dropdownList = driver.findElement(By.xpath("//select[@name='subdir1']"));
    Select select = new Select(dropdownList);
    select.selectByVisibleText(uploadOption);
    
    //Step 4
    WebElement newSubfolderTextArea = driver.findElement(By.xpath("//input[@name='newsubdir1']"));
    newSubfolderTextArea.sendKeys(newSubfolderName);
    
    //Step 5
    WebElement emailAddress = driver.findElement(By.xpath("//input[@id='formfield-email_address']"));
    emailAddress.sendKeys(email);
    WebElement firstName    = driver.findElement(By.xpath("//input[@id='formfield-first_name']"));
    firstName.sendKeys(Name);
    
    //Step 6
    WebElement uploadbtn    = driver.findElement(By.xpath("//input[@id='uploadbutton']"));
    uploadbtn.click();
    
    //Step 7
    WebElement emailInfo = driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dd[text()='Email Address: " + email + "']"));
    Assert.assertTrue(emailInfo.isDisplayed());
    WebElement namelInfo = driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dd[text()='First Name: " + Name + "']"));
    Assert.assertTrue(namelInfo.isDisplayed());
    WebElement fileNameInfo = driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dt//a[text()='" + fileName1 + "']"));
    Assert.assertTrue(fileNameInfo.isDisplayed());
    
    //Step 8
    WebElement viewUploadedFiles = driver.findElement(By.xpath("//a[text()='View Uploaded Files']"));
    viewUploadedFiles.click();
    
    //Step 9
    WebElement newFolder = driver.findElement(By.xpath("//a[text()='" + newSubfolderName + "']"));
    newFolder.click();
    
    //Step 10
    WebElement file = driver.findElement(By.xpath("//a[text()='" + fileName1 + "']"));
    Assert.assertTrue(file.isDisplayed());
    
  }
  @AfterClass
  public void afterClass() {
    driver.quit();
  }

}
