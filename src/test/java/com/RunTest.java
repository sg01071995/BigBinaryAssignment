package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RunTest extends CommonMethods {
	WebDriver driver;

	@BeforeTest
	public void closeRunningBrowsers() throws IOException
	{
		Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
		Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
	}
	
	@BeforeClass
	public void beforeClass() throws FileNotFoundException, IOException
	{
		prop.load(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\locators.properties"));
	}
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException
	{
		launchBrowser("https://staging.aceinvoice.com/sign_in");
	}
	
	@DataProvider(name="data")
	public String[][] getData() throws IOException
	{
		return readData();
	}
	
	@Test(dataProvider="data")
	public void runTest(String email, String password, String fName, String lName, String orgName, String orgEMail) throws IOException
	{
		getLocator("signUpButton").click();
		getLocator("inputEmail").sendKeys(email);
		getLocator("getStartedButton").click();
		getLocator("inputPassword").sendKeys(password);
		getLocator("inputPasswordConf").sendKeys(password);
		getLocator("continueButton").click();
		getLocator("inputFName").sendKeys(fName);
		getLocator("inputLastName").sendKeys(lName);
		getLocator("ContinueButton").click();
		getLocator("inputOrgName").sendKeys(orgName);
		getLocator("inputOrgEmail").sendKeys(orgEMail);
		getLocator("ContinueButton").click();
		getLocator("skipButton").click();
		getLocator("continueToAppButton").click();
		
		String actualName = getLocator("nameValue").getText();
		String actualEmail = getLocator("emailValue").getText();
		
		SoftAssert sAssert = new SoftAssert();
		
		sAssert.assertEquals(actualName, fName + " " + lName, "Name verification failed");
		sAssert.assertEquals(actualEmail, email, "EMail verification failed");
		
		sAssert.assertAll();		
	}
	
	@AfterMethod
	public void afterMethod()
	{
		tearDown();
	}
}
