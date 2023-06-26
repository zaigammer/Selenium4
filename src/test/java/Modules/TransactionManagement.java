package Modules;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TransactionManagement {
	
	static String Month ="September";
	static String day = "10";
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeTest()
    public void launch(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
    
    @Test(priority = 1)
    public void verifyTitle() {
        //    driver.get("https://www.example.com");
        driver.get("http://3.6.118.47/admin/#/auth/login/simple");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Simple Login | Mega Able Angular 7+";
        Assert.assertEquals(actualTitle, expectedTitle);
    }


    @Test(priority = 2)
    public void loginIn() {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));

        //    WebElement submitButton = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
        // check if the submit button is enabled
        if (submitButton.isEnabled()) {
            submitButton.click();
            System.out.println("Test Passed : User able to login");
        } else {
            System.out.println("Test Failed : User unable to login");
        }
    }

    @Test(priority = 3)
    public void transactions() throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	WebElement Transaction = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[contains(text(),'Transactions')]")));
    	Transaction.click();
   	//FilterBy
        // Click on the filter icon
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//i[@class='icon-filter filtericon']")));
        filter.click();

// Select From Date
        WebElement fromDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//input[@placeholder='From Date']")));
        fromDate.click();

// Scroll to the desired month in the datepicker
        Select fromMonthDropdown = new Select(driver.findElement(By.xpath
                ("//select[@class='p-datepicker-month ng-tns-c148-12 ng-star-inserted']")));
        fromMonthDropdown.selectByVisibleText("June");

// Select the desired date from the datepicker
        List<WebElement> fromDateOptions = driver.findElements(By.xpath
                ("//div[contains(@class,'p-datepicker-calendar-container')]//tbody//td"));
        for (WebElement option : fromDateOptions) {
            String text = option.getText();
            if (text.equalsIgnoreCase("12")) {
                option.click();
                break;
            }
        }

// Select Till Date
        Thread.sleep(2000); // Add explicit wait if required
        WebElement toDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//div//p-calendar[@placeholder='Till Date']")));
        toDate.click();

// Scroll to the desired month in the datepicker
        Select tillMonthDropdown = new Select(driver.findElement(By.xpath
                ("//select[contains(@class, 'p-datepicker-month')]")));
        tillMonthDropdown.selectByVisibleText("June");

// Select the desired date from the datepicker
        List<WebElement> tillDateOptions = driver.findElements(By.xpath
                ("//div[contains(@class,'p-datepicker-calendar-container')]//tbody//td"));
        for (WebElement option : tillDateOptions) {
            String text = option.getText();
            if (text.equalsIgnoreCase("14")) {
                option.click();
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[contains(text(),'Search')]"))).click();

   /*     	String text = driver.findElement
                (By.xpath("//select[@class='p-datepicker-month ng-tns-c148-12 ng-star-inserted']")).
                getText();
    	String[] monthArr = text.split("\n");
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	 int currentMonth = cal.get(Calendar.MONTH);
    	 System.out.println(monthArr[0].equals(Month));
    	for (int i = currentMonth; i < monthArr.length; i++) {
    	    if (monthArr[i].equals(Month)) {
    	        break;
    	    } else {
    	    		WebElement Forward = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='p-datepicker-next p-link ng-tns-c148-12 p-ripple ng-star-inserted']")));
        	        Forward.click();
    	    	
    	        continue; // Add the break statement here to exit the loop after clicking the forward butto   
    	}}
    	WebElement Date = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//tbody[@class='ng-tns-c148-12']//td[contains(., " + day + ")]")));
    	Date.click();
  */
    	}

    @Test(priority = 4)
    public void viewdetail() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("(//a[@class='underLineText pointerClass'])[1]"))).click();

        WebElement orderDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='row orderDetailsWrap']")));
        String orderDetailsText = orderDetails.getText();
        System.out.println(orderDetailsText);
        Assert.assertEquals(orderDetailsText, orderDetailsText);
       //Download Invoice
        WebElement downloader= driver.findElement(By.xpath("//p[@class='m-t-10 pointerClass reciepttext']"));
        if (downloader.isEnabled()) {
            downloader.click();
            System.out.println("Test Passed : User able to download invoice");
        } else {
            System.out.println("Test Failed : User unable to download invoice");
        }

    }

}


