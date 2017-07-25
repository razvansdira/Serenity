package com.emag.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

//import static org.apache.tools.ant.util.FileUtils.rand;

/**
 * Created by razvansidra on 7/25/2017.
 */

@DefaultUrl("www.emag.ro")

public class ProductPage extends PageObject {

//    WebDriver driver;
//    Random rand = new Random();
//
//    @FindBy(css=".link-imagine")
//    public WebElement dynamicElement;
//
//    public void redirectToRandomProductPage(String selectedProduct){
//        dynamicElement = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".link-imagine")));
//        List<WebElement> products = driver.findElements(By.cssSelector(".link-imagine"));
//        int product = rand.nextInt(products.size()) + 1;
//        System.out.println(product);
//        products.get(product).click();
//        String title = driver.findElement(By.cssSelector(".page-title")).getText();
//    }

    @FindBy(css = ".btn.btn-primary.btn-emag.btn-block.yeahIWantThisProduct.btn-lg")
    private WebElement addToCartButton;

    public void clickOnAddToCartButton() {
        addToCartButton.click();
    }

    public String getProductTitle() {
        return getDriver().findElement(By.cssSelector(".page-title")).getText();
    }

    public void clickOnShowCartDetails() {
        getDriver().findElement(By.cssSelector(".btn")).submit();
    }
}