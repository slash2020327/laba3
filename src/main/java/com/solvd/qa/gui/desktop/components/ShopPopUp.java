package com.solvd.qa.gui.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.qa.gui.common.components.BaseComponent;
import com.solvd.qa.gui.desktop.pages.CheckoutPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.solvd.qa.constants.WaitTime.MAXIMAL_TIMEOUT;

public class ShopPopUp extends BaseComponent {

    @FindBy(xpath = "//a[@class='btn btn--block']")
    private ExtendedWebElement chooseShopButton;

    public ShopPopUp(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CheckoutPage chooseShop() {
        waitUntil(ExpectedConditions.visibilityOf(chooseShopButton.getElement()), MAXIMAL_TIMEOUT);
        chooseShopButton.click();
        return new CheckoutPage(getDriver());
    }
}
