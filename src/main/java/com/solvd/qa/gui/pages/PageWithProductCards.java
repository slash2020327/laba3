package com.solvd.qa.gui.pages;

import com.solvd.qa.gui.components.ProductCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class PageWithProductCards extends BasePage {

    @FindBy(xpath = "//div[@data-product_id]")
    private List<ProductCard> productCards;

    public PageWithProductCards(WebDriver driver) {
        super(driver);
    }

    private List<ProductCard> getProductCards() {
        return productCards;
    }

    public ProductCard getProductByName(String name) {
        productCards = getProductCards();
        for (ProductCard card : productCards) {
            if (card.getProductName().equals(name)) {
                return card;
            }
        }
        throw new RuntimeException("Unable to find product: " + name);
    }

    public ProductCard getProductByCode(int code) {
        productCards = getProductCards();
        for (ProductCard card : productCards) {
            if (card.getProductCode().getText().contains(String.valueOf(code))) {
                return card;
            }
        }
        throw new RuntimeException("Unable to find product: " + name);
    }
}
