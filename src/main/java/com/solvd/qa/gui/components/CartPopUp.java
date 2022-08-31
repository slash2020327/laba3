package com.solvd.qa.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class CartPopUp extends BaseComponent {

    @FindBy(xpath = ".//a[text()='Перейти в корзину']")
    private ExtendedWebElement goToCartButton;

    @FindBy(xpath = ".//a[text()='Вернуться к покупкам']")
    private ExtendedWebElement backToShoppingButton;

    public CartPopUp(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void goToCart() {
        goToCartButton.click();
    }

    public void backToShopping() {
        backToShoppingButton.click();
    }
}
