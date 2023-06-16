package Modules;

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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Testimonial {
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
    public void testimonial() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement Testimonial = driver.findElement(
                By.xpath("//span[contains(text(),'Testimonial')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(Testimonial).click().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='card-block card-flex']")).click();

        driver.findElement(By.xpath("(//input[@id='first'])[1]")).
                sendKeys("New Testimonial");
        driver.findElement(By.xpath("//textarea[@placeholder='Description']")).
                sendKeys("New Description");
        //Add Thumbnail
        WebElement Thumbnail = driver.findElement(By.id("motherProfileImage"));
        Thumbnail.sendKeys("C:/Users/DELL/Downloads/image.jpg");
        driver.findElement(By.xpath("(//button[@class='btn-save ng-star-inserted'])[2]")).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));
        driver.findElement(By.xpath("//h2[@id='swal2-title']"));
        WebElement submit = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
        if (submit.isEnabled()) {
            submit.click();
            System.out.println("Test Passed : User able to saved Testimonial successfully");
        } else {
            System.out.println("Test Failed : User unable to saved Testimonial successfully");
        }
    }
        @Test(priority = 4)
        public void ActionDelete(){
            WebElement filterElement = driver.
                    findElement(By.xpath("(//a[@ptooltip='Delete Data'])[1]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(filterElement).click().perform();

            driver.findElement(By.xpath("//button[contains(text(),'Yes, delete it!')]")).click();
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated
                            (By.xpath("(//div[@id='swal2-content'])[1]")));
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        }
    }



