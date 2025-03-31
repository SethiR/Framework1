package Selenium.Pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.BaseClass;



public class OrderPage extends BaseClass{
	
	
	WebDriver driver;
	
	
	public OrderPage(WebDriver driver)
	{	
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderNames;
	
	public boolean verifyOrderHistory(String[] itemNeeded) throws InterruptedException
	{	
		for(int i=0;i<itemNeeded.length;i++)
		{
			for(int j=0;j<orderNames.size();j++)
			{
				String name = orderNames.get(j).getText();
				System.out.println(name);
				if(name.equalsIgnoreCase(itemNeeded[i]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	
//	public boolean verifyOrderHistory(String[] itemNeeded) throws InterruptedException
//	{	
//		for(int i=0;i<itemNeeded.length;i++)
//		{
//			for(int j=0;j<orderNames.size();j++)
//			{
//				String name = orderNames.get(j).getText();
//				System.out.println(name);
//				if(name.equalsIgnoreCase(itemNeeded[i]))
//				{
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//	public boolean verifyOrderHistory(String[] itemNeeded) throws InterruptedException
//	{	
//		for(int i=0;i<itemNeeded.length;i++)
//		{
//			for(int j=0;j<orderNames.size();j++)
//			{
//				String name = orderNames.get(j).getText();
//				System.out.println(name);
//				if(name.equalsIgnoreCase(itemNeeded[i]))
//				{
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	

}
