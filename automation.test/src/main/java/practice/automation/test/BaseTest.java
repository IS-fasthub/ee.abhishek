package practice.automation.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	protected static WebDriver driver;
	private WebDriverWait wait;
	
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
}
