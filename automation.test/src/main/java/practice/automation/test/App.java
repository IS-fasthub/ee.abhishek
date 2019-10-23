package practice.automation.test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import practice.automation.test.pages.CartAddressPage;
import practice.automation.test.pages.CartPaymentPage;
import practice.automation.test.pages.CartShippingPage;
import practice.automation.test.pages.CartSummaryPage;
import practice.automation.test.pages.GuestHomePage;
import practice.automation.test.pages.LoginPage;
import practice.automation.test.pages.MemberHomePage;
import practice.automation.test.pages.ProductAddedPopUp;
import practice.automation.test.pages.SearchResultPage;
import practice.automation.test.utils.Utilities;

/**
 * Hello world!
 *
 */
public class App extends BaseTest
{
	@Test
    public void happyFlow() {
    	String username = Utilities.getPropertyValue("assignment1.username");
    	//password from test.properties file
    	String password = Utilities.getPropertyValue("assignment1.password");
    	//loggedInUser from test.properties file
    	String loggedInUser = Utilities.getPropertyValue("assignment1.loggedInUser");
    	WebDriverWait wait = getWebDriverWait(30);
    	
    	GuestHomePage guestHomePage = new GuestHomePage();
    	guestHomePage.waitForPageToLoad();
    	guestHomePage.getLinkSignIn().click();
    	
    	LoginPage loginPage = new LoginPage();
    	wait.until(loginPage.logInFormToAppear);
    	reportLogger("Logging in with "+username);
    	
    	loginPage.signIn(username, password);
    	MemberHomePage memberHomePage = new MemberHomePage();
    	wait.until(memberHomePage.loggedInUsernameToAppear);
    	
    	verify(memberHomePage.getLoggedInUser().isDisplayed(),"Login successful", "Login failed");

    	memberHomePage.getSearchTextBox().sendKeys("t-shirt"+Keys.ENTER);
    	
    	SearchResultPage searchResultPage = new SearchResultPage();
    	wait.until(searchResultPage.resultToAppear);
    	reportLogger("Search result for t-shirt");
    	
    	String itemPrice = searchResultPage.getProductPriceByIndex(0).getText().trim().replaceAll("\\$", "");
    	
    	Utilities.moveToWebElement(getDriver(), searchResultPage.getProductByIndex(0));
    	searchResultPage.getButtonAddToCartByIndex(0).click();
    	
    	ProductAddedPopUp popUp = new ProductAddedPopUp();
    	wait.until(popUp.popUpToAppear);
    	popUp.getButtonContinueShopping().click();
    	
    	Utilities.sleep(2000);
    	
    	SearchResultPage searchResultPage1 = new SearchResultPage();
    	
    	reportLogger("Adding item to cart");
    	Utilities.moveToWebElement(getDriver(), searchResultPage1.getProductByIndex(0));
    	searchResultPage1.getButtonAddToCartByIndex(0).click();
    	
    	ProductAddedPopUp popUp1 = new ProductAddedPopUp();
    	wait.until(popUp1.popUpToAppear);
    	popUp1.getButtonProceedToCheckout().click();
    	
    	CartSummaryPage cartPage = new CartSummaryPage();
    	cartPage.waitForPageToLoad();
    	
    	double unitPrice = Double.parseDouble(cartPage.getUnitPrice().getText().trim().replaceAll("\\$", ""));
    	int itemQuantity = Integer.parseInt(cartPage.getQuantity().getAttribute("value"));
    	double total = Double.parseDouble(cartPage.getCartTotal().getText().trim().replaceAll("\\$", ""));
    	double totalShipping = Double.parseDouble(cartPage.getTotalShipping().getText().trim().replaceAll("\\$", ""));
    	
    	verify(total==unitPrice*itemQuantity, "Total is as per quantity", "Total is not as per quantity");
    	
    	double tax = Double.parseDouble(cartPage.getTaxAmount().getText().trim().replaceAll("\\$", ""));
    	double totalAmount = Double.parseDouble(cartPage.getTotalAmount().getText().trim().replaceAll("\\$", ""));
    	
    	
    	verify(totalAmount==total+totalShipping+tax, "Total amount includes tax amount", "Total amount does not includes tax amount");
    	
    	
    	Utilities.moveToWebElement(getDriver(), cartPage.getButtonCart());
    	
    	CartSummaryPage cartPage1 = new CartSummaryPage();
    	cartPage1.getButtonRemoveFromCart().click();
    	wait.until(cartPage1.emptyWarningToAppear);
    	
    	cartPage1.getButtonSignOut().click();
		
	}
	
	@Test
    public void unhappyFlow() {
    	
		String username = Utilities.getPropertyValue("assignment1.username");
    	//password from test.properties file
    	String password = Utilities.getPropertyValue("assignment1.password");
    	//loggedInUser from test.properties file
    	String loggedInUser = Utilities.getPropertyValue("assignment1.loggedInUser");
    	WebDriverWait wait = getWebDriverWait(30);
    	
    	GuestHomePage guestHomePage = new GuestHomePage();
    	guestHomePage.waitForPageToLoad();
    	guestHomePage.getLinkSignIn().click();
    	
    	LoginPage loginPage = new LoginPage();
    	wait.until(loginPage.logInFormToAppear);
    	loginPage.signIn(username, password);

    	MemberHomePage memberHomePage = new MemberHomePage();
    	wait.until(memberHomePage.loggedInUsernameToAppear);
    	
    	verify(memberHomePage.getLoggedInUser().isDisplayed(), "Login successful", "Login failed");
    	
    	memberHomePage.getSearchTextBox().sendKeys("t-shirt"+Keys.ENTER);
    	
    	SearchResultPage searchResultPage = new SearchResultPage();
    	wait.until(searchResultPage.resultToAppear);
    	
    	String itemPrice = searchResultPage.getProductPriceByIndex(0).getText().trim().replaceAll("\\$", "");
    	
    	Utilities.moveToWebElement(getDriver(), searchResultPage.getProductByIndex(0));
    	searchResultPage.getButtonAddToCartByIndex(0).click();
    	
    	ProductAddedPopUp popUp = new ProductAddedPopUp();
    	wait.until(popUp.popUpToAppear);
    	popUp.getButtonProceedToCheckout().click();
    	
    	CartSummaryPage cartSummaryPage = new CartSummaryPage();
    	cartSummaryPage.waitForPageToLoad();
    	
    	double unitPrice = Double.parseDouble(cartSummaryPage.getUnitPrice().getText().trim().replaceAll("\\$", ""));
    	int itemQuantity = Integer.parseInt(cartSummaryPage.getQuantity().getAttribute("value"));
    	double total = Double.parseDouble(cartSummaryPage.getCartTotal().getText().trim().replaceAll("\\$", ""));
    	double totalShipping = Double.parseDouble(cartSummaryPage.getTotalShipping().getText().trim().replaceAll("\\$", ""));
    	
    	verify(total==unitPrice*itemQuantity, "Total is as per quantity", "Total is not as per quantity");
    	
    	double tax = Double.parseDouble(cartSummaryPage.getTaxAmount().getText().trim().replaceAll("\\$", ""));
    	double totalAmount = Double.parseDouble(cartSummaryPage.getTotalAmount().getText().trim().replaceAll("\\$", ""));
    	
    	
    	verify(totalAmount==total+totalShipping+tax, "Total amount includes tax amount", "Total amount does not includes tax amount");
    	
    	
    	CartSummaryPage cartSummaryPage1 = new CartSummaryPage();
	
    	cartSummaryPage1.getButtonProceedToCheckout().click();
    	Utilities.sleep(2000);
    
    	CartAddressPage cartAddressPage = new CartAddressPage();
    	cartAddressPage.getButtonProceedToCheckout().click();
    	Utilities.sleep(2000);
    	
    	CartShippingPage cartShippingPage = new CartShippingPage();
    	cartShippingPage.getCheckboxTAndC().click();
    	cartShippingPage.getButtonProceedToCheckout().click();
    	Utilities.sleep(2000);
    	
    	CartPaymentPage cartPaymentPage = new CartPaymentPage();
    	cartPaymentPage.getLinkPayByCheque().click();
    	Utilities.sleep(2000);
    	
    	CartPaymentPage cartPaymentPage1 = new CartPaymentPage();
    	cartPaymentPage1.getButtonConfirmOrder().click();
    	
    	verify(cartPaymentPage1.getLabelSuccessMessage().getText().contains("Your order on My Store is complete."), "Order placed successfully", "Order placement failed");
    		
    	cartPaymentPage1.getButtonSignOut().click();
		
	}
}
