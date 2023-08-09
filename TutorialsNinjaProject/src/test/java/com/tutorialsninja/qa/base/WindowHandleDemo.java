package com.tutorialsninja.qa.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WindowHandleDemo extends Base
{
	public static WebDriver driver;
	@Parameters({"browserName","url"})
	@BeforeMethod
	public void setUp(String browserName, String url) throws InterruptedException, IOException {
		driver = launchBrowser(browserName,url);
		Thread.sleep(2000);
	}
	@Test
	public void switchToNaukri() throws InterruptedException {
		String parentWindow = driver.getWindowHandle();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement playStore= driver.findElement(By.xpath("(//span[@class='gb-icon'])[6]"));
		js.executeScript("arguments[0].scrollIntoView(true);",playStore);
		playStore.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@class='gb-icon'])[7]")).click();
		String parentWin=driver.getWindowHandle();
		Set<String>allWin=driver.getWindowHandles();
		
		for(String s:allWin) {
			
			String title=driver.switchTo().window(s).getTitle();
			
			System.out.println(title);
			
			if(!(title.contains("Window Handling in Selenium with All Examples"))) {
				String getText = driver.getTitle();
				System.out.println(getText);
				driver.close();
			}
		}
		
		Thread.sleep(2000);
		driver.switchTo().window(parentWin);
		WebElement text = driver.findElement(By.xpath("//h1[@class='entry-title']"));
		js.executeScript("arguments[0].scrollIntoView(true);",text);
		String text1 = text.getText();
		System.out.println(text1);
        System.out.println(driver.getTitle());
        
        
        
	}

	
	
	

}
