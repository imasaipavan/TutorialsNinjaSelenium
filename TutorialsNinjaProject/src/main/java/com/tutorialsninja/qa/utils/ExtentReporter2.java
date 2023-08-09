package com.tutorialsninja.qa.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter2 {
	public static Properties prop;
	public static ExtentReports report;

	@Test
	public static void generateExtentReport()  {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		File file = new File(System.getProperty("user.dir")+"/Reports/"+timeStamp+"Report1.html");
		System.out.println(file);
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Tutorials Ninja Project");
		spark.config().setDocumentTitle("Automation Report");
		report = new ExtentReports();
		report.attachReporter(spark);
		
		ExtentTest test = report.createTest("Test1","This is a sample Selenium test");
		WebDriver driver = new ChromeDriver();
        driver.get("https://www.Google.com");
		test.log(Status.PASS, "Navigated to example.com");


		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("User name" , System.getProperty("user.name"));
		report.flush();

//		return report;
	}


}
