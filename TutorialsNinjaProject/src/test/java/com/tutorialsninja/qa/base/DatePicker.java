package com.tutorialsninja.qa.base;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DatePicker extends Base{

	public DatePicker() throws IOException {
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
		driver.findElement(By.xpath("//*[@id='date_form_field-btn']")).click();
		String month = "October 2023";	
		String startDate = "22";
		String endDate="24";
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='uitk-date-picker date-picker-menu']")));

		String monthVal = driver.findElement(By.xpath("(//h2[@class='uitk-date-picker-month-name uitk-type-medium'])[1]")).getText();
		System.out.println(monthVal);
		
		while(!(monthVal.equals(month))) {
			driver.findElement(By.xpath("(//*[@data-stid='date-picker-paging'])[2]")).click();
			monthVal = driver.findElement(By.xpath("(//h2[@class='uitk-date-picker-month-name uitk-type-medium'])[1]")).getText();
		}
		System.out.println(monthVal);
		driver.findElement(By.xpath("(//*[@data-day="+startDate+"])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[@data-day="+endDate+"])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@data-stid='apply-date-picker']")).click();

		
	}

}
