package com.solvd.qa.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPopUp extends BaseComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartPopUp.class);

    @FindBy(xpath = ".//a[text()='Перейти в корзину']")
    private ExtendedWebElement goToCartButton;

    @FindBy(xpath = ".//a[text()='Вернуться к покупкам']")
    private ExtendedWebElement backToShoppingButton;

    public CartPopUp(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void goToCart() {
        try {
            goToCartButton.click();
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("Element is not intractable, trying to click again");
            goToCartButton.click();
        }
    }

    public void backToShopping() {
        try {
            backToShoppingButton.click();
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("Element is not intractable, trying to click again with pause");
            pause(1);
            backToShoppingButton.click();
        }
    }
}
