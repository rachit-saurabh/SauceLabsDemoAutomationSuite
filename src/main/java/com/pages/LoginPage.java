package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.Utilities;

public class LoginPage 
{
	
	public WebDriver driver;
	Utilities util;
	
	public LoginPage(WebDriver driver) 
	{
		this.driver = driver;
		this.util = new Utilities(driver);
	}
	
	By usernameTextBox = By.id("user-name");
	
	By passwordTextBox = By.id("password");
	
	By loginBtn = By.id("login-button");
	
	public void enterUserName() 
	{
		util.waitingForElementToBeVisible(usernameTextBox);
		driver.findElement(usernameTextBox).sendKeys("standard_user");
	}
	
	public void enterPassword() 
	{
		util.waitingForElementToBeVisible(passwordTextBox);
		driver.findElement(passwordTextBox).sendKeys("secret_sauce");
	}
	
	public void clickOnLoginBtn() 
	{
		util.waitingForElementToBeVisible(loginBtn);
		util.waitingForElementToBeClickable(loginBtn);
		driver.findElement(loginBtn).click();
	}
	
	

}
