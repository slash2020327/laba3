package com.solvd.qa.gui.common.pages;

import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public abstract class CommonCartPage extends BasePage {

    public CommonCartPage(WebDriver driver) {
        super(driver);
    }

    public abstract BigDecimal getSumOfPrices();

    public abstract BigDecimal getTotal();

    public abstract void goToCheckout();

}
