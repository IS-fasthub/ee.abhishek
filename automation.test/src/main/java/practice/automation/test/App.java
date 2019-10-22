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
	private ExtentReports reports = new ExtentReports("ExtentReport.html");
	
	
	@Test
    public void happyFlow() {
    	
		ExtentTest happyFlow = reports.startTest("Happy Flow");
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
    	
    	if(memberHomePage.getLoggedInUser().isDisplayed())
    		happyFlow.log(LogStatus.PASS, "Login successful");
    	else
    		happyFlow.log(LogStatus.FAIL, "Login failed");
    	
    	memberHomePage.getSearchTextBox().sendKeys("t-shirt"+Keys.ENTER);
    	
    	SearchResultPage searchResultPage = new SearchResultPage();
    	wait.until(searchResultPage.resultToAppear);
    	
    	String itemPrice = searchResultPage.getProductPriceByIndex(0).getText().trim().replaceAll("\\$", "");
    	
    	Utilities.moveToWebElement(getDriver(), searchResultPage.getProductByIndex(0));
    	searchResultPage.getButtonAddToCartByIndex(0).click();
    	
    	ProductAddedPopUp popUp = new ProductAddedPopUp();
    	wait.until(popUp.popUpToAppear);
    	popUp.getButtonContinueShopping().click();
    	
    	Utilities.sleep(2000);
    	
    	SearchResultPage searchResultPage1 = new SearchResultPage();
  
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
    	
    	if(total==unitPrice*itemQuantity)
    		happyFlow.log(LogStatus.PASS, "Total is as per quantity");
    	else
    		happyFlow.log(LogStatus.FAIL, "Total is not as per quantity");
    	
    	double tax = Double.parseDouble(cartPage.getTaxAmount().getText().trim().replaceAll("\\$", ""));
    	double totalAmount = Double.parseDouble(cartPage.getTotalAmount().getText().trim().replaceAll("\\$", ""));
    	
    	
    	if(totalAmount==total+totalShipping+tax)
    		happyFlow.log(LogStatus.PASS, "Total amount includes tax amount");
    	else
    		happyFlow.log(LogStatus.FAIL, "Total amount does not includes tax amount");
    	
    	
    	Utilities.moveToWebElement(getDriver(), cartPage.getButtonCart());
    	
    	CartSummaryPage cartPage1 = new CartSummaryPage();
    	cartPage1.getButtonRemoveFromCart().click();
    	wait.until(cartPage1.emptyWarningToAppear);
    	
    	cartPage1.getButtonSignOut().click();
    	reports.endTest(happyFlow);
    	reports.flush();
	}
	
	@Test
    public void unhappyFlow() {
    	
		ExtentTest unHappyFlow = reports.startTest("Unhappy Flow");
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
    	
    	if(memberHomePage.getLoggedInUser().isDisplayed())
    		unHappyFlow.log(LogStatus.PASS, "Login successful");
    	else
    		unHappyFlow.log(LogStatus.FAIL, "Login failed");
    	
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
    	
    	if(total==unitPrice*itemQuantity)
    		unHappyFlow.log(LogStatus.PASS, "Total is as per quantity");
    	else
    		unHappyFlow.log(LogStatus.FAIL, "Total is not as per quantity");
    	
    	double tax = Double.parseDouble(cartSummaryPage.getTaxAmount().getText().trim().replaceAll("\\$", ""));
    	double totalAmount = Double.parseDouble(cartSummaryPage.getTotalAmount().getText().trim().replaceAll("\\$", ""));
    	
    	
    	if(totalAmount==total+totalShipping+tax)
    		unHappyFlow.log(LogStatus.PASS, "Total amount includes tax amount");
    	else
    		unHappyFlow.log(LogStatus.FAIL, "Total amount does not includes tax amount");
    	
    	
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
    	
    	if(cartPaymentPage1.getLabelSuccessMessage().getText().contains("Your order on My Store is complete."))
    		unHappyFlow.log(LogStatus.PASS, "Order placed successfully");
    	else
    		unHappyFlow.log(LogStatus.FAIL, "Order placement failed");
    		
    	cartPaymentPage1.getButtonSignOut().click();
    	reports.endTest(unHappyFlow);
    	reports.flush();
	}
}
