package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static Properties prop;
	public static ExtentReports report;

	public static ExtentReports generateExtentReport()  {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		File file = new File(System.getProperty("user.dir")+"/Reports/"+timeStamp+"Report1.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Tutorials Ninja Project");
		spark.config().setDocumentTitle("Automation Report");
		report = new ExtentReports();
		report.attachReporter(spark);
		
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("User name" , System.getProperty("user.name"));

		return report;
	}


}
