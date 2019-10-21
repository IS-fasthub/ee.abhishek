package practice.automation.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import practice.automation.test.BaseTest;


public class BasePage extends BaseTest{

	public WebElement getSearchTextBox() {

		return getDriver().findElement(By.xpath("//input[@id='search_query_top']"));
	}
	
	public WebElement getButtonCart() {

		return getDriver().findElement(By.xpath("//a[@title='View my shopping cart']"));
	}
	
	public WebElement getButtonSignOut() {
		return getDriver().findElement(By.xpath("//a[@title='Log me out']"));
	}
	
	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
	
	public static void scrollIntoView(WebElement element) {
		
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
