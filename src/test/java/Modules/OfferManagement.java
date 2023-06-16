package Modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OfferManagement {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeTest()
    public void launch() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
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
        // check if the submit button is enabled
        if (submitButton.isEnabled()) {
            submitButton.click();
            System.out.println("Test Passed : User able to login");
        } else {
            System.out.println("Test Failed : User unable to login");
        }
    }

    @Test(priority = 3)
    public void offerManagement() throws InterruptedException {
         wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement offerManagement = wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//span[contains(text(),'Offer Management')]"))
        );
        offerManagement.click();
    }

    @Test(priority = 4)
    public void search() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement search = wait.
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//input[@placeholder='Search coupon code and offer name']")));

        if (search.isDisplayed() && search.isEnabled()) {
            search.sendKeys("Test");
            search.click();
            System.out.println("User performed the search");
        } else {
            System.out.println("Search input is not displayed or enabled");
        }
    }

    @Test(priority = 5)
    public void totalOffer() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@class='card-block card-block1']")));
        WebElement totalOffer = driver.findElement(By.xpath("//div[@class='card-block card-block1']"));
        String Total = totalOffer.getText();
        System.out.println("Total Offer:" + Total);
    }
    @Test(priority = 5)
    public void addNewOffer() {
        WebElement AddNewTra = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='card-block card-flex']")));
        AddNewTra.click();
        driver.findElement(By.xpath("//input[@placeholder='Validity From']")).click();

        driver.findElement(By.xpath("//tbody[@class='ng-tns-c148-102']")).click();

        driver.findElement(By.xpath("//input[@placeholder='Coupon Code']")).sendKeys("DEMO12");
    }
   /*
    @Test(priority = 6)
    public void tablePagination() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement recordsPerPageDropdown = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
        recordsPerPageDropdown.click();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("(//li[@aria-label='10'])[1]")));
        dropdown.click();
        Thread.sleep(1000);
        List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='p-datatable-tbody']//tr"));
        int numberOfRecords = rows.size();
        // Compare the number of records displayed with the expected value of 10
        Assert.assertEquals(numberOfRecords, 10);
    }
    */






    }
