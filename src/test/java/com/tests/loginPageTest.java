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
		Thread.sleep(1000);
		cp.clickOnContinueButton();
		Thread.sleep(1000);
		cp.clickOnFinishButton();
		cp.verifySuccessMessage();
		Assert.assertEquals(cp.verifySuccessMessage(), "Thank you for your order!");
		Thread.sleep(2000);
	}

}
