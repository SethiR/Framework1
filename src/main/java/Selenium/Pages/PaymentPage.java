package Selenium.Pages;





import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Selenium.AbstractComponents.BaseClass;

public class PaymentPage extends BaseClass{
	WebDriver driver;
	public PaymentPage(WebDriver driver)
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (xpath="//div[@class='row']/div[@class='field']/input")
	WebElement creditNo;
	
	@FindBy(xpath="//div[@class='field small']/input[1]")
	WebElement cvv;
	
	@FindBy(xpath="//div[@class='row'][3]/div[@class='field']/input")
	WebElement name;
	
	@FindBy(name="coupon")
	WebElement coupon;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	By countryList= By.cssSelector(".ta-item.list-group-item.ng-star-inserted");
	
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	List<WebElement> counList;
	
	@FindBy(xpath="//div[@class='actions']/a")
	WebElement placeOrder;
	
	public void creditcardDetails()
	{
		creditNo.clear();
		creditNo.sendKeys("4542 9931 9292 2293");
		cvv.sendKeys("456");
		name.sendKeys("Rohit Sethi");
		coupon.sendKeys("Coupon");
	}
	public void shippingDetails()
	{
		country.sendKeys("Ind");
		waitForElementsToBeLocated(countryList);

		for(int i=0;i<counList.size();i++)	
		{	System.out.println(counList.get(i).getText());
			if(counList.get(i).getText().equalsIgnoreCase("India"))
			{	
				counList.get(i).click();
				break;
			}
		}
			

	}
	public OrderConfirmationPage placeOrder()
	{
		placeOrder.click();
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		return ocp;
	}
	
	
	
}
