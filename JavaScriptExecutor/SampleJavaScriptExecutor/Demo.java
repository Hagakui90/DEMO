package SampleJavaScriptExecutor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		} else { //Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_TechPanda() {
		//***************
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		
		String techPandaDomain = (String) executeForBrowser("return document.domain");	
		Assert.assertEquals(techPandaDomain, "live.techpanda.org");
		
		String techPandaUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(techPandaUrl, "http://live.techpanda.org/");
		
		//****************
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		//****************
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div//span[text()='Add to Cart']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div//span[text()='Add to Cart']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		//****************
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		
		String titleCustomerService = (String) executeForBrowser("return document.title");
		Assert.assertEquals(titleCustomerService, "Customer Service");
		
		scrollToElementOnDown("//input[@id='newsletter']");
		hightlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "hastamanana." + generateRandomNumber() + "@gmail.com");
		
		//****************
		hightlightElement("//span[text()='Subscribe']");
		clickToElementByJS("//span[text()='Subscribe']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		//****************
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		String demoGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
		
	}

	@Test
	public void TC_02_Ubuntu() {
		driver.get("https://login.ubuntu.com/");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("button#cookie-policy-button-accept")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("div.login-form button[type='submit']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"),"Please fill out this field.");
		
		String email = "hastamanana" + generateRandomNumber() + "gmail.com";
		driver.findElement(By.cssSelector("div.login-form input[name='email']")).sendKeys(email);
		driver.findElement(By.cssSelector("div.login-form button[type='submit']")).click();
		Assert.assertEquals(getElementValidationMessage("//div[@class='login-form']//input[@id='id_password']"),"Please fill out this field.");

		
	
	}
	
	@Test
	public void TC_03_Rode() {
		driver.get("https://warranty.rode.com/");
		
		// Valid message for Fistname textbox
		driver.findElement(By.xpath("//form[contains(@action,'register')]//button[@type='submit']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"),"Please fill out this field.");
		
		// Valid message for Surname textbox
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Hasta");
		driver.findElement(By.xpath("//form[contains(@action,'register')]//button[@type='submit']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"),"Please fill out this field.");
				
		// Valid message for E-mail textbox
		driver.findElement(By.xpath("//input[@id='surname']")).sendKeys("Manana");
		driver.findElement(By.xpath("//form[contains(@action,'register')]//button[@type='submit']")).click();
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='email']"),"Please fill out this field.");
		
		
		// Valid message for Password textbox
		String email = "hastamanana" + generateRandomNumber() + "@gmail.com";
		driver.findElement(By.xpath("//form[contains(@action,'register')]//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//form[contains(@action,'register')]//button[@type='submit']")).click();
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='password']"),"Please fill out this field.");
		
		
		// Valid message for Password textbox
		driver.findElement(By.xpath("//form[contains(@action,'register')]//input[@id='password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//form[contains(@action,'register')]//button[@type='submit']")).click();
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='password-confirm']"),"Please fill out this field.");
			
		
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
