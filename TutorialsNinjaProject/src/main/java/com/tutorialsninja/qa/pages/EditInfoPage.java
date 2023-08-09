package com.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EditInfoPage {
	
	public WebDriver driver;
	
	public EditInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	/*
	 
	@FindBy(how = How.XPATH, using= "(//a[@href='https://tutorialsninja.com/demo/index.php?route=account/edit'])[1]")
	private static WebElement editAccInfo;
	
	@FindBy(how = How.ID, using= "input-firstname")
	private static WebElement firstName;
	
	@FindBy(how = How.XPATH, using= "input-firstname")
	private static WebElement lastName;
	
	@FindBy(how = How.XPATH, using= "")
	private static WebElement phoneNumber;
	
	@FindBy(how = How.XPATH, using= "//input[@type='submit']")
	private static WebElement submit;
	
	@FindBy(how = How.XPATH, using= "//*[@class='alert alert-success alert-dismissible']")
	private static WebElement successMessage;
	
	*/
	
	
	
	public static WebElement successMessage(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@class='alert alert-success alert-dismissible']"));
	}
	
	public static WebElement firstName(WebDriver driver) {
		return driver.findElement(By.id("input-firstname"));
	}
	
	public static WebElement lastName(WebDriver driver) {
		return driver.findElement(By.id("input-lastname"));
	}
	
	public static WebElement submit(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@type='submit']"));
	}
	
	public static WebElement editAccLink(WebDriver driver) {
		return driver.findElement(By.xpath("(//a[@href='https://tutorialsninja.com/demo/index.php?route=account/edit'])[1]"));
	}
	
	
		
	public static String editAccInfo(WebDriver driver) throws InterruptedException {
		editAccLink(driver).click();
		Thread.sleep(2000);
		firstName(driver).clear();
		firstName(driver).sendKeys("SAI");
		Thread.sleep(2000);
		lastName(driver).clear();
		lastName(driver).sendKeys("PAVAN");
		Thread.sleep(2000);
		submit(driver).click();
		Thread.sleep(2000);
		String message = successMessage(driver).getText();
		return message;
		
	}
}
