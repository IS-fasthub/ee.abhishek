package practice.automation.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class SearchResultPage extends BasePage {

	public List<WebElement> getProductList() {

		return getDriver().findElements(By.xpath("//ul[@class='product_list grid row']/li"));
	}

	public WebElement getProductByIndex(int index) {
		return getProductList().get(index).findElement(By.xpath("//div[@class='product-container']"));
	}
	
	public WebElement getProductPriceByIndex(int index) {
		return getProductList().get(index).findElement(By.xpath("//div[@class='right-block']//span[@itemprop='price']"));
	}
	
	public WebElement getButtonAddToCartByIndex(int index) {
		return getProductList().get(index).findElement(By.xpath("//a[@title='Add to cart']"));
	}
	
	public ExpectedCondition<Boolean> resultToAppear = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return getProductByIndex(0).isDisplayed();
		}
	};

}
