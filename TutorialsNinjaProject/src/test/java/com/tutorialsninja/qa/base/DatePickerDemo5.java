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


public class DatePickerDemo5 extends Base{

	public DatePickerDemo5() throws IOException {
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
		String month = "March";
		String year = "2024";
		String day = "22";
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//*[@id='datepicker']")).click();
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
		
		
	}

}
