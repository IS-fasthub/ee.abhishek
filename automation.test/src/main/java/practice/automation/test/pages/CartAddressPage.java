package practice.automation.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CartAddressPage extends BaseCartPage {

	public WebElement getPageHeader() {

		return getDriver().findElement(By.xpath("//h1"));
	}

	
}
