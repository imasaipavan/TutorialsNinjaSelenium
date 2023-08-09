package com.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	public static WebElement myAccount(WebDriver driver) {
		return driver.findElement(By.xpath("(//*[@class='hidden-xs hidden-sm hidden-md'])[3]"));
	}
	
	public static WebElement login(WebDriver driver) {
		return driver.findElement(By.linkText("Login"));
	}
	
	public static WebElement register(WebDriver driver) {
		return driver.findElement(By.linkText("Register"));
	}
	
	
	
}
