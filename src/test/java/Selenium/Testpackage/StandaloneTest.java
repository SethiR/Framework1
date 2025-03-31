package Selenium.Testpackage;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class StandaloneTest {
	
	@Test
	public void Full() throws InterruptedException
	{	String[] itemNeeded= {"ADIDAS ORIGINAL","ZARA COAT 3"};
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("rohit0007sethi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@135");
		driver.findElement(By.id("login")).click();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card-body']/h5/b")));
		List<WebElement> productNames =driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
		
		for(int j=0; j<itemNeeded.length;j++)
		{   for(int i=0;i<productNames.size();i++)
			{
				if(productNames.get(i).getText().equalsIgnoreCase(itemNeeded[j]))
				{
					driver.findElements(By.xpath("//div[@class='card-body']/button[2]")).get(i).click();
					w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'ngx-spinner-overlay')]"))));
					w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']"))));  
					System.out.println(driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']")).getText());
					break;
				}
			}
		
		}
		
		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<String> item = Arrays.asList(itemNeeded);
		List<WebElement> reconfirm= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		 System.out.println(item);
		for(int i=0;i<reconfirm.size();i++)
		{
		Assert.assertTrue(reconfirm.get(i).getText().equalsIgnoreCase(itemNeeded[i]));
		}
	
		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='Checkout']"))));
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item.list-group-item.ng-star-inserted")));
		List<WebElement> countries=driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
		for(int i=0;i<countries.size();i++)	
		{	System.out.println(countries.get(i).getText());
			if(countries.get(i).getText().equalsIgnoreCase("India"))
			{	
				countries.get(i).click();
				break;
			}
			
		}
		
		driver.findElement(By.xpath("//div[@class='row']/div[@class='field']/input")).clear();
		driver.findElement(By.xpath("//div[@class='row']/div[@class='field']/input")).sendKeys("Rohit Sethi");
		driver.findElement(By.xpath("//div[@class='row'][3]/div[@class='field']/input")).sendKeys("Rohit Sethi");
		driver.findElement(By.xpath("//div[@class='field small']/input[1]")).sendKeys("456");
		driver.findElement(By.name("coupon")).sendKeys("Coupon");
		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated((By.tagName("h1"))));
		String text= driver.findElement(By.tagName("h1")).getText();
		Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
		driver.close();
	}
	
}
