package com.tutorialsninja.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class Listeners implements ITestListener{
	//	public static Properties prop;
	ExtentTest test;
	public static ExtentSparkReporter spark;
	String testName ;
	ExtentReports extent = ExtentReporter.generateExtentReport();
	
	@Override
	public void onTestStart(ITestResult result) {
		 testName = result.getName();
		test = extent.createTest(testName);
		test.log(Status.INFO, testName+ " Started Executing");
		System.out.println(testName+ " Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, testName+ " got Successfully executed");
		System.out.println(testName+ " got Successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		WebDriver driver =null;
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch(Exception e)
		{

		}
		String dest = Utilities.generateScreenshot(testName,driver);
		test.addScreenCaptureFromPath(dest);

		System.out.println(testName+ " got failed and screenshot taken");

	}

	@Override
	public void onStart(ITestContext context) {
		extent = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		spark = new ExtentSparkReporter("./Reports/"+timeStamp+"Report.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("SAI PAVAN");
		spark.config().setDocumentTitle("Tutorials Ninja Project");

		extent.attachReporter(spark);
		System.out.println("Test started");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, testName+ " got skipped");
		System.out.println(testName+ " got Skipped");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
