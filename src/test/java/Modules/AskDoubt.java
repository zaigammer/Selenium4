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
import java.util.concurrent.TimeUnit;

public class AskDoubt {
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
    public void askdoubt() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement askdoubt = wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//span[contains(text(),'Ask Doubt')]")));
        askdoubt.click();
    }

    @Test(priority = 3)
    public void dropdown() {
        driver.findElement(By.xpath
                ("//a[@id='mobile-collapse']//i[@class='icon-menu ng-tns-c164-0']")).click();
        driver.findElement((By.xpath("//span[contains(.,'Select chapters')]"))).click();
//search
        driver.findElement(By.xpath
                ("//span[contains(text(),'RERA: Compliances and Filing')]")).click();
        System.out.println(driver.findElement(By.xpath("//div[@class='flexAsk pointerClass']")).getText());
    }


}