package practice.automation.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class LoginPage extends BasePage {

	public WebElement getLogInForm() {

		return getDriver().findElement(By.xpath("//form[@id='login_form']"));
	}
	
	public WebElement getEmailField() {

		return getDriver().findElement(By.xpath("//input[@id='email']"));
	}
	
	public WebElement getPasswordField() {

		return getDriver().findElement(By.xpath("//input[@id='passwd']"));
	}
	
	public WebElement getButtonSignIn() {

		return getDriver().findElement(By.xpath("//button[@id='SubmitLogin']"));
	}
	
	public ExpectedCondition<Boolean> logInFormToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getLogInForm().isDisplayed();
		}
	};
}