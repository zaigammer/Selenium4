package Modules;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class TrainerManagement {
	
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
	    public void TrainerManagement() throws InterruptedException {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	WebElement trainerManagement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Trainer Management')]")));
			trainerManagement.click();
	    }
	    @Test(priority = 4)
	    public void Test_Search() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search Trainer name, Description, Email...']")));

	        if (search.isDisplayed() && search.isEnabled()) {
	            search.sendKeys("Nikita");
	            search.click();
	            System.out.println("User performed the search");
	        } else {
	            System.out.println("Search input is not displayed or enabled");
	        }
	    }
	    
	    @Test(priority = 5)     
	    public void Test_Card_TotalTrainers() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//div[@class='card-block card-block1']")));
	        WebElement TotalTrainers = driver.findElement
					(By.xpath("//div[@class='card-block card-block1']"));
	        String TotalTrainer = TotalTrainers.getText();
	        System.out.println("Total Students:"+ TotalTrainer);
	    }
	 /*   @Test(priority = 6)
	    public void Test_Card_AddNewTrainer() {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    	 WebElement AddNewTra = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-block card-flex']")));
	    	 AddNewTra.click();
	    	 driver.findElement(By.xpath("//input[@placeholder='Trainer Name']")).sendKeys("Naveen");
	        driver.findElement(By.xpath("//textarea[@placeholder='About Trainer']")).sendKeys("Hey Guys, I am Naveen Automation Architect. I love the automation of everything");
	    driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("Nav@yopmail.com");
	    driver.findElement(By.xpath("//input[@placeholder='Qualification']")).sendKeys("M.Tech");
	    driver.findElement(By.xpath("//input[@placeholder='Experience']")).sendKeys("5");
	    
	    WebElement chooseFile = driver.findElement(By.id("motherProfileImage"));
	    chooseFile.sendKeys("C:/Users/DELL/Downloads/image.jpg");
	    System.out.println("File is Uploaded Successfully");
	    driver.findElement(By.xpath("(//button[@class='btn-save ng-star-inserted'])[2]")).click();
	    wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Trainer saved successfully')]")));
		driver.findElement(By.xpath("//*[contains(text(),'Trainer saved successfully')]"));
		driver.findElement(By.xpath("//*[contains(text(),'OK']")).click(); /
	    }  */
	    
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
			Assert.assertEquals(numberOfRecords,10);
			}

	    @Test(priority = 7)
		public void Test_ActionDelete(){        
		    	WebElement filterElement = driver.
						findElement(By.xpath("(//a[@ptooltip='Delete Data'])[1]"));
				Actions actions = new Actions(driver);
				actions.moveToElement(filterElement).click().perform();
				
				driver.findElement(By.xpath("//button[contains(text(),'Yes, delete it!')]")).click();
				 wait.until(ExpectedConditions
							.visibilityOfElementLocated
									(By.xpath("//*[contains(text(),'Trainer deleted successfully.)]")));
				 driver.findElement(By.xpath("//*[contains(text(),'OK']")).click();

	    }
}
