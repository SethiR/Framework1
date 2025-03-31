package Selenium.Testpackage;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Selenium.BaseTest.BaseTest;
import Selenium.Pages.CartPage;
import Selenium.Pages.LoginPage;
import Selenium.Pages.OrderConfirmationPage;
import Selenium.Pages.OrderPage;
import Selenium.Pages.PaymentPage;
import Selenium.Pages.productCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	WebDriver driver;
	@Test(groups= {"Regression"})
	public void submitOrder() throws InterruptedException, IOException
	{	String[] itemNeeded= {"ADIDAS ORIGINAL","ZARA COAT 3"};
		driver= InitializeDriver();
		
		LoginPage lp = new LoginPage(driver);
		productCatalogue pc=lp.goToSite("rohit0007sethi@gmail.com", "Password@135");
		
		CartPage cp=pc.AddToCard(itemNeeded);
		cp.selectCartButton();
		PaymentPage pp=cp.selectCheckout();
	
		
	}
	
	@Test
	public void addOrders() throws IOException
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
	
	@Test(dependsOnMethods= {"addOrders"})
	public void OrderHistory() throws IOException, InterruptedException
	{	String[] itemNeeded= {"ADIDAS ORIGINAL","ZARA COAT 3"};
		driver= InitializeDriver();
		LoginPage lp = new LoginPage(driver);
		productCatalogue pc=lp.goToSite("rohit0007sethi@gmail.com", "Password@135");
		pc.goToOrders();
		OrderPage op= new OrderPage(driver);
		Assert.assertTrue(op.verifyOrderHistory(itemNeeded));
	
	}
	
}
