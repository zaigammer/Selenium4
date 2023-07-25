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

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ChapterManagement {
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
    public void chapterManagement() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement chapterManagement = wait.until(ExpectedConditions
                .elementToBeClickable(
                        By.xpath("//span[contains(text(),'Chapter Management')]")));
        if (chapterManagement.isEnabled()) {
            chapterManagement.click();
            System.out.println("Test Passed : User able to click on Chapter Management");
        } else {
            System.out.println("Test Failed : User unable to click on Chapter Management");
        }
    }

    @Test(priority = 4)
    public void search() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement search = wait.
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//input[@placeholder='Search Chapter Name']")));

        if (search.isDisplayed() && search.isEnabled()) {
            search.sendKeys("Test");
            search.click();
            System.out.println("User performed the search");
        } else {
            System.out.println("Search input is not displayed or enabled");
        }
    }

    @Test(priority = 5)
    public void totalChapters() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@class='card-block card-block1']")));
        WebElement totalCourses = driver.findElement(By.xpath("//div[@class='card-block card-block1']"));
        String Total = totalCourses.getText();
        System.out.println("Total Chapters:" + Total);
    }

    @Test(priority = 6)
    public void addNewChapter() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement NewChapter = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//p[contains(.,'Add New Chapter')]")));
        NewChapter.click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'Chapter Name')]")).
                sendKeys("Software Testing Principles");
        //Add Thumbnail
        WebElement chooseFile = driver.findElement(By.id("motherProfileImage"));
        chooseFile.sendKeys("C:/Users/DELL/Downloads/image.jpg");
        System.out.println("Thumbnail is Uploaded Successfully");

        driver.findElement(By.xpath("//textarea[@placeholder='Short Description']")).
                sendKeys("Software testing is the process of executing a program with" +
                        " the aim of finding the error.");
        //includes
        driver.findElement(By.xpath("(//input[@id='first'])[2]")).
                sendKeys("Exclusive mentorship by industry leaders");
        driver.findElement(By.xpath("//button[text()=' Add ']")).click();
        //Duration in mins
        WebElement duration = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//input[@placeholder='Duration in mins']")));
        duration.sendKeys("1");
        //Video
        driver.findElement(By.xpath("(//input[@placeholder='Title'])[1]")).sendKeys("ST chapter");
        driver.findElement(By.xpath("(//input[@placeholder='Id'])[2]")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@placeholder='Add sequence']")).sendKeys("1");
        driver.findElement(By.xpath("//span[contains(.,'Select Language')]")).click();
        driver.findElement(By.xpath("(//li[@aria-label='English'])[1]")).click();
        driver.findElement(By.xpath("(//button[contains(text(),'Add')])[3]")).click();
        //Add Notes
        driver.findElement(By.xpath("(//input[@placeholder='Title'])[2]")).sendKeys("New Title");
        WebElement Notes= driver.findElement(By.id("notesUpload"));
        Notes.sendKeys("C:/Users/DELL/Downloads/Tools for Testers.pdf");
        driver.findElement(By.xpath("//button[contains(.,'Add Notes')]")).click();
        System.out.println("Notes Uploaded Successfully");
        //select Trainer
        driver.findElement(By.xpath("//div[contains(text(),'Select Trainer')]")).click();
        driver.findElement(By.xpath("(//li[@class='p-multiselect-item'])[1]")).click();

        driver.findElement(By.xpath("(//button[contains(.,'Save')])[2]")).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));
        driver.findElement(By.xpath("//h2[@id='swal2-title']"));
        WebElement submit = driver.findElement(By.xpath("//button[contains(.,'OK')]"));
        if (submit.isEnabled()) {
            submit.click();
            System.out.println("Test Passed : User able to saved chapter successfully");
        } else {
            System.out.println("Test Failed : User unable to saved chapter successfully");
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


}
