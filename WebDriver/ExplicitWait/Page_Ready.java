package ExplicitWait;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	String keyword = "Selenium";
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		} else { //Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		action = new Actions(driver);
	}
	
	@Test
	public void TC_ProjectIO() {
		driver.get("https://blog.testproject.io/");
		
		action.moveToElement(driver.findElement(By.cssSelector("aside input.search-field"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("aside input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("aside span.glass")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("h2.page-title span")).isDisplayed());

		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postTitle = driver.findElements(By.cssSelector("h3.post-title"));
		for (WebElement title : postTitle) {
			String postTitleText = title.getText();
			System.out.println(postTitleText);
			Assert.assertTrue(title.getText().contains(keyword));
		}
		
		
	}
	
	public boolean isPageLoadedSuccess() {
		
		
		explicitWait = new WebDriverWait(driver, 30);
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean)jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean)jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
			
		};
		
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	
	}
}
