package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {

	WebDriver driver;
	

	private static final String ALPHA_STRING = "qwertyuiopasdfghjklzxcvbnm";
	private static final String NUMERIC_STRING = "0123456789";
	
	public static HashMap<String,String> data = new HashMap<String,String>();

	public WebDriver launchBrowser(String url) throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);

		return driver;
	}

	public String[][] readData() throws IOException
	{
		return new String[][] {
		{
			randomAlphaString(6) + randomNumericString(3) + "@" + randomAlphaString(4) + ".com",
			"qwewrty",
			randomAlphaString(8),
			randomAlphaString(8),
			randomAlphaString(8),
			randomAlphaString(6) + randomNumericString(3) + "@" + randomAlphaString(4) + ".com"}
		};
	}

	public String randomAlphaString(int length)
	{
		StringBuilder builder = new StringBuilder();
		while (length-- != 0) 
		{
			int character = (int)(Math.random()*ALPHA_STRING.length());
			builder.append(ALPHA_STRING.charAt(character));
		}
		return builder.toString();
	}
	public String randomNumericString(int length)
	{
		StringBuilder builder = new StringBuilder();
		while (length-- != 0) 
		{
			int character = (int)(Math.random()*NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
}
