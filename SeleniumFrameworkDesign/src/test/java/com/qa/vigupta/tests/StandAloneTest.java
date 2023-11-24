package com.qa.vigupta.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.vigupta.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		// Creating the object of 'LoginPage' class to pass the 'driver'
		LandingPage login = new LandingPage(driver);
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("vigupta.kws@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("P@ssw0rd@94");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement product = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.cssSelector("div[class*='ngx-spinner-overlay']"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		WebElement country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		Actions act = new Actions(driver);
		act.sendKeys(country, "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));

		WebElement count = countries.stream().filter(c -> c
				.findElement(By.cssSelector("span[class='ng-star-inserted']")).getText().equalsIgnoreCase("India"))
				.findFirst().orElse(null);
		count.click();
//        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMsgString = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(confirmMsgString, "THANKYOU FOR THE ORDER.");
		driver.close();

	}

}

//ng-tns-c31-0 la-3x la-ball-scale-multiple ng-star-inserted 