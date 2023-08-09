package com.tutorialsninja.qa.testcases;

import java.io.File;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base {
	public RegisterTest() throws IOException {
		super();
	}

	public WebDriver driver;
	RegisterPage register;

	@Parameters({"browserName","url"})
	@BeforeMethod
	public void setup(String browserName, String url) throws Exception {
		driver = launchBrowser(browserName,url);
		register = PageFactory.initElements(driver, RegisterPage.class);
		driver.findElement(By.xpath("(//*[@class='hidden-xs hidden-sm hidden-md'])[3]")).click();
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(2000);

	}

	@Test(enabled  = true, priority  = 1)
	public void validateRegister() throws Exception {
		register = PageFactory.initElements(driver, RegisterPage.class);
		VideoRecorder_utlity.startRecord("Register with new data");
		register.registerByEnteringAllMadatoryFields();

	}

	@Test(enabled  = true, priority  = 2)
	public void validateWithAlreadyRegisteredMail() throws Exception {
		register = PageFactory.initElements(driver, RegisterPage.class);
		VideoRecorder_utlity.startRecord("Register with existing email");
		register.registerWithAlreadyRegisteredMail();

	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
		VideoRecorder_utlity.stopRecord();		

	}

}
