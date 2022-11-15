package ExplicitWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Sample_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		} else { //Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}


	@Test
	public void TC_Ajax_Loading() {
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait = new WebDriverWait(driver, 15);
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.RadCalendar")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='16']")));
		
		driver.findElement(By.xpath("//a[text()='16']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='16']")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, November 16, 2022");
		
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	
	}
	
	
}
