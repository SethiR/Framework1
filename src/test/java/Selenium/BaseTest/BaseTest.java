package Selenium.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	public WebDriver InitializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium\\TestData\\GlobalData.properties");
		prop.load(fis);
		String browserName= (System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser"));
		
		if(browserName.contains("chrome"))
		{  	
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			WebDriverManager.chromedriver().setup();
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			WebDriverManager.firefoxdriver().setup();
		}
		driver.manage().window().maximize();
		return driver;
	
	}
	
	@AfterMethod(alwaysRun=true)
	public void close()
	{
		if (driver != null) 
		{   
			driver.quit();
		}
	}
	
	public List<HashMap<String, String>> jasonToMap(String filePath) throws IOException
	{	String jasonContent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>>data= mapper.readValue(jasonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenShot(String testCase, WebDriver driver) throws IOException
	{	
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//reports//"+testCase+".png"));
		return System.getProperty("user.dir")+"//reports//"+testCase+".png";
	}
	
	
}
