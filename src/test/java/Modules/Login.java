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

public class Login {
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
    public void Test_Dashboard_SalesStatistics() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='dashboard-flex'])[1]")));
        WebElement SalesStatistics = driver.findElement(By.xpath("(//div[@class='dashboard-flex'])[1]"));
        String SalesStatist = SalesStatistics.getText();
        System.out.println("Sales Statistics Data: [--"+ SalesStatist+"--]");
    }
        @Test(priority = 4)     
        public void Test_Dashboard_NewRegistrations() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='dashboard-flex'])[2]")));
        WebElement NewRegistrations = driver.findElement(By.xpath("(//div[@class='dashboard-flex'])[2]"));
        String NewReg = NewRegistrations.getText();
        System.out.println("New Registrations Data: [--"+ NewReg +"--]");

        }

        @Test(priority = 5)     
        public void Test_Dashboard_NewEnrollCourse() {
            driver.findElement(By.xpath("//div[@class='p-multiselect-label ng-tns-c143-2 p-placeholder']")).click();
            driver.findElement(By.xpath("//span[normalize-space()='Real Estate Mastery Program']")).click();
            driver.findElement(By.xpath("//button[contains(text(),'Generate Report')]")).click();
        }

        @Test(priority = 6)     
        public void Test_Dashboard_NewRegistrations_DataWise() {
            WebElement NewRegistrations = driver.findElement(By.xpath
                    ("(//canvas[@class='chartjs-render-monitor'])[2]"));
            String graphText = NewRegistrations.getText();    
            System.out.println("NewRegistrations graph: " + graphText);

        }


        @Test(priority = 7)     
        public void Test_Dashboard_Registrations() {
     
        	WebElement ele = driver.findElement(By.xpath
                    ("//p-chart[@type='horizontalBar']//canvas[@class='chartjs-render-monitor']"));
        	Actions action = new Actions(driver);
        	action.moveToElement(ele).perform();
        	String dataValue = ele.getAttribute("class");
        	System.out.println("Registrations Location Wise: " + dataValue);
        	
             
        	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
            // ("//p-chart[@type='horizontalBar']//canvas[@class='chartjs-render-monitor']")));
//            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
//            jsExecutor.executeScript("arguments[0].click()", chartElement);
//            String dataValue = (String) jsExecutor.executeScript("return myChart.getDataValue()");
//            System.out.println("Data value: " + dataValue);
//          
        }

 

        @Test(priority = 8)     
        public void Test_Dashboard_Registrations_GenderWise() {
            WebElement NewRegistrations = driver.findElement(By.xpath
                    ("//p-chart[@type='pie']//canvas[@class='chartjs-render-monitor']"));

            String graphText = NewRegistrations.getText();    
            System.out.println("NewRegistrations graph: " + graphText);

        }
        @Test(priority = 9)     
        public void Test_admin() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'admin')]")));
            driver.findElement(By.xpath("//span[contains(text(),'admin')]")).click();
            // Check if the welcome message is displayed
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
                    ("//a[contains(text(),'Company Profile')]")));
            element.click();

            WebElement update = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
                    ("(//button[contains(text(),'Update')])[1]")));
            update.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
         WebElement submitButton =     driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
                // check if the submit button is enabled
                if (submitButton.isEnabled()) {
                    submitButton.click();
                    System.out.println("Test Passed : Company Profile saved successfully");
                } else {
                    System.out.println("Test Failed : Company Profile unable saved successfully");
                }
        }
        @Test(priority = 10)     
        public void Test_Logout() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'admin')]")));
            driver.findElement(By.xpath("//span[contains(text(),'admin')]")).click();
            WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Logout')]")));
            logout.click();
        }

}
