package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.Base.Base;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.loginPage;

public class loginPageTest extends Base
{
	loginPage lp;
	HomePage hp;
	CartPage cp;
	
	@Test
	public void verifySuccessProductOrderE2E() throws InterruptedException
	{
		lp = new loginPage(driver);
		hp = new HomePage(driver);
		cp = new CartPage(driver);
		
		lp.enterUserName();
		lp.enterPassword();
		lp.clickOnLoginBtn();
		hp.verifyHomePageHeader();
		hp.selectFirstInventoryItem();
		System.out.println(hp.getCartBadgeCount());
		int actualCount = Integer.parseInt(hp.getCartBadgeCount());
		Assert.assertEquals(actualCount, 1);
		hp.selectShoppingCartIcon();
		cp.clickOnCheckoutButton();
		cp.enterFirstName();
		cp.enterLastName();
		cp.enterPostalCode();
		cp.clickOnContinueButton();
		cp.clickOnFinishButton();
		cp.verifySuccessMessage();
		Assert.assertEquals(cp.verifySuccessMessage(), "Thank you for your order!");
	}

	@Test
	public void verifyLogoutFunctionality() throws Exception 
	{
		lp = new loginPage(driver);
		hp = new HomePage(driver);
		lp.enterUserName();
		lp.enterPassword();
		lp.clickOnLoginBtn();
		hp.verifyHomePageHeader();
		hp.clickOnHamburgerMenuIcon();
		Thread.sleep(1000);
		hp.selectLogoutHamburgerMenuOption();
		lp.enterUserName();
	}
	
	@Test
	public void verifyAboutPageFunctionality() throws Exception 
	{
		lp = new loginPage(driver);
		hp = new HomePage(driver);
		lp.enterUserName();
		lp.enterPassword();
		lp.clickOnLoginBtn();
		hp.verifyHomePageHeader();
		hp.clickOnHamburgerMenuIcon();
		Thread.sleep(1000);
		hp.selectAboutHamburgerMenuOption();
		String actualText = hp.getAboutActualPageText();
		String expectedText = "Build apps users love with AI-driven quality";
		Assert.assertEquals(actualText, expectedText);
		driver.navigate().back();
		Thread.sleep(2000);
	}
	
	
}