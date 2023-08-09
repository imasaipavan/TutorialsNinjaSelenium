package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base{
	public SearchTest() throws IOException {
		super();
	}
	public WebDriver driver;
	
	@Parameters({"browserName","url"})
	@BeforeMethod
	public void setup(String browserName, String url) throws Exception {
		driver = launchBrowser(browserName,url);
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
		VideoRecorder_utlity.stopRecord();		

	}
	
	@Test(priority = 1)
	public void searchForValidProduct() throws Exception {
		VideoRecorder_utlity.startRecord("Search for a valid product");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("HP");
		driver.findElement(By.xpath("//*[@class='fa fa-search']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed()," Search is not working");
		
	}
	
	@Test(priority = 2)
	public void searchForInvalidProduct() throws Exception {
		VideoRecorder_utlity.startRecord("Search for a invalid product");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("asdfafadsfa");
		driver.findElement(By.xpath("//*[@class='fa fa-search']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='content']/p[2]")).isDisplayed()," Search is not working");
		
	}
	@Test(priority = 3)
	public void searchWithEnteringAnything() throws Exception {
		VideoRecorder_utlity.startRecord("Search without entering any data");
		driver.findElement(By.xpath("//*[@class='fa fa-search']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='content']/p[2]")).isDisplayed()," Search is not working");
		
	}

}
