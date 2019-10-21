package practice.automation.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CartPage extends BasePage {

	public WebElement getPageHeader() {

		return getDriver().findElement(By.xpath("//h1"));
	}

	public WebElement getUnitPrice() {

		return getDriver().findElement(By.xpath("//td[@data-title='Unit price']/span"));
	}
	
	public WebElement getQuantity() {

		return getDriver().findElement(By.xpath("//td[starts-with(@class, 'cart_quantity')]/input[@type='text']"));
	}

	public WebElement getCartTotal() {

		return getDriver().findElement(By.xpath("//td[@class= 'cart_total']/span"));
	}
	
	public WebElement getTotalShipping() {

		return getDriver().findElement(By.xpath("//td[@id='total_shipping']"));
	}	
	public WebElement getTaxAmount() {

		return getDriver().findElement(By.xpath("//tr[@class= 'cart_total_tax']/td[@id='total_tax']"));
	}
	
	public WebElement getTotalAmount() {

		return getDriver().findElement(By.xpath("//tr[@class= 'cart_total_price']/td[@id='total_price_container']"));
	}
	
	public WebElement getButtonRemoveFromCart() {

		return getDriver().findElement(By.xpath("//a[@title='remove this product from my cart']"));
	}
	
	public WebElement getLabelEmptyCartWarning() {

		return getDriver().findElement(By.xpath("//p[text()='Your shopping cart is empty.']"));
	}
	
	
	public ExpectedCondition<Boolean> titleToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getPageHeader().getText().trim().equals("Shopping-cart summary");
		}
	};

	public ExpectedCondition<Boolean> emptyWarningToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getLabelEmptyCartWarning().isDisplayed();
		}
	};
}
