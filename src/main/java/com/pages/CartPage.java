package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utilities.Utilities;

public class CartPage {
	
	WebDriver driver;
	Utilities util;
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		this.util = new Utilities(driver);
	}
	
	By checkoutButton = By.id("checkout");
	
	By removeButton = By.xpath("//button[contains(text(),'Remove')]");
	
	By firstName = By.name("firstName");
	
	By lastName = By.name("lastName");
	
	By postalCode = By.name("postalCode");
	
	By continueButton = By.name("continue");
	
	By finishButton = By.name("finish");
	
	By successMessage = By.xpath("//*[@data-test='complete-header']");
	
	public void clickOnCheckoutButton() 
	{
		util.waitingForElementToBeVisible(checkoutButton);
		util.waitingForElementToBeClickable(checkoutButton);
		driver.findElement(checkoutButton).click();
	}
	
	public void enterFirstName() 
	{
		util.waitingForElementToBeVisible(firstName);
		driver.findElement(firstName).clear();
		util.enterTextIntoTextField(firstName, "Rachit");
	}
	
	public void enterLastName() 
	{
		util.waitingForElementToBeVisible(lastName);
		driver.findElement(lastName).clear();
		util.enterTextIntoTextField(lastName, "Saurabh");
	}
	
	public void enterPostalCode() 
	{
		util.waitingForElementToBeVisible(postalCode);
		driver.findElement(postalCode).clear();
		util.enterTextIntoTextField(postalCode, "411047");
	}
	
	public void clickOnContinueButton() 
	{
		util.scrollToElement(continueButton);
		driver.findElement(continueButton).click();
	}
	
	public void clickOnFinishButton() 
	{
		util.scrollToElement(finishButton);
		driver.findElement(finishButton).click();
	}
	
	public String verifySuccessMessage() 
	{
		util.waitingForElementToBeVisible(successMessage);
		return(driver.findElement(successMessage).getText());
	}
}