package com.solvd.qa.gui.common.pages;

import com.solvd.qa.gui.desktop.pages.PageWithProductCards;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CommonSearchPage extends PageWithProductCards {

    public CommonSearchPage(WebDriver driver) {
        super(driver);
    }

    public abstract void openCart();

    public abstract void openComparePage();

    public abstract void chooseCategoryByName(String name);

    public abstract void addProductsToCompare(List<String> products);

    public abstract void addProductsToCardByProductCodes(List<String> productCodes);
}
