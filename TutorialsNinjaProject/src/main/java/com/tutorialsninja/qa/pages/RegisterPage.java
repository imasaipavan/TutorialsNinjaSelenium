package com.tutorialsninja.qa.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class RegisterPage {
	public  WebDriver driver;

	public static Properties prop;

	public RegisterPage(WebDriver driver) throws InterruptedException, IOException {
//		PageFactory.initElements(driver, RegisterPage.class);
		prop = new Properties();
		FileInputStream file = new FileInputStream("./src/main/java/com/tutorialsninja/qa/config/config.properties");
		prop.load(file);
		this.driver = driver;
//		myAccount.click();
//		Thread.sleep(2000);
//		register.click();
	}

	

	@FindBy(how=How.XPATH,using="(//*[@class='hidden-xs hidden-sm hidden-md'])[3]")
	WebElement myAccount;

	@FindBy(how=How.LINK_TEXT,using="Register")
	WebElement register;

	@FindBy(how=How.XPATH,using="//*[@name='firstname']")
	WebElement firstName;

	@FindBy(how=How.XPATH,using="//*[@name='lastname']")
	WebElement lastName;

	@FindBy(how=How.XPATH,using="//*[@name='email']")
	WebElement email;

	@FindBy(how=How.XPATH,using="//*[@name='telephone']")
	WebElement mobile;

	@FindBy(how=How.XPATH,using="//*[@name='password']")
	WebElement password;

	@FindBy(how=How.XPATH,using="//*[@name='confirm']")
	WebElement confirmPassword;

	@FindBy(how=How.XPATH,using="//*[@name='agree']")
	WebElement agree;

	@FindBy(how=How.XPATH,using="//input[@type='submit']")
	WebElement submit;

	@FindBy(how=How.XPATH,using="(//h1)[2]")
	WebElement successMessage;

	@FindBy(how=How.XPATH,using="//*[@class='alert alert-danger alert-dismissible']")
	WebElement warningMessage;
	

	@Test(priority = 1)
	public void registerByEnteringAllMadatoryFields() throws InterruptedException {

		firstName.sendKeys(prop.getProperty("firstName"));
		lastName.sendKeys(prop.getProperty("lastName"));
		email.sendKeys(System.currentTimeMillis()+"@gmail.com");
		mobile.sendKeys(prop.getProperty("mobile"));
		password.sendKeys(prop.getProperty("password"));
		confirmPassword.sendKeys(prop.getProperty("password"));
		agree.click();
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(2000);
		String successPage = successMessage.getText();
		Assert.assertEquals(successPage, "Your Account Has Been Created!");

	}

	@Test(priority = 2)
	public void registerWithAlreadyRegisteredMail() throws InterruptedException {

		firstName.sendKeys(prop.getProperty("firstName"));
		lastName.sendKeys(prop.getProperty("lastName"));
		email.sendKeys(prop.getProperty("email"));
		mobile.sendKeys(prop.getProperty("mobile"));
		password.sendKeys(prop.getProperty("password"));
		confirmPassword.sendKeys(prop.getProperty("password"));
		agree.click();
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(2000);
		String warningPage = warningMessage.getText();
		Assert.assertEquals(warningPage, "Warning: E-Mail Address is already registered!");

	}

}
