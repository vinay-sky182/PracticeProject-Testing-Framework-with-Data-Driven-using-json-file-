package com.qa.vigupta.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.vigupta.pageobjects.CartPage;
import com.qa.vigupta.pageobjects.ProductCatalouge;
import com.qa.vigupta.testComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation()
	{
		lPage.loginApplication("vigupta.kws@gmal.com", "P@ssw0rd@94");
		
		Assert.assertEquals("Incorrect email password.", lPage.getErrorMessage());	
	}
	
	 @Test 
	 public void productErrorValidation() throws IOException, InterruptedException
	 {

		String productName = "ZARA COAT 3";
//		String countryName = "FINLAND";
		
//		LandingPage lPage = launchApplication();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		LandingPage lp = new LandingPage(driver);
//		lp.goTo();

		ProductCatalouge pc = lPage.loginApplication("vigupta.kws@gmail.com", "P@ssw0rd@94");

		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);

		CartPage cp = pc.goToCartPage();

		Boolean match = cp.VerifyProductDisplay("ZARA COAT 33");

		Assert.assertFalse(match);
	}

}
