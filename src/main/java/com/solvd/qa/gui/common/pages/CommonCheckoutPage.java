package com.solvd.qa.gui.common.pages;

import org.openqa.selenium.WebDriver;

public abstract class CommonCheckoutPage extends BasePage {

    public CommonCheckoutPage(WebDriver driver) {
        super(driver);
    }

    public abstract void fillCheckoutForm();

    public abstract boolean isOrderFormsComplete();

}
