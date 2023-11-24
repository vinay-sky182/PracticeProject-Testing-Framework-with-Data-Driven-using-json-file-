package com.qa.vigupta.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.vigupta.abstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;


	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
