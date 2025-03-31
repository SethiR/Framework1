package Selenium.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.BaseClass;

public class productCatalogue extends BaseClass{
	WebDriver driver;
	public productCatalogue(WebDriver driver)
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By pdName = By.xpath("//div[@class='card-body']/h5/b");
	@FindBy(xpath="//div[contains(@class,'ngx-spinner-overlay')]")
	WebElement spinner;
	
	@FindBy(xpath="//div[@class='card-body']/h5/b")
	List<WebElement> productNames;
	
	@FindBy(xpath="//div[@class='card-body']/button[2]")
	List<WebElement> cartButton;
	
	@FindBy(xpath="//div[@aria-label='Product Added To Cart']")
	WebElement toastMsg;
	
	
	public CartPage AddToCard(String[] itemNeeded)
	{
		waitForElementsToBeLocated(pdName);
		for(int j=0; j<itemNeeded.length;j++)
		{   for(int i=0;i<productNames.size();i++)
			{
				if(productNames.get(i).getText().equalsIgnoreCase(itemNeeded[j]))
				{
					cartButton.get(i).click();
					waitForElementToBeDissappear(spinner);
					waitForElementToBeAppear(toastMsg);  
					System.out.println(toastMsg.getText());
					break;
				}
			}
		}
		CartPage cp= new CartPage(driver);
		return cp;
	
	}
	
	
	
	
}
