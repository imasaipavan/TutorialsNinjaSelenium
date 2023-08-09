package com.tutorialsninja.qa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public static WebDriver driver;
	static long timeStamp = System.currentTimeMillis();
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	/*
	 
	@FindBy(how = How.XPATH, using = "//*[@name='email(driver)']")
	public static WebElement email(driver);
	
	@FindBy(how = How.XPATH, using = "//*[@name='password']")
	public static WebElement password;
	
	@FindBy(how = How.XPATH, using = "//*[@value='Login']")
	public static WebElement login;
	
	*/
	
	public static WebElement email(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@name='email']"));
	}

	public static WebElement password(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@name='password']"));
	}
	
	public static WebElement login(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@value='Login']"));
	}
	
	public static String loginWithValidCredentials(WebDriver driver) {
		email(driver).clear();
		email(driver).sendKeys("yep@yopmail.com");
		password(driver).clear();
		password(driver).sendKeys("yep@yopmail.com");
		login(driver).click();
		String title = driver.getTitle();
		return title;
		
	}
	
	public static String loginWithInvalidCredentials(WebDriver driver) {
		email(driver).clear();
		email(driver).sendKeys(timeStamp+"@yopmail.com");
		password(driver).clear();
		password(driver).sendKeys("yep@yopmail.com");
		login(driver).click();
		String title = driver.getTitle();
		return title;
		
	}
	
	public static String loginWithValidMailAndInvalidPassword(WebDriver driver) {
		email(driver).clear();
		email(driver).sendKeys("yep@yopmail.com");
		password(driver).clear();
		password(driver).sendKeys("yepasdfm");
		login(driver).click();
		String title = driver.getTitle();
		return title;
		
	}
	public static String loginWithInvalidMailAndValidPassword(WebDriver driver) {
		email(driver).clear();
		email(driver).sendKeys("yep@yopasdffamail.com");
		password(driver).clear();
		password(driver).sendKeys("yep@yopmail.com");
		login(driver).click();
		String title = driver.getTitle();
		return title;
		
	}

}
