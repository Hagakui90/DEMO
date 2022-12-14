package SampleJavaScriptExecutor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//change
public class Demo {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		} else { //Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor)driver;
		actions = new Actions(driver);
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
	@Test
	public void TC_04_Pexels() {
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		// Close bottom bar
		driver.findElement(By.cssSelector("a.switch-locale__close")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("button[class='rd__button rd__button--full-width']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='user_first_name']"), "Please fill out this field.");
		
		driver.findElement(By.cssSelector("input#user_first_name")).sendKeys("Hasta");
		driver.findElement(By.cssSelector("button[class='rd__button rd__button--full-width']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='user_email']"), "Please fill out this field.");
		
		String email = "hastamanana" + generateRandomNumber() + "@gmail.com";
		driver.findElement(By.cssSelector("input#user_email")).sendKeys(email);
		Assert.assertEquals(getElementValidationMessage("//input[@id='user_password']"), "Please fill out this field.");
		
	}

	@Test
	public void TC_05_Guru99() {
		driver.get("https://demo.guru99.com/v4/");
		
		driver.findElement(By.name("uid")).sendKeys("mngr452292");
		driver.findElement(By.name("password")).sendKeys("arUzana ");
		driver.findElement(By.name("btnLogin")).click();	
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepInSecond(3);
		
		// This webpage contains Advertisement pop up. This pop up is contained by 2 nested iframe. 
		// So solution is switch to every frame: frame1 > frame2 
		WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
		driver.switchTo().frame(frame1);
		
		WebElement btnClose = driver.findElement(By.cssSelector("div#ad_position_box svg"));
		String hexColor = btnClose.getAttribute("fill");
		System.out.println("HEX color = " + hexColor);
		if (hexColor.equals("none")) {			
			System.out.println("Exist button Close");
			WebElement frame2 = driver.findElement(By.id("ad_iframe"));
			driver.switchTo().frame(frame2);
			driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
		} else {

			System.out.println("Exist button X");
			driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
		}
				
		// Switch to New customer page
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("input[name='name']")).sendKeys("Travis L Garcia");
		driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td/input[@value='f']")).click();
		
		// Remove attribute type of Date picker
		removeAttributeInDOM("//input[@id='dob']", "type");
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("07/07/1983");
		driver.findElement(By.cssSelector("textarea[name='addr']")).sendKeys("2093 Woodland Terrace");
		driver.findElement(By.cssSelector("input[name='city']")).sendKeys("Sacramento");
		driver.findElement(By.cssSelector("input[name='state']")).sendKeys("California");
		driver.findElement(By.cssSelector("input[name='pinno']")).sendKeys("783642");
		driver.findElement(By.cssSelector("input[name='telephoneno']")).sendKeys("9169803460");
		
		String email = "neva2012" + generateRandomNumber() + "@hotmail.com";
		driver.findElement(By.cssSelector("input[name='emailid']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("783642");
		driver.findElement(By.cssSelector("input[name='sub']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		
		
	}
	
	@Test
	public void TC_06_TechPanda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		
		// Click link "My Account" at hidden header "Account" (not click to Account link)
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		sleepInSecond(3);
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
		return rand.nextInt(9999);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	
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


