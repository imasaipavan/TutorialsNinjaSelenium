package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.EditInfoPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class EditInfoTest extends Base {
	
	public EditInfoTest() throws IOException {
		super();


	}
	public WebDriver driver;
	HomePage home;
	LoginPage login;
	EditInfoPage editInfo;
	long timeStamp = System.currentTimeMillis();


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
	
	@Test
	public void editInfo() throws Exception {
		VideoRecorder_utlity.startRecord("Edit Info Data");
		login = new LoginPage(driver);
		login.loginWithValidCredentials(driver);
		String expectedTitle = editInfo.editAccInfo(driver);
		Assert.assertEquals("Success: Your account has been successfully updated.", expectedTitle);
		
	}

}
