package com.solvd.qa.gui.desktop.components;

import com.solvd.qa.gui.common.components.CommonProductCard;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCard extends CommonProductCard {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCard.class);

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void addToCart() {
        tryToClickIntercepted(addToCartButton);
    }
}
