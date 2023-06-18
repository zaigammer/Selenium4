package Modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CourseManagement {

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
    public void courseManagement() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement courseManagement = wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//span[contains(text(),'Course Management')]"))
        );
        if (courseManagement.isEnabled()) {
            courseManagement.click();
            System.out.println("Test Passed : User able to click on course management");
        } else {
            System.out.println("Test Failed : User unable to click on course management");
        }
    }

    @Test(priority = 4)
    public void search() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement search = wait.
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//input[@placeholder='Search Course Name']")));

        if (search.isDisplayed() && search.isEnabled()) {
            search.sendKeys("Test");
            search.click();
            System.out.println("User performed the search");
        } else {
            System.out.println("Search input is not displayed or enabled");
        }
    }

    @Test(priority = 5)
    public void totalCourses() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@class='card-block card-block1']")));
        WebElement totalCourses = driver.findElement(By.xpath("//div[@class='card-block card-block1']"));
        String Total = totalCourses.getText();
        System.out.println("Total Courses:" + Total);
    }
    @Test(priority = 6)
    public void addNewCourse() {
         wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement NewCourse = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//p[contains(.,'Add New Course')]")));
        NewCourse.click();
        driver.findElement(By.xpath("//input[@placeholder='Course Name']")).
                sendKeys("Testing Program");
        driver.findElement(By.xpath("//input[@placeholder='Sales Tag']")).
                sendKeys("manual testing");
        driver.findElement(By.xpath("//div[contains(text(),'Select Language')]")).click();
        //dropdown
        driver.findElement(By.xpath("//span[contains(text(),'Hindi')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'English')]")).click();
        //Add Thumbnail
        WebElement chooseFile = driver.findElement(By.id("motherProfileImage"));
        chooseFile.sendKeys("C:/Users/DELL/Downloads/image.jpg");
        System.out.println("Thumbnail is Uploaded Successfully");
        //Add Banner
        WebElement Banner = driver.findElement(By.id("bannerImage"));
        Banner.sendKeys("C:/Users/DELL/Downloads/Banner.jpg");
        System.out.println("Banner image Uploaded Successfully");
        //Short Description
        driver.findElement(By.xpath("//textarea[@placeholder='Short Description']")).
                sendKeys("Manual testing is the process of manually testing " +
                        "software for defects. It requires a tester to play the role.");
        //includes
        driver.findElement(By.xpath("(//input[@id='first'])[4]")).
                sendKeys("Exclusive mentorship by industry leaders");
        driver.findElement(By.xpath("//button[@class='btn-save m-l-20']")).click();
        //Exam Duration min
        driver.findElement(By.xpath("//input[@placeholder='Duration in minutes']")).sendKeys("60");
        //Exam No of Questions
        driver.findElement(By.xpath("//input[@placeholder='No of questions']")).sendKeys("15");
        driver.findElement(By.xpath("//input[@placeholder='MRP : Strike out​​']")).sendKeys("1000");
        driver.findElement(By.xpath("//input[@placeholder='Sales Price']")).sendKeys("100");
        driver.findElement(By.xpath("//input[@placeholder='Course Duration in Hrs']")).sendKeys("1");
        //Add Brochure
        WebElement Brochure = driver.findElement(By.id("brochureUpload"));
        Brochure.sendKeys("C:/Users/DELL/Downloads/Tools for Testers.pdf");
        System.out.println("Brochure Uploaded Successfully");
        //select chapters
        driver.findElement(By.xpath("//div[@role='button']")).click();
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//li[@aria-label='Administration in Real Estate']"))).click();
        driver.findElement(By.xpath("//input[@placeholder='Sequence Number']")).sendKeys("1");
        driver.findElement(By.xpath("//button[contains(text(),'Add Lecture')]")).click();
        driver.findElement(By.xpath("(//button[contains(.,'Save')])[2]")).click();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("(//div[@id='swal2-content'])[1]")));
        driver.findElement(By.xpath("(//div[@id='swal2-content'])[1]"));
        WebElement submit = driver.findElement(By.xpath("(//button[normalize-space()='OK'])[1]"));
        if (submit.isEnabled()) {
            submit.click();
            System.out.println("Test Passed : User able to saved course successfully");
        } else {
            System.out.println("Test Failed : User unable to saved course successfully");
        }
    }

    @Test(priority = 7)
    public void ActionDelete() {
        WebElement filterElement = driver.
                findElement(By.xpath("(//a[@ptooltip='Delete Data'])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(filterElement).click().perform();

        driver.findElement(By.xpath("//button[contains(text(),'Yes, delete it!')]")).click();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated
                        (By.xpath("(//div[@id='swal2-content'])[1]")));
        driver.findElement(By.xpath("(//div[@id='swal2-content'])[1]"));
        WebElement delete = driver.findElement(By.xpath("(//button[normalize-space()='OK'])[1]"));
        if (delete.isEnabled()) {
            delete.click();
            System.out.println("Test Passed : User able to deleted course successfully");
        } else {
            System.out.println("Test Failed : User unable to deleted course successfully");
        }
    }




   /* @AfterClass
    public void close() {
        driver.close();
    }
   */

}
