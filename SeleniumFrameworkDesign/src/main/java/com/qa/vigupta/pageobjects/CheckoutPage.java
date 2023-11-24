package com.qa.vigupta.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.vigupta.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement placeOrderClick;

	@FindBy(css = ".ta-item")
	List<WebElement> countriesList;

	By results = By.cssSelector(".ta-results");
	By addToCountry = By.cssSelector("input[placeholder='Select Country']");

	public void selectCountry(String countryName) {
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();
	}

	public List<WebElement> getCountryList() {
		waitForElementToAppear(results);
		return countriesList;
	}

	public WebElement getCountryByName(String countryName) {
		WebElement cuntry = getCountryList().stream().filter(c -> c
				.findElement(By.cssSelector("span[class='ng-star-inserted']")).getText().equalsIgnoreCase("Finland"))
				.findFirst().orElse(null);
		return cuntry;
	}

	public void selectCountryName(String countryName) {
		WebElement con = getCountryByName(countryName);
		con.click();
	}

	public ConfirmationPage submitOrder() {
		placeOrderClick.click();
		return new ConfirmationPage(driver);
	}
}
