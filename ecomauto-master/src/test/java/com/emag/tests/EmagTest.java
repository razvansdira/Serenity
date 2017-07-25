package com.emag.tests;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmagTest {
	WebDriver driver;
	WebElement addToCart, search, cartDetails, dynamicElement, dynamicElement2, dynamicElement3;
	Random rand = new Random();
	String searchedProduct = "telefon";

	@Before
	public void openChromeTest() {
		System.setProperty("webdriver.chrome.driver", "C://Users//razvansidra//ecomauto-master//drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://emag.ro");
	}

	@After
	public void closeChromeTest() {
		driver.quit();
	}

	@Test
	public void searchTest() {
		search = driver.findElement(By.cssSelector("#emg-input-autosuggest"));
		search.sendKeys(searchedProduct);
		search.submit();
		List<WebElement> pages = driver.findElements(By.cssSelector(".emg-pagination-no"));
		// pageNo =
		// driver.findElement(By.cssSelector("emg-pagination-no:last-child")).getText();
		int noOfPages = Integer.parseInt(pages.get(pages.size() - 1).getText());
		System.out.println(noOfPages);
		int pageNumber = rand.nextInt(noOfPages) + 1;
		System.out.println(pageNumber);
		driver.get("https://emag.ro/search/" + searchedProduct + "/p" + pageNumber);
		dynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".lazy")));
		List<WebElement> products = driver.findElements(By.cssSelector(".lazy"));
		int product = rand.nextInt(products.size()) + 1;
		System.out.println(product);
		products.get(product).click();
		String title = driver.findElement(By.cssSelector(".page-title")).getText();
		dynamicElement3 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector(".btn.btn-primary.btn-emag.btn-block.yeahIWantThisProduct.gtm_680klw")));
		addToCart = driver
				.findElement(By.cssSelector(".btn.btn-primary.btn-emag.btn-block.yeahIWantThisProduct.gtm_680klw"));
		addToCart.click();
		dynamicElement2 = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn")));
		cartDetails = driver.findElement(By.cssSelector(".btn"));
		cartDetails.submit();
		String productTitle = driver.findElement(By.cssSelector(".line-item-title.main-product-title")).getText();
		Assert.assertEquals(title, productTitle);
	}
}