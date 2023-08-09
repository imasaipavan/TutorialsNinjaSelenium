package com.tutorialsninja.qa.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Base {

	 
	public static WebDriver driver;

	public static Properties prop;

	public static Logger logger;
	
	public Base() {
		logger = Logger.getLogger("Tutorials Ninja");
		PropertyConfigurator.configure("log4j.properties");	
		prop= new Properties();
		
		try(FileInputStream fis=new FileInputStream(new File("./src/main/java/com/tutorialsninja/qa"
				+ "/config/config.properties"))){
		prop.load(fis);}catch(Exception e) {
			//handle exception
		}
	}
	
	public static WebDriver launchBrowser(String browserName, String url) throws IOException
	{
		
		
			
//		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("EDGE"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		

//		driver.get(prop.getProperty("url"));
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		return driver;
	}

	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
	}

}
