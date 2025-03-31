package Selenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.BaseClass;

public class LoginPage extends BaseClass{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id= "userEmail")
	WebElement email;
	
	@FindBy(id= "userPassword")
	WebElement pwd;
	
	@FindBy(id= "login")
	WebElement login;
	
	
	public productCatalogue goToSite(String user, String password)
	{
		driver.get("https://rahulshettyacademy.com/client/");
		email.sendKeys(user);
		pwd.sendKeys(password);
		login.click();
		productCatalogue pc = new productCatalogue(driver);
		return pc;
	}
	

	
	
}
