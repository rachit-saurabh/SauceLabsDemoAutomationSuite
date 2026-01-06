package com.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Utilities 
{
	
	public WebDriver driver;
	
	public Utilities(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void waitingForElementToBeVisible(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitingForElementToBeClickable(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void handleAlertAccept()
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void handleAlertDismiss()
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public void handleAlertInputFields(String username, String password)
	{
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(username);
		alert.sendKeys(password);
		alert.accept();
	}
	
	public void getAlertText() 
	{
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
	}
	
	public void takesScreenshots() throws IOException 
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = "SauceDemo_Application/test-output";
		File dest = new File(path);
	    Files.copy(src, dest);
	}
	
	public void scrollToElement(By locator)
	{
	    WebElement element = driver.findElement(locator);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void enterTextIntoTextField(By locator, String text)
	{
		driver.findElement(locator).sendKeys(text);
	}
	
	public void selectHamburgerMenuOptionByText(By locator, String Option)
	{
	    List<WebElement> elements = driver.findElements(locator);
	    boolean isFound = false;

	    for (WebElement element : elements) 
	    {
	        if (element.getText().trim().equalsIgnoreCase(Option)) 
	        {
	            element.click();
	            break;
	        }
	    }
	}
	
	public List<String> expectedHamburgerOptions()
	{
		return(Arrays.asList("All Items", "About", "Logout", "Reset App State"));
	}
}
