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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class OfferManagement {
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
                        By.xpath("//span[contains(text(),'Offer Management')]")));
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


  /*  @Test(priority = 5)
    public void totalOffer() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@class='card-block card-block1']")));
        WebElement totalOffer = driver.findElement(By.xpath("//div[@class='card-block card-block1']"));
        String Total = totalOffer.getText();
        System.out.println("Total Offer:" + Total);
    }

   */
    @Test(priority = 5)
    public void addNewOffer() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement AddNewTra = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='card-block card-flex']")));
        AddNewTra.click();
       //Validity From
        driver.findElement(By.xpath("//input[@placeholder='Validity From']")).click();
        // Scroll to the desired month in the datepicker
        while (!driver.findElement(By.xpath(
                        "//div[contains(@class,'ng-trigger ng-trigger-overlayAnimation')]" +
                                "//div[contains(@class,'p-datepicker-header')]//div"))
                .getText().contains("July")) {
            driver.findElement(By.xpath("//button[contains(@class,'p-datepicker-next')]")).click();
        }
        // Select the desired date from the datepicker
        List<WebElement> dates = driver.findElements(By.xpath(
                "//div[contains(@class,'p-datepicker-calendar-container')]//tbody//td"));
        for (WebElement date : dates) {
            String text = date.getText();
            if (text.equalsIgnoreCase("15")) {
                date.click();
                break;
            }
        }
        //Validity Till
        Thread.sleep(2000);
      driver.findElement(By.xpath("//input[@placeholder='Validity Till']")).click();
        while (!driver.findElement(By.xpath(
                        "//div[contains(@class,'ng-trigger ng-trigger-overlayAnimation')]" +
                                "//div[contains(@class,'p-datepicker-header')]//div"))
                .getText().contains("July")) {
            driver.findElement(By.xpath("//button[contains(@class,'p-datepicker-next')]")).click();
        }
        // Select the desired date from the datepicker
        List<WebElement> Tilldates = driver.findElements(By.xpath(
                "//div[contains(@class,'p-datepicker-calendar-container')]//tbody//td"));
        for (WebElement date : Tilldates) {
            String text = date.getText();
            if (text.equalsIgnoreCase("20")) {
                date.click();
                break;
            }
        }
       driver.findElement(By.xpath("//input[@placeholder='Coupon Code']")).sendKeys("DEMO12");
        driver.findElement(By.xpath("//input[@placeholder='Offer Name']")).sendKeys("DEMO");
       //discount in percentage
       driver.findElement(By.xpath("//span[contains(@class,'p-dropdown-label')]")).click();
       driver.findElement(By.xpath("//li[@aria-label='False']")).click();
       driver.findElement(By.xpath("//input[@placeholder='Discount in Amount']")).sendKeys("100");
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'searchWrap')]//button")));
        driver.findElement(By.xpath("//div[contains(@class,'searchWrap')]//button")).click();
        WebElement submit = driver.findElement(By.xpath("(//button[normalize-space()='OK'])[1]"));
        if (submit.isEnabled()) {
            submit.click();
            System.out.println("Test Passed : User is able to saved offer successfully");
        } else {
            System.out.println("Test Failed : User unable to saved offer successfully");
        }
    }

    @Test(priority = 6)
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
    }
