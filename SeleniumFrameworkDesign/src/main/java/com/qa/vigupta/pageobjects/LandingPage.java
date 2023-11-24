package com.qa.vigupta.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.vigupta.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	// Class variable
	WebDriver driver;

	// Constructor of 'LoginPage' class
	public LandingPage(WebDriver driver) {
		super(driver); // super(); will call the constructor of base class
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

	// Page-object model

	// WebElement userEmails = driver.findElement(By.id("userEmail"));
	// WebElement userPassw = driver.findElement(By.id("userPassword"));
	// WebElement butnLogin = driver.findElement(By.id("login"));

	// pageFactory Design Technique

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPass;

	@FindBy(id = "login")
	WebElement btnLogin;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement tostErrorMessage;

	public ProductCatalouge loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		btnLogin.click();

		ProductCatalouge pc = new ProductCatalouge(driver);
		return pc;
	}
	
	public String getErrorMessage()
	{
		waitForElementToAppear(tostErrorMessage);
	    return tostErrorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
