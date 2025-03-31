package Selenium.AbstractComponents;

import java.time.Duration;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	WebDriverWait w ;
	WebDriver driver;
	public BaseClass(WebDriver driver)
	{
		this.driver = driver;
		this.w = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(xpath="//ul/li[5]/button")
	WebElement signOut;
	
	@FindBy(xpath="//div[@aria-label='Logout Successfully']")
	WebElement signOuttoast;
	
	
	@FindBy(xpath="//div[contains(@class,'ngx-spinner-overlay')]")
	WebElement spinner;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderButton;
	
	public void waitForElementsToBeLocated(By product)
	{
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(product));
	}

	public void waitForElementToBeLocated(By locator)
	{
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void waitForElementToBeDissappear(WebElement ele)
	{
		w.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitForElementToBeAppear(WebElement ele)
	{
		w.until(ExpectedConditions.visibilityOf(ele));
		
	}
	public void waitForElementToBeClickable(WebElement ele)
	{
		w.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void goToCart()
	{	
		waitForElementToBeDissappear(spinner);
		waitForElementToBeClickable(cart);
		cart.click();
	}
	public String goToSignOut() throws InterruptedException
	{	
		waitForElementToBeClickable(signOut);
		signOut.click();
		waitForElementToBeAppear(signOuttoast);
		String soutToast=signOuttoast.getText();
		return soutToast;
	}
	public void goToOrders()
	{	
		waitForElementToBeClickable(orderButton);
		orderButton.click();
	}
}
