package com.solvd.qa.gui.common.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class CommonProductCard extends BaseComponent {

    @FindBy(xpath = ".//span[contains(@class, 'ic-compare')]")
    protected ExtendedWebElement addToCompareButton;

    @FindBy(xpath = ".//span[text()='В корзину']/ancestor::button")
    protected ExtendedWebElement addToCartButton;

    @FindBy(xpath = ".//a[@class='c-text']")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//div[@class='c-sku']")
    private ExtendedWebElement productCode;

    public CommonProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductCode() {
        return productCode.getText().split(":")[1].trim();
    }

    public void addToCompare() {
        tryToClickIntercepted(addToCompareButton);
    }

    public abstract void addToCart();

    public String getProductName() {
        return productName.getText();
    }
}
