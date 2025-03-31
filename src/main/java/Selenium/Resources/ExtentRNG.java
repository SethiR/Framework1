package Selenium.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentRNG {

	
	public static ExtentReports getReport(){
		String filepath = System.getProperty("user.dir")+"//reports/report.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
		reporter.config().setReportName("Test Results");
		reporter.config().setDocumentTitle("Web Automation Testing");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rohit Sethi");
		
		return extent;
	}
}