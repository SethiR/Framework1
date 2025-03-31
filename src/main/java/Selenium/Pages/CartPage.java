package Selenium.Pages;


import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Selenium.AbstractComponents.BaseClass;

public class CartPage extends BaseClass{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> reconfirm;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	boolean value=false;
	
	public void selectCartButton()
	{
		goToCart();
	}
	public boolean cartpdtValidation(String[] itemNeeded)
	{
		goToCart();
		for(int i=0;i<reconfirm.size();i++)
		{
			System.out.println(reconfirm.get(i).getText());
			if(reconfirm.get(i).getText().equalsIgnoreCase(itemNeeded[i]));
			{
				value=true;
			}
			
		}
		return value;
	
	}
	public PaymentPage selectCheckout()
	{
		waitForElementToBeClickable(checkout);
		checkout.click();
		PaymentPage pp = new PaymentPage(driver);
		return pp;
	}
	
	
	
	
}
