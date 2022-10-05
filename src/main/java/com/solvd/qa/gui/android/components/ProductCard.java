package com.solvd.qa.gui.android.components;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.components.CommonProductCard;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

public class ProductCard extends CommonProductCard implements IMobileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCard.class);

    @FindBy(xpath = ".//a[@class='c-text']")
    private ExtendedWebElement productName;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void addToCart() {
        try {
            waitUntil(ExpectedConditions.elementToBeClickable(addToCartButton.getElement()), MAXIMAL_TIMEOUT);
            addToCartButton.click();
        } catch (Exception e) {
            LOGGER.warn("Element click intercepted, trying to click once again with swipe");
            swipeUp(1);
            addToCartButton.click();
        }
    }

    @Override
    public void addToCompare() {
        try {
            waitUntil(ExpectedConditions.elementToBeClickable(addToCompareButton.getElement()), MAXIMAL_TIMEOUT);
            addToCompareButton.click();
        } catch (Exception e) {
            LOGGER.warn("Element click intercepted, trying to click once again with swipe");
            swipeUp(1);
            addToCompareButton.click();
        }
    }

    @Override
    public String getProductName() {
        return this.productName.getText();
    }
}
