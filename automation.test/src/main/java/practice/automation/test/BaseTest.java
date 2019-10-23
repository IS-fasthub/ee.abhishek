package practice.automation.test;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	protected static WebDriver driver;
	protected static ExtentReports reports = new ExtentReports("ExtentReport.html");
	protected static ExtentTest test;
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWebDriverWait(int timeout) {
		return new WebDriverWait(getDriver(), timeout);
	}

	private void setDriver(String browserType, String appURL) {
		if (browserType.equalsIgnoreCase("chrome")) 
			driver = initChromeDriver(appURL);
		else if (browserType.equalsIgnoreCase("firefox"))
			driver = initFirefoxDriver(appURL);
		else {
			System.out.println("Browser type : " + browserType + " is invalid, using Chrome as default browser");
			driver = initChromeDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(appURL);
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		WebDriver driver = new FirefoxDriver();
		driver.get(appURL);
		driver.manage().window().maximize();
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initialize(String browserType, String appURL) {
		setDriver(browserType, appURL);
	}

	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
	
	@BeforeMethod
	public void logSetup(Method method) {
		test = reports.startTest(method.getName());
	}
	
	@AfterMethod
	public void endTestLog() {
		reports.endTest(test);
		reports.flush();
	}
	
	public static void verify(boolean verification, String passMessage, String failMessage) {
		if(verification)
			test.log(LogStatus.PASS, passMessage);
		else
			test.log(LogStatus.FAIL, failMessage);
	}
	
	public static void reportLogger(String text) {
		test.log(LogStatus.INFO, text);
	}
}
