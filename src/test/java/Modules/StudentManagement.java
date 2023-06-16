package Modules;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StudentManagement {
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
    public void Test_verifyTitle() {
        //    driver.get("https://www.example.com");
        driver.get("http://3.6.118.47/admin/#/auth/login/simple");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Simple Login | Mega Able Angular 7+";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(priority = 2)
    public void Test_LoginIn() {
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
    public void StudentManagement() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement studentManagement = wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//span[contains(text(),'Student Management')]"))
        );
        studentManagement.click();
    }

    @Test(priority = 4)
    public void Test_Search() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='Search Student Name, Email and Phone.']")
                ));
        WebElement search = driver
                .findElement(
                        By.xpath("//input[@placeholder='Search Student Name, Email and Phone.']")
                );
//			search.click();
//			search.sendKeys("Marks");	
        if (search.isDisplayed() && search.isEnabled()) {
            search.click();
            System.out.println("user able to Search");
        } else {
            System.out.println("user unable to Search");
        }
    }

    @Test(priority = 5)
    public void Test_Card_TotalStudents() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-block card-block1']")));
        WebElement totalStudents = driver.findElement(By.xpath("//div[@class='card-block card-block1']"));
        String total = totalStudents.getText();
        System.out.printf("Total Students:" + total);

    }
/*
    @Test(priority = 6)
    public void Test_tablePagination() throws InterruptedException {
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
    public void Test_filter() {
        // Filter
        WebElement filterElement = driver.findElement(By.xpath("//i[@class='icon-filter filtericon']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(filterElement).click().perform();
        // dropdown
        driver.findElement(By.xpath("//span[contains(text(),'Abandoned Cart')]")).click();
        driver.findElement(By.xpath("//li[@aria-label='No']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
    }

    @Test(priority = 7)
    public void Viewdetail() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(By.xpath("//a[contains(text(),'Adityakumar Manojkumar Agarwal')]")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        String[] labels = { "Name", "Email Address", "Mobile Number", "Designation", "Occupation","Date Of Birth",
                "Location","Gender","Work Experience","Qualification","Resume Uploaded","About me","Company Name","Billing Address" };

        for (String viewdetail : labels) {
            WebElement element = driver.findElement(By.xpath("(//p[contains(.,'" + viewdetail + "')])[1]"));
            String text = element.getText();
            Assert.assertEquals(text, viewdetail);
            System.out.println(viewdetail + ": " + text);
            //   System.out.println(driver.findElement(By.xpath("(//p[contains(.,'Mobile Number')])[1]")).getText());

            driver.findElement(By.xpath("//span[contains(.,'Enrolled Courses')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//a[@id='p-accordiontab-0']"))).click();

        }
    }


}