package com.tutorialsninja.qa.base;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class DatePickerDemo6 extends Base{

	public DatePickerDemo6() throws IOException {
		super();


	}
	public static WebDriver driver;


	@Parameters({"browserName","url"})
	@BeforeMethod
	public void setup(String browserName, String url) throws InterruptedException, IOException {
		driver = launchBrowser(browserName,url);
		Thread.sleep(2000);
	}

	@Test
	public static void datePickerDemo() throws InterruptedException {
		String month = "August";
		String year = "2023";
		String day = "22";
		driver.findElement(By.xpath("//*[@id='datepicker1']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
		String monthVal = driver.findElement(By.className("ui-datepicker-month")).getText();
		String yearVal = driver.findElement(By.className("ui-datepicker-year")).getText();
		
		while(!(monthVal.equals(month)&&(yearVal.equals(year)))) {
			driver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			monthVal = driver.findElement(By.className("ui-datepicker-month")).getText();
			yearVal = driver.findElement(By.className("ui-datepicker-year")).getText();
		}
		driver.findElement(By.xpath("//a[text()="+day+"]")).click();
		
		
		
		
		
		
		
		
		
		/*
		String monthData= "November";
		String yearData= "2023";
		String dayData= "11";
		Thread.sleep(3000);
		driver.findElement(By.id("datepicker")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")));
		String titleName = driver.findElement(By.className("ui-datepicker-title")).getText();

		String monthVal = driver.findElement(By.xpath("//*[@class='ui-datepicker-month']")).getText();
		String yearVal = driver.findElement(By.xpath("//*[@class='ui-datepicker-year']")).getText();

		while(!(monthData.equals(monthVal)&&yearData.equals(yearVal)))
		{
			driver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			titleName = driver.findElement(By.className("ui-datepicker-title")).getText();
			System.out.println(titleName);
			monthVal=titleName.split(" ")[0].trim();
			yearVal=titleName.split(" ")[1].trim();
		}

		driver.findElement(By.xpath("//a[text()="+dayData+"]")).click();
		driver.quit();
		*/
	}

}
