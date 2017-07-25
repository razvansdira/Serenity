package com.emag.steps;

import com.emag.pages.CartPage;
import com.emag.pages.EmagHomePage;
import com.emag.pages.ProductPage;
import com.emag.pages.SearchResultsList;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import static org.junit.Assert.assertTrue;

public class EmagSteps {

    EmagHomePage emagHomePage;
    SearchResultsList searchResultsList;
    ProductPage productPage;
    CartPage cartPage;

    @Step
    public void navigateTo(String url) {
        emagHomePage.navigateTo(url);
    }

    @Step
    public void searchTerm(String keyword) {
        emagHomePage.inputSearchTerm(keyword);
    }

    @Step
    public void navigateToRandomSearchPage(String keyword) {
        searchResultsList.redirectToRandomProductsListPage(keyword);
    }

//	@Step
//	public void navigateToRandomProductPage(String keyword){ productPage.redirectToRandomProductPage(keyword);}

    @StepGroup
    public void searchTermAndNavigateToRandomSearchPage(String keyword) {
        searchTerm(keyword);
        navigateToRandomSearchPage(keyword);
    }

    @Step
    public void clickOnRandomProduct() {
        searchResultsList.clickOnRandomProduct();
    }

    @Step
    public void clickOnAddToCartButton() {
        productPage.clickOnAddToCartButton();
    }

    @Step
    public void setSessionProductTitle() {
        Serenity.setSessionVariable("productTitle").to(productPage.getProductTitle());
    }

    @StepGroup
    public void getProductTitleAndClickOnAddToCartButton() {
        setSessionProductTitle();
        clickOnAddToCartButton();
    }

    @Step
    public void clickOnShowCartDetails() {
        productPage.clickOnShowCartDetails();
    }

    @Step
    public void checkByTitleIfProductWasAddedInCart() {
        assertTrue(cartPage.isProductInCart(Serenity.sessionVariableCalled("productTitle").toString()));
    }
}