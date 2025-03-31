package Selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Selenium.AbstractComponents.BaseClass;

public class OrderConfirmationPage extends BaseClass{
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver)
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By heading = By.tagName("h1");
	@FindBy (tagName="h1")
	WebElement msg;
	
	
	public String msgConfirmation()
	{
		waitForElementToBeLocated(heading);
		String text= msg.getText();
		return text;

	}
	

	
	
	
}
