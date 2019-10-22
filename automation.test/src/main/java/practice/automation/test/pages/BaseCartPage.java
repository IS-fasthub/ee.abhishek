package practice.automation.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class BaseCartPage extends BasePage {

	public WebElement getPageHeader() {

		return getDriver().findElement(By.xpath("//h1"));
	}

	public WebElement getButtonRemoveFromCart() {

		return getDriver().findElement(By.xpath("//a[@title='remove this product from my cart']"));
	}
	
	public WebElement getButtonProceedToCheckout() {

		return getDriver().findElement(By.xpath("//p[@class='cart_navigation clearfix']//a[@title='Proceed to checkout']|//button/span[contains(text(),'Proceed to checkout')]"));
	}
	
	public ExpectedCondition<Boolean> titleToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getPageHeader().getText().trim().equals("Shopping-cart summary");
		}
	};


}
