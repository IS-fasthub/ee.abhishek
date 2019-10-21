package practice.automation.test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import practice.automation.test.pages.CartPage;
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
	private ExtentTest test = reports.startTest("EasternEnterprise");
	
	@Test
    public void assignmentOne() {
    	
		SoftAssert assert_soft = new SoftAssert();
    	String username = Utilities.getPropertyValue("assignment1.username");
    	//password from test.properties file
    	String password = Utilities.getPropertyValue("assignment1.password");
    	//loggedInUser from test.properties file
    	String loggedInUser = Utilities.getPropertyValue("assignment1.loggedInUser");
    	
    	GuestHomePage guestHomePage = new GuestHomePage();
    	guestHomePage.waitForPageToLoad();
    	guestHomePage.getLinkSignIn().click();
    	
    	LoginPage loginPage = new LoginPage();
    	WebDriverWait wait = loginPage.getWebDriverWait(30);
    	wait.until(loginPage.logInFormToAppear);
    	
    	loginPage.getEmailField().sendKeys(username);
    	loginPage.getPasswordField().sendKeys(password);
    	loginPage.getButtonSignIn().click();
    	
    	
    	
    	MemberHomePage memberHomePage = new MemberHomePage();
    	wait.until(memberHomePage.loggedInUsernameToAppear);
    	if(memberHomePage.getLoggedInUser().isDisplayed())
    		test.log(LogStatus.PASS, "Login successful");
    	else
    		test.log(LogStatus.FAIL, "Login failed");
    	
    	memberHomePage.getSearchTextBox().sendKeys("t-shirt"+Keys.ENTER);
    	
    	SearchResultPage searchResultPage = new SearchResultPage();
    	wait.until(searchResultPage.resultToAppear);
    	
    	String itemPrice = searchResultPage.getProductPriceByIndex(0).getText().trim().replaceAll("\\$", "");
    	
    	Actions act = new Actions(getDriver());
    	act.moveToElement(searchResultPage.getProductByIndex(0)).perform();
    	searchResultPage.getButtonAddToCartByIndex(0).click();
    	
    	ProductAddedPopUp popUp = new ProductAddedPopUp();
    	wait.until(popUp.popUpToAppear);
    	popUp.getButtonContinueShopping().click();
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	Actions act1 = new Actions(getDriver());
    	SearchResultPage searchResultPage1 = new SearchResultPage();
    	act1.moveToElement(searchResultPage1.getProductByIndex(0)).perform();
    	searchResultPage1.getButtonAddToCartByIndex(0).click();
    	
    	ProductAddedPopUp popUp1 = new ProductAddedPopUp();
    	wait.until(popUp1.popUpToAppear);
    	popUp1.getButtonProceedToCheckout().click();
    	
    	CartPage cartPage = new CartPage();
    	cartPage.waitForPageToLoad();
    	
    	double unitPrice = Double.parseDouble(cartPage.getUnitPrice().getText().trim().replaceAll("\\$", ""));
    	int itemQuantity = Integer.parseInt(cartPage.getQuantity().getAttribute("value"));
    	double total = Double.parseDouble(cartPage.getCartTotal().getText().trim().replaceAll("\\$", ""));
    	double totalShipping = Double.parseDouble(cartPage.getTotalShipping().getText().trim().replaceAll("\\$", ""));
    	
    	if(total==unitPrice*itemQuantity)
    		test.log(LogStatus.PASS, "Total is as per quantity");
    	else
    		test.log(LogStatus.FAIL, "Total is not as per quantity");
    	
    	double tax = Double.parseDouble(cartPage.getTaxAmount().getText().trim().replaceAll("\\$", ""));
    	double totalAmount = Double.parseDouble(cartPage.getTotalAmount().getText().trim().replaceAll("\\$", ""));
    	
    	
    	if(totalAmount==total+totalShipping+tax)
    		test.log(LogStatus.PASS, "Total amount includes tax amount");
    	else
    		test.log(LogStatus.FAIL, "Total amount does not includes tax amount");
    	
    	
    	act.moveToElement(cartPage.getButtonCart()).perform();
    	
    	CartPage cartPage1 = new CartPage();
    	cartPage1.getButtonRemoveFromCart().click();
    	wait.until(cartPage1.emptyWarningToAppear);
    	
    	cartPage1.getButtonSignOut().click();
    	reports.endTest(test);
    	reports.flush();
	}
}
