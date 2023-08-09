package com.tutorialsninja.qa.testcases;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
public class LoginTest extends Base {
	public LoginTest() throws IOException {
		super();


	}
	public WebDriver driver;
	HomePage home;
	LoginPage login;
	


	@Parameters({"browserName","url"})
	@BeforeMethod
	public void setup(String browserName, String url) throws Exception {
		driver = launchBrowser(browserName,url);

		home = new HomePage(driver);
		home.myAccount(driver).click();
		home.login(driver).click();
		Thread.sleep(2000);
		

	}


	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
		VideoRecorder_utlity.stopRecord();	

	}


	@DataProvider(name = "getDataManually")
	public Object[][] loginData(){
		Object[][] data = {{"yep@yopmail.com","yep@yopmail.com"},
				{"yep1@yopmail.com","yep1@yopmail.com"},
				{"yep2@yopmail.com","yep2@yopmail.com"}};

		return data;
	}

	@DataProvider(name = "getDataFromExcel")
	public Object[][] loginDataFromExcel() throws IOException{
		Object[][] data = Utilities.dataProviderMethod();

		return data;
	}


	@Test(priority = 1, enabled = true)
	public void verifyWithValidCredentails() throws Exception {
		VideoRecorder_utlity.startRecord("Login with Valid Credentials");
		String Dashboard= login.loginWithValidCredentials(driver);
		Thread.sleep(2000);
		AssertJUnit.assertEquals(Dashboard, "My Account");
		driver.quit();
		VideoRecorder_utlity.stopRecord();
	}

	@Test(enabled = true,priority = 2 )
	public void verifyWithInvalidCredentials() throws Exception {
		VideoRecorder_utlity.startRecord("Login with Invalid Credentials");
		String warning_Alert = login.loginWithInvalidCredentials(driver);
		Thread.sleep(2000);
		AssertJUnit.assertEquals(warning_Alert, "Account Login");
		driver.quit();

	}

	@Test(enabled = false,priority = 3)
	public void verifyWithValidMailAndInvalidPassword() throws Exception {
		VideoRecorder_utlity.startRecord("Login with valid mail and invalid password");
		String warning_Alert = login.loginWithInvalidMailAndValidPassword(driver);
		Thread.sleep(2000);
//		String warning_Alert = "Warning: No match for E-Mail Address and/or Password.";
		AssertJUnit.assertEquals(warning_Alert, "Account Login");
		driver.quit();

	}
	@Test(enabled = false,priority = 4)
	public void verifyWithInvalidMailAndValidPassword() throws Exception {
		VideoRecorder_utlity.startRecord("Login with invalid mail valid password");
		String warning_Alert = login.loginWithValidMailAndInvalidPassword(driver);
		Thread.sleep(2000);
//		String warning_Alert = "Warning: No match for E-Mail Address and/or Password.";
		AssertJUnit.assertEquals(warning_Alert, "Account Login");
		driver.quit();

	}

}
