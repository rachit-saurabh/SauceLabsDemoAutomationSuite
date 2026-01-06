package com.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.Base.Base;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginPageTest extends Base
{
	LoginPage lp;
	HomePage hp;
	CartPage cp;
	
	@BeforeMethod
    public void setupPages() 
	{
        lp = new LoginPage(driver);
        hp = new HomePage(driver);
        cp = new CartPage(driver);
    }
	
	@Test
	public void verifySuccessProductOrderE2E() throws InterruptedException
	{		
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
		lp.enterUserName();
		lp.enterPassword();
		lp.clickOnLoginBtn();
		hp.verifyHomePageHeader();
		hp.clickOnHamburgerMenuIcon();
		hp.selectLogoutHamburgerMenuOption();
		lp.enterUserName();
	}
	
	@Test
	public void verifyAboutPageFunctionality() throws Exception 
	{
		lp.enterUserName();
		lp.enterPassword();
		lp.clickOnLoginBtn();
		hp.verifyHomePageHeader();
		hp.clickOnHamburgerMenuIcon();
		hp.selectAboutHamburgerMenuOption();
		String actualText = hp.getAboutActualPageText();
		String expectedText = "Build apps users love with AI-driven quality";
		Assert.assertEquals(actualText, expectedText);
		driver.navigate().back();
	}
	
	@Test
	public void verifyHamburgerMenuOptions(List<String> actual, List<String> expected) 
	{
		lp.enterUserName();
		lp.enterPassword();
		lp.clickOnLoginBtn();
		hp.verifyHomePageHeader();
		hp.clickOnHamburgerMenuIcon();
		Assert.assertEquals(
                actual,
                expected,
                "Hamburger menu items mismatch!"
        );
	}
}