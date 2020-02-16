package com;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommonMethods extends BasePage {
	
	public static Properties prop = new Properties();
	
	public WebElement getLocator(String element) throws IOException
	{
		switch(prop.getProperty(element).split("<>")[0])
		{
		case "xpath":
			return wDriver.findElement(By.xpath(prop.getProperty(element).split("<>")[1]));
		case "cssSelector" :
			return wDriver.findElement(By.cssSelector(prop.getProperty(element).split("<>")[1]));
		}
		return null;
	}
	
}
