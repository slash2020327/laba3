package com.solvd.qa.gui.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.components.BaseComponent;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

public class CartPopUp extends BaseComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartPopUp.class);

    @FindBy(xpath = "//div[@class='m-controls']/a[text()='Перейти в корзину']")
    private ExtendedWebElement goToCartButton;

    @FindBy(xpath = "//a[text()='Вернуться к покупкам']")
    private ExtendedWebElement backToShoppingButton;

    public CartPopUp(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void backToShopping() {
        try {
            waitUntil(ExpectedConditions.visibilityOf(backToShoppingButton.getElement()), MAXIMAL_TIMEOUT);
            backToShoppingButton.click();
        } catch (ElementClickInterceptedException a) {
            waitUntil(ExpectedConditions.elementToBeClickable(backToShoppingButton.getElement()), MAXIMAL_TIMEOUT);
            backToShoppingButton.click();

        } catch (ElementNotInteractableException e) {
            LOGGER.warn("Element is not intractable, trying to click again with pause");
            waitUntil(ExpectedConditions.elementToBeClickable(backToShoppingButton.getElement()), MAXIMAL_TIMEOUT);
            backToShoppingButton.click();
        }
    }
}
