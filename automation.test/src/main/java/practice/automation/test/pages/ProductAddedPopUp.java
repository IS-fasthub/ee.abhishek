package practice.automation.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ProductAddedPopUp extends BasePage {

	public WebElement getProductDetailPopUp() {

		return getDriver().findElement(By.xpath("//div[@id='layer_cart']/div[@class='clearfix']"));
	}

	public WebElement getButtonContinueShopping() {
		return getProductDetailPopUp().findElement(By.xpath("//span[@title='Continue shopping']"));
	}
	
	public WebElement getButtonProceedToCheckout() {
		return getProductDetailPopUp().findElement(By.xpath("//a[@title='Proceed to checkout']"));
	}
	
	public ExpectedCondition<Boolean> popUpToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getProductDetailPopUp().isDisplayed();
		}
	};

}
