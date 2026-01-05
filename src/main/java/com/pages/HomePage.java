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
	
	By hamburgerMenuIcon = By.id("react-burger-menu-btn");
	
	By hambergurMenuOptions = By.xpath("//*[@class='bm-item menu-item']");
	
	By logoutOption = By.xpath("//*[@class='bm-item menu-item' and contains(text(),'Logout')]");
	
	By aboutOption = By.xpath("//*[@class='bm-item menu-item' and contains(text(),'About')]");
	
	By resetAppStateOption = By.xpath("//*[@class='bm-item menu-item' and contains(text(),'Reset App State')]");
	
	By allitemsOption = By.xpath("//*[@class='bm-item menu-item' and contains(text(),'All Items')]");
	
	By aboutPageText = By.xpath("//*[contains(text(),'Build apps users love with AI-driven quality')]");
	
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
	
	public void clickOnHamburgerMenuIcon() 
	{
		util.waitingForElementToBeVisible(hamburgerMenuIcon);
		driver.findElement(hamburgerMenuIcon).click();
	}
	
	public void selectLogoutHamburgerMenuOption() 
	{
		util.selectHamburgerMenuOptionByText(hambergurMenuOptions, "Logout");
	}
	
	public void selectAboutHamburgerMenuOption() 
	{
		util.selectHamburgerMenuOptionByText(hambergurMenuOptions, "About");
		util.waitingForElementToBeVisible(aboutPageText);
	}
	
	public String getAboutActualPageText() 
	{
		String aboutPage = driver.findElement(aboutPageText).getText();
		return aboutPage;
	}

}
