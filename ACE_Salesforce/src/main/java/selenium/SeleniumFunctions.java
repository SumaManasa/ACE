package selenium;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import StepDefinitions.functional.Hooks;
import Utils.Config;
import Utils.PageObjectManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class SeleniumFunctions {
	public static WebDriver driver;
	public static WebDriverWait waitVar = null;

	private static PageObjectManager pageManager;
	private static Config config = Config.getInstance();
	private static String driverType = config.getBrowser();

	public PageObjectManager getPageManager() {
		return pageManager;
	}

	public void setPageManager(PageObjectManager manager) {
	pageManager = manager;
	}

	public SeleniumFunctions() {
		createWebDriver();
		if(pageManager==null) {
		pageManager = new PageObjectManager(driver);
		}
	}

	public void createWebDriver() {
		if (driver == null) {
			try {
				driver = new SeleniumFactory().getWebDriver(driverType, 30);
				driver.manage().window().maximize();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public WebDriver createDriver(String url) throws Exception {
		if(driver.getCurrentUrl().isEmpty()) {
			driver = new SeleniumFactory().getWebDriver(driverType, 30);
			driver.manage().window().maximize();
			driver.get(url);
			waitVar = new WebDriverWait(driver, 5000);
		}else {
			driver.get(url);
			waitVar = new WebDriverWait(driver, 5000);
		}
		
		return driver;
	}

	public WebDriver createInCognito(String url) {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * TestNgTestRunner.class.getClassLoader().getResource("chromedriver.exe").
		 * toExternalForm());
		 */
		ChromeDriverManager.getInstance().setup();
		ChromeOptions o = new ChromeOptions();
		o.addArguments("incognito");
		o.addArguments("disable-extensions");
		o.addArguments("--start-maximized");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(o.CAPABILITY, o);
		WebDriver driver = new ChromeDriver(o);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		waitVar = new WebDriverWait(driver, 5000);
		return driver;
	}

	public void teardown() {
		driver.quit();
	}

	public boolean isHomePageDisplayed() {
		boolean flag;
		if (pageManager.getLoginPage().btn_login.isDisplayed()) {
			flag = true;
			System.out.println("passed");
		} else {
			waitVar.until(ExpectedConditions.visibilityOf(pageManager.getLoginPage().btn_login));
			flag = true;
		}
		return flag;
	}

	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	public boolean isElementPresent(WebElement element) throws Exception {
		boolean flag = false;
		try {
			if (element.isDisplayed()) {
				flag = true;
				System.out.println("passed");
			}
		} catch (NoSuchElementException e) {
			flag = false;
		}
		return flag;
	}

	// Hooks.scenario.embed(((TakesScreenshot)
	// driver).getScreenshotAs(OutputType.BYTES), "image/png");
	
	public void takeScreenshot(String outputFilePath, String step) {
		try {
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File(outputFilePath + "\\" + step + ".jpg"));
			Hooks.scenario.embed(((TakesScreenshot)
					driver).getScreenshotAs(OutputType.BYTES), "image/png");
//			Hooks.scenario.embed(TakesScreenshot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*public void takeScreenshot(String windowHandle, WebDriver driver, String outputFilePath, String step) {
		try {
			driver.switchTo().window(windowHandle);
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File(outputFilePath + "\\" + step + ".jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	}
}
