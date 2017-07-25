package com.emag.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class SearchResultsList extends PageObject {
    Random random = new Random();

//	@FindBy(css = ".emg-fashion-view-dots + a")
//	public WebElement pagesNumbers;
//
//	public void redirectToRandomProductsList(String searchedProduct) {
//		int noOfPages = Integer.parseInt(pagesNumbers.getText().trim());
//		System.out.println(noOfPages);
//		int pageNumber = new Random().nextInt(noOfPages);
//		System.out.println(pageNumber);
//		getDriver().get("https://emag.ro/search/" + searchedProduct + "/p" + pageNumber);
//	}

    @FindBy(css="[class~=emg-pagination-no]")
    public List<WebElement> pagesBigResolution;

    @FindBy(css="li.active > span")
    public List<WebElement> pagesSmallResolution;

    @FindBy(css=".lazy")
    public List<WebElement> productsBigResolution;

    @FindBy(css=".thumbnail.js-product-url")
    public List<WebElement> productsSmallResolution;

    public void redirectToRandomProductsListPage(String keyword) {
        Integer pageNr = 1;
        String[] split;
        int i;

        if(pagesBigResolution.size() != 0) {
            while (true) {
                pageNr = Math.abs(random.nextInt()) % Integer.parseInt(pagesBigResolution.get(pagesBigResolution.size()-1).getText());
                if (pageNr != 0)
                    break;
            }
        } else {
            if(pagesSmallResolution.size() != 0) {
                split = pagesSmallResolution.get(0).getText().split(" ");
                pageNr = Math.abs(random.nextInt()) % Integer.parseInt(split[2]);
            }
        }

        if(pageNr != 1) {
            split = keyword.split(" ");
            keyword = "";
            for(i = 0; i < split.length; i++) {
                keyword += split[i];
                if(i != split.length - 1) {
                    keyword += "+";
                }
            }
            getDriver().get("https://www.emag.ro/search/" + keyword + "/p" + pageNr);
        }
    }

    public void clickOnRandomProduct() {

        if(productsBigResolution.isEmpty()) {
            Integer productsNr = Math.abs(random.nextInt()) % productsSmallResolution.size();
            WebElement product = productsSmallResolution.get(productsNr);

            product.click();
        } else {
            Integer productsNr = Math.abs(random.nextInt()) % productsBigResolution.size();
            WebElement product = productsBigResolution.get(productsNr);

            product.click();
        }
    }
}