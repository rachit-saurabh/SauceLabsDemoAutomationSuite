package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utilities.Utilities;

public class HomePage {
	
	WebDriver driver;
	Utilities util;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		this.util = new Utilities(driver);
	}
	
	By homePageHeaderText = By.xpath("//*[@class='app_logo' and contains(text(),'Swag Labs')]");
	
	By productItems = By.xpath("//*[@data-test='inventory-item']");
	
	By firstAddToCardButton = By.xpath("(//*[@data-test='inventory-item'])[1]//*[contains(@id,'add-to-cart')]");
	
	By shoppingCartBadge = By.xpath("//*[@data-test='shopping-cart-badge']");
	
	By shoppingCartLink = By.xpath("//*[@data-test='shopping-cart-link']");
	
	public void verifyHomePageHeader()
	{
		util.waitingForElementToBeVisible(homePageHeaderText);
	}
	
	public void selectFirstInventoryItem() 
	{
		List<WebElement> inventoryItems = driver.findElements(productItems);
		System.out.println(inventoryItems.size());
		driver.findElement(firstAddToCardButton).click();
	}
	
	public String getCartBadgeCount() 
	{
		String cartBadgeCount = driver.findElement(shoppingCartBadge).getText();
		return cartBadgeCount;
	}
	
	public void selectShoppingCartIcon() 
	{
		driver.findElement(shoppingCartLink).click();
	}

}
