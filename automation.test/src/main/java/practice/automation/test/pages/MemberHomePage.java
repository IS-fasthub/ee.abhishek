package practice.automation.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class MemberHomePage extends BasePage {

	public WebElement getPageHeader() {

		return getDriver().findElement(By.xpath("//h1"));
	}

	public WebElement getLoggedInUser() {

		return getDriver().findElement(By.xpath("//a[@class='account']/span"));
	}

	public ExpectedCondition<Boolean> loggedInUsernameToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getLoggedInUser().isDisplayed();
		}
	};

}
