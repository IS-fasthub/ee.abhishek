package practice.automation.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CartPaymentPage extends BaseCartPage {

	public WebElement getPageHeader() {

		return getDriver().findElement(By.xpath("//h1"));
	}
	
	public WebElement getLinkPayByCheque() {

		return getDriver().findElement(By.xpath("//a[@title='Pay by check.']"));
	}

	public WebElement getButtonConfirmOrder() {

		return getDriver().findElement(By.xpath("//button[@type='submit']/span[text()='I confirm my order']"));
	}
	
	public WebElement getLabelSuccessMessage() {

		return getDriver().findElement(By.xpath("//p[@class='alert alert-success']"));
	}
	
	public ExpectedCondition<Boolean> alertMessageToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getLabelSuccessMessage().isDisplayed();
		}
	};
	
}
