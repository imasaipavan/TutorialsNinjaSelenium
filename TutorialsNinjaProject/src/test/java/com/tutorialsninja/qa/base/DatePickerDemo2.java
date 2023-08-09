package com.tutorialsninja.qa.base;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DatePickerDemo2 extends Base{

	public DatePickerDemo2() throws IOException {
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
		 driver.findElement(By.xpath("(//*[@id='departure'])[1]")).click();
		
		String month = "August 2023";	
		String date = "30";
		try {
			pickDate(date, month);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test(enabled = false)
	public static void pickDate( String date, String month) throws InterruptedException {
		
		while(true)
		{
			WebElement element = driver.findElement(By.xpath("(//*[@class='switch'][@colspan='5'])[7]"));
			String monthData = element.getText();
			System.out.println(monthData);
			if(monthData.equals(month)) {
				break;
			}else {
				driver.findElement(By.xpath("(//th[@class='next'])[7]")).click();
				Thread.sleep(2000);
			}
		}
		driver.findElement(By.xpath("(//tbody)[7]/tr/td[contains(text(),'"+date+"')]")).click();

	}

}
