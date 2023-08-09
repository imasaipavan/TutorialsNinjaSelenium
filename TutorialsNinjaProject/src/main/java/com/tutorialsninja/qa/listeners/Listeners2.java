package com.tutorialsninja.qa.listeners;

import java.io.File;
import java.io.IOException;

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

public class Listeners2  implements ITestListener{
	//	public static Properties prop;
	ExtentTest test;
	public static ExtentSparkReporter spark;
	String testName ;
	ExtentReports extent = ExtentReporter.generateExtentReport();
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		test = extent.createTest(testName);
		extentTest.set(test);
		test.log(Status.INFO, testName+ " Started Executing");
		System.out.println(testName+ " Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS,testName + "Test Passed");

		test.log(Status.PASS, testName+ " got Successfully executed");
		System.out.println(testName+ " got Successfully executed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		WebDriver driver =null;
		String testMethodName =result.getMethod().getMethodName();
		
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch(Exception e)
		{
			
		}
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"/Reports/"+testName+".png";
				try {
					FileUtils.copyFile(src, new File(dest));
					org.openqa.selenium.io.FileHandler.copy(src, new File(dest));
		
				} catch (IOException e) {
					e.printStackTrace();
				}
				test.addScreenCaptureFromPath(dest);
			

		
		System.out.println(testName+ " got failed and screenshot taken");
	
	}

	@Override
	public void onStart(ITestContext context) {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("./extentReport.html");
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
