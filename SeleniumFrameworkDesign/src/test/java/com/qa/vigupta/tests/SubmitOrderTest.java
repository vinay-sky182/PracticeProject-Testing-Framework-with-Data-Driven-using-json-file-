package com.qa.vigupta.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.vigupta.pageobjects.CartPage;
import com.qa.vigupta.pageobjects.CheckoutPage;
import com.qa.vigupta.pageobjects.ConfirmationPage;
import com.qa.vigupta.pageobjects.OrderPage;
import com.qa.vigupta.pageobjects.ProductCatalouge;
import com.qa.vigupta.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		String countryName = "FINLAND";

//		LandingPage lPage = launchApplication();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		LandingPage lp = new LandingPage(driver);
//		lp.goTo();

		ProductCatalouge pc = lPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("product"));

		CartPage cp = pc.goToCartPage();

		Boolean match = cp.VerifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);
		CheckoutPage checkOP = cp.goToCheckout();
		checkOP.selectCountry("fiNLanD");
		List<WebElement> country1 = checkOP.getCountryList();
		checkOP.selectCountryName(countryName);
		ConfirmationPage conPage = checkOP.submitOrder();
		String confirmMsgString = conPage.getConfirmationMessage();

		Assert.assertEquals(confirmMsgString, "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistory() {
		ProductCatalouge pc = lPage.loginApplication("vigupta.kws@gmail.com", "P@ssw0rd@94");
		OrderPage op = pc.goToOrderPage();
		Assert.assertTrue(op.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDatatoMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\vigupta\\data\\PurchaseOrder.json");
		
//		 Object[][] dataArray = new Object[data.size()][1];
//		    for (int i = 0; i < data.size(); i++) {
//		        dataArray[i][0] = data.get(i);
//		    }
//		    return dataArray;
		return new Object[][] {{ data.get(0)}, {data.get(1) }};

//		return new Object[][] {{"vigupta.kws@gmail.com", "P@ssw0rd@94", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};

	}
}

//      ng-tns-c31-0 la-3x la-ball-scale-multiple ng-star-inserted 