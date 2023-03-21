import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OTP_Email {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email = "hanahasta" + randomNumber() + "@mailsac.com";
	String password = "12345678";
	String OTPCode;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver");
		} else { // Window
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_OTPEmail() {
		String url = "http://mailsac.com";
		driver.get("https://playground.mailslurp.com/");
		driver.findElement(By.xpath("//a[text()='Create account']")).click();

		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(@class,'Button')]")).click();

		openNewTabByJS(url);
		sleepInSecond(3);

		switchToWindowByName("Mailsac - Fast Disposable Email and APIs for Software Testing");

		driver.findElement(By.xpath("//input[contains(@class,'myinbox')]")).sendKeys(email);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//td[text()='Please confirm your email address']")).click();
		String emailConfirmContent = driver.findElement(By.xpath("//div[contains(text(),'verification code')]")).getText();
		OTPCode = getOTPCode(emailConfirmContent);

		sleepInSecond(3);
		switchToWindowByName("React App");

		driver.findElement(By.xpath("//input[@name='code']")).sendKeys(OTPCode);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

	}

	public void switchToWindowByName(String expectedPageTitle) {

		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			driver.switchTo().window(id);

			String currentlyPageTitle = driver.getTitle();

			if (currentlyPageTitle.equals(expectedPageTitle)) {
				break;
			}

		}

	}

	public void openNewTabByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + url + "','_blank');");
	}

	public String getOTPCode(String content) {
		Pattern code = Pattern.compile("[0-9]{6}");
		Matcher matcher = code.matcher(content);
		matcher.find();

		return OTPCode = content.substring(matcher.start(), matcher.end());
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();

	}

	public int randomNumber() {
		Random random = new Random();
		int rand = random.nextInt(9999);
		return rand;
	}

	// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
