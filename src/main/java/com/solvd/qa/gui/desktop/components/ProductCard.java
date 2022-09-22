package com.solvd.qa.gui.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCard extends BaseComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCard.class);

    @FindBy(xpath = ".//span[contains(@class, 'ic-compare')]")
    private ExtendedWebElement addToCompareButton;

    @FindBy(xpath = ".//span[text()='В корзину']/ancestor::button")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = ".//a[@class='c-text']")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//div[@class='c-sku']")
    private ExtendedWebElement productCode;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getProductCode() {
        return productCode;
    }

    public void addToCompare() {
        tryToClickIntercepted(addToCompareButton);
    }

    public void addToCart() {
        tryToClickIntercepted(addToCartButton);
    }

    public String getProductName() {
        return productName.getText();
    }
}
