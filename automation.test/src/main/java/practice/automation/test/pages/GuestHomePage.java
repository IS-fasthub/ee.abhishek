package practice.automation.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GuestHomePage extends BasePage{

	public WebElement getLinkSignIn() {

		return getDriver().findElement(By.xpath("//a[@class='login']"));
	}
}
