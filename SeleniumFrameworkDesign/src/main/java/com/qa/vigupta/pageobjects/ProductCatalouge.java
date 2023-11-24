package com.qa.vigupta.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.vigupta.abstractComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {

	// Class variable
	WebDriver driver;

	// Constructor of 'LoginPage' class
	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;

		/*
		 * 'this.driver' is the representing to class variable. 'driver' is representing
		 * to constructor's parameter variable.
		 */

		PageFactory.initElements(driver, this);
		// 'this' keyword it represent the current class object.
		// Instead of 'this' keyword we can use 'LandingPage.class' because It returns
		// the
		// 'LandingPage' Class object that represents the LoginPage.
	}

	// pageFactory Design Technique

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = "div[class*='ngx-spinner-overlay']")
	WebElement spinner;

	By prod = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(prod);
		return products;

	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement pro = getProductByName(productName);
		pro.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);

	}

}
