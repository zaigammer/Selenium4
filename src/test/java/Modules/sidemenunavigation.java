package Modules;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class sidemenunavigation {


    static WebDriver driver;
    static WebDriverWait wait;
    private Properties props;
    private FileReader reader;

    @BeforeTest()
    public void launch(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        props = new Properties();
        try {
            reader = new FileReader("src/test/java/Modules/Config.properties");
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 1)
    public void Test_verifyTitle() {
        driver.get(props.getProperty("url"));
        String actualTitle = driver.getTitle();
        String expectedTitle = "EDSTATE";
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 2)
    public void loginIn() {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));

        if (submitButton.isEnabled()) {
            submitButton.click();
            System.out.println("Test Passed : User able to login");
        } else {
            System.out.println("Test Failed : User unable to login");
        }
    }

    @Test(priority = 3, description = "Verify User can navigate to all the main pages from the sidemenu")
    public void navigatetosidemenu() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sideMenu = driver.findElement
                (By.xpath("//li[contains(@class,'ng-tns-c164-0 ng-star-inserted')]"));
        // Adjust the XPath to target your side menu element
        js.executeScript("arguments[0].scrollIntoView(true);", sideMenu);


        String[] labels = {
                "Dashboard", "Student Management", "Transactions","Staff Management","Course Management","Chapter Management",
                "Ask Doubt", "Offer Management", "Trainer Management", "Exam", "Testimonial"};
        for (String navigatetosidemenu  : labels) {
            WebElement element = driver.findElement(By.xpath("(//li[contains(.,'" +navigatetosidemenu+ "')])[1]"));
            String text = element.getText();
            Assert.assertEquals(text, navigatetosidemenu );
            System.out.println(navigatetosidemenu  + ": " + text);
        }
    }


}