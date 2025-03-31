package Selenium.Testpackage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Selenium.BaseTest.BaseTest;
import Selenium.BaseTest.Retry;
import Selenium.Pages.CartPage;
import Selenium.Pages.LoginPage;
import Selenium.Pages.OrderConfirmationPage;
import Selenium.Pages.PaymentPage;
import Selenium.Pages.productCatalogue;

public class ErrorValidationTest extends BaseTest {
	
	WebDriver driver;
	
	
	@Test(dataProvider="getData", groups= {"Smoke"})//,retryAnalyzer= Retry.class)
	public void validateSignout(HashMap<String, String> input) throws IOException, InterruptedException 
	{
	
		driver= InitializeDriver();
		LoginPage lp = new LoginPage(driver);
		productCatalogue pc=lp.goToSite(input.get("email"), input.get("password"));
		String signOutToast=lp.goToSignOut();
		Assert.assertEquals(signOutToast, "Logout Successfully");
	}

	@Test(groups= {"Regression"})
	public void validateOrderConfirmation() throws IOException
	{
		String[] itemNeeded= {"ADIDAS ORIGINAL","ZARA COAT 3"};
		driver= InitializeDriver();
		
		LoginPage lp = new LoginPage(driver);
		productCatalogue pc=lp.goToSite("rohit0007sethi@gmail.com", "Password@135");
		CartPage cp=pc.AddToCard(itemNeeded);
		cp.selectCartButton();
		PaymentPage pp=cp.selectCheckout();
		pp.creditcardDetails();
		pp.shippingDetails();
		OrderConfirmationPage ocp=pp.placeOrder();
		Assert.assertEquals(ocp.msgConfirmation(), "THANKYOU FOR THE ORDER.");
	}
	
	@Test(groups= {"Regression"})
	public void validatecartProduct() throws IOException
	{
		String[] itemNeeded= {"ADIDAS ORIGINAL","ZARA COAT 3"};
		driver= InitializeDriver();
		
		LoginPage lp = new LoginPage(driver);
		productCatalogue pc=lp.goToSite("rohit0007sethi@gmail.com", "Password@135");
		CartPage cp=pc.AddToCard(itemNeeded);
		cp.selectCartButton();
		Assert.assertTrue(cp.cartpdtValidation(itemNeeded));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{	
		List<HashMap<String,String>> data = jasonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium\\TestData\\DataReader.json");
		return new Object[][]{ {data.get(0)}, {data.get(1)}};
		
	}
	
	
}
