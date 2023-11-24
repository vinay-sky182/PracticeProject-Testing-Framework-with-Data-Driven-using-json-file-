package com.qa.vigupta.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.vigupta.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
    WebDriver driver;
    
    @FindBy(css = ".cart h3")
    List<WebElement> cartProducts;
    
    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;
    
   
	public CartPage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		CheckoutPage cop = new CheckoutPage(driver);
		return cop;
	}

}
