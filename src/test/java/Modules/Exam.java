package Modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Exam {

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
    public void Exam() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement Exam = wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//span[contains(text(),'Exam')]")));
        Exam.click();
    }

    @Test(priority = 4)
    public void search() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement search = wait.
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//input[@placeholder='Search Course Name, Customer Name,  Customer Phone']")));

        if (search.isDisplayed() && search.isEnabled()) {
            search.sendKeys("Test");
            search.click();
            System.out.println("User performed the search");
        } else {
            System.out.println("Search input is not displayed or enabled");
        }
    }

    @Test(priority = 5)
    public void carts() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement TotalExams = driver.findElement
                (By.xpath("//div[@class='card-block card-block1']"));
        String TotalExamsConducted = TotalExams.getText();
        System.out.println("Card:" + TotalExamsConducted);

        WebElement MinimumMarks = driver.findElement
                (By.xpath("(//div[@class='col-md-3 ng-star-inserted'])[2]"));
        String MiniMarks = MinimumMarks.getText();
        System.out.println("Card:" + MiniMarks);

        WebElement MaximumMarks = driver.findElement
                (By.xpath("(//div[@class='col-md-3 ng-star-inserted'])[3]"));
        String MaxiMarks = MaximumMarks.getText();
        System.out.println("Card:" + MaxiMarks);
    }

    @Test(priority = 6)
    public void Test_filter() {
        // Filter
        WebElement filterElement = driver.findElement(By.xpath("//i[@class='icon-filter filtericon']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(filterElement).click().perform();
        // dropdown
        driver.findElement(By.xpath("//input[@placeholder='Minimum Percentage']")).sendKeys("10");
        driver.findElement(By.xpath("//input[@placeholder='Maximum Percentage']")).sendKeys("30");
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
    }

    /*
        @Test(priority = 7)
        public void tablePagination() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement recordsPerPageDropdown = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
            recordsPerPageDropdown.click();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@aria-label='10'])[1]")));
            dropdown.click();
            Thread.sleep(1000);
            List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='p-datatable-tbody']//tr"));
            int numberOfRecords = rows.size();
            // Compare the number of records displayed with the expected value of 10
            Assert.assertEquals(numberOfRecords, 10);
        }*/
    @Test(priority = 7)
    public void Action() {
        driver.findElement(By.xpath("(//i[@class='fa fa-eye'])[2]")).click();
        WebElement recordsPerPageDropdown = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
        recordsPerPageDropdown.click();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("(//li[@aria-label='10'])[1]")));
        dropdown.click();
        List<WebElement> rows = driver.findElements
                (By.xpath("//div[@class='p-datatable-scrollable-body ng-star-inserted']//tr"));
        int numberOfRecords = rows.size();
        // Compare the number of records displayed with the expected value of 10
        Assert.assertEquals(numberOfRecords, 10);

        WebElement element = driver.findElement(By.xpath("//u[contains(.,'Back')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
     WebElement download=  driver.findElement(By.xpath("(//i[@class='fa fa-download'])[2]"));
        if (download.isEnabled()) {
            download.click();
            System.out.println("Test Passed : User able to click on Download");
        } else {
            System.out.println("Test Failed : User unable to click on Download");
        }
    }



}



